package com.yj.coffeemachines.mvp.model.beans;

/* loaded from: classes.dex */
public class DeviceQrBean {
    private String exchangePromptContent;
    private String exchangeQr;
    private String memberPromptContent;
    private String memberQr;
    private String merchantId;
    private String merchantLogo;

    public String getExchangePromptContent() {
        return this.exchangePromptContent;
    }

    public String getExchangeQr() {
        return this.exchangeQr;
    }

    public String getMemberPromptContent() {
        return this.memberPromptContent;
    }

    public String getMemberQr() {
        return this.memberQr;
    }

    public String getMerchantId() {
        return this.merchantId;
    }

    public String getMerchantLogo() {
        return this.merchantLogo;
    }

    public void setExchangePromptContent(String str) {
        this.exchangePromptContent = str;
    }

    public void setExchangeQr(String str) {
        this.exchangeQr = str;
    }

    public void setMemberPromptContent(String str) {
        this.memberPromptContent = str;
    }

    public void setMemberQr(String str) {
        this.memberQr = str;
    }

    public void setMerchantId(String str) {
        this.merchantId = str;
    }

    public void setMerchantLogo(String str) {
        this.merchantLogo = str;
    }
}
