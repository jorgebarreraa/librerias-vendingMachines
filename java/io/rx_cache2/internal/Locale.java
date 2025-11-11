package io.rx_cache2.internal;

/* loaded from: classes2.dex */
public interface Locale {
    public static final String EVICT_DYNAMIC_KEY_GROUP_PROVIDED_BUT_NOT_PROVIDED_ANY_DYNAMIC_KEY_GROUP = " EvictDynamicKeyGroup was provided but not was provided any Group";
    public static final String EVICT_DYNAMIC_KEY_PROVIDED_BUT_NOT_PROVIDED_ANY_DYNAMIC_KEY = " EvictDynamicKey was provided but not was provided any DynamicKey";
    public static final String INVALID_RETURN_TYPE = " needs to return one of the next reactive types: observable, single, maybe or flowable";
    public static final String JSON_CONVERTER_CAN_NOT_BE_NULL = "JsonConverter can not be null";
    public static final String JUST_ONE_INSTANCE = " requires just one instance of type ";
    public static final String NOT_DATA_RETURN_WHEN_CALLING_OBSERVABLE_LOADER = "The Loader provided did not return any data and there is not data to load from the Cache";
    public static final String NOT_REACTIVE_TYPE_FOR_LOADER_WAS_FOUND = " requires an instance of one of the next reactive types: observable, single, maybe or flowable";
    public static final String RECORD_CAN_NOT_BE_EVICTED_BECAUSE_NO_ONE_IS_EXPIRABLE = "Records can not be evicted because no one is expirable";
    public static final String RECORD_CAN_NOT_BE_PERSISTED_BECAUSE_WOULD_EXCEED_THRESHOLD_LIMIT = "RxCache -> Record can not be persisted because it would exceed the max limit megabytes settled down";
    public static final String REPOSITORY_DISK_ADAPTER_CAN_NOT_BE_NULL = "File cache directory can not be null";
    public static final String REPOSITORY_DISK_ADAPTER_DOES_NOT_EXIST = "File cache directory does not exist";
    public static final String REPOSITORY_DISK_ADAPTER_IS_NOT_WRITABLE = "File cache directory is not writable";
}
