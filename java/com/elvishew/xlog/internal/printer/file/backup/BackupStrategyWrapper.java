package com.elvishew.xlog.internal.printer.file.backup;

import com.elvishew.xlog.printer.file.backup.BackupStrategy;
import com.elvishew.xlog.printer.file.backup.BackupStrategy2;
import java.io.File;

/* loaded from: classes.dex */
public class BackupStrategyWrapper implements BackupStrategy2 {
    private BackupStrategy backupStrategy;

    public BackupStrategyWrapper(BackupStrategy backupStrategy) {
        this.backupStrategy = backupStrategy;
    }

    @Override // com.elvishew.xlog.printer.file.backup.BackupStrategy2
    public String getBackupFileName(String str, int i) {
        return str + ".bak";
    }

    @Override // com.elvishew.xlog.printer.file.backup.BackupStrategy2
    public int getMaxBackupIndex() {
        return 1;
    }

    @Override // com.elvishew.xlog.printer.file.backup.BackupStrategy
    public boolean shouldBackup(File file) {
        return this.backupStrategy.shouldBackup(file);
    }
}
