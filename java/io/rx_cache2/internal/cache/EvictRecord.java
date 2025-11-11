package io.rx_cache2.internal.cache;

import io.rx_cache2.internal.Memory;
import io.rx_cache2.internal.Persistence;
import javax.inject.Inject;

/* loaded from: classes2.dex */
public final class EvictRecord extends Action {
    @Inject
    public EvictRecord(Memory memory, Persistence persistence) {
        super(memory, persistence);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void evictAll() {
        this.memory.evictAll();
        this.persistence.evictAll();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void evictRecordMatchingDynamicKeyGroup(String str, String str2, String str3) {
        String keyOnMemoryMatchingDynamicKeyGroup = getKeyOnMemoryMatchingDynamicKeyGroup(str, str2, str3);
        this.memory.evict(keyOnMemoryMatchingDynamicKeyGroup);
        this.persistence.evict(keyOnMemoryMatchingDynamicKeyGroup);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void evictRecordsMatchingDynamicKey(String str, String str2) {
        for (String str3 : getKeysOnMemoryMatchingDynamicKey(str, str2)) {
            this.memory.evict(str3);
            this.persistence.evict(str3);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void evictRecordsMatchingProviderKey(String str) {
        for (String str2 : getKeysOnMemoryMatchingProviderKey(str)) {
            this.memory.evict(str2);
            this.persistence.evict(str2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void mockMemoryDestroyed() {
        this.memory.evictAll();
    }
}
