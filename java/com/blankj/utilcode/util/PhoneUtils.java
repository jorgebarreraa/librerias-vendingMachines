package com.blankj.utilcode.util;

import android.annotation.SuppressLint;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresPermission;

/* loaded from: classes.dex */
public final class PhoneUtils {
    private PhoneUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    @RequiresPermission("android.permission.CALL_PHONE")
    public static void call(@NonNull String str) {
        Utils.getApp().startActivity(UtilsBridge.getCallIntent(str));
    }

    public static void dial(@NonNull String str) {
        Utils.getApp().startActivity(UtilsBridge.getDialIntent(str));
    }

    @RequiresPermission("android.permission.READ_PHONE_STATE")
    @SuppressLint({"HardwareIds"})
    public static String getDeviceId() {
        if (Build.VERSION.SDK_INT >= 29) {
            return "";
        }
        TelephonyManager telephonyManager = getTelephonyManager();
        String deviceId = telephonyManager.getDeviceId();
        if (!TextUtils.isEmpty(deviceId)) {
            return deviceId;
        }
        if (Build.VERSION.SDK_INT < 26) {
            return "";
        }
        String imei = telephonyManager.getImei();
        if (!TextUtils.isEmpty(imei)) {
            return imei;
        }
        String meid = telephonyManager.getMeid();
        return TextUtils.isEmpty(meid) ? "" : meid;
    }

    @RequiresPermission("android.permission.READ_PHONE_STATE")
    public static String getIMEI() {
        return getImeiOrMeid(true);
    }

    @RequiresPermission("android.permission.READ_PHONE_STATE")
    @SuppressLint({"HardwareIds"})
    public static String getIMSI() {
        if (Build.VERSION.SDK_INT >= 29) {
            try {
                getTelephonyManager().getSubscriberId();
            } catch (SecurityException e) {
                e.printStackTrace();
                return "";
            }
        }
        return getTelephonyManager().getSubscriberId();
    }

