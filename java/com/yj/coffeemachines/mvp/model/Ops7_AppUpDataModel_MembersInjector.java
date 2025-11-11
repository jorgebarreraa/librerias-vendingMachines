package com.yj.coffeemachines.mvp.model;

import android.app.Application;
import com.google.gson.Gson;
import dagger.MembersInjector;
import dagger.internal.InjectedFieldSignature;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class Ops7_AppUpDataModel_MembersInjector implements MembersInjector<Ops7_AppUpDataModel> {
    private final Provider<Application> mApplicationProvider;
    private final Provider<Gson> mGsonProvider;

    public Ops7_AppUpDataModel_MembersInjector(Provider<Gson> provider, Provider<Application> provider2) {
        this.mGsonProvider = provider;
        this.mApplicationProvider = provider2;
    }

    public static MembersInjector<Ops7_AppUpDataModel> create(Provider<Gson> provider, Provider<Application> provider2) {
        return new Ops7_AppUpDataModel_MembersInjector(provider, provider2);
    }

    @InjectedFieldSignature("com.yj.coffeemachines.mvp.model.Ops7_AppUpDataModel.mApplication")
    public static void injectMApplication(Ops7_AppUpDataModel ops7_AppUpDataModel, Application application) {
        ops7_AppUpDataModel.mApplication = application;
    }

    @InjectedFieldSignature("com.yj.coffeemachines.mvp.model.Ops7_AppUpDataModel.mGson")
    public static void injectMGson(Ops7_AppUpDataModel ops7_AppUpDataModel, Gson gson) {
        ops7_AppUpDataModel.mGson = gson;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(Ops7_AppUpDataModel ops7_AppUpDataModel) {
        injectMGson(ops7_AppUpDataModel, this.mGsonProvider.get());
        injectMApplication(ops7_AppUpDataModel, this.mApplicationProvider.get());
    }
}
