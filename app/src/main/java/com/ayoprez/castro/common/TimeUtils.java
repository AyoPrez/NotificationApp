package com.ayoprez.castro.common;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


/**
 * Created by ayo on 18.09.16.
 */
public class TimeUtils {

    public long getDateInMilliseconds(String date, String time) {

        Locale locale = getLocaleFromDate(date);

        String someDate = getDayNumberFromDate(date)+"."+
                getMonthNumberFromDate(locale, date)+"."+
                getYearFromDate(date)+"."+
                getHourFromTime(time)+"."+
                getMinutesFromTime(time);
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy.HH.mm");
        Date total = null;
        try {
            total = sdf.parse(someDate);
        } catch (ParseException e) {
            return 0;
        }
        return total.getTime();
    }

    private String purifyDate(String date){
        date = date.replace(date.contains(" de ") ? "de " : "of ", "").trim();
        date = date.substring(date.indexOf(' '), date.length()).trim();
        return date;
    }

    public int getDayNumberFromDate(String date) {
        String day = purifyDate(date);
        day = day.substring(0, day.indexOf(' ')).trim();
        day = day.replaceAll("\\D+","");
        return Integer.valueOf(day);
    }

    public int getMonthNumberFromDate(Locale locale, String dateComplete) {
        String month = getMonthNameFromDate(dateComplete);

        Date date = null;
        try {
            date = new SimpleDateFormat("MMMM", locale).parse(month);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.MONTH)+1;
    }

    public String getMonthNameFromDate(String date) {
        String month = purifyDate(date);
        month = month.substring(month.indexOf(' '), month.lastIndexOf(' ')).trim();
        return month;
    }

    public int getYearFromDate(String date) {
        String year = purifyDate(date);
        year = year.substring(year.lastIndexOf(' '), year.length()).trim();
        return Integer.valueOf(year);
    }

    public int getHourFromTime(String time) {
        String hour = time.replaceAll("\\D+","");

        if(hour.length() == 3 || hour.length() == 1) {
          hour = hour.substring(0, 1);
        } else {
          hour = hour.substring(0, 2);
        }

        return Integer.valueOf(hour);
    }

    public int getMinutesFromTime(String time) {
        String minute = time.replaceAll("\\D+","");

        switch (minute.length()){
            case 1:
                minute = "00";
                break;
            case 3:
                minute = minute.substring(1, 3);
                break;
            default:
                minute = minute.substring(2, 4);
        }

        return Integer.valueOf(minute);
    }

    public boolean isFutureDate(String date, String time) {
        long milliseconds = getDateInMilliseconds(date, time);
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTimeInMillis(milliseconds);

        long currentDateMilliseconds = Calendar.getInstance().getTimeInMillis();
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTimeInMillis(currentDateMilliseconds);

        return calendar1.after(calendar2);
    }

    public Locale getLocaleFromDate(String date) {
        return date.contains(" of ") ? Locale.ENGLISH : new Locale("es", "ES");
    }

}
