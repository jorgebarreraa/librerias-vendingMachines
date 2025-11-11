package com.yj.coffeemachines.mvp.model;

import android.app.Application;
import com.google.gson.Gson;
import dagger.MembersInjector;
import dagger.internal.InjectedFieldSignature;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class Step1Model_MembersInjector implements MembersInjector<Step1Model> {
    private final Provider<Application> mApplicationProvider;
    private final Provider<Gson> mGsonProvider;

    public Step1Model_MembersInjector(Provider<Gson> provider, Provider<Application> provider2) {
        this.mGsonProvider = provider;
        this.mApplicationProvider = provider2;
    }

    public static MembersInjector<Step1Model> create(Provider<Gson> provider, Provider<Application> provider2) {
        return new Step1Model_MembersInjector(provider, provider2);
    }

    @InjectedFieldSignature("com.yj.coffeemachines.mvp.model.Step1Model.mApplication")
    public static void injectMApplication(Step1Model step1Model, Application application) {
        step1Model.mApplication = application;
    }

    @InjectedFieldSignature("com.yj.coffeemachines.mvp.model.Step1Model.mGson")
    public static void injectMGson(Step1Model step1Model, Gson gson) {
        step1Model.mGson = gson;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(Step1Model step1Model) {
        injectMGson(step1Model, this.mGsonProvider.get());
        injectMApplication(step1Model, this.mApplicationProvider.get());
    }
}
