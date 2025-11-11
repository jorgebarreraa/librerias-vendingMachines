package com.blankj.utilcode.util;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import androidx.annotation.AnimRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.blankj.utilcode.util.Utils;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.eclipse.paho.client.mqttv3.internal.ClientDefaults;

/* loaded from: classes.dex */
public final class ActivityUtils {
    private ActivityUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static void addActivityLifecycleCallbacks(@Nullable Activity activity, @Nullable Utils.ActivityLifecycleCallbacks activityLifecycleCallbacks) {
        UtilsBridge.addActivityLifecycleCallbacks(activity, activityLifecycleCallbacks);
    }

    public static void addActivityLifecycleCallbacks(@Nullable Utils.ActivityLifecycleCallbacks activityLifecycleCallbacks) {
        UtilsBridge.addActivityLifecycleCallbacks(activityLifecycleCallbacks);
    }

    public static void finishActivity(@NonNull Activity activity) {
        finishActivity(activity, false);
    }

    public static void finishActivity(@NonNull Activity activity, @AnimRes int i, @AnimRes int i2) {
        activity.finish();
        activity.overridePendingTransition(i, i2);
    }

    public static void finishActivity(@NonNull Activity activity, boolean z) {
        activity.finish();
        if (z) {
            return;
        }
        activity.overridePendingTransition(0, 0);
    }

    public static void finishActivity(@NonNull Class<? extends Activity> cls) {
        finishActivity(cls, false);
    }

    public static void finishActivity(@NonNull Class<? extends Activity> cls, @AnimRes int i, @AnimRes int i2) {
        for (Activity activity : UtilsBridge.getActivityList()) {
            if (activity.getClass().equals(cls)) {
                activity.finish();
                activity.overridePendingTransition(i, i2);
            }
        }
    }

    public static void finishActivity(@NonNull Class<? extends Activity> cls, boolean z) {
        for (Activity activity : UtilsBridge.getActivityList()) {
            if (activity.getClass().equals(cls)) {
                activity.finish();
                if (!z) {
                    activity.overridePendingTransition(0, 0);
                }
            }
        }
    }

    public static void finishAllActivities() {
        finishAllActivities(false);
    }

    public static void finishAllActivities(@AnimRes int i, @AnimRes int i2) {
        for (Activity activity : UtilsBridge.getActivityList()) {
            activity.finish();
            activity.overridePendingTransition(i, i2);
        }
    }

    public static void finishAllActivities(boolean z) {
        for (Activity activity : UtilsBridge.getActivityList()) {
            activity.finish();
            if (!z) {
                activity.overridePendingTransition(0, 0);
            }
        }
    }

    public static void finishAllActivitiesExceptNewest() {
        finishAllActivitiesExceptNewest(false);
    }

    public static void finishAllActivitiesExceptNewest(@AnimRes int i, @AnimRes int i2) {
        List<Activity> activityList = UtilsBridge.getActivityList();
        for (int i3 = 1; i3 < activityList.size(); i3++) {
            finishActivity(activityList.get(i3), i, i2);
        }
    }

    public static void finishAllActivitiesExceptNewest(boolean z) {
        List<Activity> activityList = UtilsBridge.getActivityList();
        for (int i = 1; i < activityList.size(); i++) {
            finishActivity(activityList.get(i), z);
        }
    }

    public static void finishOtherActivities(@NonNull Class<? extends Activity> cls) {
        finishOtherActivities(cls, false);
    }

    public static void finishOtherActivities(@NonNull Class<? extends Activity> cls, @AnimRes int i, @AnimRes int i2) {
        for (Activity activity : UtilsBridge.getActivityList()) {
            if (!activity.getClass().equals(cls)) {
                finishActivity(activity, i, i2);
            }
        }
    }

    public static void finishOtherActivities(@NonNull Class<? extends Activity> cls, boolean z) {
        for (Activity activity : UtilsBridge.getActivityList()) {
            if (!activity.getClass().equals(cls)) {
                finishActivity(activity, z);
            }
        }
    }

    public static boolean finishToActivity(@NonNull Activity activity, boolean z) {
        return finishToActivity(activity, z, false);
    }

    public static boolean finishToActivity(@NonNull Activity activity, boolean z, @AnimRes int i, @AnimRes int i2) {
        for (Activity activity2 : UtilsBridge.getActivityList()) {
            if (activity2.equals(activity)) {
                if (!z) {
                    return true;
                }
                finishActivity(activity2, i, i2);
                return true;
            }
            finishActivity(activity2, i, i2);
        }
        return false;
    }

