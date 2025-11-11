package com.blankj.utilcode.util;

import android.R;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.os.SystemClock;
import android.util.Log;
import android.util.StateSet;
import android.view.MotionEvent;
import android.view.TouchDelegate;
import android.view.View;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;
import com.blankj.utilcode.util.ShadowUtils;

/* loaded from: classes.dex */
public class ClickUtils {
    private static final long DEBOUNCING_DEFAULT_VALUE = 1000;
    private static final float PRESSED_BG_ALPHA_DEFAULT_VALUE = 0.9f;
    private static final int PRESSED_BG_ALPHA_STYLE = 4;
    private static final float PRESSED_BG_DARK_DEFAULT_VALUE = 0.9f;
    private static final int PRESSED_BG_DARK_STYLE = 5;
    private static final float PRESSED_VIEW_ALPHA_DEFAULT_VALUE = 0.8f;
    private static final int PRESSED_VIEW_ALPHA_SRC_TAG = -3;
    private static final int PRESSED_VIEW_ALPHA_TAG = -2;
    private static final float PRESSED_VIEW_SCALE_DEFAULT_VALUE = -0.06f;
    private static final int PRESSED_VIEW_SCALE_TAG = -1;
    private static final long TIP_DURATION = 2000;
    private static int sClickCount;
    private static long sLastClickMillis;

    /* loaded from: classes.dex */
    public interface Back2HomeFriendlyListener {
        public static final Back2HomeFriendlyListener DEFAULT = new Back2HomeFriendlyListener() { // from class: com.blankj.utilcode.util.ClickUtils.Back2HomeFriendlyListener.1
            @Override // com.blankj.utilcode.util.ClickUtils.Back2HomeFriendlyListener
            public void dismiss() {
                UtilsBridge.toastCancel();
            }

            @Override // com.blankj.utilcode.util.ClickUtils.Back2HomeFriendlyListener
            public void show(CharSequence charSequence, long j) {
                UtilsBridge.toastShowShort(charSequence);
            }
        };

        void dismiss();

        void show(CharSequence charSequence, long j);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class ClickDrawableWrapper extends ShadowUtils.DrawableWrapper {
        private BitmapDrawable mBitmapDrawable;
        private Paint mColorPaint;

        public ClickDrawableWrapper(Drawable drawable) {
            super(drawable);
            this.mBitmapDrawable = null;
            this.mColorPaint = null;
            if (drawable instanceof ColorDrawable) {
                this.mColorPaint = new Paint(5);
                this.mColorPaint.setColor(((ColorDrawable) drawable).getColor());
            }
        }

        @Override // com.blankj.utilcode.util.ShadowUtils.DrawableWrapper, android.graphics.drawable.Drawable
        public void draw(Canvas canvas) {
            if (this.mBitmapDrawable == null) {
                Bitmap createBitmap = Bitmap.createBitmap(getBounds().width(), getBounds().height(), Bitmap.Config.ARGB_8888);
                Canvas canvas2 = new Canvas(createBitmap);
                if (this.mColorPaint != null) {
                    canvas2.drawRect(getBounds(), this.mColorPaint);
                } else {
                    super.draw(canvas2);
                }
                this.mBitmapDrawable = new BitmapDrawable(Resources.getSystem(), createBitmap);
                this.mBitmapDrawable.setBounds(getBounds());
            }
            this.mBitmapDrawable.draw(canvas);
        }

        @Override // com.blankj.utilcode.util.ShadowUtils.DrawableWrapper, android.graphics.drawable.Drawable
        public void setAlpha(int i) {
            Paint paint;
            super.setAlpha(i);
            if (Build.VERSION.SDK_INT >= 21 || (paint = this.mColorPaint) == null) {
                return;
            }
            paint.setColor(((ColorDrawable) getWrappedDrawable()).getColor());
        }

        @Override // com.blankj.utilcode.util.ShadowUtils.DrawableWrapper, android.graphics.drawable.Drawable
        public void setColorFilter(ColorFilter colorFilter) {
            Paint paint;
            super.setColorFilter(colorFilter);
            if (Build.VERSION.SDK_INT >= 21 || (paint = this.mColorPaint) == null) {
                return;
            }
            paint.setColorFilter(colorFilter);
        }
    }

