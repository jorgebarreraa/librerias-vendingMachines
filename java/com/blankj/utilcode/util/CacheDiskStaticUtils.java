package com.blankj.utilcode.util;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.io.Serializable;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes.dex */
public final class CacheDiskStaticUtils {
    private static CacheDiskUtils sDefaultCacheDiskUtils;

    public static boolean clear() {
        return clear(getDefaultCacheDiskUtils());
    }

    public static boolean clear(@NonNull CacheDiskUtils cacheDiskUtils) {
        return cacheDiskUtils.clear();
    }

    public static Bitmap getBitmap(@NonNull String str) {
        return getBitmap(str, getDefaultCacheDiskUtils());
    }

    public static Bitmap getBitmap(@NonNull String str, @Nullable Bitmap bitmap) {
        return getBitmap(str, bitmap, getDefaultCacheDiskUtils());
    }

    public static Bitmap getBitmap(@NonNull String str, @Nullable Bitmap bitmap, @NonNull CacheDiskUtils cacheDiskUtils) {
        return cacheDiskUtils.getBitmap(str, bitmap);
    }

    public static Bitmap getBitmap(@NonNull String str, @NonNull CacheDiskUtils cacheDiskUtils) {
        return cacheDiskUtils.getBitmap(str);
    }

    public static byte[] getBytes(@NonNull String str) {
        return getBytes(str, getDefaultCacheDiskUtils());
    }

    public static byte[] getBytes(@NonNull String str, @NonNull CacheDiskUtils cacheDiskUtils) {
        return cacheDiskUtils.getBytes(str);
    }

    public static byte[] getBytes(@NonNull String str, @Nullable byte[] bArr) {
        return getBytes(str, bArr, getDefaultCacheDiskUtils());
    }

    public static byte[] getBytes(@NonNull String str, @Nullable byte[] bArr, @NonNull CacheDiskUtils cacheDiskUtils) {
        return cacheDiskUtils.getBytes(str, bArr);
    }

    public static int getCacheCount() {
        return getCacheCount(getDefaultCacheDiskUtils());
    }

    public static int getCacheCount(@NonNull CacheDiskUtils cacheDiskUtils) {
        return cacheDiskUtils.getCacheCount();
    }

    public static long getCacheSize() {
        return getCacheSize(getDefaultCacheDiskUtils());
    }

    public static long getCacheSize(@NonNull CacheDiskUtils cacheDiskUtils) {
        return cacheDiskUtils.getCacheSize();
    }

    @NonNull
    private static CacheDiskUtils getDefaultCacheDiskUtils() {
        CacheDiskUtils cacheDiskUtils = sDefaultCacheDiskUtils;
        return cacheDiskUtils != null ? cacheDiskUtils : CacheDiskUtils.getInstance();
    }

    public static Drawable getDrawable(@NonNull String str) {
        return getDrawable(str, getDefaultCacheDiskUtils());
    }

    public static Drawable getDrawable(@NonNull String str, @Nullable Drawable drawable) {
        return getDrawable(str, drawable, getDefaultCacheDiskUtils());
    }

    public static Drawable getDrawable(@NonNull String str, @Nullable Drawable drawable, @NonNull CacheDiskUtils cacheDiskUtils) {
        return cacheDiskUtils.getDrawable(str, drawable);
    }

    public static Drawable getDrawable(@NonNull String str, @NonNull CacheDiskUtils cacheDiskUtils) {
        return cacheDiskUtils.getDrawable(str);
    }

    public static JSONArray getJSONArray(@NonNull String str) {
        return getJSONArray(str, getDefaultCacheDiskUtils());
    }

    public static JSONArray getJSONArray(@NonNull String str, @NonNull CacheDiskUtils cacheDiskUtils) {
        return cacheDiskUtils.getJSONArray(str);
    }

    public static JSONArray getJSONArray(@NonNull String str, @Nullable JSONArray jSONArray) {
        return getJSONArray(str, jSONArray, getDefaultCacheDiskUtils());
    }

    public static JSONArray getJSONArray(@NonNull String str, @Nullable JSONArray jSONArray, @NonNull CacheDiskUtils cacheDiskUtils) {
        return cacheDiskUtils.getJSONArray(str, jSONArray);
    }

    public static JSONObject getJSONObject(@NonNull String str) {
        return getJSONObject(str, getDefaultCacheDiskUtils());
    }

