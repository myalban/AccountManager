package com.myleshumphreys.accountmanager.models;

public class AccountInfo {

    private int id;
    private String value;
    private int accountId;
    private int widgetId;


    public AccountInfo(int id, String value, int accountId, int widgetId) {
        this.id = id;
        this.value = value;
        this.accountId = accountId;
        this.widgetId = widgetId;
    }

    public int getId() {
        return this.id;
    }

    public String getValue() {
        return this.value;
    }

    public int getAccountId() {
        return this.accountId;
    }

    public int getWidgetId() {
        return this.widgetId;
    }
}