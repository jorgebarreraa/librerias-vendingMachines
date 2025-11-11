package com.jess.arms.di.component;

import android.app.Application;
import androidx.fragment.app.FragmentManager;
import com.google.gson.Gson;
import com.jess.arms.base.delegate.AppDelegate;
import com.jess.arms.base.delegate.AppDelegate_MembersInjector;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.module.AppModule;
import com.jess.arms.di.module.AppModule_ProvideAppManagerFactory;
import com.jess.arms.di.module.AppModule_ProvideExtrasFactory;
import com.jess.arms.di.module.AppModule_ProvideFragmentLifecyclesFactory;
import com.jess.arms.di.module.AppModule_ProvideGsonFactory;
import com.jess.arms.di.module.ClientModule;
import com.jess.arms.di.module.ClientModule_ProRxErrorHandlerFactory;
import com.jess.arms.di.module.ClientModule_ProvideClientBuilderFactory;
import com.jess.arms.di.module.ClientModule_ProvideClientFactory;
import com.jess.arms.di.module.ClientModule_ProvideRetrofitBuilderFactory;
import com.jess.arms.di.module.ClientModule_ProvideRetrofitFactory;
import com.jess.arms.di.module.ClientModule_ProvideRxCacheDirectoryFactory;
import com.jess.arms.di.module.ClientModule_ProvideRxCacheFactory;
import com.jess.arms.di.module.GlobalConfigModule;
import com.jess.arms.di.module.GlobalConfigModule_ProvideBaseUrlFactory;
import com.jess.arms.di.module.GlobalConfigModule_ProvideCacheFactoryFactory;
import com.jess.arms.di.module.GlobalConfigModule_ProvideCacheFileFactory;
import com.jess.arms.di.module.GlobalConfigModule_ProvideExecutorServiceFactory;
import com.jess.arms.di.module.GlobalConfigModule_ProvideFormatPrinterFactory;
import com.jess.arms.di.module.GlobalConfigModule_ProvideGlobalHttpHandlerFactory;
import com.jess.arms.di.module.GlobalConfigModule_ProvideGsonConfigurationFactory;
import com.jess.arms.di.module.GlobalConfigModule_ProvideImageLoaderStrategyFactory;
import com.jess.arms.di.module.GlobalConfigModule_ProvideInterceptorsFactory;
import com.jess.arms.di.module.GlobalConfigModule_ProvideObtainServiceDelegateFactory;
import com.jess.arms.di.module.GlobalConfigModule_ProvideOkhttpConfigurationFactory;
import com.jess.arms.di.module.GlobalConfigModule_ProvidePrintHttpLogLevelFactory;
import com.jess.arms.di.module.GlobalConfigModule_ProvideResponseErrorListenerFactory;
import com.jess.arms.di.module.GlobalConfigModule_ProvideRetrofitConfigurationFactory;
import com.jess.arms.di.module.GlobalConfigModule_ProvideRxCacheConfigurationFactory;
import com.jess.arms.http.GlobalHttpHandler;
import com.jess.arms.http.imageloader.BaseImageLoaderStrategy;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.http.imageloader.ImageLoader_Factory;
import com.jess.arms.http.log.FormatPrinter;
import com.jess.arms.http.log.RequestInterceptor;
import com.jess.arms.http.log.RequestInterceptor_Factory;
import com.jess.arms.integration.ActivityLifecycle;
import com.jess.arms.integration.ActivityLifecycle_Factory;
import com.jess.arms.integration.AppManager;
import com.jess.arms.integration.FragmentLifecycle;
import com.jess.arms.integration.FragmentLifecycle_Factory;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.integration.RepositoryManager;
import com.jess.arms.integration.RepositoryManager_Factory;
import com.jess.arms.integration.cache.Cache;
import com.jess.arms.integration.lifecycle.ActivityLifecycleForRxLifecycle;
import com.jess.arms.integration.lifecycle.ActivityLifecycleForRxLifecycle_Factory;
import com.jess.arms.integration.lifecycle.FragmentLifecycleForRxLifecycle;
import com.jess.arms.integration.lifecycle.FragmentLifecycleForRxLifecycle_Factory;
import dagger.internal.DoubleCheck;
import dagger.internal.InstanceFactory;
import dagger.internal.Preconditions;
import io.rx_cache2.internal.RxCache;
import java.io.File;
import java.util.List;
import java.util.concurrent.ExecutorService;
import javax.inject.Provider;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.listener.ResponseErrorListener;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/* loaded from: classes.dex */
public final class DaggerAppComponent implements AppComponent {
    private Provider<ActivityLifecycleForRxLifecycle> activityLifecycleForRxLifecycleProvider;
    private Provider<ActivityLifecycle> activityLifecycleProvider;
    private final Application application;
    private Provider<Application> applicationProvider;
    private Provider<FragmentLifecycleForRxLifecycle> fragmentLifecycleForRxLifecycleProvider;
    private Provider<FragmentLifecycle> fragmentLifecycleProvider;
    private Provider<ImageLoader> imageLoaderProvider;
    private Provider<RxErrorHandler> proRxErrorHandlerProvider;
    private Provider<AppManager> provideAppManagerProvider;
    private Provider<HttpUrl> provideBaseUrlProvider;
    private Provider<Cache.Factory> provideCacheFactoryProvider;
    private Provider<File> provideCacheFileProvider;
    private Provider<OkHttpClient.Builder> provideClientBuilderProvider;
    private Provider<OkHttpClient> provideClientProvider;
    private Provider<ExecutorService> provideExecutorServiceProvider;
    private Provider<Cache<String, Object>> provideExtrasProvider;
    private Provider<FormatPrinter> provideFormatPrinterProvider;
    private Provider<List<FragmentManager.FragmentLifecycleCallbacks>> provideFragmentLifecyclesProvider;
    private Provider<GlobalHttpHandler> provideGlobalHttpHandlerProvider;
    private Provider<AppModule.GsonConfiguration> provideGsonConfigurationProvider;
    private Provider<Gson> provideGsonProvider;
    private Provider<BaseImageLoaderStrategy> provideImageLoaderStrategyProvider;
    private Provider<List<Interceptor>> provideInterceptorsProvider;
    private Provider<IRepositoryManager.ObtainServiceDelegate> provideObtainServiceDelegateProvider;
    private Provider<ClientModule.OkhttpConfiguration> provideOkhttpConfigurationProvider;
    private Provider<RequestInterceptor.Level> providePrintHttpLogLevelProvider;
    private Provider<ResponseErrorListener> provideResponseErrorListenerProvider;
    private Provider<Retrofit.Builder> provideRetrofitBuilderProvider;
    private Provider<ClientModule.RetrofitConfiguration> provideRetrofitConfigurationProvider;
    private Provider<Retrofit> provideRetrofitProvider;
    private Provider<ClientModule.RxCacheConfiguration> provideRxCacheConfigurationProvider;
    private Provider<File> provideRxCacheDirectoryProvider;
    private Provider<RxCache> provideRxCacheProvider;
    private Provider<RepositoryManager> repositoryManagerProvider;
    private Provider<RequestInterceptor> requestInterceptorProvider;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class Builder implements AppComponent.Builder {
        private Application application;
        private GlobalConfigModule globalConfigModule;

        private Builder() {
        }

        @Override // com.jess.arms.di.component.AppComponent.Builder
        public Builder application(Application application) {
            this.application = (Application) Preconditions.checkNotNull(application);
            return this;
        }

        @Override // com.jess.arms.di.component.AppComponent.Builder
        public AppComponent build() {
            Preconditions.checkBuilderRequirement(this.application, Application.class);
            Preconditions.checkBuilderRequirement(this.globalConfigModule, GlobalConfigModule.class);
            return new DaggerAppComponent(this.globalConfigModule, this.application);
        }

        @Override // com.jess.arms.di.component.AppComponent.Builder
        public Builder globalConfigModule(GlobalConfigModule globalConfigModule) {
            this.globalConfigModule = (GlobalConfigModule) Preconditions.checkNotNull(globalConfigModule);
            return this;
        }
    }

