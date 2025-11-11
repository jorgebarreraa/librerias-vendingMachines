package io.rx_cache2.internal;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* loaded from: classes2.dex */
public final class RxCacheModule_ProvideProcessorProvidersFactory implements Factory<ProcessorProviders> {
    private final RxCacheModule module;
    private final Provider<ProcessorProvidersBehaviour> processorProvidersBehaviourProvider;

    public RxCacheModule_ProvideProcessorProvidersFactory(RxCacheModule rxCacheModule, Provider<ProcessorProvidersBehaviour> provider) {
        this.module = rxCacheModule;
        this.processorProvidersBehaviourProvider = provider;
    }

    public static RxCacheModule_ProvideProcessorProvidersFactory create(RxCacheModule rxCacheModule, Provider<ProcessorProvidersBehaviour> provider) {
        return new RxCacheModule_ProvideProcessorProvidersFactory(rxCacheModule, provider);
    }

    public static ProcessorProviders proxyProvideProcessorProviders(RxCacheModule rxCacheModule, ProcessorProvidersBehaviour processorProvidersBehaviour) {
        return (ProcessorProviders) Preconditions.checkNotNull(rxCacheModule.provideProcessorProviders(processorProvidersBehaviour), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    public ProcessorProviders get() {
        return (ProcessorProviders) Preconditions.checkNotNull(this.module.provideProcessorProviders(this.processorProvidersBehaviourProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }
}
