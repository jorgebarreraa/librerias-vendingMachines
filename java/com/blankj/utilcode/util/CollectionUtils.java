package com.blankj.utilcode.util;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/* loaded from: classes.dex */
public final class CollectionUtils {

    /* loaded from: classes.dex */
    public interface Closure<E> {
        void execute(int i, E e);
    }

    /* loaded from: classes.dex */
    public interface Predicate<E> {
        boolean evaluate(E e);
    }

    /* loaded from: classes.dex */
    public interface Transformer<E1, E2> {
        E2 transform(E1 e1);
    }

    private CollectionUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static <E> void addAll(Collection<E> collection, Enumeration<E> enumeration) {
        if (collection == null || enumeration == null) {
            return;
        }
        while (enumeration.hasMoreElements()) {
            collection.add(enumeration.nextElement());
        }
    }

    public static <E> void addAll(Collection<E> collection, Iterator<E> it2) {
        if (collection == null || it2 == null) {
            return;
        }
        while (it2.hasNext()) {
            collection.add(it2.next());
        }
    }

    public static <E> void addAll(Collection<E> collection, E[] eArr) {
        if (collection == null || eArr == null || eArr.length == 0) {
            return;
        }
        collection.addAll(Arrays.asList(eArr));
    }

    public static <E> boolean addIgnoreNull(Collection<E> collection, E e) {
        return (collection == null || e == null || !collection.add(e)) ? false : true;
    }

    public static <E> int cardinality(E e, Collection<E> collection) {
        int i = 0;
        if (collection == null) {
            return 0;
        }
        if (collection instanceof Set) {
            return collection.contains(e) ? 1 : 0;
        }
        if (e == null) {
            Iterator<E> it2 = collection.iterator();
            while (it2.hasNext()) {
                if (it2.next() == null) {
                    i++;
                }
            }
        } else {
            Iterator<E> it3 = collection.iterator();
            while (it3.hasNext()) {
                if (e.equals(it3.next())) {
                    i++;
                }
            }
        }
        return i;
    }

    public static <E1, E2> Collection<E2> collect(Collection<E1> collection, Transformer<E1, E2> transformer) {
        ArrayList arrayList = new ArrayList();
        if (collection != null && transformer != null) {
            Iterator<E1> it2 = collection.iterator();
            while (it2.hasNext()) {
                arrayList.add(transformer.transform(it2.next()));
            }
        }
        return arrayList;
    }

