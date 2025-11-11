package com.yj.coffeemachines;

import com.yj.coffeemachines.app.utils.MD5Utils;
import com.yj.coffeemachines.bean.HttpBean;
import com.yj.coffeemachines.mvp.model.beans.ALiPayGenOrderBack;
import com.yj.coffeemachines.mvp.model.beans.AddOpsLogBack;
import com.yj.coffeemachines.mvp.model.beans.AliPayReturnAmtBack;
import com.yj.coffeemachines.mvp.model.beans.AppUploadBean;
import com.yj.coffeemachines.mvp.model.beans.ChangeBack;
import com.yj.coffeemachines.mvp.model.beans.CheckFileIntactBack;
import com.yj.coffeemachines.mvp.model.beans.CleanPlanListBack;
import com.yj.coffeemachines.mvp.model.beans.DeviceInfoBean;
import com.yj.coffeemachines.mvp.model.beans.DeviceQrBean;
import com.yj.coffeemachines.mvp.model.beans.DigitalPayGenOrderBack;
import com.yj.coffeemachines.mvp.model.beans.DigitalRefundBack;
import com.yj.coffeemachines.mvp.model.beans.ExchangeGenOrderBack;
import com.yj.coffeemachines.mvp.model.beans.GenOrderBack;
import com.yj.coffeemachines.mvp.model.beans.GetUiInfoBack;
import com.yj.coffeemachines.mvp.model.beans.ListTypeAllMaterialBack;
import com.yj.coffeemachines.mvp.model.beans.LoginBack;
import com.yj.coffeemachines.mvp.model.beans.OrderCancelBack;
import com.yj.coffeemachines.mvp.model.beans.OrderRefundBean;
import com.yj.coffeemachines.mvp.model.beans.OrderStateBean;
import com.yj.coffeemachines.mvp.model.beans.OutStockOverBack;
import com.yj.coffeemachines.mvp.model.beans.PayCodeGenOrderBack;
import com.yj.coffeemachines.mvp.model.beans.PayOrderBean;
import com.yj.coffeemachines.mvp.model.beans.PositionVoiceListBack;
import com.yj.coffeemachines.mvp.model.beans.ProduceFailBack;
import com.yj.coffeemachines.mvp.model.beans.ProduceOverBack;
import com.yj.coffeemachines.mvp.model.beans.ProductBean;
import com.yj.coffeemachines.mvp.model.beans.ProgramPlanListBack;
import com.yj.coffeemachines.mvp.model.beans.QueryPayResulBack;
import com.yj.coffeemachines.mvp.model.beans.ReplenishListBack;
import com.yj.coffeemachines.mvp.model.beans.ReplenishSubmitBack;
import com.yj.coffeemachines.mvp.model.beans.T50PayCreateQrBean;
import com.yj.coffeemachines.mvp.model.beans.T50PayOrderQrBean;
import com.yj.coffeemachines.mvp.model.beans.T50PayWayBean;
import com.yj.coffeemachines.mvp.model.beans.UnionPayGenOrderBack;
import com.yj.coffeemachines.mvp.model.beans.UnionPayReturnAmtBack;
import com.yj.coffeemachines.mvp.model.beans.UploadFileBack;
import com.yj.coffeemachines.mvp.model.beans.WeChartPayGenOrderBack;
import com.yj.coffeemachines.mvp.model.beans.WeChartPayReturnAmtBack;
import com.yj.coffeemachines.mvp.model.beans.aggregationCodePayment;
import com.yj.coffeemachines.mvp.model.beans.barcodepay;
import com.yj.coffeemachines.mvp.model.beans.facePay;
import com.yj.coffeemachines.mvp.model.beans.icbcPayGenOrderBack;
import com.yj.coffeemachines.mvp.model.beans.icbcPayReturnAmtBack;
import com.yj.coffeemachines.mvp.model.beans.query;
import com.yj.coffeemachines.mvp.model.beans.queryrefund;
import com.yj.coffeemachines.mvp.model.beans.refund;
import io.reactivex.Observable;
import java.util.HashMap;
import java.util.List;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Url;

/* loaded from: classes.dex */
public interface Api {
    public static final String EN_MQTT_URL = "tcp://gsvden.coffeeji.com:1883";
    public static final String EN_MQTT_URL_TEST = "tcp://8.154.23.241:2000";
    public static final String EN_URL = "https://gsvden.coffeeji.com";
    public static final String EN_URL_TEST = "http://8.154.23.241:8090";
    public static final String ZH_MQTT_URL = "tcp://gsvd.coffeeji.com:1883";
    public static final String ZH_MQTT_URL_TEST = "tcp://8.154.23.241:1883";
    public static final String ZH_URL = "https://gsvd.coffeeji.com";
    public static final String ZH_URL_TEST = "http://8.154.23.241";
    public static final String MQTT_CLIENTID = "client_" + Constants.deviceSN();
    public static final String MQTT_USERNAME = Constants.deviceSN();
    public static final String MQTT_PASSWORD = MD5Utils.md5_32_low(Constants.deviceSN());

