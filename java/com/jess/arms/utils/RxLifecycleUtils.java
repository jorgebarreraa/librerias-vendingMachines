package com.jess.arms.utils;

import com.jess.arms.integration.lifecycle.ActivityLifecycleable;
import com.jess.arms.integration.lifecycle.FragmentLifecycleable;
import com.jess.arms.integration.lifecycle.Lifecycleable;
import com.jess.arms.mvp.IView;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.RxLifecycle;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.android.FragmentEvent;
import com.trello.rxlifecycle2.android.RxLifecycleAndroid;
import io.reactivex.annotations.NonNull;

/* loaded from: classes.dex */
public class RxLifecycleUtils {
    private RxLifecycleUtils() {
        throw new IllegalStateException("you can't instantiate me!");
    }

    public static <T> LifecycleTransformer<T> bindToLifecycle(@NonNull Lifecycleable lifecycleable) {
        Preconditions.checkNotNull(lifecycleable, "lifecycleable == null");
        if (lifecycleable instanceof ActivityLifecycleable) {
            return RxLifecycleAndroid.bindActivity(((ActivityLifecycleable) lifecycleable).provideLifecycleSubject());
        }
        if (lifecycleable instanceof FragmentLifecycleable) {
            return RxLifecycleAndroid.bindFragment(((FragmentLifecycleable) lifecycleable).provideLifecycleSubject());
        }
        throw new IllegalArgumentException("Lifecycleable not match");
    }

    public static <T> LifecycleTransformer<T> bindToLifecycle(@NonNull IView iView) {
        Preconditions.checkNotNull(iView, "view == null");
        if (iView instanceof Lifecycleable) {
            return bindToLifecycle((Lifecycleable) iView);
        }
        throw new IllegalArgumentException("view isn't Lifecycleable");
    }

    public static <T, R> LifecycleTransformer<T> bindUntilEvent(@NonNull Lifecycleable<R> lifecycleable, R r) {
        Preconditions.checkNotNull(lifecycleable, "lifecycleable == null");
        return RxLifecycle.bindUntilEvent(lifecycleable.provideLifecycleSubject(), r);
    }

    public static <T> LifecycleTransformer<T> bindUntilEvent(@NonNull IView iView, ActivityEvent activityEvent) {
        Preconditions.checkNotNull(iView, "view == null");
        if (iView instanceof ActivityLifecycleable) {
            return bindUntilEvent((ActivityLifecycleable) iView, activityEvent);
        }
        throw new IllegalArgumentException("view isn't ActivityLifecycleable");
    }

    public static <T> LifecycleTransformer<T> bindUntilEvent(@NonNull IView iView, FragmentEvent fragmentEvent) {
        Preconditions.checkNotNull(iView, "view == null");
        if (iView instanceof FragmentLifecycleable) {
            return bindUntilEvent((FragmentLifecycleable) iView, fragmentEvent);
        }
        throw new IllegalArgumentException("view isn't FragmentLifecycleable");
    }
}
