package rx.schedulers;

import rx.Scheduler;

@Deprecated
/* loaded from: classes2.dex */
public final class NewThreadScheduler extends Scheduler {
    private NewThreadScheduler() {
        throw new AssertionError();
    }

    @Override // rx.Scheduler
    public Scheduler.Worker createWorker() {
        return null;
    }
}
