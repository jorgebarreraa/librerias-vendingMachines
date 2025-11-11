package com.hjq.language;

// import android.app.LocaleManager; // Eliminado: dependencia no disponible
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.LocaleList;
import java.util.Locale;

/* loaded from: classes.dex */
final class LanguagesUtils {
    LanguagesUtils() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Context attachLanguages(Context context, Locale locale) {
        Resources resources = context.getResources();
        Configuration configuration = new Configuration(resources.getConfiguration());
        setLocale(configuration, locale);
        if (Build.VERSION.SDK_INT >= 17) {
            context = context.createConfigurationContext(configuration);
        }
        resources.updateConfiguration(configuration, resources.getDisplayMetrics());
        return context;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Resources generateLanguageResources(Context context, Locale locale) {
        Configuration configuration = new Configuration();
        setLocale(configuration, locale);
        return Build.VERSION.SDK_INT >= 17 ? context.createConfigurationContext(configuration).getResources() : new Resources(context.getAssets(), context.getResources().getDisplayMetrics(), configuration);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Locale getLocale(Context context) {
        return getLocale(context.getResources().getConfiguration());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Locale getLocale(Configuration configuration) {
        return Build.VERSION.SDK_INT >= 24 ? configuration.getLocales().get(0) : configuration.locale;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Locale getSystemLocale(Context context) {
        LocaleManager localeManager;
        return (Build.VERSION.SDK_INT < 33 || (localeManager = (LocaleManager) context.getSystemService(LocaleManager.class)) == null) ? getLocale(Resources.getSystem().getConfiguration()) : localeManager.getSystemLocales().get(0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void setDefaultLocale(Context context) {
        Configuration configuration = context.getResources().getConfiguration();
        if (Build.VERSION.SDK_INT >= 24) {
            LocaleList.setDefault(configuration.getLocales());
        } else {
            Locale.setDefault(configuration.locale);
        }
    }

    static void setLocale(Configuration configuration, Locale locale) {
        if (Build.VERSION.SDK_INT >= 24) {
            configuration.setLocales(new LocaleList(locale));
        } else if (Build.VERSION.SDK_INT >= 17) {
            configuration.setLocale(locale);
        } else {
            configuration.locale = locale;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void updateConfigurationChanged(Context context, Configuration configuration, Locale locale) {
        Configuration configuration2 = new Configuration(configuration);
        setLocale(configuration2, locale);
        Resources resources = context.getResources();
        resources.updateConfiguration(configuration2, resources.getDisplayMetrics());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void updateLanguages(Resources resources, Locale locale) {
        Configuration configuration = resources.getConfiguration();
        setLocale(configuration, locale);
        resources.updateConfiguration(configuration, resources.getDisplayMetrics());
    }
}
