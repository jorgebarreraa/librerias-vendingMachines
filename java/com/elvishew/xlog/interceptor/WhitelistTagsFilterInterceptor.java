package com.elvishew.xlog.interceptor;

import com.elvishew.xlog.LogItem;
import java.util.Arrays;
import java.util.Iterator;

/* loaded from: classes.dex */
public class WhitelistTagsFilterInterceptor extends AbstractFilterInterceptor {
    private Iterable<String> whitelistTags;

    public WhitelistTagsFilterInterceptor(Iterable<String> iterable) {
        if (iterable == null) {
            throw new NullPointerException();
        }
        this.whitelistTags = iterable;
    }

    public WhitelistTagsFilterInterceptor(String... strArr) {
        this(Arrays.asList(strArr));
    }

    @Override // com.elvishew.xlog.interceptor.AbstractFilterInterceptor
    protected boolean reject(LogItem logItem) {
        Iterable<String> iterable = this.whitelistTags;
        if (iterable == null) {
            return true;
        }
        Iterator<String> it2 = iterable.iterator();
        while (it2.hasNext()) {
            if (logItem.tag.equals(it2.next())) {
                return false;
            }
        }
        return true;
    }
}
