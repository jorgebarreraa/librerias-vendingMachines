package com.google.zxing.datamatrix.encoder;

import android.app.kingsun.rk3288;

/* loaded from: classes.dex */
public final class ErrorCorrection {
    private static final int MODULO_VALUE = 301;
    private static final int[] FACTOR_SETS = {5, 7, 10, 11, 12, 14, 18, 20, 24, 28, 36, 42, 48, 56, 62, 68};
    private static final int[][] FACTORS = {new int[]{rk3288.RK30_PIN7_PA4, 48, 15, 111, 62}, new int[]{23, 68, 144, 134, rk3288.RK30_PIN7_PC0, 92, rk3288.RK30_PIN7_PD6}, new int[]{28, 24, rk3288.RK30_PIN5_PD1, rk3288.RK30_PIN5_PA6, rk3288.RK30_PIN6_PD7, rk3288.RK30_PIN7_PD0, 116, 255, 110, 61}, new int[]{rk3288.RK30_PIN5_PB7, 138, rk3288.RK30_PIN6_PB5, 12, rk3288.RK30_PIN6_PA2, rk3288.RK30_PIN5_PB0, 39, rk3288.RK30_PIN7_PC5, 60, 97, 120}, new int[]{41, 153, 158, 91, 61, 42, 142, rk3288.RK30_PIN6_PC5, 97, rk3288.RK30_PIN5_PC2, 100, rk3288.RK30_PIN7_PC2}, new int[]{156, 97, rk3288.RK30_PIN6_PA0, rk3288.RK30_PIN7_PD4, 95, 9, 157, 119, 138, 45, 18, rk3288.RK30_PIN5_PD2, 83, rk3288.RK30_PIN5_PD1}, new int[]{83, rk3288.RK30_PIN6_PA3, 100, 39, rk3288.RK30_PIN5_PD4, 75, 66, 61, rk3288.RK30_PIN7_PC1, rk3288.RK30_PIN6_PC5, 109, 129, 94, rk3288.RK30_PIN7_PD6, rk3288.RK30_PIN7_PA1, 48, 90, rk3288.RK30_PIN5_PD4}, new int[]{15, rk3288.RK30_PIN6_PA3, rk3288.RK30_PIN7_PC4, 9, rk3288.RK30_PIN7_PB1, 71, rk3288.RK30_PIN5_PB0, 2, rk3288.RK30_PIN5_PD4, 160, 153, 145, rk3288.RK30_PIN7_PD5, 79, 108, 82, 27, rk3288.RK30_PIN5_PB6, rk3288.RK30_PIN5_PD2, rk3288.RK30_PIN5_PB4}, new int[]{52, rk3288.RK30_PIN5_PD6, 88, rk3288.RK30_PIN6_PB5, 109, 39, rk3288.RK30_PIN5_PC0, 21, 155, rk3288.RK30_PIN6_PA5, rk3288.RK30_PIN7_PD3, rk3288.RK30_PIN6_PD7, 155, 21, 5, rk3288.RK30_PIN5_PB4, rk3288.RK30_PIN7_PD6, 124, 12, rk3288.RK30_PIN5_PC5, rk3288.RK30_PIN5_PD0, 96, 50, rk3288.RK30_PIN6_PA1}, new int[]{rk3288.RK30_PIN6_PC3, rk3288.RK30_PIN7_PA7, 43, 97, 71, 96, 103, rk3288.RK30_PIN5_PB6, 37, 151, rk3288.RK30_PIN5_PB2, 53, 75, 34, rk3288.RK30_PIN7_PD1, 121, 17, 138, 110, rk3288.RK30_PIN6_PC5, 141, 136, 120, 151, rk3288.RK30_PIN7_PB1, rk3288.RK30_PIN5_PB0, 93, 255}, new int[]{rk3288.RK30_PIN7_PC5, 127, rk3288.RK30_PIN7_PC2, rk3288.RK30_PIN6_PD2, 130, 250, 162, rk3288.RK30_PIN5_PC5, 102, 120, 84, rk3288.RK30_PIN5_PC3, rk3288.RK30_PIN6_PD4, rk3288.RK30_PIN7_PD3, 80, rk3288.RK30_PIN5_PC6, rk3288.RK30_PIN7_PA5, 18, 2, 4, 68, 33, 101, 137, 95, 119, 115, 44, rk3288.RK30_PIN5_PB7, rk3288.RK30_PIN5_PD0, 59, 25, rk3288.RK30_PIN7_PA1, 98, 81, 112}, new int[]{77, rk3288.RK30_PIN6_PA1, 137, 31, 19, 38, 22, 153, rk3288.RK30_PIN7_PC7, 105, 122, 2, rk3288.RK30_PIN7_PC5, 133, rk3288.RK30_PIN7_PC2, 8, rk3288.RK30_PIN5_PB7, 95, 100, 9, rk3288.RK30_PIN5_PA7, 105, rk3288.RK30_PIN6_PC6, 111, 57, 121, 21, 1, rk3288.RK30_PIN7_PD5, 57, 54, 101, rk3288.RK30_PIN7_PD0, rk3288.RK30_PIN6_PB2, 69, 50, 150, rk3288.RK30_PIN5_PC1, rk3288.RK30_PIN7_PA2, 5, 9, 5}, new int[]{rk3288.RK30_PIN7_PC5, 132, rk3288.RK30_PIN5_PB4, rk3288.RK30_PIN6_PD7, 96, 32, 117, 22, rk3288.RK30_PIN7_PB6, 133, rk3288.RK30_PIN7_PB6, rk3288.RK30_PIN7_PA7, rk3288.RK30_PIN6_PB5, rk3288.RK30_PIN5_PD4, rk3288.RK30_PIN7_PB5, 87, rk3288.RK30_PIN5_PD7, 106, 16, 147, 118, 23, 37, 90, rk3288.RK30_PIN5_PB2, rk3288.RK30_PIN6_PB5, 131, 88, 120, 100, 66, 138, rk3288.RK30_PIN5_PD2, rk3288.RK30_PIN7_PC0, 82, 44, rk3288.RK30_PIN5_PC0, 87, rk3288.RK30_PIN5_PD3, 147, 160, rk3288.RK30_PIN5_PB7, 69, rk3288.RK30_PIN6_PC5, 92, rk3288.RK30_PIN7_PD5, rk3288.RK30_PIN7_PA1, 19}, new int[]{rk3288.RK30_PIN5_PB7, 9, rk3288.RK30_PIN6_PD7, rk3288.RK30_PIN7_PB6, 12, 17, rk3288.RK30_PIN6_PD4, rk3288.RK30_PIN6_PC0, 100, 29, rk3288.RK30_PIN5_PB7, rk3288.RK30_PIN5_PB2, rk3288.RK30_PIN7_PA6, rk3288.RK30_PIN6_PA0, rk3288.RK30_PIN6_PC7, rk3288.RK30_PIN7_PB3, 150, 159, 36, rk3288.RK30_PIN6_PD7, 38, 200, 132, 54, rk3288.RK30_PIN7_PA4, 146, rk3288.RK30_PIN6_PD2, rk3288.RK30_PIN7_PB2, 117, rk3288.RK30_PIN6_PB3, 29, rk3288.RK30_PIN7_PB0, 144, rk3288.RK30_PIN7_PB6, 22, 150, rk3288.RK30_PIN6_PB1, 117, 62, rk3288.RK30_PIN6_PB7, 164, 13, 137, rk3288.RK30_PIN7_PC5, 127, 67, rk3288.RK30_PIN7_PC7, 28, 155, 43, rk3288.RK30_PIN6_PB3, 107, rk3288.RK30_PIN7_PB1, 53, 143, 46}, new int[]{rk3288.RK30_PIN7_PC2, 93, rk3288.RK30_PIN5_PB1, 50, 144, rk3288.RK30_PIN6_PC2, 39, 118, rk3288.RK30_PIN6_PB2, rk3288.RK30_PIN5_PD4, rk3288.RK30_PIN6_PB1, rk3288.RK30_PIN5_PD5, 143, 108, rk3288.RK30_PIN6_PA4, 37, rk3288.RK30_PIN5_PD1, 112, 134, rk3288.RK30_PIN7_PA6, rk3288.RK30_PIN7_PC5, 63, rk3288.RK30_PIN6_PA5, rk3288.RK30_PIN5_PD6, 250, 106, rk3288.RK30_PIN5_PD1, 221, rk3288.RK30_PIN5_PB7, 64, 114, 71, 161, 44, 147, 6, 27, rk3288.RK30_PIN6_PD2, 51, 63, 87, 10, 40, 130, rk3288.RK30_PIN5_PD4, 17, 163, 31, rk3288.RK30_PIN5_PC0, rk3288.RK30_PIN5_PB2, 4, 107, rk3288.RK30_PIN7_PB0, 7, 94, rk3288.RK30_PIN5_PA6, rk3288.RK30_PIN7_PA0, 124, 86, 47, 11, rk3288.RK30_PIN6_PB4}, new int[]{rk3288.RK30_PIN6_PD4, rk3288.RK30_PIN7_PA4, rk3288.RK30_PIN5_PB5, 89, rk3288.RK30_PIN7_PD3, 149, 159, 56, 89, 33, 147, rk3288.RK30_PIN7_PC4, 154, 36, 73, 127, rk3288.RK30_PIN6_PC5, 136, rk3288.RK30_PIN7_PD0, 180, rk3288.RK30_PIN7_PB2, rk3288.RK30_PIN6_PA5, 158, rk3288.RK30_PIN5_PC1, 68, 122, 93, rk3288.RK30_PIN6_PC5, 15, 160, rk3288.RK30_PIN7_PA3, rk3288.RK30_PIN7_PB4, 66, 139, 153, rk3288.RK30_PIN5_PD1, rk3288.RK30_PIN6_PB2, rk3288.RK30_PIN5_PA7, rk3288.RK30_PIN5_PC3, 25, rk3288.RK30_PIN6_PD4, rk3288.RK30_PIN7_PB0, 96, rk3288.RK30_PIN6_PC2, rk3288.RK30_PIN7_PA7, 136, rk3288.RK30_PIN6_PD7, rk3288.RK30_PIN7_PB7, rk3288.RK30_PIN5_PC5, rk3288.RK30_PIN7_PC1, 59, 52, rk3288.RK30_PIN5_PB4, 25, 49, rk3288.RK30_PIN7_PB0, rk3288.RK30_PIN6_PC3, rk3288.RK30_PIN5_PD5, 64, 54, 108, 153, 132, 63, 96, 103, 82, rk3288.RK30_PIN5_PD2}};
    private static final int[] LOG = new int[256];
    private static final int[] ALOG = new int[255];

