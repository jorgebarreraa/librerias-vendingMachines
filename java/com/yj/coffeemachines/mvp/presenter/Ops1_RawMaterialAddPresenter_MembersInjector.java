package com.yj.coffeemachines.mvp.presenter;

import android.app.Application;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.integration.AppManager;
import dagger.MembersInjector;
import dagger.internal.InjectedFieldSignature;
import javax.inject.Provider;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;

/* loaded from: classes.dex */
public final class Ops1_RawMaterialAddPresenter_MembersInjector implements MembersInjector<Ops1_RawMaterialAddPresenter> {
    private final Provider<AppManager> mAppManagerProvider;
    private final Provider<Application> mApplicationProvider;
    private final Provider<RxErrorHandler> mErrorHandlerProvider;
    private final Provider<ImageLoader> mImageLoaderProvider;

    public Ops1_RawMaterialAddPresenter_MembersInjector(Provider<RxErrorHandler> provider, Provider<Application> provider2, Provider<ImageLoader> provider3, Provider<AppManager> provider4) {
        this.mErrorHandlerProvider = provider;
        this.mApplicationProvider = provider2;
        this.mImageLoaderProvider = provider3;
        this.mAppManagerProvider = provider4;
    }

    public static MembersInjector<Ops1_RawMaterialAddPresenter> create(Provider<RxErrorHandler> provider, Provider<Application> provider2, Provider<ImageLoader> provider3, Provider<AppManager> provider4) {
        return new Ops1_RawMaterialAddPresenter_MembersInjector(provider, provider2, provider3, provider4);
    }

    @InjectedFieldSignature("com.yj.coffeemachines.mvp.presenter.Ops1_RawMaterialAddPresenter.mAppManager")
    public static void injectMAppManager(Ops1_RawMaterialAddPresenter ops1_RawMaterialAddPresenter, AppManager appManager) {
        ops1_RawMaterialAddPresenter.mAppManager = appManager;
    }

    @InjectedFieldSignature("com.yj.coffeemachines.mvp.presenter.Ops1_RawMaterialAddPresenter.mApplication")
    public static void injectMApplication(Ops1_RawMaterialAddPresenter ops1_RawMaterialAddPresenter, Application application) {
        ops1_RawMaterialAddPresenter.mApplication = application;
    }

    @InjectedFieldSignature("com.yj.coffeemachines.mvp.presenter.Ops1_RawMaterialAddPresenter.mErrorHandler")
    public static void injectMErrorHandler(Ops1_RawMaterialAddPresenter ops1_RawMaterialAddPresenter, RxErrorHandler rxErrorHandler) {
        ops1_RawMaterialAddPresenter.mErrorHandler = rxErrorHandler;
    }

    @InjectedFieldSignature("com.yj.coffeemachines.mvp.presenter.Ops1_RawMaterialAddPresenter.mImageLoader")
    public static void injectMImageLoader(Ops1_RawMaterialAddPresenter ops1_RawMaterialAddPresenter, ImageLoader imageLoader) {
        ops1_RawMaterialAddPresenter.mImageLoader = imageLoader;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(Ops1_RawMaterialAddPresenter ops1_RawMaterialAddPresenter) {
        injectMErrorHandler(ops1_RawMaterialAddPresenter, this.mErrorHandlerProvider.get());
        injectMApplication(ops1_RawMaterialAddPresenter, this.mApplicationProvider.get());
        injectMImageLoader(ops1_RawMaterialAddPresenter, this.mImageLoaderProvider.get());
        injectMAppManager(ops1_RawMaterialAddPresenter, this.mAppManagerProvider.get());
    }
}
