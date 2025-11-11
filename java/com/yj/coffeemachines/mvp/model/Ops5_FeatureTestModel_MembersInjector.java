package com.yj.coffeemachines.mvp.model;

import android.app.Application;
import com.google.gson.Gson;
import dagger.MembersInjector;
import dagger.internal.InjectedFieldSignature;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class Ops5_FeatureTestModel_MembersInjector implements MembersInjector<Ops5_FeatureTestModel> {
    private final Provider<Application> mApplicationProvider;
    private final Provider<Gson> mGsonProvider;

    public Ops5_FeatureTestModel_MembersInjector(Provider<Gson> provider, Provider<Application> provider2) {
        this.mGsonProvider = provider;
        this.mApplicationProvider = provider2;
    }

    public static MembersInjector<Ops5_FeatureTestModel> create(Provider<Gson> provider, Provider<Application> provider2) {
        return new Ops5_FeatureTestModel_MembersInjector(provider, provider2);
    }

    @InjectedFieldSignature("com.yj.coffeemachines.mvp.model.Ops5_FeatureTestModel.mApplication")
    public static void injectMApplication(Ops5_FeatureTestModel ops5_FeatureTestModel, Application application) {
        ops5_FeatureTestModel.mApplication = application;
    }

    @InjectedFieldSignature("com.yj.coffeemachines.mvp.model.Ops5_FeatureTestModel.mGson")
    public static void injectMGson(Ops5_FeatureTestModel ops5_FeatureTestModel, Gson gson) {
        ops5_FeatureTestModel.mGson = gson;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(Ops5_FeatureTestModel ops5_FeatureTestModel) {
        injectMGson(ops5_FeatureTestModel, this.mGsonProvider.get());
        injectMApplication(ops5_FeatureTestModel, this.mApplicationProvider.get());
    }
}
