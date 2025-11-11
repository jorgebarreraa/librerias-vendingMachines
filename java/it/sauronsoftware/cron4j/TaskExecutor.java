package it.sauronsoftware.cron4j;

import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes2.dex */
public class TaskExecutor {
    private Scheduler scheduler;
    private Task task;
    private Thread thread;
    private String guid = GUIDGenerator.generate();
    private TaskExecutor myself = this;
    private ArrayList listeners = new ArrayList();
    private long startTime = -1;
    private boolean paused = false;
    private boolean stopped = false;
    private Object lock = new Object();
    private MyContext context = new MyContext();

    /* loaded from: classes2.dex */
    private class MyContext implements TaskExecutionContext {
        private double completeness;
        private String message;

        private MyContext() {
            this.message = "";
            this.completeness = 0.0d;
        }

        public double getCompleteness() {
            return this.completeness;
        }

        @Override // it.sauronsoftware.cron4j.TaskExecutionContext
        public Scheduler getScheduler() {
            return TaskExecutor.this.scheduler;
        }

        public String getStatusMessage() {
            return this.message;
        }

        @Override // it.sauronsoftware.cron4j.TaskExecutionContext
        public TaskExecutor getTaskExecutor() {
            return TaskExecutor.this.myself;
        }

        @Override // it.sauronsoftware.cron4j.TaskExecutionContext
        public boolean isStopped() {
            return TaskExecutor.this.stopped;
        }

        @Override // it.sauronsoftware.cron4j.TaskExecutionContext
        public void pauseIfRequested() {
            synchronized (TaskExecutor.this.lock) {
                if (TaskExecutor.this.paused) {
                    try {
                        TaskExecutor.this.lock.wait();
                    } catch (InterruptedException unused) {
                    }
                }
            }
        }

        @Override // it.sauronsoftware.cron4j.TaskExecutionContext
        public void setCompleteness(double d) {
            if (d < 0.0d || d > 1.0d) {
                return;
            }
            this.completeness = d;
            TaskExecutor.this.notifyCompletenessValueChanged(d);
        }

