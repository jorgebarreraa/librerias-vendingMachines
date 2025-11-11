package com.jess.arms.di.module;

import androidx.annotation.Nullable;
import com.jess.arms.http.GlobalHttpHandler;
import dagger.internal.Factory;

/* loaded from: classes.dex */
public final class GlobalConfigModule_ProvideGlobalHttpHandlerFactory implements Factory<GlobalHttpHandler> {
    private final GlobalConfigModule module;

    public GlobalConfigModule_ProvideGlobalHttpHandlerFactory(GlobalConfigModule globalConfigModule) {
        this.module = globalConfigModule;
    }

    public static GlobalConfigModule_ProvideGlobalHttpHandlerFactory create(GlobalConfigModule globalConfigModule) {
        return new GlobalConfigModule_ProvideGlobalHttpHandlerFactory(globalConfigModule);
    }

    @Nullable
    public static GlobalHttpHandler provideGlobalHttpHandler(GlobalConfigModule globalConfigModule) {
        return globalConfigModule.provideGlobalHttpHandler();
    }

    @Override // javax.inject.Provider
    @Nullable
    public GlobalHttpHandler get() {
        return provideGlobalHttpHandler(this.module);
    }
}
