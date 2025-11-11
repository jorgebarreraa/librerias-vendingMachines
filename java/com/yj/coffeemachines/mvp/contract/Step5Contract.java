package com.yj.coffeemachines.mvp.contract;

import android.content.Context;
import com.jess.arms.mvp.IModel;
import com.jess.arms.mvp.IView;
import com.yj.coffeemachines.mvp.model.beans.OutStockOverBack;
import com.yj.coffeemachines.mvp.model.beans.ProductBean;
import io.reactivex.Observable;

/* loaded from: classes.dex */
public interface Step5Contract {

    /* loaded from: classes.dex */
    public interface Model extends IModel {
        Observable<String> checkMaterialIsEnoughAndSetsSoldOut();

        Observable<OutStockOverBack> outStockOver();
    }

    /* loaded from: classes.dex */
    public interface View extends IView {
        Context getActivity();

        void showDetailMessage(ProductBean.ProductDetailBean productDetailBean);
    }
}
