package com.yj.coffeemachines.app.utils;

import android.text.TextUtils;
import com.yj.coffeemachines.eventbusbean.ErrorCodeNew;

/* loaded from: classes.dex */
public class ErrorUtils {
    public static String codeDesc(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        String[] split = str.split("_");
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < split.length; i++) {
            String str2 = split[i];
            try {
                String errorCodeNew = ErrorCodeNew.valueOf(str2).toString();
                stringBuffer.append(str2);
                stringBuffer.append("：");
                stringBuffer.append(errorCodeNew);
            } catch (IllegalArgumentException unused) {
                stringBuffer.append(str2);
            }
            if (i != split.length - 1) {
                stringBuffer.append("；");
            }
        }
        return stringBuffer.toString();
    }

    public static String convertCode(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        String[] split = str.split("_");
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < split.length; i++) {
            String str2 = split[i];
            try {
                stringBuffer.append(ErrorCodeNew.valueOf(str2).toString());
            } catch (IllegalArgumentException unused) {
                stringBuffer.append(str2);
            }
            if (i != split.length - 1) {
                stringBuffer.append("_");
            }
        }
        return stringBuffer.toString();
    }
}
