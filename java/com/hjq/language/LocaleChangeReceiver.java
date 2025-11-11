package com.hjq.language;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import java.util.Locale;

/* loaded from: classes.dex */
final class LocaleChangeReceiver extends BroadcastReceiver {
    private static volatile Locale sSystemLanguage;
    private final Application mApplication;

    public LocaleChangeReceiver(Application application) {
        this.mApplication = application;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void register(Application application) {
        sSystemLanguage = LanguagesUtils.getSystemLocale(application);
        application.registerReceiver(new LocaleChangeReceiver(application), new IntentFilter("android.intent.action.LOCALE_CHANGED"));
    }

    public void notifySystemLocaleChange(Locale locale, Locale locale2) {
        sSystemLanguage = locale2;
        if (LanguagesConfig.isSystemLanguage(this.mApplication)) {
            LanguagesConfig.clearLanguageSetting(this.mApplication);
        }
        OnLanguageListener onLanguagesListener = MultiLanguages.getOnLanguagesListener();
        if (onLanguagesListener == null) {
            return;
        }
        onLanguagesListener.onSystemLocaleChange(locale, locale2);
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String action;
        if (intent == null || (action = intent.getAction()) == null || !"android.intent.action.LOCALE_CHANGED".equals(action) || sSystemLanguage == null) {
            return;
        }
        Locale systemLanguage = MultiLanguages.getSystemLanguage(this.mApplication);
        if (MultiLanguages.equalsCountry(systemLanguage, sSystemLanguage)) {
            return;
        }
        notifySystemLocaleChange(sSystemLanguage, systemLanguage);
    }
}
