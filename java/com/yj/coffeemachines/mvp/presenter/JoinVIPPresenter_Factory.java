package com.yj.coffeemachines.mvp.presenter;

import android.app.Application;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.integration.AppManager;
import com.yj.coffeemachines.mvp.contract.JoinVIPContract;
import dagger.internal.Factory;
import javax.inject.Provider;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;

/* loaded from: classes.dex */
public final class JoinVIPPresenter_Factory implements Factory<JoinVIPPresenter> {
    private final Provider<AppManager> mAppManagerProvider;
    private final Provider<Application> mApplicationProvider;
    private final Provider<RxErrorHandler> mErrorHandlerProvider;
    private final Provider<ImageLoader> mImageLoaderProvider;
    private final Provider<JoinVIPContract.Model> modelProvider;
    private final Provider<JoinVIPContract.View> rootViewProvider;

    public JoinVIPPresenter_Factory(Provider<JoinVIPContract.Model> provider, Provider<JoinVIPContract.View> provider2, Provider<RxErrorHandler> provider3, Provider<Application> provider4, Provider<ImageLoader> provider5, Provider<AppManager> provider6) {
        this.modelProvider = provider;
        this.rootViewProvider = provider2;
        this.mErrorHandlerProvider = provider3;
        this.mApplicationProvider = provider4;
        this.mImageLoaderProvider = provider5;
        this.mAppManagerProvider = provider6;
    }

    public static JoinVIPPresenter_Factory create(Provider<JoinVIPContract.Model> provider, Provider<JoinVIPContract.View> provider2, Provider<RxErrorHandler> provider3, Provider<Application> provider4, Provider<ImageLoader> provider5, Provider<AppManager> provider6) {
        return new JoinVIPPresenter_Factory(provider, provider2, provider3, provider4, provider5, provider6);
    }

    public static JoinVIPPresenter newInstance(JoinVIPContract.Model model, JoinVIPContract.View view) {
        return new JoinVIPPresenter(model, view);
    }

    @Override // javax.inject.Provider
    public JoinVIPPresenter get() {
        JoinVIPPresenter newInstance = newInstance(this.modelProvider.get(), this.rootViewProvider.get());
        JoinVIPPresenter_MembersInjector.injectMErrorHandler(newInstance, this.mErrorHandlerProvider.get());
        JoinVIPPresenter_MembersInjector.injectMApplication(newInstance, this.mApplicationProvider.get());
        JoinVIPPresenter_MembersInjector.injectMImageLoader(newInstance, this.mImageLoaderProvider.get());
        JoinVIPPresenter_MembersInjector.injectMAppManager(newInstance, this.mAppManagerProvider.get());
        return newInstance;
    }
}
