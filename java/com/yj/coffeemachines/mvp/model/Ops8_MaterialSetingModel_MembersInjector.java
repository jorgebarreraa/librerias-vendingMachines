package com.yj.coffeemachines.mvp.model;

import android.app.Application;
import com.google.gson.Gson;
import dagger.MembersInjector;
import dagger.internal.InjectedFieldSignature;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class Ops8_MaterialSetingModel_MembersInjector implements MembersInjector<Ops8_MaterialSetingModel> {
    private final Provider<Application> mApplicationProvider;
    private final Provider<Gson> mGsonProvider;

    public Ops8_MaterialSetingModel_MembersInjector(Provider<Gson> provider, Provider<Application> provider2) {
        this.mGsonProvider = provider;
        this.mApplicationProvider = provider2;
    }

    public static MembersInjector<Ops8_MaterialSetingModel> create(Provider<Gson> provider, Provider<Application> provider2) {
        return new Ops8_MaterialSetingModel_MembersInjector(provider, provider2);
    }

    @InjectedFieldSignature("com.yj.coffeemachines.mvp.model.Ops8_MaterialSetingModel.mApplication")
    public static void injectMApplication(Ops8_MaterialSetingModel ops8_MaterialSetingModel, Application application) {
        ops8_MaterialSetingModel.mApplication = application;
    }

    @InjectedFieldSignature("com.yj.coffeemachines.mvp.model.Ops8_MaterialSetingModel.mGson")
    public static void injectMGson(Ops8_MaterialSetingModel ops8_MaterialSetingModel, Gson gson) {
        ops8_MaterialSetingModel.mGson = gson;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(Ops8_MaterialSetingModel ops8_MaterialSetingModel) {
        injectMGson(ops8_MaterialSetingModel, this.mGsonProvider.get());
        injectMApplication(ops8_MaterialSetingModel, this.mApplicationProvider.get());
    }
}
