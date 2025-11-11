package io.rx_cache2.internal.cache;

import io.reactivex.Observable;
import io.rx_cache2.internal.Memory;
import io.rx_cache2.internal.Persistence;
import io.rx_cache2.internal.Record;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
/* loaded from: classes2.dex */
public final class EvictExpiredRecordsPersistence extends Action {
    private final String encryptKey;
    private final HasRecordExpired hasRecordExpired;

    @Inject
    public EvictExpiredRecordsPersistence(Memory memory, Persistence persistence, HasRecordExpired hasRecordExpired, String str) {
        super(memory, persistence);
        this.hasRecordExpired = hasRecordExpired;
        this.encryptKey = str;
    }

    public Observable<Integer> startEvictingExpiredRecords() {
        String str;
        for (String str2 : this.persistence.allKeys()) {
            Record retrieveRecord = this.persistence.retrieveRecord(str2, false, this.encryptKey);
            if (retrieveRecord == null && (str = this.encryptKey) != null && !str.isEmpty()) {
                retrieveRecord = this.persistence.retrieveRecord(str2, true, this.encryptKey);
            }
            if (retrieveRecord != null && this.hasRecordExpired.hasRecordExpired(retrieveRecord)) {
                this.persistence.evict(str2);
            }
        }
        return Observable.just(1);
    }
}
