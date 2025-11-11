package com.yj.coffeemachines.greendao.daos;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.yj.coffeemachines.greendao.beans.DevOpenAndCloseMessage;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

/* loaded from: classes.dex */
public class DevOpenAndCloseMessageDao extends AbstractDao<DevOpenAndCloseMessage, Long> {
    public static final String TABLENAME = "DEV_OPEN_AND_CLOSE_MESSAGE";

    /* loaded from: classes.dex */
    public static class Properties {
        public static final Property Key = new Property(0, Long.class, "key", true, "_id");
        public static final Property Cycle = new Property(1, String.class, "cycle", false, "CYCLE");
        public static final Property CycleCron = new Property(2, String.class, "cycleCron", false, "CYCLE_CRON");
        public static final Property DeviceId = new Property(3, String.class, "deviceId", false, "DEVICE_ID");
        public static final Property DeviceNo = new Property(4, String.class, "deviceNo", false, "DEVICE_NO");
        public static final Property Id = new Property(5, String.class, "id", false, "ID");
        public static final Property PowerType = new Property(6, Integer.TYPE, "powerType", false, "POWER_TYPE");
        public static final Property SortNo = new Property(7, Integer.TYPE, "sortNo", false, "SORT_NO");
        public static final Property TimeStr = new Property(8, String.class, "timeStr", false, "TIME_STR");
    }

    public DevOpenAndCloseMessageDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public DevOpenAndCloseMessageDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
    }

    public static void createTable(Database database, boolean z) {
        database.execSQL("CREATE TABLE " + (z ? "IF NOT EXISTS " : "") + "\"DEV_OPEN_AND_CLOSE_MESSAGE\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ,\"CYCLE\" TEXT,\"CYCLE_CRON\" TEXT,\"DEVICE_ID\" TEXT,\"DEVICE_NO\" TEXT,\"ID\" TEXT,\"POWER_TYPE\" INTEGER NOT NULL ,\"SORT_NO\" INTEGER NOT NULL ,\"TIME_STR\" TEXT);");
    }

    public static void dropTable(Database database, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("\"DEV_OPEN_AND_CLOSE_MESSAGE\"");
        database.execSQL(sb.toString());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(SQLiteStatement sQLiteStatement, DevOpenAndCloseMessage devOpenAndCloseMessage) {
        sQLiteStatement.clearBindings();
        Long key = devOpenAndCloseMessage.getKey();
        if (key != null) {
            sQLiteStatement.bindLong(1, key.longValue());
        }
        String cycle = devOpenAndCloseMessage.getCycle();
        if (cycle != null) {
            sQLiteStatement.bindString(2, cycle);
        }
        String cycleCron = devOpenAndCloseMessage.getCycleCron();
        if (cycleCron != null) {
            sQLiteStatement.bindString(3, cycleCron);
        }
        String deviceId = devOpenAndCloseMessage.getDeviceId();
        if (deviceId != null) {
            sQLiteStatement.bindString(4, deviceId);
        }
        String deviceNo = devOpenAndCloseMessage.getDeviceNo();
        if (deviceNo != null) {
            sQLiteStatement.bindString(5, deviceNo);
        }
        String id = devOpenAndCloseMessage.getId();
        if (id != null) {
            sQLiteStatement.bindString(6, id);
        }
        sQLiteStatement.bindLong(7, devOpenAndCloseMessage.getPowerType());
        sQLiteStatement.bindLong(8, devOpenAndCloseMessage.getSortNo());
        String timeStr = devOpenAndCloseMessage.getTimeStr();
        if (timeStr != null) {
            sQLiteStatement.bindString(9, timeStr);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(DatabaseStatement databaseStatement, DevOpenAndCloseMessage devOpenAndCloseMessage) {
        databaseStatement.clearBindings();
        Long key = devOpenAndCloseMessage.getKey();
        if (key != null) {
            databaseStatement.bindLong(1, key.longValue());
        }
        String cycle = devOpenAndCloseMessage.getCycle();
        if (cycle != null) {
            databaseStatement.bindString(2, cycle);
        }
        String cycleCron = devOpenAndCloseMessage.getCycleCron();
        if (cycleCron != null) {
            databaseStatement.bindString(3, cycleCron);
        }
        String deviceId = devOpenAndCloseMessage.getDeviceId();
        if (deviceId != null) {
            databaseStatement.bindString(4, deviceId);
        }
        String deviceNo = devOpenAndCloseMessage.getDeviceNo();
        if (deviceNo != null) {
            databaseStatement.bindString(5, deviceNo);
        }
        String id = devOpenAndCloseMessage.getId();
        if (id != null) {
            databaseStatement.bindString(6, id);
        }
        databaseStatement.bindLong(7, devOpenAndCloseMessage.getPowerType());
        databaseStatement.bindLong(8, devOpenAndCloseMessage.getSortNo());
        String timeStr = devOpenAndCloseMessage.getTimeStr();
        if (timeStr != null) {
            databaseStatement.bindString(9, timeStr);
        }
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public Long getKey(DevOpenAndCloseMessage devOpenAndCloseMessage) {
        if (devOpenAndCloseMessage != null) {
            return devOpenAndCloseMessage.getKey();
        }
        return null;
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public boolean hasKey(DevOpenAndCloseMessage devOpenAndCloseMessage) {
        return devOpenAndCloseMessage.getKey() != null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final boolean isEntityUpdateable() {
        return true;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.greenrobot.greendao.AbstractDao
    public DevOpenAndCloseMessage readEntity(Cursor cursor, int i) {
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
        int i8 = cursor.getInt(i + 6);
        int i9 = cursor.getInt(i + 7);
        int i10 = i + 8;
        return new DevOpenAndCloseMessage(valueOf, string, string2, string3, string4, string5, i8, i9, cursor.isNull(i10) ? null : cursor.getString(i10));
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public void readEntity(Cursor cursor, DevOpenAndCloseMessage devOpenAndCloseMessage, int i) {
        int i2 = i + 0;
        devOpenAndCloseMessage.setKey(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)));
        int i3 = i + 1;
        devOpenAndCloseMessage.setCycle(cursor.isNull(i3) ? null : cursor.getString(i3));
        int i4 = i + 2;
        devOpenAndCloseMessage.setCycleCron(cursor.isNull(i4) ? null : cursor.getString(i4));
        int i5 = i + 3;
        devOpenAndCloseMessage.setDeviceId(cursor.isNull(i5) ? null : cursor.getString(i5));
        int i6 = i + 4;
        devOpenAndCloseMessage.setDeviceNo(cursor.isNull(i6) ? null : cursor.getString(i6));
        int i7 = i + 5;
        devOpenAndCloseMessage.setId(cursor.isNull(i7) ? null : cursor.getString(i7));
        devOpenAndCloseMessage.setPowerType(cursor.getInt(i + 6));
        devOpenAndCloseMessage.setSortNo(cursor.getInt(i + 7));
        int i8 = i + 8;
        devOpenAndCloseMessage.setTimeStr(cursor.isNull(i8) ? null : cursor.getString(i8));
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
    public final Long updateKeyAfterInsert(DevOpenAndCloseMessage devOpenAndCloseMessage, long j) {
        devOpenAndCloseMessage.setKey(Long.valueOf(j));
        return Long.valueOf(j);
    }
}
