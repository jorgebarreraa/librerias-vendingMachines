package com.yj.coffeemachines.mvp.presenter;

import android.app.Application;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.integration.AppManager;
import com.yj.coffeemachines.mvp.contract.ADContract;
import dagger.internal.Factory;
import javax.inject.Provider;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;

/* loaded from: classes.dex */
public final class ADPresenter_Factory implements Factory<ADPresenter> {
    private final Provider<AppManager> mAppManagerProvider;
    private final Provider<Application> mApplicationProvider;
    private final Provider<RxErrorHandler> mErrorHandlerProvider;
    private final Provider<ImageLoader> mImageLoaderProvider;
    private final Provider<ADContract.Model> modelProvider;
    private final Provider<ADContract.View> rootViewProvider;

    public ADPresenter_Factory(Provider<ADContract.Model> provider, Provider<ADContract.View> provider2, Provider<RxErrorHandler> provider3, Provider<Application> provider4, Provider<ImageLoader> provider5, Provider<AppManager> provider6) {
        this.modelProvider = provider;
        this.rootViewProvider = provider2;
        this.mErrorHandlerProvider = provider3;
        this.mApplicationProvider = provider4;
        this.mImageLoaderProvider = provider5;
        this.mAppManagerProvider = provider6;
    }

    public static ADPresenter_Factory create(Provider<ADContract.Model> provider, Provider<ADContract.View> provider2, Provider<RxErrorHandler> provider3, Provider<Application> provider4, Provider<ImageLoader> provider5, Provider<AppManager> provider6) {
        return new ADPresenter_Factory(provider, provider2, provider3, provider4, provider5, provider6);
    }

    public static ADPresenter newInstance(ADContract.Model model, ADContract.View view) {
        return new ADPresenter(model, view);
    }

    @Override // javax.inject.Provider
    public ADPresenter get() {
        ADPresenter newInstance = newInstance(this.modelProvider.get(), this.rootViewProvider.get());
        ADPresenter_MembersInjector.injectMErrorHandler(newInstance, this.mErrorHandlerProvider.get());
        ADPresenter_MembersInjector.injectMApplication(newInstance, this.mApplicationProvider.get());
        ADPresenter_MembersInjector.injectMImageLoader(newInstance, this.mImageLoaderProvider.get());
        ADPresenter_MembersInjector.injectMAppManager(newInstance, this.mAppManagerProvider.get());
        return newInstance;
    }
}
