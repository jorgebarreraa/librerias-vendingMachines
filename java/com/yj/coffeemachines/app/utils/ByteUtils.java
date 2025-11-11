package com.yj.coffeemachines.app.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Arrays;
import kotlin.UByte;
import okhttp3.internal.ws.WebSocketProtocol;

/* loaded from: classes.dex */
public class ByteUtils {
    private static final int HASH_ITERATIONS = 3;

    public static String byte2HexStr2(byte[] bArr) {
        StringBuilder sb = new StringBuilder("");
        for (byte b : bArr) {
            String hexString = Integer.toHexString(b & UByte.MAX_VALUE);
            if (hexString.length() == 1) {
                hexString = "0" + hexString;
            }
            sb.append(hexString);
            sb.append(" ");
        }
        return sb.toString().toUpperCase().trim();
    }

    public static String byte2HexStrMdb(byte[] bArr) {
        StringBuilder sb = new StringBuilder("");
        for (byte b : bArr) {
            String hexString = Integer.toHexString(b & UByte.MAX_VALUE);
            if (hexString.length() == 1) {
                hexString = "0" + hexString;
            }
            sb.append(hexString);
            sb.append("");
        }
        return sb.toString().toUpperCase().trim();
    }

    public static byte[] byteMerger(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = new byte[bArr.length + bArr2.length];
        System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
        System.arraycopy(bArr2, 0, bArr3, bArr.length, bArr2.length);
        return bArr3;
    }

    public static String bytesToHex(byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer();
        for (byte b : bArr) {
            String hexString = Integer.toHexString(b & UByte.MAX_VALUE);
            if (hexString.length() < 2) {
                stringBuffer.append(0);
            }
            stringBuffer.append(hexString);
        }
        return stringBuffer.toString();
    }

    public static int bytesToInt4(byte[] bArr, int i) {
        return (bArr[i + 1] & UByte.MAX_VALUE) | ((bArr[i] & UByte.MAX_VALUE) << 8);
    }

    public static int calculateFMST(byte[] bArr, byte[] bArr2) {
        return extract16BitValue(bArr) | (extract16BitValue(bArr2) << 16);
    }

    public static boolean checkByte(byte[] bArr) {
        if (bArr.length < 5) {
            return false;
        }
        int i = 0;
        for (int i2 = 0; i2 < bArr.length - 1; i2++) {
            i += bArr[i2];
        }
        return (i & 255) == (bArr[bArr.length - 1] & UByte.MAX_VALUE);
    }

    public static long convertsByteToInt(byte[] bArr, byte[] bArr2) {
        return (((bArr2[1] & UByte.MAX_VALUE) | ((bArr2[0] & UByte.MAX_VALUE) << 8)) & WebSocketProtocol.PAYLOAD_SHORT_MAX) | (((bArr[1] & UByte.MAX_VALUE) | ((bArr[0] & UByte.MAX_VALUE) << 8)) << 16);
    }

    public static int extract16BitValue(byte[] bArr) {
        return (bArr[0] & UByte.MAX_VALUE) | ((bArr[1] & UByte.MAX_VALUE) << 8);
    }

    public static byte[] generateUniqueBytes(String str) throws Exception {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        byte[] bytes = str.getBytes(StandardCharsets.UTF_8);
        for (int i = 0; i < 3; i++) {
            bytes = messageDigest.digest(bytes);
        }
        byte[] bArr = new byte[4];
        boolean[] zArr = new boolean[256];
        int i2 = 0;
        for (byte b : bytes) {
            int i3 = b & UByte.MAX_VALUE;
            if (!zArr[i3] && i2 < 4) {
                bArr[i2] = (byte) i3;
                zArr[i3] = true;
                i2++;
            }
            if (i2 == 4) {
                break;
            }
        }
        if (i2 < 4) {
            Arrays.fill(bArr, i2, 4, (byte) (bytes[0] & UByte.MAX_VALUE));
        }
        return bArr;
    }

    public static byte[] getBooleanArray(byte b) {
        byte[] bArr = new byte[8];
        byte b2 = b;
        for (int i = 7; i >= 0; i--) {
            bArr[i] = (byte) (b2 & 1);
            b2 = (byte) (b2 >> 1);
        }
        byte[] bArr2 = new byte[bArr.length];
        for (int i2 = 0; i2 < bArr.length; i2++) {
            bArr2[7 - i2] = bArr[i2];
        }
        return bArr2;
    }

    public static byte[] hexTobytes(String str) {
        if (str.length() < 1) {
            return null;
        }
        byte[] bArr = new byte[str.length() / 2];
        int i = 0;
        int i2 = 0;
        while (i < str.length()) {
            int i3 = i + 2;
            bArr[i2] = (byte) Integer.parseInt(str.substring(i, i3), 16);
            i2++;
            i = i3;
        }
        return bArr;
    }

    public static byte[] intToBytes2_HL(int i, int i2) {
        byte[] bArr = {(byte) ((i >> 24) & 255), (byte) ((i >> 16) & 255), (byte) ((i >> 8) & 255), (byte) (i & 255)};
        byte[] bArr2 = {bArr[2], bArr[3]};
        if (i2 == 4) {
            return bArr;
        }
        if (i2 == 2) {
            return bArr2;
        }
        return null;
    }

    public static byte[] subBytes(byte[] bArr, int i, int i2) {
        byte[] bArr2 = new byte[i2];
        for (int i3 = i; i3 < i + i2; i3++) {
            bArr2[i3 - i] = bArr[i3];
        }
        return bArr2;
    }

    public static String to32BitBinaryString(long j) {
        return String.format("%32s", Long.toBinaryString(j)).replace(' ', '0');
    }
}
