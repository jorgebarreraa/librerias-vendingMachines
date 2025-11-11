package com.jess.arms.utils;

import android.os.Build;
import com.bumptech.glide.load.Key;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import java.util.zip.Inflater;

/* loaded from: classes.dex */
public class ZipHelper {
    private ZipHelper() {
        throw new IllegalStateException("you can't instantiate me!");
    }

    public static void closeQuietly(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (RuntimeException e) {
                throw e;
            } catch (Exception unused) {
            }
        }
    }

    public static byte[] compressForGzip(String str) {
        ByteArrayOutputStream byteArrayOutputStream;
        GZIPOutputStream gZIPOutputStream;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream(str.length());
            try {
                gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
                try {
                    try {
                        if (Build.VERSION.SDK_INT >= 19) {
                            gZIPOutputStream.write(str.getBytes(StandardCharsets.UTF_8));
                        } else {
                            gZIPOutputStream.write(str.getBytes(Key.STRING_CHARSET_NAME));
                        }
                        byte[] byteArray = byteArrayOutputStream.toByteArray();
                        closeQuietly(gZIPOutputStream);
                        closeQuietly(byteArrayOutputStream);
                        return byteArray;
                    } catch (IOException e) {
                        e = e;
                        e.printStackTrace();
                        closeQuietly(gZIPOutputStream);
                        closeQuietly(byteArrayOutputStream);
                        return null;
                    }
                } catch (Throwable th) {
                    th = th;
                    closeQuietly(gZIPOutputStream);
                    closeQuietly(byteArrayOutputStream);
                    throw th;
                }
            } catch (IOException e2) {
                e = e2;
                gZIPOutputStream = null;
            } catch (Throwable th2) {
                th = th2;
                gZIPOutputStream = null;
                closeQuietly(gZIPOutputStream);
                closeQuietly(byteArrayOutputStream);
                throw th;
            }
        } catch (IOException e3) {
            e = e3;
            byteArrayOutputStream = null;
            gZIPOutputStream = null;
        } catch (Throwable th3) {
            th = th3;
            byteArrayOutputStream = null;
            gZIPOutputStream = null;
        }
    }

    public static byte[] compressForZlib(String str) {
        try {
            return Build.VERSION.SDK_INT >= 19 ? compressForZlib(str.getBytes(StandardCharsets.UTF_8)) : compressForZlib(str.getBytes(Key.STRING_CHARSET_NAME));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static byte[] compressForZlib(byte[] bArr) {
        Deflater deflater = new Deflater();
        deflater.setInput(bArr);
        deflater.finish();
        byte[] bArr2 = new byte[32767];
        int deflate = deflater.deflate(bArr2);
        byte[] bArr3 = new byte[deflate];
        System.arraycopy(bArr2, 0, bArr3, 0, deflate);
        return bArr3;
    }

    public static String decompressForGzip(byte[] bArr) {
        return decompressForGzip(bArr, Key.STRING_CHARSET_NAME);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v0, types: [byte[]] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v3, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r7v5 */
    public static String decompressForGzip(byte[] bArr, String str) {
        ByteArrayInputStream byteArrayInputStream;
        GZIPInputStream gZIPInputStream;
        int length = bArr.length;
        try {
            try {
                byteArrayInputStream = new ByteArrayInputStream(bArr);
            } catch (Throwable th) {
                th = th;
            }
            try {
                gZIPInputStream = new GZIPInputStream(byteArrayInputStream, length);
                try {
                    StringBuilder sb = new StringBuilder();
                    byte[] bArr2 = new byte[length];
                    while (true) {
                        int read = gZIPInputStream.read(bArr2);
                        if (read == -1) {
                            String sb2 = sb.toString();
                            closeQuietly(gZIPInputStream);
                            closeQuietly(byteArrayInputStream);
                            return sb2;
                        }
                        sb.append(new String(bArr2, 0, read, str));
                    }
                } catch (IOException e) {
                    e = e;
                    e.printStackTrace();
                    closeQuietly(gZIPInputStream);
                    closeQuietly(byteArrayInputStream);
                    return null;
                }
            } catch (IOException e2) {
                e = e2;
                gZIPInputStream = null;
            } catch (Throwable th2) {
                th = th2;
                bArr = 0;
                closeQuietly(bArr);
                closeQuietly(byteArrayInputStream);
                throw th;
            }
        } catch (IOException e3) {
            e = e3;
            gZIPInputStream = null;
            byteArrayInputStream = null;
        } catch (Throwable th3) {
            th = th3;
            bArr = 0;
            byteArrayInputStream = null;
        }
    }

    public static byte[] decompressForZlib(byte[] bArr) {
        byte[] bArr2;
        Inflater inflater = new Inflater();
        int length = bArr.length;
        inflater.setInput(bArr, 0, length);
        ArrayList arrayList = new ArrayList();
        while (true) {
            bArr2 = null;
            try {
                if (inflater.needsInput()) {
                    break;
                }
                byte[] bArr3 = new byte[length];
                int inflate = inflater.inflate(bArr3);
                for (int i = 0; i < inflate; i++) {
                    arrayList.add(Byte.valueOf(bArr3[i]));
                }
            } catch (DataFormatException e) {
                e.printStackTrace();
            }
        }
        bArr2 = new byte[arrayList.size()];
        for (int i2 = 0; i2 < bArr2.length; i2++) {
            bArr2[i2] = ((Byte) arrayList.get(i2)).byteValue();
        }
        inflater.end();
        return bArr2;
    }

    public static String decompressToStringForZlib(byte[] bArr) {
        return decompressToStringForZlib(bArr, Key.STRING_CHARSET_NAME);
    }

    public static String decompressToStringForZlib(byte[] bArr, String str) {
        byte[] decompressForZlib = decompressForZlib(bArr);
        try {
            return new String(decompressForZlib, 0, decompressForZlib.length, str);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
