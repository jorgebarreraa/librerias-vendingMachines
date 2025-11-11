package com.jess.arms.utils;

import android.text.TextUtils;
import android.util.Log;

/* loaded from: classes.dex */
public class LogUtils {
    private static final String DEFAULT_TAG = "MVPArms";
    private static boolean isLog = true;

    private LogUtils() {
        throw new IllegalStateException("you can't instantiate me!");
    }

    public static void debugInfo(String str) {
        debugInfo(DEFAULT_TAG, str);
    }

    public static void debugInfo(String str, String str2) {
        if (!isLog || TextUtils.isEmpty(str2)) {
            return;
        }
        Log.d(str, str2);
    }

    public static void debugLongInfo(String str) {
        debugLongInfo(DEFAULT_TAG, str);
    }

    public static void debugLongInfo(String str, String str2) {
        if (!isLog || TextUtils.isEmpty(str2)) {
            return;
        }
        String trim = str2.trim();
        int i = 0;
        while (i < trim.length()) {
            int i2 = i + 3500;
            Log.d(str, (trim.length() <= i2 ? trim.substring(i) : trim.substring(i, i2)).trim());
            i = i2;
        }
    }

    public static boolean isLog() {
        return isLog;
    }

    public static void setLog(boolean z) {
        isLog = z;
    }

    public static void warnInfo(String str) {
        warnInfo(DEFAULT_TAG, str);
    }

    public static void warnInfo(String str, String str2) {
        if (!isLog || TextUtils.isEmpty(str2)) {
            return;
        }
        Log.w(str, str2);
    }
}
