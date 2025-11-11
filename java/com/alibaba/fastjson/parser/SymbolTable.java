package com.alibaba.fastjson.parser;

import com.alibaba.fastjson.JSON;

/* loaded from: classes.dex */
public class SymbolTable {
    private final int indexMask;
    private final Entry[] symbols;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class Entry {
        final char[] chars;
        final int hashCode;
        final String value;

        Entry(String str, int i) {
            this.value = str;
            this.chars = str.toCharArray();
            this.hashCode = i;
        }
    }

    public SymbolTable(int i) {
        this.indexMask = i - 1;
        this.symbols = new Entry[i];
        addSymbol("$ref", 0, 4, "$ref".hashCode());
        addSymbol(JSON.DEFAULT_TYPE_KEY, 0, 5, JSON.DEFAULT_TYPE_KEY.hashCode());
    }

    private static String subString(String str, int i, int i2) {
        char[] cArr = new char[i2];
        str.getChars(i, i2 + i, cArr, 0);
        return new String(cArr);
    }

    public String addSymbol(String str, int i, int i2, int i3) {
        int i4 = this.indexMask & i3;
        Entry entry = this.symbols[i4];
        if (entry != null) {
            return (i3 == entry.hashCode && i2 == entry.chars.length && str.regionMatches(i, entry.value, 0, i2)) ? entry.value : subString(str, i, i2);
        }
        if (i2 != str.length()) {
            str = subString(str, i, i2);
        }
        String intern = str.intern();
        this.symbols[i4] = new Entry(intern, i3);
        return intern;
    }

    public String addSymbol(char[] cArr, int i, int i2, int i3) {
        int i4 = this.indexMask & i3;
        Entry entry = this.symbols[i4];
        if (entry == null) {
            String intern = new String(cArr, i, i2).intern();
            this.symbols[i4] = new Entry(intern, i3);
            return intern;
        }
        boolean z = false;
        if (i3 == entry.hashCode && i2 == entry.chars.length) {
            int i5 = 0;
            while (true) {
                if (i5 >= i2) {
                    z = true;
                    break;
                }
                if (cArr[i + i5] != entry.chars[i5]) {
                    break;
                }
                i5++;
            }
        }
        return z ? entry.value : new String(cArr, i, i2);
    }
}
