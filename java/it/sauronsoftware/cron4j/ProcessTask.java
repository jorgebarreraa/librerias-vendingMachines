package it.sauronsoftware.cron4j;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* loaded from: classes2.dex */
public class ProcessTask extends Task {
    private String[] command;
    private File directory;
    private String[] envs;
    private File stderrFile;
    private File stdinFile;
    private File stdoutFile;

    public ProcessTask(String str) {
        this(new String[]{str}, null, null);
    }

    public ProcessTask(String[] strArr) {
        this(strArr, null, null);
    }

    public ProcessTask(String[] strArr, String[] strArr2) {
        this(strArr, strArr2, null);
    }

    public ProcessTask(String[] strArr, String[] strArr2, File file) {
        this.stdinFile = null;
        this.stdoutFile = null;
        this.stderrFile = null;
        this.command = strArr;
        this.envs = strArr2;
        this.directory = file;
    }

    private InputStream buildInputStream(File file) {
        if (file != null) {
            try {
                return new FileInputStream(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private OutputStream buildOutputStream(File file) {
        if (file != null) {
            try {
                return new FileOutputStream(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private Process exec() throws IOException {
        Runtime runtime = Runtime.getRuntime();
        try {
            return runtime.exec(this.command, this.envs, this.directory);
        } catch (NoSuchMethodError unused) {
            return runtime.exec(this.command, this.envs);
        }
    }

    private static String listStrings(String[] strArr) {
        if (strArr == null) {
            return "null";
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append('[');
        for (int i = 0; i < strArr.length; i++) {
            if (i > 0) {
                stringBuffer.append(", ");
            }
            stringBuffer.append(strArr[i]);
        }
        stringBuffer.append(']');
        return stringBuffer.toString();
    }

    @Override // it.sauronsoftware.cron4j.Task
    public boolean canBeStopped() {
        return true;
    }

    @Override // it.sauronsoftware.cron4j.Task
    public void execute(TaskExecutionContext taskExecutionContext) throws RuntimeException {
        try {
            Process exec = exec();
            InputStream buildInputStream = buildInputStream(this.stdinFile);
            OutputStream buildOutputStream = buildOutputStream(this.stdoutFile);
            OutputStream buildOutputStream2 = buildOutputStream(this.stderrFile);
            if (buildInputStream != null) {
                new StreamBridge(buildInputStream, exec.getOutputStream()).start();
            }
            if (buildOutputStream != null) {
                new StreamBridge(exec.getInputStream(), buildOutputStream).start();
            }
            if (buildOutputStream2 != null) {
                new StreamBridge(exec.getErrorStream(), buildOutputStream2).start();
            }
            try {
                try {
                    int waitFor = exec.waitFor();
                    if (waitFor == 0) {
                        return;
                    }
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append(toString());
                    stringBuffer.append(" returns with error code ");
                    stringBuffer.append(waitFor);
                    throw new RuntimeException(stringBuffer.toString());
                } catch (InterruptedException unused) {
                    StringBuffer stringBuffer2 = new StringBuffer();
                    stringBuffer2.append(toString());
                    stringBuffer2.append(" has been interrupted");
                    throw new RuntimeException(stringBuffer2.toString());
                }
            } finally {
                if (buildInputStream != null) {
                    try {
                        buildInputStream.close();
                    } catch (Throwable unused2) {
                    }
                }
                if (buildOutputStream != null) {
                    try {
                        buildOutputStream.close();
                    } catch (Throwable unused3) {
                    }
                }
                if (buildOutputStream2 != null) {
                    try {
                        buildOutputStream2.close();
                    } catch (Throwable unused4) {
                    }
                }
                exec.destroy();
            }
        } catch (IOException e) {
            StringBuffer stringBuffer3 = new StringBuffer();
            stringBuffer3.append(toString());
            stringBuffer3.append(" cannot be started");
            throw new RuntimeException(stringBuffer3.toString(), e);
        }
    }

    public String[] getCommand() {
        return this.command;
    }

    public File getDirectory() {
        return this.directory;
    }

    public String[] getEnvs() {
        return this.envs;
    }

    public File getStderrFile() {
        return this.stderrFile;
    }

    public File getStdinFile() {
        return this.stdinFile;
    }

    public File getStdoutFile() {
        return this.stdoutFile;
    }

    public void setCommand(String[] strArr) {
        this.command = strArr;
    }

    public void setDirectory(File file) {
        this.directory = file;
    }

    public void setEnvs(String[] strArr) {
        this.envs = strArr;
    }

    public void setStderrFile(File file) {
        this.stderrFile = file;
    }

    public void setStdinFile(File file) {
        this.stdinFile = file;
    }

    public void setStdoutFile(File file) {
        this.stdoutFile = file;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Task[");
        stringBuffer.append("cmd=");
        stringBuffer.append(listStrings(this.command));
        stringBuffer.append(", env=");
        stringBuffer.append(listStrings(this.envs));
        stringBuffer.append(", ");
        stringBuffer.append("dir=");
        stringBuffer.append(this.directory);
        stringBuffer.append("]");
        return stringBuffer.toString();
    }
}
