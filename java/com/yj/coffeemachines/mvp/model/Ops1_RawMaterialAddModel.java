package com.yj.coffeemachines.mvp.model;

import android.app.Application;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.jess.arms.di.scope.FragmentScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;
import com.yj.coffeemachines.Api;
import com.yj.coffeemachines.helper.Tools;
import com.yj.coffeemachines.mvp.contract.Ops1_RawMaterialAddContract;
import com.yj.coffeemachines.mvp.model.beans.ReplenishListBack;
import com.yj.coffeemachines.mvp.model.beans.ReplenishSubmitBack;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import java.util.List;
import javax.inject.Inject;
import okhttp3.MediaType;
import okhttp3.RequestBody;

@FragmentScope
/* loaded from: classes.dex */
public class Ops1_RawMaterialAddModel extends BaseModel implements Ops1_RawMaterialAddContract.Model {

    @Inject
    Application mApplication;

    @Inject
    Gson mGson;

    @Inject
    public Ops1_RawMaterialAddModel(IRepositoryManager iRepositoryManager) {
        super(iRepositoryManager);
    }

    private RequestBody getBody(List<Object> list) {
        JsonObject jsonObject = new JsonObject();
        JsonArray jsonArray = new JsonArray();
        for (int i = 0; i < list.size(); i++) {
            ReplenishListBack.DataBean dataBean = (ReplenishListBack.DataBean) list.get(i);
            JsonObject jsonObject2 = new JsonObject();
            jsonObject2.addProperty("id", dataBean.getId());
            jsonObject2.addProperty("replenishQt", Double.valueOf(dataBean.getNowResidueQty() - dataBean.getResidueQty()));
            jsonArray.add(jsonObject2);
        }
        jsonObject.add("items", jsonArray);
        return RequestBody.create(MediaType.parse("application/json"), jsonObject.toString());
    }

    @Override // com.yj.coffeemachines.mvp.contract.Ops1_RawMaterialAddContract.Model
    public Observable<String> checkMaterialIsEnoughAndSetsSoldOut() {
        Tools.upLocalLog("网络接口检查设备原料是否充足并设置上架商品售罄状态1checkMaterialIsEnoughAndSetsSoldOut");
        return ((Api) this.mRepositoryManager.obtainRetrofitService(Api.class)).checkMaterialIsEnoughAndSetsSoldOut().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).unsubscribeOn(Schedulers.io());
    }

    @Override // com.yj.coffeemachines.mvp.contract.Ops1_RawMaterialAddContract.Model
    public Observable<ReplenishListBack> getData() {
        Tools.upLocalLog("网络接口-原料补充-列表 .replenishList()");
        return ((Api) this.mRepositoryManager.obtainRetrofitService(Api.class)).replenishList().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).unsubscribeOn(Schedulers.io());
    }

    @Override // com.jess.arms.mvp.BaseModel, com.jess.arms.mvp.IModel
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }

    @Override // com.yj.coffeemachines.mvp.contract.Ops1_RawMaterialAddContract.Model
    public Observable<ReplenishSubmitBack> updata(List<Object> list) {
        Tools.upLocalLog("网络接口原料补充-提交.replenishSubmit（)" + list.toString());
        return ((Api) this.mRepositoryManager.obtainRetrofitService(Api.class)).replenishSubmit(getBody(list)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).unsubscribeOn(Schedulers.io());
    }
}
