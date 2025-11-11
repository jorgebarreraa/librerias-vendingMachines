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
public class Ops8_MaterialSetingFragment_ViewBinding implements Unbinder {
    private Ops8_MaterialSetingFragment target;

    @UiThread
    public Ops8_MaterialSetingFragment_ViewBinding(Ops8_MaterialSetingFragment ops8_MaterialSetingFragment, View view) {
        this.target = ops8_MaterialSetingFragment;
        ops8_MaterialSetingFragment.step8_rv = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.step8_rv, "field 'step8_rv'", RecyclerView.class);
        ops8_MaterialSetingFragment.lnReset = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ln_reset, "field 'lnReset'", LinearLayout.class);
    }

    @Override // butterknife.Unbinder
    @CallSuper
    public void unbind() {
        Ops8_MaterialSetingFragment ops8_MaterialSetingFragment = this.target;
        if (ops8_MaterialSetingFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        ops8_MaterialSetingFragment.step8_rv = null;
        ops8_MaterialSetingFragment.lnReset = null;
    }
}
