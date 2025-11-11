package com.yj.coffeemachines.app;

import android.app.Application;
import android.app.kingsun.rk3399;
import android.content.Context;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.multidex.MultiDex;
import butterknife.ButterKnife;
import com.blankj.utilcode.util.Utils;
import com.elvishew.xlog.LogConfiguration;
import com.elvishew.xlog.XLog;
import com.elvishew.xlog.flattener.PatternFlattener;
import com.elvishew.xlog.printer.AndroidPrinter;
import com.elvishew.xlog.printer.file.FilePrinter;
import com.elvishew.xlog.printer.file.backup.NeverBackupStrategy;
import com.elvishew.xlog.printer.file.clean.FileLastModifiedCleanStrategy;
import com.elvishew.xlog.printer.file.naming.DateFileNameGenerator;
import com.jess.arms.base.delegate.AppLifecycles;
import com.tencent.bugly.crashreport.CrashReport;
import com.yj.coffeemachines.Constants;
import com.yj.coffeemachines.crash.CrashHandler;
import io.reactivex.exceptions.UndeliverableException;
import io.reactivex.functions.Consumer;
import io.reactivex.plugins.RxJavaPlugins;
import java.io.IOException;
import java.lang.Thread;
import me.jessyan.autosize.AutoSizeConfig;

/* loaded from: classes.dex */
public class AppLifecyclesImpl implements AppLifecycles {
    private void initLog() {
        LogConfiguration build = new LogConfiguration.Builder().logLevel(Integer.MIN_VALUE).tag("Coffee-Maker").build();
        new AndroidPrinter(true);
        XLog.init(build, new FilePrinter.Builder(Constants.logPath).flattener(new PatternFlattener("{d} {t}: {m}")).fileNameGenerator(new DateFileNameGenerator()).backupStrategy(new NeverBackupStrategy()).cleanStrategy(new FileLastModifiedCleanStrategy(432000000L)).build());
    }

    private void setRxJavaErrorHandler() {
        if (RxJavaPlugins.getErrorHandler() != null || RxJavaPlugins.isLockdown()) {
            return;
        }
        RxJavaPlugins.setErrorHandler(new Consumer<Throwable>() { // from class: com.yj.coffeemachines.app.AppLifecyclesImpl.1
            @Override // io.reactivex.functions.Consumer
            public void accept(Throwable th) {
                Thread.UncaughtExceptionHandler uncaughtExceptionHandler;
                if ((th instanceof UndeliverableException) || (th instanceof IOException) || (th instanceof InterruptedException)) {
                    return;
                }
                if ((th instanceof NullPointerException) || (th instanceof IllegalArgumentException)) {
                    Thread.UncaughtExceptionHandler uncaughtExceptionHandler2 = Thread.currentThread().getUncaughtExceptionHandler();
                    if (uncaughtExceptionHandler2 != null) {
                        uncaughtExceptionHandler2.uncaughtException(Thread.currentThread(), th);
                        return;
                    }
                    return;
                }
                if (!(th instanceof IllegalStateException) || (uncaughtExceptionHandler = Thread.currentThread().getUncaughtExceptionHandler()) == null) {
                    return;
                }
                uncaughtExceptionHandler.uncaughtException(Thread.currentThread(), th);
            }
        });
    }

    @Override // com.jess.arms.base.delegate.AppLifecycles
    public void attachBaseContext(@NonNull Context context) {
        MultiDex.install(context);
    }

    @Override // com.jess.arms.base.delegate.AppLifecycles
    public void onCreate(@NonNull Application application) {
        initLog();
        Utils.init(application);
        AutoSizeConfig.getInstance().setCustomFragment(true);
        if (application.getResources().getConfiguration().orientation == 2) {
            AutoSizeConfig.getInstance().setDesignWidthInDp(1920).setDesignHeightInDp(rk3399.RK3399_GPIO2_C0);
        } else {
            AutoSizeConfig.getInstance().setDesignWidthInDp(rk3399.RK3399_GPIO2_C0).setDesignHeightInDp(1920);
        }
        ButterKnife.setDebug(false);
        CrashHandler.getInstance().init(application);
        CrashReport.UserStrategy userStrategy = new CrashReport.UserStrategy(application);
        userStrategy.setDeviceID(Constants.deviceSN(application));
        userStrategy.setDeviceModel(Build.MODEL);
        userStrategy.setEnableCatchAnrTrace(true);
        userStrategy.setEnableRecordAnrMainStack(true);
        CrashReport.setIsDevelopmentDevice(application, false);
        CrashReport.initCrashReport(application, "2214afbd58", false, userStrategy);
        CrashReport.setUserId(Constants.deviceSN(application));
        setRxJavaErrorHandler();
    }

    @Override // com.jess.arms.base.delegate.AppLifecycles
    public void onTerminate(@NonNull Application application) {
    }
}
