package com.example.mireaapp.Utils;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Credentials {

    public static final String BASE_URL = "http://64.227.34.48:5000";

    private static Retrofit getRetrofitInstance() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static ScheduleApi getApiService() {
        return getRetrofitInstance().create(ScheduleApi.class);
    }
}
