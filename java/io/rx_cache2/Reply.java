package io.rx_cache2;

/* loaded from: classes2.dex */
public final class Reply<T> {
    private final T data;
    private final boolean isEncrypted;
    private final Source source;

    public Reply(T t, Source source, boolean z) {
        this.data = t;
        this.source = source;
        this.isEncrypted = z;
    }

    public T getData() {
        return this.data;
    }

    public Source getSource() {
        return this.source;
    }

    public boolean isEncrypted() {
        return this.isEncrypted;
    }

    public String toString() {
        return "Reply{data=" + this.data + ", source=" + this.source + ", isEncrypted=" + this.isEncrypted + '}';
    }
}
