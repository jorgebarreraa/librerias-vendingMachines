package com.jess.arms.integration.lifecycle;

import androidx.annotation.NonNull;
import io.reactivex.subjects.Subject;

/* loaded from: classes.dex */
public interface Lifecycleable<E> {
    @NonNull
    Subject<E> provideLifecycleSubject();
}
