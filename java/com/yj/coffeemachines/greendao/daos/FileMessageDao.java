package com.yj.coffeemachines.greendao.daos;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.yj.coffeemachines.greendao.beans.FileMessage;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

/* loaded from: classes.dex */
public class FileMessageDao extends AbstractDao<FileMessage, Long> {
    public static final String TABLENAME = "FILE_MESSAGE";

    /* loaded from: classes.dex */
    public static class Properties {
        public static final Property Key = new Property(0, Long.class, "key", true, "_id");
        public static final Property FileName = new Property(1, String.class, "fileName", false, "FILE_NAME");
        public static final Property FileType = new Property(2, Integer.TYPE, "fileType", false, "FILE_TYPE");
        public static final Property UseObject = new Property(3, String.class, "useObject", false, "USE_OBJECT");
        public static final Property DownloadUrl = new Property(4, String.class, "downloadUrl", false, "DOWNLOAD_URL");
        public static final Property LocalPath = new Property(5, String.class, "localPath", false, "LOCAL_PATH");
        public static final Property IsCheck = new Property(6, Boolean.TYPE, "isCheck", false, "IS_CHECK");
    }

    public FileMessageDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public FileMessageDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
    }

    public static void createTable(Database database, boolean z) {
        database.execSQL("CREATE TABLE " + (z ? "IF NOT EXISTS " : "") + "\"FILE_MESSAGE\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ,\"FILE_NAME\" TEXT,\"FILE_TYPE\" INTEGER NOT NULL ,\"USE_OBJECT\" TEXT,\"DOWNLOAD_URL\" TEXT,\"LOCAL_PATH\" TEXT,\"IS_CHECK\" INTEGER NOT NULL );");
    }

    public static void dropTable(Database database, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("\"FILE_MESSAGE\"");
        database.execSQL(sb.toString());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(SQLiteStatement sQLiteStatement, FileMessage fileMessage) {
        sQLiteStatement.clearBindings();
        Long key = fileMessage.getKey();
        if (key != null) {
            sQLiteStatement.bindLong(1, key.longValue());
        }
        String fileName = fileMessage.getFileName();
        if (fileName != null) {
            sQLiteStatement.bindString(2, fileName);
        }
        sQLiteStatement.bindLong(3, fileMessage.getFileType());
        String useObject = fileMessage.getUseObject();
        if (useObject != null) {
            sQLiteStatement.bindString(4, useObject);
        }
        String downloadUrl = fileMessage.getDownloadUrl();
        if (downloadUrl != null) {
            sQLiteStatement.bindString(5, downloadUrl);
        }
        String localPath = fileMessage.getLocalPath();
        if (localPath != null) {
            sQLiteStatement.bindString(6, localPath);
        }
        sQLiteStatement.bindLong(7, fileMessage.getIsCheck() ? 1L : 0L);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(DatabaseStatement databaseStatement, FileMessage fileMessage) {
        databaseStatement.clearBindings();
        Long key = fileMessage.getKey();
        if (key != null) {
            databaseStatement.bindLong(1, key.longValue());
        }
        String fileName = fileMessage.getFileName();
        if (fileName != null) {
            databaseStatement.bindString(2, fileName);
        }
        databaseStatement.bindLong(3, fileMessage.getFileType());
        String useObject = fileMessage.getUseObject();
        if (useObject != null) {
            databaseStatement.bindString(4, useObject);
        }
        String downloadUrl = fileMessage.getDownloadUrl();
        if (downloadUrl != null) {
            databaseStatement.bindString(5, downloadUrl);
        }
        String localPath = fileMessage.getLocalPath();
        if (localPath != null) {
            databaseStatement.bindString(6, localPath);
        }
        databaseStatement.bindLong(7, fileMessage.getIsCheck() ? 1L : 0L);
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public Long getKey(FileMessage fileMessage) {
        if (fileMessage != null) {
            return fileMessage.getKey();
        }
        return null;
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public boolean hasKey(FileMessage fileMessage) {
        return fileMessage.getKey() != null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final boolean isEntityUpdateable() {
        return true;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.greenrobot.greendao.AbstractDao
    public FileMessage readEntity(Cursor cursor, int i) {
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
        return new FileMessage(valueOf, string, i4, string2, string3, cursor.isNull(i7) ? null : cursor.getString(i7), cursor.getShort(i + 6) != 0);
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public void readEntity(Cursor cursor, FileMessage fileMessage, int i) {
        int i2 = i + 0;
        fileMessage.setKey(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)));
        int i3 = i + 1;
        fileMessage.setFileName(cursor.isNull(i3) ? null : cursor.getString(i3));
        fileMessage.setFileType(cursor.getInt(i + 2));
        int i4 = i + 3;
        fileMessage.setUseObject(cursor.isNull(i4) ? null : cursor.getString(i4));
        int i5 = i + 4;
        fileMessage.setDownloadUrl(cursor.isNull(i5) ? null : cursor.getString(i5));
        int i6 = i + 5;
        fileMessage.setLocalPath(cursor.isNull(i6) ? null : cursor.getString(i6));
        fileMessage.setIsCheck(cursor.getShort(i + 6) != 0);
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
    public final Long updateKeyAfterInsert(FileMessage fileMessage, long j) {
        fileMessage.setKey(Long.valueOf(j));
        return Long.valueOf(j);
    }
}
