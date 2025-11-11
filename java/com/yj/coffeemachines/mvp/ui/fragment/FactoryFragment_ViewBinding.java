package com.yj.coffeemachines.mvp.ui.fragment;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.yj.coffeemachines.R;

/* loaded from: classes.dex */
public class FactoryFragment_ViewBinding implements Unbinder {
    private FactoryFragment target;
    private View view7f090137;
    private View view7f090138;
    private View view7f090353;

    @UiThread
    public FactoryFragment_ViewBinding(final FactoryFragment factoryFragment, View view) {
        this.target = factoryFragment;
        factoryFragment.mTvFactroy1 = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_factroy1, "field 'mTvFactroy1'", TextView.class);
        factoryFragment.mTvFactroy1En = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_factroy1_en, "field 'mTvFactroy1En'", TextView.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.lay01, "field 'mLay01' and method 'onClick'");
        factoryFragment.mLay01 = (LinearLayout) Utils.castView(findRequiredView, R.id.lay01, "field 'mLay01'", LinearLayout.class);
        this.view7f090137 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.FactoryFragment_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                factoryFragment.onClick(view2);
            }
        });
        factoryFragment.mTvFactroy2 = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_factroy2, "field 'mTvFactroy2'", TextView.class);
        factoryFragment.mTvFactroy2En = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_factroy2_en, "field 'mTvFactroy2En'", TextView.class);
        View findRequiredView2 = Utils.findRequiredView(view, R.id.lay02, "field 'mLay02' and method 'onClick'");
        factoryFragment.mLay02 = (LinearLayout) Utils.castView(findRequiredView2, R.id.lay02, "field 'mLay02'", LinearLayout.class);
        this.view7f090138 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.FactoryFragment_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                factoryFragment.onClick(view2);
            }
        });
        factoryFragment.mFactroyFramelayout = (FrameLayout) Utils.findRequiredViewAsType(view, R.id.factroy_framelayout, "field 'mFactroyFramelayout'", FrameLayout.class);
        View findRequiredView3 = Utils.findRequiredView(view, R.id.unlock, "field 'mUnlock' and method 'onClick'");
        factoryFragment.mUnlock = (LinearLayout) Utils.castView(findRequiredView3, R.id.unlock, "field 'mUnlock'", LinearLayout.class);
        this.view7f090353 = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.FactoryFragment_ViewBinding.3
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                factoryFragment.onClick(view2);
            }
        });
    }

    @Override // butterknife.Unbinder
    @CallSuper
    public void unbind() {
        FactoryFragment factoryFragment = this.target;
        if (factoryFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        factoryFragment.mTvFactroy1 = null;
        factoryFragment.mTvFactroy1En = null;
        factoryFragment.mLay01 = null;
        factoryFragment.mTvFactroy2 = null;
        factoryFragment.mTvFactroy2En = null;
        factoryFragment.mLay02 = null;
        factoryFragment.mFactroyFramelayout = null;
        factoryFragment.mUnlock = null;
        this.view7f090137.setOnClickListener(null);
        this.view7f090137 = null;
        this.view7f090138.setOnClickListener(null);
        this.view7f090138 = null;
        this.view7f090353.setOnClickListener(null);
        this.view7f090353 = null;
    }
}
