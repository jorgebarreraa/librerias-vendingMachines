package io.rx_cache2;

/* loaded from: classes2.dex */
public class EvictProvider {
    private final boolean evict;

    public EvictProvider(boolean z) {
        this.evict = z;
    }

    public boolean evict() {
        return this.evict;
    }
}
