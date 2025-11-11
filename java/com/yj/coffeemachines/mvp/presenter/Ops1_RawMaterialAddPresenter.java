package com.yj.coffeemachines.mvp.presenter;

import android.app.Application;
import com.jess.arms.di.scope.FragmentScope;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.integration.AppManager;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.utils.RxLifecycleUtils;
import com.yj.coffeemachines.MyAppLocation;
import com.yj.coffeemachines.R;
import com.yj.coffeemachines.app.utils.DataUtils;
import com.yj.coffeemachines.eventbusbean.ProductRefish;
import com.yj.coffeemachines.helper.Tools;
import com.yj.coffeemachines.mvp.contract.Ops1_RawMaterialAddContract;
import com.yj.coffeemachines.mvp.model.beans.ReplenishListBack;
import com.yj.coffeemachines.mvp.model.beans.ReplenishSubmitBack;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.util.List;
import javax.inject.Inject;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;
import org.greenrobot.eventbus.EventBus;

@FragmentScope
/* loaded from: classes.dex */
public class Ops1_RawMaterialAddPresenter extends BasePresenter<Ops1_RawMaterialAddContract.Model, Ops1_RawMaterialAddContract.View> {

    @Inject
    AppManager mAppManager;

    @Inject
    Application mApplication;

    @Inject
    RxErrorHandler mErrorHandler;

    @Inject
    ImageLoader mImageLoader;

    @Inject
    public Ops1_RawMaterialAddPresenter(Ops1_RawMaterialAddContract.Model model, Ops1_RawMaterialAddContract.View view) {
        super(model, view);
    }

