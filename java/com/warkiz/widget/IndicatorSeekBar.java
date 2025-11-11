package com.warkiz.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;

/* loaded from: classes.dex */
public class IndicatorSeekBar extends View {
    private static final String FORMAT_PROGRESS = "${PROGRESS}";
    private static final String FORMAT_TICK_TEXT = "${TICK_TEXT}";
    private static final int THUMB_MAX_WIDTH = 30;
    private float lastProgress;
    private boolean mAdjustAuto;
    private RectF mBackgroundTrack;
    private int mBackgroundTrackColor;
    private int mBackgroundTrackSize;
    private boolean mClearPadding;
    private Context mContext;
    private float mCustomDrawableMaxHeight;
    private boolean mCustomTrackSectionColorResult;
    private float mFaultTolerance;
    private boolean mHideThumb;
    private int mHoveredTextColor;
    private Indicator mIndicator;
    private int mIndicatorColor;
    private View mIndicatorContentView;
    private boolean mIndicatorStayAlways;
    private int mIndicatorTextColor;
    private String mIndicatorTextFormat;
    private int mIndicatorTextSize;
    private View mIndicatorTopContentView;
    private boolean mIsFloatProgress;
    private boolean mIsTouching;
    private float mMax;
    private int mMeasuredWidth;
    private float mMin;
    private boolean mOnlyThumbDraggable;
    private int mPaddingLeft;
    private int mPaddingRight;
    private int mPaddingTop;
    private Bitmap mPressedThumbBitmap;
    private int mPressedThumbColor;
    private float mProgress;
    private float[] mProgressArr;
    private RectF mProgressTrack;
    private int mProgressTrackColor;
    private int mProgressTrackSize;
    private boolean mR2L;
    private Rect mRect;
    private int mScale;
    private float mScreenWidth;
    private int[] mSectionTrackColorArray;
    private float mSeekBlockLength;
    private OnSeekChangeListener mSeekChangeListener;
    private float mSeekLength;
    private SeekParams mSeekParams;
    private boolean mSeekSmoothly;
    private Bitmap mSelectTickMarksBitmap;
    private int mSelectedTextsColor;
    private int mSelectedTickMarksColor;
    private boolean mShowBothTickTextsOnly;
    private int mShowIndicatorType;
    private boolean mShowThumbText;
    private int mShowTickMarksType;
    private boolean mShowTickText;
    private Paint mStockPaint;
    private float[] mTextCenterX;
    private TextPaint mTextPaint;
    private Typeface mTextsTypeface;
    private Bitmap mThumbBitmap;
    private int mThumbColor;
    private Drawable mThumbDrawable;
    private float mThumbRadius;
    private int mThumbSize;
    private int mThumbTextColor;
    private float mThumbTextY;
    private float mThumbTouchRadius;
    private Drawable mTickMarksDrawable;
    private boolean mTickMarksEndsHide;
    private int mTickMarksSize;
    private boolean mTickMarksSweptHide;
    private float[] mTickMarksX;
    private float mTickRadius;
    private float mTickTextY;
    private String[] mTickTextsArr;
    private CharSequence[] mTickTextsCustomArray;
    private int mTickTextsHeight;
    private int mTickTextsSize;
    private float[] mTickTextsWidth;
    private int mTicksCount;
    private boolean mTrackRoundedCorners;
    private int mUnSelectedTickMarksColor;
    private Bitmap mUnselectTickMarksBitmap;
    private int mUnselectedTextsColor;
    private boolean mUserSeekable;

    public IndicatorSeekBar(Context context) {
        this(context, null);
    }

