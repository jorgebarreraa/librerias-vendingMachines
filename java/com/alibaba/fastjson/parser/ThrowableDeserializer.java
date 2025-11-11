package com.alibaba.fastjson.parser;

/* loaded from: classes.dex */
public class ThrowableDeserializer extends JavaBeanDeserializer {
    public ThrowableDeserializer(ParserConfig parserConfig, Class<?> cls) {
        super(parserConfig, cls, cls);
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x0032, code lost:
    
        if (java.lang.Throwable.class.isAssignableFrom(r2) != false) goto L18;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x00d8, code lost:
    
        if (r10 != null) goto L113;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x00da, code lost:
    
        r3 = (T) new java.lang.Exception(r11, r4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0167, code lost:
    
        if (r12 == null) goto L91;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0169, code lost:
    
        ((java.lang.Throwable) r3).setStackTrace(r12);
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x016c, code lost:
    
        if (r2 == null) goto L107;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x016e, code lost:
    
        if (r10 == null) goto L99;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0172, code lost:
    
        if (r10 != r17.clazz) goto L96;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x0174, code lost:
    
        r0 = r17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x0184, code lost:
    
        if (r0 == null) goto L107;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x0186, code lost:
    
        r2 = r2.entrySet().iterator();
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x0192, code lost:
    
        if (r2.hasNext() == false) goto L121;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x0194, code lost:
    
        r4 = (java.util.Map.Entry) r2.next();
        r5 = (java.lang.String) r4.getKey();
        r4 = r4.getValue();
        r5 = r0.getFieldDeserializer(r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x01a8, code lost:
    
        if (r5 == null) goto L124;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x01aa, code lost:
    
        r5.setValue(r3, r4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x0176, code lost:
    
        r0 = r18.config.getDeserializer(r10);
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x017e, code lost:
    
        if ((r0 instanceof com.alibaba.fastjson.parser.JavaBeanDeserializer) == false) goto L99;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x0180, code lost:
    
        r0 = (com.alibaba.fastjson.parser.JavaBeanDeserializer) r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x0183, code lost:
    
        r0 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x01ae, code lost:
    
        return (T) r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x00e1, code lost:
    
        r3 = r10.getConstructors();
        r5 = r3.length;
        r8 = null;
        r13 = null;
        r14 = null;
        r7 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x00eb, code lost:
    
        if (r7 >= r5) goto L126;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x00ed, code lost:
    
        r16 = r3[r7];
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x00f4, code lost:
    
        if (r16.getParameterTypes().length != 0) goto L65;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x00f6, code lost:
    
        r14 = r16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x012c, code lost:
    
        r7 = r7 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x00fe, code lost:
    
        if (r16.getParameterTypes().length != 1) goto L70;
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x0108, code lost:
    
        if (r16.getParameterTypes()[0] != java.lang.String.class) goto L70;
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x010a, code lost:
    
        r13 = r16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x0113, code lost:
    
        if (r16.getParameterTypes().length != 2) goto L130;
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x011d, code lost:
    
        if (r16.getParameterTypes()[0] != java.lang.String.class) goto L131;
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x0128, code lost:
    
        if (r16.getParameterTypes()[1] != java.lang.Throwable.class) goto L132;
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x012a, code lost:
    
        r8 = r16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x0130, code lost:
    
        if (r8 == null) goto L80;
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x0132, code lost:
    
        r6 = (java.lang.Throwable) r8.newInstance(r11, r4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:80:0x015e, code lost:
    
        if (r6 != null) goto L88;
     */
    /* JADX WARN: Code restructure failed: missing block: B:81:0x0160, code lost:
    
        r3 = (T) new java.lang.Exception(r11, r4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:83:0x0166, code lost:
    
        r3 = (T) r6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:84:0x0142, code lost:
    
        if (r13 == null) goto L82;
     */
    /* JADX WARN: Code restructure failed: missing block: B:85:0x0144, code lost:
    
        r6 = (java.lang.Throwable) r13.newInstance(r11);
     */
    /* JADX WARN: Code restructure failed: missing block: B:86:0x0151, code lost:
    
        if (r14 == null) goto L84;
     */
    /* JADX WARN: Code restructure failed: missing block: B:87:0x0153, code lost:
    
        r6 = (java.lang.Throwable) r14.newInstance(new java.lang.Object[0]);
     */
    /* JADX WARN: Code restructure failed: missing block: B:88:0x015d, code lost:
    
        r6 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:89:0x01af, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:91:0x01b7, code lost:
    
        throw new com.alibaba.fastjson.JSONException("create instance error", r0);
     */
    @Override // com.alibaba.fastjson.parser.JavaBeanDeserializer, com.alibaba.fastjson.parser.deserializer.ObjectDeserializer
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public <T> T deserialze(com.alibaba.fastjson.parser.DefaultJSONParser r18, java.lang.reflect.Type r19, java.lang.Object r20) {
        /*
            Method dump skipped, instructions count: 446
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.ThrowableDeserializer.deserialze(com.alibaba.fastjson.parser.DefaultJSONParser, java.lang.reflect.Type, java.lang.Object):java.lang.Object");
    }
}
