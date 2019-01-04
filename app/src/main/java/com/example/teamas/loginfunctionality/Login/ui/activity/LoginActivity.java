package com.example.teamas.loginfunctionality.Login.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.teamas.loginfunctionality.Login.mainContracts.SignInContract;
import com.example.teamas.loginfunctionality.Login.mainContracts.presenters.SignInPresenter;
import com.example.teamas.loginfunctionality.Login.pojo.SignIn.SignInData;
import com.example.teamas.loginfunctionality.Login.utils.SecurityUtility;
import com.example.teamas.loginfunctionality.Login.utils.constants.LoginInApplication;
import com.example.teamas.loginfunctionality.Login.utils.AppStrings;
import com.example.teamas.loginfunctionality.R;
import com.google.gson.JsonObject;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, SignInContract.View {

    private EditText email;
    private EditText password;
    private Button signIn;
    private SignInPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initPresenter();
        setClickListeners();

    }

    public void initViews() {
        email = (EditText) findViewById(R.id.et_email);
        password = (EditText) findViewById(R.id.et_password);
        signIn = (Button) findViewById(R.id.btn_sign_in);
    }

    public void initPresenter() {
        presenter = new SignInPresenter(this, this);
    }

    public void setClickListeners() {
        signIn.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btn_sign_in:
                if (email.getText().toString().length() > 0 && LoginInApplication.isValidEmail(email.getText().toString())) {

                    String encodedPassword = SecurityUtility.getEncodedString(password.getText().toString());
                    JsonObject jsonObject = new JsonObject();
                    jsonObject.addProperty(AppStrings.EMAIL, email.getText().toString());
                    jsonObject.addProperty(AppStrings.PASSWORD, encodedPassword);
                    presenter.validateCredentials(jsonObject);
                } else {
                    email.setError("In valid Email");
                }
                break;
        }
    }

    @Override
    public void signInSuccessful(SignInData signInData) {
        Toast.makeText(this, "Sign In Successful", Toast.LENGTH_SHORT).show();
    }
}
