package com.yj.coffeemachines.pay.mdb;

import com.yj.coffeemachines.helper.Tools;

/* loaded from: classes.dex */
public class TransmissionUtils {
    public static byte[] pulseLevel2 = Tools.strToByte("110002000002");
    public static byte[] pulseLevel3 = Tools.strToByte("110003000000");
    public static byte[] max = Tools.strToByte("1101FFFF0000");
    public static byte[] readId = Tools.strToByte("17004E4543303030303030303030303030202020534F4C4953544120200011");
    public static byte[] enable = Tools.strToByte("170400000020");
    public static byte[] pulseStart = Tools.strToByte("1401");
    public static byte[] pulseSuccess = Tools.strToByte("13020001");
    public static byte[] pulseCancel = Tools.strToByte("1301");
    public static byte[] endTransaction = Tools.strToByte("1304");
    public static byte[] Query = Tools.strToByte("12");
    public static byte[] Resat = Tools.strToByte("10");
    public static byte[] coinInit = Tools.strToByte("09");
    public static byte[] coinReadId = Tools.strToByte("0F00");
    public static byte[] coinReadStatus = Tools.strToByte("0A");
    public static byte[] coinEnable = Tools.strToByte("0F0100000001");
    public static byte[] coinStart = Tools.strToByte("0CFFFFFFFF");
    public static byte[] coinClose = Tools.strToByte("0C00000000");
    public static byte[] coinRead = Tools.strToByte("0B");
    public static byte[] coinChangeNum = Tools.strToByte("0F04");
    public static byte[] coinChangeNumAgain = Tools.strToByte("0F03");
    public static byte[] billInit = Tools.strToByte("31");
    public static byte[] billStart = Tools.strToByte("34FFFF0000");
    public static byte[] billClose = Tools.strToByte("3400000000");
    public static byte[] billRead = Tools.strToByte("06");
    public static byte[] billMoney = Tools.strToByte("3705");
    public static byte[] billNO1 = Tools.strToByte("370100000002");
    public static byte[] billChange = Tools.strToByte("3703");
    public static byte[] billNO2 = Tools.strToByte("3706030001");
}
