package io.rx_cache2.internal.cache;

import dagger.internal.Factory;
import javax.inject.Provider;

/* loaded from: classes2.dex */
public final class TwoLayersCache_Factory implements Factory<TwoLayersCache> {
    private final Provider<EvictRecord> evictRecordProvider;
    private final Provider<RetrieveRecord> retrieveRecordProvider;
    private final Provider<SaveRecord> saveRecordProvider;

    public TwoLayersCache_Factory(Provider<EvictRecord> provider, Provider<RetrieveRecord> provider2, Provider<SaveRecord> provider3) {
        this.evictRecordProvider = provider;
        this.retrieveRecordProvider = provider2;
        this.saveRecordProvider = provider3;
    }

    public static TwoLayersCache_Factory create(Provider<EvictRecord> provider, Provider<RetrieveRecord> provider2, Provider<SaveRecord> provider3) {
        return new TwoLayersCache_Factory(provider, provider2, provider3);
    }

    @Override // javax.inject.Provider
    public TwoLayersCache get() {
        return new TwoLayersCache(this.evictRecordProvider.get(), this.retrieveRecordProvider.get(), this.saveRecordProvider.get());
    }
}
