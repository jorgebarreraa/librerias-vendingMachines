package com.yj.coffeemachines.mvp.presenter;

import android.app.Application;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.integration.AppManager;
import dagger.MembersInjector;
import dagger.internal.InjectedFieldSignature;
import javax.inject.Provider;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;

/* loaded from: classes.dex */
public final class SetingsHomePresenter_MembersInjector implements MembersInjector<SetingsHomePresenter> {
    private final Provider<AppManager> mAppManagerProvider;
    private final Provider<Application> mApplicationProvider;
    private final Provider<RxErrorHandler> mErrorHandlerProvider;
    private final Provider<ImageLoader> mImageLoaderProvider;

    public SetingsHomePresenter_MembersInjector(Provider<RxErrorHandler> provider, Provider<Application> provider2, Provider<ImageLoader> provider3, Provider<AppManager> provider4) {
        this.mErrorHandlerProvider = provider;
        this.mApplicationProvider = provider2;
        this.mImageLoaderProvider = provider3;
        this.mAppManagerProvider = provider4;
    }

    public static MembersInjector<SetingsHomePresenter> create(Provider<RxErrorHandler> provider, Provider<Application> provider2, Provider<ImageLoader> provider3, Provider<AppManager> provider4) {
        return new SetingsHomePresenter_MembersInjector(provider, provider2, provider3, provider4);
    }

    @InjectedFieldSignature("com.yj.coffeemachines.mvp.presenter.SetingsHomePresenter.mAppManager")
    public static void injectMAppManager(SetingsHomePresenter setingsHomePresenter, AppManager appManager) {
        setingsHomePresenter.mAppManager = appManager;
    }

    @InjectedFieldSignature("com.yj.coffeemachines.mvp.presenter.SetingsHomePresenter.mApplication")
    public static void injectMApplication(SetingsHomePresenter setingsHomePresenter, Application application) {
        setingsHomePresenter.mApplication = application;
    }

    @InjectedFieldSignature("com.yj.coffeemachines.mvp.presenter.SetingsHomePresenter.mErrorHandler")
    public static void injectMErrorHandler(SetingsHomePresenter setingsHomePresenter, RxErrorHandler rxErrorHandler) {
        setingsHomePresenter.mErrorHandler = rxErrorHandler;
    }

    @InjectedFieldSignature("com.yj.coffeemachines.mvp.presenter.SetingsHomePresenter.mImageLoader")
    public static void injectMImageLoader(SetingsHomePresenter setingsHomePresenter, ImageLoader imageLoader) {
        setingsHomePresenter.mImageLoader = imageLoader;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(SetingsHomePresenter setingsHomePresenter) {
        injectMErrorHandler(setingsHomePresenter, this.mErrorHandlerProvider.get());
        injectMApplication(setingsHomePresenter, this.mApplicationProvider.get());
        injectMImageLoader(setingsHomePresenter, this.mImageLoaderProvider.get());
        injectMAppManager(setingsHomePresenter, this.mAppManagerProvider.get());
    }
}