    public static boolean finishToActivity(@NonNull Activity activity, boolean z, boolean z2) {
        for (Activity activity2 : UtilsBridge.getActivityList()) {
            if (activity2.equals(activity)) {
                if (!z) {
                    return true;
                }
                finishActivity(activity2, z2);
                return true;
            }
            finishActivity(activity2, z2);
        }
        return false;
    }

    public static boolean finishToActivity(@NonNull Class<? extends Activity> cls, boolean z) {
        return finishToActivity(cls, z, false);
    }

    public static boolean finishToActivity(@NonNull Class<? extends Activity> cls, boolean z, @AnimRes int i, @AnimRes int i2) {
        for (Activity activity : UtilsBridge.getActivityList()) {
            if (activity.getClass().equals(cls)) {
                if (!z) {
                    return true;
                }
                finishActivity(activity, i, i2);
                return true;
            }
            finishActivity(activity, i, i2);
        }
        return false;
    }

    public static boolean finishToActivity(@NonNull Class<? extends Activity> cls, boolean z, boolean z2) {
        for (Activity activity : UtilsBridge.getActivityList()) {
            if (activity.getClass().equals(cls)) {
                if (!z) {
                    return true;
                }
                finishActivity(activity, z2);
                return true;
            }
            finishActivity(activity, z2);
        }
        return false;
    }

    @Nullable
    public static Activity getActivityByContext(@Nullable Context context) {
        if (context == null) {
            return null;
        }
        Activity activityByContextInner = getActivityByContextInner(context);
        if (isActivityAlive(activityByContextInner)) {
            return activityByContextInner;
        }
        return null;
    }

