package com.yj.coffeemachines.mvp.contract;

import android.content.Context;
import com.jess.arms.mvp.IModel;
import com.jess.arms.mvp.IView;

/* loaded from: classes.dex */
public interface ADContract {

    /* loaded from: classes.dex */
    public interface Model extends IModel {
    }

    /* loaded from: classes.dex */
    public interface View extends IView {
        Context getActivity();
    }
}
