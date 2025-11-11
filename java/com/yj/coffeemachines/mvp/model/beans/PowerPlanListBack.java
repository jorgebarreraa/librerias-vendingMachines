package com.yj.coffeemachines.mvp.model.beans;

import java.util.List;

/* loaded from: classes.dex */
public class PowerPlanListBack {
    private int code;
    private List<DataBean> data;
    private String msg;
    private boolean success;

    /* loaded from: classes.dex */
    public static class DataBean {
        private String cycle;
        private String deviceId;
        private String deviceNo;
        private String id;
        private String powerOffCycleCron;
        private String powerOffTimeStr;
        private String powerOnCycleCron;
        private String powerOnTimeStr;
        private String updateTime;
        private String updateUser;

        public String getCycle() {
            return this.cycle;
        }

        public String getDeviceId() {
            return this.deviceId;
        }

        public String getDeviceNo() {
            return this.deviceNo;
        }

        public String getId() {
            return this.id;
        }

        public String getPowerOffCycleCron() {
            return this.powerOffCycleCron;
        }

        public String getPowerOffTimeStr() {
            return this.powerOffTimeStr;
        }

        public String getPowerOnCycleCron() {
            return this.powerOnCycleCron;
        }

        public String getPowerOnTimeStr() {
            return this.powerOnTimeStr;
        }

        public String getUpdateTime() {
            return this.updateTime;
        }

        public String getUpdateUser() {
            return this.updateUser;
        }

        public void setCycle(String str) {
            this.cycle = str;
        }

        public void setDeviceId(String str) {
            this.deviceId = str;
        }

        public void setDeviceNo(String str) {
            this.deviceNo = str;
        }

        public void setId(String str) {
            this.id = str;
        }

        public void setPowerOffCycleCron(String str) {
            this.powerOffCycleCron = str;
        }

        public void setPowerOffTimeStr(String str) {
            this.powerOffTimeStr = str;
        }

        public void setPowerOnCycleCron(String str) {
            this.powerOnCycleCron = str;
        }

        public void setPowerOnTimeStr(String str) {
            this.powerOnTimeStr = str;
        }

        public void setUpdateTime(String str) {
            this.updateTime = str;
        }

        public void setUpdateUser(String str) {
            this.updateUser = str;
        }

        public String toString() {
            return "定时开关机配置{id='" + this.id + "', deviceId='" + this.deviceId + "', cycle='" + this.cycle + "', powerOnTimeStr='" + this.powerOnTimeStr + "', powerOffTimeStr='" + this.powerOffTimeStr + "', powerOnCycleCron='" + this.powerOnCycleCron + "', powerOffCycleCron='" + this.powerOffCycleCron + "', updateUser='" + this.updateUser + "', updateTime='" + this.updateTime + "', deviceNo='" + this.deviceNo + "'}";
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
        return "PowerPlanListBack{code=" + this.code + ", success=" + this.success + ", data=" + this.data + ", msg='" + this.msg + "'}";
    }
}
