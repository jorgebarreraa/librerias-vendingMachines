package com.yj.coffeemachines.mvp.model;

import android.app.Application;
import com.google.gson.Gson;
import dagger.MembersInjector;
import dagger.internal.InjectedFieldSignature;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class Step3Model_MembersInjector implements MembersInjector<Step3Model> {
    private final Provider<Application> mApplicationProvider;
    private final Provider<Gson> mGsonProvider;

    public Step3Model_MembersInjector(Provider<Gson> provider, Provider<Application> provider2) {
        this.mGsonProvider = provider;
        this.mApplicationProvider = provider2;
    }

    public static MembersInjector<Step3Model> create(Provider<Gson> provider, Provider<Application> provider2) {
        return new Step3Model_MembersInjector(provider, provider2);
    }

    @InjectedFieldSignature("com.yj.coffeemachines.mvp.model.Step3Model.mApplication")
    public static void injectMApplication(Step3Model step3Model, Application application) {
        step3Model.mApplication = application;
    }

    @InjectedFieldSignature("com.yj.coffeemachines.mvp.model.Step3Model.mGson")
    public static void injectMGson(Step3Model step3Model, Gson gson) {
        step3Model.mGson = gson;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(Step3Model step3Model) {
        injectMGson(step3Model, this.mGsonProvider.get());
        injectMApplication(step3Model, this.mApplicationProvider.get());
    }
}
