package io.rx_cache2.internal.cache;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import io.rx_cache2.internal.Locale;
import io.rx_cache2.internal.Memory;
import io.rx_cache2.internal.Persistence;
import io.rx_cache2.internal.Record;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
/* loaded from: classes2.dex */
public final class EvictExpirableRecordsPersistence extends Action {
    private static final float PERCENTAGE_MEMORY_STORED_TO_START = 0.95f;
    public static final float PERCENTAGE_MEMORY_STORED_TO_STOP = 0.7f;
    private boolean couldBeExpirableRecords;
    private final String encryptKey;
    private boolean isEncrypted;
    private final Integer maxMgPersistenceCache;
    private final Observable<String> oEvictingTask;

    @Inject
    public EvictExpirableRecordsPersistence(Memory memory, Persistence persistence, Integer num, String str) {
        super(memory, persistence);
        this.maxMgPersistenceCache = num;
        this.encryptKey = str;
        this.couldBeExpirableRecords = true;
        this.oEvictingTask = oEvictingTask();
    }

    private Observable<String> oEvictingTask() {
        return Observable.create(new ObservableOnSubscribe<String>() { // from class: io.rx_cache2.internal.cache.EvictExpirableRecordsPersistence.2
            @Override // io.reactivex.ObservableOnSubscribe
            public void subscribe(ObservableEmitter<String> observableEmitter) throws Exception {
                if (!EvictExpirableRecordsPersistence.this.couldBeExpirableRecords) {
                    observableEmitter.onNext(Locale.RECORD_CAN_NOT_BE_EVICTED_BECAUSE_NO_ONE_IS_EXPIRABLE);
                    observableEmitter.onComplete();
                    return;
                }
                int storedMB = EvictExpirableRecordsPersistence.this.persistence.storedMB();
                if (!EvictExpirableRecordsPersistence.this.reachedPercentageMemoryToStart(storedMB)) {
                    observableEmitter.onComplete();
                    return;
                }
                float f = 0.0f;
                for (String str : EvictExpirableRecordsPersistence.this.persistence.allKeys()) {
                    if (EvictExpirableRecordsPersistence.this.reachedPercentageMemoryToStop(storedMB, f)) {
                        break;
                    }
                    Record retrieveRecord = EvictExpirableRecordsPersistence.this.persistence.retrieveRecord(str, EvictExpirableRecordsPersistence.this.isEncrypted, EvictExpirableRecordsPersistence.this.encryptKey);
                    if (retrieveRecord != null && retrieveRecord.getExpirable().booleanValue()) {
                        EvictExpirableRecordsPersistence.this.persistence.evict(str);
                        observableEmitter.onNext(str);
                        f += retrieveRecord.getSizeOnMb();
                    }
                }
                EvictExpirableRecordsPersistence evictExpirableRecordsPersistence = EvictExpirableRecordsPersistence.this;
                evictExpirableRecordsPersistence.couldBeExpirableRecords = evictExpirableRecordsPersistence.reachedPercentageMemoryToStop(storedMB, f);
                observableEmitter.onComplete();
            }
        }).subscribeOn(Schedulers.io()).observeOn(Schedulers.io()).doOnError(new Consumer<Throwable>() { // from class: io.rx_cache2.internal.cache.EvictExpirableRecordsPersistence.1
            @Override // io.reactivex.functions.Consumer
            public void accept(Throwable th) throws Exception {
                th.printStackTrace();
            }
        }).share();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean reachedPercentageMemoryToStart(int i) {
        return i >= ((int) (((float) this.maxMgPersistenceCache.intValue()) * PERCENTAGE_MEMORY_STORED_TO_START));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean reachedPercentageMemoryToStop(int i, float f) {
        return ((float) i) - f <= ((float) this.maxMgPersistenceCache.intValue()) * 0.7f;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Observable<String> startTaskIfNeeded(boolean z) {
        this.isEncrypted = z;
        this.oEvictingTask.subscribe();
        return this.oEvictingTask;
    }
}
