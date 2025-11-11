package io.rx_cache2.internal;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import io.rx_cache2.ConfigProvider;
import io.rx_cache2.EvictDynamicKey;
import io.rx_cache2.EvictDynamicKeyGroup;
import io.rx_cache2.Reply;
import io.rx_cache2.RxCacheException;
import io.rx_cache2.Source;
import io.rx_cache2.internal.cache.EvictExpiredRecordsPersistence;
import io.rx_cache2.internal.cache.GetDeepCopy;
import io.rx_cache2.internal.cache.TwoLayersCache;
import io.rx_cache2.internal.migration.DoMigrations;
import java.util.concurrent.Callable;
import javax.inject.Inject;

/* loaded from: classes2.dex */
public final class ProcessorProvidersBehaviour implements ProcessorProviders {
    private final GetDeepCopy getDeepCopy;
    private volatile Boolean hasProcessesEnded = false;
    private final Observable<Integer> oProcesses;
    private final TwoLayersCache twoLayersCache;
    private final Boolean useExpiredDataIfLoaderNotAvailable;

    @Inject
    public ProcessorProvidersBehaviour(TwoLayersCache twoLayersCache, Boolean bool, EvictExpiredRecordsPersistence evictExpiredRecordsPersistence, GetDeepCopy getDeepCopy, DoMigrations doMigrations) {
        this.twoLayersCache = twoLayersCache;
        this.useExpiredDataIfLoaderNotAvailable = bool;
        this.getDeepCopy = getDeepCopy;
        this.oProcesses = startProcesses(doMigrations, evictExpiredRecordsPersistence);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearKeyIfNeeded(ConfigProvider configProvider) {
        if (configProvider.evictProvider().evict()) {
            if (configProvider.evictProvider() instanceof EvictDynamicKeyGroup) {
                this.twoLayersCache.evictDynamicKeyGroup(configProvider.getProviderKey(), configProvider.getDynamicKey().toString(), configProvider.getDynamicKeyGroup().toString());
            } else if (configProvider.evictProvider() instanceof EvictDynamicKey) {
                this.twoLayersCache.evictDynamicKey(configProvider.getProviderKey(), configProvider.getDynamicKey().toString());
            } else {
                this.twoLayersCache.evictProviderKey(configProvider.getProviderKey());
            }
        }
    }

    private Observable<Reply> getDataFromLoader(final ConfigProvider configProvider, final Record record) {
        return configProvider.getLoaderObservable().map(new Function<Object, Reply>() { // from class: io.rx_cache2.internal.ProcessorProvidersBehaviour.6
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // io.reactivex.functions.Function
            public Reply apply(Object obj) throws Exception {
                Record record2;
                boolean booleanValue = (configProvider.useExpiredDataIfNotLoaderAvailable() != null ? configProvider.useExpiredDataIfNotLoaderAvailable() : ProcessorProvidersBehaviour.this.useExpiredDataIfLoaderNotAvailable).booleanValue();
                if (obj == null && booleanValue && (record2 = record) != null) {
                    return new Reply(record2.getData(), record.getSource(), configProvider.isEncrypted());
                }
                ProcessorProvidersBehaviour.this.clearKeyIfNeeded(configProvider);
                if (obj != null) {
                    ProcessorProvidersBehaviour.this.twoLayersCache.save(configProvider.getProviderKey(), configProvider.getDynamicKey(), configProvider.getDynamicKeyGroup(), obj, configProvider.getLifeTimeMillis(), configProvider.isExpirable(), configProvider.isEncrypted());
                    return new Reply(obj, Source.CLOUD, configProvider.isEncrypted());
                }
                throw new RxCacheException("The Loader provided did not return any data and there is not data to load from the Cache " + configProvider.getProviderKey());
            }
        }).onErrorReturn(new Function<Object, Object>() { // from class: io.rx_cache2.internal.ProcessorProvidersBehaviour.5
            @Override // io.reactivex.functions.Function
            public Object apply(Object obj) throws Exception {
                Record record2;
                ProcessorProvidersBehaviour.this.clearKeyIfNeeded(configProvider);
                if ((configProvider.useExpiredDataIfNotLoaderAvailable() != null ? configProvider.useExpiredDataIfNotLoaderAvailable() : ProcessorProvidersBehaviour.this.useExpiredDataIfLoaderNotAvailable).booleanValue() && (record2 = record) != null) {
                    return new Reply(record2.getData(), record.getSource(), configProvider.isEncrypted());
                }
                throw new RxCacheException("The Loader provided did not return any data and there is not data to load from the Cache " + configProvider.getProviderKey(), (Throwable) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Object getReturnType(ConfigProvider configProvider, Reply reply) {
        Object deepCopy = this.getDeepCopy.deepCopy(reply.getData());
        return configProvider.requiredDetailedResponse() ? new Reply(deepCopy, reply.getSource(), configProvider.isEncrypted()) : deepCopy;
    }

    private Observable<Integer> startProcesses(DoMigrations doMigrations, final EvictExpiredRecordsPersistence evictExpiredRecordsPersistence) {
        Observable<Integer> share = doMigrations.react().flatMap(new Function<Integer, ObservableSource<Integer>>() { // from class: io.rx_cache2.internal.ProcessorProvidersBehaviour.1
            @Override // io.reactivex.functions.Function
            public ObservableSource<Integer> apply(Integer num) throws Exception {
                return evictExpiredRecordsPersistence.startEvictingExpiredRecords();
            }
        }).subscribeOn(Schedulers.io()).observeOn(Schedulers.io()).share();
        share.subscribe(new Consumer<Integer>() { // from class: io.rx_cache2.internal.ProcessorProvidersBehaviour.2
            @Override // io.reactivex.functions.Consumer
            public void accept(Integer num) throws Exception {
                ProcessorProvidersBehaviour.this.hasProcessesEnded = true;
            }
        });
        return share;
    }

    @Override // io.rx_cache2.internal.ProcessorProviders
    public Observable<Void> evictAll() {
        return Observable.defer(new Callable<ObservableSource<Void>>() { // from class: io.rx_cache2.internal.ProcessorProvidersBehaviour.7
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            public ObservableSource<Void> call() throws Exception {
                ProcessorProvidersBehaviour.this.twoLayersCache.evictAll();
                return Completable.complete().toObservable();
            }
        });
    }

    <T> Observable<T> getData(final ConfigProvider configProvider) {
        Record<T> retrieve = this.twoLayersCache.retrieve(configProvider.getProviderKey(), configProvider.getDynamicKey(), configProvider.getDynamicKeyGroup(), this.useExpiredDataIfLoaderNotAvailable.booleanValue(), configProvider.getLifeTimeMillis(), configProvider.isEncrypted());
        return (Observable<T>) ((retrieve == null || configProvider.evictProvider().evict()) ? getDataFromLoader(configProvider, retrieve) : Observable.just(new Reply(retrieve.getData(), retrieve.getSource(), configProvider.isEncrypted()))).map(new Function<Reply, Object>() { // from class: io.rx_cache2.internal.ProcessorProvidersBehaviour.4
            @Override // io.reactivex.functions.Function
            public Object apply(Reply reply) throws Exception {
                return ProcessorProvidersBehaviour.this.getReturnType(configProvider, reply);
            }
        });
    }

    @Override // io.rx_cache2.internal.ProcessorProviders
    public <T> Observable<T> process(final ConfigProvider configProvider) {
        return Observable.defer(new Callable<ObservableSource<? extends T>>() { // from class: io.rx_cache2.internal.ProcessorProvidersBehaviour.3
            @Override // java.util.concurrent.Callable
            public ObservableSource<? extends T> call() throws Exception {
                return ProcessorProvidersBehaviour.this.hasProcessesEnded.booleanValue() ? ProcessorProvidersBehaviour.this.getData(configProvider) : ProcessorProvidersBehaviour.this.oProcesses.flatMap(new Function<Integer, ObservableSource<? extends T>>() { // from class: io.rx_cache2.internal.ProcessorProvidersBehaviour.3.1
                    @Override // io.reactivex.functions.Function
                    public ObservableSource<? extends T> apply(Integer num) throws Exception {
                        return ProcessorProvidersBehaviour.this.getData(configProvider);
                    }
                });
            }
        });
    }
}