    /* JADX WARN: Code restructure failed: missing block: B:37:0x00a9, code lost:
    
        if (r0.length() < 15) goto L55;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x00bd, code lost:
    
        r0 = "";
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x00bb, code lost:
    
        if (r0.length() == 14) goto L55;
     */
    /* JADX WARN: Removed duplicated region for block: B:31:0x009a  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00ac  */
    @androidx.annotation.RequiresPermission("android.permission.READ_PHONE_STATE")
    @android.annotation.SuppressLint({"HardwareIds"})
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String getImeiOrMeid(boolean r12) {
        /*
            int r0 = android.os.Build.VERSION.SDK_INT
            java.lang.String r1 = ""
            r2 = 29
            if (r0 < r2) goto L9
            return r1
        L9:
            android.telephony.TelephonyManager r0 = getTelephonyManager()
            int r2 = android.os.Build.VERSION.SDK_INT
            r3 = 26
            r4 = 1
            r5 = 0
            if (r2 < r3) goto L31
            if (r12 == 0) goto L24
            java.lang.String r12 = r0.getImei(r5)
            java.lang.String r0 = r0.getImei(r4)
            java.lang.String r12 = getMinOne(r12, r0)
            return r12
        L24:
            java.lang.String r12 = r0.getMeid(r5)
            java.lang.String r0 = r0.getMeid(r4)
            java.lang.String r12 = getMinOne(r12, r0)
            return r12
        L31:
            int r2 = android.os.Build.VERSION.SDK_INT
            r3 = 21
            r6 = 15
            r7 = 14
            if (r2 < r3) goto Lc3
            if (r12 == 0) goto L40
            java.lang.String r2 = "ril.gsm.imei"
            goto L42
        L40:
            java.lang.String r2 = "ril.cdma.meid"
        L42:
            java.lang.String r2 = getSystemPropertyByReflect(r2)
            boolean r3 = android.text.TextUtils.isEmpty(r2)
            r8 = 2
            if (r3 != 0) goto L62
            java.lang.String r12 = ","
            java.lang.String[] r12 = r2.split(r12)
            int r0 = r12.length
            if (r0 != r8) goto L5f
            r0 = r12[r5]
            r12 = r12[r4]
            java.lang.String r12 = getMinOne(r0, r12)
            return r12
        L5f:
            r12 = r12[r5]
            return r12
        L62:
            java.lang.String r2 = r0.getDeviceId()
            java.lang.Class r3 = r0.getClass()     // Catch: java.lang.reflect.InvocationTargetException -> L89 java.lang.IllegalAccessException -> L8e java.lang.NoSuchMethodException -> L93
            java.lang.String r9 = "getDeviceId"
            java.lang.Class[] r10 = new java.lang.Class[r4]     // Catch: java.lang.reflect.InvocationTargetException -> L89 java.lang.IllegalAccessException -> L8e java.lang.NoSuchMethodException -> L93
            java.lang.Class r11 = java.lang.Integer.TYPE     // Catch: java.lang.reflect.InvocationTargetException -> L89 java.lang.IllegalAccessException -> L8e java.lang.NoSuchMethodException -> L93
            r10[r5] = r11     // Catch: java.lang.reflect.InvocationTargetException -> L89 java.lang.IllegalAccessException -> L8e java.lang.NoSuchMethodException -> L93
            java.lang.reflect.Method r3 = r3.getMethod(r9, r10)     // Catch: java.lang.reflect.InvocationTargetException -> L89 java.lang.IllegalAccessException -> L8e java.lang.NoSuchMethodException -> L93
            java.lang.Object[] r9 = new java.lang.Object[r4]     // Catch: java.lang.reflect.InvocationTargetException -> L89 java.lang.IllegalAccessException -> L8e java.lang.NoSuchMethodException -> L93
            if (r12 == 0) goto L7b
            goto L7c
        L7b:
            r4 = 2
        L7c:
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch: java.lang.reflect.InvocationTargetException -> L89 java.lang.IllegalAccessException -> L8e java.lang.NoSuchMethodException -> L93
            r9[r5] = r4     // Catch: java.lang.reflect.InvocationTargetException -> L89 java.lang.IllegalAccessException -> L8e java.lang.NoSuchMethodException -> L93
            java.lang.Object r0 = r3.invoke(r0, r9)     // Catch: java.lang.reflect.InvocationTargetException -> L89 java.lang.IllegalAccessException -> L8e java.lang.NoSuchMethodException -> L93
            java.lang.String r0 = (java.lang.String) r0     // Catch: java.lang.reflect.InvocationTargetException -> L89 java.lang.IllegalAccessException -> L8e java.lang.NoSuchMethodException -> L93
            goto L98
        L89:
            r0 = move-exception
            r0.printStackTrace()
            goto L97
        L8e:
            r0 = move-exception
            r0.printStackTrace()
            goto L97
        L93:
            r0 = move-exception
            r0.printStackTrace()
        L97:
            r0 = r1
        L98:
            if (r12 == 0) goto Lac
            if (r2 == 0) goto La3
            int r12 = r2.length()
            if (r12 >= r6) goto La3
            r2 = r1
        La3:
            if (r0 == 0) goto Lbe
            int r12 = r0.length()
            if (r12 >= r6) goto Lbe
            goto Lbd
        Lac:
            if (r2 == 0) goto Lb5
            int r12 = r2.length()
            if (r12 != r7) goto Lb5
            r2 = r1
        Lb5:
            if (r0 == 0) goto Lbe
            int r12 = r0.length()
            if (r12 != r7) goto Lbe
        Lbd:
            r0 = r1
        Lbe:
            java.lang.String r12 = getMinOne(r2, r0)
            return r12
        Lc3:
            java.lang.String r0 = r0.getDeviceId()
            if (r12 == 0) goto Ld2
            if (r0 == 0) goto Ldb
            int r12 = r0.length()
            if (r12 < r6) goto Ldb
            return r0
        Ld2:
            if (r0 == 0) goto Ldb
            int r12 = r0.length()
            if (r12 != r7) goto Ldb
            return r0
        Ldb:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.blankj.utilcode.util.PhoneUtils.getImeiOrMeid(boolean):java.lang.String");
    }

    @RequiresPermission("android.permission.READ_PHONE_STATE")
    public static String getMEID() {
        return getImeiOrMeid(false);
    }

    private static String getMinOne(String str, String str2) {
        boolean isEmpty = TextUtils.isEmpty(str);
        boolean isEmpty2 = TextUtils.isEmpty(str2);
        return (isEmpty && isEmpty2) ? "" : (isEmpty || isEmpty2) ? !isEmpty ? str : str2 : str.compareTo(str2) <= 0 ? str : str2;
    }

    public static int getPhoneType() {
        return getTelephonyManager().getPhoneType();
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x000b, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x000c, code lost:
    
        r0.printStackTrace();
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0011, code lost:
    
        return "";
     */
    @androidx.annotation.RequiresPermission("android.permission.READ_PHONE_STATE")
    @android.annotation.SuppressLint({"HardwareIds"})
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String getSerial() {
        /*
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 29
            if (r0 < r1) goto L12
            java.lang.String r0 = android.os.Build.getSerial()     // Catch: java.lang.SecurityException -> Lb
            return r0
        Lb:
            r0 = move-exception
            r0.printStackTrace()
            java.lang.String r0 = ""
            return r0
        L12:
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 26
            if (r0 < r1) goto L1d
            java.lang.String r0 = android.os.Build.getSerial()
            goto L1f
        L1d:
            java.lang.String r0 = android.os.Build.SERIAL
        L1f:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.blankj.utilcode.util.PhoneUtils.getSerial():java.lang.String");
    }

    public static String getSimOperatorByMnc() {
        String simOperator = getTelephonyManager().getSimOperator();
        if (simOperator == null) {
            return "";
        }
        char c = 65535;
        int hashCode = simOperator.hashCode();
        if (hashCode != 49679479) {
            if (hashCode != 49679502) {
                if (hashCode != 49679532) {
                    switch (hashCode) {
                        case 49679470:
                            if (simOperator.equals("46000")) {
                                c = 0;
                                break;
                            }
                            break;
                        case 49679471:
                            if (simOperator.equals("46001")) {
                                c = 4;
                                break;
                            }
                            break;
                        case 49679472:
                            if (simOperator.equals("46002")) {
                                c = 1;
                                break;
                            }
                            break;
                        case 49679473:
                            if (simOperator.equals("46003")) {
                                c = 7;
                                break;
                            }
                            break;
                        default:
                            switch (hashCode) {
                                case 49679475:
                                    if (simOperator.equals("46005")) {
                                        c = '\b';
                                        break;
                                    }
                                    break;
                                case 49679476:
                                    if (simOperator.equals("46006")) {
                                        c = 5;
                                        break;
                                    }
                                    break;
                                case 49679477:
                                    if (simOperator.equals("46007")) {
                                        c = 2;
                                        break;
                                    }
                                    break;
                            }
                    }
                } else if (simOperator.equals("46020")) {
                    c = 3;
                }
            } else if (simOperator.equals("46011")) {
                c = '\t';
            }
        } else if (simOperator.equals("46009")) {
            c = 6;
        }
        switch (c) {
            case 0:
            case 1:
            case 2:
            case 3:
                return "中国移动";
            case 4:
            case 5:
            case 6:
                return "中国联通";
            case 7:
            case '\b':
            case '\t':
                return "中国电信";
            default:
                return simOperator;
        }
    }

    public static String getSimOperatorName() {
        return getTelephonyManager().getSimOperatorName();
    }

    private static String getSystemPropertyByReflect(String str) {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            return (String) cls.getMethod("get", String.class, String.class).invoke(cls, str, "");
        } catch (Exception unused) {
            return "";
        }
    }

    private static TelephonyManager getTelephonyManager() {
        return (TelephonyManager) Utils.getApp().getSystemService("phone");
    }

    public static boolean isPhone() {
        return getTelephonyManager().getPhoneType() != 0;
    }

    public static boolean isSimCardReady() {
        return getTelephonyManager().getSimState() == 5;
    }

    public static void sendSms(@NonNull String str, String str2) {
        Utils.getApp().startActivity(UtilsBridge.getSendSmsIntent(str, str2));
    }
}
