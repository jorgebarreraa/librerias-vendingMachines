package com.yj.coffeemachines.mvp.model;

import android.app.Application;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;
import com.yj.coffeemachines.Api;
import com.yj.coffeemachines.Constants;
import com.yj.coffeemachines.bean.HttpBean;
import com.yj.coffeemachines.helper.Tools;
import com.yj.coffeemachines.mvp.contract.HomeContract;
import com.yj.coffeemachines.mvp.model.beans.AliPayReturnAmtBack;
import com.yj.coffeemachines.mvp.model.beans.BaseMaterial;
import com.yj.coffeemachines.mvp.model.beans.DigitalRefundBack;
import com.yj.coffeemachines.mvp.model.beans.LoginBack;
import com.yj.coffeemachines.mvp.model.beans.OrderCancelBack;
import com.yj.coffeemachines.mvp.model.beans.OrderRefundBean;
import com.yj.coffeemachines.mvp.model.beans.OutStockOverBack;
import com.yj.coffeemachines.mvp.model.beans.ProduceFailBack;
import com.yj.coffeemachines.mvp.model.beans.ProduceOverBack;
import com.yj.coffeemachines.mvp.model.beans.ProductBean;
import com.yj.coffeemachines.mvp.model.beans.UnionPayReturnAmtBack;
import com.yj.coffeemachines.mvp.model.beans.WeChartPayReturnAmtBack;
import com.yj.coffeemachines.mvp.model.beans.icbcPayReturnAmtBack;
import com.yj.coffeemachines.mvp.model.beans.refund;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.util.HashMap;
import java.util.List;
import javax.inject.Inject;
import okhttp3.MediaType;
import okhttp3.RequestBody;

@ActivityScope
/* loaded from: classes.dex */
public class HomeModel extends BaseModel implements HomeContract.Model {

    @Inject
    Application mApplication;

    @Inject
    Gson mGson;

    @Inject
    public HomeModel(IRepositoryManager iRepositoryManager) {
        super(iRepositoryManager);
    }

    private RequestBody getRequestBody(String str) {
        return RequestBody.create(MediaType.parse("application/x-www-form-urlencoded"), "pwd=" + str);
    }

