package it.sauronsoftware.cron4j;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class RunnableTask extends Task {
    private Runnable runnable;

    public RunnableTask(Runnable runnable) throws InvalidPatternException {
        this.runnable = runnable;
    }

    @Override // it.sauronsoftware.cron4j.Task
    public void execute(TaskExecutionContext taskExecutionContext) {
        this.runnable.run();
    }

    public Runnable getRunnable() {
        return this.runnable;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Task[");
        stringBuffer.append("runnable=");
        stringBuffer.append(this.runnable);
        stringBuffer.append("]");
        return stringBuffer.toString();
    }
}
