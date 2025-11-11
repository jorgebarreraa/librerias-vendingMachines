package io.rx_cache2.internal.migration;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import io.rx_cache2.MigrationCache;
import io.rx_cache2.internal.Persistence;
import java.util.List;
import javax.inject.Inject;

/* loaded from: classes2.dex */
public final class DoMigrations {
    private final DeleteRecordMatchingClassName deleteRecordMatchingClassName;
    private final GetCacheVersion getCacheVersion;
    private final GetClassesToEvictFromMigrations getClassesToEvictFromMigrations = new GetClassesToEvictFromMigrations();
    private final GetPendingMigrations getPendingMigrations = new GetPendingMigrations();
    private final List<MigrationCache> migrations;
    private final UpgradeCacheVersion upgradeCacheVersion;

    @Inject
    public DoMigrations(Persistence persistence, List<MigrationCache> list, String str) {
        this.getCacheVersion = new GetCacheVersion(persistence);
        this.migrations = list;
        this.upgradeCacheVersion = new UpgradeCacheVersion(persistence);
        this.deleteRecordMatchingClassName = new DeleteRecordMatchingClassName(persistence, str);
    }

    public Observable<Integer> react() {
        return this.getCacheVersion.react().flatMap(new Function<Integer, ObservableSource<List<MigrationCache>>>() { // from class: io.rx_cache2.internal.migration.DoMigrations.4
            @Override // io.reactivex.functions.Function
            public ObservableSource<List<MigrationCache>> apply(Integer num) throws Exception {
                return DoMigrations.this.getPendingMigrations.with(num.intValue(), DoMigrations.this.migrations).react();
            }
        }).flatMap(new Function<List<MigrationCache>, ObservableSource<List<Class>>>() { // from class: io.rx_cache2.internal.migration.DoMigrations.3
            @Override // io.reactivex.functions.Function
            public ObservableSource<List<Class>> apply(List<MigrationCache> list) throws Exception {
                return DoMigrations.this.getClassesToEvictFromMigrations.with(list).react();
            }
        }).flatMap(new Function<List<Class>, ObservableSource<Integer>>() { // from class: io.rx_cache2.internal.migration.DoMigrations.2
            @Override // io.reactivex.functions.Function
            public ObservableSource<Integer> apply(List<Class> list) throws Exception {
                return DoMigrations.this.deleteRecordMatchingClassName.with(list).react();
            }
        }).flatMap(new Function<Integer, ObservableSource<Integer>>() { // from class: io.rx_cache2.internal.migration.DoMigrations.1
            @Override // io.reactivex.functions.Function
            public ObservableSource<Integer> apply(Integer num) throws Exception {
                return DoMigrations.this.upgradeCacheVersion.with(DoMigrations.this.migrations).react();
            }
        });
    }
}