    private RequestBody getRequsterBody() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("orderId", Constants.trade_no);
        JsonArray jsonArray = new JsonArray();
        List<BaseMaterial> list = Constants.getnowProductMaterialMessage();
        for (int i = 0; i < list.size(); i++) {
            BaseMaterial baseMaterial = list.get(i);
            ProductBean.ProductDetailBean.FormulaListBean formulaListBean = baseMaterial.getFormulaListBean();
            JsonObject jsonObject2 = new JsonObject();
            String rawMaterialType = formulaListBean.getRawMaterialType();
            if (Constants.deviceTypeDetail.getName().contains("JK88") && baseMaterial.getMaterialMessage().getPosition().contains("8")) {
                jsonObject2.addProperty("expendValue", Double.valueOf(baseMaterial.coffeeRecalculate()));
            }
            if (rawMaterialType.contains(Constants.ice1) || rawMaterialType.contains(Constants.ice2) || rawMaterialType.contains(Constants.ice3) || rawMaterialType.contains(Constants.ice4)) {
                jsonObject2.addProperty("expendValue", Integer.valueOf(baseMaterial.getKqty()));
            }
            if (rawMaterialType.contains(Constants.ground1) || rawMaterialType.contains(Constants.ground2) || rawMaterialType.contains(Constants.ground3) || rawMaterialType.contains(Constants.ground4) || rawMaterialType.contains(Constants.ground5) || rawMaterialType.contains(Constants.ground6) || rawMaterialType.contains(Constants.ground7) || rawMaterialType.contains(Constants.ground8)) {
                jsonObject2.addProperty("expendValue", Double.valueOf(baseMaterial.coffeeRecalculate1() * formulaListBean.getGrindLeachTimes()));
            }
            int materTime = baseMaterial.getMaterTime();
            if (materTime <= 0) {
                materTime = 10;
            }
            jsonObject2.addProperty("expendTime", Integer.valueOf(materTime));
            jsonObject2.addProperty("materialId", formulaListBean.getRawMaterialId());
            jsonObject2.addProperty("micRate", Double.valueOf(baseMaterial.getMicRate() <= 0.0d ? 100.0d : baseMaterial.getMicRate()));
            jsonArray.add(jsonObject2);
        }
        jsonObject.add("materialExpendList", jsonArray);
        return RequestBody.create(MediaType.parse("application/json"), jsonObject.toString());
    }

    private RequestBody getRequster_PayReturnAmtBody() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("orderId", Constants.trade_no);
        return RequestBody.create(MediaType.parse("application/json"), jsonObject.toString());
    }

    private RequestBody getRequster_PayReturnAmtBody(String str) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("orderId", Constants.trade_no);
        return RequestBody.create(MediaType.parse("application/json"), jsonObject.toString());
    }

    private RequestBody getRequster_PayReturnAmtBody1(String str) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("orderId", str);
        return RequestBody.create(MediaType.parse("application/json"), jsonObject.toString());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Boolean lambda$cancelOrder$3(HttpBean httpBean) throws Exception {
        if (httpBean.isSuccess()) {
            return (Boolean) httpBean.getData();
        }
        throw new Exception(httpBean.getMsg());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ OrderRefundBean lambda$refundOrder$2(HttpBean httpBean) throws Exception {
        if (httpBean.isSuccess()) {
            return (OrderRefundBean) httpBean.getData();
        }
        throw new Exception(httpBean.getMsg());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Object lambda$saveOrderFailLog$1(HttpBean httpBean) throws Exception {
        if (httpBean.isSuccess()) {
            return httpBean.getData();
        }
        throw new Exception(httpBean.getMsg());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Object lambda$t50PayRefund$0(HttpBean httpBean) throws Exception {
        if (httpBean.isSuccess()) {
            return httpBean.getData();
        }
        throw new Exception(httpBean.getMsg());
    }

    @Override // com.yj.coffeemachines.mvp.contract.HomeContract.Model
    public Observable<DigitalRefundBack> ERMBReturnAmt() {
        Tools.upLocalLog("网络接口-数字rmb支付订单退款接口");
        return ((Api) this.mRepositoryManager.obtainRetrofitService(Api.class)).digitalRefund(getRequster_PayReturnAmtBody()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).unsubscribeOn(Schedulers.io());
    }

    @Override // com.yj.coffeemachines.mvp.contract.HomeContract.Model
    public Observable<DigitalRefundBack> ERMBReturnAmtHui(String str) {
        Tools.upLocalLog("网络接口-惠支付订单退款接口");
        return ((Api) this.mRepositoryManager.obtainRetrofitService(Api.class)).digitalRefund(getRequster_PayReturnAmtBody(str)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).unsubscribeOn(Schedulers.io());
    }

    @Override // com.yj.coffeemachines.mvp.contract.HomeContract.Model
    public Observable<AliPayReturnAmtBack> aLiPayReturnAmt() {
        Tools.upLocalLog("网络接口-支付宝支付订单退款接口");
        return ((Api) this.mRepositoryManager.obtainRetrofitService(Api.class)).aliPayRefund(getRequster_PayReturnAmtBody()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).unsubscribeOn(Schedulers.io());
    }

    @Override // com.yj.coffeemachines.mvp.contract.HomeContract.Model
    public Observable<Boolean> cancelOrder() {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("orderNo", Constants.pay_no);
        hashMap.put("merchantId", Constants.deviceDetail.getMerchantId());
        return ((Api) this.mRepositoryManager.obtainRetrofitService(Api.class)).cancelOrder(hashMap).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).unsubscribeOn(Schedulers.io()).map(new Function() { // from class: com.yj.coffeemachines.mvp.model.-$$Lambda$HomeModel$S-wmzuWINWmUlhjL0aCRHh8B1UU
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return HomeModel.lambda$cancelOrder$3((HttpBean) obj);
            }
        });
    }

    @Override // com.yj.coffeemachines.mvp.contract.HomeContract.Model
    public Observable<icbcPayReturnAmtBack> icbcPayReturnAmt() {
        Tools.upLocalLog("网络接口-工行支付订单退款接口");
        return ((Api) this.mRepositoryManager.obtainRetrofitService(Api.class)).icbcPayReturnAmt(getRequster_PayReturnAmtBody()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).unsubscribeOn(Schedulers.io());
    }

    @Override // com.yj.coffeemachines.mvp.contract.HomeContract.Model
    public Observable<LoginBack> login(String str) {
        Tools.upLocalLog("网络接口-登录");
        return ((Api) this.mRepositoryManager.obtainRetrofitService(Api.class)).login(getRequestBody(str)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).unsubscribeOn(Schedulers.io());
    }

    @Override // com.jess.arms.mvp.BaseModel, com.jess.arms.mvp.IModel
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }

    @Override // com.yj.coffeemachines.mvp.contract.HomeContract.Model
    public Observable<OrderCancelBack> orderCancel(String str) {
        Tools.upLocalLog("网络接口-预支付取消.outTradeNo（" + str + "）");
        return ((Api) this.mRepositoryManager.obtainRetrofitService(Api.class)).orderCancel(str).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).unsubscribeOn(Schedulers.io());
    }

    @Override // com.yj.coffeemachines.mvp.contract.HomeContract.Model
    public Observable<OutStockOverBack> outStockOver() {
        Tools.upLocalLog("网络接口-订单出货完成");
        return ((Api) this.mRepositoryManager.obtainRetrofitService(Api.class)).outStockOver(getRequsterBody()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).unsubscribeOn(Schedulers.io());
    }

    @Override // com.yj.coffeemachines.mvp.contract.HomeContract.Model
    public Observable<ProduceFailBack> produceFail() {
        return ((Api) this.mRepositoryManager.obtainRetrofitService(Api.class)).produceFail(getRequster_PayReturnAmtBody()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).unsubscribeOn(Schedulers.io());
    }

    @Override // com.yj.coffeemachines.mvp.contract.HomeContract.Model
    public Observable<ProduceOverBack> produceOver() {
        Tools.upLocalLog("网络接口——订单制作完成1.produceOver_orderId" + Constants.trade_no);
        return ((Api) this.mRepositoryManager.obtainRetrofitService(Api.class)).produceOver(getRequsterBody()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).unsubscribeOn(Schedulers.io());
    }

    @Override // com.yj.coffeemachines.mvp.contract.HomeContract.Model
    public Observable<refund> refund() {
        Tools.upLocalLog("网络接口-扫呗支付订单退款接口");
        return ((Api) this.mRepositoryManager.obtainRetrofitService(Api.class)).refund(Constants.pay_no).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).unsubscribeOn(Schedulers.io());
    }

    @Override // com.yj.coffeemachines.mvp.contract.HomeContract.Model
    public Observable<OrderRefundBean> refundOrder(String str) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("merchantId", Constants.deviceDetail.getMerchantId());
        hashMap.put("orderNo", Constants.pay_no);
        hashMap.put("refundReason", str);
        return ((Api) this.mRepositoryManager.obtainRetrofitService(Api.class)).refundOrder(hashMap).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).unsubscribeOn(Schedulers.io()).map(new Function() { // from class: com.yj.coffeemachines.mvp.model.-$$Lambda$HomeModel$muICqqjSmzkRW0nUFJo9bTgeVRI
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return HomeModel.lambda$refundOrder$2((HttpBean) obj);
            }
        });
    }

    @Override // com.yj.coffeemachines.mvp.contract.HomeContract.Model
    public Observable<Object> saveOrderFailLog(String str) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("orderId", Constants.trade_no);
        hashMap.put("failRemark", str);
        return ((Api) this.mRepositoryManager.obtainRetrofitService(Api.class)).saveOrderFailLog(hashMap).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).unsubscribeOn(Schedulers.io()).map(new Function() { // from class: com.yj.coffeemachines.mvp.model.-$$Lambda$HomeModel$Nk9x8TN9ogXkh-gjWhX6tCwvUG0
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return HomeModel.lambda$saveOrderFailLog$1((HttpBean) obj);
            }
        });
    }

    @Override // com.yj.coffeemachines.mvp.contract.HomeContract.Model
    public Observable<Object> t50PayRefund() {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("customOrderId", Constants.pay_no);
        return ((Api) this.mRepositoryManager.obtainRetrofitService(Api.class)).t50PayRefund(hashMap).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).unsubscribeOn(Schedulers.io()).map(new Function() { // from class: com.yj.coffeemachines.mvp.model.-$$Lambda$HomeModel$l8GY-cOMt2TBZWrJ1lIXcWCZIvk
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return HomeModel.lambda$t50PayRefund$0((HttpBean) obj);
            }
        });
    }

    @Override // com.yj.coffeemachines.mvp.contract.HomeContract.Model
    public Observable<UnionPayReturnAmtBack> unionPayReturnAmt() {
        Tools.upLocalLog("网络接口-聚合支付订单退款接口");
        return ((Api) this.mRepositoryManager.obtainRetrofitService(Api.class)).unionPayReturnAmt(getRequster_PayReturnAmtBody()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).unsubscribeOn(Schedulers.io());
    }

    @Override // com.yj.coffeemachines.mvp.contract.HomeContract.Model
    public Observable<UnionPayReturnAmtBack> unionPayReturnAmt(String str) {
        Tools.upLocalLog("网络接口-聚合支付订单退款接口");
        return ((Api) this.mRepositoryManager.obtainRetrofitService(Api.class)).unionPayReturnAmt(getRequster_PayReturnAmtBody1(str)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).unsubscribeOn(Schedulers.io());
    }

    @Override // com.yj.coffeemachines.mvp.contract.HomeContract.Model
    public Observable<String> uploadState(String str) {
        int i = (str.isEmpty() || Tools.toString(str).equals("")) ? 1 : 2;
        if (str.contains("已连接")) {
            i = 1;
        }
        if (i == 1) {
            str = "";
        }
        Tools.upLocalLog("网络接口更改设备状态.updateStatus——deviceRunStatus:" + i + "remark:" + str);
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("deviceRunStatus", Integer.valueOf(i));
        jsonObject.addProperty("remark", str);
        return ((Api) this.mRepositoryManager.obtainRetrofitService(Api.class)).updateStatus(RequestBody.create(MediaType.parse("application/json"), jsonObject.toString())).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).unsubscribeOn(Schedulers.io());
    }

    @Override // com.yj.coffeemachines.mvp.contract.HomeContract.Model
    public Observable<WeChartPayReturnAmtBack> weChartPayReturnAmt() {
        Tools.upLocalLog("网络接口-微信支付订单退款接口");
        return ((Api) this.mRepositoryManager.obtainRetrofitService(Api.class)).wxPayRefund(getRequster_PayReturnAmtBody()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).unsubscribeOn(Schedulers.io());
    }
}