    @Nullable
    private static Activity getActivityByContextInner(@Nullable Context context) {
        if (context == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        while (context instanceof ContextWrapper) {
            if (!(context instanceof Activity)) {
                Activity activityFromDecorContext = getActivityFromDecorContext(context);
                if (activityFromDecorContext == null) {
                    arrayList.add(context);
                    context = ((ContextWrapper) context).getBaseContext();
                    if (context == null || arrayList.contains(context)) {
                        break;
                    }
                } else {
                    return activityFromDecorContext;
                }
            } else {
                return (Activity) context;
            }
        }
        return null;
    }

    @Nullable
    private static Activity getActivityFromDecorContext(@Nullable Context context) {
        if (context != null && context.getClass().getName().equals("com.android.internal.policy.DecorContext")) {
            try {
                Field declaredField = context.getClass().getDeclaredField("mActivityContext");
                declaredField.setAccessible(true);
                return (Activity) ((WeakReference) declaredField.get(context)).get();
            } catch (Exception unused) {
            }
        }
        return null;
    }

    @Nullable
    public static Drawable getActivityIcon(@NonNull Activity activity) {
        return getActivityIcon(activity.getComponentName());
    }

    @Nullable
    public static Drawable getActivityIcon(@NonNull ComponentName componentName) {
        try {
            return Utils.getApp().getPackageManager().getActivityIcon(componentName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Nullable
    public static Drawable getActivityIcon(@NonNull Class<? extends Activity> cls) {
        return getActivityIcon(new ComponentName(Utils.getApp(), cls));
    }

    public static List<Activity> getActivityList() {
        return UtilsBridge.getActivityList();
    }

    @Nullable
    public static Drawable getActivityLogo(@NonNull Activity activity) {
        return getActivityLogo(activity.getComponentName());
    }

    @Nullable
    public static Drawable getActivityLogo(@NonNull ComponentName componentName) {
        try {
            return Utils.getApp().getPackageManager().getActivityLogo(componentName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Nullable
    public static Drawable getActivityLogo(@NonNull Class<? extends Activity> cls) {
        return getActivityLogo(new ComponentName(Utils.getApp(), cls));
    }

    public static String getLauncherActivity() {
        return getLauncherActivity(Utils.getApp().getPackageName());
    }

    public static String getLauncherActivity(@NonNull String str) {
        if (UtilsBridge.isSpace(str)) {
            return "";
        }
        Intent intent = new Intent("android.intent.action.MAIN", (Uri) null);
        intent.addCategory("android.intent.category.LAUNCHER");
        intent.setPackage(str);
        List<ResolveInfo> queryIntentActivities = Utils.getApp().getPackageManager().queryIntentActivities(intent, 0);
        return (queryIntentActivities == null || queryIntentActivities.size() == 0) ? "" : queryIntentActivities.get(0).activityInfo.name;
    }

    public static List<String> getMainActivities() {
        return getMainActivities(Utils.getApp().getPackageName());
    }

    public static List<String> getMainActivities(@NonNull String str) {
        ArrayList arrayList = new ArrayList();
        Intent intent = new Intent("android.intent.action.MAIN", (Uri) null);
        intent.setPackage(str);
        List<ResolveInfo> queryIntentActivities = Utils.getApp().getPackageManager().queryIntentActivities(intent, 0);
        int size = queryIntentActivities.size();
        if (size == 0) {
            return arrayList;
        }
        for (int i = 0; i < size; i++) {
            ResolveInfo resolveInfo = queryIntentActivities.get(i);
            if (resolveInfo.activityInfo.processName.equals(str)) {
                arrayList.add(resolveInfo.activityInfo.name);
            }
        }
        return arrayList;
    }

    private static Bundle getOptionsBundle(Activity activity, View[] viewArr) {
        int length;
        if (Build.VERSION.SDK_INT < 21 || viewArr == null || (length = viewArr.length) <= 0) {
            return null;
        }
        Pair[] pairArr = new Pair[length];
        for (int i = 0; i < length; i++) {
            pairArr[i] = Pair.create(viewArr[i], viewArr[i].getTransitionName());
        }
        return ActivityOptionsCompat.makeSceneTransitionAnimation(activity, pairArr).toBundle();
    }

    private static Bundle getOptionsBundle(Context context, int i, int i2) {
        return ActivityOptionsCompat.makeCustomAnimation(context, i, i2).toBundle();
    }

    private static Bundle getOptionsBundle(Fragment fragment, int i, int i2) {
        FragmentActivity activity = fragment.getActivity();
        if (activity == null) {
            return null;
        }
        return ActivityOptionsCompat.makeCustomAnimation(activity, i, i2).toBundle();
    }

    private static Bundle getOptionsBundle(Fragment fragment, View[] viewArr) {
        FragmentActivity activity = fragment.getActivity();
        if (activity == null) {
            return null;
        }
        return getOptionsBundle(activity, viewArr);
    }

    public static Activity getTopActivity() {
        return UtilsBridge.getTopActivity();
    }

    private static Context getTopActivityOrApp() {
        Activity topActivity;
        return (!UtilsBridge.isAppForeground() || (topActivity = getTopActivity()) == null) ? Utils.getApp() : topActivity;
    }

    public static boolean isActivityAlive(Activity activity) {
        return (activity == null || activity.isFinishing() || (Build.VERSION.SDK_INT >= 17 && activity.isDestroyed())) ? false : true;
    }

    public static boolean isActivityAlive(Context context) {
        return isActivityAlive(getActivityByContext(context));
    }

    public static boolean isActivityExists(@NonNull String str, @NonNull String str2) {
        Intent intent = new Intent();
        intent.setClassName(str, str2);
        PackageManager packageManager = Utils.getApp().getPackageManager();
        return (packageManager.resolveActivity(intent, 0) == null || intent.resolveActivity(packageManager) == null || packageManager.queryIntentActivities(intent, 0).size() == 0) ? false : true;
    }

    public static boolean isActivityExistsInStack(@NonNull Activity activity) {
        Iterator<Activity> it2 = UtilsBridge.getActivityList().iterator();
        while (it2.hasNext()) {
            if (it2.next().equals(activity)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isActivityExistsInStack(@NonNull Class<? extends Activity> cls) {
        Iterator<Activity> it2 = UtilsBridge.getActivityList().iterator();
        while (it2.hasNext()) {
            if (it2.next().getClass().equals(cls)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isIntentAvailable(Intent intent) {
        return Utils.getApp().getPackageManager().queryIntentActivities(intent, 65536).size() > 0;
    }

    public static void removeActivityLifecycleCallbacks(@Nullable Activity activity) {
        UtilsBridge.removeActivityLifecycleCallbacks(activity);
    }

    public static void removeActivityLifecycleCallbacks(@Nullable Activity activity, @Nullable Utils.ActivityLifecycleCallbacks activityLifecycleCallbacks) {
        UtilsBridge.removeActivityLifecycleCallbacks(activity, activityLifecycleCallbacks);
    }

    public static void removeActivityLifecycleCallbacks(@Nullable Utils.ActivityLifecycleCallbacks activityLifecycleCallbacks) {
        UtilsBridge.removeActivityLifecycleCallbacks(activityLifecycleCallbacks);
    }

    public static void startActivities(@NonNull Activity activity, @NonNull Intent[] intentArr) {
        startActivities(intentArr, activity, (Bundle) null);
    }

    public static void startActivities(@NonNull Activity activity, @NonNull Intent[] intentArr, @AnimRes int i, @AnimRes int i2) {
        startActivities(intentArr, activity, getOptionsBundle(activity, i, i2));
        if (Build.VERSION.SDK_INT < 16) {
            activity.overridePendingTransition(i, i2);
        }
    }

    public static void startActivities(@NonNull Activity activity, @NonNull Intent[] intentArr, @Nullable Bundle bundle) {
        startActivities(intentArr, activity, bundle);
    }

    public static void startActivities(@NonNull Intent[] intentArr) {
        startActivities(intentArr, getTopActivityOrApp(), (Bundle) null);
    }

    public static void startActivities(@NonNull Intent[] intentArr, @AnimRes int i, @AnimRes int i2) {
        Context topActivityOrApp = getTopActivityOrApp();
        startActivities(intentArr, topActivityOrApp, getOptionsBundle(topActivityOrApp, i, i2));
        if (Build.VERSION.SDK_INT >= 16 || !(topActivityOrApp instanceof Activity)) {
            return;
        }
        ((Activity) topActivityOrApp).overridePendingTransition(i, i2);
    }

    private static void startActivities(Intent[] intentArr, Context context, @Nullable Bundle bundle) {
        if (!(context instanceof Activity)) {
            for (Intent intent : intentArr) {
                intent.addFlags(ClientDefaults.MAX_MSG_SIZE);
            }
        }
        if (bundle == null || Build.VERSION.SDK_INT < 16) {
            context.startActivities(intentArr);
        } else {
            context.startActivities(intentArr, bundle);
        }
    }

    public static void startActivities(@NonNull Intent[] intentArr, @Nullable Bundle bundle) {
        startActivities(intentArr, getTopActivityOrApp(), bundle);
    }

    public static void startActivity(@NonNull Activity activity, @NonNull Intent intent) {
        startActivity(intent, activity, (Bundle) null);
    }

    public static void startActivity(@NonNull Activity activity, @NonNull Intent intent, @AnimRes int i, @AnimRes int i2) {
        startActivity(intent, activity, getOptionsBundle(activity, i, i2));
        if (Build.VERSION.SDK_INT < 16) {
            activity.overridePendingTransition(i, i2);
        }
    }

    public static void startActivity(@NonNull Activity activity, @NonNull Intent intent, @Nullable Bundle bundle) {
        startActivity(intent, activity, bundle);
    }

    public static void startActivity(@NonNull Activity activity, @NonNull Intent intent, View... viewArr) {
        startActivity(intent, activity, getOptionsBundle(activity, viewArr));
    }

    public static void startActivity(@NonNull Activity activity, @NonNull Class<? extends Activity> cls) {
        startActivity(activity, (Bundle) null, activity.getPackageName(), cls.getName(), (Bundle) null);
    }

    public static void startActivity(@NonNull Activity activity, @NonNull Class<? extends Activity> cls, @AnimRes int i, @AnimRes int i2) {
        startActivity(activity, (Bundle) null, activity.getPackageName(), cls.getName(), getOptionsBundle(activity, i, i2));
        if (Build.VERSION.SDK_INT < 16) {
            activity.overridePendingTransition(i, i2);
        }
    }

    public static void startActivity(@NonNull Activity activity, @NonNull Class<? extends Activity> cls, @Nullable Bundle bundle) {
        startActivity(activity, (Bundle) null, activity.getPackageName(), cls.getName(), bundle);
    }

    public static void startActivity(@NonNull Activity activity, @NonNull Class<? extends Activity> cls, View... viewArr) {
        startActivity(activity, (Bundle) null, activity.getPackageName(), cls.getName(), getOptionsBundle(activity, viewArr));
    }

    public static void startActivity(@NonNull Activity activity, @NonNull String str, @NonNull String str2) {
        startActivity(activity, (Bundle) null, str, str2, (Bundle) null);
    }

    public static void startActivity(@NonNull Activity activity, @NonNull String str, @NonNull String str2, @AnimRes int i, @AnimRes int i2) {
        startActivity(activity, (Bundle) null, str, str2, getOptionsBundle(activity, i, i2));
        if (Build.VERSION.SDK_INT < 16) {
            activity.overridePendingTransition(i, i2);
        }
    }

    public static void startActivity(@NonNull Activity activity, @NonNull String str, @NonNull String str2, @Nullable Bundle bundle) {
        startActivity(activity, (Bundle) null, str, str2, bundle);
    }

    public static void startActivity(@NonNull Activity activity, @NonNull String str, @NonNull String str2, View... viewArr) {
        startActivity(activity, (Bundle) null, str, str2, getOptionsBundle(activity, viewArr));
    }

    private static void startActivity(Context context, Bundle bundle, String str, String str2, @Nullable Bundle bundle2) {
        Intent intent = new Intent();
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        intent.setComponent(new ComponentName(str, str2));
        startActivity(intent, context, bundle2);
    }

    public static void startActivity(@NonNull Bundle bundle, @NonNull Activity activity, @NonNull Class<? extends Activity> cls) {
        startActivity(activity, bundle, activity.getPackageName(), cls.getName(), (Bundle) null);
    }

    public static void startActivity(@NonNull Bundle bundle, @NonNull Activity activity, @NonNull Class<? extends Activity> cls, @AnimRes int i, @AnimRes int i2) {
        startActivity(activity, bundle, activity.getPackageName(), cls.getName(), getOptionsBundle(activity, i, i2));
        if (Build.VERSION.SDK_INT < 16) {
            activity.overridePendingTransition(i, i2);
        }
    }

    public static void startActivity(@NonNull Bundle bundle, @NonNull Activity activity, @NonNull Class<? extends Activity> cls, @Nullable Bundle bundle2) {
        startActivity(activity, bundle, activity.getPackageName(), cls.getName(), bundle2);
    }

    public static void startActivity(@NonNull Bundle bundle, @NonNull Activity activity, @NonNull Class<? extends Activity> cls, View... viewArr) {
        startActivity(activity, bundle, activity.getPackageName(), cls.getName(), getOptionsBundle(activity, viewArr));
    }

    public static void startActivity(@NonNull Bundle bundle, @NonNull Activity activity, @NonNull String str, @NonNull String str2) {
        startActivity(activity, bundle, str, str2, (Bundle) null);
    }

    public static void startActivity(@NonNull Bundle bundle, @NonNull Activity activity, @NonNull String str, @NonNull String str2, @AnimRes int i, @AnimRes int i2) {
        startActivity(activity, bundle, str, str2, getOptionsBundle(activity, i, i2));
        if (Build.VERSION.SDK_INT < 16) {
            activity.overridePendingTransition(i, i2);
        }
    }

    public static void startActivity(@NonNull Bundle bundle, @NonNull Activity activity, @NonNull String str, @NonNull String str2, @Nullable Bundle bundle2) {
        startActivity(activity, bundle, str, str2, bundle2);
    }

    public static void startActivity(@NonNull Bundle bundle, @NonNull Activity activity, @NonNull String str, @NonNull String str2, View... viewArr) {
        startActivity(activity, bundle, str, str2, getOptionsBundle(activity, viewArr));
    }

    public static void startActivity(@NonNull Bundle bundle, @NonNull Class<? extends Activity> cls) {
        Context topActivityOrApp = getTopActivityOrApp();
        startActivity(topActivityOrApp, bundle, topActivityOrApp.getPackageName(), cls.getName(), (Bundle) null);
    }

    public static void startActivity(@NonNull Bundle bundle, @NonNull Class<? extends Activity> cls, @AnimRes int i, @AnimRes int i2) {
        Context topActivityOrApp = getTopActivityOrApp();
        startActivity(topActivityOrApp, bundle, topActivityOrApp.getPackageName(), cls.getName(), getOptionsBundle(topActivityOrApp, i, i2));
        if (Build.VERSION.SDK_INT >= 16 || !(topActivityOrApp instanceof Activity)) {
            return;
        }
        ((Activity) topActivityOrApp).overridePendingTransition(i, i2);
    }

    public static void startActivity(@NonNull Bundle bundle, @NonNull Class<? extends Activity> cls, @Nullable Bundle bundle2) {
        Context topActivityOrApp = getTopActivityOrApp();
        startActivity(topActivityOrApp, bundle, topActivityOrApp.getPackageName(), cls.getName(), bundle2);
    }

    public static void startActivity(@NonNull Bundle bundle, @NonNull String str, @NonNull String str2) {
        startActivity(getTopActivityOrApp(), bundle, str, str2, (Bundle) null);
    }

    public static void startActivity(@NonNull Bundle bundle, @NonNull String str, @NonNull String str2, @AnimRes int i, @AnimRes int i2) {
        Context topActivityOrApp = getTopActivityOrApp();
        startActivity(topActivityOrApp, bundle, str, str2, getOptionsBundle(topActivityOrApp, i, i2));
        if (Build.VERSION.SDK_INT >= 16 || !(topActivityOrApp instanceof Activity)) {
            return;
        }
        ((Activity) topActivityOrApp).overridePendingTransition(i, i2);
    }

    public static void startActivity(@NonNull Bundle bundle, @NonNull String str, @NonNull String str2, @Nullable Bundle bundle2) {
        startActivity(getTopActivityOrApp(), bundle, str, str2, bundle2);
    }

    public static void startActivity(@NonNull Class<? extends Activity> cls) {
        Context topActivityOrApp = getTopActivityOrApp();
        startActivity(topActivityOrApp, (Bundle) null, topActivityOrApp.getPackageName(), cls.getName(), (Bundle) null);
    }

    public static void startActivity(@NonNull Class<? extends Activity> cls, @AnimRes int i, @AnimRes int i2) {
        Context topActivityOrApp = getTopActivityOrApp();
        startActivity(topActivityOrApp, (Bundle) null, topActivityOrApp.getPackageName(), cls.getName(), getOptionsBundle(topActivityOrApp, i, i2));
        if (Build.VERSION.SDK_INT >= 16 || !(topActivityOrApp instanceof Activity)) {
            return;
        }
        ((Activity) topActivityOrApp).overridePendingTransition(i, i2);
    }

    public static void startActivity(@NonNull Class<? extends Activity> cls, @Nullable Bundle bundle) {
        Context topActivityOrApp = getTopActivityOrApp();
        startActivity(topActivityOrApp, (Bundle) null, topActivityOrApp.getPackageName(), cls.getName(), bundle);
    }

    public static void startActivity(@NonNull String str, @NonNull String str2) {
        startActivity(getTopActivityOrApp(), (Bundle) null, str, str2, (Bundle) null);
    }

    public static void startActivity(@NonNull String str, @NonNull String str2, @AnimRes int i, @AnimRes int i2) {
        Context topActivityOrApp = getTopActivityOrApp();
        startActivity(topActivityOrApp, (Bundle) null, str, str2, getOptionsBundle(topActivityOrApp, i, i2));
        if (Build.VERSION.SDK_INT >= 16 || !(topActivityOrApp instanceof Activity)) {
            return;
        }
        ((Activity) topActivityOrApp).overridePendingTransition(i, i2);
    }

    public static void startActivity(@NonNull String str, @NonNull String str2, @Nullable Bundle bundle) {
        startActivity(getTopActivityOrApp(), (Bundle) null, str, str2, bundle);
    }

    public static boolean startActivity(@NonNull Intent intent) {
        return startActivity(intent, getTopActivityOrApp(), (Bundle) null);
    }

    public static boolean startActivity(@NonNull Intent intent, @AnimRes int i, @AnimRes int i2) {
        Context topActivityOrApp = getTopActivityOrApp();
        boolean startActivity = startActivity(intent, topActivityOrApp, getOptionsBundle(topActivityOrApp, i, i2));
        if (startActivity && Build.VERSION.SDK_INT < 16 && (topActivityOrApp instanceof Activity)) {
            ((Activity) topActivityOrApp).overridePendingTransition(i, i2);
        }
        return startActivity;
    }

    private static boolean startActivity(Intent intent, Context context, Bundle bundle) {
        if (!isIntentAvailable(intent)) {
            Log.e("ActivityUtils", "intent is unavailable");
            return false;
        }
        if (!(context instanceof Activity)) {
            intent.addFlags(ClientDefaults.MAX_MSG_SIZE);
        }
        if (bundle == null || Build.VERSION.SDK_INT < 16) {
            context.startActivity(intent);
            return true;
        }
        context.startActivity(intent, bundle);
        return true;
    }

    public static boolean startActivity(@NonNull Intent intent, @Nullable Bundle bundle) {
        return startActivity(intent, getTopActivityOrApp(), bundle);
    }

    public static void startActivityForResult(@NonNull Activity activity, @NonNull Intent intent, int i) {
        startActivityForResult(intent, activity, i, (Bundle) null);
    }

    public static void startActivityForResult(@NonNull Activity activity, @NonNull Intent intent, int i, @AnimRes int i2, @AnimRes int i3) {
        startActivityForResult(intent, activity, i, getOptionsBundle(activity, i2, i3));
        if (Build.VERSION.SDK_INT < 16) {
            activity.overridePendingTransition(i2, i3);
        }
    }

    public static void startActivityForResult(@NonNull Activity activity, @NonNull Intent intent, int i, @Nullable Bundle bundle) {
        startActivityForResult(intent, activity, i, bundle);
    }

    public static void startActivityForResult(@NonNull Activity activity, @NonNull Intent intent, int i, View... viewArr) {
        startActivityForResult(intent, activity, i, getOptionsBundle(activity, viewArr));
    }

    public static void startActivityForResult(@NonNull Activity activity, @NonNull Class<? extends Activity> cls, int i) {
        startActivityForResult(activity, (Bundle) null, activity.getPackageName(), cls.getName(), i, (Bundle) null);
    }

    public static void startActivityForResult(@NonNull Activity activity, @NonNull Class<? extends Activity> cls, int i, @AnimRes int i2, @AnimRes int i3) {
        startActivityForResult(activity, (Bundle) null, activity.getPackageName(), cls.getName(), i, getOptionsBundle(activity, i2, i3));
        if (Build.VERSION.SDK_INT < 16) {
            activity.overridePendingTransition(i2, i3);
        }
    }

    public static void startActivityForResult(@NonNull Activity activity, @NonNull Class<? extends Activity> cls, int i, @Nullable Bundle bundle) {
        startActivityForResult(activity, (Bundle) null, activity.getPackageName(), cls.getName(), i, bundle);
    }

    public static void startActivityForResult(@NonNull Activity activity, @NonNull Class<? extends Activity> cls, int i, View... viewArr) {
        startActivityForResult(activity, (Bundle) null, activity.getPackageName(), cls.getName(), i, getOptionsBundle(activity, viewArr));
    }

    public static void startActivityForResult(@NonNull Bundle bundle, @NonNull Activity activity, @NonNull Class<? extends Activity> cls, int i) {
        startActivityForResult(activity, bundle, activity.getPackageName(), cls.getName(), i, (Bundle) null);
    }

    public static void startActivityForResult(@NonNull Bundle bundle, @NonNull Activity activity, @NonNull Class<? extends Activity> cls, int i, @AnimRes int i2, @AnimRes int i3) {
        startActivityForResult(activity, bundle, activity.getPackageName(), cls.getName(), i, getOptionsBundle(activity, i2, i3));
        if (Build.VERSION.SDK_INT < 16) {
            activity.overridePendingTransition(i2, i3);
        }
    }

    public static void startActivityForResult(@NonNull Bundle bundle, @NonNull Activity activity, @NonNull Class<? extends Activity> cls, int i, @Nullable Bundle bundle2) {
        startActivityForResult(activity, bundle, activity.getPackageName(), cls.getName(), i, bundle2);
    }

    public static void startActivityForResult(@NonNull Bundle bundle, @NonNull Activity activity, @NonNull Class<? extends Activity> cls, int i, View... viewArr) {
        startActivityForResult(activity, bundle, activity.getPackageName(), cls.getName(), i, getOptionsBundle(activity, viewArr));
    }

    public static void startActivityForResult(@NonNull Bundle bundle, @NonNull Activity activity, @NonNull String str, @NonNull String str2, int i) {
        startActivityForResult(activity, bundle, str, str2, i, (Bundle) null);
    }

    public static void startActivityForResult(@NonNull Bundle bundle, @NonNull Activity activity, @NonNull String str, @NonNull String str2, int i, @AnimRes int i2, @AnimRes int i3) {
        startActivityForResult(activity, bundle, str, str2, i, getOptionsBundle(activity, i2, i3));
        if (Build.VERSION.SDK_INT < 16) {
            activity.overridePendingTransition(i2, i3);
        }
    }

    public static void startActivityForResult(@NonNull Bundle bundle, @NonNull Activity activity, @NonNull String str, @NonNull String str2, int i, @Nullable Bundle bundle2) {
        startActivityForResult(activity, bundle, str, str2, i, bundle2);
    }

    public static void startActivityForResult(@NonNull Bundle bundle, @NonNull Activity activity, @NonNull String str, @NonNull String str2, int i, View... viewArr) {
        startActivityForResult(activity, bundle, str, str2, i, getOptionsBundle(activity, viewArr));
    }

    public static void startActivityForResult(@NonNull Bundle bundle, @NonNull Fragment fragment, @NonNull Class<? extends Activity> cls, int i) {
        startActivityForResult(fragment, bundle, Utils.getApp().getPackageName(), cls.getName(), i, (Bundle) null);
    }

    public static void startActivityForResult(@NonNull Bundle bundle, @NonNull Fragment fragment, @NonNull Class<? extends Activity> cls, int i, @AnimRes int i2, @AnimRes int i3) {
        startActivityForResult(fragment, bundle, Utils.getApp().getPackageName(), cls.getName(), i, getOptionsBundle(fragment, i2, i3));
    }

    public static void startActivityForResult(@NonNull Bundle bundle, @NonNull Fragment fragment, @NonNull Class<? extends Activity> cls, int i, @Nullable Bundle bundle2) {
        startActivityForResult(fragment, bundle, Utils.getApp().getPackageName(), cls.getName(), i, bundle2);
    }

    public static void startActivityForResult(@NonNull Bundle bundle, @NonNull Fragment fragment, @NonNull Class<? extends Activity> cls, int i, View... viewArr) {
        startActivityForResult(fragment, bundle, Utils.getApp().getPackageName(), cls.getName(), i, getOptionsBundle(fragment, viewArr));
    }

    public static void startActivityForResult(@NonNull Bundle bundle, @NonNull Fragment fragment, @NonNull String str, @NonNull String str2, int i) {
        startActivityForResult(fragment, bundle, str, str2, i, (Bundle) null);
    }

    public static void startActivityForResult(@NonNull Bundle bundle, @NonNull Fragment fragment, @NonNull String str, @NonNull String str2, int i, @AnimRes int i2, @AnimRes int i3) {
        startActivityForResult(fragment, bundle, str, str2, i, getOptionsBundle(fragment, i2, i3));
    }

    public static void startActivityForResult(@NonNull Bundle bundle, @NonNull Fragment fragment, @NonNull String str, @NonNull String str2, int i, @Nullable Bundle bundle2) {
        startActivityForResult(fragment, bundle, str, str2, i, bundle2);
    }

    public static void startActivityForResult(@NonNull Bundle bundle, @NonNull Fragment fragment, @NonNull String str, @NonNull String str2, int i, View... viewArr) {
        startActivityForResult(fragment, bundle, str, str2, i, getOptionsBundle(fragment, viewArr));
    }

    public static void startActivityForResult(@NonNull Fragment fragment, @NonNull Intent intent, int i) {
        startActivityForResult(intent, fragment, i, (Bundle) null);
    }

    public static void startActivityForResult(@NonNull Fragment fragment, @NonNull Intent intent, int i, @AnimRes int i2, @AnimRes int i3) {
        startActivityForResult(intent, fragment, i, getOptionsBundle(fragment, i2, i3));
    }

    public static void startActivityForResult(@NonNull Fragment fragment, @NonNull Intent intent, int i, @Nullable Bundle bundle) {
        startActivityForResult(intent, fragment, i, bundle);
    }

    public static void startActivityForResult(@NonNull Fragment fragment, @NonNull Intent intent, int i, View... viewArr) {
        startActivityForResult(intent, fragment, i, getOptionsBundle(fragment, viewArr));
    }

    public static void startActivityForResult(@NonNull Fragment fragment, @NonNull Class<? extends Activity> cls, int i) {
        startActivityForResult(fragment, (Bundle) null, Utils.getApp().getPackageName(), cls.getName(), i, (Bundle) null);
    }

    public static void startActivityForResult(@NonNull Fragment fragment, @NonNull Class<? extends Activity> cls, int i, @AnimRes int i2, @AnimRes int i3) {
        startActivityForResult(fragment, (Bundle) null, Utils.getApp().getPackageName(), cls.getName(), i, getOptionsBundle(fragment, i2, i3));
    }

    public static void startActivityForResult(@NonNull Fragment fragment, @NonNull Class<? extends Activity> cls, int i, @Nullable Bundle bundle) {
        startActivityForResult(fragment, (Bundle) null, Utils.getApp().getPackageName(), cls.getName(), i, bundle);
    }

    public static void startActivityForResult(@NonNull Fragment fragment, @NonNull Class<? extends Activity> cls, int i, View... viewArr) {
        startActivityForResult(fragment, (Bundle) null, Utils.getApp().getPackageName(), cls.getName(), i, getOptionsBundle(fragment, viewArr));
    }

    private static boolean startActivityForResult(Activity activity, Bundle bundle, String str, String str2, int i, @Nullable Bundle bundle2) {
        Intent intent = new Intent();
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        intent.setComponent(new ComponentName(str, str2));
        return startActivityForResult(intent, activity, i, bundle2);
    }

    private static boolean startActivityForResult(Intent intent, Activity activity, int i, @Nullable Bundle bundle) {
        if (!isIntentAvailable(intent)) {
            Log.e("ActivityUtils", "intent is unavailable");
            return false;
        }
        if (bundle == null || Build.VERSION.SDK_INT < 16) {
            activity.startActivityForResult(intent, i);
            return true;
        }
        activity.startActivityForResult(intent, i, bundle);
        return true;
    }

    private static boolean startActivityForResult(Intent intent, Fragment fragment, int i, @Nullable Bundle bundle) {
        if (!isIntentAvailable(intent)) {
            Log.e("ActivityUtils", "intent is unavailable");
            return false;
        }
        if (fragment.getActivity() == null) {
            Log.e("ActivityUtils", "Fragment " + fragment + " not attached to Activity");
            return false;
        }
        if (bundle == null || Build.VERSION.SDK_INT < 16) {
            fragment.startActivityForResult(intent, i);
            return true;
        }
        fragment.startActivityForResult(intent, i, bundle);
        return true;
    }

    private static boolean startActivityForResult(Fragment fragment, Bundle bundle, String str, String str2, int i, @Nullable Bundle bundle2) {
        Intent intent = new Intent();
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        intent.setComponent(new ComponentName(str, str2));
        return startActivityForResult(intent, fragment, i, bundle2);
    }

    public static void startHomeActivity() {
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.HOME");
        intent.setFlags(ClientDefaults.MAX_MSG_SIZE);
        startActivity(intent);
    }

    public static void startLauncherActivity() {
        startLauncherActivity(Utils.getApp().getPackageName());
    }

    public static void startLauncherActivity(@NonNull String str) {
        String launcherActivity = getLauncherActivity(str);
        if (TextUtils.isEmpty(launcherActivity)) {
            return;
        }
        startActivity(str, launcherActivity);
    }
}
