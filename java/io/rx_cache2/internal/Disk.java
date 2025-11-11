package io.rx_cache2.internal;

import io.rx_cache2.internal.encrypt.FileEncryptor;
import io.victoralbertos.jolyglot.JolyglotGenerics;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import org.eclipse.paho.client.mqttv3.MqttTopic;

/* loaded from: classes2.dex */
public final class Disk implements Persistence {
    private final File cacheDirectory;
    private final FileEncryptor fileEncryptor;
    private final JolyglotGenerics jolyglot;

    @Inject
    public Disk(File file, FileEncryptor fileEncryptor, JolyglotGenerics jolyglotGenerics) {
        this.cacheDirectory = file;
        this.fileEncryptor = fileEncryptor;
        this.jolyglot = jolyglotGenerics;
    }

    private String safetyKey(String str) {
        return str.replaceAll(MqttTopic.TOPIC_LEVEL_SEPARATOR, "_");
    }

    @Override // io.rx_cache2.internal.Persistence
    public List<String> allKeys() {
        ArrayList arrayList = new ArrayList();
        File[] listFiles = this.cacheDirectory.listFiles();
        if (listFiles == null) {
            return arrayList;
        }
        for (File file : listFiles) {
            if (file.isFile()) {
                arrayList.add(file.getName());
            }
        }
        return arrayList;
    }

    @Override // io.rx_cache2.internal.Persistence
    public void evict(String str) {
        new File(this.cacheDirectory, safetyKey(str)).delete();
    }

    @Override // io.rx_cache2.internal.Persistence
    public void evictAll() {
        File[] listFiles = this.cacheDirectory.listFiles();
        if (listFiles != null) {
            for (File file : listFiles) {
                if (file != null) {
                    file.delete();
                }
            }
        }
    }

    @Override // io.rx_cache2.internal.Persistence
    public <T> T retrieve(String str, Class<T> cls, boolean z, String str2) {
        File file = new File(this.cacheDirectory, safetyKey(str));
        if (z) {
            file = this.fileEncryptor.decrypt(str2, file);
        }
        try {
            T t = (T) this.jolyglot.fromJson(file, (Class) cls);
            if (z) {
                file.delete();
            }
            return t;
        } catch (Exception unused) {
            if (z) {
                file.delete();
            }
            return null;
        } catch (Throwable th) {
            if (z) {
                file.delete();
            }
            throw th;
        }
    }

    public <T> T[] retrieveArray(String str, Class<T> cls) {
        try {
            return (T[]) ((Object[]) this.jolyglot.fromJson(new File(this.cacheDirectory, safetyKey(str)), (Class) Array.newInstance((Class<?>) cls, 1).getClass()));
        } catch (Exception unused) {
            return null;
        }
    }

    public <C extends Collection<T>, T> C retrieveCollection(String str, Class<C> cls, Class<T> cls2) {
        try {
            return (C) this.jolyglot.fromJson(new File(this.cacheDirectory, safetyKey(str)), this.jolyglot.newParameterizedType(cls, cls2));
        } catch (Exception unused) {
            return null;
        }
    }

    public <M extends Map<K, V>, K, V> M retrieveMap(String str, Class cls, Class<K> cls2, Class<V> cls3) {
        try {
            return (M) this.jolyglot.fromJson(new File(this.cacheDirectory, safetyKey(str)), this.jolyglot.newParameterizedType(cls, cls2, cls3));
        } catch (Exception unused) {
            return null;
        }
    }

    @Override // io.rx_cache2.internal.Persistence
    public <T> Record<T> retrieveRecord(String str, boolean z, String str2) {
        File file = new File(this.cacheDirectory, safetyKey(str));
        if (z) {
            try {
                file = this.fileEncryptor.decrypt(str2, file);
            } catch (Exception unused) {
                if (z) {
                    file.delete();
                }
                return null;
            } catch (Throwable th) {
                if (z) {
                    file.delete();
                }
                throw th;
            }
        }
        Record record = (Record) this.jolyglot.fromJson(file, this.jolyglot.newParameterizedType(Record.class, Object.class));
        Class<?> cls = record.getDataClassName() == null ? Object.class : Class.forName(record.getDataClassName());
        Class<?> cls2 = record.getDataCollectionClassName() == null ? Object.class : Class.forName(record.getDataCollectionClassName());
        Record<T> record2 = Collection.class.isAssignableFrom(cls2) ? (Record) this.jolyglot.fromJson(file.getAbsoluteFile(), this.jolyglot.newParameterizedType(Record.class, this.jolyglot.newParameterizedType(cls2, cls))) : cls2.isArray() ? (Record) this.jolyglot.fromJson(file.getAbsoluteFile(), this.jolyglot.newParameterizedType(Record.class, cls2)) : Map.class.isAssignableFrom(cls2) ? (Record) this.jolyglot.fromJson(file.getAbsoluteFile(), this.jolyglot.newParameterizedType(Record.class, this.jolyglot.newParameterizedType(cls2, Class.forName(record.getDataKeyMapClassName()), cls))) : (Record) this.jolyglot.fromJson(file.getAbsoluteFile(), this.jolyglot.newParameterizedType(Record.class, cls));
        record2.setSizeOnMb((((float) file.length()) / 1024.0f) / 1024.0f);
        if (z) {
            file.delete();
        }
        return record2;
    }

    @Override // io.rx_cache2.internal.Persistence
    public void save(String str, Object obj, boolean z, String str2) {
        FileWriter fileWriter;
        String safetyKey = safetyKey(str);
        String json = obj instanceof Record ? this.jolyglot.toJson(obj, this.jolyglot.newParameterizedType(obj.getClass(), Object.class)) : this.jolyglot.toJson(obj);
        FileWriter fileWriter2 = null;
        try {
            try {
                fileWriter = new FileWriter(new File(this.cacheDirectory, safetyKey), false);
            } catch (Throwable th) {
                th = th;
            }
        } catch (Exception e) {
            e = e;
        }
        try {
            fileWriter.write(json);
            fileWriter.flush();
            fileWriter.close();
            if (z) {
                this.fileEncryptor.encrypt(str2, new File(this.cacheDirectory, safetyKey));
            }
        } catch (Exception e2) {
            e = e2;
            throw new RuntimeException(e);
        } catch (Throwable th2) {
            th = th2;
            fileWriter2 = fileWriter;
            if (fileWriter2 != null) {
                try {
                    fileWriter2.flush();
                    fileWriter2.close();
                } catch (IOException e3) {
                    e3.printStackTrace();
                }
            }
            throw th;
        }
    }

    @Override // io.rx_cache2.internal.Persistence
    public void saveRecord(String str, Record record, boolean z, String str2) {
        save(str, record, z, str2);
    }

    @Override // io.rx_cache2.internal.Persistence
    public int storedMB() {
        File[] listFiles = this.cacheDirectory.listFiles();
        if (listFiles == null) {
            return 0;
        }
        long j = 0;
        for (File file : listFiles) {
            j += file.length();
        }
        return (int) Math.ceil((j / 1024.0d) / 1024.0d);
    }
}
