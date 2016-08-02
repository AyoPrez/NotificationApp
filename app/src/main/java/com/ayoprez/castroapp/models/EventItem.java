package com.ayoprez.castroapp.models;


import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by ayo on 19.06.16.
 */
public class EventItem extends RealmObject{

    @PrimaryKey
    private int id;
    private String title;
    private String subtitle;
    private String image;

    public EventItem setObject(EventItem eventItem){
        this.id = eventItem.getId();
        this.title = eventItem.getTitle();
        this.subtitle = eventItem.getSubtitle();
        this.image = eventItem.getImage();
        return this;
    }

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

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
