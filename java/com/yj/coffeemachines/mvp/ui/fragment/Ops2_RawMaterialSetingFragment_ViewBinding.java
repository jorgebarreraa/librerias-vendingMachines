package com.yj.coffeemachines.mvp.ui.fragment;

import android.view.View;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.yj.coffeemachines.R;

/* loaded from: classes.dex */
public class Ops2_RawMaterialSetingFragment_ViewBinding implements Unbinder {
    private Ops2_RawMaterialSetingFragment target;

    @UiThread
    public Ops2_RawMaterialSetingFragment_ViewBinding(Ops2_RawMaterialSetingFragment ops2_RawMaterialSetingFragment, View view) {
        this.target = ops2_RawMaterialSetingFragment;
        ops2_RawMaterialSetingFragment.mRecyclerview = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.recyclerview, "field 'mRecyclerview'", RecyclerView.class);
        ops2_RawMaterialSetingFragment.submit = Utils.findRequiredView(view, R.id.submit, "field 'submit'");
    }

    @Override // butterknife.Unbinder
    @CallSuper
    public void unbind() {
        Ops2_RawMaterialSetingFragment ops2_RawMaterialSetingFragment = this.target;
        if (ops2_RawMaterialSetingFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        ops2_RawMaterialSetingFragment.mRecyclerview = null;
        ops2_RawMaterialSetingFragment.submit = null;
    }
}
