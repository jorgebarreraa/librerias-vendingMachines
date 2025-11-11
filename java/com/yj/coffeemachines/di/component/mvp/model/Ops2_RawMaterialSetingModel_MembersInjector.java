package com.yj.coffeemachines.di.component.mvp.model;

import android.app.Application;
import com.google.gson.Gson;
import dagger.MembersInjector;
import dagger.internal.InjectedFieldSignature;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class Ops2_RawMaterialSetingModel_MembersInjector implements MembersInjector<Ops2_RawMaterialSetingModel> {
    private final Provider<Application> mApplicationProvider;
    private final Provider<Gson> mGsonProvider;

    public Ops2_RawMaterialSetingModel_MembersInjector(Provider<Gson> provider, Provider<Application> provider2) {
        this.mGsonProvider = provider;
        this.mApplicationProvider = provider2;
    }

    public static MembersInjector<Ops2_RawMaterialSetingModel> create(Provider<Gson> provider, Provider<Application> provider2) {
        return new Ops2_RawMaterialSetingModel_MembersInjector(provider, provider2);
    }

    @InjectedFieldSignature("com.yj.coffeemachines.di.component.mvp.model.Ops2_RawMaterialSetingModel.mApplication")
    public static void injectMApplication(Ops2_RawMaterialSetingModel ops2_RawMaterialSetingModel, Application application) {
        ops2_RawMaterialSetingModel.mApplication = application;
    }

    @InjectedFieldSignature("com.yj.coffeemachines.di.component.mvp.model.Ops2_RawMaterialSetingModel.mGson")
    public static void injectMGson(Ops2_RawMaterialSetingModel ops2_RawMaterialSetingModel, Gson gson) {
        ops2_RawMaterialSetingModel.mGson = gson;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(Ops2_RawMaterialSetingModel ops2_RawMaterialSetingModel) {
        injectMGson(ops2_RawMaterialSetingModel, this.mGsonProvider.get());
        injectMApplication(ops2_RawMaterialSetingModel, this.mApplicationProvider.get());
    }
}
