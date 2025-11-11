package com.tencent.bugly.proguard;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Pair;
import com.tencent.bugly.BuglyStrategy;
import com.tencent.bugly.crashreport.common.info.PlugInBean;
import com.tencent.bugly.crashreport.common.strategy.StrategyBean;
import com.tencent.bugly.crashreport.crash.CrashDetailBean;
import com.tencent.bugly.proguard.ag;
import com.yj.coffeemachines.app.serialport.ShellUtils;
import java.io.File;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/* compiled from: BUGLY */
/* loaded from: classes.dex */
public final class as {
    public static int a;
    private static final Map<Integer, Pair<String, String>> h = new HashMap<Integer, Pair<String, String>>() { // from class: com.tencent.bugly.proguard.as.1
        {
            put(3, new Pair("203", "103"));
            put(7, new Pair("208", "108"));
            put(0, new Pair("200", "100"));
            put(1, new Pair("201", "101"));
            put(2, new Pair("202", "102"));
            put(4, new Pair("204", "104"));
            put(6, new Pair("206", "106"));
            put(5, new Pair("207", "107"));
        }
    };
    private static final ArrayList<a> i = new ArrayList<a>() { // from class: com.tencent.bugly.proguard.as.2
        {
            byte b2 = 0;
            add(new b(b2));
            add(new c(b2));
            add(new d(b2));
            add(new e(b2));
            add(new h(b2));
            add(new i(b2));
            add(new f(b2));
            add(new g(b2));
        }
    };
    private static final Map<Integer, Integer> j = new HashMap<Integer, Integer>() { // from class: com.tencent.bugly.proguard.as.3
        {
            put(3, 4);
            put(7, 7);
            put(2, 1);
            put(0, 0);
            put(1, 2);
            put(4, 3);
            put(5, 5);
            put(6, 6);
        }
    };
    private static final Map<Integer, String> k = new HashMap<Integer, String>() { // from class: com.tencent.bugly.proguard.as.4
        {
            put(3, "BuglyAnrCrash");
            put(0, "BuglyJavaCrash");
            put(1, "BuglyNativeCrash");
        }
    };
    private static final Map<Integer, String> l = new HashMap<Integer, String>() { // from class: com.tencent.bugly.proguard.as.5
        {
            put(3, "BuglyAnrCrashReport");
            put(0, "BuglyJavaCrashReport");
            put(1, "BuglyNativeCrashReport");
        }
    };
    protected final Context b;
    protected final ai c;
    protected final w d;
    protected final ac e;
    protected aw f;
    protected BuglyStrategy.a g;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: BUGLY */
    /* loaded from: classes.dex */
    public static abstract class a {
        final int a;

        private a(int i) {
            this.a = i;
        }

        /* synthetic */ a(int i, byte b) {
            this(i);
        }

        abstract boolean a();
    }

    /* compiled from: BUGLY */
    /* loaded from: classes.dex */
    static class b extends a {
        private b() {
            super(3, (byte) 0);
        }

        /* synthetic */ b(byte b) {
            this();
        }

        @Override // com.tencent.bugly.proguard.as.a
        final boolean a() {
            return at.a().k();
        }
    }

    /* compiled from: BUGLY */
    /* loaded from: classes.dex */
    static class c extends a {
        private c() {
            super(7, (byte) 0);
        }

        /* synthetic */ c(byte b) {
            this();
        }

        @Override // com.tencent.bugly.proguard.as.a
        final boolean a() {
            return true;
        }
    }

    /* compiled from: BUGLY */
    /* loaded from: classes.dex */
    static class d extends a {
        private d() {
            super(2, (byte) 0);
        }

        /* synthetic */ d(byte b) {
            this();
        }

        @Override // com.tencent.bugly.proguard.as.a
        final boolean a() {
            return true;
        }
    }

    /* compiled from: BUGLY */
    /* loaded from: classes.dex */
    static class e extends a {
        /* JADX WARN: Illegal instructions before constructor call */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        private e() {
            /*
                r1 = this;
                r0 = 0
                r1.<init>(r0, r0)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.as.e.<init>():void");
        }

        /* synthetic */ e(byte b) {
            this();
        }

        @Override // com.tencent.bugly.proguard.as.a
        final boolean a() {
            return at.a().j();
        }
    }

    /* compiled from: BUGLY */
    /* loaded from: classes.dex */
    static class f extends a {
        private f() {
            super(5, (byte) 0);
        }

        /* synthetic */ f(byte b) {
            this();
        }

        @Override // com.tencent.bugly.proguard.as.a
        final boolean a() {
            return (at.a().B & 2) > 0;
        }
    }

    /* compiled from: BUGLY */
    /* loaded from: classes.dex */
    static class g extends a {
        private g() {
            super(6, (byte) 0);
        }

        /* synthetic */ g(byte b) {
            this();
        }

        @Override // com.tencent.bugly.proguard.as.a
        final boolean a() {
            return (at.a().B & 1) > 0;
        }
    }

    /* compiled from: BUGLY */
    /* loaded from: classes.dex */
    static class h extends a {
        private h() {
            super(1, (byte) 0);
        }

        /* synthetic */ h(byte b) {
            this();
        }

        @Override // com.tencent.bugly.proguard.as.a
        final boolean a() {
            return at.a().j();
        }
    }

    /* compiled from: BUGLY */
    /* loaded from: classes.dex */
    static class i extends a {
        private i() {
            super(4, (byte) 0);
        }

        /* synthetic */ i(byte b) {
            this();
        }

        @Override // com.tencent.bugly.proguard.as.a
        final boolean a() {
            return (at.a().B & 4) > 0;
        }
    }

    public as(Context context, ai aiVar, w wVar, ac acVar, BuglyStrategy.a aVar) {
        a = 1004;
        this.b = context;
        this.c = aiVar;
        this.d = wVar;
        this.e = acVar;
        this.g = aVar;
        this.f = null;
    }

