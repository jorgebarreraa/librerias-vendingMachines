package com.yj.coffeemachines.mvp.presenter;

import android.app.Application;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.integration.AppManager;
import com.yj.coffeemachines.mvp.contract.Factory2_SeralPortConfigContract;
import dagger.internal.Factory;
import javax.inject.Provider;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;

/* loaded from: classes.dex */
public final class Factory2_SeralPortConfigPresenter_Factory implements Factory<Factory2_SeralPortConfigPresenter> {
    private final Provider<AppManager> mAppManagerProvider;
    private final Provider<Application> mApplicationProvider;
    private final Provider<RxErrorHandler> mErrorHandlerProvider;
    private final Provider<ImageLoader> mImageLoaderProvider;
    private final Provider<Factory2_SeralPortConfigContract.Model> modelProvider;
    private final Provider<Factory2_SeralPortConfigContract.View> rootViewProvider;

    public Factory2_SeralPortConfigPresenter_Factory(Provider<Factory2_SeralPortConfigContract.Model> provider, Provider<Factory2_SeralPortConfigContract.View> provider2, Provider<RxErrorHandler> provider3, Provider<Application> provider4, Provider<ImageLoader> provider5, Provider<AppManager> provider6) {
        this.modelProvider = provider;
        this.rootViewProvider = provider2;
        this.mErrorHandlerProvider = provider3;
        this.mApplicationProvider = provider4;
        this.mImageLoaderProvider = provider5;
        this.mAppManagerProvider = provider6;
    }

    public static Factory2_SeralPortConfigPresenter_Factory create(Provider<Factory2_SeralPortConfigContract.Model> provider, Provider<Factory2_SeralPortConfigContract.View> provider2, Provider<RxErrorHandler> provider3, Provider<Application> provider4, Provider<ImageLoader> provider5, Provider<AppManager> provider6) {
        return new Factory2_SeralPortConfigPresenter_Factory(provider, provider2, provider3, provider4, provider5, provider6);
    }

    public static Factory2_SeralPortConfigPresenter newInstance(Factory2_SeralPortConfigContract.Model model, Factory2_SeralPortConfigContract.View view) {
        return new Factory2_SeralPortConfigPresenter(model, view);
    }

    @Override // javax.inject.Provider
    public Factory2_SeralPortConfigPresenter get() {
        Factory2_SeralPortConfigPresenter newInstance = newInstance(this.modelProvider.get(), this.rootViewProvider.get());
        Factory2_SeralPortConfigPresenter_MembersInjector.injectMErrorHandler(newInstance, this.mErrorHandlerProvider.get());
        Factory2_SeralPortConfigPresenter_MembersInjector.injectMApplication(newInstance, this.mApplicationProvider.get());
        Factory2_SeralPortConfigPresenter_MembersInjector.injectMImageLoader(newInstance, this.mImageLoaderProvider.get());
        Factory2_SeralPortConfigPresenter_MembersInjector.injectMAppManager(newInstance, this.mAppManagerProvider.get());
        return newInstance;
    }
}
