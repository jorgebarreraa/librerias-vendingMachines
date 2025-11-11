package com.blankj.utilcode.util;

import android.R;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.CallSuper;
import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import com.blankj.utilcode.util.Utils;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;

/* loaded from: classes.dex */
public final class ToastUtils {
    private static final int COLOR_DEFAULT = -16777217;
    private static final ToastUtils DEFAULT_MAKER = make();
    private static final String NOTHING = "toast nothing";
    private static final String NULL = "toast null";
    private static final String TAG_TOAST = "TAG_TOAST";
    private static WeakReference<IToast> sWeakToast;
    private String mMode;
    private int mGravity = -1;
    private int mXOffset = -1;
    private int mYOffset = -1;
    private int mBgColor = COLOR_DEFAULT;
    private int mBgResource = -1;
    private int mTextColor = COLOR_DEFAULT;
    private int mTextSize = -1;
    private boolean isLong = false;
    private Drawable[] mIcons = new Drawable[4];
    private boolean isNotUseSystemToast = false;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static abstract class AbsToast implements IToast {
        protected Toast mToast = new Toast(Utils.getApp());
        protected ToastUtils mToastUtils;
        protected View mToastView;

        AbsToast(ToastUtils toastUtils) {
            this.mToastUtils = toastUtils;
            if (this.mToastUtils.mGravity == -1 && this.mToastUtils.mXOffset == -1 && this.mToastUtils.mYOffset == -1) {
                return;
            }
            this.mToast.setGravity(this.mToastUtils.mGravity, this.mToastUtils.mXOffset, this.mToastUtils.mYOffset);
        }

        private void processRtlIfNeed() {
            if (UtilsBridge.isLayoutRtl()) {
                setToastView(getToastViewSnapshot(-1));
            }
        }

        private void setBg(TextView textView) {
            if (this.mToastUtils.mBgResource != -1) {
                this.mToastView.setBackgroundResource(this.mToastUtils.mBgResource);
                textView.setBackgroundColor(0);
                return;
            }
            if (this.mToastUtils.mBgColor != ToastUtils.COLOR_DEFAULT) {
                Drawable background = this.mToastView.getBackground();
                Drawable background2 = textView.getBackground();
                if (background != null && background2 != null) {
                    background.mutate().setColorFilter(new PorterDuffColorFilter(this.mToastUtils.mBgColor, PorterDuff.Mode.SRC_IN));
                    textView.setBackgroundColor(0);
                } else if (background != null) {
                    background.mutate().setColorFilter(new PorterDuffColorFilter(this.mToastUtils.mBgColor, PorterDuff.Mode.SRC_IN));
                } else if (background2 != null) {
                    background2.mutate().setColorFilter(new PorterDuffColorFilter(this.mToastUtils.mBgColor, PorterDuff.Mode.SRC_IN));
                } else {
                    this.mToastView.setBackgroundColor(this.mToastUtils.mBgColor);
                }
            }
        }

        @Override // com.blankj.utilcode.util.ToastUtils.IToast
        @CallSuper
        public void cancel() {
            Toast toast = this.mToast;
            if (toast != null) {
                toast.cancel();
            }
            this.mToast = null;
            this.mToastView = null;
        }

        View getToastViewSnapshot(int i) {
            Bitmap view2Bitmap = UtilsBridge.view2Bitmap(this.mToastView);
            ImageView imageView = new ImageView(Utils.getApp());
            imageView.setTag(ToastUtils.TAG_TOAST + i);
            imageView.setImageBitmap(view2Bitmap);
            return imageView;
        }

        @Override // com.blankj.utilcode.util.ToastUtils.IToast
        public void setToastView(View view) {
            this.mToastView = view;
            this.mToast.setView(this.mToastView);
        }

        @Override // com.blankj.utilcode.util.ToastUtils.IToast
        public void setToastView(CharSequence charSequence) {
            View tryApplyUtilsToastView = this.mToastUtils.tryApplyUtilsToastView(charSequence);
            if (tryApplyUtilsToastView != null) {
                setToastView(tryApplyUtilsToastView);
                processRtlIfNeed();
                return;
            }
            this.mToastView = this.mToast.getView();
            View view = this.mToastView;
            if (view == null || view.findViewById(R.id.message) == null) {
                setToastView(UtilsBridge.layoutId2View(com.blankj.utilcode.R.layout.utils_toast_view));
            }
            TextView textView = (TextView) this.mToastView.findViewById(R.id.message);
            textView.setText(charSequence);
            if (this.mToastUtils.mTextColor != ToastUtils.COLOR_DEFAULT) {
                textView.setTextColor(this.mToastUtils.mTextColor);
            }
            if (this.mToastUtils.mTextSize != -1) {
                textView.setTextSize(this.mToastUtils.mTextSize);
            }
            setBg(textView);
            processRtlIfNeed();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static final class ActivityToast extends AbsToast {
        private static int sShowingIndex;
        private IToast iToast;
        private Utils.ActivityLifecycleCallbacks mActivityLifecycleCallbacks;

        ActivityToast(ToastUtils toastUtils) {
            super(toastUtils);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean isShowing() {
            return this.mActivityLifecycleCallbacks != null;
        }

        private void registerLifecycleCallback() {
            final int i = sShowingIndex;
            this.mActivityLifecycleCallbacks = new Utils.ActivityLifecycleCallbacks() { // from class: com.blankj.utilcode.util.ToastUtils.ActivityToast.2
                @Override // com.blankj.utilcode.util.Utils.ActivityLifecycleCallbacks
                public void onActivityCreated(@NonNull Activity activity) {
                    if (ActivityToast.this.isShowing()) {
                        ActivityToast.this.showWithActivityView(activity, i, false);
                    }
                }
            };
            UtilsBridge.addActivityLifecycleCallbacks(this.mActivityLifecycleCallbacks);
        }

        private IToast showSystemToast(int i) {
            SystemToast systemToast = new SystemToast(this.mToastUtils);
            systemToast.mToast = this.mToast;
            systemToast.show(i);
            return systemToast;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void showWithActivityView(Activity activity, int i, boolean z) {
            Window window = activity.getWindow();
            if (window != null) {
                ViewGroup viewGroup = (ViewGroup) window.getDecorView();
                FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
                layoutParams.gravity = this.mToast.getGravity();
                layoutParams.bottomMargin = this.mToast.getYOffset() + UtilsBridge.getNavBarHeight();
                layoutParams.topMargin = this.mToast.getYOffset() + UtilsBridge.getStatusBarHeight();
                layoutParams.leftMargin = this.mToast.getXOffset();
                View toastViewSnapshot = getToastViewSnapshot(i);
                if (z) {
                    toastViewSnapshot.setAlpha(0.0f);
                    toastViewSnapshot.animate().alpha(1.0f).setDuration(200L).start();
                }
                viewGroup.addView(toastViewSnapshot, layoutParams);
            }
        }

        private IToast showWithActivityWindow(Activity activity, int i) {
            WindowManagerToast windowManagerToast = new WindowManagerToast(this.mToastUtils, activity.getWindowManager(), 99);
            windowManagerToast.mToastView = getToastViewSnapshot(-1);
            windowManagerToast.mToast = this.mToast;
            windowManagerToast.show(i);
            return windowManagerToast;
        }

        private void unregisterLifecycleCallback() {
            UtilsBridge.removeActivityLifecycleCallbacks(this.mActivityLifecycleCallbacks);
            this.mActivityLifecycleCallbacks = null;
        }

        @Override // com.blankj.utilcode.util.ToastUtils.AbsToast, com.blankj.utilcode.util.ToastUtils.IToast
        public void cancel() {
            Window window;
            if (isShowing()) {
                unregisterLifecycleCallback();
                for (Activity activity : UtilsBridge.getActivityList()) {
                    if (UtilsBridge.isActivityAlive(activity) && (window = activity.getWindow()) != null) {
                        ViewGroup viewGroup = (ViewGroup) window.getDecorView();
                        StringBuilder sb = new StringBuilder();
                        sb.append(ToastUtils.TAG_TOAST);
                        sb.append(sShowingIndex - 1);
                        View findViewWithTag = viewGroup.findViewWithTag(sb.toString());
                        if (findViewWithTag != null) {
                            try {
                                viewGroup.removeView(findViewWithTag);
                            } catch (Exception unused) {
                            }
                        }
                    }
                }
            }
            IToast iToast = this.iToast;
            if (iToast != null) {
                iToast.cancel();
                this.iToast = null;
            }
            super.cancel();
        }

        @Override // com.blankj.utilcode.util.ToastUtils.IToast
        public void show(int i) {
            if (this.mToast == null) {
                return;
            }
            if (!UtilsBridge.isAppForeground()) {
                this.iToast = showSystemToast(i);
                return;
            }
            boolean z = false;
            for (Activity activity : UtilsBridge.getActivityList()) {
                if (UtilsBridge.isActivityAlive(activity)) {
                    if (z) {
                        showWithActivityView(activity, sShowingIndex, true);
                    } else {
                        this.iToast = showWithActivityWindow(activity, i);
                        z = true;
                    }
                }
            }
            if (!z) {
                this.iToast = showSystemToast(i);
                return;
            }
            registerLifecycleCallback();
            UtilsBridge.runOnUiThreadDelayed(new Runnable() { // from class: com.blankj.utilcode.util.ToastUtils.ActivityToast.1
                @Override // java.lang.Runnable
                public void run() {
                    ActivityToast.this.cancel();
                }
            }, i == 0 ? 2000L : 3500L);
            sShowingIndex++;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public interface IToast {
        void cancel();

        void setToastView(View view);

        void setToastView(CharSequence charSequence);

        void show(int i);
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface MODE {
        public static final String DARK = "dark";
        public static final String LIGHT = "light";
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static final class SystemToast extends AbsToast {

        /* loaded from: classes.dex */
        static class SafeHandler extends Handler {
            private Handler impl;

            SafeHandler(Handler handler) {
                this.impl = handler;
            }

            @Override // android.os.Handler
            public void dispatchMessage(@NonNull Message message) {
                try {
                    this.impl.dispatchMessage(message);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override // android.os.Handler
            public void handleMessage(@NonNull Message message) {
                this.impl.handleMessage(message);
            }
        }

        SystemToast(ToastUtils toastUtils) {
            super(toastUtils);
            if (Build.VERSION.SDK_INT == 25) {
                try {
                    Field declaredField = Toast.class.getDeclaredField("mTN");
                    declaredField.setAccessible(true);
                    Object obj = declaredField.get(this.mToast);
                    Field declaredField2 = declaredField.getType().getDeclaredField("mHandler");
                    declaredField2.setAccessible(true);
                    declaredField2.set(obj, new SafeHandler((Handler) declaredField2.get(obj)));
                } catch (Exception unused) {
                }
            }
        }

        @Override // com.blankj.utilcode.util.ToastUtils.IToast
        public void show(int i) {
            if (this.mToast == null) {
                return;
            }
            this.mToast.setDuration(i);
            this.mToast.show();
        }
    }

    /* loaded from: classes.dex */
    public static final class UtilsMaxWidthRelativeLayout extends RelativeLayout {
        private static final int SPACING = UtilsBridge.dp2px(80.0f);

        public UtilsMaxWidthRelativeLayout(Context context) {
            super(context);
        }

        public UtilsMaxWidthRelativeLayout(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public UtilsMaxWidthRelativeLayout(Context context, AttributeSet attributeSet, int i) {
            super(context, attributeSet, i);
        }

        @Override // android.widget.RelativeLayout, android.view.View
        protected void onMeasure(int i, int i2) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(UtilsBridge.getAppScreenWidth() - SPACING, Integer.MIN_VALUE), i2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static final class WindowManagerToast extends AbsToast {
        private WindowManager.LayoutParams mParams;
        private WindowManager mWM;

        WindowManagerToast(ToastUtils toastUtils, int i) {
            super(toastUtils);
            this.mParams = new WindowManager.LayoutParams();
            this.mWM = (WindowManager) Utils.getApp().getSystemService("window");
            this.mParams.type = i;
        }

        WindowManagerToast(ToastUtils toastUtils, WindowManager windowManager, int i) {
            super(toastUtils);
            this.mParams = new WindowManager.LayoutParams();
            this.mWM = windowManager;
            this.mParams.type = i;
        }

        @Override // com.blankj.utilcode.util.ToastUtils.AbsToast, com.blankj.utilcode.util.ToastUtils.IToast
        public void cancel() {
            try {
                if (this.mWM != null) {
                    this.mWM.removeViewImmediate(this.mToastView);
                    this.mWM = null;
                }
            } catch (Exception unused) {
            }
            super.cancel();
        }

        @Override // com.blankj.utilcode.util.ToastUtils.IToast
        public void show(int i) {
            if (this.mToast == null) {
                return;
            }
            WindowManager.LayoutParams layoutParams = this.mParams;
            layoutParams.height = -2;
            layoutParams.width = -2;
            layoutParams.format = -3;
            layoutParams.windowAnimations = R.style.Animation.Toast;
            layoutParams.setTitle("ToastWithoutNotification");
            WindowManager.LayoutParams layoutParams2 = this.mParams;
            layoutParams2.flags = 152;
            layoutParams2.packageName = Utils.getApp().getPackageName();
            this.mParams.gravity = this.mToast.getGravity();
            if ((this.mParams.gravity & 7) == 7) {
                this.mParams.horizontalWeight = 1.0f;
            }
            if ((this.mParams.gravity & 112) == 112) {
                this.mParams.verticalWeight = 1.0f;
            }
            this.mParams.x = this.mToast.getXOffset();
            this.mParams.y = this.mToast.getYOffset();
            this.mParams.horizontalMargin = this.mToast.getHorizontalMargin();
            this.mParams.verticalMargin = this.mToast.getVerticalMargin();
            try {
                if (this.mWM != null) {
                    this.mWM.addView(this.mToastView, this.mParams);
                }
            } catch (Exception unused) {
            }
            UtilsBridge.runOnUiThreadDelayed(new Runnable() { // from class: com.blankj.utilcode.util.ToastUtils.WindowManagerToast.1
                @Override // java.lang.Runnable
                public void run() {
                    WindowManagerToast.this.cancel();
                }
            }, i == 0 ? 2000L : 3500L);
        }
    }

    public static void cancel() {
        UtilsBridge.runOnUiThread(new Runnable() { // from class: com.blankj.utilcode.util.ToastUtils.1
            @Override // java.lang.Runnable
            public void run() {
                if (ToastUtils.sWeakToast != null) {
                    IToast iToast = (IToast) ToastUtils.sWeakToast.get();
                    if (iToast != null) {
                        iToast.cancel();
                    }
                    WeakReference unused = ToastUtils.sWeakToast = null;
                }
            }
        });
    }

    @NonNull
    public static ToastUtils getDefaultMaker() {
        return DEFAULT_MAKER;
    }

    private int getDuration() {
        return this.isLong ? 1 : 0;
    }

    private static CharSequence getToastFriendlyText(CharSequence charSequence) {
        return charSequence == null ? NULL : charSequence.length() == 0 ? NOTHING : charSequence;
    }

    @NonNull
    public static ToastUtils make() {
        return new ToastUtils();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static IToast newToast(ToastUtils toastUtils) {
        if (toastUtils.isNotUseSystemToast || !NotificationManagerCompat.from(Utils.getApp()).areNotificationsEnabled() || (Build.VERSION.SDK_INT >= 23 && UtilsBridge.isGrantedDrawOverlays())) {
            return Build.VERSION.SDK_INT < 25 ? new WindowManagerToast(toastUtils, 2005) : UtilsBridge.isGrantedDrawOverlays() ? Build.VERSION.SDK_INT >= 26 ? new WindowManagerToast(toastUtils, 2038) : new WindowManagerToast(toastUtils, 2002) : new ActivityToast(toastUtils);
        }
        return new SystemToast(toastUtils);
    }

    private static void show(@NonNull View view, int i, ToastUtils toastUtils) {
        show(view, null, i, toastUtils);
    }

    private static void show(@Nullable final View view, @Nullable final CharSequence charSequence, final int i, @NonNull ToastUtils toastUtils) {
        UtilsBridge.runOnUiThread(new Runnable() { // from class: com.blankj.utilcode.util.ToastUtils.2
            @Override // java.lang.Runnable
            public void run() {
                ToastUtils.cancel();
                IToast newToast = ToastUtils.newToast(ToastUtils.this);
                WeakReference unused = ToastUtils.sWeakToast = new WeakReference(newToast);
                View view2 = view;
                if (view2 != null) {
                    newToast.setToastView(view2);
                } else {
                    newToast.setToastView(charSequence);
                }
                newToast.show(i);
            }
        });
    }

    private static void show(@Nullable CharSequence charSequence, int i, ToastUtils toastUtils) {
        show(null, getToastFriendlyText(charSequence), i, toastUtils);
    }

    public static void showLong(@StringRes int i) {
        show(UtilsBridge.getString(i), 1, DEFAULT_MAKER);
    }

    public static void showLong(@StringRes int i, Object... objArr) {
        show(UtilsBridge.getString(i, objArr), 1, DEFAULT_MAKER);
    }

    public static void showLong(@Nullable CharSequence charSequence) {
        show(charSequence, 1, DEFAULT_MAKER);
    }

    public static void showLong(@Nullable String str, Object... objArr) {
        show(UtilsBridge.format(str, objArr), 1, DEFAULT_MAKER);
    }

    public static void showShort(@StringRes int i) {
        show(UtilsBridge.getString(i), 0, DEFAULT_MAKER);
    }

    public static void showShort(@StringRes int i, Object... objArr) {
        show(UtilsBridge.getString(i, objArr), 0, DEFAULT_MAKER);
    }

    public static void showShort(@Nullable CharSequence charSequence) {
        show(charSequence, 0, DEFAULT_MAKER);
    }

    public static void showShort(@Nullable String str, Object... objArr) {
        show(UtilsBridge.format(str, objArr), 0, DEFAULT_MAKER);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public View tryApplyUtilsToastView(CharSequence charSequence) {
        if (!MODE.DARK.equals(this.mMode) && !MODE.LIGHT.equals(this.mMode)) {
            Drawable[] drawableArr = this.mIcons;
            if (drawableArr[0] == null && drawableArr[1] == null && drawableArr[2] == null && drawableArr[3] == null) {
                return null;
            }
        }
        View layoutId2View = UtilsBridge.layoutId2View(com.blankj.utilcode.R.layout.utils_toast_view);
        TextView textView = (TextView) layoutId2View.findViewById(R.id.message);
        if (MODE.DARK.equals(this.mMode)) {
            ((GradientDrawable) layoutId2View.getBackground().mutate()).setColor(Color.parseColor("#BB000000"));
            textView.setTextColor(-1);
        }
        textView.setText(charSequence);
        if (this.mIcons[0] != null) {
            View findViewById = layoutId2View.findViewById(com.blankj.utilcode.R.id.utvLeftIconView);
            ViewCompat.setBackground(findViewById, this.mIcons[0]);
            findViewById.setVisibility(0);
        }
        if (this.mIcons[1] != null) {
            View findViewById2 = layoutId2View.findViewById(com.blankj.utilcode.R.id.utvTopIconView);
            ViewCompat.setBackground(findViewById2, this.mIcons[1]);
            findViewById2.setVisibility(0);
        }
        if (this.mIcons[2] != null) {
            View findViewById3 = layoutId2View.findViewById(com.blankj.utilcode.R.id.utvRightIconView);
            ViewCompat.setBackground(findViewById3, this.mIcons[2]);
            findViewById3.setVisibility(0);
        }
        if (this.mIcons[3] != null) {
            View findViewById4 = layoutId2View.findViewById(com.blankj.utilcode.R.id.utvBottomIconView);
            ViewCompat.setBackground(findViewById4, this.mIcons[3]);
            findViewById4.setVisibility(0);
        }
        return layoutId2View;
    }

    @NonNull
    public final ToastUtils setBgColor(@ColorInt int i) {
        this.mBgColor = i;
        return this;
    }

    @NonNull
    public final ToastUtils setBgResource(@DrawableRes int i) {
        this.mBgResource = i;
        return this;
    }

    @NonNull
    public final ToastUtils setBottomIcon(int i) {
        return setBottomIcon(ContextCompat.getDrawable(Utils.getApp(), i));
    }

    @NonNull
    public final ToastUtils setBottomIcon(@Nullable Drawable drawable) {
        this.mIcons[3] = drawable;
        return this;
    }

    @NonNull
    public final ToastUtils setDurationIsLong(boolean z) {
        this.isLong = z;
        return this;
    }

    @NonNull
    public final ToastUtils setGravity(int i, int i2, int i3) {
        this.mGravity = i;
        this.mXOffset = i2;
        this.mYOffset = i3;
        return this;
    }

    @NonNull
    public final ToastUtils setLeftIcon(@DrawableRes int i) {
        return setLeftIcon(ContextCompat.getDrawable(Utils.getApp(), i));
    }

    @NonNull
    public final ToastUtils setLeftIcon(@Nullable Drawable drawable) {
        this.mIcons[0] = drawable;
        return this;
    }

    @NonNull
    public final ToastUtils setMode(String str) {
        this.mMode = str;
        return this;
    }

    @NonNull
    public final ToastUtils setNotUseSystemToast() {
        this.isNotUseSystemToast = true;
        return this;
    }

    @NonNull
    public final ToastUtils setRightIcon(@DrawableRes int i) {
        return setRightIcon(ContextCompat.getDrawable(Utils.getApp(), i));
    }

    @NonNull
    public final ToastUtils setRightIcon(@Nullable Drawable drawable) {
        this.mIcons[2] = drawable;
        return this;
    }

    @NonNull
    public final ToastUtils setTextColor(@ColorInt int i) {
        this.mTextColor = i;
        return this;
    }

    @NonNull
    public final ToastUtils setTextSize(int i) {
        this.mTextSize = i;
        return this;
    }

    @NonNull
    public final ToastUtils setTopIcon(@DrawableRes int i) {
        return setTopIcon(ContextCompat.getDrawable(Utils.getApp(), i));
    }

    @NonNull
    public final ToastUtils setTopIcon(@Nullable Drawable drawable) {
        this.mIcons[1] = drawable;
        return this;
    }

    public final void show(@StringRes int i) {
        show(UtilsBridge.getString(i), getDuration(), this);
    }

    public final void show(@StringRes int i, Object... objArr) {
        show(UtilsBridge.getString(i, objArr), getDuration(), this);
    }

    public final void show(@NonNull View view) {
        show(view, getDuration(), this);
    }

    public final void show(@Nullable CharSequence charSequence) {
        show(charSequence, getDuration(), this);
    }

    public final void show(@Nullable String str, Object... objArr) {
        show(UtilsBridge.format(str, objArr), getDuration(), this);
    }
}
