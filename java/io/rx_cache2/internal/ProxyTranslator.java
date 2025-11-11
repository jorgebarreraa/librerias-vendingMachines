package io.rx_cache2.internal;

import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.rx_cache2.ConfigProvider;
import io.rx_cache2.DynamicKey;
import io.rx_cache2.DynamicKeyGroup;
import io.rx_cache2.Encrypt;
import io.rx_cache2.EvictProvider;
import io.rx_cache2.Expirable;
import io.rx_cache2.LifeCache;
import io.rx_cache2.ProviderKey;
import io.rx_cache2.Reply;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import javax.inject.Inject;

/* loaded from: classes2.dex */
public final class ProxyTranslator {
    private final Map<Method, ConfigProvider> configProviderMethodCache = new HashMap();

    /* JADX INFO: Access modifiers changed from: package-private */
    @Inject
    public ProxyTranslator() {
    }

    private EvictProvider evictProvider(Method method, Object[] objArr) {
        EvictProvider evictProvider = (EvictProvider) getObjectFromMethodParam(method, EvictProvider.class, objArr);
        return evictProvider != null ? evictProvider : new EvictProvider(false);
    }

    private String getDynamicKey(Method method, Object[] objArr) {
        DynamicKey dynamicKey = (DynamicKey) getObjectFromMethodParam(method, DynamicKey.class, objArr);
        if (dynamicKey != null) {
            return dynamicKey.getDynamicKey().toString();
        }
        DynamicKeyGroup dynamicKeyGroup = (DynamicKeyGroup) getObjectFromMethodParam(method, DynamicKeyGroup.class, objArr);
        return dynamicKeyGroup != null ? dynamicKeyGroup.getDynamicKey().toString() : "";
    }

    private String getDynamicKeyGroup(Method method, Object[] objArr) {
        DynamicKeyGroup dynamicKeyGroup = (DynamicKeyGroup) getObjectFromMethodParam(method, DynamicKeyGroup.class, objArr);
        return dynamicKeyGroup != null ? dynamicKeyGroup.getGroup().toString() : "";
    }

    private boolean getExpirable(Method method) {
        Expirable expirable = (Expirable) method.getAnnotation(Expirable.class);
        if (expirable != null) {
            return expirable.value();
        }
        return true;
    }

    private Long getLifeTimeCache(Method method) {
        LifeCache lifeCache = (LifeCache) method.getAnnotation(LifeCache.class);
        if (lifeCache == null) {
            return null;
        }
        return Long.valueOf(lifeCache.timeUnit().toMillis(lifeCache.duration()));
    }

    private Observable getLoaderObservable(Method method, Object[] objArr) {
        Observable observable = (Observable) getObjectFromMethodParam(method, Observable.class, objArr);
        if (observable != null) {
            return observable;
        }
        Single single = (Single) getObjectFromMethodParam(method, Single.class, objArr);
        if (single != null) {
            return single.toObservable();
        }
        Maybe maybe = (Maybe) getObjectFromMethodParam(method, Maybe.class, objArr);
        if (maybe != null) {
            return maybe.toObservable();
        }
        Flowable flowable = (Flowable) getObjectFromMethodParam(method, Flowable.class, objArr);
        if (flowable != null) {
            return flowable.toObservable();
        }
        throw new IllegalArgumentException(method.getName() + Locale.NOT_REACTIVE_TYPE_FOR_LOADER_WAS_FOUND);
    }

    private <T> T getObjectFromMethodParam(Method method, Class<T> cls, Object[] objArr) {
        T t = null;
        int i = 0;
        for (Object obj : objArr) {
            if (cls.isAssignableFrom(obj.getClass())) {
                i++;
                t = (T) obj;
            }
        }
        if (i <= 1) {
            return t;
        }
        throw new IllegalArgumentException(method.getName() + Locale.JUST_ONE_INSTANCE + t.getClass().getSimpleName());
    }

    private String getProviderKey(Method method) {
        ProviderKey providerKey = (ProviderKey) method.getAnnotation(ProviderKey.class);
        return providerKey != null ? providerKey.value() : method.getName();
    }

    private boolean isEncrypted(Method method) {
        return ((Encrypt) method.getAnnotation(Encrypt.class)) != null;
    }

    private ConfigProvider loadConfigProviderMethod(Method method) {
        ConfigProvider configProvider;
        synchronized (this.configProviderMethodCache) {
            configProvider = this.configProviderMethodCache.get(method);
            if (configProvider == null) {
                configProvider = new ConfigProvider(getProviderKey(method), null, getLifeTimeCache(method), requiredDetailResponse(method), getExpirable(method), isEncrypted(method), null, null, null, null);
                this.configProviderMethodCache.put(method, configProvider);
            }
        }
        return configProvider;
    }

    private boolean requiredDetailResponse(Method method) {
        if (method.getReturnType() == Observable.class || method.getReturnType() == Single.class || method.getReturnType() == Maybe.class || method.getReturnType() == Flowable.class) {
            return method.getGenericReturnType().toString().contains(Reply.class.getName());
        }
        throw new IllegalArgumentException(method.getName() + Locale.INVALID_RETURN_TYPE);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ConfigProvider processMethod(Method method, Object[] objArr) {
        ConfigProvider loadConfigProviderMethod = loadConfigProviderMethod(method);
        return new ConfigProvider(loadConfigProviderMethod.getProviderKey(), null, loadConfigProviderMethod.getLifeTimeMillis(), loadConfigProviderMethod.requiredDetailedResponse(), loadConfigProviderMethod.isExpirable(), loadConfigProviderMethod.isEncrypted(), getDynamicKey(method, objArr), getDynamicKeyGroup(method, objArr), getLoaderObservable(method, objArr), evictProvider(method, objArr));
    }
}
