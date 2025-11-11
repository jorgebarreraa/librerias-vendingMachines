package com.jess.arms.di.module;

import androidx.annotation.Nullable;
import com.jess.arms.di.module.ClientModule;
import dagger.internal.Factory;

/* loaded from: classes.dex */
public final class GlobalConfigModule_ProvideRxCacheConfigurationFactory implements Factory<ClientModule.RxCacheConfiguration> {
    private final GlobalConfigModule module;

    public GlobalConfigModule_ProvideRxCacheConfigurationFactory(GlobalConfigModule globalConfigModule) {
        this.module = globalConfigModule;
    }

    public static GlobalConfigModule_ProvideRxCacheConfigurationFactory create(GlobalConfigModule globalConfigModule) {
        return new GlobalConfigModule_ProvideRxCacheConfigurationFactory(globalConfigModule);
    }

    @Nullable
    public static ClientModule.RxCacheConfiguration provideRxCacheConfiguration(GlobalConfigModule globalConfigModule) {
        return globalConfigModule.provideRxCacheConfiguration();
    }

    @Override // javax.inject.Provider
    @Nullable
    public ClientModule.RxCacheConfiguration get() {
        return provideRxCacheConfiguration(this.module);
    }
}
