package com.myleshumphreys.accountmanager.data;

import android.content.Context;

public class WidgetItemImageQuery {

    private static final String TABLE_NAME = "WidgetItemImage";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_IMAGE = "image";

    private static String imageLocation;

    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME
            + "( "
            + COLUMN_ID + " INTEGER PRIMARY KEY, "
            + COLUMN_IMAGE + " BLOB "
            + ") ";

//    public static final String INSERT_DEFAULT_DATA = "INSERT INTO " + TABLE_NAME
//            + "(" + COLUMN_ID + COLUMN_IMAGE + ") VALUES ( 1, " + imageLocation + ") ";

    private Context context = null;

    public WidgetItemImageQuery(Context context) {
        this.context = context;
    }
}