package com.yj.coffeemachines.di.component.mvp.model;

import android.app.Application;
import com.google.gson.Gson;
import com.jess.arms.integration.IRepositoryManager;
import dagger.internal.Factory;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class Ops2_RawMaterialSetingModel_Factory implements Factory<Ops2_RawMaterialSetingModel> {
    private final Provider<Application> mApplicationProvider;
    private final Provider<Gson> mGsonProvider;
    private final Provider<IRepositoryManager> repositoryManagerProvider;

    public Ops2_RawMaterialSetingModel_Factory(Provider<IRepositoryManager> provider, Provider<Gson> provider2, Provider<Application> provider3) {
        this.repositoryManagerProvider = provider;
        this.mGsonProvider = provider2;
        this.mApplicationProvider = provider3;
    }

    public static Ops2_RawMaterialSetingModel_Factory create(Provider<IRepositoryManager> provider, Provider<Gson> provider2, Provider<Application> provider3) {
        return new Ops2_RawMaterialSetingModel_Factory(provider, provider2, provider3);
    }

    public static Ops2_RawMaterialSetingModel newInstance(IRepositoryManager iRepositoryManager) {
        return new Ops2_RawMaterialSetingModel(iRepositoryManager);
    }

    @Override // javax.inject.Provider
    public Ops2_RawMaterialSetingModel get() {
        Ops2_RawMaterialSetingModel newInstance = newInstance(this.repositoryManagerProvider.get());
        Ops2_RawMaterialSetingModel_MembersInjector.injectMGson(newInstance, this.mGsonProvider.get());
        Ops2_RawMaterialSetingModel_MembersInjector.injectMApplication(newInstance, this.mApplicationProvider.get());
        return newInstance;
    }
}
