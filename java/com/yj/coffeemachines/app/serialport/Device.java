package com.yj.coffeemachines.app.serialport;

import java.io.File;
import java.io.Serializable;

/* loaded from: classes.dex */
public class Device implements Serializable {
    private static final String TAG = "Device";
    private File file;
    private String name;
    private String root;

    public Device(String str, String str2, File file) {
        this.name = str;
        this.root = str2;
        this.file = file;
    }

    public File getFile() {
        return this.file;
    }

    public String getName() {
        return this.name;
    }

    public String getRoot() {
        return this.root;
    }

    public void setFile(File file) {
        this.file = this.file;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setRoot(String str) {
        this.root = str;
    }
}
