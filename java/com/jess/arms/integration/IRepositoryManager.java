package com.jess.arms.integration;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import retrofit2.Retrofit;

/* loaded from: classes.dex */
public interface IRepositoryManager {

    /* loaded from: classes.dex */
    public interface ObtainServiceDelegate {
        @Nullable
        <T> T createRetrofitService(Retrofit retrofit, Class<T> cls);
    }

    void clearAllCache();

    @NonNull
    Context getContext();

    @NonNull
    <T> T obtainCacheService(@NonNull Class<T> cls);

    @NonNull
    <T> T obtainRetrofitService(@NonNull Class<T> cls);
}
