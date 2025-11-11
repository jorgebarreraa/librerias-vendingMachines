package com.jess.arms.di.module;

import androidx.annotation.Nullable;
import com.jess.arms.di.module.ClientModule;
import dagger.internal.Factory;

/* loaded from: classes.dex */
public final class GlobalConfigModule_ProvideOkhttpConfigurationFactory implements Factory<ClientModule.OkhttpConfiguration> {
    private final GlobalConfigModule module;

    public GlobalConfigModule_ProvideOkhttpConfigurationFactory(GlobalConfigModule globalConfigModule) {
        this.module = globalConfigModule;
    }

    public static GlobalConfigModule_ProvideOkhttpConfigurationFactory create(GlobalConfigModule globalConfigModule) {
        return new GlobalConfigModule_ProvideOkhttpConfigurationFactory(globalConfigModule);
    }

    @Nullable
    public static ClientModule.OkhttpConfiguration provideOkhttpConfiguration(GlobalConfigModule globalConfigModule) {
        return globalConfigModule.provideOkhttpConfiguration();
    }

    @Override // javax.inject.Provider
    @Nullable
    public ClientModule.OkhttpConfiguration get() {
        return provideOkhttpConfiguration(this.module);
    }
}
