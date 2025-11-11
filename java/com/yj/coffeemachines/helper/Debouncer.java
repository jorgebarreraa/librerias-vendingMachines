package com.yj.coffeemachines.helper;

import android.os.Handler;
import android.os.Looper;

/* loaded from: classes.dex */
public class Debouncer {
    private final Handler handler = new Handler(Looper.getMainLooper());
    private Runnable lastRunnable;

    public void debounce(Runnable runnable, long j) {
        Runnable runnable2 = this.lastRunnable;
        if (runnable2 != null) {
            this.handler.removeCallbacks(runnable2);
        }
        this.lastRunnable = runnable;
        this.handler.postDelayed(runnable, j);
    }
}
