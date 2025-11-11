package com.yj.coffeemachines.mvp.presenter;

import android.app.Application;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.integration.AppManager;
import dagger.MembersInjector;
import dagger.internal.InjectedFieldSignature;
import javax.inject.Provider;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;

/* loaded from: classes.dex */
public final class OpsPresenter_MembersInjector implements MembersInjector<OpsPresenter> {
    private final Provider<AppManager> mAppManagerProvider;
    private final Provider<Application> mApplicationProvider;
    private final Provider<RxErrorHandler> mErrorHandlerProvider;
    private final Provider<ImageLoader> mImageLoaderProvider;

    public OpsPresenter_MembersInjector(Provider<RxErrorHandler> provider, Provider<Application> provider2, Provider<ImageLoader> provider3, Provider<AppManager> provider4) {
        this.mErrorHandlerProvider = provider;
        this.mApplicationProvider = provider2;
        this.mImageLoaderProvider = provider3;
        this.mAppManagerProvider = provider4;
    }

    public static MembersInjector<OpsPresenter> create(Provider<RxErrorHandler> provider, Provider<Application> provider2, Provider<ImageLoader> provider3, Provider<AppManager> provider4) {
        return new OpsPresenter_MembersInjector(provider, provider2, provider3, provider4);
    }

    @InjectedFieldSignature("com.yj.coffeemachines.mvp.presenter.OpsPresenter.mAppManager")
    public static void injectMAppManager(OpsPresenter opsPresenter, AppManager appManager) {
        opsPresenter.mAppManager = appManager;
    }

    @InjectedFieldSignature("com.yj.coffeemachines.mvp.presenter.OpsPresenter.mApplication")
    public static void injectMApplication(OpsPresenter opsPresenter, Application application) {
        opsPresenter.mApplication = application;
    }

    @InjectedFieldSignature("com.yj.coffeemachines.mvp.presenter.OpsPresenter.mErrorHandler")
    public static void injectMErrorHandler(OpsPresenter opsPresenter, RxErrorHandler rxErrorHandler) {
        opsPresenter.mErrorHandler = rxErrorHandler;
    }

    @InjectedFieldSignature("com.yj.coffeemachines.mvp.presenter.OpsPresenter.mImageLoader")
    public static void injectMImageLoader(OpsPresenter opsPresenter, ImageLoader imageLoader) {
        opsPresenter.mImageLoader = imageLoader;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(OpsPresenter opsPresenter) {
        injectMErrorHandler(opsPresenter, this.mErrorHandlerProvider.get());
        injectMApplication(opsPresenter, this.mApplicationProvider.get());
        injectMImageLoader(opsPresenter, this.mImageLoaderProvider.get());
        injectMAppManager(opsPresenter, this.mAppManagerProvider.get());
    }
}
