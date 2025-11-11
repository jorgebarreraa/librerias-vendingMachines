package com.yj.coffeemachines.mvp.ui.fragment;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.constraintlayout.widget.ConstraintLayout;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.yj.coffeemachines.R;
import com.yj.coffeemachines.carousel.AdvanceView;

/* loaded from: classes.dex */
public class Step3Fragment_ViewBinding implements Unbinder {
    private Step3Fragment target;
    private View view7f090060;
    private View view7f090311;
    private View view7f090312;
    private View view7f090313;
    private View view7f090314;
    private View view7f090316;
    private View view7f090317;
    private View view7f090318;
    private View view7f090319;
    private View view7f09031a;
    private View view7f09031b;
    private View view7f09031c;
    private View view7f09031f;
    private View view7f090320;
    private View view7f090321;
    private View view7f090322;
    private View view7f090323;
    private View view7f090324;

    @UiThread
    public Step3Fragment_ViewBinding(final Step3Fragment step3Fragment, View view) {
        this.target = step3Fragment;
        step3Fragment.step3_cl = (ConstraintLayout) Utils.findRequiredViewAsType(view, R.id.step3_cl, "field 'step3_cl'", ConstraintLayout.class);
        step3Fragment.step3_tv_zhifu = (TextView) Utils.findRequiredViewAsType(view, R.id.step3_tv_zhifu, "field 'step3_tv_zhifu'", TextView.class);
        step3Fragment.mBanner = (AdvanceView) Utils.findRequiredViewAsType(view, R.id.banner, "field 'mBanner'", AdvanceView.class);
        step3Fragment.mTvProductname = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_productname, "field 'mTvProductname'", TextView.class);
        step3Fragment.mIvQrcode = (ImageView) Utils.findRequiredViewAsType(view, R.id.iv_qrcode, "field 'mIvQrcode'", ImageView.class);
        step3Fragment.mTvRecivernumber = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_recivernumber, "field 'mTvRecivernumber'", TextView.class);
        step3Fragment.mTvRecivernumberEn = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_recivernumber_en, "field 'mTvRecivernumberEn'", TextView.class);
        step3Fragment.mLayoutPayother = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.layout_payother, "field 'mLayoutPayother'", LinearLayout.class);
        step3Fragment.mLayoutPayCode = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_pay_code, "field 'mLayoutPayCode'", LinearLayout.class);
        step3Fragment.mTvPice = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_pice, "field 'mTvPice'", TextView.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.btn_back, "field 'mBtnBack' and method 'onClick'");
        step3Fragment.mBtnBack = (Button) Utils.castView(findRequiredView, R.id.btn_back, "field 'mBtnBack'", Button.class);
        this.view7f090060 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Step3Fragment_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                step3Fragment.onClick(view2);
            }
        });
        step3Fragment.tvPayCodeTips = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_pay_code_tips, "field 'tvPayCodeTips'", TextView.class);
        step3Fragment.mTvPiceEn = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_pice_en, "field 'mTvPiceEn'", TextView.class);
        step3Fragment.mTvChangenumber = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_changenumber, "field 'mTvChangenumber'", TextView.class);
        step3Fragment.mTvChangenumberEn = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_changenumber_en, "field 'mTvChangenumberEn'", TextView.class);
        step3Fragment.mCountdowntime = (TextView) Utils.findRequiredViewAsType(view, R.id.countdowntime, "field 'mCountdowntime'", TextView.class);
        step3Fragment.constraintLayout3 = (ConstraintLayout) Utils.findRequiredViewAsType(view, R.id.constraintLayout3, "field 'constraintLayout3'", ConstraintLayout.class);
        step3Fragment.constraintLayout2 = (ConstraintLayout) Utils.findRequiredViewAsType(view, R.id.constraintLayout2, "field 'constraintLayout2'", ConstraintLayout.class);
        step3Fragment.step3_cl_main = (ConstraintLayout) Utils.findRequiredViewAsType(view, R.id.step3_cl_main, "field 'step3_cl_main'", ConstraintLayout.class);
        step3Fragment.step3_tv_price = (TextView) Utils.findRequiredViewAsType(view, R.id.step3_tv_price, "field 'step3_tv_price'", TextView.class);
        step3Fragment.step3_tv_zhifufangshi = (TextView) Utils.findRequiredViewAsType(view, R.id.step3_tv_zhifufangshi, "field 'step3_tv_zhifufangshi'", TextView.class);
        View findRequiredView2 = Utils.findRequiredView(view, R.id.tv_pay_bao, "field 'tv_pay_bao' and method 'onClick'");
        step3Fragment.tv_pay_bao = (LinearLayout) Utils.castView(findRequiredView2, R.id.tv_pay_bao, "field 'tv_pay_bao'", LinearLayout.class);
        this.view7f09031c = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Step3Fragment_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                step3Fragment.onClick(view2);
            }
        });
        step3Fragment.step3_view = Utils.findRequiredView(view, R.id.step3_view, "field 'step3_view'");
        View findRequiredView3 = Utils.findRequiredView(view, R.id.tv_pay8, "field 'mTvPay8' and method 'onClick'");
        step3Fragment.mTvPay8 = (LinearLayout) Utils.castView(findRequiredView3, R.id.tv_pay8, "field 'mTvPay8'", LinearLayout.class);
        this.view7f090319 = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Step3Fragment_ViewBinding.3
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                step3Fragment.onClick(view2);
            }
        });
        View findRequiredView4 = Utils.findRequiredView(view, R.id.tv_pay9, "field 'mTvPay9' and method 'onClick'");
        step3Fragment.mTvPay9 = (LinearLayout) Utils.castView(findRequiredView4, R.id.tv_pay9, "field 'mTvPay9'", LinearLayout.class);
        this.view7f09031a = findRequiredView4;
        findRequiredView4.setOnClickListener(new DebouncingOnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Step3Fragment_ViewBinding.4
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                step3Fragment.onClick(view2);
            }
        });
        step3Fragment.tv_pay4_mian = (ImageView) Utils.findRequiredViewAsType(view, R.id.tv_pay4_mian, "field 'tv_pay4_mian'", ImageView.class);
        step3Fragment.mTvErrormsg = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_errormsg, "field 'mTvErrormsg'", TextView.class);
        step3Fragment.mLayoutChange = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.layout_change, "field 'mLayoutChange'", LinearLayout.class);
        step3Fragment.mPayway = (TextView) Utils.findRequiredViewAsType(view, R.id.payway, "field 'mPayway'", TextView.class);
        View findRequiredView5 = Utils.findRequiredView(view, R.id.tv_pay1, "field 'mTvPay1' and method 'onClick'");
        step3Fragment.mTvPay1 = (LinearLayout) Utils.castView(findRequiredView5, R.id.tv_pay1, "field 'mTvPay1'", LinearLayout.class);
        this.view7f090311 = findRequiredView5;
        findRequiredView5.setOnClickListener(new DebouncingOnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Step3Fragment_ViewBinding.5
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                step3Fragment.onClick(view2);
            }
        });
        View findRequiredView6 = Utils.findRequiredView(view, R.id.tv_pay2, "field 'mTvPay2' and method 'onClick'");
        step3Fragment.mTvPay2 = (LinearLayout) Utils.castView(findRequiredView6, R.id.tv_pay2, "field 'mTvPay2'", LinearLayout.class);
        this.view7f090312 = findRequiredView6;
        findRequiredView6.setOnClickListener(new DebouncingOnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Step3Fragment_ViewBinding.6
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                step3Fragment.onClick(view2);
            }
        });
        View findRequiredView7 = Utils.findRequiredView(view, R.id.tv_pay3, "field 'mTvPay3' and method 'onClick'");
        step3Fragment.mTvPay3 = (LinearLayout) Utils.castView(findRequiredView7, R.id.tv_pay3, "field 'mTvPay3'", LinearLayout.class);
        this.view7f090313 = findRequiredView7;
        findRequiredView7.setOnClickListener(new DebouncingOnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Step3Fragment_ViewBinding.7
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                step3Fragment.onClick(view2);
            }
        });
        View findRequiredView8 = Utils.findRequiredView(view, R.id.tv_pay4, "field 'mTvPay4' and method 'onClick'");
        step3Fragment.mTvPay4 = (LinearLayout) Utils.castView(findRequiredView8, R.id.tv_pay4, "field 'mTvPay4'", LinearLayout.class);
        this.view7f090314 = findRequiredView8;
        findRequiredView8.setOnClickListener(new DebouncingOnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Step3Fragment_ViewBinding.8
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                step3Fragment.onClick(view2);
            }
        });
        View findRequiredView9 = Utils.findRequiredView(view, R.id.tv_pay5, "field 'mTvPay5' and method 'onClick'");
        step3Fragment.mTvPay5 = (LinearLayout) Utils.castView(findRequiredView9, R.id.tv_pay5, "field 'mTvPay5'", LinearLayout.class);
        this.view7f090316 = findRequiredView9;
        findRequiredView9.setOnClickListener(new DebouncingOnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Step3Fragment_ViewBinding.9
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                step3Fragment.onClick(view2);
            }
        });
        View findRequiredView10 = Utils.findRequiredView(view, R.id.tv_pay6, "field 'mTvPay6' and method 'onClick'");
        step3Fragment.mTvPay6 = (LinearLayout) Utils.castView(findRequiredView10, R.id.tv_pay6, "field 'mTvPay6'", LinearLayout.class);
        this.view7f090317 = findRequiredView10;
        findRequiredView10.setOnClickListener(new DebouncingOnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Step3Fragment_ViewBinding.10
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                step3Fragment.onClick(view2);
            }
        });
        View findRequiredView11 = Utils.findRequiredView(view, R.id.tv_pay7, "field 'mTvPay7' and method 'onClick'");
        step3Fragment.mTvPay7 = (LinearLayout) Utils.castView(findRequiredView11, R.id.tv_pay7, "field 'mTvPay7'", LinearLayout.class);
        this.view7f090318 = findRequiredView11;
        findRequiredView11.setOnClickListener(new DebouncingOnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Step3Fragment_ViewBinding.11
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                step3Fragment.onClick(view2);
            }
        });
        View findRequiredView12 = Utils.findRequiredView(view, R.id.tv_pay_saobei_zhengsao_13, "field 'tv_pay_saobei_zhengsao_13' and method 'onClick'");
        step3Fragment.tv_pay_saobei_zhengsao_13 = (LinearLayout) Utils.castView(findRequiredView12, R.id.tv_pay_saobei_zhengsao_13, "field 'tv_pay_saobei_zhengsao_13'", LinearLayout.class);
        this.view7f090322 = findRequiredView12;
        findRequiredView12.setOnClickListener(new DebouncingOnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Step3Fragment_ViewBinding.12
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                step3Fragment.onClick(view2);
            }
        });
        View findRequiredView13 = Utils.findRequiredView(view, R.id.tv_pay_saobei_fansao_13, "field 'tv_pay_saobei_fansao_13' and method 'onClick'");
        step3Fragment.tv_pay_saobei_fansao_13 = (LinearLayout) Utils.castView(findRequiredView13, R.id.tv_pay_saobei_fansao_13, "field 'tv_pay_saobei_fansao_13'", LinearLayout.class);
        this.view7f090321 = findRequiredView13;
        findRequiredView13.setOnClickListener(new DebouncingOnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Step3Fragment_ViewBinding.13
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                step3Fragment.onClick(view2);
            }
        });
        View findRequiredView14 = Utils.findRequiredView(view, R.id.tv_pay_t50, "field 'tv_pay_t50' and method 'onClick'");
        step3Fragment.tv_pay_t50 = (LinearLayout) Utils.castView(findRequiredView14, R.id.tv_pay_t50, "field 'tv_pay_t50'", LinearLayout.class);
        this.view7f090323 = findRequiredView14;
        findRequiredView14.setOnClickListener(new DebouncingOnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Step3Fragment_ViewBinding.14
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                step3Fragment.onClick(view2);
            }
        });
        View findRequiredView15 = Utils.findRequiredView(view, R.id.tv_pay_agt, "field 'tv_pay_agt' and method 'onClick'");
        step3Fragment.tv_pay_agt = (LinearLayout) Utils.castView(findRequiredView15, R.id.tv_pay_agt, "field 'tv_pay_agt'", LinearLayout.class);
        this.view7f09031b = findRequiredView15;
        findRequiredView15.setOnClickListener(new DebouncingOnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Step3Fragment_ViewBinding.15
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                step3Fragment.onClick(view2);
            }
        });
        View findRequiredView16 = Utils.findRequiredView(view, R.id.tv_pay_north_star, "field 'payNorthStar' and method 'onClick'");
        step3Fragment.payNorthStar = (LinearLayout) Utils.castView(findRequiredView16, R.id.tv_pay_north_star, "field 'payNorthStar'", LinearLayout.class);
        this.view7f090320 = findRequiredView16;
        findRequiredView16.setOnClickListener(new DebouncingOnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Step3Fragment_ViewBinding.16
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                step3Fragment.onClick(view2);
            }
        });
        View findRequiredView17 = Utils.findRequiredView(view, R.id.tv_pay_newcape, "field 'payNewCape' and method 'onClick'");
        step3Fragment.payNewCape = (LinearLayout) Utils.castView(findRequiredView17, R.id.tv_pay_newcape, "field 'payNewCape'", LinearLayout.class);
        this.view7f09031f = findRequiredView17;
        findRequiredView17.setOnClickListener(new DebouncingOnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Step3Fragment_ViewBinding.17
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                step3Fragment.onClick(view2);
            }
        });
        View findRequiredView18 = Utils.findRequiredView(view, R.id.tv_pay_tp, "field 'tv_pay_tp' and method 'onClick'");
        step3Fragment.tv_pay_tp = (LinearLayout) Utils.castView(findRequiredView18, R.id.tv_pay_tp, "field 'tv_pay_tp'", LinearLayout.class);
        this.view7f090324 = findRequiredView18;
        findRequiredView18.setOnClickListener(new DebouncingOnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Step3Fragment_ViewBinding.18
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                step3Fragment.onClick(view2);
            }
        });
    }

    @Override // butterknife.Unbinder
    @CallSuper
    public void unbind() {
        Step3Fragment step3Fragment = this.target;
        if (step3Fragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        step3Fragment.step3_cl = null;
        step3Fragment.step3_tv_zhifu = null;
        step3Fragment.mBanner = null;
        step3Fragment.mTvProductname = null;
        step3Fragment.mIvQrcode = null;
        step3Fragment.mTvRecivernumber = null;
        step3Fragment.mTvRecivernumberEn = null;
        step3Fragment.mLayoutPayother = null;
        step3Fragment.mLayoutPayCode = null;
        step3Fragment.mTvPice = null;
        step3Fragment.mBtnBack = null;
        step3Fragment.tvPayCodeTips = null;
        step3Fragment.mTvPiceEn = null;
        step3Fragment.mTvChangenumber = null;
        step3Fragment.mTvChangenumberEn = null;
        step3Fragment.mCountdowntime = null;
        step3Fragment.constraintLayout3 = null;
        step3Fragment.constraintLayout2 = null;
        step3Fragment.step3_cl_main = null;
        step3Fragment.step3_tv_price = null;
        step3Fragment.step3_tv_zhifufangshi = null;
        step3Fragment.tv_pay_bao = null;
        step3Fragment.step3_view = null;
        step3Fragment.mTvPay8 = null;
        step3Fragment.mTvPay9 = null;
        step3Fragment.tv_pay4_mian = null;
        step3Fragment.mTvErrormsg = null;
        step3Fragment.mLayoutChange = null;
        step3Fragment.mPayway = null;
        step3Fragment.mTvPay1 = null;
        step3Fragment.mTvPay2 = null;
        step3Fragment.mTvPay3 = null;
        step3Fragment.mTvPay4 = null;
        step3Fragment.mTvPay5 = null;
        step3Fragment.mTvPay6 = null;
        step3Fragment.mTvPay7 = null;
        step3Fragment.tv_pay_saobei_zhengsao_13 = null;
        step3Fragment.tv_pay_saobei_fansao_13 = null;
        step3Fragment.tv_pay_t50 = null;
        step3Fragment.tv_pay_agt = null;
        step3Fragment.payNorthStar = null;
        step3Fragment.payNewCape = null;
        step3Fragment.tv_pay_tp = null;
        this.view7f090060.setOnClickListener(null);
        this.view7f090060 = null;
        this.view7f09031c.setOnClickListener(null);
        this.view7f09031c = null;
        this.view7f090319.setOnClickListener(null);
        this.view7f090319 = null;
        this.view7f09031a.setOnClickListener(null);
        this.view7f09031a = null;
        this.view7f090311.setOnClickListener(null);
        this.view7f090311 = null;
        this.view7f090312.setOnClickListener(null);
        this.view7f090312 = null;
        this.view7f090313.setOnClickListener(null);
        this.view7f090313 = null;
        this.view7f090314.setOnClickListener(null);
        this.view7f090314 = null;
        this.view7f090316.setOnClickListener(null);
        this.view7f090316 = null;
        this.view7f090317.setOnClickListener(null);
        this.view7f090317 = null;
        this.view7f090318.setOnClickListener(null);
        this.view7f090318 = null;
        this.view7f090322.setOnClickListener(null);
        this.view7f090322 = null;
        this.view7f090321.setOnClickListener(null);
        this.view7f090321 = null;
        this.view7f090323.setOnClickListener(null);
        this.view7f090323 = null;
        this.view7f09031b.setOnClickListener(null);
        this.view7f09031b = null;
        this.view7f090320.setOnClickListener(null);
        this.view7f090320 = null;
        this.view7f09031f.setOnClickListener(null);
        this.view7f09031f = null;
        this.view7f090324.setOnClickListener(null);
        this.view7f090324 = null;
    }
}
