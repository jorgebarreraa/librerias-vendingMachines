package io.victoralbertos.jolyglot;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/* loaded from: classes2.dex */
public class GsonSpeaker implements JolyglotGenerics {
    private final Gson gson;

    public GsonSpeaker() {
        this.gson = new Gson();
    }

    public GsonSpeaker(Gson gson) {
        this.gson = gson;
    }

    @Override // io.victoralbertos.jolyglot.JolyglotGenerics
    public GenericArrayType arrayOf(Type type) {
        return Types.arrayOf(type);
    }

    @Override // io.victoralbertos.jolyglot.Jolyglot
    public <T> T fromJson(File file, Class<T> cls) throws RuntimeException {
        BufferedReader bufferedReader;
        BufferedReader bufferedReader2 = null;
        try {
            try {
                bufferedReader = new BufferedReader(new FileReader(file.getAbsoluteFile()));
            } catch (Throwable th) {
                th = th;
            }
        } catch (IOException e) {
            e = e;
        }
        try {
            T t = (T) this.gson.fromJson((Reader) bufferedReader, (Class) cls);
            bufferedReader.close();
            try {
                bufferedReader.close();
            } catch (IOException unused) {
            }
            return t;
        } catch (IOException e2) {
            e = e2;
            throw new RuntimeException(e);
        } catch (Throwable th2) {
            th = th2;
            bufferedReader2 = bufferedReader;
            if (bufferedReader2 != null) {
                try {
                    bufferedReader2.close();
                } catch (IOException unused2) {
                }
            }
            throw th;
        }
    }

    @Override // io.victoralbertos.jolyglot.JolyglotGenerics
    public <T> T fromJson(File file, Type type) throws RuntimeException {
        BufferedReader bufferedReader;
        BufferedReader bufferedReader2 = null;
        try {
            try {
                bufferedReader = new BufferedReader(new FileReader(file.getAbsoluteFile()));
            } catch (Throwable th) {
                th = th;
            }
        } catch (IOException e) {
            e = e;
        }
        try {
            T t = (T) this.gson.fromJson(bufferedReader, type);
            bufferedReader.close();
            try {
                bufferedReader.close();
            } catch (IOException unused) {
            }
            return t;
        } catch (IOException e2) {
            e = e2;
            throw new RuntimeException(e);
        } catch (Throwable th2) {
            th = th2;
            bufferedReader2 = bufferedReader;
            if (bufferedReader2 != null) {
                try {
                    bufferedReader2.close();
                } catch (IOException unused2) {
                }
            }
            throw th;
        }
    }

    @Override // io.victoralbertos.jolyglot.Jolyglot
    public <T> T fromJson(String str, Class<T> cls) throws RuntimeException {
        return (T) this.gson.fromJson(str, (Class) cls);
    }

    @Override // io.victoralbertos.jolyglot.JolyglotGenerics
    public <T> T fromJson(String str, Type type) throws RuntimeException {
        return (T) this.gson.fromJson(str, type);
    }

    @Override // io.victoralbertos.jolyglot.JolyglotGenerics
    public ParameterizedType newParameterizedType(Type type, Type... typeArr) {
        return Types.newParameterizedType(type, typeArr);
    }

    @Override // io.victoralbertos.jolyglot.Jolyglot
    public String toJson(Object obj) {
        return this.gson.toJson(obj);
    }

    @Override // io.victoralbertos.jolyglot.JolyglotGenerics
    public String toJson(Object obj, Type type) {
        return this.gson.toJson(obj, type);
    }
}
