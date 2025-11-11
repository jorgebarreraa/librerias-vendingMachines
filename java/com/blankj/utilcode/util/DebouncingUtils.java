package com.blankj.utilcode.util;

import android.os.SystemClock;
import android.text.TextUtils;
import android.view.View;
import androidx.annotation.NonNull;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes.dex */
public class DebouncingUtils {
    private static final int CACHE_SIZE = 64;
    private static final long DEBOUNCING_DEFAULT_VALUE = 1000;
    private static final Map<String, Long> KEY_MILLIS_MAP = new ConcurrentHashMap(64);

    private DebouncingUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    private static void clearIfNecessary(long j) {
        if (KEY_MILLIS_MAP.size() < 64) {
            return;
        }
        Iterator<Map.Entry<String, Long>> it2 = KEY_MILLIS_MAP.entrySet().iterator();
        while (it2.hasNext()) {
            if (j >= it2.next().getValue().longValue()) {
                it2.remove();
            }
        }
    }

    public static boolean isValid(@NonNull View view) {
        return isValid(view, DEBOUNCING_DEFAULT_VALUE);
    }

    public static boolean isValid(@NonNull View view, long j) {
        return isValid(String.valueOf(view.hashCode()), j);
    }

    public static boolean isValid(@NonNull String str, long j) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("The key is null.");
        }
        if (j < 0) {
            throw new IllegalArgumentException("The duration is less than 0.");
        }
        long elapsedRealtime = SystemClock.elapsedRealtime();
        clearIfNecessary(elapsedRealtime);
        Long l = KEY_MILLIS_MAP.get(str);
        if (l != null && elapsedRealtime < l.longValue()) {
            return false;
        }
        KEY_MILLIS_MAP.put(str, Long.valueOf(elapsedRealtime + j));
        return true;
    }
}
