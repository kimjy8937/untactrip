package com.example.untactrip3.service;


import com.example.untactrip3.api.SafeCasterApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static RetrofitClient instance = null;
    private static SafeCasterApi safeCasterApi;
    private static String baseUrl = "https://apis.openapi.sk.com";

    private RetrofitClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        safeCasterApi = retrofit.create(SafeCasterApi.class);
    }

    public static RetrofitClient getInstance() {
        if (instance == null) {
            instance = new RetrofitClient();
        }
        return instance;
    }

    public static SafeCasterApi getSafeCasterApi() {
        return safeCasterApi;
    }

}
