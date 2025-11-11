package com.elvishew.xlog.formatter.message.json;

import com.elvishew.xlog.internal.Platform;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class DefaultJsonFormatter implements JsonFormatter {
    private static final int JSON_INDENT = 4;

    @Override // com.elvishew.xlog.formatter.Formatter
    public String format(String str) {
        if (str == null || str.trim().length() == 0) {
            Platform.get().warn("JSON empty.");
            return "";
        }
        try {
            if (str.startsWith("{")) {
                return new JSONObject(str).toString(4);
            }
            if (str.startsWith("[")) {
                return new JSONArray(str).toString(4);
            }
            Platform.get().warn("JSON should start with { or [");
            return str;
        } catch (Exception e) {
            Platform.get().warn(e.getMessage());
            return str;
        }
    }
}
