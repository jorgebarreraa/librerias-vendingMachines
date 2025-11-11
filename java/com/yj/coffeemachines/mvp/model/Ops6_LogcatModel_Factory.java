package com.yj.coffeemachines.mvp.model;

import android.app.Application;
import com.google.gson.Gson;
import com.jess.arms.integration.IRepositoryManager;
import dagger.internal.Factory;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class Ops6_LogcatModel_Factory implements Factory<Ops6_LogcatModel> {
    private final Provider<Application> mApplicationProvider;
    private final Provider<Gson> mGsonProvider;
    private final Provider<IRepositoryManager> repositoryManagerProvider;

    public Ops6_LogcatModel_Factory(Provider<IRepositoryManager> provider, Provider<Gson> provider2, Provider<Application> provider3) {
        this.repositoryManagerProvider = provider;
        this.mGsonProvider = provider2;
        this.mApplicationProvider = provider3;
    }

    public static Ops6_LogcatModel_Factory create(Provider<IRepositoryManager> provider, Provider<Gson> provider2, Provider<Application> provider3) {
        return new Ops6_LogcatModel_Factory(provider, provider2, provider3);
    }

    public static Ops6_LogcatModel newInstance(IRepositoryManager iRepositoryManager) {
        return new Ops6_LogcatModel(iRepositoryManager);
    }

    @Override // javax.inject.Provider
    public Ops6_LogcatModel get() {
        Ops6_LogcatModel newInstance = newInstance(this.repositoryManagerProvider.get());
        Ops6_LogcatModel_MembersInjector.injectMGson(newInstance, this.mGsonProvider.get());
        Ops6_LogcatModel_MembersInjector.injectMApplication(newInstance, this.mApplicationProvider.get());
        return newInstance;
    }
}
