package com.shencoder.pagergridlayoutmanager;

import android.graphics.Rect;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
class PagerGridSnapHelper extends SnapHelper {
    private static final String TAG = "PagerGridSnapHelper";
    private RecyclerView mRecyclerView;
    private final List<View> snapList = new ArrayList(2);

    static int calculateDxToNextPager(PagerGridLayoutManager pagerGridLayoutManager, Rect rect) {
        if (pagerGridLayoutManager.canScrollHorizontally()) {
            return getLayoutEndAfterPadding(pagerGridLayoutManager) - rect.left;
        }
        return 0;
    }

    static int calculateDyToNextPager(PagerGridLayoutManager pagerGridLayoutManager, Rect rect) {
        if (pagerGridLayoutManager.canScrollVertically()) {
            return getLayoutEndAfterPadding(pagerGridLayoutManager) - rect.top;
        }
        return 0;
    }

    static int distanceToCenter(RecyclerView.LayoutManager layoutManager, @NonNull View view) {
        return getChildViewCenter(layoutManager, view) - getLayoutCenter(layoutManager);
    }

    static int getChildViewCenter(RecyclerView.LayoutManager layoutManager, View view) {
        return getViewDecoratedStart(layoutManager, view) + (getViewDecoratedMeasurement(layoutManager, view) / 2);
    }

    static int getLayoutCenter(RecyclerView.LayoutManager layoutManager) {
        return getLayoutStartAfterPadding(layoutManager) + (getLayoutTotalSpace(layoutManager) / 2);
    }

    static int getLayoutEndAfterPadding(RecyclerView.LayoutManager layoutManager) {
        int height;
        int paddingBottom;
        if (layoutManager.canScrollHorizontally()) {
            height = layoutManager.getWidth();
            paddingBottom = layoutManager.getPaddingEnd();
        } else {
            height = layoutManager.getHeight();
            paddingBottom = layoutManager.getPaddingBottom();
        }
        return height - paddingBottom;
    }

    static int getLayoutStartAfterPadding(RecyclerView.LayoutManager layoutManager) {
        return layoutManager.canScrollHorizontally() ? layoutManager.getPaddingStart() : layoutManager.getPaddingTop();
    }

    static int getLayoutTotalSpace(RecyclerView.LayoutManager layoutManager) {
        int height;
        int paddingBottom;
        if (layoutManager.canScrollHorizontally()) {
            height = layoutManager.getWidth() - layoutManager.getPaddingStart();
            paddingBottom = layoutManager.getPaddingEnd();
        } else {
            height = layoutManager.getHeight() - layoutManager.getPaddingTop();
            paddingBottom = layoutManager.getPaddingBottom();
        }
        return height - paddingBottom;
    }

    static int getViewDecoratedEnd(RecyclerView.LayoutManager layoutManager, View view) {
        int decoratedBottom;
        int i;
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
        if (layoutManager.canScrollHorizontally()) {
            decoratedBottom = layoutManager.getDecoratedRight(view);
            i = layoutParams.rightMargin;
        } else {
            decoratedBottom = layoutManager.getDecoratedBottom(view);
            i = layoutParams.bottomMargin;
        }
        return decoratedBottom - i;
    }

    static int getViewDecoratedMeasurement(RecyclerView.LayoutManager layoutManager, View view) {
        int decoratedMeasuredHeight;
        int i;
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
        if (layoutManager.canScrollHorizontally()) {
            decoratedMeasuredHeight = layoutManager.getDecoratedMeasuredWidth(view) + layoutParams.leftMargin;
            i = layoutParams.rightMargin;
        } else {
            decoratedMeasuredHeight = layoutManager.getDecoratedMeasuredHeight(view) + layoutParams.topMargin;
            i = layoutParams.bottomMargin;
        }
        return decoratedMeasuredHeight + i;
    }

    static int getViewDecoratedStart(RecyclerView.LayoutManager layoutManager, View view) {
        int decoratedTop;
        int i;
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
        if (layoutManager.canScrollHorizontally()) {
            decoratedTop = layoutManager.getDecoratedLeft(view);
            i = layoutParams.leftMargin;
        } else {
            decoratedTop = layoutManager.getDecoratedTop(view);
            i = layoutParams.topMargin;
        }
        return decoratedTop - i;
    }

    private boolean isForwardFling(PagerGridLayoutManager pagerGridLayoutManager, int i, int i2) {
        if (pagerGridLayoutManager.canScrollHorizontally()) {
            if (pagerGridLayoutManager.getShouldReverseLayout()) {
                if (i < 0) {
                    return true;
                }
            } else if (i > 0) {
                return true;
            }
        } else if (i2 > 0) {
            return true;
        }
        return false;
    }

