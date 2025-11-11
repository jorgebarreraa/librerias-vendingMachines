package com.yj.coffeemachines.greendao.beans;

import com.jess.arms.utils.ArmsUtils;
import com.yj.coffeemachines.MyAppLocation;
import com.yj.coffeemachines.R;

/* loaded from: classes.dex */
public class OrderMessageBean {
    private Long id;
    private String message;
    private String par1;
    private String par2;
    private String par3;
    private String par4;
    private String par5;
    private String serialName;
    private String time;
    private Long timestamp;
    private int type;

    public OrderMessageBean() {
    }

    public OrderMessageBean(Long l, String str, int i, String str2, String str3, long j) {
        this.id = l;
        this.serialName = str;
        this.type = i;
        this.time = str2;
        this.message = str3;
        this.timestamp = Long.valueOf(j);
    }

    public OrderMessageBean(Long l, String str, int i, String str2, String str3, Long l2, String str4, String str5, String str6, String str7, String str8) {
        this.id = l;
        this.serialName = str;
        this.type = i;
        this.time = str2;
        this.message = str3;
        this.timestamp = l2;
        this.par1 = str4;
        this.par2 = str5;
        this.par3 = str6;
        this.par4 = str7;
        this.par5 = str8;
    }

    public Long getId() {
        return this.id;
    }

    public String getMessage() {
        String str = this.message;
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

    public String getSerialName() {
        String str = this.serialName;
        return str == null ? "" : str;
    }

    public String getTime() {
        String str = this.time;
        return str == null ? "" : str;
    }

    public Long getTimestamp() {
        return this.timestamp;
    }

    public int getType() {
        return this.type;
    }

    public void setId(Long l) {
        this.id = l;
    }

    public void setMessage(String str) {
        if (str == null) {
            str = "";
        }
        this.message = str;
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

    public void setSerialName(String str) {
        if (str == null) {
            str = "";
        }
        this.serialName = str;
    }

    public void setTime(String str) {
        if (str == null) {
            str = "";
        }
        this.time = str;
    }

    public void setTimestamp(Long l) {
        this.timestamp = l;
    }

    public void setType(int i) {
        this.type = i;
    }

    public String toString() {
        if (!this.serialName.contains("tty")) {
            return this.id + "." + this.time + ":" + this.message;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(this.id);
        sb.append(".");
        sb.append(this.time);
        sb.append(":");
        sb.append(this.type == 1 ? ArmsUtils.getResources(MyAppLocation.myAppLocation).getString(R.string.send_cmd) : ArmsUtils.getResources(MyAppLocation.myAppLocation).getString(R.string.receive_cmd));
        sb.append(this.message);
        return sb.toString();
    }
}
