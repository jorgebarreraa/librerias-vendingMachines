package com.blankj.utilcode.util;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import androidx.annotation.NonNull;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@SuppressLint({"ApplySharedPref"})
/* loaded from: classes.dex */
public final class SPUtils {
    private static final Map<String, SPUtils> SP_UTILS_MAP = new HashMap();
    private SharedPreferences sp;

    private SPUtils(String str) {
        this.sp = Utils.getApp().getSharedPreferences(str, 0);
    }

    private SPUtils(String str, int i) {
        this.sp = Utils.getApp().getSharedPreferences(str, i);
    }

    public static SPUtils getInstance() {
        return getInstance("", 0);
    }

    public static SPUtils getInstance(int i) {
        return getInstance("", i);
    }

    public static SPUtils getInstance(String str) {
        return getInstance(str, 0);
    }

    public static SPUtils getInstance(String str, int i) {
        if (isSpace(str)) {
            str = "spUtils";
        }
        SPUtils sPUtils = SP_UTILS_MAP.get(str);
        if (sPUtils == null) {
            synchronized (SPUtils.class) {
                sPUtils = SP_UTILS_MAP.get(str);
                if (sPUtils == null) {
                    sPUtils = new SPUtils(str, i);
                    SP_UTILS_MAP.put(str, sPUtils);
                }
            }
        }
        return sPUtils;
    }

    private static boolean isSpace(String str) {
        if (str == null) {
            return true;
        }
        int length = str.length();
        for (int i = 0; i < length; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public void clear() {
        clear(false);
    }

    public void clear(boolean z) {
        if (z) {
            this.sp.edit().clear().commit();
        } else {
            this.sp.edit().clear().apply();
        }
    }

    public boolean contains(@NonNull String str) {
        return this.sp.contains(str);
    }

    public Map<String, ?> getAll() {
        return this.sp.getAll();
    }

    public boolean getBoolean(@NonNull String str) {
        return getBoolean(str, false);
    }

    public boolean getBoolean(@NonNull String str, boolean z) {
        return this.sp.getBoolean(str, z);
    }

    public float getFloat(@NonNull String str) {
        return getFloat(str, -1.0f);
    }

    public float getFloat(@NonNull String str, float f) {
        return this.sp.getFloat(str, f);
    }

    public int getInt(@NonNull String str) {
        return getInt(str, -1);
    }

    public int getInt(@NonNull String str, int i) {
        return this.sp.getInt(str, i);
    }

    public long getLong(@NonNull String str) {
        return getLong(str, -1L);
    }

    public long getLong(@NonNull String str, long j) {
        return this.sp.getLong(str, j);
    }

    public String getString(@NonNull String str) {
        return getString(str, "");
    }

    public String getString(@NonNull String str, String str2) {
        return this.sp.getString(str, str2);
    }

    public Set<String> getStringSet(@NonNull String str) {
        return getStringSet(str, Collections.emptySet());
    }

    public Set<String> getStringSet(@NonNull String str, Set<String> set) {
        return this.sp.getStringSet(str, set);
    }

    public void put(@NonNull String str, float f) {
        put(str, f, false);
    }

    public void put(@NonNull String str, float f, boolean z) {
        if (z) {
            this.sp.edit().putFloat(str, f).commit();
        } else {
            this.sp.edit().putFloat(str, f).apply();
        }
    }

    public void put(@NonNull String str, int i) {
        put(str, i, false);
    }

    public void put(@NonNull String str, int i, boolean z) {
        if (z) {
            this.sp.edit().putInt(str, i).commit();
        } else {
            this.sp.edit().putInt(str, i).apply();
        }
    }

    public void put(@NonNull String str, long j) {
        put(str, j, false);
    }

    public void put(@NonNull String str, long j, boolean z) {
        if (z) {
            this.sp.edit().putLong(str, j).commit();
        } else {
            this.sp.edit().putLong(str, j).apply();
        }
    }

    public void put(@NonNull String str, String str2) {
        put(str, str2, false);
    }

    public void put(@NonNull String str, String str2, boolean z) {
        if (z) {
            this.sp.edit().putString(str, str2).commit();
        } else {
            this.sp.edit().putString(str, str2).apply();
        }
    }

    public void put(@NonNull String str, Set<String> set) {
        put(str, set, false);
    }

    public void put(@NonNull String str, Set<String> set, boolean z) {
        if (z) {
            this.sp.edit().putStringSet(str, set).commit();
        } else {
            this.sp.edit().putStringSet(str, set).apply();
        }
    }

    public void put(@NonNull String str, boolean z) {
        put(str, z, false);
    }

    public void put(@NonNull String str, boolean z, boolean z2) {
        if (z2) {
            this.sp.edit().putBoolean(str, z).commit();
        } else {
            this.sp.edit().putBoolean(str, z).apply();
        }
    }

    public void remove(@NonNull String str) {
        remove(str, false);
    }

    public void remove(@NonNull String str, boolean z) {
        if (z) {
            this.sp.edit().remove(str).commit();
        } else {
            this.sp.edit().remove(str).apply();
        }
    }
}
