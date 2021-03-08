package com.example.mireaapp.Util;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Month;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class CalendarManager {

    public static final int MAX_WEEKS_IN_SEMESTER = 16; // Максимальное количество учебных недель в семестре

    public static final int START_DAY = 8; // Номер понедельника первой учебной недели
    public static final int START_MONTH = 2; // номер месяца, с которого начинается семестр. Например 2 = Февраль
    public static final int START_YEAR = 2021; // Год, в котором начинается семестр

    public static int getCurrentDayOfWeek(){
        Calendar c = Calendar.getInstance();
        Date currentDate = new Date();
        c.setTime(currentDate);
        int dayOfWeek = 7 - (8 - c.get(Calendar.DAY_OF_WEEK))%7 - 1;

        return dayOfWeek;
    }

    public static String getTextDayOfWeek(int dayOfWeek){
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

    /**
     Возвращает номер текущей недели (по текущей дате)
     @return номер недели (Int)
     */
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
            startDate = dateFormat.parse("08.02." + String.valueOf(currentYear));
        }

        long milliseconds = currentDate.getTime() - startDate.getTime();
        int week = (int) Math.ceil(((int) (milliseconds / (24 * 60 * 60 * 1000)))/7) + 1;
        return week;
    }

    /**
     Возвращает массив дат по номеру недели
     @param week номер недели в текущем семестре
     @return массив дат в недели длиной 6 (пн-сб)
     */
    public static ArrayList<Integer> getDaysInWeek(int week){
        ArrayList<Integer> daysInWeek = new ArrayList<Integer>();

        int currentDay = START_DAY;
        int currentMonth = START_MONTH - 1;

        for(int i = 1; i <= week; i++){
            for(int j = 0; j < 7; j++){
                if(i == week) {
                    daysInWeek.add(currentDay);
                }
                currentDay += 1;

                if(currentDay > getMaxDaysInMonth(currentMonth)){
                    currentMonth += 1;
                    currentDay = 1;
                }
            }
        }
        return daysInWeek;
    }

    /**
     Возвращает максимальное количество дней в месяце
     @param month номер месяца (1-12)
     @return кол-во дней (Int)
     */
    static int getMaxDaysInMonth(int month){
        Calendar mycal = new GregorianCalendar(START_YEAR, month, START_DAY);

        int daysInMonth = mycal.getActualMaximum(Calendar.DAY_OF_MONTH);
        return daysInMonth;
    }

    /**
     Возвращает строку месяца в родительном падеже
     @param week номер недели в текущем семестре
     @return название месяца (String)
     */
    public static String getMonthStringByWeek(int week){
        int currentDay = START_DAY;
        int currentMonth = START_MONTH - 1;

        String[] monthNames = { "января", "февраля", "марта", "апреля", "мая", "июня", "июля", "августа", "сентября", "октября", "ноября", "декабря" };
        String month = monthNames[currentMonth];

        for(int i = 1; i <= week; i++){
            for(int j = 0; j < 7; j++){
                if(i == week) {
                    month = monthNames[currentMonth];
                }
                currentDay += 1;

                if(currentDay > getMaxDaysInMonth(currentMonth)){
                    currentMonth += 1;
                    currentDay = 1;
                }
            }
        }

        return month;
    }
}
