package io.rx_cache2.internal.migration;

import dagger.internal.Factory;
import io.rx_cache2.internal.Persistence;
import javax.inject.Provider;

/* loaded from: classes2.dex */
public final class DeleteRecordMatchingClassName_Factory implements Factory<DeleteRecordMatchingClassName> {
    private final Provider<String> encryptKeyProvider;
    private final Provider<Persistence> persistenceProvider;

    public DeleteRecordMatchingClassName_Factory(Provider<Persistence> provider, Provider<String> provider2) {
        this.persistenceProvider = provider;
        this.encryptKeyProvider = provider2;
    }

    public static DeleteRecordMatchingClassName_Factory create(Provider<Persistence> provider, Provider<String> provider2) {
        return new DeleteRecordMatchingClassName_Factory(provider, provider2);
    }

    @Override // javax.inject.Provider
    public DeleteRecordMatchingClassName get() {
        return new DeleteRecordMatchingClassName(this.persistenceProvider.get(), this.encryptKeyProvider.get());
    }
}
