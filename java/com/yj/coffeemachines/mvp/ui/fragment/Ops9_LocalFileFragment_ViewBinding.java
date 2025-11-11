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
public class Ops9_LocalFileFragment_ViewBinding implements Unbinder {
    private Ops9_LocalFileFragment target;

    @UiThread
    public Ops9_LocalFileFragment_ViewBinding(Ops9_LocalFileFragment ops9_LocalFileFragment, View view) {
        this.target = ops9_LocalFileFragment;
        ops9_LocalFileFragment.rvList = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.rv_list, "field 'rvList'", RecyclerView.class);
        ops9_LocalFileFragment.llRefresh = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_refresh, "field 'llRefresh'", LinearLayout.class);
    }

    @Override // butterknife.Unbinder
    @CallSuper
    public void unbind() {
        Ops9_LocalFileFragment ops9_LocalFileFragment = this.target;
        if (ops9_LocalFileFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        ops9_LocalFileFragment.rvList = null;
        ops9_LocalFileFragment.llRefresh = null;
    }
}
