package com.myleshumphreys.accountmanager.models;

import java.sql.Blob;

public class WidgetItemImage {

    private int id;
    private Blob image;

    public WidgetItemImage(int id, Blob image) {
        this.id = id;
        this.image = image;
    }

    public int getId() {
        return this.id;
    }

    public Blob getImage() {
        return this.image;
    }
}