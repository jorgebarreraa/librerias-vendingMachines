package com.yj.coffeemachines.greendao.daos;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.yj.coffeemachines.greendao.beans.ADMessage;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

/* loaded from: classes.dex */
public class ADMessageDao extends AbstractDao<ADMessage, Long> {
    public static final String TABLENAME = "ADMESSAGE";

    /* loaded from: classes.dex */
    public static class Properties {
        public static final Property Key = new Property(0, Long.class, "key", true, "_id");
        public static final Property Id = new Property(1, String.class, "id", false, "ID");
        public static final Property No = new Property(2, String.class, "no", false, "NO");
        public static final Property Name = new Property(3, String.class, "name", false, "NAME");
        public static final Property FullPath = new Property(4, String.class, "fullPath", false, "FULL_PATH");
        public static final Property LocalPath = new Property(5, String.class, "localPath", false, "LOCAL_PATH");
        public static final Property DeviceId = new Property(6, String.class, "deviceId", false, "DEVICE_ID");
        public static final Property DeviceNo = new Property(7, String.class, "deviceNo", false, "DEVICE_NO");
        public static final Property StartTime = new Property(8, String.class, "startTime", false, "START_TIME");
        public static final Property StartTime_long = new Property(9, Long.class, "startTime_long", false, "START_TIME_LONG");
        public static final Property Sort = new Property(10, Integer.TYPE, "sort", false, "SORT");
        public static final Property EndTime = new Property(11, String.class, "endTime", false, "END_TIME");
        public static final Property EndTime_long = new Property(12, Long.class, "endTime_long", false, "END_TIME_LONG");
    }

