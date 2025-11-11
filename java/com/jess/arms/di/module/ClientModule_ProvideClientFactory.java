package com.jess.arms.di.module;

import android.app.Application;
import com.jess.arms.di.module.ClientModule;
import com.jess.arms.http.GlobalHttpHandler;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import java.util.List;
import java.util.concurrent.ExecutorService;
import javax.inject.Provider;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;

/* loaded from: classes.dex */
public final class ClientModule_ProvideClientFactory implements Factory<OkHttpClient> {
    private final Provider<Application> applicationProvider;
    private final Provider<OkHttpClient.Builder> builderProvider;
    private final Provider<ClientModule.OkhttpConfiguration> configurationProvider;
    private final Provider<ExecutorService> executorServiceProvider;
    private final Provider<GlobalHttpHandler> handlerProvider;
    private final Provider<Interceptor> interceptProvider;
    private final Provider<List<Interceptor>> interceptorsProvider;

    public ClientModule_ProvideClientFactory(Provider<Application> provider, Provider<ClientModule.OkhttpConfiguration> provider2, Provider<OkHttpClient.Builder> provider3, Provider<Interceptor> provider4, Provider<List<Interceptor>> provider5, Provider<GlobalHttpHandler> provider6, Provider<ExecutorService> provider7) {
        this.applicationProvider = provider;
        this.configurationProvider = provider2;
        this.builderProvider = provider3;
        this.interceptProvider = provider4;
        this.interceptorsProvider = provider5;
        this.handlerProvider = provider6;
        this.executorServiceProvider = provider7;
    }

    public static ClientModule_ProvideClientFactory create(Provider<Application> provider, Provider<ClientModule.OkhttpConfiguration> provider2, Provider<OkHttpClient.Builder> provider3, Provider<Interceptor> provider4, Provider<List<Interceptor>> provider5, Provider<GlobalHttpHandler> provider6, Provider<ExecutorService> provider7) {
        return new ClientModule_ProvideClientFactory(provider, provider2, provider3, provider4, provider5, provider6, provider7);
    }

    public static OkHttpClient provideClient(Application application, ClientModule.OkhttpConfiguration okhttpConfiguration, OkHttpClient.Builder builder, Interceptor interceptor, List<Interceptor> list, GlobalHttpHandler globalHttpHandler, ExecutorService executorService) {
        return (OkHttpClient) Preconditions.checkNotNull(ClientModule.provideClient(application, okhttpConfiguration, builder, interceptor, list, globalHttpHandler, executorService), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    public OkHttpClient get() {
        return provideClient(this.applicationProvider.get(), this.configurationProvider.get(), this.builderProvider.get(), this.interceptProvider.get(), this.interceptorsProvider.get(), this.handlerProvider.get(), this.executorServiceProvider.get());
    }
}
