package com.trello.rxlifecycle2;

import io.reactivex.Observable;
import androidx.annotation.CheckResult;
import androidx.annotation.NonNull;

/* loaded from: classes.dex */
public interface LifecycleProvider<E> {
    @Nonnull
    @CheckReturnValue
    <T> LifecycleTransformer<T> bindToLifecycle();

    @Nonnull
    @CheckReturnValue
    <T> LifecycleTransformer<T> bindUntilEvent(@Nonnull E e);

    @Nonnull
    @CheckReturnValue
    Observable<E> lifecycle();
}
