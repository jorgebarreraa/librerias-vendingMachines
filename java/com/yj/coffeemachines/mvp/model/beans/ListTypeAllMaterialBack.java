package com.yj.coffeemachines.mvp.model.beans;

import java.util.List;

/* loaded from: classes.dex */
public class ListTypeAllMaterialBack {
    private int code;
    private List<DataBean> data;
    private String msg;
    private boolean success;

    /* loaded from: classes.dex */
    public static class DataBean {
        private Double capacity;
        private String createTime;
        private String createUser;
        private String deviceTypeId;
        private String deviceTypeName;
        private Double expendRate;
        private String id;
        private int isDeleted;
        private String name;
        private String no;
        private String position;
        private String rawType;
        private int speedTime;
        private int speedTimeUnit;
        private String speedUnit;
        private int status;
        private String unit;
        private String updateTime;
        private String updateUser;
        private Double warnCapacity;

        public Double getCapacity() {
            return this.capacity;
        }

        public String getCreateTime() {
            return this.createTime;
        }

        public String getCreateUser() {
            return this.createUser;
        }

        public String getDeviceTypeId() {
            return this.deviceTypeId;
        }

        public String getDeviceTypeName() {
            return this.deviceTypeName;
        }

        public Double getExpendRate() {
            return this.expendRate;
        }

        public String getId() {
            return this.id;
        }

        public int getIsDeleted() {
            return this.isDeleted;
        }

        public String getName() {
            return this.name;
        }

        public String getNo() {
            return this.no;
        }

        public String getPosition() {
            return this.position;
        }

        public String getRawType() {
            return this.rawType;
        }

        public int getSpeedTime() {
            return this.speedTime;
        }

        public int getSpeedTimeUnit() {
            return this.speedTimeUnit;
        }

        public String getSpeedUnit() {
            return this.speedUnit;
        }

        public int getStatus() {
            return this.status;
        }

        public String getUnit() {
            return this.unit;
        }

        public String getUpdateTime() {
            return this.updateTime;
        }

        public String getUpdateUser() {
            return this.updateUser;
        }

        public Double getWarnCapacity() {
            return this.warnCapacity;
        }

        public void setCapacity(Double d) {
            this.capacity = d;
        }

        public void setCreateTime(String str) {
            this.createTime = str;
        }

        public void setCreateUser(String str) {
            this.createUser = str;
        }

        public void setDeviceTypeId(String str) {
            this.deviceTypeId = str;
        }

        public void setDeviceTypeName(String str) {
            this.deviceTypeName = str;
        }

        public void setExpendRate(Double d) {
            this.expendRate = d;
        }

        public void setId(String str) {
            this.id = str;
        }

        public void setIsDeleted(int i) {
            this.isDeleted = i;
        }

        public void setName(String str) {
            this.name = str;
        }

        public void setNo(String str) {
            this.no = str;
        }

        public void setPosition(String str) {
            this.position = str;
        }

        public void setRawType(String str) {
            this.rawType = str;
        }

        public void setSpeedTime(int i) {
            this.speedTime = i;
        }

        public void setSpeedTimeUnit(int i) {
            this.speedTimeUnit = i;
        }

        public void setSpeedUnit(String str) {
            this.speedUnit = str;
        }

        public void setStatus(int i) {
            this.status = i;
        }

        public void setUnit(String str) {
            this.unit = str;
        }

        public void setUpdateTime(String str) {
            this.updateTime = str;
        }

        public void setUpdateUser(String str) {
            this.updateUser = str;
        }

        public void setWarnCapacity(Double d) {
            this.warnCapacity = d;
        }

        public String toString() {
            return "原料信息{createUser='" + this.createUser + "', createTime='" + this.createTime + "', updateUser='" + this.updateUser + "', updateTime='" + this.updateTime + "', status=" + this.status + ", isDeleted=" + this.isDeleted + ", id='" + this.id + "', deviceTypeId='" + this.deviceTypeId + "', no='" + this.no + "', rawType='" + this.rawType + "', name='" + this.name + "', capacity=" + this.capacity + ", position='" + this.position + "', unit='" + this.unit + "', expendRate=" + this.expendRate + ", warnCapacity=" + this.warnCapacity + ", speedTime=" + this.speedTime + ", speedTimeUnit=" + this.speedTimeUnit + ", speedUnit='" + this.speedUnit + "', deviceTypeName='" + this.deviceTypeName + "'}";
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
        return "获取到的消息ListTypeAllMaterialBack{code=" + this.code + ", success=" + this.success + ", data=" + this.data + ", msg='" + this.msg + "'}";
    }
}
