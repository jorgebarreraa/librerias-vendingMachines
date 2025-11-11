package com.yj.coffeemachines.pay.mdb;

import com.yj.coffeemachines.Constants;
import com.yj.coffeemachines.app.utils.KvUtil;
import com.yj.coffeemachines.helper.Tools;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kotlin.UByte;

/* loaded from: classes.dex */
public class MdbUtils {
    private static final Map<String, Double> codeToAmount = new HashMap();
    private static final Map<String, Double> billAmountMap = new HashMap();
    private static final Map<String, String> billEnableMap = new HashMap();

    static {
        billEnableMap.put("000101", "3704000003000000000000000000000000000000");
        billEnableMap.put("000202", "3704000000300000000000000000000000000000");
        billEnableMap.put("000303", "3704000000030000000000000000000000000000");
        billEnableMap.put("000404", "3704000000003000000000000000000000000000");
        billEnableMap.put("000505", "3704000000000300000000000000000000000000");
        billEnableMap.put("000606", "3704000000000030000000000000000000000000");
        billEnableMap.put("000707", "3704000000000003000000000000000000000000");
        billEnableMap.put("000808", "3704000000000003000000000000000000000000");
        codeToAmount.put("51", Double.valueOf(0.1d));
        codeToAmount.put("52", Double.valueOf(0.2d));
        codeToAmount.put("53", Double.valueOf(0.5d));
        codeToAmount.put("54", Double.valueOf(1.0d));
        codeToAmount.put("55", Double.valueOf(2.0d));
    }

    public static byte[] PayMoneyBill(Double d) {
        return Tools.strToByte("3707" + String.format("%04x", Integer.valueOf((int) Math.round(d.doubleValue()))));
    }

    public static byte[] PayMoneyNew(Double d) {
        return Tools.strToByte("1300" + String.format("%04x", Integer.valueOf((int) Math.round(d.doubleValue() * KvUtil.getInstance().getInt("etmdb", 100)))) + "0001");
    }

    private static int calculateCRC(byte[] bArr, int i) {
        int i2 = 0;
        int i3 = 65535;
        while (i2 < i) {
            int i4 = i3 ^ (bArr[i2] & UByte.MAX_VALUE);
            for (int i5 = 0; i5 < 8; i5++) {
                int i6 = i4 & 1;
                i4 >>= 1;
                if (i6 != 0) {
                    i4 ^= 40961;
                }
            }
            i2++;
            i3 = i4;
        }
        return i3 & 65535;
    }

    public static List<String> calculateChange(double d) {
        int[] iArr = {200, 100, 50, 20, 10};
        String[] strArr = {"0D15", "0D14", "0D13", "0D12", "0D11"};
        int i = ((int) d) * 100;
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < iArr.length; i2++) {
            int i3 = iArr[i2];
            String str = strArr[i2];
            while (i >= i3) {
                arrayList.add(str);
                i -= i3;
            }
        }
        if (i == 0) {
            return arrayList;
        }
        throw new IllegalArgumentException("无法完成找零：剩余 " + (i / 100.0d) + " 元");
    }

    public static byte[] enableBill(String str) {
        return Tools.strToByte(billEnableMap.get(str));
    }

    public static Double getAmountByCode(String str) {
        Constants.opsSeting().getRate_y();
        return codeToAmount.get(str);
    }

    public static Double getAmountByCodeBill(String str) {
        String Country = Constants.Country();
        if (billAmountMap.isEmpty()) {
            String substring = Country.substring(6, 10);
            Tools.upLocalLog("比例系数(16进制): " + substring + ", 十进制值: " + Integer.parseInt(substring, 16));
            int parseInt = Integer.parseInt(Country.substring(10, 12), 16);
            StringBuilder sb = new StringBuilder();
            sb.append("小数位移: ");
            sb.append(parseInt);
            Tools.upLocalLog(sb.toString());
            String substring2 = Country.substring(22, 38);
            for (int i = 0; i <= 6; i++) {
                int i2 = i * 2;
                double parseInt2 = (Integer.parseInt(substring2.substring(i2, i2 + 2), 16) * r5) / Math.pow(10.0d, parseInt);
                String format = String.format("%.2f", Double.valueOf(parseInt2));
                billAmountMap.put(String.valueOf(i + 80), Double.valueOf(parseInt2));
                Tools.upLocalLog("纸币类型" + i + ": " + format);
            }
        }
        return billAmountMap.get(str);
    }
}
