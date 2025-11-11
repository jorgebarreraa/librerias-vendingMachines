package com.blankj.utilcode.util;

import android.graphics.Color;
import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.FloatRange;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.core.view.InputDeviceCompat;
import androidx.core.view.ViewCompat;
import org.eclipse.paho.client.mqttv3.MqttTopic;

/* loaded from: classes.dex */
public final class ColorUtils {
    private ColorUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static int getColor(@ColorRes int i) {
        return ContextCompat.getColor(Utils.getApp(), i);
    }

    public static int getRandomColor() {
        return getRandomColor(true);
    }

    public static int getRandomColor(boolean z) {
        return (z ? ((int) (Math.random() * 256.0d)) << 24 : ViewCompat.MEASURED_STATE_MASK) | ((int) (Math.random() * 1.6777216E7d));
    }

    public static String int2ArgbString(@ColorInt int i) {
        String hexString = Integer.toHexString(i);
        while (hexString.length() < 6) {
            hexString = "0" + hexString;
        }
        while (hexString.length() < 8) {
            hexString = "f" + hexString;
        }
        return MqttTopic.MULTI_LEVEL_WILDCARD + hexString;
    }

    public static String int2RgbString(@ColorInt int i) {
        String hexString = Integer.toHexString(i & ViewCompat.MEASURED_SIZE_MASK);
        while (hexString.length() < 6) {
            hexString = "0" + hexString;
        }
        return MqttTopic.MULTI_LEVEL_WILDCARD + hexString;
    }

    public static boolean isLightColor(@ColorInt int i) {
        return ((((double) Color.red(i)) * 0.299d) + (((double) Color.green(i)) * 0.587d)) + (((double) Color.blue(i)) * 0.114d) >= 127.5d;
    }

    public static int setAlphaComponent(@ColorInt int i, @FloatRange(from = 0.0d, to = 1.0d) float f) {
        return (i & ViewCompat.MEASURED_SIZE_MASK) | (((int) ((f * 255.0f) + 0.5f)) << 24);
    }

    public static int setAlphaComponent(@ColorInt int i, @IntRange(from = 0, to = 255) int i2) {
        return (i & ViewCompat.MEASURED_SIZE_MASK) | (i2 << 24);
    }

    public static int setBlueComponent(@ColorInt int i, @FloatRange(from = 0.0d, to = 1.0d) float f) {
        return (i & InputDeviceCompat.SOURCE_ANY) | ((int) ((f * 255.0f) + 0.5f));
    }

    public static int setBlueComponent(@ColorInt int i, @IntRange(from = 0, to = 255) int i2) {
        return (i & InputDeviceCompat.SOURCE_ANY) | i2;
    }

    public static int setGreenComponent(@ColorInt int i, @FloatRange(from = 0.0d, to = 1.0d) float f) {
        return (i & (-65281)) | (((int) ((f * 255.0f) + 0.5f)) << 8);
    }

    public static int setGreenComponent(@ColorInt int i, @IntRange(from = 0, to = 255) int i2) {
        return (i & (-65281)) | (i2 << 8);
    }

    public static int setRedComponent(@ColorInt int i, @FloatRange(from = 0.0d, to = 1.0d) float f) {
        return (i & (-16711681)) | (((int) ((f * 255.0f) + 0.5f)) << 16);
    }

    public static int setRedComponent(@ColorInt int i, @IntRange(from = 0, to = 255) int i2) {
        return (i & (-16711681)) | (i2 << 16);
    }

    public static int string2Int(@NonNull String str) {
        return Color.parseColor(str);
    }
}
