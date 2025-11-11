package com.yj.coffeemachines.di.module;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import java.util.List;

/* loaded from: classes.dex */
public final class Ops6_LogcatModule_GetdataFactory implements Factory<List<Object>> {

    /* loaded from: classes.dex */
    private static final class InstanceHolder {
        private static final Ops6_LogcatModule_GetdataFactory INSTANCE = new Ops6_LogcatModule_GetdataFactory();

        private InstanceHolder() {
        }
    }

    public static Ops6_LogcatModule_GetdataFactory create() {
        return InstanceHolder.INSTANCE;
    }

    public static List<Object> getdata() {
        return (List) Preconditions.checkNotNull(Ops6_LogcatModule.getdata(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    public List<Object> get() {
        return getdata();
    }
}
