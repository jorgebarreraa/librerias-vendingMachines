package io.rx_cache2.internal.cache;

import dagger.internal.Factory;
import io.rx_cache2.internal.Memory;
import io.rx_cache2.internal.Persistence;
import javax.inject.Provider;

/* loaded from: classes2.dex */
public final class EvictRecord_Factory implements Factory<EvictRecord> {
    private final Provider<Memory> memoryProvider;
    private final Provider<Persistence> persistenceProvider;

    public EvictRecord_Factory(Provider<Memory> provider, Provider<Persistence> provider2) {
        this.memoryProvider = provider;
        this.persistenceProvider = provider2;
    }

    public static EvictRecord_Factory create(Provider<Memory> provider, Provider<Persistence> provider2) {
        return new EvictRecord_Factory(provider, provider2);
    }

    @Override // javax.inject.Provider
    public EvictRecord get() {
        return new EvictRecord(this.memoryProvider.get(), this.persistenceProvider.get());
    }
}
