package com.alibaba.fastjson.parser;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.deserializer.ExtraProcessable;
import com.alibaba.fastjson.parser.deserializer.ExtraProcessor;
import com.alibaba.fastjson.parser.deserializer.ExtraTypeProvider;
import com.alibaba.fastjson.parser.deserializer.FieldDeserializer;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.util.FieldInfo;
import com.alibaba.fastjson.util.TypeUtils;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import kotlin.text.Typography;
// import org.eclipse.paho.android.service.MqttServiceConstants; // Eliminado: clase privada

/* loaded from: classes.dex */
public class JavaBeanDeserializer implements ObjectDeserializer {
    private final Map<String, FieldDeserializer> alterNameFieldDeserializers;
    public final JavaBeanInfo beanInfo;
    protected final Class<?> clazz;
    private ConcurrentMap<String, Object> extraFieldDeserializers;
    private final FieldDeserializer[] fieldDeserializers;
    private transient long[] smartMatchHashArray;
    private transient int[] smartMatchHashArrayMapping;
    private final FieldDeserializer[] sortedFieldDeserializers;

    public JavaBeanDeserializer(ParserConfig parserConfig, Class<?> cls, Type type) {
        this(parserConfig, cls, type, JavaBeanInfo.build(cls, cls.getModifiers(), type, false, true, true, true, parserConfig.propertyNamingStrategy));
    }

