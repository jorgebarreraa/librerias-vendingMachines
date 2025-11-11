package io.rx_cache2.internal.cache.memory.apache;

import java.util.Iterator;

/* loaded from: classes2.dex */
public class EmptyIterator<E> extends AbstractEmptyIterator<E> implements ResettableIterator<E> {
    public static final ResettableIterator RESETTABLE_INSTANCE = new EmptyIterator();
    public static final Iterator INSTANCE = RESETTABLE_INSTANCE;

    protected EmptyIterator() {
    }

    public static <E> Iterator<E> emptyIterator() {
        return INSTANCE;
    }

    public static <E> ResettableIterator<E> resettableEmptyIterator() {
        return RESETTABLE_INSTANCE;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // io.rx_cache2.internal.cache.memory.apache.AbstractEmptyIterator
    public /* bridge */ /* synthetic */ void add(Object obj) {
        super.add(obj);
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

    /* JADX WARN: Multi-variable type inference failed */
    @Override // io.rx_cache2.internal.cache.memory.apache.AbstractEmptyIterator
    public /* bridge */ /* synthetic */ void set(Object obj) {
        super.set(obj);
    }
}
