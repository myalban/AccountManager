package com.myleshumphreys.accountmanager.widgets;

import android.app.Activity;
import android.graphics.Color;
import android.widget.EditText;

public class EditTextWidget {

    public EditText addEditText(String hint, Activity activity){
        EditText newEditText = new EditText(activity);
        newEditText.setHint(hint);
        newEditText.setTextColor(Color.BLACK);
        return newEditText;
    }
}