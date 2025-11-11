package com.tencent.bugly.proguard;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.os.FileObserver;
import android.text.TextUtils;
import androidx.work.WorkRequest;
import com.tencent.bugly.crashreport.crash.CrashDetailBean;
import com.tencent.bugly.crashreport.crash.anr.TraceFileHelper;
import com.tencent.bugly.crashreport.crash.jni.NativeCrashHandler;
import com.yj.coffeemachines.app.serialport.ShellUtils;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
// import org.eclipse.paho.android.service.MqttServiceConstants; // Eliminado: clase privada
import org.eclipse.paho.client.mqttv3.MqttTopic;

/* compiled from: BUGLY */
/* loaded from: classes.dex */
public final class ay {
    public static ay f;
    public final ActivityManager b;
    final aa c;
    final ak d;
    String e;
    private final Context g;
    private final ac h;
    private final as i;
    private FileObserver k;
    private bg m;
    private int n;
    public final AtomicBoolean a = new AtomicBoolean(false);
    private final Object j = new Object();
    private boolean l = true;
    private long o = 0;

    public ay(Context context, ac acVar, aa aaVar, ak akVar, as asVar) {
        this.g = ap.a(context);
        this.b = (ActivityManager) this.g.getSystemService("activity");
        if (ap.b(NativeCrashHandler.getDumpFilePath())) {
            this.e = context.getDir("bugly", 0).getAbsolutePath();
        } else {
            this.e = NativeCrashHandler.getDumpFilePath();
        }
        this.c = aaVar;
        this.d = akVar;
        this.h = acVar;
        this.i = asVar;
    }

    private CrashDetailBean a(ax axVar) {
        CrashDetailBean crashDetailBean = new CrashDetailBean();
        try {
            crashDetailBean.C = ab.j();
            crashDetailBean.D = ab.f();
            crashDetailBean.E = ab.l();
            crashDetailBean.F = this.c.k();
            crashDetailBean.G = this.c.j();
            crashDetailBean.H = this.c.l();
            crashDetailBean.I = ab.b(this.g);
            crashDetailBean.J = ab.g();
            crashDetailBean.K = ab.h();
            crashDetailBean.b = 3;
            crashDetailBean.e = this.c.g();
            crashDetailBean.f = this.c.o;
            crashDetailBean.g = this.c.q();
            crashDetailBean.m = this.c.f();
            crashDetailBean.n = "ANR_EXCEPTION";
            crashDetailBean.o = axVar.f;
            crashDetailBean.q = axVar.g;
            crashDetailBean.T = new HashMap();
            crashDetailBean.T.put("BUGLY_CR_01", axVar.e);
            int indexOf = crashDetailBean.q != null ? crashDetailBean.q.indexOf(ShellUtils.COMMAND_LINE_END) : -1;
            crashDetailBean.p = indexOf > 0 ? crashDetailBean.q.substring(0, indexOf) : "GET_FAIL";
            crashDetailBean.r = axVar.c;
            if (crashDetailBean.q != null) {
                crashDetailBean.u = ap.c(crashDetailBean.q.getBytes());
            }
            crashDetailBean.z = axVar.b;
            crashDetailBean.A = axVar.a;
            crashDetailBean.B = "main(1)";
            crashDetailBean.L = this.c.s();
            crashDetailBean.h = this.c.p();
            crashDetailBean.i = this.c.A();
            crashDetailBean.v = axVar.d;
            crashDetailBean.P = this.c.u;
            crashDetailBean.Q = this.c.a;
            crashDetailBean.R = this.c.a();
            crashDetailBean.U = this.c.z();
            crashDetailBean.V = this.c.x;
            crashDetailBean.W = this.c.t();
            crashDetailBean.X = this.c.y();
            crashDetailBean.y = ao.a();
        } catch (Throwable th) {
            if (!al.a(th)) {
                th.printStackTrace();
            }
        }
        return crashDetailBean;
    }

    public static synchronized ay a() {
        ay ayVar;
        synchronized (ay.class) {
            ayVar = f;
        }
        return ayVar;
    }

