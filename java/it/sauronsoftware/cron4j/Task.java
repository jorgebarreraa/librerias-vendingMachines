package it.sauronsoftware.cron4j;

/* loaded from: classes2.dex */
public abstract class Task {
    private Object id = GUIDGenerator.generate();

    public boolean canBePaused() {
        return false;
    }

    public boolean canBeStopped() {
        return false;
    }

    public abstract void execute(TaskExecutionContext taskExecutionContext) throws RuntimeException;

    Object getId() {
        return this.id;
    }

    public boolean supportsCompletenessTracking() {
        return false;
    }

    public boolean supportsStatusTracking() {
        return false;
    }
}
