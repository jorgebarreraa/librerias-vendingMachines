package com.yj.coffeemachines.pay;

/* loaded from: classes.dex */
public interface PayStrategy {
    void pay(double d);

    void query();

    void refund();
}