    private static String a(List<ba> list, long j) {
        if (list == null || list.isEmpty()) {
            return "main thread stack not enable";
        }
        StringBuilder sb = new StringBuilder(4096);
        sb.append("\n>>>>> 以下为anr过程中主线程堆栈记录，可根据堆栈出现次数推测在该堆栈阻塞的时间，出现次数越多对anr贡献越大，越可能是造成anr的原因 >>>>>\n");
        sb.append("\n>>>>> Thread Stack Traces Records Start >>>>>\n");
        for (int i = 0; i < list.size(); i++) {
            ba baVar = list.get(i);
            sb.append("Thread name:");
            sb.append(baVar.a);
            sb.append(ShellUtils.COMMAND_LINE_END);
            long j2 = baVar.b - j;
            String str = j2 <= 0 ? "before " : "after ";
            sb.append("Got ");
            sb.append(str);
            sb.append("anr:");
            sb.append(Math.abs(j2));
            sb.append("ms\n");
            sb.append(baVar.c);
            sb.append(ShellUtils.COMMAND_LINE_END);
            if (sb.length() * 2 >= 101376) {
                break;
            }
        }
        sb.append("\n<<<<< Thread Stack Traces Records End <<<<<\n");
        return sb.toString();
    }

    static /* synthetic */ void a(ay ayVar) {
        long currentTimeMillis = (at.j + System.currentTimeMillis()) - ap.b();
        am.a(ayVar.e, "bugly_trace_", ".txt", currentTimeMillis);
        am.a(ayVar.e, "manual_bugly_trace_", ".txt", currentTimeMillis);
        am.a(ayVar.e, "main_stack_record_", ".txt", currentTimeMillis);
        am.a(ayVar.e, "main_stack_record_", ".txt.merged", currentTimeMillis);
    }

    private static boolean a(String str, String str2, String str3) {
        TraceFileHelper.a readTargetDumpInfo = TraceFileHelper.readTargetDumpInfo(str3, str, true);
        if (readTargetDumpInfo == null || readTargetDumpInfo.d == null || readTargetDumpInfo.d.isEmpty()) {
            al.e("not found trace dump for %s", str3);
            return false;
        }
        StringBuilder sb = new StringBuilder(1024);
        String[] strArr = readTargetDumpInfo.d.get("main");
        if (strArr != null && strArr.length >= 3) {
            sb.append("\"main\" tid=");
            sb.append(strArr[2]);
            sb.append(" :\n");
            sb.append(strArr[0]);
            sb.append(ShellUtils.COMMAND_LINE_END);
            sb.append(strArr[1]);
            sb.append("\n\n");
        }
        for (Map.Entry<String, String[]> entry : readTargetDumpInfo.d.entrySet()) {
            if (!entry.getKey().equals("main") && entry.getValue() != null && entry.getValue().length >= 3) {
                sb.append("\"");
                sb.append(entry.getKey());
                sb.append("\" tid=");
                sb.append(entry.getValue()[2]);
                sb.append(" :\n");
                sb.append(entry.getValue()[0]);
                sb.append(ShellUtils.COMMAND_LINE_END);
                sb.append(entry.getValue()[1]);
                sb.append("\n\n");
            }
        }
        return am.a(str2, sb.toString(), sb.length() * 2);
    }

    private synchronized void c() {
        if (e()) {
            al.d("start when started!", new Object[0]);
            return;
        }
        this.k = new FileObserver("/data/anr/") { // from class: com.tencent.bugly.proguard.ay.1
            @Override // android.os.FileObserver
            public final void onEvent(int i, String str) {
                if (str == null) {
                    return;
                }
                final String concat = "/data/anr/".concat(String.valueOf(str));
                al.d("watching file %s", concat);
                if (concat.contains(MqttServiceConstants.TRACE_ACTION)) {
                    ay.this.d.a(new Runnable() { // from class: com.tencent.bugly.proguard.ay.1.1
                        @Override // java.lang.Runnable
                        public final void run() {
                            ay ayVar = ay.this;
                            String str2 = concat;
                            if (ayVar.a(true)) {
                                try {
                                    al.c("read trace first dump for create time!", new Object[0]);
                                    TraceFileHelper.a readFirstDumpInfo = TraceFileHelper.readFirstDumpInfo(str2, false);
                                    long j = readFirstDumpInfo != null ? readFirstDumpInfo.c : -1L;
                                    if (j == -1) {
                                        al.d("trace dump fail could not get time!", new Object[0]);
                                        j = System.currentTimeMillis();
                                    }
                                    if (ayVar.a(j)) {
                                        return;
                                    }
                                    ayVar.a(j, str2);
                                } catch (Throwable th) {
                                    if (!al.a(th)) {
                                        th.printStackTrace();
                                    }
                                    al.e("handle anr error %s", th.getClass().toString());
                                }
                            }
                        }
                    });
                } else {
                    al.d("not anr file %s", concat);
                }
            }
        };
        try {
            this.k.startWatching();
            al.a("start anr monitor!", new Object[0]);
            this.d.a(new Runnable() { // from class: com.tencent.bugly.proguard.ay.2
                @Override // java.lang.Runnable
                public final void run() {
                    ay.a(ay.this);
                }
            });
        } catch (Throwable th) {
            this.k = null;
            al.d("start anr monitor failed!", new Object[0]);
            if (al.a(th)) {
                return;
            }
            th.printStackTrace();
        }
    }

