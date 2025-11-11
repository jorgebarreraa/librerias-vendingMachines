package com.jess.arms.di.module;

import android.app.Application;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.jess.arms.di.module.AppModule;
import com.jess.arms.di.module.ClientModule;
import com.jess.arms.http.BaseUrl;
import com.jess.arms.http.GlobalHttpHandler;
import com.jess.arms.http.imageloader.BaseImageLoaderStrategy;
import com.jess.arms.http.log.DefaultFormatPrinter;
import com.jess.arms.http.log.FormatPrinter;
import com.jess.arms.http.log.RequestInterceptor;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.integration.cache.Cache;
import com.jess.arms.integration.cache.CacheType;
import com.jess.arms.integration.cache.IntelligentCache;
import com.jess.arms.integration.cache.LruCache;
import com.jess.arms.utils.DataHelper;
import com.jess.arms.utils.Preconditions;
import dagger.Module;
import dagger.Provides;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import javax.inject.Singleton;
import me.jessyan.rxerrorhandler.handler.listener.ResponseErrorListener;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.internal.Util;

@Module
/* loaded from: classes.dex */
public class GlobalConfigModule {
    private HttpUrl mApiUrl;
    private BaseUrl mBaseUrl;
    private Cache.Factory mCacheFactory;
    private File mCacheFile;
    private ResponseErrorListener mErrorListener;
    private ExecutorService mExecutorService;
    private FormatPrinter mFormatPrinter;
    private AppModule.GsonConfiguration mGsonConfiguration;
    private GlobalHttpHandler mHandler;
    private List<Interceptor> mInterceptors;
    private BaseImageLoaderStrategy mLoaderStrategy;
    private IRepositoryManager.ObtainServiceDelegate mObtainServiceDelegate;
    private ClientModule.OkhttpConfiguration mOkhttpConfiguration;
    private RequestInterceptor.Level mPrintHttpLogLevel;
    private ClientModule.RetrofitConfiguration mRetrofitConfiguration;
    private ClientModule.RxCacheConfiguration mRxCacheConfiguration;

    /* loaded from: classes.dex */
    public static final class Builder {
        private HttpUrl apiUrl;
        private BaseUrl baseUrl;
        private Cache.Factory cacheFactory;
        private File cacheFile;
        private ExecutorService executorService;
        private FormatPrinter formatPrinter;
        private AppModule.GsonConfiguration gsonConfiguration;
        private GlobalHttpHandler handler;
        private List<Interceptor> interceptors;
        private BaseImageLoaderStrategy loaderStrategy;
        private IRepositoryManager.ObtainServiceDelegate obtainServiceDelegate;
        private ClientModule.OkhttpConfiguration okhttpConfiguration;
        private RequestInterceptor.Level printHttpLogLevel;
        private ResponseErrorListener responseErrorListener;
        private ClientModule.RetrofitConfiguration retrofitConfiguration;
        private ClientModule.RxCacheConfiguration rxCacheConfiguration;

        private Builder() {
        }

        public Builder addInterceptor(Interceptor interceptor) {
            if (this.interceptors == null) {
                this.interceptors = new ArrayList();
            }
            this.interceptors.add(interceptor);
            return this;
        }

        public Builder baseurl(BaseUrl baseUrl) {
            this.baseUrl = (BaseUrl) Preconditions.checkNotNull(baseUrl, BaseUrl.class.getCanonicalName() + "can not be null.");
            return this;
        }

        public Builder baseurl(String str) {
            if (TextUtils.isEmpty(str)) {
                throw new NullPointerException("BaseUrl can not be empty");
            }
            this.apiUrl = HttpUrl.parse(str);
            return this;
        }

        public GlobalConfigModule build() {
            return new GlobalConfigModule(this);
        }

        public Builder cacheFactory(Cache.Factory factory) {
            this.cacheFactory = factory;
            return this;
        }

        public Builder cacheFile(File file) {
            this.cacheFile = file;
            return this;
        }

        public Builder executorService(ExecutorService executorService) {
            this.executorService = executorService;
            return this;
        }

        public Builder formatPrinter(FormatPrinter formatPrinter) {
            this.formatPrinter = (FormatPrinter) Preconditions.checkNotNull(formatPrinter, FormatPrinter.class.getCanonicalName() + "can not be null.");
            return this;
        }

        public Builder globalHttpHandler(GlobalHttpHandler globalHttpHandler) {
            this.handler = globalHttpHandler;
            return this;
        }

        public Builder gsonConfiguration(AppModule.GsonConfiguration gsonConfiguration) {
            this.gsonConfiguration = gsonConfiguration;
            return this;
        }

        public Builder imageLoaderStrategy(BaseImageLoaderStrategy baseImageLoaderStrategy) {
            this.loaderStrategy = baseImageLoaderStrategy;
            return this;
        }

        public Builder obtainServiceDelegate(IRepositoryManager.ObtainServiceDelegate obtainServiceDelegate) {
            this.obtainServiceDelegate = obtainServiceDelegate;
            return this;
        }

        public Builder okhttpConfiguration(ClientModule.OkhttpConfiguration okhttpConfiguration) {
            this.okhttpConfiguration = okhttpConfiguration;
            return this;
        }

