package com.jess.arms.di.module;

import android.app.Application;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.listener.ResponseErrorListener;

/* loaded from: classes.dex */
public final class ClientModule_ProRxErrorHandlerFactory implements Factory<RxErrorHandler> {
    private final Provider<Application> applicationProvider;
    private final Provider<ResponseErrorListener> listenerProvider;

    public ClientModule_ProRxErrorHandlerFactory(Provider<Application> provider, Provider<ResponseErrorListener> provider2) {
        this.applicationProvider = provider;
        this.listenerProvider = provider2;
    }

    public static ClientModule_ProRxErrorHandlerFactory create(Provider<Application> provider, Provider<ResponseErrorListener> provider2) {
        return new ClientModule_ProRxErrorHandlerFactory(provider, provider2);
    }

    public static RxErrorHandler proRxErrorHandler(Application application, ResponseErrorListener responseErrorListener) {
        return (RxErrorHandler) Preconditions.checkNotNull(ClientModule.proRxErrorHandler(application, responseErrorListener), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    public RxErrorHandler get() {
        return proRxErrorHandler(this.applicationProvider.get(), this.listenerProvider.get());
    }
}
