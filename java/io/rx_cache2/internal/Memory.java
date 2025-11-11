package io.rx_cache2.internal;

import java.util.Set;

/* loaded from: classes2.dex */
public interface Memory {
    void evict(String str);

    void evictAll();

    <T> Record<T> getIfPresent(String str);

    Set<String> keySet();

    <T> void put(String str, Record<T> record);
}
