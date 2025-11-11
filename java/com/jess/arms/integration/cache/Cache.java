package com.jess.arms.integration.cache;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.Set;

/* loaded from: classes.dex */
public interface Cache<K, V> {

    /* loaded from: classes.dex */
    public interface Factory {
        @NonNull
        Cache build(CacheType cacheType);
    }

    void clear();

    boolean containsKey(K k);

    @Nullable
    V get(K k);

    int getMaxSize();

    Set<K> keySet();

    @Nullable
    V put(K k, V v);

    @Nullable
    V remove(K k);

    int size();
}
