package com.yj.coffeemachines.mvp.model;

import android.app.Application;
import com.google.gson.Gson;
import dagger.MembersInjector;
import dagger.internal.InjectedFieldSignature;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class JoinVIPModel_MembersInjector implements MembersInjector<JoinVIPModel> {
    private final Provider<Application> mApplicationProvider;
    private final Provider<Gson> mGsonProvider;

    public JoinVIPModel_MembersInjector(Provider<Gson> provider, Provider<Application> provider2) {
        this.mGsonProvider = provider;
        this.mApplicationProvider = provider2;
    }

    public static MembersInjector<JoinVIPModel> create(Provider<Gson> provider, Provider<Application> provider2) {
        return new JoinVIPModel_MembersInjector(provider, provider2);
    }

    @InjectedFieldSignature("com.yj.coffeemachines.mvp.model.JoinVIPModel.mApplication")
    public static void injectMApplication(JoinVIPModel joinVIPModel, Application application) {
        joinVIPModel.mApplication = application;
    }

    @InjectedFieldSignature("com.yj.coffeemachines.mvp.model.JoinVIPModel.mGson")
    public static void injectMGson(JoinVIPModel joinVIPModel, Gson gson) {
        joinVIPModel.mGson = gson;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(JoinVIPModel joinVIPModel) {
        injectMGson(joinVIPModel, this.mGsonProvider.get());
        injectMApplication(joinVIPModel, this.mApplicationProvider.get());
    }
}
