package com.jess.arms.di.module;

import androidx.annotation.Nullable;
import com.jess.arms.di.module.AppModule;
import dagger.internal.Factory;

/* loaded from: classes.dex */
public final class GlobalConfigModule_ProvideGsonConfigurationFactory implements Factory<AppModule.GsonConfiguration> {
    private final GlobalConfigModule module;

    public GlobalConfigModule_ProvideGsonConfigurationFactory(GlobalConfigModule globalConfigModule) {
        this.module = globalConfigModule;
    }

    public static GlobalConfigModule_ProvideGsonConfigurationFactory create(GlobalConfigModule globalConfigModule) {
        return new GlobalConfigModule_ProvideGsonConfigurationFactory(globalConfigModule);
    }

    @Nullable
    public static AppModule.GsonConfiguration provideGsonConfiguration(GlobalConfigModule globalConfigModule) {
        return globalConfigModule.provideGsonConfiguration();
    }

    @Override // javax.inject.Provider
    @Nullable
    public AppModule.GsonConfiguration get() {
        return provideGsonConfiguration(this.module);
    }
}
