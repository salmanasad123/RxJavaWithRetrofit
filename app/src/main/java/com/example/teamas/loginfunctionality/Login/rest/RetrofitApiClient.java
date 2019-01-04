package com.example.teamas.loginfunctionality.Login.rest;

import com.example.teamas.loginfunctionality.Login.utils.AppStrings;
import com.google.gson.Gson;

import java.io.IOException;

import io.reactivex.internal.schedulers.RxThreadFactory;
import io.reactivex.plugins.RxJavaPlugins;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitApiClient {

    private static Retrofit retrofitClient;

    public static Retrofit getPublicClient() {

        if (retrofitClient == null) {
            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request newRequest;
                    newRequest = chain.request().newBuilder()
                            .addHeader("Content-Type", "application/json")
                            .build();
                    return chain.proceed(newRequest);
                }
            }).build();
            retrofitClient = new Retrofit.Builder()
                    .client(client)
                    .baseUrl(AppStrings.URL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofitClient;
    }

    public static RetrofitApiInterface getApiService() {
        return getPublicClient().create(RetrofitApiInterface.class);
    }
}

