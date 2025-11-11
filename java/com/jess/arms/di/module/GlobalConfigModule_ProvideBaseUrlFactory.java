package com.jess.arms.di.module;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import okhttp3.HttpUrl;

/* loaded from: classes.dex */
public final class GlobalConfigModule_ProvideBaseUrlFactory implements Factory<HttpUrl> {
    private final GlobalConfigModule module;

    public GlobalConfigModule_ProvideBaseUrlFactory(GlobalConfigModule globalConfigModule) {
        this.module = globalConfigModule;
    }

    public static GlobalConfigModule_ProvideBaseUrlFactory create(GlobalConfigModule globalConfigModule) {
        return new GlobalConfigModule_ProvideBaseUrlFactory(globalConfigModule);
    }

    public static HttpUrl provideBaseUrl(GlobalConfigModule globalConfigModule) {
        return (HttpUrl) Preconditions.checkNotNull(globalConfigModule.provideBaseUrl(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    public HttpUrl get() {
        return provideBaseUrl(this.module);
    }
}
