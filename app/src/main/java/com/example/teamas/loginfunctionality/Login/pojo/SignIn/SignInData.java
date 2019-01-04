package com.example.teamas.loginfunctionality.Login.pojo.SignIn;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SignInData {
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("expires_in")
    @Expose
    private Integer expiresIn;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Integer expiresIn) {
        this.expiresIn = expiresIn;
    }

}
