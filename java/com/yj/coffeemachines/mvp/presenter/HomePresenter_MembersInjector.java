package com.yj.coffeemachines.mvp.presenter;

import android.app.Application;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.integration.AppManager;
import dagger.MembersInjector;
import dagger.internal.InjectedFieldSignature;
import javax.inject.Provider;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;

/* loaded from: classes.dex */
public final class HomePresenter_MembersInjector implements MembersInjector<HomePresenter> {
    private final Provider<AppManager> mAppManagerProvider;
    private final Provider<Application> mApplicationProvider;
    private final Provider<RxErrorHandler> mErrorHandlerProvider;
    private final Provider<ImageLoader> mImageLoaderProvider;

    public HomePresenter_MembersInjector(Provider<RxErrorHandler> provider, Provider<Application> provider2, Provider<ImageLoader> provider3, Provider<AppManager> provider4) {
        this.mErrorHandlerProvider = provider;
        this.mApplicationProvider = provider2;
        this.mImageLoaderProvider = provider3;
        this.mAppManagerProvider = provider4;
    }

    public static MembersInjector<HomePresenter> create(Provider<RxErrorHandler> provider, Provider<Application> provider2, Provider<ImageLoader> provider3, Provider<AppManager> provider4) {
        return new HomePresenter_MembersInjector(provider, provider2, provider3, provider4);
    }

    @InjectedFieldSignature("com.yj.coffeemachines.mvp.presenter.HomePresenter.mAppManager")
    public static void injectMAppManager(HomePresenter homePresenter, AppManager appManager) {
        homePresenter.mAppManager = appManager;
    }

    @InjectedFieldSignature("com.yj.coffeemachines.mvp.presenter.HomePresenter.mApplication")
    public static void injectMApplication(HomePresenter homePresenter, Application application) {
        homePresenter.mApplication = application;
    }

    @InjectedFieldSignature("com.yj.coffeemachines.mvp.presenter.HomePresenter.mErrorHandler")
    public static void injectMErrorHandler(HomePresenter homePresenter, RxErrorHandler rxErrorHandler) {
        homePresenter.mErrorHandler = rxErrorHandler;
    }

    @InjectedFieldSignature("com.yj.coffeemachines.mvp.presenter.HomePresenter.mImageLoader")
    public static void injectMImageLoader(HomePresenter homePresenter, ImageLoader imageLoader) {
        homePresenter.mImageLoader = imageLoader;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(HomePresenter homePresenter) {
        injectMErrorHandler(homePresenter, this.mErrorHandlerProvider.get());
        injectMApplication(homePresenter, this.mApplicationProvider.get());
        injectMImageLoader(homePresenter, this.mImageLoaderProvider.get());
        injectMAppManager(homePresenter, this.mAppManagerProvider.get());
    }
}
