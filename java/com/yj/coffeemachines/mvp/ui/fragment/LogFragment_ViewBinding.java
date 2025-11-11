package com.yj.coffeemachines.mvp.ui.fragment;

import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.yj.coffeemachines.R;

/* loaded from: classes.dex */
public class LogFragment_ViewBinding implements Unbinder {
    private LogFragment target;
    private View view7f09005f;
    private View view7f090065;
    private View view7f090089;

    @UiThread
    public LogFragment_ViewBinding(final LogFragment logFragment, View view) {
        this.target = logFragment;
        View findRequiredView = Utils.findRequiredView(view, R.id.btn_clear_log, "field 'mBtnClearLog' and method 'onClick'");
        logFragment.mBtnClearLog = (Button) Utils.castView(findRequiredView, R.id.btn_clear_log, "field 'mBtnClearLog'", Button.class);
        this.view7f090065 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.LogFragment_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                logFragment.onClick(view2);
            }
        });
        View findRequiredView2 = Utils.findRequiredView(view, R.id.btn_auto_end, "field 'mBtnAutoEnd' and method 'onClick'");
        logFragment.mBtnAutoEnd = (Button) Utils.castView(findRequiredView2, R.id.btn_auto_end, "field 'mBtnAutoEnd'", Button.class);
        this.view7f09005f = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.LogFragment_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                logFragment.onClick(view2);
            }
        });
        View findRequiredView3 = Utils.findRequiredView(view, R.id.btn_whether_hexadecimal, "field 'mBtnWhetherHexadecimal' and method 'onClick'");
        logFragment.mBtnWhetherHexadecimal = (Button) Utils.castView(findRequiredView3, R.id.btn_whether_hexadecimal, "field 'mBtnWhetherHexadecimal'", Button.class);
        this.view7f090089 = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.LogFragment_ViewBinding.3
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                logFragment.onClick(view2);
            }
        });
        logFragment.mLvLogs = (ListView) Utils.findRequiredViewAsType(view, R.id.lv_logs, "field 'mLvLogs'", ListView.class);
    }

    @Override // butterknife.Unbinder
    @CallSuper
    public void unbind() {
        LogFragment logFragment = this.target;
        if (logFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        logFragment.mBtnClearLog = null;
        logFragment.mBtnAutoEnd = null;
        logFragment.mBtnWhetherHexadecimal = null;
        logFragment.mLvLogs = null;
        this.view7f090065.setOnClickListener(null);
        this.view7f090065 = null;
        this.view7f09005f.setOnClickListener(null);
        this.view7f09005f = null;
        this.view7f090089.setOnClickListener(null);
        this.view7f090089 = null;
    }
}
