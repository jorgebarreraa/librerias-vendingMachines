package io.rx_cache2.internal;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import io.rx_cache2.MigrationCache;
import java.util.List;

/* loaded from: classes2.dex */
public final class RxCacheModule_ProvideMigrationsFactory implements Factory<List<MigrationCache>> {
    private final RxCacheModule module;

    public RxCacheModule_ProvideMigrationsFactory(RxCacheModule rxCacheModule) {
        this.module = rxCacheModule;
    }

    public static RxCacheModule_ProvideMigrationsFactory create(RxCacheModule rxCacheModule) {
        return new RxCacheModule_ProvideMigrationsFactory(rxCacheModule);
    }

    public static List<MigrationCache> proxyProvideMigrations(RxCacheModule rxCacheModule) {
        return (List) Preconditions.checkNotNull(rxCacheModule.provideMigrations(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    public List<MigrationCache> get() {
        return (List) Preconditions.checkNotNull(this.module.provideMigrations(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
