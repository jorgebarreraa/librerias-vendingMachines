package com.blankj.utilcode.util;

import android.util.Log;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;
import org.eclipse.paho.client.mqttv3.MqttTopic;

/* loaded from: classes.dex */
public final class ZipUtils {
    private static final int BUFFER_LEN = 8192;

    private ZipUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static List<String> getComments(File file) throws IOException {
        if (file == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        ZipFile zipFile = new ZipFile(file);
        Enumeration<? extends ZipEntry> entries = zipFile.entries();
        while (entries.hasMoreElements()) {
            arrayList.add(entries.nextElement().getComment());
        }
        zipFile.close();
        return arrayList;
    }

    public static List<String> getComments(String str) throws IOException {
        return getComments(UtilsBridge.getFileByPath(str));
    }

    public static List<String> getFilesPath(File file) throws IOException {
        if (file == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        ZipFile zipFile = new ZipFile(file);
        Enumeration<? extends ZipEntry> entries = zipFile.entries();
        while (entries.hasMoreElements()) {
            String replace = entries.nextElement().getName().replace("\\", MqttTopic.TOPIC_LEVEL_SEPARATOR);
            if (replace.contains("../")) {
                Log.e("ZipUtils", "entryName: " + replace + " is dangerous!");
                arrayList.add(replace);
            } else {
                arrayList.add(replace);
            }
        }
        zipFile.close();
        return arrayList;
    }

    public static List<String> getFilesPath(String str) throws IOException {
        return getFilesPath(UtilsBridge.getFileByPath(str));
    }

    private static boolean unzipChildFile(File file, List<File> list, ZipFile zipFile, ZipEntry zipEntry, String str) throws IOException {
        BufferedInputStream bufferedInputStream;
        File file2 = new File(file, str);
        list.add(file2);
        if (zipEntry.isDirectory()) {
            return UtilsBridge.createOrExistsDir(file2);
        }
        if (!UtilsBridge.createOrExistsFile(file2)) {
            return false;
        }
        BufferedOutputStream bufferedOutputStream = null;
        try {
            bufferedInputStream = new BufferedInputStream(zipFile.getInputStream(zipEntry));
            try {
                BufferedOutputStream bufferedOutputStream2 = new BufferedOutputStream(new FileOutputStream(file2));
                try {
                    byte[] bArr = new byte[8192];
                    while (true) {
                        int read = bufferedInputStream.read(bArr);
                        if (read == -1) {
                            bufferedInputStream.close();
                            bufferedOutputStream2.close();
                            return true;
                        }
                        bufferedOutputStream2.write(bArr, 0, read);
                    }
                } catch (Throwable th) {
                    th = th;
                    bufferedOutputStream = bufferedOutputStream2;
                    if (bufferedInputStream != null) {
                        bufferedInputStream.close();
                    }
                    if (bufferedOutputStream != null) {
                        bufferedOutputStream.close();
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Throwable th3) {
            th = th3;
            bufferedInputStream = null;
        }
    }

    public static List<File> unzipFile(File file, File file2) throws IOException {
        return unzipFileByKeyword(file, file2, (String) null);
    }

    public static List<File> unzipFile(String str, String str2) throws IOException {
        return unzipFileByKeyword(str, str2, (String) null);
    }

    public static List<File> unzipFileByKeyword(File file, File file2, String str) throws IOException {
        if (file == null || file2 == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        ZipFile zipFile = new ZipFile(file);
        Enumeration<? extends ZipEntry> entries = zipFile.entries();
        try {
            if (UtilsBridge.isSpace(str)) {
                while (entries.hasMoreElements()) {
                    ZipEntry nextElement = entries.nextElement();
                    String replace = nextElement.getName().replace("\\", MqttTopic.TOPIC_LEVEL_SEPARATOR);
                    if (replace.contains("../")) {
                        Log.e("ZipUtils", "entryName: " + replace + " is dangerous!");
                    } else if (!unzipChildFile(file2, arrayList, zipFile, nextElement, replace)) {
                        return arrayList;
                    }
                }
            } else {
                while (entries.hasMoreElements()) {
                    ZipEntry nextElement2 = entries.nextElement();
                    String replace2 = nextElement2.getName().replace("\\", MqttTopic.TOPIC_LEVEL_SEPARATOR);
                    if (replace2.contains("../")) {
                        Log.e("ZipUtils", "entryName: " + replace2 + " is dangerous!");
                    } else if (replace2.contains(str) && !unzipChildFile(file2, arrayList, zipFile, nextElement2, replace2)) {
                        return arrayList;
                    }
                }
            }
            return arrayList;
        } finally {
            zipFile.close();
        }
    }

    public static List<File> unzipFileByKeyword(String str, String str2, String str3) throws IOException {
        return unzipFileByKeyword(UtilsBridge.getFileByPath(str), UtilsBridge.getFileByPath(str2), str3);
    }

    public static boolean zipFile(File file, File file2) throws IOException {
        return zipFile(file, file2, (String) null);
    }

    public static boolean zipFile(File file, File file2, String str) throws IOException {
        if (file == null || file2 == null) {
            return false;
        }
        ZipOutputStream zipOutputStream = null;
        try {
            ZipOutputStream zipOutputStream2 = new ZipOutputStream(new FileOutputStream(file2));
            try {
                boolean zipFile = zipFile(file, "", zipOutputStream2, str);
                zipOutputStream2.close();
                return zipFile;
            } catch (Throwable th) {
                th = th;
                zipOutputStream = zipOutputStream2;
                if (zipOutputStream != null) {
                    zipOutputStream.close();
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    private static boolean zipFile(File file, String str, ZipOutputStream zipOutputStream, String str2) throws IOException {
        BufferedInputStream bufferedInputStream;
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(UtilsBridge.isSpace(str) ? "" : File.separator);
        sb.append(file.getName());
        String sb2 = sb.toString();
        if (file.isDirectory()) {
            File[] listFiles = file.listFiles();
            if (listFiles != null && listFiles.length > 0) {
                for (File file2 : listFiles) {
                    if (!zipFile(file2, sb2, zipOutputStream, str2)) {
                        return false;
                    }
                }
                return true;
            }
            ZipEntry zipEntry = new ZipEntry(sb2 + '/');
            zipEntry.setComment(str2);
            zipOutputStream.putNextEntry(zipEntry);
            zipOutputStream.closeEntry();
            return true;
        }
        try {
            bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
        } catch (Throwable th) {
            th = th;
            bufferedInputStream = null;
        }
        try {
            ZipEntry zipEntry2 = new ZipEntry(sb2);
            zipEntry2.setComment(str2);
            zipOutputStream.putNextEntry(zipEntry2);
            byte[] bArr = new byte[8192];
            while (true) {
                int read = bufferedInputStream.read(bArr, 0, 8192);
                if (read == -1) {
                    zipOutputStream.closeEntry();
                    bufferedInputStream.close();
                    return true;
                }
                zipOutputStream.write(bArr, 0, read);
            }
        } catch (Throwable th2) {
            th = th2;
            if (bufferedInputStream != null) {
                bufferedInputStream.close();
            }
            throw th;
        }
    }

    public static boolean zipFile(String str, String str2) throws IOException {
        return zipFile(UtilsBridge.getFileByPath(str), UtilsBridge.getFileByPath(str2), (String) null);
    }

    public static boolean zipFile(String str, String str2, String str3) throws IOException {
        return zipFile(UtilsBridge.getFileByPath(str), UtilsBridge.getFileByPath(str2), str3);
    }

    public static boolean zipFiles(Collection<File> collection, File file) throws IOException {
        return zipFiles(collection, file, (String) null);
    }

    public static boolean zipFiles(Collection<File> collection, File file, String str) throws IOException {
        ZipOutputStream zipOutputStream;
        if (collection == null || file == null) {
            return false;
        }
        try {
            zipOutputStream = new ZipOutputStream(new FileOutputStream(file));
            try {
                Iterator<File> it2 = collection.iterator();
                while (it2.hasNext()) {
                    if (!zipFile(it2.next(), "", zipOutputStream, str)) {
                        zipOutputStream.finish();
                        zipOutputStream.close();
                        return false;
                    }
                }
                zipOutputStream.finish();
                zipOutputStream.close();
                return true;
            } catch (Throwable th) {
                th = th;
                if (zipOutputStream != null) {
                    zipOutputStream.finish();
                    zipOutputStream.close();
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            zipOutputStream = null;
        }
    }

    public static boolean zipFiles(Collection<String> collection, String str) throws IOException {
        return zipFiles(collection, str, (String) null);
    }

    public static boolean zipFiles(Collection<String> collection, String str, String str2) throws IOException {
        ZipOutputStream zipOutputStream;
        if (collection == null || str == null) {
            return false;
        }
        try {
            zipOutputStream = new ZipOutputStream(new FileOutputStream(str));
            try {
                Iterator<String> it2 = collection.iterator();
                while (it2.hasNext()) {
                    if (!zipFile(UtilsBridge.getFileByPath(it2.next()), "", zipOutputStream, str2)) {
                        zipOutputStream.finish();
                        zipOutputStream.close();
                        return false;
                    }
                }
                zipOutputStream.finish();
                zipOutputStream.close();
                return true;
            } catch (Throwable th) {
                th = th;
                if (zipOutputStream != null) {
                    zipOutputStream.finish();
                    zipOutputStream.close();
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            zipOutputStream = null;
        }
    }
}
