package rx.schedulers;

import rx.Scheduler;

@Deprecated
/* loaded from: classes2.dex */
public final class TrampolineScheduler extends Scheduler {
    private TrampolineScheduler() {
        throw new AssertionError();
    }

    @Override // rx.Scheduler
    public Scheduler.Worker createWorker() {
        return null;
    }
}
