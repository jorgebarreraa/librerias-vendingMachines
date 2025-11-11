package com.elvishew.xlog.printer.file.backup;

import java.io.File;

/* loaded from: classes.dex */
public interface BackupStrategy {
    boolean shouldBackup(File file);
}
