package com.yj.coffeemachines.mvp.ui.fragment;

import android.view.View;
import android.widget.LinearLayout;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.yj.coffeemachines.R;

/* loaded from: classes.dex */
public class Ops3_WaterSetingFragment_ViewBinding implements Unbinder {
    private Ops3_WaterSetingFragment target;

    @UiThread
    public Ops3_WaterSetingFragment_ViewBinding(Ops3_WaterSetingFragment ops3_WaterSetingFragment, View view) {
        this.target = ops3_WaterSetingFragment;
        ops3_WaterSetingFragment.mHotRecycle = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.hot_recycle, "field 'mHotRecycle'", RecyclerView.class);
        ops3_WaterSetingFragment.mColdRecycle = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.cold_recycle, "field 'mColdRecycle'", RecyclerView.class);
        ops3_WaterSetingFragment.lnReset = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ln_reset, "field 'lnReset'", LinearLayout.class);
    }

    @Override // butterknife.Unbinder
    @CallSuper
    public void unbind() {
        Ops3_WaterSetingFragment ops3_WaterSetingFragment = this.target;
        if (ops3_WaterSetingFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        ops3_WaterSetingFragment.mHotRecycle = null;
        ops3_WaterSetingFragment.mColdRecycle = null;
        ops3_WaterSetingFragment.lnReset = null;
    }
}
