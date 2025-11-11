package io.rx_cache2.internal.cache;

import dagger.internal.Factory;
import io.rx_cache2.internal.Memory;
import io.rx_cache2.internal.Persistence;
import javax.inject.Provider;

/* loaded from: classes2.dex */
public final class SaveRecord_Factory implements Factory<SaveRecord> {
    private final Provider<String> encryptKeyProvider;
    private final Provider<EvictExpirableRecordsPersistence> evictExpirableRecordsPersistenceProvider;
    private final Provider<Integer> maxMgPersistenceCacheProvider;
    private final Provider<Memory> memoryProvider;
    private final Provider<Persistence> persistenceProvider;

    public SaveRecord_Factory(Provider<Memory> provider, Provider<Persistence> provider2, Provider<Integer> provider3, Provider<EvictExpirableRecordsPersistence> provider4, Provider<String> provider5) {
        this.memoryProvider = provider;
        this.persistenceProvider = provider2;
        this.maxMgPersistenceCacheProvider = provider3;
        this.evictExpirableRecordsPersistenceProvider = provider4;
        this.encryptKeyProvider = provider5;
    }

    public static SaveRecord_Factory create(Provider<Memory> provider, Provider<Persistence> provider2, Provider<Integer> provider3, Provider<EvictExpirableRecordsPersistence> provider4, Provider<String> provider5) {
        return new SaveRecord_Factory(provider, provider2, provider3, provider4, provider5);
    }

    @Override // javax.inject.Provider
    public SaveRecord get() {
        return new SaveRecord(this.memoryProvider.get(), this.persistenceProvider.get(), this.maxMgPersistenceCacheProvider.get(), this.evictExpirableRecordsPersistenceProvider.get(), this.encryptKeyProvider.get());
    }
}
