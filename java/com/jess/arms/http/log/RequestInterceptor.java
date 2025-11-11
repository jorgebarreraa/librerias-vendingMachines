package com.jess.arms.http.log;

import androidx.annotation.Nullable;
import com.bumptech.glide.load.Key;
import com.jess.arms.http.GlobalHttpHandler;
import com.jess.arms.utils.CharacterHandler;
import com.jess.arms.utils.UrlEncoderUtils;
import com.jess.arms.utils.ZipHelper;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import javax.inject.Singleton;
import kotlin.jvm.internal.LongCompanionObject;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import timber.log.Timber;

@Singleton
/* loaded from: classes.dex */
public class RequestInterceptor implements Interceptor {

    @Nullable
    @Inject
    GlobalHttpHandler mHandler;

    @Inject
    FormatPrinter mPrinter;

    @Inject
    Level printLevel;

    /* loaded from: classes.dex */
    public enum Level {
        NONE,
        REQUEST,
        RESPONSE,
        ALL
    }

    @Inject
    public RequestInterceptor() {
    }

    public static String convertCharset(Charset charset) {
        String charset2 = charset.toString();
        int indexOf = charset2.indexOf("[");
        return indexOf == -1 ? charset2 : charset2.substring(indexOf + 1, charset2.length() - 1);
    }

    public static boolean isForm(MediaType mediaType) {
        if (mediaType == null || mediaType.subtype() == null) {
            return false;
        }
        return mediaType.subtype().toLowerCase().contains("x-www-form-urlencoded");
    }

    public static boolean isHtml(MediaType mediaType) {
        if (mediaType == null || mediaType.subtype() == null) {
            return false;
        }
        return mediaType.subtype().toLowerCase().contains("html");
    }

    public static boolean isJson(MediaType mediaType) {
        if (mediaType == null || mediaType.subtype() == null) {
            return false;
        }
        return mediaType.subtype().toLowerCase().contains("json");
    }

    public static boolean isParseable(MediaType mediaType) {
        if (mediaType == null || mediaType.type() == null) {
            return false;
        }
        return isText(mediaType) || isPlain(mediaType) || isJson(mediaType) || isForm(mediaType) || isHtml(mediaType) || isXml(mediaType);
    }

    public static boolean isPlain(MediaType mediaType) {
        if (mediaType == null || mediaType.subtype() == null) {
            return false;
        }
        return mediaType.subtype().toLowerCase().contains("plain");
    }

    public static boolean isText(MediaType mediaType) {
        if (mediaType == null || mediaType.type() == null) {
            return false;
        }
        return "text".equals(mediaType.type());
    }

    public static boolean isXml(MediaType mediaType) {
        if (mediaType == null || mediaType.subtype() == null) {
            return false;
        }
        return mediaType.subtype().toLowerCase().contains("xml");
    }

    private String parseContent(ResponseBody responseBody, String str, Buffer buffer) {
        Charset forName = Charset.forName(Key.STRING_CHARSET_NAME);
        MediaType contentType = responseBody.contentType();
        if (contentType != null) {
            forName = contentType.charset(forName);
        }
        return "gzip".equalsIgnoreCase(str) ? ZipHelper.decompressForGzip(buffer.readByteArray(), convertCharset(forName)) : "zlib".equalsIgnoreCase(str) ? ZipHelper.decompressToStringForZlib(buffer.readByteArray(), convertCharset(forName)) : buffer.readString(forName);
    }

    public static String parseParams(Request request) throws UnsupportedEncodingException {
        try {
            RequestBody body = request.newBuilder().build().body();
            if (body == null) {
                return "";
            }
            Buffer buffer = new Buffer();
            body.writeTo(buffer);
            Charset forName = Charset.forName(Key.STRING_CHARSET_NAME);
            MediaType contentType = body.contentType();
            if (contentType != null) {
                forName = contentType.charset(forName);
            }
            String readString = buffer.readString(forName);
            if (UrlEncoderUtils.hasUrlEncoded(readString)) {
                readString = URLDecoder.decode(readString, convertCharset(forName));
            }
            return CharacterHandler.jsonFormat(readString);
        } catch (IOException e) {
            e.printStackTrace();
            return "{\"error\": \"" + e.getMessage() + "\"}";
        }
    }

    @Nullable
    private String printResult(Request request, Response response, boolean z) throws IOException {
        try {
            ResponseBody body = response.newBuilder().build().body();
            BufferedSource source = body.source();
            source.request(LongCompanionObject.MAX_VALUE);
            return parseContent(body, response.headers().get("Content-Encoding"), source.getBufferField().clone());
        } catch (IOException e) {
            e.printStackTrace();
            return "{\"error\": \"" + e.getMessage() + "\"}";
        }
    }

    @Override // okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request request = chain.request();
        if (this.printLevel == Level.ALL || (this.printLevel != Level.NONE && this.printLevel == Level.REQUEST)) {
            if (request.body() == null || !isParseable(request.body().contentType())) {
                this.mPrinter.printFileRequest(request);
            } else {
                this.mPrinter.printJsonRequest(request, parseParams(request));
            }
        }
        boolean z = this.printLevel == Level.ALL || (this.printLevel != Level.NONE && this.printLevel == Level.RESPONSE);
        long nanoTime = z ? System.nanoTime() : 0L;
        try {
            Response proceed = chain.proceed(request);
            long nanoTime2 = z ? System.nanoTime() : 0L;
            ResponseBody body = proceed.body();
            String str = null;
            if (body != null && isParseable(body.contentType())) {
                str = printResult(request, proceed, z);
            }
            if (z) {
                List<String> encodedPathSegments = request.url().encodedPathSegments();
                String headers = proceed.headers().toString();
                int code = proceed.code();
                boolean isSuccessful = proceed.isSuccessful();
                String message = proceed.message();
                String url = proceed.request().url().getUrl();
                if (body == null || !isParseable(body.contentType())) {
                    this.mPrinter.printFileResponse(TimeUnit.NANOSECONDS.toMillis(nanoTime2 - nanoTime), isSuccessful, code, headers, encodedPathSegments, message, url);
                } else {
                    this.mPrinter.printJsonResponse(TimeUnit.NANOSECONDS.toMillis(nanoTime2 - nanoTime), isSuccessful, code, headers, body.contentType(), str, encodedPathSegments, message, url);
                }
            }
            GlobalHttpHandler globalHttpHandler = this.mHandler;
            return globalHttpHandler != null ? globalHttpHandler.onHttpResultResponse(str, chain, proceed) : proceed;
        } catch (Exception e) {
            Timber.w("Http Error: %s", e);
            throw e;
        }
    }
}
