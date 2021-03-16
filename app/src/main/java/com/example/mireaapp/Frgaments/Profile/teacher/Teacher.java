package com.example.mireaapp.Frgaments.Profile.teacher;

public class Teacher {

    private String teacherName;
    private String teacherPosition;
    private String timeStart;
    private String timeEnd;
    private String nameItem;
    private String typeItem;

    public Teacher(String teacherName, String teacherPosition, String timeStart, String timeEnd, String nameItem, String typeItem) {
        this.teacherName = teacherName;
        this.teacherPosition = teacherPosition;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.nameItem = nameItem;
        this.typeItem = typeItem;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public String getTeacherPosition() {
        return teacherPosition;
    }

    public String getTimeStart() {
        return timeStart;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public String getNameItem() {
        return nameItem;
    }

    public String getTypeItem() {
        return typeItem;
    }

    public static String getTimeStart(int index) {
        String[] time = new String[] {"10:30", "12:10", "14:10", "15:50", "17:50", "19:30"};
        return time[index];
    }

    public static String getTimeEnd(int index){
        String[] time = new String[] {"10:30", "12:10", "14:10", "15:50", "17:50", "19:30"};
        return time[index];
    }
}
