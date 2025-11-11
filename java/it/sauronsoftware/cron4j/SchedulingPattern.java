package it.sauronsoftware.cron4j;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.TimeZone;
import org.eclipse.paho.client.mqttv3.MqttTopic;

/* loaded from: classes2.dex */
public class SchedulingPattern {
    private String asString;
    private static final ValueParser MINUTE_VALUE_PARSER = new MinuteValueParser();
    private static final ValueParser HOUR_VALUE_PARSER = new HourValueParser();
    private static final ValueParser DAY_OF_MONTH_VALUE_PARSER = new DayOfMonthValueParser();
    private static final ValueParser MONTH_VALUE_PARSER = new MonthValueParser();
    private static final ValueParser DAY_OF_WEEK_VALUE_PARSER = new DayOfWeekValueParser();
    protected ArrayList minuteMatchers = new ArrayList();
    protected ArrayList hourMatchers = new ArrayList();
    protected ArrayList dayOfMonthMatchers = new ArrayList();
    protected ArrayList monthMatchers = new ArrayList();
    protected ArrayList dayOfWeekMatchers = new ArrayList();
    protected int matcherSize = 0;

    /* loaded from: classes2.dex */
    private static class DayOfMonthValueParser extends SimpleValueParser {
        public DayOfMonthValueParser() {
            super(1, 31);
        }

        @Override // it.sauronsoftware.cron4j.SchedulingPattern.SimpleValueParser, it.sauronsoftware.cron4j.SchedulingPattern.ValueParser
        public int parse(String str) throws Exception {
            if (str.equalsIgnoreCase("L")) {
                return 32;
            }
            return super.parse(str);
        }
    }

    /* loaded from: classes2.dex */
    private static class DayOfWeekValueParser extends SimpleValueParser {
        private static String[] ALIASES = {"sun", "mon", "tue", "wed", "thu", "fri", "sat"};

        public DayOfWeekValueParser() {
            super(0, 7);
        }

        @Override // it.sauronsoftware.cron4j.SchedulingPattern.SimpleValueParser, it.sauronsoftware.cron4j.SchedulingPattern.ValueParser
        public int parse(String str) throws Exception {
            try {
                return super.parse(str) % 7;
            } catch (Exception unused) {
                return SchedulingPattern.parseAlias(str, ALIASES, 0);
            }
        }
    }

    /* loaded from: classes2.dex */
    private static class HourValueParser extends SimpleValueParser {
        public HourValueParser() {
            super(0, 23);
        }
    }

    /* loaded from: classes2.dex */
    private static class MinuteValueParser extends SimpleValueParser {
        public MinuteValueParser() {
            super(0, 59);
        }
    }

    /* loaded from: classes2.dex */
    private static class MonthValueParser extends SimpleValueParser {
        private static String[] ALIASES = {"jan", "feb", "mar", "apr", "may", "jun", "jul", "aug", "sep", "oct", "nov", "dec"};

        public MonthValueParser() {
            super(1, 12);
        }

        @Override // it.sauronsoftware.cron4j.SchedulingPattern.SimpleValueParser, it.sauronsoftware.cron4j.SchedulingPattern.ValueParser
        public int parse(String str) throws Exception {
            try {
                return super.parse(str);
            } catch (Exception unused) {
                return SchedulingPattern.parseAlias(str, ALIASES, 1);
            }
        }
    }

    /* loaded from: classes2.dex */
    private static class SimpleValueParser implements ValueParser {
        protected int maxValue;
        protected int minValue;

        public SimpleValueParser(int i, int i2) {
            this.minValue = i;
            this.maxValue = i2;
        }

        @Override // it.sauronsoftware.cron4j.SchedulingPattern.ValueParser
        public int getMaxValue() {
            return this.maxValue;
        }

        @Override // it.sauronsoftware.cron4j.SchedulingPattern.ValueParser
        public int getMinValue() {
            return this.minValue;
        }