    public void checkMaterialIsEnoughAndSetsSoldOut() {
        Tools.upLocalLog("网络接口检查设备原料是否充足并设置上架商品售罄状态4checkMaterialIsEnoughAndSetsSoldOut");
        ((Ops1_RawMaterialAddContract.Model) this.mModel).checkMaterialIsEnoughAndSetsSoldOut().subscribeOn(Schedulers.io()).doOnSubscribe(new Consumer() { // from class: com.yj.coffeemachines.mvp.presenter.-$$Lambda$Ops1_RawMaterialAddPresenter$UyURVs-Oe1xwUI1GnOy-iTBVjgE
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                Ops1_RawMaterialAddPresenter.this.lambda$checkMaterialIsEnoughAndSetsSoldOut$4$Ops1_RawMaterialAddPresenter((Disposable) obj);
            }
        }).subscribeOn(AndroidSchedulers.mainThread()).doFinally(new Action() { // from class: com.yj.coffeemachines.mvp.presenter.-$$Lambda$Ops1_RawMaterialAddPresenter$WaNTppfWyC8Os31_diFSVZmp69k
            @Override // io.reactivex.functions.Action
            public final void run() {
                Ops1_RawMaterialAddPresenter.this.lambda$checkMaterialIsEnoughAndSetsSoldOut$5$Ops1_RawMaterialAddPresenter();
            }
        }).compose(RxLifecycleUtils.bindToLifecycle(this.mRootView)).subscribe(new ErrorHandleSubscriber<String>(this.mErrorHandler) { // from class: com.yj.coffeemachines.mvp.presenter.Ops1_RawMaterialAddPresenter.3
            @Override // io.reactivex.Observer
            public void onNext(String str) {
            }
        });
    }

    public void getData() {
        ((Ops1_RawMaterialAddContract.Model) this.mModel).getData().subscribeOn(Schedulers.io()).doOnSubscribe(new Consumer() { // from class: com.yj.coffeemachines.mvp.presenter.-$$Lambda$Ops1_RawMaterialAddPresenter$Q9oy5Q_LANg7ipEKSag4_MCd_a4
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                Ops1_RawMaterialAddPresenter.this.lambda$getData$0$Ops1_RawMaterialAddPresenter((Disposable) obj);
            }
        }).subscribeOn(AndroidSchedulers.mainThread()).doFinally(new Action() { // from class: com.yj.coffeemachines.mvp.presenter.-$$Lambda$Ops1_RawMaterialAddPresenter$kyPS07rY6fBi65mBA6xGmwP2Cjw
            @Override // io.reactivex.functions.Action
            public final void run() {
                Ops1_RawMaterialAddPresenter.this.lambda$getData$1$Ops1_RawMaterialAddPresenter();
            }
        }).compose(RxLifecycleUtils.bindToLifecycle(this.mRootView)).subscribe(new ErrorHandleSubscriber<ReplenishListBack>(this.mErrorHandler) { // from class: com.yj.coffeemachines.mvp.presenter.Ops1_RawMaterialAddPresenter.1
            @Override // io.reactivex.Observer
            public void onNext(ReplenishListBack replenishListBack) {
                if (replenishListBack.getCode() != 200) {
                    ArmsUtils.snackbarText(replenishListBack.getMsg());
                    return;
                }
                List<ReplenishListBack.DataBean> data = replenishListBack.getData();
                if (data != null) {
                    ((Ops1_RawMaterialAddContract.View) Ops1_RawMaterialAddPresenter.this.mRootView).reFrishData(data);
                }
            }
        });
    }

    public /* synthetic */ void lambda$checkMaterialIsEnoughAndSetsSoldOut$4$Ops1_RawMaterialAddPresenter(Disposable disposable) throws Exception {
        ((Ops1_RawMaterialAddContract.View) this.mRootView).showLoading();
    }

    public /* synthetic */ void lambda$checkMaterialIsEnoughAndSetsSoldOut$5$Ops1_RawMaterialAddPresenter() throws Exception {
        ((Ops1_RawMaterialAddContract.View) this.mRootView).hideLoading();
    }

    public /* synthetic */ void lambda$getData$0$Ops1_RawMaterialAddPresenter(Disposable disposable) throws Exception {
        ((Ops1_RawMaterialAddContract.View) this.mRootView).showLoading();
    }

    public /* synthetic */ void lambda$getData$1$Ops1_RawMaterialAddPresenter() throws Exception {
        ((Ops1_RawMaterialAddContract.View) this.mRootView).hideLoading();
    }

    public /* synthetic */ void lambda$updata$2$Ops1_RawMaterialAddPresenter(Disposable disposable) throws Exception {
        ((Ops1_RawMaterialAddContract.View) this.mRootView).showLoading();
    }

    public /* synthetic */ void lambda$updata$3$Ops1_RawMaterialAddPresenter() throws Exception {
        ((Ops1_RawMaterialAddContract.View) this.mRootView).hideLoading();
    }

    @Override // com.jess.arms.mvp.BasePresenter, com.jess.arms.mvp.IPresenter
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
        this.mAppManager = null;
        this.mImageLoader = null;
        this.mApplication = null;
    }

    public void updata(List<Object> list) {
        ((Ops1_RawMaterialAddContract.Model) this.mModel).updata(list).subscribeOn(Schedulers.io()).doOnSubscribe(new Consumer() { // from class: com.yj.coffeemachines.mvp.presenter.-$$Lambda$Ops1_RawMaterialAddPresenter$w49xmQjcSkSbNRmXcOFTLB6Sta4
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                Ops1_RawMaterialAddPresenter.this.lambda$updata$2$Ops1_RawMaterialAddPresenter((Disposable) obj);
            }
        }).subscribeOn(AndroidSchedulers.mainThread()).doFinally(new Action() { // from class: com.yj.coffeemachines.mvp.presenter.-$$Lambda$Ops1_RawMaterialAddPresenter$14dyAl3xIk_k4NMVmSUTWN84AKs
            @Override // io.reactivex.functions.Action
            public final void run() {
                Ops1_RawMaterialAddPresenter.this.lambda$updata$3$Ops1_RawMaterialAddPresenter();
            }
        }).compose(RxLifecycleUtils.bindToLifecycle(this.mRootView)).subscribe(new ErrorHandleSubscriber<ReplenishSubmitBack>(this.mErrorHandler) { // from class: com.yj.coffeemachines.mvp.presenter.Ops1_RawMaterialAddPresenter.2
            @Override // io.reactivex.Observer
            public void onNext(ReplenishSubmitBack replenishSubmitBack) {
                if (replenishSubmitBack.getSuccess()) {
                    Ops1_RawMaterialAddPresenter.this.checkMaterialIsEnoughAndSetsSoldOut();
                }
                ArmsUtils.snackbarText(MyAppLocation.myAppLocation.getString(R.string.operatesuccessfully));
                Ops1_RawMaterialAddPresenter.this.getData();
                EventBus.getDefault().post(new ProductRefish());
            }
        });
        MyAppLocation.myAppLocation.myMqttService.addOpsLog(this.mApplication.getString(R.string.addproduct), DataUtils.currentTime(), 1);
    }
}
