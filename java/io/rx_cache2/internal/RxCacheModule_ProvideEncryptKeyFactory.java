package io.rx_cache2.internal;

import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* loaded from: classes2.dex */
public final class RxCacheModule_ProvideEncryptKeyFactory implements Factory<String> {
    private final RxCacheModule module;

    public RxCacheModule_ProvideEncryptKeyFactory(RxCacheModule rxCacheModule) {
        this.module = rxCacheModule;
    }

    public static RxCacheModule_ProvideEncryptKeyFactory create(RxCacheModule rxCacheModule) {
        return new RxCacheModule_ProvideEncryptKeyFactory(rxCacheModule);
    }

    public static String proxyProvideEncryptKey(RxCacheModule rxCacheModule) {
        return (String) Preconditions.checkNotNull(rxCacheModule.provideEncryptKey(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    public String get() {
        return (String) Preconditions.checkNotNull(this.module.provideEncryptKey(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
