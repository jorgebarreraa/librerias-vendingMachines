package com.yj.coffeemachines.mvp.model;

import android.app.Application;
import com.google.gson.Gson;
import dagger.MembersInjector;
import dagger.internal.InjectedFieldSignature;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class SetingsHomeModel_MembersInjector implements MembersInjector<SetingsHomeModel> {
    private final Provider<Application> mApplicationProvider;
    private final Provider<Gson> mGsonProvider;

    public SetingsHomeModel_MembersInjector(Provider<Gson> provider, Provider<Application> provider2) {
        this.mGsonProvider = provider;
        this.mApplicationProvider = provider2;
    }

    public static MembersInjector<SetingsHomeModel> create(Provider<Gson> provider, Provider<Application> provider2) {
        return new SetingsHomeModel_MembersInjector(provider, provider2);
    }

    @InjectedFieldSignature("com.yj.coffeemachines.mvp.model.SetingsHomeModel.mApplication")
    public static void injectMApplication(SetingsHomeModel setingsHomeModel, Application application) {
        setingsHomeModel.mApplication = application;
    }

    @InjectedFieldSignature("com.yj.coffeemachines.mvp.model.SetingsHomeModel.mGson")
    public static void injectMGson(SetingsHomeModel setingsHomeModel, Gson gson) {
        setingsHomeModel.mGson = gson;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(SetingsHomeModel setingsHomeModel) {
        injectMGson(setingsHomeModel, this.mGsonProvider.get());
        injectMApplication(setingsHomeModel, this.mApplicationProvider.get());
    }
}
