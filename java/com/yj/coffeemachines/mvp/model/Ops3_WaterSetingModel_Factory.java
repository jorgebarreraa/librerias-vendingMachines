package com.yj.coffeemachines.mvp.model;

import android.app.Application;
import com.google.gson.Gson;
import com.jess.arms.integration.IRepositoryManager;
import dagger.internal.Factory;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class Ops3_WaterSetingModel_Factory implements Factory<Ops3_WaterSetingModel> {
    private final Provider<Application> mApplicationProvider;
    private final Provider<Gson> mGsonProvider;
    private final Provider<IRepositoryManager> repositoryManagerProvider;

    public Ops3_WaterSetingModel_Factory(Provider<IRepositoryManager> provider, Provider<Gson> provider2, Provider<Application> provider3) {
        this.repositoryManagerProvider = provider;
        this.mGsonProvider = provider2;
        this.mApplicationProvider = provider3;
    }

    public static Ops3_WaterSetingModel_Factory create(Provider<IRepositoryManager> provider, Provider<Gson> provider2, Provider<Application> provider3) {
        return new Ops3_WaterSetingModel_Factory(provider, provider2, provider3);
    }

    public static Ops3_WaterSetingModel newInstance(IRepositoryManager iRepositoryManager) {
        return new Ops3_WaterSetingModel(iRepositoryManager);
    }

    @Override // javax.inject.Provider
    public Ops3_WaterSetingModel get() {
        Ops3_WaterSetingModel newInstance = newInstance(this.repositoryManagerProvider.get());
        Ops3_WaterSetingModel_MembersInjector.injectMGson(newInstance, this.mGsonProvider.get());
        Ops3_WaterSetingModel_MembersInjector.injectMApplication(newInstance, this.mApplicationProvider.get());
        return newInstance;
    }
}
