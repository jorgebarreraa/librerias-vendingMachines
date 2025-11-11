package com.yj.coffeemachines.mvp.presenter;

import android.app.Application;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.integration.AppManager;
import dagger.MembersInjector;
import dagger.internal.InjectedFieldSignature;
import javax.inject.Provider;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;

/* loaded from: classes.dex */
public final class EventPresenter_MembersInjector implements MembersInjector<EventPresenter> {
    private final Provider<AppManager> mAppManagerProvider;
    private final Provider<Application> mApplicationProvider;
    private final Provider<RxErrorHandler> mErrorHandlerProvider;
    private final Provider<ImageLoader> mImageLoaderProvider;

    public EventPresenter_MembersInjector(Provider<RxErrorHandler> provider, Provider<Application> provider2, Provider<ImageLoader> provider3, Provider<AppManager> provider4) {
        this.mErrorHandlerProvider = provider;
        this.mApplicationProvider = provider2;
        this.mImageLoaderProvider = provider3;
        this.mAppManagerProvider = provider4;
    }

    public static MembersInjector<EventPresenter> create(Provider<RxErrorHandler> provider, Provider<Application> provider2, Provider<ImageLoader> provider3, Provider<AppManager> provider4) {
        return new EventPresenter_MembersInjector(provider, provider2, provider3, provider4);
    }

    @InjectedFieldSignature("com.yj.coffeemachines.mvp.presenter.EventPresenter.mAppManager")
    public static void injectMAppManager(EventPresenter eventPresenter, AppManager appManager) {
        eventPresenter.mAppManager = appManager;
    }

    @InjectedFieldSignature("com.yj.coffeemachines.mvp.presenter.EventPresenter.mApplication")
    public static void injectMApplication(EventPresenter eventPresenter, Application application) {
        eventPresenter.mApplication = application;
    }

    @InjectedFieldSignature("com.yj.coffeemachines.mvp.presenter.EventPresenter.mErrorHandler")
    public static void injectMErrorHandler(EventPresenter eventPresenter, RxErrorHandler rxErrorHandler) {
        eventPresenter.mErrorHandler = rxErrorHandler;
    }

    @InjectedFieldSignature("com.yj.coffeemachines.mvp.presenter.EventPresenter.mImageLoader")
    public static void injectMImageLoader(EventPresenter eventPresenter, ImageLoader imageLoader) {
        eventPresenter.mImageLoader = imageLoader;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(EventPresenter eventPresenter) {
        injectMErrorHandler(eventPresenter, this.mErrorHandlerProvider.get());
        injectMApplication(eventPresenter, this.mApplicationProvider.get());
        injectMImageLoader(eventPresenter, this.mImageLoaderProvider.get());
        injectMAppManager(eventPresenter, this.mAppManagerProvider.get());
    }
}
