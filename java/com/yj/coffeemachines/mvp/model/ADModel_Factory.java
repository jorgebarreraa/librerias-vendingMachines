package com.yj.coffeemachines.mvp.model;

import android.app.Application;
import com.google.gson.Gson;
import com.jess.arms.integration.IRepositoryManager;
import dagger.internal.Factory;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class ADModel_Factory implements Factory<ADModel> {
    private final Provider<Application> mApplicationProvider;
    private final Provider<Gson> mGsonProvider;
    private final Provider<IRepositoryManager> repositoryManagerProvider;

    public ADModel_Factory(Provider<IRepositoryManager> provider, Provider<Gson> provider2, Provider<Application> provider3) {
        this.repositoryManagerProvider = provider;
        this.mGsonProvider = provider2;
        this.mApplicationProvider = provider3;
    }

    public static ADModel_Factory create(Provider<IRepositoryManager> provider, Provider<Gson> provider2, Provider<Application> provider3) {
        return new ADModel_Factory(provider, provider2, provider3);
    }

    public static ADModel newInstance(IRepositoryManager iRepositoryManager) {
        return new ADModel(iRepositoryManager);
    }

    @Override // javax.inject.Provider
    public ADModel get() {
        ADModel newInstance = newInstance(this.repositoryManagerProvider.get());
        ADModel_MembersInjector.injectMGson(newInstance, this.mGsonProvider.get());
        ADModel_MembersInjector.injectMApplication(newInstance, this.mApplicationProvider.get());
        return newInstance;
    }
}