    private DaggerAppComponent(GlobalConfigModule globalConfigModule, Application application) {
        this.application = application;
        initialize(globalConfigModule, application);
    }

    public static AppComponent.Builder builder() {
        return new Builder();
    }

    private void initialize(GlobalConfigModule globalConfigModule, Application application) {
        this.applicationProvider = InstanceFactory.create(application);
        this.provideAppManagerProvider = DoubleCheck.provider(AppModule_ProvideAppManagerFactory.create(this.applicationProvider));
        this.provideRetrofitConfigurationProvider = DoubleCheck.provider(GlobalConfigModule_ProvideRetrofitConfigurationFactory.create(globalConfigModule));
        this.provideRetrofitBuilderProvider = DoubleCheck.provider(ClientModule_ProvideRetrofitBuilderFactory.create());
        this.provideOkhttpConfigurationProvider = DoubleCheck.provider(GlobalConfigModule_ProvideOkhttpConfigurationFactory.create(globalConfigModule));
        this.provideClientBuilderProvider = DoubleCheck.provider(ClientModule_ProvideClientBuilderFactory.create());
        this.provideGlobalHttpHandlerProvider = DoubleCheck.provider(GlobalConfigModule_ProvideGlobalHttpHandlerFactory.create(globalConfigModule));
        this.provideFormatPrinterProvider = DoubleCheck.provider(GlobalConfigModule_ProvideFormatPrinterFactory.create(globalConfigModule));
        this.providePrintHttpLogLevelProvider = DoubleCheck.provider(GlobalConfigModule_ProvidePrintHttpLogLevelFactory.create(globalConfigModule));
        this.requestInterceptorProvider = DoubleCheck.provider(RequestInterceptor_Factory.create(this.provideGlobalHttpHandlerProvider, this.provideFormatPrinterProvider, this.providePrintHttpLogLevelProvider));
        this.provideInterceptorsProvider = DoubleCheck.provider(GlobalConfigModule_ProvideInterceptorsFactory.create(globalConfigModule));
        this.provideExecutorServiceProvider = DoubleCheck.provider(GlobalConfigModule_ProvideExecutorServiceFactory.create(globalConfigModule));
        this.provideClientProvider = DoubleCheck.provider(ClientModule_ProvideClientFactory.create(this.applicationProvider, this.provideOkhttpConfigurationProvider, this.provideClientBuilderProvider, this.requestInterceptorProvider, this.provideInterceptorsProvider, this.provideGlobalHttpHandlerProvider, this.provideExecutorServiceProvider));
        this.provideBaseUrlProvider = DoubleCheck.provider(GlobalConfigModule_ProvideBaseUrlFactory.create(globalConfigModule));
        this.provideGsonConfigurationProvider = DoubleCheck.provider(GlobalConfigModule_ProvideGsonConfigurationFactory.create(globalConfigModule));
        this.provideGsonProvider = DoubleCheck.provider(AppModule_ProvideGsonFactory.create(this.applicationProvider, this.provideGsonConfigurationProvider));
        this.provideRetrofitProvider = DoubleCheck.provider(ClientModule_ProvideRetrofitFactory.create(this.applicationProvider, this.provideRetrofitConfigurationProvider, this.provideRetrofitBuilderProvider, this.provideClientProvider, this.provideBaseUrlProvider, this.provideGsonProvider));
        this.provideRxCacheConfigurationProvider = DoubleCheck.provider(GlobalConfigModule_ProvideRxCacheConfigurationFactory.create(globalConfigModule));
        this.provideCacheFileProvider = DoubleCheck.provider(GlobalConfigModule_ProvideCacheFileFactory.create(globalConfigModule, this.applicationProvider));
        this.provideRxCacheDirectoryProvider = DoubleCheck.provider(ClientModule_ProvideRxCacheDirectoryFactory.create(this.provideCacheFileProvider));
        this.provideRxCacheProvider = DoubleCheck.provider(ClientModule_ProvideRxCacheFactory.create(this.applicationProvider, this.provideRxCacheConfigurationProvider, this.provideRxCacheDirectoryProvider, this.provideGsonProvider));
        this.provideCacheFactoryProvider = DoubleCheck.provider(GlobalConfigModule_ProvideCacheFactoryFactory.create(globalConfigModule, this.applicationProvider));
        this.provideObtainServiceDelegateProvider = DoubleCheck.provider(GlobalConfigModule_ProvideObtainServiceDelegateFactory.create(globalConfigModule));
        this.repositoryManagerProvider = DoubleCheck.provider(RepositoryManager_Factory.create(this.provideRetrofitProvider, this.provideRxCacheProvider, this.applicationProvider, this.provideCacheFactoryProvider, this.provideObtainServiceDelegateProvider));
        this.provideResponseErrorListenerProvider = DoubleCheck.provider(GlobalConfigModule_ProvideResponseErrorListenerFactory.create(globalConfigModule));
        this.proRxErrorHandlerProvider = DoubleCheck.provider(ClientModule_ProRxErrorHandlerFactory.create(this.applicationProvider, this.provideResponseErrorListenerProvider));
        this.provideImageLoaderStrategyProvider = DoubleCheck.provider(GlobalConfigModule_ProvideImageLoaderStrategyFactory.create(globalConfigModule));
        this.imageLoaderProvider = DoubleCheck.provider(ImageLoader_Factory.create(this.provideImageLoaderStrategyProvider));
        this.provideExtrasProvider = DoubleCheck.provider(AppModule_ProvideExtrasFactory.create(this.provideCacheFactoryProvider));
        this.fragmentLifecycleProvider = DoubleCheck.provider(FragmentLifecycle_Factory.create());
        this.provideFragmentLifecyclesProvider = DoubleCheck.provider(AppModule_ProvideFragmentLifecyclesFactory.create());
        this.activityLifecycleProvider = DoubleCheck.provider(ActivityLifecycle_Factory.create(this.provideAppManagerProvider, this.applicationProvider, this.provideExtrasProvider, this.fragmentLifecycleProvider, this.provideFragmentLifecyclesProvider));
        this.fragmentLifecycleForRxLifecycleProvider = DoubleCheck.provider(FragmentLifecycleForRxLifecycle_Factory.create());
        this.activityLifecycleForRxLifecycleProvider = DoubleCheck.provider(ActivityLifecycleForRxLifecycle_Factory.create(this.fragmentLifecycleForRxLifecycleProvider));
    }

