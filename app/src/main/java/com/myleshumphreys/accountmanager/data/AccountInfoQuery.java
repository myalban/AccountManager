package com.myleshumphreys.accountmanager.data;

import android.content.Context;

public class AccountInfoQuery {

    private static final String TABLE_NAME = "AccountDetail";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_VALUE = "value";
    private static final String COLUMN_ACCOUNT_ID = "accountId";
    private static final String COLUMN_WIDGET_ID = "widgetId";

    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME
            + "( "
            + COLUMN_ID + " INTEGER PRIMARY KEY, "
            + COLUMN_VALUE + " TEXT, "
            + COLUMN_ACCOUNT_ID + " INTEGER, "
            + COLUMN_WIDGET_ID + " INTEGER, "
            + "FOREIGN KEY(" + COLUMN_ACCOUNT_ID + ") REFERENCES Account(id) ON DELETE CASCADE"
            + "FOREIGN KEY(" + COLUMN_WIDGET_ID + ") REFERENCES WidgetItem(id) ON DELETE CASCADE"
            + ") ";

    private Context context = null;

    public AccountInfoQuery(Context context) {
        this.context = context;
    }
}