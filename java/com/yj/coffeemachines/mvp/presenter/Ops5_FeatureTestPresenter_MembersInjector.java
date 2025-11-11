package com.yj.coffeemachines.mvp.presenter;

import android.app.Application;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.integration.AppManager;
import dagger.MembersInjector;
import dagger.internal.InjectedFieldSignature;
import javax.inject.Provider;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;

/* loaded from: classes.dex */
public final class Ops5_FeatureTestPresenter_MembersInjector implements MembersInjector<Ops5_FeatureTestPresenter> {
    private final Provider<AppManager> mAppManagerProvider;
    private final Provider<Application> mApplicationProvider;
    private final Provider<RxErrorHandler> mErrorHandlerProvider;
    private final Provider<ImageLoader> mImageLoaderProvider;

    public Ops5_FeatureTestPresenter_MembersInjector(Provider<RxErrorHandler> provider, Provider<Application> provider2, Provider<ImageLoader> provider3, Provider<AppManager> provider4) {
        this.mErrorHandlerProvider = provider;
        this.mApplicationProvider = provider2;
        this.mImageLoaderProvider = provider3;
        this.mAppManagerProvider = provider4;
    }

    public static MembersInjector<Ops5_FeatureTestPresenter> create(Provider<RxErrorHandler> provider, Provider<Application> provider2, Provider<ImageLoader> provider3, Provider<AppManager> provider4) {
        return new Ops5_FeatureTestPresenter_MembersInjector(provider, provider2, provider3, provider4);
    }

    @InjectedFieldSignature("com.yj.coffeemachines.mvp.presenter.Ops5_FeatureTestPresenter.mAppManager")
    public static void injectMAppManager(Ops5_FeatureTestPresenter ops5_FeatureTestPresenter, AppManager appManager) {
        ops5_FeatureTestPresenter.mAppManager = appManager;
    }

    @InjectedFieldSignature("com.yj.coffeemachines.mvp.presenter.Ops5_FeatureTestPresenter.mApplication")
    public static void injectMApplication(Ops5_FeatureTestPresenter ops5_FeatureTestPresenter, Application application) {
        ops5_FeatureTestPresenter.mApplication = application;
    }

    @InjectedFieldSignature("com.yj.coffeemachines.mvp.presenter.Ops5_FeatureTestPresenter.mErrorHandler")
    public static void injectMErrorHandler(Ops5_FeatureTestPresenter ops5_FeatureTestPresenter, RxErrorHandler rxErrorHandler) {
        ops5_FeatureTestPresenter.mErrorHandler = rxErrorHandler;
    }

    @InjectedFieldSignature("com.yj.coffeemachines.mvp.presenter.Ops5_FeatureTestPresenter.mImageLoader")
    public static void injectMImageLoader(Ops5_FeatureTestPresenter ops5_FeatureTestPresenter, ImageLoader imageLoader) {
        ops5_FeatureTestPresenter.mImageLoader = imageLoader;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(Ops5_FeatureTestPresenter ops5_FeatureTestPresenter) {
        injectMErrorHandler(ops5_FeatureTestPresenter, this.mErrorHandlerProvider.get());
        injectMApplication(ops5_FeatureTestPresenter, this.mApplicationProvider.get());
        injectMImageLoader(ops5_FeatureTestPresenter, this.mImageLoaderProvider.get());
        injectMAppManager(ops5_FeatureTestPresenter, this.mAppManagerProvider.get());
    }
}
