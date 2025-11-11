package it.sauronsoftware.cron4j;

import java.io.File;
import java.util.ArrayList;
import java.util.TimeZone;

/* loaded from: classes2.dex */
public class Scheduler {
    private String guid = GUIDGenerator.generate();
    private TimeZone timezone = null;
    private boolean daemon = false;
    private boolean started = false;
    private ArrayList collectors = new ArrayList();
    private MemoryTaskCollector memoryTaskCollector = new MemoryTaskCollector();
    private FileTaskCollector fileTaskCollector = new FileTaskCollector();
    private ArrayList listeners = new ArrayList();
    private TimerThread timer = null;
    private ArrayList launchers = null;
    private ArrayList executors = null;
    private Object lock = new Object();

    public Scheduler() {
        this.collectors.add(this.memoryTaskCollector);
        this.collectors.add(this.fileTaskCollector);
    }

    private void tillExecutorDies(TaskExecutor taskExecutor) {
        boolean z = false;
        do {
            try {
                taskExecutor.join();
                z = true;
            } catch (InterruptedException unused) {
            }
        } while (!z);
    }

    private void tillThreadDies(Thread thread) {
        boolean z = false;
        do {
            try {
                thread.join();
                z = true;
            } catch (InterruptedException unused) {
            }
        } while (!z);
    }

    public void addSchedulerListener(SchedulerListener schedulerListener) {
        synchronized (this.listeners) {
            this.listeners.add(schedulerListener);
        }
    }

    public void addTaskCollector(TaskCollector taskCollector) {
        synchronized (this.collectors) {
            this.collectors.add(taskCollector);
        }
    }

    public void deschedule(Object obj) {
        deschedule((String) obj);
    }

    public void deschedule(String str) {
        this.memoryTaskCollector.remove(str);
    }

    public void descheduleFile(File file) {
        this.fileTaskCollector.removeFile(file);
    }

    public TaskExecutor[] getExecutingTasks() {
        TaskExecutor[] taskExecutorArr;
        synchronized (this.executors) {
            int size = this.executors.size();
            taskExecutorArr = new TaskExecutor[size];
            for (int i = 0; i < size; i++) {
                taskExecutorArr[i] = (TaskExecutor) this.executors.get(i);
            }
        }
        return taskExecutorArr;
    }

    public Object getGuid() {
        return this.guid;
    }

    public File[] getScheduledFiles() {
        return this.fileTaskCollector.getFiles();
    }

    public SchedulerListener[] getSchedulerListeners() {
        SchedulerListener[] schedulerListenerArr;
        synchronized (this.listeners) {
            int size = this.listeners.size();
            schedulerListenerArr = new SchedulerListener[size];
            for (int i = 0; i < size; i++) {
                schedulerListenerArr[i] = (SchedulerListener) this.listeners.get(i);
            }
        }
        return schedulerListenerArr;
    }

    public SchedulingPattern getSchedulingPattern(String str) {
        return this.memoryTaskCollector.getSchedulingPattern(str);
    }

    public Task getTask(String str) {
        return this.memoryTaskCollector.getTask(str);
    }

    public TaskCollector[] getTaskCollectors() {
        TaskCollector[] taskCollectorArr;
        synchronized (this.collectors) {
            int size = this.collectors.size() - 2;
            taskCollectorArr = new TaskCollector[size];
            for (int i = 0; i < size; i++) {
                taskCollectorArr[i] = (TaskCollector) this.collectors.get(i + 2);
            }
        }
        return taskCollectorArr;
    }

    public Runnable getTaskRunnable(Object obj) {
        Task task = getTask((String) obj);
        if (task instanceof RunnableTask) {
            return ((RunnableTask) task).getRunnable();
        }
        return null;
    }

    public String getTaskSchedulingPattern(Object obj) {
        return getSchedulingPattern((String) obj).toString();
    }

    public TimeZone getTimeZone() {
        TimeZone timeZone = this.timezone;
        return timeZone != null ? timeZone : TimeZone.getDefault();
    }

    public boolean isDaemon() {
        return this.daemon;
    }

    public boolean isStarted() {
        boolean z;
        synchronized (this.lock) {
            z = this.started;
        }
        return z;
    }

