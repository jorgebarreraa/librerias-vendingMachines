package io.rx_cache2.internal.cache;

import io.rx_cache2.Source;
import io.rx_cache2.internal.Memory;
import io.rx_cache2.internal.Persistence;
import io.rx_cache2.internal.Record;
import javax.inject.Inject;

/* loaded from: classes2.dex */
public final class RetrieveRecord extends Action {
    private final String encryptKey;
    private final EvictRecord evictRecord;
    private final HasRecordExpired hasRecordExpired;

    @Inject
    public RetrieveRecord(Memory memory, Persistence persistence, EvictRecord evictRecord, HasRecordExpired hasRecordExpired, String str) {
        super(memory, persistence);
        this.evictRecord = evictRecord;
        this.hasRecordExpired = hasRecordExpired;
        this.encryptKey = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public <T> Record<T> retrieveRecord(String str, String str2, String str3, boolean z, Long l, boolean z2) {
        String composeKey = composeKey(str, str2, str3);
        Record<T> ifPresent = this.memory.getIfPresent(composeKey);
        if (ifPresent != null) {
            ifPresent.setSource(Source.MEMORY);
        } else {
            try {
                ifPresent = this.persistence.retrieveRecord(composeKey, z2, this.encryptKey);
                ifPresent.setSource(Source.PERSISTENCE);
                this.memory.put(composeKey, ifPresent);
            } catch (Exception unused) {
                return null;
            }
        }
        ifPresent.setLifeTime(l);
        if (!this.hasRecordExpired.hasRecordExpired(ifPresent)) {
            return ifPresent;
        }
        if (!str3.isEmpty()) {
            this.evictRecord.evictRecordMatchingDynamicKeyGroup(str, str2, str3);
        } else if (str2.isEmpty()) {
            this.evictRecord.evictRecordsMatchingProviderKey(str);
        } else {
            this.evictRecord.evictRecordsMatchingDynamicKey(str, str2);
        }
        if (z) {
            return ifPresent;
        }
        return null;
    }
}
