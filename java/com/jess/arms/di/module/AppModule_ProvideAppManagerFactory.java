package com.jess.arms.di.module;

import android.app.Application;
import com.jess.arms.integration.AppManager;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class AppModule_ProvideAppManagerFactory implements Factory<AppManager> {
    private final Provider<Application> applicationProvider;

    public AppModule_ProvideAppManagerFactory(Provider<Application> provider) {
        this.applicationProvider = provider;
    }

    public static AppModule_ProvideAppManagerFactory create(Provider<Application> provider) {
        return new AppModule_ProvideAppManagerFactory(provider);
    }

    public static AppManager provideAppManager(Application application) {
        return (AppManager) Preconditions.checkNotNull(AppModule.provideAppManager(application), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    public AppManager get() {
        return provideAppManager(this.applicationProvider.get());
    }
}
