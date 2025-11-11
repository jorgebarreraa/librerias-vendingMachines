package me.jessyan.rxerrorhandler.handler;

import android.util.Log;
import io.reactivex.Flowable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import java.util.concurrent.TimeUnit;
import org.reactivestreams.Publisher;

/* loaded from: classes2.dex */
public class RetryWithDelayOfFlowable implements Function<Flowable<Throwable>, Publisher<?>> {
    public final String TAG = getClass().getSimpleName();
    private final int maxRetries;
    private int retryCount;
    private final int retryDelaySecond;

    public RetryWithDelayOfFlowable(int i, int i2) {
        this.maxRetries = i;
        this.retryDelaySecond = i2;
    }

    static /* synthetic */ int access$004(RetryWithDelayOfFlowable retryWithDelayOfFlowable) {
        int i = retryWithDelayOfFlowable.retryCount + 1;
        retryWithDelayOfFlowable.retryCount = i;
        return i;
    }

    @Override // io.reactivex.functions.Function
    public Publisher<?> apply(@NonNull Flowable<Throwable> flowable) throws Exception {
        return flowable.flatMap(new Function<Throwable, Publisher<?>>() { // from class: me.jessyan.rxerrorhandler.handler.RetryWithDelayOfFlowable.1
            @Override // io.reactivex.functions.Function
            public Publisher<?> apply(@NonNull Throwable th) throws Exception {
                if (RetryWithDelayOfFlowable.access$004(RetryWithDelayOfFlowable.this) > RetryWithDelayOfFlowable.this.maxRetries) {
                    return Flowable.error(th);
                }
                Log.d(RetryWithDelayOfFlowable.this.TAG, "Flowable get error, it will try after " + RetryWithDelayOfFlowable.this.retryDelaySecond + " second, retry count " + RetryWithDelayOfFlowable.this.retryCount);
                return Flowable.timer(RetryWithDelayOfFlowable.this.retryDelaySecond, TimeUnit.SECONDS);
            }
        });
    }
}
