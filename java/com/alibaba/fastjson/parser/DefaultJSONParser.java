package com.alibaba.fastjson.parser;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.deserializer.ExtraProcessor;
import com.alibaba.fastjson.parser.deserializer.ExtraTypeProvider;
import com.alibaba.fastjson.parser.deserializer.FieldDeserializer;
import com.alibaba.fastjson.parser.deserializer.FieldTypeResolver;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.serializer.IntegerCodec;
import com.alibaba.fastjson.serializer.StringCodec;
import com.alibaba.fastjson.util.TypeUtils;
import java.io.Closeable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

/* loaded from: classes.dex */
public class DefaultJSONParser implements Closeable {
    public static final int NONE = 0;
    public static final int NeedToResolve = 1;
    public static final int TypeNameRedirect = 2;
    public ParserConfig config;
    protected ParseContext contex;
    private ParseContext[] contextArray;
    private int contextArrayIndex;
    private DateFormat dateFormat;
    private String dateFormatPattern;
    protected List<ExtraProcessor> extraProcessors;
    protected List<ExtraTypeProvider> extraTypeProviders;
    public FieldTypeResolver fieldTypeResolver;
    public final JSONLexer lexer;
    public int resolveStatus;
    private List<ResolveTask> resolveTaskList;
    public final SymbolTable symbolTable;

    /* loaded from: classes.dex */
    public static class ResolveTask {
        private final ParseContext context;
        public FieldDeserializer fieldDeserializer;
        public ParseContext ownerContext;
        private final String referenceValue;

        public ResolveTask(ParseContext parseContext, String str) {
            this.context = parseContext;
            this.referenceValue = str;
        }
    }

    public DefaultJSONParser(JSONLexer jSONLexer) {
        this(jSONLexer, ParserConfig.global);
    }

    public DefaultJSONParser(JSONLexer jSONLexer, ParserConfig parserConfig) {
        this.dateFormatPattern = JSON.DEFFAULT_DATE_FORMAT;
        this.contextArrayIndex = 0;
        this.resolveStatus = 0;
        this.extraTypeProviders = null;
        this.extraProcessors = null;
        this.fieldTypeResolver = null;
        this.lexer = jSONLexer;
        this.config = parserConfig;
        this.symbolTable = parserConfig.symbolTable;
        char c = jSONLexer.ch;
        char c2 = JSONLexer.EOI;
        if (c == '{') {
            int i = jSONLexer.bp + 1;
            jSONLexer.bp = i;
            jSONLexer.ch = i < jSONLexer.len ? jSONLexer.text.charAt(i) : c2;
            jSONLexer.token = 12;
            return;
        }
        if (jSONLexer.ch != '[') {
            jSONLexer.nextToken();
            return;
        }
        int i2 = jSONLexer.bp + 1;
        jSONLexer.bp = i2;
        jSONLexer.ch = i2 < jSONLexer.len ? jSONLexer.text.charAt(i2) : c2;
        jSONLexer.token = 14;
    }

    public DefaultJSONParser(String str) {
        this(str, ParserConfig.global, JSON.DEFAULT_PARSER_FEATURE);
    }

    public DefaultJSONParser(String str, ParserConfig parserConfig) {
        this(new JSONLexer(str, JSON.DEFAULT_PARSER_FEATURE), parserConfig);
    }

    public DefaultJSONParser(String str, ParserConfig parserConfig, int i) {
        this(new JSONLexer(str, i), parserConfig);
    }

    public DefaultJSONParser(char[] cArr, int i, ParserConfig parserConfig, int i2) {
        this(new JSONLexer(cArr, i, i2), parserConfig);
    }

