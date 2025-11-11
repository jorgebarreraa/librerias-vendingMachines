package com.jess.arms.utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.ClipboardManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.PowerManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import java.io.File;
import java.text.NumberFormat;
import org.eclipse.paho.client.mqttv3.internal.ClientDefaults;

@TargetApi(14)
/* loaded from: classes.dex */
public class DeviceUtils {
    public static boolean GTE_HC = false;
    public static boolean GTE_ICS = false;
    public static final int NETTYPE_CMNET = 3;
    public static final int NETTYPE_CMWAP = 2;
    public static final int NETTYPE_WIFI = 1;
    public static boolean PRE_HC;
    private static Boolean _hasBigScreen;
    private static Boolean _hasCamera;
    private static Boolean _isTablet;
    private static Integer _loadFactor;
    public static float displayDensity;

    static {
        GTE_ICS = Build.VERSION.SDK_INT >= 14;
        GTE_HC = Build.VERSION.SDK_INT >= 11;
        PRE_HC = Build.VERSION.SDK_INT < 11;
    }

    private DeviceUtils() {
        throw new IllegalStateException("you can't instantiate me!");
    }

    public static void cancelFullScreen(Activity activity) {
        WindowManager.LayoutParams attributes = activity.getWindow().getAttributes();
        attributes.flags &= -1025;
        activity.getWindow().setAttributes(attributes);
        activity.getWindow().clearFlags(512);
    }

    public static void copyTextToBoard(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        ((ClipboardManager) context.getSystemService("clipboard")).setText(str);
    }

    public static float dpToPixel(Context context, float f) {
        return f * (getDisplayMetrics(context).densityDpi / 160.0f);
    }

    public static String getCurCountryLan(Context context) {
        return context.getResources().getConfiguration().locale.getLanguage() + "-" + context.getResources().getConfiguration().locale.getCountry();
    }

    public static int getDefaultLoadFactor(Context context) {
        if (_loadFactor == null) {
            Integer valueOf = Integer.valueOf(context.getResources().getConfiguration().screenLayout & 15);
            _loadFactor = valueOf;
            _loadFactor = Integer.valueOf(Math.max(valueOf.intValue(), 1));
        }
        return _loadFactor.intValue();
    }

    public static float getDensity(Context context) {
        if (displayDensity == 0.0d) {
            displayDensity = getDisplayMetrics(context).density;
        }
        return displayDensity;
    }

    public static DisplayMetrics getDisplayMetrics(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics;
    }

    public static String getIMEI(Context context) {
        return ((TelephonyManager) context.getSystemService("phone")).getDeviceId();
    }

    public static Intent getInstallApkIntent(File file) {
        Intent intent = new Intent();
        intent.setFlags(ClientDefaults.MAX_MSG_SIZE);
        intent.setAction("android.intent.action.VIEW");
        intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        return intent;
    }

