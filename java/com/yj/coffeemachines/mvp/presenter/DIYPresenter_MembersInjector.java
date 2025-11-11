package com.yj.coffeemachines.mvp.presenter;

import android.app.Application;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.integration.AppManager;
import dagger.MembersInjector;
import dagger.internal.InjectedFieldSignature;
import javax.inject.Provider;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;

/* loaded from: classes.dex */
public final class DIYPresenter_MembersInjector implements MembersInjector<DIYPresenter> {
    private final Provider<AppManager> mAppManagerProvider;
    private final Provider<Application> mApplicationProvider;
    private final Provider<RxErrorHandler> mErrorHandlerProvider;
    private final Provider<ImageLoader> mImageLoaderProvider;

    public DIYPresenter_MembersInjector(Provider<RxErrorHandler> provider, Provider<Application> provider2, Provider<ImageLoader> provider3, Provider<AppManager> provider4) {
        this.mErrorHandlerProvider = provider;
        this.mApplicationProvider = provider2;
        this.mImageLoaderProvider = provider3;
        this.mAppManagerProvider = provider4;
    }

    public static MembersInjector<DIYPresenter> create(Provider<RxErrorHandler> provider, Provider<Application> provider2, Provider<ImageLoader> provider3, Provider<AppManager> provider4) {
        return new DIYPresenter_MembersInjector(provider, provider2, provider3, provider4);
    }

    @InjectedFieldSignature("com.yj.coffeemachines.mvp.presenter.DIYPresenter.mAppManager")
    public static void injectMAppManager(DIYPresenter dIYPresenter, AppManager appManager) {
        dIYPresenter.mAppManager = appManager;
    }

    @InjectedFieldSignature("com.yj.coffeemachines.mvp.presenter.DIYPresenter.mApplication")
    public static void injectMApplication(DIYPresenter dIYPresenter, Application application) {
        dIYPresenter.mApplication = application;
    }

    @InjectedFieldSignature("com.yj.coffeemachines.mvp.presenter.DIYPresenter.mErrorHandler")
    public static void injectMErrorHandler(DIYPresenter dIYPresenter, RxErrorHandler rxErrorHandler) {
        dIYPresenter.mErrorHandler = rxErrorHandler;
    }

    @InjectedFieldSignature("com.yj.coffeemachines.mvp.presenter.DIYPresenter.mImageLoader")
    public static void injectMImageLoader(DIYPresenter dIYPresenter, ImageLoader imageLoader) {
        dIYPresenter.mImageLoader = imageLoader;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(DIYPresenter dIYPresenter) {
        injectMErrorHandler(dIYPresenter, this.mErrorHandlerProvider.get());
        injectMApplication(dIYPresenter, this.mApplicationProvider.get());
        injectMImageLoader(dIYPresenter, this.mImageLoaderProvider.get());
        injectMAppManager(dIYPresenter, this.mAppManagerProvider.get());
    }
}
