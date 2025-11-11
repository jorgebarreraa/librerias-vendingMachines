package com.tencent.bugly.proguard;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: BUGLY */
/* loaded from: classes.dex */
public final class w {
    public static boolean a = false;
    private static w b;
    private static x c;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: BUGLY */
    /* loaded from: classes.dex */
    public class a extends Thread {
        private int b = 4;
        private v c = null;
        private String d;
        private ContentValues e;
        private boolean f;
        private String[] g;
        private String h;
        private String[] i;
        private String j;
        private String k;
        private String l;
        private String m;
        private String n;
        private String[] o;
        private int p;
        private String q;
        private byte[] r;

        public a() {
        }

        public final void a(int i, String str, byte[] bArr) {
            this.p = i;
            this.q = str;
            this.r = bArr;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public final void run() {
            switch (this.b) {
                case 1:
                    w.this.a(this.d, this.e, this.c);
                    return;
                case 2:
                    w.this.a(this.d, this.n, this.o, this.c);
                    return;
                case 3:
                    Cursor a = w.this.a(this.f, this.d, this.g, this.h, this.i, this.j, this.k, this.l, this.m, this.c);
                    if (a != null) {
                        a.close();
                        return;
                    }
                    return;
                case 4:
                    w.this.a(this.p, this.q, this.r, this.c);
                    return;
                case 5:
                    w.this.a(this.p, this.c);
                    return;
                case 6:
                    w.this.a(this.p, this.q, this.c);
                    return;
                default:
                    return;
            }
        }
    }

