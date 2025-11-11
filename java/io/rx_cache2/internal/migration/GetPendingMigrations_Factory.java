package io.rx_cache2.internal.migration;

import dagger.internal.Factory;

/* loaded from: classes2.dex */
public final class GetPendingMigrations_Factory implements Factory<GetPendingMigrations> {
    private static final GetPendingMigrations_Factory INSTANCE = new GetPendingMigrations_Factory();

    public static GetPendingMigrations_Factory create() {
        return INSTANCE;
    }

    public static GetPendingMigrations newGetPendingMigrations() {
        return new GetPendingMigrations();
    }

    @Override // javax.inject.Provider
    public GetPendingMigrations get() {
        return new GetPendingMigrations();
    }
}
