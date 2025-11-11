package com.yj.coffeemachines.app.serialport;

import java.util.List;

/* loaded from: classes.dex */
public class ShellUtils {
    public static final String COMMAND_EXIT = "exit\n";
    public static final String COMMAND_LINE_END = "\n";
    public static final String COMMAND_SH = "sh";
    public static final String COMMAND_SU = "su";

    /* loaded from: classes.dex */
    public static class CommandResult {
        public String errorMsg;
        public int result;
        public String successMsg;

        public CommandResult(int i) {
            this.result = i;
        }

        public CommandResult(int i, String str, String str2) {
            this.result = i;
            this.successMsg = str;
            this.errorMsg = str2;
        }

        public String toString() {
            return "CommandResult{result=" + this.result + ", successMsg='" + this.successMsg + "', errorMsg='" + this.errorMsg + "'}";
        }
    }

    private ShellUtils() {
        throw new AssertionError();
    }

    public static boolean checkRootPermission() {
        return execCommand("echo root", true, false).result == 0;
    }

    public static CommandResult execCommand(String str, boolean z) {
        return execCommand(new String[]{str}, z, true);
    }

    public static CommandResult execCommand(String str, boolean z, boolean z2) {
        return execCommand(new String[]{str}, z, z2);
    }

    public static CommandResult execCommand(List list, boolean z) {
        return execCommand(list == null ? null : (String[]) list.toArray(new String[0]), z, true);
    }

    public static CommandResult execCommand(List list, boolean z, boolean z2) {
        return execCommand(list == null ? null : (String[]) list.toArray(new String[0]), z, z2);
    }

    public static CommandResult execCommand(String[] strArr, boolean z) {
        return execCommand(strArr, z, true);
    }

    /* JADX WARN: Can't wrap try/catch for region: R(13:(11:6|7|(1:9)(1:148)|10|11|12|13|14|(3:16|(2:18|19)(2:21|22)|20)|23|24)|(19:26|28|29|31|32|34|35|(3:36|37|(1:39)(1:40))|(2:41|(1:43)(0))|45|(1:47)|(1:49)|(1:52)|53|54|(1:56)(1:62)|(1:58)|59|60)(1:128)|44|45|(0)|(0)|(0)|53|54|(0)(0)|(0)|59|60) */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x00da, code lost:
    
        r2 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x00db, code lost:
    
        r2.printStackTrace();
     */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x0148, code lost:
    
        if (r10 != null) goto L94;
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x0124, code lost:
    