    /* loaded from: classes.dex */
    public static abstract class OnDebouncingClickListener implements View.OnClickListener {
        private static final Runnable ENABLE_AGAIN = new Runnable() { // from class: com.blankj.utilcode.util.ClickUtils.OnDebouncingClickListener.1
            @Override // java.lang.Runnable
            public void run() {
                boolean unused = OnDebouncingClickListener.mEnabled = true;
            }
        };
        private static boolean mEnabled = true;
        private long mDuration;
        private boolean mIsGlobal;

        public OnDebouncingClickListener() {
            this(true, ClickUtils.DEBOUNCING_DEFAULT_VALUE);
        }

        public OnDebouncingClickListener(long j) {
            this(true, j);
        }

        public OnDebouncingClickListener(boolean z) {
            this(z, ClickUtils.DEBOUNCING_DEFAULT_VALUE);
        }

        public OnDebouncingClickListener(boolean z, long j) {
            this.mIsGlobal = z;
            this.mDuration = j;
        }

        private static boolean isValid(@NonNull View view, long j) {
            return UtilsBridge.isValid(view, j);
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            if (!this.mIsGlobal) {
                if (isValid(view, this.mDuration)) {
                    onDebouncingClick(view);
                }
            } else if (mEnabled) {
                mEnabled = false;
                view.postDelayed(ENABLE_AGAIN, this.mDuration);
                onDebouncingClick(view);
            }
        }

        public abstract void onDebouncingClick(View view);
    }

    /* loaded from: classes.dex */
    public static abstract class OnMultiClickListener implements View.OnClickListener {
        private static final long INTERVAL_DEFAULT_VALUE = 666;
        private int mClickCount;
        private final long mClickInterval;
        private long mLastClickTime;
        private final int mTriggerClickCount;

        public OnMultiClickListener(int i) {
            this(i, INTERVAL_DEFAULT_VALUE);
        }

        public OnMultiClickListener(int i, long j) {
            this.mTriggerClickCount = i;
            this.mClickInterval = j;
        }

        public abstract void onBeforeTriggerClick(View view, int i);

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (this.mTriggerClickCount <= 1) {
                onTriggerClick(view);
                return;
            }
            long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis - this.mLastClickTime < this.mClickInterval) {
                this.mClickCount++;
                int i = this.mClickCount;
                int i2 = this.mTriggerClickCount;
                if (i == i2) {
                    onTriggerClick(view);
                } else if (i < i2) {
                    onBeforeTriggerClick(view, i);
                } else {
                    this.mClickCount = 1;
                    onBeforeTriggerClick(view, this.mClickCount);
                }
            } else {
                this.mClickCount = 1;
                onBeforeTriggerClick(view, this.mClickCount);
            }
            this.mLastClickTime = currentTimeMillis;
        }

        public abstract void onTriggerClick(View view);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class OnUtilsTouchListener implements View.OnTouchListener {

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class LazyHolder {
            private static final OnUtilsTouchListener INSTANCE = new OnUtilsTouchListener();

            private LazyHolder() {
            }
        }

        private OnUtilsTouchListener() {
        }

        public static OnUtilsTouchListener getInstance() {
            return LazyHolder.INSTANCE;
        }

        private void processAlpha(View view, boolean z) {
            Object tag = view.getTag(z ? -2 : -3);
            if (tag instanceof Float) {
                view.setAlpha(((Float) tag).floatValue());
            }
        }

        private void processScale(View view, boolean z) {
            Object tag = view.getTag(-1);
            if (tag instanceof Float) {
                float floatValue = z ? 1.0f + ((Float) tag).floatValue() : 1.0f;
                view.animate().scaleX(floatValue).scaleY(floatValue).setDuration(200L).start();
            }
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            int action = motionEvent.getAction();
            if (action == 0) {
                processScale(view, true);
                processAlpha(view, true);
            } else if (action == 1 || action == 3) {
                processScale(view, false);
                processAlpha(view, false);
            }
            return false;
        }
    }

