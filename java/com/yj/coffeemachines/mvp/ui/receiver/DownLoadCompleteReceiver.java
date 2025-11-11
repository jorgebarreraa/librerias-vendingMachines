package com.yj.coffeemachines.mvp.ui.receiver;

import android.app.DownloadManager;
import android.app.kingsun.KingsunSmartAPI;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
// Hardware propietario comentado
// import android.os.tniuds.TniudsUtils;
import androidx.core.app.NotificationCompat;
// import com.innohi.YNHAPI;
import com.jess.arms.utils.ArmsUtils;
import com.yj.coffeemachines.BuildConfig;
import com.yj.coffeemachines.Constants;
import com.yj.coffeemachines.MyAppLocation;
import java.io.File;

/* loaded from: classes.dex */
public class DownLoadCompleteReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if ("android.intent.action.DOWNLOAD_COMPLETE".equals(intent.getAction())) {
            long longExtra = intent.getLongExtra("extra_download_id", 0L);
            ArmsUtils.snackbarText("en已经下载完成");
            Cursor query = ((DownloadManager) context.getSystemService("download")).query(new DownloadManager.Query().setFilterById(longExtra));
            try {
                if (query != null) {
                    if (query.moveToFirst()) {
                        String string = query.getString(query.getColumnIndex("local_filename"));
                        if (query.getInt(query.getColumnIndexOrThrow(NotificationCompat.CATEGORY_STATUS)) == 8) {
                            Uri.fromFile(new File(string));
                            if (Constants.Model == 2) {
                                TniudsUtils.installAppCover(MyAppLocation.myAppLocation, new File(string));
                            } else if (Constants.Model == 0) {
                                ((KingsunSmartAPI) MyAppLocation.myAppLocation.getSystemService("kingsunsmartapi")).installApk(string);
                            } else if (Constants.Model == 1) {
                                YNHAPI.getInstance().installApkSilently(string, BuildConfig.APPLICATION_ID, "com.yj.coffeemachines.mvp.ui.activity.HomeActivity");
                            } else if (Constants.Model == 3) {
                                Intent intent2 = new Intent("com.ys.installapk");
                                intent2.putExtra("uri", string);
                                intent2.putExtra("start", 1);
                                intent2.putExtra("pkgName", BuildConfig.APPLICATION_ID);
                                intent2.putExtra("activityName", "com.yj.coffeemachines.mvp.ui.activity.MainActivity");
                                MyAppLocation.myAppLocation.getApplicationContext().sendBroadcast(intent2);
                            }
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                query.close();
            }
        }
    }
}