    @POST("/coffee/api/device/addOpsLog")
    Observable<AddOpsLogBack> addOpsLog(@Body RequestBody requestBody);

    @POST("/coffee/api/order/aggregationCodePayment")
    Observable<aggregationCodePayment> aggregationCodePayment(@Query("orderNo") String str, @Query("totalFee") String str2, @Query("terminalTime") String str3, @Query("terminalIp") String str4, @Query("payType") String str5);

    @POST("/coffee/api/order/aliPayGenOrder")
    Observable<ALiPayGenOrderBack> aliPayGenOrder(@Body RequestBody requestBody);

    @POST("/coffee/api/order/aliPayRefund")
    Observable<AliPayReturnAmtBack> aliPayRefund(@Body RequestBody requestBody);

    @POST("/coffee/api/order/cancelOrder")
    Observable<HttpBean<Boolean>> cancelOrder(@Body HashMap<String, Object> hashMap);

    @GET("/coffee/api/upload/checkFileIntact")
    Observable<CheckFileIntactBack> checkFileIntact(@Query("fileMd5") String str, @Query("fullPath") String str2);

    @POST("/coffee/api/device/checkMaterialIsEnoughAndSetsSoldOut")
    Observable<String> checkMaterialIsEnoughAndSetsSoldOut();

    @GET("/coffee/api/device/cleanPlanList")
    Observable<CleanPlanListBack> cleanPlanList();

    @GET("/coffee/api/device/deviceInfo")
    Observable<HttpBean<DeviceInfoBean.InfoBean>> deviceInfo();

    @GET("/coffee/api/device/deviceTypeDetail")
    Observable<HttpBean<DeviceInfoBean.TypeInfoBean>> deviceTypeDetail();

    @POST("/coffee/api/order/digitalPayGenOrder")
    Observable<DigitalPayGenOrderBack> digitalPayGenOrder(@Body RequestBody requestBody);

    @POST("/coffee/api/order/digitalRefund")
    Observable<DigitalRefundBack> digitalRefund(@Body RequestBody requestBody);

    @GET
    Observable<ResponseBody> download(@Url String str);

    @POST("/coffee/api/order/exchangeGenOrder")
    Observable<ExchangeGenOrderBack> exchangeGenOrder(@Body RequestBody requestBody);

    @POST("/coffee/api/order/facePay")
    Observable<facePay> facePay(@Query("orderNo") String str, @Query("totalFee") String str2, @Query("terminalTime") String str3, @Query("terminalIp") String str4, @Query("payType") String str5);

    @POST("/coffee/api/order/genOrder")
    Observable<GenOrderBack> genOrder(@Body RequestBody requestBody);

    @GET("/coffee/api/device/deviceAllInfo")
    Observable<HttpBean<DeviceInfoBean>> getDeviceInfo();

    @GET("/coffee/api/goods/getExchangeGoods")
    Observable<ChangeBack> getExchangeGoods(@Query("code") String str);

    @GET("/coffee/api/goods/withoutPage")
    Observable<HttpBean<List<ProductBean>>> getGoodInfo();

    @GET("/coffee/api/device/getMaxAppVersion")
    Observable<HttpBean<AppUploadBean>> getMaxAppVersion();

    @GET("/coffee/api/device/getPayWaySetting")
    Observable<String> getPayWaySetting();

    @GET("/coffee/api/merchant/getQrConfig")
    Observable<HttpBean<DeviceQrBean>> getQrConfig();

    @GET("/coffee/api/resource/getUiInfo")
    Observable<GetUiInfoBack> getUiInfo();

    @POST("/coffee/api/order/icbcPayGenOrder")
    Observable<icbcPayGenOrderBack> icbcPayGenOrder(@Body RequestBody requestBody);

    @POST("/coffee/api/order/icbcPayReturnAmt")
    Observable<icbcPayReturnAmtBack> icbcPayReturnAmt(@Body RequestBody requestBody);

    @GET("/coffee/api/device/listTypeAllMaterial")
    Observable<ListTypeAllMaterialBack> listTypeAllMaterial();

    @POST("/coffee/api/device/login")
    Observable<LoginBack> login(@Body RequestBody requestBody);

