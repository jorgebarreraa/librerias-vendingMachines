package com.yj.coffeemachines.pay.mdb;

import com.yj.coffeemachines.Constants;
import com.yj.coffeemachines.MyAppLocation;
import com.yj.coffeemachines.eventbusbean.MoneyReceiveMessage;
import com.yj.coffeemachines.helper.Tools;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.greenrobot.eventbus.EventBus;

/* loaded from: classes.dex */
public class MdbReceivePayment {
    private static volatile MdbReceivePayment instance;
    final Map<String, String> COIN_CODES = new HashMap<String, String>() { // from class: com.yj.coffeemachines.pay.mdb.MdbReceivePayment.1
        {
            put("0851", "51");
            put("0852", "52");
            put("0853", "53");
            put("0854", "54");
            put("0855", "55");
        }
    };
    final Map<String, String> BILL_CODES = new HashMap<String, String>() { // from class: com.yj.coffeemachines.pay.mdb.MdbReceivePayment.2
        {
            put("30B1", "81");
            put("30B2", "82");
            put("30B3", "83");
            put("30B4", "84");
            put("3080", "80");
            put("3081", "81");
            put("3082", "82");
            put("3083", "83");
            put("3084", "84");
            put("3085", "85");
            put("3086", "86");
        }
    };
    final Map<String, Integer> CHANGE_CODES = new HashMap<String, Integer>() { // from class: com.yj.coffeemachines.pay.mdb.MdbReceivePayment.3
        {
            put("000202", 5);
        }
    };

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public enum AmountType {
        COIN,
        NOTE
    }

    private MdbReceivePayment() {
    }

    private void getChangeAmount(String str) {
        String trim = str.length() == 72 ? str.substring(12, 14).trim() : str.substring(10, 12).trim();
        if (trim.equals("00")) {
            trim = str.length() == 72 ? str.substring(20, 22).trim() : str.substring(18, 20).trim();
        }
        int parseInt = Integer.parseInt(trim) * Constants.noteRate.intValue();
        double d = parseInt;
        if (d >= Constants.noteChange) {
            MyAppLocation.myAppLocation.mSerialDataService.addSendByteToList_Payment_main(MdbUtils.PayMoneyBill(Double.valueOf(Constants.noteChange)));
        } else {
            MyAppLocation.myAppLocation.mSerialDataService.addSendByteToList_Payment_main(MdbUtils.PayMoneyBill(Double.valueOf(d)));
        }
        Tools.upLocalLog("找零金额：" + trim + "totalAmount" + parseInt);
    }

    public static MdbReceivePayment instance() {
        if (instance == null) {
            synchronized (MdbReceivePayment.class) {
                if (instance == null) {
                    instance = new MdbReceivePayment();
                }
            }
        }
        return instance;
    }

    private void updateAndNotify(Double d, AmountType amountType) {
        String lowerCase = amountType.name().toLowerCase();
        double doubleValue = BigDecimal.valueOf(amountType == AmountType.COIN ? Constants.coin_Num : Constants.note_Num).add(BigDecimal.valueOf(d.doubleValue())).setScale(2, RoundingMode.HALF_UP).doubleValue();
        if (amountType == AmountType.COIN) {
            Constants.coin_Num = doubleValue;
        } else {
            Constants.note_Num = doubleValue;
        }
        Constants.countNum = doubleValue;
        EventBus.getDefault().post(new MoneyReceiveMessage(doubleValue));
        Tools.upLocalLog(lowerCase + "成功累加金额：" + d + "元，总金额：" + Constants.countNum);
    }

    public void payMdb(byte[] bArr) {
        String replaceAll = new String(bArr, StandardCharsets.US_ASCII).replaceAll("\\s", "");
        Tools.upLocalLog("收到mdb支付串口消息：" + replaceAll);
        if (replaceAll.length() == 56) {
            Constants.setCountry(replaceAll);
        }
        if (replaceAll.length() == 6 && replaceAll.startsWith("000")) {
            MdbUtils.enableBill(replaceAll);
        }
        if (replaceAll.length() == 6) {
            for (Map.Entry<String, Integer> entry : this.CHANGE_CODES.entrySet()) {
                if (replaceAll.contains(entry.getKey())) {
                    Constants.noteRate = entry.getValue();
                }
            }
        }
        if (replaceAll.length() == 70) {
            getChangeAmount(replaceAll);
        } else if (replaceAll.length() == 72) {
            getChangeAmount(replaceAll);
        }
        if (replaceAll.length() >= 4) {
            if (replaceAll.startsWith("05") || replaceAll.startsWith("0005") || replaceAll.contains("1005")) {
                Constants.swipeNum = 1;
            }
        }
        if (replaceAll.contains("308") || replaceAll.contains("30B")) {
            Double valueOf = Double.valueOf(0.0d);
            AmountType amountType = null;
            Iterator<Map.Entry<String, String>> it2 = this.BILL_CODES.entrySet().iterator();
            while (true) {
                if (!it2.hasNext()) {
                    break;
                }
                Map.Entry<String, String> next = it2.next();
                if (replaceAll.contains(next.getKey())) {
                    valueOf = MdbUtils.getAmountByCodeBill(next.getValue());
                    amountType = AmountType.NOTE;
                    break;
                }
            }
            if (valueOf.doubleValue() > 0.0d) {
                updateAndNotify(valueOf, amountType);
            }
        }
    }
}
