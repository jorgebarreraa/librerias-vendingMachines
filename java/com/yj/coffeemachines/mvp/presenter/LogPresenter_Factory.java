package com.yj.coffeemachines.mvp.presenter;

import android.app.Application;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.integration.AppManager;
import com.yj.coffeemachines.mvp.contract.LogContract;
import dagger.internal.Factory;
import javax.inject.Provider;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;

/* loaded from: classes.dex */
public final class LogPresenter_Factory implements Factory<LogPresenter> {
    private final Provider<AppManager> mAppManagerProvider;
    private final Provider<Application> mApplicationProvider;
    private final Provider<RxErrorHandler> mErrorHandlerProvider;
    private final Provider<ImageLoader> mImageLoaderProvider;
    private final Provider<LogContract.Model> modelProvider;
    private final Provider<LogContract.View> rootViewProvider;

    public LogPresenter_Factory(Provider<LogContract.Model> provider, Provider<LogContract.View> provider2, Provider<RxErrorHandler> provider3, Provider<Application> provider4, Provider<ImageLoader> provider5, Provider<AppManager> provider6) {
        this.modelProvider = provider;
        this.rootViewProvider = provider2;
        this.mErrorHandlerProvider = provider3;
        this.mApplicationProvider = provider4;
        this.mImageLoaderProvider = provider5;
        this.mAppManagerProvider = provider6;
    }

    public static LogPresenter_Factory create(Provider<LogContract.Model> provider, Provider<LogContract.View> provider2, Provider<RxErrorHandler> provider3, Provider<Application> provider4, Provider<ImageLoader> provider5, Provider<AppManager> provider6) {
        return new LogPresenter_Factory(provider, provider2, provider3, provider4, provider5, provider6);
    }

    public static LogPresenter newInstance(LogContract.Model model, LogContract.View view) {
        return new LogPresenter(model, view);
    }

    @Override // javax.inject.Provider
    public LogPresenter get() {
        LogPresenter newInstance = newInstance(this.modelProvider.get(), this.rootViewProvider.get());
        LogPresenter_MembersInjector.injectMErrorHandler(newInstance, this.mErrorHandlerProvider.get());
        LogPresenter_MembersInjector.injectMApplication(newInstance, this.mApplicationProvider.get());
        LogPresenter_MembersInjector.injectMImageLoader(newInstance, this.mImageLoaderProvider.get());
        LogPresenter_MembersInjector.injectMAppManager(newInstance, this.mAppManagerProvider.get());
        return newInstance;
    }
}
