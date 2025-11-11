package com.tencent.bugly.proguard;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import androidx.work.PeriodicWorkRequest;
import com.tencent.bugly.crashreport.biz.UserInfoBean;
import com.tencent.bugly.crashreport.common.strategy.StrategyBean;
import java.io.BufferedReader;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* compiled from: BUGLY */
/* loaded from: classes.dex */
public final class r {
    private static boolean e = true;
    private Context a;
    private long b;
    private int c;
    private boolean d;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: BUGLY */
    /* loaded from: classes.dex */
    public class a implements Runnable {
        private boolean b;
        private UserInfoBean c;

        public a(UserInfoBean userInfoBean, boolean z) {
            this.c = userInfoBean;
            this.b = z;
        }

        @Override // java.lang.Runnable
        public final void run() {
            if (r.this.d) {
                try {
                    if (this.c != null) {
                        r.a(this.c);
                        al.c("[UserInfo] Record user info.", new Object[0]);
                        r.this.a(this.c, false);
                    }
                    if (this.b) {
                        r.this.b();
                    }
                } catch (Throwable th) {
                    if (al.a(th)) {
                        return;
                    }
                    th.printStackTrace();
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: BUGLY */
    /* loaded from: classes.dex */
    public class b implements Runnable {
        b() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis < r.this.b) {
                ak.a().a(new b(), (r.this.b - currentTimeMillis) + 5000);
            } else {
                r.this.a(3, false);
                r.this.a();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: BUGLY */
    /* loaded from: classes.dex */
    public class c implements Runnable {
        private long b;

        public c(long j) {
            this.b = 21600000L;
            this.b = j;
        }

        @Override // java.lang.Runnable
        public final void run() {
            r.this.b();
            r.this.a(this.b);
        }
    }

    public r(Context context, boolean z) {
        this.d = true;
        this.a = context;
        this.d = z;
    }

    private static int a(List<UserInfoBean> list) {
        long currentTimeMillis = System.currentTimeMillis();
        int i = 0;
        for (UserInfoBean userInfoBean : list) {
            if (userInfoBean.e > currentTimeMillis - 600000 && (userInfoBean.b == 1 || userInfoBean.b == 4 || userInfoBean.b == 3)) {
                i++;
            }
        }
        return i;
    }

    private static UserInfoBean a(Cursor cursor) {
        if (cursor == null) {
            return null;
        }
        try {
            byte[] blob = cursor.getBlob(cursor.getColumnIndex("_dt"));
            if (blob == null) {
                return null;
            }
            long j = cursor.getLong(cursor.getColumnIndex("_id"));
            UserInfoBean userInfoBean = (UserInfoBean) ap.a(blob, UserInfoBean.CREATOR);
            if (userInfoBean != null) {
                userInfoBean.a = j;
            }
            return userInfoBean;
        } catch (Throwable th) {
            if (!al.a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:47:0x00ab  */
    /* JADX WARN: Type inference failed for: r8v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r8v2 */
    /* JADX WARN: Type inference failed for: r8v3, types: [android.database.Cursor] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.util.List<com.tencent.bugly.crashreport.biz.UserInfoBean> a(java.lang.String r8) {
        /*
            java.lang.String r0 = "t_ui"
            r1 = 0
            boolean r2 = com.tencent.bugly.proguard.ap.b(r8)     // Catch: java.lang.Throwable -> L94 java.lang.Throwable -> L97
            if (r2 == 0) goto Lb
            r8 = r1
            goto L1e
        Lb:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L94 java.lang.Throwable -> L97
            java.lang.String r3 = "_pc = '"
            r2.<init>(r3)     // Catch: java.lang.Throwable -> L94 java.lang.Throwable -> L97
            r2.append(r8)     // Catch: java.lang.Throwable -> L94 java.lang.Throwable -> L97
            java.lang.String r8 = "'"
            r2.append(r8)     // Catch: java.lang.Throwable -> L94 java.lang.Throwable -> L97
            java.lang.String r8 = r2.toString()     // Catch: java.lang.Throwable -> L94 java.lang.Throwable -> L97
        L1e:
            com.tencent.bugly.proguard.w r2 = com.tencent.bugly.proguard.w.a()     // Catch: java.lang.Throwable -> L94 java.lang.Throwable -> L97
            android.database.Cursor r8 = r2.a(r0, r1, r8)     // Catch: java.lang.Throwable -> L94 java.lang.Throwable -> L97
            if (r8 != 0) goto L2e
            if (r8 == 0) goto L2d
            r8.close()
        L2d:
            return r1
        L2e:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L92 java.lang.Throwable -> La8
            r2.<init>()     // Catch: java.lang.Throwable -> L92 java.lang.Throwable -> La8
            java.util.ArrayList r3 = new java.util.ArrayList     // Catch: java.lang.Throwable -> L92 java.lang.Throwable -> La8
            r3.<init>()     // Catch: java.lang.Throwable -> L92 java.lang.Throwable -> La8
        L38:
            boolean r4 = r8.moveToNext()     // Catch: java.lang.Throwable -> L92 java.lang.Throwable -> La8
            r5 = 0
            if (r4 == 0) goto L64
            com.tencent.bugly.crashreport.biz.UserInfoBean r4 = a(r8)     // Catch: java.lang.Throwable -> L92 java.lang.Throwable -> La8
            if (r4 == 0) goto L49
            r3.add(r4)     // Catch: java.lang.Throwable -> L92 java.lang.Throwable -> La8
            goto L38
        L49:
            java.lang.String r4 = "_id"
            int r4 = r8.getColumnIndex(r4)     // Catch: java.lang.Throwable -> L5c java.lang.Throwable -> La8
            long r6 = r8.getLong(r4)     // Catch: java.lang.Throwable -> L5c java.lang.Throwable -> La8
            java.lang.String r4 = " or _id = "
            r2.append(r4)     // Catch: java.lang.Throwable -> L5c java.lang.Throwable -> La8
            r2.append(r6)     // Catch: java.lang.Throwable -> L5c java.lang.Throwable -> La8
            goto L38
        L5c:
            java.lang.String r4 = "[Database] unknown id."
            java.lang.Object[] r5 = new java.lang.Object[r5]     // Catch: java.lang.Throwable -> L92 java.lang.Throwable -> La8
            com.tencent.bugly.proguard.al.d(r4, r5)     // Catch: java.lang.Throwable -> L92 java.lang.Throwable -> La8
            goto L38
        L64:
            java.lang.String r2 = r2.toString()     // Catch: java.lang.Throwable -> L92 java.lang.Throwable -> La8
            int r4 = r2.length()     // Catch: java.lang.Throwable -> L92 java.lang.Throwable -> La8
            if (r4 <= 0) goto L8c
            r4 = 4
            java.lang.String r2 = r2.substring(r4)     // Catch: java.lang.Throwable -> L92 java.lang.Throwable -> La8
            com.tencent.bugly.proguard.w r4 = com.tencent.bugly.proguard.w.a()     // Catch: java.lang.Throwable -> L92 java.lang.Throwable -> La8
            int r2 = r4.a(r0, r2)     // Catch: java.lang.Throwable -> L92 java.lang.Throwable -> La8
            java.lang.String r4 = "[Database] deleted %s error data %d"
            r6 = 2
            java.lang.Object[] r6 = new java.lang.Object[r6]     // Catch: java.lang.Throwable -> L92 java.lang.Throwable -> La8
            r6[r5] = r0     // Catch: java.lang.Throwable -> L92 java.lang.Throwable -> La8
            r0 = 1
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch: java.lang.Throwable -> L92 java.lang.Throwable -> La8
            r6[r0] = r2     // Catch: java.lang.Throwable -> L92 java.lang.Throwable -> La8
            com.tencent.bugly.proguard.al.d(r4, r6)     // Catch: java.lang.Throwable -> L92 java.lang.Throwable -> La8
        L8c:
            if (r8 == 0) goto L91
            r8.close()
        L91:
            return r3
        L92:
            r0 = move-exception
            goto L99
        L94:
            r0 = move-exception
            r8 = r1
            goto La9
        L97:
            r0 = move-exception
            r8 = r1
        L99:
            boolean r2 = com.tencent.bugly.proguard.al.a(r0)     // Catch: java.lang.Throwable -> La8
            if (r2 != 0) goto La2
            r0.printStackTrace()     // Catch: java.lang.Throwable -> La8
        La2:
            if (r8 == 0) goto La7
            r8.close()
        La7:
            return r1
        La8:
            r0 = move-exception
        La9:
            if (r8 == 0) goto Lae
            r8.close()
        Lae:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.r.a(java.lang.String):java.util.List");
    }

    static /* synthetic */ void a(UserInfoBean userInfoBean) {
        aa b2;
        if (userInfoBean == null || (b2 = aa.b()) == null) {
            return;
        }
        userInfoBean.j = b2.d();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(UserInfoBean userInfoBean, boolean z) {
        List<UserInfoBean> a2;
        if (userInfoBean == null) {
            return;
        }
        if (!z && userInfoBean.b != 1 && (a2 = a(aa.a(this.a).d)) != null && a2.size() >= 20) {
            al.a("[UserInfo] There are too many user info in local: %d", Integer.valueOf(a2.size()));
            return;
        }
        long a3 = w.a().a("t_ui", b(userInfoBean), (v) null);
        if (a3 >= 0) {
            al.c("[Database] insert %s success with ID: %d", "t_ui", Long.valueOf(a3));
            userInfoBean.a = a3;
        }
    }

    private static void a(List<UserInfoBean> list, List<UserInfoBean> list2) {
        int size = list.size() - 20;
        if (size > 0) {
            int i = 0;
            while (i < list.size() - 1) {
                int i2 = i + 1;
                for (int i3 = i2; i3 < list.size(); i3++) {
                    if (list.get(i).e > list.get(i3).e) {
                        UserInfoBean userInfoBean = list.get(i);
                        list.set(i, list.get(i3));
                        list.set(i3, userInfoBean);
                    }
                }
                i = i2;
            }
            for (int i4 = 0; i4 < size; i4++) {
                list2.add(list.get(i4));
            }
        }
    }

    private void a(final List<UserInfoBean> list, boolean z) {
        aa b2;
        if (!b(z)) {
            long currentTimeMillis = System.currentTimeMillis();
            for (UserInfoBean userInfoBean : list) {
                userInfoBean.f = currentTimeMillis;
                a(userInfoBean, true);
            }
            al.d("uploadCheck failed", new Object[0]);
            return;
        }
        int i = this.c == 1 ? 1 : 2;
        bv bvVar = null;
        if (list != null && list.size() != 0 && (b2 = aa.b()) != null) {
            b2.o();
            bv bvVar2 = new bv();
            bvVar2.b = b2.d;
            bvVar2.c = b2.g();
            ArrayList<bu> arrayList = new ArrayList<>();
            Iterator<UserInfoBean> it2 = list.iterator();
            while (it2.hasNext()) {
                bu a2 = ae.a(it2.next());
                if (a2 != null) {
                    arrayList.add(a2);
                }
            }
            bvVar2.d = arrayList;
            bvVar2.e = new HashMap();
            Map<String, String> map = bvVar2.e;
            StringBuilder sb = new StringBuilder();
            b2.getClass();
            map.put("A7", sb.toString());
            bvVar2.e.put("A6", aa.n());
            bvVar2.e.put("A5", b2.m());
            Map<String, String> map2 = bvVar2.e;
            StringBuilder sb2 = new StringBuilder();
            sb2.append(b2.k());
            map2.put("A2", sb2.toString());
            Map<String, String> map3 = bvVar2.e;
            StringBuilder sb3 = new StringBuilder();
            sb3.append(b2.k());
            map3.put("A1", sb3.toString());
            bvVar2.e.put("A24", b2.k);
            Map<String, String> map4 = bvVar2.e;
            StringBuilder sb4 = new StringBuilder();
            sb4.append(b2.l());
            map4.put("A17", sb4.toString());
            bvVar2.e.put("A15", b2.q());
            Map<String, String> map5 = bvVar2.e;
            StringBuilder sb5 = new StringBuilder();
            sb5.append(b2.r());
            map5.put("A13", sb5.toString());
            bvVar2.e.put("F08", b2.E);
            bvVar2.e.put("F09", b2.F);
            Map<String, String> y = b2.y();
            if (y != null && y.size() > 0) {
                for (Map.Entry<String, String> entry : y.entrySet()) {
                    bvVar2.e.put("C04_" + entry.getKey(), entry.getValue());
                }
            }
            if (i == 1) {
                bvVar2.a = (byte) 1;
            } else if (i != 2) {
                al.e("unknown up type %d ", Integer.valueOf(i));
            } else {
                bvVar2.a = (byte) 2;
            }
            bvVar = bvVar2;
        }
        if (bvVar == null) {
            al.d("[UserInfo] Failed to create UserInfoPackage.", new Object[0]);
            return;
        }
        byte[] a3 = ae.a((m) bvVar);
        if (a3 == null) {
            al.d("[UserInfo] Failed to encode data.", new Object[0]);
            return;
        }
        bq a4 = ae.a(this.a, 840, a3);
        if (a4 == null) {
            al.d("[UserInfo] Request package is null.", new Object[0]);
            return;
        }
        ai.a().a(1001, a4, ac.a().c().q, StrategyBean.a, new ah() { // from class: com.tencent.bugly.proguard.r.1
            @Override // com.tencent.bugly.proguard.ah
            public final void a(boolean z2, String str) {
                if (z2) {
                    al.c("[UserInfo] Successfully uploaded user info.", new Object[0]);
                    long currentTimeMillis2 = System.currentTimeMillis();
                    for (UserInfoBean userInfoBean2 : list) {
                        userInfoBean2.f = currentTimeMillis2;
                        r.this.a(userInfoBean2, true);
                    }
                }
            }
        }, this.c == 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:10:0x002b A[Catch: all -> 0x0092, TRY_ENTER, TryCatch #0 {, blocks: (B:3:0x0001, B:10:0x002b, B:12:0x003e, B:14:0x004c, B:15:0x0061, B:17:0x0067, B:19:0x006c, B:22:0x0073, B:25:0x0089, B:29:0x005b, B:30:0x0009, B:33:0x0010, B:36:0x0017, B:38:0x001d), top: B:2:0x0001 }] */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0067 A[Catch: all -> 0x0092, TryCatch #0 {, blocks: (B:3:0x0001, B:10:0x002b, B:12:0x003e, B:14:0x004c, B:15:0x0061, B:17:0x0067, B:19:0x006c, B:22:0x0073, B:25:0x0089, B:29:0x005b, B:30:0x0009, B:33:0x0010, B:36:0x0017, B:38:0x001d), top: B:2:0x0001 }] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0029 A[DONT_GENERATE] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public synchronized void a(boolean r8) {
        /*
            r7 = this;
            monitor-enter(r7)
            boolean r0 = r7.d     // Catch: java.lang.Throwable -> L92
            r1 = 1
            r2 = 0
            if (r0 != 0) goto L9
        L7:
            r0 = 0
            goto L27
        L9:
            com.tencent.bugly.proguard.ai r0 = com.tencent.bugly.proguard.ai.a()     // Catch: java.lang.Throwable -> L92
            if (r0 != 0) goto L10
            goto L7
        L10:
            com.tencent.bugly.proguard.ac r3 = com.tencent.bugly.proguard.ac.a()     // Catch: java.lang.Throwable -> L92
            if (r3 != 0) goto L17
            goto L7
        L17:
            boolean r3 = r3.b()     // Catch: java.lang.Throwable -> L92
            if (r3 == 0) goto L26
            r3 = 1001(0x3e9, float:1.403E-42)
            boolean r0 = r0.b(r3)     // Catch: java.lang.Throwable -> L92
            if (r0 != 0) goto L26
            goto L7
        L26:
            r0 = 1
        L27:
            if (r0 != 0) goto L2b
            monitor-exit(r7)
            return
        L2b:
            android.content.Context r0 = r7.a     // Catch: java.lang.Throwable -> L92
            com.tencent.bugly.proguard.aa r0 = com.tencent.bugly.proguard.aa.a(r0)     // Catch: java.lang.Throwable -> L92
            java.lang.String r0 = r0.d     // Catch: java.lang.Throwable -> L92
            java.util.ArrayList r3 = new java.util.ArrayList     // Catch: java.lang.Throwable -> L92
            r3.<init>()     // Catch: java.lang.Throwable -> L92
            java.util.List r0 = a(r0)     // Catch: java.lang.Throwable -> L92
            if (r0 == 0) goto L5b
            a(r0, r3)     // Catch: java.lang.Throwable -> L92
            b(r0, r3)     // Catch: java.lang.Throwable -> L92
            int r4 = a(r0)     // Catch: java.lang.Throwable -> L92
            r5 = 15
            if (r4 <= r5) goto L60
            java.lang.String r5 = "[UserInfo] Upload user info too many times in 10 min: %d"
            java.lang.Object[] r6 = new java.lang.Object[r1]     // Catch: java.lang.Throwable -> L92
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch: java.lang.Throwable -> L92
            r6[r2] = r4     // Catch: java.lang.Throwable -> L92
            com.tencent.bugly.proguard.al.d(r5, r6)     // Catch: java.lang.Throwable -> L92
            r4 = 0
            goto L61
        L5b:
            java.util.ArrayList r0 = new java.util.ArrayList     // Catch: java.lang.Throwable -> L92
            r0.<init>()     // Catch: java.lang.Throwable -> L92
        L60:
            r4 = 1
        L61:
            int r5 = r3.size()     // Catch: java.lang.Throwable -> L92
            if (r5 <= 0) goto L6a
            b(r3)     // Catch: java.lang.Throwable -> L92
        L6a:
            if (r4 == 0) goto L89
            int r3 = r0.size()     // Catch: java.lang.Throwable -> L92
            if (r3 != 0) goto L73
            goto L89
        L73:
            java.lang.String r3 = "[UserInfo] Upload user info(size: %d)"
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch: java.lang.Throwable -> L92
            int r4 = r0.size()     // Catch: java.lang.Throwable -> L92
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch: java.lang.Throwable -> L92
            r1[r2] = r4     // Catch: java.lang.Throwable -> L92
            com.tencent.bugly.proguard.al.c(r3, r1)     // Catch: java.lang.Throwable -> L92
            r7.a(r0, r8)     // Catch: java.lang.Throwable -> L92
            monitor-exit(r7)
            return
        L89:
            java.lang.String r8 = "[UserInfo] There is no user info in local database."
            java.lang.Object[] r0 = new java.lang.Object[r2]     // Catch: java.lang.Throwable -> L92
            com.tencent.bugly.proguard.al.c(r8, r0)     // Catch: java.lang.Throwable -> L92
            monitor-exit(r7)
            return
        L92:
            r8 = move-exception
            monitor-exit(r7)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.r.a(boolean):void");
    }

    private static ContentValues b(UserInfoBean userInfoBean) {
        if (userInfoBean == null) {
            return null;
        }
        try {
            ContentValues contentValues = new ContentValues();
            if (userInfoBean.a > 0) {
                contentValues.put("_id", Long.valueOf(userInfoBean.a));
            }
            contentValues.put("_tm", Long.valueOf(userInfoBean.e));
            contentValues.put("_ut", Long.valueOf(userInfoBean.f));
            contentValues.put("_tp", Integer.valueOf(userInfoBean.b));
            contentValues.put("_pc", userInfoBean.c);
            contentValues.put("_dt", ap.a(userInfoBean));
            return contentValues;
        } catch (Throwable th) {
            if (!al.a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    private static void b(List<UserInfoBean> list) {
        if (list.size() == 0) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size() && i < 50; i++) {
            UserInfoBean userInfoBean = list.get(i);
            sb.append(" or _id = ");
            sb.append(userInfoBean.a);
        }
        String sb2 = sb.toString();
        if (sb2.length() > 0) {
            sb2 = sb2.substring(4);
        }
        sb.setLength(0);
        try {
            al.c("[Database] deleted %s data %d", "t_ui", Integer.valueOf(w.a().a("t_ui", sb2)));
        } catch (Throwable th) {
            if (al.a(th)) {
                return;
            }
            th.printStackTrace();
        }
    }

    private static void b(List<UserInfoBean> list, List<UserInfoBean> list2) {
        Iterator<UserInfoBean> it2 = list.iterator();
        while (it2.hasNext()) {
            UserInfoBean next = it2.next();
            if (next.f != -1) {
                it2.remove();
                if (next.e < ap.b()) {
                    list2.add(next);
                }
            }
        }
    }

    private boolean b(boolean z) {
        boolean z2;
        boolean z3 = true;
        if (!e) {
            return true;
        }
        File file = new File(this.a.getFilesDir(), "bugly_last_us_up_tm");
        long currentTimeMillis = System.currentTimeMillis();
        if (z) {
            am.a(file, String.valueOf(currentTimeMillis), 1024L, false);
            return true;
        }
        if (file.exists()) {
            BufferedReader a2 = ap.a(file);
            try {
                if (a2 != null) {
                    try {
                        try {
                            long longValue = Long.valueOf(a2.readLine().trim()).longValue();
                            if (currentTimeMillis >= longValue && currentTimeMillis - longValue <= 86400000) {
                                z2 = true;
                                if (z2 || currentTimeMillis - longValue >= PeriodicWorkRequest.MIN_PERIODIC_FLEX_MILLIS) {
                                    am.a(file, String.valueOf(currentTimeMillis), 1024L, false);
                                } else {
                                    z3 = false;
                                }
                            }
                            z2 = false;
                            if (z2) {
                            }
                            am.a(file, String.valueOf(currentTimeMillis), 1024L, false);
                        } catch (Throwable th) {
                            al.b(th);
                            am.a(file, String.valueOf(currentTimeMillis), 1024L, false);
                            if (a2 != null) {
                                a2.close();
                            }
                        }
                    } catch (Throwable th2) {
                        if (a2 != null) {
                            try {
                                a2.close();
                            } catch (Exception e2) {
                                al.a(e2);
                            }
                        }
                        throw th2;
                    }
                }
                if (a2 != null) {
                    a2.close();
                }
            } catch (Exception e3) {
                al.a(e3);
            }
        } else {
            am.a(file, String.valueOf(currentTimeMillis), 1024L, false);
        }
        return z3;
    }

    public final void a() {
        this.b = ap.b() + 86400000;
        ak.a().a(new b(), (this.b - System.currentTimeMillis()) + 5000);
    }

    public final void a(int i, boolean z) {
        ac a2 = ac.a();
        if (a2 != null && !a2.c().g && i != 1 && i != 3) {
            al.e("UserInfo is disable", new Object[0]);
            return;
        }
        if (i == 1 || i == 3) {
            this.c++;
        }
        aa a3 = aa.a(this.a);
        UserInfoBean userInfoBean = new UserInfoBean();
        userInfoBean.b = i;
        userInfoBean.c = a3.d;
        userInfoBean.d = a3.f();
        userInfoBean.e = System.currentTimeMillis();
        userInfoBean.f = -1L;
        userInfoBean.n = a3.o;
        userInfoBean.o = i == 1 ? 1 : 0;
        userInfoBean.l = a3.a();
        userInfoBean.m = a3.y;
        userInfoBean.g = a3.z;
        userInfoBean.h = a3.A;
        userInfoBean.i = a3.B;
        userInfoBean.k = a3.C;
        userInfoBean.r = a3.t();
        userInfoBean.s = a3.y();
        userInfoBean.p = a3.z();
        userInfoBean.q = a3.x;
        ak.a().a(new a(userInfoBean, z), 0L);
    }

    public final void a(long j) {
        ak.a().a(new c(j), j);
    }

    public final void b() {
        ak a2 = ak.a();
        if (a2 != null) {
            a2.a(new Runnable() { // from class: com.tencent.bugly.proguard.r.2
                final /* synthetic */ boolean a = false;

                @Override // java.lang.Runnable
                public final void run() {
                    try {
                        r.this.a(this.a);
                    } catch (Throwable th) {
                        al.a(th);
                    }
                }
            });
        }
    }
}
