package com.jess.arms.di.module;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import okhttp3.OkHttpClient;

/* loaded from: classes.dex */
public final class ClientModule_ProvideClientBuilderFactory implements Factory<OkHttpClient.Builder> {

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class InstanceHolder {
        private static final ClientModule_ProvideClientBuilderFactory INSTANCE = new ClientModule_ProvideClientBuilderFactory();

        private InstanceHolder() {
        }
    }

    public static ClientModule_ProvideClientBuilderFactory create() {
        return InstanceHolder.INSTANCE;
    }

    public static OkHttpClient.Builder provideClientBuilder() {
        return (OkHttpClient.Builder) Preconditions.checkNotNull(ClientModule.provideClientBuilder(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    public OkHttpClient.Builder get() {
        return provideClientBuilder();
    }
}
