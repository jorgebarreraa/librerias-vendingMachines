package com.yj.coffeemachines.app.serialport;

import android.util.Log;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

/* loaded from: classes.dex */
public class SerialPortFinder {
    private static final String DRIVERS_PATH = "/proc/tty/drivers";
    private static final String SERIAL_FIELD = "serial";
    private static final String TAG = "SerialPortFinder";

    public SerialPortFinder() {
        boolean canRead = new File(DRIVERS_PATH).canRead();
        Log.i(TAG, "SerialPortFinder: file.canRead() = " + canRead);
    }

    private ArrayList<Driver> getDrivers() throws IOException {
        ArrayList<Driver> arrayList = new ArrayList<>();
        LineNumberReader lineNumberReader = new LineNumberReader(new FileReader(DRIVERS_PATH));
        while (true) {
            String readLine = lineNumberReader.readLine();
            if (readLine == null) {
                return arrayList;
            }
            String trim = readLine.substring(0, 21).trim();
            String[] split = readLine.split(" +");
            if (split.length >= 5 && split[split.length - 1].equals(SERIAL_FIELD)) {
                Log.d(TAG, "Found new driver " + trim + " on " + split[split.length - 4]);
                arrayList.add(new Driver(trim, split[split.length + (-4)]));
            }
        }
    }

    public String[] getAllDevicesPath() {
        Vector vector = new Vector();
        try {
            Iterator<Driver> it2 = getDrivers().iterator();
            while (it2.hasNext()) {
                Iterator<File> it3 = it2.next().getDevices().iterator();
                while (it3.hasNext()) {
                    vector.add(it3.next().getAbsolutePath());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return (String[]) vector.toArray(new String[vector.size()]);
    }

    public ArrayList<Device> getDevices() {
        ArrayList<Device> arrayList = new ArrayList<>();
        try {
            Iterator<Driver> it2 = getDrivers().iterator();
            while (it2.hasNext()) {
                Driver next = it2.next();
                String name = next.getName();
                Iterator<File> it3 = next.getDevices().iterator();
                while (it3.hasNext()) {
                    File next2 = it3.next();
                    arrayList.add(new Device(next2.getName(), name, next2));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return arrayList;
    }
}