    private w(Context context, List<o> list) {
        c = new x(context, list);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0033, code lost:
    
        if (r0 != null) goto L12;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public synchronized int a(java.lang.String r4, java.lang.String r5, java.lang.String[] r6, com.tencent.bugly.proguard.v r7) {
        /*
            r3 = this;
            monitor-enter(r3)
            r0 = 0
            r1 = 0
            com.tencent.bugly.proguard.x r2 = com.tencent.bugly.proguard.w.c     // Catch: java.lang.Throwable -> L1e java.lang.Throwable -> L20
            android.database.sqlite.SQLiteDatabase r0 = r2.getWritableDatabase()     // Catch: java.lang.Throwable -> L1e java.lang.Throwable -> L20
            if (r0 == 0) goto Lf
            int r1 = r0.delete(r4, r5, r6)     // Catch: java.lang.Throwable -> L1e java.lang.Throwable -> L20
        Lf:
            if (r7 == 0) goto L14
            java.lang.Integer.valueOf(r1)     // Catch: java.lang.Throwable -> L47
        L14:
            boolean r4 = com.tencent.bugly.proguard.w.a     // Catch: java.lang.Throwable -> L47
            if (r4 == 0) goto L36
            if (r0 == 0) goto L36
        L1a:
            r0.close()     // Catch: java.lang.Throwable -> L47
            goto L36
        L1e:
            r4 = move-exception
            goto L38
        L20:
            r4 = move-exception
            boolean r5 = com.tencent.bugly.proguard.al.a(r4)     // Catch: java.lang.Throwable -> L1e
            if (r5 != 0) goto L2a
            r4.printStackTrace()     // Catch: java.lang.Throwable -> L1e
        L2a:
            if (r7 == 0) goto L2f
            java.lang.Integer.valueOf(r1)     // Catch: java.lang.Throwable -> L47
        L2f:
            boolean r4 = com.tencent.bugly.proguard.w.a     // Catch: java.lang.Throwable -> L47
            if (r4 == 0) goto L36
            if (r0 == 0) goto L36
            goto L1a
        L36:
            monitor-exit(r3)
            return r1
        L38:
            if (r7 == 0) goto L3d
            java.lang.Integer.valueOf(r1)     // Catch: java.lang.Throwable -> L47
        L3d:
            boolean r5 = com.tencent.bugly.proguard.w.a     // Catch: java.lang.Throwable -> L47
            if (r5 == 0) goto L46
            if (r0 == 0) goto L46
            r0.close()     // Catch: java.lang.Throwable -> L47
        L46:
            throw r4     // Catch: java.lang.Throwable -> L47
        L47:
            r4 = move-exception
            monitor-exit(r3)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.w.a(java.lang.String, java.lang.String, java.lang.String[], com.tencent.bugly.proguard.v):int");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized Cursor a(boolean z, String str, String[] strArr, String str2, String[] strArr2, String str3, String str4, String str5, String str6, v vVar) {
        Cursor cursor;
        cursor = null;
        try {
            try {
                SQLiteDatabase writableDatabase = c.getWritableDatabase();
                if (writableDatabase != null) {
                    cursor = writableDatabase.query(z, str, strArr, str2, strArr2, str3, str4, str5, str6);
                }
            } catch (Throwable th) {
                if (!al.a(th)) {
                    th.printStackTrace();
                }
            }
        } catch (Throwable th2) {
            throw th2;
        }
        return cursor;
    }

    public static synchronized w a() {
        w wVar;
        synchronized (w.class) {
            wVar = b;
        }
        return wVar;
    }

    public static synchronized w a(Context context, List<o> list) {
        w wVar;
        synchronized (w.class) {
            if (b == null) {
                b = new w(context, list);
            }
            wVar = b;
        }
        return wVar;
    }

    private static y a(Cursor cursor) {
        if (cursor == null) {
            return null;
        }
        try {
            y yVar = new y();
            yVar.a = cursor.getLong(cursor.getColumnIndex("_id"));
            yVar.b = cursor.getInt(cursor.getColumnIndex("_tp"));
            yVar.c = cursor.getString(cursor.getColumnIndex("_pc"));
            yVar.d = cursor.getString(cursor.getColumnIndex("_th"));
            yVar.e = cursor.getLong(cursor.getColumnIndex("_tm"));
            yVar.g = cursor.getBlob(cursor.getColumnIndex("_dt"));
            return yVar;
        } catch (Throwable th) {
            if (!al.a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized boolean a(int i, String str, v vVar) {
        boolean z;
        SQLiteDatabase sQLiteDatabase;
        String str2;
        SQLiteDatabase sQLiteDatabase2 = null;
        z = false;
        try {
            try {
                sQLiteDatabase = c.getWritableDatabase();
                if (sQLiteDatabase != null) {
                    try {
                        if (ap.b(str)) {
                            str2 = "_id = ".concat(String.valueOf(i));
                        } else {
                            str2 = "_id = " + i + " and _tp = \"" + str + "\"";
                        }
                        int delete = sQLiteDatabase.delete("t_pf", str2, null);
                        al.c("[Database] deleted %s data %d", "t_pf", Integer.valueOf(delete));
                        if (delete > 0) {
                            z = true;
                        }
                    } catch (Throwable th) {
                        th = th;
                        sQLiteDatabase2 = sQLiteDatabase;
                        if (!al.a(th)) {
                            th.printStackTrace();
                        }
                        if (vVar != null) {
                            Boolean bool = Boolean.FALSE;
                        }
                        if (a && sQLiteDatabase2 != null) {
                            sQLiteDatabase2.close();
                        }
                        return z;
                    }
                }
                if (vVar != null) {
                    Boolean.valueOf(z);
                }
                if (a && sQLiteDatabase != null) {
                    sQLiteDatabase.close();
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Throwable th3) {
            th = th3;
            sQLiteDatabase = sQLiteDatabase2;
        }
        return z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean a(int i, String str, byte[] bArr, v vVar) {
        try {
            try {
                y yVar = new y();
                yVar.a = i;
                yVar.f = str;
                yVar.e = System.currentTimeMillis();
                yVar.g = bArr;
                boolean b2 = b(yVar);
                if (vVar == null) {
                    return b2;
                }
                Boolean.valueOf(b2);
                return b2;
            } catch (Throwable th) {
                if (!al.a(th)) {
                    th.printStackTrace();
                }
                if (vVar != null) {
                    Boolean bool = Boolean.FALSE;
                }
                return false;
            }
        } catch (Throwable th2) {
            if (vVar != null) {
                Boolean bool2 = Boolean.FALSE;
            }
            throw th2;
        }
    }

    private static y b(Cursor cursor) {
        if (cursor == null) {
            return null;
        }
        try {
            y yVar = new y();
            yVar.a = cursor.getLong(cursor.getColumnIndex("_id"));
            yVar.e = cursor.getLong(cursor.getColumnIndex("_tm"));
            yVar.f = cursor.getString(cursor.getColumnIndex("_tp"));
            yVar.g = cursor.getBlob(cursor.getColumnIndex("_dt"));
            return yVar;
        } catch (Throwable th) {
            if (!al.a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    private synchronized boolean b(y yVar) {
        ContentValues d;
        SQLiteDatabase sQLiteDatabase = null;
        try {
            try {
                sQLiteDatabase = c.getWritableDatabase();
                if (sQLiteDatabase == null || (d = d(yVar)) == null) {
                    if (a && sQLiteDatabase != null) {
                        sQLiteDatabase.close();
                    }
                    return false;
                }
                long replace = sQLiteDatabase.replace("t_pf", "_id", d);
                if (replace < 0) {
                    if (a && sQLiteDatabase != null) {
                        sQLiteDatabase.close();
                    }
                    return false;
                }
                al.c("[Database] insert %s success.", "t_pf");
                yVar.a = replace;
                if (a && sQLiteDatabase != null) {
                    sQLiteDatabase.close();
                }
                return true;
            } catch (Throwable th) {
                if (!al.a(th)) {
                    th.printStackTrace();
                }
                if (a && sQLiteDatabase != null) {
                    sQLiteDatabase.close();
                }
                return false;
            }
        } catch (Throwable th2) {
            if (a && sQLiteDatabase != null) {
                sQLiteDatabase.close();
            }
            throw th2;
        }
    }

    private static ContentValues c(y yVar) {
        if (yVar == null) {
            return null;
        }
        try {
            ContentValues contentValues = new ContentValues();
            if (yVar.a > 0) {
                contentValues.put("_id", Long.valueOf(yVar.a));
            }
            contentValues.put("_tp", Integer.valueOf(yVar.b));
            contentValues.put("_pc", yVar.c);
            contentValues.put("_th", yVar.d);
            contentValues.put("_tm", Long.valueOf(yVar.e));
            if (yVar.g != null) {
                contentValues.put("_dt", yVar.g);
            }
            return contentValues;
        } catch (Throwable th) {
            if (!al.a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x00b3, code lost:
    
        r1.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x00d0, code lost:
    
        if (r1 != null) goto L46;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x00b1, code lost:
    
        if (r1 != null) goto L46;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private synchronized java.util.List<com.tencent.bugly.proguard.y> c(int r12) {
        /*
            Method dump skipped, instructions count: 232
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.w.c(int):java.util.List");
    }

    private static ContentValues d(y yVar) {
        if (yVar != null && !ap.b(yVar.f)) {
            try {
                ContentValues contentValues = new ContentValues();
                if (yVar.a > 0) {
                    contentValues.put("_id", Long.valueOf(yVar.a));
                }
                contentValues.put("_tp", yVar.f);
                contentValues.put("_tm", Long.valueOf(yVar.e));
                if (yVar.g != null) {
                    contentValues.put("_dt", yVar.g);
                }
                return contentValues;
            } catch (Throwable th) {
                if (!al.a(th)) {
                    th.printStackTrace();
                }
            }
        }
        return null;
    }

    public final int a(String str, String str2) {
        return a(str, str2, (String[]) null, (v) null);
    }

    /* JADX WARN: Code restructure failed: missing block: B:30:0x0054, code lost:
    
        if (0 != 0) goto L17;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final synchronized long a(java.lang.String r10, android.content.ContentValues r11, com.tencent.bugly.proguard.v r12) {
        /*
            r9 = this;
            monitor-enter(r9)
            r0 = 0
            r1 = -1
            com.tencent.bugly.proguard.x r3 = com.tencent.bugly.proguard.w.c     // Catch: java.lang.Throwable -> L3f java.lang.Throwable -> L41
            android.database.sqlite.SQLiteDatabase r0 = r3.getWritableDatabase()     // Catch: java.lang.Throwable -> L3f java.lang.Throwable -> L41
            if (r0 == 0) goto L30
            if (r11 == 0) goto L30
            java.lang.String r3 = "_id"
            long r3 = r0.replace(r10, r3, r11)     // Catch: java.lang.Throwable -> L3f java.lang.Throwable -> L41
            r5 = 0
            r11 = 0
            r7 = 1
            int r8 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r8 < 0) goto L26
            java.lang.String r5 = "[Database] insert %s success."
            java.lang.Object[] r6 = new java.lang.Object[r7]     // Catch: java.lang.Throwable -> L3f java.lang.Throwable -> L41
            r6[r11] = r10     // Catch: java.lang.Throwable -> L3f java.lang.Throwable -> L41
            com.tencent.bugly.proguard.al.c(r5, r6)     // Catch: java.lang.Throwable -> L3f java.lang.Throwable -> L41
            goto L2f
        L26:
            java.lang.String r5 = "[Database] replace %s error."
            java.lang.Object[] r6 = new java.lang.Object[r7]     // Catch: java.lang.Throwable -> L3f java.lang.Throwable -> L41
            r6[r11] = r10     // Catch: java.lang.Throwable -> L3f java.lang.Throwable -> L41
            com.tencent.bugly.proguard.al.d(r5, r6)     // Catch: java.lang.Throwable -> L3f java.lang.Throwable -> L41
        L2f:
            r1 = r3
        L30:
            if (r12 == 0) goto L35
            java.lang.Long.valueOf(r1)     // Catch: java.lang.Throwable -> L68
        L35:
            boolean r10 = com.tencent.bugly.proguard.w.a     // Catch: java.lang.Throwable -> L68
            if (r10 == 0) goto L57
            if (r0 == 0) goto L57
        L3b:
            r0.close()     // Catch: java.lang.Throwable -> L68
            goto L57
        L3f:
            r10 = move-exception
            goto L59
        L41:
            r10 = move-exception
            boolean r11 = com.tencent.bugly.proguard.al.a(r10)     // Catch: java.lang.Throwable -> L3f
            if (r11 != 0) goto L4b
            r10.printStackTrace()     // Catch: java.lang.Throwable -> L3f
        L4b:
            if (r12 == 0) goto L50
            java.lang.Long.valueOf(r1)     // Catch: java.lang.Throwable -> L68
        L50:
            boolean r10 = com.tencent.bugly.proguard.w.a     // Catch: java.lang.Throwable -> L68
            if (r10 == 0) goto L57
            if (r0 == 0) goto L57
            goto L3b
        L57:
            monitor-exit(r9)
            return r1
        L59:
            if (r12 == 0) goto L5e
            java.lang.Long.valueOf(r1)     // Catch: java.lang.Throwable -> L68
        L5e:
            boolean r11 = com.tencent.bugly.proguard.w.a     // Catch: java.lang.Throwable -> L68
            if (r11 == 0) goto L67
            if (r0 == 0) goto L67
            r0.close()     // Catch: java.lang.Throwable -> L68
        L67:
            throw r10     // Catch: java.lang.Throwable -> L68
        L68:
            r10 = move-exception
            monitor-exit(r9)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.w.a(java.lang.String, android.content.ContentValues, com.tencent.bugly.proguard.v):long");
    }

    public final Cursor a(String str, String[] strArr, String str2) {
        return a(str, strArr, str2, (String) null, (String) null);
    }

    public final Cursor a(String str, String[] strArr, String str2, String str3, String str4) {
        return a(false, str, strArr, str2, null, null, null, str3, str4, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:51:0x00b6 A[Catch: all -> 0x00c8, TRY_LEAVE, TryCatch #3 {all -> 0x00c8, blocks: (B:20:0x0041, B:21:0x004b, B:23:0x0052, B:33:0x0058, B:26:0x005c, B:30:0x006f, B:36:0x0077, B:38:0x0081, B:49:0x00b0, B:51:0x00b6), top: B:19:0x0041 }] */
    /* JADX WARN: Removed duplicated region for block: B:54:0x00bb A[Catch: all -> 0x00da, TRY_ENTER, TryCatch #5 {, blocks: (B:3:0x0001, B:11:0x0033, B:12:0x0036, B:15:0x003c, B:41:0x00a1, B:42:0x00a4, B:45:0x00aa, B:54:0x00bb, B:55:0x00be, B:58:0x00c4, B:61:0x00cb, B:62:0x00ce, B:65:0x00d4, B:66:0x00d7), top: B:2:0x0001 }] */
    /* JADX WARN: Removed duplicated region for block: B:61:0x00cb A[Catch: all -> 0x00da, TryCatch #5 {, blocks: (B:3:0x0001, B:11:0x0033, B:12:0x0036, B:15:0x003c, B:41:0x00a1, B:42:0x00a4, B:45:0x00aa, B:54:0x00bb, B:55:0x00be, B:58:0x00c4, B:61:0x00cb, B:62:0x00ce, B:65:0x00d4, B:66:0x00d7), top: B:2:0x0001 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final synchronized java.util.List<com.tencent.bugly.proguard.y> a(int r11) {
        /*
            Method dump skipped, instructions count: 221
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.w.a(int):java.util.List");
    }

    public final Map<String, byte[]> a(int i, v vVar) {
        HashMap hashMap = null;
        try {
            List<y> c2 = c(i);
            if (c2 == null) {
                return null;
            }
            HashMap hashMap2 = new HashMap();
            try {
                for (y yVar : c2) {
                    byte[] bArr = yVar.g;
                    if (bArr != null) {
                        hashMap2.put(yVar.f, bArr);
                    }
                }
                return hashMap2;
            } catch (Throwable th) {
                th = th;
                hashMap = hashMap2;
                if (al.a(th)) {
                    return hashMap;
                }
                th.printStackTrace();
                return hashMap;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public final synchronized void a(List<y> list) {
        if (list != null) {
            if (list.size() != 0) {
                SQLiteDatabase writableDatabase = c.getWritableDatabase();
                if (writableDatabase != null) {
                    StringBuilder sb = new StringBuilder();
                    for (y yVar : list) {
                        sb.append(" or _id = ");
                        sb.append(yVar.a);
                    }
                    String sb2 = sb.toString();
                    if (sb2.length() > 0) {
                        sb2 = sb2.substring(4);
                    }
                    sb.setLength(0);
                    try {
                        try {
                            al.c("[Database] deleted %s data %d", "t_lr", Integer.valueOf(writableDatabase.delete("t_lr", sb2, null)));
                        } catch (Throwable th) {
                            if (!al.a(th)) {
                                th.printStackTrace();
                            }
                            if (a) {
                                writableDatabase.close();
                            }
                        }
                    } finally {
                        if (a) {
                            writableDatabase.close();
                        }
                    }
                }
            }
        }
    }

    public final boolean a(int i, String str, byte[] bArr, boolean z) {
        if (z) {
            return a(i, str, bArr, (v) null);
        }
        a aVar = new a();
        aVar.a(i, str, bArr);
        ak.a().a(aVar);
        return true;
    }

    public final synchronized boolean a(y yVar) {
        ContentValues c2;
        SQLiteDatabase sQLiteDatabase = null;
        try {
            try {
                sQLiteDatabase = c.getWritableDatabase();
                if (sQLiteDatabase == null || (c2 = c(yVar)) == null) {
                    if (a && sQLiteDatabase != null) {
                        sQLiteDatabase.close();
                    }
                    return false;
                }
                long replace = sQLiteDatabase.replace("t_lr", "_id", c2);
                if (replace < 0) {
                    if (a && sQLiteDatabase != null) {
                        sQLiteDatabase.close();
                    }
                    return false;
                }
                al.c("[Database] insert %s success.", "t_lr");
                yVar.a = replace;
                if (a && sQLiteDatabase != null) {
                    sQLiteDatabase.close();
                }
                return true;
            } catch (Throwable th) {
                if (!al.a(th)) {
                    th.printStackTrace();
                }
                if (a && sQLiteDatabase != null) {
                    sQLiteDatabase.close();
                }
                return false;
            }
        } catch (Throwable th2) {
            if (a && sQLiteDatabase != null) {
                sQLiteDatabase.close();
            }
            throw th2;
        }
    }

    public final synchronized void b(int i) {
        String concat;
        SQLiteDatabase writableDatabase = c.getWritableDatabase();
        if (writableDatabase != null) {
            try {
                if (i >= 0) {
                    try {
                        concat = "_tp = ".concat(String.valueOf(i));
                    } catch (Throwable th) {
                        if (!al.a(th)) {
                            th.printStackTrace();
                        }
                        if (a && writableDatabase != null) {
                            writableDatabase.close();
                            return;
                        }
                    }
                } else {
                    concat = null;
                }
                al.c("[Database] deleted %s data %d", "t_lr", Integer.valueOf(writableDatabase.delete("t_lr", concat, null)));
                if (a && writableDatabase != null) {
                    writableDatabase.close();
                }
            } catch (Throwable th2) {
                if (a && writableDatabase != null) {
                    writableDatabase.close();
                }
                throw th2;
            }
        }
    }
}
