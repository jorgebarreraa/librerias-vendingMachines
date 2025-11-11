package com.yj.coffeemachines.log;

import android.content.Context;
import androidx.work.ListenableWorker;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import com.yj.coffeemachines.MyAppLocation;
import com.yj.coffeemachines.helper.Tools;

/* loaded from: classes.dex */
public class UploadLogWorker extends Worker {
    public UploadLogWorker(Context context, WorkerParameters workerParameters) {
        super(context, workerParameters);
    }

    private void uploadLogs() {
        MyAppLocation.myAppLocation.myMqttService.outPutOrders(true, false, false);
    }

    @Override // androidx.work.Worker
    public ListenableWorker.Result doWork() {
        try {
            uploadLogs();
            Tools.upLocalLog("日志上传成功");
            return ListenableWorker.Result.success();
        } catch (Exception e) {
            Tools.upLocalLog("日志上传失败：" + e.getMessage());
            return ListenableWorker.Result.retry();
        }
    }
}
