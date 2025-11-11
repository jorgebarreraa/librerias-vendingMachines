package com.blankj.utilcode.util;

import android.app.ActivityManager;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import androidx.annotation.NonNull;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* loaded from: classes.dex */
public final class ServiceUtils {
    private ServiceUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static void bindService(@NonNull Intent intent, @NonNull ServiceConnection serviceConnection, int i) {
        try {
            Utils.getApp().bindService(intent, serviceConnection, i);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void bindService(@NonNull Class<?> cls, @NonNull ServiceConnection serviceConnection, int i) {
        bindService(new Intent(Utils.getApp(), cls), serviceConnection, i);
    }

    public static void bindService(@NonNull String str, @NonNull ServiceConnection serviceConnection, int i) {
        try {
            bindService(Class.forName(str), serviceConnection, i);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Set<String> getAllRunningServices() {
        List<ActivityManager.RunningServiceInfo> runningServices = ((ActivityManager) Utils.getApp().getSystemService("activity")).getRunningServices(Integer.MAX_VALUE);
        HashSet hashSet = new HashSet();
        if (runningServices == null || runningServices.size() == 0) {
            return null;
        }
        Iterator<ActivityManager.RunningServiceInfo> it2 = runningServices.iterator();
        while (it2.hasNext()) {
            hashSet.add(it2.next().service.getClassName());
        }
        return hashSet;
    }

    public static boolean isServiceRunning(@NonNull Class<?> cls) {
        return isServiceRunning(cls.getName());
    }

    public static boolean isServiceRunning(@NonNull String str) {
        try {
            List<ActivityManager.RunningServiceInfo> runningServices = ((ActivityManager) Utils.getApp().getSystemService("activity")).getRunningServices(Integer.MAX_VALUE);
            if (runningServices != null && runningServices.size() != 0) {
                Iterator<ActivityManager.RunningServiceInfo> it2 = runningServices.iterator();
                while (it2.hasNext()) {
                    if (str.equals(it2.next().service.getClassName())) {
                        return true;
                    }
                }
            }
        } catch (Exception unused) {
        }
        return false;
    }

    public static void startService(Intent intent) {
        try {
            intent.setFlags(32);
            if (Build.VERSION.SDK_INT >= 26) {
                Utils.getApp().startForegroundService(intent);
            } else {
                Utils.getApp().startService(intent);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void startService(@NonNull Class<?> cls) {
        startService(new Intent(Utils.getApp(), cls));
    }

    public static void startService(@NonNull String str) {
        try {
            startService(Class.forName(str));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean stopService(@NonNull Intent intent) {
        try {
            return Utils.getApp().stopService(intent);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean stopService(@NonNull Class<?> cls) {
        return stopService(new Intent(Utils.getApp(), cls));
    }

    public static boolean stopService(@NonNull String str) {
        try {
            return stopService(Class.forName(str));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void unbindService(@NonNull ServiceConnection serviceConnection) {
        Utils.getApp().unbindService(serviceConnection);
    }
}
