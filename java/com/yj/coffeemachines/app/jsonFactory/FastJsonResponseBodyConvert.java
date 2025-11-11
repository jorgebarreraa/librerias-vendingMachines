package com.yj.coffeemachines.app.jsonFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import java.io.IOException;
import java.lang.reflect.Type;
import okhttp3.ResponseBody;
import retrofit2.Converter;

/* loaded from: classes.dex */
public class FastJsonResponseBodyConvert<T> implements Converter<ResponseBody, T> {
    private Type type;

    public FastJsonResponseBodyConvert(Type type) {
        this.type = type;
    }

    @Override // retrofit2.Converter
    public T convert(ResponseBody responseBody) throws IOException {
        return (T) JSON.parseObject(responseBody.string(), this.type, new Feature[0]);
    }
}
