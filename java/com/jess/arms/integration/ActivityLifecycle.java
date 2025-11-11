package com.jess.arms.integration;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import com.jess.arms.base.delegate.ActivityDelegate;
import com.jess.arms.base.delegate.ActivityDelegateImpl;
import com.jess.arms.base.delegate.IActivity;
import com.jess.arms.integration.cache.Cache;
import com.jess.arms.integration.cache.IntelligentCache;
import com.jess.arms.utils.Preconditions;
import dagger.Lazy;
import java.util.Iterator;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
/* loaded from: classes.dex */
public class ActivityLifecycle implements Application.ActivityLifecycleCallbacks {

    @Inject
    AppManager mAppManager;

    @Inject
    Application mApplication;

    @Inject
    Cache<String, Object> mExtras;

    @Inject
    Lazy<FragmentManager.FragmentLifecycleCallbacks> mFragmentLifecycle;

    @Inject
    Lazy<List<FragmentManager.FragmentLifecycleCallbacks>> mFragmentLifecycles;

    @Inject
    public ActivityLifecycle() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    private ActivityDelegate fetchActivityDelegate(Activity activity) {
        if (activity instanceof IActivity) {
            return (ActivityDelegate) getCacheFromActivity((IActivity) activity).get(IntelligentCache.getKeyOfKeep(ActivityDelegate.ACTIVITY_DELEGATE));
        }
        return null;
    }

    @NonNull
    private Cache<String, Object> getCacheFromActivity(IActivity iActivity) {
        Cache<String, Object> provideCache = iActivity.provideCache();
        Preconditions.checkNotNull(provideCache, "%s cannot be null on Activity", Cache.class.getName());
        return provideCache;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void registerFragmentCallbacks(Activity activity) {
        boolean z = !(activity instanceof IActivity) || ((IActivity) activity).useFragment();
        if ((activity instanceof FragmentActivity) && z) {
            FragmentActivity fragmentActivity = (FragmentActivity) activity;
            fragmentActivity.getSupportFragmentManager().registerFragmentLifecycleCallbacks(this.mFragmentLifecycle.get(), true);
            if (this.mExtras.containsKey(IntelligentCache.getKeyOfKeep(ConfigModule.class.getName()))) {
                List list = (List) this.mExtras.get(IntelligentCache.getKeyOfKeep(ConfigModule.class.getName()));
                if (list != null) {
                    Iterator it2 = list.iterator();
                    while (it2.hasNext()) {
                        ((ConfigModule) it2.next()).injectFragmentLifecycle(this.mApplication, this.mFragmentLifecycles.get());
                    }
                }
                this.mExtras.remove(IntelligentCache.getKeyOfKeep(ConfigModule.class.getName()));
            }
            Iterator<FragmentManager.FragmentLifecycleCallbacks> it3 = this.mFragmentLifecycles.get().iterator();
            while (it3.hasNext()) {
                fragmentActivity.getSupportFragmentManager().registerFragmentLifecycleCallbacks(it3.next(), true);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityCreated(Activity activity, Bundle bundle) {
        if (!(activity.getIntent() != null ? activity.getIntent().getBooleanExtra(AppManager.IS_NOT_ADD_ACTIVITY_LIST, false) : false)) {
            this.mAppManager.addActivity(activity);
        }
        if (activity instanceof IActivity) {
            ActivityDelegate fetchActivityDelegate = fetchActivityDelegate(activity);
            if (fetchActivityDelegate == null) {
                Cache<String, Object> cacheFromActivity = getCacheFromActivity((IActivity) activity);
                ActivityDelegateImpl activityDelegateImpl = new ActivityDelegateImpl(activity);
                cacheFromActivity.put(IntelligentCache.getKeyOfKeep(ActivityDelegate.ACTIVITY_DELEGATE), activityDelegateImpl);
                fetchActivityDelegate = activityDelegateImpl;
            }
            fetchActivityDelegate.onCreate(bundle);
        }
        registerFragmentCallbacks(activity);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityDestroyed(Activity activity) {
        this.mAppManager.removeActivity(activity);
        ActivityDelegate fetchActivityDelegate = fetchActivityDelegate(activity);
        if (fetchActivityDelegate != null) {
            fetchActivityDelegate.onDestroy();
            getCacheFromActivity((IActivity) activity).clear();
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPaused(Activity activity) {
        ActivityDelegate fetchActivityDelegate = fetchActivityDelegate(activity);
        if (fetchActivityDelegate != null) {
            fetchActivityDelegate.onPause();
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityResumed(Activity activity) {
        this.mAppManager.setCurrentActivity(activity);
        ActivityDelegate fetchActivityDelegate = fetchActivityDelegate(activity);
        if (fetchActivityDelegate != null) {
            fetchActivityDelegate.onResume();
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        ActivityDelegate fetchActivityDelegate = fetchActivityDelegate(activity);
        if (fetchActivityDelegate != null) {
            fetchActivityDelegate.onSaveInstanceState(bundle);
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStarted(Activity activity) {
        ActivityDelegate fetchActivityDelegate = fetchActivityDelegate(activity);
        if (fetchActivityDelegate != null) {
            fetchActivityDelegate.onStart();
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStopped(Activity activity) {
        if (this.mAppManager.getCurrentActivity() == activity) {
            this.mAppManager.setCurrentActivity(null);
        }
        ActivityDelegate fetchActivityDelegate = fetchActivityDelegate(activity);
        if (fetchActivityDelegate != null) {
            fetchActivityDelegate.onStop();
        }
    }
}
