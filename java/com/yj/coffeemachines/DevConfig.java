package com.yj.coffeemachines;

/* loaded from: classes.dex */
public class DevConfig {
    private boolean cmd27;
    private boolean left1;
    private boolean left2;
    private boolean left3;
    private boolean left4;
    private boolean left5;
    private boolean left6;
    private boolean left7;
    private boolean right1;
    private boolean right2;
    private boolean right2_ice;
    private boolean right3;
    private boolean right4;
    private boolean right5;
    private boolean right6;
    private boolean right7;
    private boolean right8;
    private String startTime;
    private String stopTime;

    public DevConfig() {
    }

    public DevConfig(boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, boolean z8, boolean z9, boolean z10, boolean z11, boolean z12, boolean z13, boolean z14, boolean z15, boolean z16, String str, String str2, boolean z17) {
        this.left1 = z;
        this.left2 = z2;
        this.left3 = z3;
        this.left4 = z4;
        this.left5 = z5;
        this.left6 = z6;
        this.left7 = z7;
        this.right1 = z8;
        this.right2 = z9;
        this.right2_ice = z10;
        this.right3 = z11;
        this.right4 = z12;
        this.right5 = z13;
        this.right6 = z14;
        this.right7 = z15;
        this.right8 = z16;
        this.startTime = str;
        this.stopTime = str2;
        this.cmd27 = z17;
    }

    public String getStartTime() {
        String str = this.startTime;
        return str == null ? "" : str;
    }

    public String getStopTime() {
        String str = this.stopTime;
        return str == null ? "" : str;
    }

    public boolean isCmd27() {
        return this.cmd27;
    }

    public boolean isLeft1() {
        return this.left1;
    }

    public boolean isLeft2() {
        return this.left2;
    }

    public boolean isLeft3() {
        return this.left3;
    }

    public boolean isLeft4() {
        return this.left4;
    }

    public boolean isLeft5() {
        return this.left5;
    }

    public boolean isLeft6() {
        return this.left6;
    }

    public boolean isLeft7() {
        return this.left7;
    }

    public boolean isRight1() {
        return this.right1;
    }

    public boolean isRight2() {
        return this.right2;
    }

    public boolean isRight2_ice() {
        return this.right2_ice;
    }

    public boolean isRight3() {
        return this.right3;
    }

    public boolean isRight4() {
        return this.right4;
    }

    public boolean isRight5() {
        return this.right5;
    }

    public boolean isRight6() {
        return this.right6;
    }

    public boolean isRight7() {
        return this.right7;
    }

    public boolean isRight8() {
        return this.right8;
    }

    public void setCmd27(boolean z) {
        this.cmd27 = z;
    }

    public void setLeft1(boolean z) {
        this.left1 = z;
    }

    public void setLeft2(boolean z) {
        this.left2 = z;
    }

    public void setLeft3(boolean z) {
        this.left3 = z;
    }

    public void setLeft4(boolean z) {
        this.left4 = z;
    }

    public void setLeft5(boolean z) {
        this.left5 = z;
    }

    public void setLeft6(boolean z) {
        this.left6 = z;
    }

    public void setLeft7(boolean z) {
        this.left7 = z;
    }

    public void setRight1(boolean z) {
        this.right1 = z;
    }

    public void setRight2(boolean z) {
        this.right2 = z;
    }

    public void setRight2_ice(boolean z) {
        this.right2_ice = z;
    }

    public void setRight3(boolean z) {
        this.right3 = z;
    }

    public void setRight4(boolean z) {
        this.right4 = z;
    }

    public void setRight5(boolean z) {
        this.right5 = z;
    }

    public void setRight6(boolean z) {
        this.right6 = z;
    }

    public void setRight7(boolean z) {
        this.right7 = z;
    }

    public void setRight8(boolean z) {
        this.right8 = z;
    }

    public void setStartTime(String str) {
        if (str == null) {
            str = "";
        }
        this.startTime = str;
    }

    public void setStopTime(String str) {
        if (str == null) {
            str = "";
        }
        this.stopTime = str;
    }
}
