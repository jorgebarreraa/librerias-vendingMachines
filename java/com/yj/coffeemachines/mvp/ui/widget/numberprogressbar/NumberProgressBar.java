package com.yj.coffeemachines.mvp.ui.widget.numberprogressbar;

import android.app.kingsun.rk3288;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import com.yj.coffeemachines.R;

/* loaded from: classes.dex */
public class NumberProgressBar extends View {
    private static final String INSTANCE_MAX = "max";
    private static final String INSTANCE_PREFIX = "prefix";
    private static final String INSTANCE_PROGRESS = "progress";
    private static final String INSTANCE_REACHED_BAR_COLOR = "reached_bar_color";
    private static final String INSTANCE_REACHED_BAR_HEIGHT = "reached_bar_height";
    private static final String INSTANCE_STATE = "saved_instance";
    private static final String INSTANCE_SUFFIX = "suffix";
    private static final String INSTANCE_TEXT_COLOR = "text_color";
    private static final String INSTANCE_TEXT_SIZE = "text_size";
    private static final String INSTANCE_TEXT_VISIBILITY = "text_visibility";
    private static final String INSTANCE_UNREACHED_BAR_COLOR = "unreached_bar_color";
    private static final String INSTANCE_UNREACHED_BAR_HEIGHT = "unreached_bar_height";
    private static final int PROGRESS_TEXT_VISIBLE = 0;
    private final float default_progress_text_offset;
    private final float default_reached_bar_height;
    private final int default_reached_color;
    private final int default_text_color;
    private final float default_text_size;
    private final float default_unreached_bar_height;
    private final int default_unreached_color;
    private String mCurrentDrawText;
    private int mCurrentProgress;
    private boolean mDrawReachedBar;
    private float mDrawTextEnd;
    private float mDrawTextStart;
    private float mDrawTextWidth;
    private boolean mDrawUnreachedBar;
    private boolean mIfDrawText;
    private OnProgressBarListener mListener;
    private int mMaxProgress;
    private float mOffset;
    private String mPrefix;
    private int mReachedBarColor;
    private float mReachedBarHeight;
    private Paint mReachedBarPaint;
    private RectF mReachedRectF;
    private String mSuffix;
    private int mTextColor;
    private Paint mTextPaint;
    private float mTextSize;
    private int mUnreachedBarColor;
    private float mUnreachedBarHeight;
    private Paint mUnreachedBarPaint;
    private RectF mUnreachedRectF;

    /* loaded from: classes.dex */
    public enum ProgressTextVisibility {
        Visible,
        Invisible
    }

    public NumberProgressBar(Context context) {
        this(context, null);
    }

