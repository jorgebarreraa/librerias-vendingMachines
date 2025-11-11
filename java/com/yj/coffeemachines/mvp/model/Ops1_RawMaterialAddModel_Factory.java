package com.yj.coffeemachines.mvp.model;

import android.app.Application;
import com.google.gson.Gson;
import com.jess.arms.integration.IRepositoryManager;
import dagger.internal.Factory;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class Ops1_RawMaterialAddModel_Factory implements Factory<Ops1_RawMaterialAddModel> {
    private final Provider<Application> mApplicationProvider;
    private final Provider<Gson> mGsonProvider;
    private final Provider<IRepositoryManager> repositoryManagerProvider;

    public Ops1_RawMaterialAddModel_Factory(Provider<IRepositoryManager> provider, Provider<Gson> provider2, Provider<Application> provider3) {
        this.repositoryManagerProvider = provider;
        this.mGsonProvider = provider2;
        this.mApplicationProvider = provider3;
    }

    public static Ops1_RawMaterialAddModel_Factory create(Provider<IRepositoryManager> provider, Provider<Gson> provider2, Provider<Application> provider3) {
        return new Ops1_RawMaterialAddModel_Factory(provider, provider2, provider3);
    }

    public static Ops1_RawMaterialAddModel newInstance(IRepositoryManager iRepositoryManager) {
        return new Ops1_RawMaterialAddModel(iRepositoryManager);
    }

    @Override // javax.inject.Provider
    public Ops1_RawMaterialAddModel get() {
        Ops1_RawMaterialAddModel newInstance = newInstance(this.repositoryManagerProvider.get());
        Ops1_RawMaterialAddModel_MembersInjector.injectMGson(newInstance, this.mGsonProvider.get());
        Ops1_RawMaterialAddModel_MembersInjector.injectMApplication(newInstance, this.mApplicationProvider.get());
        return newInstance;
    }
}
