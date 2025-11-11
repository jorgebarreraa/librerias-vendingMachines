package com.blankj.utilcode.util;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import java.io.Serializable;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes.dex */
public final class CacheDoubleStaticUtils {
    private static CacheDoubleUtils sDefaultCacheDoubleUtils;

    public static void clear() {
        clear(getDefaultCacheDoubleUtils());
    }

    public static void clear(@NonNull CacheDoubleUtils cacheDoubleUtils) {
        cacheDoubleUtils.clear();
    }

    public static Bitmap getBitmap(@NonNull String str) {
        return getBitmap(str, getDefaultCacheDoubleUtils());
    }

    public static Bitmap getBitmap(@NonNull String str, Bitmap bitmap) {
        return getBitmap(str, bitmap, getDefaultCacheDoubleUtils());
    }

    public static Bitmap getBitmap(@NonNull String str, Bitmap bitmap, @NonNull CacheDoubleUtils cacheDoubleUtils) {
        return cacheDoubleUtils.getBitmap(str, bitmap);
    }

    public static Bitmap getBitmap(@NonNull String str, @NonNull CacheDoubleUtils cacheDoubleUtils) {
        return cacheDoubleUtils.getBitmap(str);
    }

    public static byte[] getBytes(@NonNull String str) {
        return getBytes(str, getDefaultCacheDoubleUtils());
    }

    public static byte[] getBytes(@NonNull String str, @NonNull CacheDoubleUtils cacheDoubleUtils) {
        return cacheDoubleUtils.getBytes(str);
    }

    public static byte[] getBytes(@NonNull String str, byte[] bArr) {
        return getBytes(str, bArr, getDefaultCacheDoubleUtils());
    }

    public static byte[] getBytes(@NonNull String str, byte[] bArr, @NonNull CacheDoubleUtils cacheDoubleUtils) {
        return cacheDoubleUtils.getBytes(str, bArr);
    }

    public static int getCacheDiskCount() {
        return getCacheDiskCount(getDefaultCacheDoubleUtils());
    }

    public static int getCacheDiskCount(@NonNull CacheDoubleUtils cacheDoubleUtils) {
        return cacheDoubleUtils.getCacheDiskCount();
    }

    public static long getCacheDiskSize() {
        return getCacheDiskSize(getDefaultCacheDoubleUtils());
    }

    public static long getCacheDiskSize(@NonNull CacheDoubleUtils cacheDoubleUtils) {
        return cacheDoubleUtils.getCacheDiskSize();
    }

    public static int getCacheMemoryCount() {
        return getCacheMemoryCount(getDefaultCacheDoubleUtils());
    }

    public static int getCacheMemoryCount(@NonNull CacheDoubleUtils cacheDoubleUtils) {
        return cacheDoubleUtils.getCacheMemoryCount();
    }

    private static CacheDoubleUtils getDefaultCacheDoubleUtils() {
        CacheDoubleUtils cacheDoubleUtils = sDefaultCacheDoubleUtils;
        return cacheDoubleUtils != null ? cacheDoubleUtils : CacheDoubleUtils.getInstance();
    }

    public static Drawable getDrawable(@NonNull String str) {
        return getDrawable(str, getDefaultCacheDoubleUtils());
    }

    public static Drawable getDrawable(@NonNull String str, Drawable drawable) {
        return getDrawable(str, drawable, getDefaultCacheDoubleUtils());
    }

    public static Drawable getDrawable(@NonNull String str, Drawable drawable, @NonNull CacheDoubleUtils cacheDoubleUtils) {
        return cacheDoubleUtils.getDrawable(str, drawable);
    }

    public static Drawable getDrawable(@NonNull String str, @NonNull CacheDoubleUtils cacheDoubleUtils) {
        return cacheDoubleUtils.getDrawable(str);
    }

    public static JSONArray getJSONArray(@NonNull String str) {
        return getJSONArray(str, getDefaultCacheDoubleUtils());
    }

    public static JSONArray getJSONArray(@NonNull String str, @NonNull CacheDoubleUtils cacheDoubleUtils) {
        return cacheDoubleUtils.getJSONArray(str);
    }

    public static JSONArray getJSONArray(@NonNull String str, JSONArray jSONArray) {
        return getJSONArray(str, jSONArray, getDefaultCacheDoubleUtils());
    }

    public static JSONArray getJSONArray(@NonNull String str, JSONArray jSONArray, @NonNull CacheDoubleUtils cacheDoubleUtils) {
        return cacheDoubleUtils.getJSONArray(str, jSONArray);
    }

