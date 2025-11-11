package com.innohi;

/* loaded from: classes.dex */
public class PropertyUtil {
    private PropertyUtil() {
    }

    public static String get(String str, String str2) {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            return (String) cls.getMethod("get", String.class, String.class).invoke(cls, str, str2);
        } catch (Exception e) {
            e.printStackTrace();
            return str2;
        }
    }

    public static boolean getBoolean(String str, boolean z) {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            return ((Boolean) cls.getMethod("getBoolean", String.class, Boolean.TYPE).invoke(cls, str, Boolean.valueOf(z))).booleanValue();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void set(String str, String str2) {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            cls.getMethod("set", String.class, String.class).invoke(cls, str, str2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
