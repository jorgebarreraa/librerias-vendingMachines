package com.yj.coffeemachines.greendao;

import android.content.Context;
import android.content.ContextWrapper;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import com.yj.coffeemachines.Constants;
import java.io.File;
import java.io.IOException;

/* loaded from: classes.dex */
public class DatabaseContext extends ContextWrapper {
    public static String dbPath = Constants.absolutePath + File.separator + "CoffeeMachine" + File.separator + "db";

    public DatabaseContext(Context context, String str) {
        super(context);
        if (TextUtils.isEmpty(str)) {
            return;
        }
        dbPath = str;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public File getDatabasePath(String str) {
        File file = new File(dbPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        File file2 = new File(dbPath, str);
        if (!file2.exists()) {
            try {
                file2.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file2;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public SQLiteDatabase openOrCreateDatabase(String str, int i, SQLiteDatabase.CursorFactory cursorFactory) {
        return SQLiteDatabase.openOrCreateDatabase(getDatabasePath(str), (SQLiteDatabase.CursorFactory) null);
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public SQLiteDatabase openOrCreateDatabase(String str, int i, SQLiteDatabase.CursorFactory cursorFactory, DatabaseErrorHandler databaseErrorHandler) {
        return SQLiteDatabase.openOrCreateDatabase(getDatabasePath(str), (SQLiteDatabase.CursorFactory) null);
    }
}