    private void reacquireSnapList(PagerGridLayoutManager pagerGridLayoutManager) {
        if (!this.snapList.isEmpty()) {
            this.snapList.clear();
        }
        int childCount = pagerGridLayoutManager.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = pagerGridLayoutManager.getChildAt(i);
            if (childAt != null && pagerGridLayoutManager.getPosition(childAt) % pagerGridLayoutManager.getOnePageSize() == 0) {
                this.snapList.add(childAt);
            }
        }
    }

    @Override // androidx.recyclerview.widget.SnapHelper
    public void attachToRecyclerView(@Nullable RecyclerView recyclerView) throws IllegalStateException {
        super.attachToRecyclerView(recyclerView);
        this.mRecyclerView = recyclerView;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0064  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x006f  */
    @Override // androidx.recyclerview.widget.SnapHelper
    @androidx.annotation.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public int[] calculateDistanceToFinalSnap(@androidx.annotation.NonNull androidx.recyclerview.widget.RecyclerView.LayoutManager r6, @androidx.annotation.NonNull android.view.View r7) {
        /*
            r5 = this;
            r0 = 2
            int[] r0 = new int[r0]
            int r1 = r6.getPosition(r7)
            boolean r2 = r6 instanceof com.shencoder.pagergridlayoutmanager.PagerGridLayoutManager
            if (r2 == 0) goto L91
            r2 = r6
            com.shencoder.pagergridlayoutmanager.PagerGridLayoutManager r2 = (com.shencoder.pagergridlayoutmanager.PagerGridLayoutManager) r2
            int r3 = getLayoutCenter(r2)
            android.graphics.Rect r4 = new android.graphics.Rect
            r4.<init>()
            r6.getDecoratedBoundsWithMargins(r7, r4)
            boolean r6 = r2.shouldHorizontallyReverseLayout()
            if (r6 == 0) goto L3d
            int r6 = getViewDecoratedEnd(r2, r7)
            if (r6 < r3) goto L33
            android.graphics.Rect r6 = r2.getStartSnapRect()
            int r7 = com.shencoder.pagergridlayoutmanager.PagerGridSmoothScroller.calculateDx(r2, r6, r4)
            int r6 = com.shencoder.pagergridlayoutmanager.PagerGridSmoothScroller.calculateDy(r2, r6, r4)
            goto L5a
        L33:
            int r6 = calculateDxToNextPager(r2, r4)
            int r7 = -r6
            int r6 = calculateDyToNextPager(r2, r4)
            goto L59
        L3d:
            int r6 = getViewDecoratedStart(r2, r7)
            if (r6 > r3) goto L50
            android.graphics.Rect r6 = r2.getStartSnapRect()
            int r7 = com.shencoder.pagergridlayoutmanager.PagerGridSmoothScroller.calculateDx(r2, r6, r4)
            int r6 = com.shencoder.pagergridlayoutmanager.PagerGridSmoothScroller.calculateDy(r2, r6, r4)
            goto L5a
        L50:
            int r6 = calculateDxToNextPager(r2, r4)
            int r7 = -r6
            int r6 = calculateDyToNextPager(r2, r4)
        L59:
            int r6 = -r6
        L5a:
            r3 = 0
            r0[r3] = r7
            r7 = 1
            r0[r7] = r6
            r6 = r0[r3]
            if (r6 != 0) goto L6b
            r6 = r0[r7]
            if (r6 != 0) goto L6b
            r2.calculateCurrentPagerIndexByPosition(r1)
        L6b:
            boolean r6 = com.shencoder.pagergridlayoutmanager.PagerGridLayoutManager.DEBUG
            if (r6 == 0) goto L91
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r7 = "calculateDistanceToFinalSnap-targetView: "
            r6.append(r7)
            r6.append(r1)
            java.lang.String r7 = ",snapDistance: "
            r6.append(r7)
            java.lang.String r7 = java.util.Arrays.toString(r0)
            r6.append(r7)
            java.lang.String r6 = r6.toString()
            java.lang.String r7 = "PagerGridSnapHelper"
            android.util.Log.i(r7, r6)
        L91:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shencoder.pagergridlayoutmanager.PagerGridSnapHelper.calculateDistanceToFinalSnap(androidx.recyclerview.widget.RecyclerView$LayoutManager, android.view.View):int[]");
    }

    @Override // androidx.recyclerview.widget.SnapHelper
    @Nullable
    protected RecyclerView.SmoothScroller createScroller(@NonNull RecyclerView.LayoutManager layoutManager) {
        RecyclerView recyclerView;
        if ((layoutManager instanceof PagerGridLayoutManager) && (recyclerView = this.mRecyclerView) != null) {
            return new PagerGridSmoothScroller(recyclerView, (PagerGridLayoutManager) layoutManager);
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0084  */
    @Override // androidx.recyclerview.widget.SnapHelper
    @androidx.annotation.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public android.view.View findSnapView(androidx.recyclerview.widget.RecyclerView.LayoutManager r8) {
        /*
            r7 = this;
            boolean r0 = r8 instanceof com.shencoder.pagergridlayoutmanager.PagerGridLayoutManager
            r1 = 0
            if (r0 == 0) goto Lb3
            r0 = r8
            com.shencoder.pagergridlayoutmanager.PagerGridLayoutManager r0 = (com.shencoder.pagergridlayoutmanager.PagerGridLayoutManager) r0
            r7.reacquireSnapList(r0)
            java.util.List<android.view.View> r2 = r7.snapList
            int r2 = r2.size()
            java.lang.String r3 = "PagerGridSnapHelper"
            r4 = 0
            r5 = 1
            if (r2 == r5) goto L77
            r6 = 2
            if (r2 == r6) goto L45
            r0 = 3
            if (r2 == r0) goto L3c
            boolean r0 = com.shencoder.pagergridlayoutmanager.PagerGridLayoutManager.DEBUG
            if (r0 == 0) goto L80
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r2 = "findSnapView wrong -> snapList.size: "
            r0.append(r2)
            java.util.List<android.view.View> r2 = r7.snapList
            int r2 = r2.size()
            r0.append(r2)
            java.lang.String r0 = r0.toString()
            android.util.Log.w(r3, r0)
            goto L80
        L3c:
            java.util.List<android.view.View> r0 = r7.snapList
            java.lang.Object r0 = r0.get(r5)
            android.view.View r0 = (android.view.View) r0
            goto L7f
        L45:
            int r1 = getLayoutCenter(r0)
            java.util.List<android.view.View> r2 = r7.snapList
            java.lang.Object r2 = r2.get(r4)
            android.view.View r2 = (android.view.View) r2
            java.util.List<android.view.View> r4 = r7.snapList
            java.lang.Object r4 = r4.get(r5)
            android.view.View r4 = (android.view.View) r4
            android.graphics.Rect r5 = new android.graphics.Rect
            r5.<init>()
            r0.getDecoratedBoundsWithMargins(r4, r5)
            boolean r5 = r0.shouldHorizontallyReverseLayout()
            if (r5 == 0) goto L6e
            int r0 = getViewDecoratedEnd(r0, r4)
            if (r0 > r1) goto L74
            goto L75
        L6e:
            int r0 = getViewDecoratedStart(r0, r4)
            if (r0 > r1) goto L75
        L74:
            r2 = r4
        L75:
            r1 = r2
            goto L80
        L77:
            java.util.List<android.view.View> r0 = r7.snapList
            java.lang.Object r0 = r0.get(r4)
            android.view.View r0 = (android.view.View) r0
        L7f:
            r1 = r0
        L80:
            boolean r0 = com.shencoder.pagergridlayoutmanager.PagerGridLayoutManager.DEBUG
            if (r0 == 0) goto Lae
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r2 = "findSnapView: position:"
            r0.append(r2)
            if (r1 == 0) goto L95
            int r8 = r8.getPosition(r1)
            goto L96
        L95:
            r8 = -1
        L96:
            r0.append(r8)
            java.lang.String r8 = ", snapList.size:"
            r0.append(r8)
            java.util.List<android.view.View> r8 = r7.snapList
            int r8 = r8.size()
            r0.append(r8)
            java.lang.String r8 = r0.toString()
            android.util.Log.i(r3, r8)
        Lae:
            java.util.List<android.view.View> r8 = r7.snapList
            r8.clear()
        Lb3:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shencoder.pagergridlayoutmanager.PagerGridSnapHelper.findSnapView(androidx.recyclerview.widget.RecyclerView$LayoutManager):android.view.View");
    }

    /* JADX WARN: Code restructure failed: missing block: B:42:0x00ac, code lost:
    
        if (r10 < 0) goto L60;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x00dd, code lost:
    
        r10 = -1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x00b7, code lost:
    
        if (r6 < 0) goto L108;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x00c8, code lost:
    
        if (r10 < 0) goto L60;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x00db, code lost:
    
        if (r10 < 0) goto L60;
     */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x0123, code lost:
    
        if (r10 < 0) goto L60;
     */
    /* JADX WARN: Code restructure failed: missing block: B:82:0x0130, code lost:
    
        if (r10 < 0) goto L60;
     */
    /* JADX WARN: Code restructure failed: missing block: B:87:0x013b, code lost:
    
        if (r4 < 0) goto L108;
     */
    /* JADX WARN: Code restructure failed: missing block: B:93:0x0153, code lost:
    
        if (r10 < 0) goto L60;
     */
    /* JADX WARN: Code restructure failed: missing block: B:97:0x0163, code lost:
    
        if (r4 < 0) goto L108;
     */
    @Override // androidx.recyclerview.widget.SnapHelper
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public int findTargetSnapPosition(androidx.recyclerview.widget.RecyclerView.LayoutManager r10, int r11, int r12) {
        /*
            Method dump skipped, instructions count: 433
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shencoder.pagergridlayoutmanager.PagerGridSnapHelper.findTargetSnapPosition(androidx.recyclerview.widget.RecyclerView$LayoutManager, int, int):int");
    }
}
