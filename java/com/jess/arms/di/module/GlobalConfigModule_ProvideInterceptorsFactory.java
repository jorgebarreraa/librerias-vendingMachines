package com.jess.arms.di.module;

import androidx.annotation.Nullable;
import dagger.internal.Factory;
import java.util.List;
import okhttp3.Interceptor;

/* loaded from: classes.dex */
public final class GlobalConfigModule_ProvideInterceptorsFactory implements Factory<List<Interceptor>> {
    private final GlobalConfigModule module;

    public GlobalConfigModule_ProvideInterceptorsFactory(GlobalConfigModule globalConfigModule) {
        this.module = globalConfigModule;
    }

    public static GlobalConfigModule_ProvideInterceptorsFactory create(GlobalConfigModule globalConfigModule) {
        return new GlobalConfigModule_ProvideInterceptorsFactory(globalConfigModule);
    }

    @Nullable
    public static List<Interceptor> provideInterceptors(GlobalConfigModule globalConfigModule) {
        return globalConfigModule.provideInterceptors();
    }

    @Override // javax.inject.Provider
    @Nullable
    public List<Interceptor> get() {
        return provideInterceptors(this.module);
    }
}
