package com.yj.coffeemachines.mvp.ui.fragment;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.yj.coffeemachines.R;

/* loaded from: classes.dex */
public class Ops5_FeatureTestFragment_ViewBinding implements Unbinder {
    private Ops5_FeatureTestFragment target;
    private View view7f090059;
    private View view7f09005a;
    private View view7f09005b;
    private View view7f09005d;
    private View view7f090062;
    private View view7f090063;
    private View view7f090064;
    private View view7f090066;
    private View view7f090069;
    private View view7f09006a;
    private View view7f09006b;
    private View view7f09006c;
    private View view7f09006d;
    private View view7f09006e;
    private View view7f090072;
    private View view7f090073;
    private View view7f090074;
    private View view7f090075;
    private View view7f090078;
    private View view7f09007a;
    private View view7f09007c;
    private View view7f09007d;
    private View view7f09007e;
    private View view7f090081;
    private View view7f090082;
    private View view7f090083;
    private View view7f090084;
    private View view7f090085;
    private View view7f090086;

    @UiThread
    public Ops5_FeatureTestFragment_ViewBinding(final Ops5_FeatureTestFragment ops5_FeatureTestFragment, View view) {
        this.target = ops5_FeatureTestFragment;
        View findRequiredView = Utils.findRequiredView(view, R.id.btn_clean1, "field 'mBtnClean1' and method 'onClick'");
        ops5_FeatureTestFragment.mBtnClean1 = (Button) Utils.castView(findRequiredView, R.id.btn_clean1, "field 'mBtnClean1'", Button.class);
        this.view7f090062 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Ops5_FeatureTestFragment_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                ops5_FeatureTestFragment.onClick(view2);
            }
        });
        View findRequiredView2 = Utils.findRequiredView(view, R.id.btn_clean2, "field 'mBtnClean2' and method 'onClick'");
        ops5_FeatureTestFragment.mBtnClean2 = (Button) Utils.castView(findRequiredView2, R.id.btn_clean2, "field 'mBtnClean2'", Button.class);
        this.view7f090063 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Ops5_FeatureTestFragment_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                ops5_FeatureTestFragment.onClick(view2);
            }
        });
        View findRequiredView3 = Utils.findRequiredView(view, R.id.btn_clean3, "field 'mBtnClean3' and method 'onClick'");
        ops5_FeatureTestFragment.mBtnClean3 = (Button) Utils.castView(findRequiredView3, R.id.btn_clean3, "field 'mBtnClean3'", Button.class);
        this.view7f090064 = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Ops5_FeatureTestFragment_ViewBinding.3
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                ops5_FeatureTestFragment.onClick(view2);
            }
        });
        View findRequiredView4 = Utils.findRequiredView(view, R.id.btn_drain_water, "field 'mBtnDrainWater' and method 'onClick'");
        ops5_FeatureTestFragment.mBtnDrainWater = (Button) Utils.castView(findRequiredView4, R.id.btn_drain_water, "field 'mBtnDrainWater'", Button.class);
        this.view7f090069 = findRequiredView4;
        findRequiredView4.setOnClickListener(new DebouncingOnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Ops5_FeatureTestFragment_ViewBinding.4
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                ops5_FeatureTestFragment.onClick(view2);
            }
        });
        ops5_FeatureTestFragment.mCleanLayout = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.clean_layout, "field 'mCleanLayout'", LinearLayout.class);
        ops5_FeatureTestFragment.mRbFlou = (RadioButton) Utils.findRequiredViewAsType(view, R.id.rb_flou, "field 'mRbFlou'", RadioButton.class);
        ops5_FeatureTestFragment.mRbHotwater = (RadioButton) Utils.findRequiredViewAsType(view, R.id.rb_hotwater, "field 'mRbHotwater'", RadioButton.class);
        ops5_FeatureTestFragment.mRbColdwater = (RadioButton) Utils.findRequiredViewAsType(view, R.id.rb_coldwater, "field 'mRbColdwater'", RadioButton.class);
        ops5_FeatureTestFragment.mEdGallery = (EditText) Utils.findRequiredViewAsType(view, R.id.ed_gallery, "field 'mEdGallery'", EditText.class);
        View findRequiredView5 = Utils.findRequiredView(view, R.id.btn_1s, "field 'mBtn1s' and method 'onClick'");
        ops5_FeatureTestFragment.mBtn1s = (Button) Utils.castView(findRequiredView5, R.id.btn_1s, "field 'mBtn1s'", Button.class);
        this.view7f09005a = findRequiredView5;
        findRequiredView5.setOnClickListener(new DebouncingOnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Ops5_FeatureTestFragment_ViewBinding.5
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                ops5_FeatureTestFragment.onClick(view2);
            }
        });
        View findRequiredView6 = Utils.findRequiredView(view, R.id.btn_5s, "field 'mBtn5s' and method 'onClick'");
        ops5_FeatureTestFragment.mBtn5s = (Button) Utils.castView(findRequiredView6, R.id.btn_5s, "field 'mBtn5s'", Button.class);
        this.view7f09005b = findRequiredView6;
        findRequiredView6.setOnClickListener(new DebouncingOnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Ops5_FeatureTestFragment_ViewBinding.6
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                ops5_FeatureTestFragment.onClick(view2);
            }
        });
        View findRequiredView7 = Utils.findRequiredView(view, R.id.btn_10s, "field 'mBtn10s' and method 'onClick'");
        ops5_FeatureTestFragment.mBtn10s = (Button) Utils.castView(findRequiredView7, R.id.btn_10s, "field 'mBtn10s'", Button.class);
        this.view7f090059 = findRequiredView7;
        findRequiredView7.setOnClickListener(new DebouncingOnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Ops5_FeatureTestFragment_ViewBinding.7
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                ops5_FeatureTestFragment.onClick(view2);
            }
        });
        ops5_FeatureTestFragment.mWaterorflourLayout = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.waterorflour_layout, "field 'mWaterorflourLayout'", LinearLayout.class);
        ops5_FeatureTestFragment.mSpinnerIndex = (EditText) Utils.findRequiredViewAsType(view, R.id.spinner_index, "field 'mSpinnerIndex'", EditText.class);
        View findRequiredView8 = Utils.findRequiredView(view, R.id.btn_reback, "field 'mBtnReback' and method 'onClick'");
        ops5_FeatureTestFragment.mBtnReback = (Button) Utils.castView(findRequiredView8, R.id.btn_reback, "field 'mBtnReback'", Button.class);
        this.view7f09007c = findRequiredView8;
        findRequiredView8.setOnClickListener(new DebouncingOnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Ops5_FeatureTestFragment_ViewBinding.8
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                ops5_FeatureTestFragment.onClick(view2);
            }
        });
        ops5_FeatureTestFragment.mPathLayout = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.path_layout, "field 'mPathLayout'", LinearLayout.class);
        View findRequiredView9 = Utils.findRequiredView(view, R.id.btn_drool1, "field 'mBtnDrool1' and method 'onClick'");
        ops5_FeatureTestFragment.mBtnDrool1 = (Button) Utils.castView(findRequiredView9, R.id.btn_drool1, "field 'mBtnDrool1'", Button.class);
        this.view7f09006a = findRequiredView9;
        findRequiredView9.setOnClickListener(new DebouncingOnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Ops5_FeatureTestFragment_ViewBinding.9
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                ops5_FeatureTestFragment.onClick(view2);
            }
        });
        View findRequiredView10 = Utils.findRequiredView(view, R.id.btn_drool2, "field 'mBtnDrool2' and method 'onClick'");
        ops5_FeatureTestFragment.mBtnDrool2 = (Button) Utils.castView(findRequiredView10, R.id.btn_drool2, "field 'mBtnDrool2'", Button.class);
        this.view7f09006b = findRequiredView10;
        findRequiredView10.setOnClickListener(new DebouncingOnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Ops5_FeatureTestFragment_ViewBinding.10
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                ops5_FeatureTestFragment.onClick(view2);
            }
        });
        View findRequiredView11 = Utils.findRequiredView(view, R.id.btn_drool3, "field 'mBtnDrool3' and method 'onClick'");
        ops5_FeatureTestFragment.mBtnDrool3 = (Button) Utils.castView(findRequiredView11, R.id.btn_drool3, "field 'mBtnDrool3'", Button.class);
        this.view7f09006c = findRequiredView11;
        findRequiredView11.setOnClickListener(new DebouncingOnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Ops5_FeatureTestFragment_ViewBinding.11
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                ops5_FeatureTestFragment.onClick(view2);
            }
        });
        View findRequiredView12 = Utils.findRequiredView(view, R.id.btn_drool4, "field 'mBtnDrool4' and method 'onClick'");
        ops5_FeatureTestFragment.mBtnDrool4 = (Button) Utils.castView(findRequiredView12, R.id.btn_drool4, "field 'mBtnDrool4'", Button.class);
        this.view7f09006d = findRequiredView12;
        findRequiredView12.setOnClickListener(new DebouncingOnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Ops5_FeatureTestFragment_ViewBinding.12
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                ops5_FeatureTestFragment.onClick(view2);
            }
        });
        View findRequiredView13 = Utils.findRequiredView(view, R.id.btn_drool5, "field 'mBtnDrool5' and method 'onClick'");
        ops5_FeatureTestFragment.mBtnDrool5 = (Button) Utils.castView(findRequiredView13, R.id.btn_drool5, "field 'mBtnDrool5'", Button.class);
        this.view7f09006e = findRequiredView13;
        findRequiredView13.setOnClickListener(new DebouncingOnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Ops5_FeatureTestFragment_ViewBinding.13
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                ops5_FeatureTestFragment.onClick(view2);
            }
        });
        ops5_FeatureTestFragment.mDroolLayout = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.drool_layout, "field 'mDroolLayout'", LinearLayout.class);
        View findRequiredView14 = Utils.findRequiredView(view, R.id.btn_ice50, "field 'mBtnIce50' and method 'onClick'");
        ops5_FeatureTestFragment.mBtnIce50 = (Button) Utils.castView(findRequiredView14, R.id.btn_ice50, "field 'mBtnIce50'", Button.class);
        this.view7f090074 = findRequiredView14;
        findRequiredView14.setOnClickListener(new DebouncingOnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Ops5_FeatureTestFragment_ViewBinding.14
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                ops5_FeatureTestFragment.onClick(view2);
            }
        });
        View findRequiredView15 = Utils.findRequiredView(view, R.id.btn_ice100, "field 'mBtnIce100' and method 'onClick'");
        ops5_FeatureTestFragment.mBtnIce100 = (Button) Utils.castView(findRequiredView15, R.id.btn_ice100, "field 'mBtnIce100'", Button.class);
        this.view7f090072 = findRequiredView15;
        findRequiredView15.setOnClickListener(new DebouncingOnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Ops5_FeatureTestFragment_ViewBinding.15
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                ops5_FeatureTestFragment.onClick(view2);
            }
        });
        View findRequiredView16 = Utils.findRequiredView(view, R.id.btn_ice150, "field 'mBtnIce150' and method 'onClick'");
        ops5_FeatureTestFragment.mBtnIce150 = (Button) Utils.castView(findRequiredView16, R.id.btn_ice150, "field 'mBtnIce150'", Button.class);
        this.view7f090073 = findRequiredView16;
        findRequiredView16.setOnClickListener(new DebouncingOnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Ops5_FeatureTestFragment_ViewBinding.16
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                ops5_FeatureTestFragment.onClick(view2);
            }
        });
        View findRequiredView17 = Utils.findRequiredView(view, R.id.btn_icepush, "field 'mBtnIcepush' and method 'onClick'");
        ops5_FeatureTestFragment.mBtnIcepush = (Button) Utils.castView(findRequiredView17, R.id.btn_icepush, "field 'mBtnIcepush'", Button.class);
        this.view7f090075 = findRequiredView17;
        findRequiredView17.setOnClickListener(new DebouncingOnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Ops5_FeatureTestFragment_ViewBinding.17
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                ops5_FeatureTestFragment.onClick(view2);
            }
        });
        ops5_FeatureTestFragment.mIceLayout = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ice_layout, "field 'mIceLayout'", LinearLayout.class);
        View findRequiredView18 = Utils.findRequiredView(view, R.id.btn_open, "field 'mBtnOpen' and method 'onClick'");
        ops5_FeatureTestFragment.mBtnOpen = (Button) Utils.castView(findRequiredView18, R.id.btn_open, "field 'mBtnOpen'", Button.class);
        this.view7f09007a = findRequiredView18;
        findRequiredView18.setOnClickListener(new DebouncingOnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Ops5_FeatureTestFragment_ViewBinding.18
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                ops5_FeatureTestFragment.onClick(view2);
            }
        });
        View findRequiredView19 = Utils.findRequiredView(view, R.id.btn_close, "field 'mBtnClose' and method 'onClick'");
        ops5_FeatureTestFragment.mBtnClose = (Button) Utils.castView(findRequiredView19, R.id.btn_close, "field 'mBtnClose'", Button.class);
        this.view7f090066 = findRequiredView19;
        findRequiredView19.setOnClickListener(new DebouncingOnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Ops5_FeatureTestFragment_ViewBinding.19
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                ops5_FeatureTestFragment.onClick(view2);
            }
        });
        ops5_FeatureTestFragment.mClockLayout = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.clock_layout, "field 'mClockLayout'", LinearLayout.class);
        View findRequiredView20 = Utils.findRequiredView(view, R.id.btn_addwater, "field 'mBtnAddwater' and method 'onClick'");
        ops5_FeatureTestFragment.mBtnAddwater = (Button) Utils.castView(findRequiredView20, R.id.btn_addwater, "field 'mBtnAddwater'", Button.class);
        this.view7f09005d = findRequiredView20;
        findRequiredView20.setOnClickListener(new DebouncingOnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Ops5_FeatureTestFragment_ViewBinding.20
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                ops5_FeatureTestFragment.onClick(view2);
            }
        });
        ops5_FeatureTestFragment.mGetwaterLayout = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.getwater_layout, "field 'mGetwaterLayout'", LinearLayout.class);
        View findRequiredView21 = Utils.findRequiredView(view, R.id.btn_systemseting, "field 'mBtnSystemseting' and method 'onClick'");
        ops5_FeatureTestFragment.mBtnSystemseting = (Button) Utils.castView(findRequiredView21, R.id.btn_systemseting, "field 'mBtnSystemseting'", Button.class);
        this.view7f090086 = findRequiredView21;
        findRequiredView21.setOnClickListener(new DebouncingOnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Ops5_FeatureTestFragment_ViewBinding.21
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                ops5_FeatureTestFragment.onClick(view2);
            }
        });
        ops5_FeatureTestFragment.mBtnShownavigationbar = (Button) Utils.findRequiredViewAsType(view, R.id.btn_shownavigationbar, "field 'mBtnShownavigationbar'", Button.class);
        ops5_FeatureTestFragment.mBtnHideavigationbar = (Button) Utils.findRequiredViewAsType(view, R.id.btn_hideavigationbar, "field 'mBtnHideavigationbar'", Button.class);
        View findRequiredView22 = Utils.findRequiredView(view, R.id.btn_state1, "field 'mBtnState1' and method 'onClick'");
        ops5_FeatureTestFragment.mBtnState1 = (Button) Utils.castView(findRequiredView22, R.id.btn_state1, "field 'mBtnState1'", Button.class);
        this.view7f090083 = findRequiredView22;
        findRequiredView22.setOnClickListener(new DebouncingOnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Ops5_FeatureTestFragment_ViewBinding.22
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                ops5_FeatureTestFragment.onClick(view2);
            }
        });
        View findRequiredView23 = Utils.findRequiredView(view, R.id.btn_state2, "field 'mBtnState2' and method 'onClick'");
        ops5_FeatureTestFragment.mBtnState2 = (Button) Utils.castView(findRequiredView23, R.id.btn_state2, "field 'mBtnState2'", Button.class);
        this.view7f090084 = findRequiredView23;
        findRequiredView23.setOnClickListener(new DebouncingOnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Ops5_FeatureTestFragment_ViewBinding.23
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                ops5_FeatureTestFragment.onClick(view2);
            }
        });
        View findRequiredView24 = Utils.findRequiredView(view, R.id.btn_state3, "field 'mBtnState3' and method 'onClick'");
        ops5_FeatureTestFragment.mBtnState3 = (Button) Utils.castView(findRequiredView24, R.id.btn_state3, "field 'mBtnState3'", Button.class);
        this.view7f090085 = findRequiredView24;
        findRequiredView24.setOnClickListener(new DebouncingOnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Ops5_FeatureTestFragment_ViewBinding.24
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                ops5_FeatureTestFragment.onClick(view2);
            }
        });
        ops5_FeatureTestFragment.mDevstateLayout = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.devstate_layout, "field 'mDevstateLayout'", LinearLayout.class);
        ops5_FeatureTestFragment.mEtData = (EditText) Utils.findRequiredViewAsType(view, R.id.et_data, "field 'mEtData'", EditText.class);
        ops5_FeatureTestFragment.mSpinnerSeriaport = (Spinner) Utils.findRequiredViewAsType(view, R.id.spinner_seriaport, "field 'mSpinnerSeriaport'", Spinner.class);
        View findRequiredView25 = Utils.findRequiredView(view, R.id.btn_send_data, "field 'mBtnSendData' and method 'onClick'");
        ops5_FeatureTestFragment.mBtnSendData = (Button) Utils.castView(findRequiredView25, R.id.btn_send_data, "field 'mBtnSendData'", Button.class);
        this.view7f09007e = findRequiredView25;
        findRequiredView25.setOnClickListener(new DebouncingOnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Ops5_FeatureTestFragment_ViewBinding.25
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                ops5_FeatureTestFragment.onClick(view2);
            }
        });
        ops5_FeatureTestFragment.mSeriaportsendLayout = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.seriaportsend_layout, "field 'mSeriaportsendLayout'", LinearLayout.class);
        View findRequiredView26 = Utils.findRequiredView(view, R.id.btn_send, "field 'mBtnSend' and method 'onClick'");
        ops5_FeatureTestFragment.mBtnSend = (Button) Utils.castView(findRequiredView26, R.id.btn_send, "field 'mBtnSend'", Button.class);
        this.view7f09007d = findRequiredView26;
        findRequiredView26.setOnClickListener(new DebouncingOnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Ops5_FeatureTestFragment_ViewBinding.26
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                ops5_FeatureTestFragment.onClick(view2);
            }
        });
        ops5_FeatureTestFragment.llMakeTest = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_make_test, "field 'llMakeTest'", LinearLayout.class);
        View findRequiredView27 = Utils.findRequiredView(view, R.id.btn_make_test, "field 'btnMakeTest' and method 'onClick'");
        ops5_FeatureTestFragment.btnMakeTest = (Button) Utils.castView(findRequiredView27, R.id.btn_make_test, "field 'btnMakeTest'", Button.class);
        this.view7f090078 = findRequiredView27;
        findRequiredView27.setOnClickListener(new DebouncingOnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Ops5_FeatureTestFragment_ViewBinding.27
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                ops5_FeatureTestFragment.onClick(view2);
            }
        });
        ops5_FeatureTestFragment.cycleLayout = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.cycle_layout, "field 'cycleLayout'", LinearLayout.class);
        ops5_FeatureTestFragment.edCycleCount = (EditText) Utils.findRequiredViewAsType(view, R.id.ed_cycle_count, "field 'edCycleCount'", EditText.class);
        ops5_FeatureTestFragment.edCyclePipeCount = (EditText) Utils.findRequiredViewAsType(view, R.id.ed_cycle_pipe_count, "field 'edCyclePipeCount'", EditText.class);
        ops5_FeatureTestFragment.rgCycleObject = (RadioGroup) Utils.findRequiredViewAsType(view, R.id.rg_cycle_object, "field 'rgCycleObject'", RadioGroup.class);
        ops5_FeatureTestFragment.rgCycleTime = (RadioGroup) Utils.findRequiredViewAsType(view, R.id.rg_cycle_time, "field 'rgCycleTime'", RadioGroup.class);
        View findRequiredView28 = Utils.findRequiredView(view, R.id.btn_start_cycle, "field 'btnStartCycle' and method 'onClick'");
        ops5_FeatureTestFragment.btnStartCycle = (Button) Utils.castView(findRequiredView28, R.id.btn_start_cycle, "field 'btnStartCycle'", Button.class);
        this.view7f090081 = findRequiredView28;
        findRequiredView28.setOnClickListener(new DebouncingOnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Ops5_FeatureTestFragment_ViewBinding.28
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                ops5_FeatureTestFragment.onClick(view2);
            }
        });
        ops5_FeatureTestFragment.pathCycleLayout = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.path_cycle_layout, "field 'pathCycleLayout'", LinearLayout.class);
        ops5_FeatureTestFragment.edPathIndexCount = (EditText) Utils.findRequiredViewAsType(view, R.id.ed_path_index_count, "field 'edPathIndexCount'", EditText.class);
        ops5_FeatureTestFragment.edPathCycleCount = (EditText) Utils.findRequiredViewAsType(view, R.id.ed_path_cycle_count, "field 'edPathCycleCount'", EditText.class);
        View findRequiredView29 = Utils.findRequiredView(view, R.id.btn_start_path, "method 'onClick'");
        this.view7f090082 = findRequiredView29;
        findRequiredView29.setOnClickListener(new DebouncingOnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Ops5_FeatureTestFragment_ViewBinding.29
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                ops5_FeatureTestFragment.onClick(view2);
            }
        });
    }

    @Override // butterknife.Unbinder
    @CallSuper
    public void unbind() {
        Ops5_FeatureTestFragment ops5_FeatureTestFragment = this.target;
        if (ops5_FeatureTestFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        ops5_FeatureTestFragment.mBtnClean1 = null;
        ops5_FeatureTestFragment.mBtnClean2 = null;
        ops5_FeatureTestFragment.mBtnClean3 = null;
        ops5_FeatureTestFragment.mBtnDrainWater = null;
        ops5_FeatureTestFragment.mCleanLayout = null;
        ops5_FeatureTestFragment.mRbFlou = null;
        ops5_FeatureTestFragment.mRbHotwater = null;
        ops5_FeatureTestFragment.mRbColdwater = null;
        ops5_FeatureTestFragment.mEdGallery = null;
        ops5_FeatureTestFragment.mBtn1s = null;
        ops5_FeatureTestFragment.mBtn5s = null;
        ops5_FeatureTestFragment.mBtn10s = null;
        ops5_FeatureTestFragment.mWaterorflourLayout = null;
        ops5_FeatureTestFragment.mSpinnerIndex = null;
        ops5_FeatureTestFragment.mBtnReback = null;
        ops5_FeatureTestFragment.mPathLayout = null;
        ops5_FeatureTestFragment.mBtnDrool1 = null;
        ops5_FeatureTestFragment.mBtnDrool2 = null;
        ops5_FeatureTestFragment.mBtnDrool3 = null;
        ops5_FeatureTestFragment.mBtnDrool4 = null;
        ops5_FeatureTestFragment.mBtnDrool5 = null;
        ops5_FeatureTestFragment.mDroolLayout = null;
        ops5_FeatureTestFragment.mBtnIce50 = null;
        ops5_FeatureTestFragment.mBtnIce100 = null;
        ops5_FeatureTestFragment.mBtnIce150 = null;
        ops5_FeatureTestFragment.mBtnIcepush = null;
        ops5_FeatureTestFragment.mIceLayout = null;
        ops5_FeatureTestFragment.mBtnOpen = null;
        ops5_FeatureTestFragment.mBtnClose = null;
        ops5_FeatureTestFragment.mClockLayout = null;
        ops5_FeatureTestFragment.mBtnAddwater = null;
        ops5_FeatureTestFragment.mGetwaterLayout = null;
        ops5_FeatureTestFragment.mBtnSystemseting = null;
        ops5_FeatureTestFragment.mBtnShownavigationbar = null;
        ops5_FeatureTestFragment.mBtnHideavigationbar = null;
        ops5_FeatureTestFragment.mBtnState1 = null;
        ops5_FeatureTestFragment.mBtnState2 = null;
        ops5_FeatureTestFragment.mBtnState3 = null;
        ops5_FeatureTestFragment.mDevstateLayout = null;
        ops5_FeatureTestFragment.mEtData = null;
        ops5_FeatureTestFragment.mSpinnerSeriaport = null;
        ops5_FeatureTestFragment.mBtnSendData = null;
        ops5_FeatureTestFragment.mSeriaportsendLayout = null;
        ops5_FeatureTestFragment.mBtnSend = null;
        ops5_FeatureTestFragment.llMakeTest = null;
        ops5_FeatureTestFragment.btnMakeTest = null;
        ops5_FeatureTestFragment.cycleLayout = null;
        ops5_FeatureTestFragment.edCycleCount = null;
        ops5_FeatureTestFragment.edCyclePipeCount = null;
        ops5_FeatureTestFragment.rgCycleObject = null;
        ops5_FeatureTestFragment.rgCycleTime = null;
        ops5_FeatureTestFragment.btnStartCycle = null;
        ops5_FeatureTestFragment.pathCycleLayout = null;
        ops5_FeatureTestFragment.edPathIndexCount = null;
        ops5_FeatureTestFragment.edPathCycleCount = null;
        this.view7f090062.setOnClickListener(null);
        this.view7f090062 = null;
        this.view7f090063.setOnClickListener(null);
        this.view7f090063 = null;
        this.view7f090064.setOnClickListener(null);
        this.view7f090064 = null;
        this.view7f090069.setOnClickListener(null);
        this.view7f090069 = null;
        this.view7f09005a.setOnClickListener(null);
        this.view7f09005a = null;
        this.view7f09005b.setOnClickListener(null);
        this.view7f09005b = null;
        this.view7f090059.setOnClickListener(null);
        this.view7f090059 = null;
        this.view7f09007c.setOnClickListener(null);
        this.view7f09007c = null;
        this.view7f09006a.setOnClickListener(null);
        this.view7f09006a = null;
        this.view7f09006b.setOnClickListener(null);
        this.view7f09006b = null;
        this.view7f09006c.setOnClickListener(null);
        this.view7f09006c = null;
        this.view7f09006d.setOnClickListener(null);
        this.view7f09006d = null;
        this.view7f09006e.setOnClickListener(null);
        this.view7f09006e = null;
        this.view7f090074.setOnClickListener(null);
        this.view7f090074 = null;
        this.view7f090072.setOnClickListener(null);
        this.view7f090072 = null;
        this.view7f090073.setOnClickListener(null);
        this.view7f090073 = null;
        this.view7f090075.setOnClickListener(null);
        this.view7f090075 = null;
        this.view7f09007a.setOnClickListener(null);
        this.view7f09007a = null;
        this.view7f090066.setOnClickListener(null);
        this.view7f090066 = null;
        this.view7f09005d.setOnClickListener(null);
        this.view7f09005d = null;
        this.view7f090086.setOnClickListener(null);
        this.view7f090086 = null;
        this.view7f090083.setOnClickListener(null);
        this.view7f090083 = null;
        this.view7f090084.setOnClickListener(null);
        this.view7f090084 = null;
        this.view7f090085.setOnClickListener(null);
        this.view7f090085 = null;
        this.view7f09007e.setOnClickListener(null);
        this.view7f09007e = null;
        this.view7f09007d.setOnClickListener(null);
        this.view7f09007d = null;
        this.view7f090078.setOnClickListener(null);
        this.view7f090078 = null;
        this.view7f090081.setOnClickListener(null);
        this.view7f090081 = null;
        this.view7f090082.setOnClickListener(null);
        this.view7f090082 = null;
    }
}
