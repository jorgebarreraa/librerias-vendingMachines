package com.jess.arms.di.module;

import android.app.Application;
import com.google.gson.Gson;
import com.jess.arms.di.module.ClientModule;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/* loaded from: classes.dex */
public final class ClientModule_ProvideRetrofitFactory implements Factory<Retrofit> {
    private final Provider<Application> applicationProvider;
    private final Provider<Retrofit.Builder> builderProvider;
    private final Provider<OkHttpClient> clientProvider;
    private final Provider<ClientModule.RetrofitConfiguration> configurationProvider;
    private final Provider<Gson> gsonProvider;
    private final Provider<HttpUrl> httpUrlProvider;

    public ClientModule_ProvideRetrofitFactory(Provider<Application> provider, Provider<ClientModule.RetrofitConfiguration> provider2, Provider<Retrofit.Builder> provider3, Provider<OkHttpClient> provider4, Provider<HttpUrl> provider5, Provider<Gson> provider6) {
        this.applicationProvider = provider;
        this.configurationProvider = provider2;
        this.builderProvider = provider3;
        this.clientProvider = provider4;
        this.httpUrlProvider = provider5;
        this.gsonProvider = provider6;
    }

    public static ClientModule_ProvideRetrofitFactory create(Provider<Application> provider, Provider<ClientModule.RetrofitConfiguration> provider2, Provider<Retrofit.Builder> provider3, Provider<OkHttpClient> provider4, Provider<HttpUrl> provider5, Provider<Gson> provider6) {
        return new ClientModule_ProvideRetrofitFactory(provider, provider2, provider3, provider4, provider5, provider6);
    }

    public static Retrofit provideRetrofit(Application application, ClientModule.RetrofitConfiguration retrofitConfiguration, Retrofit.Builder builder, OkHttpClient okHttpClient, HttpUrl httpUrl, Gson gson) {
        return (Retrofit) Preconditions.checkNotNull(ClientModule.provideRetrofit(application, retrofitConfiguration, builder, okHttpClient, httpUrl, gson), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    public Retrofit get() {
        return provideRetrofit(this.applicationProvider.get(), this.configurationProvider.get(), this.builderProvider.get(), this.clientProvider.get(), this.httpUrlProvider.get(), this.gsonProvider.get());
    }
}
