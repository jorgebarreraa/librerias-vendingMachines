package com.yj.coffeemachines.mvp.model.beans;

import java.util.List;

/* loaded from: classes.dex */
public class ProgramPlanListBack {
    private int code;
    private List<DataBean> data;
    private String msg;
    private boolean success;

    /* loaded from: classes.dex */
    public static class DataBean {
        private String deviceId;
        private String deviceNo;
        private String endTime;
        private String fullPath;
        private String id;
        private String name;
        private String no;
        private int sort;
        private String startTime;

        public String getDeviceId() {
            return this.deviceId;
        }

        public String getDeviceNo() {
            return this.deviceNo;
        }

        public String getEndTime() {
            return this.endTime;
        }

        public String getFullPath() {
            return this.fullPath;
        }

        public String getId() {
            return this.id;
        }

        public String getName() {
            return this.name;
        }

        public String getNo() {
            return this.no;
        }

        public int getSort() {
            return this.sort;
        }

        public String getStartTime() {
            return this.startTime;
        }

        public void setDeviceId(String str) {
            this.deviceId = str;
        }

        public void setDeviceNo(String str) {
            this.deviceNo = str;
        }

        public void setEndTime(String str) {
            this.endTime = str;
        }

        public void setFullPath(String str) {
            this.fullPath = str;
        }

        public void setId(String str) {
            this.id = str;
        }

        public void setName(String str) {
            this.name = str;
        }

        public void setNo(String str) {
            this.no = str;
        }

        public void setSort(int i) {
            this.sort = i;
        }

        public void setStartTime(String str) {
            this.startTime = str;
        }

        public String toString() {
            return "设备广告信息{id='" + this.id + "', no='" + this.no + "', name='" + this.name + "', fullPath='" + this.fullPath + "', deviceId='" + this.deviceId + "', deviceNo='" + this.deviceNo + "', startTime='" + this.startTime + "', sort=" + this.sort + ", endTime='" + this.endTime + "'}";
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
        return "ProgramPlanListBack{code=" + this.code + ", success=" + this.success + ", data=" + this.data + ", msg='" + this.msg + "'}";
    }
}
