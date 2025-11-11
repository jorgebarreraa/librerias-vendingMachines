package com.jess.arms.integration;

import dagger.internal.Factory;

/* loaded from: classes.dex */
public final class FragmentLifecycle_Factory implements Factory<FragmentLifecycle> {

    /* loaded from: classes.dex */
    private static final class InstanceHolder {
        private static final FragmentLifecycle_Factory INSTANCE = new FragmentLifecycle_Factory();

        private InstanceHolder() {
        }
    }

    public static FragmentLifecycle_Factory create() {
        return InstanceHolder.INSTANCE;
    }

    public static FragmentLifecycle newInstance() {
        return new FragmentLifecycle();
    }

    @Override // javax.inject.Provider
    public FragmentLifecycle get() {
        return newInstance();
    }
}
