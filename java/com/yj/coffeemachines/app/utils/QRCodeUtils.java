package com.yj.coffeemachines.app.utils;

import android.graphics.Bitmap;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.yj.coffeemachines.helper.Tools;
import java.util.HashMap;

/* loaded from: classes.dex */
public class QRCodeUtils {
    public static Bitmap generateBitmap(String str, int i, int i2) {
        QRCodeWriter qRCodeWriter = new QRCodeWriter();
        HashMap hashMap = new HashMap();
        hashMap.put(EncodeHintType.CHARACTER_SET, "utf-8");
        try {
            BitMatrix encode = qRCodeWriter.encode(str, BarcodeFormat.QR_CODE, i, i2, hashMap);
            int[] iArr = new int[i * i2];
            for (int i3 = 0; i3 < i2; i3++) {
                for (int i4 = 0; i4 < i; i4++) {
                    if (encode.get(i4, i3)) {
                        iArr[(i3 * i) + i4] = 0;
                    } else {
                        iArr[(i3 * i) + i4] = -1;
                    }
                }
            }
            return Bitmap.createBitmap(iArr, 0, i, i, i2, Bitmap.Config.RGB_565);
        } catch (WriterException e) {
            Tools.sendArMsg(e.toString());
            e.printStackTrace();
            return null;
        }
    }
}