    static {
        int i = 1;
        for (int i2 = 0; i2 < 255; i2++) {
            ALOG[i2] = i;
            LOG[i] = i2;
            i *= 2;
            if (i >= 256) {
                i ^= MODULO_VALUE;
            }
        }
    }

    private ErrorCorrection() {
    }

    private static String createECCBlock(CharSequence charSequence, int i) {
        return createECCBlock(charSequence, 0, charSequence.length(), i);
    }

    private static String createECCBlock(CharSequence charSequence, int i, int i2, int i3) {
        int i4 = 0;
        while (true) {
            int[] iArr = FACTOR_SETS;
            if (i4 >= iArr.length) {
                i4 = -1;
                break;
            }
            if (iArr[i4] == i3) {
                break;
            }
            i4++;
        }
        if (i4 < 0) {
            throw new IllegalArgumentException("Illegal number of error correction codewords specified: " + i3);
        }
        int[] iArr2 = FACTORS[i4];
        char[] cArr = new char[i3];
        for (int i5 = 0; i5 < i3; i5++) {
            cArr[i5] = 0;
        }
        for (int i6 = i; i6 < i + i2; i6++) {
            int i7 = i3 - 1;
            int charAt = cArr[i7] ^ charSequence.charAt(i6);
            while (i7 > 0) {
                if (charAt == 0 || iArr2[i7] == 0) {
                    cArr[i7] = cArr[i7 - 1];
                } else {
                    char c = cArr[i7 - 1];
                    int[] iArr3 = ALOG;
                    int[] iArr4 = LOG;
                    cArr[i7] = (char) (c ^ iArr3[(iArr4[charAt] + iArr4[iArr2[i7]]) % 255]);
                }
                i7--;
            }
            if (charAt == 0 || iArr2[0] == 0) {
                cArr[0] = 0;
            } else {
                int[] iArr5 = ALOG;
                int[] iArr6 = LOG;
                cArr[0] = (char) iArr5[(iArr6[charAt] + iArr6[iArr2[0]]) % 255];
            }
        }
        char[] cArr2 = new char[i3];
        for (int i8 = 0; i8 < i3; i8++) {
            cArr2[i8] = cArr[(i3 - i8) - 1];
        }
        return String.valueOf(cArr2);
    }

