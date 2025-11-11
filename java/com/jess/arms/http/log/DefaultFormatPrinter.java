package com.jess.arms.http.log;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.elvishew.xlog.XLog;
import com.jess.arms.utils.CharacterHandler;
import com.jess.arms.utils.LogUtils;
import java.util.List;
import okhttp3.MediaType;
import okhttp3.Request;
import org.eclipse.paho.client.mqttv3.MqttTopic;

/* loaded from: classes.dex */
public class DefaultFormatPrinter implements FormatPrinter {
    private static final String[] ARMS;
    private static final String BODY_TAG = "Body:";
    private static final String CENTER_LINE = "├ ";
    private static final String CORNER_BOTTOM = "└ ";
    private static final String CORNER_UP = "┌ ";
    private static final String DEFAULT_LINE = "│ ";
    private static final String END_LINE = "   └───────────────────────────────────────────────────────────────────────────────────────";
    private static final String HEADERS_TAG = "Headers:";
    private static final String METHOD_TAG = "Method: @";
    private static final String N = "\n";
    private static final String[] OMITTED_REQUEST;
    private static final String[] OMITTED_RESPONSE;
    private static final String RECEIVED_TAG = "Received in: ";
    private static final String REQUEST_UP_LINE = "   ┌────── Request ────────────────────────────────────────────────────────────────────────";
    private static final String RESPONSE_UP_LINE = "   ┌────── Response ───────────────────────────────────────────────────────────────────────";
    private static final String STATUS_CODE_TAG = "Status Code: ";
    private static final String T = "\t";
    private static final String TAG = "HttpLog";
    private static final String URL_TAG = "URL: ";
    private static ThreadLocal<Integer> last;
    private static final String LINE_SEPARATOR = System.getProperty("line.separator");
    private static final String DOUBLE_SEPARATOR = LINE_SEPARATOR + LINE_SEPARATOR;

    static {
        String str = LINE_SEPARATOR;
        OMITTED_RESPONSE = new String[]{str, "Omitted response body"};
        OMITTED_REQUEST = new String[]{str, "Omitted request body"};
        ARMS = new String[]{"-A-", "-R-", "-M-", "-S-"};
        last = new ThreadLocal<Integer>() { // from class: com.jess.arms.http.log.DefaultFormatPrinter.1
            /* JADX INFO: Access modifiers changed from: protected */
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.lang.ThreadLocal
            public Integer initialValue() {
                return 0;
            }
        };
    }

    private static String computeKey() {
        if (last.get().intValue() >= 4) {
            last.set(0);
        }
        String str = ARMS[last.get().intValue()];
        ThreadLocal<Integer> threadLocal = last;
        threadLocal.set(Integer.valueOf(threadLocal.get().intValue() + 1));
        return str;
    }

    private static String dotHeaders(String str) {
        String[] split = str.split(LINE_SEPARATOR);
        StringBuilder sb = new StringBuilder();
        int i = 0;
        if (split.length > 1) {
            while (i < split.length) {
                sb.append(i == 0 ? CORNER_UP : i == split.length - 1 ? CORNER_BOTTOM : CENTER_LINE);
                sb.append(split[i]);
                sb.append("\n");
                i++;
            }
        } else {
            int length = split.length;
            while (i < length) {
                String str2 = split[i];
                sb.append("─ ");
                sb.append(str2);
                sb.append("\n");
                i++;
            }
        }
        return sb.toString();
    }

    private static String[] getRequest(Request request) {
        String str;
        String headers = request.headers().toString();
        StringBuilder sb = new StringBuilder();
        sb.append(METHOD_TAG);
        sb.append(request.method());
        sb.append(DOUBLE_SEPARATOR);
        if (isEmpty(headers)) {
            str = "";
        } else {
            str = HEADERS_TAG + LINE_SEPARATOR + dotHeaders(headers);
        }
        sb.append(str);
        return sb.toString().split(LINE_SEPARATOR);
    }

    private static String[] getResponse(String str, long j, int i, boolean z, List<String> list, String str2) {
        String str3;
        String slashSegments = slashSegments(list);
        StringBuilder sb = new StringBuilder();
        String str4 = "";
        if (TextUtils.isEmpty(slashSegments)) {
            str3 = "";
        } else {
            str3 = slashSegments + " - ";
        }
        sb.append(str3);
        sb.append("is success : ");
        sb.append(z);
        sb.append(" - ");
        sb.append(RECEIVED_TAG);
        sb.append(j);
        sb.append("ms");
        sb.append(DOUBLE_SEPARATOR);
        sb.append(STATUS_CODE_TAG);
        sb.append(i);
        sb.append(" / ");
        sb.append(str2);
        sb.append(DOUBLE_SEPARATOR);
        if (!isEmpty(str)) {
            str4 = HEADERS_TAG + LINE_SEPARATOR + dotHeaders(str);
        }
        sb.append(str4);
        return sb.toString().split(LINE_SEPARATOR);
    }

