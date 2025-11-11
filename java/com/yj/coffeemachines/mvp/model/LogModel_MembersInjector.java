package com.yj.coffeemachines.mvp.model;

import android.app.Application;
import com.google.gson.Gson;
import dagger.MembersInjector;
import dagger.internal.InjectedFieldSignature;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class LogModel_MembersInjector implements MembersInjector<LogModel> {
    private final Provider<Application> mApplicationProvider;
    private final Provider<Gson> mGsonProvider;

    public LogModel_MembersInjector(Provider<Gson> provider, Provider<Application> provider2) {
        this.mGsonProvider = provider;
        this.mApplicationProvider = provider2;
    }

    public static MembersInjector<LogModel> create(Provider<Gson> provider, Provider<Application> provider2) {
        return new LogModel_MembersInjector(provider, provider2);
    }

    @InjectedFieldSignature("com.yj.coffeemachines.mvp.model.LogModel.mApplication")
    public static void injectMApplication(LogModel logModel, Application application) {
        logModel.mApplication = application;
    }

    @InjectedFieldSignature("com.yj.coffeemachines.mvp.model.LogModel.mGson")
    public static void injectMGson(LogModel logModel, Gson gson) {
        logModel.mGson = gson;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(LogModel logModel) {
        injectMGson(logModel, this.mGsonProvider.get());
        injectMApplication(logModel, this.mApplicationProvider.get());
    }
}
