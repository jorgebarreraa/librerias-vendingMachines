package com.yj.coffeemachines.mvp.model;

import android.app.Application;
import com.google.gson.Gson;
import com.jess.arms.integration.IRepositoryManager;
import dagger.internal.Factory;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class Factory2_SeralPortConfigModel_Factory implements Factory<Factory2_SeralPortConfigModel> {
    private final Provider<Application> mApplicationProvider;
    private final Provider<Gson> mGsonProvider;
    private final Provider<IRepositoryManager> repositoryManagerProvider;

    public Factory2_SeralPortConfigModel_Factory(Provider<IRepositoryManager> provider, Provider<Gson> provider2, Provider<Application> provider3) {
        this.repositoryManagerProvider = provider;
        this.mGsonProvider = provider2;
        this.mApplicationProvider = provider3;
    }

    public static Factory2_SeralPortConfigModel_Factory create(Provider<IRepositoryManager> provider, Provider<Gson> provider2, Provider<Application> provider3) {
        return new Factory2_SeralPortConfigModel_Factory(provider, provider2, provider3);
    }

    public static Factory2_SeralPortConfigModel newInstance(IRepositoryManager iRepositoryManager) {
        return new Factory2_SeralPortConfigModel(iRepositoryManager);
    }

    @Override // javax.inject.Provider
    public Factory2_SeralPortConfigModel get() {
        Factory2_SeralPortConfigModel newInstance = newInstance(this.repositoryManagerProvider.get());
        Factory2_SeralPortConfigModel_MembersInjector.injectMGson(newInstance, this.mGsonProvider.get());
        Factory2_SeralPortConfigModel_MembersInjector.injectMApplication(newInstance, this.mApplicationProvider.get());
        return newInstance;
    }
}
