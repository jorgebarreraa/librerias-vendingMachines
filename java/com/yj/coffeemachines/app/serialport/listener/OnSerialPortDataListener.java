package com.yj.coffeemachines.app.serialport.listener;

import java.io.File;

/* loaded from: classes.dex */
public interface OnSerialPortDataListener {
    void onDataReceived(File file, byte[] bArr, boolean z, boolean z2);

    void onDataSent(File file, byte[] bArr);
}
