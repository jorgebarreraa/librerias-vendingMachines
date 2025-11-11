package rx.android.schedulers;

import android.os.Handler;
import rx.Scheduler;

@Deprecated
/* loaded from: classes2.dex */
public final class HandlerScheduler extends LooperScheduler {
    private HandlerScheduler(Handler handler) {
        super(handler);
    }

    @Deprecated
    public static HandlerScheduler from(Handler handler) {
        if (handler != null) {
            return new HandlerScheduler(handler);
        }
        throw new NullPointerException("handler == null");
    }

    @Override // rx.android.schedulers.LooperScheduler, rx.Scheduler
    public /* bridge */ /* synthetic */ Scheduler.Worker createWorker() {
        return super.createWorker();
    }
}
