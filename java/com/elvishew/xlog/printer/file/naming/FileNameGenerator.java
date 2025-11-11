package com.elvishew.xlog.printer.file.naming;

/* loaded from: classes.dex */
public interface FileNameGenerator {
    String generateFileName(int i, long j);

    boolean isFileNameChangeable();
}
