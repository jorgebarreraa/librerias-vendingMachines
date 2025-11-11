package it.sauronsoftware.cron4j;

import java.util.ArrayList;

/* loaded from: classes2.dex */
class IntArrayValueMatcher implements ValueMatcher {
    private int[] values;

    public IntArrayValueMatcher(ArrayList arrayList) {
        int size = arrayList.size();
        this.values = new int[size];
        for (int i = 0; i < size; i++) {
            try {
                this.values[i] = ((Integer) arrayList.get(i)).intValue();
            } catch (Exception e) {
                throw new IllegalArgumentException(e.getMessage());
            }
        }
    }

    @Override // it.sauronsoftware.cron4j.ValueMatcher
    public boolean match(int i) {
        int i2 = 0;
        while (true) {
            int[] iArr = this.values;
            if (i2 >= iArr.length) {
                return false;
            }
            if (iArr[i2] == i) {
                return true;
            }
            i2++;
        }
    }
}
