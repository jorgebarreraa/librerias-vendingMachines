package io.rx_cache2.internal;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import java.io.File;

/* loaded from: classes2.dex */
public final class RxCacheModule_ProvideCacheDirectoryFactory implements Factory<File> {
    private final RxCacheModule module;

    public RxCacheModule_ProvideCacheDirectoryFactory(RxCacheModule rxCacheModule) {
        this.module = rxCacheModule;
    }

    public static RxCacheModule_ProvideCacheDirectoryFactory create(RxCacheModule rxCacheModule) {
        return new RxCacheModule_ProvideCacheDirectoryFactory(rxCacheModule);
    }

    public static File proxyProvideCacheDirectory(RxCacheModule rxCacheModule) {
        return (File) Preconditions.checkNotNull(rxCacheModule.provideCacheDirectory(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    public File get() {
        return (File) Preconditions.checkNotNull(this.module.provideCacheDirectory(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
