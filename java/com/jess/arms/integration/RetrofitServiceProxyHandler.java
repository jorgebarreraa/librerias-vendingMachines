package com.jess.arms.integration;

import androidx.annotation.Nullable;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.concurrent.Callable;
import retrofit2.Retrofit;

/* loaded from: classes.dex */
public class RetrofitServiceProxyHandler implements InvocationHandler {
    private Retrofit mRetrofit;
    private Object mRetrofitService;
    private Class<?> mServiceClass;

    public RetrofitServiceProxyHandler(Retrofit retrofit, Class<?> cls) {
        this.mRetrofit = retrofit;
        this.mServiceClass = cls;
    }

    private Object getRetrofitService() {
        if (this.mRetrofitService == null) {
            this.mRetrofitService = this.mRetrofit.create(this.mServiceClass);
        }
        return this.mRetrofitService;
    }

    @Override // java.lang.reflect.InvocationHandler
    public Object invoke(Object obj, final Method method, @Nullable final Object[] objArr) throws Throwable {
        return method.getReturnType() == Observable.class ? Observable.defer(new Callable() { // from class: com.jess.arms.integration.-$$Lambda$RetrofitServiceProxyHandler$so5e8DUhMI2YW-O_Pz7ZQUY1YCM
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return RetrofitServiceProxyHandler.this.lambda$invoke$0$RetrofitServiceProxyHandler(method, objArr);
            }
        }) : method.getReturnType() == Single.class ? Single.defer(new Callable() { // from class: com.jess.arms.integration.-$$Lambda$RetrofitServiceProxyHandler$kRmN9_Tzm1HuIArNKn1pK_bgBHQ
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return RetrofitServiceProxyHandler.this.lambda$invoke$1$RetrofitServiceProxyHandler(method, objArr);
            }
        }) : method.invoke(getRetrofitService(), objArr);
    }

    public /* synthetic */ ObservableSource lambda$invoke$0$RetrofitServiceProxyHandler(Method method, Object[] objArr) throws Exception {
        return (Observable) method.invoke(getRetrofitService(), objArr);
    }

    public /* synthetic */ SingleSource lambda$invoke$1$RetrofitServiceProxyHandler(Method method, Object[] objArr) throws Exception {
        return (Single) method.invoke(getRetrofitService(), objArr);
    }
}
