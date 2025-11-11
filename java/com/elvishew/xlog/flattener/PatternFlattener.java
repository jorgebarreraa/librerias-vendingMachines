package com.elvishew.xlog.flattener;

import com.elvishew.xlog.LogLevel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: classes.dex */
public class PatternFlattener implements Flattener, Flattener2 {
    static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";
    private static final String PARAM = "[^{}]*";
    private static final String PARAMETER_DATE = "d";
    private static final String PARAMETER_LEVEL_LONG = "L";
    private static final String PARAMETER_LEVEL_SHORT = "l";
    private static final String PARAMETER_MESSAGE = "m";
    private static final String PARAMETER_TAG = "t";
    private static final Pattern PARAM_REGEX = Pattern.compile("\\{([^{}]*)\\}");
    private List<ParameterFiller> parameterFillers;
    private String pattern;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class DateFiller extends ParameterFiller {
        String dateFormat;
        private ThreadLocal<SimpleDateFormat> threadLocalDateFormat;

        DateFiller(String str, String str2, String str3) {
            super(str, str2);
            this.threadLocalDateFormat = new ThreadLocal<SimpleDateFormat>() { // from class: com.elvishew.xlog.flattener.PatternFlattener.DateFiller.1
                /* JADX INFO: Access modifiers changed from: protected */
                @Override // java.lang.ThreadLocal
                public SimpleDateFormat initialValue() {
                    return new SimpleDateFormat(DateFiller.this.dateFormat, Locale.US);
                }
            };
            this.dateFormat = str3;
            try {
                this.threadLocalDateFormat.get().format(new Date());
            } catch (Exception e) {
                throw new IllegalArgumentException("Bad date pattern: " + str3, e);
            }
        }

        @Override // com.elvishew.xlog.flattener.PatternFlattener.ParameterFiller
        protected String fill(String str, long j, int i, String str2, String str3) {
            return str.replace(this.wrappedParameter, this.threadLocalDateFormat.get().format(new Date(j)));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class LevelFiller extends ParameterFiller {
        boolean useLongName;

        LevelFiller(String str, String str2, boolean z) {
            super(str, str2);
            this.useLongName = z;
        }

        @Override // com.elvishew.xlog.flattener.PatternFlattener.ParameterFiller
        protected String fill(String str, long j, int i, String str2, String str3) {
            return this.useLongName ? str.replace(this.wrappedParameter, LogLevel.getLevelName(i)) : str.replace(this.wrappedParameter, LogLevel.getShortLevelName(i));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class MessageFiller extends ParameterFiller {
        MessageFiller(String str, String str2) {
            super(str, str2);
        }

        @Override // com.elvishew.xlog.flattener.PatternFlattener.ParameterFiller
        protected String fill(String str, long j, int i, String str2, String str3) {
            return str.replace(this.wrappedParameter, str3);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static abstract class ParameterFiller {
        String trimmedParameter;
        String wrappedParameter;

        ParameterFiller(String str, String str2) {
            this.wrappedParameter = str;
            this.trimmedParameter = str2;
        }

        protected abstract String fill(String str, long j, int i, String str2, String str3);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class TagFiller extends ParameterFiller {
        TagFiller(String str, String str2) {
            super(str, str2);
        }

        @Override // com.elvishew.xlog.flattener.PatternFlattener.ParameterFiller
        protected String fill(String str, long j, int i, String str2, String str3) {
            return str.replace(this.wrappedParameter, str2);
        }
    }

    public PatternFlattener(String str) {
        if (str == null) {
            throw new NullPointerException("Pattern should not be null");
        }
        this.pattern = str;
        this.parameterFillers = parseParameters(parsePattern(str));
        if (this.parameterFillers.size() != 0) {
            return;
        }
        throw new IllegalArgumentException("No recognizable parameter found in the pattern " + str);
    }

    static DateFiller parseDateParameter(String str, String str2) {
        if (str2.startsWith("d ") && str2.length() > 2) {
            return new DateFiller(str, str2, str2.substring(2));
        }
        if (str2.equals(PARAMETER_DATE)) {
            return new DateFiller(str, str2, DEFAULT_DATE_FORMAT);
        }
        return null;
    }

    static LevelFiller parseLevelParameter(String str, String str2) {
        if (str2.equals(PARAMETER_LEVEL_SHORT)) {
            return new LevelFiller(str, str2, false);
        }
        if (str2.equals(PARAMETER_LEVEL_LONG)) {
            return new LevelFiller(str, str2, true);
        }
        return null;
    }

    static MessageFiller parseMessageParameter(String str, String str2) {
        if (str2.equals(PARAMETER_MESSAGE)) {
            return new MessageFiller(str, str2);
        }
        return null;
    }

    private static ParameterFiller parseParameter(String str) {
        String str2 = "{" + str + "}";
        String trim = str.trim();
        DateFiller parseDateParameter = parseDateParameter(str2, trim);
        if (parseDateParameter != null) {
            return parseDateParameter;
        }
        LevelFiller parseLevelParameter = parseLevelParameter(str2, trim);
        if (parseLevelParameter != null) {
            return parseLevelParameter;
        }
        TagFiller parseTagParameter = parseTagParameter(str2, trim);
        if (parseTagParameter != null) {
            return parseTagParameter;
        }
        MessageFiller parseMessageParameter = parseMessageParameter(str2, trim);
        if (parseMessageParameter != null) {
            return parseMessageParameter;
        }
        return null;
    }

    private static List<ParameterFiller> parseParameters(List<String> list) {
        ArrayList arrayList = new ArrayList(list.size());
        Iterator<String> it2 = list.iterator();
        while (it2.hasNext()) {
            ParameterFiller parseParameter = parseParameter(it2.next());
            if (parseParameter != null) {
                arrayList.add(parseParameter);
            }
        }
        return arrayList;
    }

    static List<String> parsePattern(String str) {
        ArrayList arrayList = new ArrayList(4);
        Matcher matcher = PARAM_REGEX.matcher(str);
        while (matcher.find()) {
            arrayList.add(matcher.group(1));
        }
        return arrayList;
    }

    static TagFiller parseTagParameter(String str, String str2) {
        if (str2.equals(PARAMETER_TAG)) {
            return new TagFiller(str, str2);
        }
        return null;
    }

    @Override // com.elvishew.xlog.flattener.Flattener
    public CharSequence flatten(int i, String str, String str2) {
        return flatten(System.currentTimeMillis(), i, str, str2);
    }

    @Override // com.elvishew.xlog.flattener.Flattener2
    public CharSequence flatten(long j, int i, String str, String str2) {
        String str3 = this.pattern;
        Iterator<ParameterFiller> it2 = this.parameterFillers.iterator();
        String str4 = str3;
        while (it2.hasNext()) {
            str4 = it2.next().fill(str4, j, i, str, str2);
        }
        return str4;
    }
}
