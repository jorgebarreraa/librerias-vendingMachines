package com.yj.coffeemachines.greendao.beans;

/* loaded from: classes.dex */
public class MakeDrinkMessage {
    private double changeNumber;
    private boolean isExchange;
    private Long key;
    private String orderMessage;
    private String par0;
    private String par1;
    private String par2;
    private String par3;
    private String par4;
    private String par5;
    private String par6;
    private String par7;
    private String par8;
    private String par9;
    private double payNumber;
    private int payWay;
    private double productPrice;
    private Long time;

    public MakeDrinkMessage() {
    }

    public MakeDrinkMessage(Long l, Long l2, int i, double d, double d2, double d3, boolean z, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11) {
        this.key = l;
        this.time = l2;
        this.payWay = i;
        this.payNumber = d;
        this.changeNumber = d2;
        this.productPrice = d3;
        this.isExchange = z;
        this.orderMessage = str;
        this.par0 = str2;
        this.par1 = str3;
        this.par2 = str4;
        this.par3 = str5;
        this.par4 = str6;
        this.par5 = str7;
        this.par6 = str8;
        this.par7 = str9;
        this.par8 = str10;
        this.par9 = str11;
    }

    public double getChangeNumber() {
        return this.changeNumber;
    }

    public boolean getIsExchange() {
        return this.isExchange;
    }

    public Long getKey() {
        return this.key;
    }

    public String getOrderMessage() {
        String str = this.orderMessage;
        return str == null ? "" : str;
    }

    public String getPar0() {
        String str = this.par0;
        return str == null ? "" : str;
    }

    public String getPar1() {
        String str = this.par1;
        return str == null ? "" : str;
    }

    public String getPar2() {
        String str = this.par2;
        return str == null ? "" : str;
    }

    public String getPar3() {
        String str = this.par3;
        return str == null ? "" : str;
    }

    public String getPar4() {
        String str = this.par4;
        return str == null ? "" : str;
    }

    public String getPar5() {
        String str = this.par5;
        return str == null ? "" : str;
    }

    public String getPar6() {
        String str = this.par6;
        return str == null ? "" : str;
    }

    public String getPar7() {
        String str = this.par7;
        return str == null ? "" : str;
    }

    public String getPar8() {
        String str = this.par8;
        return str == null ? "" : str;
    }

    public String getPar9() {
        String str = this.par9;
        return str == null ? "" : str;
    }

    public double getPayNumber() {
        return this.payNumber;
    }

    public int getPayWay() {
        return this.payWay;
    }

    public double getProductPrice() {
        return this.productPrice;
    }

    public Long getTime() {
        return this.time;
    }

    public boolean isExchange() {
        return this.isExchange;
    }

    public void setChangeNumber(double d) {
        this.changeNumber = d;
    }

    public void setExchange(boolean z) {
        this.isExchange = z;
    }

    public void setIsExchange(boolean z) {
        this.isExchange = z;
    }

    public void setKey(Long l) {
        this.key = l;
    }

    public void setOrderMessage(String str) {
        if (str == null) {
            str = "";
        }
        this.orderMessage = str;
    }

    public void setPar0(String str) {
        if (str == null) {
            str = "";
        }
        this.par0 = str;
    }

    public void setPar1(String str) {
        if (str == null) {
            str = "";
        }
        this.par1 = str;
    }

    public void setPar2(String str) {
        if (str == null) {
            str = "";
        }
        this.par2 = str;
    }

    public void setPar3(String str) {
        if (str == null) {
            str = "";
        }
        this.par3 = str;
    }

    public void setPar4(String str) {
        if (str == null) {
            str = "";
        }
        this.par4 = str;
    }

    public void setPar5(String str) {
        if (str == null) {
            str = "";
        }
        this.par5 = str;
    }

    public void setPar6(String str) {
        if (str == null) {
            str = "";
        }
        this.par6 = str;
    }

    public void setPar7(String str) {
        if (str == null) {
            str = "";
        }
        this.par7 = str;
    }

    public void setPar8(String str) {
        if (str == null) {
            str = "";
        }
        this.par8 = str;
    }

    public void setPar9(String str) {
        if (str == null) {
            str = "";
        }
        this.par9 = str;
    }

    public void setPayNumber(double d) {
        this.payNumber = d;
    }

    public void setPayWay(int i) {
        this.payWay = i;
    }

    public void setProductPrice(double d) {
        this.productPrice = d;
    }

    public void setTime(Long l) {
        this.time = l;
    }
}
