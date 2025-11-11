package com.jess.arms.integration.cache;

import android.app.ActivityManager;
import android.content.Context;

/* loaded from: classes.dex */
public interface CacheType {
    public static final int ACTIVITY_CACHE_TYPE_ID = 3;
    public static final int CACHE_SERVICE_CACHE_TYPE_ID = 1;
    public static final int EXTRAS_TYPE_ID = 2;
    public static final int FRAGMENT_CACHE_TYPE_ID = 4;
    public static final int RETROFIT_SERVICE_CACHE_TYPE_ID = 0;
    public static final CacheType RETROFIT_SERVICE_CACHE = new CacheType() { // from class: com.jess.arms.integration.cache.CacheType.1
        private static final int MAX_SIZE = 150;
        private static final float MAX_SIZE_MULTIPLIER = 0.002f;

        @Override // com.jess.arms.integration.cache.CacheType
        public int calculateCacheSize(Context context) {
            int memoryClass = (int) (((ActivityManager) context.getSystemService("activity")).getMemoryClass() * MAX_SIZE_MULTIPLIER * 1024.0f);
            if (memoryClass >= 150) {
                return 150;
            }
            return memoryClass;
        }

        @Override // com.jess.arms.integration.cache.CacheType
        public int getCacheTypeId() {
            return 0;
        }
    };
    public static final CacheType CACHE_SERVICE_CACHE = new CacheType() { // from class: com.jess.arms.integration.cache.CacheType.2
        private static final int MAX_SIZE = 150;
        private static final float MAX_SIZE_MULTIPLIER = 0.002f;

        @Override // com.jess.arms.integration.cache.CacheType
        public int calculateCacheSize(Context context) {
            int memoryClass = (int) (((ActivityManager) context.getSystemService("activity")).getMemoryClass() * MAX_SIZE_MULTIPLIER * 1024.0f);
            if (memoryClass >= 150) {
                return 150;
            }
            return memoryClass;
        }

        @Override // com.jess.arms.integration.cache.CacheType
        public int getCacheTypeId() {
            return 1;
        }
    };
    public static final CacheType EXTRAS = new CacheType() { // from class: com.jess.arms.integration.cache.CacheType.3
        private static final int MAX_SIZE = 500;
        private static final float MAX_SIZE_MULTIPLIER = 0.005f;

        @Override // com.jess.arms.integration.cache.CacheType
        public int calculateCacheSize(Context context) {
            int memoryClass = (int) (((ActivityManager) context.getSystemService("activity")).getMemoryClass() * MAX_SIZE_MULTIPLIER * 1024.0f);
            if (memoryClass >= 500) {
                return 500;
            }
            return memoryClass;
        }

        @Override // com.jess.arms.integration.cache.CacheType
        public int getCacheTypeId() {
            return 2;
        }
    };
    public static final CacheType ACTIVITY_CACHE = new CacheType() { // from class: com.jess.arms.integration.cache.CacheType.4
        private static final int MAX_SIZE = 80;
        private static final float MAX_SIZE_MULTIPLIER = 8.0E-4f;

        @Override // com.jess.arms.integration.cache.CacheType
        public int calculateCacheSize(Context context) {
            int memoryClass = (int) (((ActivityManager) context.getSystemService("activity")).getMemoryClass() * MAX_SIZE_MULTIPLIER * 1024.0f);
            if (memoryClass >= 80) {
                return 80;
            }
            return memoryClass;
        }

        @Override // com.jess.arms.integration.cache.CacheType
        public int getCacheTypeId() {
            return 3;
        }
    };
    public static final CacheType FRAGMENT_CACHE = new CacheType() { // from class: com.jess.arms.integration.cache.CacheType.5
        private static final int MAX_SIZE = 80;
        private static final float MAX_SIZE_MULTIPLIER = 8.0E-4f;

        @Override // com.jess.arms.integration.cache.CacheType
        public int calculateCacheSize(Context context) {
            int memoryClass = (int) (((ActivityManager) context.getSystemService("activity")).getMemoryClass() * MAX_SIZE_MULTIPLIER * 1024.0f);
            if (memoryClass >= 80) {
                return 80;
            }
            return memoryClass;
        }

        @Override // com.jess.arms.integration.cache.CacheType
        public int getCacheTypeId() {
            return 4;
        }
    };

    int calculateCacheSize(Context context);

    int getCacheTypeId();
}
