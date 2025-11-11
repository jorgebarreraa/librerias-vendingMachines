package it.sauronsoftware.cron4j;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class FileTaskCollector implements TaskCollector {
    private ArrayList files = new ArrayList();

    public synchronized void addFile(File file) {
        this.files.add(file);
    }

    public synchronized File[] getFiles() {
        File[] fileArr;
        int size = this.files.size();
        fileArr = new File[size];
        for (int i = 0; i < size; i++) {
            fileArr[i] = (File) this.files.get(i);
        }
        return fileArr;
    }

    @Override // it.sauronsoftware.cron4j.TaskCollector
    public synchronized TaskTable getTasks() {
        TaskTable taskTable;
        taskTable = new TaskTable();
        int size = this.files.size();
        for (int i = 0; i < size; i++) {
            File file = (File) this.files.get(i);
            TaskTable taskTable2 = null;
            try {
                taskTable2 = CronParser.parse(file);
            } catch (IOException e) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("Cannot parse cron file: ");
                stringBuffer.append(file.getAbsolutePath());
                new Exception(stringBuffer.toString(), e).printStackTrace();
            }
            if (taskTable2 != null) {
                int size2 = taskTable2.size();
                for (int i2 = 0; i2 < size2; i2++) {
                    taskTable.add(taskTable2.getSchedulingPattern(i2), taskTable2.getTask(i2));
                }
            }
        }
        return taskTable;
    }

    public synchronized void removeFile(File file) {
        this.files.remove(file);
    }
}
