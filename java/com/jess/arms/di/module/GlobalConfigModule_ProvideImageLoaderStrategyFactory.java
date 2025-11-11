package com.jess.arms.di.module;

import androidx.annotation.Nullable;
import com.jess.arms.http.imageloader.BaseImageLoaderStrategy;
import dagger.internal.Factory;

/* loaded from: classes.dex */
public final class GlobalConfigModule_ProvideImageLoaderStrategyFactory implements Factory<BaseImageLoaderStrategy> {
    private final GlobalConfigModule module;

    public GlobalConfigModule_ProvideImageLoaderStrategyFactory(GlobalConfigModule globalConfigModule) {
        this.module = globalConfigModule;
    }

    public static GlobalConfigModule_ProvideImageLoaderStrategyFactory create(GlobalConfigModule globalConfigModule) {
        return new GlobalConfigModule_ProvideImageLoaderStrategyFactory(globalConfigModule);
    }

    @Nullable
    public static BaseImageLoaderStrategy provideImageLoaderStrategy(GlobalConfigModule globalConfigModule) {
        return globalConfigModule.provideImageLoaderStrategy();
    }

    @Override // javax.inject.Provider
    @Nullable
    public BaseImageLoaderStrategy get() {
        return provideImageLoaderStrategy(this.module);
    }
}