    private synchronized void c(boolean z) {
        if (Build.VERSION.SDK_INT <= 19) {
            if (z) {
                c();
                return;
            } else {
                d();
                return;
            }
        }
        if (z) {
            g();
        } else {
            h();
        }
    }

    private synchronized void d() {
        if (!e()) {
            al.d("close when closed!", new Object[0]);
            return;
        }
        try {
            this.k.stopWatching();
            this.k = null;
            al.d("close anr monitor!", new Object[0]);
        } catch (Throwable th) {
            al.d("stop anr monitor failed!", new Object[0]);
            if (al.a(th)) {
                return;
            }
            th.printStackTrace();
        }
    }

    private synchronized void d(boolean z) {
        if (this.l != z) {
            al.a("user change anr %b", Boolean.valueOf(z));
            this.l = z;
        }
    }

    private synchronized boolean e() {
        return this.k != null;
    }

    private synchronized boolean f() {
        return this.l;
    }

    private synchronized void g() {
        if (e()) {
            al.d("start when started!", new Object[0]);
            return;
        }
        if (TextUtils.isEmpty(this.e)) {
            return;
        }
        synchronized (this.j) {
            if (this.m == null || !this.m.isAlive()) {
                this.m = new bg();
                bg bgVar = this.m;
                boolean z = this.c.S;
                bgVar.b = z;
                al.c("set record stack trace enable:".concat(String.valueOf(z)), new Object[0]);
                bg bgVar2 = this.m;
                StringBuilder sb = new StringBuilder("Bugly-ThreadMonitor");
                int i = this.n;
                this.n = i + 1;
                sb.append(i);
                bgVar2.setName(sb.toString());
                this.m.b();
            }
        }
        this.k = new FileObserver(this.e) { // from class: com.tencent.bugly.proguard.ay.3
            @Override // android.os.FileObserver
            public final void onEvent(int i2, String str) {
                if (str == null) {
                    return;
                }
                al.d("observe file, dir:%s fileName:%s", ay.this.e, str);
                if (!(str.startsWith("manual_bugly_trace_") && str.endsWith(".txt"))) {
                    al.c("not manual trace file, ignore.", new Object[0]);
                    return;
                }
                if (!ay.this.a.get()) {
                    al.c("proc is not in anr, just ignore", new Object[0]);
                    return;
                }
                if (ay.this.c.a()) {
                    al.c("Found foreground anr, resend sigquit immediately.", new Object[0]);
                    NativeCrashHandler.getInstance().resendSigquit();
                    long a = am.a(str, "manual_bugly_trace_", ".txt");
                    ay.this.a(a, ay.this.e + MqttTopic.TOPIC_LEVEL_SEPARATOR + str);
                    al.c("Finish handling one anr.", new Object[0]);
                    return;
                }
                al.c("Found background anr, resend sigquit later.", new Object[0]);
                long a2 = am.a(str, "manual_bugly_trace_", ".txt");
                ay.this.a(a2, ay.this.e + MqttTopic.TOPIC_LEVEL_SEPARATOR + str);
                al.c("Finish handling one anr, now resend sigquit.", new Object[0]);
                NativeCrashHandler.getInstance().resendSigquit();
            }
        };
        try {
            this.k.startWatching();
            al.a("startWatchingPrivateAnrDir! dumFilePath is %s", this.e);
            this.d.a(new Runnable() { // from class: com.tencent.bugly.proguard.ay.4
                @Override // java.lang.Runnable
                public final void run() {
                    ay.a(ay.this);
                }
            });
        } catch (Throwable th) {
            this.k = null;
            al.d("startWatchingPrivateAnrDir failed!", new Object[0]);
            if (al.a(th)) {
                return;
            }
            th.printStackTrace();
        }
    }

