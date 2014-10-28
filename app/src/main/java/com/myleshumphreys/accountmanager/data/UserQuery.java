package com.myleshumphreys.accountmanager.data;

import android.content.Context;

public class UserQuery {

    private static final String TABLE_NAME = "User";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_EMAILADDRESS = "emailAddress";
    private static final String COLUMN_PASSWORD = "password";

    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME
            + "( "
            + COLUMN_ID + " INTEGER PRIMARY KEY, "
            + COLUMN_EMAILADDRESS + " TEXT, "
            + COLUMN_PASSWORD + " TEXT, "
            + ") ";

    private Context context = null;

    public UserQuery(Context context) {
        this.context = context;
    }
}