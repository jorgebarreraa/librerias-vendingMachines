package it.sauronsoftware.cron4j;

import java.util.Date;
import java.util.TimeZone;

/* loaded from: classes2.dex */
public class Predictor {
    private SchedulingPattern schedulingPattern;
    private long time;
    private TimeZone timeZone;

    public Predictor(SchedulingPattern schedulingPattern) {
        this(schedulingPattern, System.currentTimeMillis());
    }

    public Predictor(SchedulingPattern schedulingPattern, long j) {
        this.timeZone = TimeZone.getDefault();
        this.schedulingPattern = schedulingPattern;
        this.time = (j / 60000) * 1000 * 60;
    }

    public Predictor(SchedulingPattern schedulingPattern, Date date) {
        this(schedulingPattern, date.getTime());
    }

    public Predictor(String str) throws InvalidPatternException {
        this(str, System.currentTimeMillis());
    }

    public Predictor(String str, long j) throws InvalidPatternException {
        this.timeZone = TimeZone.getDefault();
        this.schedulingPattern = new SchedulingPattern(str);
        this.time = (j / 60000) * 1000 * 60;
    }

    public Predictor(String str, Date date) throws InvalidPatternException {
        this(str, date.getTime());
    }

    public synchronized Date nextMatchingDate() {
        return new Date(nextMatchingTime());
    }

    /* JADX WARN: Code restructure failed: missing block: B:77:0x01a7, code lost:
    
        if (r9 > 59) goto L59;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public synchronized long nextMatchingTime() {
        /*
            Method dump skipped, instructions count: 478
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: it.sauronsoftware.cron4j.Predictor.nextMatchingTime():long");
    }

    public void setTimeZone(TimeZone timeZone) {
        this.timeZone = timeZone;
    }
}
