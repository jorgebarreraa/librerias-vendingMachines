package com.shencoder.pagergridlayoutmanager;

import android.content.Context;
import android.graphics.PointF;
import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.annotation.CallSuper;
import androidx.annotation.FloatRange;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.recyclerview.widget.RecyclerView;
import com.yj.coffeemachines.Listeners.MultipleClicksListeners;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* loaded from: classes.dex */
public class PagerGridLayoutManager extends RecyclerView.LayoutManager implements RecyclerView.SmoothScroller.ScrollVectorProvider {
    static boolean DEBUG = false;
    public static final int HORIZONTAL = 0;
    public static final int NO_ITEM = -1;
    public static final int NO_PAGER_COUNT = 0;
    private static final String TAG = "PagerGridLayoutManager";
    public static final int VERTICAL = 1;
    private int diffHeight;
    private int diffWidth;
    private boolean isHandlingSlidingConflictsEnabled;

    @IntRange(from = 1)
    private int mColumns;
    private int mCurrentPagerIndex;
    private final Rect mEndSnapRect;
    private int mItemHeight;
    private int mItemHeightUsed;
    private int mItemWidth;
    private int mItemWidthUsed;
    protected final LayoutChunkResult mLayoutChunkResult;
    protected final LayoutState mLayoutState;
    private int mMaxScrollOnFlingDuration;
    private float mMillisecondPreInch;
    private int mOnePageSize;
    private int mOrientation;

    @Nullable
    private PagerChangedListener mPagerChangedListener;
    private int mPagerCount;
    private PagerGridSnapHelper mPagerGridSnapHelper;
    private RecyclerView mRecyclerView;
    private boolean mReverseLayout;

    @IntRange(from = 1)
    private int mRows;
    protected boolean mShouldReverseLayout;
    private final Rect mStartSnapRect;
    private final RecyclerView.OnChildAttachStateChangeListener onChildAttachStateChangeListener;
    private RecyclerView.OnItemTouchListener onItemTouchListener;

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes.dex */
    public static class LayoutChunkResult {
        protected int mConsumed;
        protected boolean mFinished;
        protected boolean mFocusable;
        protected boolean mIgnoreConsumed;

        protected LayoutChunkResult() {
        }

        protected void resetInternal() {
            this.mConsumed = 0;
            this.mFinished = false;
            this.mIgnoreConsumed = false;
            this.mFocusable = false;
        }
    }

    /* loaded from: classes.dex */
    public static class LayoutParams extends RecyclerView.LayoutParams {
        public LayoutParams(int i, int i2) {
            super(i, i2);
        }

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }

        public LayoutParams(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
        }

