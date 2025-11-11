package com.yj.coffeemachines.greendao.daos;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.yj.coffeemachines.greendao.beans.OpsActionMessage;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

/* loaded from: classes.dex */
public class OpsActionMessageDao extends AbstractDao<OpsActionMessage, Long> {
    public static final String TABLENAME = "OPS_ACTION_MESSAGE";

    /* loaded from: classes.dex */
    public static class Properties {
        public static final Property Key = new Property(0, Long.class, "key", true, "_id");
        public static final Property Time = new Property(1, Long.class, "time", false, "TIME");
        public static final Property Action = new Property(2, String.class, "action", false, "ACTION");
        public static final Property Step = new Property(3, Integer.TYPE, "step", false, "STEP");
        public static final Property Par0 = new Property(4, String.class, "par0", false, "PAR0");
        public static final Property Par1 = new Property(5, String.class, "par1", false, "PAR1");
        public static final Property Par2 = new Property(6, String.class, "par2", false, "PAR2");
        public static final Property Par3 = new Property(7, String.class, "par3", false, "PAR3");
        public static final Property Par4 = new Property(8, String.class, "par4", false, "PAR4");
        public static final Property Par5 = new Property(9, String.class, "par5", false, "PAR5");
        public static final Property Par6 = new Property(10, String.class, "par6", false, "PAR6");
        public static final Property Par7 = new Property(11, String.class, "par7", false, "PAR7");
        public static final Property Par8 = new Property(12, String.class, "par8", false, "PAR8");
        public static final Property Par9 = new Property(13, String.class, "par9", false, "PAR9");
    }

