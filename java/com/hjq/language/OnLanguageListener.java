package com.hjq.language;

import java.util.Locale;

/* loaded from: classes.dex */
public interface OnLanguageListener {
    void onAppLocaleChange(Locale locale, Locale locale2);

    void onSystemLocaleChange(Locale locale, Locale locale2);
}
