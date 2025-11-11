package com.elvishew.xlog.printer.file.writer;

import java.io.File;

/* loaded from: classes.dex */
public abstract class Writer {
    public abstract void appendLog(String str);

    public abstract boolean close();

    public abstract File getOpenedFile();

    public abstract String getOpenedFileName();

    public abstract boolean isOpened();

    public abstract boolean open(File file);
}