    public OpsActionMessageDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public OpsActionMessageDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
    }

    public static void createTable(Database database, boolean z) {
        database.execSQL("CREATE TABLE " + (z ? "IF NOT EXISTS " : "") + "\"OPS_ACTION_MESSAGE\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ,\"TIME\" INTEGER,\"ACTION\" TEXT,\"STEP\" INTEGER NOT NULL ,\"PAR0\" TEXT,\"PAR1\" TEXT,\"PAR2\" TEXT,\"PAR3\" TEXT,\"PAR4\" TEXT,\"PAR5\" TEXT,\"PAR6\" TEXT,\"PAR7\" TEXT,\"PAR8\" TEXT,\"PAR9\" TEXT);");
    }

    public static void dropTable(Database database, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("\"OPS_ACTION_MESSAGE\"");
        database.execSQL(sb.toString());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(SQLiteStatement sQLiteStatement, OpsActionMessage opsActionMessage) {
        sQLiteStatement.clearBindings();
        Long key = opsActionMessage.getKey();
        if (key != null) {
            sQLiteStatement.bindLong(1, key.longValue());
        }
        Long time = opsActionMessage.getTime();
        if (time != null) {
            sQLiteStatement.bindLong(2, time.longValue());
        }
        String action = opsActionMessage.getAction();
        if (action != null) {
            sQLiteStatement.bindString(3, action);
        }
        sQLiteStatement.bindLong(4, opsActionMessage.getStep());
        String par0 = opsActionMessage.getPar0();
        if (par0 != null) {
            sQLiteStatement.bindString(5, par0);
        }
        String par1 = opsActionMessage.getPar1();
        if (par1 != null) {
            sQLiteStatement.bindString(6, par1);
        }
        String par2 = opsActionMessage.getPar2();
        if (par2 != null) {
            sQLiteStatement.bindString(7, par2);
        }
        String par3 = opsActionMessage.getPar3();
        if (par3 != null) {
            sQLiteStatement.bindString(8, par3);
        }
        String par4 = opsActionMessage.getPar4();
        if (par4 != null) {
            sQLiteStatement.bindString(9, par4);
        }
        String par5 = opsActionMessage.getPar5();
        if (par5 != null) {
            sQLiteStatement.bindString(10, par5);
        }
        String par6 = opsActionMessage.getPar6();
        if (par6 != null) {
            sQLiteStatement.bindString(11, par6);
        }
        String par7 = opsActionMessage.getPar7();
        if (par7 != null) {
            sQLiteStatement.bindString(12, par7);
        }
        String par8 = opsActionMessage.getPar8();
        if (par8 != null) {
            sQLiteStatement.bindString(13, par8);
        }
        String par9 = opsActionMessage.getPar9();
        if (par9 != null) {
            sQLiteStatement.bindString(14, par9);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(DatabaseStatement databaseStatement, OpsActionMessage opsActionMessage) {
        databaseStatement.clearBindings();
        Long key = opsActionMessage.getKey();
        if (key != null) {
            databaseStatement.bindLong(1, key.longValue());
        }
        Long time = opsActionMessage.getTime();
        if (time != null) {
            databaseStatement.bindLong(2, time.longValue());
        }
        String action = opsActionMessage.getAction();
        if (action != null) {
            databaseStatement.bindString(3, action);
        }
        databaseStatement.bindLong(4, opsActionMessage.getStep());
        String par0 = opsActionMessage.getPar0();
        if (par0 != null) {
            databaseStatement.bindString(5, par0);
        }
        String par1 = opsActionMessage.getPar1();
        if (par1 != null) {
            databaseStatement.bindString(6, par1);
        }
        String par2 = opsActionMessage.getPar2();
        if (par2 != null) {
            databaseStatement.bindString(7, par2);
        }
        String par3 = opsActionMessage.getPar3();
        if (par3 != null) {
            databaseStatement.bindString(8, par3);
        }
        String par4 = opsActionMessage.getPar4();
        if (par4 != null) {
            databaseStatement.bindString(9, par4);
        }
        String par5 = opsActionMessage.getPar5();
        if (par5 != null) {
            databaseStatement.bindString(10, par5);
        }
        String par6 = opsActionMessage.getPar6();
        if (par6 != null) {
            databaseStatement.bindString(11, par6);
        }
        String par7 = opsActionMessage.getPar7();
        if (par7 != null) {
            databaseStatement.bindString(12, par7);
        }
        String par8 = opsActionMessage.getPar8();
        if (par8 != null) {
            databaseStatement.bindString(13, par8);
        }
        String par9 = opsActionMessage.getPar9();
        if (par9 != null) {
            databaseStatement.bindString(14, par9);
        }
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public Long getKey(OpsActionMessage opsActionMessage) {
        if (opsActionMessage != null) {
            return opsActionMessage.getKey();
        }
        return null;
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public boolean hasKey(OpsActionMessage opsActionMessage) {
        return opsActionMessage.getKey() != null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final boolean isEntityUpdateable() {
        return true;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.greenrobot.greendao.AbstractDao
    public OpsActionMessage readEntity(Cursor cursor, int i) {
        int i2 = i + 0;
        int i3 = i + 1;
        int i4 = i + 2;
        int i5 = i + 4;
        int i6 = i + 5;
        int i7 = i + 6;
        int i8 = i + 7;
        int i9 = i + 8;
        int i10 = i + 9;
        int i11 = i + 10;
        int i12 = i + 11;
        int i13 = i + 12;
        int i14 = i + 13;
        return new OpsActionMessage(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)), cursor.isNull(i3) ? null : Long.valueOf(cursor.getLong(i3)), cursor.isNull(i4) ? null : cursor.getString(i4), cursor.getInt(i + 3), cursor.isNull(i5) ? null : cursor.getString(i5), cursor.isNull(i6) ? null : cursor.getString(i6), cursor.isNull(i7) ? null : cursor.getString(i7), cursor.isNull(i8) ? null : cursor.getString(i8), cursor.isNull(i9) ? null : cursor.getString(i9), cursor.isNull(i10) ? null : cursor.getString(i10), cursor.isNull(i11) ? null : cursor.getString(i11), cursor.isNull(i12) ? null : cursor.getString(i12), cursor.isNull(i13) ? null : cursor.getString(i13), cursor.isNull(i14) ? null : cursor.getString(i14));
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public void readEntity(Cursor cursor, OpsActionMessage opsActionMessage, int i) {
        int i2 = i + 0;
        opsActionMessage.setKey(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)));
        int i3 = i + 1;
        opsActionMessage.setTime(cursor.isNull(i3) ? null : Long.valueOf(cursor.getLong(i3)));
        int i4 = i + 2;
        opsActionMessage.setAction(cursor.isNull(i4) ? null : cursor.getString(i4));
        opsActionMessage.setStep(cursor.getInt(i + 3));
        int i5 = i + 4;
        opsActionMessage.setPar0(cursor.isNull(i5) ? null : cursor.getString(i5));
        int i6 = i + 5;
        opsActionMessage.setPar1(cursor.isNull(i6) ? null : cursor.getString(i6));
        int i7 = i + 6;
        opsActionMessage.setPar2(cursor.isNull(i7) ? null : cursor.getString(i7));
        int i8 = i + 7;
        opsActionMessage.setPar3(cursor.isNull(i8) ? null : cursor.getString(i8));
        int i9 = i + 8;
        opsActionMessage.setPar4(cursor.isNull(i9) ? null : cursor.getString(i9));
        int i10 = i + 9;
        opsActionMessage.setPar5(cursor.isNull(i10) ? null : cursor.getString(i10));
        int i11 = i + 10;
        opsActionMessage.setPar6(cursor.isNull(i11) ? null : cursor.getString(i11));
        int i12 = i + 11;
        opsActionMessage.setPar7(cursor.isNull(i12) ? null : cursor.getString(i12));
        int i13 = i + 12;
        opsActionMessage.setPar8(cursor.isNull(i13) ? null : cursor.getString(i13));
        int i14 = i + 13;
        opsActionMessage.setPar9(cursor.isNull(i14) ? null : cursor.getString(i14));
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
    public final Long updateKeyAfterInsert(OpsActionMessage opsActionMessage, long j) {
        opsActionMessage.setKey(Long.valueOf(j));
        return Long.valueOf(j);
    }
}
