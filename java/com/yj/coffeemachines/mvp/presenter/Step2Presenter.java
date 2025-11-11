package com.yj.coffeemachines.mvp.presenter;

import android.app.Application;
import com.jess.arms.di.scope.FragmentScope;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.integration.AppManager;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.utils.RxLifecycleUtils;
import com.yj.coffeemachines.R;
import com.yj.coffeemachines.mvp.contract.Step2Contract;
import com.yj.coffeemachines.mvp.model.beans.ExchangeGenOrderBack;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import javax.inject.Inject;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

@FragmentScope
/* loaded from: classes.dex */
public class Step2Presenter extends BasePresenter<Step2Contract.Model, Step2Contract.View> {

    @Inject
    AppManager mAppManager;

    @Inject
    Application mApplication;

    @Inject
    RxErrorHandler mErrorHandler;

    @Inject
    ImageLoader mImageLoader;

    @Inject
    public Step2Presenter(Step2Contract.Model model, Step2Contract.View view) {
        super(model, view);
    }

    public /* synthetic */ void lambda$putExchangeOrder$0$Step2Presenter(Disposable disposable) throws Exception {
        ((Step2Contract.View) this.mRootView).showLoading(((Step2Contract.View) this.mRootView).getActivity().getString(R.string.putordering));
    }

    public /* synthetic */ void lambda$putExchangeOrder$1$Step2Presenter() throws Exception {
        ((Step2Contract.View) this.mRootView).hideLoading();
    }

    public void loadProductDetailMessage() {
    }

    @Override // com.jess.arms.mvp.BasePresenter, com.jess.arms.mvp.IPresenter
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
        this.mAppManager = null;
        this.mImageLoader = null;
        this.mApplication = null;
    }

    public void putExchangeOrder() {
        ((Step2Contract.Model) this.mModel).putExchangeOrder().subscribeOn(Schedulers.io()).doOnSubscribe(new Consumer() { // from class: com.yj.coffeemachines.mvp.presenter.-$$Lambda$Step2Presenter$_0UZ1E9ncsApC5Gl8CEtgNENhZw
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                Step2Presenter.this.lambda$putExchangeOrder$0$Step2Presenter((Disposable) obj);
            }
        }).subscribeOn(AndroidSchedulers.mainThread()).doFinally(new Action() { // from class: com.yj.coffeemachines.mvp.presenter.-$$Lambda$Step2Presenter$MbE53MyqtZnJpHEZsCi5ne1jaPw
            @Override // io.reactivex.functions.Action
            public final void run() {
                Step2Presenter.this.lambda$putExchangeOrder$1$Step2Presenter();
            }
        }).compose(RxLifecycleUtils.bindToLifecycle(this.mRootView)).subscribe(new ErrorHandleSubscriber<ExchangeGenOrderBack>(this.mErrorHandler) { // from class: com.yj.coffeemachines.mvp.presenter.Step2Presenter.1
            @Override // io.reactivex.Observer
            public void onNext(ExchangeGenOrderBack exchangeGenOrderBack) {
                if (exchangeGenOrderBack.getCode() == 200) {
                    exchangeGenOrderBack.getData();
                    ((Step2Contract.View) Step2Presenter.this.mRootView).putExchangeOrderSuccess(exchangeGenOrderBack);
                } else {
                    ArmsUtils.snackbarText(exchangeGenOrderBack.getMsg());
                    ((Step2Contract.View) Step2Presenter.this.mRootView).putExchangeOrderfail(exchangeGenOrderBack);
                }
            }
        });
    }
}