    private ClickUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    private static void applyDebouncing(View[] viewArr, boolean z, @IntRange(from = 0) long j, final View.OnClickListener onClickListener) {
        if (viewArr == null || viewArr.length == 0 || onClickListener == null) {
            return;
        }
        for (View view : viewArr) {
            if (view != null) {
                view.setOnClickListener(new OnDebouncingClickListener(z, j) { // from class: com.blankj.utilcode.util.ClickUtils.1
                    @Override // com.blankj.utilcode.util.ClickUtils.OnDebouncingClickListener
                    public void onDebouncingClick(View view2) {
                        onClickListener.onClick(view2);
                    }
                });
            }
        }
    }

    public static void applyGlobalDebouncing(View view, @IntRange(from = 0) long j, View.OnClickListener onClickListener) {
        applyGlobalDebouncing(new View[]{view}, j, onClickListener);
    }

    public static void applyGlobalDebouncing(View view, View.OnClickListener onClickListener) {
        applyGlobalDebouncing(new View[]{view}, onClickListener);
    }

    public static void applyGlobalDebouncing(View[] viewArr, @IntRange(from = 0) long j, View.OnClickListener onClickListener) {
        applyDebouncing(viewArr, true, j, onClickListener);
    }

    public static void applyGlobalDebouncing(View[] viewArr, View.OnClickListener onClickListener) {
        applyGlobalDebouncing(viewArr, DEBOUNCING_DEFAULT_VALUE, onClickListener);
    }

    public static void applyPressedBgAlpha(View view) {
        applyPressedBgAlpha(view, 0.9f);
    }

    public static void applyPressedBgAlpha(View view, float f) {
        applyPressedBgStyle(view, 4, f);
    }

    public static void applyPressedBgDark(View view) {
        applyPressedBgDark(view, 0.9f);
    }

    public static void applyPressedBgDark(View view, float f) {
        applyPressedBgStyle(view, 5, f);
    }

    private static void applyPressedBgStyle(View view, int i, float f) {
        if (view == null) {
            return;
        }
        Drawable background = view.getBackground();
        int i2 = -i;
        Object tag = view.getTag(i2);
        if (tag instanceof Drawable) {
            ViewCompat.setBackground(view, (Drawable) tag);
            return;
        }
        Drawable createStyleDrawable = createStyleDrawable(background, i, f);
        ViewCompat.setBackground(view, createStyleDrawable);
        view.setTag(i2, createStyleDrawable);
    }

    public static void applyPressedViewAlpha(View view, float f) {
        if (view == null) {
            return;
        }
        view.setTag(-2, Float.valueOf(f));
        view.setTag(-3, Float.valueOf(view.getAlpha()));
        view.setClickable(true);
        view.setOnTouchListener(OnUtilsTouchListener.getInstance());
    }

    public static void applyPressedViewAlpha(View... viewArr) {
        applyPressedViewAlpha(viewArr, (float[]) null);
    }

    public static void applyPressedViewAlpha(View[] viewArr, float[] fArr) {
        if (viewArr == null || viewArr.length == 0) {
            return;
        }
        for (int i = 0; i < viewArr.length; i++) {
            if (fArr == null || i >= fArr.length) {
                applyPressedViewAlpha(viewArr[i], PRESSED_VIEW_ALPHA_DEFAULT_VALUE);
            } else {
                applyPressedViewAlpha(viewArr[i], fArr[i]);
            }
        }
    }

    public static void applyPressedViewScale(View view, float f) {
        if (view == null) {
            return;
        }
        view.setTag(-1, Float.valueOf(f));
        view.setClickable(true);
        view.setOnTouchListener(OnUtilsTouchListener.getInstance());
    }

    public static void applyPressedViewScale(View... viewArr) {
        applyPressedViewScale(viewArr, (float[]) null);
    }

    public static void applyPressedViewScale(View[] viewArr, float[] fArr) {
        if (viewArr == null || viewArr.length == 0) {
            return;
        }
        for (int i = 0; i < viewArr.length; i++) {
            if (fArr == null || i >= fArr.length) {
                applyPressedViewScale(viewArr[i], PRESSED_VIEW_SCALE_DEFAULT_VALUE);
            } else {
                applyPressedViewScale(viewArr[i], fArr[i]);
            }
        }
    }

