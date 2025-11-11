package com.jess.arms.di.module;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import retrofit2.Retrofit;

/* loaded from: classes.dex */
public final class ClientModule_ProvideRetrofitBuilderFactory implements Factory<Retrofit.Builder> {

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class InstanceHolder {
        private static final ClientModule_ProvideRetrofitBuilderFactory INSTANCE = new ClientModule_ProvideRetrofitBuilderFactory();

        private InstanceHolder() {
        }
    }

    public static ClientModule_ProvideRetrofitBuilderFactory create() {
        return InstanceHolder.INSTANCE;
    }

    public static Retrofit.Builder provideRetrofitBuilder() {
        return (Retrofit.Builder) Preconditions.checkNotNull(ClientModule.provideRetrofitBuilder(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    public Retrofit.Builder get() {
        return provideRetrofitBuilder();
    }
}
