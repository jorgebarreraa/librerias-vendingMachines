package com.yj.coffeemachines.mvp.model;

import android.app.Application;
import com.google.gson.Gson;
import com.jess.arms.integration.IRepositoryManager;
import dagger.internal.Factory;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class Step1Model_Factory implements Factory<Step1Model> {
    private final Provider<Application> mApplicationProvider;
    private final Provider<Gson> mGsonProvider;
    private final Provider<IRepositoryManager> repositoryManagerProvider;

    public Step1Model_Factory(Provider<IRepositoryManager> provider, Provider<Gson> provider2, Provider<Application> provider3) {
        this.repositoryManagerProvider = provider;
        this.mGsonProvider = provider2;
        this.mApplicationProvider = provider3;
    }

    public static Step1Model_Factory create(Provider<IRepositoryManager> provider, Provider<Gson> provider2, Provider<Application> provider3) {
        return new Step1Model_Factory(provider, provider2, provider3);
    }

    public static Step1Model newInstance(IRepositoryManager iRepositoryManager) {
        return new Step1Model(iRepositoryManager);
    }

    @Override // javax.inject.Provider
    public Step1Model get() {
        Step1Model newInstance = newInstance(this.repositoryManagerProvider.get());
        Step1Model_MembersInjector.injectMGson(newInstance, this.mGsonProvider.get());
        Step1Model_MembersInjector.injectMApplication(newInstance, this.mApplicationProvider.get());
        return newInstance;
    }
}