    public IndicatorSeekBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mFaultTolerance = -1.0f;
        this.mScreenWidth = -1.0f;
        this.mScale = 1;
        this.mContext = context;
        initAttrs(this.mContext, attributeSet);
        initParams();
    }

    @RequiresApi(api = 21)
    public IndicatorSeekBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mFaultTolerance = -1.0f;
        this.mScreenWidth = -1.0f;
        this.mScale = 1;
        this.mContext = context;
        initAttrs(this.mContext, attributeSet);
        initParams();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public IndicatorSeekBar(Builder builder) {
        super(builder.context);
        this.mFaultTolerance = -1.0f;
        this.mScreenWidth = -1.0f;
        this.mScale = 1;
        this.mContext = builder.context;
        int dp2px = SizeUtils.dp2px(this.mContext, 16.0f);
        setPadding(dp2px, getPaddingTop(), dp2px, getPaddingBottom());
        apply(builder);
        initParams();
    }

    private float adjustTouchX(MotionEvent motionEvent) {
        float x = motionEvent.getX();
        int i = this.mPaddingLeft;
        if (x >= i) {
            float x2 = motionEvent.getX();
            int i2 = this.mMeasuredWidth;
            int i3 = this.mPaddingRight;
            if (x2 <= i2 - i3) {
                return motionEvent.getX();
            }
            i = i2 - i3;
        }
        return i;
    }

    private void apply(Builder builder) {
        this.mMax = builder.max;
        this.mMin = builder.min;
        this.mProgress = builder.progress;
        this.mIsFloatProgress = builder.progressValueFloat;
        this.mTicksCount = builder.tickCount;
        this.mSeekSmoothly = builder.seekSmoothly;
        this.mR2L = builder.r2l;
        this.mUserSeekable = builder.userSeekable;
        this.mClearPadding = builder.clearPadding;
        this.mOnlyThumbDraggable = builder.onlyThumbDraggable;
        this.mShowIndicatorType = builder.showIndicatorType;
        this.mIndicatorColor = builder.indicatorColor;
        this.mIndicatorTextColor = builder.indicatorTextColor;
        this.mIndicatorTextSize = builder.indicatorTextSize;
        this.mIndicatorContentView = builder.indicatorContentView;
        this.mIndicatorTopContentView = builder.indicatorTopContentView;
        this.mBackgroundTrackSize = builder.trackBackgroundSize;
        this.mBackgroundTrackColor = builder.trackBackgroundColor;
        this.mProgressTrackSize = builder.trackProgressSize;
        this.mProgressTrackColor = builder.trackProgressColor;
        this.mTrackRoundedCorners = builder.trackRoundedCorners;
        this.mThumbSize = builder.thumbSize;
        this.mThumbDrawable = builder.thumbDrawable;
        this.mThumbTextColor = builder.thumbTextColor;
        initThumbColor(builder.thumbColorStateList, builder.thumbColor);
        this.mShowThumbText = builder.showThumbText;
        this.mShowTickMarksType = builder.showTickMarksType;
        this.mTickMarksSize = builder.tickMarksSize;
        this.mTickMarksDrawable = builder.tickMarksDrawable;
        this.mTickMarksEndsHide = builder.tickMarksEndsHide;
        this.mTickMarksSweptHide = builder.tickMarksSweptHide;
        initTickMarksColor(builder.tickMarksColorStateList, builder.tickMarksColor);
        this.mShowTickText = builder.showTickText;
        this.mTickTextsSize = builder.tickTextsSize;
        this.mTickTextsCustomArray = builder.tickTextsCustomArray;
        this.mTextsTypeface = builder.tickTextsTypeFace;
        initTickTextsColor(builder.tickTextsColorStateList, builder.tickTextsColor);
    }

    private boolean autoAdjustThumb() {
        if (this.mTicksCount < 3 || !this.mSeekSmoothly || !this.mAdjustAuto) {
            return false;
        }
        final int closestIndex = getClosestIndex();
        final float f = this.mProgress;
        ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, Math.abs(f - this.mProgressArr[closestIndex]));
        ofFloat.start();
        ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.warkiz.widget.IndicatorSeekBar.2
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                IndicatorSeekBar indicatorSeekBar = IndicatorSeekBar.this;
                indicatorSeekBar.lastProgress = indicatorSeekBar.mProgress;
                if (f - IndicatorSeekBar.this.mProgressArr[closestIndex] > 0.0f) {
                    IndicatorSeekBar.this.mProgress = f - ((Float) valueAnimator.getAnimatedValue()).floatValue();
                } else {
                    IndicatorSeekBar.this.mProgress = f + ((Float) valueAnimator.getAnimatedValue()).floatValue();
                }
                IndicatorSeekBar indicatorSeekBar2 = IndicatorSeekBar.this;
                indicatorSeekBar2.refreshThumbCenterXByProgress(indicatorSeekBar2.mProgress);
                IndicatorSeekBar.this.setSeekListener(false);
                if (IndicatorSeekBar.this.mIndicator != null && IndicatorSeekBar.this.mIndicatorStayAlways) {
                    IndicatorSeekBar.this.mIndicator.refreshProgressText();
                    IndicatorSeekBar.this.updateStayIndicator();
                }
                IndicatorSeekBar.this.invalidate();
            }
        });
        return true;
    }

    private float calculateProgress(float f) {
        this.lastProgress = this.mProgress;
        this.mProgress = this.mMin + ((getAmplitude() * (f - this.mPaddingLeft)) / this.mSeekLength);
        return this.mProgress;
    }

    private float calculateTouchX(float f) {
        if (this.mTicksCount > 2 && !this.mSeekSmoothly) {
            f = this.mPaddingLeft + (this.mSeekBlockLength * Math.round((f - this.mPaddingLeft) / this.mSeekBlockLength));
        }
        return this.mR2L ? (this.mSeekLength - f) + (this.mPaddingLeft * 2) : f;
    }

    private SeekParams collectParams(boolean z) {
        String[] strArr;
        if (this.mSeekParams == null) {
            this.mSeekParams = new SeekParams(this);
        }
        this.mSeekParams.progress = getProgress();
        this.mSeekParams.progressFloat = getProgressFloat();
        this.mSeekParams.fromUser = z;
        if (this.mTicksCount > 2) {
            int thumbPosOnTick = getThumbPosOnTick();
            if (this.mShowTickText && (strArr = this.mTickTextsArr) != null) {
                this.mSeekParams.tickText = strArr[thumbPosOnTick];
            }
            if (this.mR2L) {
                this.mSeekParams.thumbPosition = (this.mTicksCount - thumbPosOnTick) - 1;
            } else {
                this.mSeekParams.thumbPosition = thumbPosOnTick;
            }
        }
        return this.mSeekParams;
    }

    private void collectTicksInfo() {
        int i = this.mTicksCount;
        if (i < 0 || i > 50) {
            throw new IllegalArgumentException("the Argument: TICK COUNT must be limited between (0-50), Now is " + this.mTicksCount);
        }
        if (i == 0) {
            return;
        }
        this.mTickMarksX = new float[i];
        if (this.mShowTickText) {
            this.mTextCenterX = new float[i];
            this.mTickTextsWidth = new float[i];
        }
        this.mProgressArr = new float[this.mTicksCount];
        int i2 = 0;
        while (true) {
            float[] fArr = this.mProgressArr;
            if (i2 >= fArr.length) {
                return;
            }
            float f = this.mMin;
            fArr[i2] = f + ((i2 * (this.mMax - f)) / (this.mTicksCount + (-1) > 0 ? r4 - 1 : 1));
            i2++;
        }
    }

    private void drawThumb(Canvas canvas) {
        if (this.mHideThumb) {
            return;
        }
        float thumbCenterX = getThumbCenterX();
        if (this.mThumbDrawable == null) {
            if (this.mIsTouching) {
                this.mStockPaint.setColor(this.mPressedThumbColor);
            } else {
                this.mStockPaint.setColor(this.mThumbColor);
            }
            canvas.drawCircle(thumbCenterX, this.mProgressTrack.top, this.mIsTouching ? this.mThumbTouchRadius : this.mThumbRadius, this.mStockPaint);
            return;
        }
        if (this.mThumbBitmap == null || this.mPressedThumbBitmap == null) {
            initThumbBitmap();
        }
        if (this.mThumbBitmap == null || this.mPressedThumbBitmap == null) {
            throw new IllegalArgumentException("the format of the selector thumb drawable is wrong!");
        }
        this.mStockPaint.setAlpha(255);
        if (this.mIsTouching) {
            canvas.drawBitmap(this.mPressedThumbBitmap, thumbCenterX - (r1.getWidth() / 2.0f), this.mProgressTrack.top - (this.mPressedThumbBitmap.getHeight() / 2.0f), this.mStockPaint);
        } else {
            canvas.drawBitmap(this.mThumbBitmap, thumbCenterX - (r1.getWidth() / 2.0f), this.mProgressTrack.top - (this.mThumbBitmap.getHeight() / 2.0f), this.mStockPaint);
        }
    }

    private void drawThumbText(Canvas canvas) {
        if (this.mShowThumbText) {
            if (!this.mShowTickText || this.mTicksCount <= 2) {
                this.mTextPaint.setColor(this.mThumbTextColor);
                canvas.drawText(getProgressString(this.mProgress), getThumbCenterX(), this.mThumbTextY, this.mTextPaint);
            }
        }
    }

    private void drawTickMarks(Canvas canvas) {
        Bitmap bitmap;
        if (this.mTicksCount != 0) {
            if (this.mShowTickMarksType == 0 && this.mTickMarksDrawable == null) {
                return;
            }
            float thumbCenterX = getThumbCenterX();
            for (int i = 0; i < this.mTickMarksX.length; i++) {
                float thumbPosOnTickFloat = getThumbPosOnTickFloat();
                if ((!this.mTickMarksSweptHide || thumbCenterX < this.mTickMarksX[i]) && ((!this.mTickMarksEndsHide || (i != 0 && i != this.mTickMarksX.length - 1)) && (i != getThumbPosOnTick() || this.mTicksCount <= 2 || this.mSeekSmoothly))) {
                    float f = i;
                    if (f <= thumbPosOnTickFloat) {
                        this.mStockPaint.setColor(getLeftSideTickColor());
                    } else {
                        this.mStockPaint.setColor(getRightSideTickColor());
                    }
                    if (this.mTickMarksDrawable != null) {
                        if (this.mSelectTickMarksBitmap == null || this.mUnselectTickMarksBitmap == null) {
                            initTickMarksBitmap();
                        }
                        Bitmap bitmap2 = this.mSelectTickMarksBitmap;
                        if (bitmap2 == null || (bitmap = this.mUnselectTickMarksBitmap) == null) {
                            throw new IllegalArgumentException("the format of the selector TickMarks drawable is wrong!");
                        }
                        if (f <= thumbPosOnTickFloat) {
                            canvas.drawBitmap(bitmap2, this.mTickMarksX[i] - (bitmap.getWidth() / 2.0f), this.mProgressTrack.top - (this.mUnselectTickMarksBitmap.getHeight() / 2.0f), this.mStockPaint);
                        } else {
                            canvas.drawBitmap(bitmap, this.mTickMarksX[i] - (bitmap.getWidth() / 2.0f), this.mProgressTrack.top - (this.mUnselectTickMarksBitmap.getHeight() / 2.0f), this.mStockPaint);
                        }
                    } else {
                        int i2 = this.mShowTickMarksType;
                        if (i2 == 1) {
                            canvas.drawCircle(this.mTickMarksX[i], this.mProgressTrack.top, this.mTickRadius, this.mStockPaint);
                        } else if (i2 == 3) {
                            float dp2px = SizeUtils.dp2px(this.mContext, 1.0f);
                            float leftSideTrackSize = (thumbCenterX >= this.mTickMarksX[i] ? getLeftSideTrackSize() : getRightSideTrackSize()) / 2.0f;
                            canvas.drawRect(this.mTickMarksX[i] - dp2px, this.mProgressTrack.top - leftSideTrackSize, this.mTickMarksX[i] + dp2px, this.mProgressTrack.top + leftSideTrackSize, this.mStockPaint);
                        } else if (i2 == 2) {
                            float f2 = this.mTickMarksX[i] - (this.mTickMarksSize / 2.0f);
                            float f3 = this.mProgressTrack.top;
                            int i3 = this.mTickMarksSize;
                            canvas.drawRect(f2, f3 - (i3 / 2.0f), this.mTickMarksX[i] + (i3 / 2.0f), this.mProgressTrack.top + (this.mTickMarksSize / 2.0f), this.mStockPaint);
                        }
                    }
                }
            }
        }
    }

    private void drawTickTexts(Canvas canvas) {
        if (this.mTickTextsArr == null) {
            return;
        }
        float thumbPosOnTickFloat = getThumbPosOnTickFloat();
        int i = 0;
        while (true) {
            if (i >= this.mTickTextsArr.length) {
                return;
            }
            if (!this.mShowBothTickTextsOnly || i == 0 || i == r2.length - 1) {
                if (i == getThumbPosOnTick() && i == thumbPosOnTickFloat) {
                    this.mTextPaint.setColor(this.mHoveredTextColor);
                } else if (i < thumbPosOnTickFloat) {
                    this.mTextPaint.setColor(getLeftSideTickTextsColor());
                } else {
                    this.mTextPaint.setColor(getRightSideTickTextsColor());
                }
                int length = this.mR2L ? (this.mTickTextsArr.length - i) - 1 : i;
                if (i == 0) {
                    canvas.drawText(this.mTickTextsArr[length], this.mTextCenterX[i] + (this.mTickTextsWidth[length] / 2.0f), this.mTickTextY, this.mTextPaint);
                } else {
                    String[] strArr = this.mTickTextsArr;
                    if (i == strArr.length - 1) {
                        canvas.drawText(strArr[length], this.mTextCenterX[i] - (this.mTickTextsWidth[length] / 2.0f), this.mTickTextY, this.mTextPaint);
                    } else {
                        canvas.drawText(strArr[length], this.mTextCenterX[i], this.mTickTextY, this.mTextPaint);
                    }
                }
            }
            i++;
        }
    }

    private void drawTrack(Canvas canvas) {
        if (!this.mCustomTrackSectionColorResult) {
            this.mStockPaint.setColor(this.mProgressTrackColor);
            this.mStockPaint.setStrokeWidth(this.mProgressTrackSize);
            canvas.drawLine(this.mProgressTrack.left, this.mProgressTrack.top, this.mProgressTrack.right, this.mProgressTrack.bottom, this.mStockPaint);
            this.mStockPaint.setColor(this.mBackgroundTrackColor);
            this.mStockPaint.setStrokeWidth(this.mBackgroundTrackSize);
            canvas.drawLine(this.mBackgroundTrack.left, this.mBackgroundTrack.top, this.mBackgroundTrack.right, this.mBackgroundTrack.bottom, this.mStockPaint);
            return;
        }
        int i = this.mTicksCount;
        int i2 = i + (-1) > 0 ? i - 1 : 1;
        for (int i3 = 0; i3 < i2; i3++) {
            if (this.mR2L) {
                this.mStockPaint.setColor(this.mSectionTrackColorArray[(i2 - i3) - 1]);
            } else {
                this.mStockPaint.setColor(this.mSectionTrackColorArray[i3]);
            }
            float thumbPosOnTickFloat = getThumbPosOnTickFloat();
            float f = i3;
            if (f < thumbPosOnTickFloat) {
                int i4 = i3 + 1;
                if (thumbPosOnTickFloat < i4) {
                    float thumbCenterX = getThumbCenterX();
                    this.mStockPaint.setStrokeWidth(getLeftSideTrackSize());
                    canvas.drawLine(this.mTickMarksX[i3], this.mProgressTrack.top, thumbCenterX, this.mProgressTrack.bottom, this.mStockPaint);
                    this.mStockPaint.setStrokeWidth(getRightSideTrackSize());
                    canvas.drawLine(thumbCenterX, this.mProgressTrack.top, this.mTickMarksX[i4], this.mProgressTrack.bottom, this.mStockPaint);
                }
            }
            if (f < thumbPosOnTickFloat) {
                this.mStockPaint.setStrokeWidth(getLeftSideTrackSize());
            } else {
                this.mStockPaint.setStrokeWidth(getRightSideTrackSize());
            }
            canvas.drawLine(this.mTickMarksX[i3], this.mProgressTrack.top, this.mTickMarksX[i3 + 1], this.mProgressTrack.bottom, this.mStockPaint);
        }
    }

    private float getAmplitude() {
        float f = this.mMax;
        float f2 = this.mMin;
        if (f - f2 > 0.0f) {
            return f - f2;
        }
        return 1.0f;
    }

    private int getClosestIndex() {
        int i = 0;
        float abs = Math.abs(this.mMax - this.mMin);
        int i2 = 0;
        while (true) {
            float[] fArr = this.mProgressArr;
            if (i >= fArr.length) {
                return i2;
            }
            float abs2 = Math.abs(fArr[i] - this.mProgress);
            if (abs2 <= abs) {
                i2 = i;
                abs = abs2;
            }
            i++;
        }
    }

    private Bitmap getDrawBitmap(Drawable drawable, boolean z) {
        int intrinsicHeight;
        if (drawable == null) {
            return null;
        }
        int dp2px = SizeUtils.dp2px(this.mContext, 30.0f);
        if (drawable.getIntrinsicWidth() > dp2px) {
            int i = z ? this.mThumbSize : this.mTickMarksSize;
            intrinsicHeight = getHeightByRatio(drawable, i);
            if (i > dp2px) {
                intrinsicHeight = getHeightByRatio(drawable, dp2px);
            } else {
                dp2px = i;
            }
        } else {
            dp2px = drawable.getIntrinsicWidth();
            intrinsicHeight = drawable.getIntrinsicHeight();
        }
        Bitmap createBitmap = Bitmap.createBitmap(dp2px, intrinsicHeight, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return createBitmap;
    }

    private int getHeightByRatio(Drawable drawable, int i) {
        return Math.round(((i * 1.0f) * drawable.getIntrinsicHeight()) / drawable.getIntrinsicWidth());
    }

    private int getLeftSideTickColor() {
        return this.mR2L ? this.mUnSelectedTickMarksColor : this.mSelectedTickMarksColor;
    }

    private int getLeftSideTickTextsColor() {
        return this.mR2L ? this.mUnselectedTextsColor : this.mSelectedTextsColor;
    }

    private int getLeftSideTrackSize() {
        return this.mR2L ? this.mBackgroundTrackSize : this.mProgressTrackSize;
    }

    private String getProgressString(float f) {
        return this.mIsFloatProgress ? FormatUtils.fastFormat(f, this.mScale) : String.valueOf(Math.round(f));
    }

    private int getRightSideTickColor() {
        return this.mR2L ? this.mSelectedTickMarksColor : this.mUnSelectedTickMarksColor;
    }

    private int getRightSideTickTextsColor() {
        return this.mR2L ? this.mSelectedTextsColor : this.mUnselectedTextsColor;
    }

    private int getRightSideTrackSize() {
        return this.mR2L ? this.mProgressTrackSize : this.mBackgroundTrackSize;
    }

    private float getThumbCenterX() {
        return this.mR2L ? this.mBackgroundTrack.right : this.mProgressTrack.right;
    }

    private int getThumbPosOnTick() {
        if (this.mTicksCount != 0) {
            return Math.round((getThumbCenterX() - this.mPaddingLeft) / this.mSeekBlockLength);
        }
        return 0;
    }

    private float getThumbPosOnTickFloat() {
        if (this.mTicksCount != 0) {
            return (getThumbCenterX() - this.mPaddingLeft) / this.mSeekBlockLength;
        }
        return 0.0f;
    }

    private String getTickTextByPosition(int i) {
        CharSequence[] charSequenceArr = this.mTickTextsCustomArray;
        return charSequenceArr == null ? getProgressString(this.mProgressArr[i]) : i < charSequenceArr.length ? String.valueOf(charSequenceArr[i]) : "";
    }

    private void initAttrs(Context context, AttributeSet attributeSet) {
        Builder builder = new Builder(context);
        if (attributeSet == null) {
            apply(builder);
            return;
        }
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.IndicatorSeekBar);
        this.mMax = obtainStyledAttributes.getFloat(R.styleable.IndicatorSeekBar_isb_max, builder.max);
        this.mMin = obtainStyledAttributes.getFloat(R.styleable.IndicatorSeekBar_isb_min, builder.min);
        this.mProgress = obtainStyledAttributes.getFloat(R.styleable.IndicatorSeekBar_isb_progress, builder.progress);
        this.mIsFloatProgress = obtainStyledAttributes.getBoolean(R.styleable.IndicatorSeekBar_isb_progress_value_float, builder.progressValueFloat);
        this.mUserSeekable = obtainStyledAttributes.getBoolean(R.styleable.IndicatorSeekBar_isb_user_seekable, builder.userSeekable);
        this.mClearPadding = obtainStyledAttributes.getBoolean(R.styleable.IndicatorSeekBar_isb_clear_default_padding, builder.clearPadding);
        this.mOnlyThumbDraggable = obtainStyledAttributes.getBoolean(R.styleable.IndicatorSeekBar_isb_only_thumb_draggable, builder.onlyThumbDraggable);
        this.mSeekSmoothly = obtainStyledAttributes.getBoolean(R.styleable.IndicatorSeekBar_isb_seek_smoothly, builder.seekSmoothly);
        this.mR2L = obtainStyledAttributes.getBoolean(R.styleable.IndicatorSeekBar_isb_r2l, builder.r2l);
        this.mBackgroundTrackSize = obtainStyledAttributes.getDimensionPixelSize(R.styleable.IndicatorSeekBar_isb_track_background_size, builder.trackBackgroundSize);
        this.mProgressTrackSize = obtainStyledAttributes.getDimensionPixelSize(R.styleable.IndicatorSeekBar_isb_track_progress_size, builder.trackProgressSize);
        this.mBackgroundTrackColor = obtainStyledAttributes.getColor(R.styleable.IndicatorSeekBar_isb_track_background_color, builder.trackBackgroundColor);
        this.mProgressTrackColor = obtainStyledAttributes.getColor(R.styleable.IndicatorSeekBar_isb_track_progress_color, builder.trackProgressColor);
        this.mTrackRoundedCorners = obtainStyledAttributes.getBoolean(R.styleable.IndicatorSeekBar_isb_track_rounded_corners, builder.trackRoundedCorners);
        this.mThumbSize = obtainStyledAttributes.getDimensionPixelSize(R.styleable.IndicatorSeekBar_isb_thumb_size, builder.thumbSize);
        this.mThumbDrawable = obtainStyledAttributes.getDrawable(R.styleable.IndicatorSeekBar_isb_thumb_drawable);
        this.mAdjustAuto = obtainStyledAttributes.getBoolean(R.styleable.IndicatorSeekBar_isb_thumb_adjust_auto, true);
        initThumbColor(obtainStyledAttributes.getColorStateList(R.styleable.IndicatorSeekBar_isb_thumb_color), builder.thumbColor);
        this.mShowThumbText = obtainStyledAttributes.getBoolean(R.styleable.IndicatorSeekBar_isb_show_thumb_text, builder.showThumbText);
        this.mThumbTextColor = obtainStyledAttributes.getColor(R.styleable.IndicatorSeekBar_isb_thumb_text_color, builder.thumbTextColor);
        this.mTicksCount = obtainStyledAttributes.getInt(R.styleable.IndicatorSeekBar_isb_ticks_count, builder.tickCount);
        this.mShowTickMarksType = obtainStyledAttributes.getInt(R.styleable.IndicatorSeekBar_isb_show_tick_marks_type, builder.showTickMarksType);
        this.mTickMarksSize = obtainStyledAttributes.getDimensionPixelSize(R.styleable.IndicatorSeekBar_isb_tick_marks_size, builder.tickMarksSize);
        initTickMarksColor(obtainStyledAttributes.getColorStateList(R.styleable.IndicatorSeekBar_isb_tick_marks_color), builder.tickMarksColor);
        this.mTickMarksDrawable = obtainStyledAttributes.getDrawable(R.styleable.IndicatorSeekBar_isb_tick_marks_drawable);
        this.mTickMarksSweptHide = obtainStyledAttributes.getBoolean(R.styleable.IndicatorSeekBar_isb_tick_marks_swept_hide, builder.tickMarksSweptHide);
        this.mTickMarksEndsHide = obtainStyledAttributes.getBoolean(R.styleable.IndicatorSeekBar_isb_tick_marks_ends_hide, builder.tickMarksEndsHide);
        this.mShowTickText = obtainStyledAttributes.getBoolean(R.styleable.IndicatorSeekBar_isb_show_tick_texts, builder.showTickText);
        this.mTickTextsSize = obtainStyledAttributes.getDimensionPixelSize(R.styleable.IndicatorSeekBar_isb_tick_texts_size, builder.tickTextsSize);
        initTickTextsColor(obtainStyledAttributes.getColorStateList(R.styleable.IndicatorSeekBar_isb_tick_texts_color), builder.tickTextsColor);
        this.mTickTextsCustomArray = obtainStyledAttributes.getTextArray(R.styleable.IndicatorSeekBar_isb_tick_texts_array);
        initTextsTypeface(obtainStyledAttributes.getInt(R.styleable.IndicatorSeekBar_isb_tick_texts_typeface, -1), builder.tickTextsTypeFace);
        this.mShowIndicatorType = obtainStyledAttributes.getInt(R.styleable.IndicatorSeekBar_isb_show_indicator, builder.showIndicatorType);
        this.mIndicatorColor = obtainStyledAttributes.getColor(R.styleable.IndicatorSeekBar_isb_indicator_color, builder.indicatorColor);
        this.mIndicatorTextSize = obtainStyledAttributes.getDimensionPixelSize(R.styleable.IndicatorSeekBar_isb_indicator_text_size, builder.indicatorTextSize);
        this.mIndicatorTextColor = obtainStyledAttributes.getColor(R.styleable.IndicatorSeekBar_isb_indicator_text_color, builder.indicatorTextColor);
        int resourceId = obtainStyledAttributes.getResourceId(R.styleable.IndicatorSeekBar_isb_indicator_content_layout, 0);
        if (resourceId > 0) {
            this.mIndicatorContentView = View.inflate(this.mContext, resourceId, null);
        }
        int resourceId2 = obtainStyledAttributes.getResourceId(R.styleable.IndicatorSeekBar_isb_indicator_top_content_layout, 0);
        if (resourceId2 > 0) {
            this.mIndicatorTopContentView = View.inflate(this.mContext, resourceId2, null);
        }
        obtainStyledAttributes.recycle();
    }

    private void initDefaultPadding() {
        if (this.mClearPadding) {
            return;
        }
        int dp2px = SizeUtils.dp2px(this.mContext, 16.0f);
        if (getPaddingLeft() == 0) {
            setPadding(dp2px, getPaddingTop(), getPaddingRight(), getPaddingBottom());
        }
        if (getPaddingRight() == 0) {
            setPadding(getPaddingLeft(), getPaddingTop(), dp2px, getPaddingBottom());
        }
    }

    private void initIndicatorContentView() {
        int i = this.mShowIndicatorType;
        if (i != 0 && this.mIndicator == null) {
            this.mIndicator = new Indicator(this.mContext, this, this.mIndicatorColor, i, this.mIndicatorTextSize, this.mIndicatorTextColor, this.mIndicatorContentView, this.mIndicatorTopContentView);
            this.mIndicatorContentView = this.mIndicator.getInsideContentView();
        }
    }

    private void initParams() {
        initProgressRangeValue();
        int i = this.mBackgroundTrackSize;
        int i2 = this.mProgressTrackSize;
        if (i > i2) {
            this.mBackgroundTrackSize = i2;
        }
        if (this.mThumbDrawable == null) {
            this.mThumbRadius = this.mThumbSize / 2.0f;
            this.mThumbTouchRadius = this.mThumbRadius * 1.2f;
        } else {
            this.mThumbRadius = Math.min(SizeUtils.dp2px(this.mContext, 30.0f), this.mThumbSize) / 2.0f;
            this.mThumbTouchRadius = this.mThumbRadius;
        }
        if (this.mTickMarksDrawable == null) {
            this.mTickRadius = this.mTickMarksSize / 2.0f;
        } else {
            this.mTickRadius = Math.min(SizeUtils.dp2px(this.mContext, 30.0f), this.mTickMarksSize) / 2.0f;
        }
        this.mCustomDrawableMaxHeight = Math.max(this.mThumbTouchRadius, this.mTickRadius) * 2.0f;
        initStrokePaint();
        measureTickTextsBonds();
        this.lastProgress = this.mProgress;
        collectTicksInfo();
        this.mProgressTrack = new RectF();
        this.mBackgroundTrack = new RectF();
        initDefaultPadding();
        initIndicatorContentView();
    }

    private void initProgressRangeValue() {
        float f = this.mMax;
        float f2 = this.mMin;
        if (f < f2) {
            throw new IllegalArgumentException("the Argument: MAX's value must be larger than MIN's.");
        }
        if (this.mProgress < f2) {
            this.mProgress = f2;
        }
        float f3 = this.mProgress;
        float f4 = this.mMax;
        if (f3 > f4) {
            this.mProgress = f4;
        }
    }

    private void initSeekBarInfo() {
        this.mMeasuredWidth = getMeasuredWidth();
        if (Build.VERSION.SDK_INT < 17) {
            this.mPaddingLeft = getPaddingLeft();
            this.mPaddingRight = getPaddingRight();
        } else {
            this.mPaddingLeft = getPaddingStart();
            this.mPaddingRight = getPaddingEnd();
        }
        this.mPaddingTop = getPaddingTop();
        this.mSeekLength = (this.mMeasuredWidth - this.mPaddingLeft) - this.mPaddingRight;
        this.mSeekBlockLength = this.mSeekLength / (this.mTicksCount + (-1) > 0 ? r1 - 1 : 1);
    }

    private void initStrokePaint() {
        if (this.mStockPaint == null) {
            this.mStockPaint = new Paint();
        }
        if (this.mTrackRoundedCorners) {
            this.mStockPaint.setStrokeCap(Paint.Cap.ROUND);
        }
        this.mStockPaint.setAntiAlias(true);
        int i = this.mBackgroundTrackSize;
        if (i > this.mProgressTrackSize) {
            this.mProgressTrackSize = i;
        }
    }

    private void initTextPaint() {
        if (this.mTextPaint == null) {
            this.mTextPaint = new TextPaint();
            this.mTextPaint.setAntiAlias(true);
            this.mTextPaint.setTextAlign(Paint.Align.CENTER);
            this.mTextPaint.setTextSize(this.mTickTextsSize);
        }
        if (this.mRect == null) {
            this.mRect = new Rect();
        }
    }

    private void initTextsArray() {
        int i = this.mTicksCount;
        if (i == 0) {
            return;
        }
        if (this.mShowTickText) {
            this.mTickTextsArr = new String[i];
        }
        for (int i2 = 0; i2 < this.mTickMarksX.length; i2++) {
            if (this.mShowTickText) {
                this.mTickTextsArr[i2] = getTickTextByPosition(i2);
                TextPaint textPaint = this.mTextPaint;
                String[] strArr = this.mTickTextsArr;
                textPaint.getTextBounds(strArr[i2], 0, strArr[i2].length(), this.mRect);
                this.mTickTextsWidth[i2] = this.mRect.width();
                this.mTextCenterX[i2] = this.mPaddingLeft + (this.mSeekBlockLength * i2);
            }
            this.mTickMarksX[i2] = this.mPaddingLeft + (this.mSeekBlockLength * i2);
        }
    }

    private void initTextsTypeface(int i, Typeface typeface) {
        if (i == 0) {
            this.mTextsTypeface = Typeface.DEFAULT;
            return;
        }
        if (i == 1) {
            this.mTextsTypeface = Typeface.MONOSPACE;
            return;
        }
        if (i == 2) {
            this.mTextsTypeface = Typeface.SANS_SERIF;
            return;
        }
        if (i == 3) {
            this.mTextsTypeface = Typeface.SERIF;
        } else if (typeface == null) {
            this.mTextsTypeface = Typeface.DEFAULT;
        } else {
            this.mTextsTypeface = typeface;
        }
    }

    private void initThumbBitmap() {
        Drawable drawable = this.mThumbDrawable;
        if (drawable == null) {
            return;
        }
        if (!(drawable instanceof StateListDrawable)) {
            this.mThumbBitmap = getDrawBitmap(drawable, true);
            this.mPressedThumbBitmap = this.mThumbBitmap;
            return;
        }
        try {
            StateListDrawable stateListDrawable = (StateListDrawable) drawable;
            Class<?> cls = stateListDrawable.getClass();
            int intValue = ((Integer) cls.getMethod("getStateCount", new Class[0]).invoke(stateListDrawable, new Object[0])).intValue();
            if (intValue != 2) {
                throw new IllegalArgumentException("the format of the selector thumb drawable is wrong!");
            }
            Method method = cls.getMethod("getStateSet", Integer.TYPE);
            Method method2 = cls.getMethod("getStateDrawable", Integer.TYPE);
            for (int i = 0; i < intValue; i++) {
                int[] iArr = (int[]) method.invoke(stateListDrawable, Integer.valueOf(i));
                if (iArr.length <= 0) {
                    this.mThumbBitmap = getDrawBitmap((Drawable) method2.invoke(stateListDrawable, Integer.valueOf(i)), true);
                } else {
                    if (iArr[0] != 16842919) {
                        throw new IllegalArgumentException("the state of the selector thumb drawable is wrong!");
                    }
                    this.mPressedThumbBitmap = getDrawBitmap((Drawable) method2.invoke(stateListDrawable, Integer.valueOf(i)), true);
                }
            }
        } catch (Exception unused) {
            this.mThumbBitmap = getDrawBitmap(this.mThumbDrawable, true);
            this.mPressedThumbBitmap = this.mThumbBitmap;
        }
    }

    private void initThumbColor(ColorStateList colorStateList, int i) {
        if (colorStateList == null) {
            this.mThumbColor = i;
            this.mPressedThumbColor = this.mThumbColor;
            return;
        }
        int[][] iArr = (int[][]) null;
        try {
            int[] iArr2 = null;
            for (Field field : colorStateList.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                if ("mStateSpecs".equals(field.getName())) {
                    iArr = (int[][]) field.get(colorStateList);
                }
                if ("mColors".equals(field.getName())) {
                    iArr2 = (int[]) field.get(colorStateList);
                }
            }
            if (iArr == null || iArr2 == null) {
                return;
            }
            if (iArr.length == 1) {
                this.mThumbColor = iArr2[0];
                this.mPressedThumbColor = this.mThumbColor;
                return;
            }
            if (iArr.length != 2) {
                throw new IllegalArgumentException("the selector color file you set for the argument: isb_thumb_color is in wrong format.");
            }
            for (int i2 = 0; i2 < iArr.length; i2++) {
                int[] iArr3 = iArr[i2];
                if (iArr3.length == 0) {
                    this.mPressedThumbColor = iArr2[i2];
                } else {
                    if (iArr3[0] != 16842919) {
                        throw new IllegalArgumentException("the selector color file you set for the argument: isb_thumb_color is in wrong format.");
                    }
                    this.mThumbColor = iArr2[i2];
                }
            }
        } catch (Exception unused) {
            throw new RuntimeException("Something wrong happened when parseing thumb selector color.");
        }
    }

    private void initTickMarksBitmap() {
        Drawable drawable = this.mTickMarksDrawable;
        if (!(drawable instanceof StateListDrawable)) {
            this.mUnselectTickMarksBitmap = getDrawBitmap(drawable, false);
            this.mSelectTickMarksBitmap = this.mUnselectTickMarksBitmap;
            return;
        }
        StateListDrawable stateListDrawable = (StateListDrawable) drawable;
        try {
            Class<?> cls = stateListDrawable.getClass();
            int intValue = ((Integer) cls.getMethod("getStateCount", new Class[0]).invoke(stateListDrawable, new Object[0])).intValue();
            if (intValue != 2) {
                throw new IllegalArgumentException("the format of the selector TickMarks drawable is wrong!");
            }
            Method method = cls.getMethod("getStateSet", Integer.TYPE);
            Method method2 = cls.getMethod("getStateDrawable", Integer.TYPE);
            for (int i = 0; i < intValue; i++) {
                int[] iArr = (int[]) method.invoke(stateListDrawable, Integer.valueOf(i));
                if (iArr.length <= 0) {
                    this.mUnselectTickMarksBitmap = getDrawBitmap((Drawable) method2.invoke(stateListDrawable, Integer.valueOf(i)), false);
                } else {
                    if (iArr[0] != 16842913) {
                        throw new IllegalArgumentException("the state of the selector TickMarks drawable is wrong!");
                    }
                    this.mSelectTickMarksBitmap = getDrawBitmap((Drawable) method2.invoke(stateListDrawable, Integer.valueOf(i)), false);
                }
            }
        } catch (Exception unused) {
            this.mUnselectTickMarksBitmap = getDrawBitmap(this.mTickMarksDrawable, false);
            this.mSelectTickMarksBitmap = this.mUnselectTickMarksBitmap;
        }
    }

    private void initTickMarksColor(ColorStateList colorStateList, int i) {
        if (colorStateList == null) {
            this.mSelectedTickMarksColor = i;
            this.mUnSelectedTickMarksColor = this.mSelectedTickMarksColor;
            return;
        }
        int[][] iArr = (int[][]) null;
        try {
            int[] iArr2 = null;
            for (Field field : colorStateList.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                if ("mStateSpecs".equals(field.getName())) {
                    iArr = (int[][]) field.get(colorStateList);
                }
                if ("mColors".equals(field.getName())) {
                    iArr2 = (int[]) field.get(colorStateList);
                }
            }
            if (iArr == null || iArr2 == null) {
                return;
            }
            if (iArr.length == 1) {
                this.mSelectedTickMarksColor = iArr2[0];
                this.mUnSelectedTickMarksColor = this.mSelectedTickMarksColor;
                return;
            }
            if (iArr.length != 2) {
                throw new IllegalArgumentException("the selector color file you set for the argument: isb_tick_marks_color is in wrong format.");
            }
            for (int i2 = 0; i2 < iArr.length; i2++) {
                int[] iArr3 = iArr[i2];
                if (iArr3.length == 0) {
                    this.mUnSelectedTickMarksColor = iArr2[i2];
                } else {
                    if (iArr3[0] != 16842913) {
                        throw new IllegalArgumentException("the selector color file you set for the argument: isb_tick_marks_color is in wrong format.");
                    }
                    this.mSelectedTickMarksColor = iArr2[i2];
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Something wrong happened when parsing thumb selector color." + e.getMessage());
        }
    }

    private void initTickTextsColor(ColorStateList colorStateList, int i) {
        if (colorStateList == null) {
            this.mUnselectedTextsColor = i;
            int i2 = this.mUnselectedTextsColor;
            this.mSelectedTextsColor = i2;
            this.mHoveredTextColor = i2;
            return;
        }
        int[][] iArr = (int[][]) null;
        try {
            int[] iArr2 = null;
            for (Field field : colorStateList.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                if ("mStateSpecs".equals(field.getName())) {
                    iArr = (int[][]) field.get(colorStateList);
                }
                if ("mColors".equals(field.getName())) {
                    iArr2 = (int[]) field.get(colorStateList);
                }
            }
            if (iArr == null || iArr2 == null) {
                return;
            }
            if (iArr.length == 1) {
                this.mUnselectedTextsColor = iArr2[0];
                int i3 = this.mUnselectedTextsColor;
                this.mSelectedTextsColor = i3;
                this.mHoveredTextColor = i3;
                return;
            }
            if (iArr.length != 3) {
                throw new IllegalArgumentException("the selector color file you set for the argument: isb_tick_texts_color is in wrong format.");
            }
            for (int i4 = 0; i4 < iArr.length; i4++) {
                int[] iArr3 = iArr[i4];
                if (iArr3.length == 0) {
                    this.mUnselectedTextsColor = iArr2[i4];
                } else {
                    int i5 = iArr3[0];
                    if (i5 == 16842913) {
                        this.mSelectedTextsColor = iArr2[i4];
                    } else {
                        if (i5 != 16843623) {
                            throw new IllegalArgumentException("the selector color file you set for the argument: isb_tick_texts_color is in wrong format.");
                        }
                        this.mHoveredTextColor = iArr2[i4];
                    }
                }
            }
        } catch (Exception unused) {
            throw new RuntimeException("Something wrong happened when parseing thumb selector color.");
        }
    }

    private void initTrackLocation() {
        if (!this.mR2L) {
            RectF rectF = this.mProgressTrack;
            rectF.left = this.mPaddingLeft;
            rectF.top = this.mPaddingTop + this.mThumbTouchRadius;
            rectF.right = (((this.mProgress - this.mMin) * this.mSeekLength) / getAmplitude()) + this.mPaddingLeft;
            RectF rectF2 = this.mProgressTrack;
            rectF2.bottom = rectF2.top;
            this.mBackgroundTrack.left = this.mProgressTrack.right;
            this.mBackgroundTrack.top = this.mProgressTrack.bottom;
            RectF rectF3 = this.mBackgroundTrack;
            rectF3.right = this.mMeasuredWidth - this.mPaddingRight;
            rectF3.bottom = this.mProgressTrack.bottom;
            return;
        }
        RectF rectF4 = this.mBackgroundTrack;
        int i = this.mPaddingLeft;
        rectF4.left = i;
        rectF4.top = this.mPaddingTop + this.mThumbTouchRadius;
        rectF4.right = i + (this.mSeekLength * (1.0f - ((this.mProgress - this.mMin) / getAmplitude())));
        RectF rectF5 = this.mBackgroundTrack;
        rectF5.bottom = rectF5.top;
        this.mProgressTrack.left = this.mBackgroundTrack.right;
        this.mProgressTrack.top = this.mBackgroundTrack.top;
        RectF rectF6 = this.mProgressTrack;
        rectF6.right = this.mMeasuredWidth - this.mPaddingRight;
        rectF6.bottom = this.mBackgroundTrack.bottom;
    }

    private boolean isTouchSeekBar(float f, float f2) {
        if (this.mFaultTolerance == -1.0f) {
            this.mFaultTolerance = SizeUtils.dp2px(this.mContext, 5.0f);
        }
        float f3 = this.mPaddingLeft;
        float f4 = this.mFaultTolerance;
        return ((f > (f3 - (f4 * 2.0f)) ? 1 : (f == (f3 - (f4 * 2.0f)) ? 0 : -1)) >= 0 && (f > (((float) (this.mMeasuredWidth - this.mPaddingRight)) + (f4 * 2.0f)) ? 1 : (f == (((float) (this.mMeasuredWidth - this.mPaddingRight)) + (f4 * 2.0f)) ? 0 : -1)) <= 0) && ((f2 > ((this.mProgressTrack.top - this.mThumbTouchRadius) - this.mFaultTolerance) ? 1 : (f2 == ((this.mProgressTrack.top - this.mThumbTouchRadius) - this.mFaultTolerance) ? 0 : -1)) >= 0 && (f2 > ((this.mProgressTrack.top + this.mThumbTouchRadius) + this.mFaultTolerance) ? 1 : (f2 == ((this.mProgressTrack.top + this.mThumbTouchRadius) + this.mFaultTolerance) ? 0 : -1)) <= 0);
    }

    private boolean isTouchThumb(float f) {
        refreshThumbCenterXByProgress(this.mProgress);
        float f2 = this.mR2L ? this.mBackgroundTrack.right : this.mProgressTrack.right;
        int i = this.mThumbSize;
        return f2 - (((float) i) / 2.0f) <= f && f <= f2 + (((float) i) / 2.0f);
    }

    private void measureTickTextsBonds() {
        if (needDrawText()) {
            initTextPaint();
            this.mTextPaint.setTypeface(this.mTextsTypeface);
            this.mTextPaint.getTextBounds("j", 0, 1, this.mRect);
            this.mTickTextsHeight = this.mRect.height() + SizeUtils.dp2px(this.mContext, 3.0f);
        }
    }

    private boolean needDrawText() {
        return this.mShowThumbText || (this.mTicksCount != 0 && this.mShowTickText);
    }

    private boolean progressChange() {
        return this.mIsFloatProgress ? this.lastProgress != this.mProgress : Math.round(this.lastProgress) != Math.round(this.mProgress);
    }

    private void refreshSeekBar(MotionEvent motionEvent) {
        refreshThumbCenterXByProgress(calculateProgress(calculateTouchX(adjustTouchX(motionEvent))));
        setSeekListener(true);
        invalidate();
        updateIndicator();
    }

    private void refreshSeekBarLocation() {
        initTrackLocation();
        if (needDrawText()) {
            this.mTextPaint.getTextBounds("j", 0, 1, this.mRect);
            this.mTickTextY = this.mPaddingTop + this.mCustomDrawableMaxHeight + Math.round(this.mRect.height() - this.mTextPaint.descent()) + SizeUtils.dp2px(this.mContext, 3.0f);
            this.mThumbTextY = this.mTickTextY;
        }
        if (this.mTickMarksX == null) {
            return;
        }
        initTextsArray();
        if (this.mTicksCount > 2) {
            this.mProgress = this.mProgressArr[getClosestIndex()];
            this.lastProgress = this.mProgress;
        }
        refreshThumbCenterXByProgress(this.mProgress);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshThumbCenterXByProgress(float f) {
        if (this.mR2L) {
            this.mBackgroundTrack.right = this.mPaddingLeft + (this.mSeekLength * (1.0f - ((f - this.mMin) / getAmplitude())));
            this.mProgressTrack.left = this.mBackgroundTrack.right;
            return;
        }
        this.mProgressTrack.right = (((f - this.mMin) * this.mSeekLength) / getAmplitude()) + this.mPaddingLeft;
        this.mBackgroundTrack.left = this.mProgressTrack.right;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSeekListener(boolean z) {
        if (this.mSeekChangeListener != null && progressChange()) {
            this.mSeekChangeListener.onSeeking(collectParams(z));
        }
    }

    private void updateIndicator() {
        if (this.mIndicatorStayAlways) {
            updateStayIndicator();
            return;
        }
        Indicator indicator = this.mIndicator;
        if (indicator == null) {
            return;
        }
        indicator.iniPop();
        if (this.mIndicator.isShowing()) {
            this.mIndicator.update(getThumbCenterX());
        } else {
            this.mIndicator.show(getThumbCenterX());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateStayIndicator() {
        Indicator indicator;
        int i;
        if (!this.mIndicatorStayAlways || (indicator = this.mIndicator) == null) {
            return;
        }
        indicator.setProgressTextView(getIndicatorTextString());
        int i2 = 0;
        this.mIndicatorContentView.measure(0, 0);
        int measuredWidth = this.mIndicatorContentView.getMeasuredWidth();
        float thumbCenterX = getThumbCenterX();
        if (this.mScreenWidth == -1.0f) {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            WindowManager windowManager = (WindowManager) this.mContext.getSystemService("window");
            if (windowManager != null) {
                windowManager.getDefaultDisplay().getMetrics(displayMetrics);
                this.mScreenWidth = displayMetrics.widthPixels;
            }
        }
        float f = measuredWidth / 2;
        float f2 = f + thumbCenterX;
        int i3 = this.mMeasuredWidth;
        if (f2 > i3) {
            i2 = i3 - measuredWidth;
            i = (int) ((thumbCenterX - i2) - f);
        } else if (thumbCenterX - f < 0.0f) {
            i = -((int) (f - thumbCenterX));
        } else {
            i2 = (int) (getThumbCenterX() - f);
            i = 0;
        }
        this.mIndicator.updateIndicatorLocation(i2);
        this.mIndicator.updateArrowViewLocation(i);
    }

    public static Builder with(@NonNull Context context) {
        return new Builder(context);
    }

    public void customSectionTrackColor(@NonNull ColorCollector colorCollector) {
        int i = this.mTicksCount;
        int[] iArr = new int[i + (-1) > 0 ? i - 1 : 1];
        for (int i2 = 0; i2 < iArr.length; i2++) {
            iArr[i2] = this.mBackgroundTrackColor;
        }
        this.mCustomTrackSectionColorResult = colorCollector.collectSectionTrackColor(iArr);
        this.mSectionTrackColorArray = iArr;
        invalidate();
    }

    public void customTickTexts(@NonNull String[] strArr) {
        this.mTickTextsCustomArray = strArr;
        if (this.mTickTextsArr != null) {
            int i = 0;
            while (i < this.mTickTextsArr.length) {
                String valueOf = i < strArr.length ? String.valueOf(strArr[i]) : "";
                int i2 = this.mR2L ? (this.mTicksCount - 1) - i : i;
                this.mTickTextsArr[i2] = valueOf;
                TextPaint textPaint = this.mTextPaint;
                if (textPaint != null && this.mRect != null) {
                    textPaint.getTextBounds(valueOf, 0, valueOf.length(), this.mRect);
                    this.mTickTextsWidth[i2] = this.mRect.width();
                }
                i++;
            }
            invalidate();
        }
    }

    public void customTickTextsTypeface(@NonNull Typeface typeface) {
        this.mTextsTypeface = typeface;
        measureTickTextsBonds();
        requestLayout();
        invalidate();
    }

    @Override // android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        ViewParent parent = getParent();
        if (parent == null) {
            return super.dispatchTouchEvent(motionEvent);
        }
        int action = motionEvent.getAction();
        if (action == 0) {
            parent.requestDisallowInterceptTouchEvent(true);
        } else if (action == 1 || action == 3) {
            parent.requestDisallowInterceptTouchEvent(false);
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    public Indicator getIndicator() {
        return this.mIndicator;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public View getIndicatorContentView() {
        return this.mIndicatorContentView;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getIndicatorTextString() {
        String[] strArr;
        String str = this.mIndicatorTextFormat;
        if (str == null || !str.contains(FORMAT_TICK_TEXT)) {
            String str2 = this.mIndicatorTextFormat;
            if (str2 != null && str2.contains(FORMAT_PROGRESS)) {
                return this.mIndicatorTextFormat.replace(FORMAT_PROGRESS, getProgressString(this.mProgress));
            }
        } else if (this.mTicksCount > 2 && (strArr = this.mTickTextsArr) != null) {
            return this.mIndicatorTextFormat.replace(FORMAT_TICK_TEXT, strArr[getThumbPosOnTick()]);
        }
        return getProgressString(this.mProgress);
    }

    public float getMax() {
        return this.mMax;
    }

    public float getMin() {
        return this.mMin;
    }

    public OnSeekChangeListener getOnSeekChangeListener() {
        return this.mSeekChangeListener;
    }

    public int getProgress() {
        return Math.round(this.mProgress);
    }

    public synchronized float getProgressFloat() {
        return BigDecimal.valueOf(this.mProgress).setScale(this.mScale, 4).floatValue();
    }

    public int getTickCount() {
        return this.mTicksCount;
    }

    public void hideThumb(boolean z) {
        this.mHideThumb = z;
        invalidate();
    }

    public void hideThumbText(boolean z) {
        this.mShowThumbText = !z;
        invalidate();
    }

    @Override // android.view.View
    protected synchronized void onDraw(Canvas canvas) {
        drawTrack(canvas);
        drawTickMarks(canvas);
        drawTickTexts(canvas);
        drawThumb(canvas);
        drawThumbText(canvas);
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        setMeasuredDimension(resolveSize(SizeUtils.dp2px(this.mContext, 170.0f), i), Math.round(this.mCustomDrawableMaxHeight + getPaddingTop() + getPaddingBottom()) + this.mTickTextsHeight);
        initSeekBarInfo();
        refreshSeekBarLocation();
    }

    @Override // android.view.View
    protected void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof Bundle)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        Bundle bundle = (Bundle) parcelable;
        setProgress(bundle.getFloat("isb_progress"));
        super.onRestoreInstanceState(bundle.getParcelable("isb_instance_state"));
    }

    @Override // android.view.View
    protected Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable("isb_instance_state", super.onSaveInstanceState());
        bundle.putFloat("isb_progress", this.mProgress);
        return bundle;
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        post(new Runnable() { // from class: com.warkiz.widget.IndicatorSeekBar.1
            @Override // java.lang.Runnable
            public void run() {
                IndicatorSeekBar.this.requestLayout();
            }
        });
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0019, code lost:
    
        if (r0 != 3) goto L37;
     */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean onTouchEvent(android.view.MotionEvent r5) {
        /*
            r4 = this;
            boolean r0 = r4.mUserSeekable
            r1 = 0
            if (r0 == 0) goto L68
            boolean r0 = r4.isEnabled()
            if (r0 != 0) goto Lc
            goto L68
        Lc:
            int r0 = r5.getAction()
            r2 = 1
            if (r0 == 0) goto L3a
            if (r0 == r2) goto L20
            r2 = 2
            if (r0 == r2) goto L1c
            r2 = 3
            if (r0 == r2) goto L20
            goto L63
        L1c:
            r4.refreshSeekBar(r5)
            goto L63
        L20:
            r4.mIsTouching = r1
            com.warkiz.widget.OnSeekChangeListener r0 = r4.mSeekChangeListener
            if (r0 == 0) goto L29
            r0.onStopTrackingTouch(r4)
        L29:
            boolean r0 = r4.autoAdjustThumb()
            if (r0 != 0) goto L32
            r4.invalidate()
        L32:
            com.warkiz.widget.Indicator r0 = r4.mIndicator
            if (r0 == 0) goto L63
            r0.hide()
            goto L63
        L3a:
            r4.performClick()
            float r0 = r5.getX()
            float r3 = r5.getY()
            boolean r3 = r4.isTouchSeekBar(r0, r3)
            if (r3 == 0) goto L63
            boolean r3 = r4.mOnlyThumbDraggable
            if (r3 == 0) goto L56
            boolean r0 = r4.isTouchThumb(r0)
            if (r0 != 0) goto L56
            return r1
        L56:
            r4.mIsTouching = r2
            com.warkiz.widget.OnSeekChangeListener r0 = r4.mSeekChangeListener
            if (r0 == 0) goto L5f
            r0.onStartTrackingTouch(r4)
        L5f:
            r4.refreshSeekBar(r5)
            return r2
        L63:
            boolean r5 = super.onTouchEvent(r5)
            return r5
        L68:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.warkiz.widget.IndicatorSeekBar.onTouchEvent(android.view.MotionEvent):boolean");
    }

    @Override // android.view.View
    public boolean performClick() {
        return super.performClick();
    }

    public void setDecimalScale(int i) {
        this.mScale = i;
    }

    @Override // android.view.View
    public void setEnabled(boolean z) {
        if (z == isEnabled()) {
            return;
        }
        super.setEnabled(z);
        if (isEnabled()) {
            setAlpha(1.0f);
            if (this.mIndicatorStayAlways) {
                this.mIndicatorContentView.setAlpha(1.0f);
                return;
            }
            return;
        }
        setAlpha(0.3f);
        if (this.mIndicatorStayAlways) {
            this.mIndicatorContentView.setAlpha(0.3f);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setIndicatorStayAlways(boolean z) {
        this.mIndicatorStayAlways = z;
    }

    public void setIndicatorTextFormat(String str) {
        this.mIndicatorTextFormat = str;
        initTextsArray();
        updateStayIndicator();
    }

    public synchronized void setMax(float f) {
        this.mMax = Math.max(this.mMin, f);
        initProgressRangeValue();
        collectTicksInfo();
        refreshSeekBarLocation();
        invalidate();
        updateStayIndicator();
    }

    public synchronized void setMin(float f) {
        this.mMin = Math.min(this.mMax, f);
        initProgressRangeValue();
        collectTicksInfo();
        refreshSeekBarLocation();
        invalidate();
        updateStayIndicator();
    }

    public void setOnSeekChangeListener(@NonNull OnSeekChangeListener onSeekChangeListener) {
        this.mSeekChangeListener = onSeekChangeListener;
    }

    public synchronized void setProgress(float f) {
        this.lastProgress = this.mProgress;
        if (f < this.mMin) {
            f = this.mMin;
        } else if (f > this.mMax) {
            f = this.mMax;
        }
        this.mProgress = f;
        if (!this.mSeekSmoothly && this.mTicksCount > 2) {
            this.mProgress = this.mProgressArr[getClosestIndex()];
        }
        setSeekListener(false);
        refreshThumbCenterXByProgress(this.mProgress);
        postInvalidate();
        updateStayIndicator();
    }

    public void setR2L(boolean z) {
        this.mR2L = z;
        requestLayout();
        invalidate();
        updateStayIndicator();
    }

    public void setThumbAdjustAuto(boolean z) {
        this.mAdjustAuto = z;
    }

    public void setThumbDrawable(Drawable drawable) {
        if (drawable == null) {
            this.mThumbDrawable = null;
            this.mThumbBitmap = null;
            this.mPressedThumbBitmap = null;
        } else {
            this.mThumbDrawable = drawable;
            this.mThumbRadius = Math.min(SizeUtils.dp2px(this.mContext, 30.0f), this.mThumbSize) / 2.0f;
            this.mThumbTouchRadius = this.mThumbRadius;
            this.mCustomDrawableMaxHeight = Math.max(this.mThumbTouchRadius, this.mTickRadius) * 2.0f;
            initThumbBitmap();
        }
        requestLayout();
        invalidate();
    }

    public synchronized void setTickCount(int i) {
        if (this.mTicksCount < 0 || this.mTicksCount > 50) {
            throw new IllegalArgumentException("the Argument: TICK COUNT must be limited between (0-50), Now is " + this.mTicksCount);
        }
        this.mTicksCount = i;
        collectTicksInfo();
        initTextsArray();
        initSeekBarInfo();
        refreshSeekBarLocation();
        invalidate();
        updateStayIndicator();
    }

    public void setTickMarksDrawable(Drawable drawable) {
        if (drawable == null) {
            this.mTickMarksDrawable = null;
            this.mUnselectTickMarksBitmap = null;
            this.mSelectTickMarksBitmap = null;
        } else {
            this.mTickMarksDrawable = drawable;
            this.mTickRadius = Math.min(SizeUtils.dp2px(this.mContext, 30.0f), this.mTickMarksSize) / 2.0f;
            this.mCustomDrawableMaxHeight = Math.max(this.mThumbTouchRadius, this.mTickRadius) * 2.0f;
            initTickMarksBitmap();
        }
        invalidate();
    }

    public void setUserSeekAble(boolean z) {
        this.mUserSeekable = z;
    }

    public void showBothEndsTickTextsOnly(boolean z) {
        this.mShowBothTickTextsOnly = z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void showStayIndicator() {
        this.mIndicatorContentView.setVisibility(4);
        postDelayed(new Runnable() { // from class: com.warkiz.widget.IndicatorSeekBar.3
            @Override // java.lang.Runnable
            public void run() {
                AlphaAnimation alphaAnimation = new AlphaAnimation(0.1f, 1.0f);
                alphaAnimation.setDuration(180L);
                IndicatorSeekBar.this.mIndicatorContentView.setAnimation(alphaAnimation);
                IndicatorSeekBar.this.updateStayIndicator();
                IndicatorSeekBar.this.mIndicatorContentView.setVisibility(0);
            }
        }, 300L);
    }

    public void thumbColor(@ColorInt int i) {
        this.mThumbColor = i;
        this.mPressedThumbColor = i;
        invalidate();
    }

    public void thumbColorStateList(@NonNull ColorStateList colorStateList) {
        initThumbColor(colorStateList, this.mThumbColor);
        invalidate();
    }

    public void tickMarksColor(@ColorInt int i) {
        this.mSelectedTickMarksColor = i;
        this.mUnSelectedTickMarksColor = i;
        invalidate();
    }

    public void tickMarksColor(@NonNull ColorStateList colorStateList) {
        initTickMarksColor(colorStateList, this.mSelectedTickMarksColor);
        invalidate();
    }

    public void tickTextsColor(@ColorInt int i) {
        this.mUnselectedTextsColor = i;
        this.mSelectedTextsColor = i;
        this.mHoveredTextColor = i;
        invalidate();
    }

    public void tickTextsColorStateList(@NonNull ColorStateList colorStateList) {
        initTickTextsColor(colorStateList, this.mSelectedTextsColor);
        invalidate();
    }
}
