package com.yj.coffeemachines.mvp.model;

import android.app.Application;
import com.google.gson.Gson;
import dagger.MembersInjector;
import dagger.internal.InjectedFieldSignature;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class Ops6_LogcatModel_MembersInjector implements MembersInjector<Ops6_LogcatModel> {
    private final Provider<Application> mApplicationProvider;
    private final Provider<Gson> mGsonProvider;

    public Ops6_LogcatModel_MembersInjector(Provider<Gson> provider, Provider<Application> provider2) {
        this.mGsonProvider = provider;
        this.mApplicationProvider = provider2;
    }

    public static MembersInjector<Ops6_LogcatModel> create(Provider<Gson> provider, Provider<Application> provider2) {
        return new Ops6_LogcatModel_MembersInjector(provider, provider2);
    }

    @InjectedFieldSignature("com.yj.coffeemachines.mvp.model.Ops6_LogcatModel.mApplication")
    public static void injectMApplication(Ops6_LogcatModel ops6_LogcatModel, Application application) {
        ops6_LogcatModel.mApplication = application;
    }

    @InjectedFieldSignature("com.yj.coffeemachines.mvp.model.Ops6_LogcatModel.mGson")
    public static void injectMGson(Ops6_LogcatModel ops6_LogcatModel, Gson gson) {
        ops6_LogcatModel.mGson = gson;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(Ops6_LogcatModel ops6_LogcatModel) {
        injectMGson(ops6_LogcatModel, this.mGsonProvider.get());
        injectMApplication(ops6_LogcatModel, this.mApplicationProvider.get());
    }
}
