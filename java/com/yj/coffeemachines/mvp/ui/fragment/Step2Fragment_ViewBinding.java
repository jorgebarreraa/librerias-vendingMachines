package com.yj.coffeemachines.mvp.ui.fragment;

import android.view.View;
import android.widget.Button;
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
public class Step2Fragment_ViewBinding implements Unbinder {
    private Step2Fragment target;
    private View view7f090060;
    private View view7f090067;

    @UiThread
    public Step2Fragment_ViewBinding(final Step2Fragment step2Fragment, View view) {
        this.target = step2Fragment;
        step2Fragment.step2_view = Utils.findRequiredView(view, R.id.step2_view, "field 'step2_view'");
        step2Fragment.step2_cl_main = (ConstraintLayout) Utils.findRequiredViewAsType(view, R.id.step2_cl_main, "field 'step2_cl_main'", ConstraintLayout.class);
        step2Fragment.mBanner = (AdvanceView) Utils.findRequiredViewAsType(view, R.id.banner, "field 'mBanner'", AdvanceView.class);
        step2Fragment.mTvProductname = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_productname, "field 'mTvProductname'", TextView.class);
        step2Fragment.mTvProductnameEn = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_productname_en, "field 'mTvProductnameEn'", TextView.class);
        step2Fragment.mTvPice = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_pice, "field 'mTvPice'", TextView.class);
        step2Fragment.mChangesGroup = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.changes_group, "field 'mChangesGroup'", LinearLayout.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.btn_back, "field 'mBtnBack' and method 'onClick'");
        step2Fragment.mBtnBack = (RelativeLayout) Utils.castView(findRequiredView, R.id.btn_back, "field 'mBtnBack'", RelativeLayout.class);
        this.view7f090060 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Step2Fragment_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                step2Fragment.onClick(view2);
            }
        });
        step2Fragment.mCountdowntime = (TextView) Utils.findRequiredViewAsType(view, R.id.countdowntime, "field 'mCountdowntime'", TextView.class);
        step2Fragment.main_back_tv = (TextView) Utils.findRequiredViewAsType(view, R.id.main_back_tv, "field 'main_back_tv'", TextView.class);
        step2Fragment.main_back_iv = (ImageView) Utils.findRequiredViewAsType(view, R.id.main_back_iv, "field 'main_back_iv'", ImageView.class);
        View findRequiredView2 = Utils.findRequiredView(view, R.id.btn_confirm, "field 'btnConfirm' and method 'onClick'");
        step2Fragment.btnConfirm = (Button) Utils.castView(findRequiredView2, R.id.btn_confirm, "field 'btnConfirm'", Button.class);
        this.view7f090067 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Step2Fragment_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                step2Fragment.onClick(view2);
            }
        });
    }

    @Override // butterknife.Unbinder
    @CallSuper
    public void unbind() {
        Step2Fragment step2Fragment = this.target;
        if (step2Fragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        step2Fragment.step2_view = null;
        step2Fragment.step2_cl_main = null;
        step2Fragment.mBanner = null;
        step2Fragment.mTvProductname = null;
        step2Fragment.mTvProductnameEn = null;
        step2Fragment.mTvPice = null;
        step2Fragment.mChangesGroup = null;
        step2Fragment.mBtnBack = null;
        step2Fragment.mCountdowntime = null;
        step2Fragment.main_back_tv = null;
        step2Fragment.main_back_iv = null;
        step2Fragment.btnConfirm = null;
        this.view7f090060.setOnClickListener(null);
        this.view7f090060 = null;
        this.view7f090067.setOnClickListener(null);
        this.view7f090067 = null;
    }
}
