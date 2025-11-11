package com.jess.arms.base;

import dagger.internal.Factory;

/* loaded from: classes.dex */
public final class Unused_Factory implements Factory<Unused> {

    /* loaded from: classes.dex */
    private static final class InstanceHolder {
        private static final Unused_Factory INSTANCE = new Unused_Factory();

        private InstanceHolder() {
        }
    }

    public static Unused_Factory create() {
        return InstanceHolder.INSTANCE;
    }

    public static Unused newInstance() {
        return new Unused();
    }

    @Override // javax.inject.Provider
    public Unused get() {
        return newInstance();
    }
}
