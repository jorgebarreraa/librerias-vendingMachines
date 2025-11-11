package com.yj.coffeemachines.pay.mdb;

import com.yj.coffeemachines.Constants;
import com.yj.coffeemachines.MyAppLocation;
import com.yj.coffeemachines.helper.Tools;
import java.math.BigDecimal;

/* loaded from: classes.dex */
public class MdbSendMethod {
    private static volatile MdbSendMethod instance;

    private MdbSendMethod() {
    }

    private boolean hasAmountToRefund(double d) {
        return d > 0.0d;
    }

    public static MdbSendMethod instance() {
        if (instance == null) {
            synchronized (MdbReceivePayment.class) {
                if (instance == null) {
                    instance = new MdbSendMethod();
                }
            }
        }
        return instance;
    }

    private void sendBytes(byte[]... bArr) {
        for (byte[] bArr2 : bArr) {
            MyAppLocation.myAppLocation.mSerialDataService.addSendByteToList_Payment(bArr2);
        }
    }

    private void sendBytesMain(byte[]... bArr) {
        for (byte[] bArr2 : bArr) {
            MyAppLocation.myAppLocation.mSerialDataService.addSendByteToList_Payment_main(bArr2);
        }
    }

    public void backMoney(int i) {
        if (Constants.HomePay() != 2) {
            return;
        }
        if (i == 3) {
            if (hasAmountToRefund(Constants.countNum)) {
                clearAck(Constants.countNum);
            }
            closePay();
        } else if (i == 2) {
            cancel();
        }
    }

    public void cancel() {
        if (Constants.HomePay() != 2) {
            return;
        }
        sendBytesMain(TransmissionUtils.pulseCancel, TransmissionUtils.endTransaction, TransmissionUtils.pulseStart);
    }

    public void checkMoney() {
        sendBytes(TransmissionUtils.coinRead, TransmissionUtils.billRead);
    }

    public void clearAck(double d) {
        Tools.upLocalLog("退还/找零的金额" + d);
        sendBytesMain(TransmissionUtils.billMoney);
        Constants.noteChange = d;
    }

    public void closePay() {
        sendBytesMain(TransmissionUtils.coinClose, TransmissionUtils.billClose);
    }

    public void getCheckMoney(String str) {
        if (Constants.HomePay() != 2) {
            return;
        }
        if (Constants.buy_type == 2) {
            if (str.equals("制作失败")) {
                sendBytesMain(TransmissionUtils.pulseCancel);
            } else {
                sendBytesMain(TransmissionUtils.pulseSuccess);
            }
            sendBytesMain(TransmissionUtils.endTransaction, TransmissionUtils.pulseStart);
            return;
        }
        if (Constants.buy_type == 3) {
            double doubleValue = new BigDecimal(Constants.countNum).subtract(BigDecimal.valueOf(Constants.nowProduct_Detail.realPrice())).doubleValue();
            if (str.equals("制作失败")) {
                clearAck(Constants.countNum);
            } else {
                clearAck(doubleValue);
            }
        }
    }

    public void openPay() {
        sendBytesMain(TransmissionUtils.coinStart, TransmissionUtils.billStart);
    }
}
