package com.yj.coffeemachines.mvp.presenter;

import android.app.Application;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.integration.AppManager;
import dagger.MembersInjector;
import dagger.internal.InjectedFieldSignature;
import javax.inject.Provider;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;

/* loaded from: classes.dex */
public final class Step2Presenter_MembersInjector implements MembersInjector<Step2Presenter> {
    private final Provider<AppManager> mAppManagerProvider;
    private final Provider<Application> mApplicationProvider;
    private final Provider<RxErrorHandler> mErrorHandlerProvider;
    private final Provider<ImageLoader> mImageLoaderProvider;

    public Step2Presenter_MembersInjector(Provider<RxErrorHandler> provider, Provider<Application> provider2, Provider<ImageLoader> provider3, Provider<AppManager> provider4) {
        this.mErrorHandlerProvider = provider;
        this.mApplicationProvider = provider2;
        this.mImageLoaderProvider = provider3;
        this.mAppManagerProvider = provider4;
    }

    public static MembersInjector<Step2Presenter> create(Provider<RxErrorHandler> provider, Provider<Application> provider2, Provider<ImageLoader> provider3, Provider<AppManager> provider4) {
        return new Step2Presenter_MembersInjector(provider, provider2, provider3, provider4);
    }

    @InjectedFieldSignature("com.yj.coffeemachines.mvp.presenter.Step2Presenter.mAppManager")
    public static void injectMAppManager(Step2Presenter step2Presenter, AppManager appManager) {
        step2Presenter.mAppManager = appManager;
    }

    @InjectedFieldSignature("com.yj.coffeemachines.mvp.presenter.Step2Presenter.mApplication")
    public static void injectMApplication(Step2Presenter step2Presenter, Application application) {
        step2Presenter.mApplication = application;
    }

    @InjectedFieldSignature("com.yj.coffeemachines.mvp.presenter.Step2Presenter.mErrorHandler")
    public static void injectMErrorHandler(Step2Presenter step2Presenter, RxErrorHandler rxErrorHandler) {
        step2Presenter.mErrorHandler = rxErrorHandler;
    }

    @InjectedFieldSignature("com.yj.coffeemachines.mvp.presenter.Step2Presenter.mImageLoader")
    public static void injectMImageLoader(Step2Presenter step2Presenter, ImageLoader imageLoader) {
        step2Presenter.mImageLoader = imageLoader;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(Step2Presenter step2Presenter) {
        injectMErrorHandler(step2Presenter, this.mErrorHandlerProvider.get());
        injectMApplication(step2Presenter, this.mApplicationProvider.get());
        injectMImageLoader(step2Presenter, this.mImageLoaderProvider.get());
        injectMAppManager(step2Presenter, this.mAppManagerProvider.get());
    }
}
