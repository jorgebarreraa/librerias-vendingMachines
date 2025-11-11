package it.sauronsoftware.cron4j;

import com.bumptech.glide.load.Key;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;

/* loaded from: classes2.dex */
public class CronParser {
    private CronParser() {
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x007a  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x007e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static java.lang.String escape(java.lang.String r9) {
        /*
            int r0 = r9.length()
            java.lang.StringBuffer r1 = new java.lang.StringBuffer
            r1.<init>()
            r2 = 0
            r3 = 0
        Lb:
            if (r3 >= r0) goto L84
            char r4 = r9.charAt(r3)
            r5 = 92
            r6 = 2
            if (r4 != r5) goto L77
            int r7 = r0 + (-1)
            if (r3 >= r7) goto L77
            int r7 = r3 + 1
            char r7 = r9.charAt(r7)
            r8 = 34
            if (r7 != r8) goto L28
            r1.append(r8)
            goto L78
        L28:
            if (r7 != r5) goto L2e
        L2a:
            r1.append(r5)
            goto L78
        L2e:
            r5 = 47
            if (r7 != r5) goto L33
            goto L2a
        L33:
            r5 = 98
            if (r7 != r5) goto L3a
            r5 = 8
            goto L2a
        L3a:
            r5 = 102(0x66, float:1.43E-43)
            if (r7 != r5) goto L41
            r5 = 12
            goto L2a
        L41:
            r5 = 110(0x6e, float:1.54E-43)
            if (r7 != r5) goto L48
            r5 = 10
            goto L2a
        L48:
            r5 = 114(0x72, float:1.6E-43)
            if (r7 != r5) goto L4f
            r5 = 13
            goto L2a
        L4f:
            r5 = 116(0x74, float:1.63E-43)
            if (r7 != r5) goto L56
            r5 = 9
            goto L2a
        L56:
            r5 = 117(0x75, float:1.64E-43)
            if (r7 != r5) goto L77
            int r5 = r0 + (-5)
            if (r3 >= r5) goto L77
            int r5 = r3 + 2
            int r6 = r3 + 6
            java.lang.String r5 = r9.substring(r5, r6)
            r6 = 16
            int r5 = java.lang.Integer.parseInt(r5, r6)     // Catch: java.lang.NumberFormatException -> L77
            if (r5 < 0) goto L74
            char r5 = (char) r5     // Catch: java.lang.NumberFormatException -> L77
            r1.append(r5)     // Catch: java.lang.NumberFormatException -> L77
            r5 = 6
            goto L75
        L74:
            r5 = 0
        L75:
            r6 = r5
            goto L78
        L77:
            r6 = 0
        L78:
            if (r6 != 0) goto L7e
            r1.append(r4)
            goto L81
        L7e:
            int r6 = r6 + (-1)
            int r3 = r3 + r6
        L81:
            int r3 = r3 + 1
            goto Lb
        L84:
            java.lang.String r9 = r1.toString()
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: it.sauronsoftware.cron4j.CronParser.escape(java.lang.String):java.lang.String");
    }

    public static TaskTable parse(File file) throws IOException {
        FileInputStream fileInputStream;
        FileInputStream fileInputStream2 = null;
        try {
            fileInputStream = new FileInputStream(file);
        } catch (Throwable th) {
            th = th;
        }
        try {
            TaskTable parse = parse(fileInputStream);
            try {
                fileInputStream.close();
            } catch (Throwable unused) {
            }
            return parse;
        } catch (Throwable th2) {
            th = th2;
            fileInputStream2 = fileInputStream;
            if (fileInputStream2 != null) {
                try {
                    fileInputStream2.close();
                } catch (Throwable unused2) {
                }
            }
            throw th;
        }
    }

    public static TaskTable parse(InputStream inputStream) throws IOException {
        return parse(new InputStreamReader(inputStream, Key.STRING_CHARSET_NAME));
    }

    public static TaskTable parse(Reader reader) throws IOException {
        TaskTable taskTable = new TaskTable();
        BufferedReader bufferedReader = new BufferedReader(reader);
        while (true) {
            try {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    return taskTable;
                }
                try {
                    parseLine(taskTable, readLine);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } finally {
                reader.close();
            }
        }
    }

    public static TaskTable parse(URL url) throws IOException {
        InputStream inputStream;
        try {
            inputStream = url.openStream();
        } catch (Throwable th) {
            th = th;
            inputStream = null;
        }
        try {
            TaskTable parse = parse(inputStream);
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Throwable unused) {
                }
            }
            return parse;
        } catch (Throwable th2) {
            th = th2;
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Throwable unused2) {
                }
            }
            throw th;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:27:0x0067, code lost:
    
        r11 = true;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void parseLine(it.sauronsoftware.cron4j.TaskTable r18, java.lang.String r19) throws java.lang.Exception {
        /*
            Method dump skipped, instructions count: 627
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: it.sauronsoftware.cron4j.CronParser.parseLine(it.sauronsoftware.cron4j.TaskTable, java.lang.String):void");
    }
}
