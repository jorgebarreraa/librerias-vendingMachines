package it.sauronsoftware.cron4j;

import java.util.ArrayList;

/* loaded from: classes2.dex */
public class TaskTable {
    private int size = 0;
    private ArrayList patterns = new ArrayList();
    private ArrayList tasks = new ArrayList();

    public void add(SchedulingPattern schedulingPattern, Task task) {
        this.patterns.add(schedulingPattern);
        this.tasks.add(task);
        this.size++;
    }

    public SchedulingPattern getSchedulingPattern(int i) throws IndexOutOfBoundsException {
        return (SchedulingPattern) this.patterns.get(i);
    }

    public Task getTask(int i) throws IndexOutOfBoundsException {
        return (Task) this.tasks.get(i);
    }

    public void remove(int i) throws IndexOutOfBoundsException {
        this.tasks.remove(i);
        this.patterns.remove(i);
        this.size--;
    }

    public int size() {
        return this.size;
    }
}