        public LayoutParams(RecyclerView.LayoutParams layoutParams) {
            super(layoutParams);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes.dex */
    public static class LayoutState {
        protected static final int LAYOUT_END = 1;
        protected static final int LAYOUT_START = -1;
        protected static final int SCROLLING_OFFSET_NaN = Integer.MIN_VALUE;
        protected int mAvailable;
        protected int mCurrentPosition;
        protected int mLastScrollDelta;
        protected int mLayoutDirection;
        protected final Rect mOffsetRect = new Rect();
        protected boolean mRecycle;
        protected int mScrollingOffset;
        protected int replenishDelta;

        protected LayoutState() {
        }

        protected int getNextPosition(int i, int i2, int i3, int i4, RecyclerView.State state) {
            int i5;
            int i6;
            int i7 = i3 * i4;
            if (i2 == 0 && (i5 = i % i7) != i7 - 1) {
                int i8 = i % i4;
                int i9 = i5 / i4;
                if (!(i9 == i3 - 1) && ((i6 = i + i4) < state.getItemCount() || i8 == i4 - 1)) {
                    return i6;
                }
                i -= i9 * i4;
            }
            return i + 1;
        }

        protected int getPrePosition(int i, int i2, int i3, int i4, RecyclerView.State state) {
            int i5;
            int i6 = i3 * i4;
            if (i2 != 0 || (i5 = i % i6) == 0) {
                return i - 1;
            }
            return i5 / i4 == 0 ? (i - 1) + ((i3 - 1) * i4) : i - i4;
        }

        protected boolean hasMore(RecyclerView.State state) {
            int i = this.mCurrentPosition;
            return i >= 0 && i < state.getItemCount();
        }

        protected View next(RecyclerView.Recycler recycler) {
            return recycler.getViewForPosition(this.mCurrentPosition);
        }

        protected void setOffsetRect(int i, int i2, int i3, int i4) {
            this.mOffsetRect.set(i, i2, i3, i4);
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    /* loaded from: classes.dex */
    public @interface Orientation {
    }

    /* loaded from: classes.dex */
    public interface PagerChangedListener {
        void onPagerCountChanged(@IntRange(from = 0) int i);

        void onPagerIndexSelected(@IntRange(from = -1) int i, @IntRange(from = -1) int i2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes.dex */
    public static class SavedState implements Parcelable {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() { // from class: com.shencoder.pagergridlayoutmanager.PagerGridLayoutManager.SavedState.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        };
        protected int mColumns;
        protected int mCurrentPagerIndex;
        protected int mOrientation;
        protected boolean mReverseLayout;
        protected int mRows;

        public SavedState() {
            this.mCurrentPagerIndex = -1;
            this.mReverseLayout = false;
        }

        protected SavedState(Parcel parcel) {
            this.mCurrentPagerIndex = -1;
            this.mReverseLayout = false;
            this.mOrientation = parcel.readInt();
            this.mRows = parcel.readInt();
            this.mColumns = parcel.readInt();
            this.mCurrentPagerIndex = parcel.readInt();
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public void readFromParcel(Parcel parcel) {
            this.mOrientation = parcel.readInt();
            this.mRows = parcel.readInt();
            this.mColumns = parcel.readInt();
            this.mCurrentPagerIndex = parcel.readInt();
        }

        @NonNull
        public String toString() {
            return "SavedState{mOrientation=" + this.mOrientation + ", mRows=" + this.mRows + ", mColumns=" + this.mColumns + ", mCurrentPagerIndex=" + this.mCurrentPagerIndex + '}';
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.mOrientation);
            parcel.writeInt(this.mRows);
            parcel.writeInt(this.mColumns);
            parcel.writeInt(this.mCurrentPagerIndex);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class SmoothScrollToPosition implements Runnable {

        @NonNull
        private final PagerGridLayoutManager mLayoutManager;
        private final int mPosition;

        @NonNull
        private final RecyclerView mRecyclerView;

        SmoothScrollToPosition(int i, @NonNull PagerGridLayoutManager pagerGridLayoutManager, @NonNull RecyclerView recyclerView) {
            this.mPosition = i;
            this.mLayoutManager = pagerGridLayoutManager;
            this.mRecyclerView = recyclerView;
        }

        @Override // java.lang.Runnable
        public void run() {
            PagerGridSmoothScroller pagerGridSmoothScroller = new PagerGridSmoothScroller(this.mRecyclerView, this.mLayoutManager);
            pagerGridSmoothScroller.setTargetPosition(this.mPosition);
            this.mLayoutManager.startSmoothScroll(pagerGridSmoothScroller);
        }
    }

    public PagerGridLayoutManager(@IntRange(from = 1) int i, @IntRange(from = 1) int i2) {
        this(i, i2, 0);
    }

    public PagerGridLayoutManager(@IntRange(from = 1) int i, @IntRange(from = 1) int i2, int i3) {
        this(i, i2, i3, false);
    }

    public PagerGridLayoutManager(@IntRange(from = 1) int i, @IntRange(from = 1) int i2, int i3, boolean z) {
        this.mOrientation = 0;
        this.mPagerCount = 0;
        this.mCurrentPagerIndex = -1;
        this.mStartSnapRect = new Rect();
        this.mEndSnapRect = new Rect();
        this.mReverseLayout = false;
        this.mShouldReverseLayout = false;
        this.diffWidth = 0;
        this.diffHeight = 0;
        this.isHandlingSlidingConflictsEnabled = true;
        this.mMillisecondPreInch = 100.0f;
        this.mMaxScrollOnFlingDuration = MultipleClicksListeners.MIN_CLICK_DELAY_TIME;
        this.onChildAttachStateChangeListener = new RecyclerView.OnChildAttachStateChangeListener() { // from class: com.shencoder.pagergridlayoutmanager.PagerGridLayoutManager.1
            @Override // androidx.recyclerview.widget.RecyclerView.OnChildAttachStateChangeListener
            public void onChildViewAttachedToWindow(@NonNull View view) {
                LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
                if (layoutParams.width != -1 || layoutParams.height != -1) {
                    throw new IllegalStateException("Item layout  must fill the whole PagerGridLayoutManager (use match_parent)");
                }
            }

            @Override // androidx.recyclerview.widget.RecyclerView.OnChildAttachStateChangeListener
            public void onChildViewDetachedFromWindow(@NonNull View view) {
            }
        };
        this.mLayoutState = createLayoutState();
        this.mLayoutChunkResult = createLayoutChunkResult();
        setRows(i);
        setColumns(i2);
        setOrientation(i3);
        setReverseLayout(z);
    }

    public PagerGridLayoutManager(@IntRange(from = 1) int i, @IntRange(from = 1) int i2, boolean z) {
        this(i, i2, 0, z);
    }

    private int calculateClipOffset(boolean z, int i) {
        if (getClipToPadding()) {
            return 0;
        }
        int i2 = this.mOnePageSize;
        if (i % i2 == (z ? 0 : i2 - 1)) {
            return getClipToPaddingSize();
        }
        return 0;
    }

    private void calculateOnePageSize() {
        this.mOnePageSize = this.mRows * this.mColumns;
    }

    private int computeScrollExtent(RecyclerView.State state) {
        if (getChildCount() == 0 || state.getItemCount() == 0) {
            return 0;
        }
        int end = getEnd();
        if (DEBUG) {
            Log.i(TAG, "computeScrollExtent: " + end);
        }
        return end;
    }

    private int computeScrollOffset(RecyclerView.State state) {
        int i;
        int i2 = 0;
        if (getChildCount() != 0 && state.getItemCount() != 0) {
            View childAt = getChildAt(0);
            if (childAt == null) {
                return 0;
            }
            int position = getPosition(childAt);
            float end = getEnd() / (this.mOrientation == 0 ? this.mColumns : this.mRows);
            if (this.mOrientation == 0) {
                int pagerIndexByPosition = getPagerIndexByPosition(position);
                int i3 = this.mColumns;
                i = (pagerIndexByPosition * i3) + (position % i3);
            } else {
                i = position / this.mColumns;
            }
            i2 = shouldHorizontallyReverseLayout() ? (computeScrollRange(state) - computeScrollExtent(state)) - Math.round((i * end) + (getDecoratedEnd(childAt) - getEndAfterPadding())) : Math.round((i * end) + (getStartAfterPadding() - getDecoratedStart(childAt)));
            if (DEBUG) {
                Log.i(TAG, "computeScrollOffset: " + i2);
            }
        }
        return i2;
    }

    private int computeScrollRange(RecyclerView.State state) {
        if (getChildCount() == 0 || state.getItemCount() == 0) {
            return 0;
        }
        int max = Math.max(this.mPagerCount, 0) * getEnd();
        if (DEBUG) {
            Log.i(TAG, "computeScrollRange: " + max);
        }
        return max;
    }

    private int fill(RecyclerView.Recycler recycler, RecyclerView.State state) {
        LayoutState layoutState = this.mLayoutState;
        int i = layoutState.mAvailable;
        LayoutChunkResult layoutChunkResult = this.mLayoutChunkResult;
        for (int i2 = layoutState.mAvailable; i2 > 0 && layoutState.hasMore(state); i2 -= layoutChunkResult.mConsumed) {
            if (this.mShouldReverseLayout) {
                reverseLayoutChunk(recycler, state, layoutState, layoutChunkResult);
            } else {
                layoutChunk(recycler, state, layoutState, layoutChunkResult);
            }
            layoutState.mAvailable -= layoutChunkResult.mConsumed;
        }
        boolean z = layoutState.mLayoutDirection == 1;
        while (layoutState.hasMore(state)) {
            if (z ? isNeedMoveToNextSpan(layoutState.mCurrentPosition) : isNeedMoveToPreSpan(layoutState.mCurrentPosition)) {
                break;
            }
            if (this.mShouldReverseLayout) {
                reverseLayoutChunk(recycler, state, layoutState, layoutChunkResult);
            } else {
                layoutChunk(recycler, state, layoutState, layoutChunkResult);
            }
        }
        recycleViews(recycler);
        return i - layoutState.mAvailable;
    }

    private View getChildClosestToEnd() {
        return getChildAt(getChildCount() - 1);
    }

    private View getChildClosestToStart() {
        return getChildAt(0);
    }

    private int getClipToPaddingSize() {
        int paddingTop;
        int paddingBottom;
        if (this.mOrientation == 0) {
            paddingTop = getPaddingStart();
            paddingBottom = getPaddingEnd();
        } else {
            paddingTop = getPaddingTop();
            paddingBottom = getPaddingBottom();
        }
        return paddingTop + paddingBottom;
    }

    private int getDecoratedEnd(View view) {
        int decoratedBottom;
        int i;
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        if (this.mOrientation == 0) {
            decoratedBottom = getDecoratedRight(view);
            i = layoutParams.rightMargin;
        } else {
            decoratedBottom = getDecoratedBottom(view);
            i = layoutParams.bottomMargin;
        }
        return decoratedBottom + i;
    }

    private int getDecoratedStart(View view) {
        int decoratedTop;
        int i;
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        if (this.mOrientation == 0) {
            decoratedTop = getDecoratedLeft(view);
            i = layoutParams.leftMargin;
        } else {
            decoratedTop = getDecoratedTop(view);
            i = layoutParams.topMargin;
        }
        return decoratedTop - i;
    }

    private int getEnd() {
        return this.mOrientation == 0 ? getRealWidth() : getRealHeight();
    }

    private int getEndAfterPadding() {
        int height;
        int paddingBottom;
        if (this.mOrientation == 0) {
            height = getWidth();
            paddingBottom = getPaddingEnd();
        } else {
            height = getHeight();
            paddingBottom = getPaddingBottom();
        }
        return height - paddingBottom;
    }

    private int getPositionByPagerIndex(int i, boolean z) {
        if (z) {
            return i * this.mOnePageSize;
        }
        int i2 = this.mOnePageSize;
        return ((i * i2) + i2) - 1;
    }

    private int getRealHeight() {
        return (getHeight() - getPaddingTop()) - getPaddingBottom();
    }

    private int getRealWidth() {
        return (getWidth() - getPaddingStart()) - getPaddingEnd();
    }

    private int getStartAfterPadding() {
        return this.mOrientation == 0 ? getPaddingStart() : getPaddingTop();
    }

    private boolean isIdle() {
        RecyclerView recyclerView = this.mRecyclerView;
        return recyclerView == null || recyclerView.getScrollState() == 0;
    }

    private boolean isInScrollingContainer(View view) {
        for (ViewParent parent = view.getParent(); parent instanceof ViewGroup; parent = parent.getParent()) {
            if (((ViewGroup) parent).shouldDelayChildPressedState()) {
                return true;
            }
        }
        return false;
    }

    private boolean isNeedMoveToNextSpan(int i) {
        return this.mOrientation == 0 ? (i % this.mOnePageSize) / this.mColumns == 0 : i % this.mColumns == 0;
    }

    private boolean isNeedMoveToPreSpan(int i) {
        if (this.mOrientation == 0) {
            return (i % this.mOnePageSize) / this.mColumns == this.mRows - 1;
        }
        int i2 = this.mColumns;
        return i % i2 == i2 - 1;
    }

    private void layoutChunk(RecyclerView.Recycler recycler, RecyclerView.State state, LayoutState layoutState, LayoutChunkResult layoutChunkResult) {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        boolean z = layoutState.mLayoutDirection == 1;
        int i11 = layoutState.mCurrentPosition;
        View next = layoutState.next(recycler);
        if (z) {
            addView(next);
        } else {
            addView(next, 0);
        }
        layoutState.mCurrentPosition = z ? layoutState.getNextPosition(i11, this.mOrientation, this.mRows, this.mColumns, state) : layoutState.getPrePosition(i11, this.mOrientation, this.mRows, this.mColumns, state);
        measureChildWithMargins(next, this.mItemWidthUsed, this.mItemHeightUsed);
        boolean isNeedMoveToNextSpan = z ? isNeedMoveToNextSpan(i11) : isNeedMoveToPreSpan(i11);
        layoutChunkResult.mConsumed = isNeedMoveToNextSpan ? this.mOrientation == 0 ? this.mItemWidth : this.mItemHeight : 0;
        Rect rect = layoutState.mOffsetRect;
        if (this.mOrientation != 0) {
            if (z) {
                if (isNeedMoveToNextSpan) {
                    i = getPaddingStart();
                    i2 = rect.bottom + calculateClipOffset(true, i11);
                } else {
                    i = rect.left + this.mItemWidth;
                    i2 = rect.top;
                }
                i3 = this.mItemWidth + i;
                i4 = this.mItemHeight;
            } else if (isNeedMoveToNextSpan) {
                int width = getWidth() - getPaddingEnd();
                int i12 = width - this.mItemWidth;
                int calculateClipOffset = rect.top - calculateClipOffset(false, i11);
                i5 = width;
                i6 = calculateClipOffset;
                i7 = i12;
                i8 = calculateClipOffset - this.mItemHeight;
            } else {
                i = rect.left - this.mItemWidth;
                i2 = rect.top;
                i3 = this.mItemWidth + i;
                i4 = this.mItemHeight;
            }
            i7 = i;
            i8 = i2;
            i5 = i3;
            i6 = i4 + i2;
        } else if (z) {
            if (isNeedMoveToNextSpan) {
                i = rect.left + this.mItemWidth + calculateClipOffset(true, i11);
                i2 = getPaddingTop();
            } else {
                i = rect.left;
                i2 = rect.bottom;
            }
            i3 = this.mItemWidth + i;
            i4 = this.mItemHeight;
            i7 = i;
            i8 = i2;
            i5 = i3;
            i6 = i4 + i2;
        } else {
            if (isNeedMoveToNextSpan) {
                i9 = (rect.left - this.mItemWidth) - calculateClipOffset(false, i11);
                i10 = getHeight() - getPaddingBottom();
            } else {
                i9 = rect.left;
                i10 = rect.top;
            }
            i7 = i9;
            i6 = i10;
            i8 = i10 - this.mItemHeight;
            i5 = this.mItemWidth + i9;
        }
        layoutState.setOffsetRect(i7, i8, i5, i6);
        layoutDecoratedWithMargins(next, i7, i8, i5, i6);
    }

    private void offsetChildren(int i) {
        if (this.mOrientation == 0) {
            offsetChildrenHorizontal(i);
        } else {
            offsetChildrenVertical(i);
        }
    }

    private void recycleViews(RecyclerView.Recycler recycler) {
        if (this.mLayoutState.mRecycle) {
            if (shouldHorizontallyReverseLayout()) {
                if (this.mLayoutState.mLayoutDirection == -1) {
                    recycleViewsFromStart(recycler);
                    return;
                } else {
                    recycleViewsFromEnd(recycler);
                    return;
                }
            }
            if (this.mLayoutState.mLayoutDirection == -1) {
                recycleViewsFromEnd(recycler);
            } else {
                recycleViewsFromStart(recycler);
            }
        }
    }

    private void recycleViewsFromEnd(RecyclerView.Recycler recycler) {
        int endAfterPadding = getClipToPadding() ? getEndAfterPadding() : this.mOrientation == 0 ? getWidth() : getHeight();
        for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = getChildAt(childCount);
            if (childAt != null && getDecoratedStart(childAt) > endAfterPadding) {
                if (DEBUG) {
                    Log.w(TAG, "recycleViewsFromEnd-removeAndRecycleViewAt: " + childCount + ", position: " + getPosition(childAt));
                }
                removeAndRecycleViewAt(childCount, recycler);
            }
        }
    }

    private void recycleViewsFromStart(RecyclerView.Recycler recycler) {
        int startAfterPadding = getClipToPadding() ? getStartAfterPadding() : 0;
        for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = getChildAt(childCount);
            if (childAt != null && getDecoratedEnd(childAt) < startAfterPadding) {
                if (DEBUG) {
                    Log.w(TAG, "recycleViewsFromStart-removeAndRecycleViewAt: " + childCount + ", position: " + getPosition(childAt));
                }
                removeAndRecycleViewAt(childCount, recycler);
            }
        }
    }

    private void resolveShouldLayoutReverse() {
        if (this.mOrientation == 1 || !isLayoutRTL()) {
            this.mShouldReverseLayout = this.mReverseLayout;
        } else {
            this.mShouldReverseLayout = !this.mReverseLayout;
        }
    }

    private void reverseLayoutChunk(RecyclerView.Recycler recycler, RecyclerView.State state, LayoutState layoutState, LayoutChunkResult layoutChunkResult) {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        int i11;
        int i12;
        int i13;
        boolean z = layoutState.mLayoutDirection == 1;
        int i14 = layoutState.mCurrentPosition;
        View next = layoutState.next(recycler);
        if (z) {
            addView(next);
        } else {
            addView(next, 0);
        }
        layoutState.mCurrentPosition = z ? layoutState.getNextPosition(i14, this.mOrientation, this.mRows, this.mColumns, state) : layoutState.getPrePosition(i14, this.mOrientation, this.mRows, this.mColumns, state);
        measureChildWithMargins(next, this.mItemWidthUsed, this.mItemHeightUsed);
        boolean isNeedMoveToNextSpan = z ? isNeedMoveToNextSpan(i14) : isNeedMoveToPreSpan(i14);
        layoutChunkResult.mConsumed = isNeedMoveToNextSpan ? this.mOrientation == 0 ? this.mItemWidth : this.mItemHeight : 0;
        Rect rect = layoutState.mOffsetRect;
        if (this.mOrientation == 0) {
            if (!z) {
                if (isNeedMoveToNextSpan) {
                    i12 = rect.left + this.mItemWidth + calculateClipOffset(false, i14);
                    i13 = getHeight() - getPaddingBottom();
                } else {
                    i12 = rect.left;
                    i13 = rect.top;
                }
                i5 = i12;
                i6 = i13;
                i8 = i13 - this.mItemHeight;
                i7 = this.mItemWidth + i12;
                layoutState.setOffsetRect(i5, i8, i7, i6);
                layoutDecoratedWithMargins(next, i5, i8, i7, i6);
            }
            if (isNeedMoveToNextSpan) {
                i = (rect.left - this.mItemWidth) - calculateClipOffset(true, i14);
                i3 = getPaddingTop();
            } else {
                i = rect.left;
                i3 = rect.bottom;
            }
            i2 = this.mItemWidth + i;
            i4 = this.mItemHeight;
            i11 = i4 + i3;
            i5 = i;
            i8 = i3;
            i7 = i2;
        } else if (z) {
            if (isNeedMoveToNextSpan) {
                i9 = getWidth() - getPaddingEnd();
                i10 = rect.bottom + calculateClipOffset(true, i14);
            } else {
                i9 = rect.left;
                i10 = rect.top;
            }
            int i15 = i9 - this.mItemWidth;
            i11 = this.mItemHeight + i10;
            i7 = i9;
            i8 = i10;
            i5 = i15;
        } else {
            if (isNeedMoveToNextSpan) {
                int paddingStart = getPaddingStart();
                int i16 = this.mItemWidth + paddingStart;
                int calculateClipOffset = rect.top - calculateClipOffset(false, i14);
                i5 = paddingStart;
                i6 = calculateClipOffset;
                i7 = i16;
                i8 = calculateClipOffset - this.mItemHeight;
                layoutState.setOffsetRect(i5, i8, i7, i6);
                layoutDecoratedWithMargins(next, i5, i8, i7, i6);
            }
            i = rect.right;
            i2 = this.mItemWidth + i;
            i3 = rect.top;
            i4 = this.mItemHeight;
            i11 = i4 + i3;
            i5 = i;
            i8 = i3;
            i7 = i2;
        }
        i6 = i11;
        layoutState.setOffsetRect(i5, i8, i7, i6);
        layoutDecoratedWithMargins(next, i5, i8, i7, i6);
    }

    private int scrollBy(int i, RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (getChildCount() == 0 || i == 0 || this.mPagerCount == 1) {
            return 0;
        }
        this.mLayoutState.mRecycle = true;
        int i2 = -1;
        if (!shouldHorizontallyReverseLayout() ? i > 0 : i <= 0) {
            i2 = 1;
        }
        this.mLayoutState.mLayoutDirection = i2;
        boolean z = i2 == 1;
        int abs = Math.abs(i);
        if (DEBUG) {
            Log.i(TAG, "scrollBy -> before : childCount:" + getChildCount() + ",recycler.scrapList.size:" + recycler.getScrapList().size() + ",delta:" + i);
        }
        updateLayoutState(z, abs, true, state);
        int fill = this.mLayoutState.mScrollingOffset + fill(recycler, state);
        if (z) {
            fill += this.mLayoutState.replenishDelta;
        }
        if (fill < 0) {
            return 0;
        }
        int i3 = abs > fill ? i2 * fill : i;
        offsetChildren(-i3);
        this.mLayoutState.mLastScrollDelta = i3;
        recycleViews(recycler);
        if (DEBUG) {
            Log.i(TAG, "scrollBy -> end : childCount:" + getChildCount() + ",recycler.scrapList.size:" + recycler.getScrapList().size() + ",delta:" + i + ",scrolled:" + i3);
        }
        return i3;
    }

    private void setCurrentPagerIndex(int i) {
        int i2 = this.mCurrentPagerIndex;
        if (i2 == i) {
            return;
        }
        this.mCurrentPagerIndex = i;
        PagerChangedListener pagerChangedListener = this.mPagerChangedListener;
        if (pagerChangedListener != null) {
            pagerChangedListener.onPagerIndexSelected(i2, i);
        }
    }

    public static void setDebug(boolean z) {
        DEBUG = z;
    }

    private void setPagerCount(int i) {
        if (this.mPagerCount == i) {
            return;
        }
        this.mPagerCount = i;
        PagerChangedListener pagerChangedListener = this.mPagerChangedListener;
        if (pagerChangedListener != null) {
            pagerChangedListener.onPagerCountChanged(i);
        }
    }

    private void updateLayoutState(boolean z, int i, boolean z2, RecyclerView.State state) {
        View childClosestToStart;
        int i2;
        int startAfterPadding;
        int decoratedEnd;
        int endAfterPadding;
        int i3;
        if (z) {
            childClosestToStart = getChildClosestToEnd();
            if (shouldHorizontallyReverseLayout()) {
                i2 = -getDecoratedStart(childClosestToStart);
                startAfterPadding = getStartAfterPadding();
                i3 = i2 + startAfterPadding;
            } else {
                decoratedEnd = getDecoratedEnd(childClosestToStart);
                endAfterPadding = getEndAfterPadding();
                i3 = decoratedEnd - endAfterPadding;
            }
        } else {
            childClosestToStart = getChildClosestToStart();
            if (shouldHorizontallyReverseLayout()) {
                decoratedEnd = getDecoratedEnd(childClosestToStart);
                endAfterPadding = getEndAfterPadding();
                i3 = decoratedEnd - endAfterPadding;
            } else {
                i2 = -getDecoratedStart(childClosestToStart);
                startAfterPadding = getStartAfterPadding();
                i3 = i2 + startAfterPadding;
            }
        }
        getDecoratedBoundsWithMargins(childClosestToStart, this.mLayoutState.mOffsetRect);
        LayoutState layoutState = this.mLayoutState;
        layoutState.mCurrentPosition = z ? layoutState.getNextPosition(getPosition(childClosestToStart), this.mOrientation, this.mRows, this.mColumns, state) : layoutState.getPrePosition(getPosition(childClosestToStart), this.mOrientation, this.mRows, this.mColumns, state);
        LayoutState layoutState2 = this.mLayoutState;
        layoutState2.mAvailable = i;
        if (z2) {
            layoutState2.mAvailable -= i3;
        }
        this.mLayoutState.mScrollingOffset = i3;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void calculateCurrentPagerIndexByPosition(int i) {
        setCurrentPagerIndex(getPagerIndexByPosition(i));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public final boolean canScrollHorizontally() {
        return this.mOrientation == 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public final boolean canScrollVertically() {
        return this.mOrientation == 1;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public boolean checkLayoutParams(RecyclerView.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int computeHorizontalScrollExtent(@NonNull RecyclerView.State state) {
        return computeScrollExtent(state);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int computeHorizontalScrollOffset(@NonNull RecyclerView.State state) {
        return computeScrollOffset(state);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int computeHorizontalScrollRange(@NonNull RecyclerView.State state) {
        return computeScrollRange(state);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.SmoothScroller.ScrollVectorProvider
    @Nullable
    public PointF computeScrollVectorForPosition(int i) {
        int i2;
        int childCount = getChildCount();
        if (childCount == 0) {
            return null;
        }
        while (true) {
            childCount--;
            if (childCount < 0) {
                i2 = -1;
                break;
            }
            View childAt = getChildAt(childCount);
            if (childAt != null) {
                i2 = getPosition(childAt);
                if (i2 % this.mOnePageSize == 0) {
                    break;
                }
            }
        }
        if (i2 == -1) {
            return null;
        }
        float f = i < i2 ? -1.0f : 1.0f;
        if (shouldHorizontallyReverseLayout()) {
            f = -f;
        }
        if (DEBUG) {
            Log.w(TAG, "computeScrollVectorForPosition-firstSnapPosition: " + i2 + ", targetPosition:" + i + ",mOrientation :" + this.mOrientation + ", direction:" + f);
        }
        return this.mOrientation == 0 ? new PointF(f, 0.0f) : new PointF(0.0f, f);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int computeVerticalScrollExtent(@NonNull RecyclerView.State state) {
        return computeScrollExtent(state);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int computeVerticalScrollOffset(@NonNull RecyclerView.State state) {
        return computeScrollOffset(state);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int computeVerticalScrollRange(@NonNull RecyclerView.State state) {
        return computeScrollRange(state);
    }

    protected LayoutChunkResult createLayoutChunkResult() {
        return new LayoutChunkResult();
    }

    protected LayoutState createLayoutState() {
        return new LayoutState();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    @Nullable
    public View findViewByPosition(int i) {
        int childCount = getChildCount();
        if (childCount == 0) {
            return null;
        }
        int position = i - getPosition(getChildAt(0));
        if (position >= 0 && position < childCount) {
            View childAt = getChildAt(position);
            if (getPosition(childAt) == i) {
                return childAt;
            }
        }
        return super.findViewByPosition(i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public final RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-1, -1);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public RecyclerView.LayoutParams generateLayoutParams(Context context, AttributeSet attributeSet) {
        return new LayoutParams(context, attributeSet);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public RecyclerView.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof RecyclerView.LayoutParams ? new LayoutParams((RecyclerView.LayoutParams) layoutParams) : layoutParams instanceof ViewGroup.MarginLayoutParams ? new LayoutParams((ViewGroup.MarginLayoutParams) layoutParams) : new LayoutParams(layoutParams);
    }

    @IntRange(from = 1)
    public final int getColumns() {
        return this.mColumns;
    }

    @IntRange(from = -1)
    public final int getCurrentPagerIndex() {
        return this.mCurrentPagerIndex;
    }

    public final int getDiffHeight() {
        return Math.max(this.diffHeight, 0);
    }

    public final int getDiffWidth() {
        return Math.max(this.diffWidth, 0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Rect getEndSnapRect() {
        return this.mEndSnapRect;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public final int getHeight() {
        return super.getHeight() - getDiffHeight();
    }

    public final int getItemHeight() {
        return this.mItemHeight;
    }

    public final int getItemWidth() {
        return this.mItemWidth;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final LayoutState getLayoutState() {
        return this.mLayoutState;
    }

    public final int getMaxPagerIndex() {
        return getPagerIndexByPosition(getItemCount() - 1);
    }

    public final int getMaxScrollOnFlingDuration() {
        return this.mMaxScrollOnFlingDuration;
    }

    public final float getMillisecondPreInch() {
        return this.mMillisecondPreInch;
    }

    @IntRange(from = 1)
    public final int getOnePageSize() {
        return this.mOnePageSize;
    }

    public int getOrientation() {
        return this.mOrientation;
    }

    @IntRange(from = 0)
    public final int getPagerCount() {
        return Math.max(this.mPagerCount, 0);
    }

    public final int getPagerIndexByPosition(int i) {
        return i / this.mOnePageSize;
    }

    public boolean getReverseLayout() {
        return this.mReverseLayout;
    }

    @IntRange(from = 1)
    public final int getRows() {
        return this.mRows;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean getShouldReverseLayout() {
        return this.mShouldReverseLayout;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Rect getStartSnapRect() {
        return this.mStartSnapRect;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public final int getWidth() {
        return super.getWidth() - getDiffWidth();
    }

    public final boolean isHandlingSlidingConflictsEnabled() {
        return this.isHandlingSlidingConflictsEnabled;
    }

    protected boolean isLayoutRTL() {
        return getLayoutDirection() == 1;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onAttachedToWindow(RecyclerView recyclerView) {
        super.onAttachedToWindow(recyclerView);
        if (DEBUG) {
            Log.d(TAG, "onAttachedToWindow: ");
        }
        recyclerView.setHasFixedSize(true);
        if (isInScrollingContainer(recyclerView)) {
            if (this.isHandlingSlidingConflictsEnabled) {
                this.onItemTouchListener = new PagerGridItemTouchListener(this, recyclerView);
                recyclerView.addOnItemTouchListener(this.onItemTouchListener);
            } else if (DEBUG) {
                Log.w(TAG, "isHandlingSlidingConflictsEnabled: false.");
            }
        }
        recyclerView.addOnChildAttachStateChangeListener(this.onChildAttachStateChangeListener);
        this.mPagerGridSnapHelper = new PagerGridSnapHelper();
        this.mPagerGridSnapHelper.attachToRecyclerView(recyclerView);
        this.mRecyclerView = recyclerView;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    @CallSuper
    public void onDetachedFromWindow(RecyclerView recyclerView, RecyclerView.Recycler recycler) {
        super.onDetachedFromWindow(recyclerView, recycler);
        if (DEBUG) {
            Log.w(TAG, "onDetachedFromWindow: ");
        }
        RecyclerView recyclerView2 = this.mRecyclerView;
        if (recyclerView2 != null) {
            RecyclerView.OnItemTouchListener onItemTouchListener = this.onItemTouchListener;
            if (onItemTouchListener != null) {
                recyclerView2.removeOnItemTouchListener(onItemTouchListener);
            }
            this.mRecyclerView.removeOnChildAttachStateChangeListener(this.onChildAttachStateChangeListener);
            this.mRecyclerView = null;
        }
        this.mPagerGridSnapHelper.attachToRecyclerView(null);
        this.mPagerGridSnapHelper = null;
    }

    /* JADX WARN: Removed duplicated region for block: B:66:0x01be  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x01c8  */
    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onLayoutChildren(androidx.recyclerview.widget.RecyclerView.Recycler r11, androidx.recyclerview.widget.RecyclerView.State r12) {
        /*
            Method dump skipped, instructions count: 734
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shencoder.pagergridlayoutmanager.PagerGridLayoutManager.onLayoutChildren(androidx.recyclerview.widget.RecyclerView$Recycler, androidx.recyclerview.widget.RecyclerView$State):void");
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onLayoutCompleted(RecyclerView.State state) {
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onMeasure(@NonNull RecyclerView.Recycler recycler, @NonNull RecyclerView.State state, int i, int i2) {
        int mode = View.MeasureSpec.getMode(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        if (mode != 1073741824 || mode2 != 1073741824) {
            throw new IllegalStateException("RecyclerView's width and height must be exactly");
        }
        int size = View.MeasureSpec.getSize(i);
        int size2 = View.MeasureSpec.getSize(i2);
        int paddingStart = (size - getPaddingStart()) - getPaddingEnd();
        int paddingTop = (size2 - getPaddingTop()) - getPaddingBottom();
        int i3 = this.mColumns;
        this.mItemWidth = i3 > 0 ? paddingStart / i3 : 0;
        int i4 = this.mRows;
        this.mItemHeight = i4 > 0 ? paddingTop / i4 : 0;
        int i5 = this.mItemWidth;
        this.diffWidth = paddingStart - (this.mColumns * i5);
        int i6 = this.mItemHeight;
        this.diffHeight = paddingTop - (this.mRows * i6);
        this.mItemWidthUsed = (paddingStart - this.diffWidth) - i5;
        this.mItemHeightUsed = (paddingTop - this.diffHeight) - i6;
        if (DEBUG) {
            Log.d(TAG, "onMeasure-originalWidthSize: " + size + ",originalHeightSize: " + size2 + ",diffWidth: " + this.diffWidth + ",diffHeight: " + this.diffHeight + ",mItemWidth: " + this.mItemWidth + ",mItemHeight: " + this.mItemHeight + ",mStartSnapRect:" + this.mStartSnapRect + ",mEndSnapRect:" + this.mEndSnapRect);
        }
        super.onMeasure(recycler, state, i, i2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof SavedState) {
            SavedState savedState = (SavedState) parcelable;
            this.mOrientation = savedState.mOrientation;
            this.mRows = savedState.mRows;
            this.mColumns = savedState.mColumns;
            calculateOnePageSize();
            setCurrentPagerIndex(savedState.mCurrentPagerIndex);
            this.mReverseLayout = savedState.mReverseLayout;
            requestLayout();
            if (DEBUG) {
                Log.d(TAG, "onRestoreInstanceState: loaded saved state");
            }
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    @Nullable
    public Parcelable onSaveInstanceState() {
        if (DEBUG) {
            Log.d(TAG, "onSaveInstanceState: ");
        }
        SavedState savedState = new SavedState();
        savedState.mOrientation = this.mOrientation;
        savedState.mRows = this.mRows;
        savedState.mColumns = this.mColumns;
        savedState.mCurrentPagerIndex = this.mCurrentPagerIndex;
        savedState.mReverseLayout = this.mReverseLayout;
        return savedState;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onScrollStateChanged(int i) {
        if (i != 0) {
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int scrollHorizontallyBy(int i, RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (this.mOrientation == 1) {
            return 0;
        }
        return scrollBy(i, recycler, state);
    }

    public void scrollToNextPager() {
        assertNotInLayoutOrScroll(null);
        scrollToPagerIndex(this.mCurrentPagerIndex + 1);
    }

    public void scrollToPagerIndex(@IntRange(from = 0) int i) {
        assertNotInLayoutOrScroll(null);
        int min = Math.min(Math.max(i, 0), getMaxPagerIndex());
        if (min == this.mCurrentPagerIndex) {
            return;
        }
        setCurrentPagerIndex(min);
        requestLayout();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void scrollToPosition(int i) {
        assertNotInLayoutOrScroll(null);
        scrollToPagerIndex(getPagerIndexByPosition(i));
    }

    public void scrollToPrePager() {
        assertNotInLayoutOrScroll(null);
        scrollToPagerIndex(this.mCurrentPagerIndex - 1);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int scrollVerticallyBy(int i, RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (this.mOrientation == 0) {
            return 0;
        }
        return scrollBy(i, recycler, state);
    }

    public void setColumns(@IntRange(from = 1) int i) {
        assertNotInLayoutOrScroll(null);
        if (this.mColumns == i) {
            return;
        }
        this.mColumns = Math.max(i, 1);
        this.mPagerCount = 0;
        this.mCurrentPagerIndex = -1;
        calculateOnePageSize();
        requestLayout();
    }

    public final void setHandlingSlidingConflictsEnabled(boolean z) {
        this.isHandlingSlidingConflictsEnabled = z;
    }

    public final void setMaxScrollOnFlingDuration(@IntRange(from = 1) int i) {
        this.mMaxScrollOnFlingDuration = Math.max(1, i);
    }

    public final void setMillisecondPreInch(@FloatRange(from = 1.0d) float f) {
        this.mMillisecondPreInch = Math.max(1.0f, f);
    }

    public void setOrientation(int i) {
        assertNotInLayoutOrScroll(null);
        if (i != 0 && i != 1) {
            throw new IllegalArgumentException("invalid orientation:" + i);
        }
        if (i != this.mOrientation) {
            this.mOrientation = i;
            requestLayout();
        }
    }

    public void setPagerChangedListener(@Nullable PagerChangedListener pagerChangedListener) {
        this.mPagerChangedListener = pagerChangedListener;
    }

    public void setReverseLayout(boolean z) {
        assertNotInLayoutOrScroll(null);
        if (z == this.mReverseLayout) {
            return;
        }
        this.mReverseLayout = z;
        requestLayout();
    }

    public void setRows(@IntRange(from = 1) int i) {
        assertNotInLayoutOrScroll(null);
        if (this.mRows == i) {
            return;
        }
        this.mRows = Math.max(i, 1);
        this.mPagerCount = 0;
        this.mCurrentPagerIndex = -1;
        calculateOnePageSize();
        requestLayout();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean shouldHorizontallyReverseLayout() {
        return this.mShouldReverseLayout && this.mOrientation == 0;
    }

    public void smoothScrollToNextPager() {
        assertNotInLayoutOrScroll(null);
        smoothScrollToPagerIndex(this.mCurrentPagerIndex + 1);
    }

    public void smoothScrollToPagerIndex(@IntRange(from = 0) int i) {
        assertNotInLayoutOrScroll(null);
        int min = Math.min(Math.max(i, 0), getMaxPagerIndex());
        int i2 = this.mCurrentPagerIndex;
        if (min == i2) {
            return;
        }
        boolean z = min > i2;
        if (Math.abs(min - i2) <= 3) {
            PagerGridSmoothScroller pagerGridSmoothScroller = new PagerGridSmoothScroller(this.mRecyclerView, this);
            pagerGridSmoothScroller.setTargetPosition(getPositionByPagerIndex(min, z));
            startSmoothScroll(pagerGridSmoothScroller);
        } else {
            scrollToPagerIndex(min > i2 ? min - 3 : min + 3);
            RecyclerView recyclerView = this.mRecyclerView;
            if (recyclerView != null) {
                recyclerView.post(new SmoothScrollToPosition(getPositionByPagerIndex(min, z), this, this.mRecyclerView));
            }
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int i) {
        assertNotInLayoutOrScroll(null);
        smoothScrollToPagerIndex(getPagerIndexByPosition(i));
    }

    public void smoothScrollToPrePager() {
        assertNotInLayoutOrScroll(null);
        smoothScrollToPagerIndex(this.mCurrentPagerIndex - 1);
    }
}