    public JavaBeanDeserializer(ParserConfig parserConfig, Class<?> cls, Type type, JavaBeanInfo javaBeanInfo) {
        this.clazz = cls;
        this.beanInfo = javaBeanInfo;
        this.sortedFieldDeserializers = new FieldDeserializer[javaBeanInfo.sortedFields.length];
        int length = javaBeanInfo.sortedFields.length;
        HashMap hashMap = null;
        int i = 0;
        while (i < length) {
            FieldInfo fieldInfo = javaBeanInfo.sortedFields[i];
            FieldDeserializer createFieldDeserializer = parserConfig.createFieldDeserializer(parserConfig, cls, fieldInfo);
            this.sortedFieldDeserializers[i] = createFieldDeserializer;
            HashMap hashMap2 = hashMap;
            for (String str : fieldInfo.alternateNames) {
                if (hashMap2 == null) {
                    hashMap2 = new HashMap();
                }
                hashMap2.put(str, createFieldDeserializer);
            }
            i++;
            hashMap = hashMap2;
        }
        this.alterNameFieldDeserializers = hashMap;
        this.fieldDeserializers = new FieldDeserializer[javaBeanInfo.fields.length];
        int length2 = javaBeanInfo.fields.length;
        for (int i2 = 0; i2 < length2; i2++) {
            this.fieldDeserializers[i2] = getFieldDeserializer(javaBeanInfo.fields[i2].name);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:157:0x06f4, code lost:
    
        throw new com.alibaba.fastjson.JSONException("syntax error, unexpect token " + com.alibaba.fastjson.parser.JSONToken.name(r10.token));
     */
    /* JADX WARN: Code restructure failed: missing block: B:324:0x02d0, code lost:
    
        if (r1 == 16) goto L229;
     */
    /* JADX WARN: Code restructure failed: missing block: B:331:0x02e2, code lost:
    
        r10.nextTokenWithChar(':');
        r0 = r10.token;
     */
    /* JADX WARN: Code restructure failed: missing block: B:332:0x02e8, code lost:
    
        if (r0 != 4) goto L270;
     */
    /* JADX WARN: Code restructure failed: missing block: B:333:0x02ea, code lost:
    
        r0 = r10.stringVal();
     */
    /* JADX WARN: Code restructure failed: missing block: B:334:0x02f4, code lost:
    
        if ("@".equals(r0) == false) goto L240;
     */
    /* JADX WARN: Code restructure failed: missing block: B:336:0x02f8, code lost:
    
        r6 = (T) r14.object;
     */
    /* JADX WARN: Code restructure failed: missing block: B:337:0x0345, code lost:
    
        r10.nextToken(13);
     */
    /* JADX WARN: Code restructure failed: missing block: B:338:0x034c, code lost:
    
        if (r10.token != 13) goto L267;
     */
    /* JADX WARN: Code restructure failed: missing block: B:339:0x034e, code lost:
    
        r10.nextToken(16);
        r35.setContext(r14, r6, r37);
     */
    /* JADX WARN: Code restructure failed: missing block: B:340:0x0356, code lost:
    
        r1 = r20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:341:0x0358, code lost:
    
        if (r1 == null) goto L265;
     */
    /* JADX WARN: Code restructure failed: missing block: B:342:0x035a, code lost:
    
        r1.object = r6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:343:0x035c, code lost:
    
        r35.setContext(r14);
     */
    /* JADX WARN: Code restructure failed: missing block: B:344:0x035f, code lost:
    
        return (T) r6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:348:0x0369, code lost:
    
        throw new com.alibaba.fastjson.JSONException("illegal ref");
     */
    /* JADX WARN: Code restructure failed: missing block: B:350:0x0300, code lost:
    
        if ("..".equals(r0) == false) goto L247;
     */
    /* JADX WARN: Code restructure failed: missing block: B:351:0x0302, code lost:
    
        r1 = r14.parent;
     */
    /* JADX WARN: Code restructure failed: missing block: B:352:0x0306, code lost:
    
        if (r1.object == null) goto L245;
     */
    /* JADX WARN: Code restructure failed: missing block: B:353:0x0308, code lost:
    
        r6 = (T) r1.object;
     */
    /* JADX WARN: Code restructure failed: missing block: B:354:0x030b, code lost:
    
        r35.addResolveTask(new com.alibaba.fastjson.parser.DefaultJSONParser.ResolveTask(r1, r0));
        r35.resolveStatus = 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:356:0x031d, code lost:
    
        if ("$".equals(r0) == false) goto L258;
     */
    /* JADX WARN: Code restructure failed: missing block: B:357:0x031f, code lost:
    
        r1 = r14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:359:0x0322, code lost:
    
        if (r1.parent == null) goto L601;
     */
    /* JADX WARN: Code restructure failed: missing block: B:360:0x0324, code lost:
    
        r1 = r1.parent;
     */
    /* JADX WARN: Code restructure failed: missing block: B:363:0x0329, code lost:
    
        if (r1.object == null) goto L256;
     */
    /* JADX WARN: Code restructure failed: missing block: B:364:0x032b, code lost:
    
        r6 = (T) r1.object;
     */
    /* JADX WARN: Code restructure failed: missing block: B:365:0x032e, code lost:
    
        r35.addResolveTask(new com.alibaba.fastjson.parser.DefaultJSONParser.ResolveTask(r1, r0));
        r35.resolveStatus = 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:366:0x033a, code lost:
    
        r35.addResolveTask(new com.alibaba.fastjson.parser.DefaultJSONParser.ResolveTask(r14, r0));
        r35.resolveStatus = 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:368:0x0386, code lost:
    
        throw new com.alibaba.fastjson.JSONException("illegal ref, " + com.alibaba.fastjson.parser.JSONToken.name(r0));
     */
    /* JADX WARN: Code restructure failed: missing block: B:372:0x0393, code lost:
    
        if (com.alibaba.fastjson.JSON.DEFAULT_TYPE_KEY == r15) goto L278;
     */
    /* JADX WARN: Code restructure failed: missing block: B:373:0x0395, code lost:
    
        r10.nextTokenWithChar(':');
     */
    /* JADX WARN: Code restructure failed: missing block: B:374:0x039d, code lost:
    
        if (r10.token != 4) goto L550;
     */
    /* JADX WARN: Code restructure failed: missing block: B:375:0x039f, code lost:
    
        r2 = r10.stringVal();
        r10.nextToken(16);
     */
    /* JADX WARN: Code restructure failed: missing block: B:376:0x03aa, code lost:
    
        if ((r36 instanceof java.lang.Class) == false) goto L552;
     */
    /* JADX WARN: Code restructure failed: missing block: B:378:0x03b7, code lost:
    
        if (r2.equals(((java.lang.Class) r36).getName()) == false) goto L554;
     */
    /* JADX WARN: Code restructure failed: missing block: B:380:0x03bd, code lost:
    
        if (r10.token != 13) goto L288;
     */
    /* JADX WARN: Code restructure failed: missing block: B:382:0x03bf, code lost:
    
        r10.nextToken();
     */
    /* JADX WARN: Code restructure failed: missing block: B:383:0x03c2, code lost:
    
        r12 = r1;
        r13 = r6;
        r1 = r21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:385:0x03d4, code lost:
    
        r4 = getSeeAlso(r35.config, r34.beanInfo, r2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:386:0x03dc, code lost:
    
        if (r4 != null) goto L300;
     */
    /* JADX WARN: Code restructure failed: missing block: B:387:0x03de, code lost:
    
        r12 = r35.config.checkAutoType(r2, r34.clazz, r10.features);
        r0 = com.alibaba.fastjson.util.TypeUtils.getClass(r36);
     */
    /* JADX WARN: Code restructure failed: missing block: B:388:0x03ec, code lost:
    
        if (r0 == null) goto L299;
     */
    /* JADX WARN: Code restructure failed: missing block: B:389:0x03ee, code lost:
    
        if (r12 == null) goto L297;
     */
    /* JADX WARN: Code restructure failed: missing block: B:391:0x03f4, code lost:
    
        if (r0.isAssignableFrom(r12) == false) goto L297;
     */
    /* JADX WARN: Code restructure failed: missing block: B:394:0x03fe, code lost:
    
        throw new com.alibaba.fastjson.JSONException("type not match");
     */
    /* JADX WARN: Code restructure failed: missing block: B:395:0x03ff, code lost:
    
        r4 = r35.config.getDeserializer(r12);
     */
    /* JADX WARN: Code restructure failed: missing block: B:397:0x0409, code lost:
    
        if ((r4 instanceof com.alibaba.fastjson.parser.JavaBeanDeserializer) == false) goto L308;
     */
    /* JADX WARN: Code restructure failed: missing block: B:398:0x040b, code lost:
    
        r4 = (com.alibaba.fastjson.parser.JavaBeanDeserializer) r4;
        r0 = (T) r4.deserialze(r35, r12, r37, null);
     */
    /* JADX WARN: Code restructure failed: missing block: B:399:0x0412, code lost:
    
        if (r3 == null) goto L309;
     */
    /* JADX WARN: Code restructure failed: missing block: B:400:0x0414, code lost:
    
        r3 = r4.getFieldDeserializer(r3);
     */
    /* JADX WARN: Code restructure failed: missing block: B:401:0x0418, code lost:
    
        if (r3 == null) goto L309;
     */
    /* JADX WARN: Code restructure failed: missing block: B:402:0x041a, code lost:
    
        r3.setValue(r0, r2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:403:0x0422, code lost:
    
        if (r1 == null) goto L311;
     */
    /* JADX WARN: Code restructure failed: missing block: B:404:0x0424, code lost:
    
        r1.object = r6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:405:0x0426, code lost:
    
        r35.setContext(r14);
     */
    /* JADX WARN: Code restructure failed: missing block: B:406:0x0429, code lost:
    
        return r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:407:0x041e, code lost:
    
        r0 = (T) r4.deserialze(r35, r12, r37);
     */
    /* JADX WARN: Code restructure failed: missing block: B:408:0x0406, code lost:
    
        r12 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:412:0x0431, code lost:
    
        throw new com.alibaba.fastjson.JSONException("syntax error");
     */
    /* JADX WARN: Code restructure failed: missing block: B:415:0x038f, code lost:
    
        if (r3.equals(r15) == false) goto L276;
     */
    /* JADX WARN: Removed duplicated region for block: B:101:0x0443 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:113:0x0474  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x05ca  */
    /* JADX WARN: Removed duplicated region for block: B:148:0x05cc A[Catch: all -> 0x06fd, TryCatch #11 {all -> 0x06fd, blocks: (B:299:0x0476, B:141:0x05c4, B:148:0x05cc, B:160:0x05d2, B:151:0x06c0, B:153:0x06c6, B:156:0x06d8, B:157:0x06f4, B:115:0x0485, B:120:0x048e, B:125:0x0497, B:130:0x04a0, B:136:0x04c6, B:138:0x057d, B:237:0x04ac, B:239:0x04b4, B:240:0x04bc, B:241:0x04c1, B:246:0x04db, B:251:0x04e5, B:256:0x04ee, B:261:0x04f7, B:266:0x0500, B:267:0x0507, B:269:0x050b, B:271:0x050f, B:272:0x0514, B:273:0x051d, B:275:0x0521, B:277:0x0525, B:278:0x0529, B:279:0x0532, B:281:0x0536, B:283:0x053a, B:284:0x0540, B:285:0x054a, B:287:0x054e, B:289:0x0552, B:290:0x0556, B:244:0x0578, B:293:0x055f, B:294:0x0577, B:300:0x0591, B:302:0x05ad, B:306:0x05b3, B:307:0x05be, B:310:0x06f5, B:311:0x06fc), top: B:298:0x0476, inners: #5 }] */
    /* JADX WARN: Removed duplicated region for block: B:181:0x0707  */
    /* JADX WARN: Removed duplicated region for block: B:300:0x0591 A[Catch: all -> 0x06fd, TryCatch #11 {all -> 0x06fd, blocks: (B:299:0x0476, B:141:0x05c4, B:148:0x05cc, B:160:0x05d2, B:151:0x06c0, B:153:0x06c6, B:156:0x06d8, B:157:0x06f4, B:115:0x0485, B:120:0x048e, B:125:0x0497, B:130:0x04a0, B:136:0x04c6, B:138:0x057d, B:237:0x04ac, B:239:0x04b4, B:240:0x04bc, B:241:0x04c1, B:246:0x04db, B:251:0x04e5, B:256:0x04ee, B:261:0x04f7, B:266:0x0500, B:267:0x0507, B:269:0x050b, B:271:0x050f, B:272:0x0514, B:273:0x051d, B:275:0x0521, B:277:0x0525, B:278:0x0529, B:279:0x0532, B:281:0x0536, B:283:0x053a, B:284:0x0540, B:285:0x054a, B:287:0x054e, B:289:0x0552, B:290:0x0556, B:244:0x0578, B:293:0x055f, B:294:0x0577, B:300:0x0591, B:302:0x05ad, B:306:0x05b3, B:307:0x05be, B:310:0x06f5, B:311:0x06fc), top: B:298:0x0476, inners: #5 }] */
    /* JADX WARN: Removed duplicated region for block: B:317:0x02b3 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:32:0x005f A[Catch: all -> 0x0040, TRY_LEAVE, TryCatch #0 {all -> 0x0040, blocks: (B:17:0x0030, B:19:0x0035, B:25:0x004a, B:27:0x0050, B:32:0x005f, B:39:0x006e, B:44:0x007a, B:46:0x0084, B:49:0x008b, B:51:0x00a0, B:52:0x00a8, B:53:0x00b1, B:58:0x00b7), top: B:15:0x002e }] */
    /* JADX WARN: Removed duplicated region for block: B:99:0x043a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private <T> T deserialze(com.alibaba.fastjson.parser.DefaultJSONParser r35, java.lang.reflect.Type r36, java.lang.Object r37, java.lang.Object r38) {
        /*
            Method dump skipped, instructions count: 1810
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.JavaBeanDeserializer.deserialze(com.alibaba.fastjson.parser.DefaultJSONParser, java.lang.reflect.Type, java.lang.Object, java.lang.Object):java.lang.Object");
    }

    private <T> T deserialzeArrayMapping(DefaultJSONParser defaultJSONParser, Type type, Object obj, Object obj2) {
        Enum r8;
        String str;
        JSONLexer jSONLexer = defaultJSONParser.lexer;
        T t = (T) createInstance(defaultJSONParser, type);
        int length = this.sortedFieldDeserializers.length;
        int i = 0;
        while (i < length) {
            char c = i == length + (-1) ? ']' : ',';
            FieldDeserializer fieldDeserializer = this.sortedFieldDeserializers[i];
            FieldInfo fieldInfo = fieldDeserializer.fieldInfo;
            Class<?> cls = fieldInfo.fieldClass;
            try {
                if (cls == Integer.TYPE) {
                    int scanLongValue = (int) jSONLexer.scanLongValue();
                    if (fieldInfo.fieldAccess) {
                        fieldInfo.field.setInt(t, scanLongValue);
                    } else {
                        fieldDeserializer.setValue(t, new Integer(scanLongValue));
                    }
                    if (jSONLexer.ch == ',') {
                        int i2 = jSONLexer.bp + 1;
                        jSONLexer.bp = i2;
                        jSONLexer.ch = i2 >= jSONLexer.len ? JSONLexer.EOI : jSONLexer.text.charAt(i2);
                        jSONLexer.token = 16;
                    } else if (jSONLexer.ch == ']') {
                        int i3 = jSONLexer.bp + 1;
                        jSONLexer.bp = i3;
                        jSONLexer.ch = i3 >= jSONLexer.len ? JSONLexer.EOI : jSONLexer.text.charAt(i3);
                        jSONLexer.token = 15;
                    } else {
                        jSONLexer.nextToken();
                    }
                } else if (cls == String.class) {
                    if (jSONLexer.ch == '\"') {
                        str = jSONLexer.scanStringValue(Typography.quote);
                    } else {
                        if (jSONLexer.ch != 'n' || !jSONLexer.text.startsWith("null", jSONLexer.bp)) {
                            throw new JSONException("not match string. feild : " + obj);
                        }
                        jSONLexer.bp += 4;
                        jSONLexer.ch = jSONLexer.bp >= jSONLexer.len ? JSONLexer.EOI : jSONLexer.text.charAt(jSONLexer.bp);
                        str = null;
                    }
                    if (fieldInfo.fieldAccess) {
                        fieldInfo.field.set(t, str);
                    } else {
                        fieldDeserializer.setValue(t, str);
                    }
                    if (jSONLexer.ch == ',') {
                        int i4 = jSONLexer.bp + 1;
                        jSONLexer.bp = i4;
                        jSONLexer.ch = i4 >= jSONLexer.len ? JSONLexer.EOI : jSONLexer.text.charAt(i4);
                        jSONLexer.token = 16;
                    } else if (jSONLexer.ch == ']') {
                        int i5 = jSONLexer.bp + 1;
                        jSONLexer.bp = i5;
                        jSONLexer.ch = i5 >= jSONLexer.len ? JSONLexer.EOI : jSONLexer.text.charAt(i5);
                        jSONLexer.token = 15;
                    } else {
                        jSONLexer.nextToken();
                    }
                } else {
                    if (cls == Long.TYPE) {
                        long scanLongValue2 = jSONLexer.scanLongValue();
                        if (fieldInfo.fieldAccess) {
                            fieldInfo.field.setLong(t, scanLongValue2);
                        } else {
                            fieldDeserializer.setValue(t, new Long(scanLongValue2));
                        }
                        if (jSONLexer.ch == ',') {
                            int i6 = jSONLexer.bp + 1;
                            jSONLexer.bp = i6;
                            jSONLexer.ch = i6 >= jSONLexer.len ? JSONLexer.EOI : jSONLexer.text.charAt(i6);
                            jSONLexer.token = 16;
                        } else if (jSONLexer.ch == ']') {
                            int i7 = jSONLexer.bp + 1;
                            jSONLexer.bp = i7;
                            jSONLexer.ch = i7 >= jSONLexer.len ? JSONLexer.EOI : jSONLexer.text.charAt(i7);
                            jSONLexer.token = 15;
                        } else {
                            jSONLexer.nextToken();
                        }
                    } else if (cls == Boolean.TYPE) {
                        boolean scanBoolean = jSONLexer.scanBoolean();
                        if (fieldInfo.fieldAccess) {
                            fieldInfo.field.setBoolean(t, scanBoolean);
                        } else {
                            fieldDeserializer.setValue(t, Boolean.valueOf(scanBoolean));
                        }
                        if (jSONLexer.ch == ',') {
                            int i8 = jSONLexer.bp + 1;
                            jSONLexer.bp = i8;
                            jSONLexer.ch = i8 >= jSONLexer.len ? JSONLexer.EOI : jSONLexer.text.charAt(i8);
                            jSONLexer.token = 16;
                        } else if (jSONLexer.ch == ']') {
                            int i9 = jSONLexer.bp + 1;
                            jSONLexer.bp = i9;
                            jSONLexer.ch = i9 >= jSONLexer.len ? JSONLexer.EOI : jSONLexer.text.charAt(i9);
                            jSONLexer.token = 15;
                        } else {
                            jSONLexer.nextToken();
                        }
                    } else if (cls.isEnum()) {
                        char c2 = jSONLexer.ch;
                        if (c2 == '\"') {
                            String scanSymbol = jSONLexer.scanSymbol(defaultJSONParser.symbolTable);
                            r8 = scanSymbol == null ? null : Enum.valueOf(cls, scanSymbol);
                        } else {
                            if (c2 < '0' || c2 > '9') {
                                throw new JSONException("illegal enum." + jSONLexer.info());
                            }
                            r8 = ((EnumDeserializer) ((DefaultFieldDeserializer) fieldDeserializer).getFieldValueDeserilizer(defaultJSONParser.config)).ordinalEnums[(int) jSONLexer.scanLongValue()];
                        }
                        fieldDeserializer.setValue(t, r8);
                        if (jSONLexer.ch == ',') {
                            int i10 = jSONLexer.bp + 1;
                            jSONLexer.bp = i10;
                            jSONLexer.ch = i10 >= jSONLexer.len ? JSONLexer.EOI : jSONLexer.text.charAt(i10);
                            jSONLexer.token = 16;
                        } else if (jSONLexer.ch == ']') {
                            int i11 = jSONLexer.bp + 1;
                            jSONLexer.bp = i11;
                            jSONLexer.ch = i11 >= jSONLexer.len ? JSONLexer.EOI : jSONLexer.text.charAt(i11);
                            jSONLexer.token = 15;
                        } else {
                            jSONLexer.nextToken();
                        }
                    } else if (cls == Date.class && jSONLexer.ch == '1') {
                        fieldDeserializer.setValue(t, new Date(jSONLexer.scanLongValue()));
                        if (jSONLexer.ch == ',') {
                            int i12 = jSONLexer.bp + 1;
                            jSONLexer.bp = i12;
                            jSONLexer.ch = i12 >= jSONLexer.len ? JSONLexer.EOI : jSONLexer.text.charAt(i12);
                            jSONLexer.token = 16;
                        } else if (jSONLexer.ch == ']') {
                            int i13 = jSONLexer.bp + 1;
                            jSONLexer.bp = i13;
                            jSONLexer.ch = i13 >= jSONLexer.len ? JSONLexer.EOI : jSONLexer.text.charAt(i13);
                            jSONLexer.token = 15;
                        } else {
                            jSONLexer.nextToken();
                        }
                    } else {
                        if (jSONLexer.ch == '[') {
                            int i14 = jSONLexer.bp + 1;
                            jSONLexer.bp = i14;
                            jSONLexer.ch = i14 >= jSONLexer.len ? JSONLexer.EOI : jSONLexer.text.charAt(i14);
                            jSONLexer.token = 14;
                        } else if (jSONLexer.ch == '{') {
                            int i15 = jSONLexer.bp + 1;
                            jSONLexer.bp = i15;
                            jSONLexer.ch = i15 >= jSONLexer.len ? JSONLexer.EOI : jSONLexer.text.charAt(i15);
                            jSONLexer.token = 12;
                        } else {
                            jSONLexer.nextToken();
                        }
                        fieldDeserializer.parseField(defaultJSONParser, t, fieldInfo.fieldType, null);
                        if (c == ']') {
                            if (jSONLexer.token != 15) {
                                throw new JSONException("syntax error");
                            }
                        } else if (c == ',' && jSONLexer.token != 16) {
                            throw new JSONException("syntax error");
                        }
                    }
                    i++;
                }
                i++;
            } catch (IllegalAccessException e) {
                throw new JSONException("set " + fieldInfo.name + MqttServiceConstants.TRACE_ERROR, e);
            }
        }
        if (jSONLexer.ch == ',') {
            int i16 = jSONLexer.bp + 1;
            jSONLexer.bp = i16;
            jSONLexer.ch = i16 >= jSONLexer.len ? JSONLexer.EOI : jSONLexer.text.charAt(i16);
            jSONLexer.token = 16;
        } else {
            jSONLexer.nextToken();
        }
        return t;
    }

    private boolean parseField(DefaultJSONParser defaultJSONParser, String str, Object obj, Type type, Map<String, Object> map) {
        JSONLexer jSONLexer = defaultJSONParser.lexer;
        FieldDeserializer fieldDeserializer = getFieldDeserializer(str);
        if (fieldDeserializer == null) {
            fieldDeserializer = smartMatch(str);
        }
        int i = Feature.SupportNonPublicField.mask;
        if (fieldDeserializer == null && ((defaultJSONParser.lexer.features & i) != 0 || (i & this.beanInfo.parserFeatures) != 0)) {
            if (this.extraFieldDeserializers == null) {
                ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap(1, 0.75f, 1);
                for (Class<?> cls = this.clazz; cls != null && cls != Object.class; cls = cls.getSuperclass()) {
                    for (Field field : cls.getDeclaredFields()) {
                        String name = field.getName();
                        if (getFieldDeserializer(name) == null) {
                            int modifiers = field.getModifiers();
                            if ((modifiers & 16) == 0 && (modifiers & 8) == 0) {
                                concurrentHashMap.put(name, field);
                            }
                        }
                    }
                }
                this.extraFieldDeserializers = concurrentHashMap;
            }
            Object obj2 = this.extraFieldDeserializers.get(str);
            if (obj2 != null) {
                if (obj2 instanceof FieldDeserializer) {
                    fieldDeserializer = (FieldDeserializer) obj2;
                } else {
                    Field field2 = (Field) obj2;
                    field2.setAccessible(true);
                    fieldDeserializer = new DefaultFieldDeserializer(defaultJSONParser.config, this.clazz, new FieldInfo(str, field2.getDeclaringClass(), field2.getType(), field2.getGenericType(), field2, 0, 0));
                    this.extraFieldDeserializers.put(str, fieldDeserializer);
                }
            }
        }
        if (fieldDeserializer == null) {
            parseExtra(defaultJSONParser, obj, str);
            return false;
        }
        jSONLexer.nextTokenWithChar(':');
        fieldDeserializer.parseField(defaultJSONParser, obj, type, map);
        return true;
    }

    private FieldDeserializer smartMatch(String str) {
        boolean z;
        long fnv_64_lower = TypeUtils.fnv_64_lower(str);
        int i = 0;
        if (this.smartMatchHashArray == null) {
            long[] jArr = new long[this.sortedFieldDeserializers.length];
            int i2 = 0;
            while (true) {
                FieldDeserializer[] fieldDeserializerArr = this.sortedFieldDeserializers;
                if (i2 >= fieldDeserializerArr.length) {
                    break;
                }
                jArr[i2] = TypeUtils.fnv_64_lower(fieldDeserializerArr[i2].fieldInfo.name);
                i2++;
            }
            Arrays.sort(jArr);
            this.smartMatchHashArray = jArr;
        }
        int binarySearch = Arrays.binarySearch(this.smartMatchHashArray, fnv_64_lower);
        if (binarySearch < 0) {
            z = str.startsWith("is");
            if (z) {
                binarySearch = Arrays.binarySearch(this.smartMatchHashArray, TypeUtils.fnv_64_lower(str.substring(2)));
            }
        } else {
            z = false;
        }
        if (binarySearch < 0) {
            return null;
        }
        if (this.smartMatchHashArrayMapping == null) {
            int[] iArr = new int[this.smartMatchHashArray.length];
            Arrays.fill(iArr, -1);
            while (true) {
                FieldDeserializer[] fieldDeserializerArr2 = this.sortedFieldDeserializers;
                if (i >= fieldDeserializerArr2.length) {
                    break;
                }
                int binarySearch2 = Arrays.binarySearch(this.smartMatchHashArray, TypeUtils.fnv_64_lower(fieldDeserializerArr2[i].fieldInfo.name));
                if (binarySearch2 >= 0) {
                    iArr[binarySearch2] = i;
                }
                i++;
            }
            this.smartMatchHashArrayMapping = iArr;
        }
        int i3 = this.smartMatchHashArrayMapping[binarySearch];
        if (i3 == -1) {
            return null;
        }
        FieldDeserializer fieldDeserializer = this.sortedFieldDeserializers[i3];
        Class<?> cls = fieldDeserializer.fieldInfo.fieldClass;
        if (!z || cls == Boolean.TYPE || cls == Boolean.class) {
            return fieldDeserializer;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Object createInstance(DefaultJSONParser defaultJSONParser, Type type) {
        if ((type instanceof Class) && this.clazz.isInterface()) {
            return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{(Class) type}, new JSONObject((defaultJSONParser.lexer.features & Feature.OrderedField.mask) != 0));
        }
        if (this.beanInfo.defaultConstructor == null && this.beanInfo.factoryMethod == null) {
            return null;
        }
        if (this.beanInfo.factoryMethod != null && this.beanInfo.defaultConstructorParameterSize > 0) {
            return null;
        }
        try {
            Constructor<?> constructor = this.beanInfo.defaultConstructor;
            Object newInstance = this.beanInfo.defaultConstructorParameterSize == 0 ? constructor != null ? constructor.newInstance(new Object[0]) : this.beanInfo.factoryMethod.invoke(null, new Object[0]) : constructor.newInstance(defaultJSONParser.contex.object);
            if (defaultJSONParser != null && (defaultJSONParser.lexer.features & Feature.InitStringFieldAsEmpty.mask) != 0) {
                for (FieldInfo fieldInfo : this.beanInfo.fields) {
                    if (fieldInfo.fieldClass == String.class) {
                        fieldInfo.set(newInstance, "");
                    }
                }
            }
            return newInstance;
        } catch (Exception e) {
            throw new JSONException("create instance error, class " + this.clazz.getName(), e);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:63:0x0154  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x016e  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x0175  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object createInstance(java.util.Map<java.lang.String, java.lang.Object> r10, com.alibaba.fastjson.parser.ParserConfig r11) throws java.lang.IllegalAccessException, java.lang.IllegalArgumentException, java.lang.reflect.InvocationTargetException {
        /*
            Method dump skipped, instructions count: 461
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.JavaBeanDeserializer.createInstance(java.util.Map, com.alibaba.fastjson.parser.ParserConfig):java.lang.Object");
    }

    @Override // com.alibaba.fastjson.parser.deserializer.ObjectDeserializer
    public <T> T deserialze(DefaultJSONParser defaultJSONParser, Type type, Object obj) {
        return (T) deserialze(defaultJSONParser, type, obj, null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public FieldDeserializer getFieldDeserializer(String str) {
        if (str == null) {
            return null;
        }
        int i = 0;
        if (!this.beanInfo.ordered) {
            int length = this.sortedFieldDeserializers.length - 1;
            int i2 = 0;
            while (i2 <= length) {
                int i3 = (i2 + length) >>> 1;
                int compareTo = this.sortedFieldDeserializers[i3].fieldInfo.name.compareTo(str);
                if (compareTo < 0) {
                    i2 = i3 + 1;
                } else {
                    if (compareTo <= 0) {
                        return this.sortedFieldDeserializers[i3];
                    }
                    length = i3 - 1;
                }
            }
            Map<String, FieldDeserializer> map = this.alterNameFieldDeserializers;
            if (map != null) {
                return map.get(str);
            }
            if (this.smartMatchHashArray == null) {
                long[] jArr = new long[this.sortedFieldDeserializers.length];
                int i4 = 0;
                while (true) {
                    FieldDeserializer[] fieldDeserializerArr = this.sortedFieldDeserializers;
                    if (i4 >= fieldDeserializerArr.length) {
                        break;
                    }
                    jArr[i4] = TypeUtils.fnv_64_lower(fieldDeserializerArr[i4].fieldInfo.name);
                    i4++;
                }
                Arrays.sort(jArr);
                this.smartMatchHashArray = jArr;
            }
            int binarySearch = Arrays.binarySearch(this.smartMatchHashArray, TypeUtils.fnv_64_lower(str));
            if (binarySearch >= 0) {
                if (this.smartMatchHashArrayMapping == null) {
                    int[] iArr = new int[this.smartMatchHashArray.length];
                    Arrays.fill(iArr, -1);
                    while (true) {
                        FieldDeserializer[] fieldDeserializerArr2 = this.sortedFieldDeserializers;
                        if (i >= fieldDeserializerArr2.length) {
                            break;
                        }
                        int binarySearch2 = Arrays.binarySearch(this.smartMatchHashArray, TypeUtils.fnv_64_lower(fieldDeserializerArr2[i].fieldInfo.name));
                        if (binarySearch2 >= 0) {
                            iArr[binarySearch2] = i;
                        }
                        i++;
                    }
                    this.smartMatchHashArrayMapping = iArr;
                }
                int i5 = this.smartMatchHashArrayMapping[binarySearch];
                if (i5 != -1) {
                    return this.sortedFieldDeserializers[i5];
                }
            }
            return smartMatch(str);
        }
        while (true) {
            FieldDeserializer[] fieldDeserializerArr3 = this.sortedFieldDeserializers;
            if (i >= fieldDeserializerArr3.length) {
                return null;
            }
            FieldDeserializer fieldDeserializer = fieldDeserializerArr3[i];
            if (fieldDeserializer.fieldInfo.name.equalsIgnoreCase(str)) {
                return fieldDeserializer;
            }
            i++;
        }
    }

    protected FieldDeserializer getFieldDeserializerByHash(long j) {
        int i = 0;
        while (true) {
            FieldDeserializer[] fieldDeserializerArr = this.sortedFieldDeserializers;
            if (i >= fieldDeserializerArr.length) {
                return null;
            }
            FieldDeserializer fieldDeserializer = fieldDeserializerArr[i];
            if (fieldDeserializer.fieldInfo.nameHashCode == j) {
                return fieldDeserializer;
            }
            i++;
        }
    }

    protected JavaBeanDeserializer getSeeAlso(ParserConfig parserConfig, JavaBeanInfo javaBeanInfo, String str) {
        if (javaBeanInfo.jsonType == null) {
            return null;
        }
        for (Class<?> cls : javaBeanInfo.jsonType.seeAlso()) {
            ObjectDeserializer deserializer = parserConfig.getDeserializer(cls);
            if (deserializer instanceof JavaBeanDeserializer) {
                JavaBeanDeserializer javaBeanDeserializer = (JavaBeanDeserializer) deserializer;
                JavaBeanInfo javaBeanInfo2 = javaBeanDeserializer.beanInfo;
                if (javaBeanInfo2.typeName.equals(str)) {
                    return javaBeanDeserializer;
                }
                JavaBeanDeserializer seeAlso = getSeeAlso(parserConfig, javaBeanInfo2, str);
                if (seeAlso != null) {
                    return seeAlso;
                }
            }
        }
        return null;
    }

    void parseExtra(DefaultJSONParser defaultJSONParser, Object obj, String str) {
        JSONLexer jSONLexer = defaultJSONParser.lexer;
        if ((defaultJSONParser.lexer.features & Feature.IgnoreNotMatch.mask) == 0) {
            throw new JSONException("setter not found, class " + this.clazz.getName() + ", property " + str);
        }
        jSONLexer.nextTokenWithChar(':');
        Type type = null;
        List<ExtraTypeProvider> list = defaultJSONParser.extraTypeProviders;
        if (list != null) {
            Iterator<ExtraTypeProvider> it2 = list.iterator();
            while (it2.hasNext()) {
                type = it2.next().getExtraType(obj, str);
            }
        }
        Object parse = type == null ? defaultJSONParser.parse() : defaultJSONParser.parseObject(type);
        if (obj instanceof ExtraProcessable) {
            ((ExtraProcessable) obj).processExtra(str, parse);
            return;
        }
        List<ExtraProcessor> list2 = defaultJSONParser.extraProcessors;
        if (list2 != null) {
            Iterator<ExtraProcessor> it3 = list2.iterator();
            while (it3.hasNext()) {
                it3.next().processExtra(obj, str, parse);
            }
        }
    }
}
