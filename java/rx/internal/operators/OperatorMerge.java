package rx.internal.operators;

import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicLong;
import kotlin.jvm.internal.LongCompanionObject;
import rx.Observable;
import rx.Producer;
import rx.Subscriber;
import rx.exceptions.CompositeException;
import rx.exceptions.MissingBackpressureException;
import rx.exceptions.OnErrorThrowable;
import rx.internal.util.RxRingBuffer;
import rx.internal.util.ScalarSynchronousObservable;
import rx.internal.util.atomic.SpscAtomicArrayQueue;
import rx.internal.util.atomic.SpscExactAtomicArrayQueue;
import rx.internal.util.atomic.SpscUnboundedAtomicArrayQueue;
import rx.internal.util.unsafe.Pow2;
import rx.internal.util.unsafe.SpscArrayQueue;
import rx.internal.util.unsafe.UnsafeAccess;
import rx.subscriptions.CompositeSubscription;

/* loaded from: classes2.dex */
public final class OperatorMerge<T> implements Observable.Operator<T, Observable<? extends T>> {
    final boolean delayErrors;
    final int maxConcurrent;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static final class HolderDelayErrors {
        static final OperatorMerge<Object> INSTANCE = new OperatorMerge<>(true, Integer.MAX_VALUE);

        private HolderDelayErrors() {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static final class HolderNoDelay {
        static final OperatorMerge<Object> INSTANCE = new OperatorMerge<>(false, Integer.MAX_VALUE);

        private HolderNoDelay() {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static final class InnerSubscriber<T> extends Subscriber<T> {
        static final int limit = RxRingBuffer.SIZE / 4;
        volatile boolean done;
        final long id;
        int outstanding;
        final MergeSubscriber<T> parent;
        volatile RxRingBuffer queue;

        public InnerSubscriber(MergeSubscriber<T> mergeSubscriber, long j) {
            this.parent = mergeSubscriber;
            this.id = j;
        }

        @Override // rx.Observer
        public void onCompleted() {
            this.done = true;
            this.parent.emit();
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            this.done = true;
            this.parent.getOrCreateErrorQueue().offer(th);
            this.parent.emit();
        }

        @Override // rx.Observer
        public void onNext(T t) {
            this.parent.tryEmit(this, t);
        }

        @Override // rx.Subscriber
        public void onStart() {
            this.outstanding = RxRingBuffer.SIZE;
            request(RxRingBuffer.SIZE);
        }

        public void requestMore(long j) {
            int i = this.outstanding - ((int) j);
            if (i > limit) {
                this.outstanding = i;
                return;
            }
            this.outstanding = RxRingBuffer.SIZE;
            int i2 = RxRingBuffer.SIZE - i;
            if (i2 > 0) {
                request(i2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static final class MergeProducer<T> extends AtomicLong implements Producer {
        private static final long serialVersionUID = -1214379189873595503L;
        final MergeSubscriber<T> subscriber;

        public MergeProducer(MergeSubscriber<T> mergeSubscriber) {
            this.subscriber = mergeSubscriber;
        }

        public long produced(int i) {
            return addAndGet(-i);
        }

        @Override // rx.Producer
        public void request(long j) {
            if (j <= 0) {
                if (j < 0) {
                    throw new IllegalArgumentException("n >= 0 required");
                }
            } else {
                if (get() == LongCompanionObject.MAX_VALUE) {
                    return;
                }
                BackpressureUtils.getAndAddRequest(this, j);
                this.subscriber.emit();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static final class MergeSubscriber<T> extends Subscriber<Observable<? extends T>> {
        static final InnerSubscriber<?>[] EMPTY = new InnerSubscriber[0];
        final Subscriber<? super T> child;
        final boolean delayErrors;
        volatile boolean done;
        boolean emitting;
        volatile ConcurrentLinkedQueue<Throwable> errors;
        long lastId;
        int lastIndex;
        final int maxConcurrent;
        boolean missed;
        MergeProducer<T> producer;
        volatile Queue<Object> queue;
        int scalarEmissionCount;
        final int scalarEmissionLimit;
        volatile CompositeSubscription subscriptions;
        long uniqueId;
        final NotificationLite<T> nl = NotificationLite.instance();
        final Object innerGuard = new Object();
        volatile InnerSubscriber<?>[] innerSubscribers = EMPTY;

        public MergeSubscriber(Subscriber<? super T> subscriber, boolean z, int i) {
            this.child = subscriber;
            this.delayErrors = z;
            this.maxConcurrent = i;
            if (i == Integer.MAX_VALUE) {
                this.scalarEmissionLimit = Integer.MAX_VALUE;
                request(LongCompanionObject.MAX_VALUE);
            } else {
                this.scalarEmissionLimit = Math.max(1, i >> 1);
                request(i);
            }
        }

        private void reportError() {
            ArrayList arrayList = new ArrayList(this.errors);
            if (arrayList.size() == 1) {
                this.child.onError((Throwable) arrayList.get(0));
            } else {
                this.child.onError(new CompositeException(arrayList));
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        void addInner(InnerSubscriber<T> innerSubscriber) {
            getOrCreateComposite().add(innerSubscriber);
            synchronized (this.innerGuard) {
                InnerSubscriber<?>[] innerSubscriberArr = this.innerSubscribers;
                int length = innerSubscriberArr.length;
                InnerSubscriber<?>[] innerSubscriberArr2 = new InnerSubscriber[length + 1];
                System.arraycopy(innerSubscriberArr, 0, innerSubscriberArr2, 0, length);
                innerSubscriberArr2[length] = innerSubscriber;
                this.innerSubscribers = innerSubscriberArr2;
            }
        }

        boolean checkTerminate() {
            if (this.child.isUnsubscribed()) {
                return true;
            }
            ConcurrentLinkedQueue<Throwable> concurrentLinkedQueue = this.errors;
            if (this.delayErrors || concurrentLinkedQueue == null || concurrentLinkedQueue.isEmpty()) {
                return false;
            }
            try {
                reportError();
                return true;
            } finally {
                unsubscribe();
            }
        }

        void emit() {
            synchronized (this) {
                if (this.emitting) {
                    this.missed = true;
                } else {
                    this.emitting = true;
                    emitLoop();
                }
            }
        }

        void emitEmpty() {
            int i = this.scalarEmissionCount + 1;
            if (i != this.scalarEmissionLimit) {
                this.scalarEmissionCount = i;
            } else {
                this.scalarEmissionCount = 0;
                requestMore(i);
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:120:0x01c7  */
        /* JADX WARN: Removed duplicated region for block: B:207:0x018a A[SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:88:0x00f3 A[Catch: all -> 0x01c2, TryCatch #2 {all -> 0x01c2, blocks: (B:3:0x0002, B:4:0x0004, B:6:0x000b, B:15:0x002e, B:18:0x003d, B:20:0x0043, B:23:0x0063, B:26:0x0049, B:31:0x004d, B:28:0x005c, B:40:0x0079, B:47:0x0090, B:50:0x009b, B:54:0x00a3, B:56:0x00a7, B:59:0x00ae, B:61:0x00b2, B:64:0x00b8, B:66:0x00be, B:73:0x00d2, B:75:0x00db, B:79:0x00e2, B:84:0x00e5, B:88:0x00f3, B:90:0x00fa, B:94:0x0103, B:96:0x010a, B:98:0x010f, B:100:0x011a, B:103:0x0122, B:135:0x0144, B:136:0x0150, B:144:0x0161, B:147:0x0169, B:149:0x016f, B:151:0x0179, B:163:0x018c, B:165:0x019d, B:167:0x01a6, B:155:0x017f, B:159:0x0184), top: B:2:0x0002, inners: #8 }] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        void emitLoop() {
            /*
                Method dump skipped, instructions count: 465
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: rx.internal.operators.OperatorMerge.MergeSubscriber.emitLoop():void");
        }

        /* JADX WARN: Removed duplicated region for block: B:38:0x0056  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        protected void emitScalar(T r5, long r6) {
            /*
                r4 = this;
                r0 = 1
                r1 = 0
                rx.Subscriber<? super T> r2 = r4.child     // Catch: java.lang.Throwable -> L8 java.lang.Throwable -> Lb
                r2.onNext(r5)     // Catch: java.lang.Throwable -> L8 java.lang.Throwable -> Lb
                goto L23
            L8:
                r5 = move-exception
                r0 = 0
                goto L54
            Lb:
                r5 = move-exception
                boolean r2 = r4.delayErrors     // Catch: java.lang.Throwable -> L8
                if (r2 != 0) goto L1c
                rx.exceptions.Exceptions.throwIfFatal(r5)     // Catch: java.lang.Throwable -> L8
                r4.unsubscribe()     // Catch: java.lang.Throwable -> L1a
                r4.onError(r5)     // Catch: java.lang.Throwable -> L1a
                return
            L1a:
                r5 = move-exception
                goto L54
            L1c:
                java.util.Queue r2 = r4.getOrCreateErrorQueue()     // Catch: java.lang.Throwable -> L8
                r2.offer(r5)     // Catch: java.lang.Throwable -> L8
            L23:
                r2 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
                int r5 = (r6 > r2 ? 1 : (r6 == r2 ? 0 : -1))
                if (r5 == 0) goto L31
                rx.internal.operators.OperatorMerge$MergeProducer<T> r5 = r4.producer     // Catch: java.lang.Throwable -> L8
                r5.produced(r0)     // Catch: java.lang.Throwable -> L8
            L31:
                int r5 = r4.scalarEmissionCount     // Catch: java.lang.Throwable -> L8
                int r5 = r5 + r0
                int r6 = r4.scalarEmissionLimit     // Catch: java.lang.Throwable -> L8
                if (r5 != r6) goto L3f
                r4.scalarEmissionCount = r1     // Catch: java.lang.Throwable -> L8
                long r5 = (long) r5     // Catch: java.lang.Throwable -> L8
                r4.requestMore(r5)     // Catch: java.lang.Throwable -> L8
                goto L41
            L3f:
                r4.scalarEmissionCount = r5     // Catch: java.lang.Throwable -> L8
            L41:
                monitor-enter(r4)     // Catch: java.lang.Throwable -> L8
                boolean r5 = r4.missed     // Catch: java.lang.Throwable -> L51
                if (r5 != 0) goto L4a
                r4.emitting = r1     // Catch: java.lang.Throwable -> L51
                monitor-exit(r4)     // Catch: java.lang.Throwable -> L51
                return
            L4a:
                r4.missed = r1     // Catch: java.lang.Throwable -> L51
                monitor-exit(r4)     // Catch: java.lang.Throwable -> L51
                r4.emitLoop()
                return
            L51:
                r5 = move-exception
                monitor-exit(r4)     // Catch: java.lang.Throwable -> L51
                throw r5     // Catch: java.lang.Throwable -> L1a
            L54:
                if (r0 != 0) goto L5e
                monitor-enter(r4)
                r4.emitting = r1     // Catch: java.lang.Throwable -> L5b
                monitor-exit(r4)     // Catch: java.lang.Throwable -> L5b
                goto L5e
            L5b:
                r5 = move-exception
                monitor-exit(r4)     // Catch: java.lang.Throwable -> L5b
                throw r5
            L5e:
                throw r5
            */
            throw new UnsupportedOperationException("Method not decompiled: rx.internal.operators.OperatorMerge.MergeSubscriber.emitScalar(java.lang.Object, long):void");
        }

        /* JADX WARN: Removed duplicated region for block: B:35:0x004b  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        protected void emitScalar(rx.internal.operators.OperatorMerge.InnerSubscriber<T> r5, T r6, long r7) {
            /*
                r4 = this;
                r0 = 1
                r1 = 0
                rx.Subscriber<? super T> r2 = r4.child     // Catch: java.lang.Throwable -> L8 java.lang.Throwable -> Lb
                r2.onNext(r6)     // Catch: java.lang.Throwable -> L8 java.lang.Throwable -> Lb
                goto L23
            L8:
                r5 = move-exception
                r0 = 0
                goto L49
            Lb:
                r6 = move-exception
                boolean r2 = r4.delayErrors     // Catch: java.lang.Throwable -> L8
                if (r2 != 0) goto L1c
                rx.exceptions.Exceptions.throwIfFatal(r6)     // Catch: java.lang.Throwable -> L8
                r5.unsubscribe()     // Catch: java.lang.Throwable -> L1a
                r5.onError(r6)     // Catch: java.lang.Throwable -> L1a
                return
            L1a:
                r5 = move-exception
                goto L49
            L1c:
                java.util.Queue r2 = r4.getOrCreateErrorQueue()     // Catch: java.lang.Throwable -> L8
                r2.offer(r6)     // Catch: java.lang.Throwable -> L8
            L23:
                r2 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
                int r6 = (r7 > r2 ? 1 : (r7 == r2 ? 0 : -1))
                if (r6 == 0) goto L31
                rx.internal.operators.OperatorMerge$MergeProducer<T> r6 = r4.producer     // Catch: java.lang.Throwable -> L8
                r6.produced(r0)     // Catch: java.lang.Throwable -> L8
            L31:
                r6 = 1
                r5.requestMore(r6)     // Catch: java.lang.Throwable -> L8
                monitor-enter(r4)     // Catch: java.lang.Throwable -> L8
                boolean r5 = r4.missed     // Catch: java.lang.Throwable -> L46
                if (r5 != 0) goto L3f
                r4.emitting = r1     // Catch: java.lang.Throwable -> L46
                monitor-exit(r4)     // Catch: java.lang.Throwable -> L46
                return
            L3f:
                r4.missed = r1     // Catch: java.lang.Throwable -> L46
                monitor-exit(r4)     // Catch: java.lang.Throwable -> L46
                r4.emitLoop()
                return
            L46:
                r5 = move-exception
                monitor-exit(r4)     // Catch: java.lang.Throwable -> L46
                throw r5     // Catch: java.lang.Throwable -> L1a
            L49:
                if (r0 != 0) goto L53
                monitor-enter(r4)
                r4.emitting = r1     // Catch: java.lang.Throwable -> L50
                monitor-exit(r4)     // Catch: java.lang.Throwable -> L50
                goto L53
            L50:
                r5 = move-exception
                monitor-exit(r4)     // Catch: java.lang.Throwable -> L50
                throw r5
            L53:
                throw r5
            */
            throw new UnsupportedOperationException("Method not decompiled: rx.internal.operators.OperatorMerge.MergeSubscriber.emitScalar(rx.internal.operators.OperatorMerge$InnerSubscriber, java.lang.Object, long):void");
        }

        CompositeSubscription getOrCreateComposite() {
            CompositeSubscription compositeSubscription;
            CompositeSubscription compositeSubscription2 = this.subscriptions;
            if (compositeSubscription2 != null) {
                return compositeSubscription2;
            }
            boolean z = false;
            synchronized (this) {
                compositeSubscription = this.subscriptions;
                if (compositeSubscription == null) {
                    CompositeSubscription compositeSubscription3 = new CompositeSubscription();
                    this.subscriptions = compositeSubscription3;
                    compositeSubscription = compositeSubscription3;
                    z = true;
                }
            }
            if (z) {
                add(compositeSubscription);
            }
            return compositeSubscription;
        }

        Queue<Throwable> getOrCreateErrorQueue() {
            ConcurrentLinkedQueue<Throwable> concurrentLinkedQueue = this.errors;
            if (concurrentLinkedQueue == null) {
                synchronized (this) {
                    concurrentLinkedQueue = this.errors;
                    if (concurrentLinkedQueue == null) {
                        concurrentLinkedQueue = new ConcurrentLinkedQueue<>();
                        this.errors = concurrentLinkedQueue;
                    }
                }
            }
            return concurrentLinkedQueue;
        }

        @Override // rx.Observer
        public void onCompleted() {
            this.done = true;
            emit();
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            getOrCreateErrorQueue().offer(th);
            this.done = true;
            emit();
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // rx.Observer
        public void onNext(Observable<? extends T> observable) {
            if (observable == null) {
                return;
            }
            if (observable == Observable.empty()) {
                emitEmpty();
                return;
            }
            if (observable instanceof ScalarSynchronousObservable) {
                tryEmit(((ScalarSynchronousObservable) observable).get());
                return;
            }
            long j = this.uniqueId;
            this.uniqueId = 1 + j;
            InnerSubscriber innerSubscriber = new InnerSubscriber(this, j);
            addInner(innerSubscriber);
            observable.unsafeSubscribe(innerSubscriber);
            emit();
        }

        protected void queueScalar(T t) {
            Queue<Object> queue = this.queue;
            if (queue == null) {
                int i = this.maxConcurrent;
                if (i == Integer.MAX_VALUE) {
                    queue = new SpscUnboundedAtomicArrayQueue<>(RxRingBuffer.SIZE);
                } else {
                    queue = Pow2.isPowerOfTwo(i) ? UnsafeAccess.isUnsafeAvailable() ? new SpscArrayQueue<>(i) : new SpscAtomicArrayQueue<>(i) : new SpscExactAtomicArrayQueue<>(i);
                }
                this.queue = queue;
            }
            if (queue.offer(this.nl.next(t))) {
                emit();
            } else {
                unsubscribe();
                onError(OnErrorThrowable.addValueAsLastCause(new MissingBackpressureException(), t));
            }
        }

        protected void queueScalar(InnerSubscriber<T> innerSubscriber, T t) {
            RxRingBuffer rxRingBuffer = innerSubscriber.queue;
            if (rxRingBuffer == null) {
                rxRingBuffer = RxRingBuffer.getSpscInstance();
                innerSubscriber.add(rxRingBuffer);
                innerSubscriber.queue = rxRingBuffer;
            }
            try {
                rxRingBuffer.onNext(this.nl.next(t));
                emit();
            } catch (IllegalStateException e) {
                if (innerSubscriber.isUnsubscribed()) {
                    return;
                }
                innerSubscriber.unsubscribe();
                innerSubscriber.onError(e);
            } catch (MissingBackpressureException e2) {
                innerSubscriber.unsubscribe();
                innerSubscriber.onError(e2);
            }
        }

        void removeInner(InnerSubscriber<T> innerSubscriber) {
            RxRingBuffer rxRingBuffer = innerSubscriber.queue;
            if (rxRingBuffer != null) {
                rxRingBuffer.release();
            }
            this.subscriptions.remove(innerSubscriber);
            synchronized (this.innerGuard) {
                InnerSubscriber<?>[] innerSubscriberArr = this.innerSubscribers;
                int length = innerSubscriberArr.length;
                int i = -1;
                int i2 = 0;
                while (true) {
                    if (i2 >= length) {
                        break;
                    }
                    if (innerSubscriber.equals(innerSubscriberArr[i2])) {
                        i = i2;
                        break;
                    }
                    i2++;
                }
                if (i < 0) {
                    return;
                }
                if (length == 1) {
                    this.innerSubscribers = EMPTY;
                    return;
                }
                InnerSubscriber<?>[] innerSubscriberArr2 = new InnerSubscriber[length - 1];
                System.arraycopy(innerSubscriberArr, 0, innerSubscriberArr2, 0, i);
                System.arraycopy(innerSubscriberArr, i + 1, innerSubscriberArr2, i, (length - i) - 1);
                this.innerSubscribers = innerSubscriberArr2;
            }
        }

        public void requestMore(long j) {
            request(j);
        }

        void tryEmit(T t) {
            long j = this.producer.get();
            boolean z = false;
            if (j != 0) {
                synchronized (this) {
                    j = this.producer.get();
                    if (!this.emitting && j != 0) {
                        this.emitting = true;
                        z = true;
                    }
                }
            }
            if (z) {
                emitScalar(t, j);
            } else {
                queueScalar(t);
            }
        }

        void tryEmit(InnerSubscriber<T> innerSubscriber, T t) {
            long j = this.producer.get();
            boolean z = false;
            if (j != 0) {
                synchronized (this) {
                    j = this.producer.get();
                    if (!this.emitting && j != 0) {
                        this.emitting = true;
                        z = true;
                    }
                }
            }
            if (z) {
                emitScalar(innerSubscriber, t, j);
            } else {
                queueScalar(innerSubscriber, t);
            }
        }
    }

    OperatorMerge(boolean z, int i) {
        this.delayErrors = z;
        this.maxConcurrent = i;
    }

    public static <T> OperatorMerge<T> instance(boolean z) {
        return z ? (OperatorMerge<T>) HolderDelayErrors.INSTANCE : (OperatorMerge<T>) HolderNoDelay.INSTANCE;
    }

    public static <T> OperatorMerge<T> instance(boolean z, int i) {
        if (i > 0) {
            return i == Integer.MAX_VALUE ? instance(z) : new OperatorMerge<>(z, i);
        }
        throw new IllegalArgumentException("maxConcurrent > 0 required but it was " + i);
    }

    @Override // rx.functions.Func1
    public Subscriber<Observable<? extends T>> call(Subscriber<? super T> subscriber) {
        MergeSubscriber mergeSubscriber = new MergeSubscriber(subscriber, this.delayErrors, this.maxConcurrent);
        MergeProducer<T> mergeProducer = new MergeProducer<>(mergeSubscriber);
        mergeSubscriber.producer = mergeProducer;
        subscriber.add(mergeSubscriber);
        subscriber.setProducer(mergeProducer);
        return mergeSubscriber;
    }
}
