package com.yj.coffeemachines.mvp.model.beans;

/* loaded from: classes.dex */
public class DigitalPayGenOrderBack {
    private int code;
    private Databean1 data;
    private String msg;
    private boolean success;

    /* loaded from: classes.dex */
    public static class Databean1 {
        private int code;
        private DataBean data;
        private String msg;

        /* loaded from: classes.dex */
        public static class DataBean {
            private String code;
            private String orderId;
            private PayJsonBean payJson;

            /* loaded from: classes.dex */
            public static class PayJsonBean {
                private String merchantName;
                private String orderId;
                private String qCodeImgUrl;
                private String qCodeUrl;
                private String thirdOrderNo;
                private String transMoney;

                public String getMerchantName() {
                    return this.merchantName;
                }

                public String getOrderId() {
                    return this.orderId;
                }

                public String getQCodeImgUrl() {
                    return this.qCodeImgUrl;
                }

                public String getQCodeUrl() {
                    return this.qCodeUrl;
                }

                public String getThirdOrderNo() {
                    return this.thirdOrderNo;
                }

                public String getTransMoney() {
                    return this.transMoney;
                }

                public void setMerchantName(String str) {
                    this.merchantName = str;
                }

                public void setOrderId(String str) {
                    this.orderId = str;
                }

                public void setQCodeImgUrl(String str) {
                    this.qCodeImgUrl = str;
                }

                public void setQCodeUrl(String str) {
                    this.qCodeUrl = str;
                }

                public void setThirdOrderNo(String str) {
                    this.thirdOrderNo = str;
                }

                public void setTransMoney(String str) {
                    this.transMoney = str;
                }
            }

            public String getCode() {
                return this.code;
            }

            public String getOrderId() {
                return this.orderId;
            }

            public PayJsonBean getPayJson() {
                return this.payJson;
            }

            public void setCode(String str) {
                this.code = str;
            }

            public void setOrderId(String str) {
                this.orderId = str;
            }

            public void setPayJson(PayJsonBean payJsonBean) {
                this.payJson = payJsonBean;
            }

            public String toString() {
                return "DataBean{code='" + this.code + "', orderId='" + this.orderId + "', payJson=" + this.payJson + '}';
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

        public void setCode(int i) {
            this.code = i;
        }

        public void setData(DataBean dataBean) {
            this.data = dataBean;
        }

        public void setMsg(String str) {
            this.msg = str;
        }

        public String toString() {
            return "Databean1{code=" + this.code + ", data=" + this.data.toString() + ", msg='" + this.msg + "'}";
        }
    }

    public int getCode() {
        return this.code;
    }

    public Databean1 getData() {
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

    public void setData(Databean1 databean1) {
        this.data = databean1;
    }

    public void setMsg(String str) {
        this.msg = str;
    }

    public void setSuccess(boolean z) {
        this.success = z;
    }
}
