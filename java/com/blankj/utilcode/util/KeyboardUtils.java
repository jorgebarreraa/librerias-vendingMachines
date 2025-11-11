package com.blankj.utilcode.util;

import android.R;
import android.app.Activity;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.lang.reflect.Field;

/* loaded from: classes.dex */
public final class KeyboardUtils {
    private static final int TAG_ON_GLOBAL_LAYOUT_LISTENER = -8;
    private static long millis;
    private static int sDecorViewDelta;

    /* loaded from: classes.dex */
    public interface OnSoftInputChangedListener {
        void onSoftInputChanged(int i);
    }

    private KeyboardUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static void clickBlankArea2HideSoftInput() {
        Log.i("KeyboardUtils", "Please refer to the following code.");
    }

    public static void fixAndroidBug5497(@NonNull Activity activity) {
        fixAndroidBug5497(activity.getWindow());
    }

    public static void fixAndroidBug5497(@NonNull final Window window) {
        window.setSoftInputMode(window.getAttributes().softInputMode & (-17));
        FrameLayout frameLayout = (FrameLayout) window.findViewById(R.id.content);
        final View childAt = frameLayout.getChildAt(0);
        final int paddingBottom = childAt.getPaddingBottom();
        final int[] iArr = {getContentViewInvisibleHeight(window)};
        frameLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.blankj.utilcode.util.KeyboardUtils.3
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                int contentViewInvisibleHeight = KeyboardUtils.getContentViewInvisibleHeight(window);
                if (iArr[0] != contentViewInvisibleHeight) {
                    View view = childAt;
                    view.setPadding(view.getPaddingLeft(), childAt.getPaddingTop(), childAt.getPaddingRight(), paddingBottom + KeyboardUtils.getDecorViewInvisibleHeight(window));
                    iArr[0] = contentViewInvisibleHeight;
                }
            }
        });
    }

    public static void fixSoftInputLeaks(@NonNull Activity activity) {
        fixSoftInputLeaks(activity.getWindow());
    }

    public static void fixSoftInputLeaks(@NonNull Window window) {
        InputMethodManager inputMethodManager = (InputMethodManager) Utils.getApp().getSystemService("input_method");
        if (inputMethodManager == null) {
            return;
        }
        for (String str : new String[]{"mLastSrvView", "mCurRootView", "mServedView", "mNextServedView"}) {
            try {
                Field declaredField = InputMethodManager.class.getDeclaredField(str);
                if (!declaredField.isAccessible()) {
                    declaredField.setAccessible(true);
                }
                Object obj = declaredField.get(inputMethodManager);
                if ((obj instanceof View) && ((View) obj).getRootView() == window.getDecorView().getRootView()) {
                    declaredField.set(inputMethodManager, null);
                }
            } catch (Throwable unused) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int getContentViewInvisibleHeight(Window window) {
        View findViewById = window.findViewById(R.id.content);
        if (findViewById == null) {
            return 0;
        }
        Rect rect = new Rect();
        findViewById.getWindowVisibleDisplayFrame(rect);
        Log.d("KeyboardUtils", "getContentViewInvisibleHeight: " + (findViewById.getBottom() - rect.bottom));
        int abs = Math.abs(findViewById.getBottom() - rect.bottom);
        if (abs <= UtilsBridge.getStatusBarHeight() + UtilsBridge.getNavBarHeight()) {
            return 0;
        }
        return abs;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int getDecorViewInvisibleHeight(@NonNull Window window) {
        View decorView = window.getDecorView();
        Rect rect = new Rect();
        decorView.getWindowVisibleDisplayFrame(rect);
        Log.d("KeyboardUtils", "getDecorViewInvisibleHeight: " + (decorView.getBottom() - rect.bottom));
        int abs = Math.abs(decorView.getBottom() - rect.bottom);
        if (abs > UtilsBridge.getNavBarHeight() + UtilsBridge.getStatusBarHeight()) {
            return abs - sDecorViewDelta;
        }
        sDecorViewDelta = abs;
        return 0;
    }

    public static void hideSoftInput(@Nullable Activity activity) {
        if (activity == null) {
            return;
        }
        hideSoftInput(activity.getWindow());
    }

    public static void hideSoftInput(@NonNull View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) Utils.getApp().getSystemService("input_method");
        if (inputMethodManager == null) {
            return;
        }
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static void hideSoftInput(@Nullable Window window) {
        if (window == null) {
            return;
        }
        View currentFocus = window.getCurrentFocus();
        if (currentFocus == null) {
            View decorView = window.getDecorView();
            View findViewWithTag = decorView.findViewWithTag("keyboardTagView");
            if (findViewWithTag == null) {
                findViewWithTag = new EditText(window.getContext());
                findViewWithTag.setTag("keyboardTagView");
                ((ViewGroup) decorView).addView(findViewWithTag, 0, 0);
            }
            currentFocus = findViewWithTag;
            currentFocus.requestFocus();
        }
        hideSoftInput(currentFocus);
    }

    public static void hideSoftInputByToggle(@Nullable Activity activity) {
        if (activity == null) {
            return;
        }
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (Math.abs(elapsedRealtime - millis) > 500 && isSoftInputVisible(activity)) {
            toggleSoftInput();
        }
        millis = elapsedRealtime;
    }

    public static boolean isSoftInputVisible(@NonNull Activity activity) {
        return getDecorViewInvisibleHeight(activity.getWindow()) > 0;
    }

    public static void registerSoftInputChangedListener(@NonNull Activity activity, @NonNull OnSoftInputChangedListener onSoftInputChangedListener) {
        registerSoftInputChangedListener(activity.getWindow(), onSoftInputChangedListener);
    }

    public static void registerSoftInputChangedListener(@NonNull final Window window, @NonNull final OnSoftInputChangedListener onSoftInputChangedListener) {
        if ((window.getAttributes().flags & 512) != 0) {
            window.clearFlags(512);
        }
        FrameLayout frameLayout = (FrameLayout) window.findViewById(R.id.content);
        final int[] iArr = {getDecorViewInvisibleHeight(window)};
        ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.blankj.utilcode.util.KeyboardUtils.2
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                int decorViewInvisibleHeight = KeyboardUtils.getDecorViewInvisibleHeight(window);
                if (iArr[0] != decorViewInvisibleHeight) {
                    onSoftInputChangedListener.onSoftInputChanged(decorViewInvisibleHeight);
                    iArr[0] = decorViewInvisibleHeight;
                }
            }
        };
        frameLayout.getViewTreeObserver().addOnGlobalLayoutListener(onGlobalLayoutListener);
        frameLayout.setTag(TAG_ON_GLOBAL_LAYOUT_LISTENER, onGlobalLayoutListener);
    }

    public static void showSoftInput() {
        InputMethodManager inputMethodManager = (InputMethodManager) Utils.getApp().getSystemService("input_method");
        if (inputMethodManager == null) {
            return;
        }
        inputMethodManager.toggleSoftInput(2, 1);
    }

    public static void showSoftInput(@Nullable Activity activity) {
        if (activity == null || isSoftInputVisible(activity)) {
            return;
        }
        toggleSoftInput();
    }

    public static void showSoftInput(@NonNull View view) {
        showSoftInput(view, 0);
    }

    public static void showSoftInput(@NonNull View view, int i) {
        InputMethodManager inputMethodManager = (InputMethodManager) Utils.getApp().getSystemService("input_method");
        if (inputMethodManager == null) {
            return;
        }
        view.setFocusable(true);
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        inputMethodManager.showSoftInput(view, i, new ResultReceiver(new Handler()) { // from class: com.blankj.utilcode.util.KeyboardUtils.1
            @Override // android.os.ResultReceiver
            protected void onReceiveResult(int i2, Bundle bundle) {
                if (i2 == 1 || i2 == 3) {
                    KeyboardUtils.toggleSoftInput();
                }
            }
        });
        inputMethodManager.toggleSoftInput(2, 1);
    }

    public static void toggleSoftInput() {
        InputMethodManager inputMethodManager = (InputMethodManager) Utils.getApp().getSystemService("input_method");
        if (inputMethodManager == null) {
            return;
        }
        inputMethodManager.toggleSoftInput(0, 0);
    }

    public static void unregisterSoftInputChangedListener(@NonNull Window window) {
        View findViewById = window.findViewById(R.id.content);
        if (findViewById == null) {
            return;
        }
        Object tag = findViewById.getTag(TAG_ON_GLOBAL_LAYOUT_LISTENER);
        if (!(tag instanceof ViewTreeObserver.OnGlobalLayoutListener) || Build.VERSION.SDK_INT < 16) {
            return;
        }
        findViewById.getViewTreeObserver().removeOnGlobalLayoutListener((ViewTreeObserver.OnGlobalLayoutListener) tag);
    }
}