    private synchronized void h() {
        if (!e()) {
            al.d("close when closed!", new Object[0]);
            return;
        }
        synchronized (this.j) {
            if (this.m != null) {
                this.m.a();
                this.m = null;
            }
        }
        al.a("stopWatchingPrivateAnrDir", new Object[0]);
        try {
            this.k.stopWatching();
            this.k = null;
            al.d("close anr monitor!", new Object[0]);
        } catch (Throwable th) {
            al.d("stop anr monitor failed!", new Object[0]);
            if (al.a(th)) {
                return;
            }
            th.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0068  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0069 A[Catch: all -> 0x0213, Throwable -> 0x0215, Merged into TryCatch #1 {all -> 0x0213, Throwable -> 0x0215, blocks: (B:3:0x0007, B:4:0x0017, B:10:0x0029, B:12:0x0049, B:14:0x004f, B:18:0x0059, B:21:0x0069, B:23:0x007a, B:27:0x0085, B:29:0x008e, B:30:0x0099, B:32:0x009d, B:33:0x00a2, B:35:0x00a6, B:36:0x00ab, B:38:0x00b9, B:39:0x00bd, B:42:0x00ef, B:44:0x010e, B:47:0x011b, B:49:0x0144, B:50:0x0172, B:51:0x0175, B:58:0x0185, B:59:0x0196, B:60:0x01a7, B:62:0x01b8, B:63:0x01c7, B:66:0x01e5, B:68:0x01eb, B:69:0x01f0, B:70:0x01f7, B:81:0x020f, B:83:0x01c0, B:87:0x019f, B:88:0x015c, B:89:0x01a0, B:90:0x00e9, B:93:0x0091, B:95:0x0072, B:99:0x0212, B:104:0x0216), top: B:2:0x0007 }] */
    /* JADX WARN: Removed duplicated region for block: B:23:0x007a A[Catch: all -> 0x0213, Throwable -> 0x0215, Merged into TryCatch #1 {all -> 0x0213, Throwable -> 0x0215, blocks: (B:3:0x0007, B:4:0x0017, B:10:0x0029, B:12:0x0049, B:14:0x004f, B:18:0x0059, B:21:0x0069, B:23:0x007a, B:27:0x0085, B:29:0x008e, B:30:0x0099, B:32:0x009d, B:33:0x00a2, B:35:0x00a6, B:36:0x00ab, B:38:0x00b9, B:39:0x00bd, B:42:0x00ef, B:44:0x010e, B:47:0x011b, B:49:0x0144, B:50:0x0172, B:51:0x0175, B:58:0x0185, B:59:0x0196, B:60:0x01a7, B:62:0x01b8, B:63:0x01c7, B:66:0x01e5, B:68:0x01eb, B:69:0x01f0, B:70:0x01f7, B:81:0x020f, B:83:0x01c0, B:87:0x019f, B:88:0x015c, B:89:0x01a0, B:90:0x00e9, B:93:0x0091, B:95:0x0072, B:99:0x0212, B:104:0x0216), top: B:2:0x0007 }, TRY_LEAVE] */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0085 A[Catch: all -> 0x0213, Throwable -> 0x0215, Merged into TryCatch #1 {all -> 0x0213, Throwable -> 0x0215, blocks: (B:3:0x0007, B:4:0x0017, B:10:0x0029, B:12:0x0049, B:14:0x004f, B:18:0x0059, B:21:0x0069, B:23:0x007a, B:27:0x0085, B:29:0x008e, B:30:0x0099, B:32:0x009d, B:33:0x00a2, B:35:0x00a6, B:36:0x00ab, B:38:0x00b9, B:39:0x00bd, B:42:0x00ef, B:44:0x010e, B:47:0x011b, B:49:0x0144, B:50:0x0172, B:51:0x0175, B:58:0x0185, B:59:0x0196, B:60:0x01a7, B:62:0x01b8, B:63:0x01c7, B:66:0x01e5, B:68:0x01eb, B:69:0x01f0, B:70:0x01f7, B:81:0x020f, B:83:0x01c0, B:87:0x019f, B:88:0x015c, B:89:0x01a0, B:90:0x00e9, B:93:0x0091, B:95:0x0072, B:99:0x0212, B:104:0x0216), top: B:2:0x0007 }, TRY_ENTER] */
    /* JADX WARN: Removed duplicated region for block: B:62:0x01b8 A[Catch: all -> 0x0213, Throwable -> 0x0215, Merged into TryCatch #1 {all -> 0x0213, Throwable -> 0x0215, blocks: (B:3:0x0007, B:4:0x0017, B:10:0x0029, B:12:0x0049, B:14:0x004f, B:18:0x0059, B:21:0x0069, B:23:0x007a, B:27:0x0085, B:29:0x008e, B:30:0x0099, B:32:0x009d, B:33:0x00a2, B:35:0x00a6, B:36:0x00ab, B:38:0x00b9, B:39:0x00bd, B:42:0x00ef, B:44:0x010e, B:47:0x011b, B:49:0x0144, B:50:0x0172, B:51:0x0175, B:58:0x0185, B:59:0x0196, B:60:0x01a7, B:62:0x01b8, B:63:0x01c7, B:66:0x01e5, B:68:0x01eb, B:69:0x01f0, B:70:0x01f7, B:81:0x020f, B:83:0x01c0, B:87:0x019f, B:88:0x015c, B:89:0x01a0, B:90:0x00e9, B:93:0x0091, B:95:0x0072, B:99:0x0212, B:104:0x0216), top: B:2:0x0007 }] */
    /* JADX WARN: Removed duplicated region for block: B:65:0x01e2  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x01eb A[Catch: all -> 0x0213, Throwable -> 0x0215, Merged into TryCatch #1 {all -> 0x0213, Throwable -> 0x0215, blocks: (B:3:0x0007, B:4:0x0017, B:10:0x0029, B:12:0x0049, B:14:0x004f, B:18:0x0059, B:21:0x0069, B:23:0x007a, B:27:0x0085, B:29:0x008e, B:30:0x0099, B:32:0x009d, B:33:0x00a2, B:35:0x00a6, B:36:0x00ab, B:38:0x00b9, B:39:0x00bd, B:42:0x00ef, B:44:0x010e, B:47:0x011b, B:49:0x0144, B:50:0x0172, B:51:0x0175, B:58:0x0185, B:59:0x0196, B:60:0x01a7, B:62:0x01b8, B:63:0x01c7, B:66:0x01e5, B:68:0x01eb, B:69:0x01f0, B:70:0x01f7, B:81:0x020f, B:83:0x01c0, B:87:0x019f, B:88:0x015c, B:89:0x01a0, B:90:0x00e9, B:93:0x0091, B:95:0x0072, B:99:0x0212, B:104:0x0216), top: B:2:0x0007 }] */
    /* JADX WARN: Removed duplicated region for block: B:71:0x01f8 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:82:0x01e4  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x01c0 A[Catch: all -> 0x0213, Throwable -> 0x0215, Merged into TryCatch #1 {all -> 0x0213, Throwable -> 0x0215, blocks: (B:3:0x0007, B:4:0x0017, B:10:0x0029, B:12:0x0049, B:14:0x004f, B:18:0x0059, B:21:0x0069, B:23:0x007a, B:27:0x0085, B:29:0x008e, B:30:0x0099, B:32:0x009d, B:33:0x00a2, B:35:0x00a6, B:36:0x00ab, B:38:0x00b9, B:39:0x00bd, B:42:0x00ef, B:44:0x010e, B:47:0x011b, B:49:0x0144, B:50:0x0172, B:51:0x0175, B:58:0x0185, B:59:0x0196, B:60:0x01a7, B:62:0x01b8, B:63:0x01c7, B:66:0x01e5, B:68:0x01eb, B:69:0x01f0, B:70:0x01f7, B:81:0x020f, B:83:0x01c0, B:87:0x019f, B:88:0x015c, B:89:0x01a0, B:90:0x00e9, B:93:0x0091, B:95:0x0072, B:99:0x0212, B:104:0x0216), top: B:2:0x0007 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void a(long r19, java.lang.String r21) {
        /*
            Method dump skipped, instructions count: 545
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.ay.a(long, java.lang.String):void");
    }

    public final boolean a(long j) {
        if (Math.abs(j - this.o) < WorkRequest.MIN_BACKOFF_MILLIS) {
            al.d("should not process ANR too Fre in %dms", 10000);
            return true;
        }
        this.o = j;
        return false;
    }

    public final boolean a(boolean z) {
        boolean compareAndSet = this.a.compareAndSet(!z, z);
        al.c("tryChangeAnrState to %s, success:%s", Boolean.valueOf(z), Boolean.valueOf(compareAndSet));
        return compareAndSet;
    }

    public final synchronized void b() {
        al.d("customer decides whether to open or close.", new Object[0]);
    }

    public final void b(boolean z) {
        d(z);
        boolean f2 = f();
        ac a = ac.a();
        if (a != null) {
            f2 = f2 && a.c().f;
        }
        if (f2 != e()) {
            al.a("anr changed to %b", Boolean.valueOf(f2));
            c(f2);
        }
    }
}
