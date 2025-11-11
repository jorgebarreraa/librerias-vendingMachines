package com.yj.coffeemachines.mvp.model.beans;

import com.blankj.utilcode.util.NumberUtils;
import java.util.List;

/* loaded from: classes.dex */
public class ProductBean implements Comparable<ProductBean> {
    private String deviceGoodsItemId;
    private double devicePrice;
    private boolean discount;
    private double discountPrice;
    private ProductDetailBean goodsInfoDetailApiVo;
    private String id;
    private String imgUrl;
    private int keepDecimals = 2;
    private int localSoldOut;
    private String name;
    private String nameEn;
    private boolean news;
    private String no;
    private double price;
    private List<String> rawMaterialTypes;
    private int soldOut;
    private String spec;

    /* loaded from: classes.dex */
    public static class ProductDetailBean {
        private List<AttachBean> attachList;
        private double devicePrice;
        private boolean discount;
        private double discountPrice;
        private List<FormulaListBean> formulaList;
        private String goodsExtNo;
        private String id;
        private int isDeleted;
        private int keepDecimals = 2;
        private boolean listing;
        private int localSoldOut;
        private String name;
        private String nameEn;
        private boolean news;
        private String no;
        private double price;
        private int soldOut;
        private int sort;
        private String spec;
        private int status;

        /* loaded from: classes.dex */
        public class AttachBean {
            private String createTime;
            private String createUser;
            private String fileName;
            private String fullPath;
            private String goodsId;
            private String id;
            private String path;
            private int templateFileType;

            public AttachBean() {
            }

            public String getCreateTime() {
                return this.createTime;
            }

            public String getCreateUser() {
                return this.createUser;
            }

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

            public void setCreateTime(String str) {
                this.createTime = str;
            }

