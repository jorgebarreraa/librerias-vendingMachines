package io.reactivex.internal.operators.single;

import android.R;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.Callable;

/* loaded from: classes2.dex */
public final class SingleFromCallable<T> extends Single<T> {
    final Callable<? extends T> callable;

    public SingleFromCallable(Callable<? extends T> callable) {
        this.callable = callable;
    }

    @Override // io.reactivex.Single
    protected void subscribeActual(SingleObserver<? super T> singleObserver) {
        Disposable empty = Disposables.empty();
        singleObserver.onSubscribe(empty);
        if (empty.isDisposed()) {
            return;
        }
        try {
            R.bool boolVar = (Object) ObjectHelper.requireNonNull(this.callable.call(), "The callable returned a null value");
            if (empty.isDisposed()) {
                return;
            }
            singleObserver.onSuccess(boolVar);
        } catch (Throwable th) {
            Exceptions.throwIfFatal(th);
            if (empty.isDisposed()) {
                RxJavaPlugins.onError(th);
            } else {
                singleObserver.onError(th);
            }
        }
    }
}
