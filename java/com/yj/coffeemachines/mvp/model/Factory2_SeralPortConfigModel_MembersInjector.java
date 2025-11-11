package com.yj.coffeemachines.mvp.model;

import android.app.Application;
import com.google.gson.Gson;
import dagger.MembersInjector;
import dagger.internal.InjectedFieldSignature;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class Factory2_SeralPortConfigModel_MembersInjector implements MembersInjector<Factory2_SeralPortConfigModel> {
    private final Provider<Application> mApplicationProvider;
    private final Provider<Gson> mGsonProvider;

    public Factory2_SeralPortConfigModel_MembersInjector(Provider<Gson> provider, Provider<Application> provider2) {
        this.mGsonProvider = provider;
        this.mApplicationProvider = provider2;
    }

    public static MembersInjector<Factory2_SeralPortConfigModel> create(Provider<Gson> provider, Provider<Application> provider2) {
        return new Factory2_SeralPortConfigModel_MembersInjector(provider, provider2);
    }

    @InjectedFieldSignature("com.yj.coffeemachines.mvp.model.Factory2_SeralPortConfigModel.mApplication")
    public static void injectMApplication(Factory2_SeralPortConfigModel factory2_SeralPortConfigModel, Application application) {
        factory2_SeralPortConfigModel.mApplication = application;
    }

    @InjectedFieldSignature("com.yj.coffeemachines.mvp.model.Factory2_SeralPortConfigModel.mGson")
    public static void injectMGson(Factory2_SeralPortConfigModel factory2_SeralPortConfigModel, Gson gson) {
        factory2_SeralPortConfigModel.mGson = gson;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(Factory2_SeralPortConfigModel factory2_SeralPortConfigModel) {
        injectMGson(factory2_SeralPortConfigModel, this.mGsonProvider.get());
        injectMApplication(factory2_SeralPortConfigModel, this.mApplicationProvider.get());
    }
}
