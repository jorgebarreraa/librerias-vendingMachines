package com.yj.coffeemachines.greendao.beans;

/* loaded from: classes.dex */
public class OpsActionMessage {
    private String action;
    private Long key;
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
    private int step;
    private Long time;

    public OpsActionMessage() {
    }

    public OpsActionMessage(Long l, Long l2, String str, int i, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11) {
        this.key = l;
        this.time = l2;
        this.action = str;
        this.step = i;
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

    public String getAction() {
        String str = this.action;
        return str == null ? "" : str;
    }

    public Long getKey() {
        return this.key;
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

    public int getStep() {
        return this.step;
    }

    public Long getTime() {
        return this.time;
    }

    public void setAction(String str) {
        if (str == null) {
            str = "";
        }
        this.action = str;
    }

    public void setKey(Long l) {
        this.key = l;
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

    public void setStep(int i) {
        this.step = i;
    }

    public void setTime(Long l) {
        this.time = l;
    }
}
