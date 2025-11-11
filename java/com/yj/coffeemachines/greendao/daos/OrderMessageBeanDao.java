package com.yj.coffeemachines.greendao.daos;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.yj.coffeemachines.greendao.beans.OrderMessageBean;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

/* loaded from: classes.dex */
public class OrderMessageBeanDao extends AbstractDao<OrderMessageBean, Long> {
    public static final String TABLENAME = "ORDER_MESSAGE_BEAN";

    /* loaded from: classes.dex */
    public static class Properties {
        public static final Property Id = new Property(0, Long.class, "id", true, "_id");
        public static final Property SerialName = new Property(1, String.class, "serialName", false, "SERIAL_NAME");
        public static final Property Type = new Property(2, Integer.TYPE, "type", false, "TYPE");
        public static final Property Time = new Property(3, String.class, "time", false, "TIME");
        public static final Property Message = new Property(4, String.class, "message", false, "MESSAGE");
        public static final Property Timestamp = new Property(5, Long.class, "timestamp", false, "TIMESTAMP");
        public static final Property Par1 = new Property(6, String.class, "par1", false, "PAR1");
        public static final Property Par2 = new Property(7, String.class, "par2", false, "PAR2");
        public static final Property Par3 = new Property(8, String.class, "par3", false, "PAR3");
        public static final Property Par4 = new Property(9, String.class, "par4", false, "PAR4");
        public static final Property Par5 = new Property(10, String.class, "par5", false, "PAR5");
    }

