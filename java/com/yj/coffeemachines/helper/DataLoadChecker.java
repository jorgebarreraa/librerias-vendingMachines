package com.yj.coffeemachines.helper;

import android.os.Handler;

/* loaded from: classes.dex */
public class DataLoadChecker {
    private static volatile DataLoadChecker instance;
    private LoadCallback callback;
    private Runnable checkTask;
    private boolean isDataLoaded = false;
    private boolean isRunning = true;
    private final Handler handler = new Handler();
    private final int CHECK_INTERVAL = 180000;

    /* loaded from: classes.dex */
    public interface LoadCallback {

        /* renamed from: com.yj.coffeemachines.helper.DataLoadChecker$LoadCallback$-CC, reason: invalid class name */
        /* loaded from: classes.dex */
        public final /* synthetic */ class CC {
            public static void $default$onSuccess(LoadCallback loadCallback) {
            }
        }

        void onRetry();

        void onSuccess();
    }

    private DataLoadChecker() {
    }

    public static DataLoadChecker getInstance() {
        if (instance == null) {
            synchronized (DataLoadChecker.class) {
                if (instance == null) {
                    instance = new DataLoadChecker();
                }
            }
        }
        return instance;
    }

    public /* synthetic */ void lambda$start$0$DataLoadChecker() {
        if (this.isDataLoaded) {
            stop();
        } else {
            LoadCallback loadCallback = this.callback;
            if (loadCallback != null) {
                loadCallback.onRetry();
            }
        }
        if (this.isRunning) {
            this.handler.postDelayed(this.checkTask, 180000L);
        }
    }

    public void notifyDataLoaded(boolean z) {
        this.isDataLoaded = z;
        if (z) {
            LoadCallback loadCallback = this.callback;
            if (loadCallback != null) {
                loadCallback.onSuccess();
            }
            stop();
        }
    }

    public void setLoadCallback(LoadCallback loadCallback) {
        this.callback = loadCallback;
    }

    public void start() {
        this.isDataLoaded = false;
        this.isRunning = true;
        this.checkTask = new Runnable() { // from class: com.yj.coffeemachines.helper.-$$Lambda$DataLoadChecker$0vx1lYZPaXT5Bbbq6yJF9ddeXL8
            @Override // java.lang.Runnable
            public final void run() {
                DataLoadChecker.this.lambda$start$0$DataLoadChecker();
            }
        };
        this.handler.postDelayed(this.checkTask, 180000L);
    }

    public void stop() {
        this.isRunning = false;
        Runnable runnable = this.checkTask;
        if (runnable != null) {
            this.handler.removeCallbacks(runnable);
        }
    }
}
