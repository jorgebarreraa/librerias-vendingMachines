package it.sauronsoftware.cron4j;

/* loaded from: classes2.dex */
public interface TaskExecutorListener {
    void completenessValueChanged(TaskExecutor taskExecutor, double d);

    void executionPausing(TaskExecutor taskExecutor);

    void executionResuming(TaskExecutor taskExecutor);

    void executionStopping(TaskExecutor taskExecutor);

    void executionTerminated(TaskExecutor taskExecutor, Throwable th);

    void statusMessageChanged(TaskExecutor taskExecutor, String str);
}
