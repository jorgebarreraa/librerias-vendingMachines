package io.rx_cache2.internal.migration;

import dagger.internal.Factory;
import io.rx_cache2.MigrationCache;
import io.rx_cache2.internal.Persistence;
import java.util.List;
import javax.inject.Provider;

/* loaded from: classes2.dex */
public final class DoMigrations_Factory implements Factory<DoMigrations> {
    private final Provider<String> encryptKeyProvider;
    private final Provider<List<MigrationCache>> migrationsProvider;
    private final Provider<Persistence> persistenceProvider;

    public DoMigrations_Factory(Provider<Persistence> provider, Provider<List<MigrationCache>> provider2, Provider<String> provider3) {
        this.persistenceProvider = provider;
        this.migrationsProvider = provider2;
        this.encryptKeyProvider = provider3;
    }

    public static DoMigrations_Factory create(Provider<Persistence> provider, Provider<List<MigrationCache>> provider2, Provider<String> provider3) {
        return new DoMigrations_Factory(provider, provider2, provider3);
    }

    @Override // javax.inject.Provider
    public DoMigrations get() {
        return new DoMigrations(this.persistenceProvider.get(), this.migrationsProvider.get(), this.encryptKeyProvider.get());
    }
}
