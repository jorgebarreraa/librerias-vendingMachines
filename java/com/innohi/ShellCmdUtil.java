package com.innohi;

import android.util.Log;
import com.yj.coffeemachines.app.serialport.ShellUtils;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Paths;

/* loaded from: classes.dex */
public class ShellCmdUtil {
    private static final String TAG = "ShellCmdUtil";

    private ShellCmdUtil() {
    }

    public static boolean executeCmd(String str) throws IOException, InterruptedException {
        Process exec = Files.exists(Paths.get("/system/xbin/su", new String[0]), new LinkOption[0]) ? Runtime.getRuntime().exec(ShellUtils.COMMAND_SU) : Runtime.getRuntime().exec("ssu");
        PrintWriter printWriter = new PrintWriter(exec.getOutputStream(), true);
        printWriter.println(str);
        printWriter.println("exit");
        int waitFor = exec.waitFor();
        Log.d(TAG, "process " + str + " exit value = " + waitFor);
        return waitFor == 0;
    }
}
