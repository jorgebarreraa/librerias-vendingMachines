package com.yj.coffeemachines.app.utils;

import android.content.Context;
import android.text.TextUtils;
import com.blankj.utilcode.util.GsonUtils;
import com.tencent.mmkv.MMKV;
import com.tencent.mmkv.MMKVLogLevel;
import com.yj.coffeemachines.Constants;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/* loaded from: classes.dex */
public class KvUtil {
    private static volatile KvUtil instance;
    private String cryptKey;
    private boolean encrypt;
    private Context mContext;

    private KvUtil() {
    }

    public static KvUtil getInstance() {
        if (instance == null) {
            synchronized (KvUtil.class) {
                if (instance == null) {
                    instance = new KvUtil();
                }
            }
        }
        return instance;
    }

    public void clear() {
        getMMKV().clear();
    }

    public void clear(String str) {
        getMMKV(str).clear();
    }

    public boolean contains(String str) {
        return getMMKV().contains(str);
    }

    public boolean contains(String str, String str2) {
        return getMMKV(str).contains(str2);
    }

    public <T> void deleteAppointObjectData(Class<T> cls) {
        getMMKV().remove(cls.getName()).apply();
    }

    public <T> void deleteAppointObjectData(String str, Class<T> cls) {
        getMMKV(str).remove(cls.getName()).apply();
    }

    public boolean getBoolean(String str) {
        return getMMKV().getBoolean(str, false);
    }

    public boolean getBoolean(String str, String str2, boolean z) {
        return getMMKV(str).getBoolean(str2, z);
    }

    public boolean getBoolean(String str, boolean z) {
        return getMMKV().getBoolean(str, z);
    }

    public byte[] getBytes(String str) {
        return getMMKV().decodeBytes(str);
    }

    public byte[] getBytes(String str, String str2, byte[] bArr) {
        return getMMKV(str).decodeBytes(str2, bArr);
    }

    public byte[] getBytes(String str, byte[] bArr) {
        return getMMKV().decodeBytes(str, bArr);
    }

    public double getDouble(String str) {
        return getMMKV().decodeDouble(str);
    }

    public double getDouble(String str, double d) {
        return getMMKV().decodeDouble(str, d);
    }

    public double getDouble(String str, String str2, double d) {
        return getMMKV(str).decodeDouble(str2, d);
    }

    public float getFloat(String str) {
        return getMMKV().getFloat(str, 0.0f);
    }

    public float getFloat(String str, float f) {
        return getMMKV().getFloat(str, f);
    }

    public float getFloat(String str, String str2, float f) {
        return getMMKV(str).getFloat(str2, f);
    }

    public int getInt(String str) {
        return getMMKV().getInt(str, 0);
    }

    public int getInt(String str, int i) {
        return getMMKV().getInt(str, i);
    }

    public int getInt(String str, String str2, int i) {
        return getMMKV(str).getInt(str2, i);
    }

    public long getLong(String str) {
        return getMMKV().getLong(str, 0L);
    }

    public long getLong(String str, long j) {
        return getMMKV().getLong(str, j);
    }

    public long getLong(String str, String str2, long j) {
        return getMMKV(str).getLong(str2, j);
    }

    public MMKV getMMKV() {
        return getMMKV(null);
    }

    public MMKV getMMKV(String str) {
        if (TextUtils.isEmpty(str)) {
            return MMKV.defaultMMKV(2, this.encrypt ? this.cryptKey : null);
        }
        return this.encrypt ? MMKV.mmkvWithID(str, 2, this.cryptKey) : MMKV.mmkvWithID(str, 2);
    }

    public <T> T getObjectData(Class<T> cls) {
        String string = getMMKV().getString(cls.getName(), "");
        if (TextUtils.isEmpty(string)) {
            return null;
        }
        return (T) GsonUtils.fromJson(string, (Class) cls);
    }

    public <T> T getObjectData(String str, Class<T> cls) {
        String string = getMMKV(str).getString(cls.getName(), "");
        if (TextUtils.isEmpty(string)) {
            return null;
        }
        return (T) GsonUtils.fromJson(string, (Class) cls);
    }

    public String getString(String str) {
        return getMMKV().getString(str, "");
    }

    public String getString(String str, String str2) {
        return getMMKV().getString(str, str2);
    }

    public String getString(String str, String str2, String str3) {
        return getMMKV(str).getString(str2, str3);
    }

    public Set<String> getStringSet(String str) {
        return getMMKV().getStringSet(str, new HashSet());
    }

    public Set<String> getStringSet(String str, String str2, Set<String> set) {
        return getMMKV(str).getStringSet(str2, set);
    }

    public Set<String> getStringSet(String str, Set<String> set) {
        return getMMKV().getStringSet(str, set);
    }

    public void init(Context context) {
        this.mContext = context;
        MMKV.initialize(context, Constants.defaultSettingPath);
    }

    public void putBoolean(String str, String str2, boolean z) {
        getMMKV(str).putBoolean(str2, z);
    }

    public void putBoolean(String str, boolean z) {
        getMMKV().putBoolean(str, z);
    }

    public void putByte(String str, byte[] bArr) {
        getMMKV().encode(str, bArr);
    }

    public void putDouble(String str, double d) {
        getMMKV().encode(str, d);
    }

    public void putFloat(String str, float f) {
        getMMKV().putFloat(str, f);
    }

    public void putFloat(String str, String str2, float f) {
        getMMKV(str).putFloat(str2, f);
    }

    public void putInt(String str, int i) {
        getMMKV().putInt(str, i);
    }

    public void putInt(String str, String str2, int i) {
        getMMKV(str).putInt(str2, i);
    }

    public void putLong(String str, long j) {
        getMMKV().putLong(str, j);
    }

    public void putLong(String str, String str2, long j) {
        getMMKV(str).putLong(str2, j);
    }

    public void putObjectData(Serializable serializable) {
        getMMKV().putString(serializable.getClass().getName(), GsonUtils.toJson(serializable)).apply();
    }

    public void putObjectData(String str, Serializable serializable) {
        getMMKV(str).putString(serializable.getClass().getName(), GsonUtils.toJson(serializable)).apply();
    }

    public void putString(String str, String str2) {
        getMMKV().putString(str, str2);
    }

    public void putString(String str, String str2, String str3) {
        getMMKV(str).putString(str2, str3);
    }

    public void putStringSet(String str, String str2, Set<String> set) {
        getMMKV(str).putStringSet(str2, set);
    }

    public void putStringSet(String str, Set<String> set) {
        getMMKV().putStringSet(str, set);
    }

    public void remove(String str) {
        getMMKV().remove(str);
    }

    public void remove(String str, String str2) {
        getMMKV(str).remove(str2);
    }

    public void setEncrypt(boolean z, String str) {
        this.encrypt = z;
        this.cryptKey = str;
    }

    public void setLogLevel(MMKVLogLevel mMKVLogLevel) {
        MMKV.setLogLevel(mMKVLogLevel);
    }
}
