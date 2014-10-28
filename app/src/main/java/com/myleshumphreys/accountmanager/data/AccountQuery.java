package com.myleshumphreys.accountmanager.data;

import android.content.Context;

public class AccountQuery {

    private static final String TABLE_NAME = "Account";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_ASSOCIATION = "association";
    private static final String COLUMN_USER_ID = "userId";

    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME
            + "( "
            + COLUMN_ID + " INTEGER PRIMARY KEY, "
            + COLUMN_ASSOCIATION + " TEXT, "
            + COLUMN_USER_ID + " INTEGER, "
            + "FOREIGN KEY(" + COLUMN_USER_ID + ") REFERENCES User(id) ON DELETE CASCADE"
            + ") ";

    private Context context = null;

    public AccountQuery(Context context) {
        this.context = context;
    }
}