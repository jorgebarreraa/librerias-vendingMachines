package com.yj.coffeemachines.eventbusbean;

/* loaded from: classes.dex */
public class MachineState {
    public String message;
    public byte state;
    public Type type;

    /* loaded from: classes.dex */
    public enum Type {
        OK,
        FAULT,
        BUSY
    }

    public MachineState(byte b, String str, Type type) {
        this.state = b;
        this.message = str;
        this.type = type;
    }
}
