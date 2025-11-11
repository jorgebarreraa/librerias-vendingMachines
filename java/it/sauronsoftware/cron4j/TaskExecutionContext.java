package it.sauronsoftware.cron4j;

/* loaded from: classes2.dex */
public interface TaskExecutionContext {
    Scheduler getScheduler();

    TaskExecutor getTaskExecutor();

    boolean isStopped();

    void pauseIfRequested();

    void setCompleteness(double d);

    void setStatusMessage(String str);
}
