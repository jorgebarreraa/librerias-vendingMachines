package com.yj.coffeemachines.mvp.presenter;

import android.app.Application;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.integration.AppManager;
import dagger.MembersInjector;
import dagger.internal.InjectedFieldSignature;
import javax.inject.Provider;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;

/* loaded from: classes.dex */
public final class Factory1_DevConfigPresenter_MembersInjector implements MembersInjector<Factory1_DevConfigPresenter> {
    private final Provider<AppManager> mAppManagerProvider;
    private final Provider<Application> mApplicationProvider;
    private final Provider<RxErrorHandler> mErrorHandlerProvider;
    private final Provider<ImageLoader> mImageLoaderProvider;

    public Factory1_DevConfigPresenter_MembersInjector(Provider<RxErrorHandler> provider, Provider<Application> provider2, Provider<ImageLoader> provider3, Provider<AppManager> provider4) {
        this.mErrorHandlerProvider = provider;
        this.mApplicationProvider = provider2;
        this.mImageLoaderProvider = provider3;
        this.mAppManagerProvider = provider4;
    }

    public static MembersInjector<Factory1_DevConfigPresenter> create(Provider<RxErrorHandler> provider, Provider<Application> provider2, Provider<ImageLoader> provider3, Provider<AppManager> provider4) {
        return new Factory1_DevConfigPresenter_MembersInjector(provider, provider2, provider3, provider4);
    }

    @InjectedFieldSignature("com.yj.coffeemachines.mvp.presenter.Factory1_DevConfigPresenter.mAppManager")
    public static void injectMAppManager(Factory1_DevConfigPresenter factory1_DevConfigPresenter, AppManager appManager) {
        factory1_DevConfigPresenter.mAppManager = appManager;
    }

    @InjectedFieldSignature("com.yj.coffeemachines.mvp.presenter.Factory1_DevConfigPresenter.mApplication")
    public static void injectMApplication(Factory1_DevConfigPresenter factory1_DevConfigPresenter, Application application) {
        factory1_DevConfigPresenter.mApplication = application;
    }

    @InjectedFieldSignature("com.yj.coffeemachines.mvp.presenter.Factory1_DevConfigPresenter.mErrorHandler")
    public static void injectMErrorHandler(Factory1_DevConfigPresenter factory1_DevConfigPresenter, RxErrorHandler rxErrorHandler) {
        factory1_DevConfigPresenter.mErrorHandler = rxErrorHandler;
    }

    @InjectedFieldSignature("com.yj.coffeemachines.mvp.presenter.Factory1_DevConfigPresenter.mImageLoader")
    public static void injectMImageLoader(Factory1_DevConfigPresenter factory1_DevConfigPresenter, ImageLoader imageLoader) {
        factory1_DevConfigPresenter.mImageLoader = imageLoader;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(Factory1_DevConfigPresenter factory1_DevConfigPresenter) {
        injectMErrorHandler(factory1_DevConfigPresenter, this.mErrorHandlerProvider.get());
        injectMApplication(factory1_DevConfigPresenter, this.mApplicationProvider.get());
        injectMImageLoader(factory1_DevConfigPresenter, this.mImageLoaderProvider.get());
        injectMAppManager(factory1_DevConfigPresenter, this.mAppManagerProvider.get());
    }
}
