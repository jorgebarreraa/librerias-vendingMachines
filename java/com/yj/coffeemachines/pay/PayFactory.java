package com.yj.coffeemachines.pay;

import com.yj.coffeemachines.pay.method.WeChatPay;

/* loaded from: classes.dex */
public class PayFactory {
    public static PayStrategy getPayMethod(String str) {
        if (((str.hashCode() == -1708856474 && str.equals("WeChat")) ? (char) 0 : (char) 65535) == 0) {
            return new WeChatPay();
        }
        throw new IllegalArgumentException("Invalid payment method: " + str);
    }
}
