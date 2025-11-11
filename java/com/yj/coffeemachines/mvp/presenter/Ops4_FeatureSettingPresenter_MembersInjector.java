package com.yj.coffeemachines.mvp.presenter;

import android.app.Application;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.integration.AppManager;
import dagger.MembersInjector;
import dagger.internal.InjectedFieldSignature;
import javax.inject.Provider;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;

/* loaded from: classes.dex */
public final class Ops4_FeatureSettingPresenter_MembersInjector implements MembersInjector<Ops4_FeatureSettingPresenter> {
    private final Provider<AppManager> mAppManagerProvider;
    private final Provider<Application> mApplicationProvider;
    private final Provider<RxErrorHandler> mErrorHandlerProvider;
    private final Provider<ImageLoader> mImageLoaderProvider;

    public Ops4_FeatureSettingPresenter_MembersInjector(Provider<RxErrorHandler> provider, Provider<Application> provider2, Provider<ImageLoader> provider3, Provider<AppManager> provider4) {
        this.mErrorHandlerProvider = provider;
        this.mApplicationProvider = provider2;
        this.mImageLoaderProvider = provider3;
        this.mAppManagerProvider = provider4;
    }

    public static MembersInjector<Ops4_FeatureSettingPresenter> create(Provider<RxErrorHandler> provider, Provider<Application> provider2, Provider<ImageLoader> provider3, Provider<AppManager> provider4) {
        return new Ops4_FeatureSettingPresenter_MembersInjector(provider, provider2, provider3, provider4);
    }

    @InjectedFieldSignature("com.yj.coffeemachines.mvp.presenter.Ops4_FeatureSettingPresenter.mAppManager")
    public static void injectMAppManager(Ops4_FeatureSettingPresenter ops4_FeatureSettingPresenter, AppManager appManager) {
        ops4_FeatureSettingPresenter.mAppManager = appManager;
    }

    @InjectedFieldSignature("com.yj.coffeemachines.mvp.presenter.Ops4_FeatureSettingPresenter.mApplication")
    public static void injectMApplication(Ops4_FeatureSettingPresenter ops4_FeatureSettingPresenter, Application application) {
        ops4_FeatureSettingPresenter.mApplication = application;
    }

    @InjectedFieldSignature("com.yj.coffeemachines.mvp.presenter.Ops4_FeatureSettingPresenter.mErrorHandler")
    public static void injectMErrorHandler(Ops4_FeatureSettingPresenter ops4_FeatureSettingPresenter, RxErrorHandler rxErrorHandler) {
        ops4_FeatureSettingPresenter.mErrorHandler = rxErrorHandler;
    }

    @InjectedFieldSignature("com.yj.coffeemachines.mvp.presenter.Ops4_FeatureSettingPresenter.mImageLoader")
    public static void injectMImageLoader(Ops4_FeatureSettingPresenter ops4_FeatureSettingPresenter, ImageLoader imageLoader) {
        ops4_FeatureSettingPresenter.mImageLoader = imageLoader;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(Ops4_FeatureSettingPresenter ops4_FeatureSettingPresenter) {
        injectMErrorHandler(ops4_FeatureSettingPresenter, this.mErrorHandlerProvider.get());
        injectMApplication(ops4_FeatureSettingPresenter, this.mApplicationProvider.get());
        injectMImageLoader(ops4_FeatureSettingPresenter, this.mImageLoaderProvider.get());
        injectMAppManager(ops4_FeatureSettingPresenter, this.mAppManagerProvider.get());
    }
}
