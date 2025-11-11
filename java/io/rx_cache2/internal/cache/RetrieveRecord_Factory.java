package io.rx_cache2.internal.cache;

import dagger.internal.Factory;
import io.rx_cache2.internal.Memory;
import io.rx_cache2.internal.Persistence;
import javax.inject.Provider;

/* loaded from: classes2.dex */
public final class RetrieveRecord_Factory implements Factory<RetrieveRecord> {
    private final Provider<String> encryptKeyProvider;
    private final Provider<EvictRecord> evictRecordProvider;
    private final Provider<HasRecordExpired> hasRecordExpiredProvider;
    private final Provider<Memory> memoryProvider;
    private final Provider<Persistence> persistenceProvider;

    public RetrieveRecord_Factory(Provider<Memory> provider, Provider<Persistence> provider2, Provider<EvictRecord> provider3, Provider<HasRecordExpired> provider4, Provider<String> provider5) {
        this.memoryProvider = provider;
        this.persistenceProvider = provider2;
        this.evictRecordProvider = provider3;
        this.hasRecordExpiredProvider = provider4;
        this.encryptKeyProvider = provider5;
    }

    public static RetrieveRecord_Factory create(Provider<Memory> provider, Provider<Persistence> provider2, Provider<EvictRecord> provider3, Provider<HasRecordExpired> provider4, Provider<String> provider5) {
        return new RetrieveRecord_Factory(provider, provider2, provider3, provider4, provider5);
    }

    @Override // javax.inject.Provider
    public RetrieveRecord get() {
        return new RetrieveRecord(this.memoryProvider.get(), this.persistenceProvider.get(), this.evictRecordProvider.get(), this.hasRecordExpiredProvider.get(), this.encryptKeyProvider.get());
    }
}
