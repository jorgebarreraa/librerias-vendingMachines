package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.util.TypeUtils;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.IdentityHashMap;
import java.util.List;
import okhttp3.HttpUrl;

/* loaded from: classes.dex */
public final class ListSerializer implements ObjectSerializer {
    @Override // com.alibaba.fastjson.serializer.ObjectSerializer
    public final void write(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type) throws IOException {
        SerializeWriter serializeWriter = jSONSerializer.out;
        boolean z = (serializeWriter.features & SerializerFeature.WriteClassName.mask) != 0;
        Type collectionItemType = z ? TypeUtils.getCollectionItemType(type) : null;
        if (obj == null) {
            if ((serializeWriter.features & SerializerFeature.WriteNullListAsEmpty.mask) != 0) {
                serializeWriter.write(HttpUrl.PATH_SEGMENT_ENCODE_SET_URI);
                return;
            } else {
                serializeWriter.writeNull();
                return;
            }
        }
        List list = (List) obj;
        int size = list.size();
        if (size == 0) {
            serializeWriter.append((CharSequence) HttpUrl.PATH_SEGMENT_ENCODE_SET_URI);
            return;
        }
        SerialContext serialContext = jSONSerializer.context;
        if ((serializeWriter.features & SerializerFeature.DisableCircularReferenceDetect.mask) == 0) {
            jSONSerializer.context = new SerialContext(serialContext, obj, obj2, 0);
            if (jSONSerializer.references == null) {
                jSONSerializer.references = new IdentityHashMap<>();
            }
            jSONSerializer.references.put(obj, jSONSerializer.context);
        }
        try {
            if ((serializeWriter.features & SerializerFeature.PrettyFormat.mask) != 0) {
                serializeWriter.write(91);
                jSONSerializer.incrementIndent();
                for (int i = 0; i < size; i++) {
                    Object obj3 = list.get(i);
                    if (i != 0) {
                        serializeWriter.write(44);
                    }
                    jSONSerializer.println();
                    if (obj3 == null) {
                        jSONSerializer.out.writeNull();
                    } else if (jSONSerializer.references == null || !jSONSerializer.references.containsKey(obj3)) {
                        ObjectSerializer objectSerializer = jSONSerializer.config.get(obj3.getClass());
                        jSONSerializer.context = new SerialContext(serialContext, obj, obj2, 0);
                        objectSerializer.write(jSONSerializer, obj3, Integer.valueOf(i), collectionItemType);
                    } else {
                        jSONSerializer.writeReference(obj3);
                    }
                }
                jSONSerializer.decrementIdent();
                jSONSerializer.println();
                serializeWriter.write(93);
                return;
            }
            int i2 = serializeWriter.count + 1;
            if (i2 > serializeWriter.buf.length) {
                if (serializeWriter.writer == null) {
                    serializeWriter.expandCapacity(i2);
                } else {
                    serializeWriter.flush();
                    i2 = 1;
                }
            }
            serializeWriter.buf[serializeWriter.count] = '[';
            serializeWriter.count = i2;
            for (int i3 = 0; i3 < list.size(); i3++) {
                Object obj4 = list.get(i3);
                if (i3 != 0) {
                    int i4 = serializeWriter.count + 1;
                    if (i4 > serializeWriter.buf.length) {
                        if (serializeWriter.writer == null) {
                            serializeWriter.expandCapacity(i4);
                        } else {
                            serializeWriter.flush();
                            i4 = 1;
                        }
                    }
                    serializeWriter.buf[serializeWriter.count] = ',';
                    serializeWriter.count = i4;
                }
                if (obj4 == null) {
                    serializeWriter.append((CharSequence) "null");
                } else {
                    Class<?> cls = obj4.getClass();
                    if (cls == Integer.class) {
                        serializeWriter.writeInt(((Integer) obj4).intValue());
                    } else if (cls == Long.class) {
                        long longValue = ((Long) obj4).longValue();
                        if (z) {
                            serializeWriter.writeLong(longValue);
                            serializeWriter.write(76);
                        } else {
                            serializeWriter.writeLong(longValue);
                        }
                    } else if (cls == String.class) {
                        String str = (String) obj4;
                        if ((serializeWriter.features & SerializerFeature.UseSingleQuotes.mask) != 0) {
                            serializeWriter.writeStringWithSingleQuote(str);
                        } else {
                            serializeWriter.writeStringWithDoubleQuote(str, (char) 0, true);
                        }
                    } else {
                        if ((serializeWriter.features & SerializerFeature.DisableCircularReferenceDetect.mask) == 0) {
                            jSONSerializer.context = new SerialContext(serialContext, obj, obj2, 0);
                        }
                        if (jSONSerializer.references == null || !jSONSerializer.references.containsKey(obj4)) {
                            jSONSerializer.config.get(obj4.getClass()).write(jSONSerializer, obj4, Integer.valueOf(i3), collectionItemType);
                        } else {
                            jSONSerializer.writeReference(obj4);
                        }
                    }
                }
            }
            int i5 = serializeWriter.count + 1;
            if (i5 > serializeWriter.buf.length) {
                if (serializeWriter.writer == null) {
                    serializeWriter.expandCapacity(i5);
                } else {
                    serializeWriter.flush();
                    i5 = 1;
                }
            }
            serializeWriter.buf[serializeWriter.count] = ']';
            serializeWriter.count = i5;
        } finally {
            jSONSerializer.context = serialContext;
        }
    }
}
