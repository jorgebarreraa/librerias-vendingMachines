package com.yj.coffeemachines.app;

import com.jess.arms.utils.ArmsUtils;
import com.yj.coffeemachines.helper.Tools;
import java.lang.Thread;
import java.util.concurrent.ThreadFactory;

/* loaded from: classes.dex */
public class WorkerThreadFactory implements ThreadFactory {
    private int counter = 0;
    private String prefix = "wangzx_workerthreadfactory";

    @Override // java.util.concurrent.ThreadFactory
    public Thread newThread(Runnable runnable) {
        StringBuilder sb = new StringBuilder();
        sb.append(this.prefix);
        sb.append("-");
        int i = this.counter;
        this.counter = i + 1;
        sb.append(i);
        Thread thread = new Thread(runnable, sb.toString());
        thread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() { // from class: com.yj.coffeemachines.app.WorkerThreadFactory.1
            @Override // java.lang.Thread.UncaughtExceptionHandler
            public void uncaughtException(Thread thread2, Throwable th) {
                ArmsUtils.snackbarText("线程" + thread2.getName() + "出现了一个问题：" + th.getMessage());
                Tools.upLocalLog("WorkerThreadFactory线程出错");
            }
        });
        return thread;
    }
}
