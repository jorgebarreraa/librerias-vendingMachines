package com.yj.coffeemachines.mvp.model;

import android.app.Application;
import com.google.gson.Gson;
import com.jess.arms.integration.IRepositoryManager;
import dagger.internal.Factory;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class LogModel_Factory implements Factory<LogModel> {
    private final Provider<Application> mApplicationProvider;
    private final Provider<Gson> mGsonProvider;
    private final Provider<IRepositoryManager> repositoryManagerProvider;

    public LogModel_Factory(Provider<IRepositoryManager> provider, Provider<Gson> provider2, Provider<Application> provider3) {
        this.repositoryManagerProvider = provider;
        this.mGsonProvider = provider2;
        this.mApplicationProvider = provider3;
    }

    public static LogModel_Factory create(Provider<IRepositoryManager> provider, Provider<Gson> provider2, Provider<Application> provider3) {
        return new LogModel_Factory(provider, provider2, provider3);
    }

    public static LogModel newInstance(IRepositoryManager iRepositoryManager) {
        return new LogModel(iRepositoryManager);
    }

    @Override // javax.inject.Provider
    public LogModel get() {
        LogModel newInstance = newInstance(this.repositoryManagerProvider.get());
        LogModel_MembersInjector.injectMGson(newInstance, this.mGsonProvider.get());
        LogModel_MembersInjector.injectMApplication(newInstance, this.mApplicationProvider.get());
        return newInstance;
    }
}
