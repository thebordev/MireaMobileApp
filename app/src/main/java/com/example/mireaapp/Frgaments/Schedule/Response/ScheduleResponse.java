package com.example.mireaapp.Frgaments.Schedule.Response;

import com.example.mireaapp.Frgaments.Schedule.Models.ScheduleModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ScheduleResponse {

    @SerializedName("bachelor")
    @Expose
    private ScheduleModel schedule;

    public ScheduleModel getSchedule() {
        return schedule;
    }

    @Override
    public String toString() {
        return "ScheduleResponse{" +
                "schedule=" + schedule +
                '}';
    }
}
