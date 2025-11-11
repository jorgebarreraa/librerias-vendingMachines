package com.yj.coffeemachines.log;

import android.content.Context;
import androidx.work.Constraints;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.ListenableWorker;
import androidx.work.NetworkType;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;
import java.util.concurrent.TimeUnit;

/* loaded from: classes.dex */
public class LogScheduler {
    public static void scheduleDailyLogUpload(Context context) {
        WorkManager.getInstance(context).enqueueUniquePeriodicWork("DailyLogUpload", ExistingPeriodicWorkPolicy.REPLACE, new PeriodicWorkRequest.Builder((Class<? extends ListenableWorker>) UploadLogWorker.class, 24L, TimeUnit.HOURS).setInitialDelay(120000L, TimeUnit.MILLISECONDS).setConstraints(new Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()).build());
    }
}
