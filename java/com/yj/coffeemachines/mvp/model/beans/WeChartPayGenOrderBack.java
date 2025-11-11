package com.yj.coffeemachines.mvp.model.beans;

/* loaded from: classes.dex */
public class WeChartPayGenOrderBack {
    private int code;
    private DataBean data;
    private String msg;
    private boolean success;

    /* loaded from: classes.dex */
    public static class DataBean {
        private String appid;
        private String code_url;
        private String mch_id;
        private String nonce_str;
        private String out_trade_no;
        private String prepay_id;
        private String result_code;
        private String return_code;
        private String return_msg;
        private String sign;
        private String trade_type;

        public String getAppid() {
            return this.appid;
        }

        public String getCode_url() {
            return this.code_url;
        }

        public String getMch_id() {
            return this.mch_id;
        }

        public String getNonce_str() {
            return this.nonce_str;
        }

        public String getOut_trade_no() {
            String str = this.out_trade_no;
            return str == null ? "" : str;
        }

        public String getPrepay_id() {
            return this.prepay_id;
        }

        public String getResult_code() {
            return this.result_code;
        }

        public String getReturn_code() {
            return this.return_code;
        }

        public String getReturn_msg() {
            return this.return_msg;
        }

        public String getSign() {
            return this.sign;
        }

        public String getTrade_type() {
            return this.trade_type;
        }

        public void setAppid(String str) {
            this.appid = str;
        }

        public void setCode_url(String str) {
            this.code_url = str;
        }

        public void setMch_id(String str) {
            this.mch_id = str;
        }

        public void setNonce_str(String str) {
            this.nonce_str = str;
        }

        public void setOut_trade_no(String str) {
            if (str == null) {
                str = "";
            }
            this.out_trade_no = str;
        }

        public void setPrepay_id(String str) {
            this.prepay_id = str;
        }

        public void setResult_code(String str) {
            this.result_code = str;
        }

        public void setReturn_code(String str) {
            this.return_code = str;
        }

        public void setReturn_msg(String str) {
            this.return_msg = str;
        }

        public void setSign(String str) {
            this.sign = str;
        }

        public void setTrade_type(String str) {
            this.trade_type = str;
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
