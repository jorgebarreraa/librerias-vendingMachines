package com.jess.arms.utils;

import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.regex.Pattern;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class CharacterHandler {
    public static final InputFilter EMOJI_FILTER = new InputFilter() { // from class: com.jess.arms.utils.CharacterHandler.1
        Pattern emoji = Pattern.compile("[🀀-🏿]|[🐀-\u1f7ff]|[☀-⟿]", 66);

        @Override // android.text.InputFilter
        public CharSequence filter(CharSequence charSequence, int i, int i2, Spanned spanned, int i3, int i4) {
            if (this.emoji.matcher(charSequence).find()) {
                return "";
            }
            return null;
        }
    };

    private CharacterHandler() {
        throw new IllegalStateException("you can't instantiate me!");
    }

    public static String jsonFormat(String str) {
        if (TextUtils.isEmpty(str)) {
            return "Empty/Null json content";
        }
        try {
            str = str.trim();
            if (str.startsWith("{")) {
                str = new JSONObject(str).toString(4);
            } else if (str.startsWith("[")) {
                str = new JSONArray(str).toString(4);
            }
            return str;
        } catch (OutOfMemoryError unused) {
            return "Output omitted because of Object size";
        } catch (JSONException unused2) {
            return str;
        }
    }

    public static String str2HexStr(String str) {
        char[] charArray = "0123456789ABCDEF".toCharArray();
        StringBuilder sb = new StringBuilder();
        for (byte b : str.getBytes()) {
            sb.append(charArray[(b & 240) >> 4]);
            sb.append(charArray[b & 15]);
        }
        return sb.toString().trim();
    }

    public static String xmlFormat(String str) {
        if (TextUtils.isEmpty(str)) {
            return "Empty/Null xml content";
        }
        try {
            StreamSource streamSource = new StreamSource(new StringReader(str));
            StreamResult streamResult = new StreamResult(new StringWriter());
            Transformer newTransformer = TransformerFactory.newInstance().newTransformer();
            newTransformer.setOutputProperty("indent", "yes");
            newTransformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            newTransformer.transform(streamSource, streamResult);
            return streamResult.getWriter().toString().replaceFirst(">", ">\n");
        } catch (TransformerException unused) {
            return str;
        }
    }
}
