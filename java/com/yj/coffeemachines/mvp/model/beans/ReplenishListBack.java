package com.yj.coffeemachines.mvp.model.beans;

import java.util.List;

/* loaded from: classes.dex */
public class ReplenishListBack {
    private int code;
    private List<DataBean> data;
    private String msg;
    private boolean success;

    /* loaded from: classes.dex */
    public static class DataBean {
        private double capacity;
        private double expendRate;
        private String id;
        private String materialId;
        private String name;
        private String no;
        private double nowResidueQty = -1.0d;
        private String position;
        private String rawType;
        private double residueQty;
        private int speedTime;
        private int speedTimeUnit;
        private String speedUnit;
        private String unit;
        private double warnCapacity;

        public double getCapacity() {
            return this.capacity;
        }

        public double getExpendRate() {
            return this.expendRate;
        }

        public String getId() {
            String str = this.id;
            return str == null ? "" : str;
        }

        public String getMaterialId() {
            String str = this.materialId;
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

        public double getNowResidueQty() {
            double d = this.nowResidueQty;
            return d == -1.0d ? this.residueQty : d;
        }

        public String getPosition() {
            String str = this.position;
            return str == null ? "" : str;
        }

        public String getRawType() {
            String str = this.rawType;
            return str == null ? "" : str;
        }

        public double getResidueQty() {
            return this.residueQty;
        }

        public int getSpeedTime() {
            return this.speedTime;
        }

        public int getSpeedTimeUnit() {
            return this.speedTimeUnit;
        }

        public String getSpeedUnit() {
            String str = this.speedUnit;
            return str == null ? "" : str;
        }

        public String getUnit() {
            String str = this.unit;
            return str == null ? "" : str;
        }

        public double getWarnCapacity() {
            return this.warnCapacity;
        }

        public void setCapacity(double d) {
            this.capacity = d;
        }

        public void setExpendRate(double d) {
            this.expendRate = d;
        }

        public void setId(String str) {
            if (str == null) {
                str = "";
            }
            this.id = str;
        }

        public void setMaterialId(String str) {
            if (str == null) {
                str = "";
            }
            this.materialId = str;
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

        public void setNowResidueQty(double d) {
            this.nowResidueQty = d;
        }

        public void setPosition(String str) {
            if (str == null) {
                str = "";
            }
            this.position = str;
        }

        public void setRawType(String str) {
            if (str == null) {
                str = "";
            }
            this.rawType = str;
        }

        public void setResidueQty(double d) {
            this.residueQty = d;
        }

        public void setSpeedTime(int i) {
            this.speedTime = i;
        }

        public void setSpeedTimeUnit(int i) {
            this.speedTimeUnit = i;
        }

        public void setSpeedUnit(String str) {
            if (str == null) {
                str = "";
            }
            this.speedUnit = str;
        }

        public void setUnit(String str) {
            if (str == null) {
                str = "";
            }
            this.unit = str;
        }

        public void setWarnCapacity(double d) {
            this.warnCapacity = d;
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
