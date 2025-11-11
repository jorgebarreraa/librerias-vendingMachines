package com.jess.arms.di.module;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import java.util.concurrent.ExecutorService;

/* loaded from: classes.dex */
public final class GlobalConfigModule_ProvideExecutorServiceFactory implements Factory<ExecutorService> {
    private final GlobalConfigModule module;

    public GlobalConfigModule_ProvideExecutorServiceFactory(GlobalConfigModule globalConfigModule) {
        this.module = globalConfigModule;
    }

    public static GlobalConfigModule_ProvideExecutorServiceFactory create(GlobalConfigModule globalConfigModule) {
        return new GlobalConfigModule_ProvideExecutorServiceFactory(globalConfigModule);
    }

    public static ExecutorService provideExecutorService(GlobalConfigModule globalConfigModule) {
        return (ExecutorService) Preconditions.checkNotNull(globalConfigModule.provideExecutorService(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    public ExecutorService get() {
        return provideExecutorService(this.module);
    }
}
