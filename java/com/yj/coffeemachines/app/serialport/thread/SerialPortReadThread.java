package com.yj.coffeemachines.app.serialport.thread;

import com.yj.coffeemachines.Constants;
import com.yj.coffeemachines.app.serialport.ConfigurationSdk;
import com.yj.coffeemachines.app.utils.ByteUtils;
import com.yj.coffeemachines.helper.Tools;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import kotlin.UByte;

/* loaded from: classes.dex */
public abstract class SerialPortReadThread extends Thread {
    private static final String TAG = "SerialPortReadThread";
    private InputStream mInputStream;
    private ConfigurationSdk sdk;
    String anObject = Constants.SERIAPort_currentgrinding();
    private boolean runflag = true;
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

    public SerialPortReadThread(InputStream inputStream, ConfigurationSdk configurationSdk) {
        this.mInputStream = inputStream;
        this.sdk = configurationSdk;
    }

    public static String bytesToHex(byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer();
        for (byte b : bArr) {
            String hexString = Integer.toHexString(b & UByte.MAX_VALUE);
            if (hexString.length() < 2) {
                stringBuffer.append(0);
            }
            stringBuffer.append(hexString);
        }
        return stringBuffer.toString();
    }

    private void checkRules(byte[] bArr) {
        byte[] msgHead = this.sdk.getMsgHead();
        if (msgHead == null) {
            onDataReceived(bArr);
            return;
        }
        String byte2HexStr2 = ByteUtils.byte2HexStr2(msgHead);
        byte[] subBytes = ByteUtils.subBytes(bArr, 0, msgHead.length);
        String byte2HexStr22 = ByteUtils.byte2HexStr2(subBytes);
        if (byte2HexStr2.equals(byte2HexStr22)) {
            onDataReceived(bArr);
            return;
        }
        if (byte2HexStr22.contains("14 01") || byte2HexStr22.contains("14 02") || byte2HexStr22.contains("14 03")) {
            onDataReceived(bArr);
            return;
        }
        if (byte2HexStr22.contains("30") || byte2HexStr22.contains("46") || byte2HexStr22.contains("38") || byte2HexStr22.contains("30 30")) {
            onDataReceived(bArr);
            return;
        }
        Tools.upLocalLog("数据不合法：" + ByteUtils.byte2HexStr2(subBytes));
    }

    public abstract void onDataReceived(byte[] bArr);

    public void release() {
        interrupt();
        this.runflag = false;
        InputStream inputStream = this.mInputStream;
        if (inputStream != null) {
            try {
                inputStream.close();
                this.mInputStream = null;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        super.run();
        byte[] bArr = new byte[1024];
        while (!isInterrupted() && this.runflag) {
            try {
                Thread.sleep(40L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                int read = this.mInputStream.read(bArr);
                if (read <= 0) {
                    if (read == -1) {
                        break;
                    }
                } else {
                    this.byteArrayOutputStream.write(bArr, 0, read);
                }
                if (this.byteArrayOutputStream.size() >= 6) {
                    byte[] byteArray = this.byteArrayOutputStream.toByteArray();
                    if (!ByteUtils.byte2HexStr2(byteArray).equals("33 30 20 30 39 0D 0A")) {
                        checkRules(byteArray);
                    }
                    this.byteArrayOutputStream.reset();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
                Tools.upLocalLog("稳重的线程老先生出错：" + e2.toString());
            }
        }
        Tools.upLocalLog("速溶线程中断");
    }

    @Override // java.lang.Thread
    public synchronized void start() {
        super.start();
    }

    public byte[] subByte(byte[] bArr, int i, int i2) {
        byte[] bArr2 = new byte[i2];
        System.arraycopy(bArr, i, bArr2, 0, i2);
        return bArr2;
    }
}
