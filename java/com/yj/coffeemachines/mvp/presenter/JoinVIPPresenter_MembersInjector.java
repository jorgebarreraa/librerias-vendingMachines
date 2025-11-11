package com.yj.coffeemachines.mvp.presenter;

import android.app.Application;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.integration.AppManager;
import dagger.MembersInjector;
import dagger.internal.InjectedFieldSignature;
import javax.inject.Provider;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;

/* loaded from: classes.dex */
public final class JoinVIPPresenter_MembersInjector implements MembersInjector<JoinVIPPresenter> {
    private final Provider<AppManager> mAppManagerProvider;
    private final Provider<Application> mApplicationProvider;
    private final Provider<RxErrorHandler> mErrorHandlerProvider;
    private final Provider<ImageLoader> mImageLoaderProvider;

    public JoinVIPPresenter_MembersInjector(Provider<RxErrorHandler> provider, Provider<Application> provider2, Provider<ImageLoader> provider3, Provider<AppManager> provider4) {
        this.mErrorHandlerProvider = provider;
        this.mApplicationProvider = provider2;
        this.mImageLoaderProvider = provider3;
        this.mAppManagerProvider = provider4;
    }

    public static MembersInjector<JoinVIPPresenter> create(Provider<RxErrorHandler> provider, Provider<Application> provider2, Provider<ImageLoader> provider3, Provider<AppManager> provider4) {
        return new JoinVIPPresenter_MembersInjector(provider, provider2, provider3, provider4);
    }

    @InjectedFieldSignature("com.yj.coffeemachines.mvp.presenter.JoinVIPPresenter.mAppManager")
    public static void injectMAppManager(JoinVIPPresenter joinVIPPresenter, AppManager appManager) {
        joinVIPPresenter.mAppManager = appManager;
    }

    @InjectedFieldSignature("com.yj.coffeemachines.mvp.presenter.JoinVIPPresenter.mApplication")
    public static void injectMApplication(JoinVIPPresenter joinVIPPresenter, Application application) {
        joinVIPPresenter.mApplication = application;
    }

    @InjectedFieldSignature("com.yj.coffeemachines.mvp.presenter.JoinVIPPresenter.mErrorHandler")
    public static void injectMErrorHandler(JoinVIPPresenter joinVIPPresenter, RxErrorHandler rxErrorHandler) {
        joinVIPPresenter.mErrorHandler = rxErrorHandler;
    }

    @InjectedFieldSignature("com.yj.coffeemachines.mvp.presenter.JoinVIPPresenter.mImageLoader")
    public static void injectMImageLoader(JoinVIPPresenter joinVIPPresenter, ImageLoader imageLoader) {
        joinVIPPresenter.mImageLoader = imageLoader;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(JoinVIPPresenter joinVIPPresenter) {
        injectMErrorHandler(joinVIPPresenter, this.mErrorHandlerProvider.get());
        injectMApplication(joinVIPPresenter, this.mApplicationProvider.get());
        injectMImageLoader(joinVIPPresenter, this.mImageLoaderProvider.get());
        injectMAppManager(joinVIPPresenter, this.mAppManagerProvider.get());
    }
}
