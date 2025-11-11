package com.jess.arms.base.delegate;

import android.app.Application;
import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.res.Configuration;
import androidx.annotation.NonNull;
import com.jess.arms.base.App;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.component.DaggerAppComponent;
import com.jess.arms.di.module.GlobalConfigModule;
import com.jess.arms.integration.ConfigModule;
import com.jess.arms.integration.ManifestParser;
import com.jess.arms.integration.cache.IntelligentCache;
import com.jess.arms.utils.Preconditions;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;

/* loaded from: classes.dex */
public class AppDelegate implements App, AppLifecycles {

    @Inject
    @Named("ActivityLifecycle")
    protected Application.ActivityLifecycleCallbacks mActivityLifecycle;

    @Inject
    @Named("ActivityLifecycleForRxLifecycle")
    protected Application.ActivityLifecycleCallbacks mActivityLifecycleForRxLifecycle;
    private AppComponent mAppComponent;
    private Application mApplication;
    private ComponentCallbacks2 mComponentCallback;
    private List<ConfigModule> mModules;
    private List<AppLifecycles> mAppLifecycles = new ArrayList();
    private List<Application.ActivityLifecycleCallbacks> mActivityLifecycles = new ArrayList();

    /* loaded from: classes.dex */
    private static class AppComponentCallbacks implements ComponentCallbacks2 {
        AppComponentCallbacks(Application application, AppComponent appComponent) {
        }

        @Override // android.content.ComponentCallbacks
        public void onConfigurationChanged(Configuration configuration) {
        }

        @Override // android.content.ComponentCallbacks
        public void onLowMemory() {
        }

        @Override // android.content.ComponentCallbacks2
        public void onTrimMemory(int i) {
        }
    }

    public AppDelegate(@NonNull Context context) {
        this.mModules = new ManifestParser(context).parse();
        for (ConfigModule configModule : this.mModules) {
            configModule.injectAppLifecycle(context, this.mAppLifecycles);
            configModule.injectActivityLifecycle(context, this.mActivityLifecycles);
        }
    }

    private GlobalConfigModule getGlobalConfigModule(Context context, List<ConfigModule> list) {
        GlobalConfigModule.Builder builder = GlobalConfigModule.builder();
        Iterator<ConfigModule> it2 = list.iterator();
        while (it2.hasNext()) {
            it2.next().applyOptions(context, builder);
        }
        return builder.build();
    }

    @Override // com.jess.arms.base.delegate.AppLifecycles
    public void attachBaseContext(@NonNull Context context) {
        Iterator<AppLifecycles> it2 = this.mAppLifecycles.iterator();
        while (it2.hasNext()) {
            it2.next().attachBaseContext(context);
        }
    }

    @Override // com.jess.arms.base.App
    @NonNull
    public AppComponent getAppComponent() {
        AppComponent appComponent = this.mAppComponent;
        Object[] objArr = new Object[3];
        objArr[0] = AppComponent.class.getName();
        objArr[1] = getClass().getName();
        Application application = this.mApplication;
        objArr[2] = (application == null ? Application.class : application.getClass()).getName();
        Preconditions.checkNotNull(appComponent, "%s == null, first call %s#onCreate(Application) in %s#onCreate()", objArr);
        return this.mAppComponent;
    }

    @Override // com.jess.arms.base.delegate.AppLifecycles
    public void onCreate(@NonNull Application application) {
        this.mApplication = application;
        this.mAppComponent = DaggerAppComponent.builder().application(this.mApplication).globalConfigModule(getGlobalConfigModule(this.mApplication, this.mModules)).build();
        this.mAppComponent.inject(this);
        this.mAppComponent.extras().put(IntelligentCache.getKeyOfKeep(ConfigModule.class.getName()), this.mModules);
        this.mModules = null;
        this.mApplication.registerActivityLifecycleCallbacks(this.mActivityLifecycle);
        this.mApplication.registerActivityLifecycleCallbacks(this.mActivityLifecycleForRxLifecycle);
        Iterator<Application.ActivityLifecycleCallbacks> it2 = this.mActivityLifecycles.iterator();
        while (it2.hasNext()) {
            this.mApplication.registerActivityLifecycleCallbacks(it2.next());
        }
        this.mComponentCallback = new AppComponentCallbacks(this.mApplication, this.mAppComponent);
        this.mApplication.registerComponentCallbacks(this.mComponentCallback);
        Iterator<AppLifecycles> it3 = this.mAppLifecycles.iterator();
        while (it3.hasNext()) {
            it3.next().onCreate(this.mApplication);
        }
    }

    @Override // com.jess.arms.base.delegate.AppLifecycles
    public void onTerminate(@NonNull Application application) {
        Application.ActivityLifecycleCallbacks activityLifecycleCallbacks = this.mActivityLifecycle;
        if (activityLifecycleCallbacks != null) {
            this.mApplication.unregisterActivityLifecycleCallbacks(activityLifecycleCallbacks);
        }
        Application.ActivityLifecycleCallbacks activityLifecycleCallbacks2 = this.mActivityLifecycleForRxLifecycle;
        if (activityLifecycleCallbacks2 != null) {
            this.mApplication.unregisterActivityLifecycleCallbacks(activityLifecycleCallbacks2);
        }
        ComponentCallbacks2 componentCallbacks2 = this.mComponentCallback;
        if (componentCallbacks2 != null) {
            this.mApplication.unregisterComponentCallbacks(componentCallbacks2);
        }
        List<Application.ActivityLifecycleCallbacks> list = this.mActivityLifecycles;
        if (list != null && list.size() > 0) {
            Iterator<Application.ActivityLifecycleCallbacks> it2 = this.mActivityLifecycles.iterator();
            while (it2.hasNext()) {
                this.mApplication.unregisterActivityLifecycleCallbacks(it2.next());
            }
        }
        List<AppLifecycles> list2 = this.mAppLifecycles;
        if (list2 != null && list2.size() > 0) {
            Iterator<AppLifecycles> it3 = this.mAppLifecycles.iterator();
            while (it3.hasNext()) {
                it3.next().onTerminate(this.mApplication);
            }
        }
        this.mAppComponent = null;
        this.mActivityLifecycle = null;
        this.mActivityLifecycleForRxLifecycle = null;
        this.mActivityLifecycles = null;
        this.mComponentCallback = null;
        this.mAppLifecycles = null;
        this.mApplication = null;
    }
}
