package com.yj.coffeemachines.mvp.ui.activity;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.yj.coffeemachines.R;

/* loaded from: classes.dex */
public class SetingsHomeActivity_ViewBinding implements Unbinder {
    private SetingsHomeActivity target;
    private View view7f090060;
    private View view7f090128;
    private View view7f0902d8;
    private View view7f09030f;
    private View view7f090310;
    private View view7f09033a;
    private View view7f09033b;
    private View view7f09033c;
    private View view7f09033d;
    private View view7f09033e;
    private View view7f09033f;

    @UiThread
    public SetingsHomeActivity_ViewBinding(SetingsHomeActivity setingsHomeActivity) {
        this(setingsHomeActivity, setingsHomeActivity.getWindow().getDecorView());
    }

    @UiThread
    public SetingsHomeActivity_ViewBinding(final SetingsHomeActivity setingsHomeActivity, View view) {
        this.target = setingsHomeActivity;
        View findRequiredView = Utils.findRequiredView(view, R.id.tv_seting1, "field 'mTvSeting1' and method 'onClick'");
        setingsHomeActivity.mTvSeting1 = (TextView) Utils.castView(findRequiredView, R.id.tv_seting1, "field 'mTvSeting1'", TextView.class);
        this.view7f09033a = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.activity.SetingsHomeActivity_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                setingsHomeActivity.onClick(view2);
            }
        });
        View findRequiredView2 = Utils.findRequiredView(view, R.id.tv_seting1_en, "field 'mTvSeting1En' and method 'onClick'");
        setingsHomeActivity.mTvSeting1En = (TextView) Utils.castView(findRequiredView2, R.id.tv_seting1_en, "field 'mTvSeting1En'", TextView.class);
        this.view7f09033b = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.activity.SetingsHomeActivity_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                setingsHomeActivity.onClick(view2);
            }
        });
        View findRequiredView3 = Utils.findRequiredView(view, R.id.tv_seting2, "field 'mTvSeting2' and method 'onClick'");
        setingsHomeActivity.mTvSeting2 = (TextView) Utils.castView(findRequiredView3, R.id.tv_seting2, "field 'mTvSeting2'", TextView.class);
        this.view7f09033c = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.activity.SetingsHomeActivity_ViewBinding.3
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                setingsHomeActivity.onClick(view2);
            }
        });
        View findRequiredView4 = Utils.findRequiredView(view, R.id.tv_seting2_en, "field 'mTvSeting2En' and method 'onClick'");
        setingsHomeActivity.mTvSeting2En = (TextView) Utils.castView(findRequiredView4, R.id.tv_seting2_en, "field 'mTvSeting2En'", TextView.class);
        this.view7f09033d = findRequiredView4;
        findRequiredView4.setOnClickListener(new DebouncingOnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.activity.SetingsHomeActivity_ViewBinding.4
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                setingsHomeActivity.onClick(view2);
            }
        });
        View findRequiredView5 = Utils.findRequiredView(view, R.id.tv_seting3, "field 'mTvSeting3' and method 'onClick'");
        setingsHomeActivity.mTvSeting3 = (TextView) Utils.castView(findRequiredView5, R.id.tv_seting3, "field 'mTvSeting3'", TextView.class);
        this.view7f09033e = findRequiredView5;
        findRequiredView5.setOnClickListener(new DebouncingOnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.activity.SetingsHomeActivity_ViewBinding.5
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                setingsHomeActivity.onClick(view2);
            }
        });
        View findRequiredView6 = Utils.findRequiredView(view, R.id.tv_seting3_en, "field 'mTvSeting3En' and method 'onClick'");
        setingsHomeActivity.mTvSeting3En = (TextView) Utils.castView(findRequiredView6, R.id.tv_seting3_en, "field 'mTvSeting3En'", TextView.class);
        this.view7f09033f = findRequiredView6;
        findRequiredView6.setOnClickListener(new DebouncingOnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.activity.SetingsHomeActivity_ViewBinding.6
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                setingsHomeActivity.onClick(view2);
            }
        });
        setingsHomeActivity.mIvSn = (ImageView) Utils.findRequiredViewAsType(view, R.id.iv_sn, "field 'mIvSn'", ImageView.class);
        setingsHomeActivity.mTvSn = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_sn, "field 'mTvSn'", TextView.class);
        View findRequiredView7 = Utils.findRequiredView(view, R.id.iv_onlin, "field 'mIvOnlin' and method 'onClick'");
        setingsHomeActivity.mIvOnlin = (ImageView) Utils.castView(findRequiredView7, R.id.iv_onlin, "field 'mIvOnlin'", ImageView.class);
        this.view7f090128 = findRequiredView7;
        findRequiredView7.setOnClickListener(new DebouncingOnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.activity.SetingsHomeActivity_ViewBinding.7
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                setingsHomeActivity.onClick(view2);
            }
        });
        View findRequiredView8 = Utils.findRequiredView(view, R.id.tv_onlin, "field 'mTvOnlin' and method 'onClick'");
        setingsHomeActivity.mTvOnlin = (TextView) Utils.castView(findRequiredView8, R.id.tv_onlin, "field 'mTvOnlin'", TextView.class);
        this.view7f0902d8 = findRequiredView8;
        findRequiredView8.setOnClickListener(new DebouncingOnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.activity.SetingsHomeActivity_ViewBinding.8
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                setingsHomeActivity.onClick(view2);
            }
        });
        View findRequiredView9 = Utils.findRequiredView(view, R.id.tv_out, "field 'mTvOut' and method 'onClick'");
        setingsHomeActivity.mTvOut = (TextView) Utils.castView(findRequiredView9, R.id.tv_out, "field 'mTvOut'", TextView.class);
        this.view7f09030f = findRequiredView9;
        findRequiredView9.setOnClickListener(new DebouncingOnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.activity.SetingsHomeActivity_ViewBinding.9
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                setingsHomeActivity.onClick(view2);
            }
        });
        View findRequiredView10 = Utils.findRequiredView(view, R.id.tv_out_en, "field 'mTvOutEn' and method 'onClick'");
        setingsHomeActivity.mTvOutEn = (TextView) Utils.castView(findRequiredView10, R.id.tv_out_en, "field 'mTvOutEn'", TextView.class);
        this.view7f090310 = findRequiredView10;
        findRequiredView10.setOnClickListener(new DebouncingOnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.activity.SetingsHomeActivity_ViewBinding.10
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                setingsHomeActivity.onClick(view2);
            }
        });
        setingsHomeActivity.mFlContainer = (FrameLayout) Utils.findRequiredViewAsType(view, R.id.fl_container, "field 'mFlContainer'", FrameLayout.class);
        View findRequiredView11 = Utils.findRequiredView(view, R.id.btn_back, "field 'mBtnBack' and method 'onClick'");
        setingsHomeActivity.mBtnBack = (RelativeLayout) Utils.castView(findRequiredView11, R.id.btn_back, "field 'mBtnBack'", RelativeLayout.class);
        this.view7f090060 = findRequiredView11;
        findRequiredView11.setOnClickListener(new DebouncingOnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.activity.SetingsHomeActivity_ViewBinding.11
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                setingsHomeActivity.onClick(view2);
            }
        });
        setingsHomeActivity.mCountdowntime = (TextView) Utils.findRequiredViewAsType(view, R.id.countdowntime, "field 'mCountdowntime'", TextView.class);
        setingsHomeActivity.llSetting = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_setting, "field 'llSetting'", LinearLayout.class);
        setingsHomeActivity.llFactory = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_factory, "field 'llFactory'", LinearLayout.class);
        setingsHomeActivity.llUi = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_ui, "field 'llUi'", LinearLayout.class);
        setingsHomeActivity.lnSetings = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ln_setings, "field 'lnSetings'", LinearLayout.class);
    }

    @Override // butterknife.Unbinder
    @CallSuper
    public void unbind() {
        SetingsHomeActivity setingsHomeActivity = this.target;
        if (setingsHomeActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        setingsHomeActivity.mTvSeting1 = null;
        setingsHomeActivity.mTvSeting1En = null;
        setingsHomeActivity.mTvSeting2 = null;
        setingsHomeActivity.mTvSeting2En = null;
        setingsHomeActivity.mTvSeting3 = null;
        setingsHomeActivity.mTvSeting3En = null;
        setingsHomeActivity.mIvSn = null;
        setingsHomeActivity.mTvSn = null;
        setingsHomeActivity.mIvOnlin = null;
        setingsHomeActivity.mTvOnlin = null;
        setingsHomeActivity.mTvOut = null;
        setingsHomeActivity.mTvOutEn = null;
        setingsHomeActivity.mFlContainer = null;
        setingsHomeActivity.mBtnBack = null;
        setingsHomeActivity.mCountdowntime = null;
        setingsHomeActivity.llSetting = null;
        setingsHomeActivity.llFactory = null;
        setingsHomeActivity.llUi = null;
        setingsHomeActivity.lnSetings = null;
        this.view7f09033a.setOnClickListener(null);
        this.view7f09033a = null;
        this.view7f09033b.setOnClickListener(null);
        this.view7f09033b = null;
        this.view7f09033c.setOnClickListener(null);
        this.view7f09033c = null;
        this.view7f09033d.setOnClickListener(null);
        this.view7f09033d = null;
        this.view7f09033e.setOnClickListener(null);
        this.view7f09033e = null;
        this.view7f09033f.setOnClickListener(null);
        this.view7f09033f = null;
        this.view7f090128.setOnClickListener(null);
        this.view7f090128 = null;
        this.view7f0902d8.setOnClickListener(null);
        this.view7f0902d8 = null;
        this.view7f09030f.setOnClickListener(null);
        this.view7f09030f = null;
        this.view7f090310.setOnClickListener(null);
        this.view7f090310 = null;
        this.view7f090060.setOnClickListener(null);
        this.view7f090060 = null;
    }
}
