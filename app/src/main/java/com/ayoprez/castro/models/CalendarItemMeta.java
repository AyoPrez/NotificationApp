package com.ayoprez.castro.models;

import io.realm.RealmObject;

/**
 * Created by ayo on 21.08.16.
 */
public class CalendarItemMeta extends RealmObject{

    private String calendar;

    public String getCalendar() {
        return calendar;
    }

    public void setCalendar(String calendar) {
        this.calendar = calendar;
    }
}
