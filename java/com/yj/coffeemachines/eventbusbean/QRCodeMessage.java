package com.yj.coffeemachines.eventbusbean;

/* loaded from: classes.dex */
public class QRCodeMessage {
    private String qrcode;

    public QRCodeMessage(String str) {
        this.qrcode = str;
    }

    public String getQrcode() {
        String str = this.qrcode;
        return str == null ? "" : str;
    }

    public void setQrcode(String str) {
        if (str == null) {
            str = "";
        }
        this.qrcode = str;
    }

    public String toString() {
        return "QRCodeMessage{qrcode='" + this.qrcode + "'}";
    }
}
