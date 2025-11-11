package rx.internal.operators;

import java.util.concurrent.atomic.AtomicInteger;
import rx.Completable;
import rx.Subscription;
import rx.subscriptions.SerialSubscription;

/* loaded from: classes2.dex */
public final class CompletableOnSubscribeConcatArray implements Completable.CompletableOnSubscribe {
    final Completable[] sources;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static final class ConcatInnerSubscriber extends AtomicInteger implements Completable.CompletableSubscriber {
        private static final long serialVersionUID = -7965400327305809232L;
        final Completable.CompletableSubscriber actual;
        int index;
        final SerialSubscription sd = new SerialSubscription();
        final Completable[] sources;

        public ConcatInnerSubscriber(Completable.CompletableSubscriber completableSubscriber, Completable[] completableArr) {
            this.actual = completableSubscriber;
            this.sources = completableArr;
        }

        void next() {
            if (!this.sd.isUnsubscribed() && getAndIncrement() == 0) {
                Completable[] completableArr = this.sources;
                while (!this.sd.isUnsubscribed()) {
                    int i = this.index;
                    this.index = i + 1;
                    if (i == completableArr.length) {
                        this.actual.onCompleted();
                        return;
                    } else {
                        completableArr[i].unsafeSubscribe(this);
                        if (decrementAndGet() == 0) {
                            return;
                        }
                    }
                }
            }
        }

        @Override // rx.Completable.CompletableSubscriber
        public void onCompleted() {
            next();
        }

        @Override // rx.Completable.CompletableSubscriber
        public void onError(Throwable th) {
            this.actual.onError(th);
        }

        @Override // rx.Completable.CompletableSubscriber
        public void onSubscribe(Subscription subscription) {
            this.sd.set(subscription);
        }
    }

    public CompletableOnSubscribeConcatArray(Completable[] completableArr) {
        this.sources = completableArr;
    }

    @Override // rx.functions.Action1
    public void call(Completable.CompletableSubscriber completableSubscriber) {
        ConcatInnerSubscriber concatInnerSubscriber = new ConcatInnerSubscriber(completableSubscriber, this.sources);
        completableSubscriber.onSubscribe(concatInnerSubscriber.sd);
        concatInnerSubscriber.next();
    }
}
