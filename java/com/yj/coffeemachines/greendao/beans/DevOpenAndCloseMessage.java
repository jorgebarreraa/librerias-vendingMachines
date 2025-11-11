package com.yj.coffeemachines.greendao.beans;

/* loaded from: classes.dex */
public class DevOpenAndCloseMessage {
    private String cycle;
    private String cycleCron;
    private String deviceId;
    private String deviceNo;
    private String id;
    private Long key;
    private int powerType;
    private int sortNo;
    private String timeStr;

    public DevOpenAndCloseMessage() {
    }

    public DevOpenAndCloseMessage(Long l, String str, String str2, String str3, String str4, String str5, int i, int i2, String str6) {
        this.key = l;
        this.cycle = str;
        this.cycleCron = str2;
        this.deviceId = str3;
        this.deviceNo = str4;
        this.id = str5;
        this.powerType = i;
        this.sortNo = i2;
        this.timeStr = str6;
    }

    public String getCycle() {
        String str = this.cycle;
        return str == null ? "" : str;
    }

    public String getCycleCron() {
        String str = this.cycleCron;
        return str == null ? "" : str;
    }

    public String getDeviceId() {
        String str = this.deviceId;
        return str == null ? "" : str;
    }

    public String getDeviceNo() {
        String str = this.deviceNo;
        return str == null ? "" : str;
    }

    public String getId() {
        String str = this.id;
        return str == null ? "" : str;
    }

    public Long getKey() {
        return this.key;
    }

    public int getPowerType() {
        return this.powerType;
    }

    public int getSortNo() {
        return this.sortNo;
    }

    public String getTimeStr() {
        String str = this.timeStr;
        return str == null ? "" : str;
    }

    public void setCycle(String str) {
        if (str == null) {
            str = "";
        }
        this.cycle = str;
    }

    public void setCycleCron(String str) {
        if (str == null) {
            str = "";
        }
        this.cycleCron = str;
    }

    public void setDeviceId(String str) {
        if (str == null) {
            str = "";
        }
        this.deviceId = str;
    }

    public void setDeviceNo(String str) {
        if (str == null) {
            str = "";
        }
        this.deviceNo = str;
    }

    public void setId(String str) {
        if (str == null) {
            str = "";
        }
        this.id = str;
    }

    public void setKey(Long l) {
        this.key = l;
    }

    public void setPowerType(int i) {
        this.powerType = i;
    }

    public void setSortNo(int i) {
        this.sortNo = i;
    }

    public void setTimeStr(String str) {
        if (str == null) {
            str = "";
        }
        this.timeStr = str;
    }
}
