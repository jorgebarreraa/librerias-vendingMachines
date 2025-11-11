package com.jess.arms.di.module;

import com.jess.arms.integration.cache.Cache;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class AppModule_ProvideExtrasFactory implements Factory<Cache<String, Object>> {
    private final Provider<Cache.Factory> cacheFactoryProvider;

    public AppModule_ProvideExtrasFactory(Provider<Cache.Factory> provider) {
        this.cacheFactoryProvider = provider;
    }

    public static AppModule_ProvideExtrasFactory create(Provider<Cache.Factory> provider) {
        return new AppModule_ProvideExtrasFactory(provider);
    }

    public static Cache<String, Object> provideExtras(Cache.Factory factory) {
        return (Cache) Preconditions.checkNotNull(AppModule.provideExtras(factory), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    public Cache<String, Object> get() {
        return provideExtras(this.cacheFactoryProvider.get());
    }
}
