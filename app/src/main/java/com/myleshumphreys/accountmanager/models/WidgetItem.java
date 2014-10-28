package com.myleshumphreys.accountmanager.models;

public class WidgetItem {

    private int id;
    private String name;
    private String type;
    private int imageId;

    public WidgetItem(int id, String name, String type, int imageId) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.imageId = imageId;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getType(){
        return this.type;
    }

    public int getImageId(){
        return this.imageId;
    }
}