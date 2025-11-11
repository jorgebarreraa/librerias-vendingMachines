package com.yj.coffeemachines.mvp.model;

import android.app.Application;
import com.google.gson.Gson;
import dagger.MembersInjector;
import dagger.internal.InjectedFieldSignature;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class Ops3_WaterSetingModel_MembersInjector implements MembersInjector<Ops3_WaterSetingModel> {
    private final Provider<Application> mApplicationProvider;
    private final Provider<Gson> mGsonProvider;

    public Ops3_WaterSetingModel_MembersInjector(Provider<Gson> provider, Provider<Application> provider2) {
        this.mGsonProvider = provider;
        this.mApplicationProvider = provider2;
    }

    public static MembersInjector<Ops3_WaterSetingModel> create(Provider<Gson> provider, Provider<Application> provider2) {
        return new Ops3_WaterSetingModel_MembersInjector(provider, provider2);
    }

    @InjectedFieldSignature("com.yj.coffeemachines.mvp.model.Ops3_WaterSetingModel.mApplication")
    public static void injectMApplication(Ops3_WaterSetingModel ops3_WaterSetingModel, Application application) {
        ops3_WaterSetingModel.mApplication = application;
    }

    @InjectedFieldSignature("com.yj.coffeemachines.mvp.model.Ops3_WaterSetingModel.mGson")
    public static void injectMGson(Ops3_WaterSetingModel ops3_WaterSetingModel, Gson gson) {
        ops3_WaterSetingModel.mGson = gson;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(Ops3_WaterSetingModel ops3_WaterSetingModel) {
        injectMGson(ops3_WaterSetingModel, this.mGsonProvider.get());
        injectMApplication(ops3_WaterSetingModel, this.mApplicationProvider.get());
    }
}
