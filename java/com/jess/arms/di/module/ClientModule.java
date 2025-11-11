package com.jess.arms.di.module;

import android.app.Application;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.gson.Gson;
import com.jess.arms.http.GlobalHttpHandler;
import com.jess.arms.http.log.RequestInterceptor;
import com.jess.arms.utils.DataHelper;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import io.rx_cache2.internal.RxCache;
import io.victoralbertos.jolyglot.GsonSpeaker;
import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import javax.inject.Named;
import javax.inject.Singleton;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.listener.ResponseErrorListener;
import okhttp3.Dispatcher;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
/* loaded from: classes.dex */
public abstract class ClientModule {
    private static final int TIME_OUT = 10;

    /* loaded from: classes.dex */
    public interface OkhttpConfiguration {
        void configOkhttp(@NonNull Context context, @NonNull OkHttpClient.Builder builder);
    }

    /* loaded from: classes.dex */
    public interface RetrofitConfiguration {
        void configRetrofit(@NonNull Context context, @NonNull Retrofit.Builder builder);
    }

    /* loaded from: classes.dex */
    public interface RxCacheConfiguration {
        RxCache configRxCache(@NonNull Context context, @NonNull RxCache.Builder builder);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    public static RxErrorHandler proRxErrorHandler(Application application, ResponseErrorListener responseErrorListener) {
        return RxErrorHandler.builder().with(application).responseErrorListener(responseErrorListener).build();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    public static OkHttpClient provideClient(Application application, @Nullable OkhttpConfiguration okhttpConfiguration, OkHttpClient.Builder builder, Interceptor interceptor, @Nullable List<Interceptor> list, @Nullable final GlobalHttpHandler globalHttpHandler, ExecutorService executorService) {
        builder.connectTimeout(10L, TimeUnit.SECONDS).readTimeout(10L, TimeUnit.SECONDS).addNetworkInterceptor(interceptor);
        if (globalHttpHandler != null) {
            builder.addInterceptor(new Interceptor() { // from class: com.jess.arms.di.module.-$$Lambda$ClientModule$dShW1IbOaZ8FRrTJoBzsLaFYwkk
                @Override // okhttp3.Interceptor
                public final Response intercept(Interceptor.Chain chain) {
                    Response proceed;
                    proceed = chain.proceed(GlobalHttpHandler.this.onHttpRequestBefore(chain, chain.request()));
                    return proceed;
                }
            });
        }
        if (list != null) {
            Iterator<Interceptor> it2 = list.iterator();
            while (it2.hasNext()) {
                builder.addInterceptor(it2.next());
            }
        }
        builder.dispatcher(new Dispatcher(executorService));
        if (okhttpConfiguration != null) {
            okhttpConfiguration.configOkhttp(application, builder);
        }
        return builder.build();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    public static OkHttpClient.Builder provideClientBuilder() {
        return new OkHttpClient.Builder();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    public static Retrofit provideRetrofit(Application application, @Nullable RetrofitConfiguration retrofitConfiguration, Retrofit.Builder builder, OkHttpClient okHttpClient, HttpUrl httpUrl, Gson gson) {
        builder.baseUrl(httpUrl).client(okHttpClient);
        if (retrofitConfiguration != null) {
            retrofitConfiguration.configRetrofit(application, builder);
        }
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create()).addConverterFactory(GsonConverterFactory.create(gson));
        return builder.build();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    public static Retrofit.Builder provideRetrofitBuilder() {
        return new Retrofit.Builder();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    public static RxCache provideRxCache(Application application, @Nullable RxCacheConfiguration rxCacheConfiguration, @Named("RxCacheDirectory") File file, Gson gson) {
        RxCache.Builder builder = new RxCache.Builder();
        RxCache configRxCache = rxCacheConfiguration != null ? rxCacheConfiguration.configRxCache(application, builder) : null;
        return configRxCache != null ? configRxCache : builder.persistence(file, new GsonSpeaker(gson));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    @Named("RxCacheDirectory")
    public static File provideRxCacheDirectory(File file) {
        return DataHelper.makeDirs(new File(file, "RxCache"));
    }

    @Binds
    abstract Interceptor bindInterceptor(RequestInterceptor requestInterceptor);
}
