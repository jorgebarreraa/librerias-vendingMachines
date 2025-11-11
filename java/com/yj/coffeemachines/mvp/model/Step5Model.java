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
import com.yj.coffeemachines.mvp.contract.Step5Contract;
import com.yj.coffeemachines.mvp.model.beans.OutStockOverBack;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import javax.inject.Inject;
import okhttp3.MediaType;
import okhttp3.RequestBody;

@FragmentScope
/* loaded from: classes.dex */
public class Step5Model extends BaseModel implements Step5Contract.Model {

    @Inject
    Application mApplication;

    @Inject
    Gson mGson;

    @Inject
    public Step5Model(IRepositoryManager iRepositoryManager) {
        super(iRepositoryManager);
    }

    private RequestBody getRequsterBody() {
        Tools.upLocalLog("网络接口-订单号" + Constants.trade_no);
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("orderId", Constants.trade_no);
        return RequestBody.create(MediaType.parse("application/json"), jsonObject.toString());
    }

    @Override // com.yj.coffeemachines.mvp.contract.Step5Contract.Model
    public Observable<String> checkMaterialIsEnoughAndSetsSoldOut() {
        Tools.upLocalLog("网络接口检查设备原料是否充足并设置上架商品售罄状态2checkMaterialIsEnoughAndSetsSoldOut");
        return ((Api) this.mRepositoryManager.obtainRetrofitService(Api.class)).checkMaterialIsEnoughAndSetsSoldOut().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).unsubscribeOn(Schedulers.io());
    }

    @Override // com.jess.arms.mvp.BaseModel, com.jess.arms.mvp.IModel
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }

    @Override // com.yj.coffeemachines.mvp.contract.Step5Contract.Model
    public Observable<OutStockOverBack> outStockOver() {
        Tools.upLocalLog("网络接口-订单出货完成");
        return ((Api) this.mRepositoryManager.obtainRetrofitService(Api.class)).outStockOver(getRequsterBody()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).unsubscribeOn(Schedulers.io());
    }
}
