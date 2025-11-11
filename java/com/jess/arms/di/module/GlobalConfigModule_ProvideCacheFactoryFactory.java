package com.jess.arms.di.module;

import android.app.Application;
import com.jess.arms.integration.cache.Cache;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class GlobalConfigModule_ProvideCacheFactoryFactory implements Factory<Cache.Factory> {
    private final Provider<Application> applicationProvider;
    private final GlobalConfigModule module;

    public GlobalConfigModule_ProvideCacheFactoryFactory(GlobalConfigModule globalConfigModule, Provider<Application> provider) {
        this.module = globalConfigModule;
        this.applicationProvider = provider;
    }

    public static GlobalConfigModule_ProvideCacheFactoryFactory create(GlobalConfigModule globalConfigModule, Provider<Application> provider) {
        return new GlobalConfigModule_ProvideCacheFactoryFactory(globalConfigModule, provider);
    }

    public static Cache.Factory provideCacheFactory(GlobalConfigModule globalConfigModule, Application application) {
        return (Cache.Factory) Preconditions.checkNotNull(globalConfigModule.provideCacheFactory(application), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    public Cache.Factory get() {
        return provideCacheFactory(this.module, this.applicationProvider.get());
    }
}
