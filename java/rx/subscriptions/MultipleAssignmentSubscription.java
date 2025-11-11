package rx.subscriptions;

import java.util.concurrent.atomic.AtomicReference;
import rx.Subscription;

/* loaded from: classes2.dex */
public final class MultipleAssignmentSubscription implements Subscription {
    final AtomicReference<State> state = new AtomicReference<>(new State(false, Subscriptions.empty()));

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static final class State {
        final boolean isUnsubscribed;
        final Subscription subscription;

        State(boolean z, Subscription subscription) {
            this.isUnsubscribed = z;
            this.subscription = subscription;
        }

        State set(Subscription subscription) {
            return new State(this.isUnsubscribed, subscription);
        }

        State unsubscribe() {
            return new State(true, this.subscription);
        }
    }

    public Subscription get() {
        return this.state.get().subscription;
    }

    @Override // rx.Subscription
    public boolean isUnsubscribed() {
        return this.state.get().isUnsubscribed;
    }

    public void set(Subscription subscription) {
        State state;
        if (subscription == null) {
            throw new IllegalArgumentException("Subscription can not be null");
        }
        AtomicReference<State> atomicReference = this.state;
        do {
            state = atomicReference.get();
            if (state.isUnsubscribed) {
                subscription.unsubscribe();
                return;
            }
        } while (!atomicReference.compareAndSet(state, state.set(subscription)));
    }

    @Override // rx.Subscription
    public void unsubscribe() {
        State state;
        AtomicReference<State> atomicReference = this.state;
        do {
            state = atomicReference.get();
            if (state.isUnsubscribed) {
                return;
            }
        } while (!atomicReference.compareAndSet(state, state.unsubscribe()));
        state.subscription.unsubscribe();
    }
}
