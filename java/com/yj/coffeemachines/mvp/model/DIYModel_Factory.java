package com.yj.coffeemachines.mvp.model;

import android.app.Application;
import com.google.gson.Gson;
import com.jess.arms.integration.IRepositoryManager;
import dagger.internal.Factory;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class DIYModel_Factory implements Factory<DIYModel> {
    private final Provider<Application> mApplicationProvider;
    private final Provider<Gson> mGsonProvider;
    private final Provider<IRepositoryManager> repositoryManagerProvider;

    public DIYModel_Factory(Provider<IRepositoryManager> provider, Provider<Gson> provider2, Provider<Application> provider3) {
        this.repositoryManagerProvider = provider;
        this.mGsonProvider = provider2;
        this.mApplicationProvider = provider3;
    }

    public static DIYModel_Factory create(Provider<IRepositoryManager> provider, Provider<Gson> provider2, Provider<Application> provider3) {
        return new DIYModel_Factory(provider, provider2, provider3);
    }

    public static DIYModel newInstance(IRepositoryManager iRepositoryManager) {
        return new DIYModel(iRepositoryManager);
    }

    @Override // javax.inject.Provider
    public DIYModel get() {
        DIYModel newInstance = newInstance(this.repositoryManagerProvider.get());
        DIYModel_MembersInjector.injectMGson(newInstance, this.mGsonProvider.get());
        DIYModel_MembersInjector.injectMApplication(newInstance, this.mApplicationProvider.get());
        return newInstance;
    }
}
