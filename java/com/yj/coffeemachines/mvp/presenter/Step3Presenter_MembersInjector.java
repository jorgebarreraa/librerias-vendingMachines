package com.yj.coffeemachines.mvp.presenter;

import android.app.Application;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.integration.AppManager;
import dagger.MembersInjector;
import dagger.internal.InjectedFieldSignature;
import javax.inject.Provider;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;

/* loaded from: classes.dex */
public final class Step3Presenter_MembersInjector implements MembersInjector<Step3Presenter> {
    private final Provider<AppManager> mAppManagerProvider;
    private final Provider<Application> mApplicationProvider;
    private final Provider<RxErrorHandler> mErrorHandlerProvider;
    private final Provider<ImageLoader> mImageLoaderProvider;

    public Step3Presenter_MembersInjector(Provider<RxErrorHandler> provider, Provider<Application> provider2, Provider<ImageLoader> provider3, Provider<AppManager> provider4) {
        this.mErrorHandlerProvider = provider;
        this.mApplicationProvider = provider2;
        this.mImageLoaderProvider = provider3;
        this.mAppManagerProvider = provider4;
    }

    public static MembersInjector<Step3Presenter> create(Provider<RxErrorHandler> provider, Provider<Application> provider2, Provider<ImageLoader> provider3, Provider<AppManager> provider4) {
        return new Step3Presenter_MembersInjector(provider, provider2, provider3, provider4);
    }

    @InjectedFieldSignature("com.yj.coffeemachines.mvp.presenter.Step3Presenter.mAppManager")
    public static void injectMAppManager(Step3Presenter step3Presenter, AppManager appManager) {
        step3Presenter.mAppManager = appManager;
    }

    @InjectedFieldSignature("com.yj.coffeemachines.mvp.presenter.Step3Presenter.mApplication")
    public static void injectMApplication(Step3Presenter step3Presenter, Application application) {
        step3Presenter.mApplication = application;
    }

    @InjectedFieldSignature("com.yj.coffeemachines.mvp.presenter.Step3Presenter.mErrorHandler")
    public static void injectMErrorHandler(Step3Presenter step3Presenter, RxErrorHandler rxErrorHandler) {
        step3Presenter.mErrorHandler = rxErrorHandler;
    }

    @InjectedFieldSignature("com.yj.coffeemachines.mvp.presenter.Step3Presenter.mImageLoader")
    public static void injectMImageLoader(Step3Presenter step3Presenter, ImageLoader imageLoader) {
        step3Presenter.mImageLoader = imageLoader;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(Step3Presenter step3Presenter) {
        injectMErrorHandler(step3Presenter, this.mErrorHandlerProvider.get());
        injectMApplication(step3Presenter, this.mApplicationProvider.get());
        injectMImageLoader(step3Presenter, this.mImageLoaderProvider.get());
        injectMAppManager(step3Presenter, this.mAppManagerProvider.get());
    }
}
