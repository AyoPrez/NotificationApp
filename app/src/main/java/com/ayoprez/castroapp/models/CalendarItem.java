package com.ayoprez.castroapp.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by ayo on 16.08.16.
 */
public class CalendarItem extends RealmObject{

    @PrimaryKey
    private int id;
    private String title;
    private CalendarItemMeta meta;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public CalendarItemMeta getMeta() {
        return meta;
    }

    public void setMeta(CalendarItemMeta meta) {
        this.meta = meta;
    }
}
