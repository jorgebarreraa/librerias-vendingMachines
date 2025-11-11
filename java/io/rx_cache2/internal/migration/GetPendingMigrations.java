package io.rx_cache2.internal.migration;

import io.reactivex.Observable;
import io.rx_cache2.MigrationCache;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.inject.Inject;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class GetPendingMigrations {
    private int cacheVersion;
    private List<MigrationCache> migrations;

    /* JADX INFO: Access modifiers changed from: package-private */
    @Inject
    public GetPendingMigrations() {
    }

    public Observable<List<MigrationCache>> react() {
        List<MigrationCache> list = this.migrations;
        if (list == null || list.isEmpty()) {
            return Observable.just(new ArrayList());
        }
        Collections.sort(this.migrations, new Comparator<MigrationCache>() { // from class: io.rx_cache2.internal.migration.GetPendingMigrations.1
            @Override // java.util.Comparator
            public int compare(MigrationCache migrationCache, MigrationCache migrationCache2) {
                return migrationCache.version() - migrationCache2.version();
            }
        });
        ArrayList arrayList = new ArrayList();
        for (MigrationCache migrationCache : this.migrations) {
            if (this.cacheVersion < migrationCache.version()) {
                arrayList.add(migrationCache);
            }
        }
        return Observable.just(arrayList);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public GetPendingMigrations with(int i, List<MigrationCache> list) {
        this.cacheVersion = i;
        this.migrations = list;
        return this;
    }
}
