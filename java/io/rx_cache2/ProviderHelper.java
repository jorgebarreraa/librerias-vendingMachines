package io.rx_cache2;

import io.reactivex.Observable;

/* loaded from: classes2.dex */
public class ProviderHelper {
    public static <T> Observable<T> withoutLoader() {
        return Observable.error(new RuntimeException());
    }
}
