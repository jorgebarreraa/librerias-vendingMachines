package com.yj.coffeemachines.mvp.model;

import android.app.Application;
import com.google.gson.Gson;
import dagger.MembersInjector;
import dagger.internal.InjectedFieldSignature;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class Step4Model_MembersInjector implements MembersInjector<Step4Model> {
    private final Provider<Application> mApplicationProvider;
    private final Provider<Gson> mGsonProvider;

    public Step4Model_MembersInjector(Provider<Gson> provider, Provider<Application> provider2) {
        this.mGsonProvider = provider;
        this.mApplicationProvider = provider2;
    }

    public static MembersInjector<Step4Model> create(Provider<Gson> provider, Provider<Application> provider2) {
        return new Step4Model_MembersInjector(provider, provider2);
    }

    @InjectedFieldSignature("com.yj.coffeemachines.mvp.model.Step4Model.mApplication")
    public static void injectMApplication(Step4Model step4Model, Application application) {
        step4Model.mApplication = application;
    }

    @InjectedFieldSignature("com.yj.coffeemachines.mvp.model.Step4Model.mGson")
    public static void injectMGson(Step4Model step4Model, Gson gson) {
        step4Model.mGson = gson;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(Step4Model step4Model) {
        injectMGson(step4Model, this.mGsonProvider.get());
        injectMApplication(step4Model, this.mApplicationProvider.get());
    }
}
