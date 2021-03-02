package com.example.mireaapp.Util;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class CalendarManager {

    public static int MAX_WEEKS_IN_SEMESTER = 16;

    public static int getCurrentDayOfWeek(){
        Calendar c = Calendar.getInstance();
        Date currentDate = new Date();
        c.setTime(currentDate);
        //c.getInstance().setFirstDayOfWeek(Calendar.MONDAY);
        int dayOfWeek = 7 - (8 - c.get(Calendar.DAY_OF_WEEK))%7 - 1;

        return dayOfWeek;
    }

    public static String getCurrentTextDayOfWeek(){
        int dayOfWeek = CalendarManager.getCurrentDayOfWeek();

        String day = "Понедельник";
        switch (dayOfWeek){
            case 0:
                day = "Понедельник";
                break;
            case 1:
                day = "Вторник";
                break;
            case 2:
                day = "Среда";
                break;
            case 3:
                day = "Четверг";
                break;
            case 4:
                day = "Пятница";
                break;
            case 5:
                day = "Суббота";
                break;
            case 6:
                day = "Воскресенье";
                break;
        }

        return day;
    }

    public static int getCurrentWeek() throws ParseException {
        Date currentDate = new Date();

        Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

        Date startDate;

        if (calendar.get(Calendar.MONTH) > 9) {
            startDate = dateFormat.parse("01.09." + String.valueOf(currentYear));
        }
        else{
            startDate = dateFormat.parse("09.02." + String.valueOf(currentYear));
        }

        long milliseconds = currentDate.getTime() - startDate.getTime();
        int week = (int) Math.ceil(((int) (milliseconds / (24 * 60 * 60 * 1000)))/7) + 1;
        return week;
    }

    public static String getCurrentMonthString(){
        Date currentDate = new Date();
        Calendar calendar = Calendar.getInstance();
        String[] monthNames = { "января", "февраля", "марта", "апреля", "мая", "июня", "июля", "августа", "сентября", "октября", "ноября", "декабря" };
        String month = monthNames[calendar.get(Calendar.MONTH)];
        return month;
    }
}
