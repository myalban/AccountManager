package com.myleshumphreys.accountmanager.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseContext extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "AccountManager";

    private static DatabaseContext Instance = null;

    public DatabaseContext(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase _db) {
        _db.execSQL(WidgetItemImageQuery.CREATE_TABLE);
        _db.execSQL(WidgetItemImageQuery.INSERT_DEFAULT_DATA);
        _db.execSQL(WidgetItemQuery.CREATE_TABLE);
        _db.execSQL(WidgetItemQuery.INSERT_DEFAULT_DATA);
        _db.execSQL(AccountQuery.CREATE_TABLE);
        _db.execSQL(AccountInfoQuery.CREATE_TABLE);
        _db.execSQL(UserQuery.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase _db, int _oldVersion, int _newVersion) {
        //TODO
    }

    public static DatabaseContext getInstance(Context context) {

        if (Instance == null) {
            Instance = new DatabaseContext(context.getApplicationContext());
        }
        return Instance;
    }
}