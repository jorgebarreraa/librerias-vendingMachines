package com.yj.coffeemachines.mvp.contract;

import android.app.Activity;
import com.jess.arms.mvp.IModel;
import com.jess.arms.mvp.IView;
import com.yj.coffeemachines.mvp.model.beans.ChangeBack;
import com.yj.coffeemachines.mvp.model.beans.ProductBean;
import io.reactivex.Observable;
import java.util.List;

/* loaded from: classes.dex */
public interface EventContract {

    /* loaded from: classes.dex */
    public interface Model extends IModel {
        Observable<ChangeBack> submitCode(String str);
    }

    /* loaded from: classes.dex */
    public interface View extends IView {
        Activity getActivity();

        void showExchangeDialog(String str, List<ProductBean.ProductDetailBean> list);

        void submitSuccess(String str);
    }
}
