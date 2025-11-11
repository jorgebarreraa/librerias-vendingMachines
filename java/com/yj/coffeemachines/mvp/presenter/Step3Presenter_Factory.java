package com.yj.coffeemachines.mvp.presenter;

import android.app.Application;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.integration.AppManager;
import com.yj.coffeemachines.mvp.contract.Step3Contract;
import dagger.internal.Factory;
import javax.inject.Provider;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;

/* loaded from: classes.dex */
public final class Step3Presenter_Factory implements Factory<Step3Presenter> {
    private final Provider<AppManager> mAppManagerProvider;
    private final Provider<Application> mApplicationProvider;
    private final Provider<RxErrorHandler> mErrorHandlerProvider;
    private final Provider<ImageLoader> mImageLoaderProvider;
    private final Provider<Step3Contract.Model> modelProvider;
    private final Provider<Step3Contract.View> rootViewProvider;

    public Step3Presenter_Factory(Provider<Step3Contract.Model> provider, Provider<Step3Contract.View> provider2, Provider<RxErrorHandler> provider3, Provider<Application> provider4, Provider<ImageLoader> provider5, Provider<AppManager> provider6) {
        this.modelProvider = provider;
        this.rootViewProvider = provider2;
        this.mErrorHandlerProvider = provider3;
        this.mApplicationProvider = provider4;
        this.mImageLoaderProvider = provider5;
        this.mAppManagerProvider = provider6;
    }

    public static Step3Presenter_Factory create(Provider<Step3Contract.Model> provider, Provider<Step3Contract.View> provider2, Provider<RxErrorHandler> provider3, Provider<Application> provider4, Provider<ImageLoader> provider5, Provider<AppManager> provider6) {
        return new Step3Presenter_Factory(provider, provider2, provider3, provider4, provider5, provider6);
    }

    public static Step3Presenter newInstance(Step3Contract.Model model, Step3Contract.View view) {
        return new Step3Presenter(model, view);
    }

    @Override // javax.inject.Provider
    public Step3Presenter get() {
        Step3Presenter newInstance = newInstance(this.modelProvider.get(), this.rootViewProvider.get());
        Step3Presenter_MembersInjector.injectMErrorHandler(newInstance, this.mErrorHandlerProvider.get());
        Step3Presenter_MembersInjector.injectMApplication(newInstance, this.mApplicationProvider.get());
        Step3Presenter_MembersInjector.injectMImageLoader(newInstance, this.mImageLoaderProvider.get());
        Step3Presenter_MembersInjector.injectMAppManager(newInstance, this.mAppManagerProvider.get());
        return newInstance;
    }
}
