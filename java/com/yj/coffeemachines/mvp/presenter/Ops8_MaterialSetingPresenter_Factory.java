package com.yj.coffeemachines.mvp.presenter;

import android.app.Application;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.integration.AppManager;
import com.yj.coffeemachines.mvp.contract.Ops8_MaterialSetingContract;
import dagger.internal.Factory;
import javax.inject.Provider;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;

/* loaded from: classes.dex */
public final class Ops8_MaterialSetingPresenter_Factory implements Factory<Ops8_MaterialSetingPresenter> {
    private final Provider<AppManager> mAppManagerProvider;
    private final Provider<Application> mApplicationProvider;
    private final Provider<RxErrorHandler> mErrorHandlerProvider;
    private final Provider<ImageLoader> mImageLoaderProvider;
    private final Provider<Ops8_MaterialSetingContract.Model> modelProvider;
    private final Provider<Ops8_MaterialSetingContract.View> rootViewProvider;

    public Ops8_MaterialSetingPresenter_Factory(Provider<Ops8_MaterialSetingContract.Model> provider, Provider<Ops8_MaterialSetingContract.View> provider2, Provider<RxErrorHandler> provider3, Provider<Application> provider4, Provider<ImageLoader> provider5, Provider<AppManager> provider6) {
        this.modelProvider = provider;
        this.rootViewProvider = provider2;
        this.mErrorHandlerProvider = provider3;
        this.mApplicationProvider = provider4;
        this.mImageLoaderProvider = provider5;
        this.mAppManagerProvider = provider6;
    }

    public static Ops8_MaterialSetingPresenter_Factory create(Provider<Ops8_MaterialSetingContract.Model> provider, Provider<Ops8_MaterialSetingContract.View> provider2, Provider<RxErrorHandler> provider3, Provider<Application> provider4, Provider<ImageLoader> provider5, Provider<AppManager> provider6) {
        return new Ops8_MaterialSetingPresenter_Factory(provider, provider2, provider3, provider4, provider5, provider6);
    }

    public static Ops8_MaterialSetingPresenter newInstance(Ops8_MaterialSetingContract.Model model, Ops8_MaterialSetingContract.View view) {
        return new Ops8_MaterialSetingPresenter(model, view);
    }

    @Override // javax.inject.Provider
    public Ops8_MaterialSetingPresenter get() {
        Ops8_MaterialSetingPresenter newInstance = newInstance(this.modelProvider.get(), this.rootViewProvider.get());
        Ops8_MaterialSetingPresenter_MembersInjector.injectMErrorHandler(newInstance, this.mErrorHandlerProvider.get());
        Ops8_MaterialSetingPresenter_MembersInjector.injectMApplication(newInstance, this.mApplicationProvider.get());
        Ops8_MaterialSetingPresenter_MembersInjector.injectMImageLoader(newInstance, this.mImageLoaderProvider.get());
        Ops8_MaterialSetingPresenter_MembersInjector.injectMAppManager(newInstance, this.mAppManagerProvider.get());
        return newInstance;
    }
}
