package com.yj.coffeemachines.app.service;

import com.yj.coffeemachines.app.serialport.SerialPortManager;

/* loaded from: classes.dex */
public class SerialPortHelper {
    private static SerialPortHelper INSTANCE;
    private SerialPortManager serialPortGround;
    private SerialPortManager serialPortInstant;
    private SerialPortManager serialPortPay;

    private SerialPortHelper() {
    }

    public static synchronized SerialPortHelper getInstance() {
        SerialPortHelper serialPortHelper;
        synchronized (SerialPortHelper.class) {
            if (INSTANCE == null) {
                INSTANCE = new SerialPortHelper();
            }
            serialPortHelper = INSTANCE;
        }
        return serialPortHelper;
    }

    public void sendGround(byte[] bArr) {
        this.serialPortGround.addSendList(bArr);
    }

    public void sendGroundMain(byte[] bArr) {
        this.serialPortGround.addSendMainList(bArr);
    }

    public void sendInstant(byte[] bArr) {
        this.serialPortInstant.addSendList(bArr);
    }

    public void sendInstantMain(byte[] bArr) {
        this.serialPortInstant.addSendMainList(bArr);
    }

    public void sendPay(byte[] bArr) {
        this.serialPortPay.addSendList(bArr);
    }

    public void sendPayMain(byte[] bArr) {
        this.serialPortPay.addSendMainList(bArr);
    }
}
