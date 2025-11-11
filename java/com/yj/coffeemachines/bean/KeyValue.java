package com.yj.coffeemachines.bean;

/* loaded from: classes.dex */
public class KeyValue<T> {
    private String key;
    private T value;

    public KeyValue(String str) {
        this.key = str;
    }

    public KeyValue(String str, T t) {
        this.key = str;
        this.value = t;
    }

    public String getKey() {
        return this.key;
    }

    public T getValue() {
        return this.value;
    }

    public void setKey(String str) {
        this.key = str;
    }

    public void setValue(T t) {
        this.value = t;
    }

    public String toString() {
        String str = this.key;
        return str == null ? "" : str;
    }
}
