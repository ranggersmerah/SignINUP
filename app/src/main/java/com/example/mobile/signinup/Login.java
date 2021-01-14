package com.example.mobile.signinup;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import com.scottyab.showhidepasswordedittext.ShowHidePasswordEditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Login extends AppCompatActivity {

    public static final String regEx = "\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}\\b";

    private AutoCompleteTextView mEmail;
    private ShowHidePasswordEditText mPasswordView;

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        InitView();

    }

    private void InitView(){

        mEmail = findViewById(R.id.email);
        mPasswordView = findViewById(R.id.password);
        mPasswordView.setOnEditorActionListener((textView, id, keyEvent) -> {
            if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                attemptLogin();
                return true;
            }
            return false;
        });

        Button SignIn = findViewById(R.id.sign_in);
        SignIn.setOnClickListener(view -> attemptLogin());
        Button SignUp = findViewById(R.id.sign_up);
        SignUp.setOnClickListener(view -> {
            Intent i = new Intent();
            i.setClass(Login.this, SignUp.class);
            startActivityForResult(i,123);
        });

    }

    private void attemptLogin() {

        // Reset errors.
        mEmail.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        String Email = mEmail.getText().toString();
        String password = mPasswordView.getText().toString();

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
            mPasswordView.setError(getString(R.string.error_field_required));
            focusView = mPasswordView;
            cancel = true;
        }else if (isPasswordValid(password)){
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }


        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            new Login();
        }
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() <= 5;
    }

    private Login(){
        Intent i = new Intent();
        i.setClass(Login.this, MainActivity.class);
        finish();
    }


}