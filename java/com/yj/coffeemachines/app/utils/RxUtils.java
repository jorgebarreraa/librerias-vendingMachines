package com.yj.coffeemachines.app.utils;

import com.jess.arms.integration.lifecycle.Lifecycleable;
import com.jess.arms.utils.RxLifecycleUtils;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import java.util.concurrent.TimeUnit;

/* loaded from: classes.dex */
public class RxUtils {
    public static Disposable countdown(final int i, long j, @NonNull Lifecycleable lifecycleable, Consumer<Long> consumer) {
        return Observable.intervalRange(i, i + 1, j, 1L, TimeUnit.SECONDS).map(new Function() { // from class: com.yj.coffeemachines.app.utils.-$$Lambda$RxUtils$0YlS4A059xy1pyMhJkeM3yU4Seo
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                Long valueOf;
                valueOf = Long.valueOf((i * 2) - ((Long) obj).longValue());
                return valueOf;
            }
        }).observeOn(AndroidSchedulers.mainThread()).compose(RxLifecycleUtils.bindToLifecycle(lifecycleable)).subscribe(consumer);
    }
}
