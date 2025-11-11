package com.yj.coffeemachines.language;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.LocaleList;
import android.util.DisplayMetrics;
import java.util.Locale;

/* loaded from: classes.dex */
public class MultiLanguage {
    private static LanguageListener languageLocalListener;

    private static Locale getSetLanguageLocale(Context context) {
        LanguageListener languageListener = languageLocalListener;
        return languageListener != null ? languageListener.getSetLanguageLocale(context) : Locale.ENGLISH;
    }

    public static Locale getSystemLocal(Context context) {
        return Build.VERSION.SDK_INT >= 24 ? LocaleList.getDefault().get(0) : Locale.getDefault();
    }

    public static Locale getSystemLocal(Configuration configuration) {
        return Build.VERSION.SDK_INT >= 24 ? configuration.getLocales().get(0) : configuration.locale;
    }

    public static void init(LanguageListener languageListener) {
        languageLocalListener = languageListener;
    }

    public static void onConfigurationChanged(Context context) {
        setLocal(context);
        setApplicationLanguage(context);
    }

    public static void setApplicationLanguage(Context context) {
        Resources resources = context.getApplicationContext().getResources();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        Configuration configuration = resources.getConfiguration();
        Locale setLanguageLocale = getSetLanguageLocale(context);
        configuration.locale = setLanguageLocale;
        if (Build.VERSION.SDK_INT >= 24) {
            LocaleList localeList = new LocaleList(setLanguageLocale);
            LocaleList.setDefault(localeList);
            configuration.setLocales(localeList);
            context.getApplicationContext().createConfigurationContext(configuration);
            Locale.setDefault(setLanguageLocale);
        }
        resources.updateConfiguration(configuration, displayMetrics);
    }

    public static Context setLocal(Context context) {
        return updateResources(context, getSetLanguageLocale(context));
    }

    private static Context updateResources(Context context, Locale locale) {
        Locale.setDefault(locale);
        Resources resources = context.getResources();
        Configuration configuration = new Configuration(resources.getConfiguration());
        if (Build.VERSION.SDK_INT >= 17) {
            configuration.setLocale(locale);
            return context.createConfigurationContext(configuration);
        }
        configuration.locale = locale;
        resources.updateConfiguration(configuration, resources.getDisplayMetrics());
        return context;
    }
}
