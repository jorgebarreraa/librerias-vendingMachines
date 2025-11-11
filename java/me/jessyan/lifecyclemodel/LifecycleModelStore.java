package me.jessyan.lifecyclemodel;

import java.util.Iterator;
import me.jessyan.lifecyclemodel.cache.Cache;
import me.jessyan.lifecyclemodel.cache.LruCache;

/* loaded from: classes2.dex */
public class LifecycleModelStore {
    private final Cache<String, LifecycleModel> mCache = new LruCache(80);

    public final void clear() {
        Iterator<String> it2 = this.mCache.keySet().iterator();
        while (it2.hasNext()) {
            this.mCache.get(it2.next()).onCleared();
        }
        this.mCache.clear();
    }

    public final <T extends LifecycleModel> T get(String str) {
        return (T) this.mCache.get(str);
    }

    public final void put(String str, LifecycleModel lifecycleModel) {
        LifecycleModel lifecycleModel2 = this.mCache.get(str);
        if (lifecycleModel2 != null) {
            lifecycleModel2.onCleared();
        }
        this.mCache.put(str, lifecycleModel);
    }

    public final <T extends LifecycleModel> T remove(String str) {
        return (T) this.mCache.remove(str);
    }
}
