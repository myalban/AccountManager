package com.myleshumphreys.accountmanager.validation;

public class UserValidation {

    public boolean validateEmailAddress(String emailAddress) {
        //emailAddress.matches("[a-zA-Z0-9\\.]+@[a-zA-Z0-9\\-\\_\\.]+\\.[a-zA-Z0-9]{3}");
        return true;
    }

    public boolean validatePasswordLength(String password) {
        return (password.length() > 7);
    }
}