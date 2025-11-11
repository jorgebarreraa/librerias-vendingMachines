package com.yj.coffeemachines.mvp.model.beans;

/* loaded from: classes.dex */
public class UploadFileBack {
    private int code;
    private DataBean data;
    private String msg;
    private boolean success;

    /* loaded from: classes.dex */
    public static class DataBean {
        private String bucketName;
        private String createTime;
        private int createUser;
        private String domain;
        private Double fileSize;
        private String fileType;
        private String fullPath;
        private String id;
        private int isDeleted;
        private String localFilePath;
        private String md5;
        private String originalName;
        private String path;
        private int refCount;
        private String remark;
        private int status;
        private String updateTime;
        private int updateUser;

        public String getBucketName() {
            return this.bucketName;
        }

        public String getCreateTime() {
            return this.createTime;
        }

        public int getCreateUser() {
            return this.createUser;
        }

        public String getDomain() {
            return this.domain;
        }

        public Double getFileSize() {
            return this.fileSize;
        }

        public String getFileType() {
            return this.fileType;
        }

        public String getFullPath() {
            return this.fullPath;
        }

        public String getId() {
            return this.id;
        }

        public int getIsDeleted() {
            return this.isDeleted;
        }

        public String getLocalFilePath() {
            return this.localFilePath;
        }

        public String getMd5() {
            return this.md5;
        }

        public String getOriginalName() {
            return this.originalName;
        }

        public String getPath() {
            return this.path;
        }

        public int getRefCount() {
            return this.refCount;
        }

        public String getRemark() {
            return this.remark;
        }

        public int getStatus() {
            return this.status;
        }

        public String getUpdateTime() {
            return this.updateTime;
        }

        public int getUpdateUser() {
            return this.updateUser;
        }

        public void setBucketName(String str) {
            this.bucketName = str;
        }

        public void setCreateTime(String str) {
            this.createTime = str;
        }

        public void setCreateUser(int i) {
            this.createUser = i;
        }

        public void setDomain(String str) {
            this.domain = str;
        }

        public void setFileSize(Double d) {
            this.fileSize = d;
        }

        public void setFileType(String str) {
            this.fileType = str;
        }

        public void setFullPath(String str) {
            this.fullPath = str;
        }

        public void setId(String str) {
            this.id = str;
        }

        public void setIsDeleted(int i) {
            this.isDeleted = i;
        }

        public void setLocalFilePath(String str) {
            this.localFilePath = str;
        }

        public void setMd5(String str) {
            this.md5 = str;
        }

        public void setOriginalName(String str) {
            this.originalName = str;
        }

        public void setPath(String str) {
            this.path = str;
        }

        public void setRefCount(int i) {
            this.refCount = i;
        }

        public void setRemark(String str) {
            this.remark = str;
        }

        public void setStatus(int i) {
            this.status = i;
        }

        public void setUpdateTime(String str) {
            this.updateTime = str;
        }

        public void setUpdateUser(int i) {
            this.updateUser = i;
        }
    }

    public int getCode() {
        return this.code;
    }

    public DataBean getData() {
        return this.data;
    }

    public String getMsg() {
        return this.msg;
    }

    public boolean getSuccess() {
        return this.success;
    }

    public void setCode(int i) {
        this.code = i;
    }

    public void setData(DataBean dataBean) {
        this.data = dataBean;
    }

    public void setMsg(String str) {
        this.msg = str;
    }

    public void setSuccess(boolean z) {
        this.success = z;
    }
}
