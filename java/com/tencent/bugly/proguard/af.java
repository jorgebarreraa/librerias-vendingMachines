package com.tencent.bugly.proguard;

import android.content.Context;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/* compiled from: BUGLY */
/* loaded from: classes.dex */
public final class af {
    static af a;
    protected Context b;
    public Map<String, String> c = null;

    /* JADX INFO: Access modifiers changed from: package-private */
    public af(Context context) {
        this.b = context;
    }

    private static HttpURLConnection a(String str, String str2) {
        HttpURLConnection httpURLConnection;
        try {
            URL url = new URL(str2);
            if (an.a != null) {
                httpURLConnection = (HttpURLConnection) url.openConnection(an.a);
            } else if (str == null || !str.toLowerCase(Locale.US).contains("wap")) {
                httpURLConnection = (HttpURLConnection) url.openConnection();
            } else {
                httpURLConnection = (HttpURLConnection) url.openConnection(new Proxy(Proxy.Type.HTTP, new InetSocketAddress(System.getProperty("http.proxyHost"), Integer.parseInt(System.getProperty("http.proxyPort")))));
            }
            httpURLConnection.setConnectTimeout(30000);
            httpURLConnection.setReadTimeout(10000);
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setInstanceFollowRedirects(false);
            return httpURLConnection;
        } catch (Throwable th) {
            if (al.a(th)) {
                return null;
            }
            th.printStackTrace();
            return null;
        }
    }

    private static HttpURLConnection a(String str, byte[] bArr, String str2, Map<String, String> map) {
        if (str == null) {
            al.e("destUrl is null.", new Object[0]);
            return null;
        }
        HttpURLConnection a2 = a(str2, str);
        if (a2 == null) {
            al.e("Failed to get HttpURLConnection object.", new Object[0]);
            return null;
        }
        try {
            a2.setRequestProperty("wup_version", "3.0");
            if (map != null && map.size() > 0) {
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    a2.setRequestProperty(entry.getKey(), URLEncoder.encode(entry.getValue(), "utf-8"));
                }
            }
            a2.setRequestProperty("A37", URLEncoder.encode(str2, "utf-8"));
            a2.setRequestProperty("A38", URLEncoder.encode(str2, "utf-8"));
            OutputStream outputStream = a2.getOutputStream();
            if (bArr == null) {
                outputStream.write(0);
            } else {
                outputStream.write(bArr);
            }
            return a2;
        } catch (Throwable th) {
            if (!al.a(th)) {
                th.printStackTrace();
            }
            al.e("Failed to upload, please check your network.", new Object[0]);
            return null;
        }
    }

    private static Map<String, String> a(HttpURLConnection httpURLConnection) {
        HashMap hashMap = new HashMap();
        Map<String, List<String>> headerFields = httpURLConnection.getHeaderFields();
        if (headerFields == null || headerFields.size() == 0) {
            return null;
        }
        for (String str : headerFields.keySet()) {
            List<String> list = headerFields.get(str);
            if (list.size() > 0) {
                hashMap.put(str, list.get(0));
            }
        }
        return hashMap;
    }

    /* JADX WARN: Removed duplicated region for block: B:36:0x004f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static byte[] b(java.net.HttpURLConnection r5) {
        /*
            r0 = 0
            if (r5 != 0) goto L4
            return r0
        L4:
            java.io.BufferedInputStream r1 = new java.io.BufferedInputStream     // Catch: java.lang.Throwable -> L33 java.lang.Throwable -> L36
            java.io.InputStream r5 = r5.getInputStream()     // Catch: java.lang.Throwable -> L33 java.lang.Throwable -> L36
            r1.<init>(r5)     // Catch: java.lang.Throwable -> L33 java.lang.Throwable -> L36
            java.io.ByteArrayOutputStream r5 = new java.io.ByteArrayOutputStream     // Catch: java.lang.Throwable -> L31 java.lang.Throwable -> L4c
            r5.<init>()     // Catch: java.lang.Throwable -> L31 java.lang.Throwable -> L4c
            r2 = 1024(0x400, float:1.435E-42)
            byte[] r2 = new byte[r2]     // Catch: java.lang.Throwable -> L31 java.lang.Throwable -> L4c
        L16:
            int r3 = r1.read(r2)     // Catch: java.lang.Throwable -> L31 java.lang.Throwable -> L4c
            if (r3 <= 0) goto L21
            r4 = 0
            r5.write(r2, r4, r3)     // Catch: java.lang.Throwable -> L31 java.lang.Throwable -> L4c
            goto L16
        L21:
            r5.flush()     // Catch: java.lang.Throwable -> L31 java.lang.Throwable -> L4c
            byte[] r5 = r5.toByteArray()     // Catch: java.lang.Throwable -> L31 java.lang.Throwable -> L4c
            r1.close()     // Catch: java.lang.Throwable -> L2c
            goto L30
        L2c:
            r0 = move-exception
            r0.printStackTrace()
        L30:
            return r5
        L31:
            r5 = move-exception
            goto L38
        L33:
            r5 = move-exception
            r1 = r0
            goto L4d
        L36:
            r5 = move-exception
            r1 = r0
        L38:
            boolean r2 = com.tencent.bugly.proguard.al.a(r5)     // Catch: java.lang.Throwable -> L4c
            if (r2 != 0) goto L41
            r5.printStackTrace()     // Catch: java.lang.Throwable -> L4c
        L41:
            if (r1 == 0) goto L4b
            r1.close()     // Catch: java.lang.Throwable -> L47
            goto L4b
        L47:
            r5 = move-exception
            r5.printStackTrace()
        L4b:
            return r0
        L4c:
            r5 = move-exception
        L4d:
            if (r1 == 0) goto L57
            r1.close()     // Catch: java.lang.Throwable -> L53
            goto L57
        L53:
            r0 = move-exception
            r0.printStackTrace()
        L57:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.af.b(java.net.HttpURLConnection):byte[]");
    }

    /* JADX WARN: Removed duplicated region for block: B:69:0x0177 A[Catch: all -> 0x016a, TRY_LEAVE, TryCatch #10 {all -> 0x016a, blocks: (B:23:0x009c, B:25:0x00b3, B:28:0x00c4, B:38:0x00c2, B:79:0x00ed, B:98:0x00f5, B:85:0x0120, B:88:0x012b, B:51:0x0148, B:54:0x0155, B:67:0x0171, B:69:0x0177), top: B:22:0x009c }] */
    /* JADX WARN: Unreachable blocks removed: 2, instructions: 2 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final byte[] a(java.lang.String r21, byte[] r22, com.tencent.bugly.proguard.aj r23, java.util.Map<java.lang.String, java.lang.String> r24) {
        /*
            Method dump skipped, instructions count: 432
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.af.a(java.lang.String, byte[], com.tencent.bugly.proguard.aj, java.util.Map):byte[]");
    }
}
