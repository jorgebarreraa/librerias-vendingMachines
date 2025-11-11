package io.rx_cache2;

/* loaded from: classes2.dex */
public final class MigrationCache {
    private final Class[] evictClasses;
    private final int version;

    public MigrationCache(int i, Class[] clsArr) {
        this.version = i;
        this.evictClasses = clsArr;
    }

    public Class[] evictClasses() {
        return this.evictClasses;
    }

    public int version() {
        return this.version;
    }
}
