package com.jess.arms.integration;

import android.app.Application;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.integration.cache.Cache;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import io.rx_cache2.internal.RxCache;
import javax.inject.Provider;
import retrofit2.Retrofit;

/* loaded from: classes.dex */
public final class RepositoryManager_Factory implements Factory<RepositoryManager> {
    private final Provider<Application> mApplicationProvider;
    private final Provider<Cache.Factory> mCacheFactoryProvider;
    private final Provider<IRepositoryManager.ObtainServiceDelegate> mObtainServiceDelegateProvider;
    private final Provider<Retrofit> mRetrofitProvider;
    private final Provider<RxCache> mRxCacheProvider;

    public RepositoryManager_Factory(Provider<Retrofit> provider, Provider<RxCache> provider2, Provider<Application> provider3, Provider<Cache.Factory> provider4, Provider<IRepositoryManager.ObtainServiceDelegate> provider5) {
        this.mRetrofitProvider = provider;
        this.mRxCacheProvider = provider2;
        this.mApplicationProvider = provider3;
        this.mCacheFactoryProvider = provider4;
        this.mObtainServiceDelegateProvider = provider5;
    }

    public static RepositoryManager_Factory create(Provider<Retrofit> provider, Provider<RxCache> provider2, Provider<Application> provider3, Provider<Cache.Factory> provider4, Provider<IRepositoryManager.ObtainServiceDelegate> provider5) {
        return new RepositoryManager_Factory(provider, provider2, provider3, provider4, provider5);
    }

    public static RepositoryManager newInstance() {
        return new RepositoryManager();
    }

    @Override // javax.inject.Provider
    public RepositoryManager get() {
        RepositoryManager newInstance = newInstance();
        RepositoryManager_MembersInjector.injectMRetrofit(newInstance, DoubleCheck.lazy(this.mRetrofitProvider));
        RepositoryManager_MembersInjector.injectMRxCache(newInstance, DoubleCheck.lazy(this.mRxCacheProvider));
        RepositoryManager_MembersInjector.injectMApplication(newInstance, this.mApplicationProvider.get());
        RepositoryManager_MembersInjector.injectMCacheFactory(newInstance, this.mCacheFactoryProvider.get());
        RepositoryManager_MembersInjector.injectMObtainServiceDelegate(newInstance, this.mObtainServiceDelegateProvider.get());
        return newInstance;
    }
}
