package it.sauronsoftware.cron4j;

/* loaded from: classes2.dex */
public interface SchedulerListener {
    void taskFailed(TaskExecutor taskExecutor, Throwable th);

    void taskLaunching(TaskExecutor taskExecutor);

    void taskSucceeded(TaskExecutor taskExecutor);
}
