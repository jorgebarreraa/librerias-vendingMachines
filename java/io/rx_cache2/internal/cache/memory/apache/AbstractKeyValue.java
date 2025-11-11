package io.rx_cache2.internal.cache.memory.apache;

/* loaded from: classes2.dex */
public abstract class AbstractKeyValue<K, V> implements KeyValue<K, V> {
    private K key;
    private V value;

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractKeyValue(K k, V v) {
        this.key = k;
        this.value = v;
    }

    @Override // io.rx_cache2.internal.cache.memory.apache.KeyValue
    public K getKey() {
        return this.key;
    }

    @Override // io.rx_cache2.internal.cache.memory.apache.KeyValue
    public V getValue() {
        return this.value;
    }

    protected K setKey(K k) {
        K k2 = this.key;
        this.key = k;
        return k2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public V setValue(V v) {
        V v2 = this.value;
        this.value = v;
        return v2;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getKey());
        sb.append('=');
        sb.append(getValue());
        return sb.toString();
    }
}