        @Override // it.sauronsoftware.cron4j.TaskExecutionContext
        public void setStatusMessage(String str) {
            this.message = str != null ? str : "";
            TaskExecutor.this.notifyStatusMessageChanged(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public class Runner implements Runnable {
        private Runner() {
        }

        @Override // java.lang.Runnable
        public void run() {
            TaskExecutor.this.startTime = System.currentTimeMillis();
            Throwable th = null;
            try {
                try {
                    TaskExecutor.this.scheduler.notifyTaskLaunching(TaskExecutor.this.myself);
                    TaskExecutor.this.task.execute(TaskExecutor.this.context);
                    TaskExecutor.this.scheduler.notifyTaskSucceeded(TaskExecutor.this.myself);
                } catch (Throwable th2) {
                    th = th2;
                    TaskExecutor.this.scheduler.notifyTaskFailed(TaskExecutor.this.myself, th);
                }
            } finally {
                TaskExecutor.this.notifyExecutionTerminated(th);
                TaskExecutor.this.scheduler.notifyExecutorCompleted(TaskExecutor.this.myself);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public TaskExecutor(Scheduler scheduler, Task task) {
        this.scheduler = scheduler;
        this.task = task;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyCompletenessValueChanged(double d) {
        synchronized (this.listeners) {
            Iterator it2 = this.listeners.iterator();
            while (it2.hasNext()) {
                ((TaskExecutorListener) it2.next()).completenessValueChanged(this, d);
            }
        }
    }

    private void notifyExecutionPausing() {
        synchronized (this.listeners) {
            Iterator it2 = this.listeners.iterator();
            while (it2.hasNext()) {
                ((TaskExecutorListener) it2.next()).executionPausing(this);
            }
        }
    }

    private void notifyExecutionResuming() {
        synchronized (this.listeners) {
            Iterator it2 = this.listeners.iterator();
            while (it2.hasNext()) {
                ((TaskExecutorListener) it2.next()).executionResuming(this);
            }
        }
    }

    private void notifyExecutionStopping() {
        synchronized (this.listeners) {
            Iterator it2 = this.listeners.iterator();
            while (it2.hasNext()) {
                ((TaskExecutorListener) it2.next()).executionStopping(this);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyExecutionTerminated(Throwable th) {
        synchronized (this.listeners) {
            Iterator it2 = this.listeners.iterator();
            while (it2.hasNext()) {
                ((TaskExecutorListener) it2.next()).executionTerminated(this, th);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyStatusMessageChanged(String str) {
        synchronized (this.listeners) {
            Iterator it2 = this.listeners.iterator();
            while (it2.hasNext()) {
                ((TaskExecutorListener) it2.next()).statusMessageChanged(this, str);
            }
        }
    }

    public void addTaskExecutorListener(TaskExecutorListener taskExecutorListener) {
        synchronized (this.listeners) {
            this.listeners.add(taskExecutorListener);
        }
    }

    public boolean canBePaused() {
        return this.task.canBePaused();
    }

    public boolean canBeStopped() {
        return this.task.canBeStopped();
    }

    public double getCompleteness() throws UnsupportedOperationException {
        if (supportsCompletenessTracking()) {
            return this.context.getCompleteness();
        }
        throw new UnsupportedOperationException("Completeness tracking not supported");
    }

    public String getGuid() {
        return this.guid;
    }

    public Scheduler getScheduler() {
        return this.scheduler;
    }

    public long getStartTime() {
        return this.startTime;
    }

    public String getStatusMessage() throws UnsupportedOperationException {
        if (supportsStatusTracking()) {
            return this.context.getStatusMessage();
        }
        throw new UnsupportedOperationException("Status tracking not supported");
    }

    public Task getTask() {
        return this.task;
    }

    public TaskExecutorListener[] getTaskExecutorListeners() {
        TaskExecutorListener[] taskExecutorListenerArr;
        synchronized (this.listeners) {
            int size = this.listeners.size();
            taskExecutorListenerArr = new TaskExecutorListener[size];
            for (int i = 0; i < size; i++) {
                taskExecutorListenerArr[i] = (TaskExecutorListener) this.listeners.get(i);
            }
        }
        return taskExecutorListenerArr;
    }

    public boolean isAlive() {
        Thread thread = this.thread;
        if (thread != null) {
            return thread.isAlive();
        }
        return false;
    }

    public boolean isPaused() {
        return this.paused;
    }

    public boolean isStopped() {
        return this.stopped;
    }

    public void join() throws InterruptedException {
        Thread thread = this.thread;
        if (thread != null) {
            thread.join();
        }
    }

    public void pause() throws UnsupportedOperationException {
        if (!canBePaused()) {
            throw new UnsupportedOperationException("Pause not supported");
        }
        synchronized (this.lock) {
            if (this.thread != null && !this.paused) {
                notifyExecutionPausing();
                this.paused = true;
            }
        }
    }

    public void removeTaskExecutorListener(TaskExecutorListener taskExecutorListener) {
        synchronized (this.listeners) {
            this.listeners.remove(taskExecutorListener);
        }
    }

    public void resume() {
        synchronized (this.lock) {
            if (this.thread != null && this.paused) {
                notifyExecutionResuming();
                this.paused = false;
                this.lock.notifyAll();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void start(boolean z) {
        synchronized (this.lock) {
            this.startTime = System.currentTimeMillis();
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("cron4j::scheduler[");
            stringBuffer.append(this.scheduler.getGuid());
            stringBuffer.append("]::executor[");
            stringBuffer.append(this.guid);
            stringBuffer.append("]");
            String stringBuffer2 = stringBuffer.toString();
            this.thread = new Thread(new Runner());
            this.thread.setDaemon(z);
            this.thread.setName(stringBuffer2);
            this.thread.start();
        }
    }

    public void stop() throws UnsupportedOperationException {
        if (!canBeStopped()) {
            throw new UnsupportedOperationException("Stop not supported");
        }
        boolean z = false;
        synchronized (this.lock) {
            if (this.thread != null && !this.stopped) {
                this.stopped = true;
                if (this.paused) {
                    resume();
                }
                notifyExecutionStopping();
                this.thread.interrupt();
                z = true;
            }
        }
        if (!z) {
            return;
        }
        while (true) {
            try {
                this.thread.join();
                this.thread = null;
                return;
            } catch (InterruptedException unused) {
            }
        }
    }

    public boolean supportsCompletenessTracking() {
        return this.task.supportsCompletenessTracking();
    }

    public boolean supportsStatusTracking() {
        return this.task.supportsStatusTracking();
    }
}
