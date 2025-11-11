package com.yj.coffeemachines.mvp.presenter;

import android.app.Application;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.integration.AppManager;
import dagger.MembersInjector;
import dagger.internal.InjectedFieldSignature;
import javax.inject.Provider;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;

/* loaded from: classes.dex */
public final class Ops3_WaterSetingPresenter_MembersInjector implements MembersInjector<Ops3_WaterSetingPresenter> {
    private final Provider<AppManager> mAppManagerProvider;
    private final Provider<Application> mApplicationProvider;
    private final Provider<RxErrorHandler> mErrorHandlerProvider;
    private final Provider<ImageLoader> mImageLoaderProvider;

    public Ops3_WaterSetingPresenter_MembersInjector(Provider<RxErrorHandler> provider, Provider<Application> provider2, Provider<ImageLoader> provider3, Provider<AppManager> provider4) {
        this.mErrorHandlerProvider = provider;
        this.mApplicationProvider = provider2;
        this.mImageLoaderProvider = provider3;
        this.mAppManagerProvider = provider4;
    }

    public static MembersInjector<Ops3_WaterSetingPresenter> create(Provider<RxErrorHandler> provider, Provider<Application> provider2, Provider<ImageLoader> provider3, Provider<AppManager> provider4) {
        return new Ops3_WaterSetingPresenter_MembersInjector(provider, provider2, provider3, provider4);
    }

    @InjectedFieldSignature("com.yj.coffeemachines.mvp.presenter.Ops3_WaterSetingPresenter.mAppManager")
    public static void injectMAppManager(Ops3_WaterSetingPresenter ops3_WaterSetingPresenter, AppManager appManager) {
        ops3_WaterSetingPresenter.mAppManager = appManager;
    }

    @InjectedFieldSignature("com.yj.coffeemachines.mvp.presenter.Ops3_WaterSetingPresenter.mApplication")
    public static void injectMApplication(Ops3_WaterSetingPresenter ops3_WaterSetingPresenter, Application application) {
        ops3_WaterSetingPresenter.mApplication = application;
    }

    @InjectedFieldSignature("com.yj.coffeemachines.mvp.presenter.Ops3_WaterSetingPresenter.mErrorHandler")
    public static void injectMErrorHandler(Ops3_WaterSetingPresenter ops3_WaterSetingPresenter, RxErrorHandler rxErrorHandler) {
        ops3_WaterSetingPresenter.mErrorHandler = rxErrorHandler;
    }

    @InjectedFieldSignature("com.yj.coffeemachines.mvp.presenter.Ops3_WaterSetingPresenter.mImageLoader")
    public static void injectMImageLoader(Ops3_WaterSetingPresenter ops3_WaterSetingPresenter, ImageLoader imageLoader) {
        ops3_WaterSetingPresenter.mImageLoader = imageLoader;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(Ops3_WaterSetingPresenter ops3_WaterSetingPresenter) {
        injectMErrorHandler(ops3_WaterSetingPresenter, this.mErrorHandlerProvider.get());
        injectMApplication(ops3_WaterSetingPresenter, this.mApplicationProvider.get());
        injectMImageLoader(ops3_WaterSetingPresenter, this.mImageLoaderProvider.get());
        injectMAppManager(ops3_WaterSetingPresenter, this.mAppManagerProvider.get());
    }
}
