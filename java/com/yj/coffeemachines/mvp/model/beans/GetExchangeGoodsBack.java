package com.yj.coffeemachines.mvp.model.beans;

import java.util.List;

/* loaded from: classes.dex */
public class GetExchangeGoodsBack {
    private int code;
    private DataBean data;
    private String msg;
    private boolean success;

    /* loaded from: classes.dex */
    public static class DataBean {
        private List<AttachListBean> attachList;
        private String createTime;
        private int createUser;
        private int devicePrice;
        private String deviceTypeId;
        private boolean discount;
        private int discountPrice;
        private List<FormulaListBean> formulaList;
        private String id;
        private int isDeleted;
        private String name;
        private String nameEn;
        private boolean news;
        private String no;
        private int price;
        private String spec;
        private int status;
        private String updateTime;
        private int updateUser;

        /* loaded from: classes.dex */
        public static class AttachListBean {
            private String fileName;
            private String fullPath;
            private String goodsId;
            private String id;
            private String path;
            private int templateFileType;

            public String getFileName() {
                return this.fileName;
            }

            public String getFullPath() {
                return this.fullPath;
            }

            public String getGoodsId() {
                return this.goodsId;
            }

            public String getId() {
                return this.id;
            }

            public String getPath() {
                return this.path;
            }

            public int getTemplateFileType() {
                return this.templateFileType;
            }

            public void setFileName(String str) {
                this.fileName = str;
            }

            public void setFullPath(String str) {
                this.fullPath = str;
            }

            public void setGoodsId(String str) {
                this.goodsId = str;
            }

            public void setId(String str) {
                this.id = str;
            }

            public void setPath(String str) {
                this.path = str;
            }

            public void setTemplateFileType(int i) {
                this.templateFileType = i;
            }
        }

        /* loaded from: classes.dex */
        public static class FormulaListBean {
            private String goodsId;
            private int grindLeachTimes;
            private int grindQty;
            private int grindQtyMic;
            private int grindWaterQty;
            private int grindWaterQtyMic;
            private String id;
            private int kqty;
            private int qty;
            private String rawMaterialId;
            private String rawMaterialName;
            private String rawMaterialNo;
            private int sort;
            private int waterQty;
            private int waterType;

            public String getGoodsId() {
                return this.goodsId;
            }

            public int getGrindLeachTimes() {
                return this.grindLeachTimes;
            }

            public int getGrindQty() {
                return this.grindQty;
            }

            public int getGrindQtyMic() {
                return this.grindQtyMic;
            }

            public int getGrindWaterQty() {
                return this.grindWaterQty;
            }

            public int getGrindWaterQtyMic() {
                return this.grindWaterQtyMic;
            }

            public String getId() {
                return this.id;
            }

            public int getKqty() {
                return this.kqty;
            }

            public int getQty() {
                return this.qty;
            }

            public String getRawMaterialId() {
                return this.rawMaterialId;
            }

            public String getRawMaterialName() {
                return this.rawMaterialName;
            }

            public String getRawMaterialNo() {
                return this.rawMaterialNo;
            }

            public int getSort() {
                return this.sort;
            }

            public int getWaterQty() {
                return this.waterQty;
            }

            public int getWaterType() {
                return this.waterType;
            }

            public void setGoodsId(String str) {
                this.goodsId = str;
            }

            public void setGrindLeachTimes(int i) {
                this.grindLeachTimes = i;
            }

            public void setGrindQty(int i) {
                this.grindQty = i;
            }

            public void setGrindQtyMic(int i) {
                this.grindQtyMic = i;
            }

            public void setGrindWaterQty(int i) {
                this.grindWaterQty = i;
            }

            public void setGrindWaterQtyMic(int i) {
                this.grindWaterQtyMic = i;
            }

            public void setId(String str) {
                this.id = str;
            }

            public void setKqty(int i) {
                this.kqty = i;
            }

            public void setQty(int i) {
                this.qty = i;
            }

            public void setRawMaterialId(String str) {
                this.rawMaterialId = str;
            }

            public void setRawMaterialName(String str) {
                this.rawMaterialName = str;
            }

            public void setRawMaterialNo(String str) {
                this.rawMaterialNo = str;
            }

            public void setSort(int i) {
                this.sort = i;
            }

            public void setWaterQty(int i) {
                this.waterQty = i;
            }

            public void setWaterType(int i) {
                this.waterType = i;
            }
        }

        public List<AttachListBean> getAttachList() {
            return this.attachList;
        }

        public String getCreateTime() {
            return this.createTime;
        }

        public int getCreateUser() {
            return this.createUser;
        }

        public int getDevicePrice() {
            return this.devicePrice;
        }

        public String getDeviceTypeId() {
            return this.deviceTypeId;
        }

        public boolean getDiscount() {
            return this.discount;
        }

        public int getDiscountPrice() {
            return this.discountPrice;
        }

        public List<FormulaListBean> getFormulaList() {
            return this.formulaList;
        }

        public String getId() {
            return this.id;
        }

        public int getIsDeleted() {
            return this.isDeleted;
        }

        public String getName() {
            return this.name;
        }

        public String getNameEn() {
            return this.nameEn;
        }

        public boolean getNews() {
            return this.news;
        }

        public String getNo() {
            return this.no;
        }

        public int getPrice() {
            return this.price;
        }

        public String getSpec() {
            return this.spec;
        }

        public int getStatus() {
            return this.status;
        }

        public String getUpdateTime() {
            return this.updateTime;
        }

        public int getUpdateUser() {
            return this.updateUser;
        }

        public void setAttachList(List<AttachListBean> list) {
            this.attachList = list;
        }

        public void setCreateTime(String str) {
            this.createTime = str;
        }

        public void setCreateUser(int i) {
            this.createUser = i;
        }

        public void setDevicePrice(int i) {
            this.devicePrice = i;
        }

        public void setDeviceTypeId(String str) {
            this.deviceTypeId = str;
        }

        public void setDiscount(boolean z) {
            this.discount = z;
        }

        public void setDiscountPrice(int i) {
            this.discountPrice = i;
        }

        public void setFormulaList(List<FormulaListBean> list) {
            this.formulaList = list;
        }

        public void setId(String str) {
            this.id = str;
        }

        public void setIsDeleted(int i) {
            this.isDeleted = i;
        }

        public void setName(String str) {
            this.name = str;
        }

        public void setNameEn(String str) {
            this.nameEn = str;
        }

        public void setNews(boolean z) {
            this.news = z;
        }

        public void setNo(String str) {
            this.no = str;
        }

        public void setPrice(int i) {
            this.price = i;
        }

        public void setSpec(String str) {
            this.spec = str;
        }

        public void setStatus(int i) {
            this.status = i;
        }

        public void setUpdateTime(String str) {
            this.updateTime = str;
        }

        public void setUpdateUser(int i) {
            this.updateUser = i;
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