    public static JSONObject getJSONObject(@NonNull String str) {
        return getJSONObject(str, getDefaultCacheDoubleUtils());
    }

    public static JSONObject getJSONObject(@NonNull String str, @NonNull CacheDoubleUtils cacheDoubleUtils) {
        return cacheDoubleUtils.getJSONObject(str);
    }

    public static JSONObject getJSONObject(@NonNull String str, JSONObject jSONObject) {
        return getJSONObject(str, jSONObject, getDefaultCacheDoubleUtils());
    }

    public static JSONObject getJSONObject(@NonNull String str, JSONObject jSONObject, @NonNull CacheDoubleUtils cacheDoubleUtils) {
        return cacheDoubleUtils.getJSONObject(str, jSONObject);
    }

    public static <T> T getParcelable(@NonNull String str, @NonNull Parcelable.Creator<T> creator) {
        return (T) getParcelable(str, (Parcelable.Creator) creator, getDefaultCacheDoubleUtils());
    }

    public static <T> T getParcelable(@NonNull String str, @NonNull Parcelable.Creator<T> creator, @NonNull CacheDoubleUtils cacheDoubleUtils) {
        return (T) cacheDoubleUtils.getParcelable(str, creator);
    }

    public static <T> T getParcelable(@NonNull String str, @NonNull Parcelable.Creator<T> creator, T t) {
        return (T) getParcelable(str, creator, t, getDefaultCacheDoubleUtils());
    }

    public static <T> T getParcelable(@NonNull String str, @NonNull Parcelable.Creator<T> creator, T t, @NonNull CacheDoubleUtils cacheDoubleUtils) {
        return (T) cacheDoubleUtils.getParcelable(str, creator, t);
    }

    public static Object getSerializable(@NonNull String str) {
        return getSerializable(str, getDefaultCacheDoubleUtils());
    }

    public static Object getSerializable(@NonNull String str, @NonNull CacheDoubleUtils cacheDoubleUtils) {
        return cacheDoubleUtils.getSerializable(str);
    }

    public static Object getSerializable(@NonNull String str, Object obj) {
        return getSerializable(str, obj, getDefaultCacheDoubleUtils());
    }

    public static Object getSerializable(@NonNull String str, Object obj, @NonNull CacheDoubleUtils cacheDoubleUtils) {
        return cacheDoubleUtils.getSerializable(str, obj);
    }

    public static String getString(@NonNull String str) {
        return getString(str, getDefaultCacheDoubleUtils());
    }

    public static String getString(@NonNull String str, @NonNull CacheDoubleUtils cacheDoubleUtils) {
        return cacheDoubleUtils.getString(str);
    }

    public static String getString(@NonNull String str, String str2) {
        return getString(str, str2, getDefaultCacheDoubleUtils());
    }

    public static String getString(@NonNull String str, String str2, @NonNull CacheDoubleUtils cacheDoubleUtils) {
        return cacheDoubleUtils.getString(str, str2);
    }

    public static void put(@NonNull String str, Bitmap bitmap) {
        put(str, bitmap, getDefaultCacheDoubleUtils());
    }

    public static void put(@NonNull String str, Bitmap bitmap, int i) {
        put(str, bitmap, i, getDefaultCacheDoubleUtils());
    }

    public static void put(@NonNull String str, Bitmap bitmap, int i, @NonNull CacheDoubleUtils cacheDoubleUtils) {
        cacheDoubleUtils.put(str, bitmap, i);
    }

    public static void put(@NonNull String str, Bitmap bitmap, @NonNull CacheDoubleUtils cacheDoubleUtils) {
        cacheDoubleUtils.put(str, bitmap);
    }

    public static void put(@NonNull String str, Drawable drawable) {
        put(str, drawable, getDefaultCacheDoubleUtils());
    }

    public static void put(@NonNull String str, Drawable drawable, int i) {
        put(str, drawable, i, getDefaultCacheDoubleUtils());
    }

    public static void put(@NonNull String str, Drawable drawable, int i, @NonNull CacheDoubleUtils cacheDoubleUtils) {
        cacheDoubleUtils.put(str, drawable, i);
    }

    public static void put(@NonNull String str, Drawable drawable, @NonNull CacheDoubleUtils cacheDoubleUtils) {
        cacheDoubleUtils.put(str, drawable);
    }

