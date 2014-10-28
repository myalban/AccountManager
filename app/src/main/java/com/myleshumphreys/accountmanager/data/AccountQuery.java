package com.myleshumphreys.accountmanager.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.myleshumphreys.accountmanager.models.Account;

public class AccountQuery {

    private static final String TABLE_NAME = "Account";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";

    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME
            + "( "
            + COLUMN_ID + " INTEGER PRIMARY KEY, "
            + COLUMN_NAME + " TEXT, "
            + ") ";

    private Context context = null;

    public AccountQuery(Context context) {
        this.context = context;
    }
}