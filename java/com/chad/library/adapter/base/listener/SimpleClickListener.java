package com.chad.library.adapter.base.listener;

import android.os.Build;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import androidx.core.view.GestureDetectorCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/* loaded from: classes.dex */
public abstract class SimpleClickListener implements RecyclerView.OnItemTouchListener {
    public static String TAG = "SimpleClickListener";
    protected BaseQuickAdapter baseQuickAdapter;
    private GestureDetectorCompat mGestureDetector;
    private boolean mIsPrepressed = false;
    private boolean mIsShowPress = false;
    private View mPressedView = null;
    private RecyclerView recyclerView;

    /* loaded from: classes.dex */
    private class ItemTouchHelperGestureListener implements GestureDetector.OnGestureListener {
        private RecyclerView recyclerView;

        ItemTouchHelperGestureListener(RecyclerView recyclerView) {
            this.recyclerView = recyclerView;
        }

        private void resetPressedView(final View view) {
            if (view != null) {
                view.postDelayed(new Runnable() { // from class: com.chad.library.adapter.base.listener.SimpleClickListener.ItemTouchHelperGestureListener.1
                    @Override // java.lang.Runnable
                    public void run() {
                        View view2 = view;
                        if (view2 != null) {
                            view2.setPressed(false);
                        }
                    }
                }, 50L);
            }
            SimpleClickListener.this.mIsPrepressed = false;
            SimpleClickListener.this.mPressedView = null;
        }

        @Override // android.view.GestureDetector.OnGestureListener
        public boolean onDown(MotionEvent motionEvent) {
            SimpleClickListener.this.mIsPrepressed = true;
            SimpleClickListener.this.mPressedView = this.recyclerView.findChildViewUnder(motionEvent.getX(), motionEvent.getY());
            return false;
        }

        @Override // android.view.GestureDetector.OnGestureListener
        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            return false;
        }

