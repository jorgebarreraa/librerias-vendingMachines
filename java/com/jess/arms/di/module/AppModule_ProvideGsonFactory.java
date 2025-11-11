package com.jess.arms.di.module;

import android.app.Application;
import com.google.gson.Gson;
import com.jess.arms.di.module.AppModule;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class AppModule_ProvideGsonFactory implements Factory<Gson> {
    private final Provider<Application> applicationProvider;
    private final Provider<AppModule.GsonConfiguration> configurationProvider;

    public AppModule_ProvideGsonFactory(Provider<Application> provider, Provider<AppModule.GsonConfiguration> provider2) {
        this.applicationProvider = provider;
        this.configurationProvider = provider2;
    }

    public static AppModule_ProvideGsonFactory create(Provider<Application> provider, Provider<AppModule.GsonConfiguration> provider2) {
        return new AppModule_ProvideGsonFactory(provider, provider2);
    }

    public static Gson provideGson(Application application, AppModule.GsonConfiguration gsonConfiguration) {
        return (Gson) Preconditions.checkNotNull(AppModule.provideGson(application, gsonConfiguration), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    public Gson get() {
        return provideGson(this.applicationProvider.get(), this.configurationProvider.get());
    }
}
