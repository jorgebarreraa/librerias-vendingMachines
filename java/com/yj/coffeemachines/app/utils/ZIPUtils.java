package com.yj.coffeemachines.app.utils;

import android.util.Log;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/* loaded from: classes.dex */
public class ZIPUtils {
    private static String TAG = "ZIPUtils";

    public static List<File> GetFileList(String str, boolean z, boolean z2) throws Exception {
        ArrayList arrayList = new ArrayList();
        ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(str));
        while (true) {
            ZipEntry nextEntry = zipInputStream.getNextEntry();
            if (nextEntry == null) {
                zipInputStream.close();
                return arrayList;
            }
            String name = nextEntry.getName();
            if (nextEntry.isDirectory()) {
                File file = new File(name.substring(0, name.length() - 1));
                if (z) {
                    arrayList.add(file);
                }
            } else {
                File file2 = new File(name);
                if (z2) {
                    arrayList.add(file2);
                }
            }
        }
    }

    public static void UnZipFolder(String str, String str2) throws Exception {
        ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(str));
        while (true) {
            ZipEntry nextEntry = zipInputStream.getNextEntry();
            if (nextEntry == null) {
                zipInputStream.close();
                return;
            }
            String name = nextEntry.getName();
            if (nextEntry.isDirectory()) {
                new File(str2 + File.separator + name.substring(0, name.length() - 1)).mkdirs();
            } else {
                Log.e(TAG, str2 + File.separator + name);
                File file = new File(str2 + File.separator + name);
                if (!file.exists()) {
                    Log.e(TAG, "Create the file:" + str2 + File.separator + name);
                    file.getParentFile().mkdirs();
                    file.createNewFile();
                }
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                byte[] bArr = new byte[1024];
                while (true) {
                    int read = zipInputStream.read(bArr);
                    if (read == -1) {
                        break;
                    }
                    fileOutputStream.write(bArr, 0, read);
                    fileOutputStream.flush();
                }
                fileOutputStream.close();
            }
        }
    }
}
