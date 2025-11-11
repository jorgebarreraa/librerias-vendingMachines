package io.rx_cache2.internal.migration;

import dagger.internal.Factory;
import io.rx_cache2.internal.Persistence;
import javax.inject.Provider;

/* loaded from: classes2.dex */
public final class UpgradeCacheVersion_Factory implements Factory<UpgradeCacheVersion> {
    private final Provider<Persistence> persistenceProvider;

    public UpgradeCacheVersion_Factory(Provider<Persistence> provider) {
        this.persistenceProvider = provider;
    }

    public static UpgradeCacheVersion_Factory create(Provider<Persistence> provider) {
        return new UpgradeCacheVersion_Factory(provider);
    }

    public static UpgradeCacheVersion newUpgradeCacheVersion(Persistence persistence) {
        return new UpgradeCacheVersion(persistence);
    }

    @Override // javax.inject.Provider
    public UpgradeCacheVersion get() {
        return new UpgradeCacheVersion(this.persistenceProvider.get());
    }
}
