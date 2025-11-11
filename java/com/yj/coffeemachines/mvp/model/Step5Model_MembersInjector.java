package com.yj.coffeemachines.mvp.model;

import android.app.Application;
import com.google.gson.Gson;
import dagger.MembersInjector;
import dagger.internal.InjectedFieldSignature;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class Step5Model_MembersInjector implements MembersInjector<Step5Model> {
    private final Provider<Application> mApplicationProvider;
    private final Provider<Gson> mGsonProvider;

    public Step5Model_MembersInjector(Provider<Gson> provider, Provider<Application> provider2) {
        this.mGsonProvider = provider;
        this.mApplicationProvider = provider2;
    }

    public static MembersInjector<Step5Model> create(Provider<Gson> provider, Provider<Application> provider2) {
        return new Step5Model_MembersInjector(provider, provider2);
    }

    @InjectedFieldSignature("com.yj.coffeemachines.mvp.model.Step5Model.mApplication")
    public static void injectMApplication(Step5Model step5Model, Application application) {
        step5Model.mApplication = application;
    }

    @InjectedFieldSignature("com.yj.coffeemachines.mvp.model.Step5Model.mGson")
    public static void injectMGson(Step5Model step5Model, Gson gson) {
        step5Model.mGson = gson;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(Step5Model step5Model) {
        injectMGson(step5Model, this.mGsonProvider.get());
        injectMApplication(step5Model, this.mApplicationProvider.get());
    }
}
