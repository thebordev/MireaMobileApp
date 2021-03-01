package com.example.mireaapp;

public class ScheduleItem {
    private String title;
    private String cabinet;
    private String type;
    private String timeStart;
    private String timeEnd;

    public ScheduleItem(String title, String cabinet, String type, String timeStart, String timeEnd) {
        this.title = title;
        this.cabinet = cabinet;
        this.type = type;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
    }

    public String getTimeStart(){
        return this.timeStart;
    }

    public String getTimeEnd(){
        return this.timeEnd;
    }

    public String getType(){
        return this.type;
    }

    public String getCabinet(){
        return this.cabinet;
    }

    public String getScheduleItemName(){
        return this.title;
    }

    public static String getTimeStart(int index) {
        String time = "";
        switch (index) {
            case 0:
                time = "9:00";
                break;
            case 1:
                time = "10:40";
                break;
            case 2:
                time = "12:40";
                break;
            case 3:
                time = "14:20";
                break;
            case 4:
                time = "16:20";
                break;
            case 5:
                time = "18:00";
                break;
        }
        return time;
    }

    public static String getTimeEnd(int index){
        String time = "";
        switch (index){
            case 0:
                time = "10:30";
                break;
            case 1:
                time = "12:10";
                break;
            case 2:
                time = "14:10";
                break;
            case 3:
                time = "15:50";
                break;
            case 4:
                time = "17:50";
                break;
            case 5:
                time = "19:30";
                break;
        }
        return time;
    }
}