package com.yj.coffeemachines.mvp.model;

import android.app.Application;
import com.google.gson.Gson;
import com.jess.arms.integration.IRepositoryManager;
import dagger.internal.Factory;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class JoinVIPModel_Factory implements Factory<JoinVIPModel> {
    private final Provider<Application> mApplicationProvider;
    private final Provider<Gson> mGsonProvider;
    private final Provider<IRepositoryManager> repositoryManagerProvider;

    public JoinVIPModel_Factory(Provider<IRepositoryManager> provider, Provider<Gson> provider2, Provider<Application> provider3) {
        this.repositoryManagerProvider = provider;
        this.mGsonProvider = provider2;
        this.mApplicationProvider = provider3;
    }

    public static JoinVIPModel_Factory create(Provider<IRepositoryManager> provider, Provider<Gson> provider2, Provider<Application> provider3) {
        return new JoinVIPModel_Factory(provider, provider2, provider3);
    }

    public static JoinVIPModel newInstance(IRepositoryManager iRepositoryManager) {
        return new JoinVIPModel(iRepositoryManager);
    }

    @Override // javax.inject.Provider
    public JoinVIPModel get() {
        JoinVIPModel newInstance = newInstance(this.repositoryManagerProvider.get());
        JoinVIPModel_MembersInjector.injectMGson(newInstance, this.mGsonProvider.get());
        JoinVIPModel_MembersInjector.injectMApplication(newInstance, this.mApplicationProvider.get());
        return newInstance;
    }
}
