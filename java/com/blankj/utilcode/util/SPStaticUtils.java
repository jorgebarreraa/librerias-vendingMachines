package com.blankj.utilcode.util;

import androidx.annotation.NonNull;
import java.util.Map;
import java.util.Set;

/* loaded from: classes.dex */
public final class SPStaticUtils {
    private static SPUtils sDefaultSPUtils;

    public static void clear() {
        clear(getDefaultSPUtils());
    }

    public static void clear(@NonNull SPUtils sPUtils) {
        sPUtils.clear();
    }

    public static void clear(boolean z) {
        clear(z, getDefaultSPUtils());
    }

    public static void clear(boolean z, @NonNull SPUtils sPUtils) {
        sPUtils.clear(z);
    }

    public static boolean contains(@NonNull String str) {
        return contains(str, getDefaultSPUtils());
    }

    public static boolean contains(@NonNull String str, @NonNull SPUtils sPUtils) {
        return sPUtils.contains(str);
    }

    public static Map<String, ?> getAll() {
        return getAll(getDefaultSPUtils());
    }

    public static Map<String, ?> getAll(@NonNull SPUtils sPUtils) {
        return sPUtils.getAll();
    }

    public static boolean getBoolean(@NonNull String str) {
        return getBoolean(str, getDefaultSPUtils());
    }

    public static boolean getBoolean(@NonNull String str, @NonNull SPUtils sPUtils) {
        return sPUtils.getBoolean(str);
    }

    public static boolean getBoolean(@NonNull String str, boolean z) {
        return getBoolean(str, z, getDefaultSPUtils());
    }

    public static boolean getBoolean(@NonNull String str, boolean z, @NonNull SPUtils sPUtils) {
        return sPUtils.getBoolean(str, z);
    }

    private static SPUtils getDefaultSPUtils() {
        SPUtils sPUtils = sDefaultSPUtils;
        return sPUtils != null ? sPUtils : SPUtils.getInstance();
    }

    public static float getFloat(@NonNull String str) {
        return getFloat(str, getDefaultSPUtils());
    }

    public static float getFloat(@NonNull String str, float f) {
        return getFloat(str, f, getDefaultSPUtils());
    }

    public static float getFloat(@NonNull String str, float f, @NonNull SPUtils sPUtils) {
        return sPUtils.getFloat(str, f);
    }

    public static float getFloat(@NonNull String str, @NonNull SPUtils sPUtils) {
        return sPUtils.getFloat(str);
    }

    public static int getInt(@NonNull String str) {
        return getInt(str, getDefaultSPUtils());
    }

    public static int getInt(@NonNull String str, int i) {
        return getInt(str, i, getDefaultSPUtils());
    }

    public static int getInt(@NonNull String str, int i, @NonNull SPUtils sPUtils) {
        return sPUtils.getInt(str, i);
    }

    public static int getInt(@NonNull String str, @NonNull SPUtils sPUtils) {
        return sPUtils.getInt(str);
    }

    public static long getLong(@NonNull String str) {
        return getLong(str, getDefaultSPUtils());
    }

    public static long getLong(@NonNull String str, long j) {
        return getLong(str, j, getDefaultSPUtils());
    }

    public static long getLong(@NonNull String str, long j, @NonNull SPUtils sPUtils) {
        return sPUtils.getLong(str, j);
    }

    public static long getLong(@NonNull String str, @NonNull SPUtils sPUtils) {
        return sPUtils.getLong(str);
    }

    public static String getString(@NonNull String str) {
        return getString(str, getDefaultSPUtils());
    }

    public static String getString(@NonNull String str, @NonNull SPUtils sPUtils) {
        return sPUtils.getString(str);
    }

    public static String getString(@NonNull String str, String str2) {
        return getString(str, str2, getDefaultSPUtils());
    }

    public static String getString(@NonNull String str, String str2, @NonNull SPUtils sPUtils) {
        return sPUtils.getString(str, str2);
    }

    public static Set<String> getStringSet(@NonNull String str) {
        return getStringSet(str, getDefaultSPUtils());
    }

