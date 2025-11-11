package rx.internal.operators;

import java.util.Iterator;
import java.util.concurrent.atomic.AtomicLong;
import kotlin.jvm.internal.LongCompanionObject;
import rx.Observable;
import rx.Producer;
import rx.Subscriber;
import rx.exceptions.Exceptions;

/* loaded from: classes2.dex */
public final class OnSubscribeFromIterable<T> implements Observable.OnSubscribe<T> {
    final Iterable<? extends T> is;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static final class IterableProducer<T> extends AtomicLong implements Producer {
        private static final long serialVersionUID = -8730475647105475802L;

        /* renamed from: it, reason: collision with root package name */
        private final Iterator<? extends T> f11it;
        private final Subscriber<? super T> o;

        /* JADX INFO: Access modifiers changed from: package-private */
        public IterableProducer(Subscriber<? super T> subscriber, Iterator<? extends T> it2) {
            this.o = subscriber;
            this.f11it = it2;
        }

        void fastpath() {
            Subscriber<? super T> subscriber = this.o;
            Iterator<? extends T> it2 = this.f11it;
            while (!subscriber.isUnsubscribed()) {
                try {
                    subscriber.onNext(it2.next());
                    if (subscriber.isUnsubscribed()) {
                        return;
                    }
                    try {
                        if (!it2.hasNext()) {
                            if (subscriber.isUnsubscribed()) {
                                return;
                            }
                            subscriber.onCompleted();
                            return;
                        }
                    } catch (Throwable th) {
                        Exceptions.throwOrReport(th, subscriber);
                        return;
                    }
                } catch (Throwable th2) {
                    Exceptions.throwOrReport(th2, subscriber);
                    return;
                }
            }
        }

        @Override // rx.Producer
        public void request(long j) {
            if (get() == LongCompanionObject.MAX_VALUE) {
                return;
            }
            if (j == LongCompanionObject.MAX_VALUE && compareAndSet(0L, LongCompanionObject.MAX_VALUE)) {
                fastpath();
            } else {
                if (j <= 0 || BackpressureUtils.getAndAddRequest(this, j) != 0) {
                    return;
                }
                slowpath(j);
            }
        }

        /* JADX WARN: Code restructure failed: missing block: B:9:0x0047, code lost:
        
            r4 = rx.internal.operators.BackpressureUtils.produced(r8, r9);
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        void slowpath(long r9) {
            /*
                r8 = this;
                rx.Subscriber<? super T> r0 = r8.o
                java.util.Iterator<? extends T> r1 = r8.f11it
                r2 = 0
                r4 = r9
            L7:
                r9 = r2
            L8:
                int r6 = (r9 > r4 ? 1 : (r9 == r4 ? 0 : -1))
                if (r6 == 0) goto L3f
                boolean r6 = r0.isUnsubscribed()
                if (r6 == 0) goto L13
                return
            L13:
                java.lang.Object r6 = r1.next()     // Catch: java.lang.Throwable -> L3a
                r0.onNext(r6)
                boolean r6 = r0.isUnsubscribed()
                if (r6 == 0) goto L21
                return
            L21:
                boolean r6 = r1.hasNext()     // Catch: java.lang.Throwable -> L35
                if (r6 != 0) goto L31
                boolean r9 = r0.isUnsubscribed()
                if (r9 != 0) goto L30
                r0.onCompleted()
            L30:
                return
            L31:
                r6 = 1
                long r9 = r9 + r6
                goto L8
            L35:
                r9 = move-exception
                rx.exceptions.Exceptions.throwOrReport(r9, r0)
                return
            L3a:
                r9 = move-exception
                rx.exceptions.Exceptions.throwOrReport(r9, r0)
                return
            L3f:
                long r4 = r8.get()
                int r6 = (r9 > r4 ? 1 : (r9 == r4 ? 0 : -1))
                if (r6 != 0) goto L8
                long r4 = rx.internal.operators.BackpressureUtils.produced(r8, r9)
                int r9 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
                if (r9 != 0) goto L7
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: rx.internal.operators.OnSubscribeFromIterable.IterableProducer.slowpath(long):void");
        }
    }

    public OnSubscribeFromIterable(Iterable<? extends T> iterable) {
        if (iterable == null) {
            throw new NullPointerException("iterable must not be null");
        }
        this.is = iterable;
    }

    @Override // rx.functions.Action1
    public void call(Subscriber<? super T> subscriber) {
        try {
            Iterator<? extends T> it2 = this.is.iterator();
            boolean hasNext = it2.hasNext();
            if (subscriber.isUnsubscribed()) {
                return;
            }
            if (hasNext) {
                subscriber.setProducer(new IterableProducer(subscriber, it2));
            } else {
                subscriber.onCompleted();
            }
        } catch (Throwable th) {
            Exceptions.throwOrReport(th, subscriber);
        }
    }
}
