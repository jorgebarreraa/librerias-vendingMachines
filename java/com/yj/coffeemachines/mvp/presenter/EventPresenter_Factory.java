package com.yj.coffeemachines.mvp.presenter;

import android.app.Application;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.integration.AppManager;
import com.yj.coffeemachines.mvp.contract.EventContract;
import dagger.internal.Factory;
import javax.inject.Provider;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;

/* loaded from: classes.dex */
public final class EventPresenter_Factory implements Factory<EventPresenter> {
    private final Provider<AppManager> mAppManagerProvider;
    private final Provider<Application> mApplicationProvider;
    private final Provider<RxErrorHandler> mErrorHandlerProvider;
    private final Provider<ImageLoader> mImageLoaderProvider;
    private final Provider<EventContract.Model> modelProvider;
    private final Provider<EventContract.View> rootViewProvider;

    public EventPresenter_Factory(Provider<EventContract.Model> provider, Provider<EventContract.View> provider2, Provider<RxErrorHandler> provider3, Provider<Application> provider4, Provider<ImageLoader> provider5, Provider<AppManager> provider6) {
        this.modelProvider = provider;
        this.rootViewProvider = provider2;
        this.mErrorHandlerProvider = provider3;
        this.mApplicationProvider = provider4;
        this.mImageLoaderProvider = provider5;
        this.mAppManagerProvider = provider6;
    }

    public static EventPresenter_Factory create(Provider<EventContract.Model> provider, Provider<EventContract.View> provider2, Provider<RxErrorHandler> provider3, Provider<Application> provider4, Provider<ImageLoader> provider5, Provider<AppManager> provider6) {
        return new EventPresenter_Factory(provider, provider2, provider3, provider4, provider5, provider6);
    }

    public static EventPresenter newInstance(EventContract.Model model, EventContract.View view) {
        return new EventPresenter(model, view);
    }

    @Override // javax.inject.Provider
    public EventPresenter get() {
        EventPresenter newInstance = newInstance(this.modelProvider.get(), this.rootViewProvider.get());
        EventPresenter_MembersInjector.injectMErrorHandler(newInstance, this.mErrorHandlerProvider.get());
        EventPresenter_MembersInjector.injectMApplication(newInstance, this.mApplicationProvider.get());
        EventPresenter_MembersInjector.injectMImageLoader(newInstance, this.mImageLoaderProvider.get());
        EventPresenter_MembersInjector.injectMAppManager(newInstance, this.mAppManagerProvider.get());
        return newInstance;
    }
}
