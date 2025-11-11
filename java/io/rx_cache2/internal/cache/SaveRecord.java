package io.rx_cache2.internal.cache;

import io.rx_cache2.internal.Locale;
import io.rx_cache2.internal.Memory;
import io.rx_cache2.internal.Persistence;
import io.rx_cache2.internal.Record;
import javax.inject.Inject;

/* loaded from: classes2.dex */
public final class SaveRecord extends Action {
    private final String encryptKey;
    private final EvictExpirableRecordsPersistence evictExpirableRecordsPersistence;
    private final Integer maxMgPersistenceCache;

    @Inject
    public SaveRecord(Memory memory, Persistence persistence, Integer num, EvictExpirableRecordsPersistence evictExpirableRecordsPersistence, String str) {
        super(memory, persistence);
        this.maxMgPersistenceCache = num;
        this.evictExpirableRecordsPersistence = evictExpirableRecordsPersistence;
        this.encryptKey = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void save(String str, String str2, String str3, Object obj, Long l, boolean z, boolean z2) {
        String composeKey = composeKey(str, str2, str3);
        Record record = new Record(obj, Boolean.valueOf(z), l);
        this.memory.put(composeKey, record);
        if (this.persistence.storedMB() >= this.maxMgPersistenceCache.intValue()) {
            System.out.println(Locale.RECORD_CAN_NOT_BE_PERSISTED_BECAUSE_WOULD_EXCEED_THRESHOLD_LIMIT);
        } else {
            this.persistence.saveRecord(composeKey, record, z2, this.encryptKey);
        }
        this.evictExpirableRecordsPersistence.startTaskIfNeeded(z2);
    }
}
