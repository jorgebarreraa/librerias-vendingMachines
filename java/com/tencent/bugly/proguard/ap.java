package com.tencent.bugly.proguard;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Looper;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Process;
import android.text.TextUtils;
import androidx.work.WorkRequest;
import com.tencent.bugly.crashreport.common.info.PlugInBean;
import com.tencent.bugly.crashreport.crash.jni.NativeCrashHandler;
import com.yj.coffeemachines.app.serialport.ShellUtils;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import kotlin.UByte;

/* compiled from: BUGLY */
/* loaded from: classes.dex */
public final class ap {
    private static Map<String, String> a;

    public static Context a(Context context) {
        Context applicationContext;
        return (context == null || (applicationContext = context.getApplicationContext()) == null) ? context : applicationContext;
    }

    public static SharedPreferences a(String str, Context context) {
        if (context != null) {
            return context.getSharedPreferences(str, 0);
        }
        return null;
    }

    public static BufferedReader a(File file) {
        if (file.exists() && file.canRead()) {
            try {
                return new BufferedReader(new InputStreamReader(new FileInputStream(file), "utf-8"));
            } catch (Throwable th) {
                al.a(th);
            }
        }
        return null;
    }

    public static Object a(String str, String str2, Class<?>[] clsArr, Object[] objArr) {
        try {
            Method declaredMethod = Class.forName(str).getDeclaredMethod(str2, clsArr);
            declaredMethod.setAccessible(true);
            return declaredMethod.invoke(null, objArr);
        } catch (Exception unused) {
            return null;
        }
    }

    public static <T> T a(byte[] bArr, Parcelable.Creator<T> creator) {
        Parcel obtain = Parcel.obtain();
        obtain.unmarshall(bArr, 0, bArr.length);
        obtain.setDataPosition(0);
        try {
            try {
                T createFromParcel = creator.createFromParcel(obtain);
                if (obtain != null) {
                    obtain.recycle();
                }
                return createFromParcel;
            } catch (Throwable th) {
                th.printStackTrace();
                if (obtain == null) {
                    return null;
                }
                obtain.recycle();
                return null;
            }
        } catch (Throwable th2) {
            if (obtain != null) {
                obtain.recycle();
            }
            throw th2;
        }
    }

    public static String a() {
        return a(System.currentTimeMillis());
    }

