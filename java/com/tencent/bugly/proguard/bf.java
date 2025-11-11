package com.tencent.bugly.proguard;

import android.os.Handler;
import android.os.SystemClock;
import com.yj.coffeemachines.app.serialport.ShellUtils;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/* compiled from: BUGLY */
/* loaded from: classes.dex */
public final class bf implements Runnable {
    final Handler a;
    long d;
    private final String e;
    private final List<ba> f = new LinkedList();
    long b = 5000;
    private final long g = 5000;
    boolean c = true;

    /* JADX INFO: Access modifiers changed from: package-private */
    public bf(Handler handler, String str) {
        this.a = handler;
        this.e = str;
    }

    private Thread e() {
        return this.a.getLooper().getThread();
    }

    public final boolean a() {
        return !this.c && SystemClock.uptimeMillis() >= this.d + this.b;
    }

    public final long b() {
        return SystemClock.uptimeMillis() - this.d;
    }

    public final List<ba> c() {
        ArrayList arrayList;
        long currentTimeMillis = System.currentTimeMillis();
        synchronized (this.f) {
            arrayList = new ArrayList(this.f.size());
            for (int i = 0; i < this.f.size(); i++) {
                ba baVar = this.f.get(i);
                if (!baVar.e && currentTimeMillis - baVar.b < 200000) {
                    arrayList.add(baVar);
                    baVar.e = true;
                }
            }
        }
        return arrayList;
    }

    public final void d() {
        StringBuilder sb = new StringBuilder(1024);
        long nanoTime = System.nanoTime();
        try {
            StackTraceElement[] stackTrace = e().getStackTrace();
            if (stackTrace.length == 0) {
                sb.append("Thread does not have stack trace.\n");
            } else {
                for (StackTraceElement stackTraceElement : stackTrace) {
                    sb.append(stackTraceElement);
                    sb.append(ShellUtils.COMMAND_LINE_END);
                }
            }
        } catch (SecurityException e) {
            sb.append("getStackTrace() encountered:\n");
            sb.append(e.getMessage());
            sb.append(ShellUtils.COMMAND_LINE_END);
            al.a(e);
        }
        long nanoTime2 = System.nanoTime();
        ba baVar = new ba(sb.toString(), System.currentTimeMillis());
        baVar.d = nanoTime2 - nanoTime;
        String name = e().getName();
        if (name == null) {
            name = "";
        }
        baVar.a = name;
        synchronized (this.f) {
            while (this.f.size() >= 32) {
                this.f.remove(0);
            }
            this.f.add(baVar);
        }
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.c = true;
        this.b = this.g;
    }
}
