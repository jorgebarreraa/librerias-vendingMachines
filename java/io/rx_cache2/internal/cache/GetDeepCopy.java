package io.rx_cache2.internal.cache;

import io.rx_cache2.internal.Memory;
import io.rx_cache2.internal.Persistence;
import io.victoralbertos.jolyglot.JolyglotGenerics;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;

/* loaded from: classes2.dex */
public final class GetDeepCopy extends Action {
    private final JolyglotGenerics jolyglot;

    @Inject
    public GetDeepCopy(Memory memory, Persistence persistence, JolyglotGenerics jolyglotGenerics) {
        super(memory, persistence);
        this.jolyglot = jolyglotGenerics;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private <T> T getDeepCopyArray(T t) {
        Object[] objArr = (Object[]) t;
        if (objArr.length == 0) {
            return t;
        }
        GenericArrayType arrayOf = this.jolyglot.arrayOf(objArr[0].getClass());
        return (T) this.jolyglot.fromJson(this.jolyglot.toJson(t), arrayOf);
    }

    private <T> T getDeepCopyCollection(T t) {
        Collection collection = (Collection) t;
        if (collection.isEmpty()) {
            return t;
        }
        Class<?> cls = t.getClass();
        if (List.class.isAssignableFrom(cls)) {
            cls = List.class;
        }
        return (T) this.jolyglot.fromJson(this.jolyglot.toJson(t), this.jolyglot.newParameterizedType(cls, collection.toArray()[0].getClass()));
    }

    private <T, K, V> T getDeepCopyMap(T t) {
        Map map = (Map) t;
        if (map.isEmpty()) {
            return t;
        }
        Class<?> cls = map.values().toArray()[0].getClass();
        return (T) this.jolyglot.fromJson(this.jolyglot.toJson(t), this.jolyglot.newParameterizedType(Map.class, map.keySet().toArray()[0].getClass(), cls));
    }

    private <T> T getDeepCopyObject(T t) {
        if (t == null) {
            return t;
        }
        ParameterizedType newParameterizedType = this.jolyglot.newParameterizedType(t.getClass(), new Type[0]);
        return (T) this.jolyglot.fromJson(this.jolyglot.toJson(t), newParameterizedType);
    }

    public <T> T deepCopy(T t) {
        try {
            Class<?> cls = t.getClass();
            return Collection.class.isAssignableFrom(cls) ? (T) getDeepCopyCollection(t) : cls.isArray() ? (T) getDeepCopyArray(t) : Map.class.isAssignableFrom(cls) ? (T) getDeepCopyMap(t) : (T) getDeepCopyObject(t);
        } catch (Exception unused) {
            return t;
        }
    }
}
