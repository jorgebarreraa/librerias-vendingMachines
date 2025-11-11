package com.jess.arms.utils;

/* loaded from: classes.dex */
public class UrlEncoderUtils {
    private UrlEncoderUtils() {
        throw new IllegalStateException("you can't instantiate me!");
    }

    public static boolean hasUrlEncoded(String str) {
        int i;
        for (int i2 = 0; i2 < str.length(); i2++) {
            if (str.charAt(i2) == '%' && (i = i2 + 2) < str.length()) {
                return isValidHexChar(str.charAt(i2 + 1)) && isValidHexChar(str.charAt(i));
            }
        }
        return false;
    }

    private static boolean isValidHexChar(char c) {
        return ('0' <= c && c <= '9') || ('a' <= c && c <= 'f') || ('A' <= c && c <= 'F');
    }
}
