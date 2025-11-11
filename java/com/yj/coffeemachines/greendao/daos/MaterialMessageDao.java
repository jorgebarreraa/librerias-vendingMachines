package com.yj.coffeemachines.greendao.daos;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import androidx.core.app.NotificationCompat;
import com.yj.coffeemachines.greendao.beans.MaterialMessage;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

/* loaded from: classes.dex */
public class MaterialMessageDao extends AbstractDao<MaterialMessage, Long> {
    public static final String TABLENAME = "MATERIAL_MESSAGE";

    /* loaded from: classes.dex */
    public static class Properties {
        public static final Property Key = new Property(0, Long.class, "key", true, "_id");
        public static final Property CreateUser = new Property(1, String.class, "createUser", false, "CREATE_USER");
        public static final Property CreateTime = new Property(2, String.class, "createTime", false, "CREATE_TIME");
        public static final Property UpdateUser = new Property(3, String.class, "updateUser", false, "UPDATE_USER");
        public static final Property UpdateTime = new Property(4, String.class, "updateTime", false, "UPDATE_TIME");
        public static final Property Status = new Property(5, Integer.TYPE, NotificationCompat.CATEGORY_STATUS, false, "STATUS");
        public static final Property IsDeleted = new Property(6, Integer.TYPE, "isDeleted", false, "IS_DELETED");
        public static final Property Id = new Property(7, String.class, "id", false, "ID");
        public static final Property DeviceTypeId = new Property(8, String.class, "deviceTypeId", false, "DEVICE_TYPE_ID");
        public static final Property No = new Property(9, String.class, "no", false, "NO");
        public static final Property RawType = new Property(10, String.class, "rawType", false, "RAW_TYPE");
        public static final Property Name = new Property(11, String.class, "name", false, "NAME");
        public static final Property Capacity = new Property(12, Double.class, "capacity", false, "CAPACITY");
        public static final Property Position = new Property(13, String.class, "position", false, "POSITION");
        public static final Property Unit = new Property(14, String.class, "unit", false, "UNIT");
        public static final Property ExpendRate = new Property(15, Double.class, "expendRate", false, "EXPEND_RATE");
        public static final Property WarnCapacity = new Property(16, Double.class, "warnCapacity", false, "WARN_CAPACITY");
        public static final Property SpeedTime = new Property(17, Integer.TYPE, "speedTime", false, "SPEED_TIME");
        public static final Property SpeedTimeUnit = new Property(18, Integer.TYPE, "speedTimeUnit", false, "SPEED_TIME_UNIT");
        public static final Property SpeedUnit = new Property(19, String.class, "speedUnit", false, "SPEED_UNIT");
        public static final Property DeviceTypeName = new Property(20, String.class, "deviceTypeName", false, "DEVICE_TYPE_NAME");
        public static final Property LocaName = new Property(21, String.class, "locaName", false, "LOCA_NAME");
        public static final Property Ischange = new Property(22, Boolean.TYPE, "ischange", false, "ISCHANGE");
        public static final Property MicRate = new Property(23, Integer.TYPE, "micRate", false, "MIC_RATE");
        public static final Property LocalFineTuning_cold = new Property(24, Integer.TYPE, "localFineTuning_cold", false, "LOCAL_FINE_TUNING_COLD");
        public static final Property LocalFineTuning_hot = new Property(25, Integer.TYPE, "localFineTuning_hot", false, "LOCAL_FINE_TUNING_HOT");
        public static final Property LocalMaterialFineTuning = new Property(26, Integer.TYPE, "localMaterialFineTuning", false, "LOCAL_MATERIAL_FINE_TUNING");
        public static final Property LocalIsSuger = new Property(27, Boolean.TYPE, "localIsSuger", false, "LOCAL_IS_SUGER");
    }

