package com.yj.coffeemachines.greendao.daos;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.yj.coffeemachines.greendao.beans.MakeDrinkMessage;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

/* loaded from: classes.dex */
public class MakeDrinkMessageDao extends AbstractDao<MakeDrinkMessage, Long> {
    public static final String TABLENAME = "MAKE_DRINK_MESSAGE";

    /* loaded from: classes.dex */
    public static class Properties {
        public static final Property Key = new Property(0, Long.class, "key", true, "_id");
        public static final Property Time = new Property(1, Long.class, "time", false, "TIME");
        public static final Property PayWay = new Property(2, Integer.TYPE, "payWay", false, "PAY_WAY");
        public static final Property PayNumber = new Property(3, Double.TYPE, "payNumber", false, "PAY_NUMBER");
        public static final Property ChangeNumber = new Property(4, Double.TYPE, "changeNumber", false, "CHANGE_NUMBER");
        public static final Property ProductPrice = new Property(5, Double.TYPE, "productPrice", false, "PRODUCT_PRICE");
        public static final Property IsExchange = new Property(6, Boolean.TYPE, "isExchange", false, "IS_EXCHANGE");
        public static final Property OrderMessage = new Property(7, String.class, "orderMessage", false, "ORDER_MESSAGE");
        public static final Property Par0 = new Property(8, String.class, "par0", false, "PAR0");
        public static final Property Par1 = new Property(9, String.class, "par1", false, "PAR1");
        public static final Property Par2 = new Property(10, String.class, "par2", false, "PAR2");
        public static final Property Par3 = new Property(11, String.class, "par3", false, "PAR3");
        public static final Property Par4 = new Property(12, String.class, "par4", false, "PAR4");
        public static final Property Par5 = new Property(13, String.class, "par5", false, "PAR5");
        public static final Property Par6 = new Property(14, String.class, "par6", false, "PAR6");
        public static final Property Par7 = new Property(15, String.class, "par7", false, "PAR7");
        public static final Property Par8 = new Property(16, String.class, "par8", false, "PAR8");
        public static final Property Par9 = new Property(17, String.class, "par9", false, "PAR9");
    }

