package com.yj.coffeemachines.mvp.presenter;

import android.app.Application;
import android.text.TextUtils;
import com.blankj.utilcode.util.StringUtils;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.integration.AppManager;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.utils.RxLifecycleUtils;
import com.yj.coffeemachines.Constants;
import com.yj.coffeemachines.R;
import com.yj.coffeemachines.helper.Tools;
import com.yj.coffeemachines.mvp.contract.HomeContract;
import com.yj.coffeemachines.mvp.model.beans.AliPayReturnAmtBack;
import com.yj.coffeemachines.mvp.model.beans.DigitalRefundBack;
import com.yj.coffeemachines.mvp.model.beans.LoginBack;
import com.yj.coffeemachines.mvp.model.beans.OrderCancelBack;
import com.yj.coffeemachines.mvp.model.beans.OrderRefundBean;
import com.yj.coffeemachines.mvp.model.beans.OutStockOverBack;
import com.yj.coffeemachines.mvp.model.beans.ProduceFailBack;
import com.yj.coffeemachines.mvp.model.beans.ProduceOverBack;
import com.yj.coffeemachines.mvp.model.beans.UnionPayReturnAmtBack;
import com.yj.coffeemachines.mvp.model.beans.icbcPayReturnAmtBack;
import com.yj.coffeemachines.mvp.model.beans.refund;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import javax.inject.Inject;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;
import me.jessyan.rxerrorhandler.handler.RetryWithDelay;

@ActivityScope
/* loaded from: classes.dex */
public class HomePresenter extends BasePresenter<HomeContract.Model, HomeContract.View> {

    @Inject
    AppManager mAppManager;

    @Inject
    Application mApplication;

    @Inject
    RxErrorHandler mErrorHandler;

    @Inject
    ImageLoader mImageLoader;

    @Inject
    public HomePresenter(HomeContract.Model model, HomeContract.View view) {
        super(model, view);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$SaoBeiPayReturnAmt$20(Disposable disposable) throws Exception {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$SaoBeiPayReturnAmt$21() throws Exception {
    }

    public void ERMBReturnAmt() {
        ((HomeContract.Model) this.mModel).ERMBReturnAmt().subscribeOn(Schedulers.io()).doOnSubscribe(new Consumer() { // from class: com.yj.coffeemachines.mvp.presenter.-$$Lambda$HomePresenter$hAW1DA16wdzK8awrHLmdHvjKxLE
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                HomePresenter.this.lambda$ERMBReturnAmt$12$HomePresenter((Disposable) obj);
            }
        }).subscribeOn(AndroidSchedulers.mainThread()).doFinally(new Action() { // from class: com.yj.coffeemachines.mvp.presenter.-$$Lambda$HomePresenter$l7CS3LFb3WjIQNw0MLJstvU8iKQ
            @Override // io.reactivex.functions.Action
            public final void run() {
                HomePresenter.this.lambda$ERMBReturnAmt$13$HomePresenter();
            }
        }).compose(RxLifecycleUtils.bindToLifecycle(this.mRootView)).subscribe(new ErrorHandleSubscriber<DigitalRefundBack>(this.mErrorHandler) { // from class: com.yj.coffeemachines.mvp.presenter.HomePresenter.8
            @Override // io.reactivex.Observer
            public void onNext(DigitalRefundBack digitalRefundBack) {
                if (digitalRefundBack.getCode() == 200) {
                    return;
                }
                ArmsUtils.snackbarText(digitalRefundBack.getMsg());
            }
        });
    }

    public void ERMBReturnAmtHui(String str) {
        ((HomeContract.Model) this.mModel).ERMBReturnAmtHui(str).subscribeOn(Schedulers.io()).doOnSubscribe(new Consumer() { // from class: com.yj.coffeemachines.mvp.presenter.-$$Lambda$HomePresenter$7KTenjNoAi-R5m6EDXeZLY4mF_A
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                HomePresenter.this.lambda$ERMBReturnAmtHui$16$HomePresenter((Disposable) obj);
            }
        }).subscribeOn(AndroidSchedulers.mainThread()).doFinally(new Action() { // from class: com.yj.coffeemachines.mvp.presenter.-$$Lambda$HomePresenter$Srhe7yoVdum3w-zMN-5vPOtWScQ
            @Override // io.reactivex.functions.Action
            public final void run() {
                HomePresenter.this.lambda$ERMBReturnAmtHui$17$HomePresenter();
            }
        }).compose(RxLifecycleUtils.bindToLifecycle(this.mRootView)).subscribe(new ErrorHandleSubscriber<DigitalRefundBack>(this.mErrorHandler) { // from class: com.yj.coffeemachines.mvp.presenter.HomePresenter.10
            @Override // io.reactivex.Observer
            public void onNext(DigitalRefundBack digitalRefundBack) {
                if (digitalRefundBack.getCode() == 200) {
                    return;
                }
                ArmsUtils.snackbarText(digitalRefundBack.getMsg());
            }
        });
    }

