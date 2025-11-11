package com.tencent.bugly.proguard;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: BUGLY */
/* loaded from: classes.dex */
public final class u {
    public static final long a = System.currentTimeMillis();
    private static u b;
    private Context c;
    private SharedPreferences f;
    private Map<Integer, Map<String, t>> e = new HashMap();
    private String d = aa.b().d;

    private u(Context context) {
        this.c = context;
        this.f = context.getSharedPreferences("crashrecord", 0);
    }

    public static synchronized u a() {
        u uVar;
        synchronized (u.class) {
            uVar = b;
        }
        return uVar;
    }

    public static synchronized u a(Context context) {
        u uVar;
        synchronized (u.class) {
            if (b == null) {
                b = new u(context);
            }
            uVar = b;
        }
        return uVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized <T extends List<?>> void a(int i, T t) {
        ObjectOutputStream objectOutputStream;
        if (t == null) {
            return;
        }
        try {
            File file = new File(this.c.getDir("crashrecord", 0), String.valueOf(i));
            ObjectOutputStream objectOutputStream2 = null;
            try {
                try {
                    objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));
                } catch (Throwable th) {
                    th = th;
                }
            } catch (IOException e) {
                e = e;
            }
            try {
                objectOutputStream.writeObject(t);
                objectOutputStream.close();
            } catch (IOException e2) {
                e = e2;
                objectOutputStream2 = objectOutputStream;
                e.printStackTrace();
                al.a("open record file error", new Object[0]);
                if (objectOutputStream2 != null) {
                    objectOutputStream2.close();
                }
            } catch (Throwable th2) {
                th = th2;
                objectOutputStream2 = objectOutputStream;
                if (objectOutputStream2 != null) {
                    objectOutputStream2.close();
                }
                throw th;
            }
        } catch (Exception unused) {
            al.e("writeCrashRecord error", new Object[0]);
        }
    }

    static /* synthetic */ boolean a(t tVar, t tVar2) {
        return tVar.g == tVar2.g && tVar.b != null && tVar.b.equalsIgnoreCase(tVar2.b);
    }

    static /* synthetic */ boolean b(t tVar, t tVar2) {
        if (tVar.e == null || tVar.e.equalsIgnoreCase(tVar2.e)) {
            return !(tVar.f == null || tVar.f.equalsIgnoreCase(tVar2.f)) || tVar.d <= 0;
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized boolean c(int i) {
        try {
            List<t> d = d(i);
            if (d == null) {
                return false;
            }
            long currentTimeMillis = System.currentTimeMillis();
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            for (t tVar : d) {
                if (tVar.b != null && tVar.b.equalsIgnoreCase(this.d) && tVar.d > 0) {
                    arrayList.add(tVar);
                }
                if (tVar.c + 86400000 < currentTimeMillis) {
                    arrayList2.add(tVar);
                }
            }
            Collections.sort(arrayList);
            if (arrayList.size() < 2) {
                d.removeAll(arrayList2);
                a(i, (int) d);
                return false;
            }
            if (arrayList.size() <= 0 || ((t) arrayList.get(arrayList.size() - 1)).c + 86400000 >= currentTimeMillis) {
                return true;
            }
            d.clear();
            a(i, (int) d);
            return false;
        } catch (Exception unused) {
            al.e("isFrequentCrash failed", new Object[0]);
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x004a, code lost:
    
        if (r6 == null) goto L31;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x003e, code lost:
    
        r6.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x003c, code lost:
    
        if (r6 == null) goto L31;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v4, types: [boolean] */
    /* JADX WARN: Type inference failed for: r6v5, types: [java.io.ObjectInputStream] */
    /* JADX WARN: Type inference failed for: r6v6 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public synchronized <T extends java.util.List<?>> T d(int r6) {
        /*
            r5 = this;
            monitor-enter(r5)
            r0 = 0
            r1 = 0
            java.io.File r2 = new java.io.File     // Catch: java.lang.Throwable -> L54 java.lang.Exception -> L56
            android.content.Context r3 = r5.c     // Catch: java.lang.Throwable -> L54 java.lang.Exception -> L56
            java.lang.String r4 = "crashrecord"
            java.io.File r3 = r3.getDir(r4, r1)     // Catch: java.lang.Throwable -> L54 java.lang.Exception -> L56
            java.lang.String r6 = java.lang.String.valueOf(r6)     // Catch: java.lang.Throwable -> L54 java.lang.Exception -> L56
            r2.<init>(r3, r6)     // Catch: java.lang.Throwable -> L54 java.lang.Exception -> L56
            boolean r6 = r2.exists()     // Catch: java.lang.Throwable -> L54 java.lang.Exception -> L56
            if (r6 != 0) goto L1c
            monitor-exit(r5)
            return r0
        L1c:
            java.io.ObjectInputStream r6 = new java.io.ObjectInputStream     // Catch: java.lang.Throwable -> L31 java.lang.ClassNotFoundException -> L34 java.io.IOException -> L42
            java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L31 java.lang.ClassNotFoundException -> L34 java.io.IOException -> L42
            r3.<init>(r2)     // Catch: java.lang.Throwable -> L31 java.lang.ClassNotFoundException -> L34 java.io.IOException -> L42
            r6.<init>(r3)     // Catch: java.lang.Throwable -> L31 java.lang.ClassNotFoundException -> L34 java.io.IOException -> L42
            java.lang.Object r2 = r6.readObject()     // Catch: java.lang.ClassNotFoundException -> L35 java.io.IOException -> L43 java.lang.Throwable -> L4d
            java.util.List r2 = (java.util.List) r2     // Catch: java.lang.ClassNotFoundException -> L35 java.io.IOException -> L43 java.lang.Throwable -> L4d
            r6.close()     // Catch: java.lang.Throwable -> L54 java.lang.Exception -> L56
            monitor-exit(r5)
            return r2
        L31:
            r2 = move-exception
            r6 = r0
            goto L4e
        L34:
            r6 = r0
        L35:
            java.lang.String r2 = "get object error"
            java.lang.Object[] r3 = new java.lang.Object[r1]     // Catch: java.lang.Throwable -> L4d
            com.tencent.bugly.proguard.al.a(r2, r3)     // Catch: java.lang.Throwable -> L4d
            if (r6 == 0) goto L5d
        L3e:
            r6.close()     // Catch: java.lang.Throwable -> L54 java.lang.Exception -> L56
            goto L5d
        L42:
            r6 = r0
        L43:
            java.lang.String r2 = "open record file error"
            java.lang.Object[] r3 = new java.lang.Object[r1]     // Catch: java.lang.Throwable -> L4d
            com.tencent.bugly.proguard.al.a(r2, r3)     // Catch: java.lang.Throwable -> L4d
            if (r6 == 0) goto L5d
            goto L3e
        L4d:
            r2 = move-exception
        L4e:
            if (r6 == 0) goto L53
            r6.close()     // Catch: java.lang.Throwable -> L54 java.lang.Exception -> L56
        L53:
            throw r2     // Catch: java.lang.Throwable -> L54 java.lang.Exception -> L56
        L54:
            r6 = move-exception
            goto L5f
        L56:
            java.lang.String r6 = "readCrashRecord error"
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch: java.lang.Throwable -> L54
            com.tencent.bugly.proguard.al.e(r6, r1)     // Catch: java.lang.Throwable -> L54
        L5d:
            monitor-exit(r5)
            return r0
        L5f:
            monitor-exit(r5)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.u.d(int):java.util.List");
    }

    public final void a(final int i) {
        ak.a().a(new Runnable() { // from class: com.tencent.bugly.proguard.u.1
            final /* synthetic */ int a = 1004;

            @Override // java.lang.Runnable
            public final void run() {
                t tVar;
                try {
                    if (TextUtils.isEmpty(u.this.d)) {
                        return;
                    }
                    List<t> d = u.this.d(this.a);
                    if (d == null) {
                        d = new ArrayList();
                    }
                    if (u.this.e.get(Integer.valueOf(this.a)) == null) {
                        u.this.e.put(Integer.valueOf(this.a), new HashMap());
                    }
                    if (((Map) u.this.e.get(Integer.valueOf(this.a))).get(u.this.d) == null) {
                        tVar = new t();
                        tVar.a = this.a;
                        tVar.g = u.a;
                        tVar.b = u.this.d;
                        tVar.f = aa.b().o;
                        tVar.e = aa.b().h;
                        tVar.c = System.currentTimeMillis();
                        tVar.d = i;
                        ((Map) u.this.e.get(Integer.valueOf(this.a))).put(u.this.d, tVar);
                    } else {
                        tVar = (t) ((Map) u.this.e.get(Integer.valueOf(this.a))).get(u.this.d);
                        tVar.d = i;
                    }
                    ArrayList arrayList = new ArrayList();
                    boolean z = false;
                    for (t tVar2 : d) {
                        if (u.a(tVar2, tVar)) {
                            z = true;
                            tVar2.d = tVar.d;
                        }
                        if (u.b(tVar2, tVar)) {
                            arrayList.add(tVar2);
                        }
                    }
                    d.removeAll(arrayList);
                    if (!z) {
                        d.add(tVar);
                    }
                    u.this.a(this.a, (int) d);
                } catch (Exception unused) {
                    al.e("saveCrashRecord failed", new Object[0]);
                }
            }
        });
    }

    public final synchronized boolean b(final int i) {
        boolean z;
        z = true;
        try {
            z = this.f.getBoolean(i + "_" + this.d, true);
            ak.a().a(new Runnable() { // from class: com.tencent.bugly.proguard.u.2
                @Override // java.lang.Runnable
                public final void run() {
                    boolean c = u.this.c(i);
                    u.this.f.edit().putBoolean(i + "_" + u.this.d, !c).commit();
                }
            });
        } catch (Exception unused) {
            al.e("canInit error", new Object[0]);
            return z;
        }
        return z;
    }
}
