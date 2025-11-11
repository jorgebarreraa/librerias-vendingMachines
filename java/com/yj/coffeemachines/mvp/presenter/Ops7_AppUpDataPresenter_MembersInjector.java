package com.yj.coffeemachines.mvp.presenter;

import android.app.Application;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.integration.AppManager;
import dagger.MembersInjector;
import dagger.internal.InjectedFieldSignature;
import javax.inject.Provider;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;

/* loaded from: classes.dex */
public final class Ops7_AppUpDataPresenter_MembersInjector implements MembersInjector<Ops7_AppUpDataPresenter> {
    private final Provider<AppManager> mAppManagerProvider;
    private final Provider<Application> mApplicationProvider;
    private final Provider<RxErrorHandler> mErrorHandlerProvider;
    private final Provider<ImageLoader> mImageLoaderProvider;

    public Ops7_AppUpDataPresenter_MembersInjector(Provider<RxErrorHandler> provider, Provider<Application> provider2, Provider<ImageLoader> provider3, Provider<AppManager> provider4) {
        this.mErrorHandlerProvider = provider;
        this.mApplicationProvider = provider2;
        this.mImageLoaderProvider = provider3;
        this.mAppManagerProvider = provider4;
    }

    public static MembersInjector<Ops7_AppUpDataPresenter> create(Provider<RxErrorHandler> provider, Provider<Application> provider2, Provider<ImageLoader> provider3, Provider<AppManager> provider4) {
        return new Ops7_AppUpDataPresenter_MembersInjector(provider, provider2, provider3, provider4);
    }

    @InjectedFieldSignature("com.yj.coffeemachines.mvp.presenter.Ops7_AppUpDataPresenter.mAppManager")
    public static void injectMAppManager(Ops7_AppUpDataPresenter ops7_AppUpDataPresenter, AppManager appManager) {
        ops7_AppUpDataPresenter.mAppManager = appManager;
    }

    @InjectedFieldSignature("com.yj.coffeemachines.mvp.presenter.Ops7_AppUpDataPresenter.mApplication")
    public static void injectMApplication(Ops7_AppUpDataPresenter ops7_AppUpDataPresenter, Application application) {
        ops7_AppUpDataPresenter.mApplication = application;
    }

    @InjectedFieldSignature("com.yj.coffeemachines.mvp.presenter.Ops7_AppUpDataPresenter.mErrorHandler")
    public static void injectMErrorHandler(Ops7_AppUpDataPresenter ops7_AppUpDataPresenter, RxErrorHandler rxErrorHandler) {
        ops7_AppUpDataPresenter.mErrorHandler = rxErrorHandler;
    }

    @InjectedFieldSignature("com.yj.coffeemachines.mvp.presenter.Ops7_AppUpDataPresenter.mImageLoader")
    public static void injectMImageLoader(Ops7_AppUpDataPresenter ops7_AppUpDataPresenter, ImageLoader imageLoader) {
        ops7_AppUpDataPresenter.mImageLoader = imageLoader;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(Ops7_AppUpDataPresenter ops7_AppUpDataPresenter) {
        injectMErrorHandler(ops7_AppUpDataPresenter, this.mErrorHandlerProvider.get());
        injectMApplication(ops7_AppUpDataPresenter, this.mApplicationProvider.get());
        injectMImageLoader(ops7_AppUpDataPresenter, this.mImageLoaderProvider.get());
        injectMAppManager(ops7_AppUpDataPresenter, this.mAppManagerProvider.get());
    }
}
