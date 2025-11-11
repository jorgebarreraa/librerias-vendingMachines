package io.rx_cache2.internal.cache;

import dagger.internal.Factory;
import io.rx_cache2.internal.Memory;
import io.rx_cache2.internal.Persistence;
import javax.inject.Provider;

/* loaded from: classes2.dex */
public final class EvictExpiredRecordsPersistence_Factory implements Factory<EvictExpiredRecordsPersistence> {
    private final Provider<String> encryptKeyProvider;
    private final Provider<HasRecordExpired> hasRecordExpiredProvider;
    private final Provider<Memory> memoryProvider;
    private final Provider<Persistence> persistenceProvider;

    public EvictExpiredRecordsPersistence_Factory(Provider<Memory> provider, Provider<Persistence> provider2, Provider<HasRecordExpired> provider3, Provider<String> provider4) {
        this.memoryProvider = provider;
        this.persistenceProvider = provider2;
        this.hasRecordExpiredProvider = provider3;
        this.encryptKeyProvider = provider4;
    }

    public static EvictExpiredRecordsPersistence_Factory create(Provider<Memory> provider, Provider<Persistence> provider2, Provider<HasRecordExpired> provider3, Provider<String> provider4) {
        return new EvictExpiredRecordsPersistence_Factory(provider, provider2, provider3, provider4);
    }

    @Override // javax.inject.Provider
    public EvictExpiredRecordsPersistence get() {
        return new EvictExpiredRecordsPersistence(this.memoryProvider.get(), this.persistenceProvider.get(), this.hasRecordExpiredProvider.get(), this.encryptKeyProvider.get());
    }
}
