package io.rx_cache2.internal;

import dagger.internal.Factory;
import io.rx_cache2.internal.encrypt.FileEncryptor;
import io.victoralbertos.jolyglot.JolyglotGenerics;
import java.io.File;
import javax.inject.Provider;

/* loaded from: classes2.dex */
public final class Disk_Factory implements Factory<Disk> {
    private final Provider<File> cacheDirectoryProvider;
    private final Provider<FileEncryptor> fileEncryptorProvider;
    private final Provider<JolyglotGenerics> jolyglotProvider;

    public Disk_Factory(Provider<File> provider, Provider<FileEncryptor> provider2, Provider<JolyglotGenerics> provider3) {
        this.cacheDirectoryProvider = provider;
        this.fileEncryptorProvider = provider2;
        this.jolyglotProvider = provider3;
    }

    public static Disk_Factory create(Provider<File> provider, Provider<FileEncryptor> provider2, Provider<JolyglotGenerics> provider3) {
        return new Disk_Factory(provider, provider2, provider3);
    }

    @Override // javax.inject.Provider
    public Disk get() {
        return new Disk(this.cacheDirectoryProvider.get(), this.fileEncryptorProvider.get(), this.jolyglotProvider.get());
    }
}
