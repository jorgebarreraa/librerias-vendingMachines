package com.jess.arms.di.module;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import me.jessyan.rxerrorhandler.handler.listener.ResponseErrorListener;

/* loaded from: classes.dex */
public final class GlobalConfigModule_ProvideResponseErrorListenerFactory implements Factory<ResponseErrorListener> {
    private final GlobalConfigModule module;

    public GlobalConfigModule_ProvideResponseErrorListenerFactory(GlobalConfigModule globalConfigModule) {
        this.module = globalConfigModule;
    }

    public static GlobalConfigModule_ProvideResponseErrorListenerFactory create(GlobalConfigModule globalConfigModule) {
        return new GlobalConfigModule_ProvideResponseErrorListenerFactory(globalConfigModule);
    }

    public static ResponseErrorListener provideResponseErrorListener(GlobalConfigModule globalConfigModule) {
        return (ResponseErrorListener) Preconditions.checkNotNull(globalConfigModule.provideResponseErrorListener(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    public ResponseErrorListener get() {
        return provideResponseErrorListener(this.module);
    }
}
