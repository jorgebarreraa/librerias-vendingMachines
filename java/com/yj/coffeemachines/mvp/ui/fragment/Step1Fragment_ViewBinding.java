package com.yj.coffeemachines.mvp.ui.fragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.yj.coffeemachines.R;

/* loaded from: classes.dex */
public class Step1Fragment_ViewBinding implements Unbinder {
    private Step1Fragment target;
    private View view7f090099;
    private View view7f0900b1;
    private View view7f090124;
    private View view7f090127;
    private View view7f090237;
    private View view7f09035f;

    @UiThread
    public Step1Fragment_ViewBinding(final Step1Fragment step1Fragment, View view) {
        this.target = step1Fragment;
        View findRequiredView = Utils.findRequiredView(view, R.id.cl_step1_getdrink, "field 'cl_step1_getdrink' and method 'onClick'");
        step1Fragment.cl_step1_getdrink = (ConstraintLayout) Utils.castView(findRequiredView, R.id.cl_step1_getdrink, "field 'cl_step1_getdrink'", ConstraintLayout.class);
        this.view7f090099 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Step1Fragment_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                step1Fragment.onClick(view2);
            }
        });
        step1Fragment.mRecyclerview = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.recyclerview, "field 'mRecyclerview'", RecyclerView.class);
        step1Fragment.mFootLayout = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.foot_layout, "field 'mFootLayout'", LinearLayout.class);
        step1Fragment.step1_cl_main = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.step1_cl_main, "field 'step1_cl_main'", LinearLayout.class);
        step1Fragment.mIvJoinmember = (ImageView) Utils.findRequiredViewAsType(view, R.id.iv_joinmember, "field 'mIvJoinmember'", ImageView.class);
        View findRequiredView2 = Utils.findRequiredView(view, R.id.vip_layout, "field 'mVipLayout' and method 'onClick'");
        step1Fragment.mVipLayout = (LinearLayout) Utils.castView(findRequiredView2, R.id.vip_layout, "field 'mVipLayout'", LinearLayout.class);
        this.view7f09035f = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Step1Fragment_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                step1Fragment.onClick(view2);
            }
        });
        step1Fragment.mTvStep1 = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_step1, "field 'mTvStep1'", TextView.class);
        step1Fragment.mTvStep1En = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_step1_en, "field 'mTvStep1En'", TextView.class);
        step1Fragment.mEmptyview = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.emptyview, "field 'mEmptyview'", LinearLayout.class);
        step1Fragment.mTvErrormesage = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_errormesage, "field 'mTvErrormesage'", TextView.class);
        View findRequiredView3 = Utils.findRequiredView(view, R.id.countdowntime, "field 'mCountdowntime' and method 'onClick'");
        step1Fragment.mCountdowntime = (TextView) Utils.castView(findRequiredView3, R.id.countdowntime, "field 'mCountdowntime'", TextView.class);
        this.view7f0900b1 = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Step1Fragment_ViewBinding.3
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                step1Fragment.onClick(view2);
            }
        });
        step1Fragment.step1_tv_join = (TextView) Utils.findRequiredViewAsType(view, R.id.step1_tv_join, "field 'step1_tv_join'", TextView.class);
        step1Fragment.step1_tv_members = (TextView) Utils.findRequiredViewAsType(view, R.id.step1_tv_members, "field 'step1_tv_members'", TextView.class);
        step1Fragment.step1_tv_get = (TextView) Utils.findRequiredViewAsType(view, R.id.step1_tv_get, "field 'step1_tv_get'", TextView.class);
        step1Fragment.step1_tv_drinks = (TextView) Utils.findRequiredViewAsType(view, R.id.step1_tv_drinks, "field 'step1_tv_drinks'", TextView.class);
        View findRequiredView4 = Utils.findRequiredView(view, R.id.iv_last, "field 'mIvLast' and method 'onClick'");
        step1Fragment.mIvLast = (ImageView) Utils.castView(findRequiredView4, R.id.iv_last, "field 'mIvLast'", ImageView.class);
        this.view7f090124 = findRequiredView4;
        findRequiredView4.setOnClickListener(new DebouncingOnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Step1Fragment_ViewBinding.4
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                step1Fragment.onClick(view2);
            }
        });
        View findRequiredView5 = Utils.findRequiredView(view, R.id.iv_next, "field 'mIvNext' and method 'onClick'");
        step1Fragment.mIvNext = (ImageView) Utils.castView(findRequiredView5, R.id.iv_next, "field 'mIvNext'", ImageView.class);
        this.view7f090127 = findRequiredView5;
        findRequiredView5.setOnClickListener(new DebouncingOnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Step1Fragment_ViewBinding.5
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                step1Fragment.onClick(view2);
            }
        });
        View findRequiredView6 = Utils.findRequiredView(view, R.id.step_ll_getdrinks, "method 'onClick'");
        this.view7f090237 = findRequiredView6;
        findRequiredView6.setOnClickListener(new DebouncingOnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Step1Fragment_ViewBinding.6
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                step1Fragment.onClick(view2);
            }
        });
    }

    @Override // butterknife.Unbinder
    @CallSuper
    public void unbind() {
        Step1Fragment step1Fragment = this.target;
        if (step1Fragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        step1Fragment.cl_step1_getdrink = null;
        step1Fragment.mRecyclerview = null;
        step1Fragment.mFootLayout = null;
        step1Fragment.step1_cl_main = null;
        step1Fragment.mIvJoinmember = null;
        step1Fragment.mVipLayout = null;
        step1Fragment.mTvStep1 = null;
        step1Fragment.mTvStep1En = null;
        step1Fragment.mEmptyview = null;
        step1Fragment.mTvErrormesage = null;
        step1Fragment.mCountdowntime = null;
        step1Fragment.step1_tv_join = null;
        step1Fragment.step1_tv_members = null;
        step1Fragment.step1_tv_get = null;
        step1Fragment.step1_tv_drinks = null;
        step1Fragment.mIvLast = null;
        step1Fragment.mIvNext = null;
        this.view7f090099.setOnClickListener(null);
        this.view7f090099 = null;
        this.view7f09035f.setOnClickListener(null);
        this.view7f09035f = null;
        this.view7f0900b1.setOnClickListener(null);
        this.view7f0900b1 = null;
        this.view7f090124.setOnClickListener(null);
        this.view7f090124 = null;
        this.view7f090127.setOnClickListener(null);
        this.view7f090127 = null;
        this.view7f090237.setOnClickListener(null);
        this.view7f090237 = null;
    }
}
