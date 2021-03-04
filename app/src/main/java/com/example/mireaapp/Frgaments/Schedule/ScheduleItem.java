package com.example.mireaapp.Frgaments.Schedule;

public class ScheduleItem {
    private String title;
    private String cabinet;
    private String type;
    private String timeStart;
    private String timeEnd;
    private String teacher;

    public ScheduleItem(String title, String cabinet, String type, String timeStart, String timeEnd, String teacher) {
        this.title = title;
        this.cabinet = cabinet;
        this.type = type;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.teacher = teacher;
    }

    //
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

    public String getTeacher() { return this.teacher; }

    public static String getTimeStart(int index) {
        String[] time = new String[] {"9:00", "10:40", "12:40", "14:20", "16:20", "18:00"};
        return time[index];
    }

    public static String getTimeEnd(int index){
        String[] time = new String[] {"10:30", "12:10", "14:10", "15:50", "17:50", "19:30"};
        return time[index];
    }
}