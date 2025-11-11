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
public class OpsFragment_ViewBinding implements Unbinder {
    private OpsFragment target;
    private View view7f09014a;
    private View view7f09014b;
    private View view7f09014c;
    private View view7f09014d;
    private View view7f09014e;
    private View view7f09014f;
    private View view7f090150;
    private View view7f090151;
    private View view7f090152;
    private View view7f090353;

    @UiThread
    public OpsFragment_ViewBinding(final OpsFragment opsFragment, View view) {
        this.target = opsFragment;
        opsFragment.mTvOps1 = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_ops1, "field 'mTvOps1'", TextView.class);
        opsFragment.mTvOps1En = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_ops1_en, "field 'mTvOps1En'", TextView.class);
        opsFragment.mTvOps2 = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_ops2, "field 'mTvOps2'", TextView.class);
        opsFragment.mTvOps2En = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_ops2_en, "field 'mTvOps2En'", TextView.class);
        opsFragment.mTvOps3 = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_ops3, "field 'mTvOps3'", TextView.class);
        opsFragment.mTvOps3En = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_ops3_en, "field 'mTvOps3En'", TextView.class);
        opsFragment.mTvOps8 = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_ops8, "field 'mTvOps8'", TextView.class);
        opsFragment.mTvOps8En = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_ops8_en, "field 'mTvOps8En'", TextView.class);
        opsFragment.mTvOps4 = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_ops4, "field 'mTvOps4'", TextView.class);
        opsFragment.mTvOps4En = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_ops4_en, "field 'mTvOps4En'", TextView.class);
        opsFragment.mTvOps5 = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_ops5, "field 'mTvOps5'", TextView.class);
        opsFragment.mTvOps5En = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_ops5_en, "field 'mTvOps5En'", TextView.class);
        opsFragment.mTvOps6 = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_ops6, "field 'mTvOps6'", TextView.class);
        opsFragment.mTvOps6En = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_ops6_en, "field 'mTvOps6En'", TextView.class);
        opsFragment.mTvOps7 = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_ops7, "field 'mTvOps7'", TextView.class);
        opsFragment.mTvOps7En = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_ops7_en, "field 'mTvOps7En'", TextView.class);
        opsFragment.mTvOps9 = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_ops9, "field 'mTvOps9'", TextView.class);
        opsFragment.mTvOps9En = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_ops9_en, "field 'mTvOps9En'", TextView.class);
        opsFragment.mTitlelayout = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.titlelayout, "field 'mTitlelayout'", LinearLayout.class);
        opsFragment.mOpsFramelayout = (FrameLayout) Utils.findRequiredViewAsType(view, R.id.ops_framelayout, "field 'mOpsFramelayout'", FrameLayout.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.unlock, "field 'mUnlock' and method 'onClick'");
        opsFragment.mUnlock = (LinearLayout) Utils.castView(findRequiredView, R.id.unlock, "field 'mUnlock'", LinearLayout.class);
        this.view7f090353 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.OpsFragment_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                opsFragment.onClick(view2);
            }
        });
        View findRequiredView2 = Utils.findRequiredView(view, R.id.ll_ops1, "field 'll_ops1' and method 'onClick'");
        opsFragment.ll_ops1 = (LinearLayout) Utils.castView(findRequiredView2, R.id.ll_ops1, "field 'll_ops1'", LinearLayout.class);
        this.view7f09014a = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.OpsFragment_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                opsFragment.onClick(view2);
            }
        });
        View findRequiredView3 = Utils.findRequiredView(view, R.id.ll_ops2, "field 'll_ops2' and method 'onClick'");
        opsFragment.ll_ops2 = (LinearLayout) Utils.castView(findRequiredView3, R.id.ll_ops2, "field 'll_ops2'", LinearLayout.class);
        this.view7f09014b = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.OpsFragment_ViewBinding.3
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                opsFragment.onClick(view2);
            }
        });
        View findRequiredView4 = Utils.findRequiredView(view, R.id.ll_ops3, "field 'll_ops3' and method 'onClick'");
        opsFragment.ll_ops3 = (LinearLayout) Utils.castView(findRequiredView4, R.id.ll_ops3, "field 'll_ops3'", LinearLayout.class);
        this.view7f09014c = findRequiredView4;
        findRequiredView4.setOnClickListener(new DebouncingOnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.OpsFragment_ViewBinding.4
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                opsFragment.onClick(view2);
            }
        });
        View findRequiredView5 = Utils.findRequiredView(view, R.id.ll_ops4, "field 'll_ops4' and method 'onClick'");
        opsFragment.ll_ops4 = (LinearLayout) Utils.castView(findRequiredView5, R.id.ll_ops4, "field 'll_ops4'", LinearLayout.class);
        this.view7f09014d = findRequiredView5;
        findRequiredView5.setOnClickListener(new DebouncingOnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.OpsFragment_ViewBinding.5
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                opsFragment.onClick(view2);
            }
        });
        View findRequiredView6 = Utils.findRequiredView(view, R.id.ll_ops5, "field 'll_ops5' and method 'onClick'");
        opsFragment.ll_ops5 = (LinearLayout) Utils.castView(findRequiredView6, R.id.ll_ops5, "field 'll_ops5'", LinearLayout.class);
        this.view7f09014e = findRequiredView6;
        findRequiredView6.setOnClickListener(new DebouncingOnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.OpsFragment_ViewBinding.6
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                opsFragment.onClick(view2);
            }
        });
        View findRequiredView7 = Utils.findRequiredView(view, R.id.ll_ops6, "field 'll_ops6' and method 'onClick'");
        opsFragment.ll_ops6 = (LinearLayout) Utils.castView(findRequiredView7, R.id.ll_ops6, "field 'll_ops6'", LinearLayout.class);
        this.view7f09014f = findRequiredView7;
        findRequiredView7.setOnClickListener(new DebouncingOnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.OpsFragment_ViewBinding.7
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                opsFragment.onClick(view2);
            }
        });
        View findRequiredView8 = Utils.findRequiredView(view, R.id.ll_ops7, "field 'll_ops7' and method 'onClick'");
        opsFragment.ll_ops7 = (LinearLayout) Utils.castView(findRequiredView8, R.id.ll_ops7, "field 'll_ops7'", LinearLayout.class);
        this.view7f090150 = findRequiredView8;
        findRequiredView8.setOnClickListener(new DebouncingOnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.OpsFragment_ViewBinding.8
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                opsFragment.onClick(view2);
            }
        });
        View findRequiredView9 = Utils.findRequiredView(view, R.id.ll_ops8, "field 'll_ops8' and method 'onClick'");
        opsFragment.ll_ops8 = (LinearLayout) Utils.castView(findRequiredView9, R.id.ll_ops8, "field 'll_ops8'", LinearLayout.class);
        this.view7f090151 = findRequiredView9;
        findRequiredView9.setOnClickListener(new DebouncingOnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.OpsFragment_ViewBinding.9
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                opsFragment.onClick(view2);
            }
        });
        View findRequiredView10 = Utils.findRequiredView(view, R.id.ll_ops9, "field 'll_ops9' and method 'onClick'");
        opsFragment.ll_ops9 = (LinearLayout) Utils.castView(findRequiredView10, R.id.ll_ops9, "field 'll_ops9'", LinearLayout.class);
        this.view7f090152 = findRequiredView10;
        findRequiredView10.setOnClickListener(new DebouncingOnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.OpsFragment_ViewBinding.10
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                opsFragment.onClick(view2);
            }
        });
    }

    @Override // butterknife.Unbinder
    @CallSuper
    public void unbind() {
        OpsFragment opsFragment = this.target;
        if (opsFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        opsFragment.mTvOps1 = null;
        opsFragment.mTvOps1En = null;
        opsFragment.mTvOps2 = null;
        opsFragment.mTvOps2En = null;
        opsFragment.mTvOps3 = null;
        opsFragment.mTvOps3En = null;
        opsFragment.mTvOps8 = null;
        opsFragment.mTvOps8En = null;
        opsFragment.mTvOps4 = null;
        opsFragment.mTvOps4En = null;
        opsFragment.mTvOps5 = null;
        opsFragment.mTvOps5En = null;
        opsFragment.mTvOps6 = null;
        opsFragment.mTvOps6En = null;
        opsFragment.mTvOps7 = null;
        opsFragment.mTvOps7En = null;
        opsFragment.mTvOps9 = null;
        opsFragment.mTvOps9En = null;
        opsFragment.mTitlelayout = null;
        opsFragment.mOpsFramelayout = null;
        opsFragment.mUnlock = null;
        opsFragment.ll_ops1 = null;
        opsFragment.ll_ops2 = null;
        opsFragment.ll_ops3 = null;
        opsFragment.ll_ops4 = null;
        opsFragment.ll_ops5 = null;
        opsFragment.ll_ops6 = null;
        opsFragment.ll_ops7 = null;
        opsFragment.ll_ops8 = null;
        opsFragment.ll_ops9 = null;
        this.view7f090353.setOnClickListener(null);
        this.view7f090353 = null;
        this.view7f09014a.setOnClickListener(null);
        this.view7f09014a = null;
        this.view7f09014b.setOnClickListener(null);
        this.view7f09014b = null;
        this.view7f09014c.setOnClickListener(null);
        this.view7f09014c = null;
        this.view7f09014d.setOnClickListener(null);
        this.view7f09014d = null;
        this.view7f09014e.setOnClickListener(null);
        this.view7f09014e = null;
        this.view7f09014f.setOnClickListener(null);
        this.view7f09014f = null;
        this.view7f090150.setOnClickListener(null);
        this.view7f090150 = null;
        this.view7f090151.setOnClickListener(null);
        this.view7f090151 = null;
        this.view7f090152.setOnClickListener(null);
        this.view7f090152 = null;
    }
}
