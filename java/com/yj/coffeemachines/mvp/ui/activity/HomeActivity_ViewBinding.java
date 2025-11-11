package com.yj.coffeemachines.mvp.ui.activity;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.cardview.widget.CardView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.yj.coffeemachines.R;

/* loaded from: classes.dex */
public class HomeActivity_ViewBinding implements Unbinder {
    private HomeActivity target;
    private View view7f09005e;
    private View view7f09012f;
    private View view7f090130;
    private View view7f090131;
    private View view7f090132;
    private View view7f090133;
    private View view7f090325;
    private View view7f090326;
    private View view7f090340;
    private View view7f090341;

    @UiThread
    public HomeActivity_ViewBinding(HomeActivity homeActivity) {
        this(homeActivity, homeActivity.getWindow().getDecorView());
    }

    @UiThread
    public HomeActivity_ViewBinding(final HomeActivity homeActivity, View view) {
        this.target = homeActivity;
        View findRequiredView = Utils.findRequiredView(view, R.id.tv_phone, "field 'mTvPhone' and method 'onClick'");
        homeActivity.mTvPhone = (TextView) Utils.castView(findRequiredView, R.id.tv_phone, "field 'mTvPhone'", TextView.class);
        this.view7f090325 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.activity.HomeActivity_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                homeActivity.onClick(view2);
            }
        });
        View findRequiredView2 = Utils.findRequiredView(view, R.id.tv_phone1, "field 'mTvPhone1' and method 'onClick'");
        homeActivity.mTvPhone1 = (TextView) Utils.castView(findRequiredView2, R.id.tv_phone1, "field 'mTvPhone1'", TextView.class);
        this.view7f090326 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.activity.HomeActivity_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                homeActivity.onClick(view2);
            }
        });
        homeActivity.mIvSn = (ImageView) Utils.findRequiredViewAsType(view, R.id.iv_sn, "field 'mIvSn'", ImageView.class);
        View findRequiredView3 = Utils.findRequiredView(view, R.id.tv_sn, "field 'mTvSn' and method 'onClick'");
        homeActivity.mTvSn = (TextView) Utils.castView(findRequiredView3, R.id.tv_sn, "field 'mTvSn'", TextView.class);
        this.view7f090340 = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.activity.HomeActivity_ViewBinding.3
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                homeActivity.onClick(view2);
            }
        });
        View findRequiredView4 = Utils.findRequiredView(view, R.id.tv_sn1, "field 'mTvSn1' and method 'onClick'");
        homeActivity.mTvSn1 = (TextView) Utils.castView(findRequiredView4, R.id.tv_sn1, "field 'mTvSn1'", TextView.class);
        this.view7f090341 = findRequiredView4;
        findRequiredView4.setOnClickListener(new DebouncingOnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.activity.HomeActivity_ViewBinding.4
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                homeActivity.onClick(view2);
            }
        });
        homeActivity.ivLogo = (ImageView) Utils.findRequiredViewAsType(view, R.id.iv_logo, "field 'ivLogo'", ImageView.class);
        View findRequiredView5 = Utils.findRequiredView(view, R.id.iv_step1, "field 'mIvStep1' and method 'onClick'");
        homeActivity.mIvStep1 = (ImageView) Utils.castView(findRequiredView5, R.id.iv_step1, "field 'mIvStep1'", ImageView.class);
        this.view7f09012f = findRequiredView5;
        findRequiredView5.setOnClickListener(new DebouncingOnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.activity.HomeActivity_ViewBinding.5
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                homeActivity.onClick(view2);
            }
        });
        View findRequiredView6 = Utils.findRequiredView(view, R.id.iv_step2, "field 'mIvStep2' and method 'onClick'");
        homeActivity.mIvStep2 = (ImageView) Utils.castView(findRequiredView6, R.id.iv_step2, "field 'mIvStep2'", ImageView.class);
        this.view7f090130 = findRequiredView6;
        findRequiredView6.setOnClickListener(new DebouncingOnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.activity.HomeActivity_ViewBinding.6
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                homeActivity.onClick(view2);
            }
        });
        View findRequiredView7 = Utils.findRequiredView(view, R.id.iv_step3, "field 'mIvStep3' and method 'onClick'");
        homeActivity.mIvStep3 = (ImageView) Utils.castView(findRequiredView7, R.id.iv_step3, "field 'mIvStep3'", ImageView.class);
        this.view7f090131 = findRequiredView7;
        findRequiredView7.setOnClickListener(new DebouncingOnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.activity.HomeActivity_ViewBinding.7
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                homeActivity.onClick(view2);
            }
        });
        View findRequiredView8 = Utils.findRequiredView(view, R.id.btn_ai, "field 'mBtnAi' and method 'onClick'");
        homeActivity.mBtnAi = (Button) Utils.castView(findRequiredView8, R.id.btn_ai, "field 'mBtnAi'", Button.class);
        this.view7f09005e = findRequiredView8;
        findRequiredView8.setOnClickListener(new DebouncingOnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.activity.HomeActivity_ViewBinding.8
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                homeActivity.onClick(view2);
            }
        });
        View findRequiredView9 = Utils.findRequiredView(view, R.id.iv_step4, "field 'mIvStep4' and method 'onClick'");
        homeActivity.mIvStep4 = (ImageView) Utils.castView(findRequiredView9, R.id.iv_step4, "field 'mIvStep4'", ImageView.class);
        this.view7f090132 = findRequiredView9;
        findRequiredView9.setOnClickListener(new DebouncingOnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.activity.HomeActivity_ViewBinding.9
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                homeActivity.onClick(view2);
            }
        });
        View findRequiredView10 = Utils.findRequiredView(view, R.id.iv_step5, "field 'mIvStep5' and method 'onClick'");
        homeActivity.mIvStep5 = (ImageView) Utils.castView(findRequiredView10, R.id.iv_step5, "field 'mIvStep5'", ImageView.class);
        this.view7f090133 = findRequiredView10;
        findRequiredView10.setOnClickListener(new DebouncingOnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.activity.HomeActivity_ViewBinding.10
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                homeActivity.onClick(view2);
            }
        });
        homeActivity.mhome_tv_step1 = (TextView) Utils.findRequiredViewAsType(view, R.id.home_tv_step1, "field 'mhome_tv_step1'", TextView.class);
        homeActivity.mhome_tv_step2 = (TextView) Utils.findRequiredViewAsType(view, R.id.home_tv_step2, "field 'mhome_tv_step2'", TextView.class);
        homeActivity.mhome_tv_step3 = (TextView) Utils.findRequiredViewAsType(view, R.id.home_tv_step3, "field 'mhome_tv_step3'", TextView.class);
        homeActivity.mhome_tv_step4 = (TextView) Utils.findRequiredViewAsType(view, R.id.home_tv_step4, "field 'mhome_tv_step4'", TextView.class);
        homeActivity.mhome_tv_step5 = (TextView) Utils.findRequiredViewAsType(view, R.id.home_tv_step5, "field 'mhome_tv_step5'", TextView.class);
        homeActivity.cardviewLocation = (CardView) Utils.findRequiredViewAsType(view, R.id.cardview_location, "field 'cardviewLocation'", CardView.class);
    }

    @Override // butterknife.Unbinder
    @CallSuper
    public void unbind() {
        HomeActivity homeActivity = this.target;
        if (homeActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        homeActivity.mTvPhone = null;
        homeActivity.mTvPhone1 = null;
        homeActivity.mIvSn = null;
        homeActivity.mTvSn = null;
        homeActivity.mTvSn1 = null;
        homeActivity.ivLogo = null;
        homeActivity.mIvStep1 = null;
        homeActivity.mIvStep2 = null;
        homeActivity.mIvStep3 = null;
        homeActivity.mBtnAi = null;
        homeActivity.mIvStep4 = null;
        homeActivity.mIvStep5 = null;
        homeActivity.mhome_tv_step1 = null;
        homeActivity.mhome_tv_step2 = null;
        homeActivity.mhome_tv_step3 = null;
        homeActivity.mhome_tv_step4 = null;
        homeActivity.mhome_tv_step5 = null;
        homeActivity.cardviewLocation = null;
        this.view7f090325.setOnClickListener(null);
        this.view7f090325 = null;
        this.view7f090326.setOnClickListener(null);
        this.view7f090326 = null;
        this.view7f090340.setOnClickListener(null);
        this.view7f090340 = null;
        this.view7f090341.setOnClickListener(null);
        this.view7f090341 = null;
        this.view7f09012f.setOnClickListener(null);
        this.view7f09012f = null;
        this.view7f090130.setOnClickListener(null);
        this.view7f090130 = null;
        this.view7f090131.setOnClickListener(null);
        this.view7f090131 = null;
        this.view7f09005e.setOnClickListener(null);
        this.view7f09005e = null;
        this.view7f090132.setOnClickListener(null);
        this.view7f090132 = null;
        this.view7f090133.setOnClickListener(null);
        this.view7f090133 = null;
    }
}