            public void setCreateUser(String str) {
                this.createUser = str;
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
            private String createTime;
            private String createUser;
            private String goodsId;
            private int grindLeachTimes;
            private double grindQty;
            private double grindQtyMic;
            private double grindWaterQty;
            private double grindWaterQtyMic;
            private String id;
            private double kqty;
            private double qty;
            private String rawMaterialId;
            private String rawMaterialName;
            private String rawMaterialNo;
            private String rawMaterialType;
            private int sort;
            private String updateTime;
            private String updateUser;
            private double waterQty;
            private int waterType;

            public String getCreateTime() {
                return this.createTime;
            }

            public String getCreateUser() {
                return this.createUser;
            }

            public String getGoodsId() {
                return this.goodsId;
            }

            public int getGrindLeachTimes() {
                return this.grindLeachTimes;
            }

            public double getGrindQty() {
                return this.grindQty;
            }

            public double getGrindQtyMic() {
                return this.grindQtyMic;
            }

            public double getGrindWaterQty() {
                return this.grindWaterQty;
            }

            public double getGrindWaterQtyMic() {
                return this.grindWaterQtyMic;
            }

            public String getId() {
                return this.id;
            }

            public double getKqty() {
                return this.kqty;
            }

            public double getQty() {
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

            public String getRawMaterialType() {
                return this.rawMaterialType;
            }

            public int getSort() {
                return this.sort;
            }

            public String getUpdateTime() {
                return this.updateTime;
            }

            public String getUpdateUser() {
                return this.updateUser;
            }

            public double getWaterQty() {
                return this.waterQty;
            }

            public int getWaterType() {
                return this.waterType;
            }

            public void setCreateTime(String str) {
                this.createTime = str;
            }

            public void setCreateUser(String str) {
                this.createUser = str;
            }

            public void setGoodsId(String str) {
                this.goodsId = str;
            }

            public void setGrindLeachTimes(int i) {
                this.grindLeachTimes = i;
            }

            public void setGrindQty(double d) {
                this.grindQty = d;
            }

            public void setGrindQtyMic(double d) {
                this.grindQtyMic = d;
            }

            public void setGrindWaterQty(double d) {
                this.grindWaterQty = d;
            }

            public void setGrindWaterQtyMic(double d) {
                this.grindWaterQtyMic = d;
            }

            public void setId(String str) {
                this.id = str;
            }

            public void setKqty(double d) {
                this.kqty = d;
            }

            public void setQty(double d) {
                this.qty = d;
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

            public void setRawMaterialType(String str) {
                this.rawMaterialType = str;
            }

            public void setSort(int i) {
                this.sort = i;
            }

            public void setUpdateTime(String str) {
                this.updateTime = str;
            }

            public void setUpdateUser(String str) {
                this.updateUser = str;
            }

            public void setWaterQty(double d) {
                this.waterQty = d;
            }

            public void setWaterType(int i) {
                this.waterType = i;
            }

            public String toString() {
                return "FormulaListBean{id='" + this.id + "', goodsId='" + this.goodsId + "', rawMaterialId='" + this.rawMaterialId + "', rawMaterialNo='" + this.rawMaterialNo + "', rawMaterialName='" + this.rawMaterialName + "', rawMaterialType='" + this.rawMaterialType + "', qty=" + this.qty + ", waterQty=" + this.waterQty + ", waterType=" + this.waterType + ", kqty=" + this.kqty + ", grindQty=" + this.grindQty + ", grindQtyMic=" + this.grindQtyMic + ", grindWaterQty=" + this.grindWaterQty + ", grindWaterQtyMic=" + this.grindWaterQtyMic + ", grindLeachTimes=" + this.grindLeachTimes + ", sort=" + this.sort + ", createUser='" + this.createUser + "', createTime='" + this.createTime + "', updateUser='" + this.updateUser + "', updateTime='" + this.updateTime + "'}";
            }
        }

        public List<AttachBean> getAttachList() {
            return this.attachList;
        }

        public double getDevicePrice() {
            return this.devicePrice;
        }

        public String getDevicePriceStr() {
            return NumberUtils.format(this.devicePrice, this.keepDecimals);
        }

        public double getDiscountPrice() {
            return this.discountPrice;
        }

        public String getDiscountPriceStr() {
            return NumberUtils.format(this.discountPrice, this.keepDecimals);
        }

        public List<FormulaListBean> getFormulaList() {
            return this.formulaList;
        }

        public String getGoodsExtNo() {
            return this.goodsExtNo;
        }

        public String getId() {
            return this.id;
        }

        public int getIsDeleted() {
            return this.isDeleted;
        }

        public int getKeepDecimals() {
            return this.keepDecimals;
        }

        public int getLocalSoldOut() {
            return this.localSoldOut;
        }

        public String getName() {
            return this.name;
        }

        public String getNameEn() {
            return this.nameEn;
        }

        public String getNo() {
            return this.no;
        }

        public double getPrice() {
            return this.price;
        }

        public String getPriceStr() {
            return NumberUtils.format(this.price, this.keepDecimals);
        }

        public int getSoldOut() {
            return this.soldOut;
        }

        public int getSort() {
            return this.sort;
        }

        public String getSpec() {
            return this.spec;
        }

        public int getStatus() {
            return this.status;
        }

        public boolean isDiscount() {
            return this.discount;
        }

        public boolean isListing() {
            return this.listing;
        }

        public boolean isNews() {
            return this.news;
        }

        public double realPrice() {
            return this.discount ? this.discountPrice : getDevicePrice() <= 0.0d ? getPrice() : getDevicePrice();
        }

        public String realPriceText() {
            return NumberUtils.format(this.discount ? this.discountPrice : getDevicePrice() <= 0.0d ? getPrice() : getDevicePrice(), this.keepDecimals);
        }

        public void setAttachList(List<AttachBean> list) {
            this.attachList = list;
        }

        public void setDevicePrice(double d) {
            this.devicePrice = d;
        }

        public void setDiscount(boolean z) {
            this.discount = z;
        }

        public void setDiscountPrice(double d) {
            this.discountPrice = d;
        }

        public void setFormulaList(List<FormulaListBean> list) {
            this.formulaList = list;
        }

        public void setGoodsExtNo(String str) {
            this.goodsExtNo = str;
        }

        public void setId(String str) {
            this.id = str;
        }

        public void setIsDeleted(int i) {
            this.isDeleted = i;
        }

        public void setKeepDecimals(int i) {
            this.keepDecimals = i;
        }

        public void setListing(boolean z) {
            this.listing = z;
        }

        public void setLocalSoldOut(int i) {
            this.localSoldOut = i;
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

        public void setPrice(double d) {
            this.price = d;
        }

        public void setSoldOut(int i) {
            this.soldOut = i;
        }

        public void setSort(int i) {
            this.sort = i;
        }

        public void setSpec(String str) {
            this.spec = str;
        }

        public void setStatus(int i) {
            this.status = i;
        }
    }

    @Override // java.lang.Comparable
    public int compareTo(ProductBean productBean) {
        return getSoldOut() - productBean.getSoldOut();
    }

    public String getDeviceGoodsItemId() {
        return this.deviceGoodsItemId;
    }

    public double getDevicePrice() {
        return this.devicePrice;
    }

    public String getDevicePriceStr() {
        return NumberUtils.format(this.devicePrice, this.keepDecimals);
    }

    public double getDiscountPrice() {
        return this.discountPrice;
    }

    public String getDiscountPriceStr() {
        return NumberUtils.format(this.discountPrice, this.keepDecimals);
    }

    public ProductDetailBean getGoodsInfoDetailApiVo() {
        return this.goodsInfoDetailApiVo;
    }

    public String getId() {
        return this.id;
    }

    public String getImgUrl() {
        return this.imgUrl;
    }

    public int getKeepDecimals() {
        return this.keepDecimals;
    }

    public int getLocalSoldOut() {
        return this.localSoldOut;
    }

    public String getName() {
        return this.name;
    }

    public String getNameEn() {
        return this.nameEn;
    }

    public String getNo() {
        return this.no;
    }

    public double getPrice() {
        return this.price;
    }

    public String getPriceStr() {
        return NumberUtils.format(this.price, this.keepDecimals);
    }

    public List<String> getRawMaterialTypes() {
        return this.rawMaterialTypes;
    }

    public int getSoldOut() {
        return this.soldOut;
    }

    public String getSpec() {
        return this.spec;
    }

    public boolean isDiscount() {
        return this.discount;
    }

    public boolean isNews() {
        return this.news;
    }

    public double realPrice() {
        return this.discount ? this.discountPrice : getDevicePrice() <= 0.0d ? getPrice() : getDevicePrice();
    }

    public String realPriceText() {
        return NumberUtils.format(this.discount ? this.discountPrice : getDevicePrice() <= 0.0d ? getPrice() : getDevicePrice(), this.keepDecimals);
    }

    public void setDeviceGoodsItemId(String str) {
        this.deviceGoodsItemId = str;
    }

    public void setDevicePrice(double d) {
        this.devicePrice = d;
    }

    public void setDiscount(boolean z) {
        this.discount = z;
    }

    public void setDiscountPrice(double d) {
        this.discountPrice = d;
    }

    public void setGoodsInfoDetailApiVo(ProductDetailBean productDetailBean) {
        this.goodsInfoDetailApiVo = productDetailBean;
    }

    public void setId(String str) {
        this.id = str;
    }

    public void setImgUrl(String str) {
        this.imgUrl = str;
    }

    public void setKeepDecimals(int i) {
        this.keepDecimals = i;
    }

    public void setLocalSoldOut(int i) {
        this.localSoldOut = i;
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

    public void setPrice(double d) {
        this.price = d;
    }

    public void setRawMaterialTypes(List<String> list) {
        this.rawMaterialTypes = list;
    }

    public void setSoldOut(int i) {
        this.soldOut = i;
    }

    public void setSpec(String str) {
        this.spec = str;
    }
}