    public static JSONObject getJSONObject(@NonNull String str, @NonNull CacheDiskUtils cacheDiskUtils) {
        return cacheDiskUtils.getJSONObject(str);
    }

    public static JSONObject getJSONObject(@NonNull String str, @Nullable JSONObject jSONObject) {
        return getJSONObject(str, jSONObject, getDefaultCacheDiskUtils());
    }

    public static JSONObject getJSONObject(@NonNull String str, @Nullable JSONObject jSONObject, @NonNull CacheDiskUtils cacheDiskUtils) {
        return cacheDiskUtils.getJSONObject(str, jSONObject);
    }

    public static <T> T getParcelable(@NonNull String str, @NonNull Parcelable.Creator<T> creator) {
        return (T) getParcelable(str, (Parcelable.Creator) creator, getDefaultCacheDiskUtils());
    }

    public static <T> T getParcelable(@NonNull String str, @NonNull Parcelable.Creator<T> creator, @NonNull CacheDiskUtils cacheDiskUtils) {
        return (T) cacheDiskUtils.getParcelable(str, creator);
    }

    public static <T> T getParcelable(@NonNull String str, @NonNull Parcelable.Creator<T> creator, @Nullable T t) {
        return (T) getParcelable(str, creator, t, getDefaultCacheDiskUtils());
    }

    public static <T> T getParcelable(@NonNull String str, @NonNull Parcelable.Creator<T> creator, @Nullable T t, @NonNull CacheDiskUtils cacheDiskUtils) {
        return (T) cacheDiskUtils.getParcelable(str, creator, t);
    }

    public static Object getSerializable(@NonNull String str) {
        return getSerializable(str, getDefaultCacheDiskUtils());
    }

    public static Object getSerializable(@NonNull String str, @NonNull CacheDiskUtils cacheDiskUtils) {
        return cacheDiskUtils.getSerializable(str);
    }

    public static Object getSerializable(@NonNull String str, @Nullable Object obj) {
        return getSerializable(str, obj, getDefaultCacheDiskUtils());
    }

    public static Object getSerializable(@NonNull String str, @Nullable Object obj, @NonNull CacheDiskUtils cacheDiskUtils) {
        return cacheDiskUtils.getSerializable(str, obj);
    }

    public static String getString(@NonNull String str) {
        return getString(str, getDefaultCacheDiskUtils());
    }

    public static String getString(@NonNull String str, @NonNull CacheDiskUtils cacheDiskUtils) {
        return cacheDiskUtils.getString(str);
    }

    public static String getString(@NonNull String str, @Nullable String str2) {
        return getString(str, str2, getDefaultCacheDiskUtils());
    }

    public static String getString(@NonNull String str, @Nullable String str2, @NonNull CacheDiskUtils cacheDiskUtils) {
        return cacheDiskUtils.getString(str, str2);
    }

    public static void put(@NonNull String str, @Nullable Bitmap bitmap) {
        put(str, bitmap, getDefaultCacheDiskUtils());
    }

    public static void put(@NonNull String str, @Nullable Bitmap bitmap, int i) {
        put(str, bitmap, i, getDefaultCacheDiskUtils());
    }

    public static void put(@NonNull String str, @Nullable Bitmap bitmap, int i, @NonNull CacheDiskUtils cacheDiskUtils) {
        cacheDiskUtils.put(str, bitmap, i);
    }

    public static void put(@NonNull String str, @Nullable Bitmap bitmap, @NonNull CacheDiskUtils cacheDiskUtils) {
        cacheDiskUtils.put(str, bitmap);
    }

    public static void put(@NonNull String str, @Nullable Drawable drawable) {
        put(str, drawable, getDefaultCacheDiskUtils());
    }

    public static void put(@NonNull String str, @Nullable Drawable drawable, int i) {
        put(str, drawable, i, getDefaultCacheDiskUtils());
    }

    public static void put(@NonNull String str, @Nullable Drawable drawable, int i, @NonNull CacheDiskUtils cacheDiskUtils) {
        cacheDiskUtils.put(str, drawable, i);
    }

    public static void put(@NonNull String str, @Nullable Drawable drawable, @NonNull CacheDiskUtils cacheDiskUtils) {
        cacheDiskUtils.put(str, drawable);
    }

    public static void put(@NonNull String str, @Nullable Parcelable parcelable) {
        put(str, parcelable, getDefaultCacheDiskUtils());
    }

    public static void put(@NonNull String str, @Nullable Parcelable parcelable, int i) {
        put(str, parcelable, i, getDefaultCacheDiskUtils());
    }

