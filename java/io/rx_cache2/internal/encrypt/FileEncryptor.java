package io.rx_cache2.internal.encrypt;

import java.io.File;
import javax.inject.Inject;

/* loaded from: classes2.dex */
public final class FileEncryptor {
    private static final String SUFFIX_TMP = "-tmp";
    private final Encryptor encryptor;

    @Inject
    public FileEncryptor(Encryptor encryptor) {
        this.encryptor = encryptor;
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x005b A[Catch: Exception -> 0x0057, TRY_LEAVE, TryCatch #2 {Exception -> 0x0057, blocks: (B:33:0x0053, B:26:0x005b), top: B:32:0x0053 }] */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0053 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.io.File rename(java.io.File r10, java.io.File r11) {
        /*
            r9 = this;
            r0 = 0
            java.io.FileInputStream r1 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L33 java.lang.Exception -> L36
            r1.<init>(r10)     // Catch: java.lang.Throwable -> L33 java.lang.Exception -> L36
            java.nio.channels.FileChannel r1 = r1.getChannel()     // Catch: java.lang.Throwable -> L33 java.lang.Exception -> L36
            java.io.FileOutputStream r2 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> L2c java.lang.Exception -> L2e
            r2.<init>(r11)     // Catch: java.lang.Throwable -> L2c java.lang.Exception -> L2e
            java.nio.channels.FileChannel r0 = r2.getChannel()     // Catch: java.lang.Throwable -> L2c java.lang.Exception -> L2e
            r3 = 0
            long r5 = r1.size()     // Catch: java.lang.Throwable -> L2c java.lang.Exception -> L2e
            r2 = r1
            r7 = r0
            r2.transferTo(r3, r5, r7)     // Catch: java.lang.Throwable -> L2c java.lang.Exception -> L2e
            r10.delete()     // Catch: java.lang.Throwable -> L2c java.lang.Exception -> L2e
            if (r1 == 0) goto L26
            r1.close()     // Catch: java.lang.Exception -> L41
        L26:
            if (r0 == 0) goto L4c
            r0.close()     // Catch: java.lang.Exception -> L41
            goto L4c
        L2c:
            r10 = move-exception
            goto L51
        L2e:
            r10 = move-exception
            r8 = r1
            r1 = r0
            r0 = r8
            goto L38
        L33:
            r10 = move-exception
            r1 = r0
            goto L51
        L36:
            r10 = move-exception
            r1 = r0
        L38:
            r10.printStackTrace()     // Catch: java.lang.Throwable -> L4d
            if (r0 == 0) goto L43
            r0.close()     // Catch: java.lang.Exception -> L41
            goto L43
        L41:
            r10 = move-exception
            goto L49
        L43:
            if (r1 == 0) goto L4c
            r1.close()     // Catch: java.lang.Exception -> L41
            goto L4c
        L49:
            r10.printStackTrace()
        L4c:
            return r11
        L4d:
            r10 = move-exception
            r8 = r1
            r1 = r0
            r0 = r8
        L51:
            if (r1 == 0) goto L59
            r1.close()     // Catch: java.lang.Exception -> L57
            goto L59
        L57:
            r11 = move-exception
            goto L5f
        L59:
            if (r0 == 0) goto L62
            r0.close()     // Catch: java.lang.Exception -> L57
            goto L62
        L5f:
            r11.printStackTrace()
        L62:
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: io.rx_cache2.internal.encrypt.FileEncryptor.rename(java.io.File, java.io.File):java.io.File");
    }

    public File decrypt(String str, File file) {
        if (!file.exists()) {
            return file;
        }
        File file2 = new File(file.getAbsolutePath() + SUFFIX_TMP);
        this.encryptor.decrypt(str, file, file2);
        return file2;
    }

    public File encrypt(String str, File file) {
        if (!file.exists()) {
            return file;
        }
        String absolutePath = file.getAbsolutePath();
        File rename = rename(file, new File(absolutePath + SUFFIX_TMP));
        File file2 = new File(absolutePath);
        this.encryptor.encrypt(str, rename, file2);
        rename.delete();
        return file2;
    }
}
