package com.example.hk.myapplication.client;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiFactory extends BaseHttpClient {

    public static Api create() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://192.168.100.5/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(getClient())
                .build();

        return retrofit.create(Api.class);
    }
}
