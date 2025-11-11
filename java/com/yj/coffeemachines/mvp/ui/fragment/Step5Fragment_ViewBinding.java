package com.yj.coffeemachines.mvp.ui.fragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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
public class Step5Fragment_ViewBinding implements Unbinder {
    private Step5Fragment target;
    private View view7f090060;
    private View view7f090232;

    @UiThread
    public Step5Fragment_ViewBinding(final Step5Fragment step5Fragment, View view) {
        this.target = step5Fragment;
        View findRequiredView = Utils.findRequiredView(view, R.id.btn_back, "field 'mBtnBack' and method 'onClick'");
        step5Fragment.mBtnBack = (RelativeLayout) Utils.castView(findRequiredView, R.id.btn_back, "field 'mBtnBack'", RelativeLayout.class);
        this.view7f090060 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Step5Fragment_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                step5Fragment.onClick(view2);
            }
        });
        step5Fragment.mCountdowntime = (TextView) Utils.findRequiredViewAsType(view, R.id.countdowntime, "field 'mCountdowntime'", TextView.class);
        step5Fragment.step5_iv_enjoy = (ImageView) Utils.findRequiredViewAsType(view, R.id.step5_iv_enjoy, "field 'step5_iv_enjoy'", ImageView.class);
        step5Fragment.step5_tv_enjoy = (TextView) Utils.findRequiredViewAsType(view, R.id.step5_tv_enjoy, "field 'step5_tv_enjoy'", TextView.class);
        step5Fragment.step5_iv_takethelid = (ImageView) Utils.findRequiredViewAsType(view, R.id.step5_iv_takethelid, "field 'step5_iv_takethelid'", ImageView.class);
        step5Fragment.step5_tv_takethelid = (TextView) Utils.findRequiredViewAsType(view, R.id.step5_tv_takethelid, "field 'step5_tv_takethelid'", TextView.class);
        step5Fragment.step5_cl_main = (ConstraintLayout) Utils.findRequiredViewAsType(view, R.id.step5_cl_main, "field 'step5_cl_main'", ConstraintLayout.class);
        step5Fragment.mBanner = (AdvanceView) Utils.findRequiredViewAsType(view, R.id.banner, "field 'mBanner'", AdvanceView.class);
        step5Fragment.mTvProductname = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_productname, "field 'mTvProductname'", TextView.class);
        step5Fragment.mtv_pice = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_pice, "field 'mtv_pice'", TextView.class);
        step5Fragment.step5_view = Utils.findRequiredView(view, R.id.step5_view, "field 'step5_view'");
        View findRequiredView2 = Utils.findRequiredView(view, R.id.step5_ll_takethelid, "field 'step5_ll_takethelid' and method 'onClick'");
        step5Fragment.step5_ll_takethelid = (LinearLayout) Utils.castView(findRequiredView2, R.id.step5_ll_takethelid, "field 'step5_ll_takethelid'", LinearLayout.class);
        this.view7f090232 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Step5Fragment_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                step5Fragment.onClick(view2);
            }
        });
    }

    @Override // butterknife.Unbinder
    @CallSuper
    public void unbind() {
        Step5Fragment step5Fragment = this.target;
        if (step5Fragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        step5Fragment.mBtnBack = null;
        step5Fragment.mCountdowntime = null;
        step5Fragment.step5_iv_enjoy = null;
        step5Fragment.step5_tv_enjoy = null;
        step5Fragment.step5_iv_takethelid = null;
        step5Fragment.step5_tv_takethelid = null;
        step5Fragment.step5_cl_main = null;
        step5Fragment.mBanner = null;
        step5Fragment.mTvProductname = null;
        step5Fragment.mtv_pice = null;
        step5Fragment.step5_view = null;
        step5Fragment.step5_ll_takethelid = null;
        this.view7f090060.setOnClickListener(null);
        this.view7f090060 = null;
        this.view7f090232.setOnClickListener(null);
        this.view7f090232 = null;
    }
}
