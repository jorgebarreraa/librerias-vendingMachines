package com.yj.coffeemachines.eventbusbean;

import androidx.work.PeriodicWorkRequest;
import com.yj.coffeemachines.helper.Tools;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public class ErrorCodeNew_Msg {
    public static String[] ErrorIns = {ErrorCodeNew.E01.name(), ErrorCodeNew.E02.name(), ErrorCodeNew.E03.name(), ErrorCodeNew.E14.name(), ErrorCodeNew.E27.name(), ErrorCodeNew.E19.name(), ErrorCodeNew.A05.name(), ErrorCodeNew.E20.name(), ErrorCodeNew.E22.name(), ErrorCodeNew.E23.name(), ErrorCodeNew.E12.name(), ErrorCodeNew.E18.name(), ErrorCodeNew.E11.name(), ErrorCodeNew.E25.name(), ErrorCodeNew.E04.name(), ErrorCodeNew.A100.name(), "NoNetworkConnection", "无网络连接", "设备预热中", "EquipmentPreheating", "云服务数据连接中", "Cloud service data connection..."};
    public static String[] ErrorCur = {ErrorCodeNew.E05.name(), ErrorCodeNew.E06.name(), ErrorCodeNew.E07.name(), ErrorCodeNew.E08.name(), ErrorCodeNew.E09.name(), ErrorCodeNew.E10.name(), ErrorCodeNew.E17.name()};
    public static String[] ErrorIce = {ErrorCodeNew.A117.name(), ErrorCodeNew.A118.name(), ErrorCodeNew.E15.name(), ErrorCodeNew.E16.name()};
    public static String[] ErrorPay = new String[0];
    public static String[] ErrorPayNet = new String[0];
    public static String[] ErrorHot = new String[0];
    public static String[] ErrorCold = new String[0];
    public static String[] AbnormalNo = {ErrorCodeNew.A01.name(), ErrorCodeNew.A100.name(), ErrorCodeNew.A02.name(), ErrorCodeNew.E22.name(), ErrorCodeNew.E23.name(), ErrorCodeNew.A108.name(), ErrorCodeNew.A04.name(), ErrorCodeNew.A10.name(), ErrorCodeNew.A11.name(), ErrorCodeNew.A12.name(), ErrorCodeNew.A13.name(), ErrorCodeNew.A14.name(), ErrorCodeNew.A15.name(), ErrorCodeNew.A16.name(), ErrorCodeNew.A17.name(), ErrorCodeNew.E18.name(), ErrorCodeNew.A153.name()};
    public static String[] AbnormalYes = {ErrorCodeNew.A09.name(), ErrorCodeNew.A156.name(), ErrorCodeNew.A157.name(), ErrorCodeNew.A158.name()};
    public static String ErrorNew = "";
    public static String ErrorNewfirst = "";
    public static String ErrorNewData = "E10_E11_E13_E18";
    public static String ErrorOld = "";
    public static ErrorCodeNew MacState = ErrorCodeNew.UNKNOWN;
    public static long TEN_MINUTES_IN_MILLIS = PeriodicWorkRequest.MIN_PERIODIC_FLEX_MILLIS;
    public static Map<String, Long> dataTimeStamps = new HashMap();

    public static void addErrorCode(String str) {
        String str2 = str + "_";
        if (ErrorNew.contains(str2)) {
            return;
        }
        if (!ErrorNewData.contains(str2) || ErrorNewfirst.contains(str2)) {
            ErrorNew += str2;
            return;
        }
        ErrorNewfirst += str2;
    }

    public static void deleteErrorCode(String str) {
        ErrorNew = ErrorNew.replace(str + "_", "");
    }

    public static String getErrorNew() {
        return ErrorNew.replace("_", "");
    }

    public static String[] isError() {
        Long l = dataTimeStamps.get(getErrorNew());
        if (l == null || System.currentTimeMillis() - l.longValue() > TEN_MINUTES_IN_MILLIS) {
            Tools.upLocalLog("查询故障信息，为空则无故障：" + getErrorNew());
        }
        dataTimeStamps.put(getErrorNew(), Long.valueOf(System.currentTimeMillis()));
        String[] strArr = {"0", "0", "0", "0", "0", "0", "0", "0"};
        String str = ErrorNew;
        if (str != null && str != "" && str.length() > 0) {
            String[] split = ErrorNew.split("_");
            boolean z = false;
            for (String str2 : ErrorIns) {
                int length = split.length;
                int i = 0;
                while (true) {
                    if (i >= length) {
                        break;
                    }
                    if (str2.equals(split[i])) {
                        z = true;
                        break;
                    }
                    i++;
                }
                if (z) {
                    break;
                }
            }
            strArr[0] = z ? "1" : "0";
            boolean z2 = false;
            for (String str3 : ErrorCur) {
                int length2 = split.length;
                int i2 = 0;
                while (true) {
                    if (i2 >= length2) {
                        break;
                    }
                    if (str3.equals(split[i2])) {
                        z2 = true;
                        break;
                    }
                    i2++;
                }
                if (z2) {
                    break;
                }
            }
            strArr[1] = z2 ? "1" : "0";
            boolean z3 = false;
            for (String str4 : ErrorIce) {
                int length3 = split.length;
                int i3 = 0;
                while (true) {
                    if (i3 >= length3) {
                        break;
                    }
                    if (str4.equals(split[i3])) {
                        z3 = true;
                        break;
                    }
                    i3++;
                }
                if (z3) {
                    break;
                }
            }
            strArr[2] = z3 ? "1" : "0";
            boolean z4 = false;
            for (String str5 : ErrorPay) {
                int length4 = split.length;
                int i4 = 0;
                while (true) {
                    if (i4 >= length4) {
                        break;
                    }
                    if (str5.equals(split[i4])) {
                        z4 = true;
                        break;
                    }
                    i4++;
                }
                if (z4) {
                    break;
                }
            }
            strArr[3] = z4 ? "1" : "0";
            boolean z5 = false;
            for (String str6 : ErrorPay) {
                int length5 = split.length;
                int i5 = 0;
                while (true) {
                    if (i5 >= length5) {
                        break;
                    }
                    if (str6.equals(split[i5])) {
                        z5 = true;
                        break;
                    }
                    i5++;
                }
                if (z5) {
                    break;
                }
            }
            strArr[3] = z5 ? "1" : "0";
            boolean z6 = false;
            for (String str7 : ErrorHot) {
                int length6 = split.length;
                int i6 = 0;
                while (true) {
                    if (i6 >= length6) {
                        break;
                    }
                    if (str7.equals(split[i6])) {
                        z6 = true;
                        break;
                    }
                    i6++;
                }
                if (z6) {
                    break;
                }
            }
            strArr[4] = z6 ? "1" : "0";
            boolean z7 = false;
            for (String str8 : ErrorCold) {
                int length7 = split.length;
                int i7 = 0;
                while (true) {
                    if (i7 >= length7) {
                        break;
                    }
                    if (str8.equals(split[i7])) {
                        z7 = true;
                        break;
                    }
                    i7++;
                }
                if (z7) {
                    break;
                }
            }
            strArr[5] = z7 ? "1" : "0";
            boolean z8 = false;
            for (String str9 : AbnormalNo) {
                int length8 = split.length;
                int i8 = 0;
                while (true) {
                    if (i8 >= length8) {
                        break;
                    }
                    if (str9.equals(split[i8])) {
                        z8 = true;
                        break;
                    }
                    i8++;
                }
                if (z8) {
                    break;
                }
            }
            strArr[6] = z8 ? "1" : "0";
            boolean z9 = false;
            for (String str10 : AbnormalYes) {
                int length9 = split.length;
                int i9 = 0;
                while (true) {
                    if (i9 >= length9) {
                        break;
                    }
                    if (str10.equals(split[i9])) {
                        z9 = true;
                        break;
                    }
                    i9++;
                }
                if (z9) {
                    break;
                }
            }
            strArr[7] = z9 ? "1" : "0";
        }
        return strArr;
    }

    public static boolean isExist(String str) {
        return ErrorOld.contains(str + "_");
    }

    public static void updateErrorCode(boolean z, String str) {
        if (z) {
            addErrorCode(str);
        } else {
            deleteErrorCode(str);
        }
    }
}
