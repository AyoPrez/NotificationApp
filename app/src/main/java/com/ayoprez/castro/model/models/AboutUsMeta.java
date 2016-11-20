package com.ayoprez.castro.model.models;

import io.realm.RealmObject;

/**
 * Created by ayo on 21.08.16.
 */
public class AboutUsMeta extends RealmObject{

    private String description;
    private String share_text;
    private String number;
    private String email;
    private String share_image;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getShare_text() {
        return share_text;
    }

    public void setShare_text(String share_text) {
        this.share_text = share_text;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getShare_image() {
        return share_image;
    }

    public void setShare_image(String share_image) {
        this.share_image = share_image;
    }

}
