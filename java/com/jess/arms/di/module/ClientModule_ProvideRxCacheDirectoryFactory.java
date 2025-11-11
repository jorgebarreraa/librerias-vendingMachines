package com.jess.arms.di.module;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import java.io.File;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class ClientModule_ProvideRxCacheDirectoryFactory implements Factory<File> {
    private final Provider<File> cacheDirProvider;

    public ClientModule_ProvideRxCacheDirectoryFactory(Provider<File> provider) {
        this.cacheDirProvider = provider;
    }

    public static ClientModule_ProvideRxCacheDirectoryFactory create(Provider<File> provider) {
        return new ClientModule_ProvideRxCacheDirectoryFactory(provider);
    }

    public static File provideRxCacheDirectory(File file) {
        return (File) Preconditions.checkNotNull(ClientModule.provideRxCacheDirectory(file), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    public File get() {
        return provideRxCacheDirectory(this.cacheDirProvider.get());
    }
}
