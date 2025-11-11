package com.yj.coffeemachines.app.service;

import com.yj.coffeemachines.helper.Tools;

/* loaded from: classes.dex */
public class SerialOrderBytes {
    public static byte[] CurErrorQuery = Tools.strToByte("AA550820040380010002B1");
    public static byte[] QianMenErrorQuery = Tools.strToByte("AA5508200203000000022E");
    public static byte[] CheckMacStateNew = Tools.strToByte("AA55021E1F");
    public static byte[] CheckMacStateOld = Tools.strToByte("AA55020B0C");
    public static byte[] MakingMacStateQuery = Tools.strToByte("AA55021F20");
    public static byte[] FreshlyGroundClear = Tools.strToByte("AA550821040680060001B9");
    public static byte[] cancleFreshlyGroundClear = Tools.strToByte("AA550821040680090001BC");
    public static byte[] FreshlyGroundPaiKong = Tools.strToByte("AA5508210406800A0001BD");
    public static byte[] DrawWater = Tools.strToByte("AA55021C1D");
    public static byte[] MainControlPanel = Tools.strToByte("AA5508200103001F00024C");
    public static byte[] Water = Tools.strToByte("AA5505290100002E");
    public static byte[] WaterNotice = Tools.strToByte("AA55052903000131");
    public static byte[] GetWaterResult = Tools.strToByte("AA5505290200002F");
}