    public static int getNetworkType(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getApplicationContext().getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo == null) {
            return 0;
        }
        int type = activeNetworkInfo.getType();
        if (type != 0) {
            return type == 1 ? 1 : 0;
        }
        String extraInfo = activeNetworkInfo.getExtraInfo();
        if (extraInfo == null || extraInfo.isEmpty()) {
            return 0;
        }
        return "cmnet".equalsIgnoreCase(extraInfo) ? 3 : 2;
    }

    public static PackageInfo getPackageInfo(Context context, String str) {
        try {
            return context.getPackageManager().getPackageInfo(str, 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getPhoneType() {
        return Build.MODEL;
    }

    public static int[] getRealScreenSize(Activity activity) {
        int[] iArr = new int[2];
        Display defaultDisplay = activity.getWindowManager().getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        defaultDisplay.getMetrics(displayMetrics);
        int i = displayMetrics.widthPixels;
        int i2 = displayMetrics.heightPixels;
        if (Build.VERSION.SDK_INT >= 14 && Build.VERSION.SDK_INT < 17) {
            try {
                i = ((Integer) Display.class.getMethod("getRawWidth", new Class[0]).invoke(defaultDisplay, new Object[0])).intValue();
                i2 = ((Integer) Display.class.getMethod("getRawHeight", new Class[0]).invoke(defaultDisplay, new Object[0])).intValue();
            } catch (Exception unused) {
            }
        }
        if (Build.VERSION.SDK_INT >= 17) {
            try {
                Point point = new Point();
                Display.class.getMethod("getRealSize", Point.class).invoke(defaultDisplay, point);
                i = point.x;
                i2 = point.y;
            } catch (Exception unused2) {
            }
        }
        iArr[0] = i;
        iArr[1] = i2;
        return iArr;
    }

    public static float getScreenHeight(Context context) {
        return getDisplayMetrics(context).heightPixels;
    }

    public static float getScreenWidth(Context context) {
        return getDisplayMetrics(context).widthPixels;
    }

    public static int getStatuBarHeight(Context context) {
        try {
            Class<?> cls = Class.forName("com.android.internal.R$dimen");
            return context.getResources().getDimensionPixelSize(Integer.parseInt(cls.getField("status_bar_height").get(cls.newInstance()).toString()));
        } catch (Exception e) {
            e.printStackTrace();
            return 38;
        }
    }

    public static int getStatusBarHeight(Context context) {
        try {
            Class<?> cls = Class.forName("com.android.internal.R$dimen");
            return context.getResources().getDimensionPixelSize(Integer.parseInt(cls.getField("status_bar_height").get(cls.newInstance()).toString()));
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static int getVersionCode(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException unused) {
            return 0;
        }
    }

    public static int getVersionCode(Context context, String str) {
        try {
            return context.getPackageManager().getPackageInfo(str, 0).versionCode;
        } catch (PackageManager.NameNotFoundException unused) {
            return 0;
        }
    }

    public static String getVersionName(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException unused) {
            return "";
        }
    }

    public static boolean hasBigScreen(Context context) {
        if (_hasBigScreen == null) {
            Boolean valueOf = Boolean.valueOf((context.getResources().getConfiguration().screenLayout & 15) >= 3);
            _hasBigScreen = valueOf;
            if (!valueOf.booleanValue()) {
                _hasBigScreen = Boolean.valueOf(getDensity(context) > 1.5f);
            }
        }
        return _hasBigScreen.booleanValue();
    }

    public static boolean hasCamera(Context context) {
        if (_hasCamera == null) {
            PackageManager packageManager = context.getPackageManager();
            _hasCamera = Boolean.valueOf(packageManager.hasSystemFeature("android.hardware.camera.front") || packageManager.hasSystemFeature("android.hardware.camera"));
        }
        return _hasCamera.booleanValue();
    }

    public static boolean hasHardwareMenuKey(Context context) {
        if (PRE_HC) {
            return true;
        }
        if (GTE_ICS) {
            return ViewConfiguration.get(context).hasPermanentMenuKey();
        }
        return false;
    }

    public static boolean hasInternet(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getApplicationContext().getSystemService("connectivity");
        return (connectivityManager == null || connectivityManager.getActiveNetworkInfo() == null) ? false : true;
    }

    public static boolean hasStatusBar(Activity activity) {
        return (activity.getWindow().getAttributes().flags & 1024) != 1024;
    }

    public static void hideAnimatedView(View view) {
        if (!PRE_HC || view == null) {
            return;
        }
        view.setPadding(view.getWidth(), 0, 0, 0);
    }

    public static void hideSoftKeyboard(Context context, View view) {
        if (view == null) {
            return;
        }
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService("input_method");
        if (inputMethodManager.isActive()) {
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public static void installAPK(Context context, File file) {
        if (file == null || !file.exists()) {
            return;
        }
        Intent intent = new Intent();
        intent.setFlags(ClientDefaults.MAX_MSG_SIZE);
        intent.setAction("android.intent.action.VIEW");
        intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        context.startActivity(intent);
    }

    public static boolean isExitsSdcard() {
        return Environment.getExternalStorageState().equals("mounted");
    }

    public static boolean isHaveMarket(Context context) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.APP_MARKET");
        return context.getPackageManager().queryIntentActivities(intent, 0).size() > 0;
    }

    public static boolean isLandscape(Context context) {
        return context.getResources().getConfiguration().orientation == 2;
    }

    public static boolean isPackageExist(Context context, String str) {
        try {
        } catch (PackageManager.NameNotFoundException e) {
            Log.e("TDvice", e.getMessage());
        }
        return context.getPackageManager().getPackageInfo(str, 0) != null;
    }

    public static boolean isPortrait(Context context) {
        return context.getResources().getConfiguration().orientation == 1;
    }

    public static boolean isScreenOn(Context context) {
        return ((PowerManager) context.getSystemService("power")).isScreenOn();
    }

    public static boolean isSdcardReady() {
        return "mounted".equals(Environment.getExternalStorageState());
    }

    public static boolean isTablet(Context context) {
        if (_isTablet == null) {
            _isTablet = Boolean.valueOf((context.getResources().getConfiguration().screenLayout & 15) >= 3);
        }
        return _isTablet.booleanValue();
    }

    public static boolean isWifiOpen(Context context) {
        boolean z = false;
        for (NetworkInfo networkInfo : ((ConnectivityManager) context.getApplicationContext().getSystemService("connectivity")).getAllNetworkInfo()) {
            if (networkInfo.getState() == NetworkInfo.State.CONNECTED) {
                if (networkInfo.getType() == 0) {
                    z = false;
                }
                if (networkInfo.getType() == 1) {
                    z = true;
                }
            }
        }
        return z;
    }

    public static boolean isZhCN(Context context) {
        return "CN".equalsIgnoreCase(context.getResources().getConfiguration().locale.getCountry());
    }

    public static boolean netIsConnected(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getApplicationContext().getSystemService("connectivity");
        return connectivityManager.getNetworkInfo(0).isConnected() || connectivityManager.getNetworkInfo(1).isConnected();
    }

    public static void openApp(Context context, String str) {
        Intent launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage(str);
        if (launchIntentForPackage == null) {
            launchIntentForPackage = new Intent(str);
        }
        context.startActivity(launchIntentForPackage);
    }

    public static boolean openAppActivity(Context context, String str, String str2) {
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.LAUNCHER");
        intent.setComponent(new ComponentName(str, str2));
        try {
            context.startActivity(intent);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    public static void openCamera(Context context) {
        Intent intent = new Intent();
        intent.setAction("android.media.action.STILL_IMAGE_CAMERA");
        intent.setFlags(885260288);
        context.startActivity(intent);
    }

    public static void openDail(Context context) {
        Intent intent = new Intent("android.intent.action.DIAL");
        intent.setFlags(ClientDefaults.MAX_MSG_SIZE);
        context.startActivity(intent);
    }

    public static void openDial(Context context, String str) {
        context.startActivity(new Intent("android.intent.action.DIAL", Uri.parse("tel:" + str)));
    }

    public static void openSMS(Context context, String str, String str2) {
        Intent intent = new Intent("android.intent.action.SENDTO", Uri.parse("smsto:" + str2));
        intent.putExtra("sms_body", str);
        context.startActivity(intent);
    }

    public static void openSendMsg(Context context) {
        Intent intent = new Intent("android.intent.action.SENDTO", Uri.parse("smsto:"));
        intent.setFlags(ClientDefaults.MAX_MSG_SIZE);
        context.startActivity(intent);
    }

    public static String percent(double d, double d2) {
        double d3 = d / d2;
        NumberFormat percentInstance = NumberFormat.getPercentInstance();
        percentInstance.setMinimumFractionDigits(2);
        return percentInstance.format(d3);
    }

    public static String percent2(double d, double d2) {
        double d3 = d / d2;
        NumberFormat percentInstance = NumberFormat.getPercentInstance();
        percentInstance.setMinimumFractionDigits(0);
        return percentInstance.format(d3);
    }

    public static float pixelsToDp(Context context, float f) {
        return f / (getDisplayMetrics(context).densityDpi / 160.0f);
    }

    public static void sendEmail(Context context, String str, String str2, String... strArr) {
        try {
            Intent intent = new Intent("android.intent.action.SEND");
            intent.setType("message/rfc822");
            intent.putExtra("android.intent.extra.EMAIL", strArr);
            intent.putExtra("android.intent.extra.SUBJECT", str);
            intent.putExtra("android.intent.extra.TEXT", str2);
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void setFullScreen(Activity activity) {
        WindowManager.LayoutParams attributes = activity.getWindow().getAttributes();
        attributes.flags |= 1024;
        activity.getWindow().setAttributes(attributes);
        activity.getWindow().addFlags(512);
    }

    public static void showAnimatedView(View view) {
        if (!PRE_HC || view == null) {
            return;
        }
        view.setPadding(0, 0, 0, 0);
    }

    public static void showSoftKeyboard(Dialog dialog) {
        dialog.getWindow().setSoftInputMode(4);
    }

    public static void showSoftKeyboard(Context context, View view) {
        ((InputMethodManager) context.getSystemService("input_method")).showSoftInput(view, 2);
    }

    public static void showSystemShareOption(Activity activity, String str, String str2) {
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("text/plain");
        intent.putExtra("android.intent.extra.SUBJECT", "分享：" + str);
        intent.putExtra("android.intent.extra.TEXT", str + " " + str2);
        activity.startActivity(Intent.createChooser(intent, "选择分享"));
    }

    public static void toogleSoftKeyboard(Context context, View view) {
        ((InputMethodManager) context.getSystemService("input_method")).toggleSoftInput(0, 2);
    }

    public static void uninstallApk(Context context, String str) {
        if (isPackageExist(context, str)) {
            context.startActivity(new Intent("android.intent.action.DELETE", Uri.parse("package:" + str)));
        }
    }
}
