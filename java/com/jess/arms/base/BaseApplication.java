package com.jess.arms.base;

import android.app.Application;
import android.content.Context;
import androidx.annotation.NonNull;
import com.hjq.language.MultiLanguages;
import com.jess.arms.base.delegate.AppDelegate;
import com.jess.arms.base.delegate.AppLifecycles;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.Preconditions;

/* loaded from: classes.dex */
public class BaseApplication extends Application implements App {
    private AppLifecycles mAppDelegate;

    @Override // android.content.ContextWrapper
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(MultiLanguages.attach(context));
        if (this.mAppDelegate == null) {
            this.mAppDelegate = new AppDelegate(context);
        }
        this.mAppDelegate.attachBaseContext(context);
    }

    @Override // com.jess.arms.base.App
    @NonNull
    public AppComponent getAppComponent() {
        Preconditions.checkNotNull(this.mAppDelegate, "%s cannot be null", AppDelegate.class.getName());
        AppLifecycles appLifecycles = this.mAppDelegate;
        Preconditions.checkState(appLifecycles instanceof App, "%s must be implements %s", appLifecycles.getClass().getName(), App.class.getName());
        return ((App) this.mAppDelegate).getAppComponent();
    }

    @Override // android.app.Application
    public void onCreate() {
        super.onCreate();
        MultiLanguages.init(this);
        AppLifecycles appLifecycles = this.mAppDelegate;
        if (appLifecycles != null) {
            appLifecycles.onCreate(this);
        }
    }

    @Override // android.app.Application
    public void onTerminate() {
        super.onTerminate();
        AppLifecycles appLifecycles = this.mAppDelegate;
        if (appLifecycles != null) {
            appLifecycles.onTerminate(this);
        }
    }
}
