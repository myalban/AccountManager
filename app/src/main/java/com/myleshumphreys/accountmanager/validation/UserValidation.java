package com.myleshumphreys.accountmanager.validation;

public class UserValidation {

    public boolean validateEmailAddress(String emailAddress) {
        return emailAddress.matches("[a-zA-Z0-9\\.]+@[a-zA-Z0-9\\-\\_\\.]+\\.[a-zA-Z0-9]{3}");
    }

    public boolean validatePasswordLength(String password) {
        return (password.length() > 7);
    }
}