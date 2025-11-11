package com.yj.coffeemachines.mvp.presenter;

import android.app.Application;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.integration.AppManager;
import com.yj.coffeemachines.mvp.contract.Factory1_DevConfigContract;
import dagger.internal.Factory;
import javax.inject.Provider;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;

/* loaded from: classes.dex */
public final class Factory1_DevConfigPresenter_Factory implements Factory<Factory1_DevConfigPresenter> {
    private final Provider<AppManager> mAppManagerProvider;
    private final Provider<Application> mApplicationProvider;
    private final Provider<RxErrorHandler> mErrorHandlerProvider;
    private final Provider<ImageLoader> mImageLoaderProvider;
    private final Provider<Factory1_DevConfigContract.Model> modelProvider;
    private final Provider<Factory1_DevConfigContract.View> rootViewProvider;

    public Factory1_DevConfigPresenter_Factory(Provider<Factory1_DevConfigContract.Model> provider, Provider<Factory1_DevConfigContract.View> provider2, Provider<RxErrorHandler> provider3, Provider<Application> provider4, Provider<ImageLoader> provider5, Provider<AppManager> provider6) {
        this.modelProvider = provider;
        this.rootViewProvider = provider2;
        this.mErrorHandlerProvider = provider3;
        this.mApplicationProvider = provider4;
        this.mImageLoaderProvider = provider5;
        this.mAppManagerProvider = provider6;
    }

    public static Factory1_DevConfigPresenter_Factory create(Provider<Factory1_DevConfigContract.Model> provider, Provider<Factory1_DevConfigContract.View> provider2, Provider<RxErrorHandler> provider3, Provider<Application> provider4, Provider<ImageLoader> provider5, Provider<AppManager> provider6) {
        return new Factory1_DevConfigPresenter_Factory(provider, provider2, provider3, provider4, provider5, provider6);
    }

    public static Factory1_DevConfigPresenter newInstance(Factory1_DevConfigContract.Model model, Factory1_DevConfigContract.View view) {
        return new Factory1_DevConfigPresenter(model, view);
    }

    @Override // javax.inject.Provider
    public Factory1_DevConfigPresenter get() {
        Factory1_DevConfigPresenter newInstance = newInstance(this.modelProvider.get(), this.rootViewProvider.get());
        Factory1_DevConfigPresenter_MembersInjector.injectMErrorHandler(newInstance, this.mErrorHandlerProvider.get());
        Factory1_DevConfigPresenter_MembersInjector.injectMApplication(newInstance, this.mApplicationProvider.get());
        Factory1_DevConfigPresenter_MembersInjector.injectMImageLoader(newInstance, this.mImageLoaderProvider.get());
        Factory1_DevConfigPresenter_MembersInjector.injectMAppManager(newInstance, this.mAppManagerProvider.get());
        return newInstance;
    }
}
