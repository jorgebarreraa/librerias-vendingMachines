package com.yj.coffeemachines.mvp.presenter;

import android.app.Application;
import android.text.TextUtils;
import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.jess.arms.di.scope.FragmentScope;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.integration.AppManager;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.utils.RxLifecycleUtils;
import com.yj.coffeemachines.Constants;
import com.yj.coffeemachines.MyAppLocation;
import com.yj.coffeemachines.app.utils.ByteUtils;
import com.yj.coffeemachines.app.utils.DataUtils;
import com.yj.coffeemachines.constants.PayType;
import com.yj.coffeemachines.greendao.DBHelper;
import com.yj.coffeemachines.greendao.beans.OrderMessageBean;
import com.yj.coffeemachines.helper.Tools;
import com.yj.coffeemachines.mvp.contract.Step3Contract;
import com.yj.coffeemachines.mvp.model.beans.ALiPayGenOrderBack;
import com.yj.coffeemachines.mvp.model.beans.DigitalPayGenOrderBack;
import com.yj.coffeemachines.mvp.model.beans.GenOrderBack;
import com.yj.coffeemachines.mvp.model.beans.OrderCancelBack;
import com.yj.coffeemachines.mvp.model.beans.OrderStateBean;
import com.yj.coffeemachines.mvp.model.beans.PayCodeGenOrderBack;
import com.yj.coffeemachines.mvp.model.beans.PayOrderBean;
import com.yj.coffeemachines.mvp.model.beans.PayWaySettingBack;
import com.yj.coffeemachines.mvp.model.beans.QueryPayResulBack;
import com.yj.coffeemachines.mvp.model.beans.T50PayOrderQrBean;
import com.yj.coffeemachines.mvp.model.beans.T50PayWayBean;
import com.yj.coffeemachines.mvp.model.beans.UnionPayGenOrderBack;
import com.yj.coffeemachines.mvp.model.beans.aggregationCodePayment;
import com.yj.coffeemachines.mvp.model.beans.barcodepay;
import com.yj.coffeemachines.mvp.model.beans.icbcPayGenOrderBack;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.util.List;
import javax.inject.Inject;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;
import me.jessyan.rxerrorhandler.handler.RetryWithDelay;

@FragmentScope
/* loaded from: classes.dex */
public class Step3Presenter extends BasePresenter<Step3Contract.Model, Step3Contract.View> {

    @Inject
    AppManager mAppManager;

    @Inject
    Application mApplication;

    @Inject
    RxErrorHandler mErrorHandler;

    @Inject
    ImageLoader mImageLoader;

    @Inject
    public Step3Presenter(Step3Contract.Model model, Step3Contract.View view) {
        super(model, view);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$checkPayState$26(Disposable disposable) throws Exception {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$checkPayState$27() throws Exception {
    }

    public void backMoney() {
        int change = (int) (Constants.pay_num / Constants.opsSeting().getChange());
        Tools.upLocalLog("1退款：" + change);
        if (change < 0) {
            return;
        }
        MyAppLocation.myAppLocation.mSerialDataService.addSendByteToList_Payment(Constants.smallChange_payment((byte) change));
        Constants.pay_num = 0.0d;
        Constants.buy_type = 0;
    }

    public void barcodepay(String str) {
        ((Step3Contract.Model) this.mModel).barcodepay(str).subscribeOn(Schedulers.io()).doOnSubscribe(new Consumer() { // from class: com.yj.coffeemachines.mvp.presenter.-$$Lambda$Step3Presenter$ks3TmXV9XCXnGRUZs6J2HGRe2sI
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                Step3Presenter.this.lambda$barcodepay$32$Step3Presenter((Disposable) obj);
            }
        }).subscribeOn(AndroidSchedulers.mainThread()).doFinally(new Action() { // from class: com.yj.coffeemachines.mvp.presenter.-$$Lambda$Step3Presenter$8ego3dH2Ha4Rg0vG4uWbqQdRdEs
            @Override // io.reactivex.functions.Action
            public final void run() {
                Step3Presenter.this.lambda$barcodepay$33$Step3Presenter();
            }
        }).compose(RxLifecycleUtils.bindToLifecycle(this.mRootView)).subscribe(new ErrorHandleSubscriber<barcodepay>(this.mErrorHandler) { // from class: com.yj.coffeemachines.mvp.presenter.Step3Presenter.21
            @Override // io.reactivex.Observer
            public void onNext(barcodepay barcodepayVar) {
                if (barcodepayVar.getCode() != 200) {
                    ArmsUtils.snackbarText(barcodepayVar.getMsg());
                    ((Step3Contract.View) Step3Presenter.this.mRootView).putOrderFailQrcode(barcodepayVar.getMsg());
                } else {
                    barcodepay.DataBean data = barcodepayVar.getData();
                    Constants.pay_no = data.getTerminal_trace();
                    ((Step3Contract.View) Step3Presenter.this.mRootView).putOrderSuccessSaoBeiFan(data);
                }
            }
        });
    }

    public void cardPay() {
        byte[] intToBytes2_HL;
        byte[] bArr;
        Constants.payType = 2;
        double realPrice = Constants.nowProduct_Detail.realPrice();
        double pos = Constants.opsSeting().getPos();
        int i = (int) (realPrice / pos);
        if (!Constants.opsSeting().isCardpay() || Constants.opsSeting().isCardpaynew()) {
            intToBytes2_HL = ByteUtils.intToBytes2_HL(i, 4);
            try {
                bArr = Constants.setPaymentWay_payment_new((byte) 2, ByteUtils.generateUniqueBytes(Constants.goods_no), intToBytes2_HL);
            } catch (Exception unused) {
                bArr = null;
            }
        } else {
            intToBytes2_HL = ByteUtils.intToBytes2_HL(i, 2);
            bArr = Constants.setPaymentWay_payment((byte) 2, intToBytes2_HL);
        }
        Tools.upLocalLog("刷卡支付:v；" + realPrice + "；rate；" + pos + "；v1；" + i + "；data；" + Tools.byteToStr(intToBytes2_HL) + "；bytes；" + Tools.byteToStr(bArr) + "；Cardpay:" + Constants.opsSeting().isCardpay() + "；CardpayNew:" + Constants.opsSeting().isCardpaynew());
        if (i <= 0) {
            Tools.upLocalLog("记录错误：商品价格为" + i + ",刷卡倍率设置错误。");
        }
        if (bArr == null) {
            Tools.sendArMsg("刷卡有误。");
        } else {
            MyAppLocation.myAppLocation.mSerialDataService.addSendByteToList_Payment(bArr);
        }
    }

    public void checkPayState() {
        if (Constants.buy_type == 5 || Constants.buy_type == 15 || Constants.buy_type == PayType.NORTH_PAY || Constants.buy_type == PayType.TP_PAY) {
            ((Step3Contract.Model) this.mModel).queryOrder().subscribeOn(Schedulers.io()).subscribeOn(AndroidSchedulers.mainThread()).compose(RxLifecycleUtils.bindToLifecycle(this.mRootView)).subscribe(new ErrorHandleSubscriber<OrderStateBean>(this.mErrorHandler) { // from class: com.yj.coffeemachines.mvp.presenter.Step3Presenter.15
                @Override // me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber, io.reactivex.Observer
                public void onError(Throwable th) {
                    super.onError(th);
                    Tools.upLocalLog("查询支付结果：" + th.getMessage());
                    ((Step3Contract.View) Step3Presenter.this.mRootView).payError();
                }

                @Override // io.reactivex.Observer
                public void onNext(OrderStateBean orderStateBean) {
                    if (orderStateBean.isSuccess()) {
                        Constants.trade_no = orderStateBean.getOrderId();
                        ((Step3Contract.View) Step3Presenter.this.mRootView).paySuccess();
                    } else if (orderStateBean.isInterrupt()) {
                        ((Step3Contract.View) Step3Presenter.this.mRootView).payError(orderStateBean.getStatus());
                    }
                }
            });
        } else {
            ((Step3Contract.Model) this.mModel).checkPayState().subscribeOn(Schedulers.io()).doOnSubscribe(new Consumer() { // from class: com.yj.coffeemachines.mvp.presenter.-$$Lambda$Step3Presenter$1L65x93niAONJ6cKC90G1DYFWKU
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    Step3Presenter.lambda$checkPayState$26((Disposable) obj);
                }
            }).subscribeOn(AndroidSchedulers.mainThread()).doFinally(new Action() { // from class: com.yj.coffeemachines.mvp.presenter.-$$Lambda$Step3Presenter$Bi9ZA-gzz9Aw2OhgL7UzxQp9JR4
                @Override // io.reactivex.functions.Action
                public final void run() {
                    Step3Presenter.lambda$checkPayState$27();
                }
            }).compose(RxLifecycleUtils.bindToLifecycle(this.mRootView)).subscribe(new ErrorHandleSubscriber<QueryPayResulBack>(this.mErrorHandler) { // from class: com.yj.coffeemachines.mvp.presenter.Step3Presenter.16
                @Override // io.reactivex.Observer
                public void onNext(QueryPayResulBack queryPayResulBack) {
                    Tools.upLocalLog("查询支付结果：" + queryPayResulBack.toString());
                    if (queryPayResulBack.getCode() == 200 && queryPayResulBack.getSuccess()) {
                        QueryPayResulBack.DataBean data = queryPayResulBack.getData();
                        Constants.trade_no = data.getOrderId();
                        int status = data.getStatus();
                        if (status == 2 || status == 8 || status == 9 || status == 10) {
                            ((Step3Contract.View) Step3Presenter.this.mRootView).paySuccess();
                        } else if (status == 3 || status == 4 || status == 5 || status == 6) {
                            ((Step3Contract.View) Step3Presenter.this.mRootView).payError(status);
                        }
                    }
                }
            });
        }
        Tools.upLocalLog("查询支付结果订单：" + Constants.trade_no);
    }

