package com.myleshumphreys.accountmanager.data;

import android.content.Context;

public class WidgetItemQuery {

    private static final String TABLE_NAME = "WidgetItem";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "widgetName";
    private static final String COLUMN_TYPE = "widgetType";
    private static final String COLUMN_IMAGE_ID = "widgetImageId";

    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME
            + "( "
            + COLUMN_ID + " INTEGER PRIMARY KEY, "
            + COLUMN_NAME + " TEXT, "
            + COLUMN_TYPE + " TEXT, "
            + COLUMN_IMAGE_ID + " INTEGER, "
            + "FOREIGN KEY(" + COLUMN_IMAGE_ID + ") REFERENCES WidgetItemImage(id) ON DELETE CASCADE"
            + ") ";

    public static final String INSERT_DEFAULT_DATA = "INSERT INTO " + TABLE_NAME
            + "(id, widgetName, widgetType, widgetImageId ) VALUES ( 1, 'TextView', 'EmailAddress' , 1 )";

    private Context context = null;

    public WidgetItemQuery(Context context) {
        this.context = context;
    }
}