        @Override // it.sauronsoftware.cron4j.SchedulingPattern.ValueParser
        public int parse(String str) throws Exception {
            try {
                int parseInt = Integer.parseInt(str);
                if (parseInt < this.minValue || parseInt > this.maxValue) {
                    throw new Exception("value out of range");
                }
                return parseInt;
            } catch (NumberFormatException unused) {
                throw new Exception("invalid integer value");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public interface ValueParser {
        int getMaxValue();

        int getMinValue();

        int parse(String str) throws Exception;
    }

    public SchedulingPattern(String str) throws InvalidPatternException {
        this.asString = str;
        StringTokenizer stringTokenizer = new StringTokenizer(str, "|");
        if (stringTokenizer.countTokens() < 1) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("invalid pattern: \"");
            stringBuffer.append(str);
            stringBuffer.append("\"");
            throw new InvalidPatternException(stringBuffer.toString());
        }
        while (stringTokenizer.hasMoreTokens()) {
            String nextToken = stringTokenizer.nextToken();
            StringTokenizer stringTokenizer2 = new StringTokenizer(nextToken, " \t");
            if (stringTokenizer2.countTokens() != 5) {
                StringBuffer stringBuffer2 = new StringBuffer();
                stringBuffer2.append("invalid pattern: \"");
                stringBuffer2.append(nextToken);
                stringBuffer2.append("\"");
                throw new InvalidPatternException(stringBuffer2.toString());
            }
            try {
                this.minuteMatchers.add(buildValueMatcher(stringTokenizer2.nextToken(), MINUTE_VALUE_PARSER));
                try {
                    this.hourMatchers.add(buildValueMatcher(stringTokenizer2.nextToken(), HOUR_VALUE_PARSER));
                    try {
                        this.dayOfMonthMatchers.add(buildValueMatcher(stringTokenizer2.nextToken(), DAY_OF_MONTH_VALUE_PARSER));
                        try {
                            this.monthMatchers.add(buildValueMatcher(stringTokenizer2.nextToken(), MONTH_VALUE_PARSER));
                            try {
                                this.dayOfWeekMatchers.add(buildValueMatcher(stringTokenizer2.nextToken(), DAY_OF_WEEK_VALUE_PARSER));
                                this.matcherSize++;
                            } catch (Exception e) {
                                StringBuffer stringBuffer3 = new StringBuffer();
                                stringBuffer3.append("invalid pattern \"");
                                stringBuffer3.append(nextToken);
                                stringBuffer3.append("\". Error parsing days of week field: ");
                                stringBuffer3.append(e.getMessage());
                                stringBuffer3.append(".");
                                throw new InvalidPatternException(stringBuffer3.toString());
                            }
                        } catch (Exception e2) {
                            StringBuffer stringBuffer4 = new StringBuffer();
                            stringBuffer4.append("invalid pattern \"");
                            stringBuffer4.append(nextToken);
                            stringBuffer4.append("\". Error parsing months field: ");
                            stringBuffer4.append(e2.getMessage());
                            stringBuffer4.append(".");
                            throw new InvalidPatternException(stringBuffer4.toString());
                        }
                    } catch (Exception e3) {
                        StringBuffer stringBuffer5 = new StringBuffer();
                        stringBuffer5.append("invalid pattern \"");
                        stringBuffer5.append(nextToken);
                        stringBuffer5.append("\". Error parsing days of month field: ");
                        stringBuffer5.append(e3.getMessage());
                        stringBuffer5.append(".");
                        throw new InvalidPatternException(stringBuffer5.toString());
                    }
                } catch (Exception e4) {
                    StringBuffer stringBuffer6 = new StringBuffer();
                    stringBuffer6.append("invalid pattern \"");
                    stringBuffer6.append(nextToken);
                    stringBuffer6.append("\". Error parsing hours field: ");
                    stringBuffer6.append(e4.getMessage());
                    stringBuffer6.append(".");
                    throw new InvalidPatternException(stringBuffer6.toString());
                }
            } catch (Exception e5) {
                StringBuffer stringBuffer7 = new StringBuffer();
                stringBuffer7.append("invalid pattern \"");
                stringBuffer7.append(nextToken);
                stringBuffer7.append("\". Error parsing minutes field: ");
                stringBuffer7.append(e5.getMessage());
                stringBuffer7.append(".");
                throw new InvalidPatternException(stringBuffer7.toString());
            }
        }
    }

    private ValueMatcher buildValueMatcher(String str, ValueParser valueParser) throws Exception {
        if (str.length() == 1 && str.equals("*")) {
            return new AlwaysTrueValueMatcher();
        }
        ArrayList arrayList = new ArrayList();
        StringTokenizer stringTokenizer = new StringTokenizer(str, ",");
        while (stringTokenizer.hasMoreTokens()) {
            String nextToken = stringTokenizer.nextToken();
            try {
                Iterator it2 = parseListElement(nextToken, valueParser).iterator();
                while (it2.hasNext()) {
                    Object next = it2.next();
                    if (!arrayList.contains(next)) {
                        arrayList.add(next);
                    }
                }
            } catch (Exception e) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("invalid field \"");
                stringBuffer.append(str);
                stringBuffer.append("\", invalid element \"");
                stringBuffer.append(nextToken);
                stringBuffer.append("\", ");
                stringBuffer.append(e.getMessage());
                throw new Exception(stringBuffer.toString());
            }
        }
        if (arrayList.size() != 0) {
            return valueParser == DAY_OF_MONTH_VALUE_PARSER ? new DayOfMonthValueMatcher(arrayList) : new IntArrayValueMatcher(arrayList);
        }
        StringBuffer stringBuffer2 = new StringBuffer();
        stringBuffer2.append("invalid field \"");
        stringBuffer2.append(str);
        stringBuffer2.append("\"");
        throw new Exception(stringBuffer2.toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int parseAlias(String str, String[] strArr, int i) throws Exception {
        for (int i2 = 0; i2 < strArr.length; i2++) {
            if (strArr[i2].equalsIgnoreCase(str)) {
                return i + i2;
            }
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("invalid alias \"");
        stringBuffer.append(str);
        stringBuffer.append("\"");
        throw new Exception(stringBuffer.toString());
    }

    private ArrayList parseListElement(String str, ValueParser valueParser) throws Exception {
        StringTokenizer stringTokenizer = new StringTokenizer(str, MqttTopic.TOPIC_LEVEL_SEPARATOR);
        int countTokens = stringTokenizer.countTokens();
        if (countTokens < 1 || countTokens > 2) {
            throw new Exception("syntax error");
        }
        try {
            ArrayList parseRange = parseRange(stringTokenizer.nextToken(), valueParser);
            if (countTokens != 2) {
                return parseRange;
            }
            String nextToken = stringTokenizer.nextToken();
            try {
                int parseInt = Integer.parseInt(nextToken);
                if (parseInt < 1) {
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append("non positive divisor \"");
                    stringBuffer.append(parseInt);
                    stringBuffer.append("\"");
                    throw new Exception(stringBuffer.toString());
                }
                ArrayList arrayList = new ArrayList();
                for (int i = 0; i < parseRange.size(); i += parseInt) {
                    arrayList.add(parseRange.get(i));
                }
                return arrayList;
            } catch (NumberFormatException unused) {
                StringBuffer stringBuffer2 = new StringBuffer();
                stringBuffer2.append("invalid divisor \"");
                stringBuffer2.append(nextToken);
                stringBuffer2.append("\"");
                throw new Exception(stringBuffer2.toString());
            }
        } catch (Exception e) {
            StringBuffer stringBuffer3 = new StringBuffer();
            stringBuffer3.append("invalid range, ");
            stringBuffer3.append(e.getMessage());
            throw new Exception(stringBuffer3.toString());
        }
    }

    private ArrayList parseRange(String str, ValueParser valueParser) throws Exception {
        if (str.equals("*")) {
            int maxValue = valueParser.getMaxValue();
            ArrayList arrayList = new ArrayList();
            for (int minValue = valueParser.getMinValue(); minValue <= maxValue; minValue++) {
                arrayList.add(new Integer(minValue));
            }
            return arrayList;
        }
        StringTokenizer stringTokenizer = new StringTokenizer(str, "-");
        int countTokens = stringTokenizer.countTokens();
        if (countTokens < 1 || countTokens > 2) {
            throw new Exception("syntax error");
        }
        String nextToken = stringTokenizer.nextToken();
        try {
            int parse = valueParser.parse(nextToken);
            if (countTokens == 1) {
                ArrayList arrayList2 = new ArrayList();
                arrayList2.add(new Integer(parse));
                return arrayList2;
            }
            String nextToken2 = stringTokenizer.nextToken();
            try {
                int parse2 = valueParser.parse(nextToken2);
                ArrayList arrayList3 = new ArrayList();
                if (parse < parse2) {
                    while (parse <= parse2) {
                        arrayList3.add(new Integer(parse));
                        parse++;
                    }
                } else if (parse > parse2) {
                    int maxValue2 = valueParser.getMaxValue();
                    while (parse <= maxValue2) {
                        arrayList3.add(new Integer(parse));
                        parse++;
                    }
                    for (int minValue2 = valueParser.getMinValue(); minValue2 <= parse2; minValue2++) {
                        arrayList3.add(new Integer(minValue2));
                    }
                } else {
                    arrayList3.add(new Integer(parse));
                }
                return arrayList3;
            } catch (Exception e) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("invalid value \"");
                stringBuffer.append(nextToken2);
                stringBuffer.append("\", ");
                stringBuffer.append(e.getMessage());
                throw new Exception(stringBuffer.toString());
            }
        } catch (Exception e2) {
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append("invalid value \"");
            stringBuffer2.append(nextToken);
            stringBuffer2.append("\", ");
            stringBuffer2.append(e2.getMessage());
            throw new Exception(stringBuffer2.toString());
        }
    }

    public static boolean validate(String str) {
        try {
            new SchedulingPattern(str);
            return true;
        } catch (InvalidPatternException unused) {
            return false;
        }
    }

    public boolean match(long j) {
        return match(TimeZone.getDefault(), j);
    }

    public boolean match(TimeZone timeZone, long j) {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTimeInMillis(j);
        gregorianCalendar.setTimeZone(timeZone);
        int i = gregorianCalendar.get(12);
        int i2 = gregorianCalendar.get(11);
        int i3 = gregorianCalendar.get(5);
        int i4 = gregorianCalendar.get(2) + 1;
        int i5 = gregorianCalendar.get(7) - 1;
        int i6 = gregorianCalendar.get(1);
        for (int i7 = 0; i7 < this.matcherSize; i7++) {
            ValueMatcher valueMatcher = (ValueMatcher) this.minuteMatchers.get(i7);
            ValueMatcher valueMatcher2 = (ValueMatcher) this.hourMatchers.get(i7);
            ValueMatcher valueMatcher3 = (ValueMatcher) this.dayOfMonthMatchers.get(i7);
            if (valueMatcher.match(i) && valueMatcher2.match(i2) && (!(valueMatcher3 instanceof DayOfMonthValueMatcher) ? !valueMatcher3.match(i3) : !((DayOfMonthValueMatcher) valueMatcher3).match(i3, i4, gregorianCalendar.isLeapYear(i6))) && ((ValueMatcher) this.monthMatchers.get(i7)).match(i4) && ((ValueMatcher) this.dayOfWeekMatchers.get(i7)).match(i5)) {
                return true;
            }
        }
        return false;
    }

    public String toString() {
        return this.asString;
    }
}
