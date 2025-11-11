package com.yj.coffeemachines.mvp.model;

import android.app.Application;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.jess.arms.di.scope.FragmentScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;
import com.yj.coffeemachines.Api;
import com.yj.coffeemachines.Constants;
import com.yj.coffeemachines.helper.Tools;
import com.yj.coffeemachines.mvp.contract.Step2Contract;
import com.yj.coffeemachines.mvp.model.beans.ExchangeGenOrderBack;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import javax.inject.Inject;
import okhttp3.MediaType;
import okhttp3.RequestBody;

@FragmentScope
/* loaded from: classes.dex */
public class Step2Model extends BaseModel implements Step2Contract.Model {

    @Inject
    Application mApplication;

    @Inject
    Gson mGson;

    @Inject
    public Step2Model(IRepositoryManager iRepositoryManager) {
        super(iRepositoryManager);
    }

    private RequestBody getResusterBody() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("code", Constants.exchangeCode + "");
        jsonObject.addProperty("goodsId", Constants.nowProduct_Detail.getId() + "");
        return RequestBody.create(MediaType.parse("application/json"), jsonObject.toString());
    }

    @Override // com.jess.arms.mvp.BaseModel, com.jess.arms.mvp.IModel
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }

    @Override // com.yj.coffeemachines.mvp.contract.Step2Contract.Model
    public Observable<ExchangeGenOrderBack> putExchangeOrder() {
        Tools.upLocalLog("网络接口兑换商品并生成订单exchangeGenOrder（ getResusterBody()code:" + Constants.exchangeCode + ";goodsId:" + Constants.nowProduct_Detail.getId());
        return ((Api) this.mRepositoryManager.obtainRetrofitService(Api.class)).exchangeGenOrder(getResusterBody()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).unsubscribeOn(Schedulers.io());
    }
}
