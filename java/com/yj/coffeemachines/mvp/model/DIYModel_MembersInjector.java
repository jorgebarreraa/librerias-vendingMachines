package com.yj.coffeemachines.mvp.model;

import android.app.Application;
import com.google.gson.Gson;
import dagger.MembersInjector;
import dagger.internal.InjectedFieldSignature;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class DIYModel_MembersInjector implements MembersInjector<DIYModel> {
    private final Provider<Application> mApplicationProvider;
    private final Provider<Gson> mGsonProvider;

    public DIYModel_MembersInjector(Provider<Gson> provider, Provider<Application> provider2) {
        this.mGsonProvider = provider;
        this.mApplicationProvider = provider2;
    }

    public static MembersInjector<DIYModel> create(Provider<Gson> provider, Provider<Application> provider2) {
        return new DIYModel_MembersInjector(provider, provider2);
    }

    @InjectedFieldSignature("com.yj.coffeemachines.mvp.model.DIYModel.mApplication")
    public static void injectMApplication(DIYModel dIYModel, Application application) {
        dIYModel.mApplication = application;
    }

    @InjectedFieldSignature("com.yj.coffeemachines.mvp.model.DIYModel.mGson")
    public static void injectMGson(DIYModel dIYModel, Gson gson) {
        dIYModel.mGson = gson;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(DIYModel dIYModel) {
        injectMGson(dIYModel, this.mGsonProvider.get());
        injectMApplication(dIYModel, this.mApplicationProvider.get());
    }
}
