package com.jess.arms.di.module;

import android.app.Application;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jess.arms.integration.ActivityLifecycle;
import com.jess.arms.integration.AppManager;
import com.jess.arms.integration.FragmentLifecycle;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.integration.RepositoryManager;
import com.jess.arms.integration.cache.Cache;
import com.jess.arms.integration.cache.CacheType;
import com.jess.arms.integration.lifecycle.ActivityLifecycleForRxLifecycle;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import javax.inject.Singleton;

@Module
/* loaded from: classes.dex */
public abstract class AppModule {

    /* loaded from: classes.dex */
    public interface GsonConfiguration {
        void configGson(@NonNull Context context, @NonNull GsonBuilder gsonBuilder);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    public static AppManager provideAppManager(Application application) {
        return AppManager.getAppManager().init(application);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    public static Cache<String, Object> provideExtras(Cache.Factory factory) {
        return factory.build(CacheType.EXTRAS);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    public static List<FragmentManager.FragmentLifecycleCallbacks> provideFragmentLifecycles() {
        return new ArrayList();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    public static Gson provideGson(Application application, @Nullable GsonConfiguration gsonConfiguration) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        if (gsonConfiguration != null) {
            gsonConfiguration.configGson(application, gsonBuilder);
        }
        return gsonBuilder.create();
    }

    @Binds
    @Named("ActivityLifecycle")
    abstract Application.ActivityLifecycleCallbacks bindActivityLifecycle(ActivityLifecycle activityLifecycle);

    @Binds
    @Named("ActivityLifecycleForRxLifecycle")
    abstract Application.ActivityLifecycleCallbacks bindActivityLifecycleForRxLifecycle(ActivityLifecycleForRxLifecycle activityLifecycleForRxLifecycle);

    @Binds
    abstract FragmentManager.FragmentLifecycleCallbacks bindFragmentLifecycle(FragmentLifecycle fragmentLifecycle);

    @Binds
    abstract IRepositoryManager bindRepositoryManager(RepositoryManager repositoryManager);
}
