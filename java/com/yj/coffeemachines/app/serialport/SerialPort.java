package com.yj.coffeemachines.app.serialport;

import java.io.File;
import java.io.FileDescriptor;
import java.io.IOException;

/* loaded from: classes.dex */
public class SerialPort {
    private static final String TAG;

    static {
        System.loadLibrary("SerialPort");
        TAG = SerialPort.class.getSimpleName();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean chmod777(File file) {
        if (file != null && file.exists()) {
            try {
                Process exec = Runtime.getRuntime().exec("/system/bin/su");
                exec.getOutputStream().write(("chmod 777 " + file.getAbsolutePath() + "\nexit\n").getBytes());
                if (exec.waitFor() == 0 && file.canRead() && file.canWrite()) {
                    if (file.canExecute()) {
                        return true;
                    }
                }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public native void close();

    /* JADX INFO: Access modifiers changed from: protected */
    public native FileDescriptor open(String str, int i, int i2);
}
