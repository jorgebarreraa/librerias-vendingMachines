package com.yj.coffeemachines.eventbusbean;

import androidx.annotation.NonNull;

/* loaded from: classes.dex */
public enum ErrorMaking {
    EM01("配方信息异常3"),
    EM02("传杯到起始位失败3"),
    EM03("传杯到指定工位失败3"),
    EM04("传杯到终点位失败3"),
    EM05("开门操作失败3"),
    EM06("关门操作失败"),
    EM07("自动落杯失败3"),
    EM08("手动放杯超时3"),
    EM09("低水位导致失败3"),
    EM10("并行现磨操作失败3"),
    EM11("本次现磨操作失败3"),
    EM12("等待取杯超时2上报"),
    EM13("现磨异常失败2现磨售罄"),
    EM00("UNKNOWN");

    private String name;

    ErrorMaking(String str) {
        this.name = str;
    }

    @Override // java.lang.Enum
    @NonNull
    public String toString() {
        return this.name;
    }
}
