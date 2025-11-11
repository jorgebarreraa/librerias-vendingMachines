package com.yj.coffeemachines.eventbusbean;

import com.yj.coffeemachines.helper.Tools;

/* loaded from: classes.dex */
public class DialogMessageNew {
    private boolean hintself;
    private String msg;
    private boolean show;
    private int time;
    private int type;

    public DialogMessageNew(String str, int i) {
        this.show = true;
        this.msg = str;
        this.hintself = false;
        this.type = i;
    }

    public DialogMessageNew(boolean z, int i) {
        this.show = false;
        this.msg = "";
        this.hintself = false;
        this.type = i;
    }

    public DialogMessageNew(boolean z, String str, boolean z2) {
        this.show = z;
        this.msg = str;
        this.hintself = z2;
    }

    public DialogMessageNew(boolean z, String str, boolean z2, int i) {
        this.show = z;
        this.msg = str;
        this.hintself = z2;
        this.type = i;
    }

    public DialogMessageNew(boolean z, String str, boolean z2, int i, int i2) {
        this.show = z;
        this.msg = str;
        this.hintself = z2;
        this.type = i;
        this.time = i2;
    }

    public String getMsg() {
        String str = this.msg;
        return str == null ? "" : str;
    }

    public int getTime() {
        if (Tools.toInt(Integer.valueOf(this.time)) == 0) {
            return 3;
        }
        return Tools.toInt(Integer.valueOf(this.time));
    }

    public int getType() {
        int i = this.type;
        if (i != 1) {
            return 0;
        }
        return i;
    }

    public boolean isHintself() {
        return this.hintself;
    }

    public boolean isShow() {
        return this.show;
    }

    public void setMsg(String str) {
        this.msg = str;
    }

    public void setTime(int i) {
        this.time = i;
    }

    public String toString() {
        return "DialogMessageNew{show=" + this.show + ", msg='" + this.msg + "', hintself=" + this.hintself + ", type=" + this.type + ", time=" + this.time + '}';
    }
}
