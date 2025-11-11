package com.yj.coffeemachines.mvp.presenter;

import android.app.Application;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.integration.AppManager;
import com.yj.coffeemachines.mvp.contract.OpsContract;
import dagger.internal.Factory;
import javax.inject.Provider;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;

/* loaded from: classes.dex */
public final class OpsPresenter_Factory implements Factory<OpsPresenter> {
    private final Provider<AppManager> mAppManagerProvider;
    private final Provider<Application> mApplicationProvider;
    private final Provider<RxErrorHandler> mErrorHandlerProvider;
    private final Provider<ImageLoader> mImageLoaderProvider;
    private final Provider<OpsContract.Model> modelProvider;
    private final Provider<OpsContract.View> rootViewProvider;

    public OpsPresenter_Factory(Provider<OpsContract.Model> provider, Provider<OpsContract.View> provider2, Provider<RxErrorHandler> provider3, Provider<Application> provider4, Provider<ImageLoader> provider5, Provider<AppManager> provider6) {
        this.modelProvider = provider;
        this.rootViewProvider = provider2;
        this.mErrorHandlerProvider = provider3;
        this.mApplicationProvider = provider4;
        this.mImageLoaderProvider = provider5;
        this.mAppManagerProvider = provider6;
    }

    public static OpsPresenter_Factory create(Provider<OpsContract.Model> provider, Provider<OpsContract.View> provider2, Provider<RxErrorHandler> provider3, Provider<Application> provider4, Provider<ImageLoader> provider5, Provider<AppManager> provider6) {
        return new OpsPresenter_Factory(provider, provider2, provider3, provider4, provider5, provider6);
    }

    public static OpsPresenter newInstance(OpsContract.Model model, OpsContract.View view) {
        return new OpsPresenter(model, view);
    }

    @Override // javax.inject.Provider
    public OpsPresenter get() {
        OpsPresenter newInstance = newInstance(this.modelProvider.get(), this.rootViewProvider.get());
        OpsPresenter_MembersInjector.injectMErrorHandler(newInstance, this.mErrorHandlerProvider.get());
        OpsPresenter_MembersInjector.injectMApplication(newInstance, this.mApplicationProvider.get());
        OpsPresenter_MembersInjector.injectMImageLoader(newInstance, this.mImageLoaderProvider.get());
        OpsPresenter_MembersInjector.injectMAppManager(newInstance, this.mAppManagerProvider.get());
        return newInstance;
    }
}
