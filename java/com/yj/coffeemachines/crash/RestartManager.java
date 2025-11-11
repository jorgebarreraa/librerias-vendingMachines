package com.yj.coffeemachines.crash;

import com.yj.coffeemachines.app.utils.KvUtil;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/* loaded from: classes.dex */
public class RestartManager {
    private static final int MAX_RESTARTS_PER_DAY = 20;
    private static RestartManager restartManager;
    private DateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");

    private RestartManager() {
    }

    private String getCurrentDate() {
        return this.formatter.format(new Date(System.currentTimeMillis()));
    }

    public static RestartManager getInstance() {
        if (restartManager == null) {
            restartManager = new RestartManager();
        }
        return restartManager;
    }

    private int getTodayRestartCount() {
        if (getCurrentDate().equals(KvUtil.getInstance().getString("last_restart_date", ""))) {
            return KvUtil.getInstance().getInt("restart_count", 0);
        }
        return 0;
    }

    private void incrementRestartCount() {
        KvUtil.getInstance().putInt("restart_count", getTodayRestartCount() + 1);
        KvUtil.getInstance().putString("last_restart_date", getCurrentDate());
    }

    public boolean canRestart() {
        return getTodayRestartCount() < 20;
    }
}
