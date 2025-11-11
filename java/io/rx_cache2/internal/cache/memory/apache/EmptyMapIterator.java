package io.rx_cache2.internal.cache.memory.apache;

/* loaded from: classes2.dex */
public class EmptyMapIterator<K, V> extends AbstractEmptyMapIterator<K, V> implements MapIterator<K, V>, ResettableIterator<K> {
    public static final MapIterator INSTANCE = new EmptyMapIterator();

    protected EmptyMapIterator() {
    }

    public static <K, V> MapIterator<K, V> emptyMapIterator() {
        return INSTANCE;
    }
}
