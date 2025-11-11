package com.yj.coffeemachines.mvp.presenter;

import android.app.Application;
import com.jess.arms.di.scope.FragmentScope;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.integration.AppManager;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.utils.RxLifecycleUtils;
import com.yj.coffeemachines.mvp.contract.Step5Contract;
import com.yj.coffeemachines.mvp.model.beans.OutStockOverBack;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import javax.inject.Inject;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;
import me.jessyan.rxerrorhandler.handler.RetryWithDelay;

@FragmentScope
/* loaded from: classes.dex */
public class Step5Presenter extends BasePresenter<Step5Contract.Model, Step5Contract.View> {

    @Inject
    AppManager mAppManager;

    @Inject
    Application mApplication;

    @Inject
    RxErrorHandler mErrorHandler;

    @Inject
    ImageLoader mImageLoader;

    @Inject
    public Step5Presenter(Step5Contract.Model model, Step5Contract.View view) {
        super(model, view);
    }

    public void checkMaterialIsEnoughAndSetsSoldOut() {
        ((Step5Contract.Model) this.mModel).checkMaterialIsEnoughAndSetsSoldOut().subscribeOn(Schedulers.io()).retryWhen(new RetryWithDelay(2, 1)).doOnSubscribe(new Consumer() { // from class: com.yj.coffeemachines.mvp.presenter.-$$Lambda$Step5Presenter$X4_vF9PtBZBFl2Bo8ilWYwKMET0
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                Step5Presenter.this.lambda$checkMaterialIsEnoughAndSetsSoldOut$2$Step5Presenter((Disposable) obj);
            }
        }).subscribeOn(AndroidSchedulers.mainThread()).doFinally(new Action() { // from class: com.yj.coffeemachines.mvp.presenter.-$$Lambda$Step5Presenter$9CYNqskRsKqNLUQHZjOoYUjO7dY
            @Override // io.reactivex.functions.Action
            public final void run() {
                Step5Presenter.this.lambda$checkMaterialIsEnoughAndSetsSoldOut$3$Step5Presenter();
            }
        }).compose(RxLifecycleUtils.bindToLifecycle(this.mRootView)).subscribe(new ErrorHandleSubscriber<String>(this.mErrorHandler) { // from class: com.yj.coffeemachines.mvp.presenter.Step5Presenter.2
            @Override // io.reactivex.Observer
            public void onNext(String str) {
            }
        });
    }

    public /* synthetic */ void lambda$checkMaterialIsEnoughAndSetsSoldOut$2$Step5Presenter(Disposable disposable) throws Exception {
        ((Step5Contract.View) this.mRootView).showLoading();
    }

    public /* synthetic */ void lambda$checkMaterialIsEnoughAndSetsSoldOut$3$Step5Presenter() throws Exception {
        ((Step5Contract.View) this.mRootView).hideLoading();
    }

    public /* synthetic */ void lambda$outStockOver$0$Step5Presenter(Disposable disposable) throws Exception {
        ((Step5Contract.View) this.mRootView).showLoading();
    }

    public /* synthetic */ void lambda$outStockOver$1$Step5Presenter() throws Exception {
        ((Step5Contract.View) this.mRootView).hideLoading();
    }

    @Override // com.jess.arms.mvp.BasePresenter, com.jess.arms.mvp.IPresenter
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
        this.mAppManager = null;
        this.mImageLoader = null;
        this.mApplication = null;
    }

    public void outStockOver() {
        ((Step5Contract.Model) this.mModel).outStockOver().subscribeOn(Schedulers.io()).retryWhen(new RetryWithDelay(2, 1)).doOnSubscribe(new Consumer() { // from class: com.yj.coffeemachines.mvp.presenter.-$$Lambda$Step5Presenter$3WPC-Fu4ol1gmcBPn6w5PStT2sY
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                Step5Presenter.this.lambda$outStockOver$0$Step5Presenter((Disposable) obj);
            }
        }).subscribeOn(AndroidSchedulers.mainThread()).doFinally(new Action() { // from class: com.yj.coffeemachines.mvp.presenter.-$$Lambda$Step5Presenter$ZQ5rhSoLrjUWLO6iVIfy8ajPPlI
            @Override // io.reactivex.functions.Action
            public final void run() {
                Step5Presenter.this.lambda$outStockOver$1$Step5Presenter();
            }
        }).compose(RxLifecycleUtils.bindToLifecycle(this.mRootView)).subscribe(new ErrorHandleSubscriber<OutStockOverBack>(this.mErrorHandler) { // from class: com.yj.coffeemachines.mvp.presenter.Step5Presenter.1
            @Override // io.reactivex.Observer
            public void onNext(OutStockOverBack outStockOverBack) {
                if (outStockOverBack.getCode() == 200) {
                    return;
                }
                ArmsUtils.snackbarText(outStockOverBack.getMsg());
            }
        });
    }
}
