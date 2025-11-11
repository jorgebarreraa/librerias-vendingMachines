package io.rx_cache2.internal;

import io.reactivex.Observable;
import io.victoralbertos.jolyglot.JolyglotGenerics;
import java.io.File;
import java.lang.reflect.Proxy;
import java.security.InvalidParameterException;

/* loaded from: classes2.dex */
public final class RxCache {
    private final Builder builder;
    private ProxyProviders proxyProviders;

    /* loaded from: classes2.dex */
    public static class Builder {
        private File cacheDirectory;
        private JolyglotGenerics jolyglot;
        private Integer maxMBPersistenceCache;
        private boolean useExpiredDataIfLoaderNotAvailable;

        public File getCacheDirectory() {
            return this.cacheDirectory;
        }

        public JolyglotGenerics getJolyglot() {
            return this.jolyglot;
        }

        public Integer getMaxMBPersistenceCache() {
            return this.maxMBPersistenceCache;
        }

        public RxCache persistence(File file, JolyglotGenerics jolyglotGenerics) {
            if (file == null) {
                throw new InvalidParameterException(Locale.REPOSITORY_DISK_ADAPTER_CAN_NOT_BE_NULL);
            }
            if (!file.exists()) {
                throw new InvalidParameterException(Locale.REPOSITORY_DISK_ADAPTER_DOES_NOT_EXIST);
            }
            if (!file.canWrite()) {
                throw new InvalidParameterException(Locale.REPOSITORY_DISK_ADAPTER_IS_NOT_WRITABLE);
            }
            if (jolyglotGenerics == null) {
                throw new InvalidParameterException(Locale.JSON_CONVERTER_CAN_NOT_BE_NULL);
            }
            this.cacheDirectory = file;
            this.jolyglot = jolyglotGenerics;
            return new RxCache(this);
        }

        public Builder setMaxMBPersistenceCache(Integer num) {
            this.maxMBPersistenceCache = num;
            return this;
        }

        public Builder useExpiredDataIfLoaderNotAvailable(boolean z) {
            this.useExpiredDataIfLoaderNotAvailable = z;
            return this;
        }

        public boolean useExpiredDataIfLoaderNotAvailable() {
            return this.useExpiredDataIfLoaderNotAvailable;
        }
    }

    private RxCache(Builder builder) {
        this.builder = builder;
    }

    public Observable<Void> evictAll() {
        return this.proxyProviders.evictAll();
    }

    public <T> T using(Class<T> cls) {
        this.proxyProviders = new ProxyProviders(this.builder, cls);
        return (T) Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{cls}, this.proxyProviders);
    }
}
