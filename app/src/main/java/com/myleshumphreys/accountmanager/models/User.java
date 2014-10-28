package com.myleshumphreys.accountmanager.models;

public class User {

    private int id;
    private String emailAddress;
    private String password;

    public User(int id, String emailAddress, String password) {
        this.id = id;
        this.emailAddress = emailAddress;
        this.password = password;
    }

    public int getId() {
        return this.id;
    }

    public String getEmailAddress() {
        return this.emailAddress;
    }

    public String getPassword() {
        return this.password;
    }
}