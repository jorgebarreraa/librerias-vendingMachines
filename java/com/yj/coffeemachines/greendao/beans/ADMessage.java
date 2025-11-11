package com.yj.coffeemachines.greendao.beans;

/* loaded from: classes.dex */
public class ADMessage {
    private String deviceId;
    private String deviceNo;
    private String endTime;
    private Long endTime_long;
    private String fullPath;
    private String id;
    private Long key;
    private String localPath;
    private String name;
    private String no;
    private int sort;
    private String startTime;
    private Long startTime_long;

    public ADMessage() {
    }

    public ADMessage(Long l, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, Long l2, int i, String str9, Long l3) {
        this.key = l;
        this.id = str;
        this.no = str2;
        this.name = str3;
        this.fullPath = str4;
        this.localPath = str5;
        this.deviceId = str6;
        this.deviceNo = str7;
        this.startTime = str8;
        this.startTime_long = l2;
        this.sort = i;
        this.endTime = str9;
        this.endTime_long = l3;
    }

    public String getDeviceId() {
        String str = this.deviceId;
        return str == null ? "" : str;
    }

    public String getDeviceNo() {
        String str = this.deviceNo;
        return str == null ? "" : str;
    }

    public String getEndTime() {
        String str = this.endTime;
        return str == null ? "" : str;
    }

    public Long getEndTime_long() {
        return this.endTime_long;
    }

    public String getFullPath() {
        String str = this.fullPath;
        return str == null ? "" : str;
    }

    public String getId() {
        String str = this.id;
        return str == null ? "" : str;
    }

    public Long getKey() {
        return this.key;
    }

    public String getLocalPath() {
        String str = this.localPath;
        return str == null ? "" : str;
    }

    public String getName() {
        String str = this.name;
        return str == null ? "" : str;
    }

    public String getNo() {
        String str = this.no;
        return str == null ? "" : str;
    }

    public int getSort() {
        return this.sort;
    }

    public String getStartTime() {
        String str = this.startTime;
        return str == null ? "" : str;
    }

    public Long getStartTime_long() {
        return this.startTime_long;
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

    public void setEndTime(String str) {
        if (str == null) {
            str = "";
        }
        this.endTime = str;
    }

    public void setEndTime_long(Long l) {
        this.endTime_long = l;
    }

    public void setFullPath(String str) {
        if (str == null) {
            str = "";
        }
        this.fullPath = str;
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

    public void setLocalPath(String str) {
        if (str == null) {
            str = "";
        }
        this.localPath = str;
    }

    public void setName(String str) {
        if (str == null) {
            str = "";
        }
        this.name = str;
    }

    public void setNo(String str) {
        if (str == null) {
            str = "";
        }
        this.no = str;
    }

    public void setSort(int i) {
        this.sort = i;
    }

    public void setStartTime(String str) {
        if (str == null) {
            str = "";
        }
        this.startTime = str;
    }

    public void setStartTime_long(Long l) {
        this.startTime_long = l;
    }
}
