package com.ayoprez.castro;

import com.ayoprez.castro.common.TimeUtils;

import org.junit.Before;
import org.junit.Test;

import java.util.Locale;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

/**
 * Created by ayo on 26.09.16.
 */

public class TimeUtilsTest {

    String date;
    String dateEng;
    String time;

    @Before
    public void setUp(){
        dateEng = "Monday 12 of December of 2016";
        date = "Lunes 12 de Septiembre de 2016"; //MS 1473681900000
        time = "14:05";
    }

    @Test
    public void shouldSeparateCorrectlyTheDayNumber(){
        TimeUtils timeUtils = new TimeUtils();
        assertEquals(12, timeUtils.getDayNumberFromDate(date));
    }

    @Test
    public void shouldSeparateCorrectlyTheDayNumberEnglish(){
        TimeUtils timeUtils = new TimeUtils();
        assertEquals(12, timeUtils.getDayNumberFromDate(dateEng));
    }

    @Test
    public void shouldSeparateCorrectlyTheDayNumberShort(){
        TimeUtils timeUtils = new TimeUtils();

        String dateShort = "Lunes 2 de Septiembre de 2016";

        assertEquals(2, timeUtils.getDayNumberFromDate(dateShort));
    }

    @Test
    public void shouldSeparateCorrectlyTheDayNumberEnglishShort(){
        TimeUtils timeUtils = new TimeUtils();

        String dateShort = "Monday 2 of September of 2016";

        assertEquals(2, timeUtils.getDayNumberFromDate(dateShort));
    }

    @Test
    public void shouldSeparateCorrectlyTheDayNumberEnglishWithFormat(){
        TimeUtils timeUtils = new TimeUtils();

        String dateShort = "Monday 14th of September of 2016";

        assertEquals(14, timeUtils.getDayNumberFromDate(dateShort));
    }

    @Test
    public void shouldSeparateCorrectlyTheDayNumberEnglishShortWithFormat(){
        TimeUtils timeUtils = new TimeUtils();

        String dateShort = "Monday 2nd of September of 2016";

        assertEquals(2, timeUtils.getDayNumberFromDate(dateShort));
    }

    @Test
    public void shouldSeparateCorrectlyTheMonthText(){
        TimeUtils timeUtils = new TimeUtils();

        assertEquals("Septiembre", timeUtils.getMonthNameFromDate(date));
    }

    @Test
    public void shouldSeparateCorrectlyTheMonthTextEnglish(){
        TimeUtils timeUtils = new TimeUtils();

        String dateShort = "Monday 2nd of January of 2016";

        assertEquals("January", timeUtils.getMonthNameFromDate(dateShort));
    }

    @Test
    public void shouldConvertCorrectlyTheMonthToNumber(){
        TimeUtils timeUtils = new TimeUtils();

        assertEquals(9, timeUtils.getMonthNumberFromDate(new Locale("es", "ES"), date));
    }

    @Test
    public void shouldConvertCorrectlyTheMonthToNumberEnglish(){
        TimeUtils timeUtils = new TimeUtils();

        String dateShort = "Monday 2 of January of 2016";

        assertEquals(1, timeUtils.getMonthNumberFromDate(Locale.ENGLISH, dateShort));
    }

    @Test
    public void shouldSeparateCorrectlyTheYear(){
        TimeUtils timeUtils = new TimeUtils();

        assertEquals(2016, timeUtils.getYearFromDate(date));
    }

    @Test
    public void shouldSeparateCorrectlyTheHour(){
        TimeUtils timeUtils = new TimeUtils();

        assertEquals(14, timeUtils.getHourFromTime(time));
    }

    @Test
    public void shouldSeparateCorrectlyTheHourWithoutText(){
        TimeUtils timeUtils = new TimeUtils();

        String time = "Cadetes 14:50/ Juveniles 16:30 / Senior 21:00";

        assertEquals(14, timeUtils.getHourFromTime(time));
    }

    @Test
    public void shouldSeparateCorrectlyTheHourWhenThereIsOnlyHour(){
        TimeUtils timeUtils = new TimeUtils();

        String timeShort = "21h";

        assertEquals(21, timeUtils.getHourFromTime(timeShort));
    }

    @Test
    public void shouldSeparateCorrectlyTheHourWhenThereIsOnlyHourShort(){
        TimeUtils timeUtils = new TimeUtils();

        String timeShort = "5PM";

        assertEquals(5, timeUtils.getHourFromTime(timeShort));
    }

    @Test
    public void shouldSeparateCorrectlyTheHourShort(){
        TimeUtils timeUtils = new TimeUtils();

        String timeShort = "6:50PM";

        assertEquals(6, timeUtils.getHourFromTime(timeShort));
    }

    @Test
    public void shouldSeparateCorrectlyTheDayMinute(){
        TimeUtils timeUtils = new TimeUtils();

        assertEquals(05, timeUtils.getMinutesFromTime(time));
    }

    @Test
    public void shouldSeparateCorrectlyTheDayMinuteShort(){
        TimeUtils timeUtils = new TimeUtils();

        String timeShort = "6:05PM";

        assertEquals(05, timeUtils.getMinutesFromTime(timeShort));
    }

    @Test
    public void shouldSeparateCorrectlyTheDayMinuteWhenThereIsNoMinutes(){
        TimeUtils timeUtils = new TimeUtils();

        String timeShort = "6PM";

        assertEquals(00, timeUtils.getMinutesFromTime(timeShort));
    }

    @Test
    public void shouldGetEnglishLocaleFromDate(){
        TimeUtils timeUtils = new TimeUtils();

        assertEquals(Locale.ENGLISH, timeUtils.getLocaleFromDate(dateEng));
    }

    @Test
    public void shouldGetSpanishLocaleFromDate(){
        TimeUtils timeUtils = new TimeUtils();

        assertEquals(new Locale("es", "ES"), timeUtils.getLocaleFromDate(date));
    }

    @Test
    public void shouldGetTheRightMillisecondsToTheDate(){
        TimeUtils timeUtils = new TimeUtils();

        assertEquals("1473681900000", String.valueOf(timeUtils.getDateInMilliseconds(date, time)));
    }

    @Test
    public void shouldGetTheRightMillisecondsToTheDateEnglish(){
        TimeUtils timeUtils = new TimeUtils();

        String dateShort = "Monday 12 of September of 2016";

        assertEquals("1473681900000", String.valueOf(timeUtils.getDateInMilliseconds(dateShort, time)));
    }

    @Test
    public void shouldCompareAndIndicateIfIsAFutureDate(){
        TimeUtils timeUtils = new TimeUtils();

        String date = "Miércoles 28 de Septiembre de 2016";
        String time = "14:55";

        assertTrue(timeUtils.isFutureDate(date, time));
    }

    @Test
    public void shouldCompareAndIndicateIfIsAFutureDateEnglish(){
        TimeUtils timeUtils = new TimeUtils();

        String dateEng = "Wednesday 28 of January of 2017";
        String time = "2:55PM";

        assertTrue(timeUtils.isFutureDate(dateEng, time));
    }

    @Test
    public void shouldCompareAndIndicateIfIsNotAFutureDate(){
        TimeUtils timeUtils = new TimeUtils();

        String date = "Miércoles 28 de Septiembre de 2014";
        String time = "14:55";

        assertFalse(timeUtils.isFutureDate(date, time));
    }

}
