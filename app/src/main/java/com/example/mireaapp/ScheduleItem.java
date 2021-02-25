package com.example.mireaapp;

class ScheduleItem {
    String title;
    String cabinet;
    String type;
    String timeStart;
    String timeEnd;

    public ScheduleItem(String title, String cabinet, String type, String timeStart, String timeEnd) {
        this.title = title;
        this.cabinet = cabinet;
        this.type = type;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
    }
}