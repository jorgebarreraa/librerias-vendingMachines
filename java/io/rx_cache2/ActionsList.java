package io.rx_cache2;

import io.reactivex.Observable;
import io.reactivex.functions.Function;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* loaded from: classes2.dex */
public class ActionsList<T> {
    protected Observable<List<T>> cache;
    protected final Evict<T> evict;
    boolean startToEvict;

    /* loaded from: classes2.dex */
    public interface Evict<T> {
        Observable<List<T>> call(Observable<List<T>> observable);
    }

    /* loaded from: classes2.dex */
    public interface Func1Count {
        boolean call(int i);
    }

    /* loaded from: classes2.dex */
    public interface Func1Element<T> {
        boolean call(T t);
    }

    /* loaded from: classes2.dex */
    public interface Func2 {
        boolean call(int i, int i2);
    }

    /* loaded from: classes2.dex */
    public interface Func3<T> {
        boolean call(int i, int i2, T t);
    }

    /* loaded from: classes2.dex */
    public interface Replace<T> {
        T call(T t);
    }

    public ActionsList(Evict<T> evict, Observable<List<T>> observable) {
        this.evict = evict;
        this.cache = observable;
    }

    public static <T> ActionsList<T> with(Evict<T> evict, Observable<List<T>> observable) {
        return new ActionsList<>(evict, observable);
    }

    public ActionsList<T> add(Func2 func2, T t) {
        return addAll(func2, Arrays.asList(t));
    }

    public ActionsList<T> addAll(final Func2 func2, final List<T> list) {
        this.cache = (Observable<List<T>>) this.cache.map(new Function<List<T>, List<T>>() { // from class: io.rx_cache2.ActionsList.5
            @Override // io.reactivex.functions.Function
            public List<T> apply(List<T> list2) throws Exception {
                int size = list2.size();
                int i = 0;
                while (true) {
                    if (i > size) {
                        break;
                    }
                    if (func2.call(i, size)) {
                        list2.addAll(i, list);
                        break;
                    }
                    i++;
                }
                return list2;
            }
        });
        return this;
    }

    public ActionsList<T> addAllFirst(List<T> list) {
        return addAll(new Func2() { // from class: io.rx_cache2.ActionsList.3
            @Override // io.rx_cache2.ActionsList.Func2
            public boolean call(int i, int i2) {
                return i == 0;
            }
        }, list);
    }

    public ActionsList<T> addAllLast(List<T> list) {
        return addAll(new Func2() { // from class: io.rx_cache2.ActionsList.4
            @Override // io.rx_cache2.ActionsList.Func2
            public boolean call(int i, int i2) {
                return i == i2;
            }
        }, list);
    }

    public ActionsList<T> addFirst(T t) {
        return addAll(new Func2() { // from class: io.rx_cache2.ActionsList.1
            @Override // io.rx_cache2.ActionsList.Func2
            public boolean call(int i, int i2) {
                return i == 0;
            }
        }, Arrays.asList(t));
    }

    public ActionsList<T> addLast(T t) {
        return addAll(new Func2() { // from class: io.rx_cache2.ActionsList.2
            @Override // io.rx_cache2.ActionsList.Func2
            public boolean call(int i, int i2) {
                return i == i2;
            }
        }, Arrays.asList(t));
    }

    public ActionsList<T> evict(final Func1Element<T> func1Element) {
        return evict(new Func3<T>() { // from class: io.rx_cache2.ActionsList.14
            @Override // io.rx_cache2.ActionsList.Func3
            public boolean call(int i, int i2, T t) {
                return func1Element.call(t);
            }
        });
    }

    public ActionsList<T> evict(final Func3<T> func3) {
        this.cache = (Observable<List<T>>) this.cache.map(new Function<List<T>, List<T>>() { // from class: io.rx_cache2.ActionsList.15
            @Override // io.reactivex.functions.Function
            public List<T> apply(List<T> list) throws Exception {
                int size = list.size();
                int i = 0;
                while (true) {
                    if (i >= size) {
                        break;
                    }
                    if (func3.call(i, size, list.get(i))) {
                        list.remove(i);
                        break;
                    }
                    i++;
                }
                return list;
            }
        });
        return this;
    }

    public ActionsList<T> evictAll() {
        return evictIterable(new Func3<T>() { // from class: io.rx_cache2.ActionsList.16
            @Override // io.rx_cache2.ActionsList.Func3
            public boolean call(int i, int i2, T t) {
                return true;
            }
        });
    }

    public ActionsList<T> evictAllKeepingFirstN(final int i) {
        return evictIterable(new Func3<T>() { // from class: io.rx_cache2.ActionsList.17
            @Override // io.rx_cache2.ActionsList.Func3
            public boolean call(int i2, int i3, T t) {
                return i2 >= i3 - (i3 - i);
            }
        });
    }

    public ActionsList<T> evictAllKeepingLastN(final int i) {
        return evictIterable(new Func3<T>() { // from class: io.rx_cache2.ActionsList.18
            @Override // io.rx_cache2.ActionsList.Func3
            public boolean call(int i2, int i3, T t) {
                return i2 < i3 - i;
            }
        });
    }

    public ActionsList<T> evictFirst() {
        return evict(new Func3<T>() { // from class: io.rx_cache2.ActionsList.6
            @Override // io.rx_cache2.ActionsList.Func3
            public boolean call(int i, int i2, T t) {
                return i == 0;
            }
        });
    }

