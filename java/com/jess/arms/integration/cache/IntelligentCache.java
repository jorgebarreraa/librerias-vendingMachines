package com.jess.arms.integration.cache;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.jess.arms.utils.Preconditions;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/* loaded from: classes.dex */
public class IntelligentCache<V> implements Cache<String, V> {
    public static final String KEY_KEEP = "Keep=";
    private final Cache<String, V> mCache;
    private final Map<String, V> mMap = new HashMap();

    public IntelligentCache(int i) {
        this.mCache = new LruCache(i);
    }

    @NonNull
    public static String getKeyOfKeep(@NonNull String str) {
        Preconditions.checkNotNull(str, "key == null");
        return KEY_KEEP + str;
    }

    @Override // com.jess.arms.integration.cache.Cache
    public void clear() {
        this.mCache.clear();
        this.mMap.clear();
    }

    @Override // com.jess.arms.integration.cache.Cache
    public synchronized boolean containsKey(String str) {
        if (str.startsWith(KEY_KEEP)) {
            return this.mMap.containsKey(str);
        }
        return this.mCache.containsKey(str);
    }

    @Override // com.jess.arms.integration.cache.Cache
    @Nullable
    public synchronized V get(String str) {
        if (str.startsWith(KEY_KEEP)) {
            return this.mMap.get(str);
        }
        return this.mCache.get(str);
    }

    @Override // com.jess.arms.integration.cache.Cache
    public synchronized int getMaxSize() {
        return this.mMap.size() + this.mCache.getMaxSize();
    }

    @Override // com.jess.arms.integration.cache.Cache
    public synchronized Set<String> keySet() {
        Set<String> keySet;
        keySet = this.mCache.keySet();
        keySet.addAll(this.mMap.keySet());
        return keySet;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.jess.arms.integration.cache.Cache
    @Nullable
    public /* bridge */ /* synthetic */ Object put(String str, Object obj) {
        return put2(str, (String) obj);
    }

    @Nullable
    /* renamed from: put, reason: avoid collision after fix types in other method */
    public synchronized V put2(String str, V v) {
        if (str.startsWith(KEY_KEEP)) {
            return this.mMap.put(str, v);
        }
        return this.mCache.put(str, v);
    }

    @Override // com.jess.arms.integration.cache.Cache
    @Nullable
    public synchronized V remove(String str) {
        if (str.startsWith(KEY_KEEP)) {
            return this.mMap.remove(str);
        }
        return this.mCache.remove(str);
    }

    @Override // com.jess.arms.integration.cache.Cache
    public synchronized int size() {
        return this.mMap.size() + this.mCache.size();
    }
}
