package com.myleshumphreys.accountmanager.toasts;

import android.content.Context;
import android.widget.Toast;

public class RegisteredActivityToasts {

    public void registeredUserToast(Context context) {
        Toast.makeText(context,
                "User has been added", Toast.LENGTH_SHORT).show();
    }

    public void invalidEmailAddressToast(Context context) {
        Toast.makeText(context,
                "Must be a valid email address", Toast.LENGTH_SHORT).show();
    }

    public void invalidPasswordToast(Context context) {
        Toast.makeText(context,
                "Password must be at least 8 characters", Toast.LENGTH_SHORT).show();
    }

    public void invalidPasswordMatchToast(Context context) {
        Toast.makeText(context,
                "Passwords do not match", Toast.LENGTH_SHORT).show();
    }

    public void userExistsToast(Context context) {
        Toast.makeText(context,
                "Email Address already registered", Toast.LENGTH_SHORT).show();
    }
}