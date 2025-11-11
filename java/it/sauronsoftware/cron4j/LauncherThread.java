package it.sauronsoftware.cron4j;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class LauncherThread extends Thread {
    private TaskCollector[] collectors;
    private String guid = GUIDGenerator.generate();
    private long referenceTimeInMillis;
    private Scheduler scheduler;

    public LauncherThread(Scheduler scheduler, TaskCollector[] taskCollectorArr, long j) {
        this.scheduler = scheduler;
        this.collectors = taskCollectorArr;
        this.referenceTimeInMillis = j;
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("cron4j::scheduler[");
        stringBuffer.append(scheduler.getGuid());
        stringBuffer.append("]::launcher[");
        stringBuffer.append(this.guid);
        stringBuffer.append("]");
        setName(stringBuffer.toString());
    }

    public Object getGuid() {
        return this.guid;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        int i = 0;
        loop0: while (true) {
            TaskCollector[] taskCollectorArr = this.collectors;
            if (i >= taskCollectorArr.length) {
                break;
            }
            TaskTable tasks = taskCollectorArr[i].getTasks();
            int size = tasks.size();
            for (int i2 = 0; i2 < size; i2++) {
                if (isInterrupted()) {
                    break loop0;
                }
                if (tasks.getSchedulingPattern(i2).match(this.scheduler.getTimeZone(), this.referenceTimeInMillis)) {
                    this.scheduler.spawnExecutor(tasks.getTask(i2));
                }
            }
            i++;
        }
        this.scheduler.notifyLauncherCompleted(this);
    }
}
