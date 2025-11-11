package com.yj.coffeemachines.Listeners;

import android.os.SystemClock;
import android.view.View;

/* loaded from: classes.dex */
public class MultipleClicksListeners implements View.OnClickListener {
    public static final int MIN_CLICK_DELAY_TIME = 500;
    private long lastClickTime = 0;
    private int mClickTimes;
    long[] mHits;
    private OnMultipleClickListener onMultipleClickListener;

    /* loaded from: classes.dex */
    public interface OnMultipleClickListener {
        void onMultipleClick(View view);
    }

    public MultipleClicksListeners(int i, OnMultipleClickListener onMultipleClickListener) {
        this.mClickTimes = 1;
        this.mClickTimes = i;
        this.onMultipleClickListener = onMultipleClickListener;
        this.mHits = new long[i];
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        long[] jArr = this.mHits;
        System.arraycopy(jArr, 1, jArr, 0, jArr.length - 1);
        long[] jArr2 = this.mHits;
        jArr2[jArr2.length - 1] = SystemClock.uptimeMillis();
        if (this.mHits[0] >= SystemClock.uptimeMillis() - 500) {
            this.mHits = new long[this.mClickTimes];
            this.onMultipleClickListener.onMultipleClick(view);
        }
    }
}
