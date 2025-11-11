package io.rx_cache2.internal.cache.memory;

import io.rx_cache2.internal.Memory;
import io.rx_cache2.internal.Record;
import io.rx_cache2.internal.cache.memory.apache.ReferenceMap;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* loaded from: classes2.dex */
public final class ReferenceMapMemory implements Memory {
    private final Map<String, Record> referenceMap = Collections.synchronizedMap(new ReferenceMap());

    @Override // io.rx_cache2.internal.Memory
    public void evict(String str) {
        this.referenceMap.remove(str);
    }

    @Override // io.rx_cache2.internal.Memory
    public void evictAll() {
        Set<String> keySet = this.referenceMap.keySet();
        synchronized (this.referenceMap) {
            Iterator<String> it2 = keySet.iterator();
            while (it2.hasNext()) {
                it2.next();
                it2.remove();
            }
        }
    }

    @Override // io.rx_cache2.internal.Memory
    public <T> Record<T> getIfPresent(String str) {
        return this.referenceMap.get(str);
    }

    @Override // io.rx_cache2.internal.Memory
    public Set<String> keySet() {
        return this.referenceMap.keySet();
    }

    @Override // io.rx_cache2.internal.Memory
    public <T> void put(String str, Record<T> record) {
        this.referenceMap.put(str, record);
    }
}
