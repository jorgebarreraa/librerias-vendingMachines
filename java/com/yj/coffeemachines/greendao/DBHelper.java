package com.yj.coffeemachines.greendao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.blankj.utilcode.util.PathUtils;
import com.github.yuweiguocn.library.greendao.MigrationHelper;
import com.yj.coffeemachines.MyAppLocation;
import com.yj.coffeemachines.greendao.daos.ADMessageDao;
import com.yj.coffeemachines.greendao.daos.DaoMaster;
import com.yj.coffeemachines.greendao.daos.DevOpenAndCloseMessageDao;
import com.yj.coffeemachines.greendao.daos.FileMessageDao;
import com.yj.coffeemachines.greendao.daos.MakeDrinkMessageDao;
import com.yj.coffeemachines.greendao.daos.MaterialMessageDao;
import com.yj.coffeemachines.greendao.daos.OpsActionMessageDao;
import com.yj.coffeemachines.greendao.daos.OrderMessageBeanDao;
import com.yj.coffeemachines.greendao.daos.VoiceMessageDao;
import java.io.File;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.database.Database;

/* loaded from: classes.dex */
public class DBHelper extends DaoMaster.OpenHelper {
    private static DBHelper herbalteavendingmachinedb;
    private final Context context;

    public DBHelper(Context context, String str, SQLiteDatabase.CursorFactory cursorFactory) {
        super(context, str, cursorFactory);
        this.context = context;
    }

    public static ADMessageDao getADMessageDao() {
        return getdb().newSession().getADMessageDao();
    }

    public static DevOpenAndCloseMessageDao getDevOpenAndCloseMessageDao() {
        return getdb().newSession().getDevOpenAndCloseMessageDao();
    }

    public static FileMessageDao getFileMessageDao() {
        return getdb().newSession().getFileMessageDao();
    }

    public static MakeDrinkMessageDao getMakeDrinkMessageDao() {
        return getdb().newSession().getMakeDrinkMessageDao();
    }

    public static MaterialMessageDao getMaterialMessageDao() {
        return getdb().newSession().getMaterialMessageDao();
    }

    public static OpsActionMessageDao getOpsActionMessageDao() {
        return getdb().newSession().getOpsActionMessageDao();
    }

    public static OrderMessageBeanDao getOrderMessageBeanDao() {
        return getdb().newSession().getOrderMessageBeanDao();
    }

    public static VoiceMessageDao getVoiceMessageDao() {
        return getdb().newSession().getVoiceMessageDao();
    }

    private static DaoMaster getdb() {
        if (herbalteavendingmachinedb == null) {
            File file = new File(DatabaseContext.dbPath, "coffee.db");
            File file2 = new File(PathUtils.getInternalAppDbsPath(), "coffee.db");
            if (!file.exists() && !file2.exists()) {
                herbalteavendingmachinedb = new DBHelper(MyAppLocation.myAppLocation, "coffee.db", null);
            } else if (!file.exists()) {
                herbalteavendingmachinedb = new DBHelper(MyAppLocation.myAppLocation, "coffee.db", null);
            } else if (!file2.exists()) {
                herbalteavendingmachinedb = new DBHelper(new DatabaseContext(MyAppLocation.myAppLocation, DatabaseContext.dbPath), "coffee.db", null);
            } else if (file.lastModified() > file2.lastModified()) {
                herbalteavendingmachinedb = new DBHelper(new DatabaseContext(MyAppLocation.myAppLocation, DatabaseContext.dbPath), "coffee.db", null);
            } else {
                herbalteavendingmachinedb = new DBHelper(MyAppLocation.myAppLocation, "coffee.db", null);
            }
        }
        return new DaoMaster(herbalteavendingmachinedb.getWritableDatabase());
    }

    @Override // org.greenrobot.greendao.database.DatabaseOpenHelper, android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        MigrationHelper.migrate(sQLiteDatabase, new MigrationHelper.ReCreateAllTableListener() { // from class: com.yj.coffeemachines.greendao.DBHelper.1
            @Override // com.github.yuweiguocn.library.greendao.MigrationHelper.ReCreateAllTableListener
            public void onCreateAllTables(Database database, boolean z) {
                DaoMaster.createAllTables(database, z);
            }

            @Override // com.github.yuweiguocn.library.greendao.MigrationHelper.ReCreateAllTableListener
            public void onDropAllTables(Database database, boolean z) {
                DaoMaster.dropAllTables(database, z);
            }
        }, (Class<? extends AbstractDao<?, ?>>[]) new Class[]{OrderMessageBeanDao.class, MaterialMessageDao.class, ADMessageDao.class, DevOpenAndCloseMessageDao.class, VoiceMessageDao.class, OpsActionMessageDao.class, MakeDrinkMessageDao.class});
    }
}
