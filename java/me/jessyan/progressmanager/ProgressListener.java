package me.jessyan.progressmanager;

import me.jessyan.progressmanager.body.ProgressInfo;

/* loaded from: classes2.dex */
public interface ProgressListener {
    void onError(long j, Exception exc);

    void onProgress(ProgressInfo progressInfo);
}
