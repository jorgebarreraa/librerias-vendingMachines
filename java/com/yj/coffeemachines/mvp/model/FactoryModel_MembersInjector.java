package com.yj.coffeemachines.mvp.model;

import android.app.Application;
import com.google.gson.Gson;
import dagger.MembersInjector;
import dagger.internal.InjectedFieldSignature;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class FactoryModel_MembersInjector implements MembersInjector<FactoryModel> {
    private final Provider<Application> mApplicationProvider;
    private final Provider<Gson> mGsonProvider;

    public FactoryModel_MembersInjector(Provider<Gson> provider, Provider<Application> provider2) {
        this.mGsonProvider = provider;
        this.mApplicationProvider = provider2;
    }

    public static MembersInjector<FactoryModel> create(Provider<Gson> provider, Provider<Application> provider2) {
        return new FactoryModel_MembersInjector(provider, provider2);
    }

    @InjectedFieldSignature("com.yj.coffeemachines.mvp.model.FactoryModel.mApplication")
    public static void injectMApplication(FactoryModel factoryModel, Application application) {
        factoryModel.mApplication = application;
    }

    @InjectedFieldSignature("com.yj.coffeemachines.mvp.model.FactoryModel.mGson")
    public static void injectMGson(FactoryModel factoryModel, Gson gson) {
        factoryModel.mGson = gson;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(FactoryModel factoryModel) {
        injectMGson(factoryModel, this.mGsonProvider.get());
        injectMApplication(factoryModel, this.mApplicationProvider.get());
    }
}
