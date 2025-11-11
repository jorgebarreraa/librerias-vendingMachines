package com.yj.coffeemachines.mvp.presenter;

import android.app.Application;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.integration.AppManager;
import dagger.MembersInjector;
import dagger.internal.InjectedFieldSignature;
import javax.inject.Provider;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;

/* loaded from: classes.dex */
public final class Ops6_LogcatPresenter_MembersInjector implements MembersInjector<Ops6_LogcatPresenter> {
    private final Provider<AppManager> mAppManagerProvider;
    private final Provider<Application> mApplicationProvider;
    private final Provider<RxErrorHandler> mErrorHandlerProvider;
    private final Provider<ImageLoader> mImageLoaderProvider;

    public Ops6_LogcatPresenter_MembersInjector(Provider<RxErrorHandler> provider, Provider<Application> provider2, Provider<ImageLoader> provider3, Provider<AppManager> provider4) {
        this.mErrorHandlerProvider = provider;
        this.mApplicationProvider = provider2;
        this.mImageLoaderProvider = provider3;
        this.mAppManagerProvider = provider4;
    }

    public static MembersInjector<Ops6_LogcatPresenter> create(Provider<RxErrorHandler> provider, Provider<Application> provider2, Provider<ImageLoader> provider3, Provider<AppManager> provider4) {
        return new Ops6_LogcatPresenter_MembersInjector(provider, provider2, provider3, provider4);
    }

    @InjectedFieldSignature("com.yj.coffeemachines.mvp.presenter.Ops6_LogcatPresenter.mAppManager")
    public static void injectMAppManager(Ops6_LogcatPresenter ops6_LogcatPresenter, AppManager appManager) {
        ops6_LogcatPresenter.mAppManager = appManager;
    }

    @InjectedFieldSignature("com.yj.coffeemachines.mvp.presenter.Ops6_LogcatPresenter.mApplication")
    public static void injectMApplication(Ops6_LogcatPresenter ops6_LogcatPresenter, Application application) {
        ops6_LogcatPresenter.mApplication = application;
    }

    @InjectedFieldSignature("com.yj.coffeemachines.mvp.presenter.Ops6_LogcatPresenter.mErrorHandler")
    public static void injectMErrorHandler(Ops6_LogcatPresenter ops6_LogcatPresenter, RxErrorHandler rxErrorHandler) {
        ops6_LogcatPresenter.mErrorHandler = rxErrorHandler;
    }

    @InjectedFieldSignature("com.yj.coffeemachines.mvp.presenter.Ops6_LogcatPresenter.mImageLoader")
    public static void injectMImageLoader(Ops6_LogcatPresenter ops6_LogcatPresenter, ImageLoader imageLoader) {
        ops6_LogcatPresenter.mImageLoader = imageLoader;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(Ops6_LogcatPresenter ops6_LogcatPresenter) {
        injectMErrorHandler(ops6_LogcatPresenter, this.mErrorHandlerProvider.get());
        injectMApplication(ops6_LogcatPresenter, this.mApplicationProvider.get());
        injectMImageLoader(ops6_LogcatPresenter, this.mImageLoaderProvider.get());
        injectMAppManager(ops6_LogcatPresenter, this.mAppManagerProvider.get());
    }
}
