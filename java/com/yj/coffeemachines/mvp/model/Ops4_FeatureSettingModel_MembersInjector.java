package com.yj.coffeemachines.mvp.model;

import android.app.Application;
import com.google.gson.Gson;
import dagger.MembersInjector;
import dagger.internal.InjectedFieldSignature;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class Ops4_FeatureSettingModel_MembersInjector implements MembersInjector<Ops4_FeatureSettingModel> {
    private final Provider<Application> mApplicationProvider;
    private final Provider<Gson> mGsonProvider;

    public Ops4_FeatureSettingModel_MembersInjector(Provider<Gson> provider, Provider<Application> provider2) {
        this.mGsonProvider = provider;
        this.mApplicationProvider = provider2;
    }

    public static MembersInjector<Ops4_FeatureSettingModel> create(Provider<Gson> provider, Provider<Application> provider2) {
        return new Ops4_FeatureSettingModel_MembersInjector(provider, provider2);
    }

    @InjectedFieldSignature("com.yj.coffeemachines.mvp.model.Ops4_FeatureSettingModel.mApplication")
    public static void injectMApplication(Ops4_FeatureSettingModel ops4_FeatureSettingModel, Application application) {
        ops4_FeatureSettingModel.mApplication = application;
    }

    @InjectedFieldSignature("com.yj.coffeemachines.mvp.model.Ops4_FeatureSettingModel.mGson")
    public static void injectMGson(Ops4_FeatureSettingModel ops4_FeatureSettingModel, Gson gson) {
        ops4_FeatureSettingModel.mGson = gson;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(Ops4_FeatureSettingModel ops4_FeatureSettingModel) {
        injectMGson(ops4_FeatureSettingModel, this.mGsonProvider.get());
        injectMApplication(ops4_FeatureSettingModel, this.mApplicationProvider.get());
    }
}
