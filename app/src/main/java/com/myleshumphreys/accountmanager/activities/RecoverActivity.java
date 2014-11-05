package com.myleshumphreys.accountmanager.activities;

import android.app.Activity;
import android.os.Bundle;

import com.myleshumphreys.accountmanager.R;

public class RecoverActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recover);
    }

    private void EndActivity() {
        RecoverActivity.this.finish();
    }

    @Override
    public void onBackPressed() {
        EndActivity();
    }
}