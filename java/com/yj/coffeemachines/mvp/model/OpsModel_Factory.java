package com.yj.coffeemachines.mvp.model;

import android.app.Application;
import com.google.gson.Gson;
import com.jess.arms.integration.IRepositoryManager;
import dagger.internal.Factory;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class OpsModel_Factory implements Factory<OpsModel> {
    private final Provider<Application> mApplicationProvider;
    private final Provider<Gson> mGsonProvider;
    private final Provider<IRepositoryManager> repositoryManagerProvider;

    public OpsModel_Factory(Provider<IRepositoryManager> provider, Provider<Gson> provider2, Provider<Application> provider3) {
        this.repositoryManagerProvider = provider;
        this.mGsonProvider = provider2;
        this.mApplicationProvider = provider3;
    }

    public static OpsModel_Factory create(Provider<IRepositoryManager> provider, Provider<Gson> provider2, Provider<Application> provider3) {
        return new OpsModel_Factory(provider, provider2, provider3);
    }

    public static OpsModel newInstance(IRepositoryManager iRepositoryManager) {
        return new OpsModel(iRepositoryManager);
    }

    @Override // javax.inject.Provider
    public OpsModel get() {
        OpsModel newInstance = newInstance(this.repositoryManagerProvider.get());
        OpsModel_MembersInjector.injectMGson(newInstance, this.mGsonProvider.get());
        OpsModel_MembersInjector.injectMApplication(newInstance, this.mApplicationProvider.get());
        return newInstance;
    }
}
