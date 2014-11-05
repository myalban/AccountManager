package com.myleshumphreys.accountmanager.widgets;

import android.app.Activity;
import android.graphics.Color;
import android.widget.Button;

public class ButtonWidget {

    public Button addButton(String buttonText, Activity activity){
        Button newButton = new Button(activity);
        newButton.setText(buttonText);
        newButton.setTextColor(Color.BLACK);
        return newButton;
    }
}