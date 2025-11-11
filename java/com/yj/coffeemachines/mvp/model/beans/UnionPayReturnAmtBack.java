package com.yj.coffeemachines.mvp.model.beans;

/* loaded from: classes.dex */
public class UnionPayReturnAmtBack {
    private int code;
    private DataBean data;
    private String msg;
    private boolean success;

    /* loaded from: classes.dex */
    public static class DataBean {
        private String code;
        private String msg;
        private String sign;
        private String sub_code;
        private String sub_msg;
        private YsepayOnlineTradeRefundResponseBean ysepay_online_trade_refund_response;

        /* loaded from: classes.dex */
        public static class YsepayOnlineTradeRefundResponseBean {
            private String account_date;
            private String code;
            private String msg;
            private String out_request_no;
            private String out_trade_no;
            private String refund_amount;
            private String refund_sn;
            private String trade_no;

            public String getAccount_date() {
                return this.account_date;
            }

            public String getCode() {
                return this.code;
            }

            public String getMsg() {
                return this.msg;
            }

            public String getOut_request_no() {
                return this.out_request_no;
            }

            public String getOut_trade_no() {
                return this.out_trade_no;
            }

            public String getRefund_amount() {
                return this.refund_amount;
            }

            public String getRefund_sn() {
                return this.refund_sn;
            }

            public String getTrade_no() {
                return this.trade_no;
            }

            public void setAccount_date(String str) {
                this.account_date = str;
            }

            public void setCode(String str) {
                this.code = str;
            }

            public void setMsg(String str) {
                this.msg = str;
            }

            public void setOut_request_no(String str) {
                this.out_request_no = str;
            }

            public void setOut_trade_no(String str) {
                this.out_trade_no = str;
            }

            public void setRefund_amount(String str) {
                this.refund_amount = str;
            }

            public void setRefund_sn(String str) {
                this.refund_sn = str;
            }

            public void setTrade_no(String str) {
                this.trade_no = str;
            }

            public String toString() {
                return "YsepayOnlineTradeRefundResponseBean{account_date='" + this.account_date + "', code='" + this.code + "', msg='" + this.msg + "', out_request_no='" + this.out_request_no + "', out_trade_no='" + this.out_trade_no + "', refund_amount='" + this.refund_amount + "', refund_sn='" + this.refund_sn + "', trade_no='" + this.trade_no + "'}";
            }
        }

        public String getCode() {
            return this.code;
        }

        public String getMsg() {
            return this.msg;
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

        public YsepayOnlineTradeRefundResponseBean getYsepay_online_trade_refund_response() {
            return this.ysepay_online_trade_refund_response;
        }

        public void setCode(String str) {
            this.code = str;
        }

        public void setMsg(String str) {
            this.msg = str;
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

        public void setYsepay_online_trade_refund_response(YsepayOnlineTradeRefundResponseBean ysepayOnlineTradeRefundResponseBean) {
            this.ysepay_online_trade_refund_response = ysepayOnlineTradeRefundResponseBean;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("DataBean{code='");
            sb.append(this.code);
            sb.append('\'');
            sb.append(", msg='");
            sb.append(this.msg);
            sb.append('\'');
            sb.append(", sign='");
            sb.append(this.sign);
            sb.append('\'');
            sb.append(", sub_code='");
            sb.append(this.sub_code);
            sb.append('\'');
            sb.append(", sub_msg='");
            sb.append(this.sub_msg);
            sb.append('\'');
            sb.append(", ysepay_online_trade_refund_response=");
            YsepayOnlineTradeRefundResponseBean ysepayOnlineTradeRefundResponseBean = this.ysepay_online_trade_refund_response;
            sb.append(ysepayOnlineTradeRefundResponseBean == null ? "" : ysepayOnlineTradeRefundResponseBean.toString());
            sb.append('}');
            return sb.toString();
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
        StringBuilder sb = new StringBuilder();
        sb.append("UnionPayReturnAmtBack{code=");
        sb.append(this.code);
        sb.append(", data=");
        DataBean dataBean = this.data;
        sb.append(dataBean == null ? "" : dataBean.toString());
        sb.append(", msg='");
        sb.append(this.msg);
        sb.append('\'');
        sb.append(", success=");
        sb.append(this.success);
        sb.append('}');
        return sb.toString();
    }
}
