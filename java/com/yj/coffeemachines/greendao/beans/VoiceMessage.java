package com.yj.coffeemachines.greendao.beans;

/* loaded from: classes.dex */
public class VoiceMessage {
    private String deviceId;
    private String deviceNo;
    private String fullPath;
    private Long key;
    private String localPath;
    private String path;
    private String positionId;
    private String positionSort;

    public VoiceMessage() {
    }

    public VoiceMessage(Long l, String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        this.key = l;
        this.positionId = str;
        this.deviceId = str2;
        this.deviceNo = str3;
        this.positionSort = str4;
        this.path = str5;
        this.fullPath = str6;
        this.localPath = str7;
    }

    public String getDeviceId() {
        String str = this.deviceId;
        return str == null ? "" : str;
    }

    public String getDeviceNo() {
        String str = this.deviceNo;
        return str == null ? "" : str;
    }

    public String getFullPath() {
        String str = this.fullPath;
        return str == null ? "" : str;
    }

    public Long getKey() {
        return this.key;
    }

    public String getLocalPath() {
        String str = this.localPath;
        return str == null ? "" : str;
    }

    public String getPath() {
        String str = this.path;
        return str == null ? "" : str;
    }

    public String getPositionId() {
        String str = this.positionId;
        return str == null ? "" : str;
    }

    public String getPositionSort() {
        String str = this.positionSort;
        return str == null ? "" : str;
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

    public void setFullPath(String str) {
        if (str == null) {
            str = "";
        }
        this.fullPath = str;
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

    public void setPath(String str) {
        if (str == null) {
            str = "";
        }
        this.path = str;
    }

    public void setPositionId(String str) {
        if (str == null) {
            str = "";
        }
        this.positionId = str;
    }

    public void setPositionSort(String str) {
        if (str == null) {
            str = "";
        }
        this.positionSort = str;
    }

    public String toString() {
        return "VoiceMessage{key=" + this.key + ", positionId='" + this.positionId + "', deviceId='" + this.deviceId + "', deviceNo='" + this.deviceNo + "', positionSort='" + this.positionSort + "', path='" + this.path + "', fullPath='" + this.fullPath + "', localPath='" + this.localPath + "'}";
    }
}
