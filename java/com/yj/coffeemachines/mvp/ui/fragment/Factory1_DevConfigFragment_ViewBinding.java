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
public class Factory1_DevConfigFragment_ViewBinding implements Unbinder {
    private Factory1_DevConfigFragment target;
    private View view7f090076;
    private View view7f09023a;

    @UiThread
    public Factory1_DevConfigFragment_ViewBinding(final Factory1_DevConfigFragment factory1_DevConfigFragment, View view) {
        this.target = factory1_DevConfigFragment;
        factory1_DevConfigFragment.ops4_cl_language = (ConstraintLayout) Utils.findRequiredViewAsType(view, R.id.ops4_cl_language, "field 'ops4_cl_language'", ConstraintLayout.class);
        factory1_DevConfigFragment.ops4_swith_language = (Switch) Utils.findRequiredViewAsType(view, R.id.ops4_swith_language, "field 'ops4_swith_language'", Switch.class);
        factory1_DevConfigFragment.ops4_cl_clearCurInterval = (ConstraintLayout) Utils.findRequiredViewAsType(view, R.id.ops4_cl_clearCurInterval, "field 'ops4_cl_clearCurInterval'", ConstraintLayout.class);
        factory1_DevConfigFragment.et_clearCurInterval = (EditText) Utils.findRequiredViewAsType(view, R.id.et_clearCurInterval, "field 'et_clearCurInterval'", EditText.class);
        factory1_DevConfigFragment.swith_clearCurInterval = (Switch) Utils.findRequiredViewAsType(view, R.id.swith_clearCurInterval, "field 'swith_clearCurInterval'", Switch.class);
        factory1_DevConfigFragment.mTvOrder1 = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_order1, "field 'mTvOrder1'", TextView.class);
        factory1_DevConfigFragment.mTvOrder1En = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_order1_en, "field 'mTvOrder1En'", TextView.class);
        factory1_DevConfigFragment.mSwithOrder1 = (Switch) Utils.findRequiredViewAsType(view, R.id.swith_order1, "field 'mSwithOrder1'", Switch.class);
        factory1_DevConfigFragment.mTvOrder2 = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_order2, "field 'mTvOrder2'", TextView.class);
        factory1_DevConfigFragment.mTvOrder2En = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_order2_en, "field 'mTvOrder2En'", TextView.class);
        factory1_DevConfigFragment.mSwithOrder2 = (Switch) Utils.findRequiredViewAsType(view, R.id.swith_order2, "field 'mSwithOrder2'", Switch.class);
        factory1_DevConfigFragment.mTvOrder3 = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_order3, "field 'mTvOrder3'", TextView.class);
        factory1_DevConfigFragment.mTvOrder3En = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_order3_en, "field 'mTvOrder3En'", TextView.class);
        factory1_DevConfigFragment.mSwithOrder3 = (Switch) Utils.findRequiredViewAsType(view, R.id.swith_order3, "field 'mSwithOrder3'", Switch.class);
        factory1_DevConfigFragment.mTvOrder4 = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_order4, "field 'mTvOrder4'", TextView.class);
        factory1_DevConfigFragment.mTvOrder4En = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_order4_en, "field 'mTvOrder4En'", TextView.class);
        factory1_DevConfigFragment.mSwithOrder4 = (Switch) Utils.findRequiredViewAsType(view, R.id.swith_order4, "field 'mSwithOrder4'", Switch.class);
        factory1_DevConfigFragment.mTvOrder5 = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_order5, "field 'mTvOrder5'", TextView.class);
        factory1_DevConfigFragment.mTvOrder5En = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_order5_en, "field 'mTvOrder5En'", TextView.class);
        factory1_DevConfigFragment.mSwithOrder5 = (Switch) Utils.findRequiredViewAsType(view, R.id.swith_order5, "field 'mSwithOrder5'", Switch.class);
        factory1_DevConfigFragment.mTvOrder6 = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_order6, "field 'mTvOrder6'", TextView.class);
        factory1_DevConfigFragment.mTvOrder6En = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_order6_en, "field 'mTvOrder6En'", TextView.class);
        factory1_DevConfigFragment.mSwithOrder6 = (Switch) Utils.findRequiredViewAsType(view, R.id.swith_order6, "field 'mSwithOrder6'", Switch.class);
        factory1_DevConfigFragment.mTvOrder7 = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_order7, "field 'mTvOrder7'", TextView.class);
        factory1_DevConfigFragment.mTvOrder7En = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_order7_en, "field 'mTvOrder7En'", TextView.class);
        factory1_DevConfigFragment.mSwithOrder7 = (Switch) Utils.findRequiredViewAsType(view, R.id.swith_order7, "field 'mSwithOrder7'", Switch.class);
        factory1_DevConfigFragment.mTvOrder1L = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_order1_l, "field 'mTvOrder1L'", TextView.class);
        factory1_DevConfigFragment.mTvOrder1EnL = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_order1_en_l, "field 'mTvOrder1EnL'", TextView.class);
        factory1_DevConfigFragment.mSwithOrder1L = (Switch) Utils.findRequiredViewAsType(view, R.id.swith_order1_l, "field 'mSwithOrder1L'", Switch.class);
        factory1_DevConfigFragment.mTvOrder2L = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_order2_l, "field 'mTvOrder2L'", TextView.class);
        factory1_DevConfigFragment.mTvOrder2EnL = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_order2_en_l, "field 'mTvOrder2EnL'", TextView.class);
        factory1_DevConfigFragment.mSwithOrder2L = (Switch) Utils.findRequiredViewAsType(view, R.id.swith_order2_l, "field 'mSwithOrder2L'", Switch.class);
        factory1_DevConfigFragment.mTvOrder3L = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_order3_l, "field 'mTvOrder3L'", TextView.class);
        factory1_DevConfigFragment.mTvOrder3EnL = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_order3_en_l, "field 'mTvOrder3EnL'", TextView.class);
        factory1_DevConfigFragment.mSwithOrder3L = (Switch) Utils.findRequiredViewAsType(view, R.id.swith_order3_l, "field 'mSwithOrder3L'", Switch.class);
        factory1_DevConfigFragment.mTvOrder4L = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_order4_l, "field 'mTvOrder4L'", TextView.class);
        factory1_DevConfigFragment.mTvOrder4EnL = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_order4_en_l, "field 'mTvOrder4EnL'", TextView.class);
        factory1_DevConfigFragment.mSwithOrder4L = (Switch) Utils.findRequiredViewAsType(view, R.id.swith_order4_l, "field 'mSwithOrder4L'", Switch.class);
        factory1_DevConfigFragment.mTvOrder5L = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_order5_l, "field 'mTvOrder5L'", TextView.class);
        factory1_DevConfigFragment.mTvOrder5EnL = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_order5_en_l, "field 'mTvOrder5EnL'", TextView.class);
        factory1_DevConfigFragment.mSwithOrder5L = (Switch) Utils.findRequiredViewAsType(view, R.id.swith_order5_l, "field 'mSwithOrder5L'", Switch.class);
        factory1_DevConfigFragment.mTvOrder6L = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_order6_l, "field 'mTvOrder6L'", TextView.class);
        factory1_DevConfigFragment.mTvOrder6EnL = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_order6_en_l, "field 'mTvOrder6EnL'", TextView.class);
        factory1_DevConfigFragment.mSwithOrder6L = (Switch) Utils.findRequiredViewAsType(view, R.id.swith_order6_l, "field 'mSwithOrder6L'", Switch.class);
        factory1_DevConfigFragment.mTvOrder7L = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_order7_l, "field 'mTvOrder7L'", TextView.class);
        factory1_DevConfigFragment.mTvOrder7EnL = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_order7_en_l, "field 'mTvOrder7EnL'", TextView.class);
        factory1_DevConfigFragment.mSwithOrder7L = (Switch) Utils.findRequiredViewAsType(view, R.id.swith_order7_l, "field 'mSwithOrder7L'", Switch.class);
        factory1_DevConfigFragment.mTvOrder8 = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_order8, "field 'mTvOrder8'", TextView.class);
        factory1_DevConfigFragment.mTvOrder8En = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_order8_en, "field 'mTvOrder8En'", TextView.class);
        factory1_DevConfigFragment.mTimestart = (Button) Utils.findRequiredViewAsType(view, R.id.timestart, "field 'mTimestart'", Button.class);
        factory1_DevConfigFragment.mTimestop = (Button) Utils.findRequiredViewAsType(view, R.id.timestop, "field 'mTimestop'", Button.class);
        factory1_DevConfigFragment.mSwithOrder8L = (Switch) Utils.findRequiredViewAsType(view, R.id.swith_order8_l, "field 'mSwithOrder8L'", Switch.class);
        factory1_DevConfigFragment.mTvOrder8L = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_order8_l, "field 'mTvOrder8L'", TextView.class);
        factory1_DevConfigFragment.mTvOrder8EnL = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_order8_en_l, "field 'mTvOrder8EnL'", TextView.class);
        factory1_DevConfigFragment.mParentTimegroup = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.parent_timegroup, "field 'mParentTimegroup'", LinearLayout.class);
        factory1_DevConfigFragment.mSwithOrder2LIce = (Switch) Utils.findRequiredViewAsType(view, R.id.swith_order2_l_ice, "field 'mSwithOrder2LIce'", Switch.class);
        factory1_DevConfigFragment.mTvOrder2LIce = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_order2_l_ice, "field 'mTvOrder2LIce'", TextView.class);
        factory1_DevConfigFragment.mTvOrder2EnLIce = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_order2_en_l_ice, "field 'mTvOrder2EnLIce'", TextView.class);
        factory1_DevConfigFragment.mSwithOrder27 = (Switch) Utils.findRequiredViewAsType(view, R.id.swith_order27, "field 'mSwithOrder27'", Switch.class);
        factory1_DevConfigFragment.mTvOrder27 = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_order27, "field 'mTvOrder27'", TextView.class);
        factory1_DevConfigFragment.mTvOrder27En = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_order27_en, "field 'mTvOrder27En'", TextView.class);
        factory1_DevConfigFragment.ops4_cl_cash = (ConstraintLayout) Utils.findRequiredViewAsType(view, R.id.ops4_cl_cash, "field 'ops4_cl_cash'", ConstraintLayout.class);
        factory1_DevConfigFragment.mTvCash = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_cash, "field 'mTvCash'", TextView.class);
        factory1_DevConfigFragment.mTvCashEn = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_cash_en, "field 'mTvCashEn'", TextView.class);
        factory1_DevConfigFragment.mSwithCash = (Switch) Utils.findRequiredViewAsType(view, R.id.swith_cash, "field 'mSwithCash'", Switch.class);
        factory1_DevConfigFragment.ops4_cl_isChange = (ConstraintLayout) Utils.findRequiredViewAsType(view, R.id.ops4_cl_isChange, "field 'ops4_cl_isChange'", ConstraintLayout.class);
        factory1_DevConfigFragment.mTvIschange = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_ischange, "field 'mTvIschange'", TextView.class);
        factory1_DevConfigFragment.mTvIschangeEn = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_ischange_en, "field 'mTvIschangeEn'", TextView.class);
        factory1_DevConfigFragment.mSwithIschange = (Switch) Utils.findRequiredViewAsType(view, R.id.swith_ischange, "field 'mSwithIschange'", Switch.class);
        factory1_DevConfigFragment.ops4_cl_cardpay = (ConstraintLayout) Utils.findRequiredViewAsType(view, R.id.ops4_cl_cardpay, "field 'ops4_cl_cardpay'", ConstraintLayout.class);
        factory1_DevConfigFragment.mTvCardpay = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_cardpay, "field 'mTvCardpay'", TextView.class);
        factory1_DevConfigFragment.mTvCardpayEn = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_cardpay_en, "field 'mTvCardpayEn'", TextView.class);
        factory1_DevConfigFragment.mSwithCardpay = (Switch) Utils.findRequiredViewAsType(view, R.id.swith_cardpay, "field 'mSwithCardpay'", Switch.class);
        factory1_DevConfigFragment.ops4_cl_saobeipay = (ConstraintLayout) Utils.findRequiredViewAsType(view, R.id.ops4_cl_saobeipay, "field 'ops4_cl_saobeipay'", ConstraintLayout.class);
        factory1_DevConfigFragment.swith_saobeipay = (Switch) Utils.findRequiredViewAsType(view, R.id.swith_saobeipay, "field 'swith_saobeipay'", Switch.class);
        factory1_DevConfigFragment.ops4_cl_NewCapePay = (ConstraintLayout) Utils.findRequiredViewAsType(view, R.id.ops4_cl_newcapepay, "field 'ops4_cl_NewCapePay'", ConstraintLayout.class);
        factory1_DevConfigFragment.swith_newCapePay = (Switch) Utils.findRequiredViewAsType(view, R.id.swith_newcapepay, "field 'swith_newCapePay'", Switch.class);
        factory1_DevConfigFragment.group_pay = (RadioGroup) Utils.findRequiredViewAsType(view, R.id.group_pay, "field 'group_pay'", RadioGroup.class);
        factory1_DevConfigFragment.rbUniversalPay = (RadioButton) Utils.findRequiredViewAsType(view, R.id.rb_universalpay, "field 'rbUniversalPay'", RadioButton.class);
        factory1_DevConfigFragment.rbMdbPay = (RadioButton) Utils.findRequiredViewAsType(view, R.id.rb_mdbpay, "field 'rbMdbPay'", RadioButton.class);
        factory1_DevConfigFragment.rbNewCar = (RadioButton) Utils.findRequiredViewAsType(view, R.id.rb_newcar, "field 'rbNewCar'", RadioButton.class);
        factory1_DevConfigFragment.rbOldCar = (RadioButton) Utils.findRequiredViewAsType(view, R.id.rb_oldcar, "field 'rbOldCar'", RadioButton.class);
        factory1_DevConfigFragment.ops4_cl_ignore_qianmen = (ConstraintLayout) Utils.findRequiredViewAsType(view, R.id.ops4_cl_ignore_qianmen, "field 'ops4_cl_ignore_qianmen'", ConstraintLayout.class);
        factory1_DevConfigFragment.swith_ignore_qianmen = (Switch) Utils.findRequiredViewAsType(view, R.id.swith_ignore_qianmen, "field 'swith_ignore_qianmen'", Switch.class);
        factory1_DevConfigFragment.ops4_cl_v3_lbq = (ConstraintLayout) Utils.findRequiredViewAsType(view, R.id.ops4_cl_v3_lbq, "field 'ops4_cl_v3_lbq'", ConstraintLayout.class);
        factory1_DevConfigFragment.swith_v3_lbq = (Switch) Utils.findRequiredViewAsType(view, R.id.swith_v3_lbq, "field 'swith_v3_lbq'", Switch.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.btn_language, "field 'btnLanguage' and method 'onClick'");
        factory1_DevConfigFragment.btnLanguage = (Button) Utils.castView(findRequiredView, R.id.btn_language, "field 'btnLanguage'", Button.class);
        this.view7f090076 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Factory1_DevConfigFragment_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                factory1_DevConfigFragment.onClick(view2);
            }
        });
        View findRequiredView2 = Utils.findRequiredView(view, R.id.submit, "field 'submit' and method 'onClick'");
        factory1_DevConfigFragment.submit = (LinearLayout) Utils.castView(findRequiredView2, R.id.submit, "field 'submit'", LinearLayout.class);
        this.view7f09023a = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Factory1_DevConfigFragment_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                factory1_DevConfigFragment.onClick(view2);
            }
        });
    }

    @Override // butterknife.Unbinder
    @CallSuper
    public void unbind() {
        Factory1_DevConfigFragment factory1_DevConfigFragment = this.target;
        if (factory1_DevConfigFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        factory1_DevConfigFragment.ops4_cl_language = null;
        factory1_DevConfigFragment.ops4_swith_language = null;
        factory1_DevConfigFragment.ops4_cl_clearCurInterval = null;
        factory1_DevConfigFragment.et_clearCurInterval = null;
        factory1_DevConfigFragment.swith_clearCurInterval = null;
        factory1_DevConfigFragment.mTvOrder1 = null;
        factory1_DevConfigFragment.mTvOrder1En = null;
        factory1_DevConfigFragment.mSwithOrder1 = null;
        factory1_DevConfigFragment.mTvOrder2 = null;
        factory1_DevConfigFragment.mTvOrder2En = null;
        factory1_DevConfigFragment.mSwithOrder2 = null;
        factory1_DevConfigFragment.mTvOrder3 = null;
        factory1_DevConfigFragment.mTvOrder3En = null;
        factory1_DevConfigFragment.mSwithOrder3 = null;
        factory1_DevConfigFragment.mTvOrder4 = null;
        factory1_DevConfigFragment.mTvOrder4En = null;
        factory1_DevConfigFragment.mSwithOrder4 = null;
        factory1_DevConfigFragment.mTvOrder5 = null;
        factory1_DevConfigFragment.mTvOrder5En = null;
        factory1_DevConfigFragment.mSwithOrder5 = null;
        factory1_DevConfigFragment.mTvOrder6 = null;
        factory1_DevConfigFragment.mTvOrder6En = null;
        factory1_DevConfigFragment.mSwithOrder6 = null;
        factory1_DevConfigFragment.mTvOrder7 = null;
        factory1_DevConfigFragment.mTvOrder7En = null;
        factory1_DevConfigFragment.mSwithOrder7 = null;
        factory1_DevConfigFragment.mTvOrder1L = null;
        factory1_DevConfigFragment.mTvOrder1EnL = null;
        factory1_DevConfigFragment.mSwithOrder1L = null;
        factory1_DevConfigFragment.mTvOrder2L = null;
        factory1_DevConfigFragment.mTvOrder2EnL = null;
        factory1_DevConfigFragment.mSwithOrder2L = null;
        factory1_DevConfigFragment.mTvOrder3L = null;
        factory1_DevConfigFragment.mTvOrder3EnL = null;
        factory1_DevConfigFragment.mSwithOrder3L = null;
        factory1_DevConfigFragment.mTvOrder4L = null;
        factory1_DevConfigFragment.mTvOrder4EnL = null;
        factory1_DevConfigFragment.mSwithOrder4L = null;
        factory1_DevConfigFragment.mTvOrder5L = null;
        factory1_DevConfigFragment.mTvOrder5EnL = null;
        factory1_DevConfigFragment.mSwithOrder5L = null;
        factory1_DevConfigFragment.mTvOrder6L = null;
        factory1_DevConfigFragment.mTvOrder6EnL = null;
        factory1_DevConfigFragment.mSwithOrder6L = null;
        factory1_DevConfigFragment.mTvOrder7L = null;
        factory1_DevConfigFragment.mTvOrder7EnL = null;
        factory1_DevConfigFragment.mSwithOrder7L = null;
        factory1_DevConfigFragment.mTvOrder8 = null;
        factory1_DevConfigFragment.mTvOrder8En = null;
        factory1_DevConfigFragment.mTimestart = null;
        factory1_DevConfigFragment.mTimestop = null;
        factory1_DevConfigFragment.mSwithOrder8L = null;
        factory1_DevConfigFragment.mTvOrder8L = null;
        factory1_DevConfigFragment.mTvOrder8EnL = null;
        factory1_DevConfigFragment.mParentTimegroup = null;
        factory1_DevConfigFragment.mSwithOrder2LIce = null;
        factory1_DevConfigFragment.mTvOrder2LIce = null;
        factory1_DevConfigFragment.mTvOrder2EnLIce = null;
        factory1_DevConfigFragment.mSwithOrder27 = null;
        factory1_DevConfigFragment.mTvOrder27 = null;
        factory1_DevConfigFragment.mTvOrder27En = null;
        factory1_DevConfigFragment.ops4_cl_cash = null;
        factory1_DevConfigFragment.mTvCash = null;
        factory1_DevConfigFragment.mTvCashEn = null;
        factory1_DevConfigFragment.mSwithCash = null;
        factory1_DevConfigFragment.ops4_cl_isChange = null;
        factory1_DevConfigFragment.mTvIschange = null;
        factory1_DevConfigFragment.mTvIschangeEn = null;
        factory1_DevConfigFragment.mSwithIschange = null;
        factory1_DevConfigFragment.ops4_cl_cardpay = null;
        factory1_DevConfigFragment.mTvCardpay = null;
        factory1_DevConfigFragment.mTvCardpayEn = null;
        factory1_DevConfigFragment.mSwithCardpay = null;
        factory1_DevConfigFragment.ops4_cl_saobeipay = null;
        factory1_DevConfigFragment.swith_saobeipay = null;
        factory1_DevConfigFragment.ops4_cl_NewCapePay = null;
        factory1_DevConfigFragment.swith_newCapePay = null;
        factory1_DevConfigFragment.group_pay = null;
        factory1_DevConfigFragment.rbUniversalPay = null;
        factory1_DevConfigFragment.rbMdbPay = null;
        factory1_DevConfigFragment.rbNewCar = null;
        factory1_DevConfigFragment.rbOldCar = null;
        factory1_DevConfigFragment.ops4_cl_ignore_qianmen = null;
        factory1_DevConfigFragment.swith_ignore_qianmen = null;
        factory1_DevConfigFragment.ops4_cl_v3_lbq = null;
        factory1_DevConfigFragment.swith_v3_lbq = null;
        factory1_DevConfigFragment.btnLanguage = null;
        factory1_DevConfigFragment.submit = null;
        this.view7f090076.setOnClickListener(null);
        this.view7f090076 = null;
        this.view7f09023a.setOnClickListener(null);
        this.view7f09023a = null;
    }
}
