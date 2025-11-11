package com.elvishew.xlog.flattener;

/* loaded from: classes.dex */
public class ClassicFlattener extends PatternFlattener {
    private static final String DEFAULT_PATTERN = "{d} {l}/{t}: {m}";

    public ClassicFlattener() {
        super(DEFAULT_PATTERN);
    }
}