    public NumberProgressBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public NumberProgressBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mMaxProgress = 100;
        this.mCurrentProgress = 0;
        this.mSuffix = "%";
        this.mPrefix = "";
        this.default_text_color = Color.rgb(66, 145, rk3288.RK30_PIN7_PC1);
        this.default_reached_color = Color.rgb(66, 145, rk3288.RK30_PIN7_PC1);
        this.default_unreached_color = Color.rgb(rk3288.RK30_PIN6_PB4, rk3288.RK30_PIN6_PB4, rk3288.RK30_PIN6_PB4);
        this.mUnreachedRectF = new RectF(0.0f, 0.0f, 0.0f, 0.0f);
        this.mReachedRectF = new RectF(0.0f, 0.0f, 0.0f, 0.0f);
        this.mDrawUnreachedBar = true;
        this.mDrawReachedBar = true;
        this.mIfDrawText = true;
        this.default_reached_bar_height = dp2px(15.0f);
        this.default_unreached_bar_height = dp2px(10.0f);
        this.default_text_size = sp2px(20.0f);
        this.default_progress_text_offset = dp2px(30.0f);
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.NumberProgressBar, i, 0);
        this.mReachedBarColor = obtainStyledAttributes.getColor(3, this.default_reached_color);
        this.mUnreachedBarColor = obtainStyledAttributes.getColor(9, this.default_unreached_color);
        this.mTextColor = obtainStyledAttributes.getColor(4, this.default_text_color);
        this.mTextSize = obtainStyledAttributes.getDimension(6, this.default_text_size);
        this.mReachedBarHeight = obtainStyledAttributes.getDimension(2, this.default_reached_bar_height);
        this.mUnreachedBarHeight = obtainStyledAttributes.getDimension(8, this.default_unreached_bar_height);
        this.mOffset = obtainStyledAttributes.getDimension(5, this.default_progress_text_offset);
        if (obtainStyledAttributes.getInt(7, 0) != 0) {
            this.mIfDrawText = false;
        }
        setProgress(obtainStyledAttributes.getInt(0, 0));
        setMax(obtainStyledAttributes.getInt(1, 100));
        obtainStyledAttributes.recycle();
        initializePainters();
    }

    private void calculateDrawRectF() {
        this.mCurrentDrawText = String.format("%d", Integer.valueOf((getProgress() * 100) / getMax()));
        this.mCurrentDrawText = this.mPrefix + this.mCurrentDrawText + this.mSuffix;
        this.mDrawTextWidth = this.mTextPaint.measureText(this.mCurrentDrawText);
        if (getProgress() == 0) {
            this.mDrawReachedBar = false;
            this.mDrawTextStart = getPaddingLeft();
        } else {
            this.mDrawReachedBar = true;
            this.mReachedRectF.left = getPaddingLeft();
            this.mReachedRectF.top = (getHeight() / 2.0f) - (this.mReachedBarHeight / 2.0f);
            this.mReachedRectF.right = (((((getWidth() - getPaddingLeft()) - getPaddingRight()) / (getMax() * 1.0f)) * getProgress()) - this.mOffset) + getPaddingLeft();
            this.mReachedRectF.bottom = (getHeight() / 2.0f) + (this.mReachedBarHeight / 2.0f);
            this.mDrawTextStart = this.mReachedRectF.right + this.mOffset;
        }
        this.mDrawTextEnd = (int) ((getHeight() / 2.0f) - ((this.mTextPaint.descent() + this.mTextPaint.ascent()) / 2.0f));
        if (this.mDrawTextStart + this.mDrawTextWidth >= getWidth() - getPaddingRight()) {
            this.mDrawTextStart = (getWidth() - getPaddingRight()) - this.mDrawTextWidth;
            this.mReachedRectF.right = this.mDrawTextStart - this.mOffset;
        }
        float f = this.mDrawTextStart + this.mDrawTextWidth + this.mOffset;
        if (f >= getWidth() - getPaddingRight()) {
            this.mDrawUnreachedBar = false;
            return;
        }
        this.mDrawUnreachedBar = true;
        RectF rectF = this.mUnreachedRectF;
        rectF.left = f;
        rectF.right = getWidth() - getPaddingRight();
        this.mUnreachedRectF.top = (getHeight() / 2.0f) + ((-this.mUnreachedBarHeight) / 2.0f);
        this.mUnreachedRectF.bottom = (getHeight() / 2.0f) + (this.mUnreachedBarHeight / 2.0f);
    }

    private void calculateDrawRectFWithoutProgressText() {
        this.mReachedRectF.left = getPaddingLeft();
        this.mReachedRectF.top = (getHeight() / 2.0f) - (this.mReachedBarHeight / 2.0f);
        this.mReachedRectF.right = ((((getWidth() - getPaddingLeft()) - getPaddingRight()) / (getMax() * 1.0f)) * getProgress()) + getPaddingLeft();
        this.mReachedRectF.bottom = (getHeight() / 2.0f) + (this.mReachedBarHeight / 2.0f);
        this.mUnreachedRectF.left = this.mReachedRectF.right;
        this.mUnreachedRectF.right = getWidth() - getPaddingRight();
        this.mUnreachedRectF.top = (getHeight() / 2.0f) + ((-this.mUnreachedBarHeight) / 2.0f);
        this.mUnreachedRectF.bottom = (getHeight() / 2.0f) + (this.mUnreachedBarHeight / 2.0f);
    }

    private void initializePainters() {
        this.mReachedBarPaint = new Paint(1);
        this.mReachedBarPaint.setColor(this.mReachedBarColor);
        this.mUnreachedBarPaint = new Paint(1);
        this.mUnreachedBarPaint.setColor(this.mUnreachedBarColor);
        this.mTextPaint = new Paint(1);
        this.mTextPaint.setColor(this.mTextColor);
        this.mTextPaint.setTextSize(this.mTextSize);
    }

    private int measure(int i, boolean z) {
        int paddingTop;
        int paddingBottom;
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        if (z) {
            paddingTop = getPaddingLeft();
            paddingBottom = getPaddingRight();
        } else {
            paddingTop = getPaddingTop();
            paddingBottom = getPaddingBottom();
        }
        int i2 = paddingTop + paddingBottom;
        if (mode == 1073741824) {
            return size;
        }
        int suggestedMinimumWidth = i2 + (z ? getSuggestedMinimumWidth() : getSuggestedMinimumHeight());
        return mode == Integer.MIN_VALUE ? z ? Math.max(suggestedMinimumWidth, size) : Math.min(suggestedMinimumWidth, size) : suggestedMinimumWidth;
    }

    public float dp2px(float f) {
        return (f * getResources().getDisplayMetrics().density) + 0.5f;
    }

    public int getMax() {
        return this.mMaxProgress;
    }

    public String getPrefix() {
        return this.mPrefix;
    }

    public int getProgress() {
        return this.mCurrentProgress;
    }

    public float getProgressTextSize() {
        return this.mTextSize;
    }

    public boolean getProgressTextVisibility() {
        return this.mIfDrawText;
    }

    public int getReachedBarColor() {
        return this.mReachedBarColor;
    }

    public float getReachedBarHeight() {
        return this.mReachedBarHeight;
    }

    public String getSuffix() {
        return this.mSuffix;
    }

    @Override // android.view.View
    protected int getSuggestedMinimumHeight() {
        return Math.max((int) this.mTextSize, Math.max((int) this.mReachedBarHeight, (int) this.mUnreachedBarHeight));
    }

    @Override // android.view.View
    protected int getSuggestedMinimumWidth() {
        return (int) this.mTextSize;
    }

    public int getTextColor() {
        return this.mTextColor;
    }

    public int getUnreachedBarColor() {
        return this.mUnreachedBarColor;
    }

    public float getUnreachedBarHeight() {
        return this.mUnreachedBarHeight;
    }

    public void incrementProgressBy(int i) {
        if (i > 0) {
            setProgress(getProgress() + i);
        }
        OnProgressBarListener onProgressBarListener = this.mListener;
        if (onProgressBarListener != null) {
            onProgressBarListener.onProgressChange(getProgress(), getMax());
        }
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        if (this.mIfDrawText) {
            calculateDrawRectF();
        } else {
            calculateDrawRectFWithoutProgressText();
        }
        if (this.mDrawReachedBar) {
            canvas.drawRect(this.mReachedRectF, this.mReachedBarPaint);
        }
        if (this.mDrawUnreachedBar) {
            canvas.drawRect(this.mUnreachedRectF, this.mUnreachedBarPaint);
        }
        if (this.mIfDrawText) {
            canvas.drawText(this.mCurrentDrawText, this.mDrawTextStart, this.mDrawTextEnd, this.mTextPaint);
        }
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        setMeasuredDimension(measure(i, true), measure(i2, false));
    }

    @Override // android.view.View
    protected void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof Bundle)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        Bundle bundle = (Bundle) parcelable;
        this.mTextColor = bundle.getInt(INSTANCE_TEXT_COLOR);
        this.mTextSize = bundle.getFloat(INSTANCE_TEXT_SIZE);
        this.mReachedBarHeight = bundle.getFloat(INSTANCE_REACHED_BAR_HEIGHT);
        this.mUnreachedBarHeight = bundle.getFloat(INSTANCE_UNREACHED_BAR_HEIGHT);
        this.mReachedBarColor = bundle.getInt(INSTANCE_REACHED_BAR_COLOR);
        this.mUnreachedBarColor = bundle.getInt(INSTANCE_UNREACHED_BAR_COLOR);
        initializePainters();
        setMax(bundle.getInt(INSTANCE_MAX));
        setProgress(bundle.getInt("progress"));
        setPrefix(bundle.getString(INSTANCE_PREFIX));
        setSuffix(bundle.getString(INSTANCE_SUFFIX));
        setProgressTextVisibility(bundle.getBoolean(INSTANCE_TEXT_VISIBILITY) ? ProgressTextVisibility.Visible : ProgressTextVisibility.Invisible);
        super.onRestoreInstanceState(bundle.getParcelable(INSTANCE_STATE));
    }

    @Override // android.view.View
    protected Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable(INSTANCE_STATE, super.onSaveInstanceState());
        bundle.putInt(INSTANCE_TEXT_COLOR, getTextColor());
        bundle.putFloat(INSTANCE_TEXT_SIZE, getProgressTextSize());
        bundle.putFloat(INSTANCE_REACHED_BAR_HEIGHT, getReachedBarHeight());
        bundle.putFloat(INSTANCE_UNREACHED_BAR_HEIGHT, getUnreachedBarHeight());
        bundle.putInt(INSTANCE_REACHED_BAR_COLOR, getReachedBarColor());
        bundle.putInt(INSTANCE_UNREACHED_BAR_COLOR, getUnreachedBarColor());
        bundle.putInt(INSTANCE_MAX, getMax());
        bundle.putInt("progress", getProgress());
        bundle.putString(INSTANCE_SUFFIX, getSuffix());
        bundle.putString(INSTANCE_PREFIX, getPrefix());
        bundle.putBoolean(INSTANCE_TEXT_VISIBILITY, getProgressTextVisibility());
        return bundle;
    }

    public void setMax(int i) {
        if (i > 0) {
            this.mMaxProgress = i;
            invalidate();
        }
    }

    public void setOnProgressBarListener(OnProgressBarListener onProgressBarListener) {
        this.mListener = onProgressBarListener;
    }

    public void setPrefix(String str) {
        if (str == null) {
            this.mPrefix = "";
        } else {
            this.mPrefix = str;
        }
    }

    public void setProgress(int i) {
        if (i > getMax() || i < 0) {
            return;
        }
        this.mCurrentProgress = i;
        invalidate();
    }

    public void setProgressTextColor(int i) {
        this.mTextColor = i;
        this.mTextPaint.setColor(this.mTextColor);
        invalidate();
    }

    public void setProgressTextSize(float f) {
        this.mTextSize = f;
        this.mTextPaint.setTextSize(this.mTextSize);
        invalidate();
    }

    public void setProgressTextVisibility(ProgressTextVisibility progressTextVisibility) {
        this.mIfDrawText = progressTextVisibility == ProgressTextVisibility.Visible;
        invalidate();
    }

    public void setReachedBarColor(int i) {
        this.mReachedBarColor = i;
        this.mReachedBarPaint.setColor(this.mReachedBarColor);
        invalidate();
    }

    public void setReachedBarHeight(float f) {
        this.mReachedBarHeight = f;
    }

    public void setSuffix(String str) {
        if (str == null) {
            this.mSuffix = "";
        } else {
            this.mSuffix = str;
        }
    }

    public void setUnreachedBarColor(int i) {
        this.mUnreachedBarColor = i;
        this.mUnreachedBarPaint.setColor(this.mUnreachedBarColor);
        invalidate();
    }

    public void setUnreachedBarHeight(float f) {
        this.mUnreachedBarHeight = f;
    }

    public float sp2px(float f) {
        return f * getResources().getDisplayMetrics().scaledDensity;
    }
}