    public static String a(int i, String str) {
        String[] strArr = str == null ? new String[]{"logcat", "-d", "-v", "threadtime"} : new String[]{"logcat", "-d", "-v", "threadtime", "-s", str};
        Process process = null;
        StringBuilder sb = new StringBuilder();
        try {
            try {
                process = Runtime.getRuntime().exec(strArr);
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                while (true) {
                    String readLine = bufferedReader.readLine();
                    if (readLine == null) {
                        break;
                    }
                    sb.append(readLine);
                    sb.append(ShellUtils.COMMAND_LINE_END);
                    if (i > 0 && sb.length() > i) {
                        sb.delete(0, sb.length() - i);
                    }
                }
                String sb2 = sb.toString();
                if (process != null) {
                    try {
                        process.getOutputStream().close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        process.getInputStream().close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                    try {
                        process.getErrorStream().close();
                    } catch (IOException e3) {
                        e3.printStackTrace();
                    }
                }
                return sb2;
            } catch (Throwable th) {
                if (!al.a(th)) {
                    th.printStackTrace();
                }
                sb.append("\n[error:");
                sb.append(th.toString());
                sb.append("]");
                String sb3 = sb.toString();
                if (process != null) {
                    try {
                        process.getOutputStream().close();
                    } catch (IOException e4) {
                        e4.printStackTrace();
                    }
                    try {
                        process.getInputStream().close();
                    } catch (IOException e5) {
                        e5.printStackTrace();
                    }
                    try {
                        process.getErrorStream().close();
                    } catch (IOException e6) {
                        e6.printStackTrace();
                    }
                }
                return sb3;
            }
        } catch (Throwable th2) {
            if (process != null) {
                try {
                    process.getOutputStream().close();
                } catch (IOException e7) {
                    e7.printStackTrace();
                }
                try {
                    process.getInputStream().close();
                } catch (IOException e8) {
                    e8.printStackTrace();
                }
                try {
                    process.getErrorStream().close();
                } catch (IOException e9) {
                    e9.printStackTrace();
                }
            }
            throw th2;
        }
    }

    public static String a(long j) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US).format(new Date(j));
        } catch (Exception unused) {
            return new Date().toString();
        }
    }

    public static String a(File file, int i, boolean z) {
        BufferedReader bufferedReader;
        BufferedReader bufferedReader2 = null;
        if (file != null && file.exists()) {
            try {
                if (file.canRead()) {
                    try {
                        StringBuilder sb = new StringBuilder();
                        bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "utf-8"));
                        while (true) {
                            try {
                                String readLine = bufferedReader.readLine();
                                if (readLine == null) {
                                    break;
                                }
                                sb.append(readLine);
                                sb.append(ShellUtils.COMMAND_LINE_END);
                                if (i > 0 && sb.length() > i) {
                                    if (z) {
                                        sb.delete(i, sb.length());
                                        break;
                                    }
                                    sb.delete(0, sb.length() - i);
                                }
                            } catch (Throwable th) {
                                th = th;
                                al.a(th);
                                if (bufferedReader != null) {
                                    try {
                                        bufferedReader.close();
                                    } catch (Exception e) {
                                        al.a(e);
                                    }
                                }
                                return null;
                            }
                        }
                        String sb2 = sb.toString();
                        try {
                            bufferedReader.close();
                        } catch (Exception e2) {
                            al.a(e2);
                        }
                        return sb2;
                    } catch (Throwable th2) {
                        th = th2;
                        bufferedReader = null;
                    }
                }
            } catch (Throwable th3) {
                th = th3;
            }
        }
        return null;
    }

    public static String a(String str) {
        if (str.trim().equals("")) {
            return "";
        }
        try {
            if (a == null) {
                a = new HashMap();
            }
            if (a.containsKey(str)) {
                return a.get(str);
            }
            String systemProperty = NativeCrashHandler.getInstance().getSystemProperty(str);
            if (!TextUtils.isEmpty(systemProperty) && !systemProperty.equals("fail")) {
                a.put(str, systemProperty);
            }
            return systemProperty;
        } catch (Throwable th) {
            al.b(th);
            return "fail";
        }
    }

    public static String a(Thread thread) {
        if (thread == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (StackTraceElement stackTraceElement : thread.getStackTrace()) {
            sb.append(stackTraceElement.toString());
            sb.append(ShellUtils.COMMAND_LINE_END);
        }
        return sb.toString();
    }

    public static String a(Throwable th) {
        if (th == null) {
            return "";
        }
        try {
            StringWriter stringWriter = new StringWriter();
            th.printStackTrace(new PrintWriter(stringWriter));
            return stringWriter.getBuffer().toString();
        } catch (Throwable th2) {
            if (al.a(th2)) {
                return "fail";
            }
            th2.printStackTrace();
            return "fail";
        }
    }

    public static String a(Date date) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US).format(date);
        } catch (Exception unused) {
            return new Date().toString();
        }
    }

    public static Thread a(Runnable runnable, String str) {
        try {
            Thread thread = new Thread(runnable);
            thread.setName(str);
            thread.start();
            return thread;
        } catch (Throwable th) {
            al.e("[Util] Failed to start a thread to execute task with message: %s", th.getMessage());
            return null;
        }
    }

    private static Map<String, String> a(int i) {
        HashMap hashMap = new HashMap(12);
        Map<Thread, StackTraceElement[]> allStackTraces = Thread.getAllStackTraces();
        if (allStackTraces == null) {
            return null;
        }
        Thread thread = Looper.getMainLooper().getThread();
        if (!allStackTraces.containsKey(thread)) {
            allStackTraces.put(thread, thread.getStackTrace());
        }
        Thread.currentThread().getId();
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Thread, StackTraceElement[]> entry : allStackTraces.entrySet()) {
            int i2 = 0;
            sb.setLength(0);
            if (entry.getValue() != null && entry.getValue().length != 0) {
                StackTraceElement[] value = entry.getValue();
                int length = value.length;
                while (true) {
                    if (i2 >= length) {
                        break;
                    }
                    StackTraceElement stackTraceElement = value[i2];
                    if (i > 0 && sb.length() >= i) {
                        sb.append("\n[Stack over limit size :" + i + " , has been cut!]");
                        break;
                    }
                    sb.append(stackTraceElement.toString());
                    sb.append(ShellUtils.COMMAND_LINE_END);
                    i2++;
                }
                hashMap.put(entry.getKey().getName() + "(" + entry.getKey().getId() + ")", sb.toString());
            }
        }
        return hashMap;
    }

    public static Map<String, PlugInBean> a(Parcel parcel) {
        Bundle readBundle = parcel.readBundle();
        HashMap hashMap = null;
        if (readBundle == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        int intValue = ((Integer) readBundle.get("pluginNum")).intValue();
        for (int i = 0; i < intValue; i++) {
            arrayList.add(readBundle.getString("pluginKey".concat(String.valueOf(i))));
        }
        for (int i2 = 0; i2 < intValue; i2++) {
            arrayList2.add(new PlugInBean(readBundle.getString("pluginVal" + i2 + "plugInId"), readBundle.getString("pluginVal" + i2 + "plugInVersion"), readBundle.getString("pluginVal" + i2 + "plugInUUID")));
        }
        if (arrayList.size() == arrayList2.size()) {
            hashMap = new HashMap(arrayList.size());
            for (int i3 = 0; i3 < arrayList.size(); i3++) {
                hashMap.put(arrayList.get(i3), PlugInBean.class.cast(arrayList2.get(i3)));
            }
        } else {
            al.e("map plugin parcel error!", new Object[0]);
        }
        return hashMap;
    }

    public static Map<String, String> a(boolean z, int i) {
        if (z) {
            Map<String, String> a2 = a(i);
            return a2 == null ? new HashMap() : a2;
        }
        al.c("get all thread stack not enable", new Object[0]);
        return new HashMap();
    }

    public static void a(Parcel parcel, Map<String, PlugInBean> map) {
        if (map == null || map.size() <= 0) {
            parcel.writeBundle(null);
            return;
        }
        int size = map.size();
        ArrayList arrayList = new ArrayList(size);
        ArrayList arrayList2 = new ArrayList(size);
        for (Map.Entry<String, PlugInBean> entry : map.entrySet()) {
            arrayList.add(entry.getKey());
            arrayList2.add(entry.getValue());
        }
        Bundle bundle = new Bundle();
        bundle.putInt("pluginNum", arrayList.size());
        for (int i = 0; i < arrayList.size(); i++) {
            bundle.putString("pluginKey".concat(String.valueOf(i)), (String) arrayList.get(i));
        }
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            bundle.putString("pluginVal" + i2 + "plugInId", ((PlugInBean) arrayList2.get(i2)).a);
            bundle.putString("pluginVal" + i2 + "plugInUUID", ((PlugInBean) arrayList2.get(i2)).c);
            bundle.putString("pluginVal" + i2 + "plugInVersion", ((PlugInBean) arrayList2.get(i2)).b);
        }
        parcel.writeBundle(bundle);
    }

    public static void a(Class<?> cls, String str, Object obj) {
        try {
            Field declaredField = cls.getDeclaredField(str);
            declaredField.setAccessible(true);
            declaredField.set(null, obj);
        } catch (Exception unused) {
        }
    }

    public static boolean a(Context context, String str) {
        al.c("[Util] Try to lock file:%s (pid=%d | tid=%d)", str, Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
        try {
            File file = new File(context.getFilesDir() + File.separator + str);
            if (file.exists()) {
                if (System.currentTimeMillis() - file.lastModified() < WorkRequest.MIN_BACKOFF_MILLIS) {
                    return false;
                }
                al.c("[Util] Lock file (%s) is expired, unlock it.", str);
                b(context, str);
            }
            if (file.createNewFile()) {
                al.c("[Util] Successfully locked file: %s (pid=%d | tid=%d)", str, Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
                return true;
            }
            al.c("[Util] Failed to locked file: %s (pid=%d | tid=%d)", str, Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
            return false;
        } catch (Throwable th) {
            al.a(th);
            return false;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:59:0x00aa A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:64:0x00a0 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static boolean a(java.io.File r7, java.io.File r8) {
        /*
            java.lang.String r0 = "rqdp{  ZF end}"
            r1 = 0
            java.lang.Object[] r2 = new java.lang.Object[r1]
            java.lang.String r3 = "rqdp{  ZF start}"
            com.tencent.bugly.proguard.al.c(r3, r2)
            boolean r2 = b(r7, r8)
            if (r2 != 0) goto L11
            return r1
        L11:
            r2 = 0
            java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L73 java.lang.Throwable -> L77
            r3.<init>(r7)     // Catch: java.lang.Throwable -> L73 java.lang.Throwable -> L77
            java.util.zip.ZipOutputStream r4 = new java.util.zip.ZipOutputStream     // Catch: java.lang.Throwable -> L6c java.lang.Throwable -> L6f
            java.io.BufferedOutputStream r5 = new java.io.BufferedOutputStream     // Catch: java.lang.Throwable -> L6c java.lang.Throwable -> L6f
            java.io.FileOutputStream r6 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> L6c java.lang.Throwable -> L6f
            r6.<init>(r8)     // Catch: java.lang.Throwable -> L6c java.lang.Throwable -> L6f
            r5.<init>(r6)     // Catch: java.lang.Throwable -> L6c java.lang.Throwable -> L6f
            r4.<init>(r5)     // Catch: java.lang.Throwable -> L6c java.lang.Throwable -> L6f
            r8 = 8
            r4.setMethod(r8)     // Catch: java.lang.Throwable -> L68 java.lang.Throwable -> L6a
            java.util.zip.ZipEntry r8 = new java.util.zip.ZipEntry     // Catch: java.lang.Throwable -> L68 java.lang.Throwable -> L6a
            java.lang.String r7 = r7.getName()     // Catch: java.lang.Throwable -> L68 java.lang.Throwable -> L6a
            r8.<init>(r7)     // Catch: java.lang.Throwable -> L68 java.lang.Throwable -> L6a
            r4.putNextEntry(r8)     // Catch: java.lang.Throwable -> L68 java.lang.Throwable -> L6a
            r7 = 5000(0x1388, float:7.006E-42)
            r8 = 1000(0x3e8, float:1.401E-42)
            int r7 = java.lang.Math.max(r7, r8)     // Catch: java.lang.Throwable -> L68 java.lang.Throwable -> L6a
            byte[] r7 = new byte[r7]     // Catch: java.lang.Throwable -> L68 java.lang.Throwable -> L6a
        L41:
            int r8 = r3.read(r7)     // Catch: java.lang.Throwable -> L68 java.lang.Throwable -> L6a
            if (r8 <= 0) goto L4b
            r4.write(r7, r1, r8)     // Catch: java.lang.Throwable -> L68 java.lang.Throwable -> L6a
            goto L41
        L4b:
            r4.flush()     // Catch: java.lang.Throwable -> L68 java.lang.Throwable -> L6a
            r4.closeEntry()     // Catch: java.lang.Throwable -> L68 java.lang.Throwable -> L6a
            r3.close()     // Catch: java.io.IOException -> L55
            goto L59
        L55:
            r7 = move-exception
            r7.printStackTrace()
        L59:
            r4.close()     // Catch: java.io.IOException -> L5d
            goto L61
        L5d:
            r7 = move-exception
            r7.printStackTrace()
        L61:
            java.lang.Object[] r7 = new java.lang.Object[r1]
            com.tencent.bugly.proguard.al.c(r0, r7)
            r7 = 1
            return r7
        L68:
            r7 = move-exception
            goto L9e
        L6a:
            r7 = move-exception
            goto L71
        L6c:
            r7 = move-exception
            r4 = r2
            goto L9e
        L6f:
            r7 = move-exception
            r4 = r2
        L71:
            r2 = r3
            goto L79
        L73:
            r7 = move-exception
            r3 = r2
            r4 = r3
            goto L9e
        L77:
            r7 = move-exception
            r4 = r2
        L79:
            boolean r8 = com.tencent.bugly.proguard.al.a(r7)     // Catch: java.lang.Throwable -> L9c
            if (r8 != 0) goto L82
            r7.printStackTrace()     // Catch: java.lang.Throwable -> L9c
        L82:
            if (r2 == 0) goto L8c
            r2.close()     // Catch: java.io.IOException -> L88
            goto L8c
        L88:
            r7 = move-exception
            r7.printStackTrace()
        L8c:
            if (r4 == 0) goto L96
            r4.close()     // Catch: java.io.IOException -> L92
            goto L96
        L92:
            r7 = move-exception
            r7.printStackTrace()
        L96:
            java.lang.Object[] r7 = new java.lang.Object[r1]
            com.tencent.bugly.proguard.al.c(r0, r7)
            return r1
        L9c:
            r7 = move-exception
            r3 = r2
        L9e:
            if (r3 == 0) goto La8
            r3.close()     // Catch: java.io.IOException -> La4
            goto La8
        La4:
            r8 = move-exception
            r8.printStackTrace()
        La8:
            if (r4 == 0) goto Lb2
            r4.close()     // Catch: java.io.IOException -> Lae
            goto Lb2
        Lae:
            r8 = move-exception
            r8.printStackTrace()
        Lb2:
            java.lang.Object[] r8 = new java.lang.Object[r1]
            com.tencent.bugly.proguard.al.c(r0, r8)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.ap.a(java.io.File, java.io.File):boolean");
    }

    public static boolean a(Runnable runnable) {
        ak a2 = ak.a();
        if (a2 != null) {
            return a2.a(runnable);
        }
        String[] split = runnable.getClass().getName().split("\\.");
        return a(runnable, split[split.length - 1]) != null;
    }

    public static byte[] a(Parcelable parcelable) {
        Parcel obtain = Parcel.obtain();
        parcelable.writeToParcel(obtain, 0);
        byte[] marshall = obtain.marshall();
        obtain.recycle();
        return marshall;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0083 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r4v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r4v3, types: [java.util.zip.ZipOutputStream] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static byte[] a(java.lang.String r6, java.lang.String r7) {
        /*
            java.lang.String r0 = "rqdp{  ZF end}"
            r1 = 0
            if (r6 == 0) goto L91
            int r2 = r6.length()
            if (r2 != 0) goto Ld
            goto L91
        Ld:
            r2 = 0
            java.lang.Object[] r3 = new java.lang.Object[r2]
            java.lang.String r4 = "rqdp{  ZF start}"
            com.tencent.bugly.proguard.al.c(r4, r3)
            java.lang.String r3 = "UTF-8"
            byte[] r6 = r6.getBytes(r3)     // Catch: java.lang.Throwable -> L62 java.lang.Throwable -> L65
            java.io.ByteArrayInputStream r3 = new java.io.ByteArrayInputStream     // Catch: java.lang.Throwable -> L62 java.lang.Throwable -> L65
            r3.<init>(r6)     // Catch: java.lang.Throwable -> L62 java.lang.Throwable -> L65
            java.io.ByteArrayOutputStream r6 = new java.io.ByteArrayOutputStream     // Catch: java.lang.Throwable -> L62 java.lang.Throwable -> L65
            r6.<init>()     // Catch: java.lang.Throwable -> L62 java.lang.Throwable -> L65
            java.util.zip.ZipOutputStream r4 = new java.util.zip.ZipOutputStream     // Catch: java.lang.Throwable -> L62 java.lang.Throwable -> L65
            r4.<init>(r6)     // Catch: java.lang.Throwable -> L62 java.lang.Throwable -> L65
            r5 = 8
            r4.setMethod(r5)     // Catch: java.lang.Throwable -> L60 java.lang.Throwable -> L80
            java.util.zip.ZipEntry r5 = new java.util.zip.ZipEntry     // Catch: java.lang.Throwable -> L60 java.lang.Throwable -> L80
            r5.<init>(r7)     // Catch: java.lang.Throwable -> L60 java.lang.Throwable -> L80
            r4.putNextEntry(r5)     // Catch: java.lang.Throwable -> L60 java.lang.Throwable -> L80
            r7 = 1024(0x400, float:1.435E-42)
            byte[] r7 = new byte[r7]     // Catch: java.lang.Throwable -> L60 java.lang.Throwable -> L80
        L3b:
            int r5 = r3.read(r7)     // Catch: java.lang.Throwable -> L60 java.lang.Throwable -> L80
            if (r5 <= 0) goto L45
            r4.write(r7, r2, r5)     // Catch: java.lang.Throwable -> L60 java.lang.Throwable -> L80
            goto L3b
        L45:
            r4.closeEntry()     // Catch: java.lang.Throwable -> L60 java.lang.Throwable -> L80
            r4.flush()     // Catch: java.lang.Throwable -> L60 java.lang.Throwable -> L80
            r4.finish()     // Catch: java.lang.Throwable -> L60 java.lang.Throwable -> L80
            byte[] r6 = r6.toByteArray()     // Catch: java.lang.Throwable -> L60 java.lang.Throwable -> L80
            r4.close()     // Catch: java.io.IOException -> L56
            goto L5a
        L56:
            r7 = move-exception
            r7.printStackTrace()
        L5a:
            java.lang.Object[] r7 = new java.lang.Object[r2]
            com.tencent.bugly.proguard.al.c(r0, r7)
            return r6
        L60:
            r6 = move-exception
            goto L67
        L62:
            r6 = move-exception
            r4 = r1
            goto L81
        L65:
            r6 = move-exception
            r4 = r1
        L67:
            boolean r7 = com.tencent.bugly.proguard.al.a(r6)     // Catch: java.lang.Throwable -> L80
            if (r7 != 0) goto L70
            r6.printStackTrace()     // Catch: java.lang.Throwable -> L80
        L70:
            if (r4 == 0) goto L7a
            r4.close()     // Catch: java.io.IOException -> L76
            goto L7a
        L76:
            r6 = move-exception
            r6.printStackTrace()
        L7a:
            java.lang.Object[] r6 = new java.lang.Object[r2]
            com.tencent.bugly.proguard.al.c(r0, r6)
            return r1
        L80:
            r6 = move-exception
        L81:
            if (r4 == 0) goto L8b
            r4.close()     // Catch: java.io.IOException -> L87
            goto L8b
        L87:
            r7 = move-exception
            r7.printStackTrace()
        L8b:
            java.lang.Object[] r7 = new java.lang.Object[r2]
            com.tencent.bugly.proguard.al.c(r0, r7)
            throw r6
        L91:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.ap.a(java.lang.String, java.lang.String):byte[]");
    }

    public static byte[] a(byte[] bArr) {
        if (bArr == null) {
            return bArr;
        }
        al.c("[Util] Zip %d bytes data with type %s", Integer.valueOf(bArr.length), "Gzip");
        try {
            return bh.a().a(bArr);
        } catch (Throwable th) {
            if (al.a(th)) {
                return null;
            }
            th.printStackTrace();
            return null;
        }
    }

    public static long b() {
        try {
            return (((System.currentTimeMillis() + TimeZone.getDefault().getRawOffset()) / 86400000) * 86400000) - TimeZone.getDefault().getRawOffset();
        } catch (Throwable th) {
            if (al.a(th)) {
                return -1L;
            }
            th.printStackTrace();
            return -1L;
        }
    }

    public static BufferedReader b(String str, String str2) {
        if (str == null) {
            return null;
        }
        try {
            File file = new File(str, str2);
            if (file.exists() && file.canRead()) {
                return a(file);
            }
            return null;
        } catch (NullPointerException e) {
            al.a(e);
            return null;
        }
    }

    public static String b(Throwable th) {
        if (th == null) {
            return "";
        }
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        th.printStackTrace(printWriter);
        printWriter.flush();
        return stringWriter.toString();
    }

    public static Map<String, String> b(Parcel parcel) {
        Bundle readBundle = parcel.readBundle();
        HashMap hashMap = null;
        if (readBundle == null) {
            return null;
        }
        ArrayList<String> stringArrayList = readBundle.getStringArrayList("keys");
        ArrayList<String> stringArrayList2 = readBundle.getStringArrayList("values");
        if (stringArrayList == null || stringArrayList2 == null || stringArrayList.size() != stringArrayList2.size()) {
            al.e("map parcel error!", new Object[0]);
        } else {
            hashMap = new HashMap(stringArrayList.size());
            for (int i = 0; i < stringArrayList.size(); i++) {
                hashMap.put(stringArrayList.get(i), stringArrayList2.get(i));
            }
        }
        return hashMap;
    }

    public static void b(long j) {
        try {
            Thread.sleep(j);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void b(Parcel parcel, Map<String, String> map) {
        if (map == null || map.size() <= 0) {
            parcel.writeBundle(null);
            return;
        }
        int size = map.size();
        ArrayList<String> arrayList = new ArrayList<>(size);
        ArrayList<String> arrayList2 = new ArrayList<>(size);
        for (Map.Entry<String, String> entry : map.entrySet()) {
            arrayList.add(entry.getKey());
            arrayList2.add(entry.getValue());
        }
        Bundle bundle = new Bundle();
        bundle.putStringArrayList("keys", arrayList);
        bundle.putStringArrayList("values", arrayList2);
        parcel.writeBundle(bundle);
    }

    public static boolean b(Context context, String str) {
        al.c("[Util] Try to unlock file: %s (pid=%d | tid=%d)", str, Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
        try {
            File file = new File(context.getFilesDir() + File.separator + str);
            if (!file.exists()) {
                return true;
            }
            if (!file.delete()) {
                return false;
            }
            al.c("[Util] Successfully unlocked file: %s (pid=%d | tid=%d)", str, Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
            return true;
        } catch (Throwable th) {
            al.a(th);
            return false;
        }
    }

    private static boolean b(File file, File file2) {
        if (file == null || file2 == null || file.equals(file2)) {
            al.d("rqdp{  err ZF 1R!}", new Object[0]);
            return false;
        }
        if (!file.exists() || !file.canRead()) {
            al.d("rqdp{  !sFile.exists() || !sFile.canRead(),pls check ,return!}", new Object[0]);
            return false;
        }
        try {
            if (file2.getParentFile() != null && !file2.getParentFile().exists()) {
                file2.getParentFile().mkdirs();
            }
            if (!file2.exists()) {
                file2.createNewFile();
            }
        } catch (Throwable th) {
            if (!al.a(th)) {
                th.printStackTrace();
            }
        }
        return file2.exists() && file2.canWrite();
    }

    public static boolean b(String str) {
        return str == null || str.trim().length() <= 0;
    }

    public static byte[] b(byte[] bArr) {
        if (bArr == null) {
            return bArr;
        }
        al.c("[Util] Unzip %d bytes data with type %s", Integer.valueOf(bArr.length), "Gzip");
        try {
            return bh.a().b(bArr);
        } catch (Throwable th) {
            if (th.getMessage() != null && th.getMessage().contains("Not in GZIP format")) {
                al.d(th.getMessage(), new Object[0]);
                return null;
            }
            if (al.a(th)) {
                return null;
            }
            th.printStackTrace();
            return null;
        }
    }

    public static String c(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return "NULL";
        }
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
            messageDigest.update(bArr);
            byte[] digest = messageDigest.digest();
            if (digest == null) {
                return "";
            }
            StringBuffer stringBuffer = new StringBuffer();
            for (byte b : digest) {
                String hexString = Integer.toHexString(b & UByte.MAX_VALUE);
                if (hexString.length() == 1) {
                    stringBuffer.append("0");
                }
                stringBuffer.append(hexString);
            }
            return stringBuffer.toString().toUpperCase();
        } catch (Throwable th) {
            if (al.a(th)) {
                return null;
            }
            th.printStackTrace();
            return null;
        }
    }

    public static void c(String str) {
        if (str == null) {
            return;
        }
        File file = new File(str);
        if (file.isFile() && file.exists() && file.canWrite()) {
            file.delete();
        }
    }

    public static void c(String str, String str2) {
        if (aa.b() == null || aa.b().O == null) {
            return;
        }
        aa.b().O.edit().putString(str, str2).apply();
    }

    public static byte[] c(long j) {
        try {
            return String.valueOf(j).getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static long d(byte[] bArr) {
        if (bArr == null) {
            return -1L;
        }
        try {
            return Long.parseLong(new String(bArr, "utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return -1L;
        }
    }

    public static String d(String str, String str2) {
        return (aa.b() == null || aa.b().O == null) ? "" : aa.b().O.getString(str, str2);
    }

    public static boolean d(String str) {
        if (b(str)) {
            return false;
        }
        if (str.length() > 255) {
            al.a("URL(%s)'s length is larger than 255.", str);
            return false;
        }
        if (str.toLowerCase().startsWith("http")) {
            return true;
        }
        al.a("URL(%s) is not start with \"http\".", str);
        return false;
    }
}
