package com.elvishew.xlog;

import com.elvishew.xlog.LogConfiguration;
import com.elvishew.xlog.formatter.border.BorderFormatter;
import com.elvishew.xlog.formatter.message.json.JsonFormatter;
import com.elvishew.xlog.formatter.message.object.ObjectFormatter;
import com.elvishew.xlog.formatter.message.throwable.ThrowableFormatter;
import com.elvishew.xlog.formatter.message.xml.XmlFormatter;
import com.elvishew.xlog.formatter.stacktrace.StackTraceFormatter;
import com.elvishew.xlog.formatter.thread.ThreadFormatter;
import com.elvishew.xlog.interceptor.Interceptor;
import com.elvishew.xlog.internal.DefaultsFactory;
import com.elvishew.xlog.internal.Platform;
import com.elvishew.xlog.internal.SystemCompat;
import com.elvishew.xlog.internal.util.StackTraceUtil;
import com.elvishew.xlog.printer.Printer;
import com.elvishew.xlog.printer.PrinterSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class Logger {
    private LogConfiguration logConfiguration;
    private Printer printer;

    /* loaded from: classes.dex */
    public static class Builder {
        private BorderFormatter borderFormatter;
        private boolean borderSet;
        private List<Interceptor> interceptors;
        private JsonFormatter jsonFormatter;
        private int logLevel;
        private Map<Class<?>, ObjectFormatter<?>> objectFormatters;
        private Printer printer;
        private int stackTraceDepth;
        private StackTraceFormatter stackTraceFormatter;
        private String stackTraceOrigin;
        private boolean stackTraceSet;
        private String tag;
        private ThreadFormatter threadFormatter;
        private boolean threadSet;
        private ThrowableFormatter throwableFormatter;
        private boolean withBorder;
        private boolean withStackTrace;
        private boolean withThread;
        private XmlFormatter xmlFormatter;

        public Builder() {
            XLog.assertInitialization();
        }

        public Builder addInterceptor(Interceptor interceptor) {
            if (this.interceptors == null) {
                this.interceptors = new ArrayList();
            }
            this.interceptors.add(interceptor);
            return this;
        }

        public <T> Builder addObjectFormatter(Class<T> cls, ObjectFormatter<? super T> objectFormatter) {
            if (this.objectFormatters == null) {
                this.objectFormatters = new HashMap(DefaultsFactory.builtinObjectFormatters());
            }
            this.objectFormatters.put(cls, objectFormatter);
            return this;
        }

        @Deprecated
        public Builder b() {
            return enableBorder();
        }

        public Builder borderFormatter(BorderFormatter borderFormatter) {
            this.borderFormatter = borderFormatter;
            return this;
        }

        public Logger build() {
            return new Logger(this);
        }

        public void d(Object obj) {
            build().d(obj);
        }

        public void d(String str) {
            build().d(str);
        }

        public void d(String str, Throwable th) {
            build().d(str, th);
        }

        public void d(String str, Object... objArr) {
            build().d(str, objArr);
        }

        public void d(Object[] objArr) {
            build().d(objArr);
        }

        public Builder disableBorder() {
            this.withBorder = false;
            this.borderSet = true;
            return this;
        }

        public Builder disableStackTrace() {
            this.withStackTrace = false;
            this.stackTraceOrigin = null;
            this.stackTraceDepth = 0;
            this.stackTraceSet = true;
            return this;
        }

        public Builder disableThreadInfo() {
            this.withThread = false;
            this.threadSet = true;
            return this;
        }

        public void e(Object obj) {
            build().e(obj);
        }

        public void e(String str) {
            build().e(str);
        }

        public void e(String str, Throwable th) {
            build().e(str, th);
        }

        public void e(String str, Object... objArr) {
            build().e(str, objArr);
        }

        public void e(Object[] objArr) {
            build().e(objArr);
        }

        public Builder enableBorder() {
            this.withBorder = true;
            this.borderSet = true;
            return this;
        }

        public Builder enableStackTrace(int i) {
            this.withStackTrace = true;
            this.stackTraceDepth = i;
            this.stackTraceSet = true;
            return this;
        }

        public Builder enableStackTrace(String str, int i) {
            this.withStackTrace = true;
            this.stackTraceOrigin = str;
            this.stackTraceDepth = i;
            this.stackTraceSet = true;
            return this;
        }

        public Builder enableThreadInfo() {
            this.withThread = true;
            this.threadSet = true;
            return this;
        }

        public void i(Object obj) {
            build().i(obj);
        }

        public void i(String str) {
            build().i(str);
        }

        public void i(String str, Throwable th) {
            build().i(str, th);
        }

        public void i(String str, Object... objArr) {
            build().i(str, objArr);
        }

        public void i(Object[] objArr) {
            build().i(objArr);
        }

        public void json(String str) {
            build().json(str);
        }

        public Builder jsonFormatter(JsonFormatter jsonFormatter) {
            this.jsonFormatter = jsonFormatter;
            return this;
        }

        public void log(int i, Object obj) {
            build().log(i, obj);
        }

        public void log(int i, String str) {
            build().log(i, str);
        }

        public void log(int i, String str, Throwable th) {
            build().log(i, str, th);
        }

        public void log(int i, String str, Object... objArr) {
            build().log(i, str, objArr);
        }

        public void log(int i, Object[] objArr) {
            build().log(i, objArr);
        }

        public Builder logLevel(int i) {
            this.logLevel = i;
            return this;
        }

        @Deprecated
        public Builder nb() {
            return disableBorder();
        }

        @Deprecated
        public Builder nst() {
            return disableStackTrace();
        }

        @Deprecated
        public Builder nt() {
            return disableThreadInfo();
        }

        public Builder printers(Printer... printerArr) {
            if (printerArr.length == 0) {
                this.printer = null;
            } else if (printerArr.length == 1) {
                this.printer = printerArr[0];
            } else {
                this.printer = new PrinterSet(printerArr);
            }
            return this;
        }

        @Deprecated
        public Builder st(int i) {
            return enableStackTrace(i);
        }

        @Deprecated
        public Builder st(String str, int i) {
            return enableStackTrace(str, i);
        }

        public Builder stackTraceFormatter(StackTraceFormatter stackTraceFormatter) {
            this.stackTraceFormatter = stackTraceFormatter;
            return this;
        }

        @Deprecated
        public Builder t() {
            return enableThreadInfo();
        }

        public Builder tag(String str) {
            this.tag = str;
            return this;
        }

        public Builder threadFormatter(ThreadFormatter threadFormatter) {
            this.threadFormatter = threadFormatter;
            return this;
        }

        public Builder throwableFormatter(ThrowableFormatter throwableFormatter) {
            this.throwableFormatter = throwableFormatter;
            return this;
        }

        public void v(Object obj) {
            build().v(obj);
        }

        public void v(String str) {
            build().v(str);
        }

        public void v(String str, Throwable th) {
            build().v(str, th);
        }

        public void v(String str, Object... objArr) {
            build().v(str, objArr);
        }

        public void v(Object[] objArr) {
            build().v(objArr);
        }

        public void w(Object obj) {
            build().w(obj);
        }

        public void w(String str) {
            build().w(str);
        }

        public void w(String str, Throwable th) {
            build().w(str, th);
        }

        public void w(String str, Object... objArr) {
            build().w(str, objArr);
        }

        public void w(Object[] objArr) {
            build().w(objArr);
        }

        public void xml(String str) {
            build().xml(str);
        }

        public Builder xmlFormatter(XmlFormatter xmlFormatter) {
            this.xmlFormatter = xmlFormatter;
            return this;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Logger(LogConfiguration logConfiguration, Printer printer) {
        this.logConfiguration = logConfiguration;
        this.printer = printer;
    }

    Logger(Builder builder) {
        LogConfiguration.Builder builder2 = new LogConfiguration.Builder(XLog.sLogConfiguration);
        if (builder.logLevel != 0) {
            builder2.logLevel(builder.logLevel);
        }
        if (builder.tag != null) {
            builder2.tag(builder.tag);
        }
        if (builder.threadSet) {
            if (builder.withThread) {
                builder2.enableThreadInfo();
            } else {
                builder2.disableThreadInfo();
            }
        }
        if (builder.stackTraceSet) {
            if (builder.withStackTrace) {
                builder2.enableStackTrace(builder.stackTraceOrigin, builder.stackTraceDepth);
            } else {
                builder2.disableStackTrace();
            }
        }
        if (builder.borderSet) {
            if (builder.withBorder) {
                builder2.enableBorder();
            } else {
                builder2.disableBorder();
            }
        }
        if (builder.jsonFormatter != null) {
            builder2.jsonFormatter(builder.jsonFormatter);
        }
        if (builder.xmlFormatter != null) {
            builder2.xmlFormatter(builder.xmlFormatter);
        }
        if (builder.throwableFormatter != null) {
            builder2.throwableFormatter(builder.throwableFormatter);
        }
        if (builder.threadFormatter != null) {
            builder2.threadFormatter(builder.threadFormatter);
        }
        if (builder.stackTraceFormatter != null) {
            builder2.stackTraceFormatter(builder.stackTraceFormatter);
        }
        if (builder.borderFormatter != null) {
            builder2.borderFormatter(builder.borderFormatter);
        }
        if (builder.objectFormatters != null) {
            builder2.objectFormatters(builder.objectFormatters);
        }
        if (builder.interceptors != null) {
            builder2.interceptors(builder.interceptors);
        }
        this.logConfiguration = builder2.build();
        if (builder.printer != null) {
            this.printer = builder.printer;
        } else {
            this.printer = XLog.sPrinter;
        }
    }

    private String formatArgs(String str, Object... objArr) {
        if (str != null) {
            return String.format(str, objArr);
        }
        StringBuilder sb = new StringBuilder();
        int length = objArr.length;
        for (int i = 0; i < length; i++) {
            if (i != 0) {
                sb.append(", ");
            }
            sb.append(objArr[i]);
        }
        return sb.toString();
    }

    private <T> void println(int i, T t) {
        String str;
        if (i < this.logConfiguration.logLevel) {
            return;
        }
        if (t != null) {
            ObjectFormatter<? super T> objectFormatter = this.logConfiguration.getObjectFormatter(t);
            str = objectFormatter != null ? objectFormatter.format(t) : t.toString();
        } else {
            str = "null";
        }
        printlnInternal(i, str);
    }

    private void println(int i, String str, Throwable th) {
        String str2;
        if (i < this.logConfiguration.logLevel) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        if (str == null || str.length() == 0) {
            str2 = "";
        } else {
            str2 = str + SystemCompat.lineSeparator;
        }
        sb.append(str2);
        sb.append(this.logConfiguration.throwableFormatter.format(th));
        printlnInternal(i, sb.toString());
    }

    private void println(int i, String str, Object... objArr) {
        if (i < this.logConfiguration.logLevel) {
            return;
        }
        printlnInternal(i, formatArgs(str, objArr));
    }

    private void println(int i, Object[] objArr) {
        if (i < this.logConfiguration.logLevel) {
            return;
        }
        printlnInternal(i, Arrays.deepToString(objArr));
    }

    private void printlnInternal(int i, String str) {
        String sb;
        String str2 = this.logConfiguration.tag;
        String format = this.logConfiguration.withThread ? this.logConfiguration.threadFormatter.format(Thread.currentThread()) : null;
        String format2 = this.logConfiguration.withStackTrace ? this.logConfiguration.stackTraceFormatter.format(StackTraceUtil.getCroppedRealStackTrack(new Throwable().getStackTrace(), this.logConfiguration.stackTraceOrigin, this.logConfiguration.stackTraceDepth)) : null;
        if (this.logConfiguration.interceptors != null) {
            LogItem logItem = new LogItem(i, str2, format, format2, str);
            for (Interceptor interceptor : this.logConfiguration.interceptors) {
                logItem = interceptor.intercept(logItem);
                if (logItem == null) {
                    return;
                }
                if (logItem.tag == null || logItem.msg == null) {
                    Platform.get().error("Interceptor " + interceptor + " should not remove the tag or message of a log, if you don't want to print this log, just return a null when intercept.");
                    return;
                }
            }
            i = logItem.level;
            str2 = logItem.tag;
            format = logItem.threadInfo;
            format2 = logItem.stackTraceInfo;
            str = logItem.msg;
        }
        Printer printer = this.printer;
        if (this.logConfiguration.withBorder) {
            sb = this.logConfiguration.borderFormatter.format(new String[]{format, format2, str});
        } else {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(format != null ? format + SystemCompat.lineSeparator : "");
            sb2.append(format2 != null ? format2 + SystemCompat.lineSeparator : "");
            sb2.append(str);
            sb = sb2.toString();
        }
        printer.println(i, str2, sb);
    }

    public void d(Object obj) {
        println(3, (int) obj);
    }

    public void d(String str) {
        println(3, str);
    }

    public void d(String str, Throwable th) {
        println(3, str, th);
    }

    public void d(String str, Object... objArr) {
        println(3, str, objArr);
    }

    public void d(Object[] objArr) {
        println(3, objArr);
    }

    public void e(Object obj) {
        println(6, (int) obj);
    }

    public void e(String str) {
        println(6, str);
    }

    public void e(String str, Throwable th) {
        println(6, str, th);
    }

    public void e(String str, Object... objArr) {
        println(6, str, objArr);
    }

    public void e(Object[] objArr) {
        println(6, objArr);
    }

    public void i(Object obj) {
        println(4, (int) obj);
    }

    public void i(String str) {
        println(4, str);
    }

    public void i(String str, Throwable th) {
        println(4, str, th);
    }

    public void i(String str, Object... objArr) {
        println(4, str, objArr);
    }

    public void i(Object[] objArr) {
        println(4, objArr);
    }

    public void json(String str) {
        if (3 < this.logConfiguration.logLevel) {
            return;
        }
        printlnInternal(3, this.logConfiguration.jsonFormatter.format(str));
    }

    public void log(int i, Object obj) {
        println(i, (int) obj);
    }

    public void log(int i, String str) {
        println(i, str);
    }

    public void log(int i, String str, Throwable th) {
        println(i, str, th);
    }

    public void log(int i, String str, Object... objArr) {
        println(i, str, objArr);
    }

    public void log(int i, Object[] objArr) {
        println(i, objArr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void println(int i, String str) {
        if (i < this.logConfiguration.logLevel) {
            return;
        }
        if (str == null) {
            str = "";
        }
        printlnInternal(i, str);
    }

    public void v(Object obj) {
        println(2, (int) obj);
    }

    public void v(String str) {
        println(2, str);
    }

    public void v(String str, Throwable th) {
        println(2, str, th);
    }

    public void v(String str, Object... objArr) {
        println(2, str, objArr);
    }

    public void v(Object[] objArr) {
        println(2, objArr);
    }

    public void w(Object obj) {
        println(5, (int) obj);
    }

    public void w(String str) {
        println(5, str);
    }

    public void w(String str, Throwable th) {
        println(5, str, th);
    }

    public void w(String str, Object... objArr) {
        println(5, str, objArr);
    }

    public void w(Object[] objArr) {
        println(5, objArr);
    }

    public void xml(String str) {
        if (3 < this.logConfiguration.logLevel) {
            return;
        }
        printlnInternal(3, this.logConfiguration.xmlFormatter.format(str));
    }
}
