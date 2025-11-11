package com.yj.coffeemachines.mvp.model;

import android.app.Application;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.jess.arms.di.scope.FragmentScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;
import com.yj.coffeemachines.Api;
import com.yj.coffeemachines.Constants;
import com.yj.coffeemachines.bean.HttpBean;
import com.yj.coffeemachines.constants.PayType;
import com.yj.coffeemachines.helper.Tools;
import com.yj.coffeemachines.mvp.contract.Step3Contract;
import com.yj.coffeemachines.mvp.model.beans.ALiPayGenOrderBack;
import com.yj.coffeemachines.mvp.model.beans.DigitalPayGenOrderBack;
import com.yj.coffeemachines.mvp.model.beans.GenOrderBack;
import com.yj.coffeemachines.mvp.model.beans.OrderCancelBack;
import com.yj.coffeemachines.mvp.model.beans.OrderStateBean;
import com.yj.coffeemachines.mvp.model.beans.PayCodeGenOrderBack;
import com.yj.coffeemachines.mvp.model.beans.PayOrderBean;
import com.yj.coffeemachines.mvp.model.beans.QueryPayResulBack;
import com.yj.coffeemachines.mvp.model.beans.T50PayCreateQrBean;
import com.yj.coffeemachines.mvp.model.beans.T50PayOrderQrBean;
import com.yj.coffeemachines.mvp.model.beans.T50PayWayBean;
import com.yj.coffeemachines.mvp.model.beans.UnionPayGenOrderBack;
import com.yj.coffeemachines.mvp.model.beans.WeChartPayGenOrderBack;
import com.yj.coffeemachines.mvp.model.beans.aggregationCodePayment;
import com.yj.coffeemachines.mvp.model.beans.barcodepay;
import com.yj.coffeemachines.mvp.model.beans.icbcPayGenOrderBack;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import javax.inject.Inject;
import okhttp3.MediaType;
import okhttp3.RequestBody;

@FragmentScope
/* loaded from: classes.dex */
public class Step3Model extends BaseModel implements Step3Contract.Model {

    @Inject
    Application mApplication;

    @Inject
    Gson mGson;

    @Inject
    public Step3Model(IRepositoryManager iRepositoryManager) {
        super(iRepositoryManager);
    }

