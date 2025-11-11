package com.yj.coffeemachines.mvp.contract;

import android.content.Context;
import com.jess.arms.mvp.IModel;
import com.jess.arms.mvp.IView;
import com.yj.coffeemachines.mvp.model.beans.ProductBean;

/* loaded from: classes.dex */
public interface Step4Contract {

    /* loaded from: classes.dex */
    public interface Model extends IModel {
    }

    /* loaded from: classes.dex */
    public interface View extends IView {
        Context getActivity();

        void makeFinish();

        void showDetailMessage(ProductBean.ProductDetailBean productDetailBean);
    }
}
