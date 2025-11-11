package com.alibaba.fastjson.util;

import com.alibaba.fastjson.JSONAware;
import com.alibaba.fastjson.annotation.JSONField;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

/* loaded from: classes.dex */
public class FieldInfo implements Comparable<FieldInfo> {
    public final String[] alternateNames;
    public final Class<?> declaringClass;
    public final Field field;
    public final boolean fieldAccess;
    private final JSONField fieldAnnotation;
    public final Class<?> fieldClass;
    public final boolean fieldTransient;
    public final Type fieldType;
    public final String format;
    public final boolean getOnly;
    public final boolean isEnum;
    public final Method method;
    private final JSONField methodAnnotation;
    public final String name;
    public final long nameHashCode;
    private int ordinal;
    public final int serialzeFeatures;

    public FieldInfo(String str, Class<?> cls, Class<?> cls2, Type type, Field field, int i, int i2) {
        this.ordinal = 0;
        i = i < 0 ? 0 : i;
        this.name = str;
        this.declaringClass = cls;
        this.fieldClass = cls2;
        this.fieldType = type;
        this.method = null;
        this.field = field;
        this.ordinal = i;
        this.serialzeFeatures = i2;
        boolean z = true;
        this.isEnum = cls2.isEnum() && !JSONAware.class.isAssignableFrom(cls2);
        this.fieldAnnotation = null;
        this.methodAnnotation = null;
        if (field != null) {
            int modifiers = field.getModifiers();
            if ((modifiers & 1) == 0 && this.method != null) {
                z = false;
            }
            this.fieldAccess = z;
            this.fieldTransient = Modifier.isTransient(modifiers);
        } else {
            this.fieldAccess = false;
            this.fieldTransient = false;
        }
        this.getOnly = false;
        long j = -3750763034362895579L;
        for (int i3 = 0; i3 < str.length(); i3++) {
            j = (j ^ str.charAt(i3)) * 1099511628211L;
        }
        this.nameHashCode = j;
        this.format = null;
        this.alternateNames = new String[0];
    }

    /* JADX WARN: Code restructure failed: missing block: B:64:0x0136, code lost:
    
        r13 = r5.declaringClass.getTypeParameters();
        r15 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x013e, code lost:
    
        if (r15 >= r13.length) goto L133;
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x0146, code lost:
    
        if (r8.equals(r13[r15]) == false) goto L92;
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x014b, code lost:
    
        r15 = r15 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x0148, code lost:
    
        r12 = r14[r15];
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public FieldInfo(java.lang.String r6, java.lang.reflect.Method r7, java.lang.reflect.Field r8, java.lang.Class<?> r9, java.lang.reflect.Type r10, int r11, int r12, com.alibaba.fastjson.annotation.JSONField r13, com.alibaba.fastjson.annotation.JSONField r14, boolean r15) {
        /*
            Method dump skipped, instructions count: 425
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.util.FieldInfo.<init>(java.lang.String, java.lang.reflect.Method, java.lang.reflect.Field, java.lang.Class, java.lang.reflect.Type, int, int, com.alibaba.fastjson.annotation.JSONField, com.alibaba.fastjson.annotation.JSONField, boolean):void");
    }

    public static Type getFieldType(Class<?> cls, Type type, Type type2) {
        TypeVariable<Class<? super Object>>[] typeVariableArr;
        ParameterizedType parameterizedType;
        if (cls != null && type != null) {
            if (type2 instanceof GenericArrayType) {
                Type genericComponentType = ((GenericArrayType) type2).getGenericComponentType();
                Type fieldType = getFieldType(cls, type, genericComponentType);
                return genericComponentType != fieldType ? Array.newInstance(TypeUtils.getClass(fieldType), 0).getClass() : type2;
            }
            if (!TypeUtils.isGenericParamType(type)) {
                return type2;
            }
            if (type2 instanceof TypeVariable) {
                ParameterizedType parameterizedType2 = (ParameterizedType) TypeUtils.getGenericParamType(type);
                Class<?> cls2 = TypeUtils.getClass(parameterizedType2);
                TypeVariable typeVariable = (TypeVariable) type2;
                for (int i = 0; i < cls2.getTypeParameters().length; i++) {
                    if (cls2.getTypeParameters()[i].getName().equals(typeVariable.getName())) {
                        return parameterizedType2.getActualTypeArguments()[i];
                    }
                }
            }
            if (type2 instanceof ParameterizedType) {
                ParameterizedType parameterizedType3 = (ParameterizedType) type2;
                Type[] actualTypeArguments = parameterizedType3.getActualTypeArguments();
                if (type instanceof ParameterizedType) {
                    parameterizedType = (ParameterizedType) type;
                    typeVariableArr = cls.getTypeParameters();
                } else if (cls.getGenericSuperclass() instanceof ParameterizedType) {
                    parameterizedType = (ParameterizedType) cls.getGenericSuperclass();
                    typeVariableArr = cls.getSuperclass().getTypeParameters();
                } else {
                    typeVariableArr = null;
                    parameterizedType = null;
                }
                Type[] typeArr = null;
                boolean z = false;
                for (int i2 = 0; i2 < actualTypeArguments.length && parameterizedType != null; i2++) {
                    Type type3 = actualTypeArguments[i2];
                    if (type3 instanceof TypeVariable) {
                        TypeVariable typeVariable2 = (TypeVariable) type3;
                        Type[] typeArr2 = typeArr;
                        boolean z2 = z;
                        for (int i3 = 0; i3 < typeVariableArr.length; i3++) {
                            if (typeVariableArr[i3].getName().equals(typeVariable2.getName())) {
                                if (typeArr2 == null) {
                                    typeArr2 = parameterizedType.getActualTypeArguments();
                                }
                                actualTypeArguments[i2] = typeArr2[i3];
                                z2 = true;
                            }
                        }
                        z = z2;
                        typeArr = typeArr2;
                    }
                }
                if (z) {
                    return new ParameterizedTypeImpl(actualTypeArguments, parameterizedType3.getOwnerType(), parameterizedType3.getRawType());
                }
            }
        }
        return type2;
    }

    @Override // java.lang.Comparable
    public int compareTo(FieldInfo fieldInfo) {
        int i = this.ordinal;
        int i2 = fieldInfo.ordinal;
        if (i < i2) {
            return -1;
        }
        if (i > i2) {
            return 1;
        }
        return this.name.compareTo(fieldInfo.name);
    }

    public boolean equals(FieldInfo fieldInfo) {
        return fieldInfo == this || compareTo(fieldInfo) == 0;
    }

    public Object get(Object obj) throws IllegalAccessException, InvocationTargetException {
        return this.fieldAccess ? this.field.get(obj) : this.method.invoke(obj, new Object[0]);
    }

    public JSONField getAnnotation() {
        JSONField jSONField = this.fieldAnnotation;
        return jSONField != null ? jSONField : this.methodAnnotation;
    }

    public void set(Object obj, Object obj2) throws IllegalAccessException, InvocationTargetException {
        Method method = this.method;
        if (method != null) {
            method.invoke(obj, obj2);
        } else {
            this.field.set(obj, obj2);
        }
    }

    public String toString() {
        return this.name;
    }
}