    private RequestBody getResusterBody() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("goodsId", Constants.nowProduct_Detail.getId() + "");
        jsonObject.addProperty("price", Constants.nowProduct_Detail.realPrice() + "");
        return RequestBody.create(MediaType.parse("application/json"), jsonObject.toString());
    }

    private RequestBody getResusterBody(String str) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("goodsId", Constants.nowProduct_Detail.getId() + "");
        jsonObject.addProperty("price", Constants.nowProduct_Detail.realPrice() + "");
        jsonObject.addProperty("productType", str);
        return RequestBody.create(MediaType.parse("application/json"), jsonObject.toString());
    }

    private RequestBody getResusterBody1() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("goodsId", Constants.nowProduct_Detail.getId() + "");
        jsonObject.addProperty("price", BigDecimal.valueOf(Constants.nowProduct_Detail.realPrice()).multiply(new BigDecimal(100)).toString());
        return RequestBody.create(MediaType.parse("application/json"), jsonObject.toString());
    }

    private RequestBody getResusterBody_putOrderCrsh(int i) {
        JsonObject jsonObject = new JsonObject();
        int i2 = 4;
        jsonObject.addProperty("amt", Double.valueOf(i == 4 ? 0.0d : Constants.nowProduct_Detail.realPrice()));
        jsonObject.addProperty("goodsId", Constants.nowProduct_Detail.getId() + "");
        if (i == 2) {
            i2 = 3;
        } else if (i == 3) {
            i2 = 1;
        }
        jsonObject.addProperty("payWay", Integer.valueOf(i2));
        return RequestBody.create(MediaType.parse("application/json"), jsonObject.toString());
    }

    private RequestBody getScanCodeResusterBody(String str) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("goodsId", Constants.nowProduct_Detail.getId() + "");
        jsonObject.addProperty("price", Constants.nowProduct_Detail.realPrice() + "");
        jsonObject.addProperty("payCode", str);
        return RequestBody.create(MediaType.parse("application/json"), jsonObject.toString());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Boolean lambda$cancelOrder$4(HttpBean httpBean) throws Exception {
        if (httpBean.isSuccess()) {
            return (Boolean) httpBean.getData();
        }
        throw new Exception(httpBean.getMsg());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ OrderStateBean lambda$queryOrder$3(HttpBean httpBean) throws Exception {
        if (httpBean.isSuccess()) {
            return (OrderStateBean) httpBean.getData();
        }
        throw new Exception(httpBean.getMsg());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ T50PayOrderQrBean lambda$t50PayCreateQr$1(HttpBean httpBean) throws Exception {
        if (httpBean.isSuccess()) {
            return (T50PayOrderQrBean) httpBean.getData();
        }
        throw new Exception(httpBean.getMsg());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ List lambda$t50PayGetWay$0(HttpBean httpBean) throws Exception {
        if (httpBean.isSuccess()) {
            return (List) httpBean.getData();
        }
        throw new Exception(httpBean.getMsg());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ PayOrderBean lambda$unifiedOrder$2(HttpBean httpBean) throws Exception {
        if (httpBean.isSuccess()) {
            return (PayOrderBean) httpBean.getData();
        }
        throw new Exception(httpBean.getMsg());
    }

    @Override // com.yj.coffeemachines.mvp.contract.Step3Contract.Model
    public Observable<aggregationCodePayment> aggregationCodePayment() {
        Tools.upLocalLog("网络接口_扫呗正扫支付下单生成支付二维码.aggregationCodePayment：goodsId：" + Constants.nowProduct_Detail.getId() + "price：" + Tools.toString(Double.valueOf(Double.parseDouble(BigDecimal.valueOf(Constants.nowProduct_Detail.realPrice()).multiply(new BigDecimal(100)).toString()))));
        return ((Api) this.mRepositoryManager.obtainRetrofitService(Api.class)).saoBeiAggregatedPayGenOrder(getResusterBody1()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).unsubscribeOn(Schedulers.io());
    }

    @Override // com.yj.coffeemachines.mvp.contract.Step3Contract.Model
    public Observable<barcodepay> barcodepay(String str) {
        Tools.upLocalLog("网络接口扫呗反扫支付下单saoBeiPayCodeGenOrdergoodsId：" + Constants.nowProduct_Detail.getId() + "price：" + Constants.nowProduct_Detail.realPrice());
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("authNo", str);
        hashMap.put("merchantId", Constants.deviceDetail.getMerchantId());
        hashMap.put("deviceId", Constants.deviceDetail.getId());
        hashMap.put("deviceNo", Constants.deviceDetail.getDeviceNo());
        hashMap.put("price", BigDecimal.valueOf(Constants.nowProduct_Detail.realPrice()).multiply(new BigDecimal(100)).toString());
        hashMap.put("goodsId", Constants.nowProduct_Detail.getId());
        return ((Api) this.mRepositoryManager.obtainRetrofitService(Api.class)).saoBeiPayCodeGenOrder(hashMap).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).unsubscribeOn(Schedulers.io());
    }

    @Override // com.yj.coffeemachines.mvp.contract.Step3Contract.Model
    public Observable<Boolean> cancelOrder() {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("orderNo", Constants.pay_no);
        hashMap.put("merchantId", Constants.deviceDetail.getMerchantId());
        return ((Api) this.mRepositoryManager.obtainRetrofitService(Api.class)).cancelOrder(hashMap).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).unsubscribeOn(Schedulers.io()).map(new Function() { // from class: com.yj.coffeemachines.mvp.model.-$$Lambda$Step3Model$p_il7l4yeuUMuWJfhwsNmnOzd3w
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return Step3Model.lambda$cancelOrder$4((HttpBean) obj);
            }
        });
    }

    @Override // com.yj.coffeemachines.mvp.contract.Step3Contract.Model
    public Observable<QueryPayResulBack> checkPayState() {
        return ((Api) this.mRepositoryManager.obtainRetrofitService(Api.class)).queryPayResul(Constants.pay_no).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).unsubscribeOn(Schedulers.io());
    }

    @Override // com.yj.coffeemachines.mvp.contract.Step3Contract.Model
    public Observable<DigitalPayGenOrderBack> digitalPayGenOrder() {
        Tools.upLocalLog("网络接口数字货币支付下单生成支付二维码.digitalPayGenOrdergoodsId：" + Constants.nowProduct_Detail.getId() + "price：" + Constants.nowProduct_Detail.realPrice());
        return ((Api) this.mRepositoryManager.obtainRetrofitService(Api.class)).digitalPayGenOrder(getResusterBody()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).unsubscribeOn(Schedulers.io());
    }

    @Override // com.yj.coffeemachines.mvp.contract.Step3Contract.Model
    public Observable<DigitalPayGenOrderBack> digitalPayGenOrder(String str) {
        Tools.upLocalLog("网络接口_惠支付下单生成支付二维码.digitalPayGenOrder;goodsId：" + Constants.nowProduct_Detail.getId() + ";price：" + Constants.nowProduct_Detail.realPrice() + ";productType" + str);
        return ((Api) this.mRepositoryManager.obtainRetrofitService(Api.class)).digitalPayGenOrder(getResusterBody(str)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).unsubscribeOn(Schedulers.io());
    }

    @Override // com.yj.coffeemachines.mvp.contract.Step3Contract.Model
    public Observable<String> getPayWaySetting() {
        Tools.upLocalLog("网络接口-查询设备存在哪些类型的支付配置.getPayWaySetting");
        return ((Api) this.mRepositoryManager.obtainRetrofitService(Api.class)).getPayWaySetting().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).unsubscribeOn(Schedulers.io());
    }

    @Override // com.yj.coffeemachines.mvp.contract.Step3Contract.Model
    public Observable<icbcPayGenOrderBack> icbcPayGenOrder() {
        Tools.upLocalLog("网络接口_工行支付下单生成支付二维码.icbcPayGenOrdergoodsId：" + Constants.nowProduct_Detail.getId() + "price：" + Constants.nowProduct_Detail.realPrice());
        return ((Api) this.mRepositoryManager.obtainRetrofitService(Api.class)).icbcPayGenOrder(getResusterBody()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).unsubscribeOn(Schedulers.io());
    }

    @Override // com.jess.arms.mvp.BaseModel, com.jess.arms.mvp.IModel
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }

    @Override // com.yj.coffeemachines.mvp.contract.Step3Contract.Model
    public Observable<OrderCancelBack> orderCancel(String str) {
        Tools.upLocalLog("网络接口-预支付取消.outTradeNo（" + str + "）");
        return ((Api) this.mRepositoryManager.obtainRetrofitService(Api.class)).orderCancel(str).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).unsubscribeOn(Schedulers.io());
    }

    @Override // com.yj.coffeemachines.mvp.contract.Step3Contract.Model
    public Observable<UnionPayGenOrderBack> putOrder() {
        Tools.upLocalLog("网络接口聚合支付下单生成支付二维码.unionPayGenOrdergoodsId：" + Constants.nowProduct_Detail.getId() + "price：" + Constants.nowProduct_Detail.realPrice());
        return ((Api) this.mRepositoryManager.obtainRetrofitService(Api.class)).unionPayGenOrder(getResusterBody()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).unsubscribeOn(Schedulers.io());
    }

    @Override // com.yj.coffeemachines.mvp.contract.Step3Contract.Model
    public Observable<ALiPayGenOrderBack> putOrderALiPay() {
        Tools.upLocalLog("网络接口支付宝支付下单生成支付二维码.aliPayGenOrdergoodsId：" + Constants.nowProduct_Detail.getId() + "price：" + Constants.nowProduct_Detail.realPrice());
        return ((Api) this.mRepositoryManager.obtainRetrofitService(Api.class)).aliPayGenOrder(getResusterBody()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).unsubscribeOn(Schedulers.io());
    }

    @Override // com.yj.coffeemachines.mvp.contract.Step3Contract.Model
    public Observable<GenOrderBack> putOrderCrsh(int i) {
        StringBuilder sb = new StringBuilder();
        sb.append("网络接口现金与免支付生成订单.genOrder：；amt:");
        int i2 = 4;
        sb.append(i == 4 ? 0.0d : Constants.nowProduct_Detail.realPrice());
        sb.append("；goodsId：");
        sb.append(Constants.nowProduct_Detail.getId());
        sb.append("；payWay:value:");
        if (i == 2) {
            i2 = 3;
        } else if (i == 3) {
            i2 = 1;
        }
        sb.append(i2);
        sb.append("(支付方式(1-现金支付,3-刷卡支付,4-免支付))");
        Tools.upLocalLog(sb.toString());
        return ((Api) this.mRepositoryManager.obtainRetrofitService(Api.class)).genOrder(getResusterBody_putOrderCrsh(i)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).unsubscribeOn(Schedulers.io());
    }

    @Override // com.yj.coffeemachines.mvp.contract.Step3Contract.Model
    public Observable<PayCodeGenOrderBack> putOrderPayCode(String str) {
        Tools.upLocalLog("网络接口扫码用户数字钱包付款码支付.scanUserDigitalQrcodegoodsId：" + Constants.nowProduct_Detail.getId() + "price" + Constants.nowProduct_Detail.realPrice());
        return ((Api) this.mRepositoryManager.obtainRetrofitService(Api.class)).scanUserDigitalQrcode(getScanCodeResusterBody(str)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).unsubscribeOn(Schedulers.io());
    }

    @Override // com.yj.coffeemachines.mvp.contract.Step3Contract.Model
    public Observable<WeChartPayGenOrderBack> putOrderWeCharPay() {
        Tools.upLocalLog("网络接口微信支付下单生成支付二维码.wxPayGenOrdergoodsId：" + Constants.nowProduct_Detail.getId() + "price：" + Constants.nowProduct_Detail.realPrice());
        return ((Api) this.mRepositoryManager.obtainRetrofitService(Api.class)).wxPayGenOrder(getResusterBody()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).unsubscribeOn(Schedulers.io());
    }

    @Override // com.yj.coffeemachines.mvp.contract.Step3Contract.Model
    public Observable<OrderStateBean> queryOrder() {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("orderNo", Constants.pay_no);
        hashMap.put("merchantId", Constants.deviceDetail.getMerchantId());
        return ((Api) this.mRepositoryManager.obtainRetrofitService(Api.class)).queryOrder(hashMap).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).unsubscribeOn(Schedulers.io()).map(new Function() { // from class: com.yj.coffeemachines.mvp.model.-$$Lambda$Step3Model$x_-UpG6z_2zs6mFJ0ERUsEEi88w
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return Step3Model.lambda$queryOrder$3((HttpBean) obj);
            }
        });
    }

    @Override // com.yj.coffeemachines.mvp.contract.Step3Contract.Model
    public Observable<T50PayOrderQrBean> t50PayCreateQr(String str) {
        T50PayCreateQrBean t50PayCreateQrBean = new T50PayCreateQrBean();
        t50PayCreateQrBean.setGoodsId(Constants.nowProduct_Detail.getId());
        t50PayCreateQrBean.setPrice(Double.parseDouble(BigDecimal.valueOf(Constants.nowProduct_Detail.realPrice()).multiply(new BigDecimal(100)).toString()));
        t50PayCreateQrBean.setQrOption(str);
        return ((Api) this.mRepositoryManager.obtainRetrofitService(Api.class)).t50PayCreateQr(t50PayCreateQrBean).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).unsubscribeOn(Schedulers.io()).map(new Function() { // from class: com.yj.coffeemachines.mvp.model.-$$Lambda$Step3Model$e8nfcZgP0COsBpAJbrdJaMzAKf0
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return Step3Model.lambda$t50PayCreateQr$1((HttpBean) obj);
            }
        });
    }

    @Override // com.yj.coffeemachines.mvp.contract.Step3Contract.Model
    public Observable<List<T50PayWayBean>> t50PayGetWay() {
        return ((Api) this.mRepositoryManager.obtainRetrofitService(Api.class)).t50PayGetWay().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).unsubscribeOn(Schedulers.io()).map(new Function() { // from class: com.yj.coffeemachines.mvp.model.-$$Lambda$Step3Model$IDcjKKMrOI2KcnOrioPIuRKMeio
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return Step3Model.lambda$t50PayGetWay$0((HttpBean) obj);
            }
        });
    }

    @Override // com.yj.coffeemachines.mvp.contract.Step3Contract.Model
    public Observable<PayOrderBean> unifiedOrder(int i) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("merchantId", Constants.deviceDetail.getMerchantId());
        hashMap.put("deviceId", Constants.deviceDetail.getId());
        hashMap.put("deviceNo", Constants.deviceDetail.getDeviceNo());
        hashMap.put("price", Double.valueOf(Constants.nowProduct_Detail.realPrice()));
        hashMap.put("objectId", Constants.nowProduct_Detail.getId());
        hashMap.put("objectType", 1);
        if (i == 5) {
            hashMap.put("payMethod", "wxpay");
        } else if (i == 15) {
            hashMap.put("payMethod", "mercadopay");
        } else if (i == PayType.NORTH_PAY) {
            hashMap.put("payMethod", "nspay");
        } else if (i == PayType.TP_PAY) {
            hashMap.put("payMethod", "tppay");
        }
        hashMap.put("wayCode", "qr");
        return ((Api) this.mRepositoryManager.obtainRetrofitService(Api.class)).unifiedOrder(hashMap).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).unsubscribeOn(Schedulers.io()).map(new Function() { // from class: com.yj.coffeemachines.mvp.model.-$$Lambda$Step3Model$JJc_CcYlg3O2dCKkRP8eQJ1MOUU
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return Step3Model.lambda$unifiedOrder$2((HttpBean) obj);
            }
        });
    }
}
