package com.yj.coffeemachines.mvp.model;

import android.app.Application;
import com.google.gson.Gson;
import com.jess.arms.integration.IRepositoryManager;
import dagger.internal.Factory;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class SetingsHomeModel_Factory implements Factory<SetingsHomeModel> {
    private final Provider<Application> mApplicationProvider;
    private final Provider<Gson> mGsonProvider;
    private final Provider<IRepositoryManager> repositoryManagerProvider;

    public SetingsHomeModel_Factory(Provider<IRepositoryManager> provider, Provider<Gson> provider2, Provider<Application> provider3) {
        this.repositoryManagerProvider = provider;
        this.mGsonProvider = provider2;
        this.mApplicationProvider = provider3;
    }

    public static SetingsHomeModel_Factory create(Provider<IRepositoryManager> provider, Provider<Gson> provider2, Provider<Application> provider3) {
        return new SetingsHomeModel_Factory(provider, provider2, provider3);
    }

    public static SetingsHomeModel newInstance(IRepositoryManager iRepositoryManager) {
        return new SetingsHomeModel(iRepositoryManager);
    }

    @Override // javax.inject.Provider
    public SetingsHomeModel get() {
        SetingsHomeModel newInstance = newInstance(this.repositoryManagerProvider.get());
        SetingsHomeModel_MembersInjector.injectMGson(newInstance, this.mGsonProvider.get());
        SetingsHomeModel_MembersInjector.injectMApplication(newInstance, this.mApplicationProvider.get());
        return newInstance;
    }
}
