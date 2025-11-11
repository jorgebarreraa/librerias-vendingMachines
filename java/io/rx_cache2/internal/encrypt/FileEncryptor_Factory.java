package io.rx_cache2.internal.encrypt;

import dagger.internal.Factory;
import javax.inject.Provider;

/* loaded from: classes2.dex */
public final class FileEncryptor_Factory implements Factory<FileEncryptor> {
    private final Provider<Encryptor> encryptorProvider;

    public FileEncryptor_Factory(Provider<Encryptor> provider) {
        this.encryptorProvider = provider;
    }

    public static FileEncryptor_Factory create(Provider<Encryptor> provider) {
        return new FileEncryptor_Factory(provider);
    }

    @Override // javax.inject.Provider
    public FileEncryptor get() {
        return new FileEncryptor(this.encryptorProvider.get());
    }
}
