package com.jess.arms.integration.lifecycle;

import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class ActivityLifecycleForRxLifecycle_Factory implements Factory<ActivityLifecycleForRxLifecycle> {
    private final Provider<FragmentLifecycleForRxLifecycle> mFragmentLifecycleProvider;

    public ActivityLifecycleForRxLifecycle_Factory(Provider<FragmentLifecycleForRxLifecycle> provider) {
        this.mFragmentLifecycleProvider = provider;
    }

    public static ActivityLifecycleForRxLifecycle_Factory create(Provider<FragmentLifecycleForRxLifecycle> provider) {
        return new ActivityLifecycleForRxLifecycle_Factory(provider);
    }

    public static ActivityLifecycleForRxLifecycle newInstance() {
        return new ActivityLifecycleForRxLifecycle();
    }

    @Override // javax.inject.Provider
    public ActivityLifecycleForRxLifecycle get() {
        ActivityLifecycleForRxLifecycle newInstance = newInstance();
        ActivityLifecycleForRxLifecycle_MembersInjector.injectMFragmentLifecycle(newInstance, DoubleCheck.lazy(this.mFragmentLifecycleProvider));
        return newInstance;
    }
}
