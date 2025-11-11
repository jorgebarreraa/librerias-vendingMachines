package com.shencoder.pagergridlayoutmanager;

import android.util.Log;
import android.view.MotionEvent;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/* loaded from: classes.dex */
class PagerGridItemTouchListener extends RecyclerView.SimpleOnItemTouchListener {
    private static final String TAG = "ItemTouchListener";
    private final PagerGridLayoutManager layoutManager;
    private int mInitialTouchX;
    private int mInitialTouchY;
    private int mScrollPointerId;
    private final RecyclerView recyclerView;

    /* JADX INFO: Access modifiers changed from: package-private */
    public PagerGridItemTouchListener(PagerGridLayoutManager pagerGridLayoutManager, RecyclerView recyclerView) {
        this.layoutManager = pagerGridLayoutManager;
        this.recyclerView = recyclerView;
    }

    private void onPointerUp(MotionEvent motionEvent) {
        int actionIndex = motionEvent.getActionIndex();
        if (motionEvent.getPointerId(actionIndex) == this.mScrollPointerId) {
            int i = actionIndex == 0 ? 1 : 0;
            this.mScrollPointerId = motionEvent.getPointerId(i);
            this.mInitialTouchX = (int) (motionEvent.getX(i) + 0.5f);
            this.mInitialTouchY = (int) (motionEvent.getY(i) + 0.5f);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.SimpleOnItemTouchListener, androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
    public boolean onInterceptTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        int actionIndex = motionEvent.getActionIndex();
        if (PagerGridLayoutManager.DEBUG) {
            Log.i(TAG, "onInterceptTouchEvent-actionMasked: " + actionMasked + ", actionIndex: " + actionIndex);
        }
        if (actionMasked != 0) {
            if (actionMasked == 2) {
                int findPointerIndex = motionEvent.findPointerIndex(this.mScrollPointerId);
                if (findPointerIndex < 0) {
                    return false;
                }
                int x = (int) (motionEvent.getX(findPointerIndex) + 0.5f);
                int y = (int) (motionEvent.getY(findPointerIndex) + 0.5f);
                int i = x - this.mInitialTouchX;
                int i2 = y - this.mInitialTouchY;
                if (this.layoutManager.canScrollHorizontally()) {
                    this.recyclerView.getParent().requestDisallowInterceptTouchEvent(this.recyclerView.canScrollHorizontally(-i));
                }
                if (this.layoutManager.canScrollVertically()) {
                    this.recyclerView.getParent().requestDisallowInterceptTouchEvent(this.recyclerView.canScrollVertically(-i2));
                }
            } else if (actionMasked != 5) {
                if (actionMasked == 6) {
                    onPointerUp(motionEvent);
                }
            }
            return false;
        }
        this.mScrollPointerId = motionEvent.getPointerId(actionIndex);
        this.mInitialTouchX = (int) (motionEvent.getX(actionIndex) + 0.5f);
        this.mInitialTouchY = (int) (motionEvent.getY(actionIndex) + 0.5f);
        this.recyclerView.getParent().requestDisallowInterceptTouchEvent(true);
        return false;
    }
}
