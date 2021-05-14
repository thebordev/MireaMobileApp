package com.example.mireaapp.Frgaments.Schedule.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class ScheduleModel implements Parcelable {

    private String title;
    private String cabinet;
    private String type;
    private String timeStart;
    private String timeEnd;
    private String teacher;

    public ScheduleModel(String title, String cabinet, String type, String timeStart, String timeEnd, String teacher) {
        this.title = title;
        this.cabinet = cabinet;
        this.type = type;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.teacher = teacher;
    }

    protected ScheduleModel(Parcel in) {
        title = in.readString();
        cabinet = in.readString();
        type = in.readString();
        timeStart = in.readString();
        timeEnd = in.readString();
        teacher = in.readString();
    }

    public static final Creator<ScheduleModel> CREATOR = new Creator<ScheduleModel>() {
        @Override
        public ScheduleModel createFromParcel(Parcel in) {
            return new ScheduleModel(in);
        }

        @Override
        public ScheduleModel[] newArray(int size) {
            return new ScheduleModel[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCabinet() {
        return cabinet;
    }

    public void setCabinet(String cabinet) {
        this.cabinet = cabinet;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(cabinet);
        dest.writeString(type);
        dest.writeString(timeStart);
        dest.writeString(timeEnd);
        dest.writeString(teacher);
    }
}
