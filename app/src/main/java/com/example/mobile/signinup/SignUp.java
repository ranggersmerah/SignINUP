package com.example.mobile.signinup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import com.scottyab.showhidepasswordedittext.ShowHidePasswordEditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.transform.Result;

import static com.example.mobile.signinup.Login.regEx;

public class SignUp extends AppCompatActivity {

    private AutoCompleteTextView mEmail;
    private ShowHidePasswordEditText mPassword,mConfirmPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

    }

    private void InitView(){

        mEmail = findViewById(R.id.email);
        mPassword = findViewById(R.id.password);
        mConfirmPass = findViewById(R.id.confirm_password);

        Button Submit = findViewById(R.id.sign_in);
        Submit.setOnClickListener(view -> CheckValidation());

    }

    private void CheckValidation() {

        // Reset errors.
        mEmail.setError(null);
        mPassword.setError(null);
        mConfirmPass.setError(null);

        // Store values at the time of the login attempt.
        String Email = mEmail.getText().toString();
        String password = mPassword.getText().toString();
        String ConPassword = mConfirmPass.getText().toString();

        boolean cancel = false;
        View focusView = null;
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(Email);

        if (TextUtils.isEmpty(Email)) {
            mEmail.setError(getString(R.string.error_field_required));
            focusView = mEmail;
            cancel = true;
        }else if (!m.find()) {
            mEmail.setError(getString(R.string.email_not_valid));
            focusView = mEmail;
            cancel = true;
        }else if (TextUtils.isEmpty(password)) {
            mPassword.setError(getString(R.string.error_field_required));
            focusView = mPassword;
            cancel = true;
        }else if (isPasswordValid(password)){
            mPassword.setError(getString(R.string.error_invalid_password));
            focusView = mPassword;
            cancel = true;
        }else if (!ConPassword.equals(password)){
            mPassword.setError(getString(R.string.not_match));
            focusView = mPassword;
            cancel = true;
        }


        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            setResult(RESULT_OK);
            finish();
        }
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() <= 5;
    }

}