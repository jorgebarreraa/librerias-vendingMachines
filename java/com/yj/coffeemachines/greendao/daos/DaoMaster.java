package com.yj.coffeemachines.greendao.daos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import org.greenrobot.greendao.AbstractDaoMaster;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseOpenHelper;
import org.greenrobot.greendao.database.StandardDatabase;
import org.greenrobot.greendao.identityscope.IdentityScopeType;

/* loaded from: classes.dex */
public class DaoMaster extends AbstractDaoMaster {
    public static final int SCHEMA_VERSION = 8;

    /* loaded from: classes.dex */
    public static class DevOpenHelper extends OpenHelper {
        public DevOpenHelper(Context context, String str) {
            super(context, str);
        }

        public DevOpenHelper(Context context, String str, SQLiteDatabase.CursorFactory cursorFactory) {
            super(context, str, cursorFactory);
        }

        @Override // org.greenrobot.greendao.database.DatabaseOpenHelper
        public void onUpgrade(Database database, int i, int i2) {
            Log.i("greenDAO", "Upgrading schema from version " + i + " to " + i2 + " by dropping all tables");
            DaoMaster.dropAllTables(database, true);
            onCreate(database);
        }
    }

    /* loaded from: classes.dex */
    public static abstract class OpenHelper extends DatabaseOpenHelper {
        public OpenHelper(Context context, String str) {
            super(context, str, 8);
        }

        public OpenHelper(Context context, String str, SQLiteDatabase.CursorFactory cursorFactory) {
            super(context, str, cursorFactory, 8);
        }

        @Override // org.greenrobot.greendao.database.DatabaseOpenHelper
        public void onCreate(Database database) {
            Log.i("greenDAO", "Creating tables for schema version 8");
            DaoMaster.createAllTables(database, false);
        }
    }

    public DaoMaster(SQLiteDatabase sQLiteDatabase) {
        this(new StandardDatabase(sQLiteDatabase));
    }

    public DaoMaster(Database database) {
        super(database, 8);
        registerDaoClass(ADMessageDao.class);
        registerDaoClass(DevOpenAndCloseMessageDao.class);
        registerDaoClass(FileMessageDao.class);
        registerDaoClass(MakeDrinkMessageDao.class);
        registerDaoClass(MaterialMessageDao.class);
        registerDaoClass(OpsActionMessageDao.class);
        registerDaoClass(OrderMessageBeanDao.class);
        registerDaoClass(VoiceMessageDao.class);
    }

    public static void createAllTables(Database database, boolean z) {
        ADMessageDao.createTable(database, z);
        DevOpenAndCloseMessageDao.createTable(database, z);
        FileMessageDao.createTable(database, z);
        MakeDrinkMessageDao.createTable(database, z);
        MaterialMessageDao.createTable(database, z);
        OpsActionMessageDao.createTable(database, z);
        OrderMessageBeanDao.createTable(database, z);
        VoiceMessageDao.createTable(database, z);
    }

    public static void dropAllTables(Database database, boolean z) {
        ADMessageDao.dropTable(database, z);
        DevOpenAndCloseMessageDao.dropTable(database, z);
        FileMessageDao.dropTable(database, z);
        MakeDrinkMessageDao.dropTable(database, z);
        MaterialMessageDao.dropTable(database, z);
        OpsActionMessageDao.dropTable(database, z);
        OrderMessageBeanDao.dropTable(database, z);
        VoiceMessageDao.dropTable(database, z);
    }

    public static DaoSession newDevSession(Context context, String str) {
        return new DaoMaster(new DevOpenHelper(context, str).getWritableDb()).newSession();
    }

    @Override // org.greenrobot.greendao.AbstractDaoMaster
    public DaoSession newSession() {
        return new DaoSession(this.db, IdentityScopeType.Session, this.daoConfigMap);
    }

    @Override // org.greenrobot.greendao.AbstractDaoMaster
    public DaoSession newSession(IdentityScopeType identityScopeType) {
        return new DaoSession(this.db, identityScopeType, this.daoConfigMap);
    }
}
