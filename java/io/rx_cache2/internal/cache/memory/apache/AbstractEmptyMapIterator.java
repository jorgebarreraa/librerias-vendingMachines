package io.rx_cache2.internal.cache.memory.apache;

/* loaded from: classes2.dex */
public abstract class AbstractEmptyMapIterator<K, V> extends AbstractEmptyIterator<K> {
    @Override // io.rx_cache2.internal.cache.memory.apache.AbstractEmptyIterator
    public /* bridge */ /* synthetic */ void add(Object obj) {
        super.add(obj);
    }

    public K getKey() {
        throw new IllegalStateException("Iterator contains no elements");
    }

    public V getValue() {
        throw new IllegalStateException("Iterator contains no elements");
    }

    @Override // io.rx_cache2.internal.cache.memory.apache.AbstractEmptyIterator
    public /* bridge */ /* synthetic */ boolean hasNext() {
        return super.hasNext();
    }

    @Override // io.rx_cache2.internal.cache.memory.apache.AbstractEmptyIterator
    public /* bridge */ /* synthetic */ boolean hasPrevious() {
        return super.hasPrevious();
    }

    @Override // io.rx_cache2.internal.cache.memory.apache.AbstractEmptyIterator
    public /* bridge */ /* synthetic */ Object next() {
        return super.next();
    }

    @Override // io.rx_cache2.internal.cache.memory.apache.AbstractEmptyIterator
    public /* bridge */ /* synthetic */ int nextIndex() {
        return super.nextIndex();
    }

    @Override // io.rx_cache2.internal.cache.memory.apache.AbstractEmptyIterator
    public /* bridge */ /* synthetic */ Object previous() {
        return super.previous();
    }

    @Override // io.rx_cache2.internal.cache.memory.apache.AbstractEmptyIterator
    public /* bridge */ /* synthetic */ int previousIndex() {
        return super.previousIndex();
    }

    @Override // io.rx_cache2.internal.cache.memory.apache.AbstractEmptyIterator
    public /* bridge */ /* synthetic */ void remove() {
        super.remove();
    }

    @Override // io.rx_cache2.internal.cache.memory.apache.AbstractEmptyIterator
    public /* bridge */ /* synthetic */ void reset() {
        super.reset();
    }

    @Override // io.rx_cache2.internal.cache.memory.apache.AbstractEmptyIterator
    public /* bridge */ /* synthetic */ void set(Object obj) {
        super.set(obj);
    }

    public V setValue(V v) {
        throw new IllegalStateException("Iterator contains no elements");
    }
}
