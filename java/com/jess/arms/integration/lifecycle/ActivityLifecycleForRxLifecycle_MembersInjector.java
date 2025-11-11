package com.jess.arms.integration.lifecycle;

import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import dagger.internal.InjectedFieldSignature;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class ActivityLifecycleForRxLifecycle_MembersInjector implements MembersInjector<ActivityLifecycleForRxLifecycle> {
    private final Provider<FragmentLifecycleForRxLifecycle> mFragmentLifecycleProvider;

    public ActivityLifecycleForRxLifecycle_MembersInjector(Provider<FragmentLifecycleForRxLifecycle> provider) {
        this.mFragmentLifecycleProvider = provider;
    }

    public static MembersInjector<ActivityLifecycleForRxLifecycle> create(Provider<FragmentLifecycleForRxLifecycle> provider) {
        return new ActivityLifecycleForRxLifecycle_MembersInjector(provider);
    }

    @InjectedFieldSignature("com.jess.arms.integration.lifecycle.ActivityLifecycleForRxLifecycle.mFragmentLifecycle")
    public static void injectMFragmentLifecycle(ActivityLifecycleForRxLifecycle activityLifecycleForRxLifecycle, Lazy<FragmentLifecycleForRxLifecycle> lazy) {
        activityLifecycleForRxLifecycle.mFragmentLifecycle = lazy;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(ActivityLifecycleForRxLifecycle activityLifecycleForRxLifecycle) {
        injectMFragmentLifecycle(activityLifecycleForRxLifecycle, DoubleCheck.lazy(this.mFragmentLifecycleProvider));
    }
}