    public TaskExecutor launch(Task task) {
        TaskExecutor spawnExecutor;
        synchronized (this.lock) {
            if (!this.started) {
                throw new IllegalStateException("Scheduler not started");
            }
            spawnExecutor = spawnExecutor(task);
        }
        return spawnExecutor;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void notifyExecutorCompleted(TaskExecutor taskExecutor) {
        synchronized (this.executors) {
            this.executors.remove(taskExecutor);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void notifyLauncherCompleted(LauncherThread launcherThread) {
        synchronized (this.launchers) {
            this.launchers.remove(launcherThread);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void notifyTaskFailed(TaskExecutor taskExecutor, Throwable th) {
        synchronized (this.listeners) {
            int size = this.listeners.size();
            if (size > 0) {
                for (int i = 0; i < size; i++) {
                    ((SchedulerListener) this.listeners.get(i)).taskFailed(taskExecutor, th);
                }
            } else {
                th.printStackTrace();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void notifyTaskLaunching(TaskExecutor taskExecutor) {
        synchronized (this.listeners) {
            int size = this.listeners.size();
            for (int i = 0; i < size; i++) {
                ((SchedulerListener) this.listeners.get(i)).taskLaunching(taskExecutor);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void notifyTaskSucceeded(TaskExecutor taskExecutor) {
        synchronized (this.listeners) {
            int size = this.listeners.size();
            for (int i = 0; i < size; i++) {
                ((SchedulerListener) this.listeners.get(i)).taskSucceeded(taskExecutor);
            }
        }
    }

    public void removeSchedulerListener(SchedulerListener schedulerListener) {
        synchronized (this.listeners) {
            this.listeners.remove(schedulerListener);
        }
    }

    public void removeTaskCollector(TaskCollector taskCollector) {
        synchronized (this.collectors) {
            this.collectors.remove(taskCollector);
        }
    }

    public void reschedule(Object obj, String str) throws InvalidPatternException {
        reschedule((String) obj, new SchedulingPattern(str));
    }

    public void reschedule(String str, SchedulingPattern schedulingPattern) {
        this.memoryTaskCollector.update(str, schedulingPattern);
    }

    public void reschedule(String str, String str2) throws InvalidPatternException {
        reschedule(str, new SchedulingPattern(str2));
    }

    public String schedule(SchedulingPattern schedulingPattern, Task task) {
        return this.memoryTaskCollector.add(schedulingPattern, task);
    }

    public String schedule(String str, Task task) throws InvalidPatternException {
        return schedule(new SchedulingPattern(str), task);
    }

    public String schedule(String str, Runnable runnable) throws InvalidPatternException {
        return schedule(str, new RunnableTask(runnable));
    }

    public void scheduleFile(File file) {
        this.fileTaskCollector.addFile(file);
    }

    public void setDaemon(boolean z) throws IllegalStateException {
        synchronized (this.lock) {
            if (this.started) {
                throw new IllegalStateException("Scheduler already started");
            }
            this.daemon = z;
        }
    }

    public void setTimeZone(TimeZone timeZone) {
        this.timezone = timeZone;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public TaskExecutor spawnExecutor(Task task) {
        TaskExecutor taskExecutor = new TaskExecutor(this, task);
        synchronized (this.executors) {
            this.executors.add(taskExecutor);
        }
        taskExecutor.start(this.daemon);
        return taskExecutor;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public LauncherThread spawnLauncher(long j) {
        TaskCollector[] taskCollectorArr;
        synchronized (this.collectors) {
            int size = this.collectors.size();
            taskCollectorArr = new TaskCollector[size];
            for (int i = 0; i < size; i++) {
                taskCollectorArr[i] = (TaskCollector) this.collectors.get(i);
            }
        }
        LauncherThread launcherThread = new LauncherThread(this, taskCollectorArr, j);
        synchronized (this.launchers) {
            this.launchers.add(launcherThread);
        }
        launcherThread.setDaemon(this.daemon);
        launcherThread.start();
        return launcherThread;
    }

    public void start() throws IllegalStateException {
        synchronized (this.lock) {
            if (this.started) {
                throw new IllegalStateException("Scheduler already started");
            }
            this.launchers = new ArrayList();
            this.executors = new ArrayList();
            this.timer = new TimerThread(this);
            this.timer.setDaemon(this.daemon);
            this.timer.start();
            this.started = true;
        }
    }

    public void stop() throws IllegalStateException {
        TaskExecutor taskExecutor;
        LauncherThread launcherThread;
        synchronized (this.lock) {
            if (!this.started) {
                throw new IllegalStateException("Scheduler not started");
            }
            this.timer.interrupt();
            tillThreadDies(this.timer);
            this.timer = null;
            while (true) {
                synchronized (this.launchers) {
                    if (this.launchers.size() == 0) {
                        break;
                    } else {
                        launcherThread = (LauncherThread) this.launchers.remove(0);
                    }
                }
                launcherThread.interrupt();
                tillThreadDies(launcherThread);
            }
            this.launchers = null;
            while (true) {
                synchronized (this.executors) {
                    if (this.executors.size() == 0) {
                        this.executors = null;
                        this.started = false;
                    } else {
                        taskExecutor = (TaskExecutor) this.executors.remove(0);
                    }
                }
                if (taskExecutor.canBeStopped()) {
                    taskExecutor.stop();
                }
                tillExecutorDies(taskExecutor);
            }
        }
    }
}