    public void crshPay() {
        byte[] intToBytes2_HL;
        byte[] paymentWay_payment_new;
        Constants.payType = 1;
        double realPrice = Constants.nowProduct_Detail.realPrice();
        int rate_y = (int) (realPrice / Constants.opsSeting().getRate_y());
        if (!Constants.opsSeting().isCash() || Constants.opsSeting().isCardpaynew()) {
            intToBytes2_HL = ByteUtils.intToBytes2_HL(rate_y, 4);
            String str = Constants.goods_no;
            byte[] strToByte = Tools.strToByte(Tools.addZeroToEachDigit(str.substring(str.length() - 4)));
            paymentWay_payment_new = (strToByte == null || strToByte.length < 4) ? null : Constants.setPaymentWay_payment_new((byte) 1, strToByte, intToBytes2_HL);
        } else {
            byte[] intToBytes2_HL2 = ByteUtils.intToBytes2_HL(rate_y, 2);
            paymentWay_payment_new = Constants.setPaymentWay_payment((byte) 1, intToBytes2_HL2);
            intToBytes2_HL = intToBytes2_HL2;
        }
        Tools.upLocalLog("现金支付:v" + realPrice + "v1" + rate_y + "data" + Tools.byteToStr(intToBytes2_HL) + "bytes" + Tools.byteToStr(paymentWay_payment_new) + "isCash:" + Constants.opsSeting().isCash() + "CardpayNew:" + Constants.opsSeting().isCardpaynew());
        if (paymentWay_payment_new == null) {
            Tools.sendArMsg("现金有误。");
        } else {
            MyAppLocation.myAppLocation.mSerialDataService.addSendByteToList_Payment(paymentWay_payment_new);
        }
    }

