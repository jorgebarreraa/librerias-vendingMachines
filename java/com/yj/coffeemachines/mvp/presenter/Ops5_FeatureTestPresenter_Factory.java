package com.yj.coffeemachines.mvp.presenter;

import android.app.Application;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.integration.AppManager;
import com.yj.coffeemachines.mvp.contract.Ops5_FeatureTestContract;
import dagger.internal.Factory;
import javax.inject.Provider;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;

/* loaded from: classes.dex */
public final class Ops5_FeatureTestPresenter_Factory implements Factory<Ops5_FeatureTestPresenter> {
    private final Provider<AppManager> mAppManagerProvider;
    private final Provider<Application> mApplicationProvider;
    private final Provider<RxErrorHandler> mErrorHandlerProvider;
    private final Provider<ImageLoader> mImageLoaderProvider;
    private final Provider<Ops5_FeatureTestContract.Model> modelProvider;
    private final Provider<Ops5_FeatureTestContract.View> rootViewProvider;

    public Ops5_FeatureTestPresenter_Factory(Provider<Ops5_FeatureTestContract.Model> provider, Provider<Ops5_FeatureTestContract.View> provider2, Provider<RxErrorHandler> provider3, Provider<Application> provider4, Provider<ImageLoader> provider5, Provider<AppManager> provider6) {
        this.modelProvider = provider;
        this.rootViewProvider = provider2;
        this.mErrorHandlerProvider = provider3;
        this.mApplicationProvider = provider4;
        this.mImageLoaderProvider = provider5;
        this.mAppManagerProvider = provider6;
    }

    public static Ops5_FeatureTestPresenter_Factory create(Provider<Ops5_FeatureTestContract.Model> provider, Provider<Ops5_FeatureTestContract.View> provider2, Provider<RxErrorHandler> provider3, Provider<Application> provider4, Provider<ImageLoader> provider5, Provider<AppManager> provider6) {
        return new Ops5_FeatureTestPresenter_Factory(provider, provider2, provider3, provider4, provider5, provider6);
    }

    public static Ops5_FeatureTestPresenter newInstance(Ops5_FeatureTestContract.Model model, Ops5_FeatureTestContract.View view) {
        return new Ops5_FeatureTestPresenter(model, view);
    }

    @Override // javax.inject.Provider
    public Ops5_FeatureTestPresenter get() {
        Ops5_FeatureTestPresenter newInstance = newInstance(this.modelProvider.get(), this.rootViewProvider.get());
        Ops5_FeatureTestPresenter_MembersInjector.injectMErrorHandler(newInstance, this.mErrorHandlerProvider.get());
        Ops5_FeatureTestPresenter_MembersInjector.injectMApplication(newInstance, this.mApplicationProvider.get());
        Ops5_FeatureTestPresenter_MembersInjector.injectMImageLoader(newInstance, this.mImageLoaderProvider.get());
        Ops5_FeatureTestPresenter_MembersInjector.injectMAppManager(newInstance, this.mAppManagerProvider.get());
        return newInstance;
    }
}