    private static String getTag(boolean z) {
        return z ? "HttpLog-Request" : "HttpLog-Response";
    }

    private static boolean isEmpty(String str) {
        return TextUtils.isEmpty(str) || "\n".equals(str) || T.equals(str) || TextUtils.isEmpty(str.trim());
    }

    private static void logLines(String str, String[] strArr, boolean z) {
        for (String str2 : strArr) {
            int length = str2.length();
            int i = z ? 110 : length;
            int i2 = 0;
            while (i2 <= length / i) {
                int i3 = i2 * i;
                i2++;
                int i4 = i2 * i;
                if (i4 > str2.length()) {
                    i4 = str2.length();
                }
                LogUtils.debugInfo(resolveTag(str), DEFAULT_LINE + str2.substring(i3, i4));
            }
        }
    }

    private static String resolveTag(String str) {
        return computeKey() + str;
    }

    private static String slashSegments(List<String> list) {
        StringBuilder sb = new StringBuilder();
        for (String str : list) {
            sb.append(MqttTopic.TOPIC_LEVEL_SEPARATOR);
            sb.append(str);
        }
        return sb.toString();
    }

    @Override // com.jess.arms.http.log.FormatPrinter
    public void printFileRequest(@NonNull Request request) {
        String tag = getTag(true);
        LogUtils.debugInfo(tag, REQUEST_UP_LINE);
        logLines(tag, new String[]{URL_TAG + request.url()}, false);
        logLines(tag, getRequest(request), true);
        logLines(tag, OMITTED_REQUEST, true);
        LogUtils.debugInfo(tag, END_LINE);
        XLog.tag(tag).i(URL_TAG + request.url());
    }

    @Override // com.jess.arms.http.log.FormatPrinter
    public void printFileResponse(long j, boolean z, int i, @NonNull String str, @NonNull List<String> list, @NonNull String str2, @NonNull String str3) {
        String tag = getTag(false);
        String[] strArr = {URL_TAG + str3, "\n"};
        LogUtils.debugInfo(tag, RESPONSE_UP_LINE);
        logLines(tag, strArr, true);
        logLines(tag, getResponse(str, j, i, z, list, str2), true);
        logLines(tag, OMITTED_RESPONSE, true);
        LogUtils.debugInfo(tag, END_LINE);
    }

    @Override // com.jess.arms.http.log.FormatPrinter
    public void printJsonRequest(@NonNull Request request, @NonNull String str) {
        String str2 = LINE_SEPARATOR + BODY_TAG + LINE_SEPARATOR + str;
        String tag = getTag(true);
        LogUtils.debugInfo(tag, REQUEST_UP_LINE);
        logLines(tag, new String[]{URL_TAG + request.url()}, false);
        logLines(tag, getRequest(request), true);
        logLines(tag, str2.split(LINE_SEPARATOR), true);
        LogUtils.debugInfo(tag, END_LINE);
        XLog.tag(tag).i(URL_TAG + request.url());
    }

    @Override // com.jess.arms.http.log.FormatPrinter
    public void printJsonResponse(long j, boolean z, int i, @NonNull String str, @Nullable MediaType mediaType, @Nullable String str2, @NonNull List<String> list, @NonNull String str3, @NonNull String str4) {
        String str5 = LINE_SEPARATOR + BODY_TAG + LINE_SEPARATOR + (RequestInterceptor.isJson(mediaType) ? CharacterHandler.jsonFormat(str2) : RequestInterceptor.isXml(mediaType) ? CharacterHandler.xmlFormat(str2) : str2);
        String tag = getTag(false);
        String[] strArr = {URL_TAG + str4, "\n"};
        LogUtils.debugInfo(tag, RESPONSE_UP_LINE);
        logLines(tag, strArr, true);
        logLines(tag, getResponse(str, j, i, z, list, str3), true);
        logLines(tag, str5.split(LINE_SEPARATOR), true);
        LogUtils.debugInfo(tag, END_LINE);
        XLog.tag(tag).i(getResponse(str, j, i, z, list, str3)[0]);
    }
}
