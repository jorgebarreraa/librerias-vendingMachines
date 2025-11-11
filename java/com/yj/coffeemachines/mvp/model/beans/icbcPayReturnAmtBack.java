package com.yj.coffeemachines.mvp.model.beans;

/* loaded from: classes.dex */
public class icbcPayReturnAmtBack {
    private int code;
    private DataBean data;
    private String msg;
    private boolean success;

    /* loaded from: classes.dex */
    public static class DataBean {
        private String custId;
        private String msg_id;
        private String realRejectAmt;
        private String rejectAmt;
        private String rejectNo;
        private String returnCode;
        private String returnMsg;
        private String traceNo;

        public String getCustId() {
            return this.custId;
        }

        public String getMsg_id() {
            return this.msg_id;
        }

        public String getRealRejectAmt() {
            return this.realRejectAmt;
        }

        public String getRejectAmt() {
            return this.rejectAmt;
        }

        public String getRejectNo() {
            return this.rejectNo;
        }

        public String getReturnCode() {
            return this.returnCode;
        }

        public String getReturnMsg() {
            return this.returnMsg;
        }

        public String getTraceNo() {
            return this.traceNo;
        }

        public void setCustId(String str) {
            this.custId = str;
        }

        public void setMsg_id(String str) {
            this.msg_id = str;
        }

        public void setRealRejectAmt(String str) {
            this.realRejectAmt = str;
        }

        public void setRejectAmt(String str) {
            this.rejectAmt = str;
        }

        public void setRejectNo(String str) {
            this.rejectNo = str;
        }

        public void setReturnCode(String str) {
            this.returnCode = str;
        }

        public void setReturnMsg(String str) {
            this.returnMsg = str;
        }

        public void setTraceNo(String str) {
            this.traceNo = str;
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

    public boolean isSuccess() {
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
