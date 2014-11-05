package com.myleshumphreys.accountmanager.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.myleshumphreys.accountmanager.data.AccountInfoQuery;
import com.myleshumphreys.accountmanager.data.AccountQuery;
import com.myleshumphreys.accountmanager.models.Account;
import com.myleshumphreys.accountmanager.models.AccountInfo;
import com.myleshumphreys.accountmanager.validation.InputValidation;
import com.myleshumphreys.accountmanager.widgets.ButtonWidget;
import com.myleshumphreys.accountmanager.widgets.EditTextWidget;

import java.util.ArrayList;
import java.util.List;

public class CreateAccountActivity extends Activity {

    private List<AccountInfo> accountInfoList;

    private List<EditText> editTextList;
    private List<String> editTextValueList;

    private int userId;
    private AccountInfoQuery accountInfoQuery;
    private AccountQuery accountQuery;

    private LinearLayout linearLayout;
    private ScrollView scrollView;
    private Button newButton;

    private EditText associationEditText;
    private EditText usernameEditText;
    private EditText emailAddressEditText;
    private EditText passwordEditText;

    private int newAccountId;

    private Button saveAccount;
    InputValidation inputValidation = new InputValidation();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        accountInfoList = new ArrayList<AccountInfo>();
        editTextList = new ArrayList<EditText>();
        editTextValueList = new ArrayList<String>();
        accountInfoQuery = new AccountInfoQuery(getApplicationContext());
        accountQuery = new AccountQuery(getApplicationContext());

        getSavedData();
        addLayout();
        addDefaultOptions();
        addNewValueButton();
        newValueButtonListener();
        addSaveAccountButton();
        addTextWatcher();
        saveAccountListener();
    }

    private void addLayout() {
        scrollView = new ScrollView(this);
        linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        scrollView.addView(linearLayout);
    }

    private void addDefaultOptions() {
        associationEditText = new EditTextWidget().addEditText("Association", this);
        linearLayout.addView(associationEditText);

        usernameEditText = new EditTextWidget().addEditText("Username", this);
        linearLayout.addView(usernameEditText);

        emailAddressEditText = new EditTextWidget().addEditText("Email Address", this);
        linearLayout.addView(emailAddressEditText);

        passwordEditText = new EditTextWidget().addEditText("Password", this);
        linearLayout.addView(passwordEditText);
    }

    private void addSaveAccountButton() {
        saveAccount = new ButtonWidget().addButton("Save Account", this);
        linearLayout.addView(saveAccount);
    }

    private void addNewValueButton() {
        newButton = new ButtonWidget().addButton("Add Value", this);
        linearLayout.addView(newButton);
    }

    private void newValueButtonListener() {
        newButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ((LinearLayout) newButton.getParent()).removeView(newButton);
                ((LinearLayout) saveAccount.getParent()).removeView(saveAccount);

                addEditView();

                linearLayout.addView(newButton);
                linearLayout.addView(saveAccount);
            }
        });

        this.setContentView(scrollView);
    }

    private void saveAccountListener() {
        saveAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveAccount();
                saveAccountInfo();
                saveSucceededToast();
                EndActivity();
            }
        });
    }

    private void getNewWidgetValues() {
        addUsernameValue();
        addEmailAddressValue();
        addPasswordValue();

        for (EditText item : editTextList) {
            if (inputValidation.IsNullOrEmpty(item.getText().toString())) {
                editTextValueList.add(item.getText().toString());
            }
        }
    }

    private void checkFieldsForEmptyValues(EditText associationEditText) {
        String association = associationEditText.getText().toString();
        boolean validAssociation = inputValidation.IsNullOrEmpty(association);

        if (validAssociation) {
            saveAccount.setEnabled(true);
        } else {
            saveAccount.setEnabled(false);
        }
    }

    private void addTextWatcher() {
        associationEditText.addTextChangedListener(textWatcher);
        checkFieldsForEmptyValues(associationEditText);
    }

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override
        public void afterTextChanged(Editable editable) {
            checkFieldsForEmptyValues(associationEditText);
        }
    };

    private void addEditView() {
        EditText editTextItem = new EditTextWidget().addEditText("New Value", CreateAccountActivity.this);
        editTextList.add(editTextItem);
        linearLayout.addView(editTextItem);
    }

    private void removeEditView(EditText editTextItem) {

    }

    private void getAllEnteredValues() {
        getNewWidgetValues();
        newAccountId++;
        for (String item : editTextValueList)
            accountInfoList.add(new AccountInfo(accountInfoQuery.getCount(), item, newAccountId, 1));
    }

    private void saveAccount() {
        String association = getAssociationValue();
        Account newAccount = new Account(accountQuery.getCount(), association, userId);
        accountQuery.create(newAccount);
        newAccountId = newAccount.getId();
    }

    private void saveAccountInfo() {
        getAllEnteredValues();
        for (AccountInfo item : accountInfoList) {
            accountInfoQuery.create(item);
        }
    }

    private String getAssociationValue() {
        return String.valueOf(associationEditText.getText());
    }

    private void addUsernameValue() {
        String username = String.valueOf(usernameEditText.getText());
        if (inputValidation.IsNullOrEmpty(username)) {
            editTextValueList.add(String.valueOf(username));
        }
    }

    private void addEmailAddressValue() {
        String emailAddress = String.valueOf(emailAddressEditText.getText());
        if (inputValidation.IsNullOrEmpty(emailAddress)) {
            editTextValueList.add(String.valueOf(emailAddress));
        }
    }

    private void addPasswordValue() {
        String password = String.valueOf(passwordEditText.getText());
        if (inputValidation.IsNullOrEmpty(password)) {
            editTextValueList.add(String.valueOf(password));
        }
    }

    private void getSavedData() {
        Intent intentAccountList = getIntent();
        Bundle userInfo = intentAccountList.getExtras();

        if (userInfo != null) {
            userId = (Integer) userInfo.get("userId");
        }
    }

    private void saveSucceededToast() {
        Toast.makeText(getApplicationContext(), "Account Created", Toast.LENGTH_SHORT).show();
    }

    private void saveFailedToast() {

    }

    private void EndActivity() {
        CreateAccountActivity.this.finish();
    }

    @Override
    public void onBackPressed() {
        EndActivity();
    }
}