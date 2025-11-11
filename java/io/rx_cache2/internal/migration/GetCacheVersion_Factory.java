package io.rx_cache2.internal.migration;

import dagger.internal.Factory;
import io.rx_cache2.internal.Persistence;
import javax.inject.Provider;

/* loaded from: classes2.dex */
public final class GetCacheVersion_Factory implements Factory<GetCacheVersion> {
    private final Provider<Persistence> persistenceProvider;

    public GetCacheVersion_Factory(Provider<Persistence> provider) {
        this.persistenceProvider = provider;
    }

    public static GetCacheVersion_Factory create(Provider<Persistence> provider) {
        return new GetCacheVersion_Factory(provider);
    }

    public static GetCacheVersion newGetCacheVersion(Persistence persistence) {
        return new GetCacheVersion(persistence);
    }

    @Override // javax.inject.Provider
    public GetCacheVersion get() {
        return new GetCacheVersion(this.persistenceProvider.get());
    }
}
