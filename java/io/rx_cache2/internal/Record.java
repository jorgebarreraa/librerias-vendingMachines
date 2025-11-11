package io.rx_cache2.internal;

import io.rx_cache2.Source;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes2.dex */
public final class Record<T> {
    private final T data;
    private final String dataClassName;
    private final String dataCollectionClassName;
    private final String dataKeyMapClassName;
    private Boolean expirable;
    private Long lifeTime;
    private transient float sizeOnMb;
    private Source source;
    private final long timeAtWhichWasPersisted;

    public Record() {
        this.data = null;
        this.timeAtWhichWasPersisted = 0L;
        this.dataClassName = null;
        this.dataCollectionClassName = null;
        this.dataKeyMapClassName = null;
        this.expirable = true;
    }

    Record(T t) {
        this(t, true, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public Record(T t, Boolean bool, Long l) {
        this.data = t;
        this.expirable = bool;
        this.lifeTime = l;
        this.timeAtWhichWasPersisted = System.currentTimeMillis();
        this.source = Source.MEMORY;
        boolean isAssignableFrom = Collection.class.isAssignableFrom(t.getClass());
        boolean isArray = t.getClass().isArray();
        boolean isAssignableFrom2 = Map.class.isAssignableFrom(t.getClass());
        if (isAssignableFrom) {
            this.dataKeyMapClassName = null;
            List list = (List) t;
            if (list.size() > 0) {
                this.dataCollectionClassName = List.class.getName();
                this.dataClassName = list.get(0).getClass().getName();
                return;
            } else {
                this.dataClassName = null;
                this.dataCollectionClassName = null;
                return;
            }
        }
        if (isArray) {
            this.dataKeyMapClassName = null;
            Object[] objArr = (Object[]) t;
            if (objArr.length > 0) {
                this.dataClassName = objArr[0].getClass().getName();
                this.dataCollectionClassName = t.getClass().getName();
                return;
            } else {
                this.dataClassName = null;
                this.dataCollectionClassName = null;
                return;
            }
        }
        if (!isAssignableFrom2) {
            this.dataKeyMapClassName = null;
            this.dataClassName = t.getClass().getName();
            this.dataCollectionClassName = null;
            return;
        }
        Map map = (Map) t;
        if (map.size() <= 0) {
            this.dataClassName = null;
            this.dataCollectionClassName = null;
            this.dataKeyMapClassName = null;
            return;
        }
        this.dataCollectionClassName = Map.class.getName();
        Iterator it2 = map.entrySet().iterator();
        Map.Entry entry = (Map.Entry) it2.next();
        Class<?> cls = entry.getValue().getClass();
        Class<?> cls2 = entry.getKey().getClass();
        while (true) {
            if (it2.hasNext() || (cls == null && cls2 == null)) {
                Map.Entry entry2 = (Map.Entry) it2.next();
                if (cls2 != null && cls2 != entry2.getKey().getClass()) {
                    cls2 = null;
                }
                if (cls != null && cls != entry2.getValue().getClass()) {
                    cls = null;
                }
            }
        }
        this.dataClassName = cls != null ? cls.getName() : null;
        this.dataKeyMapClassName = cls2 != null ? cls2.getName() : null;
    }

    public T getData() {
        return this.data;
    }

    public String getDataClassName() {
        return this.dataClassName;
    }

    public String getDataCollectionClassName() {
        return this.dataCollectionClassName;
    }

    public String getDataKeyMapClassName() {
        return this.dataKeyMapClassName;
    }

    public Boolean getExpirable() {
        return this.expirable;
    }

    public Long getLifeTime() {
        return this.lifeTime;
    }

    public float getSizeOnMb() {
        return this.sizeOnMb;
    }

    public Source getSource() {
        return this.source;
    }

    public long getTimeAtWhichWasPersisted() {
        return this.timeAtWhichWasPersisted;
    }

    public void setExpirable(Boolean bool) {
        this.expirable = bool;
    }

    public void setLifeTime(Long l) {
        this.lifeTime = l;
    }

    public void setSizeOnMb(float f) {
        this.sizeOnMb = f;
    }

    public void setSource(Source source) {
        this.source = source;
    }
}
