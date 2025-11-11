package io.rx_cache2.internal.migration;

import io.reactivex.Observable;
import io.rx_cache2.MigrationCache;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.inject.Inject;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class GetClassesToEvictFromMigrations {
    private List<MigrationCache> migrations;

    @Inject
    public GetClassesToEvictFromMigrations() {
    }

    private boolean isAlreadyAdded(List<Class> list, Class cls) {
        Iterator<Class> it2 = list.iterator();
        while (it2.hasNext()) {
            if (it2.next().getName().equals(cls.getName())) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Observable<List<Class>> react() {
        ArrayList arrayList = new ArrayList();
        Iterator<MigrationCache> it2 = this.migrations.iterator();
        while (it2.hasNext()) {
            for (Class cls : it2.next().evictClasses()) {
                if (!isAlreadyAdded(arrayList, cls)) {
                    arrayList.add(cls);
                }
            }
        }
        return Observable.just(arrayList);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public GetClassesToEvictFromMigrations with(List<MigrationCache> list) {
        this.migrations = list;
        return this;
    }
}
