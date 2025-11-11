package com.yj.coffeemachines.language;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.Locale;

/* loaded from: classes.dex */
public class SPUtil {
    private static volatile SPUtil instance;
    private final SharedPreferences mSharedPreferences;
    private final String SP_NAME = "language_setting";
    private final String TAG_LANGUAGE = "language_select";
    private final String TAG_SYSTEM_LANGUAGE = "system_language";
    private Locale systemCurrentLocal = Locale.ENGLISH;

    public SPUtil(Context context) {
        this.mSharedPreferences = context.getSharedPreferences("language_setting", 0);
    }

    public static SPUtil getInstance(Context context) {
        if (instance == null) {
            synchronized (SPUtil.class) {
                if (instance == null) {
                    instance = new SPUtil(context);
                }
            }
        }
        return instance;
    }

    public int getSelectLanguage() {
        return this.mSharedPreferences.getInt("language_select", 0);
    }

    public Locale getSystemCurrentLocal() {
        return this.systemCurrentLocal;
    }

    public void saveLanguage(int i) {
        SharedPreferences.Editor edit = this.mSharedPreferences.edit();
        edit.putInt("language_select", i);
        edit.commit();
    }

    public void setSystemCurrentLocal(Locale locale) {
        this.systemCurrentLocal = locale;
    }
}
