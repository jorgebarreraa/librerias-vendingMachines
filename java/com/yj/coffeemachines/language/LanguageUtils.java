package com.yj.coffeemachines.language;

import android.content.Context;
import android.content.res.Configuration;
import java.util.Locale;

/* loaded from: classes.dex */
public class LanguageUtils {
    private static final String TAG = "LocalManageUtil";

    public static Locale getSetLanguageLocale(Context context) {
        int selectLanguage = SPUtil.getInstance(context).getSelectLanguage();
        return selectLanguage != 0 ? selectLanguage != 1 ? selectLanguage != 2 ? Locale.ENGLISH : Locale.TAIWAN : Locale.CHINA : getSystemLocale(context);
    }

    public static Locale getSystemLocale(Context context) {
        return SPUtil.getInstance(context).getSystemCurrentLocal();
    }

    public static void saveSelectLanguage(Context context, int i) {
        SPUtil.getInstance(context).saveLanguage(i);
        MultiLanguage.setApplicationLanguage(context);
    }

    public static void saveSystemCurrentLanguage(Context context) {
        SPUtil.getInstance(context).setSystemCurrentLocal(MultiLanguage.getSystemLocal(context));
    }

    public static void saveSystemCurrentLanguage(Context context, Configuration configuration) {
        SPUtil.getInstance(context).setSystemCurrentLocal(MultiLanguage.getSystemLocal(configuration));
    }
}
