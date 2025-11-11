package com.yj.coffeemachines.mvp.presenter;

import android.app.Application;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.integration.AppManager;
import com.yj.coffeemachines.mvp.contract.SetingsHomeContract;
import dagger.internal.Factory;
import javax.inject.Provider;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;

/* loaded from: classes.dex */
public final class SetingsHomePresenter_Factory implements Factory<SetingsHomePresenter> {
    private final Provider<AppManager> mAppManagerProvider;
    private final Provider<Application> mApplicationProvider;
    private final Provider<RxErrorHandler> mErrorHandlerProvider;
    private final Provider<ImageLoader> mImageLoaderProvider;
    private final Provider<SetingsHomeContract.Model> modelProvider;
    private final Provider<SetingsHomeContract.View> rootViewProvider;

    public SetingsHomePresenter_Factory(Provider<SetingsHomeContract.Model> provider, Provider<SetingsHomeContract.View> provider2, Provider<RxErrorHandler> provider3, Provider<Application> provider4, Provider<ImageLoader> provider5, Provider<AppManager> provider6) {
        this.modelProvider = provider;
        this.rootViewProvider = provider2;
        this.mErrorHandlerProvider = provider3;
        this.mApplicationProvider = provider4;
        this.mImageLoaderProvider = provider5;
        this.mAppManagerProvider = provider6;
    }

    public static SetingsHomePresenter_Factory create(Provider<SetingsHomeContract.Model> provider, Provider<SetingsHomeContract.View> provider2, Provider<RxErrorHandler> provider3, Provider<Application> provider4, Provider<ImageLoader> provider5, Provider<AppManager> provider6) {
        return new SetingsHomePresenter_Factory(provider, provider2, provider3, provider4, provider5, provider6);
    }

    public static SetingsHomePresenter newInstance(SetingsHomeContract.Model model, SetingsHomeContract.View view) {
        return new SetingsHomePresenter(model, view);
    }

    @Override // javax.inject.Provider
    public SetingsHomePresenter get() {
        SetingsHomePresenter newInstance = newInstance(this.modelProvider.get(), this.rootViewProvider.get());
        SetingsHomePresenter_MembersInjector.injectMErrorHandler(newInstance, this.mErrorHandlerProvider.get());
        SetingsHomePresenter_MembersInjector.injectMApplication(newInstance, this.mApplicationProvider.get());
        SetingsHomePresenter_MembersInjector.injectMImageLoader(newInstance, this.mImageLoaderProvider.get());
        SetingsHomePresenter_MembersInjector.injectMAppManager(newInstance, this.mAppManagerProvider.get());
        return newInstance;
    }
}
