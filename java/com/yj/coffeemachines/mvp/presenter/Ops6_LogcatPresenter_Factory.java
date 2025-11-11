package com.yj.coffeemachines.mvp.presenter;

import android.app.Application;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.integration.AppManager;
import com.yj.coffeemachines.mvp.contract.Ops6_LogcatContract;
import dagger.internal.Factory;
import javax.inject.Provider;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;

/* loaded from: classes.dex */
public final class Ops6_LogcatPresenter_Factory implements Factory<Ops6_LogcatPresenter> {
    private final Provider<AppManager> mAppManagerProvider;
    private final Provider<Application> mApplicationProvider;
    private final Provider<RxErrorHandler> mErrorHandlerProvider;
    private final Provider<ImageLoader> mImageLoaderProvider;
    private final Provider<Ops6_LogcatContract.Model> modelProvider;
    private final Provider<Ops6_LogcatContract.View> rootViewProvider;

    public Ops6_LogcatPresenter_Factory(Provider<Ops6_LogcatContract.Model> provider, Provider<Ops6_LogcatContract.View> provider2, Provider<RxErrorHandler> provider3, Provider<Application> provider4, Provider<ImageLoader> provider5, Provider<AppManager> provider6) {
        this.modelProvider = provider;
        this.rootViewProvider = provider2;
        this.mErrorHandlerProvider = provider3;
        this.mApplicationProvider = provider4;
        this.mImageLoaderProvider = provider5;
        this.mAppManagerProvider = provider6;
    }

    public static Ops6_LogcatPresenter_Factory create(Provider<Ops6_LogcatContract.Model> provider, Provider<Ops6_LogcatContract.View> provider2, Provider<RxErrorHandler> provider3, Provider<Application> provider4, Provider<ImageLoader> provider5, Provider<AppManager> provider6) {
        return new Ops6_LogcatPresenter_Factory(provider, provider2, provider3, provider4, provider5, provider6);
    }

    public static Ops6_LogcatPresenter newInstance(Ops6_LogcatContract.Model model, Ops6_LogcatContract.View view) {
        return new Ops6_LogcatPresenter(model, view);
    }

    @Override // javax.inject.Provider
    public Ops6_LogcatPresenter get() {
        Ops6_LogcatPresenter newInstance = newInstance(this.modelProvider.get(), this.rootViewProvider.get());
        Ops6_LogcatPresenter_MembersInjector.injectMErrorHandler(newInstance, this.mErrorHandlerProvider.get());
        Ops6_LogcatPresenter_MembersInjector.injectMApplication(newInstance, this.mApplicationProvider.get());
        Ops6_LogcatPresenter_MembersInjector.injectMImageLoader(newInstance, this.mImageLoaderProvider.get());
        Ops6_LogcatPresenter_MembersInjector.injectMAppManager(newInstance, this.mAppManagerProvider.get());
        return newInstance;
    }
}
