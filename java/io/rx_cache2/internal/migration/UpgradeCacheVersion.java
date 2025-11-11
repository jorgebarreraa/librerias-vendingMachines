package io.rx_cache2.internal.migration;

import io.reactivex.Observable;
import io.rx_cache2.MigrationCache;
import io.rx_cache2.internal.Persistence;
import java.util.List;
import javax.inject.Inject;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class UpgradeCacheVersion extends CacheVersion {
    private List<MigrationCache> migrations;

    @Inject
    public UpgradeCacheVersion(Persistence persistence) {
        super(persistence);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Observable<Integer> react() {
        List<MigrationCache> list = this.migrations;
        if (list == null || list.isEmpty()) {
            return Observable.just(1);
        }
        List<MigrationCache> list2 = this.migrations;
        this.persistence.save("key_cache_version", Integer.valueOf(list2.get(list2.size() - 1).version()), false, null);
        return Observable.just(1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public UpgradeCacheVersion with(List<MigrationCache> list) {
        this.migrations = list;
        return this;
    }
}
