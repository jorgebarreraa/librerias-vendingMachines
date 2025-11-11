package com.yj.coffeemachines.greendao.beans;

/* loaded from: classes.dex */
public class FileMessage {
    private String downloadUrl;
    private String fileName;
    private String fileSize;
    private int fileType;
    private boolean isCheck;
    private boolean isLocalExists;
    private Long key;
    private String localPath;
    private String useObject;

    public FileMessage() {
    }

    public FileMessage(Long l, String str, int i, String str2, String str3, String str4, boolean z) {
        this.key = l;
        this.fileName = str;
        this.fileType = i;
        this.useObject = str2;
        this.downloadUrl = str3;
        this.localPath = str4;
        this.isCheck = z;
    }

    public String getDownloadUrl() {
        return this.downloadUrl;
    }

    public String getFileName() {
        return this.fileName;
    }

    public String getFileSize() {
        return this.fileSize;
    }

    public int getFileType() {
        return this.fileType;
    }

    public boolean getIsCheck() {
        return this.isCheck;
    }

    public Long getKey() {
        return this.key;
    }

    public String getLocalPath() {
        return this.localPath;
    }

    public String getUseObject() {
        return this.useObject;
    }

    public boolean isCheck() {
        return this.isCheck;
    }

    public boolean isLocalExists() {
        return this.isLocalExists;
    }

    public void setCheck(boolean z) {
        this.isCheck = z;
    }

    public void setDownloadUrl(String str) {
        this.downloadUrl = str;
    }

    public void setFileName(String str) {
        this.fileName = str;
    }

    public void setFileSize(String str) {
        this.fileSize = str;
    }

    public void setFileType(int i) {
        this.fileType = i;
    }

    public void setIsCheck(boolean z) {
        this.isCheck = z;
    }

    public void setKey(Long l) {
        this.key = l;
    }

    public void setLocalExists(boolean z) {
        this.isLocalExists = z;
    }

    public void setLocalPath(String str) {
        this.localPath = str;
    }

    public void setUseObject(String str) {
        this.useObject = str;
    }

    public String toString() {
        return "FileMessage{key=" + this.key + ", fileName='" + this.fileName + "', fileType='" + this.fileType + "', useObject='" + this.useObject + "', fullPath='" + this.downloadUrl + "', localPath='" + this.localPath + "'}";
    }
}
