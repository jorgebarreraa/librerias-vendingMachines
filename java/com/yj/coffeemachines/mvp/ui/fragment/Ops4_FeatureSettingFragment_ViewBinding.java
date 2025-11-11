package com.yj.coffeemachines.mvp.ui.fragment;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.constraintlayout.widget.ConstraintLayout;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.yj.coffeemachines.R;

/* loaded from: classes.dex */
public class Ops4_FeatureSettingFragment_ViewBinding implements Unbinder {
    private Ops4_FeatureSettingFragment target;
    private View view7f090068;
    private View view7f09006f;
    private View view7f090071;
    private View view7f09007f;
    private View view7f090088;

    @UiThread
    public Ops4_FeatureSettingFragment_ViewBinding(final Ops4_FeatureSettingFragment ops4_FeatureSettingFragment, View view) {
        this.target = ops4_FeatureSettingFragment;
        ops4_FeatureSettingFragment.ops4_cl_getdrink = (ConstraintLayout) Utils.findRequiredViewAsType(view, R.id.ops4_cl_getdrink, "field 'ops4_cl_getdrink'", ConstraintLayout.class);
        ops4_FeatureSettingFragment.ops4_swith_getdrink = (Switch) Utils.findRequiredViewAsType(view, R.id.ops4_swith_getdrink, "field 'ops4_swith_getdrink'", Switch.class);
        ops4_FeatureSettingFragment.ops4_cl_vip = (ConstraintLayout) Utils.findRequiredViewAsType(view, R.id.ops4_cl_vip, "field 'ops4_cl_vip'", ConstraintLayout.class);
        ops4_FeatureSettingFragment.mTvVip = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_vip, "field 'mTvVip'", TextView.class);
        ops4_FeatureSettingFragment.mTvVipEn = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_vip_en, "field 'mTvVipEn'", TextView.class);
        ops4_FeatureSettingFragment.mSwithVip = (Switch) Utils.findRequiredViewAsType(view, R.id.swith_vip, "field 'mSwithVip'", Switch.class);
        ops4_FeatureSettingFragment.ops4_cl_ad = (ConstraintLayout) Utils.findRequiredViewAsType(view, R.id.ops4_cl_ad, "field 'ops4_cl_ad'", ConstraintLayout.class);
        ops4_FeatureSettingFragment.mTvAd = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_ad, "field 'mTvAd'", TextView.class);
        ops4_FeatureSettingFragment.mTvAdEn = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_ad_en, "field 'mTvAdEn'", TextView.class);
        ops4_FeatureSettingFragment.mSwithAd = (Switch) Utils.findRequiredViewAsType(view, R.id.swith_ad, "field 'mSwithAd'", Switch.class);
        ops4_FeatureSettingFragment.ops4_cl_nopay = (ConstraintLayout) Utils.findRequiredViewAsType(view, R.id.ops4_cl_nopay, "field 'ops4_cl_nopay'", ConstraintLayout.class);
        ops4_FeatureSettingFragment.mTvNopay = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_nopay, "field 'mTvNopay'", TextView.class);
        ops4_FeatureSettingFragment.mTvNopayEn = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_nopay_en, "field 'mTvNopayEn'", TextView.class);
        ops4_FeatureSettingFragment.mSwithNopay = (Switch) Utils.findRequiredViewAsType(view, R.id.swith_nopay, "field 'mSwithNopay'", Switch.class);
        ops4_FeatureSettingFragment.ops4_cl_adjust = (ConstraintLayout) Utils.findRequiredViewAsType(view, R.id.ops4_cl_adjust, "field 'ops4_cl_adjust'", ConstraintLayout.class);
        ops4_FeatureSettingFragment.mTvAdjust = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_adjust, "field 'mTvAdjust'", TextView.class);
        ops4_FeatureSettingFragment.mTvAdjustEn = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_adjust_en, "field 'mTvAdjustEn'", TextView.class);
        ops4_FeatureSettingFragment.mSwithAdjust = (Switch) Utils.findRequiredViewAsType(view, R.id.swith_adjust, "field 'mSwithAdjust'", Switch.class);
        ops4_FeatureSettingFragment.ops4_cl_limit = (ConstraintLayout) Utils.findRequiredViewAsType(view, R.id.ops4_cl_limit, "field 'ops4_cl_limit'", ConstraintLayout.class);
        ops4_FeatureSettingFragment.mTvLimit = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_limit, "field 'mTvLimit'", TextView.class);
        ops4_FeatureSettingFragment.mEtCups = (EditText) Utils.findRequiredViewAsType(view, R.id.et_cups, "field 'mEtCups'", EditText.class);
        ops4_FeatureSettingFragment.mTvLimitEn = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_limit_en, "field 'mTvLimitEn'", TextView.class);
        ops4_FeatureSettingFragment.mSwithLimit = (Switch) Utils.findRequiredViewAsType(view, R.id.swith_limit, "field 'mSwithLimit'", Switch.class);
        ops4_FeatureSettingFragment.mTvCashcodeEn = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_cashcode_en, "field 'mTvCashcodeEn'", TextView.class);
        ops4_FeatureSettingFragment.mEtCashcode = (EditText) Utils.findRequiredViewAsType(view, R.id.et_cashcode, "field 'mEtCashcode'", EditText.class);
        ops4_FeatureSettingFragment.mTvRate = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_rate, "field 'mTvRate'", TextView.class);
        ops4_FeatureSettingFragment.mTvRateEn = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_rate_en, "field 'mTvRateEn'", TextView.class);
        ops4_FeatureSettingFragment.mEtRate = (EditText) Utils.findRequiredViewAsType(view, R.id.et_rate, "field 'mEtRate'", EditText.class);
        ops4_FeatureSettingFragment.mTvRateY = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_rate_y, "field 'mTvRateY'", TextView.class);
        ops4_FeatureSettingFragment.mTvRateEnY = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_rate_en_y, "field 'mTvRateEnY'", TextView.class);
        ops4_FeatureSettingFragment.mEtRateY = (EditText) Utils.findRequiredViewAsType(view, R.id.et_rate_y, "field 'mEtRateY'", EditText.class);
        ops4_FeatureSettingFragment.mTvChange = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_change, "field 'mTvChange'", TextView.class);
        ops4_FeatureSettingFragment.mTvChangeEn = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_change_en, "field 'mTvChangeEn'", TextView.class);
        ops4_FeatureSettingFragment.mEtChange = (EditText) Utils.findRequiredViewAsType(view, R.id.et_change, "field 'mEtChange'", EditText.class);
        ops4_FeatureSettingFragment.mTvPos = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_pos, "field 'mTvPos'", TextView.class);
        ops4_FeatureSettingFragment.mTvPosEn = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_pos_en, "field 'mTvPosEn'", TextView.class);
        ops4_FeatureSettingFragment.mEtPos = (EditText) Utils.findRequiredViewAsType(view, R.id.et_pos, "field 'mEtPos'", EditText.class);
        ops4_FeatureSettingFragment.mEtMdb = (EditText) Utils.findRequiredViewAsType(view, R.id.et_mdb, "field 'mEtMdb'", EditText.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.btn_show, "field 'mBtnShow' and method 'onClick'");
        ops4_FeatureSettingFragment.mBtnShow = (Button) Utils.castView(findRequiredView, R.id.btn_show, "field 'mBtnShow'", Button.class);
        this.view7f09007f = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Ops4_FeatureSettingFragment_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                ops4_FeatureSettingFragment.onClick(view2);
            }
        });
        View findRequiredView2 = Utils.findRequiredView(view, R.id.btn_hint, "field 'mBtnHint' and method 'onClick'");
        ops4_FeatureSettingFragment.mBtnHint = (Button) Utils.castView(findRequiredView2, R.id.btn_hint, "field 'mBtnHint'", Button.class);
        this.view7f090071 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Ops4_FeatureSettingFragment_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                ops4_FeatureSettingFragment.onClick(view2);
            }
        });
        View findRequiredView3 = Utils.findRequiredView(view, R.id.btn_exitapp, "field 'mBtnExitapp' and method 'onClick'");
        ops4_FeatureSettingFragment.mBtnExitapp = (Button) Utils.castView(findRequiredView3, R.id.btn_exitapp, "field 'mBtnExitapp'", Button.class);
        this.view7f09006f = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Ops4_FeatureSettingFragment_ViewBinding.3
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                ops4_FeatureSettingFragment.onClick(view2);
            }
        });
        View findRequiredView4 = Utils.findRequiredView(view, R.id.btn_uplog, "field 'mBtnUplog' and method 'onClick'");
        ops4_FeatureSettingFragment.mBtnUplog = (Button) Utils.castView(findRequiredView4, R.id.btn_uplog, "field 'mBtnUplog'", Button.class);
        this.view7f090088 = findRequiredView4;
        findRequiredView4.setOnClickListener(new DebouncingOnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Ops4_FeatureSettingFragment_ViewBinding.4
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                ops4_FeatureSettingFragment.onClick(view2);
            }
        });
        ops4_FeatureSettingFragment.mParentLayout = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.parent_layout, "field 'mParentLayout'", LinearLayout.class);
        View findRequiredView5 = Utils.findRequiredView(view, R.id.btn_doubleactivity, "field 'mBtnDoubleactivity' and method 'onClick'");
        ops4_FeatureSettingFragment.mBtnDoubleactivity = (Button) Utils.castView(findRequiredView5, R.id.btn_doubleactivity, "field 'mBtnDoubleactivity'", Button.class);
        this.view7f090068 = findRequiredView5;
        findRequiredView5.setOnClickListener(new DebouncingOnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Ops4_FeatureSettingFragment_ViewBinding.5
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                ops4_FeatureSettingFragment.onClick(view2);
            }
        });
        ops4_FeatureSettingFragment.group_pay = (RadioGroup) Utils.findRequiredViewAsType(view, R.id.group_pay, "field 'group_pay'", RadioGroup.class);
        ops4_FeatureSettingFragment.rbLevel2 = (RadioButton) Utils.findRequiredViewAsType(view, R.id.rb_Level2, "field 'rbLevel2'", RadioButton.class);
        ops4_FeatureSettingFragment.rbLevel3 = (RadioButton) Utils.findRequiredViewAsType(view, R.id.rb_Level3, "field 'rbLevel3'", RadioButton.class);
    }

    @Override // butterknife.Unbinder
    @CallSuper
    public void unbind() {
        Ops4_FeatureSettingFragment ops4_FeatureSettingFragment = this.target;
        if (ops4_FeatureSettingFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        ops4_FeatureSettingFragment.ops4_cl_getdrink = null;
        ops4_FeatureSettingFragment.ops4_swith_getdrink = null;
        ops4_FeatureSettingFragment.ops4_cl_vip = null;
        ops4_FeatureSettingFragment.mTvVip = null;
        ops4_FeatureSettingFragment.mTvVipEn = null;
        ops4_FeatureSettingFragment.mSwithVip = null;
        ops4_FeatureSettingFragment.ops4_cl_ad = null;
        ops4_FeatureSettingFragment.mTvAd = null;
        ops4_FeatureSettingFragment.mTvAdEn = null;
        ops4_FeatureSettingFragment.mSwithAd = null;
        ops4_FeatureSettingFragment.ops4_cl_nopay = null;
        ops4_FeatureSettingFragment.mTvNopay = null;
        ops4_FeatureSettingFragment.mTvNopayEn = null;
        ops4_FeatureSettingFragment.mSwithNopay = null;
        ops4_FeatureSettingFragment.ops4_cl_adjust = null;
        ops4_FeatureSettingFragment.mTvAdjust = null;
        ops4_FeatureSettingFragment.mTvAdjustEn = null;
        ops4_FeatureSettingFragment.mSwithAdjust = null;
        ops4_FeatureSettingFragment.ops4_cl_limit = null;
        ops4_FeatureSettingFragment.mTvLimit = null;
        ops4_FeatureSettingFragment.mEtCups = null;
        ops4_FeatureSettingFragment.mTvLimitEn = null;
        ops4_FeatureSettingFragment.mSwithLimit = null;
        ops4_FeatureSettingFragment.mTvCashcodeEn = null;
        ops4_FeatureSettingFragment.mEtCashcode = null;
        ops4_FeatureSettingFragment.mTvRate = null;
        ops4_FeatureSettingFragment.mTvRateEn = null;
        ops4_FeatureSettingFragment.mEtRate = null;
        ops4_FeatureSettingFragment.mTvRateY = null;
        ops4_FeatureSettingFragment.mTvRateEnY = null;
        ops4_FeatureSettingFragment.mEtRateY = null;
        ops4_FeatureSettingFragment.mTvChange = null;
        ops4_FeatureSettingFragment.mTvChangeEn = null;
        ops4_FeatureSettingFragment.mEtChange = null;
        ops4_FeatureSettingFragment.mTvPos = null;
        ops4_FeatureSettingFragment.mTvPosEn = null;
        ops4_FeatureSettingFragment.mEtPos = null;
        ops4_FeatureSettingFragment.mEtMdb = null;
        ops4_FeatureSettingFragment.mBtnShow = null;
        ops4_FeatureSettingFragment.mBtnHint = null;
        ops4_FeatureSettingFragment.mBtnExitapp = null;
        ops4_FeatureSettingFragment.mBtnUplog = null;
        ops4_FeatureSettingFragment.mParentLayout = null;
        ops4_FeatureSettingFragment.mBtnDoubleactivity = null;
        ops4_FeatureSettingFragment.group_pay = null;
        ops4_FeatureSettingFragment.rbLevel2 = null;
        ops4_FeatureSettingFragment.rbLevel3 = null;
        this.view7f09007f.setOnClickListener(null);
        this.view7f09007f = null;
        this.view7f090071.setOnClickListener(null);
        this.view7f090071 = null;
        this.view7f09006f.setOnClickListener(null);
        this.view7f09006f = null;
        this.view7f090088.setOnClickListener(null);
        this.view7f090088 = null;
        this.view7f090068.setOnClickListener(null);
        this.view7f090068 = null;
    }
}
