package com.example.teamas.loginfunctionality.Login.pojo.SignIn;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SignInResponse extends BaseResponse {
    @SerializedName("data")
    @Expose
    private SignInData signInData;

    public SignInData getSignInData() {
        return signInData;
    }

    public void setSignInData(SignInData signInData) {
        this.signInData = signInData;
    }
}

