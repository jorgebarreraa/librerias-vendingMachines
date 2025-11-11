package me.jessyan.rxerrorhandler.handler;

import android.util.Log;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import java.util.concurrent.TimeUnit;

/* loaded from: classes2.dex */
public class RetryWithDelay implements Function<Observable<Throwable>, ObservableSource<?>> {
    public final String TAG = getClass().getSimpleName();
    private final int maxRetries;
    private int retryCount;
    private final int retryDelaySecond;

    public RetryWithDelay(int i, int i2) {
        this.maxRetries = i;
        this.retryDelaySecond = i2;
    }

    static /* synthetic */ int access$004(RetryWithDelay retryWithDelay) {
        int i = retryWithDelay.retryCount + 1;
        retryWithDelay.retryCount = i;
        return i;
    }

    @Override // io.reactivex.functions.Function
    public ObservableSource<?> apply(@NonNull Observable<Throwable> observable) throws Exception {
        return observable.flatMap(new Function<Throwable, ObservableSource<?>>() { // from class: me.jessyan.rxerrorhandler.handler.RetryWithDelay.1
            @Override // io.reactivex.functions.Function
            public ObservableSource<?> apply(@NonNull Throwable th) throws Exception {
                if (RetryWithDelay.access$004(RetryWithDelay.this) > RetryWithDelay.this.maxRetries) {
                    return Observable.error(th);
                }
                Log.d(RetryWithDelay.this.TAG, "Observable get error, it will try after " + RetryWithDelay.this.retryDelaySecond + " second, retry count " + RetryWithDelay.this.retryCount);
                return Observable.timer(RetryWithDelay.this.retryDelaySecond, TimeUnit.SECONDS);
            }
        });
    }
}
