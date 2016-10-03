package com.ayoprez.castro.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by ayo on 02.10.16.
 */

public class NotificationEvents extends RealmObject {

    @PrimaryKey
    private short id;
    private short eventId;
    private String eventTitle;

    public int getId() {
        return id;
    }

    public void setId(short id) {
        this.id = id;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(short eventId) {
        this.eventId = eventId;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }
}
