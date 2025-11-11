package com.yj.coffeemachines.mvp.presenter;

import android.app.Application;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.integration.AppManager;
import com.yj.coffeemachines.mvp.contract.Step2Contract;
import dagger.internal.Factory;
import javax.inject.Provider;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;

/* loaded from: classes.dex */
public final class Step2Presenter_Factory implements Factory<Step2Presenter> {
    private final Provider<AppManager> mAppManagerProvider;
    private final Provider<Application> mApplicationProvider;
    private final Provider<RxErrorHandler> mErrorHandlerProvider;
    private final Provider<ImageLoader> mImageLoaderProvider;
    private final Provider<Step2Contract.Model> modelProvider;
    private final Provider<Step2Contract.View> rootViewProvider;

    public Step2Presenter_Factory(Provider<Step2Contract.Model> provider, Provider<Step2Contract.View> provider2, Provider<RxErrorHandler> provider3, Provider<Application> provider4, Provider<ImageLoader> provider5, Provider<AppManager> provider6) {
        this.modelProvider = provider;
        this.rootViewProvider = provider2;
        this.mErrorHandlerProvider = provider3;
        this.mApplicationProvider = provider4;
        this.mImageLoaderProvider = provider5;
        this.mAppManagerProvider = provider6;
    }

    public static Step2Presenter_Factory create(Provider<Step2Contract.Model> provider, Provider<Step2Contract.View> provider2, Provider<RxErrorHandler> provider3, Provider<Application> provider4, Provider<ImageLoader> provider5, Provider<AppManager> provider6) {
        return new Step2Presenter_Factory(provider, provider2, provider3, provider4, provider5, provider6);
    }

    public static Step2Presenter newInstance(Step2Contract.Model model, Step2Contract.View view) {
        return new Step2Presenter(model, view);
    }

    @Override // javax.inject.Provider
    public Step2Presenter get() {
        Step2Presenter newInstance = newInstance(this.modelProvider.get(), this.rootViewProvider.get());
        Step2Presenter_MembersInjector.injectMErrorHandler(newInstance, this.mErrorHandlerProvider.get());
        Step2Presenter_MembersInjector.injectMApplication(newInstance, this.mApplicationProvider.get());
        Step2Presenter_MembersInjector.injectMImageLoader(newInstance, this.mImageLoaderProvider.get());
        Step2Presenter_MembersInjector.injectMAppManager(newInstance, this.mAppManagerProvider.get());
        return newInstance;
    }
}
