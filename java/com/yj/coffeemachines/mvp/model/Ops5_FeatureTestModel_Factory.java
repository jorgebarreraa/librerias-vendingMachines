package com.yj.coffeemachines.mvp.model;

import android.app.Application;
import com.google.gson.Gson;
import com.jess.arms.integration.IRepositoryManager;
import dagger.internal.Factory;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class Ops5_FeatureTestModel_Factory implements Factory<Ops5_FeatureTestModel> {
    private final Provider<Application> mApplicationProvider;
    private final Provider<Gson> mGsonProvider;
    private final Provider<IRepositoryManager> repositoryManagerProvider;

    public Ops5_FeatureTestModel_Factory(Provider<IRepositoryManager> provider, Provider<Gson> provider2, Provider<Application> provider3) {
        this.repositoryManagerProvider = provider;
        this.mGsonProvider = provider2;
        this.mApplicationProvider = provider3;
    }

    public static Ops5_FeatureTestModel_Factory create(Provider<IRepositoryManager> provider, Provider<Gson> provider2, Provider<Application> provider3) {
        return new Ops5_FeatureTestModel_Factory(provider, provider2, provider3);
    }

    public static Ops5_FeatureTestModel newInstance(IRepositoryManager iRepositoryManager) {
        return new Ops5_FeatureTestModel(iRepositoryManager);
    }

    @Override // javax.inject.Provider
    public Ops5_FeatureTestModel get() {
        Ops5_FeatureTestModel newInstance = newInstance(this.repositoryManagerProvider.get());
        Ops5_FeatureTestModel_MembersInjector.injectMGson(newInstance, this.mGsonProvider.get());
        Ops5_FeatureTestModel_MembersInjector.injectMApplication(newInstance, this.mApplicationProvider.get());
        return newInstance;
    }
}
