package com.jess.arms.integration.lifecycle;

import dagger.internal.Factory;

/* loaded from: classes.dex */
public final class FragmentLifecycleForRxLifecycle_Factory implements Factory<FragmentLifecycleForRxLifecycle> {

    /* loaded from: classes.dex */
    private static final class InstanceHolder {
        private static final FragmentLifecycleForRxLifecycle_Factory INSTANCE = new FragmentLifecycleForRxLifecycle_Factory();

        private InstanceHolder() {
        }
    }

    public static FragmentLifecycleForRxLifecycle_Factory create() {
        return InstanceHolder.INSTANCE;
    }

    public static FragmentLifecycleForRxLifecycle newInstance() {
        return new FragmentLifecycleForRxLifecycle();
    }

    @Override // javax.inject.Provider
    public FragmentLifecycleForRxLifecycle get() {
        return newInstance();
    }
}
