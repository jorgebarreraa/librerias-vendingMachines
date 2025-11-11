package com.yj.coffeemachines.app;

import android.app.Application;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import com.google.gson.GsonBuilder;
import com.jess.arms.base.delegate.AppLifecycles;
import com.jess.arms.di.module.AppModule;
import com.jess.arms.di.module.ClientModule;
import com.jess.arms.di.module.GlobalConfigModule;
import com.jess.arms.http.log.RequestInterceptor;
import com.jess.arms.integration.ConfigModule;
import com.jess.arms.integration.cache.Cache;
import com.jess.arms.integration.cache.CacheType;
import com.jess.arms.integration.cache.LruCache;
import com.yj.coffeemachines.app.jsonFactory.FastJsonConverterFactory;
import com.yj.coffeemachines.helper.LanguageHelper;
import io.rx_cache2.internal.RxCache;
import java.util.List;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import me.jessyan.progressmanager.ProgressManager;
import me.jessyan.retrofiturlmanager.RetrofitUrlManager;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/* loaded from: classes.dex */
public final class GlobalConfiguration implements ConfigModule {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$applyOptions$2(Context context, OkHttpClient.Builder builder) {
        builder.connectTimeout(10L, TimeUnit.SECONDS);
        ProgressManager.getInstance().with(builder);
        RetrofitUrlManager.getInstance().with(builder);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ RxCache lambda$applyOptions$3(Context context, RxCache.Builder builder) {
        builder.useExpiredDataIfLoaderNotAvailable(true);
        return null;
    }

    @Override // com.jess.arms.integration.ConfigModule
    public void applyOptions(@NonNull Context context, @NonNull GlobalConfigModule.Builder builder) {
        builder.printHttpLogLevel(RequestInterceptor.Level.ALL);
        builder.baseurl(LanguageHelper.language().getUrl()).cacheFactory(new Cache.Factory() { // from class: com.yj.coffeemachines.app.GlobalConfiguration.1
            @Override // com.jess.arms.integration.cache.Cache.Factory
            @NonNull
            public Cache build(CacheType cacheType) {
                return new LruCache(2048);
            }
        }).executorService(new ScheduledThreadPoolExecutor(4, new WorkerThreadFactory())).globalHttpHandler(new GlobalHttpHandlerImpl(context)).gsonConfiguration(new AppModule.GsonConfiguration() { // from class: com.yj.coffeemachines.app.-$$Lambda$GlobalConfiguration$IfLbTdnQ7NMppxO0aYYIGutr4z8
            @Override // com.jess.arms.di.module.AppModule.GsonConfiguration
            public final void configGson(Context context2, GsonBuilder gsonBuilder) {
                gsonBuilder.serializeNulls().enableComplexMapKeySerialization();
            }
        }).retrofitConfiguration(new ClientModule.RetrofitConfiguration() { // from class: com.yj.coffeemachines.app.-$$Lambda$GlobalConfiguration$4IO7iY83NW6v1dzmrHPYOOryxxk
            @Override // com.jess.arms.di.module.ClientModule.RetrofitConfiguration
            public final void configRetrofit(Context context2, Retrofit.Builder builder2) {
                builder2.addConverterFactory(FastJsonConverterFactory.create());
            }
        }).okhttpConfiguration(new ClientModule.OkhttpConfiguration() { // from class: com.yj.coffeemachines.app.-$$Lambda$GlobalConfiguration$VZ87U-_gdCzvtb8ZVeG2PklmaTw
            @Override // com.jess.arms.di.module.ClientModule.OkhttpConfiguration
            public final void configOkhttp(Context context2, OkHttpClient.Builder builder2) {
                GlobalConfiguration.lambda$applyOptions$2(context2, builder2);
            }
        }).rxCacheConfiguration(new ClientModule.RxCacheConfiguration() { // from class: com.yj.coffeemachines.app.-$$Lambda$GlobalConfiguration$7kpeqmpYSI7hyy54PGn1BqQ5E54
            @Override // com.jess.arms.di.module.ClientModule.RxCacheConfiguration
            public final RxCache configRxCache(Context context2, RxCache.Builder builder2) {
                return GlobalConfiguration.lambda$applyOptions$3(context2, builder2);
            }
        });
    }

    @Override // com.jess.arms.integration.ConfigModule
    public void injectActivityLifecycle(@NonNull Context context, @NonNull List<Application.ActivityLifecycleCallbacks> list) {
        list.add(new ActivityLifecycleCallbacksImpl());
    }

    @Override // com.jess.arms.integration.ConfigModule
    public void injectAppLifecycle(@NonNull Context context, @NonNull List<AppLifecycles> list) {
        list.add(new AppLifecyclesImpl());
    }

    @Override // com.jess.arms.integration.ConfigModule
    public void injectFragmentLifecycle(@NonNull Context context, @NonNull List<FragmentManager.FragmentLifecycleCallbacks> list) {
        list.add(new FragmentLifecycleCallbacksImpl());
    }
}
