package io.rx_cache2.internal.cache;

import dagger.internal.Factory;
import io.rx_cache2.internal.Memory;
import io.rx_cache2.internal.Persistence;
import io.victoralbertos.jolyglot.JolyglotGenerics;
import javax.inject.Provider;

/* loaded from: classes2.dex */
public final class GetDeepCopy_Factory implements Factory<GetDeepCopy> {
    private final Provider<JolyglotGenerics> jolyglotProvider;
    private final Provider<Memory> memoryProvider;
    private final Provider<Persistence> persistenceProvider;

    public GetDeepCopy_Factory(Provider<Memory> provider, Provider<Persistence> provider2, Provider<JolyglotGenerics> provider3) {
        this.memoryProvider = provider;
        this.persistenceProvider = provider2;
        this.jolyglotProvider = provider3;
    }

    public static GetDeepCopy_Factory create(Provider<Memory> provider, Provider<Persistence> provider2, Provider<JolyglotGenerics> provider3) {
        return new GetDeepCopy_Factory(provider, provider2, provider3);
    }

    @Override // javax.inject.Provider
    public GetDeepCopy get() {
        return new GetDeepCopy(this.memoryProvider.get(), this.persistenceProvider.get(), this.jolyglotProvider.get());
    }
}
