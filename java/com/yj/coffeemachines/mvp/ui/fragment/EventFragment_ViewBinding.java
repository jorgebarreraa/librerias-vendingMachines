package com.yj.coffeemachines.mvp.ui.fragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.yj.coffeemachines.R;

/* loaded from: classes.dex */
public class EventFragment_ViewBinding implements Unbinder {
    private EventFragment target;
    private View view7f090060;
    private View view7f090292;
    private View view7f090293;
    private View view7f090294;
    private View view7f090295;
    private View view7f090296;
    private View view7f090297;
    private View view7f090298;
    private View view7f090299;
    private View view7f09029a;
    private View view7f09029b;
    private View view7f0902b2;
    private View view7f090345;

    @UiThread
    public EventFragment_ViewBinding(final EventFragment eventFragment, View view) {
        this.target = eventFragment;
        View findRequiredView = Utils.findRequiredView(view, R.id.tv_1, "field 'mTv1' and method 'onClick'");
        eventFragment.mTv1 = (TextView) Utils.castView(findRequiredView, R.id.tv_1, "field 'mTv1'", TextView.class);
        this.view7f090293 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.EventFragment_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                eventFragment.onClick(view2);
            }
        });
        View findRequiredView2 = Utils.findRequiredView(view, R.id.tv_2, "field 'mTv2' and method 'onClick'");
        eventFragment.mTv2 = (TextView) Utils.castView(findRequiredView2, R.id.tv_2, "field 'mTv2'", TextView.class);
        this.view7f090294 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.EventFragment_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                eventFragment.onClick(view2);
            }
        });
        View findRequiredView3 = Utils.findRequiredView(view, R.id.tv_3, "field 'mTv3' and method 'onClick'");
        eventFragment.mTv3 = (TextView) Utils.castView(findRequiredView3, R.id.tv_3, "field 'mTv3'", TextView.class);
        this.view7f090295 = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.EventFragment_ViewBinding.3
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                eventFragment.onClick(view2);
            }
        });
        View findRequiredView4 = Utils.findRequiredView(view, R.id.tv_4, "field 'mTv4' and method 'onClick'");
        eventFragment.mTv4 = (TextView) Utils.castView(findRequiredView4, R.id.tv_4, "field 'mTv4'", TextView.class);
        this.view7f090296 = findRequiredView4;
        findRequiredView4.setOnClickListener(new DebouncingOnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.EventFragment_ViewBinding.4
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                eventFragment.onClick(view2);
            }
        });
        View findRequiredView5 = Utils.findRequiredView(view, R.id.tv_5, "field 'mTv5' and method 'onClick'");
        eventFragment.mTv5 = (TextView) Utils.castView(findRequiredView5, R.id.tv_5, "field 'mTv5'", TextView.class);
        this.view7f090297 = findRequiredView5;
        findRequiredView5.setOnClickListener(new DebouncingOnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.EventFragment_ViewBinding.5
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                eventFragment.onClick(view2);
            }
        });
        View findRequiredView6 = Utils.findRequiredView(view, R.id.tv_6, "field 'mTv6' and method 'onClick'");
        eventFragment.mTv6 = (TextView) Utils.castView(findRequiredView6, R.id.tv_6, "field 'mTv6'", TextView.class);
        this.view7f090298 = findRequiredView6;
        findRequiredView6.setOnClickListener(new DebouncingOnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.EventFragment_ViewBinding.6
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                eventFragment.onClick(view2);
            }
        });
        View findRequiredView7 = Utils.findRequiredView(view, R.id.tv_7, "field 'mTv7' and method 'onClick'");
        eventFragment.mTv7 = (TextView) Utils.castView(findRequiredView7, R.id.tv_7, "field 'mTv7'", TextView.class);
        this.view7f090299 = findRequiredView7;
        findRequiredView7.setOnClickListener(new DebouncingOnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.EventFragment_ViewBinding.7
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                eventFragment.onClick(view2);
            }
        });
        View findRequiredView8 = Utils.findRequiredView(view, R.id.tv_8, "field 'mTv8' and method 'onClick'");
        eventFragment.mTv8 = (TextView) Utils.castView(findRequiredView8, R.id.tv_8, "field 'mTv8'", TextView.class);
        this.view7f09029a = findRequiredView8;
        findRequiredView8.setOnClickListener(new DebouncingOnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.EventFragment_ViewBinding.8
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                eventFragment.onClick(view2);
            }
        });
        View findRequiredView9 = Utils.findRequiredView(view, R.id.tv_9, "field 'mTv9' and method 'onClick'");
        eventFragment.mTv9 = (TextView) Utils.castView(findRequiredView9, R.id.tv_9, "field 'mTv9'", TextView.class);
        this.view7f09029b = findRequiredView9;
        findRequiredView9.setOnClickListener(new DebouncingOnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.EventFragment_ViewBinding.9
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                eventFragment.onClick(view2);
            }
        });
        View findRequiredView10 = Utils.findRequiredView(view, R.id.tv_0, "field 'mTv0' and method 'onClick'");
        eventFragment.mTv0 = (TextView) Utils.castView(findRequiredView10, R.id.tv_0, "field 'mTv0'", TextView.class);
        this.view7f090292 = findRequiredView10;
        findRequiredView10.setOnClickListener(new DebouncingOnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.EventFragment_ViewBinding.10
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                eventFragment.onClick(view2);
            }
        });
        View findRequiredView11 = Utils.findRequiredView(view, R.id.tv_delete, "field 'mTvDelete' and method 'onClick'");
        eventFragment.mTvDelete = (TextView) Utils.castView(findRequiredView11, R.id.tv_delete, "field 'mTvDelete'", TextView.class);
        this.view7f0902b2 = findRequiredView11;
        findRequiredView11.setOnClickListener(new DebouncingOnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.EventFragment_ViewBinding.11
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                eventFragment.onClick(view2);
            }
        });
        View findRequiredView12 = Utils.findRequiredView(view, R.id.tv_submit, "field 'mTvSubmit' and method 'onClick'");
        eventFragment.mTvSubmit = (TextView) Utils.castView(findRequiredView12, R.id.tv_submit, "field 'mTvSubmit'", TextView.class);
        this.view7f090345 = findRequiredView12;
        findRequiredView12.setOnClickListener(new DebouncingOnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.EventFragment_ViewBinding.12
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                eventFragment.onClick(view2);
            }
        });
        View findRequiredView13 = Utils.findRequiredView(view, R.id.btn_back, "field 'mBtnBack' and method 'onClick'");
        eventFragment.mBtnBack = (RelativeLayout) Utils.castView(findRequiredView13, R.id.btn_back, "field 'mBtnBack'", RelativeLayout.class);
        this.view7f090060 = findRequiredView13;
        findRequiredView13.setOnClickListener(new DebouncingOnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.EventFragment_ViewBinding.13
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                eventFragment.onClick(view2);
            }
        });
        eventFragment.mTvInputmessage = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_inputmessage, "field 'mTvInputmessage'", TextView.class);
        eventFragment.mIvQrcode = (ImageView) Utils.findRequiredViewAsType(view, R.id.iv_qrcode, "field 'mIvQrcode'", ImageView.class);
        eventFragment.mParentlayout = (RelativeLayout) Utils.findRequiredViewAsType(view, R.id.parentlayout, "field 'mParentlayout'", RelativeLayout.class);
        eventFragment.event_tv_1 = (TextView) Utils.findRequiredViewAsType(view, R.id.event_tv_1, "field 'event_tv_1'", TextView.class);
        eventFragment.event_tv_2 = (TextView) Utils.findRequiredViewAsType(view, R.id.event_tv_2, "field 'event_tv_2'", TextView.class);
        eventFragment.event_tv_3 = (TextView) Utils.findRequiredViewAsType(view, R.id.event_tv_3, "field 'event_tv_3'", TextView.class);
        eventFragment.event_tv_4 = (TextView) Utils.findRequiredViewAsType(view, R.id.event_tv_4, "field 'event_tv_4'", TextView.class);
        eventFragment.main_back_iv = (ImageView) Utils.findRequiredViewAsType(view, R.id.main_back_iv, "field 'main_back_iv'", ImageView.class);
        eventFragment.main_back_tv = (TextView) Utils.findRequiredViewAsType(view, R.id.main_back_tv, "field 'main_back_tv'", TextView.class);
        eventFragment.mCountdowntime = (TextView) Utils.findRequiredViewAsType(view, R.id.countdowntime, "field 'mCountdowntime'", TextView.class);
    }

    @Override // butterknife.Unbinder
    @CallSuper
    public void unbind() {
        EventFragment eventFragment = this.target;
        if (eventFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        eventFragment.mTv1 = null;
        eventFragment.mTv2 = null;
        eventFragment.mTv3 = null;
        eventFragment.mTv4 = null;
        eventFragment.mTv5 = null;
        eventFragment.mTv6 = null;
        eventFragment.mTv7 = null;
        eventFragment.mTv8 = null;
        eventFragment.mTv9 = null;
        eventFragment.mTv0 = null;
        eventFragment.mTvDelete = null;
        eventFragment.mTvSubmit = null;
        eventFragment.mBtnBack = null;
        eventFragment.mTvInputmessage = null;
        eventFragment.mIvQrcode = null;
        eventFragment.mParentlayout = null;
        eventFragment.event_tv_1 = null;
        eventFragment.event_tv_2 = null;
        eventFragment.event_tv_3 = null;
        eventFragment.event_tv_4 = null;
        eventFragment.main_back_iv = null;
        eventFragment.main_back_tv = null;
        eventFragment.mCountdowntime = null;
        this.view7f090293.setOnClickListener(null);
        this.view7f090293 = null;
        this.view7f090294.setOnClickListener(null);
        this.view7f090294 = null;
        this.view7f090295.setOnClickListener(null);
        this.view7f090295 = null;
        this.view7f090296.setOnClickListener(null);
        this.view7f090296 = null;
        this.view7f090297.setOnClickListener(null);
        this.view7f090297 = null;
        this.view7f090298.setOnClickListener(null);
        this.view7f090298 = null;
        this.view7f090299.setOnClickListener(null);
        this.view7f090299 = null;
        this.view7f09029a.setOnClickListener(null);
        this.view7f09029a = null;
        this.view7f09029b.setOnClickListener(null);
        this.view7f09029b = null;
        this.view7f090292.setOnClickListener(null);
        this.view7f090292 = null;
        this.view7f0902b2.setOnClickListener(null);
        this.view7f0902b2 = null;
        this.view7f090345.setOnClickListener(null);
        this.view7f090345 = null;
        this.view7f090060.setOnClickListener(null);
        this.view7f090060 = null;
    }
}
