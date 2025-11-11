package com.yj.coffeemachines.pay.method;

import com.yj.coffeemachines.helper.Tools;

/* loaded from: classes.dex */
public class NewCapePay {
    public static byte calculateLRC(byte[] bArr, int i) {
        if (bArr == null || bArr.length <= i) {
            throw new IllegalArgumentException("Invalid input data");
        }
        byte b = 0;
        while (i < bArr.length) {
            b = (byte) (b ^ bArr[i]);
            i++;
        }
        return b;
    }

    public static String cancelPay() {
        byte[] strToByte = Tools.strToByte("5A000101A4");
        return "5A000101A4" + String.format("%02X", Integer.valueOf(calculateLRC(strToByte, strToByte.length)));
    }

    public static byte[] payCount(double d) {
        String format = String.format("%04x", Integer.valueOf((int) Math.round(d * 100.0d)));
        return Tools.strToByte("5A000101A20004" + format + ((int) calculateLRC(Tools.strToByte("5A000101A20004" + format), 1)));
    }
}
