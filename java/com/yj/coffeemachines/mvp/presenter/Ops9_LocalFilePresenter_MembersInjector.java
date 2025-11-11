package com.yj.coffeemachines.mvp.presenter;

import android.app.Application;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.integration.AppManager;
import dagger.MembersInjector;
import dagger.internal.InjectedFieldSignature;
import javax.inject.Provider;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;

/* loaded from: classes.dex */
public final class Ops9_LocalFilePresenter_MembersInjector implements MembersInjector<Ops9_LocalFilePresenter> {
    private final Provider<AppManager> mAppManagerProvider;
    private final Provider<Application> mApplicationProvider;
    private final Provider<RxErrorHandler> mErrorHandlerProvider;
    private final Provider<ImageLoader> mImageLoaderProvider;

    public Ops9_LocalFilePresenter_MembersInjector(Provider<RxErrorHandler> provider, Provider<Application> provider2, Provider<ImageLoader> provider3, Provider<AppManager> provider4) {
        this.mErrorHandlerProvider = provider;
        this.mApplicationProvider = provider2;
        this.mImageLoaderProvider = provider3;
        this.mAppManagerProvider = provider4;
    }

    public static MembersInjector<Ops9_LocalFilePresenter> create(Provider<RxErrorHandler> provider, Provider<Application> provider2, Provider<ImageLoader> provider3, Provider<AppManager> provider4) {
        return new Ops9_LocalFilePresenter_MembersInjector(provider, provider2, provider3, provider4);
    }

    @InjectedFieldSignature("com.yj.coffeemachines.mvp.presenter.Ops9_LocalFilePresenter.mAppManager")
    public static void injectMAppManager(Ops9_LocalFilePresenter ops9_LocalFilePresenter, AppManager appManager) {
        ops9_LocalFilePresenter.mAppManager = appManager;
    }

    @InjectedFieldSignature("com.yj.coffeemachines.mvp.presenter.Ops9_LocalFilePresenter.mApplication")
    public static void injectMApplication(Ops9_LocalFilePresenter ops9_LocalFilePresenter, Application application) {
        ops9_LocalFilePresenter.mApplication = application;
    }

    @InjectedFieldSignature("com.yj.coffeemachines.mvp.presenter.Ops9_LocalFilePresenter.mErrorHandler")
    public static void injectMErrorHandler(Ops9_LocalFilePresenter ops9_LocalFilePresenter, RxErrorHandler rxErrorHandler) {
        ops9_LocalFilePresenter.mErrorHandler = rxErrorHandler;
    }

    @InjectedFieldSignature("com.yj.coffeemachines.mvp.presenter.Ops9_LocalFilePresenter.mImageLoader")
    public static void injectMImageLoader(Ops9_LocalFilePresenter ops9_LocalFilePresenter, ImageLoader imageLoader) {
        ops9_LocalFilePresenter.mImageLoader = imageLoader;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(Ops9_LocalFilePresenter ops9_LocalFilePresenter) {
        injectMErrorHandler(ops9_LocalFilePresenter, this.mErrorHandlerProvider.get());
        injectMApplication(ops9_LocalFilePresenter, this.mApplicationProvider.get());
        injectMImageLoader(ops9_LocalFilePresenter, this.mImageLoaderProvider.get());
        injectMAppManager(ops9_LocalFilePresenter, this.mAppManagerProvider.get());
    }
}
