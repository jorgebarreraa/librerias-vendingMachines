package com.yj.coffeemachines.mvp.ui.fragment;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.yj.coffeemachines.R;

/* loaded from: classes.dex */
public class Ops7_AppUpDataFragment_ViewBinding implements Unbinder {
    private Ops7_AppUpDataFragment target;
    private View view7f09034d;
    private View view7f090356;

    @UiThread
    public Ops7_AppUpDataFragment_ViewBinding(final Ops7_AppUpDataFragment ops7_AppUpDataFragment, View view) {
        this.target = ops7_AppUpDataFragment;
        ops7_AppUpDataFragment.mTvSN = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_sn, "field 'mTvSN'", TextView.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.tv_version, "field 'mTvVersion' and method 'onClick'");
        ops7_AppUpDataFragment.mTvVersion = (TextView) Utils.castView(findRequiredView, R.id.tv_version, "field 'mTvVersion'", TextView.class);
        this.view7f09034d = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Ops7_AppUpDataFragment_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                ops7_AppUpDataFragment.onClick(view2);
            }
        });
        View findRequiredView2 = Utils.findRequiredView(view, R.id.updataapp, "field 'mUpdataapp' and method 'onClick'");
        ops7_AppUpDataFragment.mUpdataapp = (LinearLayout) Utils.castView(findRequiredView2, R.id.updataapp, "field 'mUpdataapp'", LinearLayout.class);
        this.view7f090356 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Ops7_AppUpDataFragment_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                ops7_AppUpDataFragment.onClick(view2);
            }
        });
        ops7_AppUpDataFragment.mTvMessage = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_message, "field 'mTvMessage'", TextView.class);
    }

    @Override // butterknife.Unbinder
    @CallSuper
    public void unbind() {
        Ops7_AppUpDataFragment ops7_AppUpDataFragment = this.target;
        if (ops7_AppUpDataFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        ops7_AppUpDataFragment.mTvSN = null;
        ops7_AppUpDataFragment.mTvVersion = null;
        ops7_AppUpDataFragment.mUpdataapp = null;
        ops7_AppUpDataFragment.mTvMessage = null;
        this.view7f09034d.setOnClickListener(null);
        this.view7f09034d = null;
        this.view7f090356.setOnClickListener(null);
        this.view7f090356 = null;
    }
}