        /* JADX WARN: Removed duplicated region for block: B:29:0x00a9  */
        /* JADX WARN: Removed duplicated region for block: B:44:? A[RETURN, SYNTHETIC] */
        @Override // android.view.GestureDetector.OnGestureListener
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void onLongPress(android.view.MotionEvent r10) {
            /*
                r9 = this;
                androidx.recyclerview.widget.RecyclerView r0 = r9.recyclerView
                int r0 = r0.getScrollState()
                if (r0 == 0) goto L9
                return
            L9:
                com.chad.library.adapter.base.listener.SimpleClickListener r0 = com.chad.library.adapter.base.listener.SimpleClickListener.this
                boolean r0 = com.chad.library.adapter.base.listener.SimpleClickListener.access$000(r0)
                if (r0 == 0) goto L100
                com.chad.library.adapter.base.listener.SimpleClickListener r0 = com.chad.library.adapter.base.listener.SimpleClickListener.this
                android.view.View r0 = com.chad.library.adapter.base.listener.SimpleClickListener.access$100(r0)
                if (r0 == 0) goto L100
                com.chad.library.adapter.base.listener.SimpleClickListener r0 = com.chad.library.adapter.base.listener.SimpleClickListener.this
                android.view.View r0 = com.chad.library.adapter.base.listener.SimpleClickListener.access$100(r0)
                r1 = 0
                r0.performHapticFeedback(r1)
                androidx.recyclerview.widget.RecyclerView r0 = r9.recyclerView
                com.chad.library.adapter.base.listener.SimpleClickListener r2 = com.chad.library.adapter.base.listener.SimpleClickListener.this
                android.view.View r2 = com.chad.library.adapter.base.listener.SimpleClickListener.access$100(r2)
                androidx.recyclerview.widget.RecyclerView$ViewHolder r0 = r0.getChildViewHolder(r2)
                com.chad.library.adapter.base.BaseViewHolder r0 = (com.chad.library.adapter.base.BaseViewHolder) r0
                com.chad.library.adapter.base.listener.SimpleClickListener r2 = com.chad.library.adapter.base.listener.SimpleClickListener.this
                int r3 = r0.getLayoutPosition()
                boolean r2 = com.chad.library.adapter.base.listener.SimpleClickListener.access$300(r2, r3)
                if (r2 != 0) goto L100
                java.util.HashSet r2 = r0.getItemChildLongClickViewIds()
                java.util.Set r3 = r0.getNestViews()
                r4 = 1
                if (r2 == 0) goto La6
                int r5 = r2.size()
                if (r5 <= 0) goto La6
                java.util.Iterator r5 = r2.iterator()
            L52:
                boolean r6 = r5.hasNext()
                if (r6 == 0) goto La6
                java.lang.Object r6 = r5.next()
                java.lang.Integer r6 = (java.lang.Integer) r6
                com.chad.library.adapter.base.listener.SimpleClickListener r7 = com.chad.library.adapter.base.listener.SimpleClickListener.this
                android.view.View r7 = com.chad.library.adapter.base.listener.SimpleClickListener.access$100(r7)
                int r8 = r6.intValue()
                android.view.View r7 = r7.findViewById(r8)
                com.chad.library.adapter.base.listener.SimpleClickListener r8 = com.chad.library.adapter.base.listener.SimpleClickListener.this
                boolean r8 = r8.inRangeOfView(r7, r10)
                if (r8 == 0) goto L52
                boolean r8 = r7.isEnabled()
                if (r8 == 0) goto L52
                if (r3 == 0) goto L83
                boolean r3 = r3.contains(r6)
                if (r3 == 0) goto L83
                goto La4
            L83:
                com.chad.library.adapter.base.listener.SimpleClickListener r3 = com.chad.library.adapter.base.listener.SimpleClickListener.this
                com.chad.library.adapter.base.listener.SimpleClickListener.access$400(r3, r10, r7)
                com.chad.library.adapter.base.listener.SimpleClickListener r3 = com.chad.library.adapter.base.listener.SimpleClickListener.this
                com.chad.library.adapter.base.BaseQuickAdapter r5 = r3.baseQuickAdapter
                int r6 = r0.getLayoutPosition()
                com.chad.library.adapter.base.listener.SimpleClickListener r8 = com.chad.library.adapter.base.listener.SimpleClickListener.this
                com.chad.library.adapter.base.BaseQuickAdapter r8 = r8.baseQuickAdapter
                int r8 = r8.getHeaderLayoutCount()
                int r6 = r6 - r8
                r3.onItemChildLongClick(r5, r7, r6)
                r7.setPressed(r4)
                com.chad.library.adapter.base.listener.SimpleClickListener r3 = com.chad.library.adapter.base.listener.SimpleClickListener.this
                com.chad.library.adapter.base.listener.SimpleClickListener.access$202(r3, r4)
            La4:
                r3 = 1
                goto La7
            La6:
                r3 = 0
            La7:
                if (r3 != 0) goto L100
                com.chad.library.adapter.base.listener.SimpleClickListener r3 = com.chad.library.adapter.base.listener.SimpleClickListener.this
                com.chad.library.adapter.base.BaseQuickAdapter r5 = r3.baseQuickAdapter
                com.chad.library.adapter.base.listener.SimpleClickListener r6 = com.chad.library.adapter.base.listener.SimpleClickListener.this
                android.view.View r6 = com.chad.library.adapter.base.listener.SimpleClickListener.access$100(r6)
                int r0 = r0.getLayoutPosition()
                com.chad.library.adapter.base.listener.SimpleClickListener r7 = com.chad.library.adapter.base.listener.SimpleClickListener.this
                com.chad.library.adapter.base.BaseQuickAdapter r7 = r7.baseQuickAdapter
                int r7 = r7.getHeaderLayoutCount()
                int r0 = r0 - r7
                r3.onItemLongClick(r5, r6, r0)
                com.chad.library.adapter.base.listener.SimpleClickListener r0 = com.chad.library.adapter.base.listener.SimpleClickListener.this
                android.view.View r3 = com.chad.library.adapter.base.listener.SimpleClickListener.access$100(r0)
                com.chad.library.adapter.base.listener.SimpleClickListener.access$400(r0, r10, r3)
                com.chad.library.adapter.base.listener.SimpleClickListener r10 = com.chad.library.adapter.base.listener.SimpleClickListener.this
                android.view.View r10 = com.chad.library.adapter.base.listener.SimpleClickListener.access$100(r10)
                r10.setPressed(r4)
                if (r2 == 0) goto Lfb
                java.util.Iterator r10 = r2.iterator()
            Ldb:
                boolean r0 = r10.hasNext()
                if (r0 == 0) goto Lfb
                java.lang.Object r0 = r10.next()
                java.lang.Integer r0 = (java.lang.Integer) r0
                com.chad.library.adapter.base.listener.SimpleClickListener r2 = com.chad.library.adapter.base.listener.SimpleClickListener.this
                android.view.View r2 = com.chad.library.adapter.base.listener.SimpleClickListener.access$100(r2)
                int r0 = r0.intValue()
                android.view.View r0 = r2.findViewById(r0)
                if (r0 == 0) goto Ldb
                r0.setPressed(r1)
                goto Ldb
            Lfb:
                com.chad.library.adapter.base.listener.SimpleClickListener r10 = com.chad.library.adapter.base.listener.SimpleClickListener.this
                com.chad.library.adapter.base.listener.SimpleClickListener.access$202(r10, r4)
            L100:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.chad.library.adapter.base.listener.SimpleClickListener.ItemTouchHelperGestureListener.onLongPress(android.view.MotionEvent):void");
        }

        @Override // android.view.GestureDetector.OnGestureListener
        public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            return false;
        }

        @Override // android.view.GestureDetector.OnGestureListener
        public void onShowPress(MotionEvent motionEvent) {
            if (!SimpleClickListener.this.mIsPrepressed || SimpleClickListener.this.mPressedView == null) {
                return;
            }
            SimpleClickListener.this.mIsShowPress = true;
        }

        @Override // android.view.GestureDetector.OnGestureListener
        public boolean onSingleTapUp(MotionEvent motionEvent) {
            if (SimpleClickListener.this.mIsPrepressed && SimpleClickListener.this.mPressedView != null) {
                if (this.recyclerView.getScrollState() != 0) {
                    return false;
                }
                View view = SimpleClickListener.this.mPressedView;
                BaseViewHolder baseViewHolder = (BaseViewHolder) this.recyclerView.getChildViewHolder(view);
                if (SimpleClickListener.this.isHeaderOrFooterPosition(baseViewHolder.getLayoutPosition())) {
                    return false;
                }
                HashSet<Integer> childClickViewIds = baseViewHolder.getChildClickViewIds();
                Set<Integer> nestViews = baseViewHolder.getNestViews();
                if (childClickViewIds == null || childClickViewIds.size() <= 0) {
                    SimpleClickListener.this.setPressViewHotSpot(motionEvent, view);
                    SimpleClickListener.this.mPressedView.setPressed(true);
                    if (childClickViewIds != null && childClickViewIds.size() > 0) {
                        Iterator<Integer> it2 = childClickViewIds.iterator();
                        while (it2.hasNext()) {
                            View findViewById = view.findViewById(it2.next().intValue());
                            if (findViewById != null) {
                                findViewById.setPressed(false);
                            }
                        }
                    }
                    SimpleClickListener simpleClickListener = SimpleClickListener.this;
                    simpleClickListener.onItemClick(simpleClickListener.baseQuickAdapter, view, baseViewHolder.getLayoutPosition() - SimpleClickListener.this.baseQuickAdapter.getHeaderLayoutCount());
                } else {
                    for (Integer num : childClickViewIds) {
                        View findViewById2 = view.findViewById(num.intValue());
                        if (findViewById2 != null) {
                            if (SimpleClickListener.this.inRangeOfView(findViewById2, motionEvent) && findViewById2.isEnabled()) {
                                if (nestViews != null && nestViews.contains(num)) {
                                    return false;
                                }
                                SimpleClickListener.this.setPressViewHotSpot(motionEvent, findViewById2);
                                findViewById2.setPressed(true);
                                SimpleClickListener simpleClickListener2 = SimpleClickListener.this;
                                simpleClickListener2.onItemChildClick(simpleClickListener2.baseQuickAdapter, findViewById2, baseViewHolder.getLayoutPosition() - SimpleClickListener.this.baseQuickAdapter.getHeaderLayoutCount());
                                resetPressedView(findViewById2);
                                return true;
                            }
                            findViewById2.setPressed(false);
                        }
                    }
                    SimpleClickListener.this.setPressViewHotSpot(motionEvent, view);
                    SimpleClickListener.this.mPressedView.setPressed(true);
                    Iterator<Integer> it3 = childClickViewIds.iterator();
                    while (it3.hasNext()) {
                        View findViewById3 = view.findViewById(it3.next().intValue());
                        if (findViewById3 != null) {
                            findViewById3.setPressed(false);
                        }
                    }
                    SimpleClickListener simpleClickListener3 = SimpleClickListener.this;
                    simpleClickListener3.onItemClick(simpleClickListener3.baseQuickAdapter, view, baseViewHolder.getLayoutPosition() - SimpleClickListener.this.baseQuickAdapter.getHeaderLayoutCount());
                }
                resetPressedView(view);
            }
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isHeaderOrFooterPosition(int i) {
        if (this.baseQuickAdapter == null) {
            RecyclerView recyclerView = this.recyclerView;
            if (recyclerView == null) {
                return false;
            }
            this.baseQuickAdapter = (BaseQuickAdapter) recyclerView.getAdapter();
        }
        int itemViewType = this.baseQuickAdapter.getItemViewType(i);
        return itemViewType == 1365 || itemViewType == 273 || itemViewType == 819 || itemViewType == 546;
    }

    private boolean isHeaderOrFooterView(int i) {
        return i == 1365 || i == 273 || i == 819 || i == 546;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setPressViewHotSpot(MotionEvent motionEvent, View view) {
        if (Build.VERSION.SDK_INT < 21 || view == null || view.getBackground() == null) {
            return;
        }
        view.getBackground().setHotspot(motionEvent.getRawX(), motionEvent.getY() - view.getY());
    }

    public boolean inRangeOfView(View view, MotionEvent motionEvent) {
        int[] iArr = new int[2];
        if (view != null && view.isShown()) {
            view.getLocationOnScreen(iArr);
            int i = iArr[0];
            int i2 = iArr[1];
            if (motionEvent.getRawX() >= i && motionEvent.getRawX() <= i + view.getWidth() && motionEvent.getRawY() >= i2 && motionEvent.getRawY() <= i2 + view.getHeight()) {
                return true;
            }
        }
        return false;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
    public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
        BaseViewHolder baseViewHolder;
        RecyclerView recyclerView2 = this.recyclerView;
        if (recyclerView2 == null) {
            this.recyclerView = recyclerView;
            this.baseQuickAdapter = (BaseQuickAdapter) this.recyclerView.getAdapter();
            this.mGestureDetector = new GestureDetectorCompat(this.recyclerView.getContext(), new ItemTouchHelperGestureListener(this.recyclerView));
        } else if (recyclerView2 != recyclerView) {
            this.recyclerView = recyclerView;
            this.baseQuickAdapter = (BaseQuickAdapter) this.recyclerView.getAdapter();
            this.mGestureDetector = new GestureDetectorCompat(this.recyclerView.getContext(), new ItemTouchHelperGestureListener(this.recyclerView));
        }
        if (!this.mGestureDetector.onTouchEvent(motionEvent) && motionEvent.getActionMasked() == 1 && this.mIsShowPress) {
            View view = this.mPressedView;
            if (view != null && ((baseViewHolder = (BaseViewHolder) this.recyclerView.getChildViewHolder(view)) == null || !isHeaderOrFooterView(baseViewHolder.getItemViewType()))) {
                this.mPressedView.setPressed(false);
            }
            this.mIsShowPress = false;
            this.mIsPrepressed = false;
        }
        return false;
    }

    public abstract void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i);

    public abstract void onItemChildLongClick(BaseQuickAdapter baseQuickAdapter, View view, int i);

    public abstract void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i);

    public abstract void onItemLongClick(BaseQuickAdapter baseQuickAdapter, View view, int i);

    @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
    public void onRequestDisallowInterceptTouchEvent(boolean z) {
    }

    @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
    public void onTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
        this.mGestureDetector.onTouchEvent(motionEvent);
    }
}
