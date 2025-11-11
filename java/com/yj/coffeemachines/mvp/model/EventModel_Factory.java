package com.yj.coffeemachines.mvp.model;

import android.app.Application;
import com.google.gson.Gson;
import com.jess.arms.integration.IRepositoryManager;
import dagger.internal.Factory;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class EventModel_Factory implements Factory<EventModel> {
    private final Provider<Application> mApplicationProvider;
    private final Provider<Gson> mGsonProvider;
    private final Provider<IRepositoryManager> repositoryManagerProvider;

    public EventModel_Factory(Provider<IRepositoryManager> provider, Provider<Gson> provider2, Provider<Application> provider3) {
        this.repositoryManagerProvider = provider;
        this.mGsonProvider = provider2;
        this.mApplicationProvider = provider3;
    }

    public static EventModel_Factory create(Provider<IRepositoryManager> provider, Provider<Gson> provider2, Provider<Application> provider3) {
        return new EventModel_Factory(provider, provider2, provider3);
    }

    public static EventModel newInstance(IRepositoryManager iRepositoryManager) {
        return new EventModel(iRepositoryManager);
    }

    @Override // javax.inject.Provider
    public EventModel get() {
        EventModel newInstance = newInstance(this.repositoryManagerProvider.get());
        EventModel_MembersInjector.injectMGson(newInstance, this.mGsonProvider.get());
        EventModel_MembersInjector.injectMApplication(newInstance, this.mApplicationProvider.get());
        return newInstance;
    }
}
