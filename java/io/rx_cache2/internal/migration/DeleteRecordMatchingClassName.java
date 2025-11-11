package io.rx_cache2.internal.migration;

import io.reactivex.Observable;
import io.rx_cache2.internal.Persistence;
import io.rx_cache2.internal.Record;
import java.util.Iterator;
import java.util.List;
import javax.inject.Inject;

/* loaded from: classes2.dex */
public final class DeleteRecordMatchingClassName {
    private List<Class> classes;
    private final String encryptKey;
    private final Persistence persistence;

    @Inject
    public DeleteRecordMatchingClassName(Persistence persistence, String str) {
        this.persistence = persistence;
        this.encryptKey = str;
    }

    private boolean evictRecord(Record record) {
        String dataClassName = record.getDataClassName();
        Iterator<Class> it2 = this.classes.iterator();
        while (it2.hasNext()) {
            if (it2.next().getName().equals(dataClassName)) {
                return true;
            }
        }
        return false;
    }

    public Observable<Integer> react() {
        if (this.classes.isEmpty()) {
            return Observable.just(1);
        }
        for (String str : this.persistence.allKeys()) {
            Record retrieveRecord = this.persistence.retrieveRecord(str, false, this.encryptKey);
            if (retrieveRecord == null) {
                retrieveRecord = this.persistence.retrieveRecord(str, true, this.encryptKey);
            }
            if (evictRecord(retrieveRecord)) {
                this.persistence.evict(str);
            }
        }
        return Observable.just(1);
    }

    public DeleteRecordMatchingClassName with(List<Class> list) {
        this.classes = list;
        return this;
    }
}
