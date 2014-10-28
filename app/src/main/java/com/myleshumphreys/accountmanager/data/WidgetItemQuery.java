package com.myleshumphreys.accountmanager.data;

import android.content.Context;

public class WidgetItemQuery {

    private static final String TABLE_NAME = "WidgetItem";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "widgetName";
    private static final String COLUMN_TYPE = "widgetType";
    private static final String COLUMN_IMAGE_ID = "widgetImage";

    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME
            + "( "
            + COLUMN_ID + " INTEGER PRIMARY KEY, "
            + COLUMN_NAME + " TEXT, "
            + COLUMN_TYPE + " TEXT, "
            + COLUMN_IMAGE_ID + " INTEGER, "
            + ") ";

    public static final String INSERT_DEFAULT_DATA = "INSERT INTO " + TABLE_NAME
            + "(" + COLUMN_ID + COLUMN_NAME + COLUMN_TYPE + ") VALUES ( 1, EditView, association, 1) ";

    private Context context = null;

    public WidgetItemQuery(Context context) {
        this.context = context;
    }
}