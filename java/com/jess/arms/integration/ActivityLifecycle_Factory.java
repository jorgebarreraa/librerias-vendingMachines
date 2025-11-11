package com.jess.arms.integration;

import android.app.Application;
import androidx.fragment.app.FragmentManager;
import com.jess.arms.integration.cache.Cache;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import java.util.List;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class ActivityLifecycle_Factory implements Factory<ActivityLifecycle> {
    private final Provider<AppManager> mAppManagerProvider;
    private final Provider<Application> mApplicationProvider;
    private final Provider<Cache<String, Object>> mExtrasProvider;
    private final Provider<FragmentManager.FragmentLifecycleCallbacks> mFragmentLifecycleProvider;
    private final Provider<List<FragmentManager.FragmentLifecycleCallbacks>> mFragmentLifecyclesProvider;

    public ActivityLifecycle_Factory(Provider<AppManager> provider, Provider<Application> provider2, Provider<Cache<String, Object>> provider3, Provider<FragmentManager.FragmentLifecycleCallbacks> provider4, Provider<List<FragmentManager.FragmentLifecycleCallbacks>> provider5) {
        this.mAppManagerProvider = provider;
        this.mApplicationProvider = provider2;
        this.mExtrasProvider = provider3;
        this.mFragmentLifecycleProvider = provider4;
        this.mFragmentLifecyclesProvider = provider5;
    }

    public static ActivityLifecycle_Factory create(Provider<AppManager> provider, Provider<Application> provider2, Provider<Cache<String, Object>> provider3, Provider<FragmentManager.FragmentLifecycleCallbacks> provider4, Provider<List<FragmentManager.FragmentLifecycleCallbacks>> provider5) {
        return new ActivityLifecycle_Factory(provider, provider2, provider3, provider4, provider5);
    }

    public static ActivityLifecycle newInstance() {
        return new ActivityLifecycle();
    }

    @Override // javax.inject.Provider
    public ActivityLifecycle get() {
        ActivityLifecycle newInstance = newInstance();
        ActivityLifecycle_MembersInjector.injectMAppManager(newInstance, this.mAppManagerProvider.get());
        ActivityLifecycle_MembersInjector.injectMApplication(newInstance, this.mApplicationProvider.get());
        ActivityLifecycle_MembersInjector.injectMExtras(newInstance, this.mExtrasProvider.get());
        ActivityLifecycle_MembersInjector.injectMFragmentLifecycle(newInstance, DoubleCheck.lazy(this.mFragmentLifecycleProvider));
        ActivityLifecycle_MembersInjector.injectMFragmentLifecycles(newInstance, DoubleCheck.lazy(this.mFragmentLifecyclesProvider));
        return newInstance;
    }
}