    public void getPayWaySetting() {
        if (Constants.isPayWaySettingBack) {
            ((Step3Contract.View) this.mRootView).payWaySettingSuccess(Constants.payWaySettingBack);
        } else {
            ((Step3Contract.Model) this.mModel).getPayWaySetting().subscribeOn(Schedulers.io()).doOnSubscribe(new Consumer() { // from class: com.yj.coffeemachines.mvp.presenter.-$$Lambda$Step3Presenter$XOP-N_igrpff1A9YFyfR2ptvsiE
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    Step3Presenter.this.lambda$getPayWaySetting$28$Step3Presenter((Disposable) obj);
                }
            }).subscribeOn(AndroidSchedulers.mainThread()).doFinally(new Action() { // from class: com.yj.coffeemachines.mvp.presenter.-$$Lambda$Step3Presenter$L6OTjkj4FqCE0dnBxZgm03zpXyg
                @Override // io.reactivex.functions.Action
                public final void run() {
                    Step3Presenter.this.lambda$getPayWaySetting$29$Step3Presenter();
                }
            }).compose(RxLifecycleUtils.bindToLifecycle(this.mRootView)).subscribe(new ErrorHandleSubscriber<String>(this.mErrorHandler) { // from class: com.yj.coffeemachines.mvp.presenter.Step3Presenter.17
                @Override // io.reactivex.Observer
                public void onNext(String str) {
                    Tools.upLocalLog("查询设备存在哪些类型的支付配置" + new Gson().toJson(str));
                    PayWaySettingBack payWaySettingBack = (PayWaySettingBack) JSON.parseObject(str, PayWaySettingBack.class);
                    if (payWaySettingBack.getSuccess()) {
                        ((Step3Contract.View) Step3Presenter.this.mRootView).payWaySettingSuccess(payWaySettingBack);
                    } else {
                        ((Step3Contract.View) Step3Presenter.this.mRootView).payWaySettingFail();
                    }
                }
            });
        }
    }

    public /* synthetic */ void lambda$barcodepay$32$Step3Presenter(Disposable disposable) throws Exception {
        ((Step3Contract.View) this.mRootView).showLoading();
    }

    public /* synthetic */ void lambda$barcodepay$33$Step3Presenter() throws Exception {
        ((Step3Contract.View) this.mRootView).hideLoading();
    }

    public /* synthetic */ void lambda$getPayWaySetting$28$Step3Presenter(Disposable disposable) throws Exception {
        ((Step3Contract.View) this.mRootView).showLoading();
    }

    public /* synthetic */ void lambda$getPayWaySetting$29$Step3Presenter() throws Exception {
        ((Step3Contract.View) this.mRootView).hideLoading();
    }

    public /* synthetic */ void lambda$putOrder$0$Step3Presenter(Disposable disposable) throws Exception {
        ((Step3Contract.View) this.mRootView).showLoading();
    }

    public /* synthetic */ void lambda$putOrder$1$Step3Presenter() throws Exception {
        ((Step3Contract.View) this.mRootView).hideLoading();
    }

    public /* synthetic */ void lambda$putOrder$10$Step3Presenter(Disposable disposable) throws Exception {
        ((Step3Contract.View) this.mRootView).showLoading();
    }

    public /* synthetic */ void lambda$putOrder$11$Step3Presenter() throws Exception {
        ((Step3Contract.View) this.mRootView).hideLoading();
    }

    public /* synthetic */ void lambda$putOrder$12$Step3Presenter(Disposable disposable) throws Exception {
        ((Step3Contract.View) this.mRootView).showLoading();
    }

    public /* synthetic */ void lambda$putOrder$13$Step3Presenter() throws Exception {
        ((Step3Contract.View) this.mRootView).hideLoading();
    }

    public /* synthetic */ void lambda$putOrder$14$Step3Presenter(Disposable disposable) throws Exception {
        ((Step3Contract.View) this.mRootView).showLoading();
    }

    public /* synthetic */ void lambda$putOrder$15$Step3Presenter() throws Exception {
        ((Step3Contract.View) this.mRootView).hideLoading();
    }

    public /* synthetic */ void lambda$putOrder$16$Step3Presenter(Disposable disposable) throws Exception {
        ((Step3Contract.View) this.mRootView).showLoading();
    }

    public /* synthetic */ void lambda$putOrder$17$Step3Presenter() throws Exception {
        ((Step3Contract.View) this.mRootView).hideLoading();
    }

    public /* synthetic */ void lambda$putOrder$18$Step3Presenter(Disposable disposable) throws Exception {
        ((Step3Contract.View) this.mRootView).showLoading();
    }

    public /* synthetic */ void lambda$putOrder$19$Step3Presenter() throws Exception {
        ((Step3Contract.View) this.mRootView).hideLoading();
    }

    public /* synthetic */ void lambda$putOrder$2$Step3Presenter(Disposable disposable) throws Exception {
        ((Step3Contract.View) this.mRootView).showLoading();
    }

    public /* synthetic */ void lambda$putOrder$20$Step3Presenter(Disposable disposable) throws Exception {
        ((Step3Contract.View) this.mRootView).showLoading();
    }

    public /* synthetic */ void lambda$putOrder$21$Step3Presenter() throws Exception {
        ((Step3Contract.View) this.mRootView).hideLoading();
    }

    public /* synthetic */ void lambda$putOrder$22$Step3Presenter(Disposable disposable) throws Exception {
        ((Step3Contract.View) this.mRootView).showLoading();
    }

    public /* synthetic */ void lambda$putOrder$23$Step3Presenter() throws Exception {
        ((Step3Contract.View) this.mRootView).hideLoading();
    }

    public /* synthetic */ void lambda$putOrder$24$Step3Presenter(Disposable disposable) throws Exception {
        ((Step3Contract.View) this.mRootView).showLoading();
    }

    public /* synthetic */ void lambda$putOrder$25$Step3Presenter() throws Exception {
        ((Step3Contract.View) this.mRootView).hideLoading();
    }

    public /* synthetic */ void lambda$putOrder$3$Step3Presenter() throws Exception {
        ((Step3Contract.View) this.mRootView).hideLoading();
    }

    public /* synthetic */ void lambda$putOrder$4$Step3Presenter(Disposable disposable) throws Exception {
        ((Step3Contract.View) this.mRootView).showLoading();
    }

    public /* synthetic */ void lambda$putOrder$5$Step3Presenter() throws Exception {
        ((Step3Contract.View) this.mRootView).hideLoading();
    }

    public /* synthetic */ void lambda$putOrder$6$Step3Presenter(Disposable disposable) throws Exception {
        ((Step3Contract.View) this.mRootView).showLoading();
    }

    public /* synthetic */ void lambda$putOrder$7$Step3Presenter() throws Exception {
        ((Step3Contract.View) this.mRootView).hideLoading();
    }

    public /* synthetic */ void lambda$putOrder$8$Step3Presenter(Disposable disposable) throws Exception {
        ((Step3Contract.View) this.mRootView).showLoading();
    }

    public /* synthetic */ void lambda$putOrder$9$Step3Presenter() throws Exception {
        ((Step3Contract.View) this.mRootView).hideLoading();
    }

    public /* synthetic */ void lambda$putOrder_eRmbScan$30$Step3Presenter(Disposable disposable) throws Exception {
        ((Step3Contract.View) this.mRootView).showLoading();
    }

    public /* synthetic */ void lambda$putOrder_eRmbScan$31$Step3Presenter() throws Exception {
        ((Step3Contract.View) this.mRootView).hideLoading();
    }

    @Override // com.jess.arms.mvp.BasePresenter, com.jess.arms.mvp.IPresenter
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
        this.mAppManager = null;
        this.mImageLoader = null;
        this.mApplication = null;
    }

    public void orderCancel(String str, int i) {
        if (TextUtils.isEmpty(str)) {
            Tools.upLocalLog("预支付取消outTradeNo为空");
        } else if (i == 5 || i == 15) {
            ((Step3Contract.Model) this.mModel).cancelOrder().subscribeOn(Schedulers.io()).retryWhen(new RetryWithDelay(2, 1)).subscribeOn(AndroidSchedulers.mainThread()).compose(RxLifecycleUtils.bindToLifecycle(this.mRootView)).subscribe(new ErrorHandleSubscriber<Boolean>(this.mErrorHandler) { // from class: com.yj.coffeemachines.mvp.presenter.Step3Presenter.19
                @Override // me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber, io.reactivex.Observer
                public void onError(Throwable th) {
                    super.onError(th);
                    Tools.upLocalLog("预支付取消失败：" + th.getMessage());
                }

                @Override // io.reactivex.Observer
                public void onNext(@NonNull Boolean bool) {
                    if (bool.booleanValue()) {
                        Tools.upLocalLog("预支付取消成功");
                    } else {
                        Tools.upLocalLog("预支付取消失败");
                    }
                }
            });
        } else {
            ((Step3Contract.Model) this.mModel).orderCancel(str).subscribeOn(Schedulers.io()).retryWhen(new RetryWithDelay(2, 1)).subscribeOn(AndroidSchedulers.mainThread()).compose(RxLifecycleUtils.bindToLifecycle(this.mRootView)).subscribe(new ErrorHandleSubscriber<OrderCancelBack>(this.mErrorHandler) { // from class: com.yj.coffeemachines.mvp.presenter.Step3Presenter.20
                @Override // io.reactivex.Observer
                public void onNext(@NonNull OrderCancelBack orderCancelBack) {
                    if (orderCancelBack.getCode() != 200) {
                        ArmsUtils.snackbarText(orderCancelBack.getMsg());
                        Tools.upLocalLog("预支付取消失败：" + orderCancelBack.getMsg());
                        return;
                    }
                    if (orderCancelBack.isData()) {
                        Tools.upLocalLog("预支付取消成功");
                        return;
                    }
                    ArmsUtils.snackbarText(orderCancelBack.getMsg());
                    Tools.upLocalLog("预支付取消失败：" + orderCancelBack.getMsg());
                }
            });
        }
    }

    public void putOrder(final int i) {
        if (i == 1) {
            DBHelper.getOrderMessageBeanDao().insert(new OrderMessageBean(null, "商品选择", 1, DataUtils.currentTime(), "聚合支付", System.currentTimeMillis()));
            ((Step3Contract.Model) this.mModel).putOrder().subscribeOn(Schedulers.io()).retryWhen(new RetryWithDelay(2, 1)).doOnSubscribe(new Consumer() { // from class: com.yj.coffeemachines.mvp.presenter.-$$Lambda$Step3Presenter$h1MCwCkdfm3ZPo1V5rzzg9mthqs
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    Step3Presenter.this.lambda$putOrder$0$Step3Presenter((Disposable) obj);
                }
            }).subscribeOn(AndroidSchedulers.mainThread()).doFinally(new Action() { // from class: com.yj.coffeemachines.mvp.presenter.-$$Lambda$Step3Presenter$5aN9ODM415gprliqAPo8VIG9Ecw
                @Override // io.reactivex.functions.Action
                public final void run() {
                    Step3Presenter.this.lambda$putOrder$1$Step3Presenter();
                }
            }).compose(RxLifecycleUtils.bindToLifecycle(this.mRootView)).subscribe(new ErrorHandleSubscriber<UnionPayGenOrderBack>(this.mErrorHandler) { // from class: com.yj.coffeemachines.mvp.presenter.Step3Presenter.1
                @Override // io.reactivex.Observer
                public void onNext(UnionPayGenOrderBack unionPayGenOrderBack) {
                    Tools.upLocalLog("选择聚合支付:" + new Gson().toJson(unionPayGenOrderBack));
                    if (unionPayGenOrderBack.getCode() == 200) {
                        ((Step3Contract.View) Step3Presenter.this.mRootView).putOrderSuccessUnion(unionPayGenOrderBack.getData());
                    } else {
                        ((Step3Contract.View) Step3Presenter.this.mRootView).showOrderFail(true);
                        ArmsUtils.snackbarText(unionPayGenOrderBack.getMsg());
                        ((Step3Contract.View) Step3Presenter.this.mRootView).putOrderFailQrcode(unionPayGenOrderBack.getMsg());
                    }
                }
            });
            return;
        }
        if (i == 5 || i == 15 || i == PayType.NORTH_PAY || i == PayType.TP_PAY) {
            ((Step3Contract.Model) this.mModel).unifiedOrder(i).subscribeOn(Schedulers.io()).retryWhen(new RetryWithDelay(2, 1)).doOnSubscribe(new Consumer() { // from class: com.yj.coffeemachines.mvp.presenter.-$$Lambda$Step3Presenter$pR-lVFZEc6iLjmyj4eUfTJqDFUc
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    Step3Presenter.this.lambda$putOrder$2$Step3Presenter((Disposable) obj);
                }
            }).subscribeOn(AndroidSchedulers.mainThread()).doFinally(new Action() { // from class: com.yj.coffeemachines.mvp.presenter.-$$Lambda$Step3Presenter$SfrWWJMpY7tgP1jdJTd4SLxhysU
                @Override // io.reactivex.functions.Action
                public final void run() {
                    Step3Presenter.this.lambda$putOrder$3$Step3Presenter();
                }
            }).compose(RxLifecycleUtils.bindToLifecycle(this.mRootView)).subscribe(new ErrorHandleSubscriber<PayOrderBean>(this.mErrorHandler) { // from class: com.yj.coffeemachines.mvp.presenter.Step3Presenter.2
                @Override // me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber, io.reactivex.Observer
                public void onError(Throwable th) {
                    super.onError(th);
                    ArmsUtils.snackbarText(th.getMessage());
                    ((Step3Contract.View) Step3Presenter.this.mRootView).putOrderFailQrcode(th.getMessage());
                }

                @Override // io.reactivex.Observer
                public void onNext(PayOrderBean payOrderBean) {
                    Tools.upLocalLog("统一支付接口:" + payOrderBean.toString());
                    ((Step3Contract.View) Step3Presenter.this.mRootView).putOrderSuccessWeChar(payOrderBean);
                }
            });
            return;
        }
        if (i == 6) {
            ((Step3Contract.Model) this.mModel).putOrderALiPay().subscribeOn(Schedulers.io()).retryWhen(new RetryWithDelay(2, 1)).doOnSubscribe(new Consumer() { // from class: com.yj.coffeemachines.mvp.presenter.-$$Lambda$Step3Presenter$TnHomYGViKCF-DCX8l0d2lCSoao
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    Step3Presenter.this.lambda$putOrder$4$Step3Presenter((Disposable) obj);
                }
            }).subscribeOn(AndroidSchedulers.mainThread()).doFinally(new Action() { // from class: com.yj.coffeemachines.mvp.presenter.-$$Lambda$Step3Presenter$723G81pDLplDf5iw-wYyWj0k6CE
                @Override // io.reactivex.functions.Action
                public final void run() {
                    Step3Presenter.this.lambda$putOrder$5$Step3Presenter();
                }
            }).compose(RxLifecycleUtils.bindToLifecycle(this.mRootView)).subscribe(new ErrorHandleSubscriber<ALiPayGenOrderBack>(this.mErrorHandler) { // from class: com.yj.coffeemachines.mvp.presenter.Step3Presenter.3
                @Override // io.reactivex.Observer
                public void onNext(ALiPayGenOrderBack aLiPayGenOrderBack) {
                    Tools.upLocalLog("选择支付宝支付:" + new Gson().toJson(aLiPayGenOrderBack));
                    ALiPayGenOrderBack.DataBean data = aLiPayGenOrderBack.getData();
                    if (data == null) {
                        ArmsUtils.snackbarText(aLiPayGenOrderBack.getMsg());
                        ((Step3Contract.View) Step3Presenter.this.mRootView).putOrderFailQrcode(aLiPayGenOrderBack.getMsg());
                        return;
                    }
                    if (aLiPayGenOrderBack.getCode() != 200) {
                        ArmsUtils.snackbarText(aLiPayGenOrderBack.getMsg());
                        ((Step3Contract.View) Step3Presenter.this.mRootView).putOrderFailQrcode(aLiPayGenOrderBack.getMsg());
                    } else if (!"10000".equals(data.getCode()) || data.getQr_code() == null) {
                        ArmsUtils.snackbarText(data.getSub_msg());
                        ((Step3Contract.View) Step3Presenter.this.mRootView).putOrderFailQrcode(data.getSub_msg());
                    } else {
                        data.setQr_code(data.getQr_code().replace("\\", ""));
                        ((Step3Contract.View) Step3Presenter.this.mRootView).putOrderSuccessALiPay(data);
                    }
                }
            });
            return;
        }
        if (i == 7) {
            ((Step3Contract.Model) this.mModel).digitalPayGenOrder().subscribeOn(Schedulers.io()).retryWhen(new RetryWithDelay(2, 1)).doOnSubscribe(new Consumer() { // from class: com.yj.coffeemachines.mvp.presenter.-$$Lambda$Step3Presenter$G9Ltp2fYc8zTnWUqmnrsqm7pX6g
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    Step3Presenter.this.lambda$putOrder$6$Step3Presenter((Disposable) obj);
                }
            }).subscribeOn(AndroidSchedulers.mainThread()).doFinally(new Action() { // from class: com.yj.coffeemachines.mvp.presenter.-$$Lambda$Step3Presenter$37EkUCtMc9r9Mwq16JoJ5lcwO1o
                @Override // io.reactivex.functions.Action
                public final void run() {
                    Step3Presenter.this.lambda$putOrder$7$Step3Presenter();
                }
            }).compose(RxLifecycleUtils.bindToLifecycle(this.mRootView)).subscribe(new ErrorHandleSubscriber<DigitalPayGenOrderBack>(this.mErrorHandler) { // from class: com.yj.coffeemachines.mvp.presenter.Step3Presenter.4
                @Override // io.reactivex.Observer
                public void onNext(DigitalPayGenOrderBack digitalPayGenOrderBack) {
                    Tools.upLocalLog("选择数字货币支付:" + new Gson().toJson(digitalPayGenOrderBack));
                    if (digitalPayGenOrderBack.getCode() == 200) {
                        ((Step3Contract.View) Step3Presenter.this.mRootView).putOrderSuccessERMB(digitalPayGenOrderBack.getData());
                    } else {
                        ArmsUtils.snackbarText(digitalPayGenOrderBack.getMsg());
                        ((Step3Contract.View) Step3Presenter.this.mRootView).putOrderFailQrcode(digitalPayGenOrderBack.getMsg());
                    }
                }
            });
            return;
        }
        if (i == 2) {
            ((Step3Contract.Model) this.mModel).putOrderCrsh(i).subscribeOn(Schedulers.io()).retryWhen(new RetryWithDelay(2, 1)).doOnSubscribe(new Consumer() { // from class: com.yj.coffeemachines.mvp.presenter.-$$Lambda$Step3Presenter$peG0MK0hYGPpGWCnY-YYtozD7Qw
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    Step3Presenter.this.lambda$putOrder$8$Step3Presenter((Disposable) obj);
                }
            }).subscribeOn(AndroidSchedulers.mainThread()).doFinally(new Action() { // from class: com.yj.coffeemachines.mvp.presenter.-$$Lambda$Step3Presenter$nxo7su5bhI45OEGWCHFaFZPRn10
                @Override // io.reactivex.functions.Action
                public final void run() {
                    Step3Presenter.this.lambda$putOrder$9$Step3Presenter();
                }
            }).compose(RxLifecycleUtils.bindToLifecycle(this.mRootView)).subscribe(new ErrorHandleSubscriber<GenOrderBack>(this.mErrorHandler) { // from class: com.yj.coffeemachines.mvp.presenter.Step3Presenter.5
                @Override // me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber, io.reactivex.Observer
                public void onError(Throwable th) {
                    super.onError(th);
                    ((Step3Contract.View) Step3Presenter.this.mRootView).showOrderFail(true);
                }

                @Override // io.reactivex.Observer
                public void onNext(GenOrderBack genOrderBack) {
                    Tools.upLocalLog("选择刷卡支付:" + new Gson().toJson(genOrderBack));
                    if (genOrderBack.getCode().intValue() == 200) {
                        ((Step3Contract.View) Step3Presenter.this.mRootView).putOrderOtherSuccess(genOrderBack, i);
                    } else {
                        ArmsUtils.snackbarText(genOrderBack.getMsg());
                        ((Step3Contract.View) Step3Presenter.this.mRootView).putOrderFailQrcode(genOrderBack.getMsg());
                    }
                }
            });
            return;
        }
        if (i == 3) {
            ((Step3Contract.Model) this.mModel).putOrderCrsh(i).subscribeOn(Schedulers.io()).retryWhen(new RetryWithDelay(2, 1)).doOnSubscribe(new Consumer() { // from class: com.yj.coffeemachines.mvp.presenter.-$$Lambda$Step3Presenter$M0aT3ITAiXlTznmg3U-IDgM-GmQ
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    Step3Presenter.this.lambda$putOrder$10$Step3Presenter((Disposable) obj);
                }
            }).subscribeOn(AndroidSchedulers.mainThread()).doFinally(new Action() { // from class: com.yj.coffeemachines.mvp.presenter.-$$Lambda$Step3Presenter$W8CZlcNbhnz5kLiMYZMmAUNIoAU
                @Override // io.reactivex.functions.Action
                public final void run() {
                    Step3Presenter.this.lambda$putOrder$11$Step3Presenter();
                }
            }).compose(RxLifecycleUtils.bindToLifecycle(this.mRootView)).subscribe(new ErrorHandleSubscriber<GenOrderBack>(this.mErrorHandler) { // from class: com.yj.coffeemachines.mvp.presenter.Step3Presenter.6
                @Override // me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber, io.reactivex.Observer
                public void onError(Throwable th) {
                    super.onError(th);
                    ((Step3Contract.View) Step3Presenter.this.mRootView).showOrderFail(false);
                }

                @Override // io.reactivex.Observer
                public void onNext(GenOrderBack genOrderBack) {
                    Tools.upLocalLog("现金支付：" + new Gson().toJson(genOrderBack));
                    if (genOrderBack.getCode().intValue() == 200) {
                        ((Step3Contract.View) Step3Presenter.this.mRootView).putOrderOtherSuccess(genOrderBack, i);
                        return;
                    }
                    ArmsUtils.snackbarText(genOrderBack.getMsg());
                    ((Step3Contract.View) Step3Presenter.this.mRootView).putOrderFailQrcode(genOrderBack.getMsg());
                    ((Step3Contract.View) Step3Presenter.this.mRootView).showOrderFail(false);
                }
            });
            return;
        }
        if (i == 4) {
            ((Step3Contract.Model) this.mModel).putOrderCrsh(i).subscribeOn(Schedulers.io()).retryWhen(new RetryWithDelay(2, 1)).doOnSubscribe(new Consumer() { // from class: com.yj.coffeemachines.mvp.presenter.-$$Lambda$Step3Presenter$8taqL6waB6exCC3EJ-lqtKpry9c
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    Step3Presenter.this.lambda$putOrder$12$Step3Presenter((Disposable) obj);
                }
            }).subscribeOn(AndroidSchedulers.mainThread()).doFinally(new Action() { // from class: com.yj.coffeemachines.mvp.presenter.-$$Lambda$Step3Presenter$GMi-59Ll4cL_L73FSppMWOnV1b4
                @Override // io.reactivex.functions.Action
                public final void run() {
                    Step3Presenter.this.lambda$putOrder$13$Step3Presenter();
                }
            }).compose(RxLifecycleUtils.bindToLifecycle(this.mRootView)).subscribe(new ErrorHandleSubscriber<GenOrderBack>(this.mErrorHandler) { // from class: com.yj.coffeemachines.mvp.presenter.Step3Presenter.7
                @Override // me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber, io.reactivex.Observer
                public void onError(Throwable th) {
                    super.onError(th);
                    ((Step3Contract.View) Step3Presenter.this.mRootView).showOrderFail(false);
                }

                @Override // io.reactivex.Observer
                public void onNext(GenOrderBack genOrderBack) {
                    Tools.upLocalLog("选择免支付:" + new Gson().toJson(genOrderBack));
                    if (genOrderBack.getCode().intValue() == 200) {
                        ((Step3Contract.View) Step3Presenter.this.mRootView).putOrderOtherSuccess(genOrderBack, i);
                        return;
                    }
                    ArmsUtils.snackbarText(genOrderBack.getMsg());
                    ((Step3Contract.View) Step3Presenter.this.mRootView).putOrderFailQrcode(genOrderBack.getMsg());
                    ((Step3Contract.View) Step3Presenter.this.mRootView).showOrderFail(false);
                }
            });
            return;
        }
        if (i == 8) {
            ((Step3Contract.Model) this.mModel).digitalPayGenOrder("2").subscribeOn(Schedulers.io()).retryWhen(new RetryWithDelay(2, 1)).doOnSubscribe(new Consumer() { // from class: com.yj.coffeemachines.mvp.presenter.-$$Lambda$Step3Presenter$DuHMRyIhjkQGPOUb0uDNLHB7Eak
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    Step3Presenter.this.lambda$putOrder$14$Step3Presenter((Disposable) obj);
                }
            }).subscribeOn(AndroidSchedulers.mainThread()).doFinally(new Action() { // from class: com.yj.coffeemachines.mvp.presenter.-$$Lambda$Step3Presenter$fkL6L6jbygYBMy5saqkP4EMHTYM
                @Override // io.reactivex.functions.Action
                public final void run() {
                    Step3Presenter.this.lambda$putOrder$15$Step3Presenter();
                }
            }).compose(RxLifecycleUtils.bindToLifecycle(this.mRootView)).subscribe(new ErrorHandleSubscriber<DigitalPayGenOrderBack>(this.mErrorHandler) { // from class: com.yj.coffeemachines.mvp.presenter.Step3Presenter.8
                @Override // io.reactivex.Observer
                public void onNext(DigitalPayGenOrderBack digitalPayGenOrderBack) {
                    Tools.upLocalLog("惠支付:" + new Gson().toJson(digitalPayGenOrderBack));
                    if (digitalPayGenOrderBack.getCode() == 200) {
                        ((Step3Contract.View) Step3Presenter.this.mRootView).putOrderSuccessHui(digitalPayGenOrderBack.getData());
                    } else {
                        ArmsUtils.snackbarText(digitalPayGenOrderBack.getMsg());
                        ((Step3Contract.View) Step3Presenter.this.mRootView).putOrderFailQrcode(digitalPayGenOrderBack.getMsg());
                    }
                }
            });
            return;
        }
        if (i == 9) {
            ((Step3Contract.Model) this.mModel).icbcPayGenOrder().subscribeOn(Schedulers.io()).retryWhen(new RetryWithDelay(2, 1)).doOnSubscribe(new Consumer() { // from class: com.yj.coffeemachines.mvp.presenter.-$$Lambda$Step3Presenter$fHBoaI34Bw6b13Mkdk2W7Ukpvhs
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    Step3Presenter.this.lambda$putOrder$16$Step3Presenter((Disposable) obj);
                }
            }).subscribeOn(AndroidSchedulers.mainThread()).doFinally(new Action() { // from class: com.yj.coffeemachines.mvp.presenter.-$$Lambda$Step3Presenter$l5jgSH1Vz-x8kLYXgQqteV16d_o
                @Override // io.reactivex.functions.Action
                public final void run() {
                    Step3Presenter.this.lambda$putOrder$17$Step3Presenter();
                }
            }).compose(RxLifecycleUtils.bindToLifecycle(this.mRootView)).subscribe(new ErrorHandleSubscriber<icbcPayGenOrderBack>(this.mErrorHandler) { // from class: com.yj.coffeemachines.mvp.presenter.Step3Presenter.9
                @Override // io.reactivex.Observer
                public void onNext(@NonNull icbcPayGenOrderBack icbcpaygenorderback) {
                    Tools.upLocalLog("工行支付:" + new Gson().toJson(icbcpaygenorderback));
                    if (icbcpaygenorderback.getCode() == 200) {
                        ((Step3Contract.View) Step3Presenter.this.mRootView).putOrderSuccessIcbc(icbcpaygenorderback.getData());
                        return;
                    }
                    ArmsUtils.snackbarText(icbcpaygenorderback.getMsg() + icbcpaygenorderback.getData().getReturn_msg());
                    ((Step3Contract.View) Step3Presenter.this.mRootView).putOrderFailQrcode(icbcpaygenorderback.getMsg() + icbcpaygenorderback.getData().getReturn_msg());
                }
            });
            return;
        }
        if (i == 10) {
            ((Step3Contract.Model) this.mModel).putOrderCrsh(2).subscribeOn(Schedulers.io()).retryWhen(new RetryWithDelay(2, 1)).doOnSubscribe(new Consumer() { // from class: com.yj.coffeemachines.mvp.presenter.-$$Lambda$Step3Presenter$tkXn8G13R3Yc5uGzneRRdxbpFbQ
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    Step3Presenter.this.lambda$putOrder$18$Step3Presenter((Disposable) obj);
                }
            }).subscribeOn(AndroidSchedulers.mainThread()).doFinally(new Action() { // from class: com.yj.coffeemachines.mvp.presenter.-$$Lambda$Step3Presenter$bgp5MqwKbkFffgtM8G1KPQCVz1Y
                @Override // io.reactivex.functions.Action
                public final void run() {
                    Step3Presenter.this.lambda$putOrder$19$Step3Presenter();
                }
            }).compose(RxLifecycleUtils.bindToLifecycle(this.mRootView)).subscribe(new ErrorHandleSubscriber<GenOrderBack>(this.mErrorHandler) { // from class: com.yj.coffeemachines.mvp.presenter.Step3Presenter.10
                @Override // io.reactivex.Observer
                public void onNext(GenOrderBack genOrderBack) {
                    Tools.upLocalLog("员工餐卡支付：" + new Gson().toJson(genOrderBack));
                    if (genOrderBack.getCode().intValue() == 200) {
                        ((Step3Contract.View) Step3Presenter.this.mRootView).putOrderOtherSuccess(genOrderBack, i);
                    } else {
                        ArmsUtils.snackbarText(genOrderBack.getMsg());
                        ((Step3Contract.View) Step3Presenter.this.mRootView).putOrderFailQrcode(genOrderBack.getMsg());
                    }
                }
            });
            return;
        }
        if (i == 11) {
            ((Step3Contract.Model) this.mModel).putOrderCrsh(2).subscribeOn(Schedulers.io()).retryWhen(new RetryWithDelay(2, 1)).doOnSubscribe(new Consumer() { // from class: com.yj.coffeemachines.mvp.presenter.-$$Lambda$Step3Presenter$qAf9xKh2EUCfcOvSu-WVRJ9po7I
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    Step3Presenter.this.lambda$putOrder$20$Step3Presenter((Disposable) obj);
                }
            }).subscribeOn(AndroidSchedulers.mainThread()).doFinally(new Action() { // from class: com.yj.coffeemachines.mvp.presenter.-$$Lambda$Step3Presenter$wEz1Gg-beMAlEE5SZgpisW4OVs4
                @Override // io.reactivex.functions.Action
                public final void run() {
                    Step3Presenter.this.lambda$putOrder$21$Step3Presenter();
                }
            }).compose(RxLifecycleUtils.bindToLifecycle(this.mRootView)).subscribe(new ErrorHandleSubscriber<GenOrderBack>(this.mErrorHandler) { // from class: com.yj.coffeemachines.mvp.presenter.Step3Presenter.11
                @Override // io.reactivex.Observer
                public void onNext(GenOrderBack genOrderBack) {
                    Tools.upLocalLog("北极星支付：" + new Gson().toJson(genOrderBack));
                    if (genOrderBack.getCode().intValue() == 200) {
                        ((Step3Contract.View) Step3Presenter.this.mRootView).putOrderOtherSuccess(genOrderBack, i);
                    } else {
                        ArmsUtils.snackbarText(genOrderBack.getMsg());
                        ((Step3Contract.View) Step3Presenter.this.mRootView).putOrderFailQrcode(genOrderBack.getMsg());
                    }
                }
            });
            return;
        }
        if (i == 12) {
            ((Step3Contract.Model) this.mModel).putOrderCrsh(2).subscribeOn(Schedulers.io()).retryWhen(new RetryWithDelay(2, 1)).doOnSubscribe(new Consumer() { // from class: com.yj.coffeemachines.mvp.presenter.-$$Lambda$Step3Presenter$lAeMXcLyj_ly0_rg-piQXMlztmM
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    Step3Presenter.this.lambda$putOrder$22$Step3Presenter((Disposable) obj);
                }
            }).subscribeOn(AndroidSchedulers.mainThread()).doFinally(new Action() { // from class: com.yj.coffeemachines.mvp.presenter.-$$Lambda$Step3Presenter$9URD5e8vLIJzB5F58wRc9mNMZ5E
                @Override // io.reactivex.functions.Action
                public final void run() {
                    Step3Presenter.this.lambda$putOrder$23$Step3Presenter();
                }
            }).compose(RxLifecycleUtils.bindToLifecycle(this.mRootView)).subscribe(new ErrorHandleSubscriber<GenOrderBack>(this.mErrorHandler) { // from class: com.yj.coffeemachines.mvp.presenter.Step3Presenter.12
                @Override // io.reactivex.Observer
                public void onNext(GenOrderBack genOrderBack) {
                    Tools.upLocalLog("神华餐卡支付：" + new Gson().toJson(genOrderBack));
                    if (genOrderBack.getCode().intValue() == 200) {
                        ((Step3Contract.View) Step3Presenter.this.mRootView).putOrderOtherSuccess(genOrderBack, i);
                    } else {
                        ArmsUtils.snackbarText(genOrderBack.getMsg());
                        ((Step3Contract.View) Step3Presenter.this.mRootView).putOrderFailQrcode(genOrderBack.getMsg());
                    }
                }
            });
        } else if (i == 13) {
            Tools.upLocalLog("选择扫呗正扫支付");
            ((Step3Contract.Model) this.mModel).aggregationCodePayment().subscribeOn(Schedulers.io()).retryWhen(new RetryWithDelay(2, 1)).doOnSubscribe(new Consumer() { // from class: com.yj.coffeemachines.mvp.presenter.-$$Lambda$Step3Presenter$S-xxZgvx6wlmXM9hO7afCIDv70g
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    Step3Presenter.this.lambda$putOrder$24$Step3Presenter((Disposable) obj);
                }
            }).subscribeOn(AndroidSchedulers.mainThread()).doFinally(new Action() { // from class: com.yj.coffeemachines.mvp.presenter.-$$Lambda$Step3Presenter$9czzD6QORBl8DNf9y2gMIzRHTwo
                @Override // io.reactivex.functions.Action
                public final void run() {
                    Step3Presenter.this.lambda$putOrder$25$Step3Presenter();
                }
            }).compose(RxLifecycleUtils.bindToLifecycle(this.mRootView)).subscribe(new ErrorHandleSubscriber<aggregationCodePayment>(this.mErrorHandler) { // from class: com.yj.coffeemachines.mvp.presenter.Step3Presenter.13
                @Override // io.reactivex.Observer
                public void onNext(aggregationCodePayment aggregationcodepayment) {
                    Tools.upLocalLog("支付回复:" + new Gson().toJson(aggregationcodepayment));
                    aggregationCodePayment.DataBean data = aggregationcodepayment.getData();
                    if (data == null) {
                        ArmsUtils.snackbarText(aggregationcodepayment.getMsg());
                        ((Step3Contract.View) Step3Presenter.this.mRootView).putOrderFailQrcode(aggregationcodepayment.getMsg());
                        return;
                    }
                    if (aggregationcodepayment.getCode() != 200) {
                        ArmsUtils.snackbarText(aggregationcodepayment.getMsg());
                        ((Step3Contract.View) Step3Presenter.this.mRootView).putOrderFailQrcode(aggregationcodepayment.getMsg());
                    } else if (!"01".equals(data.getReturn_code()) || !"01".equals(data.getResult_code())) {
                        ArmsUtils.snackbarText(data.getReturn_msg());
                        ((Step3Contract.View) Step3Presenter.this.mRootView).putOrderFailQrcode(data.getReturn_msg());
                    } else if (!TextUtils.isEmpty(data.getQr_url())) {
                        ((Step3Contract.View) Step3Presenter.this.mRootView).putOrderSuccessAggregationCodePayment(data);
                    } else {
                        ArmsUtils.snackbarText(data.getReturn_msg());
                        ((Step3Contract.View) Step3Presenter.this.mRootView).putOrderFailQrcode(data.getReturn_msg());
                    }
                }
            });
        }
    }

    public void putOrder_eRmbScan(int i, String str) {
        ((Step3Contract.Model) this.mModel).putOrderPayCode(str).subscribeOn(Schedulers.io()).doOnSubscribe(new Consumer() { // from class: com.yj.coffeemachines.mvp.presenter.-$$Lambda$Step3Presenter$tzogNxwG97c1Js73AWAZtJIfhG8
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                Step3Presenter.this.lambda$putOrder_eRmbScan$30$Step3Presenter((Disposable) obj);
            }
        }).subscribeOn(AndroidSchedulers.mainThread()).doFinally(new Action() { // from class: com.yj.coffeemachines.mvp.presenter.-$$Lambda$Step3Presenter$1xKAdnEC6uqzk5vO3TUA-OntN2Y
            @Override // io.reactivex.functions.Action
            public final void run() {
                Step3Presenter.this.lambda$putOrder_eRmbScan$31$Step3Presenter();
            }
        }).compose(RxLifecycleUtils.bindToLifecycle(this.mRootView)).subscribe(new ErrorHandleSubscriber<PayCodeGenOrderBack>(this.mErrorHandler) { // from class: com.yj.coffeemachines.mvp.presenter.Step3Presenter.18
            @Override // io.reactivex.Observer
            public void onNext(PayCodeGenOrderBack payCodeGenOrderBack) {
                if (payCodeGenOrderBack.getCode() == 200) {
                    ((Step3Contract.View) Step3Presenter.this.mRootView).putOrderSuccessERMBCode(payCodeGenOrderBack.getData());
                } else {
                    ArmsUtils.snackbarText(payCodeGenOrderBack.getMsg());
                    ((Step3Contract.View) Step3Presenter.this.mRootView).putOrderFailQrcode(payCodeGenOrderBack.getMsg());
                }
            }
        });
    }

    public void t50PayCreateQr(String str) {
        ((Step3Contract.Model) this.mModel).t50PayCreateQr(str).retryWhen(new RetryWithDelay(2, 1)).compose(RxLifecycleUtils.bindToLifecycle(this.mRootView)).subscribe(new ErrorHandleSubscriber<T50PayOrderQrBean>(this.mErrorHandler) { // from class: com.yj.coffeemachines.mvp.presenter.Step3Presenter.14
            @Override // me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber, io.reactivex.Observer
            public void onError(Throwable th) {
                super.onError(th);
                ((Step3Contract.View) Step3Presenter.this.mRootView).putOrderFailQrcode(th.getMessage());
            }

            @Override // io.reactivex.Observer
            public void onNext(T50PayOrderQrBean t50PayOrderQrBean) {
                Tools.upLocalLog("支付回复:" + new Gson().toJson(t50PayOrderQrBean));
                if (t50PayOrderQrBean != null && !TextUtils.isEmpty(t50PayOrderQrBean.getQr())) {
                    t50PayOrderQrBean.setQr(t50PayOrderQrBean.getQr().replace("\\", ""));
                }
                ((Step3Contract.View) Step3Presenter.this.mRootView).t50PayCreateQr(t50PayOrderQrBean);
            }
        });
    }

    public void t50PayGetWay() {
        ((Step3Contract.Model) this.mModel).t50PayGetWay().subscribeOn(Schedulers.io()).subscribeOn(AndroidSchedulers.mainThread()).compose(RxLifecycleUtils.bindToLifecycle(this.mRootView)).subscribe(new ErrorHandleSubscriber<List<T50PayWayBean>>(this.mErrorHandler) { // from class: com.yj.coffeemachines.mvp.presenter.Step3Presenter.22
            @Override // io.reactivex.Observer
            public void onNext(List<T50PayWayBean> list) {
                ((Step3Contract.View) Step3Presenter.this.mRootView).t50PayWay(list);
            }
        });
    }
}
