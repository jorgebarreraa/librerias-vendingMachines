package com.yj.coffeemachines.greendao.daos;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.yj.coffeemachines.greendao.beans.VoiceMessage;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

/* loaded from: classes.dex */
public class VoiceMessageDao extends AbstractDao<VoiceMessage, Long> {
    public static final String TABLENAME = "VOICE_MESSAGE";

    /* loaded from: classes.dex */
    public static class Properties {
        public static final Property Key = new Property(0, Long.class, "key", true, "_id");
        public static final Property PositionId = new Property(1, String.class, "positionId", false, "POSITION_ID");
        public static final Property DeviceId = new Property(2, String.class, "deviceId", false, "DEVICE_ID");
        public static final Property DeviceNo = new Property(3, String.class, "deviceNo", false, "DEVICE_NO");
        public static final Property PositionSort = new Property(4, String.class, "positionSort", false, "POSITION_SORT");
        public static final Property Path = new Property(5, String.class, "path", false, "PATH");
        public static final Property FullPath = new Property(6, String.class, "fullPath", false, "FULL_PATH");
        public static final Property LocalPath = new Property(7, String.class, "localPath", false, "LOCAL_PATH");
    }

    public VoiceMessageDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public VoiceMessageDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
    }

    public static void createTable(Database database, boolean z) {
        database.execSQL("CREATE TABLE " + (z ? "IF NOT EXISTS " : "") + "\"VOICE_MESSAGE\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ,\"POSITION_ID\" TEXT,\"DEVICE_ID\" TEXT,\"DEVICE_NO\" TEXT,\"POSITION_SORT\" TEXT,\"PATH\" TEXT,\"FULL_PATH\" TEXT,\"LOCAL_PATH\" TEXT);");
    }

    public static void dropTable(Database database, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("\"VOICE_MESSAGE\"");
        database.execSQL(sb.toString());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(SQLiteStatement sQLiteStatement, VoiceMessage voiceMessage) {
        sQLiteStatement.clearBindings();
        Long key = voiceMessage.getKey();
        if (key != null) {
            sQLiteStatement.bindLong(1, key.longValue());
        }
        String positionId = voiceMessage.getPositionId();
        if (positionId != null) {
            sQLiteStatement.bindString(2, positionId);
        }
        String deviceId = voiceMessage.getDeviceId();
        if (deviceId != null) {
            sQLiteStatement.bindString(3, deviceId);
        }
        String deviceNo = voiceMessage.getDeviceNo();
        if (deviceNo != null) {
            sQLiteStatement.bindString(4, deviceNo);
        }
        String positionSort = voiceMessage.getPositionSort();
        if (positionSort != null) {
            sQLiteStatement.bindString(5, positionSort);
        }
        String path = voiceMessage.getPath();
        if (path != null) {
            sQLiteStatement.bindString(6, path);
        }
        String fullPath = voiceMessage.getFullPath();
        if (fullPath != null) {
            sQLiteStatement.bindString(7, fullPath);
        }
        String localPath = voiceMessage.getLocalPath();
        if (localPath != null) {
            sQLiteStatement.bindString(8, localPath);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(DatabaseStatement databaseStatement, VoiceMessage voiceMessage) {
        databaseStatement.clearBindings();
        Long key = voiceMessage.getKey();
        if (key != null) {
            databaseStatement.bindLong(1, key.longValue());
        }
        String positionId = voiceMessage.getPositionId();
        if (positionId != null) {
            databaseStatement.bindString(2, positionId);
        }
        String deviceId = voiceMessage.getDeviceId();
        if (deviceId != null) {
            databaseStatement.bindString(3, deviceId);
        }
        String deviceNo = voiceMessage.getDeviceNo();
        if (deviceNo != null) {
            databaseStatement.bindString(4, deviceNo);
        }
        String positionSort = voiceMessage.getPositionSort();
        if (positionSort != null) {
            databaseStatement.bindString(5, positionSort);
        }
        String path = voiceMessage.getPath();
        if (path != null) {
            databaseStatement.bindString(6, path);
        }
        String fullPath = voiceMessage.getFullPath();
        if (fullPath != null) {
            databaseStatement.bindString(7, fullPath);
        }
        String localPath = voiceMessage.getLocalPath();
        if (localPath != null) {
            databaseStatement.bindString(8, localPath);
        }
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public Long getKey(VoiceMessage voiceMessage) {
        if (voiceMessage != null) {
            return voiceMessage.getKey();
        }
        return null;
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public boolean hasKey(VoiceMessage voiceMessage) {
        return voiceMessage.getKey() != null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final boolean isEntityUpdateable() {
        return true;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.greenrobot.greendao.AbstractDao
    public VoiceMessage readEntity(Cursor cursor, int i) {
        int i2 = i + 0;
        Long valueOf = cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2));
        int i3 = i + 1;
        String string = cursor.isNull(i3) ? null : cursor.getString(i3);
        int i4 = i + 2;
        String string2 = cursor.isNull(i4) ? null : cursor.getString(i4);
        int i5 = i + 3;
        String string3 = cursor.isNull(i5) ? null : cursor.getString(i5);
        int i6 = i + 4;
        String string4 = cursor.isNull(i6) ? null : cursor.getString(i6);
        int i7 = i + 5;
        String string5 = cursor.isNull(i7) ? null : cursor.getString(i7);
        int i8 = i + 6;
        int i9 = i + 7;
        return new VoiceMessage(valueOf, string, string2, string3, string4, string5, cursor.isNull(i8) ? null : cursor.getString(i8), cursor.isNull(i9) ? null : cursor.getString(i9));
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public void readEntity(Cursor cursor, VoiceMessage voiceMessage, int i) {
        int i2 = i + 0;
        voiceMessage.setKey(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)));
        int i3 = i + 1;
        voiceMessage.setPositionId(cursor.isNull(i3) ? null : cursor.getString(i3));
        int i4 = i + 2;
        voiceMessage.setDeviceId(cursor.isNull(i4) ? null : cursor.getString(i4));
        int i5 = i + 3;
        voiceMessage.setDeviceNo(cursor.isNull(i5) ? null : cursor.getString(i5));
        int i6 = i + 4;
        voiceMessage.setPositionSort(cursor.isNull(i6) ? null : cursor.getString(i6));
        int i7 = i + 5;
        voiceMessage.setPath(cursor.isNull(i7) ? null : cursor.getString(i7));
        int i8 = i + 6;
        voiceMessage.setFullPath(cursor.isNull(i8) ? null : cursor.getString(i8));
        int i9 = i + 7;
        voiceMessage.setLocalPath(cursor.isNull(i9) ? null : cursor.getString(i9));
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.greenrobot.greendao.AbstractDao
    public Long readKey(Cursor cursor, int i) {
        int i2 = i + 0;
        if (cursor.isNull(i2)) {
            return null;
        }
        return Long.valueOf(cursor.getLong(i2));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final Long updateKeyAfterInsert(VoiceMessage voiceMessage, long j) {
        voiceMessage.setKey(Long.valueOf(j));
        return Long.valueOf(j);
    }
}
