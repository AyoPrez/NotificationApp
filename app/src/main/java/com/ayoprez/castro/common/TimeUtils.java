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

    public long getDateInMilliseconds(String date, String time) throws ParseException {
        date = date.replace(date.contains("de") ? "de " : "of ", "").trim();
        date = date.substring(date.indexOf(' '), date.length()).trim();

        String day = date.substring(0, date.indexOf(' ')).trim();
        String month = date.substring(date.indexOf(' '), date.lastIndexOf(' ')).trim();
        String year = date.substring(date.lastIndexOf(' '), date.length()).trim();

        date = day+"/"+getMonthNumber(month)+"/"+year;

        Locale spanish = new Locale("es", "ES");

        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat sdf = new SimpleDateFormat("d/m/yyyy HH:mm", spanish);

        return sdf.parse(date + " " + time).getTime();
    }

    private int getMonthNumber(String month){
        Locale spanish = new Locale("es", "ES");
        Date date = null;
        try {
            date = new SimpleDateFormat("MMMM", spanish).parse(month);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.MONTH)+1;
    }

}
