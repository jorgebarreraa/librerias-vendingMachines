package com.yj.coffeemachines.bean;

/* loaded from: classes.dex */
public class LanguageItem {
    private String langCode;
    private String mqttUrl;
    private String name;
    private String nativeName;
    private String url;

    public LanguageItem() {
        this.name = "";
        this.nativeName = "";
        this.langCode = "";
        this.url = "";
        this.mqttUrl = "";
    }

    public LanguageItem(String str, String str2, String str3) {
        this.name = "";
        this.nativeName = "";
        this.langCode = "";
        this.url = "";
        this.mqttUrl = "";
        this.name = str;
        this.nativeName = str2;
        this.langCode = str3;
    }

    public LanguageItem(String str, String str2, String str3, String str4, String str5) {
        this.name = "";
        this.nativeName = "";
        this.langCode = "";
        this.url = "";
        this.mqttUrl = "";
        this.name = str;
        this.nativeName = str2;
        this.langCode = str3;
        this.url = str4;
        this.mqttUrl = str5;
    }

    public String getLangCode() {
        return this.langCode;
    }

    public String getMqttUrl() {
        return this.mqttUrl;
    }

    public String getName() {
        return this.name;
    }

    public String getNativeName() {
        return this.nativeName;
    }

    public String getUrl() {
        return this.url;
    }

    public void setLangCode(String str) {
        this.langCode = str;
    }

    public void setMqttUrl(String str) {
        this.mqttUrl = str;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setNativeName(String str) {
        this.nativeName = str;
    }

    public void setUrl(String str) {
        this.url = str;
    }
}
