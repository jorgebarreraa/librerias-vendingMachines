package io.rx_cache2.internal;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import io.rx_cache2.internal.encrypt.Encryptor;

/* loaded from: classes2.dex */
public final class RxCacheModule_ProvideEncryptorFactory implements Factory<Encryptor> {
    private final RxCacheModule module;

    public RxCacheModule_ProvideEncryptorFactory(RxCacheModule rxCacheModule) {
        this.module = rxCacheModule;
    }

    public static RxCacheModule_ProvideEncryptorFactory create(RxCacheModule rxCacheModule) {
        return new RxCacheModule_ProvideEncryptorFactory(rxCacheModule);
    }

    public static Encryptor proxyProvideEncryptor(RxCacheModule rxCacheModule) {
        return (Encryptor) Preconditions.checkNotNull(rxCacheModule.provideEncryptor(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    public Encryptor get() {
        return (Encryptor) Preconditions.checkNotNull(this.module.provideEncryptor(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
