package com.yj.coffeemachines.mvp.model.beans;

/* loaded from: classes.dex */
public class QueryPayResulBack {
    private int code;
    private DataBean data;
    private String msg;
    private boolean success;

    /* loaded from: classes.dex */
    public static class DataBean {
        private String orderId;
        private int status;

        public String getOrderId() {
            return this.orderId;
        }

        public int getStatus() {
            return this.status;
        }

        public void setOrderId(String str) {
            this.orderId = str;
        }

        public void setStatus(int i) {
            this.status = i;
        }

        public String toString() {
            return "DataBean{status=" + this.status + ", orderId='" + this.orderId + "'}";
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

    public String toString() {
        return "QueryPayResulBack{code=" + this.code + ", success=" + this.success + ", data={" + this.data.toString() + "}, msg='" + this.msg + "'}";
    }
}
