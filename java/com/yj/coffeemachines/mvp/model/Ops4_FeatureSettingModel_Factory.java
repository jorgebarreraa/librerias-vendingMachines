package com.yj.coffeemachines.mvp.model;

import android.app.Application;
import com.google.gson.Gson;
import com.jess.arms.integration.IRepositoryManager;
import dagger.internal.Factory;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class Ops4_FeatureSettingModel_Factory implements Factory<Ops4_FeatureSettingModel> {
    private final Provider<Application> mApplicationProvider;
    private final Provider<Gson> mGsonProvider;
    private final Provider<IRepositoryManager> repositoryManagerProvider;

    public Ops4_FeatureSettingModel_Factory(Provider<IRepositoryManager> provider, Provider<Gson> provider2, Provider<Application> provider3) {
        this.repositoryManagerProvider = provider;
        this.mGsonProvider = provider2;
        this.mApplicationProvider = provider3;
    }

    public static Ops4_FeatureSettingModel_Factory create(Provider<IRepositoryManager> provider, Provider<Gson> provider2, Provider<Application> provider3) {
        return new Ops4_FeatureSettingModel_Factory(provider, provider2, provider3);
    }

    public static Ops4_FeatureSettingModel newInstance(IRepositoryManager iRepositoryManager) {
        return new Ops4_FeatureSettingModel(iRepositoryManager);
    }

    @Override // javax.inject.Provider
    public Ops4_FeatureSettingModel get() {
        Ops4_FeatureSettingModel newInstance = newInstance(this.repositoryManagerProvider.get());
        Ops4_FeatureSettingModel_MembersInjector.injectMGson(newInstance, this.mGsonProvider.get());
        Ops4_FeatureSettingModel_MembersInjector.injectMApplication(newInstance, this.mApplicationProvider.get());
        return newInstance;
    }
}
