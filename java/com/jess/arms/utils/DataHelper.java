package com.jess.arms.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;
import android.util.Base64;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/* loaded from: classes.dex */
public class DataHelper {
    public static final String SP_NAME = "config";
    private static SharedPreferences mSharedPreferences;

    private DataHelper() {
        throw new IllegalStateException("you can't instantiate me!");
    }

    public static String bytyToString(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[1024];
        while (inputStream.read(bArr) != -1) {
            byteArrayOutputStream.write(bArr, 0, bArr.length);
        }
        String byteArrayOutputStream2 = byteArrayOutputStream.toString();
        byteArrayOutputStream.close();
        return byteArrayOutputStream2;
    }

    public static void clearShareprefrence(Context context) {
        if (mSharedPreferences == null) {
            mSharedPreferences = context.getSharedPreferences(SP_NAME, 0);
        }
        mSharedPreferences.edit().clear().apply();
    }

    public static boolean deleteDir(File file) {
        if (file == null || !file.isDirectory()) {
            return false;
        }
        for (File file2 : file.listFiles()) {
            if (file2.isFile()) {
                file2.delete();
            } else if (file2.isDirectory()) {
                deleteDir(file2);
            }
        }
        return true;
    }

    public static File getCacheFile(Context context) {
        if (!Environment.getExternalStorageState().equals("mounted")) {
            return context.getCacheDir();
        }
        File externalCacheDir = context.getExternalCacheDir();
        if (externalCacheDir != null) {
            return externalCacheDir;
        }
        File file = new File(getCacheFilePath(context));
        makeDirs(file);
        return file;
    }

    public static String getCacheFilePath(Context context) {
        return Environment.getExternalStorageDirectory().getPath() + context.getPackageName();
    }

    public static <T> T getDeviceData(Context context, String str) {
        if (mSharedPreferences == null) {
            mSharedPreferences = context.getSharedPreferences(SP_NAME, 0);
        }
        String string = mSharedPreferences.getString(str, null);
        if (string == null) {
            return null;
        }
        try {
            return (T) new ObjectInputStream(new ByteArrayInputStream(Base64.decode(string.getBytes(), 0))).readObject();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static long getDirSize(File file) {
        long dirSize;
        long j = 0;
        if (file == null || !file.isDirectory()) {
            return 0L;
        }
        for (File file2 : file.listFiles()) {
            if (file2.isFile()) {
                dirSize = file2.length();
            } else if (file2.isDirectory()) {
                j += file2.length();
                dirSize = getDirSize(file2);
            }
            j += dirSize;
        }
        return j;
    }

    public static int getIntergerSF(Context context, String str) {
        if (mSharedPreferences == null) {
            mSharedPreferences = context.getSharedPreferences(SP_NAME, 0);
        }
        return mSharedPreferences.getInt(str, -1);
    }

    public static String getStringSF(Context context, String str) {
        if (mSharedPreferences == null) {
            mSharedPreferences = context.getSharedPreferences(SP_NAME, 0);
        }
        return mSharedPreferences.getString(str, null);
    }

    public static File makeDirs(File file) {
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }

    public static void removeSF(Context context, String str) {
        if (mSharedPreferences == null) {
            mSharedPreferences = context.getSharedPreferences(SP_NAME, 0);
        }
        mSharedPreferences.edit().remove(str).apply();
    }

    public static <T> boolean saveDeviceData(Context context, String str, T t) {
        if (mSharedPreferences == null) {
            mSharedPreferences = context.getSharedPreferences(SP_NAME, 0);
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            new ObjectOutputStream(byteArrayOutputStream).writeObject(t);
            mSharedPreferences.edit().putString(str, new String(Base64.encode(byteArrayOutputStream.toByteArray(), 0))).apply();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void setIntergerSF(Context context, String str, int i) {
        if (mSharedPreferences == null) {
            mSharedPreferences = context.getSharedPreferences(SP_NAME, 0);
        }
        mSharedPreferences.edit().putInt(str, i).apply();
    }

    public static void setStringSF(Context context, String str, String str2) {
        if (mSharedPreferences == null) {
            mSharedPreferences = context.getSharedPreferences(SP_NAME, 0);
        }
        mSharedPreferences.edit().putString(str, str2).apply();
    }
}
