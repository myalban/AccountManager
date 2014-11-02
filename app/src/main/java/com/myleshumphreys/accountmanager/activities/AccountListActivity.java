package com.myleshumphreys.accountmanager.activities;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import com.myleshumphreys.accountmanager.R;
import com.myleshumphreys.accountmanager.adapters.AccountListAdapter;
import com.myleshumphreys.accountmanager.models.Account;
import com.myleshumphreys.accountmanager.data.AccountQuery;

public class AccountListActivity extends Activity {

    private AccountQuery accountQuery;
    private ListView accountListView;
    List<Account> accountList;
    private int userId;
    private AccountListAdapter accountListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        accountQuery = new AccountQuery(getApplicationContext());
        accountList = new ArrayList<Account>();
        getSavedData();
        getAccounts();
    }

    private void getSavedData() {
        Intent intentAccountList = getIntent();
        Bundle userInfo = intentAccountList.getExtras();

        if (userInfo != null) {
            userId = (Integer) userInfo.get("userId");
        }
    }

    private void setupAccountListView() {
        setContentView(R.layout.activity_account_list);
        accountListView = (ListView) findViewById(R.id.listViewAccountList);
        populateAccountList();
    }

    private void addItemsToAccountList() {
        accountList.clear();
        accountList.addAll(accountQuery.getAllAccounts(userId));
        accountListAdapter.notifyDataSetChanged();
    }

    private void getAccounts() {
        int accountCount = accountQuery.getCountById(userId);

        if (accountCount != 0) {
            setupAccountListView();
            addItemsToAccountList();

        } else {
            accountList.clear();
        }
    }

    private void populateAccountList() {
        accountListAdapter = new AccountListAdapter(this, accountList);
        accountListView.setAdapter(accountListAdapter);
    }

    private void logout() {
        Intent intentLogin = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intentLogin);
        endActivity();
    }

    private void logoutDialog() {
        AlertDialog.Builder logoutAlert = new AlertDialog.Builder(this);
        logoutAlert.setTitle("Log Out");
        logoutAlert.setMessage("Are you sure you want to log out?");

        logoutAlert.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                logout();
            }
        });

        logoutAlert.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                    }
                });

        logoutAlert.show();
    }

    @Override
    public void onResume() {
        super.onResume();
        getAccounts();
    }

    private void endActivity() {
        AccountListActivity.this.finish();
    }

    @Override
    public void onBackPressed() {
        logoutDialog();
    }
}