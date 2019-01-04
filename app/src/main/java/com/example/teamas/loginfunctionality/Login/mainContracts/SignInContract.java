package com.example.teamas.loginfunctionality.Login.mainContracts;

import com.example.teamas.loginfunctionality.Login.pojo.SignIn.SignInData;
import com.google.gson.JsonObject;

public interface SignInContract {

    interface View {
        void signInSuccessful(SignInData signInData);
    }


    interface Presenter {

        void validateCredentials(JsonObject body);

    }
}
