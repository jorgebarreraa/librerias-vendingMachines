package io.rx_cache2.internal.migration;

import io.rx_cache2.internal.Persistence;

/* loaded from: classes2.dex */
abstract class CacheVersion {
    protected static final String KEY_CACHE_VERSION = "key_cache_version";
    protected final Persistence persistence;

    public CacheVersion(Persistence persistence) {
        this.persistence = persistence;
    }
}
