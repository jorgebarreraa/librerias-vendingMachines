package com.yj.coffeemachines.mvp.presenter;

import android.app.Application;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.integration.AppManager;
import com.yj.coffeemachines.mvp.contract.DIYContract;
import dagger.internal.Factory;
import javax.inject.Provider;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;

/* loaded from: classes.dex */
public final class DIYPresenter_Factory implements Factory<DIYPresenter> {
    private final Provider<AppManager> mAppManagerProvider;
    private final Provider<Application> mApplicationProvider;
    private final Provider<RxErrorHandler> mErrorHandlerProvider;
    private final Provider<ImageLoader> mImageLoaderProvider;
    private final Provider<DIYContract.Model> modelProvider;
    private final Provider<DIYContract.View> rootViewProvider;

    public DIYPresenter_Factory(Provider<DIYContract.Model> provider, Provider<DIYContract.View> provider2, Provider<RxErrorHandler> provider3, Provider<Application> provider4, Provider<ImageLoader> provider5, Provider<AppManager> provider6) {
        this.modelProvider = provider;
        this.rootViewProvider = provider2;
        this.mErrorHandlerProvider = provider3;
        this.mApplicationProvider = provider4;
        this.mImageLoaderProvider = provider5;
        this.mAppManagerProvider = provider6;
    }

    public static DIYPresenter_Factory create(Provider<DIYContract.Model> provider, Provider<DIYContract.View> provider2, Provider<RxErrorHandler> provider3, Provider<Application> provider4, Provider<ImageLoader> provider5, Provider<AppManager> provider6) {
        return new DIYPresenter_Factory(provider, provider2, provider3, provider4, provider5, provider6);
    }

    public static DIYPresenter newInstance(DIYContract.Model model, DIYContract.View view) {
        return new DIYPresenter(model, view);
    }

    @Override // javax.inject.Provider
    public DIYPresenter get() {
        DIYPresenter newInstance = newInstance(this.modelProvider.get(), this.rootViewProvider.get());
        DIYPresenter_MembersInjector.injectMErrorHandler(newInstance, this.mErrorHandlerProvider.get());
        DIYPresenter_MembersInjector.injectMApplication(newInstance, this.mApplicationProvider.get());
        DIYPresenter_MembersInjector.injectMImageLoader(newInstance, this.mImageLoaderProvider.get());
        DIYPresenter_MembersInjector.injectMAppManager(newInstance, this.mAppManagerProvider.get());
        return newInstance;
    }
}
