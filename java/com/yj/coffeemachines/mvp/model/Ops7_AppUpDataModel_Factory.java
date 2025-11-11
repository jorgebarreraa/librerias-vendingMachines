package com.yj.coffeemachines.mvp.model;

import android.app.Application;
import com.google.gson.Gson;
import com.jess.arms.integration.IRepositoryManager;
import dagger.internal.Factory;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class Ops7_AppUpDataModel_Factory implements Factory<Ops7_AppUpDataModel> {
    private final Provider<Application> mApplicationProvider;
    private final Provider<Gson> mGsonProvider;
    private final Provider<IRepositoryManager> repositoryManagerProvider;

    public Ops7_AppUpDataModel_Factory(Provider<IRepositoryManager> provider, Provider<Gson> provider2, Provider<Application> provider3) {
        this.repositoryManagerProvider = provider;
        this.mGsonProvider = provider2;
        this.mApplicationProvider = provider3;
    }

    public static Ops7_AppUpDataModel_Factory create(Provider<IRepositoryManager> provider, Provider<Gson> provider2, Provider<Application> provider3) {
        return new Ops7_AppUpDataModel_Factory(provider, provider2, provider3);
    }

    public static Ops7_AppUpDataModel newInstance(IRepositoryManager iRepositoryManager) {
        return new Ops7_AppUpDataModel(iRepositoryManager);
    }

    @Override // javax.inject.Provider
    public Ops7_AppUpDataModel get() {
        Ops7_AppUpDataModel newInstance = newInstance(this.repositoryManagerProvider.get());
        Ops7_AppUpDataModel_MembersInjector.injectMGson(newInstance, this.mGsonProvider.get());
        Ops7_AppUpDataModel_MembersInjector.injectMApplication(newInstance, this.mApplicationProvider.get());
        return newInstance;
    }
}
