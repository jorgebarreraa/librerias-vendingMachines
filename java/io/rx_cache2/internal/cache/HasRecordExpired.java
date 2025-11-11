package io.rx_cache2.internal.cache;

import io.rx_cache2.internal.Record;
import javax.inject.Inject;

/* loaded from: classes2.dex */
public final class HasRecordExpired {
    @Inject
    public HasRecordExpired() {
    }

    public boolean hasRecordExpired(Record record) {
        long currentTimeMillis = System.currentTimeMillis();
        Long lifeTime = record.getLifeTime();
        return lifeTime != null && currentTimeMillis > record.getTimeAtWhichWasPersisted() + lifeTime.longValue();
    }
}
