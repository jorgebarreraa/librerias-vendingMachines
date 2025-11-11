package com.yj.coffeemachines.mvp.presenter;

import android.app.Application;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.integration.AppManager;
import com.yj.coffeemachines.mvp.contract.Ops2_RawMaterialSetingContract;
import dagger.internal.Factory;
import javax.inject.Provider;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;

/* loaded from: classes.dex */
public final class Ops2_RawMaterialSetingPresenter_Factory implements Factory<Ops2_RawMaterialSetingPresenter> {
    private final Provider<AppManager> mAppManagerProvider;
    private final Provider<Application> mApplicationProvider;
    private final Provider<RxErrorHandler> mErrorHandlerProvider;
    private final Provider<ImageLoader> mImageLoaderProvider;
    private final Provider<Ops2_RawMaterialSetingContract.Model> modelProvider;
    private final Provider<Ops2_RawMaterialSetingContract.View> rootViewProvider;

    public Ops2_RawMaterialSetingPresenter_Factory(Provider<Ops2_RawMaterialSetingContract.Model> provider, Provider<Ops2_RawMaterialSetingContract.View> provider2, Provider<RxErrorHandler> provider3, Provider<Application> provider4, Provider<ImageLoader> provider5, Provider<AppManager> provider6) {
        this.modelProvider = provider;
        this.rootViewProvider = provider2;
        this.mErrorHandlerProvider = provider3;
        this.mApplicationProvider = provider4;
        this.mImageLoaderProvider = provider5;
        this.mAppManagerProvider = provider6;
    }

    public static Ops2_RawMaterialSetingPresenter_Factory create(Provider<Ops2_RawMaterialSetingContract.Model> provider, Provider<Ops2_RawMaterialSetingContract.View> provider2, Provider<RxErrorHandler> provider3, Provider<Application> provider4, Provider<ImageLoader> provider5, Provider<AppManager> provider6) {
        return new Ops2_RawMaterialSetingPresenter_Factory(provider, provider2, provider3, provider4, provider5, provider6);
    }

    public static Ops2_RawMaterialSetingPresenter newInstance(Ops2_RawMaterialSetingContract.Model model, Ops2_RawMaterialSetingContract.View view) {
        return new Ops2_RawMaterialSetingPresenter(model, view);
    }

    @Override // javax.inject.Provider
    public Ops2_RawMaterialSetingPresenter get() {
        Ops2_RawMaterialSetingPresenter newInstance = newInstance(this.modelProvider.get(), this.rootViewProvider.get());
        Ops2_RawMaterialSetingPresenter_MembersInjector.injectMErrorHandler(newInstance, this.mErrorHandlerProvider.get());
        Ops2_RawMaterialSetingPresenter_MembersInjector.injectMApplication(newInstance, this.mApplicationProvider.get());
        Ops2_RawMaterialSetingPresenter_MembersInjector.injectMImageLoader(newInstance, this.mImageLoaderProvider.get());
        Ops2_RawMaterialSetingPresenter_MembersInjector.injectMAppManager(newInstance, this.mAppManagerProvider.get());
        return newInstance;
    }
}
