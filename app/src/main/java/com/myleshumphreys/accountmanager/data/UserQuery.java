package com.myleshumphreys.accountmanager.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.myleshumphreys.accountmanager.models.User;

public class UserQuery {

    private static final String TABLE_NAME = "User";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_EMAIL_ADDRESS = "emailAddress";
    private static final String COLUMN_PASSWORD = "password";

    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME
            + "( "
            + COLUMN_ID + " INTEGER PRIMARY KEY, "
            + COLUMN_EMAIL_ADDRESS + " TEXT, "
            + COLUMN_PASSWORD + " TEXT "
            + ") ";

    private Context context = null;

    public UserQuery(Context context) {
        this.context = context;
    }

    public void create(User user) {
        SQLiteDatabase db = DatabaseContext.getInstance(this.context).getWritableDatabase();
        ContentValues userValues = new ContentValues();
        userValues.put(COLUMN_EMAIL_ADDRESS, user.getEmailAddress());
        userValues.put(COLUMN_PASSWORD, user.getPassword());
        db.insert(TABLE_NAME, null, userValues);
        db.close();
    }

    public int getId(String emailAddress, String password) {
        SQLiteDatabase db = DatabaseContext.getInstance(this.context).getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME, new String[]{
                        COLUMN_ID,
                        COLUMN_EMAIL_ADDRESS,
                        COLUMN_PASSWORD},
                COLUMN_EMAIL_ADDRESS + "=? AND " + COLUMN_PASSWORD + "=?", new String[]
                        {String.valueOf(emailAddress), String.valueOf(password)}, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }
        return Integer.parseInt(cursor.getString(0));
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

    public boolean validateCredentials(String emailAddress, String password) {
        SQLiteDatabase db = DatabaseContext.getInstance(this.context).getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME, new String[]{
                        COLUMN_ID,
                        COLUMN_EMAIL_ADDRESS,
                        COLUMN_PASSWORD},
                COLUMN_EMAIL_ADDRESS + "=? AND " + COLUMN_PASSWORD + "=?", new String[]
                        {String.valueOf(emailAddress), String.valueOf(password)}, null, null, null, null);

        return cursor.moveToFirst();
    }

    public boolean userExists(String emailAddress) {
        SQLiteDatabase db = DatabaseContext.getInstance(this.context).getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME, new String[]{
                        COLUMN_ID,
                        COLUMN_EMAIL_ADDRESS,
                        COLUMN_PASSWORD},
                COLUMN_EMAIL_ADDRESS + "=?", new String[]
                        {String.valueOf(emailAddress)}, null, null, null, null);

        return cursor.moveToFirst();
    }
}