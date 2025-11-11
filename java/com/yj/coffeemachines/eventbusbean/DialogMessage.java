package com.yj.coffeemachines.eventbusbean;

/* loaded from: classes.dex */
public class DialogMessage {
    private String code;
    private boolean hintself;
    private String msg;
    private boolean show;
    private String type;

    public DialogMessage(String str) {
        this.show = true;
        this.code = "";
        this.msg = "";
        this.hintself = false;
        this.type = str;
    }

    public DialogMessage(boolean z, String str, String str2, boolean z2, String str3) {
        this.show = z;
        this.code = str;
        this.msg = str2;
        this.hintself = z2;
        this.type = str3;
    }

    public String getCode() {
        String str = this.code;
        return str == null ? "" : str;
    }

    public String getMsg() {
        String str = this.msg;
        return str == null ? "" : str;
    }

    public String getType() {
        String str = this.type;
        return str == null ? "" : str;
    }

    public boolean isHintself() {
        return this.hintself;
    }

    public boolean isShow() {
        return this.show;
    }

    public void setCode(String str) {
        if (str == null) {
            str = "";
        }
        this.code = str;
    }

    public void setHintself(boolean z) {
        this.hintself = z;
    }

    public void setMsg(String str) {
        if (str == null) {
            str = "";
        }
        this.msg = str;
    }

    public void setShow(boolean z) {
        this.show = z;
    }

    public void setType(String str) {
        if (str == null) {
            str = "";
        }
        this.type = str;
    }
}
