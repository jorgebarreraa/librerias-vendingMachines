package io.rx_cache2.internal;

import dagger.internal.DoubleCheck;
import dagger.internal.Preconditions;
import io.rx_cache2.MigrationCache;
import io.rx_cache2.internal.cache.EvictExpirableRecordsPersistence;
import io.rx_cache2.internal.cache.EvictExpirableRecordsPersistence_Factory;
import io.rx_cache2.internal.cache.EvictExpiredRecordsPersistence;
import io.rx_cache2.internal.cache.EvictExpiredRecordsPersistence_Factory;
import io.rx_cache2.internal.cache.EvictRecord_Factory;
import io.rx_cache2.internal.cache.GetDeepCopy;
import io.rx_cache2.internal.cache.HasRecordExpired_Factory;
import io.rx_cache2.internal.cache.RetrieveRecord_Factory;
import io.rx_cache2.internal.cache.SaveRecord_Factory;
import io.rx_cache2.internal.cache.TwoLayersCache;
import io.rx_cache2.internal.cache.TwoLayersCache_Factory;
import io.rx_cache2.internal.encrypt.Encryptor;
import io.rx_cache2.internal.encrypt.FileEncryptor_Factory;
import io.rx_cache2.internal.migration.DoMigrations;
import io.victoralbertos.jolyglot.JolyglotGenerics;
import java.io.File;
import java.util.List;
import javax.inject.Provider;

/* loaded from: classes2.dex */
public final class DaggerRxCacheComponent implements RxCacheComponent {
    private Disk_Factory diskProvider;
    private Provider<EvictExpirableRecordsPersistence> evictExpirableRecordsPersistenceProvider;
    private Provider<EvictExpiredRecordsPersistence> evictExpiredRecordsPersistenceProvider;
    private EvictRecord_Factory evictRecordProvider;
    private FileEncryptor_Factory fileEncryptorProvider;
    private Provider<Integer> maxMbPersistenceCacheProvider;
    private Provider<File> provideCacheDirectoryProvider;
    private Provider<String> provideEncryptKeyProvider;
    private Provider<Encryptor> provideEncryptorProvider;
    private Provider<JolyglotGenerics> provideJolyglotProvider;
    private Provider<Memory> provideMemoryProvider;
    private Provider<List<MigrationCache>> provideMigrationsProvider;
    private Provider<Persistence> providePersistenceProvider;
    private RetrieveRecord_Factory retrieveRecordProvider;
    private RxCacheModule rxCacheModule;
    private SaveRecord_Factory saveRecordProvider;
    private Provider<TwoLayersCache> twoLayersCacheProvider;
    private Provider<Boolean> useExpiredDataIfLoaderNotAvailableProvider;

    /* loaded from: classes2.dex */
    public static final class Builder {
        private RxCacheModule rxCacheModule;

        private Builder() {
        }

        public RxCacheComponent build() {
            if (this.rxCacheModule != null) {
                return new DaggerRxCacheComponent(this);
            }
            throw new IllegalStateException(RxCacheModule.class.getCanonicalName() + " must be set");
        }

        public Builder rxCacheModule(RxCacheModule rxCacheModule) {
            this.rxCacheModule = (RxCacheModule) Preconditions.checkNotNull(rxCacheModule);
            return this;
        }
    }

    private DaggerRxCacheComponent(Builder builder) {
        initialize(builder);
    }

    public static Builder builder() {
        return new Builder();
    }

    private DoMigrations getDoMigrations() {
        return new DoMigrations(this.providePersistenceProvider.get(), this.provideMigrationsProvider.get(), this.provideEncryptKeyProvider.get());
    }

    private GetDeepCopy getGetDeepCopy() {
        return new GetDeepCopy(this.provideMemoryProvider.get(), this.providePersistenceProvider.get(), this.provideJolyglotProvider.get());
    }

    private ProcessorProvidersBehaviour getProcessorProvidersBehaviour() {
        return new ProcessorProvidersBehaviour(this.twoLayersCacheProvider.get(), this.useExpiredDataIfLoaderNotAvailableProvider.get(), this.evictExpiredRecordsPersistenceProvider.get(), getGetDeepCopy(), getDoMigrations());
    }

    private void initialize(Builder builder) {
        this.rxCacheModule = builder.rxCacheModule;
        this.provideMemoryProvider = DoubleCheck.provider(RxCacheModule_ProvideMemoryFactory.create(builder.rxCacheModule));
        this.provideCacheDirectoryProvider = DoubleCheck.provider(RxCacheModule_ProvideCacheDirectoryFactory.create(builder.rxCacheModule));
        this.provideEncryptorProvider = DoubleCheck.provider(RxCacheModule_ProvideEncryptorFactory.create(builder.rxCacheModule));
        this.fileEncryptorProvider = FileEncryptor_Factory.create(this.provideEncryptorProvider);
        this.provideJolyglotProvider = DoubleCheck.provider(RxCacheModule_ProvideJolyglotFactory.create(builder.rxCacheModule));
        this.diskProvider = Disk_Factory.create(this.provideCacheDirectoryProvider, this.fileEncryptorProvider, this.provideJolyglotProvider);
        this.providePersistenceProvider = DoubleCheck.provider(RxCacheModule_ProvidePersistenceFactory.create(builder.rxCacheModule, this.diskProvider));
        this.evictRecordProvider = EvictRecord_Factory.create(this.provideMemoryProvider, this.providePersistenceProvider);
        this.provideEncryptKeyProvider = DoubleCheck.provider(RxCacheModule_ProvideEncryptKeyFactory.create(builder.rxCacheModule));
        this.retrieveRecordProvider = RetrieveRecord_Factory.create(this.provideMemoryProvider, this.providePersistenceProvider, this.evictRecordProvider, HasRecordExpired_Factory.create(), this.provideEncryptKeyProvider);
        this.maxMbPersistenceCacheProvider = DoubleCheck.provider(RxCacheModule_MaxMbPersistenceCacheFactory.create(builder.rxCacheModule));
        this.evictExpirableRecordsPersistenceProvider = DoubleCheck.provider(EvictExpirableRecordsPersistence_Factory.create(this.provideMemoryProvider, this.providePersistenceProvider, this.maxMbPersistenceCacheProvider, this.provideEncryptKeyProvider));
        this.saveRecordProvider = SaveRecord_Factory.create(this.provideMemoryProvider, this.providePersistenceProvider, this.maxMbPersistenceCacheProvider, this.evictExpirableRecordsPersistenceProvider, this.provideEncryptKeyProvider);
        this.twoLayersCacheProvider = DoubleCheck.provider(TwoLayersCache_Factory.create(this.evictRecordProvider, this.retrieveRecordProvider, this.saveRecordProvider));
        this.useExpiredDataIfLoaderNotAvailableProvider = DoubleCheck.provider(RxCacheModule_UseExpiredDataIfLoaderNotAvailableFactory.create(builder.rxCacheModule));
        this.evictExpiredRecordsPersistenceProvider = DoubleCheck.provider(EvictExpiredRecordsPersistence_Factory.create(this.provideMemoryProvider, this.providePersistenceProvider, HasRecordExpired_Factory.create(), this.provideEncryptKeyProvider));
        this.provideMigrationsProvider = DoubleCheck.provider(RxCacheModule_ProvideMigrationsFactory.create(builder.rxCacheModule));
    }

    @Override // io.rx_cache2.internal.RxCacheComponent
    public ProcessorProviders providers() {
        return RxCacheModule_ProvideProcessorProvidersFactory.proxyProvideProcessorProviders(this.rxCacheModule, getProcessorProvidersBehaviour());
    }
}
