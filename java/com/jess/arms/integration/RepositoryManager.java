package com.jess.arms.integration;

import android.app.Application;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.integration.cache.Cache;
import com.jess.arms.integration.cache.CacheType;
import com.jess.arms.utils.Preconditions;
import dagger.Lazy;
import io.rx_cache2.internal.RxCache;
import java.lang.reflect.Proxy;
import javax.inject.Inject;
import javax.inject.Singleton;
import retrofit2.Retrofit;

@Singleton
/* loaded from: classes.dex */
public class RepositoryManager implements IRepositoryManager {

    @Inject
    Application mApplication;

    @Inject
    Cache.Factory mCacheFactory;
    private Cache<String, Object> mCacheServiceCache;

    @Nullable
    @Inject
    IRepositoryManager.ObtainServiceDelegate mObtainServiceDelegate;

    @Inject
    Lazy<Retrofit> mRetrofit;
    private Cache<String, Object> mRetrofitServiceCache;

    @Inject
    Lazy<RxCache> mRxCache;

    @Inject
    public RepositoryManager() {
    }

    @Override // com.jess.arms.integration.IRepositoryManager
    public void clearAllCache() {
        this.mRxCache.get().evictAll().subscribe();
    }

    @Override // com.jess.arms.integration.IRepositoryManager
    @NonNull
    public Context getContext() {
        return this.mApplication;
    }

    @Override // com.jess.arms.integration.IRepositoryManager
    @NonNull
    public synchronized <T> T obtainCacheService(@NonNull Class<T> cls) {
        T t;
        Preconditions.checkNotNull(cls, "cacheClass == null");
        if (this.mCacheServiceCache == null) {
            this.mCacheServiceCache = this.mCacheFactory.build(CacheType.CACHE_SERVICE_CACHE);
        }
        Preconditions.checkNotNull(this.mCacheServiceCache, "Cannot return null from a Cache.Factory#build(int) method");
        t = (T) this.mCacheServiceCache.get(cls.getCanonicalName());
        if (t == null) {
            t = (T) this.mRxCache.get().using(cls);
            this.mCacheServiceCache.put(cls.getCanonicalName(), t);
        }
        return t;
    }

    @Override // com.jess.arms.integration.IRepositoryManager
    @NonNull
    public synchronized <T> T obtainRetrofitService(@NonNull Class<T> cls) {
        T t;
        if (this.mRetrofitServiceCache == null) {
            this.mRetrofitServiceCache = this.mCacheFactory.build(CacheType.RETROFIT_SERVICE_CACHE);
        }
        Preconditions.checkNotNull(this.mRetrofitServiceCache, "Cannot return null from a Cache.Factory#build(int) method");
        t = (T) this.mRetrofitServiceCache.get(cls.getCanonicalName());
        if (t == null) {
            if (this.mObtainServiceDelegate != null) {
                t = (T) this.mObtainServiceDelegate.createRetrofitService(this.mRetrofit.get(), cls);
            }
            if (t == null) {
                t = (T) Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{cls}, new RetrofitServiceProxyHandler(this.mRetrofit.get(), cls));
            }
            this.mRetrofitServiceCache.put(cls.getCanonicalName(), t);
        }
        return t;
    }
}
