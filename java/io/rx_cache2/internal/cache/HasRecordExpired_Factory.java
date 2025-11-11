package io.rx_cache2.internal.cache;

import dagger.internal.Factory;

/* loaded from: classes2.dex */
public final class HasRecordExpired_Factory implements Factory<HasRecordExpired> {
    private static final HasRecordExpired_Factory INSTANCE = new HasRecordExpired_Factory();

    public static HasRecordExpired_Factory create() {
        return INSTANCE;
    }

    @Override // javax.inject.Provider
    public HasRecordExpired get() {
        return new HasRecordExpired();
    }
}
