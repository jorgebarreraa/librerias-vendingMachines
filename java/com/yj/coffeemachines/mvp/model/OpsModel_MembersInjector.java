package com.yj.coffeemachines.mvp.model;

import android.app.Application;
import com.google.gson.Gson;
import dagger.MembersInjector;
import dagger.internal.InjectedFieldSignature;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class OpsModel_MembersInjector implements MembersInjector<OpsModel> {
    private final Provider<Application> mApplicationProvider;
    private final Provider<Gson> mGsonProvider;

    public OpsModel_MembersInjector(Provider<Gson> provider, Provider<Application> provider2) {
        this.mGsonProvider = provider;
        this.mApplicationProvider = provider2;
    }

    public static MembersInjector<OpsModel> create(Provider<Gson> provider, Provider<Application> provider2) {
        return new OpsModel_MembersInjector(provider, provider2);
    }

    @InjectedFieldSignature("com.yj.coffeemachines.mvp.model.OpsModel.mApplication")
    public static void injectMApplication(OpsModel opsModel, Application application) {
        opsModel.mApplication = application;
    }

    @InjectedFieldSignature("com.yj.coffeemachines.mvp.model.OpsModel.mGson")
    public static void injectMGson(OpsModel opsModel, Gson gson) {
        opsModel.mGson = gson;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(OpsModel opsModel) {
        injectMGson(opsModel, this.mGsonProvider.get());
        injectMApplication(opsModel, this.mApplicationProvider.get());
    }
}
