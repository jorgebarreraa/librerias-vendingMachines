package com.yj.coffeemachines.helper;

import com.yj.coffeemachines.Api;
import com.yj.coffeemachines.app.utils.KvUtil;
import com.yj.coffeemachines.bean.LanguageItem;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public class LanguageHelper {
    public static void initLanguage() {
        if (isSetLanguage()) {
            return;
        }
        List<LanguageItem> languageOptions = languageOptions();
        LanguageItem languageItem = null;
        int i = 0;
        while (true) {
            if (i >= languageOptions.size()) {
                break;
            }
            LanguageItem languageItem2 = languageOptions.get(i);
            if (languageItem2.getLangCode().equalsIgnoreCase("en")) {
                languageItem = languageItem2;
                break;
            }
            i++;
        }
        if (languageItem != null) {
            KvUtil.getInstance().putString("LanguageSet", languageItem.getLangCode());
            KvUtil.getInstance().putString("LanguageNameSet", languageItem.getNativeName());
        }
    }

    public static boolean isLanguageChange(LanguageItem languageItem) {
        if (languageItem == null) {
            return false;
        }
        String langCode = language().getLangCode();
        String nativeName = language().getNativeName();
        if (!isSetLanguage()) {
            KvUtil.getInstance().putString("LanguageSet", languageItem.getLangCode());
            KvUtil.getInstance().putString("LanguageNameSet", languageItem.getNativeName());
            return true;
        }
        if (langCode.equalsIgnoreCase(languageItem.getLangCode()) && nativeName.equalsIgnoreCase(languageItem.getNativeName())) {
            return false;
        }
        KvUtil.getInstance().putString("LanguageSet", languageItem.getLangCode());
        KvUtil.getInstance().putString("LanguageNameSet", languageItem.getNativeName());
        return true;
    }

    public static boolean isSetLanguage() {
        return KvUtil.getInstance().getString("LanguageSet", null) != null;
    }

    public static LanguageItem language() {
        LanguageItem languageItem;
        String string = KvUtil.getInstance().getString("LanguageSet", "zh");
        String string2 = KvUtil.getInstance().getString("LanguageNameSet", "中文");
        List<LanguageItem> languageOptions = languageOptions();
        int i = 0;
        while (true) {
            if (i >= languageOptions.size()) {
                languageItem = null;
                break;
            }
            languageItem = languageOptions.get(i);
            if (string.equalsIgnoreCase(languageItem.getLangCode()) && string2.equalsIgnoreCase(languageItem.getNativeName())) {
                break;
            }
            i++;
        }
        return languageItem == null ? languageOptions.get(0) : languageItem;
    }

    public static List<String> languageNativeName() {
        ArrayList arrayList = new ArrayList();
        Iterator<LanguageItem> it2 = languageOptions().iterator();
        while (it2.hasNext()) {
            arrayList.add(it2.next().getNativeName());
        }
        return arrayList;
    }

    public static List<LanguageItem> languageOptions() {
        String str;
        String str2;
        String str3;
        String str4;
        ArrayList arrayList = new ArrayList();
        if ("Main-3.4.52".toUpperCase().contains("TEST")) {
            str = Api.ZH_URL_TEST;
            str2 = Api.ZH_MQTT_URL_TEST;
            str3 = Api.EN_URL_TEST;
            str4 = Api.EN_MQTT_URL_TEST;
        } else {
            str = Api.ZH_URL;
            str2 = Api.ZH_MQTT_URL;
            str3 = Api.EN_URL;
            str4 = Api.EN_MQTT_URL;
        }
        String str5 = str3;
        String str6 = str4;
        arrayList.add(new LanguageItem("中文", "中文", "zh", str, str2));
        arrayList.add(new LanguageItem("英语", "English", "en", str5, str6));
        arrayList.add(new LanguageItem("俄语", "Русский", "ru", str5, str6));
        arrayList.add(new LanguageItem("西班牙语", "Español", "es", str5, str6));
        arrayList.add(new LanguageItem("德语", "Deutsch", "de", str5, str6));
        arrayList.add(new LanguageItem("法语", "Français", "fr", str5, str6));
        arrayList.add(new LanguageItem("罗马尼亚语", "Română", "ro", str5, str6));
        arrayList.add(new LanguageItem("阿拉伯语", "العربية", "ar", str5, str6));
        arrayList.add(new LanguageItem("波兰语", "Polski", "pl", str5, str6));
        return arrayList;
    }
}
