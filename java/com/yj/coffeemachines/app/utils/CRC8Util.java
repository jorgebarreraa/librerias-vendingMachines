package com.yj.coffeemachines.app.utils;

/* loaded from: classes.dex */
public class CRC8Util {
    public static int byteCheckSum(byte[] bArr) {
        int i = 0;
        for (byte b : bArr) {
            i += b;
        }
        return i;
    }
}
