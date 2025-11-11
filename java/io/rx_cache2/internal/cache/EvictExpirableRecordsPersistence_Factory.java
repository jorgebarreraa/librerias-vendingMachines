package io.rx_cache2.internal.cache;

import dagger.internal.Factory;
import io.rx_cache2.internal.Memory;
import io.rx_cache2.internal.Persistence;
import javax.inject.Provider;

/* loaded from: classes2.dex */
public final class EvictExpirableRecordsPersistence_Factory implements Factory<EvictExpirableRecordsPersistence> {
    private final Provider<String> encryptKeyProvider;
    private final Provider<Integer> maxMgPersistenceCacheProvider;
    private final Provider<Memory> memoryProvider;
    private final Provider<Persistence> persistenceProvider;

    public EvictExpirableRecordsPersistence_Factory(Provider<Memory> provider, Provider<Persistence> provider2, Provider<Integer> provider3, Provider<String> provider4) {
        this.memoryProvider = provider;
        this.persistenceProvider = provider2;
        this.maxMgPersistenceCacheProvider = provider3;
        this.encryptKeyProvider = provider4;
    }

    public static EvictExpirableRecordsPersistence_Factory create(Provider<Memory> provider, Provider<Persistence> provider2, Provider<Integer> provider3, Provider<String> provider4) {
        return new EvictExpirableRecordsPersistence_Factory(provider, provider2, provider3, provider4);
    }

    @Override // javax.inject.Provider
    public EvictExpirableRecordsPersistence get() {
        return new EvictExpirableRecordsPersistence(this.memoryProvider.get(), this.persistenceProvider.get(), this.maxMgPersistenceCacheProvider.get(), this.encryptKeyProvider.get());
    }
}
