package com.yj.coffeemachines.pay.bean;

/* loaded from: classes.dex */
public enum PayCodeType {
    QR("qr"),
    BAR("bar"),
    S_CARD("scard"),
    R_CARD("rcard"),
    EXCHANGE("exchange");

    private String codeType;

    PayCodeType(String str) {
        this.codeType = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.codeType;
    }
}
