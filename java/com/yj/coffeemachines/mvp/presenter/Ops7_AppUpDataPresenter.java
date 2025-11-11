package com.yj.coffeemachines.mvp.presenter;

import android.app.Application;
import com.blankj.utilcode.util.AppUtils;
import com.jess.arms.di.scope.FragmentScope;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.integration.AppManager;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.utils.RxLifecycleUtils;
import com.yj.coffeemachines.Constants;
import com.yj.coffeemachines.R;
import com.yj.coffeemachines.app.utils.FileUtils;
import com.yj.coffeemachines.mvp.contract.Ops7_AppUpDataContract;
import com.yj.coffeemachines.mvp.model.beans.AppUploadBean;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.io.File;
import javax.inject.Inject;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

@FragmentScope
/* loaded from: classes.dex */
public class Ops7_AppUpDataPresenter extends BasePresenter<Ops7_AppUpDataContract.Model, Ops7_AppUpDataContract.View> {

    @Inject
    AppManager mAppManager;

    @Inject
    Application mApplication;

    @Inject
    RxErrorHandler mErrorHandler;

    @Inject
    ImageLoader mImageLoader;

    @Inject
    public Ops7_AppUpDataPresenter(Ops7_AppUpDataContract.Model model, Ops7_AppUpDataContract.View view) {
        super(model, view);
    }

    public void downloadApk(String str) {
        ((Ops7_AppUpDataContract.Model) this.mModel).downLoadApp(str).compose(RxLifecycleUtils.bindToLifecycle(this.mRootView)).subscribe(new ErrorHandleSubscriber<File>(this.mErrorHandler) { // from class: com.yj.coffeemachines.mvp.presenter.Ops7_AppUpDataPresenter.2
            @Override // me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber, io.reactivex.Observer
            public void onError(Throwable th) {
                super.onError(th);
                ArmsUtils.snackbarText(th.getMessage());
            }

            @Override // io.reactivex.Observer
            public void onNext(File file) {
                if (!file.exists()) {
                    ArmsUtils.snackbarText("下载失败");
                } else if (Constants.Model == 3) {
                    FileUtils.broadcastUpdateApk(file);
                } else {
                    AppUtils.installApp(file);
                }
            }
        });
    }

    public /* synthetic */ void lambda$uploadApp$0$Ops7_AppUpDataPresenter(Disposable disposable) throws Exception {
        ((Ops7_AppUpDataContract.View) this.mRootView).showLoading();
    }

    public /* synthetic */ void lambda$uploadApp$1$Ops7_AppUpDataPresenter() throws Exception {
        ((Ops7_AppUpDataContract.View) this.mRootView).hideLoading();
    }

    @Override // com.jess.arms.mvp.BasePresenter, com.jess.arms.mvp.IPresenter
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
        this.mAppManager = null;
        this.mImageLoader = null;
        this.mApplication = null;
    }

    public void uploadApp() {
        ((Ops7_AppUpDataContract.Model) this.mModel).uploadApp().subscribeOn(Schedulers.io()).doOnSubscribe(new Consumer() { // from class: com.yj.coffeemachines.mvp.presenter.-$$Lambda$Ops7_AppUpDataPresenter$8zyjyAUyaITlDoVdvqYgA45U-G8
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                Ops7_AppUpDataPresenter.this.lambda$uploadApp$0$Ops7_AppUpDataPresenter((Disposable) obj);
            }
        }).subscribeOn(AndroidSchedulers.mainThread()).doFinally(new Action() { // from class: com.yj.coffeemachines.mvp.presenter.-$$Lambda$Ops7_AppUpDataPresenter$g829MlnSdrfDJ_lYckRiFh7d_6o
            @Override // io.reactivex.functions.Action
            public final void run() {
                Ops7_AppUpDataPresenter.this.lambda$uploadApp$1$Ops7_AppUpDataPresenter();
            }
        }).compose(RxLifecycleUtils.bindToLifecycle(this.mRootView)).subscribe(new ErrorHandleSubscriber<AppUploadBean>(this.mErrorHandler) { // from class: com.yj.coffeemachines.mvp.presenter.Ops7_AppUpDataPresenter.1
            @Override // me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber, io.reactivex.Observer
            public void onError(Throwable th) {
                super.onError(th);
                ArmsUtils.snackbarText(Ops7_AppUpDataPresenter.this.mApplication.getString(R.string.requsterro));
            }

            @Override // io.reactivex.Observer
            public void onNext(AppUploadBean appUploadBean) {
                if (appUploadBean == null) {
                    ArmsUtils.snackbarText(Ops7_AppUpDataPresenter.this.mApplication.getString(R.string.requsterro));
                } else if (appUploadBean.getAppVersionNum() > 34520) {
                    ((Ops7_AppUpDataContract.View) Ops7_AppUpDataPresenter.this.mRootView).makeDialogNewVersion(appUploadBean);
                } else {
                    ArmsUtils.snackbarText(Ops7_AppUpDataPresenter.this.mApplication.getString(R.string.thenewst));
                }
            }
        });
    }
}
