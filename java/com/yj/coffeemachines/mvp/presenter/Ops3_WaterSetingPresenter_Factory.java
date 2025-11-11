package com.yj.coffeemachines.mvp.presenter;

import android.app.Application;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.integration.AppManager;
import com.yj.coffeemachines.mvp.contract.Ops3_WaterSetingContract;
import dagger.internal.Factory;
import javax.inject.Provider;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;

/* loaded from: classes.dex */
public final class Ops3_WaterSetingPresenter_Factory implements Factory<Ops3_WaterSetingPresenter> {
    private final Provider<AppManager> mAppManagerProvider;
    private final Provider<Application> mApplicationProvider;
    private final Provider<RxErrorHandler> mErrorHandlerProvider;
    private final Provider<ImageLoader> mImageLoaderProvider;
    private final Provider<Ops3_WaterSetingContract.Model> modelProvider;
    private final Provider<Ops3_WaterSetingContract.View> rootViewProvider;

    public Ops3_WaterSetingPresenter_Factory(Provider<Ops3_WaterSetingContract.Model> provider, Provider<Ops3_WaterSetingContract.View> provider2, Provider<RxErrorHandler> provider3, Provider<Application> provider4, Provider<ImageLoader> provider5, Provider<AppManager> provider6) {
        this.modelProvider = provider;
        this.rootViewProvider = provider2;
        this.mErrorHandlerProvider = provider3;
        this.mApplicationProvider = provider4;
        this.mImageLoaderProvider = provider5;
        this.mAppManagerProvider = provider6;
    }

    public static Ops3_WaterSetingPresenter_Factory create(Provider<Ops3_WaterSetingContract.Model> provider, Provider<Ops3_WaterSetingContract.View> provider2, Provider<RxErrorHandler> provider3, Provider<Application> provider4, Provider<ImageLoader> provider5, Provider<AppManager> provider6) {
        return new Ops3_WaterSetingPresenter_Factory(provider, provider2, provider3, provider4, provider5, provider6);
    }

    public static Ops3_WaterSetingPresenter newInstance(Ops3_WaterSetingContract.Model model, Ops3_WaterSetingContract.View view) {
        return new Ops3_WaterSetingPresenter(model, view);
    }

    @Override // javax.inject.Provider
    public Ops3_WaterSetingPresenter get() {
        Ops3_WaterSetingPresenter newInstance = newInstance(this.modelProvider.get(), this.rootViewProvider.get());
        Ops3_WaterSetingPresenter_MembersInjector.injectMErrorHandler(newInstance, this.mErrorHandlerProvider.get());
        Ops3_WaterSetingPresenter_MembersInjector.injectMApplication(newInstance, this.mApplicationProvider.get());
        Ops3_WaterSetingPresenter_MembersInjector.injectMImageLoader(newInstance, this.mImageLoaderProvider.get());
        Ops3_WaterSetingPresenter_MembersInjector.injectMAppManager(newInstance, this.mAppManagerProvider.get());
        return newInstance;
    }
}
