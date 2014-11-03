package com.myleshumphreys.accountmanager.data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.myleshumphreys.accountmanager.models.AccountInfo;

import java.util.ArrayList;
import java.util.List;

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
            + "FOREIGN KEY(" + COLUMN_ACCOUNT_ID + ") REFERENCES Account(id) ON DELETE CASCADE,"
            + "FOREIGN KEY(" + COLUMN_WIDGET_ID + ") REFERENCES WidgetItem(id) ON DELETE CASCADE"
            + ") ";

    public static final String INSERT_DEFAULT_DATA = "INSERT INTO " + TABLE_NAME
            + "(id, value, accountId, widgetId ) VALUES ( 1, 'email@email.com', 1, 1 )";

    private Context context = null;

    public AccountInfoQuery(Context context) {
        this.context = context;
    }

    public List<AccountInfo> getAccountInfo(int accountId) {
        List<AccountInfo> accounts = new ArrayList<AccountInfo>();
        SQLiteDatabase db = DatabaseContext.getInstance(this.context).getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME
                + " WHERE " + COLUMN_ACCOUNT_ID + " = " + accountId, null);

        if (cursor.moveToFirst()) {
            do {
                accounts.add(new AccountInfo(
                        Integer.parseInt(cursor.getString(0)),
                        cursor.getString(1),
                        Integer.parseInt(cursor.getString(2)),
                        Integer.parseInt(cursor.getString(3))));
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return accounts;
    }
}