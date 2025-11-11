package com.jess.arms.di.module;

import android.app.Application;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import java.io.File;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class GlobalConfigModule_ProvideCacheFileFactory implements Factory<File> {
    private final Provider<Application> applicationProvider;
    private final GlobalConfigModule module;

    public GlobalConfigModule_ProvideCacheFileFactory(GlobalConfigModule globalConfigModule, Provider<Application> provider) {
        this.module = globalConfigModule;
        this.applicationProvider = provider;
    }

    public static GlobalConfigModule_ProvideCacheFileFactory create(GlobalConfigModule globalConfigModule, Provider<Application> provider) {
        return new GlobalConfigModule_ProvideCacheFileFactory(globalConfigModule, provider);
    }

    public static File provideCacheFile(GlobalConfigModule globalConfigModule, Application application) {
        return (File) Preconditions.checkNotNull(globalConfigModule.provideCacheFile(application), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    public File get() {
        return provideCacheFile(this.module, this.applicationProvider.get());
    }
}
