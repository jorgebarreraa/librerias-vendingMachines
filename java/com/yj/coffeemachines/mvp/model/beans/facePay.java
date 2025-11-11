package com.yj.coffeemachines.mvp.model.beans;

import com.yj.coffeemachines.mvp.model.beans.refund;

/* loaded from: classes.dex */
public class facePay {
    private int code;
    private refund.DataBean data;
    private String msg;
    private boolean success;

    /* loaded from: classes.dex */
    public static class DataBean {
        private String attach;
        private String channel_order_no;
        private String channel_trade_no;
        private String merchant_name;
        private String merchant_no;
        private String out_trade_no;
        private String pay_type;
        private String receipt_fee;
        private String result_code;
        private String return_code;
        private String return_msg;
        private String terminal_id;
        private String terminal_time;
        private String terminal_trace;
        private String total_fee;
        private String user_id;

        public String getAttach() {
            return this.attach;
        }

        public String getChannel_order_no() {
            return this.channel_order_no;
        }

        public String getChannel_trade_no() {
            return this.channel_trade_no;
        }

        public String getMerchant_name() {
            return this.merchant_name;
        }

        public String getMerchant_no() {
            return this.merchant_no;
        }

        public String getOut_trade_no() {
            return this.out_trade_no;
        }

        public String getPay_type() {
            return this.pay_type;
        }

        public String getReceipt_fee() {
            return this.receipt_fee;
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

        public String getTerminal_id() {
            return this.terminal_id;
        }

        public String getTerminal_time() {
            return this.terminal_time;
        }

        public String getTerminal_trace() {
            return this.terminal_trace;
        }

        public String getTotal_fee() {
            return this.total_fee;
        }

        public String getUser_id() {
            return this.user_id;
        }

        public void setAttach(String str) {
            this.attach = str;
        }

        public void setChannel_order_no(String str) {
            this.channel_order_no = str;
        }

        public void setChannel_trade_no(String str) {
            this.channel_trade_no = str;
        }

        public void setMerchant_name(String str) {
            this.merchant_name = str;
        }

        public void setMerchant_no(String str) {
            this.merchant_no = str;
        }

        public void setOut_trade_no(String str) {
            this.out_trade_no = str;
        }

        public void setPay_type(String str) {
            this.pay_type = str;
        }

        public void setReceipt_fee(String str) {
            this.receipt_fee = str;
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

        public void setTerminal_id(String str) {
            this.terminal_id = str;
        }

        public void setTerminal_time(String str) {
            this.terminal_time = str;
        }

        public void setTerminal_trace(String str) {
            this.terminal_trace = str;
        }

        public void setTotal_fee(String str) {
            this.total_fee = str;
        }

        public void setUser_id(String str) {
            this.user_id = str;
        }

        public String toString() {
            return "DataBean{attach='" + this.attach + "', channel_order_no='" + this.channel_order_no + "', channel_trade_no='" + this.channel_trade_no + "', merchant_name='" + this.merchant_name + "', merchant_no='" + this.merchant_no + "', out_trade_no='" + this.out_trade_no + "', pay_type='" + this.pay_type + "', receipt_fee='" + this.receipt_fee + "', result_code='" + this.result_code + "', return_code='" + this.return_code + "', return_msg='" + this.return_msg + "', terminal_id='" + this.terminal_id + "', terminal_time='" + this.terminal_time + "', terminal_trace='" + this.terminal_trace + "', total_fee='" + this.total_fee + "', user_id='" + this.user_id + "'}";
        }
    }

    public int getCode() {
        return this.code;
    }

    public refund.DataBean getData() {
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

    public void setData(refund.DataBean dataBean) {
        this.data = dataBean;
    }

    public void setMsg(String str) {
        this.msg = str;
    }

    public void setSuccess(boolean z) {
        this.success = z;
    }

    public String toString() {
        return "刷脸支付refund{code=" + this.code + ", success=" + this.success + ", data=" + this.data + ", msg='" + this.msg + "'}";
    }
}
