package com.yj.coffeemachines.mvp.ui.fragment;

import android.view.View;
import android.widget.LinearLayout;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.yj.coffeemachines.R;

/* loaded from: classes.dex */
public class Ops1_RawMaterialAddFragment_ViewBinding implements Unbinder {
    private Ops1_RawMaterialAddFragment target;
    private View view7f090045;
    private View view7f090046;
    private View view7f090047;
    private View view7f090048;
    private View view7f090049;

    @UiThread
    public Ops1_RawMaterialAddFragment_ViewBinding(final Ops1_RawMaterialAddFragment ops1_RawMaterialAddFragment, View view) {
        this.target = ops1_RawMaterialAddFragment;
        ops1_RawMaterialAddFragment.mRecyclerview = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.recyclerview, "field 'mRecyclerview'", RecyclerView.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.add100, "field 'mAdd100' and method 'onClick'");
        ops1_RawMaterialAddFragment.mAdd100 = (LinearLayout) Utils.castView(findRequiredView, R.id.add100, "field 'mAdd100'", LinearLayout.class);
        this.view7f090046 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Ops1_RawMaterialAddFragment_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                ops1_RawMaterialAddFragment.onClick(view2);
            }
        });
        View findRequiredView2 = Utils.findRequiredView(view, R.id.add75, "field 'mAdd75' and method 'onClick'");
        ops1_RawMaterialAddFragment.mAdd75 = (LinearLayout) Utils.castView(findRequiredView2, R.id.add75, "field 'mAdd75'", LinearLayout.class);
        this.view7f090049 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Ops1_RawMaterialAddFragment_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                ops1_RawMaterialAddFragment.onClick(view2);
            }
        });
        View findRequiredView3 = Utils.findRequiredView(view, R.id.add50, "field 'mAdd50' and method 'onClick'");
        ops1_RawMaterialAddFragment.mAdd50 = (LinearLayout) Utils.castView(findRequiredView3, R.id.add50, "field 'mAdd50'", LinearLayout.class);
        this.view7f090048 = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Ops1_RawMaterialAddFragment_ViewBinding.3
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                ops1_RawMaterialAddFragment.onClick(view2);
            }
        });
        View findRequiredView4 = Utils.findRequiredView(view, R.id.add25, "field 'mAdd25' and method 'onClick'");
        ops1_RawMaterialAddFragment.mAdd25 = (LinearLayout) Utils.castView(findRequiredView4, R.id.add25, "field 'mAdd25'", LinearLayout.class);
        this.view7f090047 = findRequiredView4;
        findRequiredView4.setOnClickListener(new DebouncingOnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Ops1_RawMaterialAddFragment_ViewBinding.4
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                ops1_RawMaterialAddFragment.onClick(view2);
            }
        });
        View findRequiredView5 = Utils.findRequiredView(view, R.id.add0, "field 'mAdd0' and method 'onClick'");
        ops1_RawMaterialAddFragment.mAdd0 = (LinearLayout) Utils.castView(findRequiredView5, R.id.add0, "field 'mAdd0'", LinearLayout.class);
        this.view7f090045 = findRequiredView5;
        findRequiredView5.setOnClickListener(new DebouncingOnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Ops1_RawMaterialAddFragment_ViewBinding.5
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                ops1_RawMaterialAddFragment.onClick(view2);
            }
        });
        ops1_RawMaterialAddFragment.mFootlayout = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.footlayout, "field 'mFootlayout'", LinearLayout.class);
    }

    @Override // butterknife.Unbinder
    @CallSuper
    public void unbind() {
        Ops1_RawMaterialAddFragment ops1_RawMaterialAddFragment = this.target;
        if (ops1_RawMaterialAddFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        ops1_RawMaterialAddFragment.mRecyclerview = null;
        ops1_RawMaterialAddFragment.mAdd100 = null;
        ops1_RawMaterialAddFragment.mAdd75 = null;
        ops1_RawMaterialAddFragment.mAdd50 = null;
        ops1_RawMaterialAddFragment.mAdd25 = null;
        ops1_RawMaterialAddFragment.mAdd0 = null;
        ops1_RawMaterialAddFragment.mFootlayout = null;
        this.view7f090046.setOnClickListener(null);
        this.view7f090046 = null;
        this.view7f090049.setOnClickListener(null);
        this.view7f090049 = null;
        this.view7f090048.setOnClickListener(null);
        this.view7f090048 = null;
        this.view7f090047.setOnClickListener(null);
        this.view7f090047 = null;
        this.view7f090045.setOnClickListener(null);
        this.view7f090045 = null;
    }
}
