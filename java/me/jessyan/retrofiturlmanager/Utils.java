package me.jessyan.retrofiturlmanager;

import okhttp3.HttpUrl;

/* loaded from: classes2.dex */
class Utils {
    private Utils() {
        throw new IllegalStateException("do not instantiation me");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T> T checkNotNull(T t, String str) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static HttpUrl checkUrl(String str) {
        HttpUrl parse = HttpUrl.parse(str);
        if (parse != null) {
            return parse;
        }
        throw new InvalidUrlException(str);
    }
}
