package com.shencoder.pagergridlayoutmanager;

import android.graphics.PointF;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;

/* loaded from: classes.dex */
class PagerGridSmoothScroller extends LinearSmoothScroller {
    static final int MAX_SCROLL_ON_FLING_DURATION = 500;
    static final float MILLISECONDS_PER_INCH = 100.0f;
    private static final String TAG = "PagerGridSmoothScroller";

    @NonNull
    private final PagerGridLayoutManager mLayoutManager;

    @NonNull
    private final RecyclerView mRecyclerView;

    /* JADX INFO: Access modifiers changed from: package-private */
    public PagerGridSmoothScroller(@NonNull RecyclerView recyclerView, @NonNull PagerGridLayoutManager pagerGridLayoutManager) {
        super(recyclerView.getContext());
        this.mRecyclerView = recyclerView;
        this.mLayoutManager = pagerGridLayoutManager;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int calculateDx(PagerGridLayoutManager pagerGridLayoutManager, Rect rect, Rect rect2) {
        if (pagerGridLayoutManager.canScrollHorizontally()) {
            return rect2.left - rect.left;
        }
        return 0;
    }

    static int calculateDx(PagerGridLayoutManager pagerGridLayoutManager, Rect rect, Rect rect2, boolean z) {
        int i;
        int i2;
        if (!pagerGridLayoutManager.canScrollHorizontally()) {
            return 0;
        }
        if (z) {
            i = rect2.left;
            i2 = rect.left;
        } else {
            i = rect2.right;
            i2 = rect.right;
        }
        return i - i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int calculateDy(PagerGridLayoutManager pagerGridLayoutManager, Rect rect, Rect rect2) {
        if (pagerGridLayoutManager.canScrollVertically()) {
            return rect2.top - rect.top;
        }
        return 0;
    }

    static int calculateDy(PagerGridLayoutManager pagerGridLayoutManager, Rect rect, Rect rect2, boolean z) {
        int i;
        int i2;
        if (!pagerGridLayoutManager.canScrollVertically()) {
            return 0;
        }
        if (z) {
            i = rect2.top;
            i2 = rect.top;
        } else {
            i = rect2.bottom;
            i2 = rect.bottom;
        }
        return i - i2;
    }

    @Override // androidx.recyclerview.widget.LinearSmoothScroller
    protected float calculateSpeedPerPixel(DisplayMetrics displayMetrics) {
        float millisecondPreInch = this.mLayoutManager.getMillisecondPreInch() / displayMetrics.densityDpi;
        if (PagerGridLayoutManager.DEBUG) {
            Log.i(TAG, "calculateSpeedPerPixel-speed: " + millisecondPreInch);
        }
        return millisecondPreInch;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.recyclerview.widget.LinearSmoothScroller
    public final int calculateTimeForScrolling(int i) {
        int min = Math.min(this.mLayoutManager.getMaxScrollOnFlingDuration(), super.calculateTimeForScrolling(i));
        Log.i(TAG, "calculateTimeForScrolling-time: " + min);
        return min;
    }

    @Override // androidx.recyclerview.widget.LinearSmoothScroller, androidx.recyclerview.widget.RecyclerView.SmoothScroller
    protected void onTargetFound(View view, RecyclerView.State state, RecyclerView.SmoothScroller.Action action) {
        PagerGridLayoutManager pagerGridLayoutManager;
        int position;
        PointF computeScrollVectorForPosition;
        RecyclerView.LayoutManager layoutManager = getLayoutManager();
        if (!(layoutManager instanceof PagerGridLayoutManager) || (computeScrollVectorForPosition = computeScrollVectorForPosition((position = (pagerGridLayoutManager = (PagerGridLayoutManager) layoutManager).getPosition(view)))) == null) {
            return;
        }
        boolean z = computeScrollVectorForPosition.x > 0.0f || computeScrollVectorForPosition.y > 0.0f;
        if (pagerGridLayoutManager.shouldHorizontallyReverseLayout()) {
            z = !z;
        }
        Rect startSnapRect = z ? pagerGridLayoutManager.getStartSnapRect() : pagerGridLayoutManager.getEndSnapRect();
        Rect rect = new Rect();
        layoutManager.getDecoratedBoundsWithMargins(view, rect);
        int calculateDx = calculateDx(pagerGridLayoutManager, startSnapRect, rect, z);
        int calculateDy = calculateDy(pagerGridLayoutManager, startSnapRect, rect, z);
        int calculateTimeForDeceleration = calculateTimeForDeceleration(Math.max(Math.abs(calculateDx), Math.abs(calculateDy)));
        if (PagerGridLayoutManager.DEBUG) {
            Log.i(TAG, "onTargetFound-targetPosition:" + position + ", dx:" + calculateDx + ",dy:" + calculateDy + ",time:" + calculateTimeForDeceleration + ",isLayoutToEnd:" + z + ",snapRect:" + startSnapRect + ",targetRect:" + rect);
        }
        if (calculateTimeForDeceleration > 0) {
            action.update(calculateDx, calculateDy, calculateTimeForDeceleration, this.mDecelerateInterpolator);
        } else {
            pagerGridLayoutManager.calculateCurrentPagerIndexByPosition(position);
        }
    }
}
