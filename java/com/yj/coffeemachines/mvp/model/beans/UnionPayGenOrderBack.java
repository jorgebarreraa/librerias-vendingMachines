package com.yj.coffeemachines.mvp.model.beans;

/* loaded from: classes.dex */
public class UnionPayGenOrderBack {
    private int code;
    private DataBean data;
    private String msg;
    private boolean success;

    /* loaded from: classes.dex */
    public static class DataBean {
        private String code;
        private String currency;
        private String extra_common_param;
        private String msg;
        private String out_trade_no;
        private String poly_code_url;
        private String sign;
        private String sub_code;
        private String sub_msg;
        private String total_amount;
        private String trade_no;
        private String trade_status;
        private YsepayOnlinePolycodepayResponseBean ysepay_online_polycodepay_response;

        /* loaded from: classes.dex */
        public static class YsepayOnlinePolycodepayResponseBean {
            private String code;
            private String msg;
            private String sub_code;
            private String sub_msg;

            public String getCode() {
                return this.code;
            }

            public String getMsg() {
                return this.msg;
            }

            public String getSub_code() {
                return this.sub_code;
            }

            public String getSub_msg() {
                return this.sub_msg;
            }

            public void setCode(String str) {
                this.code = str;
            }

            public void setMsg(String str) {
                this.msg = str;
            }

            public void setSub_code(String str) {
                this.sub_code = str;
            }

            public void setSub_msg(String str) {
                this.sub_msg = str;
            }
        }

        public String getCode() {
            return this.code;
        }

        public String getCurrency() {
            return this.currency;
        }

        public String getExtra_common_param() {
            return this.extra_common_param;
        }

        public String getMsg() {
            return this.msg;
        }

        public String getOut_trade_no() {
            return this.out_trade_no;
        }

        public String getPoly_code_url() {
            return this.poly_code_url;
        }

        public String getSign() {
            return this.sign;
        }

        public String getSub_code() {
            return this.sub_code;
        }

        public String getSub_msg() {
            return this.sub_msg;
        }

        public String getTotal_amount() {
            return this.total_amount;
        }

        public String getTrade_no() {
            return this.trade_no;
        }

        public String getTrade_status() {
            return this.trade_status;
        }

        public YsepayOnlinePolycodepayResponseBean getYsepay_online_polycodepay_response() {
            return this.ysepay_online_polycodepay_response;
        }

        public void setCode(String str) {
            this.code = str;
        }

        public void setCurrency(String str) {
            this.currency = str;
        }

        public void setExtra_common_param(String str) {
            this.extra_common_param = str;
        }

        public void setMsg(String str) {
            this.msg = str;
        }

        public void setOut_trade_no(String str) {
            this.out_trade_no = str;
        }

        public void setPoly_code_url(String str) {
            this.poly_code_url = str;
        }

        public void setSign(String str) {
            this.sign = str;
        }

        public void setSub_code(String str) {
            this.sub_code = str;
        }

        public void setSub_msg(String str) {
            this.sub_msg = str;
        }

        public void setTotal_amount(String str) {
            this.total_amount = str;
        }

        public void setTrade_no(String str) {
            this.trade_no = str;
        }

        public void setTrade_status(String str) {
            this.trade_status = str;
        }

        public void setYsepay_online_polycodepay_response(YsepayOnlinePolycodepayResponseBean ysepayOnlinePolycodepayResponseBean) {
            this.ysepay_online_polycodepay_response = ysepayOnlinePolycodepayResponseBean;
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
        return "UnionPayGenOrderBack{code=" + this.code + ", data=" + this.data + ", msg='" + this.msg + "', success=" + this.success + '}';
    }
}
