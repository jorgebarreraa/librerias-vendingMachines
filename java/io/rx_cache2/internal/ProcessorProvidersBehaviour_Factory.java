package io.rx_cache2.internal;

import dagger.internal.Factory;
import io.rx_cache2.internal.cache.EvictExpiredRecordsPersistence;
import io.rx_cache2.internal.cache.GetDeepCopy;
import io.rx_cache2.internal.cache.TwoLayersCache;
import io.rx_cache2.internal.migration.DoMigrations;
import javax.inject.Provider;

/* loaded from: classes2.dex */
public final class ProcessorProvidersBehaviour_Factory implements Factory<ProcessorProvidersBehaviour> {
    private final Provider<DoMigrations> doMigrationsProvider;
    private final Provider<EvictExpiredRecordsPersistence> evictExpiredRecordsPersistenceProvider;
    private final Provider<GetDeepCopy> getDeepCopyProvider;
    private final Provider<TwoLayersCache> twoLayersCacheProvider;
    private final Provider<Boolean> useExpiredDataIfLoaderNotAvailableProvider;

    public ProcessorProvidersBehaviour_Factory(Provider<TwoLayersCache> provider, Provider<Boolean> provider2, Provider<EvictExpiredRecordsPersistence> provider3, Provider<GetDeepCopy> provider4, Provider<DoMigrations> provider5) {
        this.twoLayersCacheProvider = provider;
        this.useExpiredDataIfLoaderNotAvailableProvider = provider2;
        this.evictExpiredRecordsPersistenceProvider = provider3;
        this.getDeepCopyProvider = provider4;
        this.doMigrationsProvider = provider5;
    }

    public static ProcessorProvidersBehaviour_Factory create(Provider<TwoLayersCache> provider, Provider<Boolean> provider2, Provider<EvictExpiredRecordsPersistence> provider3, Provider<GetDeepCopy> provider4, Provider<DoMigrations> provider5) {
        return new ProcessorProvidersBehaviour_Factory(provider, provider2, provider3, provider4, provider5);
    }

    @Override // javax.inject.Provider
    public ProcessorProvidersBehaviour get() {
        return new ProcessorProvidersBehaviour(this.twoLayersCacheProvider.get(), this.useExpiredDataIfLoaderNotAvailableProvider.get(), this.evictExpiredRecordsPersistenceProvider.get(), this.getDeepCopyProvider.get(), this.doMigrationsProvider.get());
    }
}
