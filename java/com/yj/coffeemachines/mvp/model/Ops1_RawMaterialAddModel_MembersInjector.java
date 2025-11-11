package com.yj.coffeemachines.mvp.model;

import android.app.Application;
import com.google.gson.Gson;
import dagger.MembersInjector;
import dagger.internal.InjectedFieldSignature;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class Ops1_RawMaterialAddModel_MembersInjector implements MembersInjector<Ops1_RawMaterialAddModel> {
    private final Provider<Application> mApplicationProvider;
    private final Provider<Gson> mGsonProvider;

    public Ops1_RawMaterialAddModel_MembersInjector(Provider<Gson> provider, Provider<Application> provider2) {
        this.mGsonProvider = provider;
        this.mApplicationProvider = provider2;
    }

    public static MembersInjector<Ops1_RawMaterialAddModel> create(Provider<Gson> provider, Provider<Application> provider2) {
        return new Ops1_RawMaterialAddModel_MembersInjector(provider, provider2);
    }

    @InjectedFieldSignature("com.yj.coffeemachines.mvp.model.Ops1_RawMaterialAddModel.mApplication")
    public static void injectMApplication(Ops1_RawMaterialAddModel ops1_RawMaterialAddModel, Application application) {
        ops1_RawMaterialAddModel.mApplication = application;
    }

    @InjectedFieldSignature("com.yj.coffeemachines.mvp.model.Ops1_RawMaterialAddModel.mGson")
    public static void injectMGson(Ops1_RawMaterialAddModel ops1_RawMaterialAddModel, Gson gson) {
        ops1_RawMaterialAddModel.mGson = gson;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(Ops1_RawMaterialAddModel ops1_RawMaterialAddModel) {
        injectMGson(ops1_RawMaterialAddModel, this.mGsonProvider.get());
        injectMApplication(ops1_RawMaterialAddModel, this.mApplicationProvider.get());
    }
}
