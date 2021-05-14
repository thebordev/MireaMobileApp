package com.example.mireaapp.Utils;

import com.example.mireaapp.Frgaments.Profile.GroupsList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ScheduleApi {

    @GET("/api/schedule/groups")
    Call<GroupsList> groupJson();
}
