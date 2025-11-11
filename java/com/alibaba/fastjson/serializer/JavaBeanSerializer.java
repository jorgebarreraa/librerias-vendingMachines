package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.PropertyNamingStrategy;
import com.alibaba.fastjson.annotation.JSONType;
import com.alibaba.fastjson.util.FieldInfo;
import com.alibaba.fastjson.util.TypeUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class JavaBeanSerializer implements ObjectSerializer {
    protected int features;
    private final FieldSerializer[] getters;
    private final FieldSerializer[] sortedGetters;
    protected final String typeKey;
    protected final String typeName;
    private static final char[] true_chars = {'t', 'r', 'u', 'e'};
    private static final char[] false_chars = {'f', 'a', 'l', 's', 'e'};

    public JavaBeanSerializer(Class<?> cls) {
        this(cls, (PropertyNamingStrategy) null);
    }

    public JavaBeanSerializer(Class<?> cls, int i, Map<String, String> map, boolean z, boolean z2, boolean z3, boolean z4, PropertyNamingStrategy propertyNamingStrategy) {
        PropertyNamingStrategy propertyNamingStrategy2;
        String str;
        String str2;
        PropertyNamingStrategy naming;
        this.features = 0;
        JSONType jSONType = z2 ? (JSONType) cls.getAnnotation(JSONType.class) : null;
        if (jSONType != null) {
            this.features = SerializerFeature.of(jSONType.serialzeFeatures());
            str = jSONType.typeName();
            if (str.length() == 0) {
                str = null;
                str2 = null;
            } else {
                String str3 = null;
                for (Class<? super Object> superclass = cls.getSuperclass(); superclass != null && superclass != Object.class; superclass = superclass.getSuperclass()) {
                    JSONType jSONType2 = (JSONType) superclass.getAnnotation(JSONType.class);
                    if (jSONType2 == null) {
                        break;
                    }
                    str3 = jSONType2.typeKey();
                    if (str3.length() != 0) {
                        break;
                    }
                }
                str2 = str3;
                for (Class<?> cls2 : cls.getInterfaces()) {
                    JSONType jSONType3 = (JSONType) cls2.getAnnotation(JSONType.class);
                    if (jSONType3 != null) {
                        str2 = jSONType3.typeKey();
                        if (str2.length() != 0) {
                            break;
                        }
                    }
                }
                if (str2 != null && str2.length() == 0) {
                    str2 = null;
                }
            }
            propertyNamingStrategy2 = (propertyNamingStrategy != null || (naming = jSONType.naming()) == PropertyNamingStrategy.CamelCase) ? propertyNamingStrategy : naming;
        } else {
            propertyNamingStrategy2 = propertyNamingStrategy;
            str = null;
            str2 = null;
        }
        this.typeName = str;
        this.typeKey = str2;
        List<FieldInfo> computeGetters = TypeUtils.computeGetters(cls, i, z, jSONType, map, false, z3, z4, propertyNamingStrategy2);
        ArrayList arrayList = new ArrayList();
        Iterator<FieldInfo> it2 = computeGetters.iterator();
        while (it2.hasNext()) {
            arrayList.add(new FieldSerializer(it2.next()));
        }
        this.getters = (FieldSerializer[]) arrayList.toArray(new FieldSerializer[arrayList.size()]);
        String[] orders = jSONType != null ? jSONType.orders() : null;
        if (orders != null && orders.length != 0) {
            List<FieldInfo> computeGetters2 = TypeUtils.computeGetters(cls, i, z, jSONType, map, true, z3, z4, propertyNamingStrategy2);
            ArrayList arrayList2 = new ArrayList();
            Iterator<FieldInfo> it3 = computeGetters2.iterator();
            while (it3.hasNext()) {
                arrayList2.add(new FieldSerializer(it3.next()));
            }
            this.sortedGetters = (FieldSerializer[]) arrayList2.toArray(new FieldSerializer[arrayList2.size()]);
            return;
        }
        FieldSerializer[] fieldSerializerArr = this.getters;
        FieldSerializer[] fieldSerializerArr2 = new FieldSerializer[fieldSerializerArr.length];
        System.arraycopy(fieldSerializerArr, 0, fieldSerializerArr2, 0, fieldSerializerArr.length);
        Arrays.sort(fieldSerializerArr2);
        if (Arrays.equals(fieldSerializerArr2, this.getters)) {
            this.sortedGetters = this.getters;
        } else {
            this.sortedGetters = fieldSerializerArr2;
        }
    }

    public JavaBeanSerializer(Class<?> cls, PropertyNamingStrategy propertyNamingStrategy) {
        this(cls, cls.getModifiers(), (Map) null, false, true, true, true, propertyNamingStrategy);
    }

    public JavaBeanSerializer(Class<?> cls, String... strArr) {
        this(cls, cls.getModifiers(), map(strArr), false, true, true, true, null);
    }

    private static Map<String, String> map(String... strArr) {
        HashMap hashMap = new HashMap();
        for (String str : strArr) {
            hashMap.put(str, str);
        }
        return hashMap;
    }

    public Map<String, Object> getFieldValuesMap(Object obj) throws Exception {
        LinkedHashMap linkedHashMap = new LinkedHashMap(this.sortedGetters.length);
        for (FieldSerializer fieldSerializer : this.sortedGetters) {
            linkedHashMap.put(fieldSerializer.fieldInfo.name, fieldSerializer.getPropertyValue(obj));
        }
        return linkedHashMap;
    }

    /* JADX WARN: Removed duplicated region for block: B:125:0x0224  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x0255 A[Catch: all -> 0x05d1, Exception -> 0x05d7, TryCatch #8 {Exception -> 0x05d7, all -> 0x05d1, blocks: (B:92:0x017e, B:94:0x0199, B:96:0x019d, B:103:0x01a2, B:105:0x01a6, B:109:0x01b1, B:110:0x01b5, B:112:0x01bb, B:117:0x01d6, B:119:0x01dd, B:121:0x01e1, B:126:0x0226, B:128:0x022c, B:130:0x024b, B:131:0x024f, B:133:0x0255, B:143:0x0284, B:145:0x0288, B:147:0x02a3, B:148:0x02aa, B:150:0x02b0, B:156:0x02cd, B:158:0x02d1, B:160:0x02d8, B:162:0x02dc, B:163:0x02e1, B:165:0x02e5, B:166:0x02ea, B:167:0x02f0, B:169:0x02f6, B:177:0x0316, B:179:0x0322, B:181:0x032f, B:183:0x0333, B:186:0x033e, B:188:0x0341, B:194:0x03ed, B:196:0x03f1, B:198:0x03f5, B:200:0x03f9, B:202:0x03fd, B:204:0x0401, B:206:0x0413, B:208:0x0417, B:210:0x041b, B:212:0x0405, B:214:0x0409, B:218:0x042b, B:220:0x0434, B:222:0x0438, B:223:0x043c, B:224:0x0440, B:226:0x0453, B:229:0x045d, B:230:0x0461, B:236:0x046b, B:237:0x046e, B:240:0x0476, B:242:0x0481, B:244:0x0485, B:246:0x048b, B:250:0x04ac, B:253:0x04b6, B:256:0x04bd, B:260:0x04c5, B:264:0x04d1, B:268:0x04d7, B:270:0x04db, B:271:0x04dd, B:273:0x04e5, B:275:0x04e9, B:276:0x04ed, B:278:0x04fd, B:266:0x0507, B:281:0x050a, B:283:0x050e, B:284:0x0517, B:287:0x051d, B:288:0x052a, B:292:0x053d, B:294:0x0544, B:296:0x054d, B:299:0x0555, B:300:0x055a, B:301:0x0561, B:303:0x0565, B:304:0x056a, B:305:0x0571, B:308:0x0579, B:310:0x0582, B:314:0x0596, B:315:0x059b, B:317:0x05a0, B:318:0x05ab, B:319:0x05b0, B:320:0x05b5, B:322:0x0346, B:326:0x0355, B:328:0x0360, B:330:0x0364, B:333:0x036b, B:335:0x036e, B:338:0x0377, B:340:0x037f, B:342:0x038a, B:344:0x038e, B:347:0x0395, B:349:0x0398, B:351:0x039d, B:352:0x03a3, B:354:0x03ab, B:356:0x03b6, B:358:0x03ba, B:361:0x03c1, B:363:0x03c4, B:365:0x03c9, B:367:0x03d2, B:369:0x03d6, B:374:0x028f, B:376:0x0293, B:377:0x0298, B:379:0x029c, B:385:0x0235, B:387:0x0239, B:388:0x023e, B:390:0x0242, B:394:0x01ef, B:396:0x01f3, B:397:0x01fe, B:399:0x0202, B:400:0x020f, B:402:0x0216, B:427:0x05eb, B:428:0x05f3, B:430:0x05f9, B:435:0x060b, B:437:0x0614, B:440:0x0624, B:442:0x0628, B:443:0x062c), top: B:91:0x017e }] */
    /* JADX WARN: Removed duplicated region for block: B:139:0x0278  */
    /* JADX WARN: Removed duplicated region for block: B:140:0x027e  */
    /* JADX WARN: Removed duplicated region for block: B:150:0x02b0 A[Catch: all -> 0x05d1, Exception -> 0x05d7, LOOP:4: B:148:0x02aa->B:150:0x02b0, LOOP_END, TryCatch #8 {Exception -> 0x05d7, all -> 0x05d1, blocks: (B:92:0x017e, B:94:0x0199, B:96:0x019d, B:103:0x01a2, B:105:0x01a6, B:109:0x01b1, B:110:0x01b5, B:112:0x01bb, B:117:0x01d6, B:119:0x01dd, B:121:0x01e1, B:126:0x0226, B:128:0x022c, B:130:0x024b, B:131:0x024f, B:133:0x0255, B:143:0x0284, B:145:0x0288, B:147:0x02a3, B:148:0x02aa, B:150:0x02b0, B:156:0x02cd, B:158:0x02d1, B:160:0x02d8, B:162:0x02dc, B:163:0x02e1, B:165:0x02e5, B:166:0x02ea, B:167:0x02f0, B:169:0x02f6, B:177:0x0316, B:179:0x0322, B:181:0x032f, B:183:0x0333, B:186:0x033e, B:188:0x0341, B:194:0x03ed, B:196:0x03f1, B:198:0x03f5, B:200:0x03f9, B:202:0x03fd, B:204:0x0401, B:206:0x0413, B:208:0x0417, B:210:0x041b, B:212:0x0405, B:214:0x0409, B:218:0x042b, B:220:0x0434, B:222:0x0438, B:223:0x043c, B:224:0x0440, B:226:0x0453, B:229:0x045d, B:230:0x0461, B:236:0x046b, B:237:0x046e, B:240:0x0476, B:242:0x0481, B:244:0x0485, B:246:0x048b, B:250:0x04ac, B:253:0x04b6, B:256:0x04bd, B:260:0x04c5, B:264:0x04d1, B:268:0x04d7, B:270:0x04db, B:271:0x04dd, B:273:0x04e5, B:275:0x04e9, B:276:0x04ed, B:278:0x04fd, B:266:0x0507, B:281:0x050a, B:283:0x050e, B:284:0x0517, B:287:0x051d, B:288:0x052a, B:292:0x053d, B:294:0x0544, B:296:0x054d, B:299:0x0555, B:300:0x055a, B:301:0x0561, B:303:0x0565, B:304:0x056a, B:305:0x0571, B:308:0x0579, B:310:0x0582, B:314:0x0596, B:315:0x059b, B:317:0x05a0, B:318:0x05ab, B:319:0x05b0, B:320:0x05b5, B:322:0x0346, B:326:0x0355, B:328:0x0360, B:330:0x0364, B:333:0x036b, B:335:0x036e, B:338:0x0377, B:340:0x037f, B:342:0x038a, B:344:0x038e, B:347:0x0395, B:349:0x0398, B:351:0x039d, B:352:0x03a3, B:354:0x03ab, B:356:0x03b6, B:358:0x03ba, B:361:0x03c1, B:363:0x03c4, B:365:0x03c9, B:367:0x03d2, B:369:0x03d6, B:374:0x028f, B:376:0x0293, B:377:0x0298, B:379:0x029c, B:385:0x0235, B:387:0x0239, B:388:0x023e, B:390:0x0242, B:394:0x01ef, B:396:0x01f3, B:397:0x01fe, B:399:0x0202, B:400:0x020f, B:402:0x0216, B:427:0x05eb, B:428:0x05f3, B:430:0x05f9, B:435:0x060b, B:437:0x0614, B:440:0x0624, B:442:0x0628, B:443:0x062c), top: B:91:0x017e }] */
    /* JADX WARN: Removed duplicated region for block: B:278:0x04fd A[Catch: all -> 0x05d1, Exception -> 0x05d7, TryCatch #8 {Exception -> 0x05d7, all -> 0x05d1, blocks: (B:92:0x017e, B:94:0x0199, B:96:0x019d, B:103:0x01a2, B:105:0x01a6, B:109:0x01b1, B:110:0x01b5, B:112:0x01bb, B:117:0x01d6, B:119:0x01dd, B:121:0x01e1, B:126:0x0226, B:128:0x022c, B:130:0x024b, B:131:0x024f, B:133:0x0255, B:143:0x0284, B:145:0x0288, B:147:0x02a3, B:148:0x02aa, B:150:0x02b0, B:156:0x02cd, B:158:0x02d1, B:160:0x02d8, B:162:0x02dc, B:163:0x02e1, B:165:0x02e5, B:166:0x02ea, B:167:0x02f0, B:169:0x02f6, B:177:0x0316, B:179:0x0322, B:181:0x032f, B:183:0x0333, B:186:0x033e, B:188:0x0341, B:194:0x03ed, B:196:0x03f1, B:198:0x03f5, B:200:0x03f9, B:202:0x03fd, B:204:0x0401, B:206:0x0413, B:208:0x0417, B:210:0x041b, B:212:0x0405, B:214:0x0409, B:218:0x042b, B:220:0x0434, B:222:0x0438, B:223:0x043c, B:224:0x0440, B:226:0x0453, B:229:0x045d, B:230:0x0461, B:236:0x046b, B:237:0x046e, B:240:0x0476, B:242:0x0481, B:244:0x0485, B:246:0x048b, B:250:0x04ac, B:253:0x04b6, B:256:0x04bd, B:260:0x04c5, B:264:0x04d1, B:268:0x04d7, B:270:0x04db, B:271:0x04dd, B:273:0x04e5, B:275:0x04e9, B:276:0x04ed, B:278:0x04fd, B:266:0x0507, B:281:0x050a, B:283:0x050e, B:284:0x0517, B:287:0x051d, B:288:0x052a, B:292:0x053d, B:294:0x0544, B:296:0x054d, B:299:0x0555, B:300:0x055a, B:301:0x0561, B:303:0x0565, B:304:0x056a, B:305:0x0571, B:308:0x0579, B:310:0x0582, B:314:0x0596, B:315:0x059b, B:317:0x05a0, B:318:0x05ab, B:319:0x05b0, B:320:0x05b5, B:322:0x0346, B:326:0x0355, B:328:0x0360, B:330:0x0364, B:333:0x036b, B:335:0x036e, B:338:0x0377, B:340:0x037f, B:342:0x038a, B:344:0x038e, B:347:0x0395, B:349:0x0398, B:351:0x039d, B:352:0x03a3, B:354:0x03ab, B:356:0x03b6, B:358:0x03ba, B:361:0x03c1, B:363:0x03c4, B:365:0x03c9, B:367:0x03d2, B:369:0x03d6, B:374:0x028f, B:376:0x0293, B:377:0x0298, B:379:0x029c, B:385:0x0235, B:387:0x0239, B:388:0x023e, B:390:0x0242, B:394:0x01ef, B:396:0x01f3, B:397:0x01fe, B:399:0x0202, B:400:0x020f, B:402:0x0216, B:427:0x05eb, B:428:0x05f3, B:430:0x05f9, B:435:0x060b, B:437:0x0614, B:440:0x0624, B:442:0x0628, B:443:0x062c), top: B:91:0x017e }] */
    /* JADX WARN: Removed duplicated region for block: B:393:0x0271  */
    /* JADX WARN: Removed duplicated region for block: B:417:0x0652 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:425:0x05e6  */
    /* JADX WARN: Removed duplicated region for block: B:435:0x060b A[Catch: all -> 0x05d1, Exception -> 0x05d7, TRY_ENTER, TryCatch #8 {Exception -> 0x05d7, all -> 0x05d1, blocks: (B:92:0x017e, B:94:0x0199, B:96:0x019d, B:103:0x01a2, B:105:0x01a6, B:109:0x01b1, B:110:0x01b5, B:112:0x01bb, B:117:0x01d6, B:119:0x01dd, B:121:0x01e1, B:126:0x0226, B:128:0x022c, B:130:0x024b, B:131:0x024f, B:133:0x0255, B:143:0x0284, B:145:0x0288, B:147:0x02a3, B:148:0x02aa, B:150:0x02b0, B:156:0x02cd, B:158:0x02d1, B:160:0x02d8, B:162:0x02dc, B:163:0x02e1, B:165:0x02e5, B:166:0x02ea, B:167:0x02f0, B:169:0x02f6, B:177:0x0316, B:179:0x0322, B:181:0x032f, B:183:0x0333, B:186:0x033e, B:188:0x0341, B:194:0x03ed, B:196:0x03f1, B:198:0x03f5, B:200:0x03f9, B:202:0x03fd, B:204:0x0401, B:206:0x0413, B:208:0x0417, B:210:0x041b, B:212:0x0405, B:214:0x0409, B:218:0x042b, B:220:0x0434, B:222:0x0438, B:223:0x043c, B:224:0x0440, B:226:0x0453, B:229:0x045d, B:230:0x0461, B:236:0x046b, B:237:0x046e, B:240:0x0476, B:242:0x0481, B:244:0x0485, B:246:0x048b, B:250:0x04ac, B:253:0x04b6, B:256:0x04bd, B:260:0x04c5, B:264:0x04d1, B:268:0x04d7, B:270:0x04db, B:271:0x04dd, B:273:0x04e5, B:275:0x04e9, B:276:0x04ed, B:278:0x04fd, B:266:0x0507, B:281:0x050a, B:283:0x050e, B:284:0x0517, B:287:0x051d, B:288:0x052a, B:292:0x053d, B:294:0x0544, B:296:0x054d, B:299:0x0555, B:300:0x055a, B:301:0x0561, B:303:0x0565, B:304:0x056a, B:305:0x0571, B:308:0x0579, B:310:0x0582, B:314:0x0596, B:315:0x059b, B:317:0x05a0, B:318:0x05ab, B:319:0x05b0, B:320:0x05b5, B:322:0x0346, B:326:0x0355, B:328:0x0360, B:330:0x0364, B:333:0x036b, B:335:0x036e, B:338:0x0377, B:340:0x037f, B:342:0x038a, B:344:0x038e, B:347:0x0395, B:349:0x0398, B:351:0x039d, B:352:0x03a3, B:354:0x03ab, B:356:0x03b6, B:358:0x03ba, B:361:0x03c1, B:363:0x03c4, B:365:0x03c9, B:367:0x03d2, B:369:0x03d6, B:374:0x028f, B:376:0x0293, B:377:0x0298, B:379:0x029c, B:385:0x0235, B:387:0x0239, B:388:0x023e, B:390:0x0242, B:394:0x01ef, B:396:0x01f3, B:397:0x01fe, B:399:0x0202, B:400:0x020f, B:402:0x0216, B:427:0x05eb, B:428:0x05f3, B:430:0x05f9, B:435:0x060b, B:437:0x0614, B:440:0x0624, B:442:0x0628, B:443:0x062c), top: B:91:0x017e }] */
    /* JADX WARN: Removed duplicated region for block: B:440:0x0624 A[Catch: all -> 0x05d1, Exception -> 0x05d7, TRY_ENTER, TryCatch #8 {Exception -> 0x05d7, all -> 0x05d1, blocks: (B:92:0x017e, B:94:0x0199, B:96:0x019d, B:103:0x01a2, B:105:0x01a6, B:109:0x01b1, B:110:0x01b5, B:112:0x01bb, B:117:0x01d6, B:119:0x01dd, B:121:0x01e1, B:126:0x0226, B:128:0x022c, B:130:0x024b, B:131:0x024f, B:133:0x0255, B:143:0x0284, B:145:0x0288, B:147:0x02a3, B:148:0x02aa, B:150:0x02b0, B:156:0x02cd, B:158:0x02d1, B:160:0x02d8, B:162:0x02dc, B:163:0x02e1, B:165:0x02e5, B:166:0x02ea, B:167:0x02f0, B:169:0x02f6, B:177:0x0316, B:179:0x0322, B:181:0x032f, B:183:0x0333, B:186:0x033e, B:188:0x0341, B:194:0x03ed, B:196:0x03f1, B:198:0x03f5, B:200:0x03f9, B:202:0x03fd, B:204:0x0401, B:206:0x0413, B:208:0x0417, B:210:0x041b, B:212:0x0405, B:214:0x0409, B:218:0x042b, B:220:0x0434, B:222:0x0438, B:223:0x043c, B:224:0x0440, B:226:0x0453, B:229:0x045d, B:230:0x0461, B:236:0x046b, B:237:0x046e, B:240:0x0476, B:242:0x0481, B:244:0x0485, B:246:0x048b, B:250:0x04ac, B:253:0x04b6, B:256:0x04bd, B:260:0x04c5, B:264:0x04d1, B:268:0x04d7, B:270:0x04db, B:271:0x04dd, B:273:0x04e5, B:275:0x04e9, B:276:0x04ed, B:278:0x04fd, B:266:0x0507, B:281:0x050a, B:283:0x050e, B:284:0x0517, B:287:0x051d, B:288:0x052a, B:292:0x053d, B:294:0x0544, B:296:0x054d, B:299:0x0555, B:300:0x055a, B:301:0x0561, B:303:0x0565, B:304:0x056a, B:305:0x0571, B:308:0x0579, B:310:0x0582, B:314:0x0596, B:315:0x059b, B:317:0x05a0, B:318:0x05ab, B:319:0x05b0, B:320:0x05b5, B:322:0x0346, B:326:0x0355, B:328:0x0360, B:330:0x0364, B:333:0x036b, B:335:0x036e, B:338:0x0377, B:340:0x037f, B:342:0x038a, B:344:0x038e, B:347:0x0395, B:349:0x0398, B:351:0x039d, B:352:0x03a3, B:354:0x03ab, B:356:0x03b6, B:358:0x03ba, B:361:0x03c1, B:363:0x03c4, B:365:0x03c9, B:367:0x03d2, B:369:0x03d6, B:374:0x028f, B:376:0x0293, B:377:0x0298, B:379:0x029c, B:385:0x0235, B:387:0x0239, B:388:0x023e, B:390:0x0242, B:394:0x01ef, B:396:0x01f3, B:397:0x01fe, B:399:0x0202, B:400:0x020f, B:402:0x0216, B:427:0x05eb, B:428:0x05f3, B:430:0x05f9, B:435:0x060b, B:437:0x0614, B:440:0x0624, B:442:0x0628, B:443:0x062c), top: B:91:0x017e }] */
    /* JADX WARN: Removed duplicated region for block: B:455:0x016b  */
    /* JADX WARN: Removed duplicated region for block: B:456:0x015f  */
    /* JADX WARN: Removed duplicated region for block: B:463:0x013e  */
    /* JADX WARN: Removed duplicated region for block: B:464:0x011e  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x011b  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x0123 A[Catch: all -> 0x00a0, Exception -> 0x00a5, TRY_ENTER, TryCatch #9 {Exception -> 0x00a5, all -> 0x00a0, blocks: (B:469:0x0093, B:471:0x0097, B:472:0x009b, B:33:0x00b5, B:35:0x00be, B:39:0x00cd, B:42:0x00d8, B:44:0x00e1, B:46:0x00e5, B:51:0x00f1, B:53:0x00f7, B:55:0x00fb, B:56:0x0102, B:58:0x0109, B:59:0x0111, B:66:0x0123, B:67:0x0129, B:69:0x012f, B:76:0x0148, B:465:0x00fe), top: B:468:0x0093 }] */
    /* JADX WARN: Removed duplicated region for block: B:72:0x013c  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x0148 A[Catch: all -> 0x00a0, Exception -> 0x00a5, TRY_ENTER, TRY_LEAVE, TryCatch #9 {Exception -> 0x00a5, all -> 0x00a0, blocks: (B:469:0x0093, B:471:0x0097, B:472:0x009b, B:33:0x00b5, B:35:0x00be, B:39:0x00cd, B:42:0x00d8, B:44:0x00e1, B:46:0x00e5, B:51:0x00f1, B:53:0x00f7, B:55:0x00fb, B:56:0x0102, B:58:0x0109, B:59:0x0111, B:66:0x0123, B:67:0x0129, B:69:0x012f, B:76:0x0148, B:465:0x00fe), top: B:468:0x0093 }] */
    /* JADX WARN: Removed duplicated region for block: B:82:0x015d  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x0169  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x017e A[EXC_TOP_SPLITTER, SYNTHETIC] */
    @Override // com.alibaba.fastjson.serializer.ObjectSerializer
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void write(com.alibaba.fastjson.serializer.JSONSerializer r36, java.lang.Object r37, java.lang.Object r38, java.lang.reflect.Type r39) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 1649
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.serializer.JavaBeanSerializer.write(com.alibaba.fastjson.serializer.JSONSerializer, java.lang.Object, java.lang.Object, java.lang.reflect.Type):void");
    }
}
