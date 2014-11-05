package com.myleshumphreys.accountmanager.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.myleshumphreys.accountmanager.data.AccountInfoQuery;
import com.myleshumphreys.accountmanager.models.AccountInfo;

import java.util.ArrayList;
import java.util.List;

public class AccountInfoActivity extends Activity {

    private int accountId;
    private AccountInfoQuery accountInfoQuery;
    List<AccountInfo> accountInfoList;

    LinearLayout linearLayout;
    ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        accountInfoQuery = new AccountInfoQuery(getApplicationContext());
        addLayout();
        accountInfoList = new ArrayList<AccountInfo>();
        getSavedData();
        getAccountInfo();
    }

    private void getSavedData() {
        Intent intentAccountList = getIntent();
        Bundle accountInfo = intentAccountList.getExtras();

        if (accountInfo != null) {
            accountId = (Integer) accountInfo.get("accountId");
        }
    }

    private void getAccountInfo() {
        accountInfoList.addAll(accountInfoQuery.getAccountInfo(accountId));
        checkEmptyAccount();
    }

    private void checkEmptyAccount() {
        if (!accountInfoList.isEmpty()) displayAccountInfo();
        else {
            Toast.makeText(getApplicationContext(), "account is empty", Toast.LENGTH_SHORT).show();
        }
    }

    private void displayAccountInfo() {
        for (AccountInfo item : accountInfoList) {
            TextView textView = new TextView(getApplicationContext());
            textView.setText(item.getValue());
            textView.setTextColor(Color.BLACK);
            linearLayout.addView(textView);
            this.setContentView(scrollView);
        }
    }

    private void addLayout(){
        scrollView = new ScrollView(this);
        linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        scrollView.addView(linearLayout);
    }

    private void updateAccountAssociation(){

    }

    private void EndActivity() {
        AccountInfoActivity.this.finish();
    }

    @Override
    public void onBackPressed() {
        EndActivity();
    }
}