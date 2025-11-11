package io.rx_cache2.internal.cache;

import io.rx_cache2.internal.Record;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
/* loaded from: classes2.dex */
public final class TwoLayersCache {
    private final EvictRecord evictRecord;
    private final RetrieveRecord retrieveRecord;
    private final SaveRecord saveRecord;

    @Inject
    public TwoLayersCache(EvictRecord evictRecord, RetrieveRecord retrieveRecord, SaveRecord saveRecord) {
        this.evictRecord = evictRecord;
        this.retrieveRecord = retrieveRecord;
        this.saveRecord = saveRecord;
    }

    public void evictAll() {
        this.evictRecord.evictAll();
    }

    public void evictDynamicKey(String str, String str2) {
        this.evictRecord.evictRecordsMatchingDynamicKey(str, str2);
    }

    public void evictDynamicKeyGroup(String str, String str2, String str3) {
        this.evictRecord.evictRecordMatchingDynamicKeyGroup(str, str2, str3);
    }

    public void evictProviderKey(String str) {
        this.evictRecord.evictRecordsMatchingProviderKey(str);
    }

    public void mockMemoryDestroyed() {
        this.evictRecord.mockMemoryDestroyed();
    }

    public <T> Record<T> retrieve(String str, String str2, String str3, boolean z, Long l, boolean z2) {
        return this.retrieveRecord.retrieveRecord(str, str2, str3, z, l, z2);
    }

    public void save(String str, String str2, String str3, Object obj, Long l, boolean z, boolean z2) {
        this.saveRecord.save(str, str2, str3, obj, l, z, z2);
    }
}
