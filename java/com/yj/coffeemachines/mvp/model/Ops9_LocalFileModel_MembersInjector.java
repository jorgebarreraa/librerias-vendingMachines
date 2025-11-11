package com.yj.coffeemachines.mvp.model;

import android.app.Application;
import com.google.gson.Gson;
import dagger.MembersInjector;
import dagger.internal.InjectedFieldSignature;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class Ops9_LocalFileModel_MembersInjector implements MembersInjector<Ops9_LocalFileModel> {
    private final Provider<Application> mApplicationProvider;
    private final Provider<Gson> mGsonProvider;

    public Ops9_LocalFileModel_MembersInjector(Provider<Gson> provider, Provider<Application> provider2) {
        this.mGsonProvider = provider;
        this.mApplicationProvider = provider2;
    }

    public static MembersInjector<Ops9_LocalFileModel> create(Provider<Gson> provider, Provider<Application> provider2) {
        return new Ops9_LocalFileModel_MembersInjector(provider, provider2);
    }

    @InjectedFieldSignature("com.yj.coffeemachines.mvp.model.Ops9_LocalFileModel.mApplication")
    public static void injectMApplication(Ops9_LocalFileModel ops9_LocalFileModel, Application application) {
        ops9_LocalFileModel.mApplication = application;
    }

    @InjectedFieldSignature("com.yj.coffeemachines.mvp.model.Ops9_LocalFileModel.mGson")
    public static void injectMGson(Ops9_LocalFileModel ops9_LocalFileModel, Gson gson) {
        ops9_LocalFileModel.mGson = gson;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(Ops9_LocalFileModel ops9_LocalFileModel) {
        injectMGson(ops9_LocalFileModel, this.mGsonProvider.get());
        injectMApplication(ops9_LocalFileModel, this.mApplicationProvider.get());
    }
}
