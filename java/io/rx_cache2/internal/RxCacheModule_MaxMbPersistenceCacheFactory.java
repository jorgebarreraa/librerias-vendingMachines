package io.rx_cache2.internal;

import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* loaded from: classes2.dex */
public final class RxCacheModule_MaxMbPersistenceCacheFactory implements Factory<Integer> {
    private final RxCacheModule module;

    public RxCacheModule_MaxMbPersistenceCacheFactory(RxCacheModule rxCacheModule) {
        this.module = rxCacheModule;
    }

    public static RxCacheModule_MaxMbPersistenceCacheFactory create(RxCacheModule rxCacheModule) {
        return new RxCacheModule_MaxMbPersistenceCacheFactory(rxCacheModule);
    }

    public static Integer proxyMaxMbPersistenceCache(RxCacheModule rxCacheModule) {
        return (Integer) Preconditions.checkNotNull(rxCacheModule.maxMbPersistenceCache(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    public Integer get() {
        return (Integer) Preconditions.checkNotNull(this.module.maxMbPersistenceCache(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
