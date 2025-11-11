package com.yj.coffeemachines.mvp.model;

import android.app.Application;
import com.google.gson.Gson;
import dagger.MembersInjector;
import dagger.internal.InjectedFieldSignature;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class HomeModel_MembersInjector implements MembersInjector<HomeModel> {
    private final Provider<Application> mApplicationProvider;
    private final Provider<Gson> mGsonProvider;

    public HomeModel_MembersInjector(Provider<Gson> provider, Provider<Application> provider2) {
        this.mGsonProvider = provider;
        this.mApplicationProvider = provider2;
    }

    public static MembersInjector<HomeModel> create(Provider<Gson> provider, Provider<Application> provider2) {
        return new HomeModel_MembersInjector(provider, provider2);
    }

    @InjectedFieldSignature("com.yj.coffeemachines.mvp.model.HomeModel.mApplication")
    public static void injectMApplication(HomeModel homeModel, Application application) {
        homeModel.mApplication = application;
    }

    @InjectedFieldSignature("com.yj.coffeemachines.mvp.model.HomeModel.mGson")
    public static void injectMGson(HomeModel homeModel, Gson gson) {
        homeModel.mGson = gson;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(HomeModel homeModel) {
        injectMGson(homeModel, this.mGsonProvider.get());
        injectMApplication(homeModel, this.mApplicationProvider.get());
    }
}
