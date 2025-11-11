package com.yj.coffeemachines.app.utils;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;

/* loaded from: classes.dex */
public class BeanUtils {
    public static <A, T> T modelAconvertoB_Gson(A a, Class<T> cls) {
        try {
            Gson gson = new Gson();
            return (T) gson.fromJson(gson.toJson(a), (Class) cls);
        } catch (Exception unused) {
            return null;
        }
    }

    public static <A, T> T modelAconvertoB_Json(A a, Class<T> cls) {
        try {
            return (T) JSON.parseObject(JSON.toJSONString(a), cls);
        } catch (Exception unused) {
            return null;
        }
    }
}