    public MakeDrinkMessageDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public MakeDrinkMessageDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
    }

    public static void createTable(Database database, boolean z) {
        database.execSQL("CREATE TABLE " + (z ? "IF NOT EXISTS " : "") + "\"MAKE_DRINK_MESSAGE\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ,\"TIME\" INTEGER,\"PAY_WAY\" INTEGER NOT NULL ,\"PAY_NUMBER\" REAL NOT NULL ,\"CHANGE_NUMBER\" REAL NOT NULL ,\"PRODUCT_PRICE\" REAL NOT NULL ,\"IS_EXCHANGE\" INTEGER NOT NULL ,\"ORDER_MESSAGE\" TEXT,\"PAR0\" TEXT,\"PAR1\" TEXT,\"PAR2\" TEXT,\"PAR3\" TEXT,\"PAR4\" TEXT,\"PAR5\" TEXT,\"PAR6\" TEXT,\"PAR7\" TEXT,\"PAR8\" TEXT,\"PAR9\" TEXT);");
    }

    public static void dropTable(Database database, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("\"MAKE_DRINK_MESSAGE\"");
        database.execSQL(sb.toString());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(SQLiteStatement sQLiteStatement, MakeDrinkMessage makeDrinkMessage) {
        sQLiteStatement.clearBindings();
        Long key = makeDrinkMessage.getKey();
        if (key != null) {
            sQLiteStatement.bindLong(1, key.longValue());
        }
        Long time = makeDrinkMessage.getTime();
        if (time != null) {
            sQLiteStatement.bindLong(2, time.longValue());
        }
        sQLiteStatement.bindLong(3, makeDrinkMessage.getPayWay());
        sQLiteStatement.bindDouble(4, makeDrinkMessage.getPayNumber());
        sQLiteStatement.bindDouble(5, makeDrinkMessage.getChangeNumber());
        sQLiteStatement.bindDouble(6, makeDrinkMessage.getProductPrice());
        sQLiteStatement.bindLong(7, makeDrinkMessage.getIsExchange() ? 1L : 0L);
        String orderMessage = makeDrinkMessage.getOrderMessage();
        if (orderMessage != null) {
            sQLiteStatement.bindString(8, orderMessage);
        }
        String par0 = makeDrinkMessage.getPar0();
        if (par0 != null) {
            sQLiteStatement.bindString(9, par0);
        }
        String par1 = makeDrinkMessage.getPar1();
        if (par1 != null) {
            sQLiteStatement.bindString(10, par1);
        }
        String par2 = makeDrinkMessage.getPar2();
        if (par2 != null) {
            sQLiteStatement.bindString(11, par2);
        }
        String par3 = makeDrinkMessage.getPar3();
        if (par3 != null) {
            sQLiteStatement.bindString(12, par3);
        }
        String par4 = makeDrinkMessage.getPar4();
        if (par4 != null) {
            sQLiteStatement.bindString(13, par4);
        }
        String par5 = makeDrinkMessage.getPar5();
        if (par5 != null) {
            sQLiteStatement.bindString(14, par5);
        }
        String par6 = makeDrinkMessage.getPar6();
        if (par6 != null) {
            sQLiteStatement.bindString(15, par6);
        }
        String par7 = makeDrinkMessage.getPar7();
        if (par7 != null) {
            sQLiteStatement.bindString(16, par7);
        }
        String par8 = makeDrinkMessage.getPar8();
        if (par8 != null) {
            sQLiteStatement.bindString(17, par8);
        }
        String par9 = makeDrinkMessage.getPar9();
        if (par9 != null) {
            sQLiteStatement.bindString(18, par9);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(DatabaseStatement databaseStatement, MakeDrinkMessage makeDrinkMessage) {
        databaseStatement.clearBindings();
        Long key = makeDrinkMessage.getKey();
        if (key != null) {
            databaseStatement.bindLong(1, key.longValue());
        }
        Long time = makeDrinkMessage.getTime();
        if (time != null) {
            databaseStatement.bindLong(2, time.longValue());
        }
        databaseStatement.bindLong(3, makeDrinkMessage.getPayWay());
        databaseStatement.bindDouble(4, makeDrinkMessage.getPayNumber());
        databaseStatement.bindDouble(5, makeDrinkMessage.getChangeNumber());
        databaseStatement.bindDouble(6, makeDrinkMessage.getProductPrice());
        databaseStatement.bindLong(7, makeDrinkMessage.getIsExchange() ? 1L : 0L);
        String orderMessage = makeDrinkMessage.getOrderMessage();
        if (orderMessage != null) {
            databaseStatement.bindString(8, orderMessage);
        }
        String par0 = makeDrinkMessage.getPar0();
        if (par0 != null) {
            databaseStatement.bindString(9, par0);
        }
        String par1 = makeDrinkMessage.getPar1();
        if (par1 != null) {
            databaseStatement.bindString(10, par1);
        }
        String par2 = makeDrinkMessage.getPar2();
        if (par2 != null) {
            databaseStatement.bindString(11, par2);
        }
        String par3 = makeDrinkMessage.getPar3();
        if (par3 != null) {
            databaseStatement.bindString(12, par3);
        }
        String par4 = makeDrinkMessage.getPar4();
        if (par4 != null) {
            databaseStatement.bindString(13, par4);
        }
        String par5 = makeDrinkMessage.getPar5();
        if (par5 != null) {
            databaseStatement.bindString(14, par5);
        }
        String par6 = makeDrinkMessage.getPar6();
        if (par6 != null) {
            databaseStatement.bindString(15, par6);
        }
        String par7 = makeDrinkMessage.getPar7();
        if (par7 != null) {
            databaseStatement.bindString(16, par7);
        }
        String par8 = makeDrinkMessage.getPar8();
        if (par8 != null) {
            databaseStatement.bindString(17, par8);
        }
        String par9 = makeDrinkMessage.getPar9();
        if (par9 != null) {
            databaseStatement.bindString(18, par9);
        }
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public Long getKey(MakeDrinkMessage makeDrinkMessage) {
        if (makeDrinkMessage != null) {
            return makeDrinkMessage.getKey();
        }
        return null;
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public boolean hasKey(MakeDrinkMessage makeDrinkMessage) {
        return makeDrinkMessage.getKey() != null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final boolean isEntityUpdateable() {
        return true;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.greenrobot.greendao.AbstractDao
    public MakeDrinkMessage readEntity(Cursor cursor, int i) {
        int i2 = i + 0;
        Long valueOf = cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2));
        int i3 = i + 1;
        Long valueOf2 = cursor.isNull(i3) ? null : Long.valueOf(cursor.getLong(i3));
        int i4 = cursor.getInt(i + 2);
        double d = cursor.getDouble(i + 3);
        double d2 = cursor.getDouble(i + 4);
        double d3 = cursor.getDouble(i + 5);
        boolean z = cursor.getShort(i + 6) != 0;
        int i5 = i + 7;
        String string = cursor.isNull(i5) ? null : cursor.getString(i5);
        int i6 = i + 8;
        String string2 = cursor.isNull(i6) ? null : cursor.getString(i6);
        int i7 = i + 9;
        String string3 = cursor.isNull(i7) ? null : cursor.getString(i7);
        int i8 = i + 10;
        String string4 = cursor.isNull(i8) ? null : cursor.getString(i8);
        int i9 = i + 11;
        String string5 = cursor.isNull(i9) ? null : cursor.getString(i9);
        int i10 = i + 12;
        String string6 = cursor.isNull(i10) ? null : cursor.getString(i10);
        int i11 = i + 13;
        String string7 = cursor.isNull(i11) ? null : cursor.getString(i11);
        int i12 = i + 14;
        String string8 = cursor.isNull(i12) ? null : cursor.getString(i12);
        int i13 = i + 15;
        String string9 = cursor.isNull(i13) ? null : cursor.getString(i13);
        int i14 = i + 16;
        String string10 = cursor.isNull(i14) ? null : cursor.getString(i14);
        int i15 = i + 17;
        return new MakeDrinkMessage(valueOf, valueOf2, i4, d, d2, d3, z, string, string2, string3, string4, string5, string6, string7, string8, string9, string10, cursor.isNull(i15) ? null : cursor.getString(i15));
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public void readEntity(Cursor cursor, MakeDrinkMessage makeDrinkMessage, int i) {
        int i2 = i + 0;
        makeDrinkMessage.setKey(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)));
        int i3 = i + 1;
        makeDrinkMessage.setTime(cursor.isNull(i3) ? null : Long.valueOf(cursor.getLong(i3)));
        makeDrinkMessage.setPayWay(cursor.getInt(i + 2));
        makeDrinkMessage.setPayNumber(cursor.getDouble(i + 3));
        makeDrinkMessage.setChangeNumber(cursor.getDouble(i + 4));
        makeDrinkMessage.setProductPrice(cursor.getDouble(i + 5));
        makeDrinkMessage.setIsExchange(cursor.getShort(i + 6) != 0);
        int i4 = i + 7;
        makeDrinkMessage.setOrderMessage(cursor.isNull(i4) ? null : cursor.getString(i4));
        int i5 = i + 8;
        makeDrinkMessage.setPar0(cursor.isNull(i5) ? null : cursor.getString(i5));
        int i6 = i + 9;
        makeDrinkMessage.setPar1(cursor.isNull(i6) ? null : cursor.getString(i6));
        int i7 = i + 10;
        makeDrinkMessage.setPar2(cursor.isNull(i7) ? null : cursor.getString(i7));
        int i8 = i + 11;
        makeDrinkMessage.setPar3(cursor.isNull(i8) ? null : cursor.getString(i8));
        int i9 = i + 12;
        makeDrinkMessage.setPar4(cursor.isNull(i9) ? null : cursor.getString(i9));
        int i10 = i + 13;
        makeDrinkMessage.setPar5(cursor.isNull(i10) ? null : cursor.getString(i10));
        int i11 = i + 14;
        makeDrinkMessage.setPar6(cursor.isNull(i11) ? null : cursor.getString(i11));
        int i12 = i + 15;
        makeDrinkMessage.setPar7(cursor.isNull(i12) ? null : cursor.getString(i12));
        int i13 = i + 16;
        makeDrinkMessage.setPar8(cursor.isNull(i13) ? null : cursor.getString(i13));
        int i14 = i + 17;
        makeDrinkMessage.setPar9(cursor.isNull(i14) ? null : cursor.getString(i14));
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
    public final Long updateKeyAfterInsert(MakeDrinkMessage makeDrinkMessage, long j) {
        makeDrinkMessage.setKey(Long.valueOf(j));
        return Long.valueOf(j);
    }
}
