package com.tencent.bugly.proguard;

import android.content.Context;
import com.tencent.bugly.crashreport.crash.CrashDetailBean;
import com.tencent.bugly.crashreport.crash.jni.NativeExceptionHandler;
import com.yj.coffeemachines.app.serialport.ShellUtils;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/* compiled from: BUGLY */
/* loaded from: classes.dex */
public final class be {
    private static List<File> a = new ArrayList();

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v3, types: [boolean] */
    /* JADX WARN: Type inference failed for: r7v6 */
    public static CrashDetailBean a(Context context, String str, NativeExceptionHandler nativeExceptionHandler) {
        BufferedInputStream bufferedInputStream;
        String str2;
        String a2;
        if (context == null || str == null || nativeExceptionHandler == null) {
            al.e("get eup record file args error", new Object[0]);
            return null;
        }
        File file = new File(str, "rqd_record.eup");
        if (file.exists()) {
            BufferedInputStream canRead = file.canRead();
            try {
                if (canRead != 0) {
                    try {
                        bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
                        try {
                            String a3 = a(bufferedInputStream);
                            if (a3 != null && a3.equals("NATIVE_RQD_REPORT")) {
                                HashMap hashMap = new HashMap();
                                loop0: while (true) {
                                    str2 = null;
                                    while (true) {
                                        a2 = a(bufferedInputStream);
                                        if (a2 == null) {
                                            break loop0;
                                        }
                                        if (str2 == null) {
                                            str2 = a2;
                                        }
                                    }
                                    hashMap.put(str2, a2);
                                }
                                if (str2 != null) {
                                    al.e("record not pair! drop! %s", str2);
                                    try {
                                        bufferedInputStream.close();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                    return null;
                                }
                                CrashDetailBean a4 = a(context, hashMap, nativeExceptionHandler);
                                try {
                                    bufferedInputStream.close();
                                } catch (IOException e2) {
                                    e2.printStackTrace();
                                }
                                return a4;
                            }
                            al.e("record read fail! %s", a3);
                            try {
                                bufferedInputStream.close();
                            } catch (IOException e3) {
                                e3.printStackTrace();
                            }
                            return null;
                        } catch (IOException e4) {
                            e = e4;
                            e.printStackTrace();
                            if (bufferedInputStream != null) {
                                try {
                                    bufferedInputStream.close();
                                } catch (IOException e5) {
                                    e5.printStackTrace();
                                }
                            }
                            return null;
                        }
                    } catch (IOException e6) {
                        e = e6;
                        bufferedInputStream = null;
                    } catch (Throwable th) {
                        th = th;
                        canRead = 0;
                        if (canRead != 0) {
                            try {
                                canRead.close();
                            } catch (IOException e7) {
                                e7.printStackTrace();
                            }
                        }
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
            }
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0037 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0038  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static com.tencent.bugly.crashreport.crash.CrashDetailBean a(android.content.Context r25, java.util.Map<java.lang.String, java.lang.String> r26, com.tencent.bugly.crashreport.crash.jni.NativeExceptionHandler r27) {
        /*
            Method dump skipped, instructions count: 589
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.be.a(android.content.Context, java.util.Map, com.tencent.bugly.crashreport.crash.jni.NativeExceptionHandler):com.tencent.bugly.crashreport.crash.CrashDetailBean");
    }

    private static <KeyT, ValueT> ValueT a(Map<KeyT, ValueT> map, KeyT keyt, ValueT valuet) {
        ValueT valuet2;
        try {
            valuet2 = map.get(keyt);
        } catch (Exception e) {
            al.a(e);
        }
        return valuet2 != null ? valuet2 : valuet;
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x0030, code lost:
    
        r1.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0033, code lost:
    
        return null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x002e, code lost:
    
        if (r1 == null) goto L21;
     */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0037  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static java.lang.String a(java.io.BufferedInputStream r4) throws java.io.IOException {
        /*
            r0 = 0
            java.io.ByteArrayOutputStream r1 = new java.io.ByteArrayOutputStream     // Catch: java.lang.Throwable -> L26 java.lang.Throwable -> L29
            r2 = 1024(0x400, float:1.435E-42)
            r1.<init>(r2)     // Catch: java.lang.Throwable -> L26 java.lang.Throwable -> L29
        L8:
            int r2 = r4.read()     // Catch: java.lang.Throwable -> L24 java.lang.Throwable -> L34
            r3 = -1
            if (r2 == r3) goto L30
            if (r2 != 0) goto L20
            java.lang.String r4 = new java.lang.String     // Catch: java.lang.Throwable -> L24 java.lang.Throwable -> L34
            byte[] r2 = r1.toByteArray()     // Catch: java.lang.Throwable -> L24 java.lang.Throwable -> L34
            java.lang.String r3 = "UTf-8"
            r4.<init>(r2, r3)     // Catch: java.lang.Throwable -> L24 java.lang.Throwable -> L34
            r1.close()
            return r4
        L20:
            r1.write(r2)     // Catch: java.lang.Throwable -> L24 java.lang.Throwable -> L34
            goto L8
        L24:
            r4 = move-exception
            goto L2b
        L26:
            r4 = move-exception
            r1 = r0
            goto L35
        L29:
            r4 = move-exception
            r1 = r0
        L2b:
            com.tencent.bugly.proguard.al.a(r4)     // Catch: java.lang.Throwable -> L34
            if (r1 == 0) goto L33
        L30:
            r1.close()
        L33:
            return r0
        L34:
            r4 = move-exception
        L35:
            if (r1 == 0) goto L3a
            r1.close()
        L3a:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.be.a(java.io.BufferedInputStream):java.lang.String");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static String a(String str) {
        if (str == null) {
            return "";
        }
        String[] split = str.split(ShellUtils.COMMAND_LINE_END);
        if (split == null || split.length == 0) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        for (String str2 : split) {
            if (!str2.contains("java.lang.Thread.getStackTrace(")) {
                sb.append(str2);
                sb.append(ShellUtils.COMMAND_LINE_END);
            }
        }
        return sb.toString();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v1, types: [java.lang.StringBuilder] */
    /* JADX WARN: Type inference failed for: r6v12 */
    /* JADX WARN: Type inference failed for: r6v13 */
    /* JADX WARN: Type inference failed for: r6v7, types: [java.lang.String] */
    public static String a(String str, int i, String str2, boolean z) {
        BufferedReader bufferedReader;
        BufferedReader bufferedReader2 = null;
        if (str != null && i > 0) {
            File file = new File(str);
            if (file.exists() && file.canRead()) {
                al.a("Read system log from native record file(length: %s bytes): %s", Long.valueOf(file.length()), file.getAbsolutePath());
                a.add(file);
                al.c("Add this record file to list for cleaning lastly.", new Object[0]);
                if (str2 == null) {
                    return ap.a(new File(str), i, z);
                }
                String sb = new StringBuilder();
                try {
                    try {
                        try {
                            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "utf-8"));
                            while (true) {
                                try {
                                    String readLine = bufferedReader.readLine();
                                    if (readLine == null) {
                                        break;
                                    }
                                    if (Pattern.compile(str2 + "[ ]*:").matcher(readLine).find()) {
                                        sb.append(readLine);
                                        sb.append(ShellUtils.COMMAND_LINE_END);
                                    }
                                    if (i > 0 && sb.length() > i) {
                                        if (z) {
                                            sb.delete(i, sb.length());
                                            break;
                                        }
                                        sb.delete(0, sb.length() - i);
                                    }
                                } catch (Throwable th) {
                                    th = th;
                                    if (bufferedReader != null) {
                                        try {
                                            bufferedReader.close();
                                        } catch (Exception e) {
                                            al.a(e);
                                        }
                                    }
                                    throw th;
                                }
                            }
                            String sb2 = sb.toString();
                            bufferedReader.close();
                            sb = sb2;
                        } catch (Throwable th2) {
                            th = th2;
                        }
                        return sb;
                    } catch (Throwable th3) {
                        th = th3;
                        bufferedReader = bufferedReader2;
                    }
                } catch (Exception e2) {
                    al.a(e2);
                    return sb;
                }
            }
        }
        return null;
    }

    public static String a(String str, String str2) {
        if (str == null || str2 == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        String b = b(str, str2);
        if (b != null && !b.isEmpty()) {
            sb.append("Register infos:\n");
            sb.append(b);
        }
        String c = c(str, str2);
        if (c != null && !c.isEmpty()) {
            if (sb.length() > 0) {
                sb.append(ShellUtils.COMMAND_LINE_END);
            }
            sb.append("System SO infos:\n");
            sb.append(c);
        }
        return sb.toString();
    }

    private static Map<String, String> a(Map<String, String> map) {
        String str = map.get("key-value");
        if (str == null) {
            return null;
        }
        HashMap hashMap = new HashMap();
        for (String str2 : str.split(ShellUtils.COMMAND_LINE_END)) {
            String[] split = str2.split("=");
            if (split.length == 2) {
                hashMap.put(split[0], split[1]);
            }
        }
        return hashMap;
    }

    public static void a(boolean z, String str) {
        if (str != null) {
            a.add(new File(str, "rqd_record.eup"));
            a.add(new File(str, "reg_record.txt"));
            a.add(new File(str, "map_record.txt"));
            a.add(new File(str, "backup_record.txt"));
            if (z) {
                c(str);
            }
        }
        List<File> list = a;
        if (list == null || list.size() <= 0) {
            return;
        }
        for (File file : a) {
            if (file.exists() && file.canWrite()) {
                file.delete();
                al.c("Delete record file %s", file.getAbsoluteFile());
            }
        }
    }

    private static long b(Map<String, String> map) {
        String str = map.get("launchTime");
        if (str == null) {
            return -1L;
        }
        al.c("[Native record info] launchTime: %s", str);
        try {
            return Long.parseLong(str);
        } catch (NumberFormatException e) {
            if (al.a(e)) {
                return -1L;
            }
            e.printStackTrace();
            return -1L;
        }
    }

    public static String b(String str) {
        if (str == null) {
            return null;
        }
        File file = new File(str, "backup_record.txt");
        if (file.exists()) {
            return file.getAbsolutePath();
        }
        return null;
    }

    /* JADX WARN: Code restructure failed: missing block: B:41:0x006d, code lost:
    
        r9.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x0071, code lost:
    
        r9 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x0072, code lost:
    
        com.tencent.bugly.proguard.al.a(r9);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static java.lang.String b(java.lang.String r9, java.lang.String r10) {
        /*
            java.lang.String r0 = "reg_record.txt"
            java.io.BufferedReader r9 = com.tencent.bugly.proguard.ap.b(r9, r0)
            r0 = 0
            if (r9 != 0) goto La
            return r0
        La:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L76 java.lang.Throwable -> L78
            r1.<init>()     // Catch: java.lang.Throwable -> L76 java.lang.Throwable -> L78
            java.lang.String r2 = r9.readLine()     // Catch: java.lang.Throwable -> L76 java.lang.Throwable -> L78
            if (r2 == 0) goto L6b
            boolean r10 = r2.startsWith(r10)     // Catch: java.lang.Throwable -> L76 java.lang.Throwable -> L78
            if (r10 != 0) goto L1c
            goto L6b
        L1c:
            java.lang.String r10 = "                "
            r2 = 18
            r3 = 0
            r2 = 0
            r4 = 18
            r5 = 0
        L25:
            java.lang.String r6 = r9.readLine()     // Catch: java.lang.Throwable -> L76 java.lang.Throwable -> L78
            java.lang.String r7 = "\n"
            if (r6 == 0) goto L59
            int r8 = r2 % 4
            if (r8 != 0) goto L3c
            if (r2 <= 0) goto L36
            r1.append(r7)     // Catch: java.lang.Throwable -> L76 java.lang.Throwable -> L78
        L36:
            java.lang.String r5 = "  "
            r1.append(r5)     // Catch: java.lang.Throwable -> L76 java.lang.Throwable -> L78
            goto L4f
        L3c:
            int r7 = r6.length()     // Catch: java.lang.Throwable -> L76 java.lang.Throwable -> L78
            r8 = 16
            if (r7 <= r8) goto L46
            r4 = 28
        L46:
            int r5 = r4 - r5
            java.lang.String r5 = r10.substring(r3, r5)     // Catch: java.lang.Throwable -> L76 java.lang.Throwable -> L78
            r1.append(r5)     // Catch: java.lang.Throwable -> L76 java.lang.Throwable -> L78
        L4f:
            int r5 = r6.length()     // Catch: java.lang.Throwable -> L76 java.lang.Throwable -> L78
            r1.append(r6)     // Catch: java.lang.Throwable -> L76 java.lang.Throwable -> L78
            int r2 = r2 + 1
            goto L25
        L59:
            r1.append(r7)     // Catch: java.lang.Throwable -> L76 java.lang.Throwable -> L78
            java.lang.String r10 = r1.toString()     // Catch: java.lang.Throwable -> L76 java.lang.Throwable -> L78
            if (r9 == 0) goto L6a
            r9.close()     // Catch: java.lang.Exception -> L66
            goto L6a
        L66:
            r9 = move-exception
            com.tencent.bugly.proguard.al.a(r9)
        L6a:
            return r10
        L6b:
            if (r9 == 0) goto L75
            r9.close()     // Catch: java.lang.Exception -> L71
            goto L75
        L71:
            r9 = move-exception
            com.tencent.bugly.proguard.al.a(r9)
        L75:
            return r0
        L76:
            r10 = move-exception
            goto L87
        L78:
            r10 = move-exception
            com.tencent.bugly.proguard.al.a(r10)     // Catch: java.lang.Throwable -> L76
            if (r9 == 0) goto L86
            r9.close()     // Catch: java.lang.Exception -> L82
            goto L86
        L82:
            r9 = move-exception
            com.tencent.bugly.proguard.al.a(r9)
        L86:
            return r0
        L87:
            if (r9 == 0) goto L91
            r9.close()     // Catch: java.lang.Exception -> L8d
            goto L91
        L8d:
            r9 = move-exception
            com.tencent.bugly.proguard.al.a(r9)
        L91:
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.be.b(java.lang.String, java.lang.String):java.lang.String");
    }

    /* JADX WARN: Code restructure failed: missing block: B:27:0x0041, code lost:
    
        r3.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0045, code lost:
    
        r3 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0046, code lost:
    
        com.tencent.bugly.proguard.al.a(r3);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static java.lang.String c(java.lang.String r3, java.lang.String r4) {
        /*
            java.lang.String r0 = "map_record.txt"
            java.io.BufferedReader r3 = com.tencent.bugly.proguard.ap.b(r3, r0)
            r0 = 0
            if (r3 != 0) goto La
            return r0
        La:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L4a java.lang.Throwable -> L4c
            r1.<init>()     // Catch: java.lang.Throwable -> L4a java.lang.Throwable -> L4c
            java.lang.String r2 = r3.readLine()     // Catch: java.lang.Throwable -> L4a java.lang.Throwable -> L4c
            if (r2 == 0) goto L3f
            boolean r4 = r2.startsWith(r4)     // Catch: java.lang.Throwable -> L4a java.lang.Throwable -> L4c
            if (r4 != 0) goto L1c
            goto L3f
        L1c:
            java.lang.String r4 = r3.readLine()     // Catch: java.lang.Throwable -> L4a java.lang.Throwable -> L4c
            if (r4 == 0) goto L30
            java.lang.String r2 = "  "
            r1.append(r2)     // Catch: java.lang.Throwable -> L4a java.lang.Throwable -> L4c
            r1.append(r4)     // Catch: java.lang.Throwable -> L4a java.lang.Throwable -> L4c
            java.lang.String r4 = "\n"
            r1.append(r4)     // Catch: java.lang.Throwable -> L4a java.lang.Throwable -> L4c
            goto L1c
        L30:
            java.lang.String r4 = r1.toString()     // Catch: java.lang.Throwable -> L4a java.lang.Throwable -> L4c
            if (r3 == 0) goto L3e
            r3.close()     // Catch: java.lang.Exception -> L3a
            goto L3e
        L3a:
            r3 = move-exception
            com.tencent.bugly.proguard.al.a(r3)
        L3e:
            return r4
        L3f:
            if (r3 == 0) goto L49
            r3.close()     // Catch: java.lang.Exception -> L45
            goto L49
        L45:
            r3 = move-exception
            com.tencent.bugly.proguard.al.a(r3)
        L49:
            return r0
        L4a:
            r4 = move-exception
            goto L5b
        L4c:
            r4 = move-exception
            com.tencent.bugly.proguard.al.a(r4)     // Catch: java.lang.Throwable -> L4a
            if (r3 == 0) goto L5a
            r3.close()     // Catch: java.lang.Exception -> L56
            goto L5a
        L56:
            r3 = move-exception
            com.tencent.bugly.proguard.al.a(r3)
        L5a:
            return r0
        L5b:
            if (r3 == 0) goto L65
            r3.close()     // Catch: java.lang.Exception -> L61
            goto L65
        L61:
            r3 = move-exception
            com.tencent.bugly.proguard.al.a(r3)
        L65:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.be.c(java.lang.String, java.lang.String):java.lang.String");
    }

    public static void c(String str) {
        File[] listFiles;
        if (str == null) {
            return;
        }
        try {
            File file = new File(str);
            if (file.canRead() && file.isDirectory() && (listFiles = file.listFiles()) != null) {
                for (File file2 : listFiles) {
                    if (file2.canRead() && file2.canWrite() && file2.length() == 0) {
                        file2.delete();
                        al.c("Delete empty record file %s", file2.getAbsoluteFile());
                    }
                }
            }
        } catch (Throwable th) {
            al.a(th);
        }
    }

    private static Map<String, Integer> d(String str) {
        if (str == null) {
            return null;
        }
        try {
            HashMap hashMap = new HashMap();
            for (String str2 : str.split(",")) {
                String[] split = str2.split(":");
                if (split.length != 2) {
                    al.e("error format at %s", str2);
                    return null;
                }
                hashMap.put(split[0], Integer.valueOf(Integer.parseInt(split[1])));
            }
            return hashMap;
        } catch (Exception e) {
            al.e("error format intStateStr %s", str);
            e.printStackTrace();
            return null;
        }
    }
}
