package com.jess.arms.integration;

import android.app.Application;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.integration.cache.Cache;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import dagger.internal.InjectedFieldSignature;
import io.rx_cache2.internal.RxCache;
import javax.inject.Provider;
import retrofit2.Retrofit;

/* loaded from: classes.dex */
public final class RepositoryManager_MembersInjector implements MembersInjector<RepositoryManager> {
    private final Provider<Application> mApplicationProvider;
    private final Provider<Cache.Factory> mCacheFactoryProvider;
    private final Provider<IRepositoryManager.ObtainServiceDelegate> mObtainServiceDelegateProvider;
    private final Provider<Retrofit> mRetrofitProvider;
    private final Provider<RxCache> mRxCacheProvider;

    public RepositoryManager_MembersInjector(Provider<Retrofit> provider, Provider<RxCache> provider2, Provider<Application> provider3, Provider<Cache.Factory> provider4, Provider<IRepositoryManager.ObtainServiceDelegate> provider5) {
        this.mRetrofitProvider = provider;
        this.mRxCacheProvider = provider2;
        this.mApplicationProvider = provider3;
        this.mCacheFactoryProvider = provider4;
        this.mObtainServiceDelegateProvider = provider5;
    }

    public static MembersInjector<RepositoryManager> create(Provider<Retrofit> provider, Provider<RxCache> provider2, Provider<Application> provider3, Provider<Cache.Factory> provider4, Provider<IRepositoryManager.ObtainServiceDelegate> provider5) {
        return new RepositoryManager_MembersInjector(provider, provider2, provider3, provider4, provider5);
    }

    @InjectedFieldSignature("com.jess.arms.integration.RepositoryManager.mApplication")
    public static void injectMApplication(RepositoryManager repositoryManager, Application application) {
        repositoryManager.mApplication = application;
    }

    @InjectedFieldSignature("com.jess.arms.integration.RepositoryManager.mCacheFactory")
    public static void injectMCacheFactory(RepositoryManager repositoryManager, Cache.Factory factory) {
        repositoryManager.mCacheFactory = factory;
    }

    @InjectedFieldSignature("com.jess.arms.integration.RepositoryManager.mObtainServiceDelegate")
    public static void injectMObtainServiceDelegate(RepositoryManager repositoryManager, IRepositoryManager.ObtainServiceDelegate obtainServiceDelegate) {
        repositoryManager.mObtainServiceDelegate = obtainServiceDelegate;
    }

    @InjectedFieldSignature("com.jess.arms.integration.RepositoryManager.mRetrofit")
    public static void injectMRetrofit(RepositoryManager repositoryManager, Lazy<Retrofit> lazy) {
        repositoryManager.mRetrofit = lazy;
    }

    @InjectedFieldSignature("com.jess.arms.integration.RepositoryManager.mRxCache")
    public static void injectMRxCache(RepositoryManager repositoryManager, Lazy<RxCache> lazy) {
        repositoryManager.mRxCache = lazy;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(RepositoryManager repositoryManager) {
        injectMRetrofit(repositoryManager, DoubleCheck.lazy(this.mRetrofitProvider));
        injectMRxCache(repositoryManager, DoubleCheck.lazy(this.mRxCacheProvider));
        injectMApplication(repositoryManager, this.mApplicationProvider.get());
        injectMCacheFactory(repositoryManager, this.mCacheFactoryProvider.get());
        injectMObtainServiceDelegate(repositoryManager, this.mObtainServiceDelegateProvider.get());
    }
}
