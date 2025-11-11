package com.github.yuweiguocn.library.greendao;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.NonNull;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.StandardDatabase;
import org.greenrobot.greendao.internal.DaoConfig;

/* loaded from: classes.dex */
public final class MigrationHelper {
    public static boolean DEBUG = false;
    private static final String SQLITE_MASTER = "sqlite_master";
    private static final String SQLITE_TEMP_MASTER = "sqlite_temp_master";
    private static String TAG = "MigrationHelper";
    private static WeakReference<ReCreateAllTableListener> weakListener;

    /* loaded from: classes.dex */
    public interface ReCreateAllTableListener {
        void onCreateAllTables(Database database, boolean z);

        void onDropAllTables(Database database, boolean z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class TableInfo {
        int cid;
        String dfltValue;
        String name;
        boolean notnull;
        boolean pk;
        String type;

        private TableInfo() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static List<TableInfo> getTableInfo(Database database, String str) {
            String str2 = "PRAGMA table_info(`" + str + "`)";
            MigrationHelper.printLog(str2);
            Cursor rawQuery = database.rawQuery(str2, null);
            if (rawQuery == null) {
                return new ArrayList();
            }
            ArrayList arrayList = new ArrayList();
            while (rawQuery.moveToNext()) {
                TableInfo tableInfo = new TableInfo();
                boolean z = false;
                tableInfo.cid = rawQuery.getInt(0);
                tableInfo.name = rawQuery.getString(1);
                tableInfo.type = rawQuery.getString(2);
                tableInfo.notnull = rawQuery.getInt(3) == 1;
                tableInfo.dfltValue = rawQuery.getString(4);
                if (rawQuery.getInt(5) == 1) {
                    z = true;
                }
                tableInfo.pk = z;
                arrayList.add(tableInfo);
            }
            rawQuery.close();
            return arrayList;
        }

        public boolean equals(Object obj) {
            return this == obj || (obj != null && getClass() == obj.getClass() && this.name.equals(((TableInfo) obj).name));
        }

        public String toString() {
            return "TableInfo{cid=" + this.cid + ", name='" + this.name + "', type='" + this.type + "', notnull=" + this.notnull + ", dfltValue='" + this.dfltValue + "', pk=" + this.pk + '}';
        }
    }

    private static void createAllTables(Database database, boolean z, @NonNull Class<? extends AbstractDao<?, ?>>... clsArr) {
        reflectMethod(database, "createTable", z, clsArr);
        printLog("【Create all table by reflect】");
    }

    private static void dropAllTables(Database database, boolean z, @NonNull Class<? extends AbstractDao<?, ?>>... clsArr) {
        reflectMethod(database, "dropTable", z, clsArr);
        printLog("【Drop all table by reflect】");
    }

    private static void generateTempTables(Database database, Class<? extends AbstractDao<?, ?>>... clsArr) {
        for (Class<? extends AbstractDao<?, ?>> cls : clsArr) {
            String str = null;
            DaoConfig daoConfig = new DaoConfig(database, cls);
            String str2 = daoConfig.tablename;
            if (isTableExists(database, false, str2)) {
                try {
                    str = daoConfig.tablename.concat("_TEMP");
                    database.execSQL("DROP TABLE IF EXISTS " + str + ";");
                    database.execSQL("CREATE TEMPORARY TABLE " + str + " AS SELECT * FROM `" + str2 + "`;");
                    StringBuilder sb = new StringBuilder();
                    sb.append("【Table】");
                    sb.append(str2);
                    sb.append("\n ---Columns-->");
                    sb.append(getColumnsStr(daoConfig));
                    printLog(sb.toString());
                    printLog("【Generate temp table】" + str);
                } catch (SQLException e) {
                    Log.e(TAG, "【Failed to generate temp table】" + str, e);
                }
            } else {
                printLog("【New Table】" + str2);
            }
        }
    }

    private static List<String> getColumns(Database database, String str) {
        Cursor cursor = null;
        r0 = null;
        List<String> asList = null;
        cursor = null;
        try {
            try {
                Cursor rawQuery = database.rawQuery("SELECT * FROM " + str + " limit 0", null);
                if (rawQuery != null) {
                    try {
                        if (rawQuery.getColumnCount() > 0) {
                            asList = Arrays.asList(rawQuery.getColumnNames());
                        }
                    } catch (Exception e) {
                        e = e;
                        cursor = rawQuery;
                        e.printStackTrace();
                        if (cursor != null) {
                            cursor.close();
                        }
                        return new ArrayList();
                    } catch (Throwable th) {
                        th = th;
                        cursor = rawQuery;
                        if (cursor != null) {
                            cursor.close();
                        }
                        new ArrayList();
                        throw th;
                    }
                }
                if (rawQuery != null) {
                    rawQuery.close();
                }
                return asList == null ? new ArrayList() : asList;
            } catch (Exception e2) {
                e = e2;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    private static String getColumnsStr(DaoConfig daoConfig) {
        if (daoConfig == null) {
            return "no columns";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < daoConfig.allColumns.length; i++) {
            sb.append(daoConfig.allColumns[i]);
            sb.append(",");
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

    /* JADX WARN: Code restructure failed: missing block: B:23:0x004b, code lost:
    
        r1.close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static boolean isTableExists(org.greenrobot.greendao.database.Database r5, boolean r6, java.lang.String r7) {
        /*
            r0 = 0
            if (r5 == 0) goto L65
            boolean r1 = android.text.TextUtils.isEmpty(r7)
            if (r1 == 0) goto La
            goto L65
        La:
            if (r6 == 0) goto Lf
            java.lang.String r6 = "sqlite_temp_master"
            goto L11
        Lf:
            java.lang.String r6 = "sqlite_master"
        L11:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "SELECT COUNT(*) FROM `"
            r1.append(r2)
            r1.append(r6)
            java.lang.String r6 = "` WHERE type = ? AND name = ?"
            r1.append(r6)
            java.lang.String r6 = r1.toString()
            r1 = 0
            r2 = 2
            r3 = 1
            java.lang.String[] r2 = new java.lang.String[r2]     // Catch: java.lang.Throwable -> L4f java.lang.Exception -> L51
            java.lang.String r4 = "table"
            r2[r0] = r4     // Catch: java.lang.Throwable -> L4f java.lang.Exception -> L51
            r2[r3] = r7     // Catch: java.lang.Throwable -> L4f java.lang.Exception -> L51
            android.database.Cursor r1 = r5.rawQuery(r6, r2)     // Catch: java.lang.Throwable -> L4f java.lang.Exception -> L51
            if (r1 == 0) goto L49
            boolean r5 = r1.moveToFirst()     // Catch: java.lang.Throwable -> L4f java.lang.Exception -> L51
            if (r5 != 0) goto L3f
            goto L49
        L3f:
            int r5 = r1.getInt(r0)     // Catch: java.lang.Throwable -> L4f java.lang.Exception -> L51
            if (r1 == 0) goto L5b
            r1.close()
            goto L5b
        L49:
            if (r1 == 0) goto L4e
            r1.close()
        L4e:
            return r0
        L4f:
            r5 = move-exception
            goto L5f
        L51:
            r5 = move-exception
            r5.printStackTrace()     // Catch: java.lang.Throwable -> L4f
            if (r1 == 0) goto L5a
            r1.close()
        L5a:
            r5 = 0
        L5b:
            if (r5 <= 0) goto L5e
            r0 = 1
        L5e:
            return r0
        L5f:
            if (r1 == 0) goto L64
            r1.close()
        L64:
            throw r5
        L65:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.github.yuweiguocn.library.greendao.MigrationHelper.isTableExists(org.greenrobot.greendao.database.Database, boolean, java.lang.String):boolean");
    }

    public static void migrate(SQLiteDatabase sQLiteDatabase, ReCreateAllTableListener reCreateAllTableListener, Class<? extends AbstractDao<?, ?>>... clsArr) {
        weakListener = new WeakReference<>(reCreateAllTableListener);
        migrate(sQLiteDatabase, clsArr);
    }

    public static void migrate(SQLiteDatabase sQLiteDatabase, Class<? extends AbstractDao<?, ?>>... clsArr) {
        printLog("【The Old Database Version】" + sQLiteDatabase.getVersion());
        migrate(new StandardDatabase(sQLiteDatabase), clsArr);
    }

    public static void migrate(Database database, ReCreateAllTableListener reCreateAllTableListener, Class<? extends AbstractDao<?, ?>>... clsArr) {
        weakListener = new WeakReference<>(reCreateAllTableListener);
        migrate(database, clsArr);
    }

    public static void migrate(Database database, Class<? extends AbstractDao<?, ?>>... clsArr) {
        printLog("【Generate temp table】start");
        generateTempTables(database, clsArr);
        printLog("【Generate temp table】complete");
        WeakReference<ReCreateAllTableListener> weakReference = weakListener;
        ReCreateAllTableListener reCreateAllTableListener = weakReference != null ? weakReference.get() : null;
        if (reCreateAllTableListener != null) {
            reCreateAllTableListener.onDropAllTables(database, true);
            printLog("【Drop all table by listener】");
            reCreateAllTableListener.onCreateAllTables(database, false);
            printLog("【Create all table by listener】");
        } else {
            dropAllTables(database, true, clsArr);
            createAllTables(database, false, clsArr);
        }
        printLog("【Restore data】start");
        restoreData(database, clsArr);
        printLog("【Restore data】complete");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void printLog(String str) {
        if (DEBUG) {
            Log.d(TAG, str);
        }
    }

    private static void reflectMethod(Database database, String str, boolean z, @NonNull Class<? extends AbstractDao<?, ?>>... clsArr) {
        if (clsArr.length < 1) {
            return;
        }
        try {
            for (Class<? extends AbstractDao<?, ?>> cls : clsArr) {
                cls.getDeclaredMethod(str, Database.class, Boolean.TYPE).invoke(null, database, Boolean.valueOf(z));
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e2) {
            e2.printStackTrace();
        } catch (InvocationTargetException e3) {
            e3.printStackTrace();
        }
    }

    private static void restoreData(Database database, Class<? extends AbstractDao<?, ?>>... clsArr) {
        for (Class<? extends AbstractDao<?, ?>> cls : clsArr) {
            DaoConfig daoConfig = new DaoConfig(database, cls);
            String str = daoConfig.tablename;
            String concat = daoConfig.tablename.concat("_TEMP");
            if (isTableExists(database, true, concat)) {
                try {
                    List<TableInfo> tableInfo = TableInfo.getTableInfo(database, str);
                    List<TableInfo> tableInfo2 = TableInfo.getTableInfo(database, concat);
                    ArrayList arrayList = new ArrayList(tableInfo.size());
                    ArrayList arrayList2 = new ArrayList(tableInfo.size());
                    for (TableInfo tableInfo3 : tableInfo2) {
                        if (tableInfo.contains(tableInfo3)) {
                            String str2 = '`' + tableInfo3.name + '`';
                            arrayList2.add(str2);
                            arrayList.add(str2);
                        }
                    }
                    for (TableInfo tableInfo4 : tableInfo) {
                        if (tableInfo4.notnull && !tableInfo2.contains(tableInfo4)) {
                            String str3 = '`' + tableInfo4.name + '`';
                            arrayList2.add(str3);
                            arrayList.add((tableInfo4.dfltValue != null ? "'" + tableInfo4.dfltValue + "' AS " : "'' AS ") + str3);
                        }
                    }
                    if (arrayList2.size() != 0) {
                        database.execSQL("REPLACE INTO `" + str + "` (" + TextUtils.join(",", arrayList2) + ") SELECT " + TextUtils.join(",", arrayList) + " FROM " + concat + ";");
                        StringBuilder sb = new StringBuilder();
                        sb.append("【Restore data】 to ");
                        sb.append(str);
                        printLog(sb.toString());
                    }
                    database.execSQL("DROP TABLE " + concat);
                    printLog("【Drop temp table】" + concat);
                } catch (SQLException e) {
                    Log.e(TAG, "【Failed to restore data from temp table 】" + concat, e);
                }
            }
        }
    }
}