    public static void put(@NonNull String str, Parcelable parcelable) {
        put(str, parcelable, getDefaultCacheDoubleUtils());
    }

    public static void put(@NonNull String str, Parcelable parcelable, int i) {
        put(str, parcelable, i, getDefaultCacheDoubleUtils());
    }

    public static void put(@NonNull String str, Parcelable parcelable, int i, @NonNull CacheDoubleUtils cacheDoubleUtils) {
        cacheDoubleUtils.put(str, parcelable, i);
    }

    public static void put(@NonNull String str, Parcelable parcelable, @NonNull CacheDoubleUtils cacheDoubleUtils) {
        cacheDoubleUtils.put(str, parcelable);
    }

    public static void put(@NonNull String str, Serializable serializable) {
        put(str, serializable, getDefaultCacheDoubleUtils());
    }

    public static void put(@NonNull String str, Serializable serializable, int i) {
        put(str, serializable, i, getDefaultCacheDoubleUtils());
    }

    public static void put(@NonNull String str, Serializable serializable, int i, @NonNull CacheDoubleUtils cacheDoubleUtils) {
        cacheDoubleUtils.put(str, serializable, i);
    }

    public static void put(@NonNull String str, Serializable serializable, @NonNull CacheDoubleUtils cacheDoubleUtils) {
        cacheDoubleUtils.put(str, serializable);
    }

    public static void put(@NonNull String str, String str2) {
        put(str, str2, getDefaultCacheDoubleUtils());
    }

    public static void put(@NonNull String str, String str2, int i) {
        put(str, str2, i, getDefaultCacheDoubleUtils());
    }

    public static void put(@NonNull String str, String str2, int i, @NonNull CacheDoubleUtils cacheDoubleUtils) {
        cacheDoubleUtils.put(str, str2, i);
    }

    public static void put(@NonNull String str, String str2, @NonNull CacheDoubleUtils cacheDoubleUtils) {
        cacheDoubleUtils.put(str, str2);
    }

    public static void put(@NonNull String str, JSONArray jSONArray) {
        put(str, jSONArray, getDefaultCacheDoubleUtils());
    }

    public static void put(@NonNull String str, JSONArray jSONArray, int i) {
        put(str, jSONArray, i, getDefaultCacheDoubleUtils());
    }

    public static void put(@NonNull String str, JSONArray jSONArray, int i, @NonNull CacheDoubleUtils cacheDoubleUtils) {
        cacheDoubleUtils.put(str, jSONArray, i);
    }

    public static void put(@NonNull String str, JSONArray jSONArray, @NonNull CacheDoubleUtils cacheDoubleUtils) {
        cacheDoubleUtils.put(str, jSONArray);
    }

    public static void put(@NonNull String str, JSONObject jSONObject) {
        put(str, jSONObject, getDefaultCacheDoubleUtils());
    }

    public static void put(@NonNull String str, JSONObject jSONObject, int i) {
        put(str, jSONObject, i, getDefaultCacheDoubleUtils());
    }

    public static void put(@NonNull String str, JSONObject jSONObject, int i, @NonNull CacheDoubleUtils cacheDoubleUtils) {
        cacheDoubleUtils.put(str, jSONObject, i);
    }

    public static void put(@NonNull String str, JSONObject jSONObject, @NonNull CacheDoubleUtils cacheDoubleUtils) {
        cacheDoubleUtils.put(str, jSONObject);
    }

    public static void put(@NonNull String str, byte[] bArr) {
        put(str, bArr, getDefaultCacheDoubleUtils());
    }

    public static void put(@NonNull String str, byte[] bArr, int i) {
        put(str, bArr, i, getDefaultCacheDoubleUtils());
    }

    public static void put(@NonNull String str, byte[] bArr, int i, @NonNull CacheDoubleUtils cacheDoubleUtils) {
        cacheDoubleUtils.put(str, bArr, i);
    }

    public static void put(@NonNull String str, byte[] bArr, @NonNull CacheDoubleUtils cacheDoubleUtils) {
        cacheDoubleUtils.put(str, bArr);
    }

    public static void remove(@NonNull String str) {
        remove(str, getDefaultCacheDoubleUtils());
    }

    public static void remove(@NonNull String str, @NonNull CacheDoubleUtils cacheDoubleUtils) {
        cacheDoubleUtils.remove(str);
    }

    public static void setDefaultCacheDoubleUtils(CacheDoubleUtils cacheDoubleUtils) {
        sDefaultCacheDoubleUtils = cacheDoubleUtils;
    }
}
