package com.yj.coffeemachines.mvp.ui.fragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.constraintlayout.widget.ConstraintLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.yj.coffeemachines.R;
import com.yj.coffeemachines.carousel.AdvanceView;
import com.yj.coffeemachines.mvp.ui.widget.numberprogressbar.NumberProgressBar;

/* loaded from: classes.dex */
public class Step4Fragment_ViewBinding implements Unbinder {
    private Step4Fragment target;

    @UiThread
    public Step4Fragment_ViewBinding(Step4Fragment step4Fragment, View view) {
        this.target = step4Fragment;
        step4Fragment.mBanner = (AdvanceView) Utils.findRequiredViewAsType(view, R.id.banner, "field 'mBanner'", AdvanceView.class);
        step4Fragment.mTvMessage = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_message, "field 'mTvMessage'", TextView.class);
        step4Fragment.mTvMessageEn = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_message_en, "field 'mTvMessageEn'", TextView.class);
        step4Fragment.mPbSchedule = (NumberProgressBar) Utils.findRequiredViewAsType(view, R.id.pb_schedule, "field 'mPbSchedule'", NumberProgressBar.class);
        step4Fragment.mIvProductPic = (ImageView) Utils.findRequiredViewAsType(view, R.id.iv_product_pic, "field 'mIvProductPic'", ImageView.class);
        step4Fragment.mTvProductname = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_productname, "field 'mTvProductname'", TextView.class);
        step4Fragment.mtv_pice = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_pice, "field 'mtv_pice'", TextView.class);
        step4Fragment.mCountdowntime = (TextView) Utils.findRequiredViewAsType(view, R.id.countdowntime, "field 'mCountdowntime'", TextView.class);
        step4Fragment.step4_cl_main = (ConstraintLayout) Utils.findRequiredViewAsType(view, R.id.step4_cl_main, "field 'step4_cl_main'", ConstraintLayout.class);
        step4Fragment.step4_view = Utils.findRequiredView(view, R.id.step4_view, "field 'step4_view'");
    }

    @Override // butterknife.Unbinder
    @CallSuper
    public void unbind() {
        Step4Fragment step4Fragment = this.target;
        if (step4Fragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        step4Fragment.mBanner = null;
        step4Fragment.mTvMessage = null;
        step4Fragment.mTvMessageEn = null;
        step4Fragment.mPbSchedule = null;
        step4Fragment.mIvProductPic = null;
        step4Fragment.mTvProductname = null;
        step4Fragment.mtv_pice = null;
        step4Fragment.mCountdowntime = null;
        step4Fragment.step4_cl_main = null;
        step4Fragment.step4_view = null;
    }
}