    public ADMessageDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public ADMessageDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
    }

    public static void createTable(Database database, boolean z) {
        database.execSQL("CREATE TABLE " + (z ? "IF NOT EXISTS " : "") + "\"ADMESSAGE\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ,\"ID\" TEXT,\"NO\" TEXT,\"NAME\" TEXT,\"FULL_PATH\" TEXT,\"LOCAL_PATH\" TEXT,\"DEVICE_ID\" TEXT,\"DEVICE_NO\" TEXT,\"START_TIME\" TEXT,\"START_TIME_LONG\" INTEGER,\"SORT\" INTEGER NOT NULL ,\"END_TIME\" TEXT,\"END_TIME_LONG\" INTEGER);");
    }

    public static void dropTable(Database database, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("\"ADMESSAGE\"");
        database.execSQL(sb.toString());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(SQLiteStatement sQLiteStatement, ADMessage aDMessage) {
        sQLiteStatement.clearBindings();
        Long key = aDMessage.getKey();
        if (key != null) {
            sQLiteStatement.bindLong(1, key.longValue());
        }
        String id = aDMessage.getId();
        if (id != null) {
            sQLiteStatement.bindString(2, id);
        }
        String no = aDMessage.getNo();
        if (no != null) {
            sQLiteStatement.bindString(3, no);
        }
        String name = aDMessage.getName();
        if (name != null) {
            sQLiteStatement.bindString(4, name);
        }
        String fullPath = aDMessage.getFullPath();
        if (fullPath != null) {
            sQLiteStatement.bindString(5, fullPath);
        }
        String localPath = aDMessage.getLocalPath();
        if (localPath != null) {
            sQLiteStatement.bindString(6, localPath);
        }
        String deviceId = aDMessage.getDeviceId();
        if (deviceId != null) {
            sQLiteStatement.bindString(7, deviceId);
        }
        String deviceNo = aDMessage.getDeviceNo();
        if (deviceNo != null) {
            sQLiteStatement.bindString(8, deviceNo);
        }
        String startTime = aDMessage.getStartTime();
        if (startTime != null) {
            sQLiteStatement.bindString(9, startTime);
        }
        Long startTime_long = aDMessage.getStartTime_long();
        if (startTime_long != null) {
            sQLiteStatement.bindLong(10, startTime_long.longValue());
        }
        sQLiteStatement.bindLong(11, aDMessage.getSort());
        String endTime = aDMessage.getEndTime();
        if (endTime != null) {
            sQLiteStatement.bindString(12, endTime);
        }
        Long endTime_long = aDMessage.getEndTime_long();
        if (endTime_long != null) {
            sQLiteStatement.bindLong(13, endTime_long.longValue());
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(DatabaseStatement databaseStatement, ADMessage aDMessage) {
        databaseStatement.clearBindings();
        Long key = aDMessage.getKey();
        if (key != null) {
            databaseStatement.bindLong(1, key.longValue());
        }
        String id = aDMessage.getId();
        if (id != null) {
            databaseStatement.bindString(2, id);
        }
        String no = aDMessage.getNo();
        if (no != null) {
            databaseStatement.bindString(3, no);
        }
        String name = aDMessage.getName();
        if (name != null) {
            databaseStatement.bindString(4, name);
        }
        String fullPath = aDMessage.getFullPath();
        if (fullPath != null) {
            databaseStatement.bindString(5, fullPath);
        }
        String localPath = aDMessage.getLocalPath();
        if (localPath != null) {
            databaseStatement.bindString(6, localPath);
        }
        String deviceId = aDMessage.getDeviceId();
        if (deviceId != null) {
            databaseStatement.bindString(7, deviceId);
        }
        String deviceNo = aDMessage.getDeviceNo();
        if (deviceNo != null) {
            databaseStatement.bindString(8, deviceNo);
        }
        String startTime = aDMessage.getStartTime();
        if (startTime != null) {
            databaseStatement.bindString(9, startTime);
        }
        Long startTime_long = aDMessage.getStartTime_long();
        if (startTime_long != null) {
            databaseStatement.bindLong(10, startTime_long.longValue());
        }
        databaseStatement.bindLong(11, aDMessage.getSort());
        String endTime = aDMessage.getEndTime();
        if (endTime != null) {
            databaseStatement.bindString(12, endTime);
        }
        Long endTime_long = aDMessage.getEndTime_long();
        if (endTime_long != null) {
            databaseStatement.bindLong(13, endTime_long.longValue());
        }
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public Long getKey(ADMessage aDMessage) {
        if (aDMessage != null) {
            return aDMessage.getKey();
        }
        return null;
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public boolean hasKey(ADMessage aDMessage) {
        return aDMessage.getKey() != null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final boolean isEntityUpdateable() {
        return true;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.greenrobot.greendao.AbstractDao
    public ADMessage readEntity(Cursor cursor, int i) {
        int i2 = i + 0;
        int i3 = i + 1;
        int i4 = i + 2;
        int i5 = i + 3;
        int i6 = i + 4;
        int i7 = i + 5;
        int i8 = i + 6;
        int i9 = i + 7;
        int i10 = i + 8;
        int i11 = i + 9;
        int i12 = i + 11;
        int i13 = i + 12;
        return new ADMessage(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)), cursor.isNull(i3) ? null : cursor.getString(i3), cursor.isNull(i4) ? null : cursor.getString(i4), cursor.isNull(i5) ? null : cursor.getString(i5), cursor.isNull(i6) ? null : cursor.getString(i6), cursor.isNull(i7) ? null : cursor.getString(i7), cursor.isNull(i8) ? null : cursor.getString(i8), cursor.isNull(i9) ? null : cursor.getString(i9), cursor.isNull(i10) ? null : cursor.getString(i10), cursor.isNull(i11) ? null : Long.valueOf(cursor.getLong(i11)), cursor.getInt(i + 10), cursor.isNull(i12) ? null : cursor.getString(i12), cursor.isNull(i13) ? null : Long.valueOf(cursor.getLong(i13)));
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public void readEntity(Cursor cursor, ADMessage aDMessage, int i) {
        int i2 = i + 0;
        aDMessage.setKey(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)));
        int i3 = i + 1;
        aDMessage.setId(cursor.isNull(i3) ? null : cursor.getString(i3));
        int i4 = i + 2;
        aDMessage.setNo(cursor.isNull(i4) ? null : cursor.getString(i4));
        int i5 = i + 3;
        aDMessage.setName(cursor.isNull(i5) ? null : cursor.getString(i5));
        int i6 = i + 4;
        aDMessage.setFullPath(cursor.isNull(i6) ? null : cursor.getString(i6));
        int i7 = i + 5;
        aDMessage.setLocalPath(cursor.isNull(i7) ? null : cursor.getString(i7));
        int i8 = i + 6;
        aDMessage.setDeviceId(cursor.isNull(i8) ? null : cursor.getString(i8));
        int i9 = i + 7;
        aDMessage.setDeviceNo(cursor.isNull(i9) ? null : cursor.getString(i9));
        int i10 = i + 8;
        aDMessage.setStartTime(cursor.isNull(i10) ? null : cursor.getString(i10));
        int i11 = i + 9;
        aDMessage.setStartTime_long(cursor.isNull(i11) ? null : Long.valueOf(cursor.getLong(i11)));
        aDMessage.setSort(cursor.getInt(i + 10));
        int i12 = i + 11;
        aDMessage.setEndTime(cursor.isNull(i12) ? null : cursor.getString(i12));
        int i13 = i + 12;
        aDMessage.setEndTime_long(cursor.isNull(i13) ? null : Long.valueOf(cursor.getLong(i13)));
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
    public final Long updateKeyAfterInsert(ADMessage aDMessage, long j) {
        aDMessage.setKey(Long.valueOf(j));
        return Long.valueOf(j);
    }
}
