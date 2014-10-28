package com.myleshumphreys.accountmanager.toasts;

import android.content.Context;
import android.widget.Toast;

public class LoginActivityToasts {

    public void validLoginToast(Context context) {
        Toast.makeText(context, "Login Successful", Toast.LENGTH_SHORT).show();
    }

    public void inValidLoginToast(Context context) {
        Toast.makeText(context, "Incorrect credentials", Toast.LENGTH_SHORT).show();
    }
}