package com.yj.coffeemachines.mvp.presenter;

import android.app.Application;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.integration.AppManager;
import com.yj.coffeemachines.mvp.contract.FactoryContract;
import dagger.internal.Factory;
import javax.inject.Provider;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;

/* loaded from: classes.dex */
public final class FactoryPresenter_Factory implements Factory<FactoryPresenter> {
    private final Provider<AppManager> mAppManagerProvider;
    private final Provider<Application> mApplicationProvider;
    private final Provider<RxErrorHandler> mErrorHandlerProvider;
    private final Provider<ImageLoader> mImageLoaderProvider;
    private final Provider<FactoryContract.Model> modelProvider;
    private final Provider<FactoryContract.View> rootViewProvider;

    public FactoryPresenter_Factory(Provider<FactoryContract.Model> provider, Provider<FactoryContract.View> provider2, Provider<RxErrorHandler> provider3, Provider<Application> provider4, Provider<ImageLoader> provider5, Provider<AppManager> provider6) {
        this.modelProvider = provider;
        this.rootViewProvider = provider2;
        this.mErrorHandlerProvider = provider3;
        this.mApplicationProvider = provider4;
        this.mImageLoaderProvider = provider5;
        this.mAppManagerProvider = provider6;
    }

    public static FactoryPresenter_Factory create(Provider<FactoryContract.Model> provider, Provider<FactoryContract.View> provider2, Provider<RxErrorHandler> provider3, Provider<Application> provider4, Provider<ImageLoader> provider5, Provider<AppManager> provider6) {
        return new FactoryPresenter_Factory(provider, provider2, provider3, provider4, provider5, provider6);
    }

    public static FactoryPresenter newInstance(FactoryContract.Model model, FactoryContract.View view) {
        return new FactoryPresenter(model, view);
    }

    @Override // javax.inject.Provider
    public FactoryPresenter get() {
        FactoryPresenter newInstance = newInstance(this.modelProvider.get(), this.rootViewProvider.get());
        FactoryPresenter_MembersInjector.injectMErrorHandler(newInstance, this.mErrorHandlerProvider.get());
        FactoryPresenter_MembersInjector.injectMApplication(newInstance, this.mApplicationProvider.get());
        FactoryPresenter_MembersInjector.injectMImageLoader(newInstance, this.mImageLoaderProvider.get());
        FactoryPresenter_MembersInjector.injectMAppManager(newInstance, this.mAppManagerProvider.get());
        return newInstance;
    }
}