    public OrderMessageBeanDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public OrderMessageBeanDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
    }

    public static void createTable(Database database, boolean z) {
        database.execSQL("CREATE TABLE " + (z ? "IF NOT EXISTS " : "") + "\"ORDER_MESSAGE_BEAN\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ,\"SERIAL_NAME\" TEXT,\"TYPE\" INTEGER NOT NULL ,\"TIME\" TEXT,\"MESSAGE\" TEXT,\"TIMESTAMP\" INTEGER,\"PAR1\" TEXT,\"PAR2\" TEXT,\"PAR3\" TEXT,\"PAR4\" TEXT,\"PAR5\" TEXT);");
    }

    public static void dropTable(Database database, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("\"ORDER_MESSAGE_BEAN\"");
        database.execSQL(sb.toString());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(SQLiteStatement sQLiteStatement, OrderMessageBean orderMessageBean) {
        sQLiteStatement.clearBindings();
        Long id = orderMessageBean.getId();
        if (id != null) {
            sQLiteStatement.bindLong(1, id.longValue());
        }
        String serialName = orderMessageBean.getSerialName();
        if (serialName != null) {
            sQLiteStatement.bindString(2, serialName);
        }
        sQLiteStatement.bindLong(3, orderMessageBean.getType());
        String time = orderMessageBean.getTime();
        if (time != null) {
            sQLiteStatement.bindString(4, time);
        }
        String message = orderMessageBean.getMessage();
        if (message != null) {
            sQLiteStatement.bindString(5, message);
        }
        Long timestamp = orderMessageBean.getTimestamp();
        if (timestamp != null) {
            sQLiteStatement.bindLong(6, timestamp.longValue());
        }
        String par1 = orderMessageBean.getPar1();
        if (par1 != null) {
            sQLiteStatement.bindString(7, par1);
        }
        String par2 = orderMessageBean.getPar2();
        if (par2 != null) {
            sQLiteStatement.bindString(8, par2);
        }
        String par3 = orderMessageBean.getPar3();
        if (par3 != null) {
            sQLiteStatement.bindString(9, par3);
        }
        String par4 = orderMessageBean.getPar4();
        if (par4 != null) {
            sQLiteStatement.bindString(10, par4);
        }
        String par5 = orderMessageBean.getPar5();
        if (par5 != null) {
            sQLiteStatement.bindString(11, par5);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(DatabaseStatement databaseStatement, OrderMessageBean orderMessageBean) {
        databaseStatement.clearBindings();
        Long id = orderMessageBean.getId();
        if (id != null) {
            databaseStatement.bindLong(1, id.longValue());
        }
        String serialName = orderMessageBean.getSerialName();
        if (serialName != null) {
            databaseStatement.bindString(2, serialName);
        }
        databaseStatement.bindLong(3, orderMessageBean.getType());
        String time = orderMessageBean.getTime();
        if (time != null) {
            databaseStatement.bindString(4, time);
        }
        String message = orderMessageBean.getMessage();
        if (message != null) {
            databaseStatement.bindString(5, message);
        }
        Long timestamp = orderMessageBean.getTimestamp();
        if (timestamp != null) {
            databaseStatement.bindLong(6, timestamp.longValue());
        }
        String par1 = orderMessageBean.getPar1();
        if (par1 != null) {
            databaseStatement.bindString(7, par1);
        }
        String par2 = orderMessageBean.getPar2();
        if (par2 != null) {
            databaseStatement.bindString(8, par2);
        }
        String par3 = orderMessageBean.getPar3();
        if (par3 != null) {
            databaseStatement.bindString(9, par3);
        }
        String par4 = orderMessageBean.getPar4();
        if (par4 != null) {
            databaseStatement.bindString(10, par4);
        }
        String par5 = orderMessageBean.getPar5();
        if (par5 != null) {
            databaseStatement.bindString(11, par5);
        }
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public Long getKey(OrderMessageBean orderMessageBean) {
        if (orderMessageBean != null) {
            return orderMessageBean.getId();
        }
        return null;
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public boolean hasKey(OrderMessageBean orderMessageBean) {
        return orderMessageBean.getId() != null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final boolean isEntityUpdateable() {
        return true;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.greenrobot.greendao.AbstractDao
    public OrderMessageBean readEntity(Cursor cursor, int i) {
        int i2 = i + 0;
        Long valueOf = cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2));
        int i3 = i + 1;
        String string = cursor.isNull(i3) ? null : cursor.getString(i3);
        int i4 = cursor.getInt(i + 2);
        int i5 = i + 3;
        String string2 = cursor.isNull(i5) ? null : cursor.getString(i5);
        int i6 = i + 4;
        String string3 = cursor.isNull(i6) ? null : cursor.getString(i6);
        int i7 = i + 5;
        Long valueOf2 = cursor.isNull(i7) ? null : Long.valueOf(cursor.getLong(i7));
        int i8 = i + 6;
        String string4 = cursor.isNull(i8) ? null : cursor.getString(i8);
        int i9 = i + 7;
        String string5 = cursor.isNull(i9) ? null : cursor.getString(i9);
        int i10 = i + 8;
        String string6 = cursor.isNull(i10) ? null : cursor.getString(i10);
        int i11 = i + 9;
        int i12 = i + 10;
        return new OrderMessageBean(valueOf, string, i4, string2, string3, valueOf2, string4, string5, string6, cursor.isNull(i11) ? null : cursor.getString(i11), cursor.isNull(i12) ? null : cursor.getString(i12));
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public void readEntity(Cursor cursor, OrderMessageBean orderMessageBean, int i) {
        int i2 = i + 0;
        orderMessageBean.setId(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)));
        int i3 = i + 1;
        orderMessageBean.setSerialName(cursor.isNull(i3) ? null : cursor.getString(i3));
        orderMessageBean.setType(cursor.getInt(i + 2));
        int i4 = i + 3;
        orderMessageBean.setTime(cursor.isNull(i4) ? null : cursor.getString(i4));
        int i5 = i + 4;
        orderMessageBean.setMessage(cursor.isNull(i5) ? null : cursor.getString(i5));
        int i6 = i + 5;
        orderMessageBean.setTimestamp(cursor.isNull(i6) ? null : Long.valueOf(cursor.getLong(i6)));
        int i7 = i + 6;
        orderMessageBean.setPar1(cursor.isNull(i7) ? null : cursor.getString(i7));
        int i8 = i + 7;
        orderMessageBean.setPar2(cursor.isNull(i8) ? null : cursor.getString(i8));
        int i9 = i + 8;
        orderMessageBean.setPar3(cursor.isNull(i9) ? null : cursor.getString(i9));
        int i10 = i + 9;
        orderMessageBean.setPar4(cursor.isNull(i10) ? null : cursor.getString(i10));
        int i11 = i + 10;
        orderMessageBean.setPar5(cursor.isNull(i11) ? null : cursor.getString(i11));
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
    public final Long updateKeyAfterInsert(OrderMessageBean orderMessageBean, long j) {
        orderMessageBean.setId(Long.valueOf(j));
        return Long.valueOf(j);
    }
}
