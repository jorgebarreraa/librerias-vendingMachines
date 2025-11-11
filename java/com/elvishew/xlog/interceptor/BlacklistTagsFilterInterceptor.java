package com.elvishew.xlog.interceptor;

import com.elvishew.xlog.LogItem;
import java.util.Arrays;
import java.util.Iterator;

/* loaded from: classes.dex */
public class BlacklistTagsFilterInterceptor extends AbstractFilterInterceptor {
    private Iterable<String> blacklistTags;

    public BlacklistTagsFilterInterceptor(Iterable<String> iterable) {
        if (iterable == null) {
            throw new NullPointerException();
        }
        this.blacklistTags = iterable;
    }

    public BlacklistTagsFilterInterceptor(String... strArr) {
        this(Arrays.asList(strArr));
    }

    @Override // com.elvishew.xlog.interceptor.AbstractFilterInterceptor
    protected boolean reject(LogItem logItem) {
        Iterable<String> iterable = this.blacklistTags;
        if (iterable == null) {
            return false;
        }
        Iterator<String> it2 = iterable.iterator();
        while (it2.hasNext()) {
            if (logItem.tag.equals(it2.next())) {
                return true;
            }
        }
        return false;
    }
}