    @POST("/coffee/api/order/orderCancel")
    Observable<OrderCancelBack> orderCancel(@Query("outTradeNo") String str);

    @POST("/coffee/api/order/outStockOver")
    Observable<OutStockOverBack> outStockOver(@Body RequestBody requestBody);

    @GET("/coffee/api/resource/positionVoiceList")
    Observable<PositionVoiceListBack> positionVoiceList();

    @GET("/coffee/api/device/powerPlanList")
    Observable<HttpBean<List<DeviceInfoBean.PowerPlanBean>>> powerPlanList();

    @POST("/coffee/api/order/produceFail")
    Observable<ProduceFailBack> produceFail(@Body RequestBody requestBody);

    @POST("/coffee/api/order/produceOver")
    Observable<ProduceOverBack> produceOver(@Body RequestBody requestBody);

    @GET("/coffee/api/resource/programPlanList")
    Observable<ProgramPlanListBack> programPlanList();

    @POST("/coffee/api/order/query")
    Observable<query> query(@Query("orderNo") String str, @Query("totalFee") String str2, @Query("terminalTime") String str3, @Query("terminalIp") String str4, @Query("payType") String str5);

    @POST("/coffee/api/order/queryOrder")
    Observable<HttpBean<OrderStateBean>> queryOrder(@Body HashMap<String, Object> hashMap);

    @GET("/coffee/api/order/queryPayResul")
    Observable<QueryPayResulBack> queryPayResul(@Query("outTradeNo") String str);

    @POST("/coffee/api/order/queryrefund")
    Observable<queryrefund> queryrefund(@Query("orderNo") String str, @Query("totalFee") String str2, @Query("terminalTime") String str3, @Query("terminalIp") String str4, @Query("payType") String str5);

    @POST("/coffee/api/order/refund")
    Observable<refund> refund(@Query("orderNo") String str);

    @POST("/coffee/api/order/refundOrder")
    Observable<HttpBean<OrderRefundBean>> refundOrder(@Body HashMap<String, Object> hashMap);

    @GET("/coffee/api/device/replenishList")
    Observable<ReplenishListBack> replenishList();

    @POST("/coffee/api/device/replenishSubmit")
    Observable<ReplenishSubmitBack> replenishSubmit(@Body RequestBody requestBody);

    @POST("/coffee/api/order/saoBeiAggregatedPayGenOrder")
    Observable<aggregationCodePayment> saoBeiAggregatedPayGenOrder(@Body RequestBody requestBody);

    @POST("/coffee/api/order/saoBeiPayCodeGenOrder")
    Observable<barcodepay> saoBeiPayCodeGenOrder(@Body HashMap<String, Object> hashMap);

    @POST("/coffee/api/order/saveOrderFailLog")
    Observable<HttpBean<Object>> saveOrderFailLog(@Body HashMap<String, Object> hashMap);

    @POST("/coffee/api/order/scanUserDigitalQrcode")
    Observable<PayCodeGenOrderBack> scanUserDigitalQrcode(@Body RequestBody requestBody);

    @POST("/coffee/api/order/qrPayGenOrder")
    Observable<HttpBean<T50PayOrderQrBean>> t50PayCreateQr(@Body T50PayCreateQrBean t50PayCreateQrBean);

    @POST("/coffee/api/order/getQrPayWay")
    Observable<HttpBean<List<T50PayWayBean>>> t50PayGetWay();

    @POST("/coffee/api/order/qrPayRefund")
    Observable<HttpBean<Object>> t50PayRefund(@Body HashMap<String, Object> hashMap);

    @POST("/coffee/api/order/unifiedOrder")
    Observable<HttpBean<PayOrderBean>> unifiedOrder(@Body HashMap<String, Object> hashMap);

    @POST("/coffee/api/order/unionPayGenOrder")
    Observable<UnionPayGenOrderBack> unionPayGenOrder(@Body RequestBody requestBody);

    @POST("/coffee/api/order/unionPayReturnAmt")
    Observable<UnionPayReturnAmtBack> unionPayReturnAmt(@Body RequestBody requestBody);

    @POST("/coffee/api/device/updateStatus")
    Observable<String> updateStatus(@Body RequestBody requestBody);

    @POST("/coffee/api/upload/file")
    Observable<UploadFileBack> uploadFile(@Query("fileType") String str, @Body RequestBody requestBody);

    @POST("/coffee/api/order/wxPayGenOrder")
    Observable<WeChartPayGenOrderBack> wxPayGenOrder(@Body RequestBody requestBody);

    @POST("/coffee/api/order/wxPayRefund")
    Observable<WeChartPayReturnAmtBack> wxPayRefund(@Body RequestBody requestBody);
}
