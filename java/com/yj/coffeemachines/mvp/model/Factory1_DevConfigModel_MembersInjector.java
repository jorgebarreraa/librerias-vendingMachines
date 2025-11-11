package com.yj.coffeemachines.mvp.model;

import android.app.Application;
import com.google.gson.Gson;
import dagger.MembersInjector;
import dagger.internal.InjectedFieldSignature;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class Factory1_DevConfigModel_MembersInjector implements MembersInjector<Factory1_DevConfigModel> {
    private final Provider<Application> mApplicationProvider;
    private final Provider<Gson> mGsonProvider;

    public Factory1_DevConfigModel_MembersInjector(Provider<Gson> provider, Provider<Application> provider2) {
        this.mGsonProvider = provider;
        this.mApplicationProvider = provider2;
    }

    public static MembersInjector<Factory1_DevConfigModel> create(Provider<Gson> provider, Provider<Application> provider2) {
        return new Factory1_DevConfigModel_MembersInjector(provider, provider2);
    }

    @InjectedFieldSignature("com.yj.coffeemachines.mvp.model.Factory1_DevConfigModel.mApplication")
    public static void injectMApplication(Factory1_DevConfigModel factory1_DevConfigModel, Application application) {
        factory1_DevConfigModel.mApplication = application;
    }

    @InjectedFieldSignature("com.yj.coffeemachines.mvp.model.Factory1_DevConfigModel.mGson")
    public static void injectMGson(Factory1_DevConfigModel factory1_DevConfigModel, Gson gson) {
        factory1_DevConfigModel.mGson = gson;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(Factory1_DevConfigModel factory1_DevConfigModel) {
        injectMGson(factory1_DevConfigModel, this.mGsonProvider.get());
        injectMApplication(factory1_DevConfigModel, this.mApplicationProvider.get());
    }
}
