package com.yj.coffeemachines.mvp.contract;

import com.jess.arms.mvp.IModel;
import com.jess.arms.mvp.IView;
import com.yj.coffeemachines.mvp.model.beans.ReplenishListBack;
import com.yj.coffeemachines.mvp.model.beans.ReplenishSubmitBack;
import io.reactivex.Observable;
import java.util.List;

/* loaded from: classes.dex */
public interface Ops1_RawMaterialAddContract {

    /* loaded from: classes.dex */
    public interface Model extends IModel {
        Observable<String> checkMaterialIsEnoughAndSetsSoldOut();

        Observable<ReplenishListBack> getData();

        Observable<ReplenishSubmitBack> updata(List<Object> list);
    }

    /* loaded from: classes.dex */
    public interface View extends IView {
        void reFrishData(List<ReplenishListBack.DataBean> list);
    }
}
