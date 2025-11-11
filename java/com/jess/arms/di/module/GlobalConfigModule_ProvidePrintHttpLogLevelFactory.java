package com.jess.arms.di.module;

import com.jess.arms.http.log.RequestInterceptor;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* loaded from: classes.dex */
public final class GlobalConfigModule_ProvidePrintHttpLogLevelFactory implements Factory<RequestInterceptor.Level> {
    private final GlobalConfigModule module;

    public GlobalConfigModule_ProvidePrintHttpLogLevelFactory(GlobalConfigModule globalConfigModule) {
        this.module = globalConfigModule;
    }

    public static GlobalConfigModule_ProvidePrintHttpLogLevelFactory create(GlobalConfigModule globalConfigModule) {
        return new GlobalConfigModule_ProvidePrintHttpLogLevelFactory(globalConfigModule);
    }

    public static RequestInterceptor.Level providePrintHttpLogLevel(GlobalConfigModule globalConfigModule) {
        return (RequestInterceptor.Level) Preconditions.checkNotNull(globalConfigModule.providePrintHttpLogLevel(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    public RequestInterceptor.Level get() {
        return providePrintHttpLogLevel(this.module);
    }
}
