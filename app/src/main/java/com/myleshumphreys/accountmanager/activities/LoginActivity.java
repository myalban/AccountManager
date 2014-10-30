package com.myleshumphreys.accountmanager.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.myleshumphreys.accountmanager.R;
import com.myleshumphreys.accountmanager.data.UserQuery;
import com.myleshumphreys.accountmanager.toasts.LoginActivityToasts;
import com.myleshumphreys.accountmanager.validation.InputValidation;

public class LoginActivity extends Activity {

    private EditText editTextEmailAddress;
    private EditText editTextPassword;
    private Button buttonSignIn;
    private Button buttonRegister;
    private Button buttonRecover;
    private UserQuery userQuery;

    InputValidation inputValidation = new InputValidation();
    LoginActivityToasts toast = new LoginActivityToasts();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        userQuery = new UserQuery(getApplicationContext());
        addWidgets();
        addTextWatcher();
        loginButtonListener();
        registerButtonListener();
        recoverButtonListener();
    }

    private void addWidgets() {
        editTextEmailAddress = (EditText) findViewById(R.id.editTextEmailAddress);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        buttonSignIn = (Button) findViewById(R.id.buttonSignIn);
        buttonRegister = (Button) findViewById(R.id.buttonRegister);
        buttonRecover = (Button) findViewById(R.id.buttonRecover);
    }

    private void getUserInput(EditText editTextEmailAddress, EditText editTextPassword) {
        String emailAddress = editTextEmailAddress.getText().toString();
        String password = editTextPassword.getText().toString();
        checkFieldsForEmptyValues(emailAddress, password);
    }

    private void checkFieldsForEmptyValues(String emailAddress, String password) {
        boolean validEmailAddress = inputValidation.IsNullOrEmpty(emailAddress);
        boolean validPassword = inputValidation.IsNullOrEmpty(password);

        if (validEmailAddress && validPassword) {
            buttonSignIn.setEnabled(true);
        } else {
            buttonSignIn.setEnabled(false);
        }
    }

    private void loginButtonListener() {
        buttonSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailAddress = getEmailAddress();
                String password = getPassword();
                boolean validUser = userQuery.validateCredentials(emailAddress, password);
                if(validUser){
                    validLogin(emailAddress, password);
                }
                else{
                    toast.inValidLoginToast(getApplicationContext());
                }
            }
        });
    }

    private void validLogin(String emailAddress, String password) {
        toast.validLoginToast(getApplicationContext());
        int userId = GetValidUserId(emailAddress, password);
        Intent intentAccountList = new Intent(getApplicationContext(), AccountListActivity.class);
        intentAccountList.putExtra("userId", userId);
        startActivity(intentAccountList);
        EndActivity();
    }

    private void addTextWatcher() {
        editTextEmailAddress.addTextChangedListener(textWatcher);
        editTextPassword.addTextChangedListener(textWatcher);
        getUserInput(editTextEmailAddress, editTextPassword);
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
            getUserInput(editTextEmailAddress, editTextPassword);
        }
    };

    private int GetValidUserId(String emailAddress, String password) {
        return userQuery.getId(emailAddress, password);
    }

    private String getEmailAddress() {
        return String.valueOf(editTextEmailAddress.getText()).toLowerCase();
    }

    private String getPassword() {
        return String.valueOf(editTextPassword.getText());
    }

    private void registerButtonListener() {
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentRegister = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intentRegister);
            }
        });
    }

    private void recoverButtonListener() {
        buttonRecover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentRegister = new Intent(getApplicationContext(), RecoverActivity.class);
                startActivity(intentRegister);
            }
        });
    }

    private void EndActivity() {
        LoginActivity.this.finish();
    }
}