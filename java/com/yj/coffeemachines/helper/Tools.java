package com.yj.coffeemachines.helper;

import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import com.alibaba.fastjson.JSON;
import com.elvishew.xlog.Logger;
import com.elvishew.xlog.XLog;
import com.google.gson.Gson;
import com.jess.arms.utils.ArmsUtils;
import com.yj.coffeemachines.Constants;
import com.yj.coffeemachines.app.utils.FileUtils;
import com.yj.coffeemachines.eventbusbean.DialogMessageNew;
import com.yj.coffeemachines.mvp.model.beans.ProductBean;
import java.io.File;
import java.nio.ByteBuffer;
import java.text.SimpleDateFormat;
import java.util.Random;
import kotlin.UByte;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.greenrobot.eventbus.EventBus;

/* loaded from: classes.dex */
public class Tools {
    public static int DateCompare(String str, String str2, String str3) throws Exception {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(str3);
        return simpleDateFormat.parse(str).compareTo(simpleDateFormat.parse(str2));
    }

    public static String addZeroToEachDigit(Object obj) {
        String valueOf = String.valueOf(obj);
        String str = "";
        if (valueOf != null && valueOf != "" && valueOf.length() > 0) {
            for (char c : valueOf.toCharArray()) {
                str = (str + "0") + c;
            }
        }
        return str;
    }

    public static String byteToStr(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        char[] charArray = "0123456789ABCDEF".toCharArray();
        char[] cArr = new char[bArr.length * 2];
        for (int i = 0; i < bArr.length; i++) {
            int i2 = bArr[i] & UByte.MAX_VALUE;
            int i3 = i * 2;
            cArr[i3] = charArray[i2 >>> 4];
            cArr[i3 + 1] = charArray[i2 & 15];
        }
        return new String(cArr);
    }

    public static int bytesToInt(byte[] bArr) {
        return ByteBuffer.wrap(bArr).getInt();
    }

    public static String getExistLocalpathByFullpath(String str) {
        String str2 = Constants.adPath + MqttTopic.TOPIC_LEVEL_SEPARATOR + str.split(MqttTopic.TOPIC_LEVEL_SEPARATOR)[r3.length - 1];
        return FileUtils.isFileExists(new File(str2)) ? str2 : "";
    }

    public static String getExistLocalpathByName(String str) {
        String str2 = Constants.adPath + MqttTopic.TOPIC_LEVEL_SEPARATOR + str;
        return FileUtils.isFileExists(new File(str2)) ? str2 : "";
    }

    public static <T> T jsonToObject(String str, Class<T> cls) throws Exception {
        return (T) new Gson().fromJson(str, (Class) cls);
    }

    public static void loadFileByFullpath(ProductBean.ProductDetailBean.AttachBean attachBean) {
        File file = new File(Constants.adPath + MqttTopic.TOPIC_LEVEL_SEPARATOR + attachBean.getFileName());
        if (FileUtils.isFileExists(file)) {
            return;
        }
        FileUtils.downloadFile(attachBean.getFullPath(), file);
    }

    public static String rundom(int i) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i2 = 0; i2 < i; i2++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }

    public static void sendArMsg(Object obj) {
        ArmsUtils.snackbarText(String.valueOf(obj));
    }

    public static void sendErrorMsgShort(String str, int i, int i2) {
        EventBus.getDefault().post(new DialogMessageNew(str, 1));
    }

    public static SpannableString shouSize(String str, int i, int i2, int i3) {
        SpannableString spannableString = new SpannableString(str);
        spannableString.setSpan(new AbsoluteSizeSpan(i2, true), 0, i, 33);
        spannableString.setSpan(new AbsoluteSizeSpan(i3, true), i, str.length(), 33);
        return spannableString;
    }

    public static byte[] strToByte(String str) {
        if (str == null) {
            return null;
        }
        if (str.length() == 0) {
            return new byte[0];
        }
        byte[] bArr = new byte[str.length() / 2];
        for (int i = 0; i < bArr.length; i++) {
            int i2 = i * 2;
            bArr[i] = (byte) Integer.parseInt(str.substring(i2, i2 + 2), 16);
        }
        return bArr;
    }

    public static double toDouble(Object obj, double d) {
        try {
            return Double.parseDouble(String.valueOf(obj));
        } catch (NumberFormatException unused) {
            return d;
        }
    }

    public static double toDoubleMin(Object obj, double d, double d2) {
        try {
            double parseDouble = Double.parseDouble(String.valueOf(obj));
            return parseDouble < d2 ? d2 : parseDouble;
        } catch (NumberFormatException unused) {
            return d;
        }
    }

    public static int toInt(Object obj) {
        String valueOf = String.valueOf(obj);
        if (valueOf == null || valueOf == "") {
            return 0;
        }
        if (valueOf.length() > 0) {
            try {
            } catch (NumberFormatException unused) {
                return 0;
            }
        }
        return Integer.parseInt(valueOf);
    }

    public static int toInt(Object obj, int i) {
        String valueOf = String.valueOf(obj);
        if (valueOf != null && valueOf != "" && valueOf.length() > 0) {
            try {
                return Integer.parseInt(valueOf);
            } catch (NumberFormatException unused) {
            }
        }
        return i;
    }

    public static String toJson(Object obj) {
        return JSON.toJSONString(obj);
    }

    public static String toString(Object obj) {
        return obj == null ? "" : String.valueOf(obj);
    }

    public static void upLocalLog(Object obj) {
        boolean z = Constants.HomePay() == 2;
        String valueOf = String.valueOf(obj);
        if (z && valueOf.contains("支付模组离线")) {
            return;
        }
        XLog.tag("Coffee-Machine").i(valueOf);
    }

    public static void upLocalLog(Object obj, String str, int i) {
        Logger.Builder tag = XLog.tag("Coffee-Machine");
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append("---");
        sb.append(i == 1 ? "发送：" : "接收：");
        sb.append(String.valueOf(obj));
        tag.i(sb.toString());
    }

    public static void upLocalLogM2(Object obj) {
        XLog.tag("Coffee-Machine").i(String.valueOf(obj));
    }

    public static void upLocalLogTest(Object obj) {
        Constants.testNum++;
        XLog.tag("Coffee-Machine").i(String.valueOf(obj));
    }
}