    private static CrashDetailBean a(Cursor cursor) {
        if (cursor == null) {
            return null;
        }
        try {
            byte[] blob = cursor.getBlob(cursor.getColumnIndex("_dt"));
            if (blob == null) {
                return null;
            }
            long j2 = cursor.getLong(cursor.getColumnIndex("_id"));
            CrashDetailBean crashDetailBean = (CrashDetailBean) ap.a(blob, CrashDetailBean.CREATOR);
            if (crashDetailBean != null) {
                crashDetailBean.a = j2;
            }
            return crashDetailBean;
        } catch (Throwable th) {
            if (!al.a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    private static CrashDetailBean a(List<ar> list, CrashDetailBean crashDetailBean) {
        List<CrashDetailBean> c2;
        if (list.isEmpty()) {
            return crashDetailBean;
        }
        CrashDetailBean crashDetailBean2 = null;
        ArrayList arrayList = new ArrayList(10);
        for (ar arVar : list) {
            if (arVar.e) {
                arrayList.add(arVar);
            }
        }
        if (!arrayList.isEmpty() && (c2 = c(arrayList)) != null && !c2.isEmpty()) {
            Collections.sort(c2);
            crashDetailBean2 = c2.get(0);
            a(crashDetailBean2, c2);
        }
        if (crashDetailBean2 == null) {
            crashDetailBean.j = true;
            crashDetailBean.t = 0;
            crashDetailBean.s = "";
            crashDetailBean2 = crashDetailBean;
        }
        b(crashDetailBean2, list);
        if (crashDetailBean2.r != crashDetailBean.r) {
            String str = crashDetailBean2.s;
            StringBuilder sb = new StringBuilder();
            sb.append(crashDetailBean.r);
            if (!str.contains(sb.toString())) {
                crashDetailBean2.t++;
                crashDetailBean2.s += crashDetailBean.r + ShellUtils.COMMAND_LINE_END;
            }
        }
        return crashDetailBean2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:53:0x00ca  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x00b6 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r7v2, types: [java.io.File] */
    /* JADX WARN: Type inference failed for: r7v4 */
    /* JADX WARN: Type inference failed for: r7v5, types: [java.io.FileInputStream] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static com.tencent.bugly.proguard.bn a(java.lang.String r6, android.content.Context r7, java.lang.String r8) {
        /*
            java.lang.String r0 = "del tmp"
            r1 = 0
            r2 = 0
            if (r8 == 0) goto Ld3
            if (r7 != 0) goto La
            goto Ld3
        La:
            r3 = 1
            java.lang.Object[] r4 = new java.lang.Object[r3]
            r4[r2] = r8
            java.lang.String r5 = "zip %s"
            com.tencent.bugly.proguard.al.c(r5, r4)
            java.io.File r4 = new java.io.File
            r4.<init>(r8)
            java.io.File r8 = new java.io.File
            java.io.File r7 = r7.getCacheDir()
            r8.<init>(r7, r6)
            boolean r6 = com.tencent.bugly.proguard.ap.a(r4, r8)
            if (r6 != 0) goto L30
            java.lang.Object[] r6 = new java.lang.Object[r2]
            java.lang.String r7 = "zip fail!"
            com.tencent.bugly.proguard.al.d(r7, r6)
            return r1
        L30:
            java.io.ByteArrayOutputStream r6 = new java.io.ByteArrayOutputStream
            r6.<init>()
            java.io.FileInputStream r7 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L86 java.lang.Throwable -> L89
            r7.<init>(r8)     // Catch: java.lang.Throwable -> L86 java.lang.Throwable -> L89
            r4 = 4096(0x1000, float:5.74E-42)
            byte[] r4 = new byte[r4]     // Catch: java.lang.Throwable -> L84 java.lang.Throwable -> Lb3
        L3e:
            int r5 = r7.read(r4)     // Catch: java.lang.Throwable -> L84 java.lang.Throwable -> Lb3
            if (r5 <= 0) goto L4b
            r6.write(r4, r2, r5)     // Catch: java.lang.Throwable -> L84 java.lang.Throwable -> Lb3
            r6.flush()     // Catch: java.lang.Throwable -> L84 java.lang.Throwable -> Lb3
            goto L3e
        L4b:
            byte[] r6 = r6.toByteArray()     // Catch: java.lang.Throwable -> L84 java.lang.Throwable -> Lb3
            java.lang.String r4 = "read bytes :%d"
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch: java.lang.Throwable -> L84 java.lang.Throwable -> Lb3
            int r5 = r6.length     // Catch: java.lang.Throwable -> L84 java.lang.Throwable -> Lb3
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch: java.lang.Throwable -> L84 java.lang.Throwable -> Lb3
            r3[r2] = r5     // Catch: java.lang.Throwable -> L84 java.lang.Throwable -> Lb3
            com.tencent.bugly.proguard.al.c(r4, r3)     // Catch: java.lang.Throwable -> L84 java.lang.Throwable -> Lb3
            com.tencent.bugly.proguard.bn r3 = new com.tencent.bugly.proguard.bn     // Catch: java.lang.Throwable -> L84 java.lang.Throwable -> Lb3
            r4 = 2
            java.lang.String r5 = r8.getName()     // Catch: java.lang.Throwable -> L84 java.lang.Throwable -> Lb3
            r3.<init>(r4, r5, r6)     // Catch: java.lang.Throwable -> L84 java.lang.Throwable -> Lb3
            r7.close()     // Catch: java.io.IOException -> L6b
            goto L75
        L6b:
            r6 = move-exception
            boolean r7 = com.tencent.bugly.proguard.al.a(r6)
            if (r7 != 0) goto L75
            r6.printStackTrace()
        L75:
            boolean r6 = r8.exists()
            if (r6 == 0) goto L83
            java.lang.Object[] r6 = new java.lang.Object[r2]
            com.tencent.bugly.proguard.al.c(r0, r6)
            r8.delete()
        L83:
            return r3
        L84:
            r6 = move-exception
            goto L8b
        L86:
            r6 = move-exception
            r7 = r1
            goto Lb4
        L89:
            r6 = move-exception
            r7 = r1
        L8b:
            boolean r3 = com.tencent.bugly.proguard.al.a(r6)     // Catch: java.lang.Throwable -> Lb3
            if (r3 != 0) goto L94
            r6.printStackTrace()     // Catch: java.lang.Throwable -> Lb3
        L94:
            if (r7 == 0) goto La4
            r7.close()     // Catch: java.io.IOException -> L9a
            goto La4
        L9a:
            r6 = move-exception
            boolean r7 = com.tencent.bugly.proguard.al.a(r6)
            if (r7 != 0) goto La4
            r6.printStackTrace()
        La4:
            boolean r6 = r8.exists()
            if (r6 == 0) goto Lb2
            java.lang.Object[] r6 = new java.lang.Object[r2]
            com.tencent.bugly.proguard.al.c(r0, r6)
            r8.delete()
        Lb2:
            return r1
        Lb3:
            r6 = move-exception
        Lb4:
            if (r7 == 0) goto Lc4
            r7.close()     // Catch: java.io.IOException -> Lba
            goto Lc4
        Lba:
            r7 = move-exception
            boolean r1 = com.tencent.bugly.proguard.al.a(r7)
            if (r1 != 0) goto Lc4
            r7.printStackTrace()
        Lc4:
            boolean r7 = r8.exists()
            if (r7 == 0) goto Ld2
            java.lang.Object[] r7 = new java.lang.Object[r2]
            com.tencent.bugly.proguard.al.c(r0, r7)
            r8.delete()
        Ld2:
            throw r6
        Ld3:
            java.lang.Object[] r6 = new java.lang.Object[r2]
            java.lang.String r7 = "rqdp{  createZipAttachment sourcePath == null || context == null ,pls check}"
            com.tencent.bugly.proguard.al.d(r7, r6)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.as.a(java.lang.String, android.content.Context, java.lang.String):com.tencent.bugly.proguard.bn");
    }

    private static bo a(Context context, CrashDetailBean crashDetailBean, aa aaVar) {
        ArrayList<bl> arrayList = null;
        if (context == null || crashDetailBean == null || aaVar == null) {
            al.d("enExp args == null", new Object[0]);
            return null;
        }
        bo boVar = new bo();
        boVar.a = e(crashDetailBean);
        boVar.b = crashDetailBean.r;
        boVar.c = crashDetailBean.n;
        boVar.d = crashDetailBean.o;
        boVar.e = crashDetailBean.p;
        boVar.g = crashDetailBean.q;
        boVar.h = crashDetailBean.z;
        boVar.i = crashDetailBean.c;
        boVar.j = null;
        boVar.l = crashDetailBean.m;
        boVar.m = crashDetailBean.e;
        boVar.f = crashDetailBean.B;
        boVar.n = null;
        if (crashDetailBean.h != null && !crashDetailBean.h.isEmpty()) {
            arrayList = new ArrayList<>(crashDetailBean.h.size());
            for (Map.Entry<String, PlugInBean> entry : crashDetailBean.h.entrySet()) {
                bl blVar = new bl();
                blVar.a = entry.getValue().a;
                blVar.c = entry.getValue().c;
                blVar.e = entry.getValue().b;
                arrayList.add(blVar);
            }
        }
        boVar.p = arrayList;
        al.c("libInfo %s", boVar.o);
        ArrayList<bn> arrayList2 = new ArrayList<>(20);
        a(arrayList2, crashDetailBean);
        a(arrayList2, crashDetailBean.w);
        b(arrayList2, crashDetailBean.x);
        c(arrayList2, crashDetailBean.Z);
        a(arrayList2, crashDetailBean.aa, context);
        a(arrayList2, crashDetailBean.y);
        a(arrayList2, crashDetailBean, context);
        b(arrayList2, crashDetailBean, context);
        a(arrayList2, aaVar.L);
        b(arrayList2, crashDetailBean.Y);
        boVar.q = arrayList2;
        if (crashDetailBean.j) {
            boVar.k = crashDetailBean.t;
        }
        boVar.r = a(crashDetailBean, aaVar);
        boVar.s = new HashMap();
        if (crashDetailBean.S != null && crashDetailBean.S.size() > 0) {
            boVar.s.putAll(crashDetailBean.S);
            al.a("setted message size %d", Integer.valueOf(boVar.s.size()));
        }
        Map<String, String> map = boVar.s;
        al.c("pss:" + crashDetailBean.I + " vss:" + crashDetailBean.J + " javaHeap:" + crashDetailBean.K, new Object[0]);
        StringBuilder sb = new StringBuilder();
        sb.append(crashDetailBean.I);
        map.put("SDK_UPLOAD_U1", sb.toString());
        StringBuilder sb2 = new StringBuilder();
        sb2.append(crashDetailBean.J);
        map.put("SDK_UPLOAD_U2", sb2.toString());
        StringBuilder sb3 = new StringBuilder();
        sb3.append(crashDetailBean.K);
        map.put("SDK_UPLOAD_U3", sb3.toString());
        Object[] objArr = new Object[12];
        objArr[0] = crashDetailBean.n;
        objArr[1] = crashDetailBean.c;
        objArr[2] = aaVar.d();
        objArr[3] = Long.valueOf((crashDetailBean.r - crashDetailBean.Q) / 1000);
        objArr[4] = Boolean.valueOf(crashDetailBean.k);
        objArr[5] = Boolean.valueOf(crashDetailBean.R);
        objArr[6] = Boolean.valueOf(crashDetailBean.j);
        objArr[7] = Boolean.valueOf(crashDetailBean.b == 1);
        objArr[8] = Integer.valueOf(crashDetailBean.t);
        objArr[9] = crashDetailBean.s;
        objArr[10] = Boolean.valueOf(crashDetailBean.d);
        objArr[11] = Integer.valueOf(boVar.r.size());
        al.c("%s rid:%s sess:%s ls:%ds isR:%b isF:%b isM:%b isN:%b mc:%d ,%s ,isUp:%b ,vm:%d", objArr);
        return boVar;
    }

    private static bp a(Context context, List<CrashDetailBean> list, aa aaVar) {
        if (context == null || list == null || list.size() == 0 || aaVar == null) {
            al.d("enEXPPkg args == null!", new Object[0]);
            return null;
        }
        bp bpVar = new bp();
        bpVar.a = new ArrayList<>();
        Iterator<CrashDetailBean> it2 = list.iterator();
        while (it2.hasNext()) {
            bpVar.a.add(a(context, it2.next(), aaVar));
        }
        return bpVar;
    }

    public static List<CrashDetailBean> a() {
        StrategyBean c2 = ac.a().c();
        if (c2 == null) {
            al.d("have not synced remote!", new Object[0]);
            return null;
        }
        if (!c2.f) {
            al.d("Crashreport remote closed, please check your APP ID correct and Version available, then uninstall and reinstall your app.", new Object[0]);
            al.b("[init] WARNING! Crashreport closed by server, please check your APP ID correct and Version available, then uninstall and reinstall your app.", new Object[0]);
            return null;
        }
        long currentTimeMillis = System.currentTimeMillis();
        long b2 = ap.b();
        List<ar> b3 = b();
        al.c("Size of crash list loaded from DB: %s", Integer.valueOf(b3.size()));
        if (b3 == null || b3.size() <= 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        arrayList.addAll(a(b3));
        b3.removeAll(arrayList);
        Iterator<ar> it2 = b3.iterator();
        while (it2.hasNext()) {
            ar next = it2.next();
            if (next.b < b2 - at.j) {
                arrayList2.add(next);
                it2.remove();
                arrayList.add(next);
            } else if (next.d) {
                if (next.b >= currentTimeMillis - 86400000) {
                    it2.remove();
                } else if (!next.e) {
                    it2.remove();
                    arrayList.add(next);
                }
            } else if (next.f >= 3 && next.b < currentTimeMillis - 86400000) {
                it2.remove();
                arrayList.add(next);
            }
        }
        b(arrayList2);
        if (arrayList.size() > 0) {
            d(arrayList);
        }
        ArrayList arrayList3 = new ArrayList();
        List<CrashDetailBean> c3 = c(b3);
        if (c3 != null && c3.size() > 0) {
            String str = aa.b().o;
            Iterator<CrashDetailBean> it3 = c3.iterator();
            while (it3.hasNext()) {
                CrashDetailBean next2 = it3.next();
                if (!str.equals(next2.f)) {
                    it3.remove();
                    arrayList3.add(next2);
                }
            }
        }
        if (arrayList3.size() > 0) {
            e(arrayList3);
        }
        return c3;
    }

    private static List<ar> a(List<ar> list) {
        if (list == null || list.size() == 0) {
            return null;
        }
        long currentTimeMillis = System.currentTimeMillis();
        ArrayList arrayList = new ArrayList();
        for (ar arVar : list) {
            if (arVar.d && arVar.b <= currentTimeMillis - 86400000) {
                arrayList.add(arVar);
            }
        }
        return arrayList;
    }

    private static Map<String, String> a(CrashDetailBean crashDetailBean, aa aaVar) {
        HashMap hashMap = new HashMap(30);
        try {
            StringBuilder sb = new StringBuilder();
            sb.append(crashDetailBean.C);
            hashMap.put("A9", sb.toString());
            StringBuilder sb2 = new StringBuilder();
            sb2.append(crashDetailBean.D);
            hashMap.put("A11", sb2.toString());
            StringBuilder sb3 = new StringBuilder();
            sb3.append(crashDetailBean.E);
            hashMap.put("A10", sb3.toString());
            hashMap.put("A23", crashDetailBean.f);
            StringBuilder sb4 = new StringBuilder();
            aaVar.getClass();
            hashMap.put("A7", sb4.toString());
            hashMap.put("A6", aa.n());
            hashMap.put("A5", aaVar.m());
            hashMap.put("A22", aaVar.g());
            StringBuilder sb5 = new StringBuilder();
            sb5.append(crashDetailBean.G);
            hashMap.put("A2", sb5.toString());
            StringBuilder sb6 = new StringBuilder();
            sb6.append(crashDetailBean.F);
            hashMap.put("A1", sb6.toString());
            hashMap.put("A24", aaVar.k);
            StringBuilder sb7 = new StringBuilder();
            sb7.append(crashDetailBean.H);
            hashMap.put("A17", sb7.toString());
            hashMap.put("A25", aaVar.g());
            hashMap.put("A15", aaVar.q());
            StringBuilder sb8 = new StringBuilder();
            sb8.append(aaVar.r());
            hashMap.put("A13", sb8.toString());
            hashMap.put("A34", crashDetailBean.A);
            if (aaVar.G != null) {
                hashMap.put("productIdentify", aaVar.G);
            }
            hashMap.put("A26", URLEncoder.encode(crashDetailBean.L, "utf-8"));
            boolean z = true;
            if (crashDetailBean.b == 1) {
                hashMap.put("A27", crashDetailBean.O);
                hashMap.put("A28", crashDetailBean.N);
                StringBuilder sb9 = new StringBuilder();
                sb9.append(crashDetailBean.k);
                hashMap.put("A29", sb9.toString());
            }
            hashMap.put("A30", crashDetailBean.P);
            StringBuilder sb10 = new StringBuilder();
            sb10.append(crashDetailBean.Q);
            hashMap.put("A18", sb10.toString());
            StringBuilder sb11 = new StringBuilder();
            if (crashDetailBean.R) {
                z = false;
            }
            sb11.append(z);
            hashMap.put("A36", sb11.toString());
            StringBuilder sb12 = new StringBuilder();
            sb12.append(aaVar.z);
            hashMap.put("F02", sb12.toString());
            StringBuilder sb13 = new StringBuilder();
            sb13.append(aaVar.A);
            hashMap.put("F03", sb13.toString());
            hashMap.put("F04", aaVar.d());
            StringBuilder sb14 = new StringBuilder();
            sb14.append(aaVar.B);
            hashMap.put("F05", sb14.toString());
            hashMap.put("F06", aaVar.y);
            hashMap.put("F08", aaVar.E);
            hashMap.put("F09", aaVar.F);
            StringBuilder sb15 = new StringBuilder();
            sb15.append(aaVar.C);
            hashMap.put("F10", sb15.toString());
            a(hashMap, crashDetailBean);
        } catch (Exception e2) {
            e2.printStackTrace();
            al.a(e2);
        }
        return hashMap;
    }

    private static void a(CrashDetailBean crashDetailBean, List<CrashDetailBean> list) {
        String[] split;
        StringBuilder sb = new StringBuilder(128);
        for (int i2 = 1; i2 < list.size(); i2++) {
            CrashDetailBean crashDetailBean2 = list.get(i2);
            if (crashDetailBean2.s != null && (split = crashDetailBean2.s.split(ShellUtils.COMMAND_LINE_END)) != null) {
                for (String str : split) {
                    if (!crashDetailBean.s.contains(str)) {
                        crashDetailBean.t++;
                        sb.append(str);
                        sb.append(ShellUtils.COMMAND_LINE_END);
                    }
                }
            }
        }
        crashDetailBean.s += sb.toString();
    }

    private static void a(CrashDetailBean crashDetailBean, Map<String, String> map) {
        String value;
        if (map == null || map.isEmpty()) {
            al.d("extra map is empty. CrashBean won't have userDatas.", new Object[0]);
            return;
        }
        crashDetailBean.S = new LinkedHashMap(map.size());
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (!ap.b(entry.getKey())) {
                String key = entry.getKey();
                if (key.length() > 100) {
                    key = key.substring(0, 100);
                    al.d("setted key length is over limit %d substring to %s", 100, key);
                }
                if (ap.b(entry.getValue()) || entry.getValue().length() <= 100000) {
                    value = entry.getValue();
                } else {
                    value = entry.getValue().substring(entry.getValue().length() - BuglyStrategy.a.MAX_USERDATA_VALUE_LENGTH);
                    al.d("setted %s value length is over limit %d substring", key, Integer.valueOf(BuglyStrategy.a.MAX_USERDATA_VALUE_LENGTH));
                }
                crashDetailBean.S.put(key, value);
                al.a("add setted key %s value size:%d", key, Integer.valueOf(value.length()));
            }
        }
    }

    public static void a(String str, String str2, String str3, String str4, String str5, CrashDetailBean crashDetailBean) {
        String str6;
        aa b2 = aa.b();
        if (b2 == null) {
            return;
        }
        al.e("#++++++++++Record By Bugly++++++++++#", new Object[0]);
        al.e("# You can use Bugly(http:\\\\bugly.qq.com) to get more Crash Detail!", new Object[0]);
        al.e("# PKG NAME: %s", b2.c);
        al.e("# APP VER: %s", b2.o);
        al.e("# SDK VER: %s", b2.h);
        al.e("# LAUNCH TIME: %s", ap.a(new Date(aa.b().a)));
        al.e("# CRASH TYPE: %s", str);
        al.e("# CRASH TIME: %s", str2);
        al.e("# CRASH PROCESS: %s", str3);
        al.e("# CRASH FOREGROUND: %s", Boolean.valueOf(b2.a()));
        al.e("# CRASH THREAD: %s", str4);
        if (crashDetailBean != null) {
            al.e("# REPORT ID: %s", crashDetailBean.c);
            Object[] objArr = new Object[2];
            objArr[0] = b2.h();
            objArr[1] = b2.r().booleanValue() ? "ROOTED" : "UNROOT";
            al.e("# CRASH DEVICE: %s %s", objArr);
            al.e("# RUNTIME AVAIL RAM:%d ROM:%d SD:%d", Long.valueOf(crashDetailBean.C), Long.valueOf(crashDetailBean.D), Long.valueOf(crashDetailBean.E));
            al.e("# RUNTIME TOTAL RAM:%d ROM:%d SD:%d", Long.valueOf(crashDetailBean.F), Long.valueOf(crashDetailBean.G), Long.valueOf(crashDetailBean.H));
            if (!ap.b(crashDetailBean.O)) {
                al.e("# EXCEPTION FIRED BY %s %s", crashDetailBean.O, crashDetailBean.N);
            } else if (crashDetailBean.b == 3) {
                Object[] objArr2 = new Object[1];
                if (crashDetailBean.T == null) {
                    str6 = "null";
                } else {
                    str6 = crashDetailBean.T.get("BUGLY_CR_01");
                }
                objArr2[0] = str6;
                al.e("# EXCEPTION ANR MESSAGE:\n %s", objArr2);
            }
        }
        if (!ap.b(str5)) {
            al.e("# CRASH STACK: ", new Object[0]);
            al.e(str5, new Object[0]);
        }
        al.e("#++++++++++++++++++++++++++++++++++++++++++#", new Object[0]);
    }

    private static void a(ArrayList<bn> arrayList, CrashDetailBean crashDetailBean) {
        if (crashDetailBean.j && crashDetailBean.s != null && crashDetailBean.s.length() > 0) {
            try {
                arrayList.add(new bn((byte) 1, "alltimes.txt", crashDetailBean.s.getBytes("utf-8")));
            } catch (Exception e2) {
                e2.printStackTrace();
                al.a(e2);
            }
        }
    }

    private static void a(ArrayList<bn> arrayList, CrashDetailBean crashDetailBean, Context context) {
        bn a2;
        if (crashDetailBean.b != 3) {
            return;
        }
        al.c("crashBean.anrMessages:%s", crashDetailBean.T);
        try {
            if (crashDetailBean.T != null && crashDetailBean.T.containsKey("BUGLY_CR_01")) {
                if (!TextUtils.isEmpty(crashDetailBean.T.get("BUGLY_CR_01"))) {
                    arrayList.add(new bn((byte) 1, "anrMessage.txt", crashDetailBean.T.get("BUGLY_CR_01").getBytes("utf-8")));
                    al.c("attach anr message", new Object[0]);
                }
                crashDetailBean.T.remove("BUGLY_CR_01");
            }
            if (crashDetailBean.v == null || (a2 = a("trace.zip", context, crashDetailBean.v)) == null) {
                return;
            }
            al.c("attach traces", new Object[0]);
            arrayList.add(a2);
        } catch (Exception e2) {
            e2.printStackTrace();
            al.a(e2);
        }
    }

    private static void a(ArrayList<bn> arrayList, String str) {
        if (str != null) {
            try {
                arrayList.add(new bn((byte) 1, "log.txt", str.getBytes("utf-8")));
            } catch (Exception e2) {
                e2.printStackTrace();
                al.a(e2);
            }
        }
    }

    private static void a(ArrayList<bn> arrayList, String str, Context context) {
        if (str != null) {
            try {
                bn a2 = a("backupRecord.zip", context, str);
                if (a2 != null) {
                    al.c("attach backup record", new Object[0]);
                    arrayList.add(a2);
                }
            } catch (Exception e2) {
                al.a(e2);
            }
        }
    }

    private static void a(ArrayList<bn> arrayList, List<String> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        Iterator<String> it2 = list.iterator();
        while (it2.hasNext()) {
            sb.append(it2.next());
        }
        try {
            arrayList.add(new bn((byte) 1, "martianlog.txt", sb.toString().getBytes("utf-8")));
            al.c("attach pageTracingList", new Object[0]);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private static void a(ArrayList<bn> arrayList, byte[] bArr) {
        if (bArr == null || bArr.length <= 0) {
            return;
        }
        try {
            bn bnVar = new bn((byte) 2, "buglylog.zip", bArr);
            al.c("attach user log", new Object[0]);
            arrayList.add(bnVar);
        } catch (Exception e2) {
            al.a(e2);
        }
    }

    static /* synthetic */ void a(List list, boolean z, long j2, String str, String str2) {
        if (list == null || list.isEmpty()) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        Iterator it2 = list.iterator();
        while (it2.hasNext()) {
            CrashDetailBean crashDetailBean = (CrashDetailBean) it2.next();
            String str3 = l.get(Integer.valueOf(crashDetailBean.b));
            if (!TextUtils.isEmpty(str3)) {
                arrayList.add(new ag.c(crashDetailBean.c, str3, crashDetailBean.r, z, j2, str, str2));
            }
        }
        ag.a.a().a(arrayList);
    }

    private static void a(Map<String, String> map, CrashDetailBean crashDetailBean) {
        if (crashDetailBean.U >= 0) {
            StringBuilder sb = new StringBuilder();
            sb.append(crashDetailBean.U);
            map.put("C01", sb.toString());
        }
        if (crashDetailBean.V >= 0) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(crashDetailBean.V);
            map.put("C02", sb2.toString());
        }
        if (crashDetailBean.W != null && crashDetailBean.W.size() > 0) {
            for (Map.Entry<String, String> entry : crashDetailBean.W.entrySet()) {
                map.put("C03_" + entry.getKey(), entry.getValue());
            }
        }
        if (crashDetailBean.X == null || crashDetailBean.X.size() <= 0) {
            return;
        }
        for (Map.Entry<String, String> entry2 : crashDetailBean.X.entrySet()) {
            map.put("C04_" + entry2.getKey(), entry2.getValue());
        }
    }

    public static void a(boolean z, List<CrashDetailBean> list) {
        if (list != null && list.size() > 0) {
            al.c("up finish update state %b", Boolean.valueOf(z));
            for (CrashDetailBean crashDetailBean : list) {
                al.c("pre uid:%s uc:%d re:%b me:%b", crashDetailBean.c, Integer.valueOf(crashDetailBean.l), Boolean.valueOf(crashDetailBean.d), Boolean.valueOf(crashDetailBean.j));
                crashDetailBean.l++;
                crashDetailBean.d = z;
                al.c("set uid:%s uc:%d re:%b me:%b", crashDetailBean.c, Integer.valueOf(crashDetailBean.l), Boolean.valueOf(crashDetailBean.d), Boolean.valueOf(crashDetailBean.j));
            }
            Iterator<CrashDetailBean> it2 = list.iterator();
            while (it2.hasNext()) {
                at.a().a(it2.next());
            }
            al.c("update state size %d", Integer.valueOf(list.size()));
        }
        if (z) {
            return;
        }
        al.b("[crash] upload fail.", new Object[0]);
    }

    private static boolean a(CrashDetailBean crashDetailBean, List<ar> list, List<ar> list2) {
        boolean z = false;
        for (ar arVar : list) {
            if (crashDetailBean.u.equals(arVar.c)) {
                if (arVar.e) {
                    z = true;
                }
                list2.add(arVar);
            }
        }
        return z;
    }

    private static boolean a(String str) {
        if (at.r != null && !at.r.isEmpty()) {
            try {
                al.c("Crash regular filter for crash stack is: %s", at.r);
                if (Pattern.compile(at.r).matcher(str).find()) {
                    al.d("This crash matches the regular filter string set. It will not be record and upload.", new Object[0]);
                    return true;
                }
            } catch (Exception e2) {
                al.a(e2);
                al.d("Failed to compile " + at.r, new Object[0]);
            }
        }
        return false;
    }

    private static ar b(Cursor cursor) {
        if (cursor == null) {
            return null;
        }
        try {
            ar arVar = new ar();
            arVar.a = cursor.getLong(cursor.getColumnIndex("_id"));
            arVar.b = cursor.getLong(cursor.getColumnIndex("_tm"));
            arVar.c = cursor.getString(cursor.getColumnIndex("_s1"));
            arVar.d = cursor.getInt(cursor.getColumnIndex("_up")) == 1;
            arVar.e = cursor.getInt(cursor.getColumnIndex("_me")) == 1;
            arVar.f = cursor.getInt(cursor.getColumnIndex("_uc"));
            return arVar;
        } catch (Throwable th) {
            if (!al.a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    private static List<ar> b() {
        Cursor cursor;
        ArrayList arrayList = new ArrayList();
        Cursor cursor2 = null;
        try {
            try {
                cursor = w.a().a("t_cr", new String[]{"_id", "_tm", "_s1", "_up", "_me", "_uc"}, (String) null);
                if (cursor == null) {
                    if (cursor != null) {
                        cursor.close();
                    }
                    return null;
                }
                try {
                    try {
                        if (cursor.getCount() <= 0) {
                            if (cursor != null) {
                                cursor.close();
                            }
                            return arrayList;
                        }
                        StringBuilder sb = new StringBuilder();
                        sb.append("_id in (");
                        int i2 = 0;
                        while (cursor.moveToNext()) {
                            ar b2 = b(cursor);
                            if (b2 != null) {
                                arrayList.add(b2);
                            } else {
                                try {
                                    sb.append(cursor.getLong(cursor.getColumnIndex("_id")));
                                    sb.append(",");
                                    i2++;
                                } catch (Throwable unused) {
                                    al.d("unknown id!", new Object[0]);
                                }
                            }
                        }
                        if (sb.toString().contains(",")) {
                            sb = new StringBuilder(sb.substring(0, sb.lastIndexOf(",")));
                        }
                        sb.append(")");
                        String sb2 = sb.toString();
                        sb.setLength(0);
                        if (i2 > 0) {
                            al.d("deleted %s illegal data %d", "t_cr", Integer.valueOf(w.a().a("t_cr", sb2)));
                        }
                        if (cursor != null) {
                            cursor.close();
                        }
                        return arrayList;
                    } catch (Throwable th) {
                        th = th;
                        if (cursor != null) {
                            cursor.close();
                        }
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    cursor2 = cursor;
                    if (!al.a(th)) {
                        th.printStackTrace();
                    }
                    if (cursor2 != null) {
                        cursor2.close();
                    }
                    return arrayList;
                }
            } catch (Throwable th3) {
                th = th3;
            }
        } catch (Throwable th4) {
            th = th4;
            cursor = cursor2;
        }
    }

    private static void b(CrashDetailBean crashDetailBean, List<ar> list) {
        StringBuilder sb = new StringBuilder(64);
        for (ar arVar : list) {
            if (!arVar.e && !arVar.d) {
                String str = crashDetailBean.s;
                StringBuilder sb2 = new StringBuilder();
                sb2.append(arVar.b);
                if (!str.contains(sb2.toString())) {
                    crashDetailBean.t++;
                    sb.append(arVar.b);
                    sb.append(ShellUtils.COMMAND_LINE_END);
                }
            }
        }
        crashDetailBean.s += sb.toString();
    }

    private static void b(ArrayList<bn> arrayList, CrashDetailBean crashDetailBean, Context context) {
        if (crashDetailBean.b == 1 && crashDetailBean.v != null) {
            try {
                bn a2 = a("tomb.zip", context, crashDetailBean.v);
                if (a2 != null) {
                    al.c("attach tombs", new Object[0]);
                    arrayList.add(a2);
                }
            } catch (Exception e2) {
                al.a(e2);
            }
        }
    }

    private static void b(ArrayList<bn> arrayList, String str) {
        if (str != null) {
            try {
                arrayList.add(new bn((byte) 1, "jniLog.txt", str.getBytes("utf-8")));
            } catch (Exception e2) {
                e2.printStackTrace();
                al.a(e2);
            }
        }
    }

    private static void b(ArrayList<bn> arrayList, byte[] bArr) {
        if (bArr == null || bArr.length <= 0) {
            return;
        }
        try {
            arrayList.add(new bn((byte) 1, "userExtraByteData", bArr));
            al.c("attach extraData", new Object[0]);
        } catch (Exception e2) {
            al.a(e2);
        }
    }

    private static void b(List<ar> list) {
        List<CrashDetailBean> c2 = c(list);
        if (c2 == null || c2.isEmpty()) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (CrashDetailBean crashDetailBean : c2) {
            String str = l.get(Integer.valueOf(crashDetailBean.b));
            if (!TextUtils.isEmpty(str)) {
                al.c("find expired data,crashId:%s eventType:%s", crashDetailBean.c, str);
                arrayList.add(new ag.c(crashDetailBean.c, str, crashDetailBean.r, false, 0L, "expired", null));
            }
        }
        ag.a.a().a(arrayList);
    }

    /* JADX WARN: Code restructure failed: missing block: B:37:0x0036, code lost:
    
        if (r0.size() >= com.tencent.bugly.proguard.at.d) goto L25;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private boolean b(com.tencent.bugly.crashreport.crash.CrashDetailBean r9, java.util.List<com.tencent.bugly.proguard.ar> r10, java.util.List<com.tencent.bugly.proguard.ar> r11) {
        /*
            r8 = this;
            int r0 = r9.b
            r1 = 1
            r2 = 0
            if (r0 == 0) goto Lb
            if (r0 != r1) goto L9
            goto Lb
        L9:
            r3 = 0
            goto Lc
        Lb:
            r3 = 1
        Lc:
            r4 = 3
            if (r0 != r4) goto L11
            r0 = 1
            goto L12
        L11:
            r0 = 0
        L12:
            boolean r4 = com.tencent.bugly.proguard.p.c
            if (r4 != 0) goto L1f
            if (r0 != 0) goto L1c
            if (r3 != 0) goto L1c
            r0 = 1
            goto L20
        L1c:
            boolean r0 = com.tencent.bugly.proguard.at.e
            goto L20
        L1f:
            r0 = 0
        L20:
            if (r0 != 0) goto L23
            return r2
        L23:
            java.util.ArrayList r0 = new java.util.ArrayList
            r3 = 10
            r0.<init>(r3)
            boolean r10 = a(r9, r10, r0)
            if (r10 != 0) goto L38
            int r10 = r0.size()     // Catch: java.lang.Exception -> L6d
            int r3 = com.tencent.bugly.proguard.at.d     // Catch: java.lang.Exception -> L6d
            if (r10 < r3) goto L78
        L38:
            java.lang.String r10 = "same crash occur too much do merged!"
            java.lang.Object[] r3 = new java.lang.Object[r2]     // Catch: java.lang.Exception -> L6d
            com.tencent.bugly.proguard.al.a(r10, r3)     // Catch: java.lang.Exception -> L6d
            com.tencent.bugly.crashreport.crash.CrashDetailBean r9 = a(r0, r9)     // Catch: java.lang.Exception -> L6d
            java.util.Iterator r10 = r0.iterator()     // Catch: java.lang.Exception -> L6d
        L47:
            boolean r0 = r10.hasNext()     // Catch: java.lang.Exception -> L6d
            if (r0 == 0) goto L5f
            java.lang.Object r0 = r10.next()     // Catch: java.lang.Exception -> L6d
            com.tencent.bugly.proguard.ar r0 = (com.tencent.bugly.proguard.ar) r0     // Catch: java.lang.Exception -> L6d
            long r3 = r0.a     // Catch: java.lang.Exception -> L6d
            long r5 = r9.a     // Catch: java.lang.Exception -> L6d
            int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r7 == 0) goto L47
            r11.add(r0)     // Catch: java.lang.Exception -> L6d
            goto L47
        L5f:
            r8.b(r9)     // Catch: java.lang.Exception -> L6d
            d(r11)     // Catch: java.lang.Exception -> L6d
            java.lang.String r9 = "[crash] save crash success. For this device crash many times, it will not upload crashes immediately"
            java.lang.Object[] r10 = new java.lang.Object[r2]     // Catch: java.lang.Exception -> L6d
            com.tencent.bugly.proguard.al.b(r9, r10)     // Catch: java.lang.Exception -> L6d
            return r1
        L6d:
            r9 = move-exception
            com.tencent.bugly.proguard.al.a(r9)
            java.lang.Object[] r9 = new java.lang.Object[r2]
            java.lang.String r10 = "Failed to merge crash."
            com.tencent.bugly.proguard.al.d(r10, r9)
        L78:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.as.b(com.tencent.bugly.crashreport.crash.CrashDetailBean, java.util.List, java.util.List):boolean");
    }

    private static ContentValues c(CrashDetailBean crashDetailBean) {
        if (crashDetailBean == null) {
            return null;
        }
        try {
            ContentValues contentValues = new ContentValues();
            if (crashDetailBean.a > 0) {
                contentValues.put("_id", Long.valueOf(crashDetailBean.a));
            }
            contentValues.put("_tm", Long.valueOf(crashDetailBean.r));
            contentValues.put("_s1", crashDetailBean.u);
            int i2 = 1;
            contentValues.put("_up", Integer.valueOf(crashDetailBean.d ? 1 : 0));
            if (!crashDetailBean.j) {
                i2 = 0;
            }
            contentValues.put("_me", Integer.valueOf(i2));
            contentValues.put("_uc", Integer.valueOf(crashDetailBean.l));
            contentValues.put("_dt", ap.a(crashDetailBean));
            return contentValues;
        } catch (Throwable th) {
            if (!al.a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00f5  */
    /* JADX WARN: Type inference failed for: r6v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r6v1 */
    /* JADX WARN: Type inference failed for: r6v3, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.tencent.bugly.proguard.w] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static java.util.List<com.tencent.bugly.crashreport.crash.CrashDetailBean> c(java.util.List<com.tencent.bugly.proguard.ar> r10) {
        /*
            Method dump skipped, instructions count: 250
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.as.c(java.util.List):java.util.List");
    }

    private static void c(ArrayList<bn> arrayList, String str) {
        if (ap.b(str)) {
            return;
        }
        try {
            bn bnVar = new bn((byte) 1, "crashInfos.txt", str.getBytes("utf-8"));
            al.c("attach crash infos", new Object[0]);
            arrayList.add(bnVar);
        } catch (Exception e2) {
            e2.printStackTrace();
            al.a(e2);
        }
    }

    private static void d(List<ar> list) {
        if (list == null || list.size() == 0) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("_id in (");
        Iterator<ar> it2 = list.iterator();
        while (it2.hasNext()) {
            sb.append(it2.next().a);
            sb.append(",");
        }
        StringBuilder sb2 = new StringBuilder(sb.substring(0, sb.lastIndexOf(",")));
        sb2.append(")");
        String sb3 = sb2.toString();
        sb2.setLength(0);
        try {
            al.c("deleted %s data %d", "t_cr", Integer.valueOf(w.a().a("t_cr", sb3)));
        } catch (Throwable th) {
            if (al.a(th)) {
                return;
            }
            th.printStackTrace();
        }
    }

    private boolean d(CrashDetailBean crashDetailBean) {
        try {
            al.c("save eup logs", new Object[0]);
            aa b2 = aa.b();
            String str = "#--------\npackage:" + b2.e() + "\nversion:" + b2.o + "\nsdk:" + b2.h + "\nprocess:" + crashDetailBean.A + "\ndate:" + ap.a(new Date(crashDetailBean.r)) + "\ntype:" + crashDetailBean.n + "\nmessage:" + crashDetailBean.o + "\nstack:\n" + crashDetailBean.q + "\neupID:" + crashDetailBean.c + ShellUtils.COMMAND_LINE_END;
            String str2 = null;
            if (at.m != null) {
                File file = new File(at.m);
                if (file.isFile()) {
                    file = file.getParentFile();
                }
                str2 = file.getAbsolutePath();
            } else if (Environment.getExternalStorageState().equals("mounted")) {
                str2 = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Tencent/" + this.b.getPackageName();
            }
            am.a(str2 + "/euplog.txt", str, at.n);
            return true;
        } catch (Throwable th) {
            al.d("rqdp{  save error} %s", th.toString());
            if (!al.a(th)) {
                th.printStackTrace();
            }
            return false;
        }
    }

    private static String e(CrashDetailBean crashDetailBean) {
        try {
            Pair<String, String> pair = h.get(Integer.valueOf(crashDetailBean.b));
            if (pair != null) {
                return crashDetailBean.j ? (String) pair.first : (String) pair.second;
            }
            al.e("crash type error! %d", Integer.valueOf(crashDetailBean.b));
            return "";
        } catch (Exception e2) {
            al.a(e2);
            return "";
        }
    }

    private static void e(List<CrashDetailBean> list) {
        try {
            if (list.size() == 0) {
                return;
            }
            StringBuilder sb = new StringBuilder();
            for (CrashDetailBean crashDetailBean : list) {
                sb.append(" or _id = ");
                sb.append(crashDetailBean.a);
            }
            String sb2 = sb.toString();
            if (sb2.length() > 0) {
                sb2 = sb2.substring(4);
            }
            sb.setLength(0);
            al.c("deleted %s data %d", "t_cr", Integer.valueOf(w.a().a("t_cr", sb2)));
        } catch (Throwable th) {
            if (al.a(th)) {
                return;
            }
            th.printStackTrace();
        }
    }

    public final void a(CrashDetailBean crashDetailBean) {
        int i2 = crashDetailBean.b;
        if (i2 != 0) {
            if (i2 != 1) {
                if (i2 == 3 && !at.a().k()) {
                    return;
                }
            } else if (!at.a().j()) {
                return;
            }
        } else if (!at.a().j()) {
            return;
        }
        if (this.f != null) {
            al.c("Calling 'onCrashHandleEnd' of RQD crash listener.", new Object[0]);
        }
    }

    public final void a(final List<CrashDetailBean> list, long j2, final boolean z, boolean z2, boolean z3) {
        if (!aa.a(this.b).f) {
            al.d("warn: not upload process", new Object[0]);
            return;
        }
        ai aiVar = this.c;
        if (aiVar == null) {
            al.d("warn: upload manager is null", new Object[0]);
            return;
        }
        if (!z3 && !aiVar.b(at.a)) {
            al.d("warn: not crashHappen or not should upload", new Object[0]);
            return;
        }
        StrategyBean c2 = this.e.c();
        if (!c2.f) {
            al.d("remote report is disable!", new Object[0]);
            al.b("[crash] server closed bugly in this app. please check your appid if is correct, and re-install it", new Object[0]);
            return;
        }
        if (list == null || list.size() == 0) {
            al.d("warn: crashList is null or crashList num is 0", new Object[0]);
            return;
        }
        try {
            String str = c2.r;
            String str2 = StrategyBean.b;
            bp a2 = a(this.b, list, aa.b());
            if (a2 == null) {
                al.d("create eupPkg fail!", new Object[0]);
                return;
            }
            byte[] a3 = ae.a((m) a2);
            if (a3 == null) {
                al.d("send encode fail!", new Object[0]);
                return;
            }
            bq a4 = ae.a(this.b, 830, a3);
            if (a4 == null) {
                al.d("request package is null.", new Object[0]);
                return;
            }
            final long currentTimeMillis = System.currentTimeMillis();
            ah ahVar = new ah() { // from class: com.tencent.bugly.proguard.as.6
                @Override // com.tencent.bugly.proguard.ah
                public final void a(boolean z4, String str3) {
                    as.a(list, z4, System.currentTimeMillis() - currentTimeMillis, z ? "realtime" : "cache", str3);
                    as.a(z4, (List<CrashDetailBean>) list);
                }
            };
            if (z) {
                this.c.a(a, a4, str, str2, ahVar, j2, z2);
            } else {
                this.c.a(a, a4, str, str2, ahVar, false);
            }
        } catch (Throwable th) {
            al.e("req cr error %s", th.toString());
            if (al.b(th)) {
                return;
            }
            th.printStackTrace();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:102:0x026a  */
    /* JADX WARN: Removed duplicated region for block: B:105:0x01df  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x01b6  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x020e  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x0251 A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean a(com.tencent.bugly.crashreport.crash.CrashDetailBean r20, boolean r21) {
        /*
            Method dump skipped, instructions count: 653
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.as.a(com.tencent.bugly.crashreport.crash.CrashDetailBean, boolean):boolean");
    }

    public final void b(CrashDetailBean crashDetailBean) {
        if (crashDetailBean == null) {
            return;
        }
        ContentValues c2 = c(crashDetailBean);
        if (c2 != null) {
            long a2 = w.a().a("t_cr", c2, (v) null);
            if (a2 >= 0) {
                al.c("insert %s success!", "t_cr");
                crashDetailBean.a = a2;
            }
        }
        if (at.l) {
            d(crashDetailBean);
        }
    }

    public final void b(CrashDetailBean crashDetailBean, boolean z) {
        if (!at.o) {
            al.a("do not upload spot crash right now, crash would be uploaded when app next start", new Object[0]);
            return;
        }
        al.a("try to upload right now", new Object[0]);
        ArrayList arrayList = new ArrayList();
        arrayList.add(crashDetailBean);
        a(arrayList, 3000L, z, crashDetailBean.b == 7, z);
    }
}
