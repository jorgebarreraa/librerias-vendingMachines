package com.jess.arms.http.log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.List;
import okhttp3.MediaType;
import okhttp3.Request;

/* loaded from: classes.dex */
public interface FormatPrinter {
    void printFileRequest(@NonNull Request request);

    void printFileResponse(long j, boolean z, int i, @NonNull String str, @NonNull List<String> list, @NonNull String str2, @NonNull String str3);

    void printJsonRequest(@NonNull Request request, @NonNull String str);

    void printJsonResponse(long j, boolean z, int i, @NonNull String str, @Nullable MediaType mediaType, @Nullable String str2, @NonNull List<String> list, @NonNull String str3, @NonNull String str4);
}
