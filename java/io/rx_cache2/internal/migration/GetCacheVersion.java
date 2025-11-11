package io.rx_cache2.internal.migration;

import io.reactivex.Observable;
import io.rx_cache2.internal.Persistence;
import javax.inject.Inject;

/* loaded from: classes2.dex */
final class GetCacheVersion extends CacheVersion {
    @Inject
    public GetCacheVersion(Persistence persistence) {
        super(persistence);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Observable<Integer> react() {
        Integer num = (Integer) this.persistence.retrieve("key_cache_version", Integer.class, false, null);
        return Observable.just(Integer.valueOf(num != null ? num.intValue() : 0));
    }
}
