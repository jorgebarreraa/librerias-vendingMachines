package io.rx_cache2.internal;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* loaded from: classes2.dex */
public final class RxCacheModule_ProvidePersistenceFactory implements Factory<Persistence> {
    private final Provider<Disk> diskProvider;
    private final RxCacheModule module;

    public RxCacheModule_ProvidePersistenceFactory(RxCacheModule rxCacheModule, Provider<Disk> provider) {
        this.module = rxCacheModule;
        this.diskProvider = provider;
    }

    public static RxCacheModule_ProvidePersistenceFactory create(RxCacheModule rxCacheModule, Provider<Disk> provider) {
        return new RxCacheModule_ProvidePersistenceFactory(rxCacheModule, provider);
    }

    public static Persistence proxyProvidePersistence(RxCacheModule rxCacheModule, Disk disk) {
        return (Persistence) Preconditions.checkNotNull(rxCacheModule.providePersistence(disk), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    public Persistence get() {
        return (Persistence) Preconditions.checkNotNull(this.module.providePersistence(this.diskProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }
}
