package com.yj.coffeemachines.app.serialport;

import java.io.File;

/* loaded from: classes.dex */
public class ConfigurationSdk {
    private final int baudRate;
    private final File device;
    private final byte[] msgHead;
    private final byte[] msgTail;
    private final boolean sDebug;
    private final boolean sIncludeThread;
    private final String sLogType;

    /* loaded from: classes.dex */
    public static class ConfigurationBuilder {
        private final int baudRate;
        private final File device;
        private byte[] msgHead;
        private byte[] msgTail;
        private boolean sDebug = false;
        private boolean sIncludeThread = false;
        private String sLogType = "inspection";

        public ConfigurationBuilder(File file, int i) {
            this.device = file;
            this.baudRate = i;
        }

        public ConfigurationSdk build() {
            return new ConfigurationSdk(this);
        }

        public ConfigurationBuilder log(String str, boolean z, boolean z2) {
            this.sLogType = str;
            this.sDebug = z;
            this.sIncludeThread = z2;
            return this;
        }

        public ConfigurationBuilder msgHead(byte[] bArr) {
            this.msgHead = bArr;
            return this;
        }

        public ConfigurationBuilder msgTail(byte[] bArr) {
            this.msgTail = bArr;
            return this;
        }
    }

    public ConfigurationSdk(ConfigurationBuilder configurationBuilder) {
        this.device = configurationBuilder.device;
        this.baudRate = configurationBuilder.baudRate;
        this.msgHead = configurationBuilder.msgHead;
        this.msgTail = configurationBuilder.msgTail;
        this.sDebug = configurationBuilder.sDebug;
        this.sIncludeThread = configurationBuilder.sIncludeThread;
        this.sLogType = configurationBuilder.sLogType;
    }

    public int getBaudRate() {
        return this.baudRate;
    }

    public File getDevice() {
        return this.device;
    }

    public byte[] getMsgHead() {
        return this.msgHead;
    }

    public byte[] getMsgTail() {
        return this.msgTail;
    }

    public String getsLogType() {
        return this.sLogType;
    }

    public boolean issDebug() {
        return this.sDebug;
    }

    public boolean issIncludeThread() {
        return this.sIncludeThread;
    }
}