    public void SaoBeiPayReturnAmt() {
        ((HomeContract.Model) this.mModel).refund().subscribeOn(Schedulers.io()).retryWhen(new RetryWithDelay(2, 1)).doOnSubscribe(new Consumer() { // from class: com.yj.coffeemachines.mvp.presenter.-$$Lambda$HomePresenter$aAaELfiOGitAKLgmo2OupBTngV4
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                HomePresenter.lambda$SaoBeiPayReturnAmt$20((Disposable) obj);
            }
        }).subscribeOn(AndroidSchedulers.mainThread()).doFinally(new Action() { // from class: com.yj.coffeemachines.mvp.presenter.-$$Lambda$HomePresenter$qTRf9XbFu-05wU4_hHyvcMQzhSw
            @Override // io.reactivex.functions.Action
            public final void run() {
                HomePresenter.lambda$SaoBeiPayReturnAmt$21();
            }
        }).compose(RxLifecycleUtils.bindToLifecycle(this.mRootView)).subscribe(new ErrorHandleSubscriber<refund>(this.mErrorHandler) { // from class: com.yj.coffeemachines.mvp.presenter.HomePresenter.12
            @Override // io.reactivex.Observer
            public void onNext(refund refundVar) {
                if (refundVar.getCode() != 200) {
                    ArmsUtils.snackbarText(refundVar.getMsg());
                }
                Tools.upLocalLog("支付退款：" + refundVar.toString());
            }
        });
    }

    public void aLiPayReturnAmt() {
        ((HomeContract.Model) this.mModel).aLiPayReturnAmt().subscribeOn(Schedulers.io()).retryWhen(new RetryWithDelay(2, 1)).doOnSubscribe(new Consumer() { // from class: com.yj.coffeemachines.mvp.presenter.-$$Lambda$HomePresenter$b0Pyn_qL5CXXuG6fKY25K2cM67Y
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                HomePresenter.this.lambda$aLiPayReturnAmt$10$HomePresenter((Disposable) obj);
            }
        }).subscribeOn(AndroidSchedulers.mainThread()).doFinally(new Action() { // from class: com.yj.coffeemachines.mvp.presenter.-$$Lambda$HomePresenter$UmnYxcV1tVM7YiTxHTgQwJnOz3k
            @Override // io.reactivex.functions.Action
            public final void run() {
                HomePresenter.this.lambda$aLiPayReturnAmt$11$HomePresenter();
            }
        }).compose(RxLifecycleUtils.bindToLifecycle(this.mRootView)).subscribe(new ErrorHandleSubscriber<AliPayReturnAmtBack>(this.mErrorHandler) { // from class: com.yj.coffeemachines.mvp.presenter.HomePresenter.6
            @Override // io.reactivex.Observer
            public void onNext(AliPayReturnAmtBack aliPayReturnAmtBack) {
                if (aliPayReturnAmtBack.getCode() == 200) {
                    return;
                }
                ArmsUtils.snackbarText(aliPayReturnAmtBack.getMsg());
            }
        });
    }

    public void icbcPayReturnAmt() {
        ((HomeContract.Model) this.mModel).icbcPayReturnAmt().subscribeOn(Schedulers.io()).doOnSubscribe(new Consumer() { // from class: com.yj.coffeemachines.mvp.presenter.-$$Lambda$HomePresenter$DY6PYrw1bcfOAuOBOZWkWIqDbzc
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                HomePresenter.this.lambda$icbcPayReturnAmt$18$HomePresenter((Disposable) obj);
            }
        }).subscribeOn(AndroidSchedulers.mainThread()).doFinally(new Action() { // from class: com.yj.coffeemachines.mvp.presenter.-$$Lambda$HomePresenter$HstoDXjeWaNXDhY9l5J9ZE33gi8
            @Override // io.reactivex.functions.Action
            public final void run() {
                HomePresenter.this.lambda$icbcPayReturnAmt$19$HomePresenter();
            }
        }).compose(RxLifecycleUtils.bindToLifecycle(this.mRootView)).subscribe(new ErrorHandleSubscriber<icbcPayReturnAmtBack>(this.mErrorHandler) { // from class: com.yj.coffeemachines.mvp.presenter.HomePresenter.11
            @Override // io.reactivex.Observer
            public void onNext(@NonNull icbcPayReturnAmtBack icbcpayreturnamtback) {
                if (icbcpayreturnamtback.getCode() == 200) {
                    return;
                }
                ArmsUtils.snackbarText(icbcpayreturnamtback.getMsg());
            }
        });
    }

    public /* synthetic */ void lambda$ERMBReturnAmt$12$HomePresenter(Disposable disposable) throws Exception {
        ((HomeContract.View) this.mRootView).showLoading();
    }

    public /* synthetic */ void lambda$ERMBReturnAmt$13$HomePresenter() throws Exception {
        ((HomeContract.View) this.mRootView).hideLoading();
    }

    public /* synthetic */ void lambda$ERMBReturnAmtHui$16$HomePresenter(Disposable disposable) throws Exception {
        ((HomeContract.View) this.mRootView).showLoading();
    }

    public /* synthetic */ void lambda$ERMBReturnAmtHui$17$HomePresenter() throws Exception {
        ((HomeContract.View) this.mRootView).hideLoading();
    }

    public /* synthetic */ void lambda$aLiPayReturnAmt$10$HomePresenter(Disposable disposable) throws Exception {
        ((HomeContract.View) this.mRootView).showLoading();
    }

    public /* synthetic */ void lambda$aLiPayReturnAmt$11$HomePresenter() throws Exception {
        ((HomeContract.View) this.mRootView).hideLoading();
    }

    public /* synthetic */ void lambda$icbcPayReturnAmt$18$HomePresenter(Disposable disposable) throws Exception {
        ((HomeContract.View) this.mRootView).showLoading();
    }

    public /* synthetic */ void lambda$icbcPayReturnAmt$19$HomePresenter() throws Exception {
        ((HomeContract.View) this.mRootView).hideLoading();
    }

    public /* synthetic */ void lambda$login$0$HomePresenter(Disposable disposable) throws Exception {
        ((HomeContract.View) this.mRootView).showLoading();
    }

    public /* synthetic */ void lambda$login$1$HomePresenter() throws Exception {
        ((HomeContract.View) this.mRootView).hideLoading();
    }

    public /* synthetic */ void lambda$outStockOver$4$HomePresenter(Disposable disposable) throws Exception {
        ((HomeContract.View) this.mRootView).showLoading();
    }

    public /* synthetic */ void lambda$outStockOver$5$HomePresenter() throws Exception {
        ((HomeContract.View) this.mRootView).hideLoading();
    }

    public /* synthetic */ void lambda$produceFail$14$HomePresenter(Disposable disposable) throws Exception {
        ((HomeContract.View) this.mRootView).showLoading();
    }

    public /* synthetic */ void lambda$produceFail$15$HomePresenter() throws Exception {
        ((HomeContract.View) this.mRootView).hideLoading();
    }

    public /* synthetic */ void lambda$produceOver$2$HomePresenter(Disposable disposable) throws Exception {
        ((HomeContract.View) this.mRootView).showLoading();
    }

    public /* synthetic */ void lambda$produceOver$3$HomePresenter() throws Exception {
        ((HomeContract.View) this.mRootView).hideLoading();
    }

    public /* synthetic */ void lambda$unionPayReturnAmt$6$HomePresenter(Disposable disposable) throws Exception {
        ((HomeContract.View) this.mRootView).showLoading();
    }

    public /* synthetic */ void lambda$unionPayReturnAmt$7$HomePresenter() throws Exception {
        ((HomeContract.View) this.mRootView).hideLoading();
    }

    public /* synthetic */ void lambda$unionPayReturnAmt$8$HomePresenter(Disposable disposable) throws Exception {
        ((HomeContract.View) this.mRootView).showLoading();
    }

    public /* synthetic */ void lambda$unionPayReturnAmt$9$HomePresenter() throws Exception {
        ((HomeContract.View) this.mRootView).hideLoading();
    }

    public void login(String str) {
        ((HomeContract.Model) this.mModel).login(str).subscribeOn(Schedulers.io()).doOnSubscribe(new Consumer() { // from class: com.yj.coffeemachines.mvp.presenter.-$$Lambda$HomePresenter$PcjKD2_xFfDqy9sIkh8CWumKzJM
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                HomePresenter.this.lambda$login$0$HomePresenter((Disposable) obj);
            }
        }).subscribeOn(AndroidSchedulers.mainThread()).doFinally(new Action() { // from class: com.yj.coffeemachines.mvp.presenter.-$$Lambda$HomePresenter$YP5QBv8zvvmwnHt_h6BOYFvWWBo
            @Override // io.reactivex.functions.Action
            public final void run() {
                HomePresenter.this.lambda$login$1$HomePresenter();
            }
        }).compose(RxLifecycleUtils.bindToLifecycle(this.mRootView)).subscribe(new ErrorHandleSubscriber<LoginBack>(this.mErrorHandler) { // from class: com.yj.coffeemachines.mvp.presenter.HomePresenter.1
            @Override // io.reactivex.Observer
            public void onNext(LoginBack loginBack) {
                if (loginBack.getCode() != 200) {
                    ArmsUtils.snackbarText(loginBack.getMsg());
                } else if (loginBack.getData()) {
                    ((HomeContract.View) HomePresenter.this.mRootView).loginSuccess(loginBack);
                } else {
                    ArmsUtils.snackbarText(((HomeContract.View) HomePresenter.this.mRootView).getActivity().getString(R.string.pwdErro));
                }
            }
        });
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
            ((HomeContract.Model) this.mModel).cancelOrder().subscribeOn(Schedulers.io()).retryWhen(new RetryWithDelay(2, 1)).subscribeOn(AndroidSchedulers.mainThread()).compose(RxLifecycleUtils.bindToLifecycle(this.mRootView)).subscribe(new ErrorHandleSubscriber<Boolean>(this.mErrorHandler) { // from class: com.yj.coffeemachines.mvp.presenter.HomePresenter.16
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
            ((HomeContract.Model) this.mModel).orderCancel(str).subscribeOn(Schedulers.io()).retryWhen(new RetryWithDelay(2, 1)).subscribeOn(AndroidSchedulers.mainThread()).compose(RxLifecycleUtils.bindToLifecycle(this.mRootView)).subscribe(new ErrorHandleSubscriber<OrderCancelBack>(this.mErrorHandler) { // from class: com.yj.coffeemachines.mvp.presenter.HomePresenter.17
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

    public void outStockOver() {
        ((HomeContract.Model) this.mModel).outStockOver().subscribeOn(Schedulers.io()).retryWhen(new RetryWithDelay(2, 1)).doOnSubscribe(new Consumer() { // from class: com.yj.coffeemachines.mvp.presenter.-$$Lambda$HomePresenter$vZVoFT9YJoNwi-qOJk3h0p4O-pM
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                HomePresenter.this.lambda$outStockOver$4$HomePresenter((Disposable) obj);
            }
        }).subscribeOn(AndroidSchedulers.mainThread()).doFinally(new Action() { // from class: com.yj.coffeemachines.mvp.presenter.-$$Lambda$HomePresenter$62nDmfZT_wm2-Hfj80OV1rzUoPo
            @Override // io.reactivex.functions.Action
            public final void run() {
                HomePresenter.this.lambda$outStockOver$5$HomePresenter();
            }
        }).compose(RxLifecycleUtils.bindToLifecycle(this.mRootView)).subscribe(new ErrorHandleSubscriber<OutStockOverBack>(this.mErrorHandler) { // from class: com.yj.coffeemachines.mvp.presenter.HomePresenter.3
            @Override // io.reactivex.Observer
            public void onNext(OutStockOverBack outStockOverBack) {
                if (outStockOverBack.getCode() == 200) {
                    return;
                }
                ArmsUtils.snackbarText(outStockOverBack.getMsg());
            }
        });
    }

    public void produceFail() {
        ((HomeContract.Model) this.mModel).produceFail().subscribeOn(Schedulers.io()).retryWhen(new RetryWithDelay(2, 1)).doOnSubscribe(new Consumer() { // from class: com.yj.coffeemachines.mvp.presenter.-$$Lambda$HomePresenter$-B7p7pYid-TnXzr_ECtuZO-fNg8
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                HomePresenter.this.lambda$produceFail$14$HomePresenter((Disposable) obj);
            }
        }).subscribeOn(AndroidSchedulers.mainThread()).doFinally(new Action() { // from class: com.yj.coffeemachines.mvp.presenter.-$$Lambda$HomePresenter$HMCbjj8tVEo0pfpnjZ4uCDgOJ08
            @Override // io.reactivex.functions.Action
            public final void run() {
                HomePresenter.this.lambda$produceFail$15$HomePresenter();
            }
        }).compose(RxLifecycleUtils.bindToLifecycle(this.mRootView)).subscribe(new ErrorHandleSubscriber<ProduceFailBack>(this.mErrorHandler) { // from class: com.yj.coffeemachines.mvp.presenter.HomePresenter.9
            @Override // io.reactivex.Observer
            public void onNext(ProduceFailBack produceFailBack) {
                if (produceFailBack.getCode() == 200) {
                    return;
                }
                ArmsUtils.snackbarText(produceFailBack.getMsg());
            }
        });
    }

    public void produceOver() {
        ((HomeContract.Model) this.mModel).produceOver().subscribeOn(Schedulers.io()).retryWhen(new RetryWithDelay(2, 1)).doOnSubscribe(new Consumer() { // from class: com.yj.coffeemachines.mvp.presenter.-$$Lambda$HomePresenter$TzQ8BfYB4IX-ePg6vtFemAZZ-c0
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                HomePresenter.this.lambda$produceOver$2$HomePresenter((Disposable) obj);
            }
        }).subscribeOn(AndroidSchedulers.mainThread()).doFinally(new Action() { // from class: com.yj.coffeemachines.mvp.presenter.-$$Lambda$HomePresenter$FdhjPJcSoSIzmy8kndQFl66pNuU
            @Override // io.reactivex.functions.Action
            public final void run() {
                HomePresenter.this.lambda$produceOver$3$HomePresenter();
            }
        }).compose(RxLifecycleUtils.bindToLifecycle(this.mRootView)).subscribe(new ErrorHandleSubscriber<ProduceOverBack>(this.mErrorHandler) { // from class: com.yj.coffeemachines.mvp.presenter.HomePresenter.2
            @Override // io.reactivex.Observer
            public void onNext(ProduceOverBack produceOverBack) {
                if (produceOverBack.getCode() == 200) {
                    return;
                }
                ArmsUtils.snackbarText(produceOverBack.getMsg());
            }
        });
    }

    public void saveOrderFailLog(final String str) {
        if (((HomeContract.View) this.mRootView).getState().contains("轨道异常")) {
            str = StringUtils.getString(R.string.foreign_matter);
        } else if (((HomeContract.View) this.mRootView).getState().contains("落杯器无杯")) {
            str = StringUtils.getString(R.string.E02);
        }
        ((HomeContract.Model) this.mModel).saveOrderFailLog(str).subscribeOn(Schedulers.io()).retryWhen(new RetryWithDelay(2, 1)).subscribeOn(AndroidSchedulers.mainThread()).compose(RxLifecycleUtils.bindToLifecycle(this.mRootView)).subscribe(new ErrorHandleSubscriber<Object>(this.mErrorHandler) { // from class: com.yj.coffeemachines.mvp.presenter.HomePresenter.14
            @Override // io.reactivex.Observer
            public void onNext(Object obj) {
                Tools.upLocalLog("制作失败异常上报：" + str);
            }
        });
    }

    public void t50PayRefund() {
        ((HomeContract.Model) this.mModel).t50PayRefund().subscribeOn(Schedulers.io()).retryWhen(new RetryWithDelay(2, 1)).subscribeOn(AndroidSchedulers.mainThread()).compose(RxLifecycleUtils.bindToLifecycle(this.mRootView)).subscribe(new ErrorHandleSubscriber<Object>(this.mErrorHandler) { // from class: com.yj.coffeemachines.mvp.presenter.HomePresenter.13
            @Override // io.reactivex.Observer
            public void onNext(Object obj) {
                Tools.upLocalLog("支付退款：" + obj.toString());
            }
        });
    }

    public void unionPayReturnAmt() {
        ((HomeContract.Model) this.mModel).unionPayReturnAmt().subscribeOn(Schedulers.io()).retryWhen(new RetryWithDelay(2, 1)).doOnSubscribe(new Consumer() { // from class: com.yj.coffeemachines.mvp.presenter.-$$Lambda$HomePresenter$iWmZtM1vx9s8nH6LbJJb82emmv0
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                HomePresenter.this.lambda$unionPayReturnAmt$6$HomePresenter((Disposable) obj);
            }
        }).subscribeOn(AndroidSchedulers.mainThread()).doFinally(new Action() { // from class: com.yj.coffeemachines.mvp.presenter.-$$Lambda$HomePresenter$cwhApc__dFiGrDiNO2dbfI8XpuM
            @Override // io.reactivex.functions.Action
            public final void run() {
                HomePresenter.this.lambda$unionPayReturnAmt$7$HomePresenter();
            }
        }).compose(RxLifecycleUtils.bindToLifecycle(this.mRootView)).subscribe(new ErrorHandleSubscriber<UnionPayReturnAmtBack>(this.mErrorHandler) { // from class: com.yj.coffeemachines.mvp.presenter.HomePresenter.4
            @Override // io.reactivex.Observer
            public void onNext(UnionPayReturnAmtBack unionPayReturnAmtBack) {
                if (unionPayReturnAmtBack.getCode() == 200) {
                    return;
                }
                Constants.ReturnAmtAdd += Constants.trade_no + "_";
            }
        });
    }

    public void unionPayReturnAmt(final String str) {
        ((HomeContract.Model) this.mModel).unionPayReturnAmt(str).subscribeOn(Schedulers.io()).retryWhen(new RetryWithDelay(2, 1)).doOnSubscribe(new Consumer() { // from class: com.yj.coffeemachines.mvp.presenter.-$$Lambda$HomePresenter$tcDk7ZobNBF2EgP603He8PW9VZ8
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                HomePresenter.this.lambda$unionPayReturnAmt$8$HomePresenter((Disposable) obj);
            }
        }).subscribeOn(AndroidSchedulers.mainThread()).doFinally(new Action() { // from class: com.yj.coffeemachines.mvp.presenter.-$$Lambda$HomePresenter$yro60Mw0frwoCAKh9ShUTINWY_o
            @Override // io.reactivex.functions.Action
            public final void run() {
                HomePresenter.this.lambda$unionPayReturnAmt$9$HomePresenter();
            }
        }).compose(RxLifecycleUtils.bindToLifecycle(this.mRootView)).subscribe(new ErrorHandleSubscriber<UnionPayReturnAmtBack>(this.mErrorHandler) { // from class: com.yj.coffeemachines.mvp.presenter.HomePresenter.5
            @Override // io.reactivex.Observer
            public void onNext(UnionPayReturnAmtBack unionPayReturnAmtBack) {
                if (unionPayReturnAmtBack.getCode() != 200) {
                    Tools.upLocalLog("退款失败：trade_no" + str + "。back:" + unionPayReturnAmtBack.toString());
                    return;
                }
                Constants.ReturnAmtRemove += str + "_";
                Tools.upLocalLogTest("已经退款：trade_no" + str + "。back:" + unionPayReturnAmtBack.toString());
            }
        });
    }

    public void uploadState() {
        ((HomeContract.Model) this.mModel).uploadState(((HomeContract.View) this.mRootView).getState()).retryWhen(new RetryWithDelay(2, 1)).compose(RxLifecycleUtils.bindToLifecycle(this.mRootView)).subscribe(new ErrorHandleSubscriber<String>(this.mErrorHandler) { // from class: com.yj.coffeemachines.mvp.presenter.HomePresenter.15
            @Override // me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber, io.reactivex.Observer
            public void onError(Throwable th) {
                super.onError(th);
                Tools.upLocalLog("设备状态上报失败:" + th.getMessage());
                HomePresenter.this.uploadState();
            }

            @Override // io.reactivex.Observer
            public void onNext(String str) {
                Tools.upLocalLog("设备状态上报成功");
            }
        });
    }

    public void weChartPayReturnAmt(String str) {
        ((HomeContract.Model) this.mModel).refundOrder(str).subscribeOn(Schedulers.io()).retryWhen(new RetryWithDelay(2, 1)).subscribeOn(AndroidSchedulers.mainThread()).compose(RxLifecycleUtils.bindToLifecycle(this.mRootView)).subscribe(new ErrorHandleSubscriber<OrderRefundBean>(this.mErrorHandler) { // from class: com.yj.coffeemachines.mvp.presenter.HomePresenter.7
            @Override // io.reactivex.Observer
            public void onNext(OrderRefundBean orderRefundBean) {
                if (orderRefundBean.isRefundSuccess()) {
                    Tools.upLocalLog("退款成功");
                } else {
                    Tools.upLocalLog("退款失败");
                }
            }
        });
    }
}
