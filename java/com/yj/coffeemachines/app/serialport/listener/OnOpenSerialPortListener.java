package com.yj.coffeemachines.app.serialport.listener;

import java.io.File;

/* loaded from: classes.dex */
public interface OnOpenSerialPortListener {

    /* loaded from: classes.dex */
    public enum Status {
        NO_READ_WRITE_PERMISSION,
        OPEN_FAIL
    }

    void onFail(File file, Status status);

    void onSuccess(File file);
}
