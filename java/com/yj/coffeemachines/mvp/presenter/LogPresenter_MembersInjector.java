package com.yj.coffeemachines.mvp.presenter;

import android.app.Application;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.integration.AppManager;
import dagger.MembersInjector;
import dagger.internal.InjectedFieldSignature;
import javax.inject.Provider;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;

/* loaded from: classes.dex */
public final class LogPresenter_MembersInjector implements MembersInjector<LogPresenter> {
    private final Provider<AppManager> mAppManagerProvider;
    private final Provider<Application> mApplicationProvider;
    private final Provider<RxErrorHandler> mErrorHandlerProvider;
    private final Provider<ImageLoader> mImageLoaderProvider;

    public LogPresenter_MembersInjector(Provider<RxErrorHandler> provider, Provider<Application> provider2, Provider<ImageLoader> provider3, Provider<AppManager> provider4) {
        this.mErrorHandlerProvider = provider;
        this.mApplicationProvider = provider2;
        this.mImageLoaderProvider = provider3;
        this.mAppManagerProvider = provider4;
    }

    public static MembersInjector<LogPresenter> create(Provider<RxErrorHandler> provider, Provider<Application> provider2, Provider<ImageLoader> provider3, Provider<AppManager> provider4) {
        return new LogPresenter_MembersInjector(provider, provider2, provider3, provider4);
    }

    @InjectedFieldSignature("com.yj.coffeemachines.mvp.presenter.LogPresenter.mAppManager")
    public static void injectMAppManager(LogPresenter logPresenter, AppManager appManager) {
        logPresenter.mAppManager = appManager;
    }

    @InjectedFieldSignature("com.yj.coffeemachines.mvp.presenter.LogPresenter.mApplication")
    public static void injectMApplication(LogPresenter logPresenter, Application application) {
        logPresenter.mApplication = application;
    }

    @InjectedFieldSignature("com.yj.coffeemachines.mvp.presenter.LogPresenter.mErrorHandler")
    public static void injectMErrorHandler(LogPresenter logPresenter, RxErrorHandler rxErrorHandler) {
        logPresenter.mErrorHandler = rxErrorHandler;
    }

    @InjectedFieldSignature("com.yj.coffeemachines.mvp.presenter.LogPresenter.mImageLoader")
    public static void injectMImageLoader(LogPresenter logPresenter, ImageLoader imageLoader) {
        logPresenter.mImageLoader = imageLoader;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(LogPresenter logPresenter) {
        injectMErrorHandler(logPresenter, this.mErrorHandlerProvider.get());
        injectMApplication(logPresenter, this.mApplicationProvider.get());
        injectMImageLoader(logPresenter, this.mImageLoaderProvider.get());
        injectMAppManager(logPresenter, this.mAppManagerProvider.get());
    }
}
