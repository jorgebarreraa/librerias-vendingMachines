package com.yj.coffeemachines.mvp.model.beans;

/* loaded from: classes.dex */
public class icbcPayGenOrderBack {
    private int code;
    private DataBean data;
    private String msg;
    private boolean success;

    /* loaded from: classes.dex */
    public static class DataBean {
        private String attach;
        private String msg_id;
        private String outTradeNo;
        private String qrcode;
        private String return_code;
        private String return_msg;

        public String getAttach() {
            return this.attach;
        }

        public String getMsg_id() {
            return this.msg_id;
        }

        public String getOutTradeNo() {
            return this.outTradeNo;
        }

        public String getQrcode() {
            return this.qrcode;
        }

        public String getReturn_code() {
            return this.return_code;
        }

        public String getReturn_msg() {
            return this.return_msg;
        }

        public void setAttach(String str) {
            this.attach = str;
        }

        public void setMsg_id(String str) {
            this.msg_id = str;
        }

        public void setOutTradeNo(String str) {
            this.outTradeNo = str;
        }

        public void setQrcode(String str) {
            this.qrcode = str;
        }

        public void setReturn_code(String str) {
            this.return_code = str;
        }

        public void setReturn_msg(String str) {
            this.return_msg = str;
        }

        public String toString() {
            return "DataBean{attach='" + this.attach + "', msg_id='" + this.msg_id + "', qrcode='" + this.qrcode + "', return_code='" + this.return_code + "', return_msg='" + this.return_msg + "', outTradeNo='" + this.outTradeNo + "'}";
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
