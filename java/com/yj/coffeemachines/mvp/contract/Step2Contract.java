package com.yj.coffeemachines.mvp.contract;

import android.content.Context;
import com.jess.arms.mvp.IModel;
import com.jess.arms.mvp.IView;
import com.yj.coffeemachines.mvp.model.beans.ExchangeGenOrderBack;
import com.yj.coffeemachines.mvp.model.beans.ProductBean;
import io.reactivex.Observable;

/* loaded from: classes.dex */
public interface Step2Contract {

    /* loaded from: classes.dex */
    public interface Model extends IModel {
        Observable<ExchangeGenOrderBack> putExchangeOrder();
    }

    /* loaded from: classes.dex */
    public interface View extends IView {
        Context getActivity();

        void putExchangeOrderSuccess(ExchangeGenOrderBack exchangeGenOrderBack);

        void putExchangeOrderfail(ExchangeGenOrderBack exchangeGenOrderBack);

        void showDetailMessage(ProductBean.ProductDetailBean productDetailBean);

        void showLoading(String str);
    }
}