    public static Set<String> getStringSet(@NonNull String str, @NonNull SPUtils sPUtils) {
        return sPUtils.getStringSet(str);
    }

    public static Set<String> getStringSet(@NonNull String str, Set<String> set) {
        return getStringSet(str, set, getDefaultSPUtils());
    }

    public static Set<String> getStringSet(@NonNull String str, Set<String> set, @NonNull SPUtils sPUtils) {
        return sPUtils.getStringSet(str, set);
    }

    public static void put(@NonNull String str, float f) {
        put(str, f, getDefaultSPUtils());
    }

    public static void put(@NonNull String str, float f, @NonNull SPUtils sPUtils) {
        sPUtils.put(str, f);
    }

    public static void put(@NonNull String str, float f, boolean z) {
        put(str, f, z, getDefaultSPUtils());
    }

    public static void put(@NonNull String str, float f, boolean z, @NonNull SPUtils sPUtils) {
        sPUtils.put(str, f, z);
    }

    public static void put(@NonNull String str, int i) {
        put(str, i, getDefaultSPUtils());
    }

    public static void put(@NonNull String str, int i, @NonNull SPUtils sPUtils) {
        sPUtils.put(str, i);
    }

    public static void put(@NonNull String str, int i, boolean z) {
        put(str, i, z, getDefaultSPUtils());
    }

    public static void put(@NonNull String str, int i, boolean z, @NonNull SPUtils sPUtils) {
        sPUtils.put(str, i, z);
    }

    public static void put(@NonNull String str, long j) {
        put(str, j, getDefaultSPUtils());
    }

    public static void put(@NonNull String str, long j, @NonNull SPUtils sPUtils) {
        sPUtils.put(str, j);
    }

    public static void put(@NonNull String str, long j, boolean z) {
        put(str, j, z, getDefaultSPUtils());
    }

    public static void put(@NonNull String str, long j, boolean z, @NonNull SPUtils sPUtils) {
        sPUtils.put(str, j, z);
    }

    public static void put(@NonNull String str, String str2) {
        put(str, str2, getDefaultSPUtils());
    }

    public static void put(@NonNull String str, String str2, @NonNull SPUtils sPUtils) {
        sPUtils.put(str, str2);
    }

    public static void put(@NonNull String str, String str2, boolean z) {
        put(str, str2, z, getDefaultSPUtils());
    }

    public static void put(@NonNull String str, String str2, boolean z, @NonNull SPUtils sPUtils) {
        sPUtils.put(str, str2, z);
    }

    public static void put(@NonNull String str, Set<String> set) {
        put(str, set, getDefaultSPUtils());
    }

    public static void put(@NonNull String str, Set<String> set, @NonNull SPUtils sPUtils) {
        sPUtils.put(str, set);
    }

    public static void put(@NonNull String str, Set<String> set, boolean z) {
        put(str, set, z, getDefaultSPUtils());
    }

    public static void put(@NonNull String str, Set<String> set, boolean z, @NonNull SPUtils sPUtils) {
        sPUtils.put(str, set, z);
    }

    public static void put(@NonNull String str, boolean z) {
        put(str, z, getDefaultSPUtils());
    }

    public static void put(@NonNull String str, boolean z, @NonNull SPUtils sPUtils) {
        sPUtils.put(str, z);
    }

    public static void put(@NonNull String str, boolean z, boolean z2) {
        put(str, z, z2, getDefaultSPUtils());
    }

    public static void put(@NonNull String str, boolean z, boolean z2, @NonNull SPUtils sPUtils) {
        sPUtils.put(str, z, z2);
    }

    public static void remove(@NonNull String str) {
        remove(str, getDefaultSPUtils());
    }

    public static void remove(@NonNull String str, @NonNull SPUtils sPUtils) {
        sPUtils.remove(str);
    }

    public static void remove(@NonNull String str, boolean z) {
        remove(str, z, getDefaultSPUtils());
    }

    public static void remove(@NonNull String str, boolean z, @NonNull SPUtils sPUtils) {
        sPUtils.remove(str, z);
    }

    public static void setDefaultSPUtils(SPUtils sPUtils) {
        sDefaultSPUtils = sPUtils;
    }
}
