package com.yj.coffeemachines.mvp.model;

import android.app.Application;
import com.google.gson.Gson;
import com.jess.arms.integration.IRepositoryManager;
import dagger.internal.Factory;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class FactoryModel_Factory implements Factory<FactoryModel> {
    private final Provider<Application> mApplicationProvider;
    private final Provider<Gson> mGsonProvider;
    private final Provider<IRepositoryManager> repositoryManagerProvider;

    public FactoryModel_Factory(Provider<IRepositoryManager> provider, Provider<Gson> provider2, Provider<Application> provider3) {
        this.repositoryManagerProvider = provider;
        this.mGsonProvider = provider2;
        this.mApplicationProvider = provider3;
    }

    public static FactoryModel_Factory create(Provider<IRepositoryManager> provider, Provider<Gson> provider2, Provider<Application> provider3) {
        return new FactoryModel_Factory(provider, provider2, provider3);
    }

    public static FactoryModel newInstance(IRepositoryManager iRepositoryManager) {
        return new FactoryModel(iRepositoryManager);
    }

    @Override // javax.inject.Provider
    public FactoryModel get() {
        FactoryModel newInstance = newInstance(this.repositoryManagerProvider.get());
        FactoryModel_MembersInjector.injectMGson(newInstance, this.mGsonProvider.get());
        FactoryModel_MembersInjector.injectMApplication(newInstance, this.mApplicationProvider.get());
        return newInstance;
    }
}
