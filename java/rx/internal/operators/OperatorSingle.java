package rx.internal.operators;

import java.util.NoSuchElementException;
import rx.Observable;
import rx.Subscriber;
import rx.internal.producers.SingleProducer;
import rx.internal.util.RxJavaPluginUtils;

/* loaded from: classes2.dex */
public final class OperatorSingle<T> implements Observable.Operator<T, T> {
    private final T defaultValue;
    private final boolean hasDefaultValue;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class Holder {
        static final OperatorSingle<?> INSTANCE = new OperatorSingle<>();

        private Holder() {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static final class ParentSubscriber<T> extends Subscriber<T> {
        private final Subscriber<? super T> child;
        private final T defaultValue;
        private final boolean hasDefaultValue;
        private boolean hasTooManyElements;
        private boolean isNonEmpty;
        private T value;

        ParentSubscriber(Subscriber<? super T> subscriber, boolean z, T t) {
            this.child = subscriber;
            this.hasDefaultValue = z;
            this.defaultValue = t;
            request(2L);
        }

        @Override // rx.Observer
        public void onCompleted() {
            if (this.hasTooManyElements) {
                return;
            }
            if (this.isNonEmpty) {
                Subscriber<? super T> subscriber = this.child;
                subscriber.setProducer(new SingleProducer(subscriber, this.value));
            } else if (!this.hasDefaultValue) {
                this.child.onError(new NoSuchElementException("Sequence contains no elements"));
            } else {
                Subscriber<? super T> subscriber2 = this.child;
                subscriber2.setProducer(new SingleProducer(subscriber2, this.defaultValue));
            }
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            if (this.hasTooManyElements) {
                RxJavaPluginUtils.handleException(th);
            } else {
                this.child.onError(th);
            }
        }

        @Override // rx.Observer
        public void onNext(T t) {
            if (this.hasTooManyElements) {
                return;
            }
            if (!this.isNonEmpty) {
                this.value = t;
                this.isNonEmpty = true;
            } else {
                this.hasTooManyElements = true;
                this.child.onError(new IllegalArgumentException("Sequence contains too many elements"));
                unsubscribe();
            }
        }
    }

    OperatorSingle() {
        this(false, null);
    }

    public OperatorSingle(T t) {
        this(true, t);
    }

    private OperatorSingle(boolean z, T t) {
        this.hasDefaultValue = z;
        this.defaultValue = t;
    }

    public static <T> OperatorSingle<T> instance() {
        return (OperatorSingle<T>) Holder.INSTANCE;
    }

    @Override // rx.functions.Func1
    public Subscriber<? super T> call(Subscriber<? super T> subscriber) {
        ParentSubscriber parentSubscriber = new ParentSubscriber(subscriber, this.hasDefaultValue, this.defaultValue);
        subscriber.add(parentSubscriber);
        return parentSubscriber;
    }
}