    private AppDelegate injectAppDelegate(AppDelegate appDelegate) {
        AppDelegate_MembersInjector.injectMActivityLifecycle(appDelegate, this.activityLifecycleProvider.get());
        AppDelegate_MembersInjector.injectMActivityLifecycleForRxLifecycle(appDelegate, this.activityLifecycleForRxLifecycleProvider.get());
        return appDelegate;
    }

    @Override // com.jess.arms.di.component.AppComponent
    public AppManager appManager() {
        return this.provideAppManagerProvider.get();
    }

    @Override // com.jess.arms.di.component.AppComponent
    public Application application() {
        return this.application;
    }

    @Override // com.jess.arms.di.component.AppComponent
    public Cache.Factory cacheFactory() {
        return this.provideCacheFactoryProvider.get();
    }

    @Override // com.jess.arms.di.component.AppComponent
    public File cacheFile() {
        return this.provideCacheFileProvider.get();
    }

    @Override // com.jess.arms.di.component.AppComponent
    public ExecutorService executorService() {
        return this.provideExecutorServiceProvider.get();
    }

    @Override // com.jess.arms.di.component.AppComponent
    public Cache<String, Object> extras() {
        return this.provideExtrasProvider.get();
    }

    @Override // com.jess.arms.di.component.AppComponent
    public Gson gson() {
        return this.provideGsonProvider.get();
    }

    @Override // com.jess.arms.di.component.AppComponent
    public ImageLoader imageLoader() {
        return this.imageLoaderProvider.get();
    }

    @Override // com.jess.arms.di.component.AppComponent
    public void inject(AppDelegate appDelegate) {
        injectAppDelegate(appDelegate);
    }

    @Override // com.jess.arms.di.component.AppComponent
    public OkHttpClient okHttpClient() {
        return this.provideClientProvider.get();
    }

    @Override // com.jess.arms.di.component.AppComponent
    public IRepositoryManager repositoryManager() {
        return this.repositoryManagerProvider.get();
    }

    @Override // com.jess.arms.di.component.AppComponent
    public RxErrorHandler rxErrorHandler() {
        return this.proRxErrorHandlerProvider.get();
    }
}
