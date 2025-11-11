package com.jess.arms.di.module;

import androidx.annotation.Nullable;
import com.jess.arms.integration.IRepositoryManager;
import dagger.internal.Factory;

/* loaded from: classes.dex */
public final class GlobalConfigModule_ProvideObtainServiceDelegateFactory implements Factory<IRepositoryManager.ObtainServiceDelegate> {
    private final GlobalConfigModule module;

    public GlobalConfigModule_ProvideObtainServiceDelegateFactory(GlobalConfigModule globalConfigModule) {
        this.module = globalConfigModule;
    }

    public static GlobalConfigModule_ProvideObtainServiceDelegateFactory create(GlobalConfigModule globalConfigModule) {
        return new GlobalConfigModule_ProvideObtainServiceDelegateFactory(globalConfigModule);
    }

    @Nullable
    public static IRepositoryManager.ObtainServiceDelegate provideObtainServiceDelegate(GlobalConfigModule globalConfigModule) {
        return globalConfigModule.provideObtainServiceDelegate();
    }

    @Override // javax.inject.Provider
    @Nullable
    public IRepositoryManager.ObtainServiceDelegate get() {
        return provideObtainServiceDelegate(this.module);
    }
}
