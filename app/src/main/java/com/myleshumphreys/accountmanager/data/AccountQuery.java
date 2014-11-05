package com.myleshumphreys.accountmanager.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.myleshumphreys.accountmanager.models.Account;

import java.util.ArrayList;
import java.util.List;

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

    public static final String INSERT_DEFAULT_DATA = "INSERT INTO " + TABLE_NAME
            + "(id, association, userId ) VALUES ( 1, 'Google +', 1 ), ( 2, 'Facebook', 1 ) ";

    private Context context = null;

    public AccountQuery(Context context) {
        this.context = context;
    }

    public void create(Account account) {
        SQLiteDatabase db = DatabaseContext.getInstance(this.context).getWritableDatabase();
        ContentValues accountValues = new ContentValues();
        accountValues.put(COLUMN_ASSOCIATION, account.getName());
        accountValues.put(COLUMN_USER_ID, account.getUserId());
        db.insert(TABLE_NAME, null, accountValues);
        db.close();
    }

    public List<Account> getAllAccounts(int userId) {
        List<Account> accounts = new ArrayList<Account>();
        SQLiteDatabase db = DatabaseContext.getInstance(this.context).getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME
                + " WHERE " + COLUMN_USER_ID + " = " + userId
                + " ORDER BY " + COLUMN_ASSOCIATION + " COLLATE NOCASE ASC", null);

        if (cursor.moveToFirst()) {
            do {
                accounts.add(new Account(Integer.parseInt(cursor.getString(0)),
                        cursor.getString(1),
                        Integer.parseInt(cursor.getString(0))));
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return accounts;
    }

    public int getCount() {
        SQLiteDatabase db = DatabaseContext.getInstance(this.context).getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM " + TABLE_NAME, null);
        cursor.moveToFirst();
        int count = cursor.getInt(0);
        db.close();
        cursor.close();
        return count;
    }

    public int getCountById(int userId) {
        SQLiteDatabase db = DatabaseContext.getInstance(this.context).getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM " + TABLE_NAME
                + " WHERE " + COLUMN_USER_ID + " = " + userId, null);
        cursor.moveToFirst();
        int count = cursor.getInt(0);
        db.close();
        cursor.close();
        return count;
    }
}