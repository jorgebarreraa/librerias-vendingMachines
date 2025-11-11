package com.jess.arms.integration;

import android.app.Application;
import androidx.fragment.app.FragmentManager;
import com.jess.arms.integration.cache.Cache;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import dagger.internal.InjectedFieldSignature;
import java.util.List;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class ActivityLifecycle_MembersInjector implements MembersInjector<ActivityLifecycle> {
    private final Provider<AppManager> mAppManagerProvider;
    private final Provider<Application> mApplicationProvider;
    private final Provider<Cache<String, Object>> mExtrasProvider;
    private final Provider<FragmentManager.FragmentLifecycleCallbacks> mFragmentLifecycleProvider;
    private final Provider<List<FragmentManager.FragmentLifecycleCallbacks>> mFragmentLifecyclesProvider;

    public ActivityLifecycle_MembersInjector(Provider<AppManager> provider, Provider<Application> provider2, Provider<Cache<String, Object>> provider3, Provider<FragmentManager.FragmentLifecycleCallbacks> provider4, Provider<List<FragmentManager.FragmentLifecycleCallbacks>> provider5) {
        this.mAppManagerProvider = provider;
        this.mApplicationProvider = provider2;
        this.mExtrasProvider = provider3;
        this.mFragmentLifecycleProvider = provider4;
        this.mFragmentLifecyclesProvider = provider5;
    }

    public static MembersInjector<ActivityLifecycle> create(Provider<AppManager> provider, Provider<Application> provider2, Provider<Cache<String, Object>> provider3, Provider<FragmentManager.FragmentLifecycleCallbacks> provider4, Provider<List<FragmentManager.FragmentLifecycleCallbacks>> provider5) {
        return new ActivityLifecycle_MembersInjector(provider, provider2, provider3, provider4, provider5);
    }

    @InjectedFieldSignature("com.jess.arms.integration.ActivityLifecycle.mAppManager")
    public static void injectMAppManager(ActivityLifecycle activityLifecycle, AppManager appManager) {
        activityLifecycle.mAppManager = appManager;
    }

    @InjectedFieldSignature("com.jess.arms.integration.ActivityLifecycle.mApplication")
    public static void injectMApplication(ActivityLifecycle activityLifecycle, Application application) {
        activityLifecycle.mApplication = application;
    }

    @InjectedFieldSignature("com.jess.arms.integration.ActivityLifecycle.mExtras")
    public static void injectMExtras(ActivityLifecycle activityLifecycle, Cache<String, Object> cache) {
        activityLifecycle.mExtras = cache;
    }

    @InjectedFieldSignature("com.jess.arms.integration.ActivityLifecycle.mFragmentLifecycle")
    public static void injectMFragmentLifecycle(ActivityLifecycle activityLifecycle, Lazy<FragmentManager.FragmentLifecycleCallbacks> lazy) {
        activityLifecycle.mFragmentLifecycle = lazy;
    }

    @InjectedFieldSignature("com.jess.arms.integration.ActivityLifecycle.mFragmentLifecycles")
    public static void injectMFragmentLifecycles(ActivityLifecycle activityLifecycle, Lazy<List<FragmentManager.FragmentLifecycleCallbacks>> lazy) {
        activityLifecycle.mFragmentLifecycles = lazy;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(ActivityLifecycle activityLifecycle) {
        injectMAppManager(activityLifecycle, this.mAppManagerProvider.get());
        injectMApplication(activityLifecycle, this.mApplicationProvider.get());
        injectMExtras(activityLifecycle, this.mExtrasProvider.get());
        injectMFragmentLifecycle(activityLifecycle, DoubleCheck.lazy(this.mFragmentLifecycleProvider));
        injectMFragmentLifecycles(activityLifecycle, DoubleCheck.lazy(this.mFragmentLifecyclesProvider));
    }
}