    public static String encodeECC200(String str, SymbolInfo symbolInfo) {
        if (str.length() != symbolInfo.getDataCapacity()) {
            throw new IllegalArgumentException("The number of codewords does not match the selected symbol");
        }
        StringBuilder sb = new StringBuilder(symbolInfo.getDataCapacity() + symbolInfo.getErrorCodewords());
        sb.append(str);
        int interleavedBlockCount = symbolInfo.getInterleavedBlockCount();
        if (interleavedBlockCount == 1) {
            sb.append(createECCBlock(str, symbolInfo.getErrorCodewords()));
        } else {
            sb.setLength(sb.capacity());
            int[] iArr = new int[interleavedBlockCount];
            int[] iArr2 = new int[interleavedBlockCount];
            int[] iArr3 = new int[interleavedBlockCount];
            int i = 0;
            while (i < interleavedBlockCount) {
                int i2 = i + 1;
                iArr[i] = symbolInfo.getDataLengthForInterleavedBlock(i2);
                iArr2[i] = symbolInfo.getErrorLengthForInterleavedBlock(i2);
                iArr3[i] = 0;
                if (i > 0) {
                    iArr3[i] = iArr3[i - 1] + iArr[i];
                }
                i = i2;
            }
            for (int i3 = 0; i3 < interleavedBlockCount; i3++) {
                StringBuilder sb2 = new StringBuilder(iArr[i3]);
                for (int i4 = i3; i4 < symbolInfo.getDataCapacity(); i4 += interleavedBlockCount) {
                    sb2.append(str.charAt(i4));
                }
                String createECCBlock = createECCBlock(sb2.toString(), iArr2[i3]);
                int i5 = i3;
                int i6 = 0;
                while (i5 < iArr2[i3] * interleavedBlockCount) {
                    sb.setCharAt(symbolInfo.getDataCapacity() + i5, createECCBlock.charAt(i6));
                    i5 += interleavedBlockCount;
                    i6++;
                }
            }
        }
        return sb.toString();
    }
}
