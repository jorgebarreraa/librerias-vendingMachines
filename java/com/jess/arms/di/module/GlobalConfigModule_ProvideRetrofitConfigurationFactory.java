package com.jess.arms.di.module;

import androidx.annotation.Nullable;
import com.jess.arms.di.module.ClientModule;
import dagger.internal.Factory;

/* loaded from: classes.dex */
public final class GlobalConfigModule_ProvideRetrofitConfigurationFactory implements Factory<ClientModule.RetrofitConfiguration> {
    private final GlobalConfigModule module;

    public GlobalConfigModule_ProvideRetrofitConfigurationFactory(GlobalConfigModule globalConfigModule) {
        this.module = globalConfigModule;
    }

    public static GlobalConfigModule_ProvideRetrofitConfigurationFactory create(GlobalConfigModule globalConfigModule) {
        return new GlobalConfigModule_ProvideRetrofitConfigurationFactory(globalConfigModule);
    }

    @Nullable
    public static ClientModule.RetrofitConfiguration provideRetrofitConfiguration(GlobalConfigModule globalConfigModule) {
        return globalConfigModule.provideRetrofitConfiguration();
    }

    @Override // javax.inject.Provider
    @Nullable
    public ClientModule.RetrofitConfiguration get() {
        return provideRetrofitConfiguration(this.module);
    }
}
