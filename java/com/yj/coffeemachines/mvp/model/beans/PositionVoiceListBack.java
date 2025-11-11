package com.yj.coffeemachines.mvp.model.beans;

import java.util.List;

/* loaded from: classes.dex */
public class PositionVoiceListBack {
    private int code;
    private List<DataBean> data;
    private String msg;
    private boolean success;

    /* loaded from: classes.dex */
    public static class DataBean {
        private String deviceId;
        private String deviceNo;
        private String fullPath;
        private String localPath;
        private String path;
        private String positionId;
        private String positionSort;

        public String getDeviceId() {
            return this.deviceId;
        }

        public String getDeviceNo() {
            return this.deviceNo;
        }

        public String getFullPath() {
            return this.fullPath;
        }

        public String getPath() {
            return this.path;
        }

        public String getPositionId() {
            return this.positionId;
        }

        public String getPositionSort() {
            return this.positionSort;
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

        public void setPath(String str) {
            this.path = str;
        }

        public void setPositionId(String str) {
            this.positionId = str;
        }

        public void setPositionSort(String str) {
            this.positionSort = str;
        }

        public String toString() {
            return "DataBean{positionId='" + this.positionId + "', deviceId='" + this.deviceId + "', deviceNo='" + this.deviceNo + "', positionSort='" + this.positionSort + "', path='" + this.path + "', fullPath='" + this.fullPath + "', localPath='" + this.localPath + "'}";
        }
    }

    public int getCode() {
        return this.code;
    }

    public List<DataBean> getData() {
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

    public void setData(List<DataBean> list) {
        this.data = list;
    }

    public void setMsg(String str) {
        this.msg = str;
    }

    public void setSuccess(boolean z) {
        this.success = z;
    }

    public String toString() {
        return "PositionVoiceListBack{code=" + this.code + ", success=" + this.success + ", data=" + this.data + ", msg='" + this.msg + "'}";
    }
}
