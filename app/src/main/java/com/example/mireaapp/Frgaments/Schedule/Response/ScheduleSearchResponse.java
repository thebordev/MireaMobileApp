package com.example.mireaapp.Frgaments.Schedule.Response;

import com.example.mireaapp.Frgaments.Schedule.Models.ScheduleModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ScheduleSearchResponse {

    @SerializedName("bachelor")
    @Expose
    private List<ScheduleModel> schedule;


}