        r10.destroy();
     */
    /* JADX WARN: Code restructure failed: missing block: B:94:0x0122, code lost:
    
        if (r10 != null) goto L94;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:107:0x016c A[Catch: IOException -> 0x0168, TryCatch #13 {IOException -> 0x0168, blocks: (B:119:0x0164, B:107:0x016c, B:109:0x0171), top: B:118:0x0164 }] */
    /* JADX WARN: Removed duplicated region for block: B:109:0x0171 A[Catch: IOException -> 0x0168, TRY_LEAVE, TryCatch #13 {IOException -> 0x0168, blocks: (B:119:0x0164, B:107:0x016c, B:109:0x0171), top: B:118:0x0164 }] */
    /* JADX WARN: Removed duplicated region for block: B:115:0x017a  */
    /* JADX WARN: Removed duplicated region for block: B:117:? A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:118:0x0164 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:47:0x00d1 A[Catch: IOException -> 0x00da, TryCatch #2 {IOException -> 0x00da, blocks: (B:45:0x00cc, B:47:0x00d1, B:49:0x00d6), top: B:44:0x00cc }] */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00d6 A[Catch: IOException -> 0x00da, TRY_LEAVE, TryCatch #2 {IOException -> 0x00da, blocks: (B:45:0x00cc, B:47:0x00d1, B:49:0x00d6), top: B:44:0x00cc }] */
    /* JADX WARN: Removed duplicated region for block: B:52:0x00e0  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x014f  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0158  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x0151  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x013c A[Catch: IOException -> 0x0138, TryCatch #3 {IOException -> 0x0138, blocks: (B:81:0x0134, B:71:0x013c, B:73:0x0141), top: B:80:0x0134 }] */
    /* JADX WARN: Removed duplicated region for block: B:73:0x0141 A[Catch: IOException -> 0x0138, TRY_LEAVE, TryCatch #3 {IOException -> 0x0138, blocks: (B:81:0x0134, B:71:0x013c, B:73:0x0141), top: B:80:0x0134 }] */
    /* JADX WARN: Removed duplicated region for block: B:80:0x0134 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:87:0x0116 A[Catch: IOException -> 0x0112, TryCatch #7 {IOException -> 0x0112, blocks: (B:96:0x010e, B:87:0x0116, B:89:0x011b), top: B:95:0x010e }] */
    /* JADX WARN: Removed duplicated region for block: B:89:0x011b A[Catch: IOException -> 0x0112, TRY_LEAVE, TryCatch #7 {IOException -> 0x0112, blocks: (B:96:0x010e, B:87:0x0116, B:89:0x011b), top: B:95:0x010e }] */
    /* JADX WARN: Removed duplicated region for block: B:95:0x010e A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r10v0, types: [boolean] */
    /* JADX WARN: Type inference failed for: r10v19, types: [java.lang.Process] */
    /* JADX WARN: Type inference failed for: r10v2 */
    /* JADX WARN: Type inference failed for: r11v10 */
    /* JADX WARN: Type inference failed for: r11v11, types: [java.lang.StringBuilder] */
    /* JADX WARN: Type inference failed for: r11v12 */
    /* JADX WARN: Type inference failed for: r11v13 */
    /* JADX WARN: Type inference failed for: r11v14 */
    /* JADX WARN: Type inference failed for: r11v15 */
    /* JADX WARN: Type inference failed for: r11v18 */
    /* JADX WARN: Type inference failed for: r11v2 */
    /* JADX WARN: Type inference failed for: r11v20 */
    /* JADX WARN: Type inference failed for: r11v22 */
    /* JADX WARN: Type inference failed for: r11v24 */
    /* JADX WARN: Type inference failed for: r11v25 */
    /* JADX WARN: Type inference failed for: r11v26 */
    /* JADX WARN: Type inference failed for: r11v27 */
    /* JADX WARN: Type inference failed for: r11v28 */
    /* JADX WARN: Type inference failed for: r11v29 */
    /* JADX WARN: Type inference failed for: r11v3 */
    /* JADX WARN: Type inference failed for: r11v4 */
    /* JADX WARN: Type inference failed for: r11v5 */
    /* JADX WARN: Type inference failed for: r11v6 */
    /* JADX WARN: Type inference failed for: r11v7 */
    /* JADX WARN: Type inference failed for: r11v8 */
    /* JADX WARN: Type inference failed for: r11v9 */
    /* JADX WARN: Type inference failed for: r2v0, types: [int] */
    /* JADX WARN: Type inference failed for: r2v1, types: [java.io.DataOutputStream] */
    /* JADX WARN: Type inference failed for: r2v10, types: [java.io.DataOutputStream] */
    /* JADX WARN: Type inference failed for: r2v13, types: [java.io.DataOutputStream] */
    /* JADX WARN: Type inference failed for: r2v15 */
    /* JADX WARN: Type inference failed for: r2v16 */
    /* JADX WARN: Type inference failed for: r2v17 */
    /* JADX WARN: Type inference failed for: r2v18 */
    /* JADX WARN: Type inference failed for: r2v19 */
    /* JADX WARN: Type inference failed for: r2v2 */
    /* JADX WARN: Type inference failed for: r2v20 */
    /* JADX WARN: Type inference failed for: r2v21 */
    /* JADX WARN: Type inference failed for: r2v22 */
    /* JADX WARN: Type inference failed for: r2v23 */
    /* JADX WARN: Type inference failed for: r2v24 */
    /* JADX WARN: Type inference failed for: r2v25 */
    /* JADX WARN: Type inference failed for: r2v26 */
    /* JADX WARN: Type inference failed for: r2v27 */
    /* JADX WARN: Type inference failed for: r2v9, types: [java.io.DataOutputStream] */
    /* JADX WARN: Type inference failed for: r5v0 */
    /* JADX WARN: Type inference failed for: r5v1 */
    /* JADX WARN: Type inference failed for: r5v10 */
    /* JADX WARN: Type inference failed for: r5v11 */
    /* JADX WARN: Type inference failed for: r5v12 */
    /* JADX WARN: Type inference failed for: r5v15 */
    /* JADX WARN: Type inference failed for: r5v17 */
    /* JADX WARN: Type inference failed for: r5v2 */
    /* JADX WARN: Type inference failed for: r5v3 */
    /* JADX WARN: Type inference failed for: r5v4 */
    /* JADX WARN: Type inference failed for: r5v5, types: [java.lang.StringBuilder] */
    /* JADX WARN: Type inference failed for: r5v6 */
    /* JADX WARN: Type inference failed for: r5v7 */
    /* JADX WARN: Type inference failed for: r5v8 */
    /* JADX WARN: Type inference failed for: r5v9 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static com.yj.coffeemachines.app.serialport.ShellUtils.CommandResult execCommand(java.lang.String[] r9, boolean r10, boolean r11) {
        /*
            Method dump skipped, instructions count: 388
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yj.coffeemachines.app.serialport.ShellUtils.execCommand(java.lang.String[], boolean, boolean):com.yj.coffeemachines.app.serialport.ShellUtils$CommandResult");
    }
}
