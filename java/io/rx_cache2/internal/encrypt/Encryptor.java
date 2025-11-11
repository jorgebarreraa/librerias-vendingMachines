package io.rx_cache2.internal.encrypt;

import java.io.File;

/* loaded from: classes2.dex */
public interface Encryptor {
    void decrypt(String str, File file, File file2);

    void encrypt(String str, File file, File file2);
}
