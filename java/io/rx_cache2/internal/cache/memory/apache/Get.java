package io.rx_cache2.internal.cache.memory.apache;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/* loaded from: classes2.dex */
public interface Get<K, V> {
    boolean containsKey(Object obj);

    boolean containsValue(Object obj);

    Set<Map.Entry<K, V>> entrySet();

    V get(Object obj);

    boolean isEmpty();

    Set<K> keySet();

    V remove(Object obj);

    int size();

    Collection<V> values();
}
