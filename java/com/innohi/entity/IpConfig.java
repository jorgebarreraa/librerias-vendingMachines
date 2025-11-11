package com.innohi.entity;

import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class IpConfig {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public final ArrayList<String> dnsList;
    public final String gateway;
    public final String ip;
    public final String mask;

    public IpConfig(String str, String str2, String str3, List<String> list) {
        this.ip = str;
        this.mask = str2;
        this.gateway = str3;
        this.dnsList = new ArrayList<>(list);
    }

    public String toString() {
        return "IpConfig{ip='" + this.ip + "', mask='" + this.mask + "', gateway='" + this.gateway + "', dnsList=" + this.dnsList + '}';
    }
}