    public final void accept(int i) {
        if (this.lexer.token == i) {
            this.lexer.nextToken();
            return;
        }
        throw new JSONException("syntax error, expect " + JSONToken.name(i) + ", actual " + JSONToken.name(this.lexer.token));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void addResolveTask(ResolveTask resolveTask) {
        if (this.resolveTaskList == null) {
            this.resolveTaskList = new ArrayList(2);
        }
        this.resolveTaskList.add(resolveTask);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void checkListResolve(Collection collection) {
        if (collection instanceof List) {
            ResolveTask lastResolveTask = getLastResolveTask();
            lastResolveTask.fieldDeserializer = new ResolveFieldDeserializer(this, (List) collection, collection.size() - 1);
            lastResolveTask.ownerContext = this.contex;
            this.resolveStatus = 0;
            return;
        }
        ResolveTask lastResolveTask2 = getLastResolveTask();
        lastResolveTask2.fieldDeserializer = new ResolveFieldDeserializer(collection);
        lastResolveTask2.ownerContext = this.contex;
        this.resolveStatus = 0;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void checkMapResolve(Map map, Object obj) {
        ResolveFieldDeserializer resolveFieldDeserializer = new ResolveFieldDeserializer(map, obj);
        ResolveTask lastResolveTask = getLastResolveTask();
        lastResolveTask.fieldDeserializer = resolveFieldDeserializer;
        lastResolveTask.ownerContext = this.contex;
        this.resolveStatus = 0;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        try {
            if (this.lexer.token == 20) {
                return;
            }
            throw new JSONException("not close json text, token : " + JSONToken.name(this.lexer.token));
        } finally {
            this.lexer.close();
        }
    }

    public void config(Feature feature, boolean z) {
        this.lexer.config(feature, z);
    }

    public String getDateFomartPattern() {
        return this.dateFormatPattern;
    }

    public DateFormat getDateFormat() {
        if (this.dateFormat == null) {
            this.dateFormat = new SimpleDateFormat(this.dateFormatPattern, this.lexer.locale);
            this.dateFormat.setTimeZone(this.lexer.timeZone);
        }
        return this.dateFormat;
    }

    public List<ExtraProcessor> getExtraProcessors() {
        if (this.extraProcessors == null) {
            this.extraProcessors = new ArrayList(2);
        }
        return this.extraProcessors;
    }

    public List<ExtraTypeProvider> getExtraTypeProviders() {
        if (this.extraTypeProviders == null) {
            this.extraTypeProviders = new ArrayList(2);
        }
        return this.extraTypeProviders;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ResolveTask getLastResolveTask() {
        return this.resolveTaskList.get(r0.size() - 1);
    }

    public void handleResovleTask(Object obj) {
        List<ResolveTask> list = this.resolveTaskList;
        if (list == null) {
            return;
        }
        int size = list.size();
        for (int i = 0; i < size; i++) {
            ResolveTask resolveTask = this.resolveTaskList.get(i);
            FieldDeserializer fieldDeserializer = resolveTask.fieldDeserializer;
            if (fieldDeserializer != null) {
                Object obj2 = null;
                Object obj3 = resolveTask.ownerContext != null ? resolveTask.ownerContext.object : null;
                String str = resolveTask.referenceValue;
                if (str.startsWith("$")) {
                    for (int i2 = 0; i2 < this.contextArrayIndex; i2++) {
                        if (str.equals(this.contextArray[i2].toString())) {
                            obj2 = this.contextArray[i2].object;
                        }
                    }
                } else {
                    obj2 = resolveTask.context.object;
                }
                fieldDeserializer.setValue(obj3, obj2);
            }
        }
    }

    public Object parse() {
        return parse(null);
    }

    public Object parse(Object obj) {
        int i = this.lexer.token;
        if (i == 2) {
            Number integerValue = this.lexer.integerValue();
            this.lexer.nextToken();
            return integerValue;
        }
        if (i == 3) {
            Number decimalValue = this.lexer.decimalValue((this.lexer.features & Feature.UseBigDecimal.mask) != 0);
            this.lexer.nextToken();
            return decimalValue;
        }
        if (i == 4) {
            String stringVal = this.lexer.stringVal();
            this.lexer.nextToken(16);
            if ((this.lexer.features & Feature.AllowISO8601DateFormat.mask) != 0) {
                JSONLexer jSONLexer = new JSONLexer(stringVal);
                try {
                    if (jSONLexer.scanISO8601DateIfMatch(true)) {
                        return jSONLexer.calendar.getTime();
                    }
                } finally {
                    jSONLexer.close();
                }
            }
            return stringVal;
        }
        if (i == 12) {
            return parseObject((this.lexer.features & Feature.OrderedField.mask) != 0 ? new JSONObject(new LinkedHashMap()) : new JSONObject(), obj);
        }
        if (i == 14) {
            JSONArray jSONArray = new JSONArray();
            parseArray(jSONArray, obj);
            return jSONArray;
        }
        switch (i) {
            case 6:
                this.lexer.nextToken(16);
                return Boolean.TRUE;
            case 7:
                this.lexer.nextToken(16);
                return Boolean.FALSE;
            case 8:
                break;
            case 9:
                this.lexer.nextToken(18);
                if (this.lexer.token != 18) {
                    throw new JSONException("syntax error, " + this.lexer.info());
                }
                this.lexer.nextToken(10);
                accept(10);
                long longValue = this.lexer.integerValue().longValue();
                accept(2);
                accept(11);
                return new Date(longValue);
            default:
                switch (i) {
                    case 20:
                        if (this.lexer.isBlankInput()) {
                            return null;
                        }
                        throw new JSONException("syntax error, " + this.lexer.info());
                    case 21:
                        this.lexer.nextToken();
                        HashSet hashSet = new HashSet();
                        parseArray(hashSet, obj);
                        return hashSet;
                    case 22:
                        this.lexer.nextToken();
                        TreeSet treeSet = new TreeSet();
                        parseArray(treeSet, obj);
                        return treeSet;
                    case 23:
                        break;
                    default:
                        throw new JSONException("syntax error, " + this.lexer.info());
                }
        }
        this.lexer.nextToken();
        return null;
    }

    public <T> List<T> parseArray(Class<T> cls) {
        ArrayList arrayList = new ArrayList();
        parseArray((Class<?>) cls, (Collection) arrayList);
        return arrayList;
    }

    public void parseArray(Class<?> cls, Collection collection) {
        parseArray((Type) cls, collection);
    }

    public void parseArray(Type type, Collection collection) {
        parseArray(type, collection, null);
    }

    public void parseArray(Type type, Collection collection, Object obj) {
        ObjectDeserializer deserializer;
        String str;
        if (this.lexer.token == 21 || this.lexer.token == 22) {
            this.lexer.nextToken();
        }
        if (this.lexer.token != 14) {
            throw new JSONException("exepct '[', but " + JSONToken.name(this.lexer.token) + ", " + this.lexer.info());
        }
        if (Integer.TYPE == type) {
            deserializer = IntegerCodec.instance;
            this.lexer.nextToken(2);
        } else if (String.class == type) {
            deserializer = StringCodec.instance;
            this.lexer.nextToken(4);
        } else {
            deserializer = this.config.getDeserializer(type);
            this.lexer.nextToken(12);
        }
        ParseContext parseContext = this.contex;
        if (!this.lexer.disableCircularReferenceDetect) {
            setContext(this.contex, collection, obj);
        }
        int i = 0;
        while (true) {
            try {
                if (this.lexer.token == 16) {
                    this.lexer.nextToken();
                } else {
                    if (this.lexer.token == 15) {
                        this.contex = parseContext;
                        this.lexer.nextToken(16);
                        return;
                    }
                    Object obj2 = null;
                    String obj3 = null;
                    if (Integer.TYPE == type) {
                        collection.add(IntegerCodec.instance.deserialze(this, null, null));
                    } else if (String.class == type) {
                        if (this.lexer.token == 4) {
                            str = this.lexer.stringVal();
                            this.lexer.nextToken(16);
                        } else {
                            Object parse = parse();
                            if (parse != null) {
                                obj3 = parse.toString();
                            }
                            str = obj3;
                        }
                        collection.add(str);
                    } else {
                        if (this.lexer.token == 8) {
                            this.lexer.nextToken();
                        } else {
                            obj2 = deserializer.deserialze(this, type, Integer.valueOf(i));
                        }
                        collection.add(obj2);
                        if (this.resolveStatus == 1) {
                            checkListResolve(collection);
                        }
                    }
                    if (this.lexer.token == 16) {
                        this.lexer.nextToken();
                    }
                    i++;
                }
            } catch (Throwable th) {
                this.contex = parseContext;
                throw th;
            }
        }
    }

    public final void parseArray(Collection collection) {
        parseArray(collection, (Object) null);
    }

    /* JADX WARN: Removed duplicated region for block: B:139:0x0202 A[Catch: all -> 0x0280, TryCatch #0 {all -> 0x0280, blocks: (B:11:0x002a, B:14:0x003e, B:21:0x004f, B:24:0x0069, B:28:0x008d, B:30:0x0093, B:32:0x00a1, B:35:0x00bb, B:37:0x00c4, B:43:0x00ce, B:44:0x00b3, B:48:0x00d7, B:51:0x00f1, B:53:0x00fa, B:54:0x00fd, B:59:0x00e9, B:46:0x0107, B:60:0x010c, B:62:0x0112, B:84:0x0141, B:86:0x0210, B:88:0x0217, B:89:0x021a, B:91:0x0220, B:93:0x0226, B:98:0x023d, B:101:0x0250, B:104:0x026e, B:106:0x0266, B:107:0x0271, B:110:0x0149, B:115:0x0153, B:116:0x0160, B:118:0x0168, B:119:0x016f, B:120:0x0170, B:122:0x017d, B:123:0x018d, B:124:0x0188, B:125:0x0196, B:126:0x019e, B:127:0x01a8, B:128:0x01b2, B:130:0x01ca, B:132:0x01d5, B:133:0x01db, B:134:0x01e0, B:136:0x01ed, B:137:0x01fc, B:138:0x01f5, B:139:0x0202, B:140:0x0061, B:141:0x0070, B:142:0x0077, B:145:0x0084), top: B:10:0x002a }] */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00a1 A[Catch: all -> 0x0280, TryCatch #0 {all -> 0x0280, blocks: (B:11:0x002a, B:14:0x003e, B:21:0x004f, B:24:0x0069, B:28:0x008d, B:30:0x0093, B:32:0x00a1, B:35:0x00bb, B:37:0x00c4, B:43:0x00ce, B:44:0x00b3, B:48:0x00d7, B:51:0x00f1, B:53:0x00fa, B:54:0x00fd, B:59:0x00e9, B:46:0x0107, B:60:0x010c, B:62:0x0112, B:84:0x0141, B:86:0x0210, B:88:0x0217, B:89:0x021a, B:91:0x0220, B:93:0x0226, B:98:0x023d, B:101:0x0250, B:104:0x026e, B:106:0x0266, B:107:0x0271, B:110:0x0149, B:115:0x0153, B:116:0x0160, B:118:0x0168, B:119:0x016f, B:120:0x0170, B:122:0x017d, B:123:0x018d, B:124:0x0188, B:125:0x0196, B:126:0x019e, B:127:0x01a8, B:128:0x01b2, B:130:0x01ca, B:132:0x01d5, B:133:0x01db, B:134:0x01e0, B:136:0x01ed, B:137:0x01fc, B:138:0x01f5, B:139:0x0202, B:140:0x0061, B:141:0x0070, B:142:0x0077, B:145:0x0084), top: B:10:0x002a }] */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00d5  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x0112 A[Catch: all -> 0x0280, LOOP:1: B:61:0x0110->B:62:0x0112, LOOP_END, TryCatch #0 {all -> 0x0280, blocks: (B:11:0x002a, B:14:0x003e, B:21:0x004f, B:24:0x0069, B:28:0x008d, B:30:0x0093, B:32:0x00a1, B:35:0x00bb, B:37:0x00c4, B:43:0x00ce, B:44:0x00b3, B:48:0x00d7, B:51:0x00f1, B:53:0x00fa, B:54:0x00fd, B:59:0x00e9, B:46:0x0107, B:60:0x010c, B:62:0x0112, B:84:0x0141, B:86:0x0210, B:88:0x0217, B:89:0x021a, B:91:0x0220, B:93:0x0226, B:98:0x023d, B:101:0x0250, B:104:0x026e, B:106:0x0266, B:107:0x0271, B:110:0x0149, B:115:0x0153, B:116:0x0160, B:118:0x0168, B:119:0x016f, B:120:0x0170, B:122:0x017d, B:123:0x018d, B:124:0x0188, B:125:0x0196, B:126:0x019e, B:127:0x01a8, B:128:0x01b2, B:130:0x01ca, B:132:0x01d5, B:133:0x01db, B:134:0x01e0, B:136:0x01ed, B:137:0x01fc, B:138:0x01f5, B:139:0x0202, B:140:0x0061, B:141:0x0070, B:142:0x0077, B:145:0x0084), top: B:10:0x002a }] */
    /* JADX WARN: Removed duplicated region for block: B:66:0x0120  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x0217 A[Catch: all -> 0x0280, TryCatch #0 {all -> 0x0280, blocks: (B:11:0x002a, B:14:0x003e, B:21:0x004f, B:24:0x0069, B:28:0x008d, B:30:0x0093, B:32:0x00a1, B:35:0x00bb, B:37:0x00c4, B:43:0x00ce, B:44:0x00b3, B:48:0x00d7, B:51:0x00f1, B:53:0x00fa, B:54:0x00fd, B:59:0x00e9, B:46:0x0107, B:60:0x010c, B:62:0x0112, B:84:0x0141, B:86:0x0210, B:88:0x0217, B:89:0x021a, B:91:0x0220, B:93:0x0226, B:98:0x023d, B:101:0x0250, B:104:0x026e, B:106:0x0266, B:107:0x0271, B:110:0x0149, B:115:0x0153, B:116:0x0160, B:118:0x0168, B:119:0x016f, B:120:0x0170, B:122:0x017d, B:123:0x018d, B:124:0x0188, B:125:0x0196, B:126:0x019e, B:127:0x01a8, B:128:0x01b2, B:130:0x01ca, B:132:0x01d5, B:133:0x01db, B:134:0x01e0, B:136:0x01ed, B:137:0x01fc, B:138:0x01f5, B:139:0x0202, B:140:0x0061, B:141:0x0070, B:142:0x0077, B:145:0x0084), top: B:10:0x002a }] */
    /* JADX WARN: Removed duplicated region for block: B:91:0x0220 A[Catch: all -> 0x0280, TryCatch #0 {all -> 0x0280, blocks: (B:11:0x002a, B:14:0x003e, B:21:0x004f, B:24:0x0069, B:28:0x008d, B:30:0x0093, B:32:0x00a1, B:35:0x00bb, B:37:0x00c4, B:43:0x00ce, B:44:0x00b3, B:48:0x00d7, B:51:0x00f1, B:53:0x00fa, B:54:0x00fd, B:59:0x00e9, B:46:0x0107, B:60:0x010c, B:62:0x0112, B:84:0x0141, B:86:0x0210, B:88:0x0217, B:89:0x021a, B:91:0x0220, B:93:0x0226, B:98:0x023d, B:101:0x0250, B:104:0x026e, B:106:0x0266, B:107:0x0271, B:110:0x0149, B:115:0x0153, B:116:0x0160, B:118:0x0168, B:119:0x016f, B:120:0x0170, B:122:0x017d, B:123:0x018d, B:124:0x0188, B:125:0x0196, B:126:0x019e, B:127:0x01a8, B:128:0x01b2, B:130:0x01ca, B:132:0x01d5, B:133:0x01db, B:134:0x01e0, B:136:0x01ed, B:137:0x01fc, B:138:0x01f5, B:139:0x0202, B:140:0x0061, B:141:0x0070, B:142:0x0077, B:145:0x0084), top: B:10:0x002a }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void parseArray(java.util.Collection r17, java.lang.Object r18) {
        /*
            Method dump skipped, instructions count: 685
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.DefaultJSONParser.parseArray(java.util.Collection, java.lang.Object):void");
    }

    public Object[] parseArray(Type[] typeArr) {
        Object cast;
        Class<?> cls;
        boolean z;
        Object cast2;
        int i = 8;
        if (this.lexer.token == 8) {
            this.lexer.nextToken(16);
            return null;
        }
        if (this.lexer.token != 14) {
            throw new JSONException("syntax error, " + this.lexer.info());
        }
        Object[] objArr = new Object[typeArr.length];
        if (typeArr.length == 0) {
            this.lexer.nextToken(15);
            if (this.lexer.token == 15) {
                this.lexer.nextToken(16);
                return new Object[0];
            }
            throw new JSONException("syntax error, " + this.lexer.info());
        }
        this.lexer.nextToken(2);
        int i2 = 0;
        while (i2 < typeArr.length) {
            if (this.lexer.token == i) {
                this.lexer.nextToken(16);
                cast = null;
            } else {
                Type type = typeArr[i2];
                if (type == Integer.TYPE || type == Integer.class) {
                    if (this.lexer.token == 2) {
                        cast = Integer.valueOf(this.lexer.intValue());
                        this.lexer.nextToken(16);
                    } else {
                        cast = TypeUtils.cast(parse(), type, this.config);
                    }
                } else if (type == String.class) {
                    if (this.lexer.token == 4) {
                        cast2 = this.lexer.stringVal();
                        this.lexer.nextToken(16);
                    } else {
                        cast2 = TypeUtils.cast(parse(), type, this.config);
                    }
                    cast = cast2;
                } else {
                    if (i2 == typeArr.length - 1 && (type instanceof Class)) {
                        Class cls2 = (Class) type;
                        z = cls2.isArray();
                        cls = cls2.getComponentType();
                    } else {
                        cls = null;
                        z = false;
                    }
                    if (!z || this.lexer.token == 14) {
                        cast = this.config.getDeserializer(type).deserialze(this, type, null);
                    } else {
                        ArrayList arrayList = new ArrayList();
                        ObjectDeserializer deserializer = this.config.getDeserializer(cls);
                        if (this.lexer.token != 15) {
                            while (true) {
                                arrayList.add(deserializer.deserialze(this, type, null));
                                if (this.lexer.token != 16) {
                                    break;
                                }
                                this.lexer.nextToken(12);
                            }
                            if (this.lexer.token != 15) {
                                throw new JSONException("syntax error, " + this.lexer.info());
                            }
                        }
                        cast = TypeUtils.cast(arrayList, type, this.config);
                    }
                }
            }
            objArr[i2] = cast;
            if (this.lexer.token == 15) {
                break;
            }
            if (this.lexer.token != 16) {
                throw new JSONException("syntax error, " + this.lexer.info());
            }
            if (i2 == typeArr.length - 1) {
                this.lexer.nextToken(15);
            } else {
                this.lexer.nextToken(2);
            }
            i2++;
            i = 8;
        }
        if (this.lexer.token == 15) {
            this.lexer.nextToken(16);
            return objArr;
        }
        throw new JSONException("syntax error, " + this.lexer.info());
    }

    public Object parseArrayWithType(Type type) {
        if (this.lexer.token == 8) {
            this.lexer.nextToken();
            return null;
        }
        Type[] actualTypeArguments = ((ParameterizedType) type).getActualTypeArguments();
        if (actualTypeArguments.length != 1) {
            throw new JSONException("not support type " + type);
        }
        Type type2 = actualTypeArguments[0];
        if (type2 instanceof Class) {
            ArrayList arrayList = new ArrayList();
            parseArray((Class<?>) type2, (Collection) arrayList);
            return arrayList;
        }
        if (type2 instanceof WildcardType) {
            WildcardType wildcardType = (WildcardType) type2;
            Type type3 = wildcardType.getUpperBounds()[0];
            if (!Object.class.equals(type3)) {
                ArrayList arrayList2 = new ArrayList();
                parseArray((Class<?>) type3, (Collection) arrayList2);
                return arrayList2;
            }
            if (wildcardType.getLowerBounds().length == 0) {
                return parse();
            }
            throw new JSONException("not support type : " + type);
        }
        if (type2 instanceof TypeVariable) {
            TypeVariable typeVariable = (TypeVariable) type2;
            Type[] bounds = typeVariable.getBounds();
            if (bounds.length != 1) {
                throw new JSONException("not support : " + typeVariable);
            }
            Type type4 = bounds[0];
            if (type4 instanceof Class) {
                ArrayList arrayList3 = new ArrayList();
                parseArray((Class<?>) type4, (Collection) arrayList3);
                return arrayList3;
            }
        }
        if (type2 instanceof ParameterizedType) {
            ArrayList arrayList4 = new ArrayList();
            parseArray((ParameterizedType) type2, arrayList4);
            return arrayList4;
        }
        throw new JSONException("TODO : " + type);
    }

    public JSONObject parseObject() {
        return (JSONObject) parseObject((this.lexer.features & Feature.OrderedField.mask) != 0 ? new JSONObject(new LinkedHashMap()) : new JSONObject(), (Object) null);
    }

    public <T> T parseObject(Class<T> cls) {
        return (T) parseObject(cls, (Object) null);
    }

    public <T> T parseObject(Type type) {
        return (T) parseObject(type, (Object) null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public <T> T parseObject(Type type, Object obj) {
        if (this.lexer.token == 8) {
            this.lexer.nextToken();
            return null;
        }
        if (this.lexer.token == 4) {
            if (type == byte[].class) {
                T t = (T) this.lexer.bytesValue();
                this.lexer.nextToken();
                return t;
            }
            if (type == char[].class) {
                String stringVal = this.lexer.stringVal();
                this.lexer.nextToken();
                return (T) stringVal.toCharArray();
            }
        }
        try {
            return (T) this.config.getDeserializer(type).deserialze(this, type, obj);
        } catch (JSONException e) {
            throw e;
        } catch (Exception e2) {
            throw new JSONException(e2.getMessage(), e2);
        }
    }

    public Object parseObject(Map map) {
        return parseObject(map, (Object) null);
    }

    /* JADX WARN: Code restructure failed: missing block: B:102:0x0292, code lost:
    
        if (r3 != null) goto L160;
     */
    /* JADX WARN: Code restructure failed: missing block: B:104:0x0296, code lost:
    
        if (r6 != java.lang.Cloneable.class) goto L156;
     */
    /* JADX WARN: Code restructure failed: missing block: B:105:0x0298, code lost:
    
        r3 = new java.util.HashMap();
     */
    /* JADX WARN: Code restructure failed: missing block: B:107:0x02a4, code lost:
    
        if ("java.util.Collections$EmptyMap".equals(r5) == false) goto L159;
     */
    /* JADX WARN: Code restructure failed: missing block: B:108:0x02a6, code lost:
    
        r3 = java.util.Collections.emptyMap();
     */
    /* JADX WARN: Code restructure failed: missing block: B:109:0x02ab, code lost:
    
        r3 = r6.newInstance();
     */
    /* JADX WARN: Code restructure failed: missing block: B:110:0x02af, code lost:
    
        if (r12 != false) goto L162;
     */
    /* JADX WARN: Code restructure failed: missing block: B:111:0x02b1, code lost:
    
        r18.contex = r13;
     */
    /* JADX WARN: Code restructure failed: missing block: B:112:0x02b3, code lost:
    
        return r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:113:0x0291, code lost:
    
        r3 = r9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:114:0x02b4, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:116:0x02bc, code lost:
    
        throw new com.alibaba.fastjson.JSONException("create instance error", r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:117:0x02bd, code lost:
    
        r18.resolveStatus = 2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:118:0x02c2, code lost:
    
        if (r18.contex == null) goto L171;
     */
    /* JADX WARN: Code restructure failed: missing block: B:120:0x02c6, code lost:
    
        if ((r20 instanceof java.lang.Integer) != false) goto L171;
     */
    /* JADX WARN: Code restructure failed: missing block: B:121:0x02c8, code lost:
    
        popContext();
     */
    /* JADX WARN: Code restructure failed: missing block: B:123:0x02cf, code lost:
    
        if (r19.size() <= 0) goto L177;
     */
    /* JADX WARN: Code restructure failed: missing block: B:124:0x02d1, code lost:
    
        r0 = com.alibaba.fastjson.util.TypeUtils.cast((java.lang.Object) r19, (java.lang.Class<java.lang.Object>) r6, r18.config);
        parseObject(r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:125:0x02da, code lost:
    
        if (r12 != false) goto L176;
     */
    /* JADX WARN: Code restructure failed: missing block: B:126:0x02dc, code lost:
    
        r18.contex = r13;
     */
    /* JADX WARN: Code restructure failed: missing block: B:127:0x02de, code lost:
    
        return r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:128:0x02df, code lost:
    
        r0 = r18.config.getDeserializer(r6);
        r2 = r0.deserialze(r18, r6, r20);
     */
    /* JADX WARN: Code restructure failed: missing block: B:129:0x02eb, code lost:
    
        if ((r0 instanceof com.alibaba.fastjson.parser.MapDeserializer) == false) goto L180;
     */
    /* JADX WARN: Code restructure failed: missing block: B:130:0x02ed, code lost:
    
        r18.resolveStatus = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:131:0x02f0, code lost:
    
        if (r12 != false) goto L182;
     */
    /* JADX WARN: Code restructure failed: missing block: B:132:0x02f2, code lost:
    
        r18.contex = r13;
     */
    /* JADX WARN: Code restructure failed: missing block: B:133:0x02f4, code lost:
    
        return r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:81:0x0249, code lost:
    
        r3.nextToken(16);
     */
    /* JADX WARN: Code restructure failed: missing block: B:82:0x0250, code lost:
    
        if (r3.token != 13) goto L166;
     */
    /* JADX WARN: Code restructure failed: missing block: B:83:0x0252, code lost:
    
        r3.nextToken(16);
     */
    /* JADX WARN: Code restructure failed: missing block: B:85:0x0255, code lost:
    
        r2 = r18.config.getDeserializer(r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:86:0x025d, code lost:
    
        if ((r2 instanceof com.alibaba.fastjson.parser.JavaBeanDeserializer) == false) goto L151;
     */
    /* JADX WARN: Code restructure failed: missing block: B:87:0x025f, code lost:
    
        r2 = (com.alibaba.fastjson.parser.JavaBeanDeserializer) r2;
        r3 = r2.createInstance((com.alibaba.fastjson.parser.DefaultJSONParser) r18, r6);
        r0 = r19.entrySet().iterator();
     */
    /* JADX WARN: Code restructure failed: missing block: B:89:0x0271, code lost:
    
        if (r0.hasNext() == false) goto L449;
     */
    /* JADX WARN: Code restructure failed: missing block: B:90:0x0273, code lost:
    
        r4 = (java.util.Map.Entry) r0.next();
        r7 = r4.getKey();
     */
    /* JADX WARN: Code restructure failed: missing block: B:91:0x027f, code lost:
    
        if ((r7 instanceof java.lang.String) == false) goto L453;
     */
    /* JADX WARN: Code restructure failed: missing block: B:93:0x0281, code lost:
    
        r7 = r2.getFieldDeserializer((java.lang.String) r7);
     */
    /* JADX WARN: Code restructure failed: missing block: B:94:0x0287, code lost:
    
        if (r7 == null) goto L454;
     */
    /* JADX WARN: Code restructure failed: missing block: B:96:0x0289, code lost:
    
        r7.setValue(r3, r4.getValue());
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:160:0x0381 A[Catch: all -> 0x06d5, TRY_LEAVE, TryCatch #1 {all -> 0x06d5, blocks: (B:19:0x0064, B:22:0x006e, B:26:0x0077, B:30:0x008a, B:32:0x0094, B:36:0x009c, B:37:0x00ba, B:41:0x01c5, B:44:0x01d8, B:371:0x01f4, B:59:0x01fc, B:61:0x0203, B:63:0x020b, B:64:0x0214, B:66:0x021a, B:70:0x0227, B:75:0x022f, B:77:0x023d, B:81:0x0249, B:83:0x0252, B:85:0x0255, B:87:0x025f, B:88:0x026d, B:90:0x0273, B:93:0x0281, B:96:0x0289, B:105:0x0298, B:106:0x029e, B:108:0x02a6, B:109:0x02ab, B:115:0x02b5, B:116:0x02bc, B:117:0x02bd, B:119:0x02c4, B:121:0x02c8, B:122:0x02cb, B:124:0x02d1, B:128:0x02df, B:130:0x02ed, B:142:0x02fc, B:145:0x0304, B:147:0x030d, B:149:0x031c, B:151:0x0324, B:154:0x0329, B:156:0x032d, B:158:0x037d, B:160:0x0381, B:164:0x038b, B:165:0x03a3, B:166:0x0332, B:168:0x033a, B:170:0x033e, B:171:0x0341, B:172:0x034d, B:175:0x0356, B:177:0x035a, B:179:0x035d, B:181:0x0361, B:182:0x0365, B:183:0x0371, B:184:0x03a4, B:185:0x03c0, B:188:0x03c5, B:193:0x03d6, B:195:0x03dc, B:197:0x03e8, B:198:0x03ee, B:200:0x03f3, B:202:0x058b, B:206:0x0595, B:209:0x059e, B:212:0x05b1, B:216:0x05ab, B:220:0x05bd, B:223:0x05d0, B:225:0x05d9, B:228:0x05ec, B:230:0x0634, B:234:0x05e6, B:237:0x05f7, B:240:0x060a, B:241:0x0604, B:244:0x0615, B:247:0x0628, B:248:0x0622, B:249:0x062f, B:250:0x05ca, B:251:0x063e, B:252:0x0656, B:253:0x03f7, B:258:0x0407, B:263:0x0416, B:266:0x042d, B:268:0x0436, B:272:0x0443, B:273:0x0446, B:275:0x0450, B:276:0x0457, B:285:0x045b, B:282:0x046f, B:283:0x0487, B:289:0x0454, B:291:0x0427, B:294:0x048c, B:297:0x049f, B:299:0x04b0, B:302:0x04c4, B:303:0x04ca, B:306:0x04d0, B:307:0x04da, B:309:0x04e2, B:311:0x04f5, B:314:0x04fd, B:315:0x04ff, B:317:0x0504, B:319:0x050d, B:321:0x0516, B:322:0x0519, B:330:0x051f, B:332:0x0526, B:327:0x0533, B:328:0x054b, B:336:0x0511, B:341:0x04bb, B:342:0x0499, B:345:0x0552, B:347:0x055e, B:350:0x0571, B:352:0x057d, B:353:0x0657, B:355:0x0668, B:356:0x066c, B:365:0x0675, B:359:0x0681, B:362:0x068a, B:363:0x06a2, B:378:0x01d2, B:379:0x01fa, B:440:0x00c2, B:443:0x00d3, B:447:0x00cd, B:384:0x00e6, B:386:0x00f2, B:387:0x00f5, B:391:0x00fa, B:392:0x0110, B:400:0x0123, B:402:0x0129, B:404:0x012e, B:406:0x013b, B:408:0x0140, B:412:0x0146, B:413:0x0160, B:414:0x0133, B:416:0x0161, B:417:0x017b, B:425:0x0185, B:428:0x0194, B:430:0x019a, B:431:0x01b8, B:432:0x01b9, B:434:0x06a3, B:435:0x06bb, B:437:0x06bc, B:438:0x06d4), top: B:18:0x0064, inners: #0, #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:164:0x038b A[Catch: all -> 0x06d5, TRY_ENTER, TryCatch #1 {all -> 0x06d5, blocks: (B:19:0x0064, B:22:0x006e, B:26:0x0077, B:30:0x008a, B:32:0x0094, B:36:0x009c, B:37:0x00ba, B:41:0x01c5, B:44:0x01d8, B:371:0x01f4, B:59:0x01fc, B:61:0x0203, B:63:0x020b, B:64:0x0214, B:66:0x021a, B:70:0x0227, B:75:0x022f, B:77:0x023d, B:81:0x0249, B:83:0x0252, B:85:0x0255, B:87:0x025f, B:88:0x026d, B:90:0x0273, B:93:0x0281, B:96:0x0289, B:105:0x0298, B:106:0x029e, B:108:0x02a6, B:109:0x02ab, B:115:0x02b5, B:116:0x02bc, B:117:0x02bd, B:119:0x02c4, B:121:0x02c8, B:122:0x02cb, B:124:0x02d1, B:128:0x02df, B:130:0x02ed, B:142:0x02fc, B:145:0x0304, B:147:0x030d, B:149:0x031c, B:151:0x0324, B:154:0x0329, B:156:0x032d, B:158:0x037d, B:160:0x0381, B:164:0x038b, B:165:0x03a3, B:166:0x0332, B:168:0x033a, B:170:0x033e, B:171:0x0341, B:172:0x034d, B:175:0x0356, B:177:0x035a, B:179:0x035d, B:181:0x0361, B:182:0x0365, B:183:0x0371, B:184:0x03a4, B:185:0x03c0, B:188:0x03c5, B:193:0x03d6, B:195:0x03dc, B:197:0x03e8, B:198:0x03ee, B:200:0x03f3, B:202:0x058b, B:206:0x0595, B:209:0x059e, B:212:0x05b1, B:216:0x05ab, B:220:0x05bd, B:223:0x05d0, B:225:0x05d9, B:228:0x05ec, B:230:0x0634, B:234:0x05e6, B:237:0x05f7, B:240:0x060a, B:241:0x0604, B:244:0x0615, B:247:0x0628, B:248:0x0622, B:249:0x062f, B:250:0x05ca, B:251:0x063e, B:252:0x0656, B:253:0x03f7, B:258:0x0407, B:263:0x0416, B:266:0x042d, B:268:0x0436, B:272:0x0443, B:273:0x0446, B:275:0x0450, B:276:0x0457, B:285:0x045b, B:282:0x046f, B:283:0x0487, B:289:0x0454, B:291:0x0427, B:294:0x048c, B:297:0x049f, B:299:0x04b0, B:302:0x04c4, B:303:0x04ca, B:306:0x04d0, B:307:0x04da, B:309:0x04e2, B:311:0x04f5, B:314:0x04fd, B:315:0x04ff, B:317:0x0504, B:319:0x050d, B:321:0x0516, B:322:0x0519, B:330:0x051f, B:332:0x0526, B:327:0x0533, B:328:0x054b, B:336:0x0511, B:341:0x04bb, B:342:0x0499, B:345:0x0552, B:347:0x055e, B:350:0x0571, B:352:0x057d, B:353:0x0657, B:355:0x0668, B:356:0x066c, B:365:0x0675, B:359:0x0681, B:362:0x068a, B:363:0x06a2, B:378:0x01d2, B:379:0x01fa, B:440:0x00c2, B:443:0x00d3, B:447:0x00cd, B:384:0x00e6, B:386:0x00f2, B:387:0x00f5, B:391:0x00fa, B:392:0x0110, B:400:0x0123, B:402:0x0129, B:404:0x012e, B:406:0x013b, B:408:0x0140, B:412:0x0146, B:413:0x0160, B:414:0x0133, B:416:0x0161, B:417:0x017b, B:425:0x0185, B:428:0x0194, B:430:0x019a, B:431:0x01b8, B:432:0x01b9, B:434:0x06a3, B:435:0x06bb, B:437:0x06bc, B:438:0x06d4), top: B:18:0x0064, inners: #0, #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:193:0x03d6 A[Catch: all -> 0x06d5, TryCatch #1 {all -> 0x06d5, blocks: (B:19:0x0064, B:22:0x006e, B:26:0x0077, B:30:0x008a, B:32:0x0094, B:36:0x009c, B:37:0x00ba, B:41:0x01c5, B:44:0x01d8, B:371:0x01f4, B:59:0x01fc, B:61:0x0203, B:63:0x020b, B:64:0x0214, B:66:0x021a, B:70:0x0227, B:75:0x022f, B:77:0x023d, B:81:0x0249, B:83:0x0252, B:85:0x0255, B:87:0x025f, B:88:0x026d, B:90:0x0273, B:93:0x0281, B:96:0x0289, B:105:0x0298, B:106:0x029e, B:108:0x02a6, B:109:0x02ab, B:115:0x02b5, B:116:0x02bc, B:117:0x02bd, B:119:0x02c4, B:121:0x02c8, B:122:0x02cb, B:124:0x02d1, B:128:0x02df, B:130:0x02ed, B:142:0x02fc, B:145:0x0304, B:147:0x030d, B:149:0x031c, B:151:0x0324, B:154:0x0329, B:156:0x032d, B:158:0x037d, B:160:0x0381, B:164:0x038b, B:165:0x03a3, B:166:0x0332, B:168:0x033a, B:170:0x033e, B:171:0x0341, B:172:0x034d, B:175:0x0356, B:177:0x035a, B:179:0x035d, B:181:0x0361, B:182:0x0365, B:183:0x0371, B:184:0x03a4, B:185:0x03c0, B:188:0x03c5, B:193:0x03d6, B:195:0x03dc, B:197:0x03e8, B:198:0x03ee, B:200:0x03f3, B:202:0x058b, B:206:0x0595, B:209:0x059e, B:212:0x05b1, B:216:0x05ab, B:220:0x05bd, B:223:0x05d0, B:225:0x05d9, B:228:0x05ec, B:230:0x0634, B:234:0x05e6, B:237:0x05f7, B:240:0x060a, B:241:0x0604, B:244:0x0615, B:247:0x0628, B:248:0x0622, B:249:0x062f, B:250:0x05ca, B:251:0x063e, B:252:0x0656, B:253:0x03f7, B:258:0x0407, B:263:0x0416, B:266:0x042d, B:268:0x0436, B:272:0x0443, B:273:0x0446, B:275:0x0450, B:276:0x0457, B:285:0x045b, B:282:0x046f, B:283:0x0487, B:289:0x0454, B:291:0x0427, B:294:0x048c, B:297:0x049f, B:299:0x04b0, B:302:0x04c4, B:303:0x04ca, B:306:0x04d0, B:307:0x04da, B:309:0x04e2, B:311:0x04f5, B:314:0x04fd, B:315:0x04ff, B:317:0x0504, B:319:0x050d, B:321:0x0516, B:322:0x0519, B:330:0x051f, B:332:0x0526, B:327:0x0533, B:328:0x054b, B:336:0x0511, B:341:0x04bb, B:342:0x0499, B:345:0x0552, B:347:0x055e, B:350:0x0571, B:352:0x057d, B:353:0x0657, B:355:0x0668, B:356:0x066c, B:365:0x0675, B:359:0x0681, B:362:0x068a, B:363:0x06a2, B:378:0x01d2, B:379:0x01fa, B:440:0x00c2, B:443:0x00d3, B:447:0x00cd, B:384:0x00e6, B:386:0x00f2, B:387:0x00f5, B:391:0x00fa, B:392:0x0110, B:400:0x0123, B:402:0x0129, B:404:0x012e, B:406:0x013b, B:408:0x0140, B:412:0x0146, B:413:0x0160, B:414:0x0133, B:416:0x0161, B:417:0x017b, B:425:0x0185, B:428:0x0194, B:430:0x019a, B:431:0x01b8, B:432:0x01b9, B:434:0x06a3, B:435:0x06bb, B:437:0x06bc, B:438:0x06d4), top: B:18:0x0064, inners: #0, #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:204:0x0591  */
    /* JADX WARN: Removed duplicated region for block: B:209:0x059e A[Catch: all -> 0x06d5, TryCatch #1 {all -> 0x06d5, blocks: (B:19:0x0064, B:22:0x006e, B:26:0x0077, B:30:0x008a, B:32:0x0094, B:36:0x009c, B:37:0x00ba, B:41:0x01c5, B:44:0x01d8, B:371:0x01f4, B:59:0x01fc, B:61:0x0203, B:63:0x020b, B:64:0x0214, B:66:0x021a, B:70:0x0227, B:75:0x022f, B:77:0x023d, B:81:0x0249, B:83:0x0252, B:85:0x0255, B:87:0x025f, B:88:0x026d, B:90:0x0273, B:93:0x0281, B:96:0x0289, B:105:0x0298, B:106:0x029e, B:108:0x02a6, B:109:0x02ab, B:115:0x02b5, B:116:0x02bc, B:117:0x02bd, B:119:0x02c4, B:121:0x02c8, B:122:0x02cb, B:124:0x02d1, B:128:0x02df, B:130:0x02ed, B:142:0x02fc, B:145:0x0304, B:147:0x030d, B:149:0x031c, B:151:0x0324, B:154:0x0329, B:156:0x032d, B:158:0x037d, B:160:0x0381, B:164:0x038b, B:165:0x03a3, B:166:0x0332, B:168:0x033a, B:170:0x033e, B:171:0x0341, B:172:0x034d, B:175:0x0356, B:177:0x035a, B:179:0x035d, B:181:0x0361, B:182:0x0365, B:183:0x0371, B:184:0x03a4, B:185:0x03c0, B:188:0x03c5, B:193:0x03d6, B:195:0x03dc, B:197:0x03e8, B:198:0x03ee, B:200:0x03f3, B:202:0x058b, B:206:0x0595, B:209:0x059e, B:212:0x05b1, B:216:0x05ab, B:220:0x05bd, B:223:0x05d0, B:225:0x05d9, B:228:0x05ec, B:230:0x0634, B:234:0x05e6, B:237:0x05f7, B:240:0x060a, B:241:0x0604, B:244:0x0615, B:247:0x0628, B:248:0x0622, B:249:0x062f, B:250:0x05ca, B:251:0x063e, B:252:0x0656, B:253:0x03f7, B:258:0x0407, B:263:0x0416, B:266:0x042d, B:268:0x0436, B:272:0x0443, B:273:0x0446, B:275:0x0450, B:276:0x0457, B:285:0x045b, B:282:0x046f, B:283:0x0487, B:289:0x0454, B:291:0x0427, B:294:0x048c, B:297:0x049f, B:299:0x04b0, B:302:0x04c4, B:303:0x04ca, B:306:0x04d0, B:307:0x04da, B:309:0x04e2, B:311:0x04f5, B:314:0x04fd, B:315:0x04ff, B:317:0x0504, B:319:0x050d, B:321:0x0516, B:322:0x0519, B:330:0x051f, B:332:0x0526, B:327:0x0533, B:328:0x054b, B:336:0x0511, B:341:0x04bb, B:342:0x0499, B:345:0x0552, B:347:0x055e, B:350:0x0571, B:352:0x057d, B:353:0x0657, B:355:0x0668, B:356:0x066c, B:365:0x0675, B:359:0x0681, B:362:0x068a, B:363:0x06a2, B:378:0x01d2, B:379:0x01fa, B:440:0x00c2, B:443:0x00d3, B:447:0x00cd, B:384:0x00e6, B:386:0x00f2, B:387:0x00f5, B:391:0x00fa, B:392:0x0110, B:400:0x0123, B:402:0x0129, B:404:0x012e, B:406:0x013b, B:408:0x0140, B:412:0x0146, B:413:0x0160, B:414:0x0133, B:416:0x0161, B:417:0x017b, B:425:0x0185, B:428:0x0194, B:430:0x019a, B:431:0x01b8, B:432:0x01b9, B:434:0x06a3, B:435:0x06bb, B:437:0x06bc, B:438:0x06d4), top: B:18:0x0064, inners: #0, #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:217:0x05b9 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:254:0x03fb  */
    /* JADX WARN: Removed duplicated region for block: B:311:0x04f5 A[Catch: all -> 0x06d5, TryCatch #1 {all -> 0x06d5, blocks: (B:19:0x0064, B:22:0x006e, B:26:0x0077, B:30:0x008a, B:32:0x0094, B:36:0x009c, B:37:0x00ba, B:41:0x01c5, B:44:0x01d8, B:371:0x01f4, B:59:0x01fc, B:61:0x0203, B:63:0x020b, B:64:0x0214, B:66:0x021a, B:70:0x0227, B:75:0x022f, B:77:0x023d, B:81:0x0249, B:83:0x0252, B:85:0x0255, B:87:0x025f, B:88:0x026d, B:90:0x0273, B:93:0x0281, B:96:0x0289, B:105:0x0298, B:106:0x029e, B:108:0x02a6, B:109:0x02ab, B:115:0x02b5, B:116:0x02bc, B:117:0x02bd, B:119:0x02c4, B:121:0x02c8, B:122:0x02cb, B:124:0x02d1, B:128:0x02df, B:130:0x02ed, B:142:0x02fc, B:145:0x0304, B:147:0x030d, B:149:0x031c, B:151:0x0324, B:154:0x0329, B:156:0x032d, B:158:0x037d, B:160:0x0381, B:164:0x038b, B:165:0x03a3, B:166:0x0332, B:168:0x033a, B:170:0x033e, B:171:0x0341, B:172:0x034d, B:175:0x0356, B:177:0x035a, B:179:0x035d, B:181:0x0361, B:182:0x0365, B:183:0x0371, B:184:0x03a4, B:185:0x03c0, B:188:0x03c5, B:193:0x03d6, B:195:0x03dc, B:197:0x03e8, B:198:0x03ee, B:200:0x03f3, B:202:0x058b, B:206:0x0595, B:209:0x059e, B:212:0x05b1, B:216:0x05ab, B:220:0x05bd, B:223:0x05d0, B:225:0x05d9, B:228:0x05ec, B:230:0x0634, B:234:0x05e6, B:237:0x05f7, B:240:0x060a, B:241:0x0604, B:244:0x0615, B:247:0x0628, B:248:0x0622, B:249:0x062f, B:250:0x05ca, B:251:0x063e, B:252:0x0656, B:253:0x03f7, B:258:0x0407, B:263:0x0416, B:266:0x042d, B:268:0x0436, B:272:0x0443, B:273:0x0446, B:275:0x0450, B:276:0x0457, B:285:0x045b, B:282:0x046f, B:283:0x0487, B:289:0x0454, B:291:0x0427, B:294:0x048c, B:297:0x049f, B:299:0x04b0, B:302:0x04c4, B:303:0x04ca, B:306:0x04d0, B:307:0x04da, B:309:0x04e2, B:311:0x04f5, B:314:0x04fd, B:315:0x04ff, B:317:0x0504, B:319:0x050d, B:321:0x0516, B:322:0x0519, B:330:0x051f, B:332:0x0526, B:327:0x0533, B:328:0x054b, B:336:0x0511, B:341:0x04bb, B:342:0x0499, B:345:0x0552, B:347:0x055e, B:350:0x0571, B:352:0x057d, B:353:0x0657, B:355:0x0668, B:356:0x066c, B:365:0x0675, B:359:0x0681, B:362:0x068a, B:363:0x06a2, B:378:0x01d2, B:379:0x01fa, B:440:0x00c2, B:443:0x00d3, B:447:0x00cd, B:384:0x00e6, B:386:0x00f2, B:387:0x00f5, B:391:0x00fa, B:392:0x0110, B:400:0x0123, B:402:0x0129, B:404:0x012e, B:406:0x013b, B:408:0x0140, B:412:0x0146, B:413:0x0160, B:414:0x0133, B:416:0x0161, B:417:0x017b, B:425:0x0185, B:428:0x0194, B:430:0x019a, B:431:0x01b8, B:432:0x01b9, B:434:0x06a3, B:435:0x06bb, B:437:0x06bc, B:438:0x06d4), top: B:18:0x0064, inners: #0, #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:317:0x0504 A[Catch: all -> 0x06d5, TryCatch #1 {all -> 0x06d5, blocks: (B:19:0x0064, B:22:0x006e, B:26:0x0077, B:30:0x008a, B:32:0x0094, B:36:0x009c, B:37:0x00ba, B:41:0x01c5, B:44:0x01d8, B:371:0x01f4, B:59:0x01fc, B:61:0x0203, B:63:0x020b, B:64:0x0214, B:66:0x021a, B:70:0x0227, B:75:0x022f, B:77:0x023d, B:81:0x0249, B:83:0x0252, B:85:0x0255, B:87:0x025f, B:88:0x026d, B:90:0x0273, B:93:0x0281, B:96:0x0289, B:105:0x0298, B:106:0x029e, B:108:0x02a6, B:109:0x02ab, B:115:0x02b5, B:116:0x02bc, B:117:0x02bd, B:119:0x02c4, B:121:0x02c8, B:122:0x02cb, B:124:0x02d1, B:128:0x02df, B:130:0x02ed, B:142:0x02fc, B:145:0x0304, B:147:0x030d, B:149:0x031c, B:151:0x0324, B:154:0x0329, B:156:0x032d, B:158:0x037d, B:160:0x0381, B:164:0x038b, B:165:0x03a3, B:166:0x0332, B:168:0x033a, B:170:0x033e, B:171:0x0341, B:172:0x034d, B:175:0x0356, B:177:0x035a, B:179:0x035d, B:181:0x0361, B:182:0x0365, B:183:0x0371, B:184:0x03a4, B:185:0x03c0, B:188:0x03c5, B:193:0x03d6, B:195:0x03dc, B:197:0x03e8, B:198:0x03ee, B:200:0x03f3, B:202:0x058b, B:206:0x0595, B:209:0x059e, B:212:0x05b1, B:216:0x05ab, B:220:0x05bd, B:223:0x05d0, B:225:0x05d9, B:228:0x05ec, B:230:0x0634, B:234:0x05e6, B:237:0x05f7, B:240:0x060a, B:241:0x0604, B:244:0x0615, B:247:0x0628, B:248:0x0622, B:249:0x062f, B:250:0x05ca, B:251:0x063e, B:252:0x0656, B:253:0x03f7, B:258:0x0407, B:263:0x0416, B:266:0x042d, B:268:0x0436, B:272:0x0443, B:273:0x0446, B:275:0x0450, B:276:0x0457, B:285:0x045b, B:282:0x046f, B:283:0x0487, B:289:0x0454, B:291:0x0427, B:294:0x048c, B:297:0x049f, B:299:0x04b0, B:302:0x04c4, B:303:0x04ca, B:306:0x04d0, B:307:0x04da, B:309:0x04e2, B:311:0x04f5, B:314:0x04fd, B:315:0x04ff, B:317:0x0504, B:319:0x050d, B:321:0x0516, B:322:0x0519, B:330:0x051f, B:332:0x0526, B:327:0x0533, B:328:0x054b, B:336:0x0511, B:341:0x04bb, B:342:0x0499, B:345:0x0552, B:347:0x055e, B:350:0x0571, B:352:0x057d, B:353:0x0657, B:355:0x0668, B:356:0x066c, B:365:0x0675, B:359:0x0681, B:362:0x068a, B:363:0x06a2, B:378:0x01d2, B:379:0x01fa, B:440:0x00c2, B:443:0x00d3, B:447:0x00cd, B:384:0x00e6, B:386:0x00f2, B:387:0x00f5, B:391:0x00fa, B:392:0x0110, B:400:0x0123, B:402:0x0129, B:404:0x012e, B:406:0x013b, B:408:0x0140, B:412:0x0146, B:413:0x0160, B:414:0x0133, B:416:0x0161, B:417:0x017b, B:425:0x0185, B:428:0x0194, B:430:0x019a, B:431:0x01b8, B:432:0x01b9, B:434:0x06a3, B:435:0x06bb, B:437:0x06bc, B:438:0x06d4), top: B:18:0x0064, inners: #0, #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:319:0x050d A[Catch: all -> 0x06d5, TryCatch #1 {all -> 0x06d5, blocks: (B:19:0x0064, B:22:0x006e, B:26:0x0077, B:30:0x008a, B:32:0x0094, B:36:0x009c, B:37:0x00ba, B:41:0x01c5, B:44:0x01d8, B:371:0x01f4, B:59:0x01fc, B:61:0x0203, B:63:0x020b, B:64:0x0214, B:66:0x021a, B:70:0x0227, B:75:0x022f, B:77:0x023d, B:81:0x0249, B:83:0x0252, B:85:0x0255, B:87:0x025f, B:88:0x026d, B:90:0x0273, B:93:0x0281, B:96:0x0289, B:105:0x0298, B:106:0x029e, B:108:0x02a6, B:109:0x02ab, B:115:0x02b5, B:116:0x02bc, B:117:0x02bd, B:119:0x02c4, B:121:0x02c8, B:122:0x02cb, B:124:0x02d1, B:128:0x02df, B:130:0x02ed, B:142:0x02fc, B:145:0x0304, B:147:0x030d, B:149:0x031c, B:151:0x0324, B:154:0x0329, B:156:0x032d, B:158:0x037d, B:160:0x0381, B:164:0x038b, B:165:0x03a3, B:166:0x0332, B:168:0x033a, B:170:0x033e, B:171:0x0341, B:172:0x034d, B:175:0x0356, B:177:0x035a, B:179:0x035d, B:181:0x0361, B:182:0x0365, B:183:0x0371, B:184:0x03a4, B:185:0x03c0, B:188:0x03c5, B:193:0x03d6, B:195:0x03dc, B:197:0x03e8, B:198:0x03ee, B:200:0x03f3, B:202:0x058b, B:206:0x0595, B:209:0x059e, B:212:0x05b1, B:216:0x05ab, B:220:0x05bd, B:223:0x05d0, B:225:0x05d9, B:228:0x05ec, B:230:0x0634, B:234:0x05e6, B:237:0x05f7, B:240:0x060a, B:241:0x0604, B:244:0x0615, B:247:0x0628, B:248:0x0622, B:249:0x062f, B:250:0x05ca, B:251:0x063e, B:252:0x0656, B:253:0x03f7, B:258:0x0407, B:263:0x0416, B:266:0x042d, B:268:0x0436, B:272:0x0443, B:273:0x0446, B:275:0x0450, B:276:0x0457, B:285:0x045b, B:282:0x046f, B:283:0x0487, B:289:0x0454, B:291:0x0427, B:294:0x048c, B:297:0x049f, B:299:0x04b0, B:302:0x04c4, B:303:0x04ca, B:306:0x04d0, B:307:0x04da, B:309:0x04e2, B:311:0x04f5, B:314:0x04fd, B:315:0x04ff, B:317:0x0504, B:319:0x050d, B:321:0x0516, B:322:0x0519, B:330:0x051f, B:332:0x0526, B:327:0x0533, B:328:0x054b, B:336:0x0511, B:341:0x04bb, B:342:0x0499, B:345:0x0552, B:347:0x055e, B:350:0x0571, B:352:0x057d, B:353:0x0657, B:355:0x0668, B:356:0x066c, B:365:0x0675, B:359:0x0681, B:362:0x068a, B:363:0x06a2, B:378:0x01d2, B:379:0x01fa, B:440:0x00c2, B:443:0x00d3, B:447:0x00cd, B:384:0x00e6, B:386:0x00f2, B:387:0x00f5, B:391:0x00fa, B:392:0x0110, B:400:0x0123, B:402:0x0129, B:404:0x012e, B:406:0x013b, B:408:0x0140, B:412:0x0146, B:413:0x0160, B:414:0x0133, B:416:0x0161, B:417:0x017b, B:425:0x0185, B:428:0x0194, B:430:0x019a, B:431:0x01b8, B:432:0x01b9, B:434:0x06a3, B:435:0x06bb, B:437:0x06bc, B:438:0x06d4), top: B:18:0x0064, inners: #0, #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:321:0x0516 A[Catch: all -> 0x06d5, TryCatch #1 {all -> 0x06d5, blocks: (B:19:0x0064, B:22:0x006e, B:26:0x0077, B:30:0x008a, B:32:0x0094, B:36:0x009c, B:37:0x00ba, B:41:0x01c5, B:44:0x01d8, B:371:0x01f4, B:59:0x01fc, B:61:0x0203, B:63:0x020b, B:64:0x0214, B:66:0x021a, B:70:0x0227, B:75:0x022f, B:77:0x023d, B:81:0x0249, B:83:0x0252, B:85:0x0255, B:87:0x025f, B:88:0x026d, B:90:0x0273, B:93:0x0281, B:96:0x0289, B:105:0x0298, B:106:0x029e, B:108:0x02a6, B:109:0x02ab, B:115:0x02b5, B:116:0x02bc, B:117:0x02bd, B:119:0x02c4, B:121:0x02c8, B:122:0x02cb, B:124:0x02d1, B:128:0x02df, B:130:0x02ed, B:142:0x02fc, B:145:0x0304, B:147:0x030d, B:149:0x031c, B:151:0x0324, B:154:0x0329, B:156:0x032d, B:158:0x037d, B:160:0x0381, B:164:0x038b, B:165:0x03a3, B:166:0x0332, B:168:0x033a, B:170:0x033e, B:171:0x0341, B:172:0x034d, B:175:0x0356, B:177:0x035a, B:179:0x035d, B:181:0x0361, B:182:0x0365, B:183:0x0371, B:184:0x03a4, B:185:0x03c0, B:188:0x03c5, B:193:0x03d6, B:195:0x03dc, B:197:0x03e8, B:198:0x03ee, B:200:0x03f3, B:202:0x058b, B:206:0x0595, B:209:0x059e, B:212:0x05b1, B:216:0x05ab, B:220:0x05bd, B:223:0x05d0, B:225:0x05d9, B:228:0x05ec, B:230:0x0634, B:234:0x05e6, B:237:0x05f7, B:240:0x060a, B:241:0x0604, B:244:0x0615, B:247:0x0628, B:248:0x0622, B:249:0x062f, B:250:0x05ca, B:251:0x063e, B:252:0x0656, B:253:0x03f7, B:258:0x0407, B:263:0x0416, B:266:0x042d, B:268:0x0436, B:272:0x0443, B:273:0x0446, B:275:0x0450, B:276:0x0457, B:285:0x045b, B:282:0x046f, B:283:0x0487, B:289:0x0454, B:291:0x0427, B:294:0x048c, B:297:0x049f, B:299:0x04b0, B:302:0x04c4, B:303:0x04ca, B:306:0x04d0, B:307:0x04da, B:309:0x04e2, B:311:0x04f5, B:314:0x04fd, B:315:0x04ff, B:317:0x0504, B:319:0x050d, B:321:0x0516, B:322:0x0519, B:330:0x051f, B:332:0x0526, B:327:0x0533, B:328:0x054b, B:336:0x0511, B:341:0x04bb, B:342:0x0499, B:345:0x0552, B:347:0x055e, B:350:0x0571, B:352:0x057d, B:353:0x0657, B:355:0x0668, B:356:0x066c, B:365:0x0675, B:359:0x0681, B:362:0x068a, B:363:0x06a2, B:378:0x01d2, B:379:0x01fa, B:440:0x00c2, B:443:0x00d3, B:447:0x00cd, B:384:0x00e6, B:386:0x00f2, B:387:0x00f5, B:391:0x00fa, B:392:0x0110, B:400:0x0123, B:402:0x0129, B:404:0x012e, B:406:0x013b, B:408:0x0140, B:412:0x0146, B:413:0x0160, B:414:0x0133, B:416:0x0161, B:417:0x017b, B:425:0x0185, B:428:0x0194, B:430:0x019a, B:431:0x01b8, B:432:0x01b9, B:434:0x06a3, B:435:0x06bb, B:437:0x06bc, B:438:0x06d4), top: B:18:0x0064, inners: #0, #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:324:0x052d  */
    /* JADX WARN: Removed duplicated region for block: B:329:0x051f A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:336:0x0511 A[Catch: all -> 0x06d5, TryCatch #1 {all -> 0x06d5, blocks: (B:19:0x0064, B:22:0x006e, B:26:0x0077, B:30:0x008a, B:32:0x0094, B:36:0x009c, B:37:0x00ba, B:41:0x01c5, B:44:0x01d8, B:371:0x01f4, B:59:0x01fc, B:61:0x0203, B:63:0x020b, B:64:0x0214, B:66:0x021a, B:70:0x0227, B:75:0x022f, B:77:0x023d, B:81:0x0249, B:83:0x0252, B:85:0x0255, B:87:0x025f, B:88:0x026d, B:90:0x0273, B:93:0x0281, B:96:0x0289, B:105:0x0298, B:106:0x029e, B:108:0x02a6, B:109:0x02ab, B:115:0x02b5, B:116:0x02bc, B:117:0x02bd, B:119:0x02c4, B:121:0x02c8, B:122:0x02cb, B:124:0x02d1, B:128:0x02df, B:130:0x02ed, B:142:0x02fc, B:145:0x0304, B:147:0x030d, B:149:0x031c, B:151:0x0324, B:154:0x0329, B:156:0x032d, B:158:0x037d, B:160:0x0381, B:164:0x038b, B:165:0x03a3, B:166:0x0332, B:168:0x033a, B:170:0x033e, B:171:0x0341, B:172:0x034d, B:175:0x0356, B:177:0x035a, B:179:0x035d, B:181:0x0361, B:182:0x0365, B:183:0x0371, B:184:0x03a4, B:185:0x03c0, B:188:0x03c5, B:193:0x03d6, B:195:0x03dc, B:197:0x03e8, B:198:0x03ee, B:200:0x03f3, B:202:0x058b, B:206:0x0595, B:209:0x059e, B:212:0x05b1, B:216:0x05ab, B:220:0x05bd, B:223:0x05d0, B:225:0x05d9, B:228:0x05ec, B:230:0x0634, B:234:0x05e6, B:237:0x05f7, B:240:0x060a, B:241:0x0604, B:244:0x0615, B:247:0x0628, B:248:0x0622, B:249:0x062f, B:250:0x05ca, B:251:0x063e, B:252:0x0656, B:253:0x03f7, B:258:0x0407, B:263:0x0416, B:266:0x042d, B:268:0x0436, B:272:0x0443, B:273:0x0446, B:275:0x0450, B:276:0x0457, B:285:0x045b, B:282:0x046f, B:283:0x0487, B:289:0x0454, B:291:0x0427, B:294:0x048c, B:297:0x049f, B:299:0x04b0, B:302:0x04c4, B:303:0x04ca, B:306:0x04d0, B:307:0x04da, B:309:0x04e2, B:311:0x04f5, B:314:0x04fd, B:315:0x04ff, B:317:0x0504, B:319:0x050d, B:321:0x0516, B:322:0x0519, B:330:0x051f, B:332:0x0526, B:327:0x0533, B:328:0x054b, B:336:0x0511, B:341:0x04bb, B:342:0x0499, B:345:0x0552, B:347:0x055e, B:350:0x0571, B:352:0x057d, B:353:0x0657, B:355:0x0668, B:356:0x066c, B:365:0x0675, B:359:0x0681, B:362:0x068a, B:363:0x06a2, B:378:0x01d2, B:379:0x01fa, B:440:0x00c2, B:443:0x00d3, B:447:0x00cd, B:384:0x00e6, B:386:0x00f2, B:387:0x00f5, B:391:0x00fa, B:392:0x0110, B:400:0x0123, B:402:0x0129, B:404:0x012e, B:406:0x013b, B:408:0x0140, B:412:0x0146, B:413:0x0160, B:414:0x0133, B:416:0x0161, B:417:0x017b, B:425:0x0185, B:428:0x0194, B:430:0x019a, B:431:0x01b8, B:432:0x01b9, B:434:0x06a3, B:435:0x06bb, B:437:0x06bc, B:438:0x06d4), top: B:18:0x0064, inners: #0, #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:379:0x01fa A[Catch: all -> 0x06d5, TryCatch #1 {all -> 0x06d5, blocks: (B:19:0x0064, B:22:0x006e, B:26:0x0077, B:30:0x008a, B:32:0x0094, B:36:0x009c, B:37:0x00ba, B:41:0x01c5, B:44:0x01d8, B:371:0x01f4, B:59:0x01fc, B:61:0x0203, B:63:0x020b, B:64:0x0214, B:66:0x021a, B:70:0x0227, B:75:0x022f, B:77:0x023d, B:81:0x0249, B:83:0x0252, B:85:0x0255, B:87:0x025f, B:88:0x026d, B:90:0x0273, B:93:0x0281, B:96:0x0289, B:105:0x0298, B:106:0x029e, B:108:0x02a6, B:109:0x02ab, B:115:0x02b5, B:116:0x02bc, B:117:0x02bd, B:119:0x02c4, B:121:0x02c8, B:122:0x02cb, B:124:0x02d1, B:128:0x02df, B:130:0x02ed, B:142:0x02fc, B:145:0x0304, B:147:0x030d, B:149:0x031c, B:151:0x0324, B:154:0x0329, B:156:0x032d, B:158:0x037d, B:160:0x0381, B:164:0x038b, B:165:0x03a3, B:166:0x0332, B:168:0x033a, B:170:0x033e, B:171:0x0341, B:172:0x034d, B:175:0x0356, B:177:0x035a, B:179:0x035d, B:181:0x0361, B:182:0x0365, B:183:0x0371, B:184:0x03a4, B:185:0x03c0, B:188:0x03c5, B:193:0x03d6, B:195:0x03dc, B:197:0x03e8, B:198:0x03ee, B:200:0x03f3, B:202:0x058b, B:206:0x0595, B:209:0x059e, B:212:0x05b1, B:216:0x05ab, B:220:0x05bd, B:223:0x05d0, B:225:0x05d9, B:228:0x05ec, B:230:0x0634, B:234:0x05e6, B:237:0x05f7, B:240:0x060a, B:241:0x0604, B:244:0x0615, B:247:0x0628, B:248:0x0622, B:249:0x062f, B:250:0x05ca, B:251:0x063e, B:252:0x0656, B:253:0x03f7, B:258:0x0407, B:263:0x0416, B:266:0x042d, B:268:0x0436, B:272:0x0443, B:273:0x0446, B:275:0x0450, B:276:0x0457, B:285:0x045b, B:282:0x046f, B:283:0x0487, B:289:0x0454, B:291:0x0427, B:294:0x048c, B:297:0x049f, B:299:0x04b0, B:302:0x04c4, B:303:0x04ca, B:306:0x04d0, B:307:0x04da, B:309:0x04e2, B:311:0x04f5, B:314:0x04fd, B:315:0x04ff, B:317:0x0504, B:319:0x050d, B:321:0x0516, B:322:0x0519, B:330:0x051f, B:332:0x0526, B:327:0x0533, B:328:0x054b, B:336:0x0511, B:341:0x04bb, B:342:0x0499, B:345:0x0552, B:347:0x055e, B:350:0x0571, B:352:0x057d, B:353:0x0657, B:355:0x0668, B:356:0x066c, B:365:0x0675, B:359:0x0681, B:362:0x068a, B:363:0x06a2, B:378:0x01d2, B:379:0x01fa, B:440:0x00c2, B:443:0x00d3, B:447:0x00cd, B:384:0x00e6, B:386:0x00f2, B:387:0x00f5, B:391:0x00fa, B:392:0x0110, B:400:0x0123, B:402:0x0129, B:404:0x012e, B:406:0x013b, B:408:0x0140, B:412:0x0146, B:413:0x0160, B:414:0x0133, B:416:0x0161, B:417:0x017b, B:425:0x0185, B:428:0x0194, B:430:0x019a, B:431:0x01b8, B:432:0x01b9, B:434:0x06a3, B:435:0x06bb, B:437:0x06bc, B:438:0x06d4), top: B:18:0x0064, inners: #0, #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:41:0x01c5 A[Catch: all -> 0x06d5, TryCatch #1 {all -> 0x06d5, blocks: (B:19:0x0064, B:22:0x006e, B:26:0x0077, B:30:0x008a, B:32:0x0094, B:36:0x009c, B:37:0x00ba, B:41:0x01c5, B:44:0x01d8, B:371:0x01f4, B:59:0x01fc, B:61:0x0203, B:63:0x020b, B:64:0x0214, B:66:0x021a, B:70:0x0227, B:75:0x022f, B:77:0x023d, B:81:0x0249, B:83:0x0252, B:85:0x0255, B:87:0x025f, B:88:0x026d, B:90:0x0273, B:93:0x0281, B:96:0x0289, B:105:0x0298, B:106:0x029e, B:108:0x02a6, B:109:0x02ab, B:115:0x02b5, B:116:0x02bc, B:117:0x02bd, B:119:0x02c4, B:121:0x02c8, B:122:0x02cb, B:124:0x02d1, B:128:0x02df, B:130:0x02ed, B:142:0x02fc, B:145:0x0304, B:147:0x030d, B:149:0x031c, B:151:0x0324, B:154:0x0329, B:156:0x032d, B:158:0x037d, B:160:0x0381, B:164:0x038b, B:165:0x03a3, B:166:0x0332, B:168:0x033a, B:170:0x033e, B:171:0x0341, B:172:0x034d, B:175:0x0356, B:177:0x035a, B:179:0x035d, B:181:0x0361, B:182:0x0365, B:183:0x0371, B:184:0x03a4, B:185:0x03c0, B:188:0x03c5, B:193:0x03d6, B:195:0x03dc, B:197:0x03e8, B:198:0x03ee, B:200:0x03f3, B:202:0x058b, B:206:0x0595, B:209:0x059e, B:212:0x05b1, B:216:0x05ab, B:220:0x05bd, B:223:0x05d0, B:225:0x05d9, B:228:0x05ec, B:230:0x0634, B:234:0x05e6, B:237:0x05f7, B:240:0x060a, B:241:0x0604, B:244:0x0615, B:247:0x0628, B:248:0x0622, B:249:0x062f, B:250:0x05ca, B:251:0x063e, B:252:0x0656, B:253:0x03f7, B:258:0x0407, B:263:0x0416, B:266:0x042d, B:268:0x0436, B:272:0x0443, B:273:0x0446, B:275:0x0450, B:276:0x0457, B:285:0x045b, B:282:0x046f, B:283:0x0487, B:289:0x0454, B:291:0x0427, B:294:0x048c, B:297:0x049f, B:299:0x04b0, B:302:0x04c4, B:303:0x04ca, B:306:0x04d0, B:307:0x04da, B:309:0x04e2, B:311:0x04f5, B:314:0x04fd, B:315:0x04ff, B:317:0x0504, B:319:0x050d, B:321:0x0516, B:322:0x0519, B:330:0x051f, B:332:0x0526, B:327:0x0533, B:328:0x054b, B:336:0x0511, B:341:0x04bb, B:342:0x0499, B:345:0x0552, B:347:0x055e, B:350:0x0571, B:352:0x057d, B:353:0x0657, B:355:0x0668, B:356:0x066c, B:365:0x0675, B:359:0x0681, B:362:0x068a, B:363:0x06a2, B:378:0x01d2, B:379:0x01fa, B:440:0x00c2, B:443:0x00d3, B:447:0x00cd, B:384:0x00e6, B:386:0x00f2, B:387:0x00f5, B:391:0x00fa, B:392:0x0110, B:400:0x0123, B:402:0x0129, B:404:0x012e, B:406:0x013b, B:408:0x0140, B:412:0x0146, B:413:0x0160, B:414:0x0133, B:416:0x0161, B:417:0x017b, B:425:0x0185, B:428:0x0194, B:430:0x019a, B:431:0x01b8, B:432:0x01b9, B:434:0x06a3, B:435:0x06bb, B:437:0x06bc, B:438:0x06d4), top: B:18:0x0064, inners: #0, #2 }] */
    /* JADX WARN: Type inference failed for: r18v0, types: [com.alibaba.fastjson.parser.DefaultJSONParser] */
    /* JADX WARN: Type inference failed for: r5v82, types: [java.util.Date] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object parseObject(java.util.Map r19, java.lang.Object r20) {
        /*
            Method dump skipped, instructions count: 1755
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.DefaultJSONParser.parseObject(java.util.Map, java.lang.Object):java.lang.Object");
    }

    public void parseObject(Object obj) {
        Object deserialze;
        Class<?> cls = obj.getClass();
        ObjectDeserializer deserializer = this.config.getDeserializer(cls);
        JavaBeanDeserializer javaBeanDeserializer = deserializer instanceof JavaBeanDeserializer ? (JavaBeanDeserializer) deserializer : null;
        int i = this.lexer.token;
        if (i != 12 && i != 16) {
            throw new JSONException("syntax error, expect {, actual " + JSONToken.name(i));
        }
        while (true) {
            String scanSymbol = this.lexer.scanSymbol(this.symbolTable);
            if (scanSymbol == null) {
                if (this.lexer.token == 13) {
                    this.lexer.nextToken(16);
                    return;
                } else if (this.lexer.token == 16) {
                    continue;
                }
            }
            FieldDeserializer fieldDeserializer = javaBeanDeserializer != null ? javaBeanDeserializer.getFieldDeserializer(scanSymbol) : null;
            if (fieldDeserializer != null) {
                Class<?> cls2 = fieldDeserializer.fieldInfo.fieldClass;
                Type type = fieldDeserializer.fieldInfo.fieldType;
                if (cls2 == Integer.TYPE) {
                    this.lexer.nextTokenWithChar(':');
                    deserialze = IntegerCodec.instance.deserialze(this, type, null);
                } else if (cls2 == String.class) {
                    this.lexer.nextTokenWithChar(':');
                    deserialze = parseString();
                } else if (cls2 == Long.TYPE) {
                    this.lexer.nextTokenWithChar(':');
                    deserialze = IntegerCodec.instance.deserialze(this, type, null);
                } else {
                    ObjectDeserializer deserializer2 = this.config.getDeserializer(cls2, type);
                    this.lexer.nextTokenWithChar(':');
                    deserialze = deserializer2.deserialze(this, type, null);
                }
                fieldDeserializer.setValue(obj, deserialze);
                if (this.lexer.token != 16 && this.lexer.token == 13) {
                    this.lexer.nextToken(16);
                    return;
                }
            } else {
                if ((this.lexer.features & Feature.IgnoreNotMatch.mask) == 0) {
                    throw new JSONException("setter not found, class " + cls.getName() + ", property " + scanSymbol);
                }
                this.lexer.nextTokenWithChar(':');
                parse();
                if (this.lexer.token == 13) {
                    this.lexer.nextToken();
                    return;
                }
            }
        }
    }

    public String parseString() {
        int i = this.lexer.token;
        if (i != 4) {
            if (i == 2) {
                String numberString = this.lexer.numberString();
                this.lexer.nextToken(16);
                return numberString;
            }
            Object parse = parse();
            if (parse == null) {
                return null;
            }
            return parse.toString();
        }
        String stringVal = this.lexer.stringVal();
        char c = this.lexer.ch;
        char c2 = JSONLexer.EOI;
        if (c == ',') {
            JSONLexer jSONLexer = this.lexer;
            int i2 = jSONLexer.bp + 1;
            jSONLexer.bp = i2;
            JSONLexer jSONLexer2 = this.lexer;
            if (i2 < jSONLexer2.len) {
                c2 = this.lexer.text.charAt(i2);
            }
            jSONLexer2.ch = c2;
            this.lexer.token = 16;
        } else if (this.lexer.ch == ']') {
            JSONLexer jSONLexer3 = this.lexer;
            int i3 = jSONLexer3.bp + 1;
            jSONLexer3.bp = i3;
            JSONLexer jSONLexer4 = this.lexer;
            if (i3 < jSONLexer4.len) {
                c2 = this.lexer.text.charAt(i3);
            }
            jSONLexer4.ch = c2;
            this.lexer.token = 15;
        } else if (this.lexer.ch == '}') {
            JSONLexer jSONLexer5 = this.lexer;
            int i4 = jSONLexer5.bp + 1;
            jSONLexer5.bp = i4;
            JSONLexer jSONLexer6 = this.lexer;
            if (i4 < jSONLexer6.len) {
                c2 = this.lexer.text.charAt(i4);
            }
            jSONLexer6.ch = c2;
            this.lexer.token = 13;
        } else {
            this.lexer.nextToken();
        }
        return stringVal;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void popContext() {
        this.contex = this.contex.parent;
        ParseContext[] parseContextArr = this.contextArray;
        int i = this.contextArrayIndex;
        parseContextArr[i - 1] = null;
        this.contextArrayIndex = i - 1;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ParseContext setContext(ParseContext parseContext, Object obj, Object obj2) {
        if (this.lexer.disableCircularReferenceDetect) {
            return null;
        }
        this.contex = new ParseContext(parseContext, obj, obj2);
        int i = this.contextArrayIndex;
        this.contextArrayIndex = i + 1;
        ParseContext[] parseContextArr = this.contextArray;
        if (parseContextArr == null) {
            this.contextArray = new ParseContext[8];
        } else if (i >= parseContextArr.length) {
            ParseContext[] parseContextArr2 = new ParseContext[(parseContextArr.length * 3) / 2];
            System.arraycopy(parseContextArr, 0, parseContextArr2, 0, parseContextArr.length);
            this.contextArray = parseContextArr2;
        }
        ParseContext[] parseContextArr3 = this.contextArray;
        ParseContext parseContext2 = this.contex;
        parseContextArr3[i] = parseContext2;
        return parseContext2;
    }

    public void setContext(ParseContext parseContext) {
        if (this.lexer.disableCircularReferenceDetect) {
            return;
        }
        this.contex = parseContext;
    }

    public void setDateFomrat(DateFormat dateFormat) {
        this.dateFormat = dateFormat;
    }

    public void setDateFormat(String str) {
        this.dateFormatPattern = str;
        this.dateFormat = null;
    }
}
