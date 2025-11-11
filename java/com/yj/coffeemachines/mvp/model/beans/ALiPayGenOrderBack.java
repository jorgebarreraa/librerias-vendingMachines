package com.yj.coffeemachines.mvp.model.beans;

/* loaded from: classes.dex */
public class ALiPayGenOrderBack {
    private int code;
    private DataBean data;
    private String msg;
    private boolean success;

    /* loaded from: classes.dex */
    public static class DataBean {
        private String code;
        private String out_trade_no;
        private String qr_code;
        private String sub_msg;

        public String getCode() {
            return this.code;
        }

        public String getOut_trade_no() {
            return this.out_trade_no;
        }

        public String getQr_code() {
            return this.qr_code;
        }

        public String getSub_msg() {
            String str = this.sub_msg;
            return str == null ? "" : str;
        }

        public void setCode(String str) {
            this.code = str;
        }

        public void setOut_trade_no(String str) {
            this.out_trade_no = str;
        }

        public void setQr_code(String str) {
            this.qr_code = str;
        }

        public void setSub_msg(String str) {
            if (str == null) {
                str = "";
            }
            this.sub_msg = str;
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
