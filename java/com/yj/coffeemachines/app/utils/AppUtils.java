package com.yj.coffeemachines.app.utils;

import android.app.kingsun.KingsunSmartAPI;
import android.content.Context;
// // // // import android.os.tniuds.TniudsUtils; // Hardware propietario no disponible // Hardware propietario no disponible // Hardware propietario no disponible // Hardware propietario no disponible
// // // // import com.innohi.YNHAPI; // Hardware propietario no disponible // Hardware propietario no disponible // Hardware propietario no disponible // Hardware propietario no disponible
import com.yj.coffeemachines.BuildConfig;
import com.yj.coffeemachines.Constants;
import com.yj.coffeemachines.MyAppLocation;
import java.io.File;
import java.io.FileFilter;
import java.util.regex.Pattern;

/* loaded from: classes.dex */
public class AppUtils {
    public static int getNumCores() {
        try {
            return new File("/sys/devices/system/cpu/").listFiles(new FileFilter() { // from class: com.yj.coffeemachines.app.utils.AppUtils.1
                @Override // java.io.FileFilter
                public boolean accept(File file) {
                    return Pattern.matches("cpu[0-9]", file.getName());
                }
            }).length;
        } catch (Exception unused) {
            return 1;
        }
    }

    public static void install(File file) {
        if (Constants.Model == 2) {
            // // // // TniudsUtils.installAppCover(MyAppLocation.myAppLocation, file); // Hardware propietario comentado // Hardware propietario comentado // Hardware propietario comentado // Hardware propietario comentado
            return;
        }
        if (Constants.Model == 0) {
            ((KingsunSmartAPI) MyAppLocation.myAppLocation.getSystemService("kingsunsmartapi")).installApk(file.getAbsolutePath());
        } else if (Constants.Model == 1) {
            // // // // YNHAPI.getInstance().installApkSilently(file.getAbsolutePath(), BuildConfig.APPLICATION_ID, "com.yj.coffeemachines.mvp.ui.activity.TranslucentActivity"); // Hardware propietario comentado // Hardware propietario comentado // Hardware propietario comentado // Hardware propietario comentado
        } else if (Constants.Model == 3) {
            FileUtils.broadcastUpdateApk(file);
        }
    }

    public static boolean isCurOriLand(Context context) {
        int i = context.getResources().getConfiguration().orientation;
        return i == 2 || i != 1;
    }
}
