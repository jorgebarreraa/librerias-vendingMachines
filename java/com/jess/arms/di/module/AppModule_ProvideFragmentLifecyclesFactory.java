package com.jess.arms.di.module;

import androidx.fragment.app.FragmentManager;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import java.util.List;

/* loaded from: classes.dex */
public final class AppModule_ProvideFragmentLifecyclesFactory implements Factory<List<FragmentManager.FragmentLifecycleCallbacks>> {

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class InstanceHolder {
        private static final AppModule_ProvideFragmentLifecyclesFactory INSTANCE = new AppModule_ProvideFragmentLifecyclesFactory();

        private InstanceHolder() {
        }
    }

    public static AppModule_ProvideFragmentLifecyclesFactory create() {
        return InstanceHolder.INSTANCE;
    }

    public static List<FragmentManager.FragmentLifecycleCallbacks> provideFragmentLifecycles() {
        return (List) Preconditions.checkNotNull(AppModule.provideFragmentLifecycles(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    public List<FragmentManager.FragmentLifecycleCallbacks> get() {
        return provideFragmentLifecycles();
    }
}
