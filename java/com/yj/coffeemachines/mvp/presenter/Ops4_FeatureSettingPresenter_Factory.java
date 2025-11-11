package com.yj.coffeemachines.mvp.presenter;

import android.app.Application;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.integration.AppManager;
import com.yj.coffeemachines.mvp.contract.Ops4_FeatureSettingContract;
import dagger.internal.Factory;
import javax.inject.Provider;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;

/* loaded from: classes.dex */
public final class Ops4_FeatureSettingPresenter_Factory implements Factory<Ops4_FeatureSettingPresenter> {
    private final Provider<AppManager> mAppManagerProvider;
    private final Provider<Application> mApplicationProvider;
    private final Provider<RxErrorHandler> mErrorHandlerProvider;
    private final Provider<ImageLoader> mImageLoaderProvider;
    private final Provider<Ops4_FeatureSettingContract.Model> modelProvider;
    private final Provider<Ops4_FeatureSettingContract.View> rootViewProvider;

    public Ops4_FeatureSettingPresenter_Factory(Provider<Ops4_FeatureSettingContract.Model> provider, Provider<Ops4_FeatureSettingContract.View> provider2, Provider<RxErrorHandler> provider3, Provider<Application> provider4, Provider<ImageLoader> provider5, Provider<AppManager> provider6) {
        this.modelProvider = provider;
        this.rootViewProvider = provider2;
        this.mErrorHandlerProvider = provider3;
        this.mApplicationProvider = provider4;
        this.mImageLoaderProvider = provider5;
        this.mAppManagerProvider = provider6;
    }

    public static Ops4_FeatureSettingPresenter_Factory create(Provider<Ops4_FeatureSettingContract.Model> provider, Provider<Ops4_FeatureSettingContract.View> provider2, Provider<RxErrorHandler> provider3, Provider<Application> provider4, Provider<ImageLoader> provider5, Provider<AppManager> provider6) {
        return new Ops4_FeatureSettingPresenter_Factory(provider, provider2, provider3, provider4, provider5, provider6);
    }

    public static Ops4_FeatureSettingPresenter newInstance(Ops4_FeatureSettingContract.Model model, Ops4_FeatureSettingContract.View view) {
        return new Ops4_FeatureSettingPresenter(model, view);
    }

    @Override // javax.inject.Provider
    public Ops4_FeatureSettingPresenter get() {
        Ops4_FeatureSettingPresenter newInstance = newInstance(this.modelProvider.get(), this.rootViewProvider.get());
        Ops4_FeatureSettingPresenter_MembersInjector.injectMErrorHandler(newInstance, this.mErrorHandlerProvider.get());
        Ops4_FeatureSettingPresenter_MembersInjector.injectMApplication(newInstance, this.mApplicationProvider.get());
        Ops4_FeatureSettingPresenter_MembersInjector.injectMImageLoader(newInstance, this.mImageLoaderProvider.get());
        Ops4_FeatureSettingPresenter_MembersInjector.injectMAppManager(newInstance, this.mAppManagerProvider.get());
        return newInstance;
    }
}
