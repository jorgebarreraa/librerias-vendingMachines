package com.yj.coffeemachines.mvp.presenter;

import android.app.Application;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.integration.AppManager;
import com.yj.coffeemachines.mvp.contract.Ops9_LocalFileContract;
import dagger.internal.Factory;
import javax.inject.Provider;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;

/* loaded from: classes.dex */
public final class Ops9_LocalFilePresenter_Factory implements Factory<Ops9_LocalFilePresenter> {
    private final Provider<AppManager> mAppManagerProvider;
    private final Provider<Application> mApplicationProvider;
    private final Provider<RxErrorHandler> mErrorHandlerProvider;
    private final Provider<ImageLoader> mImageLoaderProvider;
    private final Provider<Ops9_LocalFileContract.Model> modelProvider;
    private final Provider<Ops9_LocalFileContract.View> rootViewProvider;

    public Ops9_LocalFilePresenter_Factory(Provider<Ops9_LocalFileContract.Model> provider, Provider<Ops9_LocalFileContract.View> provider2, Provider<RxErrorHandler> provider3, Provider<Application> provider4, Provider<ImageLoader> provider5, Provider<AppManager> provider6) {
        this.modelProvider = provider;
        this.rootViewProvider = provider2;
        this.mErrorHandlerProvider = provider3;
        this.mApplicationProvider = provider4;
        this.mImageLoaderProvider = provider5;
        this.mAppManagerProvider = provider6;
    }

    public static Ops9_LocalFilePresenter_Factory create(Provider<Ops9_LocalFileContract.Model> provider, Provider<Ops9_LocalFileContract.View> provider2, Provider<RxErrorHandler> provider3, Provider<Application> provider4, Provider<ImageLoader> provider5, Provider<AppManager> provider6) {
        return new Ops9_LocalFilePresenter_Factory(provider, provider2, provider3, provider4, provider5, provider6);
    }

    public static Ops9_LocalFilePresenter newInstance(Ops9_LocalFileContract.Model model, Ops9_LocalFileContract.View view) {
        return new Ops9_LocalFilePresenter(model, view);
    }

    @Override // javax.inject.Provider
    public Ops9_LocalFilePresenter get() {
        Ops9_LocalFilePresenter newInstance = newInstance(this.modelProvider.get(), this.rootViewProvider.get());
        Ops9_LocalFilePresenter_MembersInjector.injectMErrorHandler(newInstance, this.mErrorHandlerProvider.get());
        Ops9_LocalFilePresenter_MembersInjector.injectMApplication(newInstance, this.mApplicationProvider.get());
        Ops9_LocalFilePresenter_MembersInjector.injectMImageLoader(newInstance, this.mImageLoaderProvider.get());
        Ops9_LocalFilePresenter_MembersInjector.injectMAppManager(newInstance, this.mAppManagerProvider.get());
        return newInstance;
    }
}
