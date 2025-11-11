package com.yj.coffeemachines.mvp.model;

import android.app.Application;
import com.google.gson.Gson;
import dagger.MembersInjector;
import dagger.internal.InjectedFieldSignature;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class EventModel_MembersInjector implements MembersInjector<EventModel> {
    private final Provider<Application> mApplicationProvider;
    private final Provider<Gson> mGsonProvider;

    public EventModel_MembersInjector(Provider<Gson> provider, Provider<Application> provider2) {
        this.mGsonProvider = provider;
        this.mApplicationProvider = provider2;
    }

    public static MembersInjector<EventModel> create(Provider<Gson> provider, Provider<Application> provider2) {
        return new EventModel_MembersInjector(provider, provider2);
    }

    @InjectedFieldSignature("com.yj.coffeemachines.mvp.model.EventModel.mApplication")
    public static void injectMApplication(EventModel eventModel, Application application) {
        eventModel.mApplication = application;
    }

    @InjectedFieldSignature("com.yj.coffeemachines.mvp.model.EventModel.mGson")
    public static void injectMGson(EventModel eventModel, Gson gson) {
        eventModel.mGson = gson;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(EventModel eventModel) {
        injectMGson(eventModel, this.mGsonProvider.get());
        injectMApplication(eventModel, this.mApplicationProvider.get());
    }
}
