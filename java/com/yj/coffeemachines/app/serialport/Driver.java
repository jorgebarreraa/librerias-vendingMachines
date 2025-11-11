package com.yj.coffeemachines.app.serialport;

import android.util.Log;
import java.io.File;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class Driver {
    private static final String TAG = "Driver";
    private String mDeviceRoot;
    private String mDriverName;

    public Driver(String str, String str2) {
        this.mDriverName = str;
        this.mDeviceRoot = str2;
    }

    public ArrayList<File> getDevices() {
        ArrayList<File> arrayList = new ArrayList<>();
        File file = new File("/dev");
        if (!file.exists()) {
            Log.i(TAG, "getDevices: " + file.getAbsolutePath() + " 不存在");
            return arrayList;
        }
        if (!file.canRead()) {
            Log.i(TAG, "getDevices: " + file.getAbsolutePath() + " 没有读取权限");
            return arrayList;
        }
        File[] listFiles = file.listFiles();
        for (int i = 0; i < listFiles.length; i++) {
            if (listFiles[i].getAbsolutePath().startsWith(this.mDeviceRoot)) {
                Log.d(TAG, "Found new device: " + listFiles[i]);
                arrayList.add(listFiles[i]);
            }
        }
        return arrayList;
    }

    public String getName() {
        return this.mDriverName;
    }
}