    public static boolean containsAny(Collection collection, Collection collection2) {
        if (collection != null && collection2 != null) {
            if (collection.size() < collection2.size()) {
                Iterator it2 = collection.iterator();
                while (it2.hasNext()) {
                    if (collection2.contains(it2.next())) {
                        return true;
                    }
                }
            } else {
                Iterator it3 = collection2.iterator();
                while (it3.hasNext()) {
                    if (collection.contains(it3.next())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static <E> int countMatches(Collection<E> collection, Predicate<E> predicate) {
        int i = 0;
        if (collection != null && predicate != null) {
            Iterator<E> it2 = collection.iterator();
            while (it2.hasNext()) {
                if (predicate.evaluate(it2.next())) {
                    i++;
                }
            }
        }
        return i;
    }

    public static Collection disjunction(Collection collection, Collection collection2) {
        if (collection == null && collection2 == null) {
            return new ArrayList();
        }
        if (collection == null) {
            return new ArrayList(collection2);
        }
        if (collection2 == null) {
            return new ArrayList(collection);
        }
        ArrayList arrayList = new ArrayList();
        Map<Object, Integer> cardinalityMap = getCardinalityMap(collection);
        Map<Object, Integer> cardinalityMap2 = getCardinalityMap(collection2);
        HashSet hashSet = new HashSet(collection);
        hashSet.addAll(collection2);
        for (Object obj : hashSet) {
            int max = Math.max(getFreq(obj, cardinalityMap), getFreq(obj, cardinalityMap2)) - Math.min(getFreq(obj, cardinalityMap), getFreq(obj, cardinalityMap2));
            for (int i = 0; i < max; i++) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    public static <E> boolean exists(Collection<E> collection, Predicate<E> predicate) {
        if (collection != null && predicate != null) {
            Iterator<E> it2 = collection.iterator();
            while (it2.hasNext()) {
                if (predicate.evaluate(it2.next())) {
                    return true;
                }
            }
        }
        return false;
    }

    public static <E> void filter(Collection<E> collection, Predicate<E> predicate) {
        if (collection == null || predicate == null) {
            return;
        }
        Iterator<E> it2 = collection.iterator();
        while (it2.hasNext()) {
            if (!predicate.evaluate(it2.next())) {
                it2.remove();
            }
        }
    }

    public static <E> E find(Collection<E> collection, Predicate<E> predicate) {
        if (collection != null && predicate != null) {
            for (E e : collection) {
                if (predicate.evaluate(e)) {
                    return e;
                }
            }
        }
        return null;
    }

    public static <E> void forAllDo(Collection<E> collection, Closure<E> closure) {
        if (collection == null || closure == null) {
            return;
        }
        int i = 0;
        Iterator<E> it2 = collection.iterator();
        while (it2.hasNext()) {
            closure.execute(i, it2.next());
            i++;
        }
    }

    public static Object get(Object obj, int i) {
        if (obj == null) {
            return null;
        }
        if (i < 0) {
            throw new IndexOutOfBoundsException("Index cannot be negative: " + i);
        }
        if (obj instanceof Map) {
            return get(((Map) obj).entrySet().iterator(), i);
        }
        if (obj instanceof List) {
            return ((List) obj).get(i);
        }
        if (obj instanceof Object[]) {
            return ((Object[]) obj)[i];
        }
        if (obj instanceof Iterator) {
            Iterator it2 = (Iterator) obj;
            while (it2.hasNext()) {
                i--;
                if (i == -1) {
                    return it2.next();
                }
                it2.next();
            }
            throw new IndexOutOfBoundsException("Entry does not exist: " + i);
        }
        if (obj instanceof Collection) {
            return get(((Collection) obj).iterator(), i);
        }
        if (!(obj instanceof Enumeration)) {
            try {
                return Array.get(obj, i);
            } catch (IllegalArgumentException unused) {
                throw new IllegalArgumentException("Unsupported object type: " + obj.getClass().getName());
            }
        }
        Enumeration enumeration = (Enumeration) obj;
        while (enumeration.hasMoreElements()) {
            i--;
            if (i == -1) {
                return enumeration.nextElement();
            }
            enumeration.nextElement();
        }
        throw new IndexOutOfBoundsException("Entry does not exist: " + i);
    }

    public static Map<Object, Integer> getCardinalityMap(Collection collection) {
        HashMap hashMap = new HashMap();
        if (collection == null) {
            return hashMap;
        }
        for (Object obj : collection) {
            Integer num = (Integer) hashMap.get(obj);
            if (num == null) {
                hashMap.put(obj, 1);
            } else {
                hashMap.put(obj, Integer.valueOf(num.intValue() + 1));
            }
        }
        return hashMap;
    }

    private static int getFreq(Object obj, Map map) {
        Integer num = (Integer) map.get(obj);
        if (num != null) {
            return num.intValue();
        }
        return 0;
    }

    public static Collection intersection(Collection collection, Collection collection2) {
        if (collection == null || collection2 == null) {
            return new ArrayList();
        }
        ArrayList arrayList = new ArrayList();
        Map<Object, Integer> cardinalityMap = getCardinalityMap(collection);
        Map<Object, Integer> cardinalityMap2 = getCardinalityMap(collection2);
        HashSet hashSet = new HashSet(collection);
        hashSet.addAll(collection2);
        for (Object obj : hashSet) {
            int min = Math.min(getFreq(obj, cardinalityMap), getFreq(obj, cardinalityMap2));
            for (int i = 0; i < min; i++) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    public static boolean isEmpty(Collection collection) {
        return collection == null || collection.size() == 0;
    }

    public static boolean isEqualCollection(Collection collection, Collection collection2) {
        if (collection == null || collection2 == null || collection.size() != collection2.size()) {
            return false;
        }
        Map<Object, Integer> cardinalityMap = getCardinalityMap(collection);
        Map<Object, Integer> cardinalityMap2 = getCardinalityMap(collection2);
        if (cardinalityMap.size() != cardinalityMap2.size()) {
            return false;
        }
        for (Object obj : cardinalityMap.keySet()) {
            if (getFreq(obj, cardinalityMap) != getFreq(obj, cardinalityMap2)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isNotEmpty(Collection collection) {
        return !isEmpty(collection);
    }

    public static boolean isProperSubCollection(Collection collection, Collection collection2) {
        return collection != null && collection2 != null && collection.size() < collection2.size() && isSubCollection(collection, collection2);
    }

    public static boolean isSubCollection(Collection collection, Collection collection2) {
        if (collection == null || collection2 == null) {
            return false;
        }
        Map<Object, Integer> cardinalityMap = getCardinalityMap(collection);
        Map<Object, Integer> cardinalityMap2 = getCardinalityMap(collection2);
        for (Object obj : collection) {
            if (getFreq(obj, cardinalityMap) > getFreq(obj, cardinalityMap2)) {
                return false;
            }
        }
        return true;
    }

    @SafeVarargs
    public static <E> ArrayList<E> newArrayList(E... eArr) {
        ArrayList<E> arrayList = new ArrayList<>();
        if (eArr != null && eArr.length != 0) {
            for (E e : eArr) {
                arrayList.add(e);
            }
        }
        return arrayList;
    }

    @SafeVarargs
    public static <E> ArrayList<E> newArrayListNotNull(E... eArr) {
        ArrayList<E> arrayList = new ArrayList<>();
        if (eArr != null && eArr.length != 0) {
            for (E e : eArr) {
                if (e != null) {
                    arrayList.add(e);
                }
            }
        }
        return arrayList;
    }

    @SafeVarargs
    public static <E> HashSet<E> newHashSet(E... eArr) {
        HashSet<E> hashSet = new HashSet<>();
        if (eArr != null && eArr.length != 0) {
            for (E e : eArr) {
                hashSet.add(e);
            }
        }
        return hashSet;
    }

    @SafeVarargs
    public static <E> HashSet<E> newHashSetNotNull(E... eArr) {
        HashSet<E> hashSet = new HashSet<>();
        if (eArr != null && eArr.length != 0) {
            for (E e : eArr) {
                if (e != null) {
                    hashSet.add(e);
                }
            }
        }
        return hashSet;
    }

    @SafeVarargs
    public static <E> LinkedList<E> newLinkedList(E... eArr) {
        LinkedList<E> linkedList = new LinkedList<>();
        if (eArr != null && eArr.length != 0) {
            for (E e : eArr) {
                linkedList.add(e);
            }
        }
        return linkedList;
    }

    @SafeVarargs
    public static <E> LinkedList<E> newLinkedListNotNull(E... eArr) {
        LinkedList<E> linkedList = new LinkedList<>();
        if (eArr != null && eArr.length != 0) {
            for (E e : eArr) {
                if (e != null) {
                    linkedList.add(e);
                }
            }
        }
        return linkedList;
    }

    public static Collection newSynchronizedCollection(Collection collection) {
        return Collections.synchronizedCollection(collection);
    }

    @SafeVarargs
    public static <E> TreeSet<E> newTreeSet(Comparator<E> comparator, E... eArr) {
        TreeSet<E> treeSet = new TreeSet<>(comparator);
        if (eArr != null && eArr.length != 0) {
            for (E e : eArr) {
                treeSet.add(e);
            }
        }
        return treeSet;
    }

    @SafeVarargs
    public static <E> TreeSet<E> newTreeSetNotNull(Comparator<E> comparator, E... eArr) {
        TreeSet<E> treeSet = new TreeSet<>(comparator);
        if (eArr != null && eArr.length != 0) {
            for (E e : eArr) {
                if (e != null) {
                    treeSet.add(e);
                }
            }
        }
        return treeSet;
    }

    public static Collection newUnmodifiableCollection(Collection collection) {
        return Collections.unmodifiableCollection(collection);
    }

    @SafeVarargs
    public static <E> List<E> newUnmodifiableList(E... eArr) {
        return Collections.unmodifiableList(newArrayList(eArr));
    }

    @SafeVarargs
    public static <E> List<E> newUnmodifiableListNotNull(E... eArr) {
        return Collections.unmodifiableList(newArrayListNotNull(eArr));
    }

    public static <E> Collection<E> removeAll(Collection<E> collection, Collection<E> collection2) {
        if (collection == null) {
            return new ArrayList();
        }
        if (collection2 == null) {
            return new ArrayList(collection);
        }
        ArrayList arrayList = new ArrayList();
        for (E e : collection) {
            if (!collection2.contains(e)) {
                arrayList.add(e);
            }
        }
        return arrayList;
    }

    public static <E> Collection<E> retainAll(Collection<E> collection, Collection<E> collection2) {
        if (collection == null || collection2 == null) {
            return new ArrayList();
        }
        ArrayList arrayList = new ArrayList();
        for (E e : collection) {
            if (collection2.contains(e)) {
                arrayList.add(e);
            }
        }
        return arrayList;
    }

    public static <E> Collection<E> select(Collection<E> collection, Predicate<E> predicate) {
        if (collection == null || predicate == null) {
            return new ArrayList();
        }
        ArrayList arrayList = new ArrayList(collection.size());
        for (E e : collection) {
            if (predicate.evaluate(e)) {
                arrayList.add(e);
            }
        }
        return arrayList;
    }

    public static <E> Collection<E> selectRejected(Collection<E> collection, Predicate<E> predicate) {
        if (collection == null || predicate == null) {
            return new ArrayList();
        }
        ArrayList arrayList = new ArrayList(collection.size());
        for (E e : collection) {
            if (!predicate.evaluate(e)) {
                arrayList.add(e);
            }
        }
        return arrayList;
    }

    public static <T> void shuffle(List<T> list) {
        Collections.shuffle(list);
    }

    public static int size(Object obj) {
        int i = 0;
        if (obj == null) {
            return 0;
        }
        if (obj instanceof Map) {
            return ((Map) obj).size();
        }
        if (obj instanceof Collection) {
            return ((Collection) obj).size();
        }
        if (obj instanceof Object[]) {
            return ((Object[]) obj).length;
        }
        if (obj instanceof Iterator) {
            Iterator it2 = (Iterator) obj;
            while (it2.hasNext()) {
                i++;
                it2.next();
            }
        } else {
            if (!(obj instanceof Enumeration)) {
                try {
                    return Array.getLength(obj);
                } catch (IllegalArgumentException unused) {
                    throw new IllegalArgumentException("Unsupported object type: " + obj.getClass().getName());
                }
            }
            Enumeration enumeration = (Enumeration) obj;
            while (enumeration.hasMoreElements()) {
                i++;
                enumeration.nextElement();
            }
        }
        return i;
    }

    public static boolean sizeIsEmpty(Object obj) {
        if (obj == null) {
            return true;
        }
        if (obj instanceof Collection) {
            return ((Collection) obj).isEmpty();
        }
        if (obj instanceof Map) {
            return ((Map) obj).isEmpty();
        }
        if (obj instanceof Object[]) {
            return ((Object[]) obj).length == 0;
        }
        if (obj instanceof Iterator) {
            return !((Iterator) obj).hasNext();
        }
        if (obj instanceof Enumeration) {
            return !((Enumeration) obj).hasMoreElements();
        }
        try {
            return Array.getLength(obj) == 0;
        } catch (IllegalArgumentException unused) {
            throw new IllegalArgumentException("Unsupported object type: " + obj.getClass().getName());
        }
    }

    public static Collection subtract(Collection collection, Collection collection2) {
        if (collection == null) {
            return new ArrayList();
        }
        if (collection2 == null) {
            return new ArrayList(collection);
        }
        ArrayList arrayList = new ArrayList(collection);
        Iterator it2 = collection2.iterator();
        while (it2.hasNext()) {
            arrayList.remove(it2.next());
        }
        return arrayList;
    }

    public static String toString(Collection collection) {
        return collection == null ? "null" : collection.toString();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <E1, E2> void transform(Collection<E1> collection, Transformer<E1, E2> transformer) {
        if (collection == null || transformer == 0) {
            return;
        }
        if (collection instanceof List) {
            ListIterator listIterator = ((List) collection).listIterator();
            while (listIterator.hasNext()) {
                listIterator.set(transformer.transform(listIterator.next()));
            }
        } else {
            Collection<? extends E1> collect = collect(collection, transformer);
            collection.clear();
            collection.addAll(collect);
        }
    }

    public static Collection union(Collection collection, Collection collection2) {
        if (collection == null && collection2 == null) {
            return new ArrayList();
        }
        if (collection == null) {
            return new ArrayList(collection2);
        }
        if (collection2 == null) {
            return new ArrayList(collection);
        }
        ArrayList arrayList = new ArrayList();
        Map<Object, Integer> cardinalityMap = getCardinalityMap(collection);
        Map<Object, Integer> cardinalityMap2 = getCardinalityMap(collection2);
        HashSet hashSet = new HashSet(collection);
        hashSet.addAll(collection2);
        for (Object obj : hashSet) {
            int max = Math.max(getFreq(obj, cardinalityMap), getFreq(obj, cardinalityMap2));
            for (int i = 0; i < max; i++) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }
}
