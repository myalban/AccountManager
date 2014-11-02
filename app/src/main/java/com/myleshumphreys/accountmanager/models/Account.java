package com.myleshumphreys.accountmanager.models;

public class Account {

    private int id;
    private String name;
    private int userId;


    public Account(int id, String name, int userId) {
        this.id = id;
        this.name = name;
        this.userId = userId;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public int getUserId(){
        return this.userId;
    }
}