    public static void applySingleDebouncing(View view, @IntRange(from = 0) long j, View.OnClickListener onClickListener) {
        applySingleDebouncing(new View[]{view}, j, onClickListener);
    }

    public static void applySingleDebouncing(View view, View.OnClickListener onClickListener) {
        applySingleDebouncing(new View[]{view}, onClickListener);
    }

    public static void applySingleDebouncing(View[] viewArr, @IntRange(from = 0) long j, View.OnClickListener onClickListener) {
        applyDebouncing(viewArr, false, j, onClickListener);
    }

    public static void applySingleDebouncing(View[] viewArr, View.OnClickListener onClickListener) {
        applySingleDebouncing(viewArr, DEBOUNCING_DEFAULT_VALUE, onClickListener);
    }

    public static void back2HomeFriendly(CharSequence charSequence) {
        back2HomeFriendly(charSequence, TIP_DURATION, Back2HomeFriendlyListener.DEFAULT);
    }

    public static void back2HomeFriendly(@NonNull CharSequence charSequence, long j, @NonNull Back2HomeFriendlyListener back2HomeFriendlyListener) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (Math.abs(elapsedRealtime - sLastClickMillis) >= j) {
            sClickCount = 1;
            back2HomeFriendlyListener.show(charSequence, j);
            sLastClickMillis = elapsedRealtime;
        } else {
            sClickCount++;
            if (sClickCount == 2) {
                UtilsBridge.startHomeActivity();
                back2HomeFriendlyListener.dismiss();
                sLastClickMillis = 0L;
            }
        }
    }

    private static Drawable createAlphaDrawable(Drawable drawable, float f) {
        ClickDrawableWrapper clickDrawableWrapper = new ClickDrawableWrapper(drawable);
        clickDrawableWrapper.setAlpha((int) (f * 255.0f));
        return clickDrawableWrapper;
    }

    private static Drawable createDarkDrawable(Drawable drawable, float f) {
        ClickDrawableWrapper clickDrawableWrapper = new ClickDrawableWrapper(drawable);
        clickDrawableWrapper.setColorFilter(getDarkColorFilter(f));
        return clickDrawableWrapper;
    }

    private static Drawable createStyleDrawable(Drawable drawable, int i, float f) {
        if (drawable == null) {
            drawable = new ColorDrawable(0);
        }
        if (drawable.getConstantState() == null) {
            return drawable;
        }
        Drawable mutate = drawable.getConstantState().newDrawable().mutate();
        if (i == 4) {
            mutate = createAlphaDrawable(mutate, f);
        } else if (i == 5) {
            mutate = createDarkDrawable(mutate, f);
        }
        Drawable createAlphaDrawable = createAlphaDrawable(drawable.getConstantState().newDrawable().mutate(), 0.5f);
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{R.attr.state_pressed}, mutate);
        stateListDrawable.addState(new int[]{-16842910}, createAlphaDrawable);
        stateListDrawable.addState(StateSet.WILD_CARD, drawable);
        return stateListDrawable;
    }

    public static void expandClickArea(@NonNull View view, int i) {
        expandClickArea(view, i, i, i, i);
    }

    public static void expandClickArea(@NonNull final View view, final int i, final int i2, final int i3, final int i4) {
        final View view2 = (View) view.getParent();
        if (view2 == null) {
            Log.e("ClickUtils", "expandClickArea must have parent view.");
        } else {
            view2.post(new Runnable() { // from class: com.blankj.utilcode.util.ClickUtils.2
                @Override // java.lang.Runnable
                public void run() {
                    Rect rect = new Rect();
                    view.getHitRect(rect);
                    rect.top -= i;
                    rect.bottom += i4;
                    rect.left -= i2;
                    rect.right += i3;
                    view2.setTouchDelegate(new TouchDelegate(rect, view));
                }
            });
        }
    }

    private static ColorMatrixColorFilter getDarkColorFilter(float f) {
        return new ColorMatrixColorFilter(new ColorMatrix(new float[]{f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 2.0f, 0.0f}));
    }
}
