package com.yj.coffeemachines.mvp.presenter;

import android.app.Application;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.integration.AppManager;
import dagger.MembersInjector;
import dagger.internal.InjectedFieldSignature;
import javax.inject.Provider;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;

/* loaded from: classes.dex */
public final class Step1Presenter_MembersInjector implements MembersInjector<Step1Presenter> {
    private final Provider<AppManager> mAppManagerProvider;
    private final Provider<Application> mApplicationProvider;
    private final Provider<RxErrorHandler> mErrorHandlerProvider;
    private final Provider<ImageLoader> mImageLoaderProvider;

    public Step1Presenter_MembersInjector(Provider<RxErrorHandler> provider, Provider<Application> provider2, Provider<ImageLoader> provider3, Provider<AppManager> provider4) {
        this.mErrorHandlerProvider = provider;
        this.mApplicationProvider = provider2;
        this.mImageLoaderProvider = provider3;
        this.mAppManagerProvider = provider4;
    }

    public static MembersInjector<Step1Presenter> create(Provider<RxErrorHandler> provider, Provider<Application> provider2, Provider<ImageLoader> provider3, Provider<AppManager> provider4) {
        return new Step1Presenter_MembersInjector(provider, provider2, provider3, provider4);
    }

    @InjectedFieldSignature("com.yj.coffeemachines.mvp.presenter.Step1Presenter.mAppManager")
    public static void injectMAppManager(Step1Presenter step1Presenter, AppManager appManager) {
        step1Presenter.mAppManager = appManager;
    }

    @InjectedFieldSignature("com.yj.coffeemachines.mvp.presenter.Step1Presenter.mApplication")
    public static void injectMApplication(Step1Presenter step1Presenter, Application application) {
        step1Presenter.mApplication = application;
    }

    @InjectedFieldSignature("com.yj.coffeemachines.mvp.presenter.Step1Presenter.mErrorHandler")
    public static void injectMErrorHandler(Step1Presenter step1Presenter, RxErrorHandler rxErrorHandler) {
        step1Presenter.mErrorHandler = rxErrorHandler;
    }

    @InjectedFieldSignature("com.yj.coffeemachines.mvp.presenter.Step1Presenter.mImageLoader")
    public static void injectMImageLoader(Step1Presenter step1Presenter, ImageLoader imageLoader) {
        step1Presenter.mImageLoader = imageLoader;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(Step1Presenter step1Presenter) {
        injectMErrorHandler(step1Presenter, this.mErrorHandlerProvider.get());
        injectMApplication(step1Presenter, this.mApplicationProvider.get());
        injectMImageLoader(step1Presenter, this.mImageLoaderProvider.get());
        injectMAppManager(step1Presenter, this.mAppManagerProvider.get());
    }
}
