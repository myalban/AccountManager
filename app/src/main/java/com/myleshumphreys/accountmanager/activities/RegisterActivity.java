package com.myleshumphreys.accountmanager.activities;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.myleshumphreys.accountmanager.R;
import com.myleshumphreys.accountmanager.data.UserQuery;
import com.myleshumphreys.accountmanager.models.User;
import com.myleshumphreys.accountmanager.toasts.RegisteredActivityToasts;
import com.myleshumphreys.accountmanager.validation.InputValidation;
import com.myleshumphreys.accountmanager.validation.UserValidation;

public class RegisterActivity extends Activity {

    private EditText editTextRegisterEmailAddress;
    private EditText editTextRegisterPassword;
    private EditText editTextRegisterConfirmPassword;
    private Button buttonConfirmRegister;
    private UserQuery userQuery;

    InputValidation inputValidation = new InputValidation();
    UserValidation userValidation = new UserValidation();
    RegisteredActivityToasts toast = new RegisteredActivityToasts();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        userQuery = new UserQuery(getApplicationContext());
        addWidgets();
        addTextWatcher();
        buttonRegisterListener();
    }

    private void addWidgets() {
        editTextRegisterEmailAddress = (EditText) findViewById(R.id.editTextRegisterEmailAddress);
        editTextRegisterPassword = (EditText) findViewById(R.id.editTextRegisterPassword);
        editTextRegisterConfirmPassword = (EditText) findViewById(R.id.editTextRegisterConfirmPassword);
        buttonConfirmRegister = (Button) findViewById(R.id.buttonConfirmRegister);
    }

    private void buttonRegisterListener() {
        buttonConfirmRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailAddress = getEmailAddress();
                String password = getPassword();
                String confirmPassword = getConfirmPassword();
                validateUserInput(emailAddress, password, confirmPassword);
            }
        });
    }

    private void getUserInput(EditText editTextEmailAddress, EditText editTextPassword, EditText editTextRegisterConfirmPassword) {
        String emailAddress = editTextEmailAddress.getText().toString();
        String password = editTextPassword.getText().toString();
        String confirmPassword = editTextRegisterConfirmPassword.getText().toString();
        checkFieldsForEmptyValues(emailAddress, password, confirmPassword);
    }

    private void checkFieldsForEmptyValues(String emailAddress, String password, String confirmPassword) {
        boolean validEmailAddress = inputValidation.IsNullOrEmpty(emailAddress);
        boolean validPassword = inputValidation.IsNullOrEmpty(password);
        boolean validConfirmPassword = inputValidation.IsNullOrEmpty(confirmPassword);

        if (validEmailAddress && validPassword && validConfirmPassword) {
            buttonConfirmRegister.setEnabled(true);
        } else {
            buttonConfirmRegister.setEnabled(false);
        }
    }

    private void validateUserInput(String emailAddress, String password, String confirmPassword) {
        boolean validEmailAddress = userValidation.validateEmailAddress(emailAddress);
        boolean validPassword = userValidation.validatePasswordLength(password);
        boolean passwordMatch = inputValidation.stringComparison(password, confirmPassword);
        boolean userExists = userQuery.userExists(emailAddress);

        if (validPassword && passwordMatch && !userExists && validEmailAddress) {
            registerUser(emailAddress, password);
        } else {
            DisplayInvalidToast(userExists, validEmailAddress, validPassword, passwordMatch);
        }
    }

    private void registerUser(String emailAddress, String password) {
        User newUser = new User(userQuery.getCount(), emailAddress, password);
        userQuery.create(newUser);
        toast.registeredUserToast(getApplicationContext());
        endActivity();
    }

    private void DisplayInvalidToast(boolean userExists, boolean validateEmailAddress,
                                     boolean validPassword, boolean passwordMatch) {
        if (!validateEmailAddress) {
            toast.invalidEmailAddressToast(getApplicationContext());
        } else if (userExists) {
            toast.userExistsToast(getApplicationContext());
        } else if (!validPassword) {
            toast.invalidPasswordToast(getApplicationContext());
        } else if (!passwordMatch) {
            toast.invalidPasswordMatchToast(getApplicationContext());
        }
    }

    private void addTextWatcher() {
        editTextRegisterEmailAddress.addTextChangedListener(textWatcher);
        editTextRegisterPassword.addTextChangedListener(textWatcher);
        editTextRegisterConfirmPassword.addTextChangedListener(textWatcher);
        getUserInput(editTextRegisterEmailAddress, editTextRegisterPassword, editTextRegisterConfirmPassword);
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
            getUserInput(editTextRegisterEmailAddress, editTextRegisterPassword, editTextRegisterConfirmPassword);
        }
    };

    private String getEmailAddress() {
        return String.valueOf(editTextRegisterEmailAddress.getText()).toLowerCase();
    }

    private String getPassword() {
        return String.valueOf(editTextRegisterPassword.getText());
    }

    private String getConfirmPassword() {
        return String.valueOf(editTextRegisterConfirmPassword.getText());
    }

    private void endActivity() {
        RegisterActivity.this.finish();
    }

    @Override
    public void onBackPressed() {
        endActivity();
    }
}