package com.yj.coffeemachines.eventbusbean;

/* loaded from: classes.dex */
public class MyEventBusMessage {
    private String from;
    private String msg;
    private int tag;
    private TYPE type;

    /* loaded from: classes.dex */
    public enum TYPE {
        CLICK("click");

        TYPE(String str) {
        }
    }

    public MyEventBusMessage() {
    }

    public MyEventBusMessage(String str, TYPE type, String str2, int i) {
        this.from = str;
        this.type = type;
        this.msg = str2;
        this.tag = i;
    }

    public String getFrom() {
        String str = this.from;
        return str == null ? "" : str;
    }

    public String getMsg() {
        String str = this.msg;
        return str == null ? "" : str;
    }

    public int getTag() {
        return this.tag;
    }

    public TYPE getType() {
        return this.type;
    }

    public void setFrom(String str) {
        if (str == null) {
            str = "";
        }
        this.from = str;
    }

    public void setMsg(String str) {
        if (str == null) {
            str = "";
        }
        this.msg = str;
    }

    public void setTag(int i) {
        this.tag = i;
    }

    public void setType(TYPE type) {
        this.type = type;
    }
}
