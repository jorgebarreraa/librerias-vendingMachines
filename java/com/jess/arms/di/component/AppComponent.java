package com.jess.arms.di.component;

import android.app.Application;
import com.google.gson.Gson;
import com.jess.arms.base.delegate.AppDelegate;
import com.jess.arms.di.module.AppModule;
import com.jess.arms.di.module.ClientModule;
import com.jess.arms.di.module.GlobalConfigModule;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.integration.AppManager;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.integration.cache.Cache;
import dagger.BindsInstance;
import dagger.Component;
import java.io.File;
import java.util.concurrent.ExecutorService;
import javax.inject.Singleton;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import okhttp3.OkHttpClient;

@Component(modules = {AppModule.class, ClientModule.class, GlobalConfigModule.class})
@Singleton
/* loaded from: classes.dex */
public interface AppComponent {

    @Component.Builder
    /* loaded from: classes.dex */
    public interface Builder {
        @BindsInstance
        Builder application(Application application);

        AppComponent build();

        Builder globalConfigModule(GlobalConfigModule globalConfigModule);
    }

    @Deprecated
    AppManager appManager();

    Application application();

    Cache.Factory cacheFactory();

    File cacheFile();

    ExecutorService executorService();

    Cache<String, Object> extras();

    Gson gson();

    ImageLoader imageLoader();

    void inject(AppDelegate appDelegate);

    OkHttpClient okHttpClient();

    IRepositoryManager repositoryManager();

    RxErrorHandler rxErrorHandler();
}
