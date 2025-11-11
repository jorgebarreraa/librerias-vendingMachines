package com.elvishew.xlog.printer.file.clean;

import java.io.File;

/* loaded from: classes.dex */
public class FileLastModifiedCleanStrategy implements CleanStrategy {
    private long maxTimeMillis;

    public FileLastModifiedCleanStrategy(long j) {
        this.maxTimeMillis = j;
    }

    @Override // com.elvishew.xlog.printer.file.clean.CleanStrategy
    public boolean shouldClean(File file) {
        return System.currentTimeMillis() - file.lastModified() > this.maxTimeMillis;
    }
}