    public static void put(@NonNull String str, @Nullable Parcelable parcelable, int i, @NonNull CacheDiskUtils cacheDiskUtils) {
        cacheDiskUtils.put(str, parcelable, i);
    }

    public static void put(@NonNull String str, @Nullable Parcelable parcelable, @NonNull CacheDiskUtils cacheDiskUtils) {
        cacheDiskUtils.put(str, parcelable);
    }

    public static void put(@NonNull String str, @Nullable Serializable serializable) {
        put(str, serializable, getDefaultCacheDiskUtils());
    }

    public static void put(@NonNull String str, @Nullable Serializable serializable, int i) {
        put(str, serializable, i, getDefaultCacheDiskUtils());
    }

    public static void put(@NonNull String str, @Nullable Serializable serializable, int i, @NonNull CacheDiskUtils cacheDiskUtils) {
        cacheDiskUtils.put(str, serializable, i);
    }

    public static void put(@NonNull String str, @Nullable Serializable serializable, @NonNull CacheDiskUtils cacheDiskUtils) {
        cacheDiskUtils.put(str, serializable);
    }

    public static void put(@NonNull String str, @Nullable String str2) {
        put(str, str2, getDefaultCacheDiskUtils());
    }

    public static void put(@NonNull String str, @Nullable String str2, int i) {
        put(str, str2, i, getDefaultCacheDiskUtils());
    }

    public static void put(@NonNull String str, @Nullable String str2, int i, @NonNull CacheDiskUtils cacheDiskUtils) {
        cacheDiskUtils.put(str, str2, i);
    }

    public static void put(@NonNull String str, @Nullable String str2, @NonNull CacheDiskUtils cacheDiskUtils) {
        cacheDiskUtils.put(str, str2);
    }

    public static void put(@NonNull String str, @Nullable JSONArray jSONArray) {
        put(str, jSONArray, getDefaultCacheDiskUtils());
    }

    public static void put(@NonNull String str, @Nullable JSONArray jSONArray, int i) {
        put(str, jSONArray, i, getDefaultCacheDiskUtils());
    }

    public static void put(@NonNull String str, @Nullable JSONArray jSONArray, int i, @NonNull CacheDiskUtils cacheDiskUtils) {
        cacheDiskUtils.put(str, jSONArray, i);
    }

    public static void put(@NonNull String str, @Nullable JSONArray jSONArray, @NonNull CacheDiskUtils cacheDiskUtils) {
        cacheDiskUtils.put(str, jSONArray);
    }

    public static void put(@NonNull String str, @Nullable JSONObject jSONObject) {
        put(str, jSONObject, getDefaultCacheDiskUtils());
    }

    public static void put(@NonNull String str, @Nullable JSONObject jSONObject, int i) {
        put(str, jSONObject, i, getDefaultCacheDiskUtils());
    }

    public static void put(@NonNull String str, @Nullable JSONObject jSONObject, int i, @NonNull CacheDiskUtils cacheDiskUtils) {
        cacheDiskUtils.put(str, jSONObject, i);
    }

    public static void put(@NonNull String str, @Nullable JSONObject jSONObject, @NonNull CacheDiskUtils cacheDiskUtils) {
        cacheDiskUtils.put(str, jSONObject);
    }

    public static void put(@NonNull String str, @Nullable byte[] bArr) {
        put(str, bArr, getDefaultCacheDiskUtils());
    }

    public static void put(@NonNull String str, @Nullable byte[] bArr, int i) {
        put(str, bArr, i, getDefaultCacheDiskUtils());
    }

    public static void put(@NonNull String str, @Nullable byte[] bArr, int i, @NonNull CacheDiskUtils cacheDiskUtils) {
        cacheDiskUtils.put(str, bArr, i);
    }

    public static void put(@NonNull String str, @Nullable byte[] bArr, @NonNull CacheDiskUtils cacheDiskUtils) {
        cacheDiskUtils.put(str, bArr);
    }

    public static boolean remove(@NonNull String str) {
        return remove(str, getDefaultCacheDiskUtils());
    }

    public static boolean remove(@NonNull String str, @NonNull CacheDiskUtils cacheDiskUtils) {
        return cacheDiskUtils.remove(str);
    }

    public static void setDefaultCacheDiskUtils(@Nullable CacheDiskUtils cacheDiskUtils) {
        sDefaultCacheDiskUtils = cacheDiskUtils;
    }
}
