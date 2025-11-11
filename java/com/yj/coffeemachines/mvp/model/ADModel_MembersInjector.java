package com.yj.coffeemachines.mvp.model;

import android.app.Application;
import com.google.gson.Gson;
import dagger.MembersInjector;
import dagger.internal.InjectedFieldSignature;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class ADModel_MembersInjector implements MembersInjector<ADModel> {
    private final Provider<Application> mApplicationProvider;
    private final Provider<Gson> mGsonProvider;

    public ADModel_MembersInjector(Provider<Gson> provider, Provider<Application> provider2) {
        this.mGsonProvider = provider;
        this.mApplicationProvider = provider2;
    }

    public static MembersInjector<ADModel> create(Provider<Gson> provider, Provider<Application> provider2) {
        return new ADModel_MembersInjector(provider, provider2);
    }

    @InjectedFieldSignature("com.yj.coffeemachines.mvp.model.ADModel.mApplication")
    public static void injectMApplication(ADModel aDModel, Application application) {
        aDModel.mApplication = application;
    }

    @InjectedFieldSignature("com.yj.coffeemachines.mvp.model.ADModel.mGson")
    public static void injectMGson(ADModel aDModel, Gson gson) {
        aDModel.mGson = gson;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(ADModel aDModel) {
        injectMGson(aDModel, this.mGsonProvider.get());
        injectMApplication(aDModel, this.mApplicationProvider.get());
    }
}
