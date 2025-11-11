package it.sauronsoftware.cron4j;

import java.util.ArrayList;

/* loaded from: classes2.dex */
class DayOfMonthValueMatcher extends IntArrayValueMatcher {
    private static final int[] lastDays = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    public DayOfMonthValueMatcher(ArrayList arrayList) {
        super(arrayList);
    }

    public boolean isLastDayOfMonth(int i, int i2, boolean z) {
        return (z && i2 == 2) ? i == 29 : i == lastDays[i2 - 1];
    }

    public boolean match(int i, int i2, boolean z) {
        return super.match(i) || (i > 27 && match(32) && isLastDayOfMonth(i, i2, z));
    }
}
