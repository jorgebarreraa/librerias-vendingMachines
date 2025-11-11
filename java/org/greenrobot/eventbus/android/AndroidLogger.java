package org.greenrobot.eventbus.android;

import android.util.Log;
import com.yj.coffeemachines.app.serialport.ShellUtils;
import java.util.logging.Level;
import org.greenrobot.eventbus.Logger;

/* loaded from: classes2.dex */
public class AndroidLogger implements Logger {
    private static final boolean ANDROID_LOG_AVAILABLE;
    private final String tag;

    static {
        boolean z = false;
        try {
            if (Class.forName("android.util.Log") != null) {
                z = true;
            }
        } catch (ClassNotFoundException unused) {
        }
        ANDROID_LOG_AVAILABLE = z;
    }

    public AndroidLogger(String str) {
        this.tag = str;
    }

    public static boolean isAndroidLogAvailable() {
        return ANDROID_LOG_AVAILABLE;
    }

    private int mapLevel(Level level) {
        int intValue = level.intValue();
        if (intValue < 800) {
            return intValue < 500 ? 2 : 3;
        }
        if (intValue < 900) {
            return 4;
        }
        return intValue < 1000 ? 5 : 6;
    }

    @Override // org.greenrobot.eventbus.Logger
    public void log(Level level, String str) {
        if (level != Level.OFF) {
            Log.println(mapLevel(level), this.tag, str);
        }
    }

    @Override // org.greenrobot.eventbus.Logger
    public void log(Level level, String str, Throwable th) {
        if (level != Level.OFF) {
            Log.println(mapLevel(level), this.tag, str + ShellUtils.COMMAND_LINE_END + Log.getStackTraceString(th));
        }
    }
}
