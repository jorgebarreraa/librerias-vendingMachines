package com.yj.coffeemachines.mvp.presenter;

import android.app.Application;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.integration.AppManager;
import com.yj.coffeemachines.mvp.contract.HomeContract;
import dagger.internal.Factory;
import javax.inject.Provider;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;

/* loaded from: classes.dex */
public final class HomePresenter_Factory implements Factory<HomePresenter> {
    private final Provider<AppManager> mAppManagerProvider;
    private final Provider<Application> mApplicationProvider;
    private final Provider<RxErrorHandler> mErrorHandlerProvider;
    private final Provider<ImageLoader> mImageLoaderProvider;
    private final Provider<HomeContract.Model> modelProvider;
    private final Provider<HomeContract.View> rootViewProvider;

    public HomePresenter_Factory(Provider<HomeContract.Model> provider, Provider<HomeContract.View> provider2, Provider<RxErrorHandler> provider3, Provider<Application> provider4, Provider<ImageLoader> provider5, Provider<AppManager> provider6) {
        this.modelProvider = provider;
        this.rootViewProvider = provider2;
        this.mErrorHandlerProvider = provider3;
        this.mApplicationProvider = provider4;
        this.mImageLoaderProvider = provider5;
        this.mAppManagerProvider = provider6;
    }

    public static HomePresenter_Factory create(Provider<HomeContract.Model> provider, Provider<HomeContract.View> provider2, Provider<RxErrorHandler> provider3, Provider<Application> provider4, Provider<ImageLoader> provider5, Provider<AppManager> provider6) {
        return new HomePresenter_Factory(provider, provider2, provider3, provider4, provider5, provider6);
    }

    public static HomePresenter newInstance(HomeContract.Model model, HomeContract.View view) {
        return new HomePresenter(model, view);
    }

    @Override // javax.inject.Provider
    public HomePresenter get() {
        HomePresenter newInstance = newInstance(this.modelProvider.get(), this.rootViewProvider.get());
        HomePresenter_MembersInjector.injectMErrorHandler(newInstance, this.mErrorHandlerProvider.get());
        HomePresenter_MembersInjector.injectMApplication(newInstance, this.mApplicationProvider.get());
        HomePresenter_MembersInjector.injectMImageLoader(newInstance, this.mImageLoaderProvider.get());
        HomePresenter_MembersInjector.injectMAppManager(newInstance, this.mAppManagerProvider.get());
        return newInstance;
    }
}
