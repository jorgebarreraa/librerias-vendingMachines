package com.yj.coffeemachines.mvp.model;

import android.app.Application;
import com.google.gson.Gson;
import com.jess.arms.integration.IRepositoryManager;
import dagger.internal.Factory;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class Ops8_MaterialSetingModel_Factory implements Factory<Ops8_MaterialSetingModel> {
    private final Provider<Application> mApplicationProvider;
    private final Provider<Gson> mGsonProvider;
    private final Provider<IRepositoryManager> repositoryManagerProvider;

    public Ops8_MaterialSetingModel_Factory(Provider<IRepositoryManager> provider, Provider<Gson> provider2, Provider<Application> provider3) {
        this.repositoryManagerProvider = provider;
        this.mGsonProvider = provider2;
        this.mApplicationProvider = provider3;
    }

    public static Ops8_MaterialSetingModel_Factory create(Provider<IRepositoryManager> provider, Provider<Gson> provider2, Provider<Application> provider3) {
        return new Ops8_MaterialSetingModel_Factory(provider, provider2, provider3);
    }

    public static Ops8_MaterialSetingModel newInstance(IRepositoryManager iRepositoryManager) {
        return new Ops8_MaterialSetingModel(iRepositoryManager);
    }

    @Override // javax.inject.Provider
    public Ops8_MaterialSetingModel get() {
        Ops8_MaterialSetingModel newInstance = newInstance(this.repositoryManagerProvider.get());
        Ops8_MaterialSetingModel_MembersInjector.injectMGson(newInstance, this.mGsonProvider.get());
        Ops8_MaterialSetingModel_MembersInjector.injectMApplication(newInstance, this.mApplicationProvider.get());
        return newInstance;
    }
}
