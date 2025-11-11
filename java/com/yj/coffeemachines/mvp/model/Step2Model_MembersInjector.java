package com.yj.coffeemachines.mvp.model;

import android.app.Application;
import com.google.gson.Gson;
import dagger.MembersInjector;
import dagger.internal.InjectedFieldSignature;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class Step2Model_MembersInjector implements MembersInjector<Step2Model> {
    private final Provider<Application> mApplicationProvider;
    private final Provider<Gson> mGsonProvider;

    public Step2Model_MembersInjector(Provider<Gson> provider, Provider<Application> provider2) {
        this.mGsonProvider = provider;
        this.mApplicationProvider = provider2;
    }

    public static MembersInjector<Step2Model> create(Provider<Gson> provider, Provider<Application> provider2) {
        return new Step2Model_MembersInjector(provider, provider2);
    }

    @InjectedFieldSignature("com.yj.coffeemachines.mvp.model.Step2Model.mApplication")
    public static void injectMApplication(Step2Model step2Model, Application application) {
        step2Model.mApplication = application;
    }

    @InjectedFieldSignature("com.yj.coffeemachines.mvp.model.Step2Model.mGson")
    public static void injectMGson(Step2Model step2Model, Gson gson) {
        step2Model.mGson = gson;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(Step2Model step2Model) {
        injectMGson(step2Model, this.mGsonProvider.get());
        injectMApplication(step2Model, this.mApplicationProvider.get());
    }
}
