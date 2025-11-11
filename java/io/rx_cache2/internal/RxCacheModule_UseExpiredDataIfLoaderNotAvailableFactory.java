package io.rx_cache2.internal;

import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* loaded from: classes2.dex */
public final class RxCacheModule_UseExpiredDataIfLoaderNotAvailableFactory implements Factory<Boolean> {
    private final RxCacheModule module;

    public RxCacheModule_UseExpiredDataIfLoaderNotAvailableFactory(RxCacheModule rxCacheModule) {
        this.module = rxCacheModule;
    }

    public static RxCacheModule_UseExpiredDataIfLoaderNotAvailableFactory create(RxCacheModule rxCacheModule) {
        return new RxCacheModule_UseExpiredDataIfLoaderNotAvailableFactory(rxCacheModule);
    }

    public static Boolean proxyUseExpiredDataIfLoaderNotAvailable(RxCacheModule rxCacheModule) {
        return (Boolean) Preconditions.checkNotNull(rxCacheModule.useExpiredDataIfLoaderNotAvailable(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    public Boolean get() {
        return (Boolean) Preconditions.checkNotNull(this.module.useExpiredDataIfLoaderNotAvailable(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
