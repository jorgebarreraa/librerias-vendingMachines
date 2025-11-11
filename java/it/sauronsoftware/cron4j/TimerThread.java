package it.sauronsoftware.cron4j;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class TimerThread extends Thread {
    private String guid = GUIDGenerator.generate();
    private Scheduler scheduler;

    public TimerThread(Scheduler scheduler) {
        this.scheduler = scheduler;
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("cron4j::scheduler[");
        stringBuffer.append(scheduler.getGuid());
        stringBuffer.append("]::timer[");
        stringBuffer.append(this.guid);
        stringBuffer.append("]");
        setName(stringBuffer.toString());
    }

    private void safeSleep(long j) throws InterruptedException {
        long j2 = 0;
        do {
            long currentTimeMillis = System.currentTimeMillis();
            sleep(j - j2);
            j2 += System.currentTimeMillis() - currentTimeMillis;
        } while (j2 < j);
    }

    public Object getGuid() {
        return this.guid;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        long currentTimeMillis = System.currentTimeMillis() / 60000;
        while (true) {
            long currentTimeMillis2 = ((currentTimeMillis + 1) * 60000) - System.currentTimeMillis();
            if (currentTimeMillis2 > 0) {
                try {
                    safeSleep(currentTimeMillis2);
                } catch (InterruptedException unused) {
                    this.scheduler = null;
                    return;
                }
            }
            long currentTimeMillis3 = System.currentTimeMillis();
            this.scheduler.spawnLauncher(currentTimeMillis3);
            currentTimeMillis = currentTimeMillis3 / 60000;
        }
    }
}