    public ActionsList<T> evictFirst(final Func1Count func1Count) {
        return evict(new Func3<T>() { // from class: io.rx_cache2.ActionsList.10
            @Override // io.rx_cache2.ActionsList.Func3
            public boolean call(int i, int i2, T t) {
                return i == 0 && func1Count.call(i2);
            }
        });
    }

    public ActionsList<T> evictFirstN(int i) {
        return evictFirstN(new Func1Count() { // from class: io.rx_cache2.ActionsList.7
            @Override // io.rx_cache2.ActionsList.Func1Count
            public boolean call(int i2) {
                return true;
            }
        }, i);
    }

    public ActionsList<T> evictFirstN(final Func1Count func1Count, final int i) {
        return evictIterable(new Func3<T>() { // from class: io.rx_cache2.ActionsList.11
            @Override // io.rx_cache2.ActionsList.Func3
            public boolean call(int i2, int i3, T t) {
                return i2 < i && func1Count.call(i3);
            }
        });
    }

    public ActionsList<T> evictIterable(final Func3<T> func3) {
        this.cache = (Observable<List<T>>) this.cache.map(new Function<List<T>, List<T>>() { // from class: io.rx_cache2.ActionsList.19
            @Override // io.reactivex.functions.Function
            public List<T> apply(List<T> list) throws Exception {
                int size = list.size();
                for (int i = 0; i < size; i++) {
                    if (func3.call(i, size, list.get(i))) {
                        list.set(i, null);
                    }
                }
                list.removeAll(Collections.singleton(null));
                return list;
            }
        });
        return this;
    }

    public ActionsList<T> evictLast() {
        return evict(new Func3<T>() { // from class: io.rx_cache2.ActionsList.8
            @Override // io.rx_cache2.ActionsList.Func3
            public boolean call(int i, int i2, T t) {
                return i == i2 - 1;
            }
        });
    }

    public ActionsList<T> evictLast(final Func1Count func1Count) {
        return evict(new Func3<T>() { // from class: io.rx_cache2.ActionsList.12
            @Override // io.rx_cache2.ActionsList.Func3
            public boolean call(int i, int i2, T t) {
                return i == i2 + (-1) && func1Count.call(i2);
            }
        });
    }

    public ActionsList<T> evictLastN(int i) {
        return evictLastN(new Func1Count() { // from class: io.rx_cache2.ActionsList.9
            @Override // io.rx_cache2.ActionsList.Func1Count
            public boolean call(int i2) {
                return true;
            }
        }, i);
    }

    public ActionsList<T> evictLastN(final Func1Count func1Count, final int i) {
        this.startToEvict = false;
        return evictIterable(new Func3<T>() { // from class: io.rx_cache2.ActionsList.13
            @Override // io.rx_cache2.ActionsList.Func3
            public boolean call(int i2, int i3, T t) {
                if (!ActionsList.this.startToEvict) {
                    ActionsList.this.startToEvict = i3 - i2 == i;
                }
                return ActionsList.this.startToEvict && i3 - i2 <= i && func1Count.call(i3);
            }
        });
    }

    public Observable<List<T>> toObservable() {
        return this.evict.call(this.cache);
    }

    public ActionsList<T> update(final Func1Element<T> func1Element, Replace<T> replace) {
        return update(new Func3<T>() { // from class: io.rx_cache2.ActionsList.20
            @Override // io.rx_cache2.ActionsList.Func3
            public boolean call(int i, int i2, T t) {
                return func1Element.call(t);
            }
        }, replace);
    }

    public ActionsList<T> update(final Func3<T> func3, final Replace<T> replace) {
        this.cache = (Observable<List<T>>) this.cache.map(new Function<List<T>, List<T>>() { // from class: io.rx_cache2.ActionsList.21
            /* JADX WARN: Multi-variable type inference failed */
            @Override // io.reactivex.functions.Function
            public List<T> apply(List<T> list) throws Exception {
                int size = list.size();
                int i = 0;
                while (true) {
                    if (i >= size) {
                        break;
                    }
                    if (func3.call(i, size, list.get(i))) {
                        list.set(i, replace.call(list.get(i)));
                        break;
                    }
                    i++;
                }
                return list;
            }
        });
        return this;
    }

    public ActionsList<T> updateIterable(final Func1Element<T> func1Element, Replace<T> replace) {
        return updateIterable(new Func3<T>() { // from class: io.rx_cache2.ActionsList.22
            @Override // io.rx_cache2.ActionsList.Func3
            public boolean call(int i, int i2, T t) {
                return func1Element.call(t);
            }
        }, replace);
    }

    public ActionsList<T> updateIterable(final Func3<T> func3, final Replace<T> replace) {
        this.cache = (Observable<List<T>>) this.cache.map(new Function<List<T>, List<T>>() { // from class: io.rx_cache2.ActionsList.23
            /* JADX WARN: Multi-variable type inference failed */
            @Override // io.reactivex.functions.Function
            public List<T> apply(List<T> list) throws Exception {
                int size = list.size();
                for (int i = 0; i < size; i++) {
                    if (func3.call(i, size, list.get(i))) {
                        list.set(i, replace.call(list.get(i)));
                    }
                }
                return list;
            }
        });
        return this;
    }
}
