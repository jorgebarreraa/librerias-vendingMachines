package com.yj.coffeemachines.mvp.model.beans;

import java.util.List;

/* loaded from: classes.dex */
public class CleanPlanListBack {
    private int code;
    private List<DataBean> data;
    private String msg;
    private boolean success;

    /* loaded from: classes.dex */
    public static class DataBean {
        private String deviceId;
        private boolean freshly;
        private String id;
        private boolean instant;
        private List<ItemsBean> items;
        private String planName;
        private int status;

        /* loaded from: classes.dex */
        public static class ItemsBean {
            private String cleanPlanId;
            private String cycle;
            private String cycleCron;
            private String deviceId;
            private String id;
            private String timeStr;
            private int times;

            public String getCleanPlanId() {
                return this.cleanPlanId;
            }

            public String getCycle() {
                return this.cycle;
            }

            public String getCycleCron() {
                return this.cycleCron;
            }

            public String getDeviceId() {
                return this.deviceId;
            }

            public String getId() {
                return this.id;
            }

            public String getTimeStr() {
                return this.timeStr;
            }

            public int getTimes() {
                return this.times;
            }

            public void setCleanPlanId(String str) {
                this.cleanPlanId = str;
            }

            public void setCycle(String str) {
                this.cycle = str;
            }

            public void setCycleCron(String str) {
                this.cycleCron = str;
            }

            public void setDeviceId(String str) {
                this.deviceId = str;
            }

            public void setId(String str) {
                this.id = str;
            }

            public void setTimeStr(String str) {
                this.timeStr = str;
            }

            public void setTimes(int i) {
                this.times = i;
            }
        }

        public String getDeviceId() {
            return this.deviceId;
        }

        public boolean getFreshly() {
            return this.freshly;
        }

        public String getId() {
            return this.id;
        }

        public boolean getInstant() {
            return this.instant;
        }

        public List<ItemsBean> getItems() {
            return this.items;
        }

        public String getPlanName() {
            return this.planName;
        }

        public int getStatus() {
            return this.status;
        }

        public void setDeviceId(String str) {
            this.deviceId = str;
        }

        public void setFreshly(boolean z) {
            this.freshly = z;
        }

        public void setId(String str) {
            this.id = str;
        }

        public void setInstant(boolean z) {
            this.instant = z;
        }

        public void setItems(List<ItemsBean> list) {
            this.items = list;
        }

        public void setPlanName(String str) {
            this.planName = str;
        }

        public void setStatus(int i) {
            this.status = i;
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
}
