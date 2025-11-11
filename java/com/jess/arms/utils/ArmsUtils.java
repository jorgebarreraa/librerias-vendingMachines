package com.jess.arms.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.SpannableString;
import android.text.SpannedString;
import android.text.style.AbsoluteSizeSpan;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.load.Key;
import com.jess.arms.base.App;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.integration.AppManager;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import kotlin.UByte;

/* loaded from: classes.dex */
public class ArmsUtils {
    public static Toast mToast;

    private ArmsUtils() {
        throw new IllegalStateException("you can't instantiate me!");
    }

    public static void configRecycleView(RecyclerView recyclerView, RecyclerView.LayoutManager layoutManager) {
        recyclerView.setLayoutManager(layoutManager);
    }

    public static void configRecyclerView(RecyclerView recyclerView, RecyclerView.LayoutManager layoutManager) {
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    public static int dip2px(@NonNull Context context, float f) {
        return (int) ((f * getResources(context).getDisplayMetrics().density) + 0.5f);
    }

    public static String encodeToMD5(String str) {
        byte[] bArr = new byte[0];
        try {
            bArr = Build.VERSION.SDK_INT >= 19 ? MessageDigest.getInstance("MD5").digest(str.getBytes(StandardCharsets.UTF_8)) : MessageDigest.getInstance("MD5").digest(str.getBytes(Key.STRING_CHARSET_NAME));
        } catch (Exception e) {
            e.printStackTrace();
        }
        StringBuilder sb = new StringBuilder(bArr.length * 2);
        for (byte b : bArr) {
            int i = b & UByte.MAX_VALUE;
            if (i < 16) {
                sb.append("0");
            }
            sb.append(Integer.toHexString(i));
        }
        return sb.toString();
    }

    public static void exitApp() {
        AppManager.getAppManager().appExit();
    }

    public static int findLayout(Context context, String str) {
        return getResources(context).getIdentifier(str, "layout", context.getPackageName());
    }

    public static <T extends View> T findViewByName(Context context, Activity activity, String str) {
        return (T) activity.findViewById(getResources(context).getIdentifier(str, "id", context.getPackageName()));
    }

    public static <T extends View> T findViewByName(Context context, View view, String str) {
        return (T) view.findViewById(getResources(context).getIdentifier(str, "id", context.getPackageName()));
    }

    public static int getColor(Context context, int i) {
        return getResources(context).getColor(i);
    }

    public static int getColor(Context context, String str) {
        return getColor(context, getResources(context).getIdentifier(str, "color", context.getPackageName()));
    }

    public static float getDimens(Context context, String str) {
        return getResources(context).getDimension(getResources(context).getIdentifier(str, "dimen", context.getPackageName()));
    }

    public static int getDimens(Context context, int i) {
        return (int) getResources(context).getDimension(i);
    }

    public static Drawable getDrawablebyResource(Context context, int i) {
        return getResources(context).getDrawable(i);
    }

    public static Resources getResources(Context context) {
        return context.getResources();
    }

    public static int getScreenHeidth(Context context) {
        return getResources(context).getDisplayMetrics().heightPixels;
    }

    public static int getScreenWidth(Context context) {
        return getResources(context).getDisplayMetrics().widthPixels;
    }

    public static String getString(Context context, int i) {
        return getResources(context).getString(i);
    }

    public static String getString(Context context, String str) {
        return getString(context, getResources(context).getIdentifier(str, "string", context.getPackageName()));
    }

    public static String[] getStringArray(Context context, int i) {
        return getResources(context).getStringArray(i);
    }

    public static View inflate(Context context, int i) {
        return View.inflate(context, i, null);
    }

    public static boolean isEmpty(Object obj) {
        return obj == null;
    }

    public static void killAll() {
        AppManager.getAppManager().killAll();
    }

    @SuppressLint({"ShowToast"})
    public static void makeText(Context context, String str) {
        if (mToast == null) {
            mToast = Toast.makeText(context.getApplicationContext(), str, 0);
            ((TextView) ((LinearLayout) mToast.getView()).getChildAt(0)).setTextSize(30.0f);
            mToast.setGravity(17, 0, 0);
        }
        mToast.setText(str);
        mToast.show();
    }

    public static AppComponent obtainAppComponentFromContext(Context context) {
        Preconditions.checkNotNull(context, "%s cannot be null", Context.class.getName());
        Preconditions.checkState(context.getApplicationContext() instanceof App, "%s must be implements %s", context.getApplicationContext().getClass().getName(), App.class.getName());
        return ((App) context.getApplicationContext()).getAppComponent();
    }

    public static int pix2dip(@NonNull Context context, int i) {
        return (int) ((i / getResources(context).getDisplayMetrics().density) + 0.5f);
    }

    public static int px2sp(@NonNull Context context, float f) {
        return (int) ((f / getResources(context).getDisplayMetrics().scaledDensity) + 0.5f);
    }

    public static void removeChild(View view) {
        ViewParent parent = view.getParent();
        if (parent instanceof ViewGroup) {
            ((ViewGroup) parent).removeView(view);
        }
    }

    public static void setViewHintSize(Context context, int i, TextView textView, int i2) {
        SpannableString spannableString = new SpannableString(getResources(context).getString(i2));
        spannableString.setSpan(new AbsoluteSizeSpan(i, true), 0, spannableString.length(), 33);
        textView.setHint(new SpannedString(spannableString));
    }

    public static void snackbarText(String str) {
        if (str.trim().isEmpty() || str.contains("UnknownHostException")) {
            return;
        }
        AppManager.getAppManager().showSnackbar(str, false);
    }

    public static void snackbarTextWithLong(String str) {
        AppManager.getAppManager().showSnackbar(str, true);
    }

    public static int sp2px(@NonNull Context context, float f) {
        return (int) ((f * getResources(context).getDisplayMetrics().scaledDensity) + 0.5f);
    }

    public static void startActivity(Activity activity, Intent intent) {
        activity.startActivity(intent);
    }

    public static void startActivity(Activity activity, Class cls) {
        activity.startActivity(new Intent(activity.getApplicationContext(), (Class<?>) cls));
    }

    public static void startActivity(Intent intent) {
        AppManager.getAppManager().startActivity(intent);
    }

    public static void startActivity(Class cls) {
        AppManager.getAppManager().startActivity(cls);
    }

    public static void statuInScreen(Activity activity) {
        WindowManager.LayoutParams attributes = activity.getWindow().getAttributes();
        attributes.flags &= -1025;
        activity.getWindow().setAttributes(attributes);
        activity.getWindow().addFlags(256);
        activity.getWindow().addFlags(512);
    }
}
