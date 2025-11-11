package com.yj.coffeemachines.mvp.ui.fragment;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.yj.coffeemachines.R;

/* loaded from: classes.dex */
public class Factory2_SeralPortConfigFragment_ViewBinding implements Unbinder {
    private Factory2_SeralPortConfigFragment target;
    private View view7f0901ec;
    private View view7f090211;
    private View view7f090212;
    private View view7f090213;

    @UiThread
    public Factory2_SeralPortConfigFragment_ViewBinding(final Factory2_SeralPortConfigFragment factory2_SeralPortConfigFragment, View view) {
        this.target = factory2_SeralPortConfigFragment;
        factory2_SeralPortConfigFragment.mTvFactroy1 = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_factroy1, "field 'mTvFactroy1'", TextView.class);
        factory2_SeralPortConfigFragment.mTvFactroy1En = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_factroy1_en, "field 'mTvFactroy1En'", TextView.class);
        factory2_SeralPortConfigFragment.mLay01 = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.lay01, "field 'mLay01'", LinearLayout.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.spinner1, "field 'mSpinner1' and method 'onClick'");
        factory2_SeralPortConfigFragment.mSpinner1 = (TextView) Utils.castView(findRequiredView, R.id.spinner1, "field 'mSpinner1'", TextView.class);
        this.view7f090211 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Factory2_SeralPortConfigFragment_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                factory2_SeralPortConfigFragment.onClick(view2);
            }
        });
        factory2_SeralPortConfigFragment.mTvFactroy2 = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_factroy2, "field 'mTvFactroy2'", TextView.class);
        factory2_SeralPortConfigFragment.mTvFactroy2En = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_factroy2_en, "field 'mTvFactroy2En'", TextView.class);
        factory2_SeralPortConfigFragment.mLay02 = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.lay02, "field 'mLay02'", LinearLayout.class);
        View findRequiredView2 = Utils.findRequiredView(view, R.id.spinner2, "field 'mSpinner2' and method 'onClick'");
        factory2_SeralPortConfigFragment.mSpinner2 = (TextView) Utils.castView(findRequiredView2, R.id.spinner2, "field 'mSpinner2'", TextView.class);
        this.view7f090212 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Factory2_SeralPortConfigFragment_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                factory2_SeralPortConfigFragment.onClick(view2);
            }
        });
        factory2_SeralPortConfigFragment.mTvFactroy3 = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_factroy3, "field 'mTvFactroy3'", TextView.class);
        factory2_SeralPortConfigFragment.mTvFactroy3En = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_factroy3_en, "field 'mTvFactroy3En'", TextView.class);
        factory2_SeralPortConfigFragment.mLay03 = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.lay03, "field 'mLay03'", LinearLayout.class);
        View findRequiredView3 = Utils.findRequiredView(view, R.id.spinner3, "field 'mSpinner3' and method 'onClick'");
        factory2_SeralPortConfigFragment.mSpinner3 = (TextView) Utils.castView(findRequiredView3, R.id.spinner3, "field 'mSpinner3'", TextView.class);
        this.view7f090213 = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Factory2_SeralPortConfigFragment_ViewBinding.3
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                factory2_SeralPortConfigFragment.onClick(view2);
            }
        });
        View findRequiredView4 = Utils.findRequiredView(view, R.id.save, "field 'mSave' and method 'onClick'");
        factory2_SeralPortConfigFragment.mSave = (LinearLayout) Utils.castView(findRequiredView4, R.id.save, "field 'mSave'", LinearLayout.class);
        this.view7f0901ec = findRequiredView4;
        findRequiredView4.setOnClickListener(new DebouncingOnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Factory2_SeralPortConfigFragment_ViewBinding.4
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                factory2_SeralPortConfigFragment.onClick(view2);
            }
        });
        factory2_SeralPortConfigFragment.mSwithPort1 = (Switch) Utils.findRequiredViewAsType(view, R.id.swith_port1, "field 'mSwithPort1'", Switch.class);
        factory2_SeralPortConfigFragment.mSwithPort2 = (Switch) Utils.findRequiredViewAsType(view, R.id.swith_port2, "field 'mSwithPort2'", Switch.class);
        factory2_SeralPortConfigFragment.mSwithPort3 = (Switch) Utils.findRequiredViewAsType(view, R.id.swith_port3, "field 'mSwithPort3'", Switch.class);
    }

    @Override // butterknife.Unbinder
    @CallSuper
    public void unbind() {
        Factory2_SeralPortConfigFragment factory2_SeralPortConfigFragment = this.target;
        if (factory2_SeralPortConfigFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        factory2_SeralPortConfigFragment.mTvFactroy1 = null;
        factory2_SeralPortConfigFragment.mTvFactroy1En = null;
        factory2_SeralPortConfigFragment.mLay01 = null;
        factory2_SeralPortConfigFragment.mSpinner1 = null;
        factory2_SeralPortConfigFragment.mTvFactroy2 = null;
        factory2_SeralPortConfigFragment.mTvFactroy2En = null;
        factory2_SeralPortConfigFragment.mLay02 = null;
        factory2_SeralPortConfigFragment.mSpinner2 = null;
        factory2_SeralPortConfigFragment.mTvFactroy3 = null;
        factory2_SeralPortConfigFragment.mTvFactroy3En = null;
        factory2_SeralPortConfigFragment.mLay03 = null;
        factory2_SeralPortConfigFragment.mSpinner3 = null;
        factory2_SeralPortConfigFragment.mSave = null;
        factory2_SeralPortConfigFragment.mSwithPort1 = null;
        factory2_SeralPortConfigFragment.mSwithPort2 = null;
        factory2_SeralPortConfigFragment.mSwithPort3 = null;
        this.view7f090211.setOnClickListener(null);
        this.view7f090211 = null;
        this.view7f090212.setOnClickListener(null);
        this.view7f090212 = null;
        this.view7f090213.setOnClickListener(null);
        this.view7f090213 = null;
        this.view7f0901ec.setOnClickListener(null);
        this.view7f0901ec = null;
    }
}
