package com.ayoprez.castro.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by ayo on 19.06.16.
 */
public class EventItem extends RealmObject {

    @PrimaryKey
    private int id;
    private String title;
    private EventItemMeta meta;

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

    public EventItemMeta getMeta() {
        return meta;
    }

    public void setMeta(EventItemMeta meta) {
        this.meta = meta;
    }
}
