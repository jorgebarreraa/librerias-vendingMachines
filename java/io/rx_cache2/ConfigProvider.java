package io.rx_cache2;

import io.reactivex.Observable;
import io.rx_cache2.internal.Locale;

/* loaded from: classes2.dex */
public final class ConfigProvider {
    private final String dynamicKey;
    private final String dynamicKeyGroup;
    private final boolean encrypted;
    private final EvictProvider evictProvider;
    private final boolean expirable;
    private final Long lifeTime;
    private final Observable loaderObservable;
    private final String providerKey;
    private final boolean requiredDetailedResponse;
    private final Boolean useExpiredDataIfNotLoaderAvailable;

    public ConfigProvider(String str, Boolean bool, Long l, boolean z, boolean z2, boolean z3, String str2, String str3, Observable observable, EvictProvider evictProvider) {
        this.providerKey = str;
        this.useExpiredDataIfNotLoaderAvailable = bool;
        this.lifeTime = l;
        this.requiredDetailedResponse = z;
        this.expirable = z2;
        this.encrypted = z3;
        this.dynamicKey = str2;
        this.dynamicKeyGroup = str3;
        this.loaderObservable = observable;
        this.evictProvider = evictProvider;
        checkIntegrity();
    }

    private void checkIntegrity() {
        if ((evictProvider() instanceof EvictDynamicKeyGroup) && getDynamicKeyGroup().isEmpty()) {
            throw new IllegalArgumentException(this.providerKey + Locale.EVICT_DYNAMIC_KEY_GROUP_PROVIDED_BUT_NOT_PROVIDED_ANY_DYNAMIC_KEY_GROUP);
        }
        if ((evictProvider() instanceof EvictDynamicKey) && getDynamicKey().isEmpty()) {
            throw new IllegalArgumentException(this.providerKey + Locale.EVICT_DYNAMIC_KEY_PROVIDED_BUT_NOT_PROVIDED_ANY_DYNAMIC_KEY);
        }
    }

    public EvictProvider evictProvider() {
        return this.evictProvider;
    }

    public String getDynamicKey() {
        return this.dynamicKey;
    }

    public String getDynamicKeyGroup() {
        return this.dynamicKeyGroup;
    }

    public Long getLifeTimeMillis() {
        return this.lifeTime;
    }

    public Observable getLoaderObservable() {
        return this.loaderObservable;
    }

    public String getProviderKey() {
        return this.providerKey;
    }

    public boolean isEncrypted() {
        return this.encrypted;
    }

    public boolean isExpirable() {
        return this.expirable;
    }

    public boolean requiredDetailedResponse() {
        return this.requiredDetailedResponse;
    }

    public Boolean useExpiredDataIfNotLoaderAvailable() {
        return this.useExpiredDataIfNotLoaderAvailable;
    }
}
