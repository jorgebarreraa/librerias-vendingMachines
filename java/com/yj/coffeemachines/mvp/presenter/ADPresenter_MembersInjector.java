package com.yj.coffeemachines.mvp.presenter;

import android.app.Application;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.integration.AppManager;
import dagger.MembersInjector;
import dagger.internal.InjectedFieldSignature;
import javax.inject.Provider;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;

/* loaded from: classes.dex */
public final class ADPresenter_MembersInjector implements MembersInjector<ADPresenter> {
    private final Provider<AppManager> mAppManagerProvider;
    private final Provider<Application> mApplicationProvider;
    private final Provider<RxErrorHandler> mErrorHandlerProvider;
    private final Provider<ImageLoader> mImageLoaderProvider;

    public ADPresenter_MembersInjector(Provider<RxErrorHandler> provider, Provider<Application> provider2, Provider<ImageLoader> provider3, Provider<AppManager> provider4) {
        this.mErrorHandlerProvider = provider;
        this.mApplicationProvider = provider2;
        this.mImageLoaderProvider = provider3;
        this.mAppManagerProvider = provider4;
    }

    public static MembersInjector<ADPresenter> create(Provider<RxErrorHandler> provider, Provider<Application> provider2, Provider<ImageLoader> provider3, Provider<AppManager> provider4) {
        return new ADPresenter_MembersInjector(provider, provider2, provider3, provider4);
    }

    @InjectedFieldSignature("com.yj.coffeemachines.mvp.presenter.ADPresenter.mAppManager")
    public static void injectMAppManager(ADPresenter aDPresenter, AppManager appManager) {
        aDPresenter.mAppManager = appManager;
    }

    @InjectedFieldSignature("com.yj.coffeemachines.mvp.presenter.ADPresenter.mApplication")
    public static void injectMApplication(ADPresenter aDPresenter, Application application) {
        aDPresenter.mApplication = application;
    }

    @InjectedFieldSignature("com.yj.coffeemachines.mvp.presenter.ADPresenter.mErrorHandler")
    public static void injectMErrorHandler(ADPresenter aDPresenter, RxErrorHandler rxErrorHandler) {
        aDPresenter.mErrorHandler = rxErrorHandler;
    }

    @InjectedFieldSignature("com.yj.coffeemachines.mvp.presenter.ADPresenter.mImageLoader")
    public static void injectMImageLoader(ADPresenter aDPresenter, ImageLoader imageLoader) {
        aDPresenter.mImageLoader = imageLoader;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(ADPresenter aDPresenter) {
        injectMErrorHandler(aDPresenter, this.mErrorHandlerProvider.get());
        injectMApplication(aDPresenter, this.mApplicationProvider.get());
        injectMImageLoader(aDPresenter, this.mImageLoaderProvider.get());
        injectMAppManager(aDPresenter, this.mAppManagerProvider.get());
    }
}
