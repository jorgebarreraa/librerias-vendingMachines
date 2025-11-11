package com.yj.coffeemachines.mvp.presenter;

import android.app.Application;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.integration.AppManager;
import dagger.MembersInjector;
import dagger.internal.InjectedFieldSignature;
import javax.inject.Provider;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;

/* loaded from: classes.dex */
public final class Factory2_SeralPortConfigPresenter_MembersInjector implements MembersInjector<Factory2_SeralPortConfigPresenter> {
    private final Provider<AppManager> mAppManagerProvider;
    private final Provider<Application> mApplicationProvider;
    private final Provider<RxErrorHandler> mErrorHandlerProvider;
    private final Provider<ImageLoader> mImageLoaderProvider;

    public Factory2_SeralPortConfigPresenter_MembersInjector(Provider<RxErrorHandler> provider, Provider<Application> provider2, Provider<ImageLoader> provider3, Provider<AppManager> provider4) {
        this.mErrorHandlerProvider = provider;
        this.mApplicationProvider = provider2;
        this.mImageLoaderProvider = provider3;
        this.mAppManagerProvider = provider4;
    }

    public static MembersInjector<Factory2_SeralPortConfigPresenter> create(Provider<RxErrorHandler> provider, Provider<Application> provider2, Provider<ImageLoader> provider3, Provider<AppManager> provider4) {
        return new Factory2_SeralPortConfigPresenter_MembersInjector(provider, provider2, provider3, provider4);
    }

    @InjectedFieldSignature("com.yj.coffeemachines.mvp.presenter.Factory2_SeralPortConfigPresenter.mAppManager")
    public static void injectMAppManager(Factory2_SeralPortConfigPresenter factory2_SeralPortConfigPresenter, AppManager appManager) {
        factory2_SeralPortConfigPresenter.mAppManager = appManager;
    }

    @InjectedFieldSignature("com.yj.coffeemachines.mvp.presenter.Factory2_SeralPortConfigPresenter.mApplication")
    public static void injectMApplication(Factory2_SeralPortConfigPresenter factory2_SeralPortConfigPresenter, Application application) {
        factory2_SeralPortConfigPresenter.mApplication = application;
    }

    @InjectedFieldSignature("com.yj.coffeemachines.mvp.presenter.Factory2_SeralPortConfigPresenter.mErrorHandler")
    public static void injectMErrorHandler(Factory2_SeralPortConfigPresenter factory2_SeralPortConfigPresenter, RxErrorHandler rxErrorHandler) {
        factory2_SeralPortConfigPresenter.mErrorHandler = rxErrorHandler;
    }

    @InjectedFieldSignature("com.yj.coffeemachines.mvp.presenter.Factory2_SeralPortConfigPresenter.mImageLoader")
    public static void injectMImageLoader(Factory2_SeralPortConfigPresenter factory2_SeralPortConfigPresenter, ImageLoader imageLoader) {
        factory2_SeralPortConfigPresenter.mImageLoader = imageLoader;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(Factory2_SeralPortConfigPresenter factory2_SeralPortConfigPresenter) {
        injectMErrorHandler(factory2_SeralPortConfigPresenter, this.mErrorHandlerProvider.get());
        injectMApplication(factory2_SeralPortConfigPresenter, this.mApplicationProvider.get());
        injectMImageLoader(factory2_SeralPortConfigPresenter, this.mImageLoaderProvider.get());
        injectMAppManager(factory2_SeralPortConfigPresenter, this.mAppManagerProvider.get());
    }
}
