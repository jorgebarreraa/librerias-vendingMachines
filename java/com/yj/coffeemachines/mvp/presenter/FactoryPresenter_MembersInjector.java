package com.yj.coffeemachines.mvp.presenter;

import android.app.Application;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.integration.AppManager;
import dagger.MembersInjector;
import dagger.internal.InjectedFieldSignature;
import javax.inject.Provider;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;

/* loaded from: classes.dex */
public final class FactoryPresenter_MembersInjector implements MembersInjector<FactoryPresenter> {
    private final Provider<AppManager> mAppManagerProvider;
    private final Provider<Application> mApplicationProvider;
    private final Provider<RxErrorHandler> mErrorHandlerProvider;
    private final Provider<ImageLoader> mImageLoaderProvider;

    public FactoryPresenter_MembersInjector(Provider<RxErrorHandler> provider, Provider<Application> provider2, Provider<ImageLoader> provider3, Provider<AppManager> provider4) {
        this.mErrorHandlerProvider = provider;
        this.mApplicationProvider = provider2;
        this.mImageLoaderProvider = provider3;
        this.mAppManagerProvider = provider4;
    }

    public static MembersInjector<FactoryPresenter> create(Provider<RxErrorHandler> provider, Provider<Application> provider2, Provider<ImageLoader> provider3, Provider<AppManager> provider4) {
        return new FactoryPresenter_MembersInjector(provider, provider2, provider3, provider4);
    }

    @InjectedFieldSignature("com.yj.coffeemachines.mvp.presenter.FactoryPresenter.mAppManager")
    public static void injectMAppManager(FactoryPresenter factoryPresenter, AppManager appManager) {
        factoryPresenter.mAppManager = appManager;
    }

    @InjectedFieldSignature("com.yj.coffeemachines.mvp.presenter.FactoryPresenter.mApplication")
    public static void injectMApplication(FactoryPresenter factoryPresenter, Application application) {
        factoryPresenter.mApplication = application;
    }

    @InjectedFieldSignature("com.yj.coffeemachines.mvp.presenter.FactoryPresenter.mErrorHandler")
    public static void injectMErrorHandler(FactoryPresenter factoryPresenter, RxErrorHandler rxErrorHandler) {
        factoryPresenter.mErrorHandler = rxErrorHandler;
    }

    @InjectedFieldSignature("com.yj.coffeemachines.mvp.presenter.FactoryPresenter.mImageLoader")
    public static void injectMImageLoader(FactoryPresenter factoryPresenter, ImageLoader imageLoader) {
        factoryPresenter.mImageLoader = imageLoader;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(FactoryPresenter factoryPresenter) {
        injectMErrorHandler(factoryPresenter, this.mErrorHandlerProvider.get());
        injectMApplication(factoryPresenter, this.mApplicationProvider.get());
        injectMImageLoader(factoryPresenter, this.mImageLoaderProvider.get());
        injectMAppManager(factoryPresenter, this.mAppManagerProvider.get());
    }
}
