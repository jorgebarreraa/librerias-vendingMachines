package io.rx_cache2.internal;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Single;
import io.rx_cache2.EncryptKey;
import io.rx_cache2.Migration;
import io.rx_cache2.MigrationCache;
import io.rx_cache2.SchemeMigration;
import io.rx_cache2.internal.RxCache;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

/* loaded from: classes2.dex */
public final class ProxyProviders implements InvocationHandler {
    private final ProcessorProviders processorProviders;
    private final ProxyTranslator proxyTranslator = new ProxyTranslator();

    public ProxyProviders(RxCache.Builder builder, Class<?> cls) {
        this.processorProviders = DaggerRxCacheComponent.builder().rxCacheModule(new RxCacheModule(builder.getCacheDirectory(), Boolean.valueOf(builder.useExpiredDataIfLoaderNotAvailable()), builder.getMaxMBPersistenceCache(), getEncryptKey(cls), getMigrations(cls), builder.getJolyglot())).build().providers();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Observable<Void> evictAll() {
        return this.processorProviders.evictAll();
    }

    public String getEncryptKey(Class<?> cls) {
        EncryptKey encryptKey = (EncryptKey) cls.getAnnotation(EncryptKey.class);
        if (encryptKey == null) {
            return null;
        }
        return encryptKey.value();
    }

    public List<MigrationCache> getMigrations(Class<?> cls) {
        ArrayList arrayList = new ArrayList();
        Annotation annotation = cls.getAnnotation(SchemeMigration.class);
        if (annotation == null) {
            return arrayList;
        }
        for (Migration migration : ((SchemeMigration) annotation).value()) {
            arrayList.add(new MigrationCache(migration.version(), migration.evictClasses()));
        }
        return arrayList;
    }

    @Override // java.lang.reflect.InvocationHandler
    public Object invoke(Object obj, final Method method, final Object[] objArr) throws Throwable {
        return Observable.defer(new Callable<ObservableSource<?>>() { // from class: io.rx_cache2.internal.ProxyProviders.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            public ObservableSource<?> call() throws Exception {
                Observable process = ProxyProviders.this.processorProviders.process(ProxyProviders.this.proxyTranslator.processMethod(method, objArr));
                Class<?> returnType = method.getReturnType();
                if (returnType == Observable.class) {
                    return Observable.just(process);
                }
                if (returnType == Single.class) {
                    return Observable.just(Single.fromObservable(process));
                }
                if (returnType == Maybe.class) {
                    return Observable.just(Maybe.fromSingle(Single.fromObservable(process)));
                }
                if (method.getReturnType() == Flowable.class) {
                    return Observable.just(process.toFlowable(BackpressureStrategy.MISSING));
                }
                throw new RuntimeException(method.getName() + Locale.INVALID_RETURN_TYPE);
            }
        }).blockingFirst();
    }
}
