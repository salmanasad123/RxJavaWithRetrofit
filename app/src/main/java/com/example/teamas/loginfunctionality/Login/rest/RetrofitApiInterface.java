package com.example.teamas.loginfunctionality.Login.rest;

import com.example.teamas.loginfunctionality.Login.pojo.SignIn.SignInResponse;
import com.google.gson.JsonObject;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RetrofitApiInterface {

    @POST("login/token")
    Observable<SignInResponse> signInUser(@Body JsonObject body);

}
