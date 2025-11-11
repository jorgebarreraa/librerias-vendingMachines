package com.yj.coffeemachines.di.component.mvp.presenter;

import android.app.Application;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.integration.AppManager;
import dagger.MembersInjector;
import dagger.internal.InjectedFieldSignature;
import javax.inject.Provider;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;

/* loaded from: classes.dex */
public final class Ops2_RawMaterialSetingPresenter_MembersInjector implements MembersInjector<Ops2_RawMaterialSetingPresenter> {
    private final Provider<AppManager> mAppManagerProvider;
    private final Provider<Application> mApplicationProvider;
    private final Provider<RxErrorHandler> mErrorHandlerProvider;
    private final Provider<ImageLoader> mImageLoaderProvider;

    public Ops2_RawMaterialSetingPresenter_MembersInjector(Provider<RxErrorHandler> provider, Provider<Application> provider2, Provider<ImageLoader> provider3, Provider<AppManager> provider4) {
        this.mErrorHandlerProvider = provider;
        this.mApplicationProvider = provider2;
        this.mImageLoaderProvider = provider3;
        this.mAppManagerProvider = provider4;
    }

    public static MembersInjector<Ops2_RawMaterialSetingPresenter> create(Provider<RxErrorHandler> provider, Provider<Application> provider2, Provider<ImageLoader> provider3, Provider<AppManager> provider4) {
        return new Ops2_RawMaterialSetingPresenter_MembersInjector(provider, provider2, provider3, provider4);
    }

    @InjectedFieldSignature("com.yj.coffeemachines.di.component.mvp.presenter.Ops2_RawMaterialSetingPresenter.mAppManager")
    public static void injectMAppManager(Ops2_RawMaterialSetingPresenter ops2_RawMaterialSetingPresenter, AppManager appManager) {
        ops2_RawMaterialSetingPresenter.mAppManager = appManager;
    }

    @InjectedFieldSignature("com.yj.coffeemachines.di.component.mvp.presenter.Ops2_RawMaterialSetingPresenter.mApplication")
    public static void injectMApplication(Ops2_RawMaterialSetingPresenter ops2_RawMaterialSetingPresenter, Application application) {
        ops2_RawMaterialSetingPresenter.mApplication = application;
    }

    @InjectedFieldSignature("com.yj.coffeemachines.di.component.mvp.presenter.Ops2_RawMaterialSetingPresenter.mErrorHandler")
    public static void injectMErrorHandler(Ops2_RawMaterialSetingPresenter ops2_RawMaterialSetingPresenter, RxErrorHandler rxErrorHandler) {
        ops2_RawMaterialSetingPresenter.mErrorHandler = rxErrorHandler;
    }

    @InjectedFieldSignature("com.yj.coffeemachines.di.component.mvp.presenter.Ops2_RawMaterialSetingPresenter.mImageLoader")
    public static void injectMImageLoader(Ops2_RawMaterialSetingPresenter ops2_RawMaterialSetingPresenter, ImageLoader imageLoader) {
        ops2_RawMaterialSetingPresenter.mImageLoader = imageLoader;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(Ops2_RawMaterialSetingPresenter ops2_RawMaterialSetingPresenter) {
        injectMErrorHandler(ops2_RawMaterialSetingPresenter, this.mErrorHandlerProvider.get());
        injectMApplication(ops2_RawMaterialSetingPresenter, this.mApplicationProvider.get());
        injectMImageLoader(ops2_RawMaterialSetingPresenter, this.mImageLoaderProvider.get());
        injectMAppManager(ops2_RawMaterialSetingPresenter, this.mAppManagerProvider.get());
    }
}
