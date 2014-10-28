package com.myleshumphreys.accountmanager.validation;

public class InputValidation {

    public boolean IsNullOrEmpty(String text) {
        return text != null && !text.isEmpty();
    }

    public boolean stringComparison(String string1, String string2) {
        return string1.equals(string2);
    }
}