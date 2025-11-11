package it.sauronsoftware.cron4j;

import java.util.ArrayList;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class MemoryTaskCollector implements TaskCollector {
    private int size = 0;
    private ArrayList patterns = new ArrayList();
    private ArrayList tasks = new ArrayList();
    private ArrayList ids = new ArrayList();

    public synchronized String add(SchedulingPattern schedulingPattern, Task task) {
        String generate;
        generate = GUIDGenerator.generate();
        this.patterns.add(schedulingPattern);
        this.tasks.add(task);
        this.ids.add(generate);
        return generate;
    }

    public synchronized SchedulingPattern getSchedulingPattern(String str) {
        int indexOf = this.ids.indexOf(str);
        if (indexOf <= -1) {
            return null;
        }
        return (SchedulingPattern) this.patterns.get(indexOf);
    }

    public synchronized Task getTask(String str) {
        int indexOf = this.ids.indexOf(str);
        if (indexOf <= -1) {
            return null;
        }
        return (Task) this.tasks.get(indexOf);
    }

    @Override // it.sauronsoftware.cron4j.TaskCollector
    public synchronized TaskTable getTasks() {
        TaskTable taskTable;
        taskTable = new TaskTable();
        int size = this.tasks.size();
        for (int i = 0; i < size; i++) {
            taskTable.add((SchedulingPattern) this.patterns.get(i), (Task) this.tasks.get(i));
        }
        return taskTable;
    }

    public synchronized void remove(String str) throws IndexOutOfBoundsException {
        int indexOf = this.ids.indexOf(str);
        if (indexOf > -1) {
            this.tasks.remove(indexOf);
            this.patterns.remove(indexOf);
            this.ids.remove(indexOf);
        }
    }

    public synchronized int size() {
        return this.size;
    }

    public synchronized void update(String str, SchedulingPattern schedulingPattern) {
        int indexOf = this.ids.indexOf(str);
        if (indexOf > -1) {
            this.patterns.set(indexOf, schedulingPattern);
        }
    }
}
