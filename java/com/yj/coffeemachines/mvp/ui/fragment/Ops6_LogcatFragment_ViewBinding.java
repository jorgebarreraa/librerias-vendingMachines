package com.yj.coffeemachines.mvp.ui.fragment;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.yj.coffeemachines.R;

/* loaded from: classes.dex */
public class Ops6_LogcatFragment_ViewBinding implements Unbinder {
    private Ops6_LogcatFragment target;
    private View view7f09007b;
    private View view7f090331;

    @UiThread
    public Ops6_LogcatFragment_ViewBinding(final Ops6_LogcatFragment ops6_LogcatFragment, View view) {
        this.target = ops6_LogcatFragment;
        ops6_LogcatFragment.mLogcatRecycleview = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.logcat_recycleview, "field 'mLogcatRecycleview'", RecyclerView.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.tv_querytime, "field 'mTvQuerytime' and method 'onClick'");
        ops6_LogcatFragment.mTvQuerytime = (TextView) Utils.castView(findRequiredView, R.id.tv_querytime, "field 'mTvQuerytime'", TextView.class);
        this.view7f090331 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Ops6_LogcatFragment_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                ops6_LogcatFragment.onClick(view2);
            }
        });
        View findRequiredView2 = Utils.findRequiredView(view, R.id.btn_query, "field 'mBtnQuery' and method 'onClick'");
        ops6_LogcatFragment.mBtnQuery = (Button) Utils.castView(findRequiredView2, R.id.btn_query, "field 'mBtnQuery'", Button.class);
        this.view7f09007b = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Ops6_LogcatFragment_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                ops6_LogcatFragment.onClick(view2);
            }
        });
    }

    @Override // butterknife.Unbinder
    @CallSuper
    public void unbind() {
        Ops6_LogcatFragment ops6_LogcatFragment = this.target;
        if (ops6_LogcatFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        ops6_LogcatFragment.mLogcatRecycleview = null;
        ops6_LogcatFragment.mTvQuerytime = null;
        ops6_LogcatFragment.mBtnQuery = null;
        this.view7f090331.setOnClickListener(null);
        this.view7f090331 = null;
        this.view7f09007b.setOnClickListener(null);
        this.view7f09007b = null;
    }
}