        public Builder printHttpLogLevel(RequestInterceptor.Level level) {
            this.printHttpLogLevel = (RequestInterceptor.Level) Preconditions.checkNotNull(level, "The printHttpLogLevel can not be null, use RequestInterceptor.Level.NONE instead.");
            return this;
        }

        public Builder responseErrorListener(ResponseErrorListener responseErrorListener) {
            this.responseErrorListener = responseErrorListener;
            return this;
        }

        public Builder retrofitConfiguration(ClientModule.RetrofitConfiguration retrofitConfiguration) {
            this.retrofitConfiguration = retrofitConfiguration;
            return this;
        }

        public Builder rxCacheConfiguration(ClientModule.RxCacheConfiguration rxCacheConfiguration) {
            this.rxCacheConfiguration = rxCacheConfiguration;
            return this;
        }
    }

    private GlobalConfigModule(Builder builder) {
        this.mApiUrl = builder.apiUrl;
        this.mBaseUrl = builder.baseUrl;
        this.mLoaderStrategy = builder.loaderStrategy;
        this.mHandler = builder.handler;
        this.mInterceptors = builder.interceptors;
        this.mErrorListener = builder.responseErrorListener;
        this.mCacheFile = builder.cacheFile;
        this.mRetrofitConfiguration = builder.retrofitConfiguration;
        this.mOkhttpConfiguration = builder.okhttpConfiguration;
        this.mRxCacheConfiguration = builder.rxCacheConfiguration;
        this.mGsonConfiguration = builder.gsonConfiguration;
        this.mPrintHttpLogLevel = builder.printHttpLogLevel;
        this.mFormatPrinter = builder.formatPrinter;
        this.mCacheFactory = builder.cacheFactory;
        this.mExecutorService = builder.executorService;
        this.mObtainServiceDelegate = builder.obtainServiceDelegate;
    }

    public static Builder builder() {
        return new Builder();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Cache lambda$provideCacheFactory$0(Application application, CacheType cacheType) {
        int cacheTypeId = cacheType.getCacheTypeId();
        return (cacheTypeId == 2 || cacheTypeId == 3 || cacheTypeId == 4) ? new IntelligentCache(cacheType.calculateCacheSize(application)) : new LruCache(cacheType.calculateCacheSize(application));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    public HttpUrl provideBaseUrl() {
        HttpUrl url;
        BaseUrl baseUrl = this.mBaseUrl;
        if (baseUrl != null && (url = baseUrl.url()) != null) {
            return url;
        }
        HttpUrl httpUrl = this.mApiUrl;
        return httpUrl == null ? HttpUrl.parse("https://api.github.com/") : httpUrl;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    public Cache.Factory provideCacheFactory(final Application application) {
        Cache.Factory factory = this.mCacheFactory;
        return factory == null ? new Cache.Factory() { // from class: com.jess.arms.di.module.-$$Lambda$GlobalConfigModule$I-JCi30x8obzaS4dU3G_TDMv4ns
            @Override // com.jess.arms.integration.cache.Cache.Factory
            public final Cache build(CacheType cacheType) {
                return GlobalConfigModule.lambda$provideCacheFactory$0(application, cacheType);
            }
        } : factory;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    public File provideCacheFile(Application application) {
        File file = this.mCacheFile;
        return file == null ? DataHelper.getCacheFile(application) : file;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    public ExecutorService provideExecutorService() {
        ExecutorService executorService = this.mExecutorService;
        return executorService == null ? new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue(), Util.threadFactory("Arms Executor", false)) : executorService;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    public FormatPrinter provideFormatPrinter() {
        FormatPrinter formatPrinter = this.mFormatPrinter;
        return formatPrinter == null ? new DefaultFormatPrinter() : formatPrinter;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    @Provides
    @Singleton
    public GlobalHttpHandler provideGlobalHttpHandler() {
        return this.mHandler;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    @Provides
    @Singleton
    public AppModule.GsonConfiguration provideGsonConfiguration() {
        return this.mGsonConfiguration;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    @Provides
    @Singleton
    public BaseImageLoaderStrategy provideImageLoaderStrategy() {
        return this.mLoaderStrategy;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    @Provides
    @Singleton
    public List<Interceptor> provideInterceptors() {
        return this.mInterceptors;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    @Provides
    @Singleton
    public IRepositoryManager.ObtainServiceDelegate provideObtainServiceDelegate() {
        return this.mObtainServiceDelegate;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    @Provides
    @Singleton
    public ClientModule.OkhttpConfiguration provideOkhttpConfiguration() {
        return this.mOkhttpConfiguration;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    public RequestInterceptor.Level providePrintHttpLogLevel() {
        RequestInterceptor.Level level = this.mPrintHttpLogLevel;
        return level == null ? RequestInterceptor.Level.ALL : level;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    public ResponseErrorListener provideResponseErrorListener() {
        ResponseErrorListener responseErrorListener = this.mErrorListener;
        return responseErrorListener == null ? ResponseErrorListener.EMPTY : responseErrorListener;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    @Provides
    @Singleton
    public ClientModule.RetrofitConfiguration provideRetrofitConfiguration() {
        return this.mRetrofitConfiguration;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    @Provides
    @Singleton
    public ClientModule.RxCacheConfiguration provideRxCacheConfiguration() {
        return this.mRxCacheConfiguration;
    }
}
