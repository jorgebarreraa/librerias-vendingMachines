package com.tencent.bugly.proguard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/* compiled from: BUGLY */
/* loaded from: classes.dex */
public final class a {
    public static String a(ArrayList<String> arrayList) {
        StringBuffer stringBuffer = new StringBuffer();
        int i = 0;
        while (true) {
            String str = "map";
            if (i >= arrayList.size()) {
                Collections.reverse(arrayList);
                for (int i2 = 0; i2 < arrayList.size(); i2++) {
                    String str2 = arrayList.get(i2);
                    if (str2.equals("list")) {
                        int i3 = i2 - 1;
                        arrayList.set(i3, "<" + arrayList.get(i3));
                        arrayList.set(0, arrayList.get(0) + ">");
                    } else if (str2.equals("map")) {
                        int i4 = i2 - 1;
                        arrayList.set(i4, "<" + arrayList.get(i4) + ",");
                        arrayList.set(0, arrayList.get(0) + ">");
                    } else if (str2.equals("Array")) {
                        int i5 = i2 - 1;
                        arrayList.set(i5, "<" + arrayList.get(i5));
                        arrayList.set(0, arrayList.get(0) + ">");
                    }
                }
                Collections.reverse(arrayList);
                Iterator<String> it2 = arrayList.iterator();
                while (it2.hasNext()) {
                    stringBuffer.append(it2.next());
                }
                return stringBuffer.toString();
            }
            String str3 = arrayList.get(i);
            if (str3.equals("java.lang.Integer") || str3.equals("int")) {
                str = "int32";
            } else if (str3.equals("java.lang.Boolean") || str3.equals("boolean")) {
                str = "bool";
            } else if (str3.equals("java.lang.Byte") || str3.equals("byte")) {
                str = "char";
            } else if (str3.equals("java.lang.Double") || str3.equals("double")) {
                str = "double";
            } else if (str3.equals("java.lang.Float") || str3.equals("float")) {
                str = "float";
            } else if (str3.equals("java.lang.Long") || str3.equals("long")) {
                str = "int64";
            } else if (str3.equals("java.lang.Short") || str3.equals("short")) {
                str = "short";
            } else {
                if (str3.equals("java.lang.Character")) {
                    throw new IllegalArgumentException("can not support java.lang.Character");
                }
                if (str3.equals("java.lang.String")) {
                    str = "string";
                } else if (str3.equals("java.util.List")) {
                    str = "list";
                } else if (!str3.equals("java.util.Map")) {
                    str = str3;
                }
            }
            arrayList.set(i, str);
            i++;
        }
    }
}
