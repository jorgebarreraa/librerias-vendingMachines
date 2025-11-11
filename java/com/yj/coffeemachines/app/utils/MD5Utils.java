package com.yj.coffeemachines.app.utils;

import com.bumptech.glide.load.Key;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import kotlin.UByte;

/* loaded from: classes.dex */
public class MD5Utils {
    public static MD5Utils getInit() {
        return new MD5Utils();
    }

    public static String md5_32_low(String str) {
        try {
            byte[] digest = MessageDigest.getInstance("MD5").digest(str.getBytes(Key.STRING_CHARSET_NAME));
            StringBuilder sb = new StringBuilder(digest.length * 2);
            for (byte b : digest) {
                int i = b & UByte.MAX_VALUE;
                if (i < 16) {
                    sb.append("0");
                }
                sb.append(Integer.toHexString(i));
            }
            return sb.toString();
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Huh, UTF-8 should be supported?", e);
        } catch (NoSuchAlgorithmException e2) {
            throw new RuntimeException("Huh, MD5 should be supported?", e2);
        }
    }
}
