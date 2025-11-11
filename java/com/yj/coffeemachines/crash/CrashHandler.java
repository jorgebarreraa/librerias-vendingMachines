package com.yj.coffeemachines.crash;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Looper;
import android.os.Process;
import android.widget.Toast;
import com.jess.arms.utils.ArmsUtils;
import com.yj.coffeemachines.Constants;
import com.yj.coffeemachines.MyAppLocation;
import com.yj.coffeemachines.R;
import com.yj.coffeemachines.app.serialport.ShellUtils;
import com.yj.coffeemachines.helper.Tools;
import com.yj.coffeemachines.mvp.ui.activity.HomeActivity;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.Thread;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public class CrashHandler implements Thread.UncaughtExceptionHandler {
    private static CrashHandler instance;
    private DateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
    private Application mApplication;
    private Thread.UncaughtExceptionHandler mDefaultHandler;
    private RestartManager restartManager;

    private CrashHandler() {
    }

    private Map<String, String> collectDeviceInfo(Context context) {
        HashMap hashMap = new HashMap();
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 1);
            if (packageInfo != null) {
                String str = packageInfo.packageName == null ? "null" : packageInfo.packageName;
                String str2 = packageInfo.versionName == null ? "null" : packageInfo.versionName;
                String valueOf = String.valueOf(packageInfo.versionCode);
                String arrays = Arrays.toString(packageInfo.requestedPermissions == null ? new String[0] : packageInfo.requestedPermissions);
                hashMap.put("packageName", str);
                hashMap.put("versionName", str2);
                hashMap.put("versionCode", valueOf);
                hashMap.put("requestedPermissions", arrays);
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        for (Field field : Build.class.getDeclaredFields()) {
            try {
                field.setAccessible(true);
                hashMap.put(field.getName(), field.get(null) == null ? "null" : field.get(null).toString());
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return hashMap;
    }

    private String generalFileName() {
        long currentTimeMillis = System.currentTimeMillis();
        return "crash-" + this.formatter.format(new Date(currentTimeMillis)) + "-" + currentTimeMillis + ".log";
    }

    public static CrashHandler getInstance() {
        if (instance == null) {
            instance = new CrashHandler();
        }
        return instance;
    }

    private String getRestartKey() {
        return this.formatter.format(new Date(System.currentTimeMillis()));
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [com.yj.coffeemachines.crash.CrashHandler$1] */
    private boolean handleThrowable(Throwable th) {
        if (th == null) {
            return false;
        }
        new Thread() { // from class: com.yj.coffeemachines.crash.CrashHandler.1
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                Looper.prepare();
                Toast.makeText(CrashHandler.this.mApplication, R.string.crash_tips, 1).show();
                Looper.loop();
            }
        }.start();
        try {
            Thread.sleep(3000L);
        } catch (InterruptedException unused) {
        }
        saveCrashIntoStorage(this.mApplication, th);
        if (!this.restartManager.canRestart()) {
            return true;
        }
        restartApp(this.mApplication);
        return true;
    }

    private void killApp() {
        Tools.upLocalLog("CrashHandler - 关闭应用");
        MyAppLocation.myAppLocation.onDestroy();
        ArmsUtils.exitApp();
    }

    private StringBuffer mergeInfo(Map<String, String> map, Throwable th) {
        StringBuffer stringBuffer = new StringBuffer();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            stringBuffer.append(key);
            stringBuffer.append("=");
            stringBuffer.append(value);
            stringBuffer.append(ShellUtils.COMMAND_LINE_END);
        }
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        th.printStackTrace(printWriter);
        for (Throwable cause = th.getCause(); cause != null; cause = cause.getCause()) {
            cause.printStackTrace(printWriter);
        }
        printWriter.close();
        stringBuffer.append(stringWriter.toString());
        return stringBuffer;
    }

    private void restartApp(Context context) {
        Tools.upLocalLog("崩溃重启应用");
        Intent intent = new Intent(context, (Class<?>) HomeActivity.class);
        intent.setFlags(268468224);
        context.startActivity(intent);
        Process.killProcess(Process.myPid());
    }

    private String saveCrashIntoStorage(Context context, Throwable th) {
        StringBuffer mergeInfo = mergeInfo(collectDeviceInfo(context), th);
        File file = new File(Constants.errorLogPath);
        File file2 = new File(file, generalFileName());
        if (!file.exists()) {
            file2.mkdirs();
        }
        if (file2.exists()) {
            file2.delete();
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file2);
            fileOutputStream.write(mergeInfo.toString().getBytes());
            fileOutputStream.close();
            return file2.getAbsolutePath();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void init(Application application) {
        this.mApplication = application;
        this.restartManager = RestartManager.getInstance();
        this.mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public void uncaughtException(Thread thread, Throwable th) {
        Thread.UncaughtExceptionHandler uncaughtExceptionHandler;
        if (handleThrowable(th) || (uncaughtExceptionHandler = this.mDefaultHandler) == null) {
            return;
        }
        uncaughtExceptionHandler.uncaughtException(thread, th);
    }
}
