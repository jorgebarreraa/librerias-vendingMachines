package com.yj.coffeemachines.mvp.model.beans;

/* loaded from: classes.dex */
public class GetUiInfoBack {
    private int code;
    private DataBean data;
    private String msg;
    private boolean success;

    /* loaded from: classes.dex */
    public static class DataBean {
        private String deviceId;
        private String deviceNo;
        private String fullPath;
        private String id;
        private String path;
        private String uiId;

        public String getDeviceId() {
            return this.deviceId;
        }

        public String getDeviceNo() {
            return this.deviceNo;
        }

        public String getFullPath() {
            return this.fullPath;
        }

        public String getId() {
            return this.id;
        }

        public String getPath() {
            return this.path;
        }

        public String getUiId() {
            return this.uiId;
        }

        public void setDeviceId(String str) {
            this.deviceId = str;
        }

        public void setDeviceNo(String str) {
            this.deviceNo = str;
        }

        public void setFullPath(String str) {
            this.fullPath = str;
        }

        public void setId(String str) {
            this.id = str;
        }

        public void setPath(String str) {
            this.path = str;
        }

        public void setUiId(String str) {
            this.uiId = str;
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
