package com.yj.coffeemachines.mvp.presenter;

import android.app.Application;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.integration.AppManager;
import com.yj.coffeemachines.mvp.contract.Ops7_AppUpDataContract;
import dagger.internal.Factory;
import javax.inject.Provider;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;

/* loaded from: classes.dex */
public final class Ops7_AppUpDataPresenter_Factory implements Factory<Ops7_AppUpDataPresenter> {
    private final Provider<AppManager> mAppManagerProvider;
    private final Provider<Application> mApplicationProvider;
    private final Provider<RxErrorHandler> mErrorHandlerProvider;
    private final Provider<ImageLoader> mImageLoaderProvider;
    private final Provider<Ops7_AppUpDataContract.Model> modelProvider;
    private final Provider<Ops7_AppUpDataContract.View> rootViewProvider;

    public Ops7_AppUpDataPresenter_Factory(Provider<Ops7_AppUpDataContract.Model> provider, Provider<Ops7_AppUpDataContract.View> provider2, Provider<RxErrorHandler> provider3, Provider<Application> provider4, Provider<ImageLoader> provider5, Provider<AppManager> provider6) {
        this.modelProvider = provider;
        this.rootViewProvider = provider2;
        this.mErrorHandlerProvider = provider3;
        this.mApplicationProvider = provider4;
        this.mImageLoaderProvider = provider5;
        this.mAppManagerProvider = provider6;
    }

    public static Ops7_AppUpDataPresenter_Factory create(Provider<Ops7_AppUpDataContract.Model> provider, Provider<Ops7_AppUpDataContract.View> provider2, Provider<RxErrorHandler> provider3, Provider<Application> provider4, Provider<ImageLoader> provider5, Provider<AppManager> provider6) {
        return new Ops7_AppUpDataPresenter_Factory(provider, provider2, provider3, provider4, provider5, provider6);
    }

    public static Ops7_AppUpDataPresenter newInstance(Ops7_AppUpDataContract.Model model, Ops7_AppUpDataContract.View view) {
        return new Ops7_AppUpDataPresenter(model, view);
    }

    @Override // javax.inject.Provider
    public Ops7_AppUpDataPresenter get() {
        Ops7_AppUpDataPresenter newInstance = newInstance(this.modelProvider.get(), this.rootViewProvider.get());
        Ops7_AppUpDataPresenter_MembersInjector.injectMErrorHandler(newInstance, this.mErrorHandlerProvider.get());
        Ops7_AppUpDataPresenter_MembersInjector.injectMApplication(newInstance, this.mApplicationProvider.get());
        Ops7_AppUpDataPresenter_MembersInjector.injectMImageLoader(newInstance, this.mImageLoaderProvider.get());
        Ops7_AppUpDataPresenter_MembersInjector.injectMAppManager(newInstance, this.mAppManagerProvider.get());
        return newInstance;
    }
}