    public MaterialMessageDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public MaterialMessageDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
    }

    public static void createTable(Database database, boolean z) {
        database.execSQL("CREATE TABLE " + (z ? "IF NOT EXISTS " : "") + "\"MATERIAL_MESSAGE\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ,\"CREATE_USER\" TEXT,\"CREATE_TIME\" TEXT,\"UPDATE_USER\" TEXT,\"UPDATE_TIME\" TEXT,\"STATUS\" INTEGER NOT NULL ,\"IS_DELETED\" INTEGER NOT NULL ,\"ID\" TEXT UNIQUE ,\"DEVICE_TYPE_ID\" TEXT,\"NO\" TEXT,\"RAW_TYPE\" TEXT,\"NAME\" TEXT,\"CAPACITY\" REAL,\"POSITION\" TEXT,\"UNIT\" TEXT,\"EXPEND_RATE\" REAL,\"WARN_CAPACITY\" REAL,\"SPEED_TIME\" INTEGER NOT NULL ,\"SPEED_TIME_UNIT\" INTEGER NOT NULL ,\"SPEED_UNIT\" TEXT,\"DEVICE_TYPE_NAME\" TEXT,\"LOCA_NAME\" TEXT,\"ISCHANGE\" INTEGER NOT NULL ,\"MIC_RATE\" INTEGER NOT NULL ,\"LOCAL_FINE_TUNING_COLD\" INTEGER NOT NULL ,\"LOCAL_FINE_TUNING_HOT\" INTEGER NOT NULL ,\"LOCAL_MATERIAL_FINE_TUNING\" INTEGER NOT NULL ,\"LOCAL_IS_SUGER\" INTEGER NOT NULL );");
    }

    public static void dropTable(Database database, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("\"MATERIAL_MESSAGE\"");
        database.execSQL(sb.toString());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(SQLiteStatement sQLiteStatement, MaterialMessage materialMessage) {
        sQLiteStatement.clearBindings();
        Long key = materialMessage.getKey();
        if (key != null) {
            sQLiteStatement.bindLong(1, key.longValue());
        }
        String createUser = materialMessage.getCreateUser();
        if (createUser != null) {
            sQLiteStatement.bindString(2, createUser);
        }
        String createTime = materialMessage.getCreateTime();
        if (createTime != null) {
            sQLiteStatement.bindString(3, createTime);
        }
        String updateUser = materialMessage.getUpdateUser();
        if (updateUser != null) {
            sQLiteStatement.bindString(4, updateUser);
        }
        String updateTime = materialMessage.getUpdateTime();
        if (updateTime != null) {
            sQLiteStatement.bindString(5, updateTime);
        }
        sQLiteStatement.bindLong(6, materialMessage.getStatus());
        sQLiteStatement.bindLong(7, materialMessage.getIsDeleted());
        String id = materialMessage.getId();
        if (id != null) {
            sQLiteStatement.bindString(8, id);
        }
        String deviceTypeId = materialMessage.getDeviceTypeId();
        if (deviceTypeId != null) {
            sQLiteStatement.bindString(9, deviceTypeId);
        }
        String no = materialMessage.getNo();
        if (no != null) {
            sQLiteStatement.bindString(10, no);
        }
        String rawType = materialMessage.getRawType();
        if (rawType != null) {
            sQLiteStatement.bindString(11, rawType);
        }
        String name = materialMessage.getName();
        if (name != null) {
            sQLiteStatement.bindString(12, name);
        }
        Double capacity = materialMessage.getCapacity();
        if (capacity != null) {
            sQLiteStatement.bindDouble(13, capacity.doubleValue());
        }
        String position = materialMessage.getPosition();
        if (position != null) {
            sQLiteStatement.bindString(14, position);
        }
        String unit = materialMessage.getUnit();
        if (unit != null) {
            sQLiteStatement.bindString(15, unit);
        }
        Double expendRate = materialMessage.getExpendRate();
        if (expendRate != null) {
            sQLiteStatement.bindDouble(16, expendRate.doubleValue());
        }
        Double warnCapacity = materialMessage.getWarnCapacity();
        if (warnCapacity != null) {
            sQLiteStatement.bindDouble(17, warnCapacity.doubleValue());
        }
        sQLiteStatement.bindLong(18, materialMessage.getSpeedTime());
        sQLiteStatement.bindLong(19, materialMessage.getSpeedTimeUnit());
        String speedUnit = materialMessage.getSpeedUnit();
        if (speedUnit != null) {
            sQLiteStatement.bindString(20, speedUnit);
        }
        String deviceTypeName = materialMessage.getDeviceTypeName();
        if (deviceTypeName != null) {
            sQLiteStatement.bindString(21, deviceTypeName);
        }
        String locaName = materialMessage.getLocaName();
        if (locaName != null) {
            sQLiteStatement.bindString(22, locaName);
        }
        sQLiteStatement.bindLong(23, materialMessage.getIschange() ? 1L : 0L);
        sQLiteStatement.bindLong(24, materialMessage.getMicRate());
        sQLiteStatement.bindLong(25, materialMessage.getLocalFineTuning_cold());
        sQLiteStatement.bindLong(26, materialMessage.getLocalFineTuning_hot());
        sQLiteStatement.bindLong(27, materialMessage.getLocalMaterialFineTuning());
        sQLiteStatement.bindLong(28, materialMessage.getLocalIsSuger() ? 1L : 0L);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(DatabaseStatement databaseStatement, MaterialMessage materialMessage) {
        databaseStatement.clearBindings();
        Long key = materialMessage.getKey();
        if (key != null) {
            databaseStatement.bindLong(1, key.longValue());
        }
        String createUser = materialMessage.getCreateUser();
        if (createUser != null) {
            databaseStatement.bindString(2, createUser);
        }
        String createTime = materialMessage.getCreateTime();
        if (createTime != null) {
            databaseStatement.bindString(3, createTime);
        }
        String updateUser = materialMessage.getUpdateUser();
        if (updateUser != null) {
            databaseStatement.bindString(4, updateUser);
        }
        String updateTime = materialMessage.getUpdateTime();
        if (updateTime != null) {
            databaseStatement.bindString(5, updateTime);
        }
        databaseStatement.bindLong(6, materialMessage.getStatus());
        databaseStatement.bindLong(7, materialMessage.getIsDeleted());
        String id = materialMessage.getId();
        if (id != null) {
            databaseStatement.bindString(8, id);
        }
        String deviceTypeId = materialMessage.getDeviceTypeId();
        if (deviceTypeId != null) {
            databaseStatement.bindString(9, deviceTypeId);
        }
        String no = materialMessage.getNo();
        if (no != null) {
            databaseStatement.bindString(10, no);
        }
        String rawType = materialMessage.getRawType();
        if (rawType != null) {
            databaseStatement.bindString(11, rawType);
        }
        String name = materialMessage.getName();
        if (name != null) {
            databaseStatement.bindString(12, name);
        }
        Double capacity = materialMessage.getCapacity();
        if (capacity != null) {
            databaseStatement.bindDouble(13, capacity.doubleValue());
        }
        String position = materialMessage.getPosition();
        if (position != null) {
            databaseStatement.bindString(14, position);
        }
        String unit = materialMessage.getUnit();
        if (unit != null) {
            databaseStatement.bindString(15, unit);
        }
        Double expendRate = materialMessage.getExpendRate();
        if (expendRate != null) {
            databaseStatement.bindDouble(16, expendRate.doubleValue());
        }
        Double warnCapacity = materialMessage.getWarnCapacity();
        if (warnCapacity != null) {
            databaseStatement.bindDouble(17, warnCapacity.doubleValue());
        }
        databaseStatement.bindLong(18, materialMessage.getSpeedTime());
        databaseStatement.bindLong(19, materialMessage.getSpeedTimeUnit());
        String speedUnit = materialMessage.getSpeedUnit();
        if (speedUnit != null) {
            databaseStatement.bindString(20, speedUnit);
        }
        String deviceTypeName = materialMessage.getDeviceTypeName();
        if (deviceTypeName != null) {
            databaseStatement.bindString(21, deviceTypeName);
        }
        String locaName = materialMessage.getLocaName();
        if (locaName != null) {
            databaseStatement.bindString(22, locaName);
        }
        databaseStatement.bindLong(23, materialMessage.getIschange() ? 1L : 0L);
        databaseStatement.bindLong(24, materialMessage.getMicRate());
        databaseStatement.bindLong(25, materialMessage.getLocalFineTuning_cold());
        databaseStatement.bindLong(26, materialMessage.getLocalFineTuning_hot());
        databaseStatement.bindLong(27, materialMessage.getLocalMaterialFineTuning());
        databaseStatement.bindLong(28, materialMessage.getLocalIsSuger() ? 1L : 0L);
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public Long getKey(MaterialMessage materialMessage) {
        if (materialMessage != null) {
            return materialMessage.getKey();
        }
        return null;
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public boolean hasKey(MaterialMessage materialMessage) {
        return materialMessage.getKey() != null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final boolean isEntityUpdateable() {
        return true;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.greenrobot.greendao.AbstractDao
    public MaterialMessage readEntity(Cursor cursor, int i) {
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
        int i7 = cursor.getInt(i + 5);
        int i8 = cursor.getInt(i + 6);
        int i9 = i + 7;
        String string5 = cursor.isNull(i9) ? null : cursor.getString(i9);
        int i10 = i + 8;
        String string6 = cursor.isNull(i10) ? null : cursor.getString(i10);
        int i11 = i + 9;
        String string7 = cursor.isNull(i11) ? null : cursor.getString(i11);
        int i12 = i + 10;
        String string8 = cursor.isNull(i12) ? null : cursor.getString(i12);
        int i13 = i + 11;
        String string9 = cursor.isNull(i13) ? null : cursor.getString(i13);
        int i14 = i + 12;
        Double valueOf2 = cursor.isNull(i14) ? null : Double.valueOf(cursor.getDouble(i14));
        int i15 = i + 13;
        String string10 = cursor.isNull(i15) ? null : cursor.getString(i15);
        int i16 = i + 14;
        String string11 = cursor.isNull(i16) ? null : cursor.getString(i16);
        int i17 = i + 15;
        Double valueOf3 = cursor.isNull(i17) ? null : Double.valueOf(cursor.getDouble(i17));
        int i18 = i + 16;
        Double valueOf4 = cursor.isNull(i18) ? null : Double.valueOf(cursor.getDouble(i18));
        int i19 = cursor.getInt(i + 17);
        int i20 = cursor.getInt(i + 18);
        int i21 = i + 19;
        String string12 = cursor.isNull(i21) ? null : cursor.getString(i21);
        int i22 = i + 20;
        String string13 = cursor.isNull(i22) ? null : cursor.getString(i22);
        int i23 = i + 21;
        return new MaterialMessage(valueOf, string, string2, string3, string4, i7, i8, string5, string6, string7, string8, string9, valueOf2, string10, string11, valueOf3, valueOf4, i19, i20, string12, string13, cursor.isNull(i23) ? null : cursor.getString(i23), cursor.getShort(i + 22) != 0, cursor.getInt(i + 23), cursor.getInt(i + 24), cursor.getInt(i + 25), cursor.getInt(i + 26), cursor.getShort(i + 27) != 0);
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public void readEntity(Cursor cursor, MaterialMessage materialMessage, int i) {
        int i2 = i + 0;
        materialMessage.setKey(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)));
        int i3 = i + 1;
        materialMessage.setCreateUser(cursor.isNull(i3) ? null : cursor.getString(i3));
        int i4 = i + 2;
        materialMessage.setCreateTime(cursor.isNull(i4) ? null : cursor.getString(i4));
        int i5 = i + 3;
        materialMessage.setUpdateUser(cursor.isNull(i5) ? null : cursor.getString(i5));
        int i6 = i + 4;
        materialMessage.setUpdateTime(cursor.isNull(i6) ? null : cursor.getString(i6));
        materialMessage.setStatus(cursor.getInt(i + 5));
        materialMessage.setIsDeleted(cursor.getInt(i + 6));
        int i7 = i + 7;
        materialMessage.setId(cursor.isNull(i7) ? null : cursor.getString(i7));
        int i8 = i + 8;
        materialMessage.setDeviceTypeId(cursor.isNull(i8) ? null : cursor.getString(i8));
        int i9 = i + 9;
        materialMessage.setNo(cursor.isNull(i9) ? null : cursor.getString(i9));
        int i10 = i + 10;
        materialMessage.setRawType(cursor.isNull(i10) ? null : cursor.getString(i10));
        int i11 = i + 11;
        materialMessage.setName(cursor.isNull(i11) ? null : cursor.getString(i11));
        int i12 = i + 12;
        materialMessage.setCapacity(cursor.isNull(i12) ? null : Double.valueOf(cursor.getDouble(i12)));
        int i13 = i + 13;
        materialMessage.setPosition(cursor.isNull(i13) ? null : cursor.getString(i13));
        int i14 = i + 14;
        materialMessage.setUnit(cursor.isNull(i14) ? null : cursor.getString(i14));
        int i15 = i + 15;
        materialMessage.setExpendRate(cursor.isNull(i15) ? null : Double.valueOf(cursor.getDouble(i15)));
        int i16 = i + 16;
        materialMessage.setWarnCapacity(cursor.isNull(i16) ? null : Double.valueOf(cursor.getDouble(i16)));
        materialMessage.setSpeedTime(cursor.getInt(i + 17));
        materialMessage.setSpeedTimeUnit(cursor.getInt(i + 18));
        int i17 = i + 19;
        materialMessage.setSpeedUnit(cursor.isNull(i17) ? null : cursor.getString(i17));
        int i18 = i + 20;
        materialMessage.setDeviceTypeName(cursor.isNull(i18) ? null : cursor.getString(i18));
        int i19 = i + 21;
        materialMessage.setLocaName(cursor.isNull(i19) ? null : cursor.getString(i19));
        materialMessage.setIschange(cursor.getShort(i + 22) != 0);
        materialMessage.setMicRate(cursor.getInt(i + 23));
        materialMessage.setLocalFineTuning_cold(cursor.getInt(i + 24));
        materialMessage.setLocalFineTuning_hot(cursor.getInt(i + 25));
        materialMessage.setLocalMaterialFineTuning(cursor.getInt(i + 26));
        materialMessage.setLocalIsSuger(cursor.getShort(i + 27) != 0);
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
    public final Long updateKeyAfterInsert(MaterialMessage materialMessage, long j) {
        materialMessage.setKey(Long.valueOf(j));
        return Long.valueOf(j);
    }
}
