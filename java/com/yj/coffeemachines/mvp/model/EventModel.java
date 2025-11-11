package com.yj.coffeemachines.mvp.model;

import android.app.Application;
import com.google.gson.Gson;
import com.jess.arms.di.scope.FragmentScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;
import com.yj.coffeemachines.Api;
import com.yj.coffeemachines.helper.Tools;
import com.yj.coffeemachines.mvp.contract.EventContract;
import com.yj.coffeemachines.mvp.model.beans.ChangeBack;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import javax.inject.Inject;

@FragmentScope
/* loaded from: classes.dex */
public class EventModel extends BaseModel implements EventContract.Model {

    @Inject
    Application mApplication;

    @Inject
    Gson mGson;

    @Inject
    public EventModel(IRepositoryManager iRepositoryManager) {
        super(iRepositoryManager);
    }

    @Override // com.jess.arms.mvp.BaseModel, com.jess.arms.mvp.IModel
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }

    @Override // com.yj.coffeemachines.mvp.contract.EventContract.Model
    public Observable<ChangeBack> submitCode(String str) {
        Tools.upLocalLog("网络接口获取兑换码对应商品.getExchangeGoods(" + str + ")");
        return ((Api) this.mRepositoryManager.obtainRetrofitService(Api.class)).getExchangeGoods(str).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).unsubscribeOn(Schedulers.io());
    }
}
