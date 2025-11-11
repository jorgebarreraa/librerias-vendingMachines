package com.yj.coffeemachines.mvp.model;

import android.app.Application;
import com.google.gson.Gson;
import com.jess.arms.integration.IRepositoryManager;
import dagger.internal.Factory;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class Step3Model_Factory implements Factory<Step3Model> {
    private final Provider<Application> mApplicationProvider;
    private final Provider<Gson> mGsonProvider;
    private final Provider<IRepositoryManager> repositoryManagerProvider;

    public Step3Model_Factory(Provider<IRepositoryManager> provider, Provider<Gson> provider2, Provider<Application> provider3) {
        this.repositoryManagerProvider = provider;
        this.mGsonProvider = provider2;
        this.mApplicationProvider = provider3;
    }

    public static Step3Model_Factory create(Provider<IRepositoryManager> provider, Provider<Gson> provider2, Provider<Application> provider3) {
        return new Step3Model_Factory(provider, provider2, provider3);
    }

    public static Step3Model newInstance(IRepositoryManager iRepositoryManager) {
        return new Step3Model(iRepositoryManager);
    }

    @Override // javax.inject.Provider
    public Step3Model get() {
        Step3Model newInstance = newInstance(this.repositoryManagerProvider.get());
        Step3Model_MembersInjector.injectMGson(newInstance, this.mGsonProvider.get());
        Step3Model_MembersInjector.injectMApplication(newInstance, this.mApplicationProvider.get());
        return newInstance;
    }
}
