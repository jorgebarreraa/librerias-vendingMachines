package com.yj.coffeemachines.pay.bean;

/* loaded from: classes.dex */
public enum PayWay {
    SB_PAY("sbpay"),
    WX_PAY("wxpay"),
    ALI_PAY("alipay"),
    UNION_PAY("unionpay"),
    CASH_PAY("cash"),
    FREE_PAY("freepay"),
    EXCHANGE_PAY("exchange"),
    APPLE_PAY("applepay"),
    ICBC_PAY("icbcpay"),
    DIGI_PAY("digipay"),
    YS_PAY("yspay");

    private String pay;

    PayWay(String str) {
        this.pay = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.pay;
    }
}
