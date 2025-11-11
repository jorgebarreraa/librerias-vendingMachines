package com.yj.coffeemachines.mvp.presenter;

import android.app.Application;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.integration.AppManager;
import com.yj.coffeemachines.mvp.contract.Ops1_RawMaterialAddContract;
import dagger.internal.Factory;
import javax.inject.Provider;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;

/* loaded from: classes.dex */
public final class Ops1_RawMaterialAddPresenter_Factory implements Factory<Ops1_RawMaterialAddPresenter> {
    private final Provider<AppManager> mAppManagerProvider;
    private final Provider<Application> mApplicationProvider;
    private final Provider<RxErrorHandler> mErrorHandlerProvider;
    private final Provider<ImageLoader> mImageLoaderProvider;
    private final Provider<Ops1_RawMaterialAddContract.Model> modelProvider;
    private final Provider<Ops1_RawMaterialAddContract.View> rootViewProvider;

    public Ops1_RawMaterialAddPresenter_Factory(Provider<Ops1_RawMaterialAddContract.Model> provider, Provider<Ops1_RawMaterialAddContract.View> provider2, Provider<RxErrorHandler> provider3, Provider<Application> provider4, Provider<ImageLoader> provider5, Provider<AppManager> provider6) {
        this.modelProvider = provider;
        this.rootViewProvider = provider2;
        this.mErrorHandlerProvider = provider3;
        this.mApplicationProvider = provider4;
        this.mImageLoaderProvider = provider5;
        this.mAppManagerProvider = provider6;
    }

    public static Ops1_RawMaterialAddPresenter_Factory create(Provider<Ops1_RawMaterialAddContract.Model> provider, Provider<Ops1_RawMaterialAddContract.View> provider2, Provider<RxErrorHandler> provider3, Provider<Application> provider4, Provider<ImageLoader> provider5, Provider<AppManager> provider6) {
        return new Ops1_RawMaterialAddPresenter_Factory(provider, provider2, provider3, provider4, provider5, provider6);
    }

    public static Ops1_RawMaterialAddPresenter newInstance(Ops1_RawMaterialAddContract.Model model, Ops1_RawMaterialAddContract.View view) {
        return new Ops1_RawMaterialAddPresenter(model, view);
    }

    @Override // javax.inject.Provider
    public Ops1_RawMaterialAddPresenter get() {
        Ops1_RawMaterialAddPresenter newInstance = newInstance(this.modelProvider.get(), this.rootViewProvider.get());
        Ops1_RawMaterialAddPresenter_MembersInjector.injectMErrorHandler(newInstance, this.mErrorHandlerProvider.get());
        Ops1_RawMaterialAddPresenter_MembersInjector.injectMApplication(newInstance, this.mApplicationProvider.get());
        Ops1_RawMaterialAddPresenter_MembersInjector.injectMImageLoader(newInstance, this.mImageLoaderProvider.get());
        Ops1_RawMaterialAddPresenter_MembersInjector.injectMAppManager(newInstance, this.mAppManagerProvider.get());
        return newInstance;
    }
}
