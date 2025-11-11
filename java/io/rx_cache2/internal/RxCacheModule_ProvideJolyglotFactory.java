package io.rx_cache2.internal;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import io.victoralbertos.jolyglot.JolyglotGenerics;

/* loaded from: classes2.dex */
public final class RxCacheModule_ProvideJolyglotFactory implements Factory<JolyglotGenerics> {
    private final RxCacheModule module;

    public RxCacheModule_ProvideJolyglotFactory(RxCacheModule rxCacheModule) {
        this.module = rxCacheModule;
    }

    public static RxCacheModule_ProvideJolyglotFactory create(RxCacheModule rxCacheModule) {
        return new RxCacheModule_ProvideJolyglotFactory(rxCacheModule);
    }

    public static JolyglotGenerics proxyProvideJolyglot(RxCacheModule rxCacheModule) {
        return (JolyglotGenerics) Preconditions.checkNotNull(rxCacheModule.provideJolyglot(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    public JolyglotGenerics get() {
        return (JolyglotGenerics) Preconditions.checkNotNull(this.module.provideJolyglot(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
