package com.yj.coffeemachines.mvp.model;

import android.app.Application;
import com.google.gson.Gson;
import com.jess.arms.integration.IRepositoryManager;
import dagger.internal.Factory;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class Factory1_DevConfigModel_Factory implements Factory<Factory1_DevConfigModel> {
    private final Provider<Application> mApplicationProvider;
    private final Provider<Gson> mGsonProvider;
    private final Provider<IRepositoryManager> repositoryManagerProvider;

    public Factory1_DevConfigModel_Factory(Provider<IRepositoryManager> provider, Provider<Gson> provider2, Provider<Application> provider3) {
        this.repositoryManagerProvider = provider;
        this.mGsonProvider = provider2;
        this.mApplicationProvider = provider3;
    }

    public static Factory1_DevConfigModel_Factory create(Provider<IRepositoryManager> provider, Provider<Gson> provider2, Provider<Application> provider3) {
        return new Factory1_DevConfigModel_Factory(provider, provider2, provider3);
    }

    public static Factory1_DevConfigModel newInstance(IRepositoryManager iRepositoryManager) {
        return new Factory1_DevConfigModel(iRepositoryManager);
    }

    @Override // javax.inject.Provider
    public Factory1_DevConfigModel get() {
        Factory1_DevConfigModel newInstance = newInstance(this.repositoryManagerProvider.get());
        Factory1_DevConfigModel_MembersInjector.injectMGson(newInstance, this.mGsonProvider.get());
        Factory1_DevConfigModel_MembersInjector.injectMApplication(newInstance, this.mApplicationProvider.get());
        return newInstance;
    }
}
