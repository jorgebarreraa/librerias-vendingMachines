package com.yj.coffeemachines.mvp.ui.activity;

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
public class JoinVIPActivity_ViewBinding implements Unbinder {
    private JoinVIPActivity target;
    private View view7f090060;

    @UiThread
    public JoinVIPActivity_ViewBinding(JoinVIPActivity joinVIPActivity) {
        this(joinVIPActivity, joinVIPActivity.getWindow().getDecorView());
    }

    @UiThread
    public JoinVIPActivity_ViewBinding(final JoinVIPActivity joinVIPActivity, View view) {
        this.target = joinVIPActivity;
        joinVIPActivity.mIvPhone = (ImageView) Utils.findRequiredViewAsType(view, R.id.iv_phone, "field 'mIvPhone'", ImageView.class);
        joinVIPActivity.mTvPhone = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_phone, "field 'mTvPhone'", TextView.class);
        joinVIPActivity.mIvSn = (ImageView) Utils.findRequiredViewAsType(view, R.id.iv_sn, "field 'mIvSn'", ImageView.class);
        joinVIPActivity.mTvSn = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_sn, "field 'mTvSn'", TextView.class);
        joinVIPActivity.mIvVipqrcode = (ImageView) Utils.findRequiredViewAsType(view, R.id.iv_vipqrcode, "field 'mIvVipqrcode'", ImageView.class);
        joinVIPActivity.mTvJoinvip = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_joinvip, "field 'mTvJoinvip'", TextView.class);
        joinVIPActivity.mTvJoinvipEn = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_joinvip_en, "field 'mTvJoinvipEn'", TextView.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.btn_back, "field 'mBtnBack' and method 'onClick'");
        joinVIPActivity.mBtnBack = (RelativeLayout) Utils.castView(findRequiredView, R.id.btn_back, "field 'mBtnBack'", RelativeLayout.class);
        this.view7f090060 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.activity.JoinVIPActivity_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                joinVIPActivity.onClick(view2);
            }
        });
    }

    @Override // butterknife.Unbinder
    @CallSuper
    public void unbind() {
        JoinVIPActivity joinVIPActivity = this.target;
        if (joinVIPActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        joinVIPActivity.mIvPhone = null;
        joinVIPActivity.mTvPhone = null;
        joinVIPActivity.mIvSn = null;
        joinVIPActivity.mTvSn = null;
        joinVIPActivity.mIvVipqrcode = null;
        joinVIPActivity.mTvJoinvip = null;
        joinVIPActivity.mTvJoinvipEn = null;
        joinVIPActivity.mBtnBack = null;
        this.view7f090060.setOnClickListener(null);
        this.view7f090060 = null;
    }
}
