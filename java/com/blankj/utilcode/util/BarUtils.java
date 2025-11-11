package com.blankj.utilcode.util;

import android.R;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.os.Build;
import android.provider.Settings;
import android.util.TypedValue;
import android.view.Display;
import android.view.KeyCharacterMap;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.annotation.RequiresPermission;
import androidx.drawerlayout.widget.DrawerLayout;

/* loaded from: classes.dex */
public final class BarUtils {
    private static final int KEY_OFFSET = -123;
    private static final String TAG_OFFSET = "TAG_OFFSET";
    private static final String TAG_STATUS_BAR = "TAG_STATUS_BAR";

    private BarUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static void addMarginTopEqualStatusBarHeight(@NonNull View view) {
        if (Build.VERSION.SDK_INT < 19) {
            return;
        }
        view.setTag(TAG_OFFSET);
        Object tag = view.getTag(KEY_OFFSET);
        if (tag == null || !((Boolean) tag).booleanValue()) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            marginLayoutParams.setMargins(marginLayoutParams.leftMargin, marginLayoutParams.topMargin + getStatusBarHeight(), marginLayoutParams.rightMargin, marginLayoutParams.bottomMargin);
            view.setTag(KEY_OFFSET, true);
        }
    }

    private static void addMarginTopEqualStatusBarHeight(@NonNull Window window) {
        View findViewWithTag;
        if (Build.VERSION.SDK_INT >= 19 && (findViewWithTag = window.getDecorView().findViewWithTag(TAG_OFFSET)) != null) {
            addMarginTopEqualStatusBarHeight(findViewWithTag);
        }
    }

    private static View applyStatusBarColor(@NonNull Activity activity, int i, boolean z) {
        return applyStatusBarColor(activity.getWindow(), i, z);
    }

    private static View applyStatusBarColor(@NonNull Window window, int i, boolean z) {
        ViewGroup viewGroup = z ? (ViewGroup) window.getDecorView() : (ViewGroup) window.findViewById(R.id.content);
        View findViewWithTag = viewGroup.findViewWithTag(TAG_STATUS_BAR);
        if (findViewWithTag == null) {
            View createStatusBarView = createStatusBarView(window.getContext(), i);
            viewGroup.addView(createStatusBarView);
            return createStatusBarView;
        }
        if (findViewWithTag.getVisibility() == 8) {
            findViewWithTag.setVisibility(0);
        }
        findViewWithTag.setBackgroundColor(i);
        return findViewWithTag;
    }

    private static View createStatusBarView(@NonNull Context context, int i) {
        View view = new View(context);
        view.setLayoutParams(new ViewGroup.LayoutParams(-1, getStatusBarHeight()));
        view.setBackgroundColor(i);
        view.setTag(TAG_STATUS_BAR);
        return view;
    }

    public static int getActionBarHeight() {
        TypedValue typedValue = new TypedValue();
        if (Utils.getApp().getTheme().resolveAttribute(R.attr.actionBarSize, typedValue, true)) {
            return TypedValue.complexToDimensionPixelSize(typedValue.data, Utils.getApp().getResources().getDisplayMetrics());
        }
        return 0;
    }

    @RequiresApi(21)
    public static int getNavBarColor(@NonNull Activity activity) {
        return getNavBarColor(activity.getWindow());
    }

    @RequiresApi(21)
    public static int getNavBarColor(@NonNull Window window) {
        return window.getNavigationBarColor();
    }

    public static int getNavBarHeight() {
        Resources resources = Utils.getApp().getResources();
        int identifier = resources.getIdentifier("navigation_bar_height", "dimen", "android");
        if (identifier != 0) {
            return resources.getDimensionPixelSize(identifier);
        }
        return 0;
    }

    private static String getResNameById(int i) {
        try {
            return Utils.getApp().getResources().getResourceEntryName(i);
        } catch (Exception unused) {
            return "";
        }
    }

    public static int getStatusBarHeight() {
        Resources resources = Utils.getApp().getResources();
        return resources.getDimensionPixelSize(resources.getIdentifier("status_bar_height", "dimen", "android"));
    }

    private static void hideStatusBarView(@NonNull Activity activity) {
        hideStatusBarView(activity.getWindow());
    }

    private static void hideStatusBarView(@NonNull Window window) {
        View findViewWithTag = ((ViewGroup) window.getDecorView()).findViewWithTag(TAG_STATUS_BAR);
        if (findViewWithTag == null) {
            return;
        }
        findViewWithTag.setVisibility(8);
    }

    private static void invokePanels(String str) {
        try {
            Class.forName("android.app.StatusBarManager").getMethod(str, new Class[0]).invoke(Utils.getApp().getSystemService("statusbar"), new Object[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean isNavBarLightMode(@NonNull Activity activity) {
        return isNavBarLightMode(activity.getWindow());
    }

    public static boolean isNavBarLightMode(@NonNull Window window) {
        return Build.VERSION.SDK_INT >= 26 && (window.getDecorView().getSystemUiVisibility() & 16) != 0;
    }

    public static boolean isNavBarVisible(@NonNull Activity activity) {
        return isNavBarVisible(activity.getWindow());
    }

    public static boolean isNavBarVisible(@NonNull Window window) {
        boolean z;
        ViewGroup viewGroup = (ViewGroup) window.getDecorView();
        int childCount = viewGroup.getChildCount();
        int i = 0;
        while (true) {
            if (i >= childCount) {
                z = false;
                break;
            }
            View childAt = viewGroup.getChildAt(i);
            int id = childAt.getId();
            if (id != -1 && "navigationBarBackground".equals(getResNameById(id)) && childAt.getVisibility() == 0) {
                z = true;
                break;
            }
            i++;
        }
        if (!z) {
            return z;
        }
        if (UtilsBridge.isSamsung() && Build.VERSION.SDK_INT >= 17 && Build.VERSION.SDK_INT < 29) {
            try {
                return Settings.Global.getInt(Utils.getApp().getContentResolver(), "navigationbar_hide_bar_enabled") == 0;
            } catch (Exception unused) {
            }
        }
        return (viewGroup.getSystemUiVisibility() & 2) == 0;
    }

    public static boolean isStatusBarLightMode(@NonNull Activity activity) {
        return isStatusBarLightMode(activity.getWindow());
    }

    public static boolean isStatusBarLightMode(@NonNull Window window) {
        return Build.VERSION.SDK_INT >= 23 && (window.getDecorView().getSystemUiVisibility() & 8192) != 0;
    }

    public static boolean isStatusBarVisible(@NonNull Activity activity) {
        return (activity.getWindow().getAttributes().flags & 1024) == 0;
    }

    public static boolean isSupportNavBar() {
        if (Build.VERSION.SDK_INT < 17) {
            return (ViewConfiguration.get(Utils.getApp()).hasPermanentMenuKey() || KeyCharacterMap.deviceHasKey(4)) ? false : true;
        }
        WindowManager windowManager = (WindowManager) Utils.getApp().getSystemService("window");
        if (windowManager == null) {
            return false;
        }
        Display defaultDisplay = windowManager.getDefaultDisplay();
        Point point = new Point();
        Point point2 = new Point();
        defaultDisplay.getSize(point);
        defaultDisplay.getRealSize(point2);
        return (point2.y == point.y && point2.x == point.x) ? false : true;
    }

    @RequiresApi(21)
    public static void setNavBarColor(@NonNull Activity activity, @ColorInt int i) {
        setNavBarColor(activity.getWindow(), i);
    }

    @RequiresApi(21)
    public static void setNavBarColor(@NonNull Window window, @ColorInt int i) {
        window.addFlags(Integer.MIN_VALUE);
        window.setNavigationBarColor(i);
    }

    public static void setNavBarLightMode(@NonNull Activity activity, boolean z) {
        setNavBarLightMode(activity.getWindow(), z);
    }

    public static void setNavBarLightMode(@NonNull Window window, boolean z) {
        if (Build.VERSION.SDK_INT >= 26) {
            View decorView = window.getDecorView();
            int systemUiVisibility = decorView.getSystemUiVisibility();
            decorView.setSystemUiVisibility(z ? systemUiVisibility | 16 : systemUiVisibility & (-17));
        }
    }

    public static void setNavBarVisibility(@NonNull Activity activity, boolean z) {
        if (Build.VERSION.SDK_INT < 19) {
            return;
        }
        setNavBarVisibility(activity.getWindow(), z);
    }

    public static void setNavBarVisibility(@NonNull Window window, boolean z) {
        if (Build.VERSION.SDK_INT < 19) {
            return;
        }
        ViewGroup viewGroup = (ViewGroup) window.getDecorView();
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = viewGroup.getChildAt(i);
            int id = childAt.getId();
            if (id != -1 && "navigationBarBackground".equals(getResNameById(id))) {
                childAt.setVisibility(z ? 0 : 4);
            }
        }
        if (z) {
            viewGroup.setSystemUiVisibility(viewGroup.getSystemUiVisibility() & (-4611));
        } else {
            viewGroup.setSystemUiVisibility(viewGroup.getSystemUiVisibility() | 4610);
        }
    }

    @RequiresPermission("android.permission.EXPAND_STATUS_BAR")
    public static void setNotificationBarVisibility(boolean z) {
        invokePanels(z ? Build.VERSION.SDK_INT <= 16 ? "expand" : "expandNotificationsPanel" : Build.VERSION.SDK_INT <= 16 ? "collapse" : "collapsePanels");
    }

    public static View setStatusBarColor(@NonNull Activity activity, @ColorInt int i) {
        return setStatusBarColor(activity, i, false);
    }

    public static View setStatusBarColor(@NonNull Activity activity, @ColorInt int i, boolean z) {
        if (Build.VERSION.SDK_INT < 19) {
            return null;
        }
        transparentStatusBar(activity);
        return applyStatusBarColor(activity, i, z);
    }

    public static View setStatusBarColor(@NonNull Window window, @ColorInt int i) {
        return setStatusBarColor(window, i, false);
    }

    public static View setStatusBarColor(@NonNull Window window, @ColorInt int i, boolean z) {
        if (Build.VERSION.SDK_INT < 19) {
            return null;
        }
        transparentStatusBar(window);
        return applyStatusBarColor(window, i, z);
    }

    public static void setStatusBarColor(@NonNull View view, @ColorInt int i) {
        Activity activityByContext;
        if (Build.VERSION.SDK_INT >= 19 && (activityByContext = UtilsBridge.getActivityByContext(view.getContext())) != null) {
            transparentStatusBar(activityByContext);
            view.setVisibility(0);
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            layoutParams.width = -1;
            layoutParams.height = getStatusBarHeight();
            view.setBackgroundColor(i);
        }
    }

    public static void setStatusBarColor4Drawer(@NonNull DrawerLayout drawerLayout, @NonNull View view, @ColorInt int i) {
        setStatusBarColor4Drawer(drawerLayout, view, i, false);
    }

    public static void setStatusBarColor4Drawer(@NonNull DrawerLayout drawerLayout, @NonNull View view, @ColorInt int i, boolean z) {
        Activity activityByContext;
        if (Build.VERSION.SDK_INT >= 19 && (activityByContext = UtilsBridge.getActivityByContext(view.getContext())) != null) {
            transparentStatusBar(activityByContext);
            drawerLayout.setFitsSystemWindows(false);
            setStatusBarColor(view, i);
            int childCount = drawerLayout.getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                drawerLayout.getChildAt(i2).setFitsSystemWindows(false);
            }
            if (z) {
                hideStatusBarView(activityByContext);
            } else {
                setStatusBarColor(activityByContext, i, false);
            }
        }
    }

    public static void setStatusBarCustom(@NonNull View view) {
        Activity activityByContext;
        if (Build.VERSION.SDK_INT >= 19 && (activityByContext = UtilsBridge.getActivityByContext(view.getContext())) != null) {
            transparentStatusBar(activityByContext);
            view.setVisibility(0);
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            if (layoutParams == null) {
                view.setLayoutParams(new ViewGroup.LayoutParams(-1, getStatusBarHeight()));
            } else {
                layoutParams.width = -1;
                layoutParams.height = getStatusBarHeight();
            }
        }
    }

    public static void setStatusBarLightMode(@NonNull Activity activity, boolean z) {
        setStatusBarLightMode(activity.getWindow(), z);
    }

    public static void setStatusBarLightMode(@NonNull Window window, boolean z) {
        if (Build.VERSION.SDK_INT >= 23) {
            View decorView = window.getDecorView();
            int systemUiVisibility = decorView.getSystemUiVisibility();
            decorView.setSystemUiVisibility(z ? systemUiVisibility | 8192 : systemUiVisibility & (-8193));
        }
    }

    public static void setStatusBarVisibility(@NonNull Activity activity, boolean z) {
        setStatusBarVisibility(activity.getWindow(), z);
    }

    public static void setStatusBarVisibility(@NonNull Window window, boolean z) {
        if (z) {
            window.clearFlags(1024);
            showStatusBarView(window);
            addMarginTopEqualStatusBarHeight(window);
        } else {
            window.addFlags(1024);
            hideStatusBarView(window);
            subtractMarginTopEqualStatusBarHeight(window);
        }
    }

    private static void showStatusBarView(@NonNull Window window) {
        View findViewWithTag = ((ViewGroup) window.getDecorView()).findViewWithTag(TAG_STATUS_BAR);
        if (findViewWithTag == null) {
            return;
        }
        findViewWithTag.setVisibility(0);
    }

    public static void subtractMarginTopEqualStatusBarHeight(@NonNull View view) {
        Object tag;
        if (Build.VERSION.SDK_INT >= 19 && (tag = view.getTag(KEY_OFFSET)) != null && ((Boolean) tag).booleanValue()) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            marginLayoutParams.setMargins(marginLayoutParams.leftMargin, marginLayoutParams.topMargin - getStatusBarHeight(), marginLayoutParams.rightMargin, marginLayoutParams.bottomMargin);
            view.setTag(KEY_OFFSET, false);
        }
    }

    private static void subtractMarginTopEqualStatusBarHeight(@NonNull Window window) {
        View findViewWithTag;
        if (Build.VERSION.SDK_INT >= 19 && (findViewWithTag = window.getDecorView().findViewWithTag(TAG_OFFSET)) != null) {
            subtractMarginTopEqualStatusBarHeight(findViewWithTag);
        }
    }

    public static void transparentStatusBar(@NonNull Activity activity) {
        transparentStatusBar(activity.getWindow());
    }

    public static void transparentStatusBar(@NonNull Window window) {
        if (Build.VERSION.SDK_INT < 19) {
            return;
        }
        if (Build.VERSION.SDK_INT < 21) {
            window.addFlags(67108864);
            return;
        }
        window.clearFlags(67108864);
        window.addFlags(Integer.MIN_VALUE);
        window.getDecorView().setSystemUiVisibility(window.getDecorView().getSystemUiVisibility() | 1280);
        window.setStatusBarColor(0);
    }
}
