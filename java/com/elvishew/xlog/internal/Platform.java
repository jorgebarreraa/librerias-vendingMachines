package com.elvishew.xlog.internal;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import com.elvishew.xlog.formatter.message.object.BundleFormatter;
import com.elvishew.xlog.formatter.message.object.IntentFormatter;
import com.elvishew.xlog.formatter.message.object.ObjectFormatter;
import com.elvishew.xlog.printer.AndroidPrinter;
import com.elvishew.xlog.printer.ConsolePrinter;
import com.elvishew.xlog.printer.Printer;
import com.yj.coffeemachines.app.serialport.ShellUtils;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public class Platform {
    private static final Platform PLATFORM = findPlatform();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class Android extends Platform {
        private static final Map<Class<?>, ObjectFormatter<?>> BUILTIN_OBJECT_FORMATTERS;

        static {
            HashMap hashMap = new HashMap();
            hashMap.put(Bundle.class, new BundleFormatter());
            hashMap.put(Intent.class, new IntentFormatter());
            BUILTIN_OBJECT_FORMATTERS = Collections.unmodifiableMap(hashMap);
        }

        Android() {
        }

        @Override // com.elvishew.xlog.internal.Platform
        Map<Class<?>, ObjectFormatter<?>> builtinObjectFormatters() {
            return BUILTIN_OBJECT_FORMATTERS;
        }

        @Override // com.elvishew.xlog.internal.Platform
        Printer defaultPrinter() {
            return new AndroidPrinter();
        }

        @Override // com.elvishew.xlog.internal.Platform
        public void error(String str) {
            Log.e("XLog", str);
        }

        @Override // com.elvishew.xlog.internal.Platform
        String lineSeparator() {
            return Build.VERSION.SDK_INT < 19 ? ShellUtils.COMMAND_LINE_END : System.lineSeparator();
        }

        @Override // com.elvishew.xlog.internal.Platform
        public void warn(String str) {
            Log.w("XLog", str);
        }
    }

    private static Platform findPlatform() {
        try {
            Class.forName("android.os.Build");
            if (Build.VERSION.SDK_INT != 0) {
                return new Android();
            }
        } catch (ClassNotFoundException unused) {
        }
        return new Platform();
    }

    public static Platform get() {
        return PLATFORM;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Map<Class<?>, ObjectFormatter<?>> builtinObjectFormatters() {
        return Collections.emptyMap();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Printer defaultPrinter() {
        return new ConsolePrinter();
    }

    public void error(String str) {
        System.out.println(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @SuppressLint({"NewApi"})
    public String lineSeparator() {
        return System.lineSeparator();
    }

    public void warn(String str) {
        System.out.println(str);
    }
}
