package io.rx_cache2.internal.cache.memory.apache;

import java.util.Map;

/* loaded from: classes2.dex */
public interface Put<K, V> {
    void clear();

    Object put(K k, V v);

    void putAll(Map<? extends K, ? extends V> map);
}
