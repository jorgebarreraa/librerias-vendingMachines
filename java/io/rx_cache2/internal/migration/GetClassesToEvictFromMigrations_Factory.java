package io.rx_cache2.internal.migration;

import dagger.internal.Factory;

/* loaded from: classes2.dex */
public final class GetClassesToEvictFromMigrations_Factory implements Factory<GetClassesToEvictFromMigrations> {
    private static final GetClassesToEvictFromMigrations_Factory INSTANCE = new GetClassesToEvictFromMigrations_Factory();

    public static GetClassesToEvictFromMigrations_Factory create() {
        return INSTANCE;
    }

    public static GetClassesToEvictFromMigrations newGetClassesToEvictFromMigrations() {
        return new GetClassesToEvictFromMigrations();
    }

    @Override // javax.inject.Provider
    public GetClassesToEvictFromMigrations get() {
        return new GetClassesToEvictFromMigrations();
    }
}
