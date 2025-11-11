package com.jess.arms.di.module;

import android.app.Application;
import com.google.gson.Gson;
import com.jess.arms.di.module.ClientModule;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import io.rx_cache2.internal.RxCache;
import java.io.File;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class ClientModule_ProvideRxCacheFactory implements Factory<RxCache> {
    private final Provider<Application> applicationProvider;
    private final Provider<File> cacheDirectoryProvider;
    private final Provider<ClientModule.RxCacheConfiguration> configurationProvider;
    private final Provider<Gson> gsonProvider;

    public ClientModule_ProvideRxCacheFactory(Provider<Application> provider, Provider<ClientModule.RxCacheConfiguration> provider2, Provider<File> provider3, Provider<Gson> provider4) {
        this.applicationProvider = provider;
        this.configurationProvider = provider2;
        this.cacheDirectoryProvider = provider3;
        this.gsonProvider = provider4;
    }

    public static ClientModule_ProvideRxCacheFactory create(Provider<Application> provider, Provider<ClientModule.RxCacheConfiguration> provider2, Provider<File> provider3, Provider<Gson> provider4) {
        return new ClientModule_ProvideRxCacheFactory(provider, provider2, provider3, provider4);
    }

    public static RxCache provideRxCache(Application application, ClientModule.RxCacheConfiguration rxCacheConfiguration, File file, Gson gson) {
        return (RxCache) Preconditions.checkNotNull(ClientModule.provideRxCache(application, rxCacheConfiguration, file, gson), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    public RxCache get() {
        return provideRxCache(this.applicationProvider.get(), this.configurationProvider.get(), this.cacheDirectoryProvider.get(), this.gsonProvider.get());
    }
}
