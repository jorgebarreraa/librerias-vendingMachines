package io.rx_cache2.internal;

import dagger.Module;
import dagger.Provides;
import io.rx_cache2.MigrationCache;
import io.rx_cache2.internal.cache.memory.ReferenceMapMemory;
import io.rx_cache2.internal.encrypt.BuiltInEncryptor;
import io.rx_cache2.internal.encrypt.Encryptor;
import io.victoralbertos.jolyglot.JolyglotGenerics;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Singleton;

@Module
/* loaded from: classes2.dex */
public final class RxCacheModule {
    private final File cacheDirectory;
    private final String encryptKey;
    private final JolyglotGenerics jolyglot;
    private final Integer maxMgPersistenceCache;
    private final List<MigrationCache> migrations;
    private final boolean useExpiredDataIfLoaderNotAvailable;

    public RxCacheModule(File file, Boolean bool, Integer num, String str, List<MigrationCache> list, JolyglotGenerics jolyglotGenerics) {
        this.cacheDirectory = file;
        this.useExpiredDataIfLoaderNotAvailable = bool.booleanValue();
        this.maxMgPersistenceCache = num;
        this.encryptKey = str;
        this.migrations = list;
        this.jolyglot = jolyglotGenerics;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    public Integer maxMbPersistenceCache() {
        Integer num = this.maxMgPersistenceCache;
        return Integer.valueOf(num != null ? num.intValue() : 100);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    public File provideCacheDirectory() {
        return this.cacheDirectory;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    public String provideEncryptKey() {
        String str = this.encryptKey;
        return str != null ? str : "";
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    public Encryptor provideEncryptor() {
        return new BuiltInEncryptor();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    public JolyglotGenerics provideJolyglot() {
        return this.jolyglot;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    public Memory provideMemory() {
        return new ReferenceMapMemory();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    public List<MigrationCache> provideMigrations() {
        List<MigrationCache> list = this.migrations;
        return list != null ? list : new ArrayList();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    public Persistence providePersistence(Disk disk) {
        return disk;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    public ProcessorProviders provideProcessorProviders(ProcessorProvidersBehaviour processorProvidersBehaviour) {
        return processorProvidersBehaviour;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    public Boolean useExpiredDataIfLoaderNotAvailable() {
        return Boolean.valueOf(this.useExpiredDataIfLoaderNotAvailable);
    }
}
