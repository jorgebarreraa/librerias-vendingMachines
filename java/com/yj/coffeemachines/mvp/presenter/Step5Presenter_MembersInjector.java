package com.yj.coffeemachines.mvp.presenter;

import android.app.Application;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.integration.AppManager;
import dagger.MembersInjector;
import dagger.internal.InjectedFieldSignature;
import javax.inject.Provider;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;

/* loaded from: classes.dex */
public final class Step5Presenter_MembersInjector implements MembersInjector<Step5Presenter> {
    private final Provider<AppManager> mAppManagerProvider;
    private final Provider<Application> mApplicationProvider;
    private final Provider<RxErrorHandler> mErrorHandlerProvider;
    private final Provider<ImageLoader> mImageLoaderProvider;

    public Step5Presenter_MembersInjector(Provider<RxErrorHandler> provider, Provider<Application> provider2, Provider<ImageLoader> provider3, Provider<AppManager> provider4) {
        this.mErrorHandlerProvider = provider;
        this.mApplicationProvider = provider2;
        this.mImageLoaderProvider = provider3;
        this.mAppManagerProvider = provider4;
    }

    public static MembersInjector<Step5Presenter> create(Provider<RxErrorHandler> provider, Provider<Application> provider2, Provider<ImageLoader> provider3, Provider<AppManager> provider4) {
        return new Step5Presenter_MembersInjector(provider, provider2, provider3, provider4);
    }

    @InjectedFieldSignature("com.yj.coffeemachines.mvp.presenter.Step5Presenter.mAppManager")
    public static void injectMAppManager(Step5Presenter step5Presenter, AppManager appManager) {
        step5Presenter.mAppManager = appManager;
    }

    @InjectedFieldSignature("com.yj.coffeemachines.mvp.presenter.Step5Presenter.mApplication")
    public static void injectMApplication(Step5Presenter step5Presenter, Application application) {
        step5Presenter.mApplication = application;
    }

    @InjectedFieldSignature("com.yj.coffeemachines.mvp.presenter.Step5Presenter.mErrorHandler")
    public static void injectMErrorHandler(Step5Presenter step5Presenter, RxErrorHandler rxErrorHandler) {
        step5Presenter.mErrorHandler = rxErrorHandler;
    }

    @InjectedFieldSignature("com.yj.coffeemachines.mvp.presenter.Step5Presenter.mImageLoader")
    public static void injectMImageLoader(Step5Presenter step5Presenter, ImageLoader imageLoader) {
        step5Presenter.mImageLoader = imageLoader;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(Step5Presenter step5Presenter) {
        injectMErrorHandler(step5Presenter, this.mErrorHandlerProvider.get());
        injectMApplication(step5Presenter, this.mApplicationProvider.get());
        injectMImageLoader(step5Presenter, this.mImageLoaderProvider.get());
        injectMAppManager(step5Presenter, this.mAppManagerProvider.get());
    }
}
