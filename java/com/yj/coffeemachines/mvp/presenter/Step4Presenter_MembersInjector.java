package com.yj.coffeemachines.mvp.presenter;

import android.app.Application;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.integration.AppManager;
import dagger.MembersInjector;
import dagger.internal.InjectedFieldSignature;
import javax.inject.Provider;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;

/* loaded from: classes.dex */
public final class Step4Presenter_MembersInjector implements MembersInjector<Step4Presenter> {
    private final Provider<AppManager> mAppManagerProvider;
    private final Provider<Application> mApplicationProvider;
    private final Provider<RxErrorHandler> mErrorHandlerProvider;
    private final Provider<ImageLoader> mImageLoaderProvider;

    public Step4Presenter_MembersInjector(Provider<RxErrorHandler> provider, Provider<Application> provider2, Provider<ImageLoader> provider3, Provider<AppManager> provider4) {
        this.mErrorHandlerProvider = provider;
        this.mApplicationProvider = provider2;
        this.mImageLoaderProvider = provider3;
        this.mAppManagerProvider = provider4;
    }

    public static MembersInjector<Step4Presenter> create(Provider<RxErrorHandler> provider, Provider<Application> provider2, Provider<ImageLoader> provider3, Provider<AppManager> provider4) {
        return new Step4Presenter_MembersInjector(provider, provider2, provider3, provider4);
    }

    @InjectedFieldSignature("com.yj.coffeemachines.mvp.presenter.Step4Presenter.mAppManager")
    public static void injectMAppManager(Step4Presenter step4Presenter, AppManager appManager) {
        step4Presenter.mAppManager = appManager;
    }

    @InjectedFieldSignature("com.yj.coffeemachines.mvp.presenter.Step4Presenter.mApplication")
    public static void injectMApplication(Step4Presenter step4Presenter, Application application) {
        step4Presenter.mApplication = application;
    }

    @InjectedFieldSignature("com.yj.coffeemachines.mvp.presenter.Step4Presenter.mErrorHandler")
    public static void injectMErrorHandler(Step4Presenter step4Presenter, RxErrorHandler rxErrorHandler) {
        step4Presenter.mErrorHandler = rxErrorHandler;
    }

    @InjectedFieldSignature("com.yj.coffeemachines.mvp.presenter.Step4Presenter.mImageLoader")
    public static void injectMImageLoader(Step4Presenter step4Presenter, ImageLoader imageLoader) {
        step4Presenter.mImageLoader = imageLoader;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(Step4Presenter step4Presenter) {
        injectMErrorHandler(step4Presenter, this.mErrorHandlerProvider.get());
        injectMApplication(step4Presenter, this.mApplicationProvider.get());
        injectMImageLoader(step4Presenter, this.mImageLoaderProvider.get());
        injectMAppManager(step4Presenter, this.mAppManagerProvider.get());
    }
}
