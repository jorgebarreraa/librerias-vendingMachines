package io.rx_cache2.internal;

import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* loaded from: classes2.dex */
public final class RxCacheModule_ProvideMemoryFactory implements Factory<Memory> {
    private final RxCacheModule module;

    public RxCacheModule_ProvideMemoryFactory(RxCacheModule rxCacheModule) {
        this.module = rxCacheModule;
    }

    public static RxCacheModule_ProvideMemoryFactory create(RxCacheModule rxCacheModule) {
        return new RxCacheModule_ProvideMemoryFactory(rxCacheModule);
    }

    public static Memory proxyProvideMemory(RxCacheModule rxCacheModule) {
        return (Memory) Preconditions.checkNotNull(rxCacheModule.provideMemory(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    public Memory get() {
        return (Memory) Preconditions.checkNotNull(this.module.provideMemory(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
