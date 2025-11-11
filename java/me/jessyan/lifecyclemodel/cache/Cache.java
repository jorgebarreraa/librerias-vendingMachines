package me.jessyan.lifecyclemodel.cache;

import androidx.annotation.Nullable;
import java.util.Set;

/* loaded from: classes2.dex */
public interface Cache<K, V> {
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
