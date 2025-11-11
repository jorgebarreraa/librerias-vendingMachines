package com.yj.coffeemachines.mvp.presenter;

import android.app.Application;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.integration.AppManager;
import dagger.MembersInjector;
import dagger.internal.InjectedFieldSignature;
import javax.inject.Provider;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;

/* loaded from: classes.dex */
public final class Ops8_MaterialSetingPresenter_MembersInjector implements MembersInjector<Ops8_MaterialSetingPresenter> {
    private final Provider<AppManager> mAppManagerProvider;
    private final Provider<Application> mApplicationProvider;
    private final Provider<RxErrorHandler> mErrorHandlerProvider;
    private final Provider<ImageLoader> mImageLoaderProvider;

    public Ops8_MaterialSetingPresenter_MembersInjector(Provider<RxErrorHandler> provider, Provider<Application> provider2, Provider<ImageLoader> provider3, Provider<AppManager> provider4) {
        this.mErrorHandlerProvider = provider;
        this.mApplicationProvider = provider2;
        this.mImageLoaderProvider = provider3;
        this.mAppManagerProvider = provider4;
    }

    public static MembersInjector<Ops8_MaterialSetingPresenter> create(Provider<RxErrorHandler> provider, Provider<Application> provider2, Provider<ImageLoader> provider3, Provider<AppManager> provider4) {
        return new Ops8_MaterialSetingPresenter_MembersInjector(provider, provider2, provider3, provider4);
    }

    @InjectedFieldSignature("com.yj.coffeemachines.mvp.presenter.Ops8_MaterialSetingPresenter.mAppManager")
    public static void injectMAppManager(Ops8_MaterialSetingPresenter ops8_MaterialSetingPresenter, AppManager appManager) {
        ops8_MaterialSetingPresenter.mAppManager = appManager;
    }

    @InjectedFieldSignature("com.yj.coffeemachines.mvp.presenter.Ops8_MaterialSetingPresenter.mApplication")
    public static void injectMApplication(Ops8_MaterialSetingPresenter ops8_MaterialSetingPresenter, Application application) {
        ops8_MaterialSetingPresenter.mApplication = application;
    }

    @InjectedFieldSignature("com.yj.coffeemachines.mvp.presenter.Ops8_MaterialSetingPresenter.mErrorHandler")
    public static void injectMErrorHandler(Ops8_MaterialSetingPresenter ops8_MaterialSetingPresenter, RxErrorHandler rxErrorHandler) {
        ops8_MaterialSetingPresenter.mErrorHandler = rxErrorHandler;
    }

    @InjectedFieldSignature("com.yj.coffeemachines.mvp.presenter.Ops8_MaterialSetingPresenter.mImageLoader")
    public static void injectMImageLoader(Ops8_MaterialSetingPresenter ops8_MaterialSetingPresenter, ImageLoader imageLoader) {
        ops8_MaterialSetingPresenter.mImageLoader = imageLoader;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(Ops8_MaterialSetingPresenter ops8_MaterialSetingPresenter) {
        injectMErrorHandler(ops8_MaterialSetingPresenter, this.mErrorHandlerProvider.get());
        injectMApplication(ops8_MaterialSetingPresenter, this.mApplicationProvider.get());
        injectMImageLoader(ops8_MaterialSetingPresenter, this.mImageLoaderProvider.get());
        injectMAppManager(ops8_MaterialSetingPresenter, this.mAppManagerProvider.get());
    }
}
