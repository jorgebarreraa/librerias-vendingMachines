package com.yj.coffeemachines.mvp.model.beans;

import com.yj.coffeemachines.mvp.model.beans.refund;

/* loaded from: classes.dex */
public class queryrefund {
    private int code;
    private refund.DataBean data;
    private String msg;
    private boolean success;

    /* loaded from: classes.dex */
    public static class DataBean {
        private String attach;
        private String channel_order_no;
        private String channel_trade_no;
        private String device_no;
        private String end_time;
        private String merchant_name;
        private String merchant_no;
        private String out_refund_no;
        private String out_trade_no;
        private String pay_time;
        private String pay_trace;
        private String pay_type;
        private String refund_buyer_pay_fee;
        private String refund_fee;
        private String refund_merchant_discount_fee;
        private String refund_platform_discount_fee;
        private String refund_promotion_detail;
        private String refund_receipt_fee;
        private String result_code;
        private String return_code;
        private String return_msg;
        private String terminal_id;
        private String terminal_time;
        private String terminal_trace;
        private String trade_state;
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

        public String getDevice_no() {
            return this.device_no;
        }

        public String getEnd_time() {
            return this.end_time;
        }

        public String getMerchant_name() {
            return this.merchant_name;
        }

        public String getMerchant_no() {
            return this.merchant_no;
        }

        public String getOut_refund_no() {
            return this.out_refund_no;
        }

        public String getOut_trade_no() {
            return this.out_trade_no;
        }

        public String getPay_time() {
            return this.pay_time;
        }

        public String getPay_trace() {
            return this.pay_trace;
        }

        public String getPay_type() {
            return this.pay_type;
        }

        public String getRefund_buyer_pay_fee() {
            return this.refund_buyer_pay_fee;
        }

        public String getRefund_fee() {
            return this.refund_fee;
        }

        public String getRefund_merchant_discount_fee() {
            return this.refund_merchant_discount_fee;
        }

        public String getRefund_platform_discount_fee() {
            return this.refund_platform_discount_fee;
        }

        public String getRefund_promotion_detail() {
            return this.refund_promotion_detail;
        }

        public String getRefund_receipt_fee() {
            return this.refund_receipt_fee;
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

        public String getTrade_state() {
            return this.trade_state;
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

        public void setDevice_no(String str) {
            this.device_no = str;
        }

        public void setEnd_time(String str) {
            this.end_time = str;
        }

        public void setMerchant_name(String str) {
            this.merchant_name = str;
        }

        public void setMerchant_no(String str) {
            this.merchant_no = str;
        }

        public void setOut_refund_no(String str) {
            this.out_refund_no = str;
        }

        public void setOut_trade_no(String str) {
            this.out_trade_no = str;
        }

        public void setPay_time(String str) {
            this.pay_time = str;
        }

        public void setPay_trace(String str) {
            this.pay_trace = str;
        }

        public void setPay_type(String str) {
            this.pay_type = str;
        }

        public void setRefund_buyer_pay_fee(String str) {
            this.refund_buyer_pay_fee = str;
        }

        public void setRefund_fee(String str) {
            this.refund_fee = str;
        }

        public void setRefund_merchant_discount_fee(String str) {
            this.refund_merchant_discount_fee = str;
        }

        public void setRefund_platform_discount_fee(String str) {
            this.refund_platform_discount_fee = str;
        }

        public void setRefund_promotion_detail(String str) {
            this.refund_promotion_detail = str;
        }

        public void setRefund_receipt_fee(String str) {
            this.refund_receipt_fee = str;
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

        public void setTrade_state(String str) {
            this.trade_state = str;
        }

        public void setUser_id(String str) {
            this.user_id = str;
        }

        public String toString() {
            return "DataBean{attach='" + this.attach + "', channel_order_no='" + this.channel_order_no + "', channel_trade_no='" + this.channel_trade_no + "', device_no='" + this.device_no + "', end_time='" + this.end_time + "', merchant_name='" + this.merchant_name + "', merchant_no='" + this.merchant_no + "', out_refund_no='" + this.out_refund_no + "', out_trade_no='" + this.out_trade_no + "', pay_time='" + this.pay_time + "', pay_trace='" + this.pay_trace + "', pay_type='" + this.pay_type + "', refund_buyer_pay_fee='" + this.refund_buyer_pay_fee + "', refund_fee='" + this.refund_fee + "', refund_merchant_discount_fee='" + this.refund_merchant_discount_fee + "', refund_platform_discount_fee='" + this.refund_platform_discount_fee + "', refund_promotion_detail='" + this.refund_promotion_detail + "', refund_receipt_fee='" + this.refund_receipt_fee + "', result_code='" + this.result_code + "', return_code='" + this.return_code + "', return_msg='" + this.return_msg + "', terminal_id='" + this.terminal_id + "', terminal_time='" + this.terminal_time + "', terminal_trace='" + this.terminal_trace + "', trade_state='" + this.trade_state + "', user_id='" + this.user_id + "'}";
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
        return "扫呗退款查询接口refund{code=" + this.code + ", success=" + this.success + ", data=" + this.data + ", msg='" + this.msg + "'}";
    }
}
